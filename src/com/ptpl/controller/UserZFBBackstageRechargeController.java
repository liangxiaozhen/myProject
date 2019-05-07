package com.ptpl.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cupdata.zicon.util.RSAEncryptUtil;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.web.util.GetStringNo;
import com.ptpl.web.util.StringUtil;

import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;
/**
 * 用户支付宝,转账充值
 * @author admin
 *
 */
@Controller
@RequestMapping("user/userzfbRecharge")
public class UserZFBBackstageRechargeController{
	//static String uri = "http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt";
	/** 托管账户信息*/
	@Autowired
	UserFsAccountInfoServiceI  userFsAccountInfoService;
	/** 充值记录*/
	@Autowired
	UserRechargeServiceI userRechargeService;

	// 历史交易明细查询
	@SuppressWarnings("all")
	public synchronized Map<String,String> test5824(String RTN_IND, String NX_INPD, String NX_RELD, String NX_INPT, String NX_TRNN)
			throws Exception {
		LinkedHashMap reqMap = new LinkedHashMap();
		reqMap.put("TRXCODE", "5824");
		reqMap.put("BANKCODE",HSignUtil.BANKCODE);
		reqMap.put("TRXDATE", GetStringNo.getYearDay());
		reqMap.put("TRXTIME", GetStringNo.getTimeString());
		reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);
		reqMap.put("COINSTCHANNEL",HSignUtil.COINSTCHANNEL_WEB);
		reqMap.put("SEQNO", GetStringNo.getOrderinfo());
		reqMap.put("SOURCE", HSignUtil.SOURCE);
		reqMap.put("RETCODE", "");
		reqMap.put("RETMSG", "");
		reqMap.put("HEADRESERVED", "");

		reqMap.put("CARDNBR", "9930040050240450029");
		reqMap.put("PINFLAG", "0");
		reqMap.put("PIN", "");
		reqMap.put("STARTDATE", "");
		reqMap.put("ENDDATE", "");
		reqMap.put("RTN_IND", RTN_IND);
		reqMap.put("NX_INPD", NX_INPD);
		reqMap.put("NX_RELD", NX_RELD);
		reqMap.put("NX_INPT", NX_INPT);
		reqMap.put("NX_TRNN", NX_TRNN);
		reqMap.put("TRANTYPE", "");
		reqMap.put("TYPE_FLAG", ""); 


