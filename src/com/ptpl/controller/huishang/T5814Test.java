package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.huishang.util.HSignUtil;
import com.ptpl.constant.Constant;
import com.ptpl.model.UserBankCard;
import com.ptpl.web.util.BankCardUtil;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class T5814Test {

	public static String doParams(String usrcustid) {
		LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
		// 请求报文头
		reqMap.put("TRXCODE", "5814"); // 交易代码TRXCODE——4位交易代码
		reqMap.put("BANKCODE", HSignUtil.BANKCODE); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——YYYYMMDD
		String TRXTIME = StringUtil.formatDate(date, "hhmmss"); // 交易时间	TRXTIME——hhmmss
		reqMap.put("TRXDATE", TRXDATE); // 交易日期TRXDATE——YYYYMMDD
		reqMap.put("TRXTIME", TRXTIME); // 交易时间	TRXTIME——hhmmss
		reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); // 合作单位编号COINSTCODE——800114
		reqMap.put("COINSTCHANNEL","000002"); // 合作单位渠道COINSTCHANNEL——000001-手机APP、000002-网页、000003-微信、000004-行内核心、000005-ESB
		reqMap.put("SEQNO",StringUtil.getNo()); // 交易流水号SEQNO——8 ~ 20 位数字流水号
		reqMap.put("SOURCE", HSignUtil.SOURCE); // ESB内部渠道SOURCE——A0
		reqMap.put("RETCODE", ""); // 应答码RETCODE——填空
		reqMap.put("RETMSG", ""); // 应答码描述RETMSG——填空
		reqMap.put("HEADRESERVED", ""); // 报文头保留域HEADRESERVED——可选（将baseid放入报文头保留域）
		// 请求报文
		reqMap.put("CARDNBR",usrcustid); // 电子账号CARDNBR
		reqMap.put("PINFLAG", "0"); // 使用密码标志PINFLAG——（0：不使用密码 、1：使用消费密码、2：使用查询密码）
		reqMap.put("PIN", ""); // 密码PIN
		reqMap.put("STATE", "1"); // 查询的记录状态STATE——（0.所有，1.当前有效的绑定卡）
		reqMap.put("REMARK","绑定卡关系查询"); // 备注REMARK
		String result = null;
		try {
			result = HSignUtil.HttpClientTask(reqMap, "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private static void callBack(String respoResult){
		String result = HSignUtil.getDecryptRSAByte(respoResult); // 接受数据后进行解密
		JSONObject jsonObject = JSONObject.fromObject(result);
		// 响应报文头
		String TRXCODE = jsonObject.getString("TRXCODE"); // 交易代码TRXCODE——与请求一致
		String BANKCODE = jsonObject.getString("BANKCODE"); // 银行代码BANKCODE——与请求一致
		String TRXDATE = jsonObject.getString("TRXDATE"); // 交易日期TRXDATE——与请求一致
		String TRXTIME = jsonObject.getString("TRXTIME"); // 交易时间TRXTIME——与请求一致
		String COINSTCODE = jsonObject.getString("COINSTCODE"); // 合作单位编号COINSTCODE——与请求一致
		String COINSTCHANNEL = jsonObject.getString("COINSTCHANNEL"); // 合作单位渠道COINSTCHANNEL——与请求一致
		String SEQNO = jsonObject.getString("SEQNO"); // 交易流水号SEQNO——与请求一致
		String SOURCE = jsonObject.getString("SOURCE"); // ESB内部渠道SOURCE——与请求一致
		String RETCODE = jsonObject.getString("RETCODE"); // 应答码RETCODE
		String RETMSG = jsonObject.getString("RETMSG"); // 应答码描述RETMSG
		String HEADRESERVED = jsonObject.getString("HEADRESERVED"); //报文头保留域HEADRESERVED
		String responseSign = jsonObject.getString("SIGN");
		System.out.println("========交易代码TRXCODE================"+TRXCODE);
		System.out.println("========银行代码BANKCODE================"+BANKCODE);
		System.out.println("========交易日期TRXDATE================"+TRXDATE);
		System.out.println("========交易时间TTRXTIME================"+TRXTIME);
		System.out.println("========合作单位编号COINSTCODE================"+COINSTCODE);
		System.out.println("========合作单位渠道COINSTCHANNEL================"+COINSTCHANNEL);
		System.out.println("========交易流水号SEQNO================"+SEQNO);
		System.out.println("========ESB内部渠道SOURCE================"+SOURCE);
		System.out.println("========应答码RETCODE================"+RETCODE);
		System.out.println("========应答码描述RETMSG================"+RETMSG);
		System.out.println("========HEADRESERVED================"+HEADRESERVED);
		// 响应报文
		String CARDNBR = jsonObject.getString("CARDNBR");// 电子账户CARDNBR
		String NAME = jsonObject.getString("NAME"); // 姓名NAME
		String CURRNO = jsonObject.getString("CURRNO"); // 本次返回交易条数CURRNO
		System.out.println("========CARDNBR================"+CARDNBR);
		System.out.println("========NAME================"+NAME);
		System.out.println("========CURRNO================"+CURRNO);
		System.out.println("==========="+jsonObject.getString("SUBPACKS"));
		String SUBPACKS = jsonObject.getString("SUBPACKS");
		JSONArray arr = JSONArray.fromObject(SUBPACKS);
		Iterator<?> it = arr.iterator(); 
		String  SIG_CARD = null;
		String  TXNDATE = null;
		String  TXNTIME = null;
		String  CANCLDATE = null;
		String  CANCLTIME = null;
		String  RESERVED = null;
		while(it.hasNext()){
			JSONObject json = (JSONObject) it.next();
			SIG_CARD = json.getString("SIG_CARD"); // 绑定卡号SIG_CARD
			TXNDATE = json.getString("TXNDATE"); // 签约日期TXNDATE
			TXNTIME = json.getString("TXNTIME"); // 签约时间TXNTIME
			CANCLDATE = json.getString("CANCLDATE"); // 签约日期CANCLDATE
			CANCLTIME = json.getString("CANCLTIME"); // 签约时间CANCLTIME
			RESERVED = json.getString("RESERVED"); // 保留域RESERVED
			
			System.out.println("========绑定卡号SIG_CARD================"+SIG_CARD);
			System.out.println("========签约日期TXNDATE================"+TXNDATE);
			System.out.println("========签约时间TXNTIME================"+TXNTIME);
			System.out.println("========签约日期CANCLDATE================"+CANCLDATE);
			System.out.println("========签约时间CANCLTIME================"+CANCLTIME);
			System.out.println("========保留域RESERVED================"+RESERVED);
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(TRXCODE));
		buffer.append(StringUtils.trimToEmpty(BANKCODE));
		buffer.append(StringUtils.trimToEmpty(TRXDATE));
		buffer.append(StringUtils.trimToEmpty(TRXTIME));
		buffer.append(StringUtils.trimToEmpty(COINSTCODE));
		buffer.append(StringUtils.trimToEmpty(COINSTCHANNEL));
		buffer.append(StringUtils.trimToEmpty(SEQNO));
		buffer.append(StringUtils.trimToEmpty(SOURCE));
		buffer.append(StringUtils.trimToEmpty(RETCODE));
		buffer.append(StringUtils.trimToEmpty(RETMSG));
		buffer.append(StringUtils.trimToEmpty(HEADRESERVED));

		buffer.append(StringUtils.trimToEmpty(CARDNBR));
		buffer.append(StringUtils.trimToEmpty(NAME));
		buffer.append(StringUtils.trimToEmpty(CURRNO));
		buffer.append(StringUtils.trimToEmpty(SIG_CARD));
		buffer.append(StringUtils.trimToEmpty(TXNDATE));
		buffer.append(StringUtils.trimToEmpty(TXNTIME));
		buffer.append(StringUtils.trimToEmpty(CANCLDATE));
		buffer.append(StringUtils.trimToEmpty(CANCLTIME));
		buffer.append(StringUtils.trimToEmpty(RESERVED));
		String str = buffer.toString();

		boolean verifyResult = HSignUtil.getVerify(str, responseSign);

		if (!verifyResult){
			System.out.println("验证签名失败...");
		} else {
			System.out.println("验证签名成功");
		}
		if ("00000000".equals(jsonObject.getString("RETCODE"))){
			System.out.println("操作成功 00000000");
			 
 		} else {
			System.out.println("操作失败,错误代码："+jsonObject.getString("RETCODE"));
		}
	}
	
	public static void main(String[] args) {
		String resu = doParams("9930040290000400018");
		callBack(resu);
	}
}
