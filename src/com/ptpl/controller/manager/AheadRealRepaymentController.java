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
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.RepaymentDetail;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.service.RepaymentDetailServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: AheadRealRepaymentController 
* @Package com.ptpl.controller 
* @Description: TODO(提前实际还款记录实体 控制层 ) 
* @author chenjiaming
* @date 2017年10月27日 14:21:55
* @version V1.0
 */
@Controller
@RequestMapping("/admin/aheadRealRepayment")
public class AheadRealRepaymentController extends BaseController{
	
	 @Autowired
	 private AheadRealRepaymentServiceI aheadRealRepaymentService;
	 
	 @Autowired	
	 private RepaymentDetailServiceI repaymentDetailServiceI;
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(提前实际还款记录实体查询通用方法) 
	 * @param @param aheadRealRepayment
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(AheadRealRepayment aheadRealRepayment){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<AheadRealRepayment> aheadRealRepayments = aheadRealRepaymentService.findAheadRealRepayments(aheadRealRepayment);
       	 for(AheadRealRepayment aheadRealRepayment1 : aheadRealRepayments){
       		 
       		 if(StringUtil.isNotEmpty(aheadRealRepayment1.getRrealbatchno()) && StringUtil.isNotEmpty(aheadRealRepayment1.getRorderno())){
        		 RepaymentDetail repaymentDetail2 = new RepaymentDetail();
       			 repaymentDetail2.setRbatchno(aheadRealRepayment1.getRrealbatchno());
       			 repaymentDetail2.setRorderno(aheadRealRepayment1.getRorderno());
       			 RepaymentDetail repaymentDetail = repaymentDetailServiceI.findRepaymentDetail(repaymentDetail2);
       			 if(repaymentDetail != null){
       				aheadRealRepayment1.setRepaymentDetail(repaymentDetail);
       			 }
       		 
       		 }
       	 }
      	 PageInfo<AheadRealRepayment> pagehelper = new PageInfo<AheadRealRepayment>(aheadRealRepayments);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/aheadRealRepayment/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(提前实际还款记录实体模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param aheadRealRepayment
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,AheadRealRepayment aheadRealRepayment){
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
       	 List<AheadRealRepayment> aheadRealRepayments = aheadRealRepaymentService.findAheadRealRepayments(aheadRealRepayment);
       	 for(AheadRealRepayment aheadRealRepayment1 : aheadRealRepayments){
      		 
      		 if(StringUtil.isNotEmpty(aheadRealRepayment1.getRrealbatchno()) && StringUtil.isNotEmpty(aheadRealRepayment1.getRorderno())){
       		 RepaymentDetail repaymentDetail2 = new RepaymentDetail();
      			 repaymentDetail2.setRbatchno(aheadRealRepayment1.getRrealbatchno());
      			 repaymentDetail2.setRorderno(aheadRealRepayment1.getRorderno());
      			 RepaymentDetail repaymentDetail = repaymentDetailServiceI.findRepaymentDetail(repaymentDetail2);
      			 if(repaymentDetail != null){
      				aheadRealRepayment1.setRepaymentDetail(repaymentDetail);
      			 }
      		 
      		 }
      	 }
      	 PageInfo<AheadRealRepayment> pagehelper = new PageInfo<AheadRealRepayment>(aheadRealRepayments);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/aheadRealRepayment/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	  
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看提前实际还款记录实体详情信息) 
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
  		     AheadRealRepayment  aheadRealRepayment = aheadRealRepaymentService.findAheadRealRepaymentById(new BigDecimal(opid));
  		     if(StringUtil.isNotEmpty(aheadRealRepayment.getRrealbatchno()) && StringUtil.isNotEmpty(aheadRealRepayment.getRorderno())){
         		 RepaymentDetail repaymentDetail2 = new RepaymentDetail();
    			 repaymentDetail2.setRbatchno(aheadRealRepayment.getRrealbatchno());
    			 repaymentDetail2.setRorderno(aheadRealRepayment.getRorderno());
    			 RepaymentDetail repaymentDetail = repaymentDetailServiceI.findRepaymentDetail(repaymentDetail2);
    			 if(repaymentDetail != null){
    				 aheadRealRepayment.setRepaymentDetail(repaymentDetail);
    			 }
         	}
  		     modelAndView.addObject("aheadRealRepayment", aheadRealRepayment);
  		     modelAndView.setViewName("admin/aheadRealRepayment/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	  
 }
