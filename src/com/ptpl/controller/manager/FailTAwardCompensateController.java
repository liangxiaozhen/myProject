package com.ptpl.controller.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.FailTAwardCompensate;
import com.ptpl.model.GfundsInt;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserGrade;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.FailTAwardCompensateServiceI;
import com.ptpl.service.GfundsIntServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import com.ptpl.web.util.UserGradeUtil;
/**
 * @description:流标奖品补偿
 */
@Controller
@RequestMapping("/admin/failTAwardCompensate")
public class FailTAwardCompensateController {
	@Autowired
	private GfundsIntServiceI gfundsIntService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private TenderItemServiceI tenderItemService;
	@Autowired
	private AwardServiceI awardService;
	@Autowired
	private FailTAwardCompensateServiceI failTAwardCompensateService;
	
	// 根据条件查找并转发到列表页面
	@RequestMapping(value = "/getFailTAwardCompensateByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectFailTAwardCompensateByCondition(HttpServletRequest request,FailTAwardCompensate failTAwardCompensate) {

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
		List<FailTAwardCompensate> failTAwardCompensateList = failTAwardCompensateService.selectByConditionAndDecorateUgrade(failTAwardCompensate);
		PageInfo<FailTAwardCompensate> pagehelper = new PageInfo<FailTAwardCompensate>(failTAwardCompensateList);
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
		modelAndView.addObject("FailTAwardCompensate", failTAwardCompensate);
		// 指定视图
		modelAndView.setViewName("admin/failTAwardCompensate/FailTAwardCompensate_List");
		PublicUtil.removeFormSession(request);
		return modelAndView;
	}

