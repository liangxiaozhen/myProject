package com.ptpl.controller.manager;
 
import java.math.BigDecimal;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.ptpl.controller.BaseController;
import com.ptpl.model.InterestExpenseRecord;
import com.ptpl.service.InterestExpenseRecordServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: InterestExpenseRecordController 
* @Package com.ptpl.controller 
* @Description: TODO(标的利息管理费记录 控制层 ) 
* @author chenjiaming
* @date 2016年10月26日 15:40:30
* @version V1.0
 */
@Controller
@RequestMapping("/admin/interestExpenseRecord")
public class InterestExpenseRecordController extends BaseController{
	
	 @Autowired
	 private InterestExpenseRecordServiceI interestExpenseRecordService;
		
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(标的利息管理费记录查询通用方法) 
	 * @param @param interestExpenseRecord
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(InterestExpenseRecord interestExpenseRecord){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<InterestExpenseRecord> interestExpenseRecords = interestExpenseRecordService.findInterestExpenseRecords(interestExpenseRecord);
       	 for(InterestExpenseRecord expenseRecord : interestExpenseRecords){
       		 if(expenseRecord.getInvestoridaccount() != null){
       			expenseRecord.setInvestoridaccount(getDecryptionUserBaseAccountInfoDetail(expenseRecord.getInvestoridaccount()));
       		 }
       	 }
       	 PageInfo<InterestExpenseRecord> pagehelper = new PageInfo<InterestExpenseRecord>(interestExpenseRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/interestExpenseRecord/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(标的利息管理费记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param interestExpenseRecord
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,InterestExpenseRecord interestExpenseRecord){
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
		 if(StringUtil.isNotEmpty(interestExpenseRecord.getLoginname())){
			 interestExpenseRecord.setLoginname(setEncrypt(interestExpenseRecord.getLoginname().trim()));
		 }
		 //查询全部用户方法
       	 List<InterestExpenseRecord> interestExpenseRecords = interestExpenseRecordService.findInterestExpenseRecords(interestExpenseRecord);
       	 for(InterestExpenseRecord expenseRecord : interestExpenseRecords){
       		 if(expenseRecord.getInvestoridaccount() != null){
       			expenseRecord.setInvestoridaccount(getDecryptionUserBaseAccountInfoDetail(expenseRecord.getInvestoridaccount()));
       		 }
       	 }
      	 PageInfo<InterestExpenseRecord> pagehelper = new PageInfo<InterestExpenseRecord>(interestExpenseRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/interestExpenseRecord/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看标的利息管理费记录详情信息) 
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
  		     InterestExpenseRecord  interestExpenseRecord = interestExpenseRecordService.findInterestExpenseRecordById(new BigDecimal(opid));
      		 if(interestExpenseRecord.getInvestoridaccount() != null){
     			interestExpenseRecord.setInvestoridaccount(getDecryptionUserBaseAccountInfoDetail(interestExpenseRecord.getInvestoridaccount()));
     		 }
         	 
   		     modelAndView.addObject("interestExpenseRecord", interestExpenseRecord);
  		     modelAndView.setViewName("admin/interestExpenseRecord/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	 
   	
 }
