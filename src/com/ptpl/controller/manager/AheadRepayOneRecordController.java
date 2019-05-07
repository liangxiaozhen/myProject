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
import com.ptpl.model.AheadRepayOneRecord;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.AheadRepayOneRecordServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: AheadRepayOneRecordController 
* @Package com.ptpl.controller 
* @Description: TODO(标的提前还款奖励单个投资人记录 控制层 ) 
* @author chenjiaming
* @date 2017年01月18日 12:08:15
* @version V1.0
 */
@Controller
@RequestMapping("/admin/aheadRepayOneRecord")
public class AheadRepayOneRecordController extends BaseController{
	
	 @Autowired
	 private AheadRepayOneRecordServiceI aheadRepayOneRecordService;
	 
	 @Autowired
	 private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	 
	 @Autowired
	 private TenderItemServiceI tenderItemServiceI;
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(标的提前还款奖励单个投资人记录查询通用方法) 
	 * @param @param aheadRepayOneRecord
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(AheadRepayOneRecord aheadRepayOneRecord){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<AheadRepayOneRecord> aheadRepayOneRecords = aheadRepayOneRecordService.findAheadRepayOneRecords(aheadRepayOneRecord);
       	 if(aheadRepayOneRecords.size() > 0){
       		UserBaseAccountInfo bmanidUserBaseAccountInfo = null;
       		UserBaseAccountInfo investoridUserBaseAccountInfo = null;
       		TenderItem tenderItem = null;
        	for(AheadRepayOneRecord aheadRepayOneRecord2 : aheadRepayOneRecords){
        		 if(aheadRepayOneRecord2.getBmanid() != null){
        			 bmanidUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(aheadRepayOneRecord2.getBmanid());
        			 if(bmanidUserBaseAccountInfo != null
        					 && bmanidUserBaseAccountInfo.getLoginname() != null){
        				 bmanidUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(bmanidUserBaseAccountInfo);
         				 aheadRepayOneRecord2.setBmanidusername(bmanidUserBaseAccountInfo.getLoginname());
        			 }
        		 }
        		 
        		 if(aheadRepayOneRecord2.getInvestorid() != null){
        			 investoridUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(aheadRepayOneRecord2.getInvestorid());
        			 if(investoridUserBaseAccountInfo != null
        					 && investoridUserBaseAccountInfo.getLoginname() != null){
        				 investoridUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(investoridUserBaseAccountInfo);
        				 aheadRepayOneRecord2.setInvestoridusername(investoridUserBaseAccountInfo.getLoginname());
        			 }
        		 }
        		 
        		 if(aheadRepayOneRecord2.getTenderid() != null){
        			 tenderItem  = tenderItemServiceI.findTenderItemById(aheadRepayOneRecord2.getTenderid());
        			 if(tenderItem != null && tenderItem.getTno() != null
        					 && tenderItem.getTname() != null){
        				 aheadRepayOneRecord2.setTno(tenderItem.getTno());
        				 aheadRepayOneRecord2.setTname(tenderItem.getTname());
        			 }
        		 }
       		 }
       	 }
      	 PageInfo<AheadRepayOneRecord> pagehelper = new PageInfo<AheadRepayOneRecord>(aheadRepayOneRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/aheadRepayOneRecord/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(标的提前还款奖励单个投资人记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param aheadRepayOneRecord
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,AheadRepayOneRecord aheadRepayOneRecord){
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
       	 List<AheadRepayOneRecord> aheadRepayOneRecords = aheadRepayOneRecordService.findAheadRepayOneRecords(aheadRepayOneRecord);
       	 if(aheadRepayOneRecords.size() > 0){
       		UserBaseAccountInfo bmanidUserBaseAccountInfo = null;
       		UserBaseAccountInfo investoridUserBaseAccountInfo = null;
       		TenderItem tenderItem = null;
        	for(AheadRepayOneRecord aheadRepayOneRecord2 : aheadRepayOneRecords){
        		 if(aheadRepayOneRecord2.getBmanid() != null){
        			 bmanidUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(aheadRepayOneRecord2.getBmanid());
        			 if(bmanidUserBaseAccountInfo != null
        					 && bmanidUserBaseAccountInfo.getLoginname() != null){
        				 bmanidUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(bmanidUserBaseAccountInfo);
        				 aheadRepayOneRecord2.setBmanidusername(bmanidUserBaseAccountInfo.getLoginname());
        			 }
        		 }
        		 
        		 if(aheadRepayOneRecord2.getInvestorid() != null){
        			 investoridUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(aheadRepayOneRecord2.getInvestorid());
        			 if(investoridUserBaseAccountInfo != null
        					 && investoridUserBaseAccountInfo.getLoginname() != null){
        				 investoridUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(investoridUserBaseAccountInfo);
        				 aheadRepayOneRecord2.setInvestoridusername(investoridUserBaseAccountInfo.getLoginname());
        			 }
        		 }
        		 
        		 if(aheadRepayOneRecord2.getTenderid() != null){
        			 tenderItem  = tenderItemServiceI.findTenderItemById(aheadRepayOneRecord2.getTenderid());
        			 if(tenderItem != null && tenderItem.getTno() != null
        					 && tenderItem.getTname() != null){
        				 aheadRepayOneRecord2.setTno(tenderItem.getTno());
        				 aheadRepayOneRecord2.setTname(tenderItem.getTname());
        			 }
        		 }
       		 }
       	 } 
      	 PageInfo<AheadRepayOneRecord> pagehelper = new PageInfo<AheadRepayOneRecord>(aheadRepayOneRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/aheadRepayOneRecord/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看标的提前还款奖励单个投资人记录详情信息) 
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
  		     AheadRepayOneRecord  aheadRepayOneRecord = aheadRepayOneRecordService.findAheadRepayOneRecordById(new BigDecimal(opid));
  		     UserBaseAccountInfo bmanidUserBaseAccountInfo = null;
      		 UserBaseAccountInfo investoridUserBaseAccountInfo = null;
      		 TenderItem tenderItem = null;
        	if(aheadRepayOneRecord.getBmanid() != null){
       			 bmanidUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(aheadRepayOneRecord.getBmanid());
       			 if(bmanidUserBaseAccountInfo != null
       					 && bmanidUserBaseAccountInfo.getLoginname() != null){
       				bmanidUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(bmanidUserBaseAccountInfo);
       				aheadRepayOneRecord.setBmanidusername(bmanidUserBaseAccountInfo.getLoginname());
       			 }
       		 }
       		 
       		 if(aheadRepayOneRecord.getInvestorid() != null){
       			 investoridUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(aheadRepayOneRecord.getInvestorid());
       			 if(investoridUserBaseAccountInfo != null
       					 && investoridUserBaseAccountInfo.getLoginname() != null){
       				investoridUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(investoridUserBaseAccountInfo);
       				aheadRepayOneRecord.setInvestoridusername(investoridUserBaseAccountInfo.getLoginname());
       			 }
       		 }
       		 
       		 if(aheadRepayOneRecord.getTenderid() != null){
       			 tenderItem  = tenderItemServiceI.findTenderItemById(aheadRepayOneRecord.getTenderid());
       			 if(tenderItem != null && tenderItem.getTno() != null
       					 && tenderItem.getTname() != null){
       				aheadRepayOneRecord.setTno(tenderItem.getTno());
       				aheadRepayOneRecord.setTname(tenderItem.getTname());
       			 }
       		 }
    		 modelAndView.addObject("aheadRepayOneRecord", aheadRepayOneRecord);
  		     modelAndView.setViewName("admin/aheadRepayOneRecord/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
  }
