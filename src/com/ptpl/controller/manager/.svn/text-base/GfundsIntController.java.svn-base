package com.ptpl.controller.manager;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.GfundsInt;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserGrade;
import com.ptpl.service.GfundsIntServiceI;
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
 * @author：liuqh
 * @Description:标的站岗利息Controller层
 * @date 2016-7-12 上午10:49:33
 */
@Controller
@RequestMapping("/admin/gfundsInt")
public class GfundsIntController {
	@Autowired
	private GfundsIntServiceI gfundsIntService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private TenderItemServiceI tenderItemService;


	// 转发到增加站岗利息页面
	@RequestMapping(value = "/insert_GfundsInt_Ui/{tid}", method   = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_GfundsInt_Ui(@PathVariable String tid,HttpServletRequest request) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/gfundsInt/GfundsInt_inst");
		List<UserGrade> uGrades = userGradeService.getAll(null);//获取全部等级
		List<String> gradeList=null;
		if(tid!=null&&!tid.isEmpty()){
			gradeList=gfundsIntService.selectGradeByTid(new BigDecimal(tid)); //查找站岗利息表中已设置的会员等级
		}
		modelAndView=  UserGradeUtil.mv(uGrades,gradeList,modelAndView,tid);
		if(uGrades.size()<=0){
			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
			tenderItem.setIssetgfundsint((short)1);
			tenderItemService.update(tenderItem);
			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
		}
		return modelAndView;
	}
	//测试用添加
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/insertGfundsInttwo", method = {RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertGfundsInttwo(GfundsInt gfundsInt,String ugrades[],HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<UserGrade> userGrades=userGradeService.getAll(null);// 获取全部等级
		List<GfundsInt> diffSection=gfundsInt.getGfundsInts();//不同区间（最低值，最高值）的设置，一个区间对应一个实体
		String isAll=gfundsInt.getUgrade();//全部等级或者部分等级，存放1或者2
		String gradeStr=UserGradeUtil.changeString(isAll,ugrades,userGrades);//将所有等级转化为字符串
		gfundsInt.setUgrade(gradeStr);//将转化后的字符串设置到ugrade字段里
		String gfno=StringUtil.getNameNoForName(Marknumber.GFUNDSINT_NO);//生成编号
		for (int i = 0; i < diffSection.size(); i++) {//通过循环筛选后的数据进行赋值
			gfundsInt.setMinmoney(diffSection.get(i).getMinmoney());//最低值
			gfundsInt.setMaxmoney(diffSection.get(i).getMaxmoney());//最高值
			gfundsInt.setQuota(diffSection.get(i).getQuota());//定额
			if(diffSection.get(i).getDayawardrate()!=null&&!diffSection.get(i).getDayawardrate().equals("")){
				gfundsInt.setDayawardrate(diffSection.get(i).getDayawardrate()/100);//费率
			}
			gfundsInt.setMaxcompensate(diffSection.get(i).getMaxcompensate());//最高补偿金额
			gfundsInt.setGfundsintno(gfno);
			gfundsIntService.insert(gfundsInt);
		}

