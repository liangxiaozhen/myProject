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
import com.ptpl.model.RepaymentDetail;
import com.ptpl.service.RepaymentDetailServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: RepaymentDetailController 
* @Package com.ptpl.controller 
* @Description: TODO(还款批量详情记录 控制层 ) 
* @author chenjiaming
* @date 2017年08月06日 18:08:51
* @version V1.0
 */
@Controller
@RequestMapping("/admin/repaymentDetail")
public class RepaymentDetailController extends BaseController{
	
	 @Autowired
	 private RepaymentDetailServiceI repaymentDetailService;
		
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(还款批量详情记录查询通用方法) 
	 * @param @param repaymentDetail
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(RepaymentDetail repaymentDetail){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<RepaymentDetail> repaymentDetails = repaymentDetailService.findRepaymentDetails(repaymentDetail);
    	 for(RepaymentDetail repaymentDetail2 : repaymentDetails){
       		 if(repaymentDetail2.getOutaccount() != null){
       			repaymentDetail2.setOutaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail2.getOutaccount()));
       		 }
       		 
       		 if(repaymentDetail2.getInaccount() != null){
       			repaymentDetail2.setInaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail2.getInaccount()));
       		 }
 
       		 if(repaymentDetail2.getProxyaccount() != null){
       			repaymentDetail2.setProxyaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail2.getProxyaccount()));
       		 }
       	 }
      	 PageInfo<RepaymentDetail> pagehelper = new PageInfo<RepaymentDetail>(repaymentDetails);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/repaymentDetail/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(还款批量详情记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param repaymentDetail
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,RepaymentDetail repaymentDetail){
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
		 if(StringUtil.isNotEmpty(repaymentDetail.getInloginname())){
			 repaymentDetail.setInloginname(setEncrypt(repaymentDetail.getInloginname().trim()));
		 }
		 
		 if(StringUtil.isNotEmpty(repaymentDetail.getOutloginname())){
			 repaymentDetail.setOutloginname(setEncrypt(repaymentDetail.getOutloginname().trim()));
		 }
		 
		 if(StringUtil.isNotEmpty(repaymentDetail.getProxyloginname())){
			 repaymentDetail.setProxyloginname(setEncrypt(repaymentDetail.getProxyloginname().trim()));
		 }
		 
       	 List<RepaymentDetail> repaymentDetails = repaymentDetailService.findRepaymentDetails(repaymentDetail);
       	 for(RepaymentDetail repaymentDetail2 : repaymentDetails){
       		 if(repaymentDetail2.getOutaccount() != null){
       			repaymentDetail2.setOutaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail2.getOutaccount()));
       		 }
       		 
       		 if(repaymentDetail2.getInaccount() != null){
       			repaymentDetail2.setInaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail2.getInaccount()));
       		 }
 
       		 if(repaymentDetail2.getProxyaccount() != null){
       			repaymentDetail2.setProxyaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail2.getProxyaccount()));
       		 }
       	 }
      	 PageInfo<RepaymentDetail> pagehelper = new PageInfo<RepaymentDetail>(repaymentDetails);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/repaymentDetail/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看还款批量详情记录详情信息) 
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
  		     RepaymentDetail  repaymentDetail = repaymentDetailService.findRepaymentDetailById(new BigDecimal(opid));
   	       		 if(repaymentDetail.getOutaccount() != null){
   	       			 repaymentDetail.setOutaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail.getOutaccount()));
  	       		 }
  	       		 
  	       		 if(repaymentDetail.getInaccount() != null){
  	       			 repaymentDetail.setInaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail.getInaccount()));
  	       		 }
  	 
  	       		 if(repaymentDetail.getProxyaccount() != null){
  	       			 repaymentDetail.setProxyaccount(getDecryptionUserBaseAccountInfoDetail(repaymentDetail.getProxyaccount()));
  	       		 }
    		     modelAndView.addObject("repaymentDetail", repaymentDetail);
  		     modelAndView.setViewName("admin/repaymentDetail/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	  
 }
