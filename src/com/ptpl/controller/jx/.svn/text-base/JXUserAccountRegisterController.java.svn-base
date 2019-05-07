package com.ptpl.controller.jx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ptpl.controller.BaseController;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: JXRegisterUserAccountController 
* @Package com.ptpl.controller.jx 
* @Description: TODO(即信银行存管系统   用户开户Controller) 
* @author cjm
* @date 2017年3月7日 上午9:30:44 
* @version V1.0
 */
@Controller
@RequestMapping(value = "/jx/userRegister")
public class JXUserAccountRegisterController extends BaseController{

	/**
	 * 
	* @Title: insertParams 
	* @Description: TODO(参数设置 ) 
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	public void insertParams(HttpServletRequest request,HttpServletResponse response){
		String version	= "" ;  //版本号 目前为10 必填
		String txCode	= "" ;  //交易代码 accountOpen 必填
		String instCode	= "" ;  //机构代码   必填
		String bankCode	= "" ;  //银行代码 必填
		String txDate	= "" ;  //交易日期  YYYYMMDD 必填
		String txTime	= "" ;  //交易时间 hhmmss 必填
		String seqNo	= "" ;  //交易流水号 定长6位 必填
		String channel	= "" ;  //交易渠道 000001手机 APP 000002 网页 000003 微信 000004 柜面
		String idType	= "" ;  //证件类型 01-身份证（18位）必填
		String idNo		= "" ;  //证件号码 必填
		String name		= "" ;  //姓名 必填
		String mobile	= "" ;  //手机号 必填
		String cardNo	= "" ;  //银行卡号 必填
		String email	= "" ;  //邮箱 选填
		String acctUse	= "" ;  //账户用途 必填 
		/*每一位字符表示一种用途，‘1’表示启用；全‘0’表示普通账户
		1-红包账户（只能有一个）
		2-手续费账户（只能有一个）
		3-担保账户
		4-保留（填0）
		5-保留（填0）
		如00100是担保人账户*/
		String retUrl	= "" ;  //前台跳转链接 选填
		String notifyUrl= "" ;  //后台通知连接 必填
		String userIP	= "" ;  //客户IP 选填
		String acqRes 	= "" ;//请求方保留 选填
	}
	
	
	
	/**
	 * 
	* @Title: UserRegisterCallBackUrl 
	* @Description: TODO(用户开户 页面回调地址) 
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value = "/CallBackUrl",method = {RequestMethod.POST,RequestMethod.GET})
	public void UserRegisterCallBackUrl(HttpServletRequest request,HttpServletResponse response){
		String version  = request.getParameter("version");//版本号
		String txCode  = request.getParameter("txCode");//交易代码
		String instCode  = request.getParameter("instCode");//机构代码
		String bankCode  = request.getParameter("bankCode");//银行代码
		String txDate  = request.getParameter("txDate");//交易日期
		String txTime  = request.getParameter("txTime");//交易时间
		String seqNo  = request.getParameter("seqNo");//交易流水号
		String channel  = request.getParameter("channel");//交易渠道
		/*000001手机APP
		000002网页
		000003微信
		000004柜面*/
		String retCode  = request.getParameter("retCode");//响应代码
		String retMsg  = request.getParameter("retMsg");//响应描述
		String accountId  = request.getParameter("accountId");//电子账号 存管平台分配的账号
		String acqRes  = request.getParameter("acqRes");//
 		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version));
		buffer.append(StringUtils.trimToEmpty(txCode));
		buffer.append(StringUtils.trimToEmpty(instCode));
		buffer.append(StringUtils.trimToEmpty(bankCode));
		buffer.append(StringUtils.trimToEmpty(txDate));
		buffer.append(StringUtils.trimToEmpty(txTime));
		buffer.append(StringUtils.trimToEmpty(seqNo));
		buffer.append(StringUtils.trimToEmpty(channel));
		buffer.append(StringUtils.trimToEmpty(retCode));
		buffer.append(StringUtils.trimToEmpty(retMsg));
		buffer.append(StringUtils.trimToEmpty(accountId));
		buffer.append(StringUtils.trimToEmpty(acqRes));
 		String str = buffer.toString();
		
	}
	
	/**
	 * 
	* @Title: userRegisterCallBack 
	* @Description: TODO(用户开户 后台应答地址) 
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value = "/CallBack",method = {RequestMethod.POST,RequestMethod.GET})
	public void userRegisterCallBack(HttpServletRequest request,HttpServletResponse response){
		String version  = request.getParameter("version");//版本号
		String txCode  = request.getParameter("txCode");//交易代码
		String instCode  = request.getParameter("instCode");//机构代码
		String bankCode  = request.getParameter("bankCode");//银行代码
		String txDate  = request.getParameter("txDate");//交易日期
		String txTime  = request.getParameter("txTime");//交易时间
		String seqNo  = request.getParameter("seqNo");//交易流水号
		String channel  = request.getParameter("channel");//交易渠道
		/*000001手机APP
		000002网页
		000003微信
		000004柜面*/
		String retCode  = request.getParameter("retCode");//响应代码
		String retMsg  = request.getParameter("retMsg");//响应描述
		String accountId  = request.getParameter("accountId");//电子账号 存管平台分配的账号
		String acqRes  = request.getParameter("acqRes");//
 		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(version));
		buffer.append(StringUtils.trimToEmpty(txCode));
		buffer.append(StringUtils.trimToEmpty(instCode));
		buffer.append(StringUtils.trimToEmpty(bankCode));
		buffer.append(StringUtils.trimToEmpty(txDate));
		buffer.append(StringUtils.trimToEmpty(txTime));
		buffer.append(StringUtils.trimToEmpty(seqNo));
		buffer.append(StringUtils.trimToEmpty(channel));
		buffer.append(StringUtils.trimToEmpty(retCode));
		buffer.append(StringUtils.trimToEmpty(retMsg));
		buffer.append(StringUtils.trimToEmpty(accountId));
		buffer.append(StringUtils.trimToEmpty(acqRes));
 		String str = buffer.toString();
	}
}