		String result = userRechargeService.fenzhuang(reqMap, "");
		Map<String,String> map = callBack(result);
		return map;
	}

	
	
	// 公共方法调用2
	
	public  Map<String,String> callBack(String respone)throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		Map jsonMap = JSONObject.fromObject(respone);
		String resultData = jsonMap.get("responseData").toString();
		// 解密调整
		String decryptKeyPath = HSignUtil.DECRYPTPATH; // 拼接解密私钥路径
		String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
		String result = RSAUtils.decryptRSAs(resultData, decryptKey4Server);// 对请求数据解密

		HashMap responseMap = (HashMap) HSignUtil.parseJSON2Map(result);
		StringBuffer responseMapMerged = new StringBuffer();
		ArrayList<String> kjList = new ArrayList();
		kjList.add("CARDNBR");
		kjList.add("NAME");
		kjList.add("STARTDATE");
		kjList.add("ENDDATE");
		kjList.add("CURRNO");
		kjList.add("RTN_IND");
		
		List<String>  resColumnList = HSignUtil.getResponseHead(kjList);//把5824接口返回报文参数和1到11域拼接一起
		if (responseMap.get("RETCODE") == null) {
			System.out.println("操作失败:" + responseMap.get("RETMSG"));
			throw new ArrayIndexOutOfBoundsException("请求参数非法");
		}
		JSONObject json = JSONObject.fromObject(responseMap);
		// for循环次数
		int forLen = resColumnList.size();
		for (int i = 0; i < forLen; i++) {
			Object columnvalue = responseMap.get(resColumnList.get(i));
			if (columnvalue != null) {
				responseMapMerged.append(responseMap.get(resColumnList.get(i)));
			}
		}

		if (json.get("SUBPACKS") != null) {
			JSONArray SUBPACKS = (JSONArray) json.get("SUBPACKS");
			System.out.println("卡号 ：  " + json.getString("CARDNBR"));
			System.out.println("持卡人姓名：  " + json.getString("NAME"));
			System.out.println("起始日期：  " + json.getString("STARTDATE"));
			System.out.println("结束日期：  " + json.getString("ENDDATE"));
			System.out.println("本次返回交易条数：  " + json.getString("CURRNO"));
			System.out.println("翻页标志：  " + json.getString("RTN_IND"));
			int Size = SUBPACKS.size();
			for (int i = 0; i < Size; i++) {
					JSONObject jsonObj = SUBPACKS.getJSONObject(i);
					responseMapMerged.append(jsonObj.get("HS_VALD"));
					responseMapMerged.append(jsonObj.get("HS_INPD"));
					responseMapMerged.append(jsonObj.get("HS_RELD"));
					responseMapMerged.append(jsonObj.get("HS_INPT"));
					responseMapMerged.append(jsonObj.get("HS_TRNN"));
					responseMapMerged.append(jsonObj.get("TRANTYPE"));
					responseMapMerged.append(jsonObj.get("O_R_FLAG"));
					responseMapMerged.append(jsonObj.get("BILL_AMT"));
					responseMapMerged.append(jsonObj.get("BILL_AMS"));
					responseMapMerged.append(jsonObj.get("AUTHCODE"));
					responseMapMerged.append(jsonObj.get("DESLINE"));
					responseMapMerged.append(jsonObj.get("CURR_BAL"));
					responseMapMerged.append(jsonObj.get("NOTE"));
					responseMapMerged.append(jsonObj.get("FORCARDNBR"));
			}
		}
		// 验签
		String responseSign = (String) responseMap.get("SIGN");
		boolean verifyResult =HSignUtil.getVerify(responseMapMerged.toString(),responseSign);
			/*	RSAEncryptUtil.MD5WithRSAVerify(responseMapMerged.toString().getBytes("UTF-8"),getVerifyKey4Client(HSignUtil.SIGNPUBLICPATH), responseSign);*/
		if (!verifyResult) {
			System.out.println("验证签名失败...");
		} else {
			System.out.println("验证签名成功");
			if ("00000000".equals(responseMap.get("RETCODE"))) {
				map = responseMap;
				System.out.println("操作成功");
			} else {
				System.out.println("操作失败,错误代码：" + responseMap.get("RETCODE"));
			}
		}
		return map;
	}

	
	//递归发请求
	public List<JSONArray> setrequest(JSONArray SUBPACKS,String dateStr,List<JSONArray> list) throws Exception{
		//获取到最后一条数据的值
		JSONObject jsonObj = (JSONObject) SUBPACKS.get(SUBPACKS.size() - 1);
		if(jsonObj.get("HS_RELD").equals(dateStr)){
			String RTN_IND = "1"; //翻页标志 0：查询完毕；1：需继续翻页查询；
			String NX_INPD = (String) jsonObj.get("HS_INPD");// 交易日期
			String NX_RELD = (String) jsonObj.get("HS_RELD");// 自然日期
			String NX_INPT = (String) jsonObj.get("HS_INPT");// 交易时间
			String NX_TRNN = (String) jsonObj.get("HS_TRNN");// 交易流水号
			Map<String,String> mapZ = test5824(RTN_IND, NX_INPD, NX_RELD, NX_INPT, NX_TRNN);
			JSONObject jsonZ = JSONObject.fromObject(mapZ);
			SUBPACKS = (JSONArray) jsonZ.get("SUBPACKS");
			list.add(SUBPACKS);
			setrequest(SUBPACKS,dateStr,list);
		}else{
			return list;
		}	
		return list;
	}
	@RequestMapping(value = "/zfb", method = { RequestMethod.POST, RequestMethod.GET })
	public  void remit() throws Exception {
			Date date = new Date();
		    String dateStr = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
	        String RTN_IND = ""; //翻页标志 0：查询完毕；1：需继续翻页查询；
			String NX_INPD = "";// 交易日期
			String NX_RELD = "";// 自然日期
			String NX_INPT = "";// 交易时间
			String NX_TRNN = "";// 交易流水号
			Map<String,String> map = test5824(RTN_IND, NX_INPD, NX_RELD, NX_INPT, NX_TRNN);

			List<JSONArray> list = new ArrayList<JSONArray>(); 
			
			JSONObject json = JSONObject.fromObject(map);
	
			//获取到所有数据
			JSONArray SUBPACKS = (JSONArray) json.get("SUBPACKS");

			list.add(SUBPACKS);
		
			list = setrequest(SUBPACKS,dateStr,list);
			
	        saveRecharge(list,json.get("CARDNBR").toString());
	}
	
	
	//保存汇款充值的充值记录
	@SuppressWarnings("unused")
	public  void saveRecharge(List<JSONArray> list,String usrcustid){
		//声明一个对象集合
		List<JSONObject> listObj = new ArrayList<JSONObject>();
		for (int i = 0; i < list.size(); i++) {
			//获取list中的每个数组对象
			JSONArray jsonArray = list.get(i);
			for (int j = 0; j < jsonArray.size(); j++) {
				//获取数组中的每个对象
				JSONObject jsonObj = jsonArray.getJSONObject(j);
				listObj.add(jsonObj);
			}
		}
		
		for (int i = 0; i < listObj.size(); i++) {
			JSONObject jsonObj =listObj.get(i);
			
			if(jsonObj.get("TRANTYPE").equals("7820")){//交易方式为7820就是汇款充值
				if(jsonObj.get("NOTE").equals("转入")){//资金转入
					//根据银行返回订单号查询充值记录,如果数据库有记录就不插入,如果没有再插入
					UserRecharge ur = new UserRecharge();
					ur.setBankreturnno(jsonObj.get("HS_TRNN").toString());
					userRechargeService.getBankReturnNo(ur);
					if(ur==null){
						//获取一些相应的数据
						String orderno = StringUtil.getNo();
						//根据电子账号获取用户的id
						UserFsAccountInfo  ufs = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(usrcustid);
						//声明一个用于保存的对象
						UserRecharge usRecharge = new UserRecharge();
						//在发数据去银行之前保存一部分充值数据到本地数据库
						usRecharge.setRechargeno(orderno);//充值订单号
						usRecharge.setBankreturnno(jsonObj.get("HS_TRNN").toString());//银行返回流水号
						usRecharge.setRechargetype((short)4);//充值方式
						usRecharge.setBaseid(ufs.getBaseid());//保存用户id
						usRecharge.setStarttime(new Date()); //充值开始时间
						usRecharge.setAmount(Double.valueOf(jsonObj.get("BILL_AMT").toString()));//充值金额
						usRecharge.setMercustid(ufs.getUsrcustid());//商户客户号
						usRecharge.setApplyman(ufs.getUsrname()); //充值人!也就是登陆的用户
						usRecharge.setIsblending(UserRecharge_Constant.ISBLENDING_NO); //表示系统未勾兑
						usRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_NO);//表示人工未勾兑
						usRecharge.setPaycompany("徽商银行"); //充值通道公司
						usRecharge.setStatus((short)1); //表示充值状态成功 
						usRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_NO);//充值是正常
						int s = userRechargeService.add(usRecharge);
					}
				}
			}
		}
	}
	
}