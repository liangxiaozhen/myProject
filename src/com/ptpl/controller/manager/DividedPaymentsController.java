package com.ptpl.controller.manager;
 
import java.math.BigDecimal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.huishang.util.HSignUtil;
import com.ptpl.constant.RepayMent_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.HSRepayMentFreezeController;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepaymentDetail;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.RepayMentBaseDealI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.RepaymentDetailServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.ThirdRepayMentDealI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: DividedPaymentsController 
* @Package com.ptpl.controller 
* @Description: TODO(标的分期还款计划 控制层 ) 
* @author chenjiaming
* @date 2016年10月17日 11:56:45
* @version V1.0
 */
@Controller
@RequestMapping("/admin/dividedPayments")
public class DividedPaymentsController extends BaseController{
	
	 @Autowired
	 private DividedPaymentsServiceI dividedPaymentsService;
	 
	 @Autowired
	 private TenderItemServiceI tenderItemServiceI;
	 
	 @Autowired
	 private  RepayMentServiceI repayMentServiceI;
	 
	 @Autowired
	 private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	 
	 @Autowired
	 private  ThirdRepayMentDealI thirdRepayMentDealI;
 		
	 @Autowired
	 private RepayMentBaseDealI repayMentBaseDealI;//还款通用处理接口
	 