	// 根据条件查找并转发到列表页面
		@RequestMapping(value = "/getFailTAwardCompensateByPager", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView getFailTAwardCompensateByPager(HttpServletRequest request,FailTAwardCompensate failTAwardCompensate) {

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
			List<FailTAwardCompensate> failTAwardCompensateList = failTAwardCompensateService.selectByConditionAndDecorateUgrade(failTAwardCompensate);
			PageInfo<FailTAwardCompensate> pagehelper = new PageInfo<FailTAwardCompensate>(failTAwardCompensateList);
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
			modelAndView.addObject("FailTAwardCompensate", failTAwardCompensate);
			// 指定视图
			modelAndView.setViewName("admin/failTAwardCompensate/FailTAwardCompensate_List");
			PublicUtil.removeFormSession(request);
			return modelAndView;
		}
		
		// 
		@RequestMapping(value = "/add/FailTAwardCompensate", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView addFailTAwardCompensateFn() {
			ModelAndView modelAndView = new ModelAndView();
			List<UserGrade> uGrades = userGradeService.getAll(null);
			modelAndView.addObject("uGrades",uGrades);
			modelAndView.setViewName("admin/failTAwardCompensate/addFailTAwardCompensateTWO");
			return modelAndView;
		}
		
		//标的流标奖品补偿
				@RequestMapping(value = "/addNewFailTAwardCompensates", method = { RequestMethod.POST, RequestMethod.GET })
				public ModelAndView addNewFailTAwardCompensates(FailTAwardCompensate failTAwardCompensate,String[] awardugrades,HttpServletRequest request,HttpServletResponse response) throws Exception{
					List<UserGrade> userGrades = userGradeService.getAll(null);  
					List<FailTAwardCompensate> failTAwardCompensateList=failTAwardCompensate.getAwardCompensates();
					String gfugrades = failTAwardCompensate.getAwardugrade();//定义一个变量接收选择的等级
			        if (gfugrades != null && !gfugrades.equals("")) {
			            if (gfugrades.equals("1")) {//全部等級
			                gfugrades = StringUtil.getPlaceholder(userGrades.size());//生成长度为30位的字符串0000000....
			                for (UserGrade grade : userGrades) {
			                    gfugrades = StringUtil.setPlaceholder(gfugrades, grade.getUgrade().intValue());//转换成对应的符串
			                }
			            }
			            if (gfugrades.equals("2")) {//部分等級
			                if (awardugrades != null && awardugrades.length > 0) {
			                    gfugrades = StringUtil.setPlaceholderForArr1(awardugrades, userGrades.size());//转成对应的字符串
			                }
			            }
			            failTAwardCompensate.setAwardugrade(gfugrades);//将转成后的字符串添加
			        }
			        AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			    	  if(adminUser!=null){
			    		  failTAwardCompensate.setAddman(adminUser.getUsername());//获取后台管理员
			    	  }
			        String gfno = StringUtil.getNameNoForName(Marknumber.FAILTC_NO);//生成编号
			        failTAwardCompensate.setFailtacno(gfno);
			        List<FailTAwardCompensate> gflists = new ArrayList<>();//定义 一个list存储筛选后的数据
			        for (FailTAwardCompensate failTAwardCompensate2 : failTAwardCompensateList) {
			            if (failTAwardCompensate2.getMinmoneyaward() != null) {
			                gflists.add(failTAwardCompensate2);
			            }
			        }
			        BigDecimal tid=(BigDecimal) request.getSession().getAttribute("tenderItemId");//获取tid;
			        if(tid!=null){
			        	failTAwardCompensate.setTid(tid);//设置tid
			        }
			 		List<String> gradeList=failTAwardCompensateService.selectAugradesByid(tid);
			 		int count=UserGradeUtil.count(gradeList);//获取已设置的等级个数
			 		if(count<userGrades.size()){
			 			ModelAndView mv=new ModelAndView("redirect:/admin/failTAwardCompensate/add/FailTAwardCompensate.action");
			 			return  mv;
			 		}else{
			 			for (int i = 0; i < gflists.size(); i++) {//通过循环筛选后的数据进行赋值
				        	failTAwardCompensate.setMinmoneyaward(gflists.get(i).getMinmoneyaward());//最低值
				        	failTAwardCompensate.setMaxmoneyaward(gflists.get(i).getMaxmoneyaward());//最高值
				            failTAwardCompensate.setAwardno(gflists.get(i).getAwardno());
				            failTAwardCompensate.setAwardcopies(gflists.get(i).getAwardcopies());//最高补偿金额
				            Date dt=new Date();
				            failTAwardCompensate.setAddtime(dt);
				            /*FailTAwardCompensate.setGfundsintno(gfno);
				            gfundsIntService.insert(FailTAwardCompensate);*/
				            /*if (gflists.get(i).getDayawardrate() != null && !gflists.get(i).getDayawardrate().equals("")) {
				            	FailTAwardCompensate.setDayawardrate(gflists.get(i).getDayawardrate() / 100);//费率
				            }
				            FailTAwardCompensate.setMaxcompensate(gflists.get(i).getMaxcompensate());//最高补偿金额
				            FailTAwardCompensate.setGfundsintno(gfno);*/
				            /*FailTAwardCompensateService.insert(FailTAwardCompensate);*/
				            failTAwardCompensateService.insert(failTAwardCompensate);
				        }
			 		}
			        return new ModelAndView("redirect:/admin/failTAwardCompensate/getFailTAwardCompensateByCondition.action");
				}
				
				// 转发到增加页面(利息方式补偿)
				@RequestMapping(value = "/selectFailTAwardCompensateByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
				public void selectFailTAwardCompensateByPrimaryKey(HttpServletRequest request,HttpServletResponse response) throws IOException {
					String id=(String) request.getParameter("id");
					FailTAwardCompensate failTAwardCompensate=failTAwardCompensateService.selectByPrimaryKey(new BigDecimal(id));
					StringUtil.sendJsonData(response, JSON.toJSONString(failTAwardCompensate));
					/*PrintWriter out =response.getWriter();
					out.println(x);*/
				}
				
				// 转发到增加页面(利息方式补偿)
				@RequestMapping(value = "/updateFailTAwardCompensate", method = { RequestMethod.POST, RequestMethod.GET })
				public void updateFailTAwardCompensate(HttpServletRequest request,HttpServletResponse response) throws IOException {
					/*String minMoney=(String) request.getParameter("minMoney");
					String maxMoney=(String) request.getParameter("maxMoney");
					FailTAwardCompensate fAwardCompensate=new FailTAwardCompensate();
					fAwardCompensate.setMinmoneyaward(new Double(minMoney));
					fAwardCompensate.setMaxmoneyaward(new Double(maxMoney));*/
					String id=(String) request.getParameter("id");
					FailTAwardCompensate failTAwardCompensate=failTAwardCompensateService.selectByPrimaryKey(new BigDecimal(id));
					StringUtil.sendJsonData(response, JSON.toJSONString(failTAwardCompensate));
					/*PrintWriter out =response.getWriter();
					out.println(x);*/
				}
				
}
