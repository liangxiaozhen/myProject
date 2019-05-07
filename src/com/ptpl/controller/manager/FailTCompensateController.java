package com.ptpl.controller.manager;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.FailTAwardCompensate;
import com.ptpl.model.FailTCompensate;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserGrade;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.FailTAwardCompensateServiceI;
import com.ptpl.service.FailTCompensateServiceI;
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
 * @date:2016年07月12日 11:06:22
 * @description:流标补偿设置
 */
@Controller
@RequestMapping("/admin/failTCompensate")
public class FailTCompensateController {
	@Autowired
	private FailTCompensateServiceI failTCompensateService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private TenderItemServiceI tenderItemService;
	@Autowired
	private AwardServiceI awardServiceI;
	@Autowired
	private FailTAwardCompensateServiceI failTAwardCompensateService;

	// 转发到增加页面(利息方式补偿)
	@RequestMapping(value = "/insert_FailTCompensate_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_FailTCompensate_Ui(@PathVariable String tid, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		List<String> gradeList=null;
		if(tid!=null){
			gradeList=failTCompensateService.selectGradeByTid(new BigDecimal(tid));
		}
		modelAndView.setViewName("admin/failTCompensate/FailTcompensate_Inst");
		UserGradeUtil.mv(uGrades,gradeList,modelAndView,tid);
		if(uGrades.size()<=0){
			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
			tenderItem.setIsintcompensateon((short)1);
			tenderItemService.update(tenderItem);
			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
		}
		return modelAndView;
	}
	// 转发到增加页面(奖品方式补偿)
	@RequestMapping(value = "/insert_FailTAwardCompensate_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_FailTAwardCompensate_Ui(@PathVariable String tid,HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		List<Award> awards=awardServiceI.selectByCondition(null);
		List<String> gradeList=null;
		if(tid!=null&&!tid.isEmpty()) {
			gradeList = failTAwardCompensateService.selectAugradesByid(new BigDecimal(tid));
		}
		modelAndView.setViewName("admin/failTCompensate/FailTAwardCompensate_Inst");
		modelAndView.addObject("awards",awards);
		UserGradeUtil.mv(uGrades,gradeList,modelAndView,tid);
		if(uGrades.size()<=0){
			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
			tenderItem.setIsawardcompensateon((short)1);
			tenderItemService.update(tenderItem);
			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
		}
		return modelAndView;
	}

	//标的流标补偿设置表(表—)
	@RequestMapping(value = "/insertFailTCompensate", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertFailTCompensate(FailTCompensate failTCompensate,String[] intugrades,HttpServletRequest request,HttpServletResponse response) throws Exception{
		failTCompensate.setFailtcno(StringUtil.getNameNoForName(Marknumber.FAILTC_NO));//生成编号
		BigDecimal tid=(BigDecimal) request.getSession().getAttribute("tenderItemId");//获取tid
		if(tid!=null){
			failTCompensate.setTid(tid);//设置tid
		}
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			failTCompensate.setAddman(adminUser.getUsername());//获取后台管理员
		}
		failTCompensate.setAddtime(new Date());//设置添加时间
		List<UserGrade> grades=userGradeService.getAll(null);//获取所有会员等级
		if(failTCompensate.getIntugrade()!=null&&!failTCompensate.getIntugrade().equals("")){//获取会员等级非空判断
			String isAll=failTCompensate.getIntugrade();//全部等级或者部分等级，存放1或者2
			String ugradeStr=UserGradeUtil.changeString(isAll,intugrades,grades);//将所有等级转化为字符串
			failTCompensate.setIntugrade(ugradeStr);
		}
		List<FailTCompensate> compensates=failTCompensate.getFailTCompensates();//获取页面的数据
		for (int i = 0; i < compensates.size(); i++) {
			if(compensates.get(i).getMinmoney()==null){
				compensates.remove(i);//去掉多余的数据
			}
		}
		for (int i = 0; i < compensates.size(); i++) {
			failTCompensate.setMinmoney(compensates.get(i).getMinmoney());//最低投资金额
			failTCompensate.setMaxmoney(compensates.get(i).getMaxmoney());//最高投资金额
			failTCompensate.setQuota(compensates.get(i).getQuota());//定额
			if(compensates.get(i).getDayawardrate()!=null&&!compensates.get(i).getDayawardrate().equals("")){
				failTCompensate.setDayawardrate(compensates.get(i).getDayawardrate()/100);//日奖励费率
			}
			failTCompensate.setMaxcompensate(compensates.get(i).getMaxcompensate());//最高补偿金额
			failTCompensateService.insert(failTCompensate);
		}

		List<String> gradeList=failTCompensateService.selectGradeByTid(failTCompensate.getTid());
		int count=UserGradeUtil.count(gradeList);//获取已设置的等级个数
		if(count<grades.size()){
			ModelAndView mv=new ModelAndView("redirect:/admin/failTCompensate/insert_FailTCompensate_Ui.action");
			return  mv;
		}
		TenderItem tenderItem=tenderItemService.findTenderItemById(failTCompensate.getTid());
		tenderItem.setIsintcompensateon((short)1);
		tenderItemService.update(tenderItem);
		ModelAndView  mv=new ModelAndView("redirect:/admin/tenderItem/tenderItem_tag_UI/"+failTCompensate.getTid()+".action");
		return mv;
	}