	 @Autowired
	 private  RepaymentDetailServiceI  repaymentDetailServiceI;
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(标的分期还款计划查询通用方法) 
	 * @param @param dividedPayments
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(DividedPayments dividedPayments){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<DividedPayments> dividedPaymentss = dividedPaymentsService.findDividedPaymentsByRepayMentAudit(dividedPayments);
       	 TenderItem tenderItem = null;
       	 for(DividedPayments dividedPayments2 : dividedPaymentss){
       		  tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments2.getTenderid());
       		  if(tenderItem != null){
       			dividedPayments2.setTenderItem(tenderItem);
       		  }
       	 }
      	 PageInfo<DividedPayments> pagehelper = new PageInfo<DividedPayments>(dividedPaymentss);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/dividedPayments/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(标的分期还款计划模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param dividedPayments
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,DividedPayments dividedPayments){
		 String pageS = request.getParameter("pageSize");
		 String pageNo = request.getParameter("pageNo");
    	 int num = 1;
		 int pageSize = 20;
		 if(StringUtil.isNotEmpty(pageS)){
			 pageSize = Integer.parseInt(pageS);
		 }
		 if(StringUtil.isNotEmpty(pageNo)){
			 num = Integer.parseInt(pageNo);
		 }
		 //根据Id排序
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 
		 //查询全部用户方法
       	 List<DividedPayments> dividedPaymentss = dividedPaymentsService.findDividedPaymentsByRepayMentAudit(dividedPayments);
       	 TenderItem tenderItem = null;
       	 for(DividedPayments dividedPayments2 : dividedPaymentss){
       		  tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments2.getTenderid());
       		  if(tenderItem != null){
       			dividedPayments2.setTenderItem(tenderItem);
       		  }
       	 }
      	 PageInfo<DividedPayments> pagehelper = new PageInfo<DividedPayments>(dividedPaymentss);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/dividedPayments/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	  
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看标的分期还款计划详情信息) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/detail")
	 public ModelAndView detail(HttpServletRequest request){
 		 String opid = request.getParameter("opid");
 		 if(StringUtil.isNotEmpty(opid)){
 			 ModelAndView modelAndView = new ModelAndView();
 			 DividedPayments dividedPayments2 = new DividedPayments();
 			 dividedPayments2.setId(new BigDecimal(opid));
  		     DividedPayments  dividedPayments = dividedPaymentsService.findDividedPaymentsByRepayMentAudit(dividedPayments2).get(0);
   		     modelAndView.addObject("dividedPayments", dividedPayments);
  		     modelAndView.setViewName("admin/dividedPayments/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	 
	 /**
	  * 
	 * @Title: installOfflinePayment 
	 * @Description: TODO(设置线下还款) 
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 @RequestMapping(value = "/installOfflinePayment",method = {RequestMethod.GET,RequestMethod.POST})
	 public String installOfflinePayment (HttpServletRequest request ,HttpServletResponse response){
		 String opid = request.getParameter("opid");
		 Map<String,String> hashMap = new HashMap<>();
		 if(StringUtil.isEmpty(opid)){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "参数错误！opid 信息找不到！请重新操作！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		 DividedPayments dividedPayments = dividedPaymentsService.findDividedPaymentsById(new BigDecimal(opid));
		 if(dividedPayments == null || dividedPayments.getIscomplete() == null){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "因网络响应不及时,借款人还款计划信息找不到,请重新操作！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		boolean checkFalg =  repayMentBaseDealI.checkDividedPaymentsNormalRepayMent(dividedPayments);
		 if(!checkFalg){
	 			hashMap.put("result", "fail");
				hashMap.put("Msg", "操作失败！不能跳期设置线下还款!");
				String str = JSON.toJSONString(hashMap);
				 try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
	 				e.printStackTrace();
				}
				 return null;
		} 
		 
		 if(dividedPayments.getIscomplete().equals((short)1)){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "操作失败！本期已还款！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
	 
 		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tenderid", dividedPayments.getTenderid());//标号ID
		map.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
		map.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
		map.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
 	    List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(map);
 	    if(!(repayMents.size() > 0)){
 	    	hashMap.put("result", "fail");
			 hashMap.put("Msg", "操作失败！因网络异常,没有找到投资人还款计划信息！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 return null;
 	    }
  	    
 	    for(RepayMent ment : repayMents){
    		ment.setRmode((short)3);/* 还款模式（1人工，2系统，3线下） */
    		ment.setRemark("已线下还款！线下还款前的还款状态是："+ment.getRepaystatus());
    		ment.setRmode((short)3);//还款模式（0初始  1人工，2系统，3线下） 
    		ment.setRepaystatus((short)5);
    		repayMentServiceI.updateById(ment);
  	    }
 	    
 	   dividedPayments.setIscomplete((short)1);/*是否还款完成0未还款 1已还款 2处理中 3部分还款(标的截标时生成时默认0)*/
 	   dividedPayments.setRemark("已线下还款！线下还款前的还款状态是："+dividedPayments.getIscomplete());
 	   int count = 0;
 	   count = dividedPaymentsService.updateById(dividedPayments);
  	   if(count > 0){
 		  hashMap.put("result", "success");
			 hashMap.put("Msg", "设置线下还款成功！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 return null;
 	   }else{
 		  hashMap.put("result", "fail");
			 hashMap.put("Msg", "操作失败！因网络异常,设置线下还款失败！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			 return null;
 	   }
 	 }
	 
	 
	 /**
	  * 
	 * @Title: normalCompensatoryRepayMent 
	 * @Description: TODO(正常代偿还款) 
	 * @param @param request
	 * @param @param response
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 @RequestMapping("/normalCompensatory")
	 public String normalCompensatory(HttpServletRequest request,HttpServletResponse response){
		 String opid = request.getParameter("opid");
		 Map<String,String> hashMap = new HashMap<>();
		 if(StringUtil.isEmpty(opid)){
			 hashMap.put("Msg", "操作失败！参数错误！opid 信息找不到！");
			 hashMap.put("result", "fail");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		 DividedPayments dividedPayments = dividedPaymentsService.findDividedPaymentsById(new BigDecimal(opid));
		 if(dividedPayments == null || dividedPayments.getTenderid() == null){
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！借款人还款计划信息 'dividedPayments' 不能为空！");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		boolean checkFalg =  repayMentBaseDealI.checkDividedPaymentsNormalRepayMent(dividedPayments);
		 if(!checkFalg){
	 			hashMap.put("result", "fail");
				hashMap.put("Msg", "正常代偿还款处理失败！不能跳期代偿还款！");
				String str = JSON.toJSONString(hashMap);
				 try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
	 				e.printStackTrace();
				}
				 return null;
		} 
		
		
		 TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments.getTenderid());
 		 if(tenderItem == null){
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的信息'TenderItem' 不能为空！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		}
  		 
  		if(tenderItem.getIscompensatory() == null){//正常还款代偿开关(1:开,0:关)
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的正常还款代偿开关 不能为空！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
 		}
 		  
  		if(!tenderItem.getIscompensatory().equals((short)1)){
  			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的正常还款代偿开关 未开启！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
  		}
  		
 		if(tenderItem.getCompensatoryman() == null){//代偿人电子账号
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "正常代偿还款处理失败！标的代偿人电子账号 不能为空！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
 		 
  		if(tenderItem.getIsapartrepay() == null){//是否支持部分还款 1是 0否
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "正常代偿还款处理失败！标的是否支持部分还款 不能为空！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
  		
  		if(tenderItem.getRepaymenttype() == null){//还款类型（1及时，2冻结还款）
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "正常代偿还款处理失败！标的还款类型不能为空！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
 		 
 		 
 		 if(tenderItem.getIsapartrepay().equals((short)1)){//允许部分还款
 			hashMap.put("result", "part");
 			hashMap.put("opid", opid);
  			hashMap.put("Msg", "允许部分还款");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		 }else{
	  			Map<String,Object> maps = new HashMap<>();
	 	  		maps.put("tenderid", dividedPayments.getTenderid());//标号ID
	 	  		maps.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
	 	  		maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
	 	  		maps.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
 	 	 		List<RepayMent> repayMents  = repayMentServiceI.findListRepayMentByConditions(maps);
	 	 		 
	 	 		if(!(repayMents.size() > 0 )){
	 	 			hashMap.put("result", "fail");
	 	 			hashMap.put("Msg", "未找到可以还款的投资人还款计划信息！");
	 	 			String str = JSON.toJSONString(hashMap);
	 	 			try {
	 	 				StringUtil.sendJsonData(response, str);
	 	 			} catch (IOException e) {
	 	 				e.printStackTrace();
	 	 			}
	 	 			return null;
	 	 		}
	 			 
	 	 		String rbatchno = StringUtil.getNo();//还款批次号
		  		Double totalamount = 0.00;
		  		for(RepayMent repayMent2 : repayMents){
		  			totalamount = Double.valueOf(df1.format(repayMent2.getRamount() + repayMent2.getRvoucher() + repayMent2.getDisablevoucher()
		  					+ repayMent2.getRinterest() + repayMent2.getRvoucherint() + repayMent2.getDisablevoucherint() + totalamount));
		  		}
 		  		
		  		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(tenderItem.getCompensatoryman(),totalamount.toString(), tenderItem.getTno(), rbatchno,(short)0,(short)1,(short)0);
				boolean fg = (boolean) resMap.get(RepayMent_Constant.FALG);
 				if(!fg){
  					hashMap.put("result", "fail");
					hashMap.put("Msg", resMap.get(RepayMent_Constant.MSG)+",请重新操作或联系客服！");
 					String str = JSON.toJSONString(hashMap);
					try {
						StringUtil.sendJsonData(response, str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				
 				Date submittime = new Date();//提交时间
  				for(RepayMent repayMent2 : repayMents){//更新状态
  					repayMent2.setIsproxyrepay((short)1);//是否代偿（1是，0否）
  					repayMent2.setIsahead((short)0);//是否提前（0否，1是）
  					repayMent2.setSubmittime(submittime);//借款人提交还款时间  注这个时间同批次提交的时候添加必须一致  
  					repayMent2.setAuditman("系统");//审核人  及时还款 写系统
  					repayMent2.setAudittime(submittime);//审核时间 
  					repayMent2.setRbatchno(rbatchno);//还款批次号
  					repayMent2.setRmode((short)1);//还款模式（0初始  1人工，2系统，3线下） 
  		    		repayMentServiceI.updateById(repayMent2);
  		     		
  		    		repayMentBaseDealI.calculateNormalRepayemInterestManageFee(repayMent2);//计算利息管理费并更新
   		    		
  		     		RepaymentDetail repaymentDetail = repayMentBaseDealI.copyRepaymentDetailByRepayMent(repayMent2);//生成快照
  		    		if(repaymentDetail != null){
  		    			repaymentDetailServiceI.insertSelective(repaymentDetail);//添加批次还款详情快照
  		    		}
   				}
 		  		
		  		Map<String,Object> resultMap = thirdRepayMentDealI.settingUpBatchNormalCompensatoryRepayMent(repayMents, rbatchno, tenderItem.getCompensatoryman(), totalamount.toString(), tenderItem.getTno());
			  	boolean falg = 	(boolean) resultMap.get(RepayMent_Constant.FALG);
 			  	if(falg){
			  		hashMap.put("result", "success");
					hashMap.put("Msg", "提示：代偿还款提交成功！");
			  		String str = JSON.toJSONString(hashMap);
					try {
						StringUtil.sendJsonData(response, str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
			  	}else{
			  		hashMap.put("result", "fail");
					hashMap.put("Msg", "提示：操作失败,因网络响应不及时,代偿还款提交失败！");
			  		String str = JSON.toJSONString(hashMap);
					try {
						StringUtil.sendJsonData(response, str);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
			  	}
  		 }
 		 
	 }
	 
	 /**
	  * 
	 * @Title: normalCompensatoryPartRepayMent 
	 * @Description: TODO(部分代偿 (这里是允许部分还款)) 
	 * @param @param response
	 * @param @param request
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 @RequestMapping("/normalCompensatoryPartRepayMent")
	 public String normalCompensatoryPartRepayMent(HttpServletResponse response,HttpServletRequest request){
		 String opid = request.getParameter("opid");
		 Map<String,Object> hashMap = new HashMap<>();
		 if(StringUtil.isEmpty(opid)){
			 hashMap.put("Msg", "操作失败！参数错误！opid 信息找不到！");
			 hashMap.put("result", "fail");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		 DividedPayments dividedPayments = dividedPaymentsService.findDividedPaymentsById(new BigDecimal(opid));
		 if(dividedPayments == null || dividedPayments.getTenderid() == null){
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！借款人还款计划信息 'dividedPayments' 不能为空！");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		 TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments.getTenderid());
 		 if(tenderItem == null){
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的信息'TenderItem' 不能为空！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		}
  		 
  		if(tenderItem.getIscompensatory() == null){//正常还款代偿开关(1:开,0:关)
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的正常还款代偿开关 不能为空！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
 		}
 		  
  		if(!tenderItem.getIscompensatory().equals((short)1)){
  			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的正常还款代偿开关 未开启！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
  		}
  		
 		if(tenderItem.getCompensatoryman() == null){//代偿人电子账号
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "正常代偿还款处理失败！标的代偿人电子账号 不能为空！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
 		 
  		if(tenderItem.getIsapartrepay() == null){//是否支持部分还款 1是 0否
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "正常代偿还款处理失败！标的是否支持部分还款 不能为空！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
  		
  		if(tenderItem.getRepaymenttype() == null){//还款类型（1及时，2冻结还款）
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "正常代偿还款处理失败！标的还款类型不能为空！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
  		 int num = 1;
		 int pageSize = 20;
 		 //根据Id排序
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 
  		Map<String,Object> maps = new HashMap<>();
  		maps.put("tenderid", dividedPayments.getTenderid());//标号ID
  		maps.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
  		maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
  		maps.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
 		List<RepayMent> repayMents  = repayMentServiceI.findListRepayMentByConditions(maps);
 		 
 		if(!(repayMents.size() > 0 )){
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "未找到可以还款的投资人还款计划信息！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
 		UserBaseAccountInfo userinaccountid = null;//投资用户
 		for(RepayMent ment : repayMents){
 			userinaccountid = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(ment.getInaccountid());
 			if(userinaccountid != null && userinaccountid.getLoginname() != null){
 				ment.setIntname(getDecrypt(userinaccountid.getLoginname()));
 			}
 			ment.setCount(Double.valueOf(df1.format(ment.getRamount() + ment.getRinterest() 
 					+ ment.getRvoucher() + ment.getRvoucherint()
 					+ ment.getDisablevoucher() + ment.getDisablevoucherint())));
 		}
 		 
		 PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	
      	hashMap.put("result", "success");
		hashMap.put("Msg", "查询成功");
		hashMap.put("detail", pagehelper);
		hashMap.put("opid", opid);
		
		String str = JSON.toJSONString(hashMap);
 		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
 	 }
	 
	 /**
	  * 
	 * @Title: NormalComponentTemplate 
	 * @Description: TODO(正常代偿分页) 
	 * @param @param response
	 * @param @param request
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 @RequestMapping("/normalComponentTemplate")
	public String  normalComponentTemplate (HttpServletResponse response,HttpServletRequest request){
		 String pageS = request.getParameter("pageSize");
		 String pageNo = request.getParameter("pageNo");
		 String opid = request.getParameter("opid");
    	 int num = 1;
		 int pageSize = 20;
		 if(StringUtil.isNotEmpty(pageS)){
			 pageSize = Integer.parseInt(pageS);
		 }
		 if(StringUtil.isNotEmpty(pageNo)){
			 num = Integer.parseInt(pageNo);
		 }
		//根据Id排序
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 
		 Map<String,Object> hashMap = new HashMap<>();
		 if(StringUtil.isEmpty(opid)){
			 hashMap.put("Msg", "操作失败！参数错误！opid 信息找不到！");
			 hashMap.put("result", "fail");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		 DividedPayments dividedPayments = dividedPaymentsService.findDividedPaymentsById(new BigDecimal(opid));
		 if(dividedPayments == null || dividedPayments.getTenderid() == null){
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！借款人还款计划信息 'dividedPayments' 不能为空！");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		  
  		Map<String,Object> maps = new HashMap<>();
  		maps.put("tenderid", dividedPayments.getTenderid());//标号ID
  		maps.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
  		maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
  		maps.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
 		List<RepayMent> repayMents  = repayMentServiceI.findListRepayMentByConditions(maps);
  		if(!(repayMents.size() > 0 )){
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "未找到可以还款的投资人还款计划信息！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
 		UserBaseAccountInfo userinaccountid = null;//投资用户
 		for(RepayMent ment : repayMents){
 			userinaccountid = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(ment.getInaccountid());
 			if(userinaccountid != null && userinaccountid.getLoginname() != null){
 				ment.setIntname(getDecrypt(userinaccountid.getLoginname()));
 			}
 			ment.setCount(Double.valueOf(df1.format(ment.getRamount() + ment.getRinterest() 
 					+ ment.getRvoucher() + ment.getRvoucherint()
 					+ ment.getDisablevoucher() + ment.getDisablevoucherint())));
 		}
 		 
		 PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	
      	hashMap.put("result", "success");
		hashMap.put("Msg", "查询成功");
		hashMap.put("detail", pagehelper);
		hashMap.put("opid", opid);
		String str = JSON.toJSONString(hashMap);
 		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
 	}
	 
	 /**
	  * 
	 * @Title: normalCompensatoryPartRepayMentTask 
	 * @Description: TODO(部分代偿提交) 
	 * @param @param response
	 * @param @param request
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 @RequestMapping("/normalCompensatoryPartRepayMentTask")
	 public String normalCompensatoryPartRepayMentTask (HttpServletResponse response,HttpServletRequest request){
		 String opid = request.getParameter("opid");
		 Map<String,Object> hashMap = new HashMap<>();
		 if(StringUtil.isEmpty(opid)){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "opid 参数没找到！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		 }
		 
		 List<RepayMent> repayMents = new ArrayList<>();
 		 String[] opids = opid.split(",");
		 for(int i = 0 ; i < opids.length ; i++){
			 RepayMent ment = repayMentServiceI.findRepayMentByRorderno(opids[i]);
			 if(ment != null){
				 repayMents.add(ment);
			 }
		 }
		  
		 if(!(repayMents.size() > 0)){
			 hashMap.put("result", "fail");
			 hashMap.put("Msg", "没有找到投资人还款计划信息！");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		 }
		 
		 
		 RepayMent repayMent = repayMents.get(0);
		 TenderItem tenderItem = tenderItemServiceI.findTenderItemById(repayMent.getTenderid());
			if(!tenderItem.getIscompensatory().equals((short)1)){
	  			hashMap.put("result", "fail");
				hashMap.put("Msg", "正常代偿还款处理失败！标的正常还款代偿开关 未开启！");
	  			String str = JSON.toJSONString(hashMap);
				 try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
	 				e.printStackTrace();
				}
				 return null;
	  		}
	  		
	 		if(tenderItem.getCompensatoryman() == null){//代偿人电子账号
	 			hashMap.put("result", "fail");
	 			hashMap.put("Msg", "正常代偿还款处理失败！标的代偿人电子账号 不能为空！");
	 			String str = JSON.toJSONString(hashMap);
	 			try {
	 				StringUtil.sendJsonData(response, str);
	 			} catch (IOException e) {
	 				e.printStackTrace();
	 			}
	 			return null;
	 		}
	 		 
	  		if(tenderItem.getIsapartrepay() == null){//是否支持部分还款 1是 0否
	 			hashMap.put("result", "fail");
	 			hashMap.put("Msg", "正常代偿还款处理失败！标的是否支持部分还款 不能为空！");
	 			String str = JSON.toJSONString(hashMap);
	 			try {
	 				StringUtil.sendJsonData(response, str);
	 			} catch (IOException e) {
	 				e.printStackTrace();
	 			}
	 			return null;
	 		}
	  		
	  		if(tenderItem.getRepaymenttype() == null){//还款类型（1及时，2冻结还款）
	 			hashMap.put("result", "fail");
	 			hashMap.put("Msg", "正常代偿还款处理失败！标的还款类型不能为空！");
	 			String str = JSON.toJSONString(hashMap);
	 			try {
	 				StringUtil.sendJsonData(response, str);
	 			} catch (IOException e) {
	 				e.printStackTrace();
	 			}
	 			return null;
	 		}
	  		
	  		String rbatchno = StringUtil.getNo();//还款批次号
	  		Double totalamount = 0.00;
	  		for(RepayMent repayMent2 : repayMents){
	  			totalamount = Double.valueOf(df1.format(repayMent2.getRamount() + repayMent2.getRvoucher() + repayMent2.getDisablevoucher()
	  					+ repayMent2.getRinterest() + repayMent2.getRvoucherint() + repayMent2.getDisablevoucherint() + totalamount));
	  		}
	  		
	  		
	  		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(tenderItem.getCompensatoryman(),totalamount.toString(), tenderItem.getTno(), rbatchno,(short)0,(short)1,(short)0);
			boolean fg = (boolean) resMap.get(RepayMent_Constant.FALG);
 			if(!fg){
 				hashMap.put("result", "fail");
				hashMap.put("Msg", resMap.get(RepayMent_Constant.MSG)+",请重新操作或联系客服！");
 				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			 
  		Date submittime = new Date();//提交时间
		for(RepayMent repayMent2 : repayMents){//更新状态
			repayMent2.setIsproxyrepay((short)1);//是否代偿（1是，0否）
			repayMent2.setIsahead((short)0);//是否提前（0否，1是）
			repayMent2.setSubmittime(submittime);//借款人提交还款时间  注这个时间同批次提交的时候添加必须一致  
			repayMent2.setAuditman("系统");//审核人  及时还款 写系统
			repayMent2.setAudittime(submittime);//审核时间 
			repayMent2.setRbatchno(rbatchno);//还款批次号
			repayMent2.setRmode((short)1);//还款模式（0初始  1人工，2系统，3线下） 
    		repayMentServiceI.updateById(repayMent2);
    		
    		repayMentBaseDealI.calculateNormalRepayemInterestManageFee(repayMent2);//计算利息管理费并更新

    		
     		RepaymentDetail repaymentDetail = repayMentBaseDealI.copyRepaymentDetailByRepayMent(repayMent2);//生成快照
    		if(repaymentDetail != null){
    			repaymentDetailServiceI.insertSelective(repaymentDetail);//添加批次还款详情快照
    		}
		}
	  	Map<String,Object> resultMap = 	thirdRepayMentDealI.settingUpBatchNormalCompensatoryRepayMent(repayMents, rbatchno, tenderItem.getCompensatoryman(), totalamount.toString(), tenderItem.getTno());
	  	boolean falg = 	(boolean) resultMap.get(RepayMent_Constant.FALG);
 	  	if(falg){
	  		hashMap.put("result", "success");
			hashMap.put("Msg", "提示：代偿还款提交成功！");
	  		String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	  	}else{
	  		hashMap.put("result", "fail");
			hashMap.put("Msg", "提示：操作失败,因网络响应不及时,代偿还款提交失败！");
	  		String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
	  	}
 	 }
	 
	 /**
	  * 
	 * @Title: normalCompensatoryAllRepayMent 
	 * @Description: TODO(全部代偿 (这里是允许部分还款)) 
	 * @param @param response
	 * @param @param request
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author   cjm  
	 * @throws
	  */
	 @RequestMapping("/normalCompensatoryAllRepayMentTask")
	 public String normalCompensatoryAllRepayMentTask(HttpServletResponse response,HttpServletRequest request){
		 String opid = request.getParameter("opid");
		 Map<String,String> hashMap = new HashMap<>();
		 if(StringUtil.isEmpty(opid)){
			 hashMap.put("Msg", "操作失败！参数错误！opid 信息找不到！");
			 hashMap.put("result", "fail");
			 String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		 DividedPayments dividedPayments = dividedPaymentsService.findDividedPaymentsById(new BigDecimal(opid));
		 if(dividedPayments == null || dividedPayments.getTenderid() == null){
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！借款人还款计划信息 'dividedPayments' 不能为空！");
			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		 }
		 
		 TenderItem tenderItem = tenderItemServiceI.findTenderItemById(dividedPayments.getTenderid());
 		 if(tenderItem == null){
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的信息'TenderItem' 不能为空！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
		}
  		 
  		if(tenderItem.getIscompensatory() == null){//正常还款代偿开关(1:开,0:关)
 			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的正常还款代偿开关 不能为空！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
 		}
 		  
  		if(!tenderItem.getIscompensatory().equals((short)1)){
  			hashMap.put("result", "fail");
			hashMap.put("Msg", "正常代偿还款处理失败！标的正常还款代偿开关 未开启！");
  			String str = JSON.toJSONString(hashMap);
			 try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			 return null;
  		}
  		
 		if(tenderItem.getCompensatoryman() == null){//代偿人电子账号
 			hashMap.put("result", "fail");
 			hashMap.put("Msg", "正常代偿还款处理失败！标的代偿人电子账号 不能为空！");
 			String str = JSON.toJSONString(hashMap);
 			try {
 				StringUtil.sendJsonData(response, str);
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 			return null;
 		}
 		
 			Map<String,Object> maps = new HashMap<>();
 	  		maps.put("tenderid", dividedPayments.getTenderid());//标号ID
 	  		maps.put("periods", dividedPayments.getPeriods());//还款期数（第几期）
 	  		maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
 	  		maps.put("sumitrepayment", (short)1);//1待还款,6还款失败 7 审核失败
 	 		List<RepayMent> repayMents  = repayMentServiceI.findListRepayMentByConditions(maps);
 	 		 
 	 		if(!(repayMents.size() > 0 )){
 	 			hashMap.put("result", "fail");
 	 			hashMap.put("Msg", "未找到可以还款的投资人还款计划信息！");
 	 			String str = JSON.toJSONString(hashMap);
 	 			try {
 	 				StringUtil.sendJsonData(response, str);
 	 			} catch (IOException e) {
 	 				e.printStackTrace();
 	 			}
 	 			return null;
 	 		}
 			 
 	 		String rbatchno = StringUtil.getNo();//还款批次号
	  		Double totalamount = 0.00;
	  		for(RepayMent repayMent2 : repayMents){
	  			totalamount = Double.valueOf(df1.format(repayMent2.getRamount() + repayMent2.getRvoucher() + repayMent2.getDisablevoucher()
	  					+ repayMent2.getRinterest() + repayMent2.getRvoucherint() + repayMent2.getDisablevoucherint() + totalamount));
	  		}
		  		
	  		Map<String,Object> resMap = HSRepayMentFreezeController.repayMentFreeze(tenderItem.getCompensatoryman(),totalamount.toString(), tenderItem.getTno(),rbatchno,(short)0,(short)1,(short)0);
			boolean fg = (boolean) resMap.get(RepayMent_Constant.FALG);
 			if(!fg){
			 
				hashMap.put("result", "fail");
				hashMap.put("Msg", resMap.get(RepayMent_Constant.MSG)+",请重新操作或联系客服！");
 				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			Date submittime = new Date();//提交时间
			for(RepayMent repayMent2 : repayMents){//更新状态
				repayMent2.setIsproxyrepay((short)1);//是否代偿（1是，0否）
				repayMent2.setIsahead((short)0);//是否提前（0否，1是）
				repayMent2.setSubmittime(submittime);//借款人提交还款时间  注这个时间同批次提交的时候添加必须一致  
				repayMent2.setAuditman("系统");//审核人  及时还款 写系统
				repayMent2.setAudittime(submittime);//审核时间 
				repayMent2.setRbatchno(rbatchno);//还款批次号
				repayMent2.setRmode((short)1);//还款模式（0初始  1人工，2系统，3线下） 
	    		repayMentServiceI.updateById(repayMent2);
	     		
	    		repayMentBaseDealI.calculateNormalRepayemInterestManageFee(repayMent2);//计算利息管理费并更新
	    		
	     		RepaymentDetail repaymentDetail = repayMentBaseDealI.copyRepaymentDetailByRepayMent(repayMent2);//生成快照
	    		if(repaymentDetail != null){
	    			repaymentDetailServiceI.insertSelective(repaymentDetail);//添加批次还款详情快照
	    		}
			}
		  		
	  		Map<String,Object> resultMap = thirdRepayMentDealI.settingUpBatchNormalCompensatoryRepayMent(repayMents, rbatchno, tenderItem.getCompensatoryman(), totalamount.toString(), tenderItem.getTno());
		  	boolean falg = 	(boolean) resultMap.get(RepayMent_Constant.FALG);
			  	if(falg){
		  		hashMap.put("result", "success");
				hashMap.put("Msg", "提示：代偿还款提交成功！");
		  		String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
		  	}else{
		  		hashMap.put("result", "fail");
				hashMap.put("Msg", "提示：操作失败,因网络响应不及时,代偿还款提交失败！");
		  		String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
		  	}
  	 }
	 
 }
