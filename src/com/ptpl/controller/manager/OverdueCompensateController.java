package com.ptpl.controller.manager;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.OverdueCompensate;
import com.ptpl.model.OverdueFeeRate;
import com.ptpl.model.OverdueRecovery;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserGrade;
import com.ptpl.service.OverdueCompensateServiceI;
import com.ptpl.service.OverdueFeeRateServiceI;
import com.ptpl.service.OverdueRecoveryServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import com.ptpl.web.util.UserGradeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @author:liuqh
 * @date:2016年07月14日 14:13:02
 * @description:标的逾期代偿设置
 */
@Controller
@RequestMapping("/admin/overdueCompensate")
public class OverdueCompensateController extends BaseController{
	@Autowired
	private OverdueCompensateServiceI overdueCompensateService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private TenderItemServiceI tenderItemService;
	@Autowired
	private OverdueRecoveryServiceI overdueRecoveryService;
	@Autowired
	private OverdueFeeRateServiceI overdueFeeRateService;
	

	// 转发到增加页面(会员垫付(表1))
	@RequestMapping(value = "/insert_OverdueCompensate_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_OverdueCompensate_Ui(@PathVariable String tid) throws Exception{
		logger.info("*******************************:"+tid);
		ModelAndView modelAndView = new ModelAndView();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		modelAndView.setViewName("admin/overdueCompensate/OverdueCompensate_Inst");
		List<String> gradeList=null;
		if(tid!=null&&!tid.isEmpty()){
			gradeList=overdueCompensateService.selectGradeByTid(new BigDecimal(tid)); //查找表中已设置的会员等级
		}
		modelAndView=  UserGradeUtil.mv(uGrades,gradeList,modelAndView,tid);
		if(uGrades.size()<=0){
			List<OverdueFeeRate> overdueFeeRates = overdueFeeRateService.findOverdueFeeRatesByTid(new BigDecimal(tid));
			if(overdueFeeRates==null||overdueFeeRates.size()==0){
				modelAndView.setViewName("admin/overdueCompensate/OverdueFeeRate_Inst");
			}else {
				TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(tid));
				tenderItem.setIsaoverduec((short) 1);
				tenderItemService.update(tenderItem);
				modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + tid + ".action");
			}
		}

		return modelAndView;
	}
	//转发到平台追偿设置(平台追偿(表2))
	@RequestMapping(value = "/insert_overdueRecovery_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_overdueRecovery_Ui(@PathVariable String tid) {
		ModelAndView modelAndView = new ModelAndView();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		modelAndView.addObject("tid",tid);
		modelAndView.setViewName("admin/overdueCompensate/overdueRecovery_Inst");
		List<OverdueRecovery> overdueRecoveries = overdueRecoveryService.selectoverRecBytid(new BigDecimal(tid));
		if(overdueRecoveries!=null&&overdueRecoveries.size()>0){
			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
			tenderItem.setIsaocfee((short)1);
			tenderItemService.update(tenderItem);
			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
		}
		return modelAndView;
	}
	

	// 增加(标的逾期代偿设置)(表一)
	@RequestMapping(value = "/insertOverdueCompensate", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertOverdueCompensate(OverdueCompensate overdueCompensate,String[] ugrades,HttpServletRequest request,HttpServletResponse response) throws Exception {
		if(overdueCompensate.getUgrade()!=null&&!overdueCompensate.getUgrade().equals("")){//获取会员等级
			String ugrade=overdueCompensate.getUgrade();
			List<UserGrade> grades=userGradeService.getAll(null);
			if("1".equals(ugrade)){
				 ugrade=StringUtil.getPlaceholder(grades.size());
				 for (UserGrade userGrade : grades) {
					ugrade=StringUtil.setPlaceholder(ugrade, userGrade.getUgrade().intValue());
				}
			}else{
				 if(ugrades!=null){
					 ugrade=StringUtil.setPlaceholderForArr1(ugrades, grades.size());
				 }
			}
			overdueCompensate.setUgrade(ugrade);
		}
		overdueCompensate.setOverduecno(StringUtil.getNameNoForName(Marknumber.OVCOMPENSATE_NO));//生成编号
		if(overdueCompensate.getPfprincipalrate()!=null&&!overdueCompensate.getPfprincipalrate().toString().equals("")){
		   overdueCompensate.setPfprincipalrate(overdueCompensate.getPfprincipalrate()/100);//本金垫付比例
		}
		if(overdueCompensate.getPfintrate()!=null&&!overdueCompensate.getPfintrate().toString().equals("")){
		   overdueCompensate.setPfintrate(overdueCompensate.getPfintrate()/100);//利息垫付比例
		}
		if(overdueCompensate.getLatefeerate()!=null&&!overdueCompensate.getLatefeerate().toString().equals("")){
		   overdueCompensate.setLatefeerate(overdueCompensate.getLatefeerate()/100);//迟纳金垫付比例
		}
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			overdueCompensate.setAddman(adminUser.getUsername());
		}
		overdueCompensate.setAddTime(new Date());
		overdueCompensateService.insert(overdueCompensate);

		/*List<String> gradeList=overdueCompensateService.selectGradeByTid(overdueCompensate.getTid());//该标已经设置的等级记录
		int count=UserGradeUtil.count(gradeList);//统计所有记录已设置的等级个数*/
	/*	if(count<userGrades.size()){
			ModelAndView mv=new ModelAndView("redirect:/admin/gfundsInt/insert_GfundsInt_Ui/"+gfundsInt.getTid()+".action");
			return  mv;
		}*///如果count小于所有等级个数，说明没有设置完，则重定向到转发页面的请求Url
		//得到逾期代偿设置的编号并set进标的逾期代偿编号字段
/*		TenderItem tenderItem=tenderItemService.findTenderItemById(overdueCompensate.getTid());
		tenderItem.setIsaoverduec((short)2);
		tenderItemService.update(tenderItem);*/

		ModelAndView  mv=new ModelAndView("admin/overdueCompensate/OverdueFeeRate_Inst");
		mv.addObject("tid",overdueCompensate.getTid());
		return mv;
	}
	//添加(标的逾期平台追偿设置)(表二)
	@RequestMapping(value="/insertoverdueRecovery",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView insertoverdueRecovery(OverdueRecovery recovery,HttpServletRequest request,HttpServletResponse response) throws Exception{
		recovery.setOverduerno(StringUtil.getNameNoForName(Marknumber.OVRECOVERY_NO));//生成编号
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			recovery.setAddman(adminUser.getUsername());
		}
		recovery.setAddtime(new Date());//生成添加时间
		List<OverdueRecovery> list=recovery.getRecoveries();//获取前台数据
		for (int i = 0; i < list.size(); i++) {//去除无用的数据
			if(list.get(i).getOcminmoney()==null){//如果分段金额低值
				list.remove(i);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			recovery.setOcminmoney(list.get(i).getOcminmoney());//金额段低值
			recovery.setOcmaxmoney(list.get(i).getOcmaxmoney());//金额段高值
			recovery.setOccquota(list.get(i).getOccquota());//定额收费
			if(list.get(i).getToccrate()!=null&&!list.get(i).getToccrate().equals("")){
				recovery.setToccrate(list.get(i).getToccrate()/100);//百分比费率
			}
			recovery.setMincfees(list.get(i).getMincfees());//最低收费
			recovery.setMaxcfees(list.get(i).getMaxcfees());//最高收费
			overdueRecoveryService.insertSelective(recovery);
		  }
		//得到逾期代偿设置的编号并set进标的逾期代偿编号字段
		TenderItem tenderItem = tenderItemService.findTenderItemById(recovery.getTid());
		tenderItem.setIsaocfee((short) 1);
		tenderItemService.update(tenderItem);
		ModelAndView mv= new ModelAndView();
		mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + recovery.getTid() + ".action");
		return  mv;
	}
	
	//逾期滞纳金费率设置(表三)
	@RequestMapping(value="insertoverdueFeeRate",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView insertoverdueFeeRate(OverdueFeeRate overdueFeeRate,HttpServletRequest request,HttpServletResponse response) throws Exception{
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			overdueFeeRate.setAddman(adminUser.getUsername());//添加人
		}
		overdueFeeRate.setAddtime(new Date());//添加時間

		//BigDecimal tid=new BigDecimal(1524);
		String feeratetype=overdueFeeRate.getFeeratetype().toString();//获取费率计算方式
		 if(feeratetype!=null&&!feeratetype.equals("")){
			if(feeratetype.equals("1")){//等比例
			   if(overdueFeeRate.getFeerate()!=null&&!overdueFeeRate.getFeerate().equals("")){
				   overdueFeeRate.setFeerate(overdueFeeRate.getFeerate()/100);//迟纳金率
			   }
			   overdueFeeRateService.insertSelective(overdueFeeRate);
			}else{//2.不等比例
			   List<OverdueFeeRate> feeRates=overdueFeeRate.getOverduefeerates();//获取页面数据
			   for (int i = 0; i < feeRates.size(); i++) {
				   if(feeRates.get(i).getBeginday()==null){
					   feeRates.remove(i);
				   }
			 }
			   for (int i = 0; i < feeRates.size(); i++) {
				   overdueFeeRate.setBeginday(feeRates.get(i).getBeginday());//开始时间
				   overdueFeeRate.setEndday(feeRates.get(i).getEndday());//结束时间
				   if(feeRates.get(i).getFeerate()!=null&&!feeRates.get(i).getFeerate().equals("")){
					   overdueFeeRate.setFeerate(feeRates.get(i).getFeerate()/100);//迟纳金率
				   }
				   overdueFeeRateService.insertSelective(overdueFeeRate);
			 }
			}
		 }
		TenderItem tenderItem=tenderItemService.findTenderItemById(overdueFeeRate.getTid());
		tenderItem.setIsaoverduec((short)1);
		tenderItemService.update(tenderItem);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+overdueFeeRate.getTid()+".action");
			return  mv;
	}
	// 根据条件查找并转发到列表页面
	@RequestMapping(value = "/selectOverdueCompensateByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectOverdueCompensateByCondition(HttpServletRequest request,OverdueCompensate overdueCompensate) {

		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		int num = 1;
		int size = 9;
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		String sortString = "id.desc";
		Order.formString(sortString);

		PageHelper.startPage(num, size);

		
		
		// 调用service层的方法得到对象列表
		List<OverdueCompensate> OverdueCompensateList = overdueCompensateService.selectByConditionAndDecorateUgrade(overdueCompensate);
		PageInfo<OverdueCompensate> pagehelper = new PageInfo<OverdueCompensate>(OverdueCompensateList);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		// 把对象放进modelAndView中
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("overdueCompensate", overdueCompensate);
		// 指定视图
		modelAndView.setViewName("admin/overdueCompensate/OverdueCompensate_List");
		PublicUtil.removeFormSession(request);
		return modelAndView;
	}

	// 根据id查找并返回详细页面(查看详情)
	@RequestMapping(value = "/selectOverdueCompensateByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectOverdueCompensateByPrimaryKey(BigDecimal tid) {
		ModelAndView modelAndView = new ModelAndView();
		if(tid!=null){
			OverdueCompensate compensate=new OverdueCompensate();
			compensate.setTid(tid);
			List<OverdueCompensate> overdueCompensates = overdueCompensateService.selectByConditionAndDecorateUgrade(compensate);
			if(overdueCompensates.size()>0){
				modelAndView.addObject("overdueCompensates", overdueCompensates);
				modelAndView.addObject("overdueCompensate", overdueCompensates.get(0));
			}
		}
		modelAndView.setViewName("admin/overdueCompensate/OverdueCompensate_Detail");
		return modelAndView;
	}

	// 转发到修改页面
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(BigDecimal tid) {
		ModelAndView modelAndView = new ModelAndView();
		if(tid!=null){
			OverdueCompensate compensate=new OverdueCompensate();
			compensate.setTid(tid);
			List<OverdueCompensate> overdueCompensates = overdueCompensateService.selectByConditionAndDecorateUgrade(compensate);
			if(overdueCompensates.size()>0){
				modelAndView.addObject("overdueCompensates", overdueCompensates);
				modelAndView.addObject("overdueCompensate", overdueCompensates.get(0));
			}
		}
		modelAndView.setViewName("admin/overdueCompensate/OverdueCompensate_Update");
		return modelAndView;
		/*OverdueCompensate overdueCompensate = new OverdueCompensate();
		overdueCompensate.setId(new BigDecimal(id));
		List<OverdueCompensate> overdueCompensates = overdueCompensateService.selectByCondition(overdueCompensate);
		OverdueCompensate overdueCompensate1 =null;
		if(overdueCompensates.size()>0){
			overdueCompensate1 = overdueCompensates.get(0);
		}
		String ugrade = overdueCompensate1.getUgrade();
		ModelAndView modelAndView = new ModelAndView();
		PublicUtil.decorateGrade(modelAndView,userGradeService,ugrade,"ugrade","ugrades1");
		List<UserGrade> ugs = userGradeService.getAll(null);
		modelAndView.addObject("ugs",ugs);
		modelAndView.addObject("overdueCompensate",overdueCompensate1);
		modelAndView.setViewName("admin/overdueCompensate/OverdueCompensate_Update");
		return modelAndView;*/
	}

	// 修改
	@RequestMapping(value = "/updateOverdueCompensate", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateOverdueCompensate(OverdueCompensate overdueCompensate,HttpServletRequest request) {
	    AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
	    if(adminUser!=null){
	    	overdueCompensate.setAddman(adminUser.getUsername());//修改人
	    }
	    overdueCompensate.setAddTime(new Date());//修改时间
		overdueCompensateService.update(overdueCompensate);
		return "redirect:/admin/overdueCompensate/selectOverdueCompensateByCondition.action";
	}
	
	//删除
			@RequestMapping(value = "/deleteOverdueCompensate", method = { RequestMethod.POST, RequestMethod.GET })
			public void deleteOverdueCompensate(BigDecimal id,BigDecimal tid, String overduecno,HttpServletResponse response) throws IOException{
				PublicUtil.decideBeforeDelete(id,tid,response, tenderItemService,overdueCompensateService);
			}
}