		List<String> gradeList=gfundsIntService.selectGradeByTid(gfundsInt.getTid());//该标已经设置的等级记录
		int count=UserGradeUtil.count(gradeList);//统计所有记录已设置的等级个数
		if(count<userGrades.size()){
			ModelAndView mv=new ModelAndView("redirect:/admin/gfundsInt/insert_GfundsInt_Ui/"+gfundsInt.getTid()+".action");
			return  mv;
		}//如果count小于所有等级个数，说明没有设置完，则重定向到转发页面的请求Url
		TenderItem tenderItem=tenderItemService.findTenderItemById(gfundsInt.getTid());
		tenderItem.setIssetgfundsint((short)1);
		tenderItemService.update(tenderItem);
		ModelAndView  mv=new ModelAndView("redirect:/admin/tenderItem/tenderItem_tag_UI/"+gfundsInt.getTid()+".action");
		return mv;
	}


	// 根据条件查找站岗利息并转发到站岗利息列表页面
	@RequestMapping(value = "/selectGfundsIntByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectGfundsIntByCondition(HttpServletRequest request,GfundsInt gfundsInt) {

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
		List<GfundsInt> GfundsIntList = gfundsIntService.selectByConditionAndDecorateUgrade(gfundsInt);
		PageInfo<GfundsInt> pagehelper = new PageInfo<GfundsInt>(GfundsIntList);
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
		// 指定视图
		modelAndView.setViewName("admin/gfundsInt/GfundsInt_List");
		PublicUtil.removeFormSession(request);
		return modelAndView;
	}



	// 根据id查找站岗利息并返回站岗利息的详细页面(详情)
	@RequestMapping(value = "/selectGfundsIntByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectGfundsIntByPrimaryKey(BigDecimal tid) {
		ModelAndView mv=new ModelAndView();
		if(tid!=null){
			GfundsInt gfundsInt= new GfundsInt();
			gfundsInt.setTid(tid);
			List<GfundsInt> gfundsInts = gfundsIntService.selectByConditionAndDecorateUgrade(gfundsInt);
			if(gfundsInts.size()>0){
				mv.addObject("gfundsInts", gfundsInts);
				mv.addObject("gfundsInt", gfundsInts.get(0));
			}
		}
		mv.setViewName("admin/gfundsInt/GfundsInt_Detail");
		return mv;
	}

	// 转发到修改站岗利息页面
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(BigDecimal tid) {
		ModelAndView mv=new ModelAndView();
		List<GfundsInt> gfundsInts = gfundsIntService.selectGfundBytid(tid);
		if(gfundsInts.size()>0){
			mv.addObject("GfundsInts", gfundsInts);
			mv.addObject("GfundsInt", gfundsInts.get(0));
		}
		/*GfundsInt gfundsInt1 =null;
		if(gfundsInts.size()>0){
			gfundsInt1 = gfundsInts.get(0);
		}
		String ugrade = gfundsInt1.getUgrade();
		ModelAndView modelAndView = new ModelAndView();

		PublicUtil.decorateGrade(modelAndView,userGradeService,ugrade,"ugrade","ugrades1");
		List<UserGrade> ugs = userGradeService.getAll(null);
		modelAndView.addObject("ugs",ugs);
		modelAndView.addObject("gfundsInt",gfundsInt1);*/
		mv.setViewName("admin/gfundsInt/GfundsInt_Update");
		return mv;
	}

	// 修改站岗利息
	@RequestMapping(value = "/updateGfundsInt", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateGfundsInt(GfundsInt gfundsInt,HttpServletRequest request) {
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			gfundsInt.setAddman(adminUser.getUsername());
		}
		gfundsInt.setAddtime(new Date());
		List<GfundsInt> gfundsInts=gfundsInt.getGfundsInts();
		for (int i = 0; i < gfundsInts.size(); i++) {
			gfundsInt.setMinmoney(gfundsInts.get(i).getMinmoney());//最低金额
			gfundsInt.setMaxmoney(gfundsInts.get(i).getMaxmoney());//最高金额
			gfundsInt.setQuota(gfundsInts.get(i).getQuota());//定额
			if(gfundsInts.get(i).getDayawardrate()!=null&&!gfundsInts.get(i).getDayawardrate().equals("")){
				gfundsInt.setDayawardrate(gfundsInts.get(i).getDayawardrate()/100);//百分比
			}
			gfundsInt.setMaxcompensate(gfundsInts.get(i).getMaxcompensate());//最高补偿金额
			gfundsInt.setId(gfundsInts.get(i).getId());//id
			gfundsIntService.update(gfundsInt);
		}
		return "redirect:/admin/gfundsInt/selectGfundsIntByCondition.action";
	}
	//删除
	@RequestMapping(value = "/deleteGfundsInt", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteGfundsInt(BigDecimal id,BigDecimal tid,HttpServletResponse response) throws IOException{
		PublicUtil.decideBeforeDelete(id,tid, response, tenderItemService,gfundsIntService);
	}


}