	// 增加(标的流标奖品补偿设置(表二))
	@RequestMapping(value = "/insertfailTAwardCompensate", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertfailTAwardCompensate(FailTAwardCompensate awardCompensate,String[] awardugrades,HttpServletRequest request,HttpServletResponse response) throws Exception {
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);//获取session中已登录的用户
		if(adminUser!=null){
			awardCompensate.setAddman(adminUser.getUsername());//设置添加人
		}
		awardCompensate.setFailtacno(StringUtil.getNameNoForName(Marknumber.FAILTCAWARD_NO));//生成编号
		awardCompensate.setAddtime(new Date());//设置添加时间
		List<UserGrade> grades=userGradeService.getAll(null);//查询所有的等级
		if(awardCompensate.getAwardugrade()!=null&&!awardCompensate.getAwardugrade().equals("")){//判断会员等级是否为空
			String isAll=awardCompensate.getAwardugrade();//全部等级或者部分等级，存放1或者2
			String ugradeStr=UserGradeUtil.changeString(isAll,awardugrades,grades);//将所有等级转化为字符串
			awardCompensate.setAwardugrade(ugradeStr);//保存会员等级字段
		}
		List<FailTAwardCompensate> awards=awardCompensate.getAwardCompensates();//获取前台数据
		for (int i = 0; i < awards.size(); i++) {
			if(awards.get(i).getMinmoneyaward()==null){
				awards.remove(i);//去除无数据的集合
			}
		}
		for (int i = 0; i < awards.size(); i++) {//添加
			awardCompensate.setMinmoneyaward(awards.get(i).getMinmoneyaward());//最低金额
			awardCompensate.setMaxmoneyaward(awards.get(i).getMaxmoneyaward());//最高金额
			if(awards.get(i).getAwardno()!=null&&!awards.get(i).getAwardno().equals("")){
				awardCompensate.setAwardno(awards.get(i).getAwardno().split(",")[0]);//奖品编号
				awardCompensate.setAwardname(awards.get(i).getAwardno().split(",")[1]);//奖品名称
			}
			failTAwardCompensateService.insertSelective(awardCompensate);
		}
		//如果是通过设置标过来的，就会有tenderItemId
		List<String> gradeList = failTAwardCompensateService.selectAugradesByid(awardCompensate.getTid());
		//如果urlList.size的长度为0，则表示，此刻保存的是最后一个标相关的设置，则把标状态改为审核中
		int count=UserGradeUtil.count(gradeList);//获取已设置的等级个数
		if(count<grades.size()){
			ModelAndView mv=new ModelAndView("redirect:/admin/failTCompensate/insert_FailTAwardCompensate_Ui.action");
			return  mv;
		}
		TenderItem tenderItem=tenderItemService.findTenderItemById(awardCompensate.getTid());
		tenderItem.setIsawardcompensateon((short)1);
		tenderItemService.update(tenderItem);
		ModelAndView  mv=new ModelAndView("redirect:/admin/tenderItem/tenderItem_tag_UI/"+awardCompensate.getTid()+".action");
		return mv;
	}



