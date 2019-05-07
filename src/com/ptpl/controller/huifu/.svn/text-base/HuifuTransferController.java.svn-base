package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;

import com.huifu.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.PlusPayoutRecord;
import com.ptpl.service.PlusPayoutRecordServiceI;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: HuifuTransferController 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(汇付天下   商户转账接口) 
* @author cjm
* @date 2016年11月23日 上午10:15:30 
* @version V1.0
 */
@Controller
@RequestMapping("/huifu/transfer")
public class HuifuTransferController extends BaseController{
 
	@Autowired
	private PlusPayoutRecordServiceI plusPayoutRecordServiceI;
	/**
	 * 
	* @Title: doTransferParams 
	* @Description: TODO(调用汇付 商户转账接口  ) 
	* @param @param request
	* @param @param transAmt 转账金额
	* @param @param inCustId 进账客户号
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	public static String doTransferParams(HttpServletRequest request,String transAmt ,String inCustId,String ordId){
			if(StringUtil.isEmpty(transAmt)){
				throw new IllegalArgumentException("'transAmt' 转账金额不能为null");
			}
			if(StringUtil.isEmpty(inCustId)){
				throw new IllegalArgumentException("'inCustId' 入账客户号不能为null");
			}
			if(StringUtil.isEmpty(ordId)){
				throw new IllegalArgumentException("'ordId' 转账流水号不能为null");
			}
			String result = "";
  			String bgRetUrl =  StringUtil.getBasePath(request)+"/huifu/transfer/callBackPlusPayoutTransfer.action";
  			HuifuParams huifuParams = new HuifuParams();
 			huifuParams.setVersion("10");
 			huifuParams.setCmdId("Transfer");
 			huifuParams.setOrdId(ordId);
 			huifuParams.setOutCustId("6000060004166478");
 			huifuParams.setOutAcctId("MDT000001");
 			huifuParams.setTransAmt(transAmt);
 			huifuParams.setInCustId(inCustId);
 			huifuParams.setBgRetUrl(bgRetUrl);
 			//MDT000001   6000060004166478   专属借记账户  SDT000001     专属用户 SDT000002 BASEDT
 			Map<String,String> params = new HashMap<String,String>();
			params.put("Version", huifuParams.getVersion());//版本号 Version 必须 固定为 10 
			params.put("CmdId", huifuParams.getCmdId());//消息类型 CmdId 必须  此处为 Transfer
			params.put("OrdId", huifuParams.getOrdId());//订单号 OrdId  必须 由商户的系统生成，必须保证唯一，请使用纯数字
			params.put("OutCustId", huifuParams.getOutCustId());//出账客户号 OutCustId 必须 出账客户号，由汇付生成，用户的唯一性标识
			params.put("OutAcctId", huifuParams.getOutAcctId());//出账子账户 OutAcctId 必须 用户在汇付的虚拟资金账户号
			params.put("TransAmt", huifuParams.getTransAmt());//交易金额 TransAmt 必须（金额格式必须是###.00）
			params.put("InCustId", huifuParams.getInCustId());//入账客户号 InCustId 必须 入账客户号，由汇付生成，用户的唯一性标识
			params.put("BgRetUrl", huifuParams.getBgRetUrl());//商户后台应答地址 BgRetUrl 必须
			params.put("CharSet", huifuParams.getCharSet());// 系统编码
 			//签名 ChkValue 必须
			//加签验签的时候，商户需告知汇付其系统编码，
			//汇付在验签的时候进行相应的编码转换验签
			//Version +CmdId + OrdId + OutCustId +
			//OutAcctId + TransAmt+ InCustId+ InAcctId+
			//RetUrl + BgRetUrl+ MerPriv
			String ChkValue = "";
			StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getTransAmt()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getInCustId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getInAcctId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
			String str =  buffer.toString();
			try {
				ChkValue = SignUtils.encryptByRSA(str);
			} catch (Exception e) {
 				e.printStackTrace();
			}
			params.put("ChkValue", ChkValue);//签名 ChkValue
			try {
				result = SignUtils.doPost(params);//后台提交 调用汇付商户转账接口
			} catch (ClientProtocolException e) {
 				e.printStackTrace();
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return result;
  	}
	 
	
	/**
	 * 
	* @Title: callBackPlusPayoutTransfer 
	* @Description: TODO(商户增益转账 回调地址) 
	* @param @param huifuParams
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping("/callBackPlusPayoutTransfer")
	public void doCallBackPlusPayoutTransfer(HuifuParams huifuParams,HttpServletRequest request ,HttpServletResponse response){
		System.out.println("==============getCmdId========================"+huifuParams.getCmdId());
 		System.out.println("==============getRespCode========================"+huifuParams.getRespCode());
		System.out.println("==============getRespDesc========================"+huifuParams.getRespDesc());
		System.out.println("==============getOrdId========================"+huifuParams.getOrdId());
		System.out.println("==============getOutCustId========================"+huifuParams.getOutCustId());
		System.out.println("==============getOutAcctId========================"+huifuParams.getOutAcctId());
		System.out.println("==============TransAmt========================"+huifuParams.getTransAmt());
		System.out.println("==============InCustId========================"+huifuParams.getInCustId());
		System.out.println("==============InAcctId========================"+huifuParams.getInAcctId());
		System.out.println("==============BgRetUrl========================"+huifuParams.getBgRetUrl());
		System.out.println("==============MerPriv========================"+huifuParams.getMerPriv());
		System.out.println("==============ChkValue========================"+huifuParams.getChkValue());
	 
		if(huifuParams.getChkValue() != null){
			/*CmdId + RespCode +OrdId + OutCustId +
			OutAcctId + TransAmt+ InCustId+ InAcctId+
			RetUrl + BgRetUrl+ MerPriv*/
			StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getRespCode()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getOutCustId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getOutAcctId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getTransAmt()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getInCustId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getInAcctId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
 			String str = buffer.toString();
 			boolean flag = false;
 			try {
				flag = SignUtils.verifyByRSA(str, huifuParams.getChkValue());
			} catch (Exception e) {
 				e.printStackTrace();
			}
  			if(flag){//解签成功
 				if(huifuParams.getRespCode().equals("000")){//调用成功
  					if(StringUtil.isNotEmpty(huifuParams.getOrdId())){//告诉汇付已经收到了
  						PlusPayoutRecord payoutRecord = plusPayoutRecordServiceI.findPlusPayoutRecordByPporderno(huifuParams.getOrdId());
  						if(payoutRecord != null){
  							payoutRecord.setIsgrant((short)1);//是否发放(0否，1是,2处理中)
  							plusPayoutRecordServiceI.updateById(payoutRecord);
  						}
 						try {
 							PrintWriter out = response.getWriter();
 							out.println("RECV_ORD_ID_".concat(huifuParams.getOrdId()));
 						} catch (IOException e) {
 							e.printStackTrace();
 						}
 					}
 				}
  			}
		}
//消息类型 CmdId 变长 String 必须 每一种消息类型代表一种交易，此处为 Transfer
//应答返回码 RespCode 必须 000--调用成功
//应答描述 RespDesc 变长 String 必须 返回码的对应中文描述
//订单号 OrdId 必须 由商户的系统生成，必须保证唯一，请使用纯数字
//出账客户号 OutCustId 必须 出账客户号，由汇付生成，用户的唯一性标识
//出账子账户 OutAcctId 必须 用户在汇付的虚拟资金账户号
//交易金额 TransAmt 变长 14 位的 必须  
//入账客户号 InCustId 必须 入账客户号，由汇付生成，用户的唯一性标识
//入账子账户 InAcctId
//商户后台应答地址BgRetUrl 必须
//商户私有域 MerPriv
//签名 ChkValue 必须
//加签验签的时候，商户需告知汇付其系统编码，
//汇付在验签的时候进行相应的编码转换验签
//CmdId + RespCode +OrdId + OutCustId +
//OutAcctId + TransAmt+ InCustId+ InAcctId+
//RetUrl + BgRetUrl+ MerPriv
		
 	}
	
}
