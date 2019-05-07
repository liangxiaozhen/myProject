package com.ptpl.controller.huifu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huifu.util.SignUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: HuifuCorpRegisterController 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(汇付天下 企业开户Controller) 
* @author chenjiaming
* @date 2016年8月12日 下午5:51:37 
* @version V1.0
 */
@Controller
@RequestMapping("/huifu/CorpRegister")
public class HuifuCorpRegisterController extends BaseController {
	/**
	 * 用户托管账户信息  ServiceI
	 */
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
	
	/**
	 * 
	* @Title: CorpRegister 
	* @Description: TODO(跳转汇付天下 企业开户 ) 
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws ServletException
	* @param @throws IOException  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping("/CorpRegister")
	public String CorpRegister(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		UserBaseAccountInfo userBaseAccountInfo = (com.ptpl.model.UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		if(userBaseAccountInfo != null){
			String busiCode = "330183000147554";//营业执照编号 210000004942310 330183000147558   330183000147551 330183000147552
 			getCorpRegisterParams(busiCode,userBaseAccountInfo, request, response);
		}else{//用户没有登录 重定向到用户登录页面
			return "redirect:/user/tologin.action";
		}
		return null;
	}
	/**
	 * 
	* @Title: CorpRegisterCallback 
	* @Description: TODO(企业开户 实现后台应答方式) 
	* @param @param request
	* @param @param response
	* @param @param huifuParams
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author chenjiaming
	* @throws
	 */
	@RequestMapping("/CorpRegisterCallback")
	public String CorpRegisterCallback(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams){
		System.out.println("========CmdId============"+huifuParams.getCmdId());
		System.out.println("========RespCode============"+huifuParams.getRespCode());
		System.out.println("========RespDesc============"+huifuParams.getRespDesc());
		System.out.println("========MerCustId============"+huifuParams.getMerCustId());
		System.out.println("========UsrId============"+huifuParams.getUsrId());
		System.out.println("========UsrName============"+huifuParams.getUsrName());
		System.out.println("========UsrCustId============"+huifuParams.getUsrCustId());
		System.out.println("========AuditStat============"+huifuParams.getAuditStat());
		System.out.println("========AuditDesc============"+huifuParams.getAuditDesc());
		System.out.println("========MerPriv============"+huifuParams.getMerPriv());
		System.out.println("=========TrxId==========="+huifuParams.getTrxId());
		System.out.println("=========OpenBankId==========="+huifuParams.getOpenBankId());
		System.out.println("=========CardId==========="+huifuParams.getCardId());
		System.out.println("=========BgRetUrl==========="+huifuParams.getBgRetUrl());
		System.out.println("==========RespExt=========="+huifuParams.getRespExt());
		System.out.println("=========ChkValue==========="+huifuParams.getChkValue());
  
		
		if(huifuParams.getChkValue() != null){
			System.out.println("sdsdsdsdsdsCorpRegisterCallback");
			/*CmdId + RespCode + MerCustId + UsrId + UsrName + UsrCustId + AuditStat + TrxId + OpenBankId + CardId + BgRetUrl + RespExt*/
			StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));//消息类型
			buffer.append(StringUtils.trimToEmpty(huifuParams.getRespCode()));//应答返回码
 			buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));//商户客户号
			buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrId()));//用户号
			buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrName()));//真实名称
			buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()));//用户客户号
			buffer.append(StringUtils.trimToEmpty(huifuParams.getAuditStat()));//审核状态审核过程中的状态 I--初始 T--提交 P--审核中 R--审核拒绝 F--开户失败 K--开户中 Y--开户成功
			buffer.append(StringUtils.trimToEmpty(huifuParams.getTrxId()));//本平台交易唯一标识
 			buffer.append(StringUtils.trimToEmpty(huifuParams.getOpenBankId()));//开户银行代号
			buffer.append(StringUtils.trimToEmpty(huifuParams.getCardId()));//开户银行账号
			buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));//商户后台应答地址
			buffer.append(StringUtils.trimToEmpty(huifuParams.getRespExt()));//返参扩展域
		    String str = buffer.toString();
			boolean flag = false;
			try {
				flag = SignUtils.verifyByRSA(str, huifuParams.getChkValue());
			} catch (Exception e) {
 				e.printStackTrace();
			}
			if(flag){//签名验证成功
////			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(null);
//				System.out.println("延签成功...."+huifuParams.getRespCode());
//				if(huifuParams.getRespCode().equalsIgnoreCase("000") && userFsAccountInfo != null){
//					//保存数据
//// 					int count = 0;
////					count = userFsAccountInfoServiceI.insertSelective(userFsAccountInfo);
//				}
			}
		}else{
			return "redirect:/user/tologin.action";
		}
		return null;
	}
	
	/**
	 * @throws IOException 
	 * @throws ServletException 
 	* @Title: getCorpRegisterParams 
	* @Description: TODO(设置企业开户 请求参数) 
	* @param @param userBaseAccountInfo
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public void getCorpRegisterParams(String busiCode,UserBaseAccountInfo userBaseAccountInfo,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HuifuParams huifuParams = new HuifuParams();
		huifuParams.setCmdId("CorpRegister");//消息类型 必须
		huifuParams.setBusiCode(busiCode);//营业执照编号 必须
		String basePath = StringUtil.getBasePath(request);
		huifuParams.setBgRetUrl(basePath+"/huifu/CorpRegister/CorpRegisterCallback.action");//商户后台应答地址 必须
		huifuParams.setUsrId("1000004");//商户客户号  可选商户下的平台用户号，在每个商户下唯一（必须是 6-25位的半角字符）1000001 1000002 1000003
		huifuParams.setGuarType("Y");//是否担保类型，Y：是 N：否第一次请求开户，如果该字段为空，默认为 N,如果需要修改企业开户信息，重复请求，若该字段为空，默认为上一次的类型
		/*Version +CmdId + MerCustId + UsrId + UsrName+InstuCode +BusiCode + TaxCode + MerPriv+GuarType + BgRetUrl+ ReqExt*/
 		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));//版本号 必须
		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));//消息类型 必须
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));//商户客户号  可选
		buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrId()));//用户号 可选
		buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrName()));//真实名称 可选
		buffer.append(StringUtils.trimToEmpty(huifuParams.getInstuCode()));//组织机构代码 可选
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBusiCode()));//营业执照编号 必须
		buffer.append(StringUtils.trimToEmpty(huifuParams.getTaxCode()));//税务登记号 可选
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));//商户私有域 可选
		buffer.append(StringUtils.trimToEmpty(huifuParams.getGuarType()));//可选 担保类型是否担保类型，Y：是 N：否第一次请求开户，如果该字段为空，默认为 N,如果需要修改企业开户信息，重复请求，若该字段为空，默认为上一次的类型
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));//商户后台应答地址 必须
		buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));//入参扩展域 可选
		buffer.append(StringUtils.trimToEmpty(huifuParams.getGuarCorpEarnestAmt()));//可选 企业用户备案金 ReqExt二级参数{"GuarCorpEarnestAmt":"100000.00"}
  		String str = buffer.toString();
  		String ChkValue ="";
 		try {
			  ChkValue = SignUtils.encryptByRSA(str);
		} catch (Exception e) {
 			e.printStackTrace();
		}
 		
 		request.setAttribute("Version", huifuParams.getVersion());	
		request.setAttribute("CmdId", huifuParams.getCmdId());	
		request.setAttribute("MerCustId", huifuParams.getMerCustId());	
		request.setAttribute("UsrId", huifuParams.getUsrId());	
		request.setAttribute("UsrName", huifuParams.getUsrName());	
		request.setAttribute("InstuCode", huifuParams.getInstuCode());	
		request.setAttribute("BusiCode", huifuParams.getBusiCode());	
		request.setAttribute("TaxCode", huifuParams.getTaxCode());	
		request.setAttribute("MerPriv", huifuParams.getMerPriv());	
		request.setAttribute("GuarType", huifuParams.getGuarType());	
		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());	
		request.setAttribute("ReqExt", huifuParams.getReqExt());	
		request.setAttribute("CharSet", huifuParams.getCharSet());	
		request.setAttribute("ChkValue",ChkValue);
    	request.getRequestDispatcher("/WEB-INF/pages/CorpRegister/CorpRegister.jsp").forward(request, response);
 	}
}
