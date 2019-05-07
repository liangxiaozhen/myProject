package com.ptpl.controller.manager;
 
import java.math.BigDecimal;
import java.io.IOException;
import java.text.DecimalFormat;
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
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huifu.HuifuTransferController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.PlusPayoutRecord;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.PlusPayoutRecordServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: PlusPayoutRecordController 
* @Package com.ptpl.controller 
* @Description: TODO(标的增益清算记录 控制层 ) 
* @author chenjiaming
* @date 2016年11月23日 15:20:58
* @version V1.0
 */
@Controller
@RequestMapping("/admin/plusPayoutRecord")
public class PlusPayoutRecordController extends BaseController{
	
	 @Autowired
	 private PlusPayoutRecordServiceI plusPayoutRecordService;
	 
	 @Autowired
	 private UserFsAccountInfoServiceI userFSAccountInfoService;
	  
	 @Autowired
	 private TenderItemServiceI tenderItemServiceI;
	 
	 @Autowired
	 private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	 
    /**
 	 * @Title: list 
	 * @Description: TODO(标的增益清算记录查询通用方法) 
	 * @param @param plusPayoutRecord
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	 */
	 @RequestMapping("/list")
 	 public ModelAndView list(PlusPayoutRecord plusPayoutRecord){
		 plusPayoutRecord.setIsaudit((short)0);//查询审核通过的
   		 int num = 1;
		 int pageSize = 20;
 		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
       	 List<PlusPayoutRecord> plusPayoutRecords = plusPayoutRecordService.findPlusPayoutRecords(plusPayoutRecord);
       	 UserBaseAccountInfo userBaseAccountInfo = null;
         UserBaseAccountInfo paynameuserBaseAccountInfo = null;
         TenderItem tenderItem  = null;
       	 for(PlusPayoutRecord plusPayoutRecord2 : plusPayoutRecords){
       		 if(plusPayoutRecord2.getTenderid() != null){
        		 tenderItem 		= tenderItemServiceI.findTenderItemById(plusPayoutRecord2.getTenderid());
       		 }
       		 
       		 if(tenderItem != null && tenderItem.getTname() != null){
       			plusPayoutRecord2.setTname(tenderItem.getTname());
       			if(tenderItem.getTno() != null){
       				plusPayoutRecord2.setTno(tenderItem.getTno());
       			}
       		 }
       		 
       		 if(plusPayoutRecord2.getInvestorid() != null){
        		userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getInvestorid());
       		 }
       		 
       		 if(userBaseAccountInfo != null && userBaseAccountInfo.getLoginname() != null){
       			userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);
       			plusPayoutRecord2.setInname(userBaseAccountInfo.getLoginname());//投资人名称
      		 }
       		 