	// 根据条件查找并转发到列表页面
	@RequestMapping(value = "/selectFailTCompensateByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectFailTCompensateByCondition(HttpServletRequest request,FailTCompensate failTCompensate) {

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
		List<FailTCompensate> FailTCompensateList = failTCompensateService.selectByConditionAndDecorateUgrade(failTCompensate);
		PageInfo<FailTCompensate> pagehelper = new PageInfo<FailTCompensate>(FailTCompensateList);
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
		modelAndView.addObject("failTCompensate", failTCompensate);
		// 指定视图
		modelAndView.setViewName("admin/failTCompensate/FailTCompensate_List");
		PublicUtil.removeFormSession(request);
		return modelAndView;
	}

	// 根据id查找并返回详细页面(查看详情)
	@RequestMapping(value = "/selectFailTCompensateByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectFailTCompensateByPrimaryKey(BigDecimal tid) {
		ModelAndView mv=new ModelAndView();
		if(tid!=null){
			FailTCompensate compensate=new FailTCompensate();
			compensate.setTid(tid);
			List<FailTCompensate> failTCompensates = failTCompensateService.selectByConditionAndDecorateUgrade(compensate);
			if(failTCompensates.size()>0){
				mv.addObject("failTCompensates", failTCompensates);
				mv.addObject("failTCompensate", failTCompensates.get(0));
			}
		}
		mv.setViewName("admin/failTCompensate/FailTCompensate_Detail");
		return mv;
	}

	// 转发到修改页面
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(BigDecimal tid) {
		ModelAndView mv=new ModelAndView();
		if(tid!=null){
			FailTCompensate compensate=new FailTCompensate();
			compensate.setTid(tid);
			List<FailTCompensate> failTCompensates = failTCompensateService.selectByConditionAndDecorateUgrade(compensate);
			if(failTCompensates.size()>0){
				mv.addObject("failTCompensates", failTCompensates);
				mv.addObject("failTCompensate", failTCompensates.get(0));
			}
		}
		mv.setViewName("admin/failTCompensate/FailTCompensate_Update");
		return mv;
		/*FailTCompensate failTCompensate = new FailTCompensate();
		failTCompensate.setId(new BigDecimal(id));
		List<FailTCompensate> failTCompensates = failTCompensateService.selectByCondition(failTCompensate);
		FailTCompensate failTCompensate1 =null;
		if(failTCompensates.size()>0){
			failTCompensate1 = failTCompensates.get(0);
		}
		ModelAndView modelAndView = new ModelAndView();
		String ugrade = failTCompensate1.getIntugrade();
		
		PublicUtil.decorateGrade(modelAndView,userGradeService,ugrade,"ugrade","ugrades1");
		List<UserGrade> ugs = userGradeService.getAll(null);
		modelAndView.addObject("ugs",ugs);
		modelAndView.addObject("failTCompensate", failTCompensate1);
		modelAndView.setViewName("admin/failTCompensate/FailTCompensate_Update");
		return modelAndView;*/
	}

	// 修改
	@RequestMapping(value = "/updateFailTCompensate", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateFailTCompensate(FailTCompensate failTCompensate,HttpServletRequest request) {
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			failTCompensate.setAddman(adminUser.getUsername());//修改人
		}
		failTCompensate.setAddtime(new Date());//修改时间
		List<FailTCompensate> compensates=failTCompensate.getFailTCompensates();
		for (int i = 0; i < compensates.size(); i++) {
			failTCompensate.setMinmoney(compensates.get(i).getMinmoney());//最低金额
			failTCompensate.setMaxmoney(compensates.get(i).getMaxmoney());//最高金额
			failTCompensate.setQuota(compensates.get(i).getQuota());//定额
			if(failTCompensate.getDayawardrate()!=null&&failTCompensate.getDayawardrate().equals("")){
				failTCompensate.setDayawardrate(compensates.get(i).getDayawardrate()/100);//百分比
			}
			failTCompensate.setMaxcompensate(compensates.get(i).getMaxcompensate());//最高补偿金额
			failTCompensate.setId(compensates.get(i).getId());//id
			failTCompensateService.update(failTCompensate);
		}
		return "redirect:/failTCompensate/selectFailTCompensateByCondition.action";
	}
	//删除
	@RequestMapping(value = "/deleteFailTCompensate", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteFailTCompensate(BigDecimal id,BigDecimal tid,HttpServletResponse response) throws IOException{
		PublicUtil.decideBeforeDelete(id,tid, response, tenderItemService,failTCompensateService);
	}

}
