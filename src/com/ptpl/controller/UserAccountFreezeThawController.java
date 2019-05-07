package com.ptpl.controller;

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
import com.ptpl.service.AccountFreezeThawServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.web.util.HttpClientUtil;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;
@Controller
@RequestMapping("/user/UsrUnFreeze")
public class UserAccountFreezeThawController extends BaseController{
	@Autowired
	AccountFreezeThawServiceI accountFreezeThawService;
	/**用户账户表*/
	@Autowired
	UserAccountServiceI userAccountService;
	/**
	 *详情页面
	 * @Title: jdUsrFreezeBgList 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @param @throws Exception  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author jiangxueyou
	 * @throws
	 */
	@RequestMapping(value = "/xiangqing", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView xiangqing(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String aftorderno = request.getParameter("aftorderno");
		ModelAndView modelAndView  = new ModelAndView();
		AccountFreezeThaw aft = accountFreezeThawService.getByOrdId(aftorderno);
		modelAndView.setViewName("/admin/usrFree/xiangqing");
		modelAndView.addObject("aft", aft);
		modelAndView.addObject("df", df);
		modelAndView.addObject("sf", sf);
		return modelAndView;
	} 
	/**
	 * 解冻冻结记录
	* @Title: UsrUnFreezeRecord 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param accountfreezethaw
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/UsrUnFreezeRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView UsrUnFreezeRecord(HttpServletRequest request, HttpServletResponse response,AccountFreezeThaw accountfreezethaw) throws Exception {
		
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(ubai.getId());
		//UserRecharge ur = new UserRecharge();
		// 当前页面，每页条数
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startdate = (String) request.getParameter("startdate");
		String enddate = (String) request.getParameter("enddate");
		accountfreezethaw.setBaseid(ubai.getId());
		if (startdate != null && startdate != "") {
			accountfreezethaw.setStart(StringUtil.stringforDateTwo(startdate));
		}
		if (enddate != null && enddate != "") {
			accountfreezethaw.setEnd(StringUtil.stringforDateTwo(enddate));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		 
		List<AccountFreezeThaw>  accountFreezeThawList = accountFreezeThawService.getLableSelect(accountfreezethaw);
		PageInfo<Object> pagehelper = initPagehelper(map, accountFreezeThawList);
		
		// 可用余额
		String avlBalance = df.format(userAccount.getAvlbalance());
		String avlBalanceA = avlBalance.substring(0, avlBalance.length() - 2);
		String avlBalanceB = avlBalance.substring(avlBalance.length() - 2);
		// 冻结余额
		String freezeBalance = df.format(userAccount.getFreezebalance());
		String freezeBalanceA = freezeBalance.substring(0, freezeBalance.length() - 2);
		String freezeBalanceB = freezeBalance.substring(freezeBalance.length() - 2);
		
		ModelAndView modelAndView  = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("avlBalanceA", avlBalanceA);
		modelAndView.addObject("avlBalanceB", avlBalanceB);
		modelAndView.addObject("freezeBalanceA", freezeBalanceA);
		modelAndView.addObject("freezeBalanceB", freezeBalanceB);
		modelAndView.addObject("startdate", startdate);
		modelAndView.addObject("enddate", enddate);
		modelAndView.addObject("sign", accountfreezethaw.getSign());
		modelAndView.addObject("isdeal", accountfreezethaw.getIsdeal());
		modelAndView.addObject("df", df);
		modelAndView.addObject("sf", sf);
		modelAndView.setViewName("user/manager/usrFree/UsrUnFreezeList");
		return modelAndView;
	} 
	/**
	 * 冻结解冻列表页面
	* @Title: jdUsrFreezeBgList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/jdqueryAllList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView jdUsrFreezeBgList(HttpServletRequest request, HttpServletResponse response,AccountFreezeThaw accountfreezethaw) throws Exception {
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startrecordtime = request.getParameter("startrecordtime");
		String endrecordtime = request.getParameter("endrecordtime");
		if(startrecordtime!=null && startrecordtime!=""){
			accountfreezethaw.setStartrecordtimeStr(startrecordtime);
		}
		if(endrecordtime!=null && endrecordtime!=""){
			accountfreezethaw.setEndrecordtimeStr(endrecordtime);
		}
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		
		List<AccountFreezeThaw>  accountFreezeThawList = accountFreezeThawService.queryAll(accountfreezethaw);
		PageInfo<Object> pagehelper = initPagehelper(map, accountFreezeThawList);
		ModelAndView modelAndView  = new ModelAndView();
		modelAndView.setViewName("/admin/usrFree/UsrUnFreezeList");
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("accountfreezethaw", accountfreezethaw);
		modelAndView.addObject("df1", df1);
		modelAndView.addObject("sf", sf);
		return modelAndView;
	} 
	/**
	 * 解冻模态框显示当前解冻的详细信息
	* @Title: jdusrFreezeList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/jdusrFreezeList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView jdusrFreezeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//根据txrid查询当前要解冻的数据,并且返回给模态框显示
		String trxid = request.getParameter("trxid");
		System.out.println(trxid);
		AccountFreezeThaw  accountFreezeThaw = accountFreezeThawService.getByTrxid(trxid);
		ModelAndView modelAndView  = new ModelAndView();
		modelAndView.setViewName("/admin/usrFree/UsrFreeze");
		modelAndView.addObject("accountFreezeThaw", accountFreezeThaw);
		modelAndView.addObject("df", df);
		modelAndView.addObject("sf", sf);
		return modelAndView;
	} 
	/**
	 * 解冻资金请求
	* @Title: jdusrFreezeBgManager 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @throws Exception  参数说明 
	* @return void    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/jdusrFreezeBgManager", method = { RequestMethod.POST, RequestMethod.GET })
	public void jdusrFreezeBgManager (HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* 从session中拿到登录用户UsrFreezeBg */
		String baseid = request.getParameter("baseid");
		String trxidlb = request.getParameter("trxidlb");
		String remarklb =  request.getParameter("remark");
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd");
		HuifuParams huifuParams = new HuifuParams();
	    String Version = huifuParams.getVersion();
	    String CmdId = "UsrUnFreeze";
	    String MerCustId = huifuParams.getMerCustId();
		String OrdId = StringUtil.getNo();//订单号
		String OrdDate = sdf.format(new Date());
		String TrxId = trxidlb;//这个是从上一个冻结的时候的说明里面拿到的值
		System.out.println(OrdDate+"订单日期****");
		//商户后台应答地址
		String URL = "http://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath();
		String BgRetUrl = URL+"/HuifuUsrFreeze/UsrUnFreezeCallback.action";
		String RetUrl = URL+"/HuifuUsrFreeze/reCallbackjd.action";
		String MerPriv = baseid;//一般是用来存取用户baseid的
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(Version)).append(StringUtils.trimToEmpty(CmdId))
		.append(StringUtils.trimToEmpty(MerCustId))
		.append(StringUtils.trimToEmpty(OrdId)).append(StringUtils.trimToEmpty(OrdDate))
		.append(StringUtils.trimToEmpty(TrxId))/*.append(StringUtils.trimToEmpty(RetUrl))*/
		.append(StringUtils.trimToEmpty(BgRetUrl)).append(StringUtils.trimToEmpty(MerPriv));
		String plainStr = sb.toString();
		System.out.println(plainStr);
		/* 加签名 */  
		String ChkValue = SignUtils.encryptByRSA(plainStr);		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Version", Version); 
		map.put("CmdId", CmdId);
		map.put("MerCustId", MerCustId);
		map.put("OrdId", OrdId);
		map.put("OrdDate", OrdDate);
		map.put("TrxId", TrxId);
		/*map.put("RetUrl", RetUrl);*/
		map.put("BgRetUrl", BgRetUrl);
		map.put("MerPriv", MerPriv);
		map.put("ChkValue", ChkValue);
		request.setAttribute("map", map);
		//以下代码是保存解冻人的
		AdminUser adminuser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		System.out.println(adminuser+"用户管理员名字");
		AccountFreezeThaw accountFreezeThaw = accountFreezeThawService.getByTrxid(TrxId);
		System.out.println(accountFreezeThaw.getRemark()+"备注信息");//这里是冻结备注信息
		String remarkdj = accountFreezeThaw.getRemark();
		String remarkjd = "【解冻备注信息:"+ remarklb+"】";
		if(remarkdj==null){
			remarkdj="【冻结备注信息:无】";
		}
		String beizhu = remarkdj+remarkjd;
		accountFreezeThaw.setRemark(beizhu);
		accountFreezeThaw.setThawman(adminuser.getUsername());
		accountFreezeThawService.update(accountFreezeThaw);
		
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

}
