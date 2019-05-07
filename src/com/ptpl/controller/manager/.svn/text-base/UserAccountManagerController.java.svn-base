package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccountFreezeThaw;
import com.ptpl.model.AdminUser;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.AccountFreezeThawServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.HttpClientUtil;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping(value="/admin/userAccount")
public class UserAccountManagerController extends BaseController{
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	UserFsAccountInfoServiceI  userFsAccountInfoService;
	@Autowired
	UserBaseAccountInfoServiceI  userBaseAccountInfoService;
	@Autowired
	private AccountFreezeThawServiceI accountFreezeThawService;
	
	//用户账户信息
	@RequestMapping(value = "/accountInfoList", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView userAccountInfo(UserBaseAccountInfo ubai){
		ModelAndView mv = new ModelAndView();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String accountnumber = ubai.getAccountnumber();
		String mobilephone = ubai.getMobilephone();
		String realname = ubai.getRealname();
		String loginname=ubai.getLoginname();
		request.setAttribute("accountnumber", accountnumber);
		request.setAttribute("mobilephone", mobilephone);
		request.setAttribute("realname", realname);
		request.setAttribute("loginname", loginname);
		
		ubai.setMobilephone(AES.getEncrypt(mobilephone));
		ubai.setRealname(AES.getEncrypt(realname));
		ubai.setLoginname(AES.getEncrypt(loginname));
		Map<String,Object> map = new HashMap<String,Object>();
		pageSize = "10";
		
		this.initPage(map, pageNum, pageSize);
		List<UserAccount> userAccountList = userAccountService.queryAllUserAccount(ubai);
		for(UserAccount account:userAccountList){
			String showName=account.getUbai().getRealname();
			String showMobile=account.getUbai().getMobilephone();
			String showLoginName=account.getUbai().getLoginname();
			if(StringUtils.isNotEmpty(showName)){
				account.getUbai().setRealname(AES.getDecrypt(showName));
			}
			if(StringUtils.isNotEmpty(showMobile)){
				account.getUbai().setMobilephone(AES.getDecrypt(showMobile));
			}
			if(StringUtils.isNotEmpty(showLoginName)){
				account.getUbai().setLoginname(AES.getDecrypt(showLoginName));
			}
		}
		PageInfo<Object> pagehelper = this.initPagehelper(map,userAccountList);
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("df",df);
		mv.setViewName("admin/userAccount/userAccount");
		return mv;
	}
	/**
	 * 冻结资金请求
	* @Title: UsrFreezeBgManager 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws Exception  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/usrFreezeBgManager", method = { RequestMethod.POST, RequestMethod.GET })
	public void UsrFreezeBgManager (HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* 从session中拿到登录用户UsrFreezeBg */
		String baseid = request.getParameter("baseid");
		String djAmount = request.getParameter("djAmount");
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		System.out.println(baseid);
		System.out.println(djAmount);
		HuifuParams huifuParams = new HuifuParams();
	    String Version = huifuParams.getVersion();
	    String CmdId = "UsrFreezeBg";
	    String MerCustId = huifuParams.getMerCustId();
	    UserFsAccountInfo  userFsAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(new BigDecimal(baseid));
		String UsrCustId = userFsAccount.getUsrcustid();//设置用户客户号
		String SubAcctType = "MERDT"; //子账户类型
		String SubAcctId="MDT000001";//子账户号
		String OrdId = StringUtil.getNo();//订单号
		System.out.println(Long.valueOf(baseid));
		System.out.println(Long.valueOf(baseid).longValue());
		String OrdDate = sdf.format(new Date());
		System.out.println(OrdDate+"订单日期****");
		Double transAmt = Double.valueOf(djAmount);
		String TransAmt = df1.format(transAmt);
		System.out.println(TransAmt+"冻结金额");
		//商户后台应答地址
		String URL = "http://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath();
		String BgRetUrl = URL+"/HuifuUsrFreeze/UsrFreezeBgCallback.action";
		/*String RetUrl = URL+"/HuifuUsrFreeze/reCallback.action";*/
		String MerPriv = baseid;//一般是用来存取用户baseid的
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(Version)).append(StringUtils.trimToEmpty(CmdId))
		.append(StringUtils.trimToEmpty(MerCustId)).append(StringUtils.trimToEmpty(UsrCustId))
		.append(StringUtils.trimToEmpty(SubAcctType)).append(StringUtils.trimToEmpty(SubAcctId))
		.append(StringUtils.trimToEmpty(OrdId)).append(StringUtils.trimToEmpty(OrdDate))
		.append(StringUtils.trimToEmpty(TransAmt))/*.append(StringUtils.trimToEmpty(RetUrl))*/
		.append(StringUtils.trimToEmpty(BgRetUrl)).append(StringUtils.trimToEmpty(MerPriv));
		String plainStr = sb.toString();
		System.out.println(plainStr);
		/* 加签名 */  
		String ChkValue = SignUtils.encryptByRSA(plainStr);		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Version", Version);
		map.put("CmdId", CmdId);
		map.put("MerCustId", MerCustId);
		map.put("UsrCustId", UsrCustId);
		map.put("SubAcctType", SubAcctType);
		map.put("SubAcctId", SubAcctId);
		map.put("OrdId", OrdId);
		map.put("OrdDate", OrdDate);
		map.put("TransAmt", TransAmt);
		/*map.put("RetUrl", RetUrl);*/
		map.put("BgRetUrl", BgRetUrl);
		map.put("MerPriv", MerPriv);
		map.put("ChkValue", ChkValue);
		request.setAttribute("map", map);
		//发起你冻结的时候就把相关数据存进冻结解冻表的数据库
		//获取登录的管理员信息
		AdminUser adminuser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		System.out.println(adminuser+"用户管理员名字");
		//获取托管账户信息
		UserFsAccountInfo userFSAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(new BigDecimal(baseid));
		//获取用户账户信息
		UserAccount userAccount =  userAccountService.getUserAccountByBaseId(new BigDecimal(baseid));
		//获取用户信息基本表
		UserBaseAccountInfo  userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(baseid));
		AccountFreezeThaw aft = new AccountFreezeThaw();
		
		aft.setBaseid(new BigDecimal(baseid));
		aft.setAftorderno(OrdId);//冻结解冻流水号
		aft.setUsername(userFSAccountInfo.getUsrname());//姓名:用户的真是姓名
		aft.setLoginname(userBaseAccountInfo.getLoginname());//用户名
		aft.setOperator(adminuser.getUsername()); //处理人:管理员名字
		aft.setBalance(userAccount.getBalance());
		aft.setAvlbalance(userAccount.getAvlbalance());
		aft.setFreezebalance(userAccount.getFreezebalance());
		aft.setAmount(transAmt);//冻结金额
		aft.setStatus((short)2);//冻结状态,发起请求的时候都是未冻结,等到汇付那边返回成功之后再改为已经冻结 1,已冻结  2,未冻结
		//aft.setIsdeal((short)1);//1,已冻结  2,已解冻
		aft.setDescription("资金冻结开始");//说明
		aft.setRecordtime(new Date());//时间
		aft.setRemark("【冻结备注信息:"+request.getParameter("remark")+"】");
		accountFreezeThawService.insertSelective(aft);
		
		HttpClientUtil http = new HttpClientUtil();
		 String result2 = http.doPost(map);
		 System.out.println(result2);
		 JSONObject jsonObj = JSON.parseObject(result2);
		 System.out.println(jsonObj);
		 String data1 = jsonObj.getString("RespCode");
		 Map<String,String> map2 = new HashMap<String,String>();
		 if(data1.equals("000")){
			 map2.put("result", "success");
		 }else{
			 map2.put("result", "fail");
		 }
		 String str = JSON.toJSONString(map2);
		 StringUtil.sendJsonData(response, str);
		 
		 
		
		//request.getRequestDispatcher("/WEB-INF/pages/UsrFreezeBg/userFreezeBg.jsp").forward(request, response);
	}
	/**
	* @Title: UsrFreezeBgList 
	* @Description: TODO(冻结资金列表页面) 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/usrFreezeBgList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView UsrFreezeBgList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String baseid = request.getParameter("baseid");
		System.out.println(baseid+"是多少");
		ModelAndView mav = new ModelAndView();
			UserAccount userAccount = userAccountService.getUserAccountByBaseId(new BigDecimal(baseid));
			UserBaseAccountInfo ubai = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(baseid));
			if(ubai!=null){
				mav.addObject("ubai", ubai);
			}
			if(userAccount!=null){
				mav.addObject("userAccount", userAccount);
				mav.setViewName("admin/userAccount/UsrFreezeBg");
			}
			mav.addObject("df1", df1);
			return mav;
	}
	/**
	 * 冻结验证 
	* @Title: usrFreezeDjyz 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/usrFreezeDjyz", method = { RequestMethod.POST, RequestMethod.GET })
	public void usrFreezeDjyz(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String baseid = request.getParameter("baseid");
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(new BigDecimal(baseid));
		String data = "";
		if(userAccount!=null){
			data=JSON.toJSONString(userAccount);
			sendJsonData(response, data);
		}
		
	}
	
}
