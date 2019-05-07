package com.ptpl.controller.manager;
 
import java.math.BigDecimal;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.EmailRecord;
import com.ptpl.service.EmailRecordServiceI;
import com.ptpl.web.util.SendMailTempalte;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: EmailRecordController 
* @Package com.ptpl.controller 
* @Description: TODO(邮件发送记录 控制层 ) 
* @author chenjiaming
* @date 2016年09月01日 18:07:13
* @version V1.0
 */
@Controller
@RequestMapping("/admin/emailRecord")
public class EmailRecordController extends BaseController{
	
	 @Autowired
	 private EmailRecordServiceI emailRecordService;
		
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(邮件发送记录查询通用方法) 
	 * @param @param emailRecord
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(EmailRecord emailRecord){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<EmailRecord> emailRecords = emailRecordService.findEmailRecords(emailRecord);
       	 
      	 PageInfo<EmailRecord> pagehelper = new PageInfo<EmailRecord>(emailRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/emailRecord/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(邮件发送记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param emailRecord
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,EmailRecord emailRecord){
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
       	 List<EmailRecord> emailRecords = emailRecordService.findEmailRecords(emailRecord);
       	  
      	 PageInfo<EmailRecord> pagehelper = new PageInfo<EmailRecord>(emailRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/emailRecord/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
      /**
       * 
      * @Title: repeat 
      * @Description: TODO(邮件补发) 
      * @param @param request
      * @param @param response
      * @param @return
      * @param @throws IOException  参数说明 
      * @return String    返回类型 
      * @author chenjiaming
      * @throws
       */
	  @RequestMapping(value="/repeat",method={RequestMethod.POST,RequestMethod.GET})
	  public String repeat(HttpServletRequest request,HttpServletResponse response) throws IOException{
 		  String opid = request.getParameter("opid");
		  if(StringUtil.isNotEmpty(opid)){
			  Map<String,String> hashMap = new HashMap<String,String>();
			  EmailRecord emailRecord = emailRecordService.findEmailRecordById(new BigDecimal(opid));
			  /*邮件补发类型 1绑定邮箱验证链接 2邮箱重置验证链接 3邮箱验证链接  4普通邮箱验证码*/
			  //验证随机码
			  String emailCode = StringUtil.getRandomStr(30);
			  String messagetype = emailRecord.getMessagetype().toString();
			  String email = emailRecord.getEmail();
			  String toKCode = emailRecord.getEmailcontent();
			  String username = emailRecord.getUsername();
			  String method = Session_Constant.REPEAT_EMAIL;
  			  if(StringUtil.isNotEmpty(messagetype) && email != null){
 				  if(messagetype.equalsIgnoreCase("1")){//绑定邮箱验证链接 
					  boolean flag = SendMailTempalte.sendEamilForEmailbind(email, emailCode, toKCode, method, username, request);
					  if(flag){
						  emailRecord.setReissuetime(new Date());
		 				  emailRecordService.updateById(emailRecord);
 						  hashMap.put("result", "success");
					  }else{
						  hashMap.put("result", "fail");
					  }
				  }else if(messagetype.equalsIgnoreCase("2")){//邮箱重置验证链接
 					  boolean flag = SendMailTempalte.sendEamilForEmailCheck(email, emailCode, toKCode, method, username, request); 
 					 if(flag){
						  emailRecord.setReissuetime(new Date());
		 				  emailRecordService.updateById(emailRecord);
						  hashMap.put("result", "success");
					  }else{
						  hashMap.put("result", "fail");
					  }
				  }else if(messagetype.equalsIgnoreCase("3")){//邮箱验证链接
		 				boolean flag = SendMailTempalte.sendEamilForEmailReger(email, emailCode, toKCode, method, username, request);
		 				if(flag){
							  emailRecord.setReissuetime(new Date());
			 				  emailRecordService.updateById(emailRecord);
							  hashMap.put("result", "success");
						  }else{
							  hashMap.put("result", "fail");
						  }	
				  }else if(messagetype.equalsIgnoreCase("4")){//普通邮箱验证码
					   
				  }
			  }else{
				  hashMap.put("result", "error");
			  }
		  
  			  String str = JSON.toJSONString(hashMap);
 			  StringUtil.sendJsonData(response, str);
 		  }
		  return null;
	  }
   	 
    
 }
