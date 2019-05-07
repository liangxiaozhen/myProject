package com.ptpl.controller.manager;
 
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.RepayMent_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.HSRepayMentFreezeController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.RepayMent;
import com.ptpl.model.RepaymentDetail;
import com.ptpl.model.RepaymentFrz;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.RepaymentDetailServiceI;
import com.ptpl.service.RepaymentFrzServiceI;
import com.ptpl.service.ThirdRepayMentDealI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: RepaymentFrzController 
* @Package com.ptpl.controller 
* @Description: TODO(还款冻结解冻记录 控制层 ) 
* @author chenjiaming
* @date 2017年05月24日 19:05:56
* @version V1.0
 */
@Controller
@RequestMapping("/admin/repaymentFrz")
public class RepaymentFrzController extends BaseController{
	
	 @Autowired
	 private RepaymentFrzServiceI repaymentFrzService;
	 
	 @Autowired
	 private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	 
	 @Autowired
	 private  RepaymentDetailServiceI repaymentDetailServiceI;
	 
	 @Autowired
	 private RepayMentServiceI repayMentServiceI;
	 
	 @Autowired
	 private ThirdRepayMentDealI thirdRepayMentDealI;
 	 
	 @Autowired
	 private BacthFileRecordServiceI bacthFileRecordServiceI;
	 
	 
	 @Autowired
	 private AheadRealRepaymentServiceI aheadRealRepaymentServiceI;
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(还款冻结解冻记录查询通用方法[查看]) 
	 * @param @param repaymentFrz
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/listbyview")
 	 public ModelAndView listbyview(RepaymentFrz repaymentFrz){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<RepaymentFrz> repaymentFrzs = repaymentFrzService.findRepaymentFrzs(repaymentFrz);
         for(RepaymentFrz frz : repaymentFrzs){
	       		 if(frz.getUseroutaccountid() != null){
	        		  frz.setUseroutaccountid( getDecryptionUserBaseAccountInfoDetail(frz.getUseroutaccountid()));
	       		 }
     			 
    			 if(frz.getReturntime() != null){
    				 frz.setReturntimestr(StringUtil.formatDate(frz.getReturntime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getThawtime() != null){
    				 frz.setThawtimestr(StringUtil.formatDate(frz.getThawtime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getFrztime() != null){
    				 frz.setFrztimestr(StringUtil.formatDate(frz.getFrztime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getThawreturntime() != null){
    				 frz.setThawreturntimestr(StringUtil.formatDate(frz.getThawreturntime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getBatchno() != null){
     				 RepaymentDetail repaymentDetail = new RepaymentDetail();
    				 repaymentDetail.setRbatchno(frz.getBatchno());
    				 List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
    				 if(repayMentDetails.size() > 0){
    					 frz.setCount(repayMentDetails.size());
    				 }
    			 }
       		  
       	 }
      	 PageInfo<RepaymentFrz> pagehelper = new PageInfo<RepaymentFrz>(repaymentFrzs);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/repaymentFrz/listbyview");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(还款冻结解冻记录模板方法 ,下一页，根据用户名模糊查询通通进这里【查看】) 
	* @param @param request
	* @param @param response
	* @param @param repaymentFrz
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/templatebyview")
 	 public ModelAndView templatebyview(HttpServletRequest request,HttpServletResponse response,RepaymentFrz repaymentFrz){
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
		 if(StringUtil.isNotEmpty(repaymentFrz.getOutloginname())){
			 repaymentFrz.setOutloginname(setEncrypt(repaymentFrz.getOutloginname().trim()));
		 }
		 //查询全部用户方法
       	 List<RepaymentFrz> repaymentFrzs = repaymentFrzService.findRepaymentFrzs(repaymentFrz);
        for(RepaymentFrz frz : repaymentFrzs){
      		 if(frz.getUseroutaccountid() != null){
       		  frz.setUseroutaccountid( getDecryptionUserBaseAccountInfoDetail(frz.getUseroutaccountid()));
      		 }
			 
			 if(frz.getReturntime() != null){
				 frz.setReturntimestr(StringUtil.formatDate(frz.getReturntime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getThawtime() != null){
				 frz.setThawtimestr(StringUtil.formatDate(frz.getThawtime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getFrztime() != null){
				 frz.setFrztimestr(StringUtil.formatDate(frz.getFrztime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getThawreturntime() != null){
				 frz.setThawreturntimestr(StringUtil.formatDate(frz.getThawreturntime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getBatchno() != null){
 				 RepaymentDetail repaymentDetail = new RepaymentDetail();
				 repaymentDetail.setRbatchno(frz.getBatchno());
				 List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
				 if(repayMentDetails.size() > 0){
					 frz.setCount(repayMentDetails.size());
				 }
			 }
     	 }
      	 PageInfo<RepaymentFrz> pagehelper = new PageInfo<RepaymentFrz>(repaymentFrzs);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/repaymentFrz/listTemplatebyview");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(还款冻结解冻记录查询通用方法) 
	 * @param @param repaymentFrz
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(RepaymentFrz repaymentFrz){
  		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 repaymentFrz.setStatusisaudanddealwait((short)1);//审核中 或者 待处理状态的信息
       	 List<RepaymentFrz> repaymentFrzs = repaymentFrzService.findRepaymentFrzs(repaymentFrz);
         for(RepaymentFrz frz : repaymentFrzs){
	       		 if(frz.getUseroutaccountid() != null){
	        		  frz.setUseroutaccountid( getDecryptionUserBaseAccountInfoDetail(frz.getUseroutaccountid()));
	       		 }
     			 
    			 if(frz.getReturntime() != null){
    				 frz.setReturntimestr(StringUtil.formatDate(frz.getReturntime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getThawtime() != null){
    				 frz.setThawtimestr(StringUtil.formatDate(frz.getThawtime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getFrztime() != null){
    				 frz.setFrztimestr(StringUtil.formatDate(frz.getFrztime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getThawreturntime() != null){
    				 frz.setThawreturntimestr(StringUtil.formatDate(frz.getThawreturntime(), "yyyy-MM-dd HH:mm:ss"));
    			 }
    			 
    			 if(frz.getBatchno() != null){
     				 RepaymentDetail repaymentDetail = new RepaymentDetail();
    				 repaymentDetail.setRbatchno(frz.getBatchno());
    				 List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
    				 if(repayMentDetails.size() > 0){
    					 frz.setCount(repayMentDetails.size());
    				 }
    			 }
       		  
       	 }
      	 PageInfo<RepaymentFrz> pagehelper = new PageInfo<RepaymentFrz>(repaymentFrzs);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/repaymentFrz/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(还款冻结解冻记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param repaymentFrz
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,RepaymentFrz repaymentFrz){
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
		 if(StringUtil.isNotEmpty(repaymentFrz.getOutloginname())){
			 repaymentFrz.setOutloginname(setEncrypt(repaymentFrz.getOutloginname().trim()));
		 }
		 //查询全部用户方法
       	 List<RepaymentFrz> repaymentFrzs = repaymentFrzService.findRepaymentFrzs(repaymentFrz);
        for(RepaymentFrz frz : repaymentFrzs){
      		 if(frz.getUseroutaccountid() != null){
       		  frz.setUseroutaccountid( getDecryptionUserBaseAccountInfoDetail(frz.getUseroutaccountid()));
      		 }
			 
			 if(frz.getReturntime() != null){
				 frz.setReturntimestr(StringUtil.formatDate(frz.getReturntime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getThawtime() != null){
				 frz.setThawtimestr(StringUtil.formatDate(frz.getThawtime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getFrztime() != null){
				 frz.setFrztimestr(StringUtil.formatDate(frz.getFrztime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getThawreturntime() != null){
				 frz.setThawreturntimestr(StringUtil.formatDate(frz.getThawreturntime(), "yyyy-MM-dd HH:mm:ss"));
			 }
			 
			 if(frz.getBatchno() != null){
 				 RepaymentDetail repaymentDetail = new RepaymentDetail();
				 repaymentDetail.setRbatchno(frz.getBatchno());
				 List<RepaymentDetail> repayMentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
				 if(repayMentDetails.size() > 0){
					 frz.setCount(repayMentDetails.size());
				 }
			 }
     	 }
      	 PageInfo<RepaymentFrz> pagehelper = new PageInfo<RepaymentFrz>(repaymentFrzs);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/repaymentFrz/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 /**
		 * 
		 * @Title: detail 
		 * @Description: TODO(查看批次还款详情信息) 
		 * @param @return  参数说明 
		 * @return String    返回类型 
		 * @author chenjiaming
		 * @throws
		 */
		@RequestMapping(value="/detail")
		public ModelAndView detail(HttpServletRequest request){
			String opid = request.getParameter("opid");//还款批次号
			if(StringUtil.isEmpty(opid)){
				return null;
			} 
			
			RepaymentFrz repayMentFrz = repaymentFrzService.findRepaymentFrzById(new BigDecimal(opid));
			if(repayMentFrz == null || repayMentFrz.getBatchno() == null){
				return null;
			}
			
			if(repayMentFrz.getUseroutaccountid() != null){
				repayMentFrz.setUseroutaccountid( getDecryptionUserBaseAccountInfoDetail(repayMentFrz.getUseroutaccountid()));
     		 }
			ModelAndView modelAndView = new ModelAndView();
 			
			RepaymentDetail repaymentDetail = new  RepaymentDetail();
			repaymentDetail.setRbatchno(repayMentFrz.getBatchno());
			List<RepaymentDetail> repaymentDetails = repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
			if(repaymentDetails.size() > 0){
				
				Collections.sort(repaymentDetails,new Comparator<RepaymentDetail>() {

					@Override
					public int compare(RepaymentDetail o1, RepaymentDetail o2) {
  						return o1.getPeriods() < o2.getPeriods() ? -1 : 1;
					}
					
					
				});
				
				repayMentFrz.setCount(repaymentDetails.size());
 				for(RepaymentDetail frz : repaymentDetails){
    					
					if(frz.getOutaccount() != null){
						frz.setOutaccount( getDecryptionUserBaseAccountInfoDetail(frz.getOutaccount()));
					}
					
					if(frz.getInaccount() != null){
						frz.setInaccount( getDecryptionUserBaseAccountInfoDetail(frz.getInaccount()));
					}
					
					if(frz.getProxyaccount() != null){
						frz.setProxyaccount( getDecryptionUserBaseAccountInfoDetail(frz.getProxyaccount()));
					}
					
					if(repayMentFrz.getIsahead().equals((short)1)){//提前还款
 						AheadRealRepayment realRepayment = aheadRealRepaymentServiceI.findAheadRealRepaymentByRordernoAndBacthNo(frz.getRorderno(), frz.getRbatchno());
 						if(realRepayment != null){
 							frz.setAheadRealRepayment(realRepayment);
 						}
  					}
				}
 				modelAndView.addObject("repaymentDetails", repaymentDetails);
			}
			
 		 
			modelAndView.addObject("repayMentFrz", repayMentFrz);
 			modelAndView.setViewName("admin/repaymentFrz/detailTemplate");
 			return modelAndView; 
 		}
		
		 
		
		/**
		 * 
		* @Title: isaudit 
		* @Description: TODO(还款审核) 
		* @param @param request
		* @param @param response
		* @param @param repaymentFrz
		* @param @return  参数说明 
		* @return ModelAndView    返回类型 
		* @author chenjiaming
		* @throws
		 */
		@RequestMapping("/isaudit")
		public String isaudit(HttpServletRequest request,HttpServletResponse response){
			String opid = request.getParameter("opid");
			String status = request.getParameter("status");
			Map<String,String> hashMap = new HashMap<>();
			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
			hashMap.put(Session_Constant.RESULTCODE, Session_Constant.FAIL);
			hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时，操作失败！");
			
			if(StringUtil.isEmpty(opid)){
				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
				hashMap.put(Session_Constant.RESULTCODE, "opid_null");
				hashMap.put(Session_Constant.MESSAGE, "opid不能为空");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			
			if(StringUtil.isEmpty(status)){
				hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
				hashMap.put(Session_Constant.RESULTCODE, "status_null");
				hashMap.put(Session_Constant.MESSAGE, "status不能为空");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			
			RepaymentFrz repayMentfrz = repaymentFrzService.findRepaymentFrzById(new BigDecimal(opid));
			if(repayMentfrz == null || StringUtil.isEmpty(repayMentfrz.getBatchno())){
				hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时，批次还款信息找不到！操作失败！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			
			RepaymentDetail repaymentDetail = new RepaymentDetail();
			repaymentDetail.setRbatchno(repayMentfrz.getBatchno());//还款批次号
			List<RepaymentDetail> repaymentDetails  =  repaymentDetailServiceI.findRepaymentDetails(repaymentDetail);
			if(!(repaymentDetails.size() > 0)){
				hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时，批次还款详情信息找不到！操作失败！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			  
			Map<String,Object> hashMap2 = new HashMap<String,Object>();
			hashMap2.put("rbatchno", repayMentfrz.getBatchno());//批次号
 			hashMap2.put("repaystatus", (short)2);//1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败 7审核失败
 			hashMap2.put("planstatus",  (short)1);//还款计划状态(1有效，2无效)
			List<RepayMent> repayMents = repayMentServiceI.findListRepayMentByConditions(hashMap2);
			if(!(repayMents.size() > 0)){
				hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时，具体还款计划信息找不到！操作失败！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			
			Date audittime = new Date();//审核时间
			AdminUser adminUser = getAdminUser();
			String auditman = adminUser.getUsername();//审核人
			if(status.equals("1")){//审核通过
				if(repayMentfrz.getIsahead().equals((short)0) && repayMentfrz.getIsproxyrepay().equals((short)0) && repayMentfrz.getIsoverdue().equals((short)0)){//正常还款
 					Map<String,Object> suiResMap = thirdRepayMentDealI.settingUpBatchNormalRepayMent(repayMents, repayMentfrz.getBatchno(), repayMentfrz.getCardnbr(), repayMentfrz.getAmount().toString(), repayMentfrz.getProduct());
 					boolean falg = (boolean) suiResMap.get(RepayMent_Constant.FALG);
 					if(!falg){
 						hashMap = getResultMap(suiResMap);
  						String str = JSON.toJSONString(hashMap);
		 					try {
		 						StringUtil.sendJsonData(response, str);
		 					} catch (IOException e) {
		 	 					e.printStackTrace();
		 					}
		 					return null;
 					}
				}else if(repayMentfrz.getIsahead().equals((short)1)){//提前还款
					
					for(RepayMent repayMent : repayMents){
 						AheadRealRepayment aheadRealRepayment = aheadRealRepaymentServiceI.findAheadRealRepaymentByRordernoAndBacthNo(repayMent.getRorderno(), repayMent.getRbatchno());
 						if(aheadRealRepayment != null){
 							repayMent.setAheadRealRepayment(aheadRealRepayment);
 						}
					}
 					
					Map<String,Object> suiResMap = thirdRepayMentDealI.settingUpBatchAheadRepayMent(repayMents, repayMentfrz.getBatchno(), repayMentfrz.getCardnbr(), repayMentfrz.getAmount().toString(), repayMentfrz.getProduct());
					boolean falg = (boolean) suiResMap.get(RepayMent_Constant.FALG);
					if(!falg){
 						hashMap = getResultMap(suiResMap);
  						String str = JSON.toJSONString(hashMap);
	 					try {
	 						StringUtil.sendJsonData(response, str);
	 					} catch (IOException e) {
	 	 					e.printStackTrace();
	 					}
	 					return null;
 					}
 				}else if(repayMentfrz.getIsoverdue().equals((short)1)){//逾期还款
 					Map<String,Object> suiResMap = thirdRepayMentDealI.settingUpBatchOverdueRepayMent(repayMents, repayMentfrz.getBatchno(), repayMentfrz.getCardnbr(), repayMentfrz.getAmount().toString(), repayMentfrz.getProduct());
					boolean falg = (boolean) suiResMap.get(RepayMent_Constant.FALG);
					if(!falg){
 						hashMap = getResultMap(suiResMap);
  						String str = JSON.toJSONString(hashMap);
	 					try {
	 						StringUtil.sendJsonData(response, str);
	 					} catch (IOException e) {
	 	 					e.printStackTrace();
	 					}
	 					return null;
 					}
 				}else{
 					hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
 					hashMap.put(Session_Constant.RESULTCODE, "repaytype_error");
 					hashMap.put(Session_Constant.MESSAGE, "审核操作失败！没有找到还款方式！");
 					String str = JSON.toJSONString(hashMap);
 					try {
 						StringUtil.sendJsonData(response, str);
 					} catch (IOException e) {
 	 					e.printStackTrace();
 					}
 					return null;
 				}
				//提交成功
				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
				hashMap.put(Session_Constant.RESULTCODE, Session_Constant.SUCCESS);
				hashMap.put(Session_Constant.MESSAGE, "审核成功！已提交银行处理！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
				
 			}else{//审核失败
				
				//解冻还款
				Map<String,Object> reMap = HSRepayMentFreezeController.repayMentunFreeze(repayMentfrz.getCardnbr(), repayMentfrz.getAmount().toString(), repayMentfrz.getProduct(), StringUtil.getNo(), repayMentfrz.getSerino());
				boolean falg = (boolean) reMap.get(RepayMent_Constant.FALG);
				if(!falg){//解冻失败
					hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
					hashMap.put(Session_Constant.RESULTCODE, Session_Constant.FAIL);
					hashMap.put(Session_Constant.MESSAGE, reMap.get(RepayMent_Constant.MSG).toString());
					String str = JSON.toJSONString(hashMap);
					try {
						StringUtil.sendJsonData(response, str);
					} catch (IOException e) {
	 					e.printStackTrace();
					}
					return null;
				}
				boolean tryFlag = true;
				//更新还款状态
 				try{
  					for(RepayMent repayMent :repayMents){
						repayMent.setRepaystatus((short)7);//还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败,7审核失败)
						repayMent.setAudittime(audittime);//审核时间
						repayMent.setAuditman(auditman);//审核人
 						repayMentServiceI.updateById(repayMent);
					}
 					
 					for(RepaymentDetail repaymentDetail2 :repaymentDetails){
 						repaymentDetail2.setRepaystatus((short)7);//还款状态(1待还款,2审核中,3待处理,4处理中,5已还款,6还款失败，7审核失败)
 						repaymentDetail2.setAudittime(audittime);//审核时间
 						repaymentDetail2.setAuditman(auditman);//审核人
 						repaymentDetailServiceI.updateById(repaymentDetail2);
					}
 					
 					repayMentfrz.setStatus((short)7);//0初始 1冻结成功 2冻结失败 3审核中 4待处理 5处理中 6处理成功 7审核失败 8废弃
 					repayMentfrz.setAuditman(auditman);//审核人
 					repayMentfrz.setAudittime(audittime);//审核时间
 					repaymentFrzService.updateById(repayMentfrz);
 					
				}catch(Exception e){
					e.printStackTrace();
					tryFlag = false;
 				}
 				
 				if(!tryFlag){
 					hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
 					hashMap.put(Session_Constant.RESULTCODE, Session_Constant.SUCCESS);
 					hashMap.put(Session_Constant.MESSAGE, "审核操作成功失败！");
 					String str = JSON.toJSONString(hashMap);
 					try {
 						StringUtil.sendJsonData(response, str);
 					} catch (IOException e) {
 	 					e.printStackTrace();
 					}
 					return null;
 				}
 				
  				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
				hashMap.put(Session_Constant.RESULTCODE, Session_Constant.SUCCESS);
				hashMap.put(Session_Constant.MESSAGE, "审核操作成功！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
 			}
  		}
 		
        //调用第三方文件写进文件上传接口返回false		
		public Map<String,String> getResultMap(Map<String,Object> suiResMap){
			Map<String,String> hashMap = new HashMap<>();
			String res = (String) suiResMap.get(RepayMent_Constant.RESULT);
				if(res.equals(RepayMent_Constant.WRITEFALG)){//写进文件失败
					hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
					hashMap.put(Session_Constant.RESULTCODE, Session_Constant.FAIL);
					hashMap.put(Session_Constant.MESSAGE, "审核失败！还款计划信息写进文件失败！");
				}
				else if(res.equals(RepayMent_Constant.FILEUPLOADFALG)){//文件上传失败
					hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
					hashMap.put(Session_Constant.RESULTCODE, Session_Constant.FAIL);
					hashMap.put(Session_Constant.MESSAGE, "审核成功！上传文件失败！请重新提交文件上传！");
				}else{//审核失败
					hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
					hashMap.put(Session_Constant.RESULTCODE, Session_Constant.FAIL);
					hashMap.put(Session_Constant.MESSAGE, "审核失败！请重新操作！");
				}
 				return hashMap;
		}
		
		
		 
		/**
		 * 
		* @Title: repayMentFileUpload 
		* @Description: TODO(上传文件) 
		* @param @param request
		* @param @param response
		* @param @param repaymentFrz
		* @param @return  参数说明 
		* @return ModelAndView    返回类型 
		* @author chenjiaming
		* @throws
		 */
		@RequestMapping("/repayMentFileUpload")
		public String repayMentFileUpload(HttpServletRequest request,HttpServletResponse response){
			String opid = request.getParameter("opid");
			Map<String,String> hashMap = new HashMap<String,String>();
			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
			hashMap.put(Session_Constant.RESULTCODE, Session_Constant.FAIL);
			hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时,操作失败！请重新操作！");
			
			if(StringUtil.isEmpty(opid)){
				hashMap.put(Session_Constant.RESULTCODE,"opid_null");
				hashMap.put(Session_Constant.MESSAGE, "参数opid找不到！请重新操作！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			
			
			RepaymentFrz repaymentFrz = repaymentFrzService.findRepaymentFrzById(new BigDecimal(opid));
			if(repaymentFrz == null){
				hashMap.put(Session_Constant.RESULTCODE,"repaymentFrz_null");
				hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时,批次还款信息 'repaymentFrz'找不到！请重新操作！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			
			BacthFileRecord bfr = new BacthFileRecord();
			bfr.setBatchNo(repaymentFrz.getBatchno());//批次号
			List<BacthFileRecord> bacthFileRecords = bacthFileRecordServiceI.getAllBacthFileRecord(bfr);
			if(!(bacthFileRecords.size() > 0)){
				hashMap.put(Session_Constant.RESULTCODE,"bacthFileRecords_fail");
				hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时,文件记录 'bacthFileRecords'找不到！请重新操作！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
			
			if(bacthFileRecords.size() > 1){
				hashMap.put(Session_Constant.RESULTCODE,"bacthFileRecords_fail");
				hashMap.put(Session_Constant.MESSAGE, "文件记录 'bacthFileRecords' 找到2个了！请重新操作！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
			}
  			
			BacthFileRecord bacthFileRecord = bacthFileRecords.get(0);
 			boolean flag = thirdRepayMentDealI.repayMentFileUpload(bacthFileRecord);
 			if(!flag){//提交失败
 				hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时,文件上传失败！");
				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
				return null;
 			}
 			
 			hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
 			hashMap.put(Session_Constant.RESULTCODE,Session_Constant.SUCCESS);
			hashMap.put(Session_Constant.MESSAGE, "文件上传成功！");
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
		* @Title: repayMentFileUpload 
		* @Description: TODO(还款解冻处理) 
		* @param @param request
		* @param @param response
		* @param @param repaymentFrz
		* @param @return  参数说明 
		* @return ModelAndView    返回类型 
		* @author chenjiaming
		* @throws
		 */
		@RequestMapping("/unfreezeDeal")
		public  String unfreezeDeal(HttpServletRequest request,HttpServletResponse response){
			Map<String,String> hashMap = new HashMap<>();
			hashMap.put(Session_Constant.MESSAGE, "解冻失败");
			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
			hashMap.put(Session_Constant.RESULTCODE, Session_Constant.FAIL);
			String opid = request.getParameter("opid");
			if(StringUtil.isEmpty(opid)){
				hashMap.put(Session_Constant.MESSAGE, "opid 参数找不到！");
				hashMap.put(Session_Constant.RESULTCODE, "opid_null");
 				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
 				return null;
			}
			
			RepaymentFrz repaymentFrz = repaymentFrzService.findRepaymentFrzById(new BigDecimal(opid));
			if(repaymentFrz == null){
				hashMap.put(Session_Constant.MESSAGE, "因网络响应不及时,'repaymentFrz' 批次还款信息找不到！");
				hashMap.put(Session_Constant.RESULTCODE, "repaymentFrz_null");
 				String str = JSON.toJSONString(hashMap);
				try {
					StringUtil.sendJsonData(response, str);
				} catch (IOException e) {
 					e.printStackTrace();
				}
 				return null;
			}
			
 			Map<String,Object> resMap = thirdRepayMentDealI.unfreezeDeal(repaymentFrz);
 			
 			boolean falg = (boolean) resMap.get(RepayMent_Constant.FALG);
 			if(falg){
 				hashMap.put(Session_Constant.MESSAGE, "解冻成功！");
 				hashMap.put(Session_Constant.RESULTCODE,resMap.get(RepayMent_Constant.RESULT).toString());
 				hashMap.put(Session_Constant.RESULT, Session_Constant.SUCCESS);
 	 			String str = JSON.toJSONString(hashMap);
 				try {
 					StringUtil.sendJsonData(response, str);
 				} catch (IOException e) {
 						e.printStackTrace();
 				}
 				return null;
 			}
 			
 			hashMap.put(Session_Constant.MESSAGE, resMap.get(RepayMent_Constant.MSG).toString());
			hashMap.put(Session_Constant.RESULTCODE,resMap.get(RepayMent_Constant.RESULT).toString());
			hashMap.put(Session_Constant.RESULT, Session_Constant.PARAMSERROR);
 			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
					e.printStackTrace();
			}
			return null;
		}
 }
