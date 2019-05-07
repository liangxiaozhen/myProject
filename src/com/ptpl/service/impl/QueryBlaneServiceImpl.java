package com.ptpl.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.huishang.util.HSignUtil;
import com.ptpl.mapper.UserAccountMapper;
import com.ptpl.model.UserAccount;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

public class QueryBlaneServiceImpl implements QueryBlaneServiceI{
	@Autowired
	private UserAccountMapper userAccountMapper;

	/**
	 * 余额查询发送
	 * @param @param usercustid
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	public  Map<String, String> queryBlane(String usercustid){
		
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
		String TRXTIME = StringUtil.formatDate(date, "HHmmss");//交易时间	TRXTIME
		LinkedHashMap<String,String> reqMap = new LinkedHashMap<String,String>();
		//请求头
		String orderno = StringUtil.getNo();//交易流水号
		reqMap.put("TRXCODE", "5863");
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);
        reqMap.put("TRXDATE",TRXDATE);
        reqMap.put("TRXTIME", TRXTIME);
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);
        reqMap.put("COINSTCHANNEL",HSignUtil.COINSTCHANNEL_WEB);
        reqMap.put("SEQNO",orderno);//交易流水号
        reqMap.put("SOURCE", HSignUtil.SOURCE);
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");
        
        //请求参数
        reqMap.put("CARDNBR",usercustid); //电子账号 也就是用户客户号 "9930040050240500013"
        reqMap.put("PINFLAG", "0");//0-不检查密码     1-检查取现密码
        reqMap.put("PIN", "");//密码 ANSI98格式，详见“信息安全处理”PIN加密部分
        reqMap.put("RESERVED", ""); //保留域
        reqMap.put("TRDRESV", ""); //第三方机构使用，原样返回
        
        String result="";
		try {
			result = HSignUtil.HttpClientTask(reqMap, "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        Map<String,String> hashMap2 = blanceCallBack(result);
		return hashMap2;
	}
	/**
	 * 查询余额返回
	 * @param @param result
	 * @param @return
	 * @return Map<String,String>
	 * @author jiangxueyou
	 */
	public Map<String,String> blanceCallBack(String result){
		System.out.println(result);
		 Map<String,String> hashMap = new HashMap<>();
		  List<String> kjList = new ArrayList<>();
		  kjList.add("CARDNBR");//电子账户
		  kjList.add("NAME");//持卡人姓名
		  kjList.add("AVAIL_BAL");//可用余额
		  kjList.add("CURR_BAL");//账面余额
		  kjList.add("RESERVED");//保留域
		  kjList.add("TRDRESV");//第三方保留域
		 String resultdata = HSignUtil.getDecryptRSAByte(result);//解密
         System.out.println("*************"+resultdata+"************");
		 Map<String,Object> responseMap = (HashMap<String,Object>)HSignUtil.parseJSON2Map(resultdata);//数据转化Map
		  
	 	  List<String>  resColumnList = HSignUtil.getResponseHead(kjList);//把3031接口返回报文参数和1到11域拼接一起
	 	  
	 	 int listLength = resColumnList.size();
	     if(responseMap.get("RETCODE") == null){
	    	  hashMap.put("result", "network");// "因网络响应不及时,处理失败";
	    	  return hashMap;
	      }
	        
	      StringBuffer responseMapMerged = new StringBuffer();
	      for (int i = 0; i < listLength; i++) {
	      	Object columnvalue = responseMap.get(resColumnList.get(i));
	      	if(columnvalue != null){
	      		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
	      	}
	      }
	      System.out.println(responseMap);
	      //验签
	      String responseSign = (String) responseMap.get("SIGN");
	      boolean verifyResult = HSignUtil.getVerify(responseMapMerged.toString(), responseSign);//验证签名
	     if(!verifyResult){
  	    	hashMap.put("result", "network");// "因网络响应不及时,处理失败";
  	    	return hashMap;
  	    }
	    System.out.println(responseMap.get("RETCODE")+"============="); //应答码
	    
	    JSONObject json = JSONObject.fromObject(resultdata);
	    if(json.getString("RETCODE").equals("00000000")){
	    	hashMap.put("CARDNBR", json.getString("CARDNBR"));
	    	hashMap.put("NAME", json.getString("NAME"));
	    	hashMap.put("AVAIL_BAL", json.getString("AVAIL_BAL"));
	    	hashMap.put("CURR_BAL", json.getString("CURR_BAL"));
	    }
		return hashMap;
		
	}
	
	/**
	 * 后台提交
	 * @param @param reqMap
	 * @param @param version
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	public String fenzhuang(LinkedHashMap<String,String> reqMap,String version){
		String TaskResult = "";
		if(reqMap.size() > 0){
			   Map<String,Object> map = new HashMap<>();
			   map.put("BANKCODE",reqMap.get("BANKCODE"));//为银行代码
		       map.put("COINSTCODE",reqMap.get("COINSTCODE"));//为机构号
		       map.put("APIVERSION",version);//为报文版本号
		       
		       try {
				String sign = HSignUtil.getRASSign(reqMap);//加签名
				   reqMap.put("SIGN", sign);
				   	
				   String data = HSignUtil.getEncryptRSAByte(reqMap);//进行摘要并对摘要进行加密
				   
				   map.put("reqMap",data);//加密后的json参数
				   TaskResult = HSignUtil.doHttpClient(map);//发送HttpClient请求
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return TaskResult;
	}
	@Override
	public int updateUserAccount(Map<String, String> map, UserAccount userAccount) {
		int a = 0;
		if(map.size()>0){
			if(userAccount!=null){
				userAccount.setBalance(Double.valueOf(map.get("CURR_BAL")));
				userAccount.setAvlbalance( Double.valueOf(map.get("AVAIL_BAL")));
				userAccount.setFreezebalance(Arith.sub(Double.valueOf(map.get("CURR_BAL")), Double.valueOf(map.get("AVAIL_BAL"))));
				a = userAccountMapper.updateUserAccountBalAvl(userAccount);
			}
		}
		return a;
	}
	/**
	 *大象专用
	 ** @param @param usercustid
	 * @param @param baseid
	 * @param @return
	 * @return int
	 * @author jiangxueyou
	 */
	public int queryBlaneAndUpdateUserAccount(String usercustid,String baseid){
		 Map<String,String> map = queryBlane(usercustid);
		 UserAccount userAccount = userAccountMapper.getUserAccountByBaseId(new BigDecimal(baseid));
		 int acount = updateUserAccount(map,userAccount);
		return acount;
		
	}
}
