package com.ptpl.controller.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huifu.util.httpClient.HttpClientHandler;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.moneymoremore.util.Common;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Award_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.UserRedHttpUpload;
import com.ptpl.controller.moneymoremore.model.LoanInfoBean;
import com.ptpl.controller.moneymoremore.model.LoanSubmitInfoBean;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserOutsideAward;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBonusPointsServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserOutsideAwardServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/admin/activityAwardList")
public class ActivityAwardListController extends BaseController{
	@Autowired
	private ActivityAwardListServiceI activityAwardListService;//获奖人
	@Autowired
	private UserRedEnvelopeServiceI userRedEnvelopeService;//红包管理
	@Autowired
	private UserBonusPointsServiceI userBonusPointsService;//积分管理
	@Autowired
	private UserInterestRateCouponServiceI userInterestRateCouponService;//券管理
	@Autowired
	private UserOutsideAwardServiceI userOutsideAwardService;//站外实物管理
    @Autowired
    private AwardServiceI awardService;//奖品
    @Autowired
    private UserAccountServiceI userAccountService;//用户账户表
    @Autowired
    private UserFsAccountInfoServiceI userFsAccountInfoService;//用户托管账户信息Service
    @Autowired
	private RedEnveLopeItemServiceI redEnveLopeItemService;//红包发放对账记录表
    @Autowired
	private AccInExRecordServiceI accInExRecordService;//账户收支记录表
    @Autowired
    private BacthFileRecordServiceI bacthFileRecordService;//批量文件记录表
    @Autowired
	private OracleSequenceMaxValueIncrementer trqtnumber;//获取序列号（max：999999）
	// 获奖列表页面(待审核状态)
	@RequestMapping(value = "/selectActivityAwardListByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectAwardByCondition(HttpServletRequest request,ActivityAwardList aal) {
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
		if(StringUtils.isNotBlank(aal.getUsername())){
			aal.setUsername(setEncrypt(aal.getUsername()));
		}
		List<ActivityAwardList> aals = activityAwardListService.selectByCondition(aal);
		for(ActivityAwardList a:aals){
			a.setUsername(getDecrypt(a.getUsername()));
		}
		
		PageInfo<ActivityAwardList> pagehelper = new PageInfo<ActivityAwardList>(aals);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		
		if(StringUtils.isNotBlank(aal.getUsername())){
			aal.setUsername(getDecrypt(aal.getUsername()));
		}
		
		// 把对象放进modelAndView中
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		//modelAndView.addObject("statusmaps",ActAward_Constant.STATUS_MAP);
		modelAndView.addObject("statusauditmaps",ActAward_Constant.STATUS_AUDIT_MAP);
		modelAndView.addObject("aal",aal);
		
		// 指定视图
		modelAndView.setViewName("admin/activityAwardList/ActivityAwardList_List");
		return modelAndView;
	}
	
