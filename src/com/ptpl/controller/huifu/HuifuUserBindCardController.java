package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.ptpl.constant.Constant;
import com.ptpl.constant.HuifuParams_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;



@Controller
@RequestMapping("/huifu/userBindCard")
public class HuifuUserBindCardController extends QueryCardInfoController {
	
	/**
	 * 用户托管账户信息  ServiceI
	 */
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoService;
	
	/**
	 * 用户银行卡信息
	 */
	@Autowired
	UserBankCardServiceI userBankCardService;
	
	/**
	 * 用户基本信息  ServiceI
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;

	/**
	 * 跳转到汇付天下-用户绑卡页面
	 * @param card
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tobindBankCard")
	public String tobindBankCard(UserBankCard card) throws Exception {
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);
		if (userBaseAccountInfo == null) { //用户未登录
			// 返回登录页面
			return "redirect:/user/tologin.action";
		} else {
			BigDecimal id = userBaseAccountInfo.getId(); // 获取对应用户基本信息表id
			if (userFsAccountInfoService.findUserFsAccountInfoByBaseId(id) == null) { // 用户未开通托管账户
				return "redirect:/huifu/UserRegister.action";
			} else {
				// 进入汇付用户绑定取现银行卡页面
				hfUserBindCard(userBaseAccountInfo, request, response);
			}
		}
		return null;
	}

	/**
	 * 汇付天下-用户绑卡接口请求参数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void hfUserBindCard(UserBaseAccountInfo userBaseAccountInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HuifuParams huifuparam = new HuifuParams();
		huifuparam.setCmdId("UserBindCard"); // 消息类型
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		if(userFsAccountInfo != null){
			huifuparam.setUsrCustId(userFsAccountInfo.getUsrcustid()); // 获取对应用户托管账户信息  用户客户号
		}
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuparam.setBgRetUrl(basePath+"/huifu/userBindCard/userBindCardCallBack.action"); // 商户后台应答地址
		// 若为中文，请用Base64转码
		huifuparam.setMerPriv(userBaseAccountInfo.getId().toString()); // 将用户基本信息id放入商户私有域
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion())).append(StringUtils.trimToEmpty(huifuparam.getCmdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId())).append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getBgRetUrl())).append(StringUtils.trimToEmpty(huifuparam.getMerPriv()));
		String plainStr = buffer.toString();
		System.out.println("组装绑卡加签字符串原文:========="+plainStr);
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);
			request.setAttribute("Version", huifuparam.getVersion());
			request.setAttribute("CmdId", huifuparam.getCmdId());
			request.setAttribute("MerCustId", huifuparam.getMerCustId());
			request.setAttribute("UsrCustId", huifuparam.getUsrCustId());
			request.setAttribute("BgRetUrl", huifuparam.getBgRetUrl());
			request.setAttribute("MerPriv", huifuparam.getMerPriv());
			request.setAttribute("ChkValue", ChkValue);
			request.getRequestDispatcher("/WEB-INF/pages/UserBindCard/UserBindCard.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 汇付天下-用户绑卡后台应答地址
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/userBindCardCallBack")
	public void userBindCardCallBack(HttpServletRequest request, HttpServletResponse response, HuifuParams huifuParam) throws Exception {
		System.out.println("进来了！！！！！！！！！！！！！！！！！！！！！！！！！");
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
	
		if(userBaseAccountInfo == null){
			userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(new BigDecimal(huifuParam.getMerPriv())); // 根据id获取用户基本信息
		}
        request.setCharacterEncoding("UTF-8");
		BigDecimal baseid = (BigDecimal) (userBaseAccountInfo.getId() == null ? huifuParam.getMerPriv() : userBaseAccountInfo.getId());
        String chkValue = huifuParam.getChkValue();
        System.out.println("绑卡应答获取的签名：=============="+chkValue);
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(huifuParam.getCmdId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getRespCode()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOpenAcctId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getOpenBankId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getUsrCustId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getTrxId()))
        		.append(StringUtils.trimToEmpty(huifuParam.getBgRetUrl()))
        		.append(StringUtils.trimToEmpty(huifuParam.getMerPriv()));
        String plainStr = buffer.toString();
        System.out.println("绑卡应答获取的返回参数拼接：=============="+plainStr);
        boolean flag = false;
        try {
            flag = SignUtils.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
        	if(huifuParam.getRespCode().equals("000")){ // 返回码000成功
        		// 保存返回参数到数据库
        		bindCard(baseid, huifuParam, userBaseAccountInfo);
        	}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
        try {
            if (StringUtils.isNotBlank(huifuParam.getTrxId())) {
                PrintWriter out = response.getWriter();
                out.print("RECV_ORD_ID_".concat(huifuParam.getTrxId()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
 	
	/**
	 * 绑卡返回码000成功后保存返回参数到数据库
	 * @param baseid
	 * @param huifuParam
	 * @param userBaseAccountInfo
	 * @throws Exception
	 */
	public void bindCard(BigDecimal baseid, HuifuParams huifuParam, UserBaseAccountInfo userBaseAccountInfo) throws Exception{
    	UserBankCard userBankCard = new UserBankCard();
    	userBankCard.setBaseid(baseid); //用户ID
    	if(userBaseAccountInfo.getAccounttype()==1){ // 用户类型为个人
        	userBankCard.setCardtype(Constant.CARDTYPE_JIEJIKA); // 卡类型（1.信用卡，2.借记卡，3.企业账户）
    	}else{ // 用户类型为企业
        	userBankCard.setCardtype(Constant.CARDTYPE_QIYE); // 卡类型（1.信用卡，2.借记卡，3.企业账户）
    	}
    	userBankCard.setBankname(Constant.BANK_MAP.get(huifuParam.getOpenBankId())); // 银行
    	userBankCard.setUsername(userBaseAccountInfo.getRealname()); // 姓名
    	userBankCard.setCardno(huifuParam.getOpenAcctId()); // 卡号
    	userBankCard.setBindmode(Constant.BINDMODE_JIEKOU); // 绑定方式  这里是接口绑定（ 1.接口，2.人工）
    	userBankCard.setIsfastbindcard(Constant.ISFASTBINDCARD_PUTONG); // 是否快捷绑卡（默认不开通）充值时可开通快捷卡（1.普通卡，2.快捷卡）
    	userBankCard.setBindstatus(Constant.BINDSTATUS_BIND); // 绑定状态（1.已绑定，2.已解绑）
    	userBankCard.setPaycompany("汇付天下"); // 绑定通道
    	userBankCard.setRemark(huifuParam.getTrxId()); //平台交易唯一标识存进备注
    	huifuParam.setCardId(""); //将huifuParam对象中的卡号清空，否则查询出的UsrCardInfolist为空数组
    	System.out.println(huifuParam.getCardId()+"=======================cardid");
    	// 调用查询银行卡接口
    	JSONArray json = doQueryCardInfo(huifuParam);
		Iterator<Object> it = json.iterator(); 
		while(it.hasNext()){ // 迭代UsrCardInfolist数组
			JSONObject json2 = (JSONObject) it.next();
			String cardno = json2.getString("CardId"); 
			System.out.println("卡号==================="+cardno);
			if(cardno.trim().equals(userBankCard.getCardno())){ //将卡号传入查询获取相应的卡信息
				Date bindtime = new SimpleDateFormat("yyyyMMddHHmmss").parse(json2.getString(HuifuParams_Constant.UpdDateTime)); //查询获得时间转换成date类型
				userBankCard.setBindtime(bindtime);
				// 查询是否是快捷卡
    			if(json2.getString(HuifuParams_Constant.ExpressFlag).trim().equals("Y")){
    				userBankCard.setIsfastbindcard(Constant.ISFASTBINDCARD_KUAIJIE);
    			}else if(json2.getString(HuifuParams_Constant.ExpressFlag).trim().equals("N") || json2.getString(HuifuParams_Constant.ExpressFlag).trim().equals("")){
    				userBankCard.setIsfastbindcard(Constant.ISFASTBINDCARD_PUTONG);
    			}
    			// 查询是否是默认卡
    			if(json2.getString(HuifuParams_Constant.IsDefault).trim().equals("Y")){ // 绑定为默认卡
    				
    	        	userBankCard.setIsdefaultcard(Constant.ISDEFAULTCARD_NO);
    	        	userBankCardService.updateDefaultCard(userBankCard); // 先修改其他卡的非默认卡状态
    	        	
    				userBankCard.setIsdefaultcard(Constant.ISDEFAULTCARD_YES); // 再将本张卡设置为默认卡
    			}else if(json2.getString(HuifuParams_Constant.IsDefault).trim().equals("N")){  // 绑定为非默认卡
    				userBankCard.setIsdefaultcard(Constant.ISDEFAULTCARD_NO);
    			}
			}
		}
    	if(userBankCardService.findDetailByCardNo(huifuParam.getOpenAcctId()) == null){ // 没有相同的卡号
        	userBankCardService.insertSelective(userBankCard); // 保存用户银行卡信息
            System.out.println("卡号：====================="+userBankCard.getCardno());
    	}
        System.out.println("银行名称：====================="+userBankCard.getBankname());
	}
	