       		 if(plusPayoutRecord2.getPaymanid() != null){
       			paynameuserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getPaymanid());
       		 }
       		 
       		 if(paynameuserBaseAccountInfo != null && paynameuserBaseAccountInfo.getLoginname() != null){
       			plusPayoutRecord2.setPayname(paynameuserBaseAccountInfo.getLoginname());
       		 }
       		 
       	 }
       	 PageInfo<PlusPayoutRecord> pagehelper = new PageInfo<PlusPayoutRecord>(plusPayoutRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize == 0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/plusPayoutRecord/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(标的增益清算记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param plusPayoutRecord
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,PlusPayoutRecord plusPayoutRecord){
		 plusPayoutRecord.setIsaudit((short)0);//查询审核通过的
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
       	 List<PlusPayoutRecord> plusPayoutRecords = plusPayoutRecordService.findPlusPayoutRecords(plusPayoutRecord);
       	UserBaseAccountInfo userBaseAccountInfo = null;
        UserBaseAccountInfo paynameuserBaseAccountInfo = null;
        TenderItem tenderItem  = null;
      	 for(PlusPayoutRecord plusPayoutRecord2 : plusPayoutRecords){
      		 if(plusPayoutRecord2.getTenderid() != null){
       		 tenderItem 		= tenderItemServiceI.findTenderItemById(plusPayoutRecord2.getTenderid());
      		 }
      		 
      		 if(tenderItem != null && tenderItem.getTname() != null){
      			plusPayoutRecord2.setTname(tenderItem.getTname());
      			if(tenderItem.getTno() != null){
      				plusPayoutRecord2.setTno(tenderItem.getTno());
      			}
      		 }
      		 
      		 if(plusPayoutRecord2.getInvestorid() != null){
       		userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getInvestorid());
      		 }
      		 
      		 if(userBaseAccountInfo != null && userBaseAccountInfo.getLoginname() != null){
        			userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);
       			plusPayoutRecord2.setInname(userBaseAccountInfo.getLoginname());//投资人名称
     		 }
      		 
      		 if(plusPayoutRecord2.getPaymanid() != null){
      			paynameuserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getPaymanid());
      		 }
      		 
      		 if(paynameuserBaseAccountInfo != null && paynameuserBaseAccountInfo.getLoginname() != null){
      			plusPayoutRecord2.setPayname(paynameuserBaseAccountInfo.getLoginname());
      		 }
      		 
      	 }
       	 PageInfo<PlusPayoutRecord> pagehelper = new PageInfo<PlusPayoutRecord>(plusPayoutRecords);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
       	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/plusPayoutRecord/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
 	 
	 /**
	 	 * @Title: list 
		 * @Description: TODO(标的增益清算记录查询通用方法) 
		 * @param @param plusPayoutRecord
		 * @param @return  参数说明 
		 * @return ModelAndView    返回类型 
		 * @author chenjiaming
		 * @throws
		 */
		 @RequestMapping("/recordList")
	 	 public ModelAndView Recordlist(PlusPayoutRecord plusPayoutRecord){
			 plusPayoutRecord.setLgisaudit((short)0);//查询审核通过的
  	   		 int num = 1;
			 int pageSize = 20;
	 		 String sort = "id.desc";
			 Order.formString(sort);
			 PageHelper.startPage(num, pageSize);
	       	 List<PlusPayoutRecord> plusPayoutRecords = plusPayoutRecordService.findPlusPayoutRecords(plusPayoutRecord);
	       	 
	       	 UserBaseAccountInfo userBaseAccountInfo = null;
	         UserBaseAccountInfo paynameuserBaseAccountInfo = null;
	         TenderItem tenderItem  = null;
	       	 for(PlusPayoutRecord plusPayoutRecord2 : plusPayoutRecords){
	       		 if(plusPayoutRecord2.getTenderid() != null){
	        		 tenderItem 		= tenderItemServiceI.findTenderItemById(plusPayoutRecord2.getTenderid());
	       		 }
	       		 
	       		 if(tenderItem != null && tenderItem.getTname() != null){
	       			plusPayoutRecord2.setTname(tenderItem.getTname());
	       			if(tenderItem.getTno() != null){
	       				plusPayoutRecord2.setTno(tenderItem.getTno());
	       			}
	       		 }
	       		 
	       		 if(plusPayoutRecord2.getInvestorid() != null){
	        		userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getInvestorid());
	       		 }
	       		 
	       		 if(userBaseAccountInfo != null && userBaseAccountInfo.getLoginname() != null){
	        			userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);

	       			plusPayoutRecord2.setInname(userBaseAccountInfo.getLoginname());//投资人名称
	      		 }
	       		 
	       		 if(plusPayoutRecord2.getPaymanid() != null){
	       			paynameuserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getPaymanid());
	       		 }
	       		 
	       		 if(paynameuserBaseAccountInfo != null && paynameuserBaseAccountInfo.getLoginname() != null){
	       			plusPayoutRecord2.setPayname(paynameuserBaseAccountInfo.getLoginname());
	       		 }
 	       	 }
	       	 PageInfo<PlusPayoutRecord> pagehelper = new PageInfo<PlusPayoutRecord>(plusPayoutRecords);
	      	 pagehelper.setFirstPage(1);
	      	 int lasePageNum = 0;
	      	 if(pagehelper.getTotal() % pageSize ==0){
	      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
	      	 }else{
	      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
	      	 }
	      	 pagehelper.setLastPage(lasePageNum);
	      	 
	      	 ModelAndView modelAndView = new ModelAndView();	
	      	 modelAndView.setViewName("admin/plusPayoutRecord/recordList");
	      	 modelAndView.addObject("pagehelper", pagehelper);
	 		 return modelAndView;
	 	 }
		 
		/**
		 * 
		* @Title: template 
		* @Description: TODO(标的增益清算记录模板方法 ,下一页，根据用户名模糊查询通通进这里) 
		* @param @param request
		* @param @param response
		* @param @param plusPayoutRecord
		* @param @return  参数说明 
		* @return ModelAndView    返回类型 
		* @author chenjiaming
		* @throws
		 */
		 @RequestMapping("/recordTemplate")
	 	 public ModelAndView recordTemplate(HttpServletRequest request,HttpServletResponse response,PlusPayoutRecord plusPayoutRecord){
			 plusPayoutRecord.setLgisaudit((short)0);
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
	       	 List<PlusPayoutRecord> plusPayoutRecords = plusPayoutRecordService.findPlusPayoutRecords(plusPayoutRecord);
	       	 UserBaseAccountInfo userBaseAccountInfo = null;
	         UserBaseAccountInfo paynameuserBaseAccountInfo = null;
	         TenderItem tenderItem  = null;
	       	 for(PlusPayoutRecord plusPayoutRecord2 : plusPayoutRecords){
	       		 if(plusPayoutRecord2.getTenderid() != null){
	        		 tenderItem 		= tenderItemServiceI.findTenderItemById(plusPayoutRecord2.getTenderid());
	       		 }
	       		 
	       		 if(tenderItem != null && tenderItem.getTname() != null){
	       			plusPayoutRecord2.setTname(tenderItem.getTname());
	       			if(tenderItem.getTno() != null){
	       				plusPayoutRecord2.setTno(tenderItem.getTno());
	       			}
	       		 }
	       		 
	       		 if(plusPayoutRecord2.getInvestorid() != null){
	        		userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getInvestorid());
	       		 }
	       		 
	       		 if(userBaseAccountInfo != null && userBaseAccountInfo.getLoginname() != null){
	        			userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);

	       			plusPayoutRecord2.setInname(userBaseAccountInfo.getLoginname());//投资人名称
	      		 }
	       		 
	       		 if(plusPayoutRecord2.getPaymanid() != null){
	       			paynameuserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord2.getPaymanid());
	       		 }
	       		 
	       		 if(paynameuserBaseAccountInfo != null && paynameuserBaseAccountInfo.getLoginname() != null){
	       			plusPayoutRecord2.setPayname(paynameuserBaseAccountInfo.getLoginname());
	       		 }
	       		 
	       	 }
	       	 PageInfo<PlusPayoutRecord> pagehelper = new PageInfo<PlusPayoutRecord>(plusPayoutRecords);
	      	 pagehelper.setFirstPage(1);
	      	 int lasePageNum = 0;
	      	 if(pagehelper.getTotal() % pageSize ==0){
	      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
	      	 }else{
	      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
	      	 }
	      	 pagehelper.setLastPage(lasePageNum);
	       	 ModelAndView modelAndView = new ModelAndView();	
	      	 modelAndView.setViewName("admin/plusPayoutRecord/recordListTemplate");
	      	 modelAndView.addObject("pagehelper", pagehelper);
	 		 return modelAndView;
	 	 }
	 	 
	 
	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看标的增益清算记录详情信息) 
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
  		     PlusPayoutRecord  plusPayoutRecord = plusPayoutRecordService.findPlusPayoutRecordById(new BigDecimal(opid));
  		   UserBaseAccountInfo userBaseAccountInfo = null;
           UserBaseAccountInfo paynameuserBaseAccountInfo = null;
           TenderItem tenderItem  = null;
         	if(plusPayoutRecord != null){
         		 if(plusPayoutRecord.getTenderid() != null){
         			 tenderItem 		= tenderItemServiceI.findTenderItemById(plusPayoutRecord.getTenderid());
         		 }
         		 
         		 if(tenderItem != null && tenderItem.getTname() != null){
         			plusPayoutRecord.setTname(tenderItem.getTname());
         			if(tenderItem.getTno() != null){
         				plusPayoutRecord.setTno(tenderItem.getTno());
         			}
         		 }
         		 
         		 if(plusPayoutRecord.getInvestorid() != null){
          		userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord.getInvestorid());
         		 }
         		 
         		 if(userBaseAccountInfo != null && userBaseAccountInfo.getLoginname() != null){
            			userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);

         			plusPayoutRecord.setInname(userBaseAccountInfo.getLoginname());//投资人名称
        		 }
         		 
         		 if(plusPayoutRecord.getPaymanid() != null){
         			paynameuserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(plusPayoutRecord.getPaymanid());
         		 }
         		 
         		 if(paynameuserBaseAccountInfo != null && paynameuserBaseAccountInfo.getLoginname() != null){
         			plusPayoutRecord.setPayname(paynameuserBaseAccountInfo.getLoginname());
         		 }
         		 
         	 }
   		     modelAndView.addObject("plusPayoutRecord", plusPayoutRecord);
  		     modelAndView.setViewName("admin/plusPayoutRecord/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
   	 }
   	
	 /**
	  * 
	 * @Title: doAudit 
	 * @Description: TODO(审核操作) 
	 * @param @param plusPayoutRecord
	 * @param @param request
	 * @param @param response  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	 @RequestMapping(value="/doAudit",method = RequestMethod.POST)
	 public void doAudit(PlusPayoutRecord plusPayoutRecord,HttpServletRequest request,
			  HttpServletResponse response){
		 Map<String,String> hashMap = new HashMap<String,String>();
		 String audit = request.getParameter("audit");
		 if(plusPayoutRecord.getId() != null && StringUtil.isNotEmpty(audit)){
 			 PlusPayoutRecord payoutRecord = plusPayoutRecordService.findPlusPayoutRecordById(plusPayoutRecord.getId());
 			 AdminUser adminUser = getAdminUser(); 
 			 if(payoutRecord != null 
					 && payoutRecord.getIsaudit() != null 
					 && payoutRecord.getIsaudit().equals((short)0)){//未审核才进这里 防止有人在浏览器串改表单数据
 				 if(audit.equals("0")){//审核不通过
					 payoutRecord.setIsaudit((short)2);//2 审核失败
					 payoutRecord.setAudittime(new Date());//审核时间
				     payoutRecord.setAuditman(adminUser.getUsername());;//审核人
					 if(plusPayoutRecord.getRemark() != null){//防止空指针异常
 						 payoutRecord.setRemark(plusPayoutRecord.getRemark());//备注信息
					 }
 					 int count = 0;
					 count =  plusPayoutRecordService.updateById(payoutRecord);
					 if(count > 0){
						 hashMap.put("result", "success");
					 }else{
						 hashMap.put("result", "fail");
					 }
				 }else if(audit.equals("1")){//1 审核通过
					 boolean flag = doTransferParams(payoutRecord, request);
					 if(flag){
 						 payoutRecord.setIsaudit((short)1);//1 审核成功
 						 payoutRecord.setIsgrant((short)2);//2处理中
 						 payoutRecord.setAudittime(new Date());//审核时间
 						 payoutRecord.setAuditman(adminUser.getUsername());;//审核人
 						 if(plusPayoutRecord.getRemark() != null){//防止空指针异常
							 payoutRecord.setRemark(plusPayoutRecord.getRemark());//备注信息
						 }
						 int count = 0;
						 count =  plusPayoutRecordService.updateById(payoutRecord);
						 if(count > 0){
							 hashMap.put("result", "success");
						 }else{
							 hashMap.put("result", "fail");
						 }
					 }else{
						 hashMap.put("result", "fail");
					 }
 				 }
			 }else{
				 hashMap.put("result", "fail");
			 }
 		 }else{
			 hashMap.put("result", "fail");
		 }
		 String str = JSON.toJSONString(hashMap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
 	 }
	 
	 /**
	  * 
	 * @Title: batchgo 
	 * @Description: TODO(批量审核操作) 
	 * @param @param request
	 * @param @param response  参数说明 
	 * @return void    返回类型 
	 * @author cjm
	 * @throws
	  */
	 @RequestMapping(value="/batchgo",method=RequestMethod.POST)
	 public void batchgo(HttpServletRequest request,HttpServletResponse response){
		 String opid = request.getParameter("opid");
		 String mark = request.getParameter("mark");
		 System.out.println(opid+"==="+mark);
		 Map<String,String> hashmap = new HashMap<String,String>();
		 if(StringUtil.isNotEmpty(opid) && StringUtil.isNotEmpty(mark)){
			 AdminUser adminUser = getAdminUser(); 
			 String[] strs = opid.split(",");
			 int size = 0;
			 for(int i = 0;i<strs.length;i++){
				 PlusPayoutRecord payoutRecord = plusPayoutRecordService.
						 findPlusPayoutRecordById(new BigDecimal(strs[i]));
				 if(payoutRecord != null){
					 if(mark.equals("1")){//审核通过
						 boolean flag = doTransferParams(payoutRecord, request);//调用汇付平台转账接口
						 if(flag){
	 						 payoutRecord.setIsaudit((short)1);//1 审核成功
	 						 payoutRecord.setIsgrant((short)2);//2处理中
	 						 payoutRecord.setAudittime(new Date());//审核时间
	 						if(adminUser.getUsername() != null){//防止空指针异常
	 						 payoutRecord.setAuditman(adminUser.getUsername());;//审核人
	 						}
	 						int count = 0;
	 						count = plusPayoutRecordService.updateById(payoutRecord);
	 						if(count > 0){
	 							size ++;
	 						}
						 }
					  }else if(mark.equals("0")){//审核不通过
						  payoutRecord.setIsaudit((short)2);//2 审核失败
						  payoutRecord.setAudittime(new Date());//审核时间
						  if(adminUser.getUsername() != null){//防止空指针异常
 							  payoutRecord.setAuditman(adminUser.getUsername());;//审核人
						  }
						   int count = 0;
						   count = plusPayoutRecordService.updateById(payoutRecord);
						   if(count > 0){
							   size ++;
	 						}
 					  }
				 }  
			 }
			 if(size == strs.length){
				 hashmap.put("result", "success");
			 }else{
				 hashmap.put("result", "fail");
			 }
 		 }else{
			 hashmap.put("result", "no_null");
		 }
		 String str = JSON.toJSONString(hashmap);
		 try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
	 }
	 /**
	  * 
	 * @Title: doTransferParams 
	 * @Description: TODO(调用汇付转账接口 ) 
	 * @param @param payoutRecord
	 * @param @param request
	 * @param @return  参数说明 
	 * @return boolean    返回类型 
	 * @author cjm
	 * @throws
	  */
	 public boolean doTransferParams(PlusPayoutRecord payoutRecord,HttpServletRequest request){
		 boolean flag = false;
		 UserFsAccountInfo UserFSAccountInfo = userFSAccountInfoService.findUserFsAccountInfoByBaseId(payoutRecord.getInvestorid());
		 if(UserFSAccountInfo != null && UserFSAccountInfo.getUsrcustid() != null){
			 DecimalFormat decimalFormat = new DecimalFormat("########################0.00");
			 String transAmt = decimalFormat.format(payoutRecord.getIntprofit() + payoutRecord.getLikevoucherprofit()).toString();//转账金额
			 String inCustId = UserFSAccountInfo.getUsrcustid();//入账客户号
			 String ordId    = payoutRecord.getPporderno();//转账流水号
			 String result = "";
			 result   = HuifuTransferController.doTransferParams(request, transAmt, inCustId, ordId);
			 if(StringUtil.isNotEmpty(result)){
 				 JSONObject jsonObject = JSONObject.parseObject(result);
				 String respCode =  jsonObject.getString("RespCode");//应答返回码
	 			 if(respCode != null && respCode.equals("000")){
	 				flag = true;
				 }
			 }
		 }
		 return flag;
	 }
	 
 }