	// 获奖列表页面(发放奖品)
	@RequestMapping(value = "/selectActivityAwardListSend", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectAwardBySend(HttpServletRequest request,ActivityAwardList aal) {
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
		if(StringUtils.isNotBlank(aal.getUsername())){
			aal.setUsername(setEncrypt(aal.getUsername()));
		}
		List<ActivityAwardList> aals = activityAwardListService.selectByConditionTwo(aal);
		for(ActivityAwardList a:aals){
			a.setUsername(getDecrypt(a.getUsername()));
		}
		PageInfo<ActivityAwardList> pagehelper = new PageInfo<ActivityAwardList>(aals);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		
		if(StringUtils.isNotBlank(aal.getUsername())){
			aal.setUsername(getDecrypt(aal.getUsername()));
		}
		
		// 把对象放进modelAndView中
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("statussendmaps",ActAward_Constant.STATUS_SEND_MAP);
		modelAndView.addObject("aal",aal);
		modelAndView.addObject("pageNum",pageNum);
		// 指定视图
		modelAndView.setViewName("admin/activityAwardList/ActivityAwardList_Send");
		return modelAndView;
	}
	
	//不通过
	@RequestMapping("/passedFailure")
	public void passedFailure(HttpServletResponse response,HttpServletRequest request,ActivityAwardList aals){
		if(aals.getStatus()==1){
			aals.setStatus((short)7);// 待审核 -- 领取失败
		}
		aals.setAudittime(new Date());//审核时间
		int rows = activityAwardListService.updateAwardStatusById(aals);
		Map<String,String> map =new HashMap<String,String>();
		if(rows>0){
			map.put("result", "操作成功");
		}else{
			map.put("result", "操作失败");
		}
		String jsonStr= JSON.toJSONString(map);
		try {
			StringUtil.sendJsonData(response, jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//审核用户奖品
	@RequestMapping("/auditActivityAward")
	public ModelAndView auditActivityAward(ActivityAwardList aals,String pageNum){
		ModelAndView mv = new ModelAndView();
		ActivityAwardList activityAwardList=activityAwardListService.getselectById(aals.getId());
		activityAwardList.setUsername(getDecrypt(activityAwardList.getUsername()));
		mv.addObject("activityAwardList",activityAwardList);
		mv.addObject("pageNum",pageNum);
		mv.setViewName("admin/activityAwardList/activityAward_Audit");
		return mv;
	}
	//审核通过
	@RequestMapping("/auditAwardPass")
	public void auditAwardPass(HttpServletRequest request,HttpServletResponse response,ActivityAwardList aals) throws IOException{
		
		aals.setAudittime(new Date());//审核时间
		if(aals.getAwardattribute()>=31){
			//站外奖品
			if(aals.getStatus()==1){
				aals.setStatus((short)4);//待审核--待确认
			}
		}else{
			//站内奖品
			if(aals.getStatus()==1){
				aals.setStatus((short)2);//待审核 -- 待处理
			}
		}
		int rows = activityAwardListService.updateAwardStatusById(aals);
		Map<String,String> map =new HashMap<String,String>();
		if(rows>0){
			map.put("result", "审核成功");
		}else{
			map.put("result", "审核失败");
		}
		String jsonStr= JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
		
	}
	
	//用户的收货信息有问题，需要用户再次确认
	@RequestMapping("/sureRemarkAgain")
	public ModelAndView sureRemarkAgain(ActivityAwardList aals){
		ModelAndView mv = new ModelAndView();
		if(aals.getStatus()==5||aals.getStatus()==6){
			//根据奖品名称查找出领奖规则
			Award award=awardService.getAwardByName(aals.getAwardname());
			aals=activityAwardListService.getselectById(aals.getId());
			mv.addObject("aals",aals);
			mv.addObject("award",award);
			mv.setViewName("admin/activityAwardList/adminAwardRemark");
		}
		return mv;
	}
	
	//管理员填写完备注信息，让用户去再次确认
	@RequestMapping("/toSureRemark")
	public void sureRemarkAgain(HttpServletResponse response,BigDecimal id,ActivityAwardList aals){
		
		aals.setId(id);
		aals.setStatus((short)4);
		int rows = activityAwardListService.updateAwardStatusAndRemarkById(aals);
		Map<String,String> map =new HashMap<String,String>();
		if(rows>0){
			map.put("result", "操作成功");
		}else{
			map.put("result", "操作失败");
		}
		String jsonStr= JSON.toJSONString(map);
		try {
			StringUtil.sendJsonData(response, jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//给用户发放奖品
	@RequestMapping("/sendActivityAward")
	public void sendActivityAward(HttpServletRequest request,HttpServletResponse response,ActivityAwardList aals) throws Exception{
		
		Map<String,String> map =new HashMap<String,String>();
		int rows = 0;
		if(aals.getAwardattribute()>=31){
			//站外奖品
			if(aals.getStatus()==5){
				aals.setStatus((short)6);//已经确认--发货中
				rows = activityAwardListService.updateAwardStatusById(aals);
				if(rows>0){
					map.put("result", "发货成功");
				}else{
					map.put("result", "发货失败");
				}
			}
		}else{
			//站内奖品
			if(aals.getStatus()==2){
				/*aals.setStatus((short)3);//待处理--已领取
				rows = activityAwardListService.updateAwardStatusById(aals);*/
				rows = issueToPrizeDetail(request,aals.getId());
				if(rows>0){
					map.put("result", "发货成功");
				}else{
					map.put("result", "用户账户未开通");
				}
			}
		}
		
		String jsonStr= JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	//用户已经领取奖品
	@RequestMapping("/alreadyReceived")
	public void alreadyReceived(HttpServletRequest request,HttpServletResponse response,ActivityAwardList aals) throws Exception{
		if(aals.getStatus()==6){
			aals.setStatus((short)3);// 发货中--已领取
		}
		int rows = activityAwardListService.updateAwardStatusById(aals);
		//将奖品发放到奖品明细表
		int lines = issueToPrizeDetail(request,aals.getId());
		Map<String,String> map =new HashMap<String,String>();
		if(rows>0&&lines>0){
			map.put("result", "操作成功");
		}else{
			map.put("result", "操作失败");
		}
		String jsonStr= JSON.toJSONString(map);
		try {
			StringUtil.sendJsonData(response, jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//用户奖品领取失败
	@RequestMapping("/receiveFailed")
	public void receiveFailed(HttpServletRequest request,HttpServletResponse response,ActivityAwardList aals) throws Exception{
		if(aals.getStatus()==6){
			aals.setStatus((short)7);// 发货中--领取失败
		}
		int rows = activityAwardListService.updateAwardStatusById(aals);
		//将奖品发放到奖品明细表
		int lines = issueToPrizeDetail(request,aals.getId());
		Map<String,String> map =new HashMap<String,String>();
		if(rows>0&&lines>0){
			map.put("result", "操作成功");
		}else{
			map.put("result", "操作失败");
		}
		String jsonStr= JSON.toJSONString(map);
		try {
			StringUtil.sendJsonData(response, jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//将奖品发放到奖品明细表
	public int issueToPrizeDetail(HttpServletRequest request,BigDecimal id) throws Exception{
		
		ActivityAwardList actawardlist=activityAwardListService.getselectById(id);
		Short awardattribute = actawardlist.getAwardattribute();
		int lines=0;
		if(awardattribute==2){
			
		    //更新用户账户表中的类现金  ，假现金
	    	//用户账户表
	    	UserAccount ua = new UserAccount();
	    	ua.setBaseid(actawardlist.getBaseid());//用户id
	    	ua.setRedenvelope(actawardlist.getAwardmoney());//用户红包 （类现金  假现金）
	    	ua.setAvlredenvelope(actawardlist.getAwardmoney());//可用红包
	    	int rows = userAccountService.updateUserAccountEnvelope(ua);
		    if(rows>0){
		    	//类现金
		    	UserRedEnvelope userred= new UserRedEnvelope();
				userred.setBaseid(actawardlist.getBaseid());//用户ID
				userred.setUreno(actawardlist.getAwardno());//奖品编号
				userred.setRectype(actawardlist.getAl().getActtype());//红包获取方式类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖)
				userred.setRetype(actawardlist.getAwardattribute());//红包类型（1.现金，2.类现金，3.假现金）
				userred.setRestime(new Date());//红包发放时间
				userred.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
				
				userred.setIsuse( (short)2);//奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）
				
				Award a = awardService.selectByAwardNo(actawardlist.getAwardno());
				userred.setRedealtime(new Date());//红包的入账时间
			    userred.setRefailtime(a.getEndtime());//到期时间
			    userred.setAuditman(actawardlist.getAuditman());//审核人
			    userred.setAudittime(actawardlist.getAudittime());//审核时间
			    if(actawardlist.getAuditman()==null||"".equals(actawardlist.getAuditman())){
			    	userred.setIsaudit((short)1);//是否审核（0.是，1.否）
			    }else{
			    	userred.setIsaudit((short)0);//是否审核（0.是，1.否）
			    }
			    userred.setPurpose("该红包不能直接提现，只能交易和投标后当本金与所产生的收益后才能提现");
			    userred.setRemark(actawardlist.getRemark());//备注
			    userred.setRedenvelope(actawardlist.getAwardmoney());//红包金额
			    int row = 0;
			    for(int i= 0;i<actawardlist.getAwardquantity();i++){
			    	row = userRedEnvelopeService.insert(userred);
			    }
			    
			    if(row>0){
			    	lines =updateAalDeal(request,actawardlist.getId());//更新奖品的发放状态，处理人和时间
			    }
		    }
		    		    
		}else if(awardattribute==3){
			
		    //更新用户账户表中的类现金  ，假现金
	    	//用户账户表
	    	UserAccount ua = new UserAccount();
	    	ua.setBaseid(actawardlist.getBaseid());//用户id
	    	ua.setRedenvelope(actawardlist.getAwardmoney());//用户红包
	    	ua.setAvlredenvelope(actawardlist.getAwardmoney());//可用红包
	    	int rows = userAccountService.updateUserAccountEnvelope(ua);
		    if(rows>0){
		    	//假现金
		    	UserRedEnvelope userred= new UserRedEnvelope();
				userred.setBaseid(actawardlist.getBaseid());//用户ID
				userred.setUreno(actawardlist.getAwardno());//奖品编号
				userred.setRectype(actawardlist.getAl().getActtype());//红包获取方式类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖)
				userred.setRetype(actawardlist.getAwardattribute());//红包类型（1.现金，2.类现金，3.假现金）
				userred.setRestime(new Date());//红包发放时间
				
				userred.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
				
				userred.setIsuse( (short)2);//奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）
				
				Award a = awardService.selectByAwardNo(actawardlist.getAwardno());
				userred.setRedealtime(new Date());//红包到账时间
			    userred.setRefailtime(a.getEndtime());//到期时间
			    userred.setAuditman(actawardlist.getAuditman());//审核人
			    userred.setAudittime(actawardlist.getAudittime());//审核时间
			    if(actawardlist.getAuditman()==null||"".equals(actawardlist.getAuditman())){
			    	userred.setIsaudit((short)1);//是否审核（0.是，1.否）
			    }else{
			    	userred.setIsaudit((short)0);//是否审核（0.是，1.否）
			    }
			    userred.setPurpose("该红包不能直接提现，只能交易和投标后所产生的利息收益才能提现");
			    userred.setRemark(actawardlist.getRemark());//备注
			    userred.setRedenvelope(actawardlist.getAwardmoney());//红包金额
			    
			    int row = 0;
			    for(int i=0;i<actawardlist.getAwardquantity();i++){
			    	row = userRedEnvelopeService.insert(userred);
			    }
			    if(row>0){
			    	lines = updateAalDeal(request,actawardlist.getId());//更新奖品的发放状态，处理人和时间
			    }
		    }
		    
		}else if(awardattribute==4 || awardattribute==5){//4.交易积分，5.系统积分（不可交易）
			
		 	//更新用户账户表中的交易积分
	    	//用户账户表
	    	UserAccount ua = new UserAccount();
	    	ua.setBaseid(actawardlist.getBaseid());
	    	//System.out.println("积分金额===="+actawardlist.getAwardmoney());
	    	if(awardattribute==4){//交易积分
	    		ua.setTradePoints(new BigDecimal(actawardlist.getAwardmoney()));//用户交易积分
	    		ua.setAvlTradePoints(new BigDecimal(actawardlist.getAwardmoney()));//可用交易积分
	    	}else if(awardattribute==5){//系统积分（不可交易）
	    		ua.setBonuspoints(new BigDecimal(actawardlist.getAwardmoney()));//系统积分
	    	}
	    	
	    	System.out.println("系统积分===="+ua.getBonuspoints());
	    	
	    	int rows = userAccountService.updateUserAccountPoint(ua);
	    	
	    	if(rows>0){
	    		//积分表
				UserBonusPoints usbopo=new UserBonusPoints();
				usbopo.setBaseid(actawardlist.getBaseid());//用户Id
				usbopo.setUbpno(actawardlist.getAwardno());//积分编号（对应奖品表编号）
				usbopo.setBptype(actawardlist.getAl().getActtype());//积分类型 (1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖)
				usbopo.setBpUseType(awardattribute);//积分类型
				usbopo.setBpstime(new Date());//积分发放时间
				usbopo.setBonuspoints(new Long(actawardlist.getAwardmoney().longValue()));//积分
				usbopo.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
				usbopo.setBpdealtime(new Date());//积分处理时间（入账）
				if(actawardlist.getAuditman()==null||"".equals(actawardlist.getAuditman())){
					usbopo.setIsaudit((short)1);//是否审核（0.是，1.否）
			    }else{
			    	usbopo.setIsaudit((short)0);//是否审核（0.是，1.否）
			    }
				usbopo.setAuditman(actawardlist.getAuditman());//审核人
				usbopo.setAudittime(actawardlist.getAudittime());//审核时间
				usbopo.setRemark(actawardlist.getRemark());//备注
				int row = 0;
				for(int i=0;i<actawardlist.getAwardquantity();i++){
					row = userBonusPointsService.insert(usbopo);
				}
				if(row>0){
					lines = updateAalDeal(request,actawardlist.getId());
				}
	    	}
		    
		}else if(awardattribute==6){/**加息券*/
			//用户使用券表
			UserInterestRateCoupon usinra=new UserInterestRateCoupon();
			usinra.setBaseid(actawardlist.getBaseid());//用户Id
			//System.out.println("baseID>>>"+usinra.getBaseid());
			usinra.setUircno(actawardlist.getAwardno());//券编号（对应奖品表编号）
			//System.out.println("券编号》》》"+usinra.getUircno());
			
			usinra.setUirctype((short)6);//券的类型
			System.out.println("券利率===="+actawardlist.getAwardmoney());
			usinra.setIcrate(actawardlist.getAwardmoney());//券利率
			//System.out.println("抵用金额》》"+usinra.getVouchercash());
			usinra.setPurpose("投标增加利息");//说明
			//System.out.println("说明>>>"+usinra.getPurpose());
			
			System.out.println("券的类型》》》"+usinra.getUirctype());
			usinra.setIctype(actawardlist.getAl().getActtype());//券类型
			//System.out.println("券类型》》》"+usinra.getIctype());
			usinra.setIctime(new Date());//发放时间
			//System.out.println("券的发放时间》》》》》"+usinra.getIctime());
			usinra.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
			//System.out.println("券的发放状态》》》"+usinra.getStatus());
			
			usinra.setIsuse((short)2);//奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）
			//System.out.println("券的奖品状态》》"+usinra.getIsuse());
			
			
			usinra.setIcdealtime(new Date());//券处理时间（入账）
			//System.out.println("券的处理时间》》》"+usinra.getIcdealtime());
			Award a = awardService.selectByAwardNo(actawardlist.getAwardno());
		    usinra.setIcfailtime(a.getEndtime());//到期时间
		    //System.out.println("券的到期时间》》》"+usinra.getIcfailtime());
		    usinra.setAuditman(actawardlist.getAuditman());//审核人
		    //System.out.println("审核人》》》"+usinra.getAuditman());
		    usinra.setAudittime(actawardlist.getAudittime());//审核时间
		    //System.out.println("审核时间》》》"+usinra.getAudittime());
		    if(actawardlist.getAuditman()==null||"".equals(actawardlist.getAuditman())){
		    	usinra.setIsaudit((short)1);//是否审核（0.是，1.否）
		    }else{
		    	usinra.setIsaudit((short)0);//是否审核（0.是，1.否）
		    }
		    //System.out.println("审核》》》"+usinra.getIsaudit());
		    usinra.setRemark(actawardlist.getRemark());//备注
		   // System.out.println("备注》》》"+usinra.getRemark());
		   // System.out.println("加息券》》》"+usinra);
		    int row = 0;
			for(int i=0;i<actawardlist.getAwardquantity();i++){
				row =userInterestRateCouponService.insert(usinra);
			}
		    if(row>0){
		    	lines = updateAalDeal(request,actawardlist.getId());
		    }
		}else if(awardattribute>=31){
			//用户站外奖品表
			UserOutsideAward usouaw=new UserOutsideAward();
			usouaw.setBaseid(actawardlist.getBaseid());//用户Id
			usouaw.setUoawardno(actawardlist.getAwardno());//站外奖品编号
			usouaw.setUoawardname(actawardlist.getAwardname());//奖品名
			usouaw.setUoatype(actawardlist.getAl().getActtype());//奖品来源类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖)
			usouaw.setUoatime(new Date());//发放时间
			usouaw.setStatus(actawardlist.getStatus());//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
			if(actawardlist.getStatus()==3){
				usouaw.setIsuse((short)4);//4.已使用
			}
			//生成发放编号
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String sendNo = "GJ"+sdf.format(new Date())+new Random().nextInt(99999);
			usouaw.setSendno(sendNo);
				
			usouaw.setAuditman(actawardlist.getAuditman());//审核人
			usouaw.setAudittime(actawardlist.getAudittime());//审核时间
			if(actawardlist.getAuditman()==null||"".equals(actawardlist.getAuditman())){
				usouaw.setIsaudit((short)1);//是否审核（0.是，1.否）
		    }else{
		    	usouaw.setIsaudit((short)0);//是否审核（0.是，1.否）
		    }
			usouaw.setRemark(actawardlist.getRemark());//备注
			int row = 0;
			for(int i=0;i<actawardlist.getAwardquantity();i++){
				row = userOutsideAwardService.insert(usouaw);
			}
			if(row>0){
				//更改奖品的处理人和处理时间
				AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER); 
				ActivityAwardList activityAwardList = new ActivityAwardList();
				activityAwardList.setId(id);
				activityAwardList.setDealman(au.getUsername());//处理人
				activityAwardList.setDealtime(new Date());//处理时间
				lines =activityAwardListService.update(activityAwardList);
			}
		}
		return lines;
	}
	
	//更新奖品名单的处理人和处理时间
	public int updateAalDeal(HttpServletRequest request,BigDecimal id){
		
	    AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER); 
	    ActivityAwardList activityAwardList = new ActivityAwardList();
	    activityAwardList.setId(id);
	    activityAwardList.setStatus((short)3);//待处理--已领取
	    activityAwardList.setDealman(au.getUsername());//处理人
	    activityAwardList.setDealtime(new Date());//处理时间
	    int line =activityAwardListService.update(activityAwardList);
	    return line;
	}
	
	// 根据id查找获奖人详细页面
	@RequestMapping(value = "/selectActivityAwardListByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectActivityAwardListByPrimaryKey(String id) {
		
		ActivityAwardList aal = activityAwardListService.getselectById(new BigDecimal(id));
		aal.setUsername(getDecrypt(aal.getUsername()));
				
		ModelAndView mv = new ModelAndView();
		mv.addObject("activityAwardList", aal);
		mv.addObject("attribute",Award_Constant.attributeData_map.get(aal.getAwardattribute()));
		mv.setViewName("admin/activityAwardList/ActivityAwardList_Detail");
		return mv;
	}

	// 转发到修改获奖人页面
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(String id) {
		ModelAndView modelAndView = selectActivityAwardListByPrimaryKey(id);
		modelAndView.setViewName("admin/activityAwardList/ActivityAwardList_Update");
		return modelAndView;
	}

	// 修改获奖人
	@RequestMapping(value = "/updateActivityAwardList", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateAward(ActivityAwardList activityAwardList) {
		System.out.println(activityAwardList);
		//activityAwardListService.update(activityAwardList);
		return "redirect:/activityAwardList/selectActivityAwardListByCondition.action";
	}
	
	//批量审核（通过与不通过）
	@RequestMapping("/batchAudit")
	public void batchAudit(HttpServletRequest request,HttpServletResponse response,String audit,String ids) throws IOException{
		
		System.out.println("ids===="+ids);
		AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		String[] idArr = ids.split(",");
		int count=0;
		for(int i=0;i<idArr.length;i++){
			System.out.println("idArr[i]==="+idArr[i]);
			if("1".equals(audit)){
				//审核通过
				//根据奖品的id查询得对应的奖品的属性
				ActivityAwardList aal =activityAwardListService.getAwardattributeById(new BigDecimal(idArr[i]));
				System.out.println("aal===="+aal);
				aal.setAudittime(new Date());//审核时间
				aal.setAuditman(au.getUsername());//审核人
				if(aal.getStatus()==1){//待审核
					
					if(aal.getAwardattribute()>=31){
						//站外奖品
						aal.setStatus((short)4);//待审核--待确认
						
					}else{
						//站内奖品
						aal.setStatus((short)2);//待审核 -- 待处理
					}
				}
				activityAwardListService.updateAwardStatusById(aal);
				count++;
				
			}else if("2".equals(audit)){
				//审核不通过
				ActivityAwardList aal = new ActivityAwardList();
				aal.setId(new BigDecimal(idArr[i]));
				aal.setStatus((short)7);// 待审核 -- 领取失败
				activityAwardListService.updateAwardStatusById(aal);
				count++;
			}
			
		}
		String jsonStr =null;
		if(count==idArr.length){
			jsonStr = JSON.toJSONString("success");
		}else{
			jsonStr = JSON.toJSONString("fail");
		}
		sendJsonData1(response,jsonStr);
	}
	
	//批量发货
	@RequestMapping("/batchDeliver")
	public synchronized void batchDeliver(HttpServletRequest request,HttpServletResponse response,String ids,Double money) throws Exception{
		
		//查看余额是否足够
		//获取徽商红包账户
		UserFsAccountInfo u = new UserFsAccountInfo();
		u.setAccounttype((short)2);//账户类型  1.个人    2.企业
		u.setAccPurpose((short)2);//账户用途（1普通，2红包，3手续费）
		u.setMercustid(/*"800114"*/HSignUtil.COINSTCODE);//商户客户号
		UserFsAccountInfo userFsAccountInfo=userFsAccountInfoService.getUsrCustId(u);
		logger.info("===托管账户表==="+userFsAccountInfo);
		//根据托管账户的baseid查询用户账户表，查看余额是否足够
		UserAccount ua = userAccountService.getUserAccountByBaseId(userFsAccountInfo.getBaseid());
		logger.info("===ua==="+ua);
		logger.info("====转账金额==="+ua.getAvlbalance());
		Map<String,String> map = new HashMap<String,String>();
		if(ua.getAvlbalance()<money){
			map.put("result", "unEnough");
			String jsonStr= JSON.toJSONString(map);
			StringUtil.sendJsonData(response, jsonStr);
			return;
		}
		
		System.out.println("ids: "+ids);
		String[] idArr = ids.split(",");
		int lines = 0;
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		for(int i=0;i<idArr.length;i++){
			System.out.println("idArr[i]==="+idArr[i]);
			ActivityAwardList aal=activityAwardListService.getselectById(new BigDecimal(idArr[i]));
			if(aal.getAwardattribute()>=31){
				//站外奖品
				if(aal.getStatus()==5){
					aal.setStatus((short)6);//已经确认--发货中
					activityAwardListService.updateAwardStatusById(aal);
					lines++;
				}
			}else{
				//站内奖品
				if(aal.getStatus()==2){
					/*aals.setStatus((short)3);//待处理--已领取
					rows = activityAwardListService.updateAwardStatusById(aals);*/
					if(aal.getAwardattribute()==1){
						//现金红包券  需要批量转账  将其id先放在一个集合里面
						list.add(aal.getId());
					}else{
						//站内奖品不是现金红包券
						issueToPrizeDetail(request,aal.getId());
						lines++;
					}
				}
			}
			
		}
		
		int row = 0;
		if(list.size() > 0){
			//row = userRedBatchTransfer(request,response,list);//乾多多现金红包批量转账接口
			row = userRedBatchHuiShangTransfer(request,response,list);//徽商银行批量转账接口
		}
		
		if(lines>0 || row>0){
			map.put("result", "success");
		}else if(lines ==0 && row ==0){
			map.put("result", "fail");
		}
		String jsonStr= JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
	
	//徽商银行批量转账接口
	public int userRedBatchHuiShangTransfer(HttpServletRequest request,HttpServletResponse response,List<BigDecimal> list) throws Exception{
		
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyyMMdd");
		DecimalFormat df = new DecimalFormat("0000000000.00");
		String batchNumber = /*(int)((Math.random()*9+1)*100000)+""*/String.format("%06d",trqtnumber.nextIntValue());
		System.out.println("批次号： "+batchNumber);
		String date = sdf.format(new Date());
		File file = null;
		//放在同一天的文件夹内
		String file_path = /*HSignUtil.DISK+*/"/batchfile"+File.separator+HSignUtil.COINSTCODE/*"800114"*/+File.separator+"trqt"+File.separator+date;
		String filePath = "";
		for(int i=0;i<list.size();i++){
			
			//根据id获取对应的获奖记录
			ActivityAwardList aal=activityAwardListService.getselectById(new BigDecimal(list.get(i).toString().trim()));
			System.out.println("aal: "+aal);
			Double amount = aal.getAwardmoney()*aal.getAwardquantity();//金额
			//根据baseid查出当前用户的客户号
			UserFsAccountInfo ufai=userFsAccountInfoService.findUserFsAccountInfoByBaseId(aal.getBaseid());
			if(ufai == null){
				return 0;
			}else if(ufai.getIsopenfsinfo()==0){
				//托管账户没有开通
				return 0;
			}
			
			//文件输出流  如果文件名是一个目录，会抛出异常
			//3004:徽商银行的银行编号
			file = new File(file_path);
			//如果文件夹不存在则创建    
			if  (!file .exists()  && !file .isDirectory()){       
			    file.mkdirs();
			}
			//文件路径
			filePath = file+File.separator+HSignUtil.FILEBANKCODE+"TRQT-"+HSignUtil.COINSTCODE/*"800114"*/+"-"+batchNumber+"-"+"20160621"/*date*/;
			FileOutputStream fos = new FileOutputStream(filePath,true);
			//采用GBK编码
			PrintWriter out = new PrintWriter(new OutputStreamWriter(fos,"GBK"),true);
			out.print("3004");//银行编号  4
			out.print(batchNumber);//批次号  6
			out.print("20160621"/*date*/);//日期 8
			out.print("001");//业务类别 3
			out.print(getDecrypt(ufai.getUsrcustid()));//转入方电子账号 19
			String[] a = df.format(amount).split("\\.");
			out.print(a[0]+a[1]);//入账金额  12
			out.print(156);//币种  人民币
			out.print(isChinese(getDecrypt(aal.getUserbai().getRealname()),60));//持卡人姓名  60
			out.print(isChinese("DESC",40));//自定义交易描述  40
			out.print(isChinese("TRDRESE",100));//保留域 100
			out.print(isChinese(aal.getId().toString(),100));//第三方保留域 100
			out.print("\n");
			out.flush();
			out.close();
			
		}
		
		//插入批量文件记录表
		BacthFileRecord bfr = new BacthFileRecord();
		bfr.setFilePath(file+File.separator);//文件路径
		bfr.setSendFileName(HSignUtil.FILEBANKCODE+"TRQT-"+/*800114*/HSignUtil.COINSTCODE+"-"+batchNumber+"-"+"20160621"/*date*/);//上传文件名称
		bfr.setGetFileName(HSignUtil.FILEBANKCODE+"TRQTRES-"+/*800114*/HSignUtil.COINSTCODE+"-"+batchNumber+"-"+"20160621"/*date*/);//下载文件的名称
		bfr.setCoinstCode(/*"800114"*/HSignUtil.COINSTCODE);//平台编号
		bfr.setPName("干将P2P平台");//平台名称
		bfr.setIsDealResult((short)0);//是否已处理结果文件   0.否   1.是
		bfr.setFileType((short)2);//业务文件类型  1.开户  2.红包转账
		
		//可以将红包转账的批量文件发给徽商银行了
		int line = new UserRedHttpUpload().userHttpUpload("20160621"/*date*/,bfr,bacthFileRecordService);
		if(line > 0){
			for(int i=0;i<list.size();i++){
				//更新奖品名单的处理人和处理时间 
				ActivityAwardList activityAwardList = new ActivityAwardList();
				activityAwardList.setId(new BigDecimal(list.get(i).toString().trim()));
				activityAwardList.setStatus((short)9);//发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败  9.(现金红包)待发放
				//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
				activityAwardList.setDealman(au.getUsername());//处理人
				activityAwardList.setDealtime(new Date());//处理时间
				int row = activityAwardListService.update(activityAwardList);
			}
		}
		return line;
	}
		
	// 判断一个字符是否是中文
	public String isChinese(String s,int length) {
		
		int len = 0;
		for(int i=0;i < s.length();i++){
			char c = s.charAt(i);
			// 根据字节码判断
			if( c >= 0x4E00 &&  c <= 0x9FA5){
				//中文字符
				len+=2;
			}else{
				len+=1;
			}
		}
		for(int i=0;i<length-len;i++){
			s+=" ";
		}
		return s;
	}
	
	//批量转账  红包   乾多多接口的
	public int userRedBatchTransfer(HttpServletRequest request,HttpServletResponse response,List<BigDecimal> list) throws IOException, Exception{
		
		List<LoanInfoBean> listLib = new ArrayList<LoanInfoBean>(); 
		LoanInfoBean lib = null;
		//遍历集合
		for(int i=0;i<list.size();i++){
			
			System.out.println(list.get(i));
			
			//根据id获取对应的获奖记录
			ActivityAwardList aal=activityAwardListService.getselectById(new BigDecimal(list.get(i).toString().trim()));
			System.out.println("aal: "+aal);
			Double amount = aal.getAwardmoney()*aal.getAwardquantity();//金额
			//根据baseid查出当前用户的客户号
			UserFsAccountInfo ufai=userFsAccountInfoService.findUserFsAccountInfoByBaseId(aal.getBaseid());
			if(ufai == null){
				return 0;
			}else if(ufai.getIsopenfsinfo()==0){
				//托管账户没有开通
				return 0;
			}
			
			lib = new LoanInfoBean();
			lib.setLoanOutMoneymoremore("p2089");//付款人乾多多标识
			lib.setLoanInMoneymoremore(ufai.getMoneymoremoreid());//收款人乾多多标识
			String a = StringUtil.getNo();
			System.out.println("a: "+a);
			lib.setOrderNo(a);//网贷平台订单
			lib.setBatchNo(Common.getRandomNum(10));//网贷平台标号  自己定义
			lib.setAmount(amount);//金额
			lib.setRemark(list.get(i).toString().trim());//将获奖名单列表放在这里
			listLib.add(lib);
		}
		
		//将模型进行JSON编码
		String LoanJsonList = Common.JSONEncode(listLib);
		System.out.println("LoanJsonList===="+LoanJsonList);
		
		LoanSubmitInfoBean lsib = new LoanSubmitInfoBean();
		lsib.setLoanJsonList(LoanJsonList);
		lsib.setPlatformMoneymoremore("p2089");//平台乾多多标识
		lsib.setTransferAction(3);//转账类型   1.投标  2.还款  3.其它
		lsib.setAction(2);//操作类型    1.手动转账    2.自动转账
		lsib.setTransferType(2);//转账方式  1.桥连   2.直连
		//lsib.setArrivalTime(null);//到账时间  空.实时转账   1.普通转账   2.次日转账
		lsib.setNeedAudit(1);//通过是否需要审核  空.需要审核   1.自动通过
		//lsib.setRemark1(list.toString());//自定义备注  存放奖品名单列表的id
		String basePath = StringUtil.getBasePath(request);
		//lsib.setReturnURL(basePath+"/admin/activityAwardList/userRedAddressBatchReturn.action");//页面返回网址
		lsib.setNotifyURL(basePath+"/moneymoremore/transfer/user999RedAddressNotify.action");//后台通知网址
		
		int line = userRedBatchSign(lsib,request,response);//加签
		
		return line;
		
	}
	
	//加签 验签以及吹逻辑
	public int userRedBatchSign(LoanSubmitInfoBean lsib,HttpServletRequest request,HttpServletResponse response) throws Exception, IOException{
		
		//后台管理员
		AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		String loanJsonList = lsib.getLoanJsonList();
		String platformMoneymoremore = lsib.getPlatformMoneymoremore();//平台乾多多标识
		Integer transferAction = lsib.getTransferAction();//转账类型
		Integer action = lsib.getAction();//操作类型
		Integer transferType = lsib.getTransferType();//转账方式
		Integer needAudit = lsib.getNeedAudit();//通过是否需要审核
		//String remark1 = lsib.getRemark1();//备注1
		String notifyURL = lsib.getNotifyURL();//后台通知网址
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(loanJsonList))
		  .append(StringUtils.trimToEmpty(platformMoneymoremore))
		  .append(StringUtils.trimToEmpty(transferAction.toString()))
		  .append(StringUtils.trimToEmpty(action.toString()))
		  .append(StringUtils.trimToEmpty(transferType.toString()))
		  .append(StringUtils.trimToEmpty(needAudit.toString()))
		  //.append(StringUtils.trimToEmpty(remark1))
		  //.append(StringUtils.trimToEmpty(ArrivalTime.toString()))
		  //.append(StringUtils.trimToEmpty(returnURL))
		  .append(StringUtils.trimToEmpty(notifyURL));
		
		System.out.println("sb===="+sb);
		
		//签名
		RsaHelper rsa = RsaHelper.getInstance();
		//参加签名的是原串
		String signInfo = rsa.signData(sb.toString(), RsaHelper.privateString);
		System.out.println("loanJsonList: "+loanJsonList);
		System.out.println("platformMoneymoremore(平台标识): "+platformMoneymoremore);
		System.out.println("transferAction（转账类型）: "+transferAction);
		System.out.println("action（手动或自动转账）: "+action);
		System.out.println("transferType（桥连或直连）: "+transferType);
		System.out.println("needAudit（是否需要审核）: "+needAudit);
		System.out.println("notifyURL: "+notifyURL);
		System.out.println("signInfo（签名信息）: "+signInfo);
		
		//提交的时候要进行UrlEncode,编码为utf-8
		String LoanJsonList = Common.UrlEncoder(loanJsonList, "utf-8");
		
		Map<String,String> req = new TreeMap<String,String>();
		req.put("LoanJsonList", LoanJsonList);
		req.put("PlatformMoneymoremore", platformMoneymoremore);
		req.put("TransferAction", transferAction.toString());
		req.put("Action", action.toString());
		req.put("TransferType", transferType.toString());
		req.put("NeedAudit", needAudit.toString());
		//req.put("Remark1", remark1);
		//req.put("ReturnURL", returnURL);
		req.put("NotifyURL", notifyURL);
		req.put("SignInfo", signInfo);
		
		//String[] resultArr = HttpClientUtil.doPostQueryCmd("http://test.moneymoremore.com:88/main/loan/loan.action", req);
		//System.out.println("00===="+resultArr[0]);
		//System.out.println("11===="+resultArr[1]);
		
		String data = doPost(req);
		System.out.println(data);
	
		int line = 0;
		JSONArray jsonArr = JSONObject.parseArray(data);
		for(int j=0;j<jsonArr.size();j++){
			
			JSONObject json = JSONObject.parseObject(jsonArr.get(j).toString());
			System.out.println("json: "+json);
			
			String loanJsonList_ = json.getString("LoanJsonList");
			String platformMoneymoremore_ = json.getString("PlatformMoneymoremore");
			String action_ = json.getString("Action");
			if(action_==null){
				action_="";
			}
			String resultCode_ = json.getString("ResultCode");
			String message_ = json.getString("Message");
			String signInfo_ = json.getString("SignInfo");
			
			String dataStr = Common.UrlDecoder(loanJsonList_, "utf-8").trim() + platformMoneymoremore_.trim() + action_.trim() + resultCode_.trim() ;
			boolean flag = rsa.verifySignature(signInfo_, dataStr, RsaHelper.publicString);
			
			
			JSONArray jsonArray = JSONArray.parseArray(Common.UrlDecoder(loanJsonList_, "utf-8"));
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
				String LoanOutMoneymoremore = jsonObject.getString("LoanOutMoneymoremore");
				String LoanInMoneymoremore = jsonObject.getString("LoanInMoneymoremore");
				String LoanNo = jsonObject.getString("LoanNo");
				String OrderNo = jsonObject.getString("OrderNo");
				String BatchNo = jsonObject.getString("BatchNo");
				String Amount =  jsonObject.getString("Amount");
				String Remark = jsonObject.getString("Remark");
				System.out.println("LoanOutMoneymoremore: "+LoanOutMoneymoremore);
				System.out.println("LoanInMoneymoremore: "+LoanInMoneymoremore);
				System.out.println("LoanNo: "+LoanNo);
				System.out.println("OrderNo: "+OrderNo);
				System.out.println("BatchNo: "+BatchNo);
				System.out.println("Amount: "+Amount);
				System.out.println("Remark: "+Remark);
				
				//处理自己的业务逻辑
				//通过获奖名单里面红包的id获取对应的红包记录
				if(!"88".equals(resultCode_)){
					return 0;
				}
				ActivityAwardList aal=activityAwardListService.getselectById(new BigDecimal(Remark.trim()));
				//现金红包发放对账记录表
				RedEnveLopeItem reli=new RedEnveLopeItem();
				reli.setOrderno(OrderNo);//记录表的流水号 对应订单号
				reli.setRedenvelopeno(aal.getAwardno());//红包编号
				reli.setRedenvelopename(aal.getAwardname());//奖品的名称
				reli.setReamount(Float.parseFloat(Amount));//奖品的金额
				reli.setBaseid(aal.getBaseid());//用户id
				reli.setUsername(aal.getUsername());//用户名
				reli.setBusinesstype(ActAward_Constant.RECTYPE_MAP.get(aal.getAl().getActtype()));//业务类型  5.手动颁奖
				reli.setSendtime(new Date());//处理时间
				reli.setIsblending((short)0);//是否系统勾兑
				reli.setIsmanblending((short)0);//是否人工勾兑
				reli.setPaycompany("乾多多");//托管通道公司（乾多多）
				//先根据流水号判断数据库中是否有该条数据，如果有，不再添加
				RedEnveLopeItem redeli=redEnveLopeItemService.getByOrderNo(OrderNo);
				if(redeli == null){
					int rows = redEnveLopeItemService.insert(reli);
					if(rows>0){
						//用户账户表
						UserAccount usAc=userAccountService.getUserAccountByBaseId(aal.getBaseid());
						Double balance = usAc.getBalance() + new Double(Amount);
						Double avlbalance = usAc.getAvlbalance() + new Double(Amount);
						Double freezeBalance = usAc.getFreezebalance() ==null?0.00 : usAc.getFreezebalance();
						//汇付转账成功后，才给用户余额进账，更新用户账户表
						usAc.setBalance(balance);
						usAc.setAvlbalance(avlbalance);
						//插入数据
						userAccountService.updateUseraccount(usAc);
						
						//账户收支记录表
						AccInExRecord aier = new AccInExRecord();
						aier.setBaseid(aal.getBaseid());//用户的id
						aier.setAieorderno(StringUtil.getNo());//收支记录流水号
						aier.setBorderno(OrderNo);//业务流水号(这里放的是订单编号)
						aier.setType((short)32);//业务类型   现金
						aier.setInamount(new Double(Amount));//收入
						aier.setOutamount(0.00);//支出
						aier.setPaccount("p2089");//平台账户
						aier.setPinamount(0.00);//平台账户收入,平台产生的费用
						aier.setPoutamount(0.00);//平台账户支出,平台产生的费用
						aier.setStatus((short)1);//业务状态  0冻结  1成功 2失败
						aier.setDescription("现金红包转账");//说明
						aier.setBalance(avlbalance);//用户的可用余额
						aier.setFreebalance(freezeBalance);//用户的冻结余额
						aier.setTotalbalance(balance);//用户的总金额
						aier.setRecordtime(new Date());//发生时间
						aier.setRemark("现金红包转账");//备注
						//插入数据
						accInExRecordService.insertSelective(aier);
						
						//插入红包表记录(需要一份一份的插入)
						UserRedEnvelope urel= new UserRedEnvelope();
						urel.setBaseid(aal.getBaseid());//用户ID
						urel.setUreno(aal.getAwardno());//奖品编号
						urel.setRectype(aal.getAl().getActtype());//红包获取方式类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖 )
						urel.setRetype(aal.getAwardattribute());//红包类型（1.现金，2.类现金，3.假现金）
						urel.setRestime(new Date());//红包发放时间
						urel.setRedenvelope(aal.getAwardmoney());//红包金额
						urel.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
						urel.setIsuse((short)4);//奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）
						//根据奖品的编号获取奖品（到期时间）
						Award a = awardService.selectByAwardNo(aal.getAwardno());
					    urel.setRefailtime(a.getEndtime());//到期时间
					    if(aal.getAuditman()==null||"".equals(aal.getAuditman())){
					    	urel.setIsaudit((short)1);//是否审核（0.是，1.否）
					    }else{
					    	urel.setIsaudit((short)0);//是否审核（0.是，1.否）
					    }
					    urel.setAuditman(aal.getAuditman());//审核人
					    urel.setAudittime(aal.getAudittime());//审核时间
					    urel.setPurpose("该红包可以直接提现");
					    urel.setRemark(aal.getRemark());//备注
					    urel.setRedealtime(new Date());//红包的入账时间
					    for(int k = 0;k<aal.getAwardquantity();k++){
					    	int lines = userRedEnvelopeService.insert(urel);
					    }
					    
					    //更新奖品名单的处理人和处理时间 
					    ActivityAwardList activityAwardList = new ActivityAwardList();
					    activityAwardList.setId(aal.getId());
					    activityAwardList.setStatus((short)3);//发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败 
					    activityAwardList.setDealman(au.getUsername());//处理人
					    activityAwardList.setDealtime(new Date());//处理时间
					    line = activityAwardListService.update(activityAwardList);
					}
				}
				
			}
		}
		return line;
	}
	
	// 如果关注性能问题可以考虑使用HttpClientConnectionManager
   	public static String doPost(Map<String,String> hfMap) throws ClientProtocolException, IOException{
   		
   		String result = null;
   		if (hfMap != null){
   			// 目标地址 
   			HttpPost httpPost = new HttpPost("http://test.moneymoremore.com:88/main/loan/loan.action");//http://mertest.chinapnr.com/muser/publicRequests
   			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(hfMap);
   			httpPost.setEntity(formEntiry);
   			
   			CloseableHttpClient httpclient = HttpClients.createDefault();
   			// 执行    
   			CloseableHttpResponse response = httpclient.execute(httpPost);
   			try{
   			
   				HttpEntity entity = response.getEntity();
   				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
   					//把内容转成字符串 
   					result = EntityUtils.toString(entity, "UTF-8");
   					//关闭HttpEntity流
   				    EntityUtils.consume(entity);
   			} finally{
   				response.close();
   			}
   		}
   		return result;
   	}
	
	@SuppressWarnings("unused")
	private void sendJsonData1(HttpServletResponse response, String data)throws IOException {
			
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		out = response.getWriter();
		out.println(data);
		out.flush();
		out.close();
	}

}