	/**
	 * 汇付天下-解绑快捷卡后台返回地址
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/usrUnBindExpressCard")
	public void UsrUnBindExpressCard(HttpServletRequest request, HttpServletResponse response, UserBankCard usCard) throws Exception{
		System.out.println("进入解绑快捷卡后台应答地址！！！！！！！！！！！");
        request.setCharacterEncoding("UTF-8");
        String cmdId = request.getParameter("CmdId");
        String respCode = request.getParameter("RespCode");
        String respDesc = request.getParameter("RespDesc");
        System.out.println("应答描述：======"+respDesc);
        String merCustId = request.getParameter("MerCustId");
        String custId = request.getParameter("CustId");
        String trxId = request.getParameter("TrxId");
        String bankId = request.getParameter("BankId");
        String cardId = request.getParameter("CardId");
        String expressFlag = request.getParameter("ExpressFlag");
        String bgRetUrl = request.getParameter("BgRetUrl");
        try {
            bgRetUrl = URLDecoder.decode(bgRetUrl, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String chkValue = request.getParameter("ChkValue");
        System.out.println("解绑快捷卡后台返回签名：================="+chkValue);

        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(cmdId))
        		.append(StringUtils.trimToEmpty(respCode))
        		.append(StringUtils.trimToEmpty(merCustId))
        		.append(StringUtils.trimToEmpty(custId))
        		.append(StringUtils.trimToEmpty(trxId))
        		.append(StringUtils.trimToEmpty(bankId))
        		.append(StringUtils.trimToEmpty(cardId))
        		.append(StringUtils.trimToEmpty(expressFlag))
        		.append(StringUtils.trimToEmpty(bgRetUrl));
        String plainStr = buffer.toString();
        System.out.println("解绑快捷卡后台返回参数拼接：================="+plainStr);
        boolean flag = false;
        try {
            flag = SignUtils.verifyByRSA(plainStr, chkValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ //验证签名成功
        	if(respCode.trim().equals("000")){ // 解绑成功
        		usCard.setCardno(cardId); // 银行的账户号
				userBankCardService.findDetailByCardNo(usCard.getCardno()); // 根据卡号查询用户银行卡详情信息
				usCard.setIsfastbindcard(Constant.ISFASTBINDCARD_PUTONG); // 解绑快捷卡
				userBankCardService.updateUserBankInfo(usCard); // 根据id修改快捷卡为普通卡
				userBankCardService.updateBindStatus(usCard); // 修改其余卡的绑定状态
        	}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
        try {
            if (StringUtils.isNotBlank(trxId)) {
                PrintWriter out = response.getWriter();
                out.print("RECV_ORD_ID_".concat(trxId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 汇付天下-删除银行卡请求参数
	 * @param huifuparam
	 * @return
	 */
	public Map<String,String> delCard(HuifuParams huifuparam){
		huifuparam.setCmdId("DelCard"); // 消息类型
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", huifuparam.getVersion());
		params.put("CmdId", huifuparam.getCmdId());
		params.put("MerCustId", huifuparam.getMerCustId());
		params.put("UsrCustId", huifuparam.getUsrCustId());
		params.put("CardId", huifuparam.getCardId());
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion())).append(StringUtils.trimToEmpty(huifuparam.getCmdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId())).append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getCardId()));
		String plainStr = buffer.toString();
		System.out.println(plainStr);
		String ChkValue ="";
	    try {
			  ChkValue = SignUtils.encryptByRSA(plainStr);
		} catch (Exception e) {
 			e.printStackTrace();
		}
		params.put("ChkValue", ChkValue);
		return params;
	}
	
	/**
	 * 删除绑定的银行卡
	 * @param card
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping("/delBankCard")
	public void delBankCard(UserBankCard card) throws ClientProtocolException, IOException {
		String id = request.getParameter("id");
		UserBankCard userbankcard = userBankCardService.findDetailById(Long.parseLong(id));
		System.out.println("要删除的卡号：=========="+userbankcard.getCardno());
		HuifuParams huifuparam = new HuifuParams();
		if(userbankcard.getCardno()!=null){
			huifuparam.setCardId(userbankcard.getCardno());
		}
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		if(userFsAccountInfo != null){
			huifuparam.setUsrCustId(userFsAccountInfo.getUsrcustid()); // 获取对应用户托管账户信息  用户客户号
		}
		Map<String,String> params = delCard(huifuparam);
		QueryCardInfoController querycardinfocontroller = new QueryCardInfoController();
		String result = querycardinfocontroller.doBankCardPost(params);
		StringUtil.sendJsonData(response, result);
		JSONObject json = JSONObject.parseObject(result); // 将返回的json字符串转换为JSONObject
		String respcode = json.getString(HuifuParams_Constant.RespCode); // 应答返回码
		if(respcode.trim().equals("000")){ //返回成功状态
			try{
				userBankCardService.deleteUserBankInfo(Long.parseLong(id));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
