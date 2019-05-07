package com.huishang.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.cupdata.zicon.util.RSAEncryptUtil;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class Test {

    static String uri = "http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt";
//    static String uri = "http://localhost:8080/dbesb/api/requestEncrypt";

    private  static String signPrivatePath="D:/wskey/sign_.key";

    private static  String signPublicPath="D:/wskey/verify_.cer";

    private  static String decryptPath="D:/wskey/decryptions_.key";

    private static  String encryptPath="d:/wskey/encryptions_.cer";
    
    
    /**
     * 报文头
     * @Title: messageHead
     * @Description: TODO(报文头)
     * @throws Exception
     * @return LinkedHashMap<String,String>    返回类型
     */
    private static LinkedHashMap<String, String> messageHead() throws Exception {
    	LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
        reqMap.put("TRXCODE", "5808"); // 交易代码
        reqMap.put("BANKCODE", HSignUtil.BANKCODE); // 银行代码
		Date date = new Date();
		String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——8位YYYYMMDD
		String TRXTIME = StringUtil.formatDate(date, "hhmmss"); // 交易时间TRXTIME——6为HHmmss
        reqMap.put("TRXDATE", TRXDATE); // 交易日期
        reqMap.put("TRXTIME", TRXTIME); // 交易时间
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); // 合作单位编号
        reqMap.put("COINSTCHANNEL","000002"); // 合作单位渠道
        reqMap.put("SEQNO",StringUtil.getNo()); // 交易流水号
        reqMap.put("SOURCE", "A0"); // ESB内部渠道
        reqMap.put("RETCODE", ""); // 应答码
        reqMap.put("RETMSG", ""); // 应答码描述
        reqMap.put("HEADRESERVED", ""); // 报文头保留域
        return reqMap;
    }
    
    /**
     * 直销银行电子账户签约卡绑定
     * @Title: test5812
     * @Description: TODO(直销银行电子账户签约卡绑定)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5812() throws Exception{
        LinkedHashMap<String, String> reqMap = messageHead();
        // 请求报文
        reqMap.put("CARDNBR","9930040050240100020"); // 电子账号
        reqMap.put("PINFLAG", "0"); // 使用密码标志（0：不使用密码 、1：使用消费密码、2：使用查询密码）
        reqMap.put("PIN", ""); // 密码
        reqMap.put("SIG_CARD", "6228480402564890018"); // 绑定卡号6228770010016827881   6212262402007758853
        reqMap.put("SURNAME", "白利雄"); // 姓名
        reqMap.put("KEYTYPE", "01"); // 证件类型
        reqMap.put("IDNO", "150221198809092918"); // 证件号码
        reqMap.put("MOBILE", "13553869019"); // 手机号码

        reqMap.put("REMARK", "绑定"); // 备注
        
        // 响应报文
        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR"); // 电子账号
        resColumnList.add("NAME"); // 姓名
        resColumnList.add("SIGNFLAG"); // 签约状态（0.未签约，1.已签约）
        resColumnList.add("TXNDATE"); // 签约日期
        resColumnList.add("TXNTIME"); // 签约时间
        testCommon2(reqMap, resColumnList,null);
    }
    
    /**
     * 直销银行电子账户签约卡绑定关系取消
     * @Title: test5813
     * @Description: TODO(直销银行电子账户签约卡绑定关系取消)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5813() throws Exception{
        LinkedHashMap<String, String> reqMap = messageHead();
        // 请求报文
        reqMap.put("CARDNBR","9930040050240700019"); // 电子账号
        reqMap.put("PINFLAG", "0"); // 使用密码标志（0：不使用密码 、1：使用消费密码、2：使用查询密码）
        reqMap.put("PIN", ""); // 密码
        reqMap.put("SIG_CARD", "6228480402564890018"); // 绑定卡号

        reqMap.put("REMARK", "解绑"); // 备注

        // 响应报文
        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR"); // 电子账号
        resColumnList.add("NAME"); // 姓名
        resColumnList.add("SIGNFLAG"); // 签约状态（0.未签约，1.已签约）
        resColumnList.add("TXNDATE"); // 签约日期
        resColumnList.add("TXNTIME"); // 签约时间
        resColumnList.add("CANCLDATE"); // 签约取消日期
        resColumnList.add("CANCLTIME"); // 签约取消时间
        testCommon2(reqMap, resColumnList,null);
    }


    /**
     * 绑定卡关系查询
     * @Title: test5814
     * @Description: TODO(绑定卡关系查询)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5814() throws Exception{
        LinkedHashMap<String, String> reqMap = messageHead();
        
        // 请求报文
        reqMap.put("CARDNBR","9930040050240500013"); // 电子账号
        reqMap.put("PINFLAG", "1"); // 使用密码标志（0：不使用密码 、1：使用消费密码、2：使用查询密码）
        reqMap.put("PIN", "123456"); // 密码
        reqMap.put("STATE", "0"); // 查询的记录状态（0.所有，1.当前有效的绑定卡）
        reqMap.put("REMARK","绑定卡关系查询"); // 备注

        // 响应报文
        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR"); // 电子账号
        resColumnList.add("NAME"); // 持卡人姓名
        resColumnList.add("CURRNO"); // 本次返回交易条数
        
        testCommon2(reqMap, resColumnList, "5814");
    }
    
    /**
     * P2P产品购买自动投标签约
     * @Title: test5802
     * @Description: TODO(P2P产品购买自动投标签约)
     * @throws Exception
     * @return void    返回类型
     */
//    public void test5802() throws Exception{
//    	LinkedHashMap<String, String> reqMap = messageHead();
//
//    	// 请求报文
//        reqMap.put("CARDNBR", "9930040050240500013"); // 电子账号
//        reqMap.put("PINFLAG", "0"); // 使用密码标志（0：不使用密码 、1：使用消费密码、2：使用查询密码）
//        reqMap.put("PIN", ""); // 密码
//
//        reqMap.put("SERI_NO", StringUtil.getNo()); // 申请流水号
//        reqMap.put("AMOUNT", "1000"); // 投标金额
//        reqMap.put("REMARK", "投标签约中"); // 备注
//
//
//        ArrayList<String> resColumnList = new ArrayList<String>();
//
//        resColumnList.add("SERI_NO"); // 申请流水号
//        resColumnList.add("AMOUNT"); // 投标金额
//        resColumnList.add("CARDNBR"); // 电子账号
//        resColumnList.add("NAME"); // 持卡人姓名
//        resColumnList.add("SIGNFLAG"); // 签约状态
//        resColumnList.add("TXNDATE"); // 签约日期
//        resColumnList.add("TXNTIME"); // 签约时间
//
//        testCommon2(reqMap, resColumnList,null);
//    }


    /**
     * P2P产品购买自动投标签约取消
     * @Title: test5803
     * @Description: TODO(P2P产品购买自动投标签约取消)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5803() throws Exception{
    	LinkedHashMap<String, String> reqMap = messageHead();

        reqMap.put("CARDNBR", "9930040050240500013");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "800114"+StringUtil.getNo());
        reqMap.put("ORISERI_NO", "80011420170408170819312451");
        reqMap.put("REMARK", "P2P产品");

        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("SERI_NO");
        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("SIGNFLAG");
        resColumnList.add("TXNDATE");
        resColumnList.add("TXNTIME");
        resColumnList.add("CANCLDATE");
        resColumnList.add("CANCLTIME");

        testCommon2(reqMap, resColumnList,null);
    }


    /**
     * P2P产品购买自动投标签约查询
     * @Title: test5804
     * @Description: TODO(P2P产品购买自动投标签约查询)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5804() throws Exception{
    	LinkedHashMap<String, String> reqMap = messageHead();

        reqMap.put("CARDNBR", "9930040050240500013");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("REMARK", "P2P产品查询");

        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR");
        resColumnList.add("SERI_NO");
        resColumnList.add("AMOUNT");
        resColumnList.add("NAME");
        resColumnList.add("SIGNFLAG");
        resColumnList.add("TXNDATE");
        resColumnList.add("TXNTIME");
        resColumnList.add("CANCLDATE");
        resColumnList.add("CANCLTIME");
        testCommon2(reqMap, resColumnList,null);
    }

    /**
     * P2P产品自动投标
     * @Title: test5805
     * @Description: TODO(P2P产品自动投标)
     * @throws Exception
     * @return void    返回类型
     */
//    public void test5805() throws Exception{
//    	LinkedHashMap<String, String> reqMap = messageHead();
//
//        reqMap.put("CARDNBR", "6288818881000101949");
//        reqMap.put("PINFLAG", "0");
//        reqMap.put("PIN", "");
//        reqMap.put("SERI_NO", "201508210002");
//        reqMap.put("AMOUNT", "200");
//        reqMap.put("AUTHCODE", "20150821001");
//        reqMap.put("FUISSUER", "PA");
//        reqMap.put("PRODUCT", "PAH003");
//        reqMap.put("INTDATE", "20151224");
//        reqMap.put("INTTYPE", "0");
//        reqMap.put("INTPAYDAY", "");
//        reqMap.put("ENDDATE", "20160420");
//        reqMap.put("YIELD", "12");
//        reqMap.put("FRZFLAG", "0");
//        reqMap.put("REMARK", "P2P自动投标");
//
//
//        ArrayList<String> resColumnList = new ArrayList<String>();
//
//        resColumnList.add("CARDNBR");
//        resColumnList.add("NAME");
//        resColumnList.add("FUISSUER");
//        resColumnList.add("PRMNO");
//        resColumnList.add("FRZAMT");
//        resColumnList.add("FORINCOME");
//        resColumnList.add("BUYDATE");
//        resColumnList.add("STATE");
//        resColumnList.add("AUTHCODE");
//        testCommon2(reqMap, resColumnList,null);
//    }


    /**
     * P2P产品投标申请
     * @Title: test5806
     * @Description: TODO(P2P产品投标申请)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5806() throws Exception{
    	LinkedHashMap<String, String> reqMap = messageHead();
        reqMap.put("CARDNBR", "9930040050240500013");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", StringUtil.getNo());
        reqMap.put("AMOUNT", "100");
        reqMap.put("FUISSUER", "14");
        reqMap.put("PRODUCT", "GJ1234");
        reqMap.put("INTDATE", "20151212");
        reqMap.put("INTTYPE", "0");
        reqMap.put("INTPAYDAY", "");
        reqMap.put("ENDDATE", "20151230");
        reqMap.put("YIELD", "1");
        reqMap.put("FRZFLAG", "1");
        reqMap.put("BOSAMT_YN", "0");
        reqMap.put("BOSAMT", "");
        reqMap.put("REMARK", "P2P产品查询");

        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("FRZAMT");
        resColumnList.add("FORINCOME");
        resColumnList.add("BUYDATE");
        resColumnList.add("STATE");
        resColumnList.add("AUTHCODE");
        resColumnList.add("BOSAMT");
        testCommon2(reqMap, resColumnList,null);
    }

    /**
     * P2P产品投标撤销
     * @Title: test5807
     * @Description: TODO(P2P产品投标撤销)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5807() throws Exception{
    	LinkedHashMap<String, String> reqMap = messageHead();
        reqMap.put("CARDNBR", "9930040050240500013");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "800114"+StringUtil.getNo());
        reqMap.put("OLDSEQNO", "20170426113610601930");
        reqMap.put("AMOUNT", "100.00");
        reqMap.put("FUISSUER", "14");
        reqMap.put("PRODUCT", "GJlo1w");

        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("FRZAMT");
        resColumnList.add("FORINCOME");
        resColumnList.add("BUYDATE");
        resColumnList.add("STATE");
        resColumnList.add("BOSAMT");
        testCommon2(reqMap, resColumnList,null);
    }


    /**
     * P2P产品投标查询
     * @Title: test5808
     * @Description: TODO(P2P产品投标查询)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5808() throws Exception{
    	LinkedHashMap<String, String> reqMap = messageHead();


        reqMap.put("CARDNBR","9930040050240500013");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "20170428104610593895");
        reqMap.put("REMARK", "P2P产品投标查询");

        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("FRZAMT");
        resColumnList.add("FORINCOME");
        resColumnList.add("BUYDATE");
        resColumnList.add("STATE");
        resColumnList.add("AUTHCODE");
        resColumnList.add("BOSAMT");
        testCommon2(reqMap, resColumnList,null);
    }


    /**
     * 产品投标明细查询
     * @Title: test5809
     * @Description: TODO(产品投标明细查询)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5809() throws Exception{
    	LinkedHashMap<String, String> reqMap = messageHead();


        reqMap.put("CARDNBR", "9930040050240500013");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("FUISSUER", "14");
        reqMap.put("PRODUCT", "GJ3psi");
        reqMap.put("STATE", "0");
        reqMap.put("RTN_IND", "");
        reqMap.put("BUYDATE", "");
        reqMap.put("SERI_NO", "");
        reqMap.put("FUISSUER_PAGE", "");
        reqMap.put("PRODUCT_PAGE", "");
        reqMap.put("REMARK", "");

        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("BUTDATE");
        resColumnList.add("SERI_NO");
        resColumnList.add("AMOUNT");
        resColumnList.add("YIELD");
        resColumnList.add("FORINCOME");
        resColumnList.add("INTTOTAL");
        resColumnList.add("INCOME");
        resColumnList.add("INCFLAG");
        resColumnList.add("ENDDATE");
        resColumnList.add("STATE");
        resColumnList.add("BOSAMT");

        testCommon2(reqMap, resColumnList,"5809");
    }
    
    /**
     * P2P产品红包转账
     * @Title: test7603
     * @Description: TODO(P2P产品红包转账)
     * @throws Exception
     * @return void    返回类型
     */
//    public void test7603() throws Exception{
//    	LinkedHashMap<String, String> reqMap = messageHead();
//
//
//        reqMap.put("CARDNBR", "6288818881000100875");
//        reqMap.put("CARDNBRIN", "6288818881000100917");
//        reqMap.put("CURRENCY", "156");
//        reqMap.put("AMOUNT", "10");
//
//
//        ArrayList<String> resColumnList = new ArrayList<String>();
//
//        resColumnList.add("CARDNBR");
//        resColumnList.add("CARDNBRIN");
//        resColumnList.add("CURRENCY");
//        resColumnList.add("AMOUNT");
//
//        testCommon2(reqMap, resColumnList,null);
//    }
    
    /**
     * P2P产品红包转账撤销
     * @Title: test7620
     * @Description: TODO(P2P产品红包转账撤销)
     * @throws Exception
     * @return void    返回类型
     */
//    public void test7620() throws Exception{
//    	LinkedHashMap<String, String> reqMap = messageHead();
//
//        reqMap.put("TRXDATE_ORI", "20150703");
//        reqMap.put("TRXTIME_ORI", "101000");
//        reqMap.put("SEQNO_ORI", "20150713163000000001");
//        reqMap.put("CARDNBR", "6288818881000100875");
//        reqMap.put("CARDNBRIN", "6288818881000100917");
//        reqMap.put("CURRENCY", "156");
//        reqMap.put("AMOUNT", "10");
//
//
//        ArrayList<String> resColumnList = new ArrayList<String>();
//
//        resColumnList.add("CARDNBR");
//        resColumnList.add("CARDNBRIN");
//        resColumnList.add("CURRENCY");
//        resColumnList.add("AMOUNT");
//
//        testCommon2(reqMap, resColumnList,null);
//    }
    
    /**
     * 电子账户余额查询
     * @Title: test5863
     * @Description: TODO(电子账户余额查询)
     * @throws Exception
     * @return void    返回类型
     */
    public void test5863() throws Exception{
    	LinkedHashMap<String, String> reqMap = messageHead();
        
        reqMap.put("CARDNBR","9930040050240500013"); // 电子账号 也就是用户客户号 "9930040050240500013"
        reqMap.put("PINFLAG", "0");// 0-不检查密码     1-检查取现密码
        reqMap.put("PIN", ""); // 密码 ANSI98格式，详见“信息安全处理”PIN加密部分
        reqMap.put("RESERVED", ""); // 保留域
        reqMap.put("TRDRESV", ""); // 第三方机构使用，原样返回

        ArrayList<String> resColumnList = new ArrayList<String>();

        resColumnList.add("CARDNBR");//电子账户
        resColumnList.add("NAME");//持卡人姓名
        resColumnList.add("AVAIL_BAL");//可用余额
        resColumnList.add("CURR_BAL");//账面余额
        resColumnList.add("RESERVED");//保留域
        resColumnList.add("TRDRESV");//第三方保留域

        testCommon2(reqMap, resColumnList,null);
    }
    
    public void testCommon2(LinkedHashMap<String, String> reqMap, ArrayList<String> resColumnList,String code) throws  Exception{
    	testCommon2(reqMap, resColumnList,code,"");
    }

    //公共方法调用2
    public void testCommon2(LinkedHashMap<String, String> reqMap, ArrayList<String> resColumnList,String code,String version) throws  Exception
    {
        String requestMapMerged = mergeMap(reqMap);
        Map<String, String> map=new HashMap<String, String>();
        map.put("BANKCODE",reqMap.get("BANKCODE"));
        map.put("COINSTCODE",reqMap.get("COINSTCODE"));
        map.put("APIVERSION",version);
        String sign1 = RSAEncryptUtil.MD5WithRSASign(requestMapMerged.getBytes("UTF-8"), getSignPrivateKey4Client(signPrivatePath));
        System.out.println("签名成功");
        reqMap.put("SIGN", sign1);
        JSONObject jsMap = JSONObject.fromObject(reqMap);
        //String data=RSAUtil2.encryptRSA(jsMap.toString(), getVerifyKey4Client(encryptPath));
        //加密调整
        String js = jsMap.toString();
        String md5encryptionPath=encryptPath;
        String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath);	//获取加密公钥字符串
        String data = RSAUtils.encryptRSAByte(js.getBytes("UTF-8"), encryptionKey4Server);//进行摘要并对摘要进行加密
        
        map.put("reqMap",data);
        HttpClient httpClient;
        PostMethod method;
        httpClient = new HttpClient();
        JSONObject reqdata = JSONObject.fromObject(map);
        System.out.println("发送请求至："+uri);
        method = new PostMethod(uri);
        method.setParameter("reqdata",reqdata.toString());
        httpClient.executeMethod(method);
        String response = method.getResponseBodyAsString();
        Map<?, ?> jsonMap= JSONObject.fromObject(response);
        String resultData=jsonMap.get("responseData").toString();
        //String result=RSAUtil2.decryptRSA(resultData,getSignPrivateKey4Client(decryptPath));
        //解密调整
        String decryptKeyPath=decryptPath;	//拼接解密私钥路径
        String decryptKey4Server=RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        String result=RSAUtils.decryptRSAs(resultData,decryptKey4Server);//对请求数据解密
        
        HashMap<?, ?> responseMap=(HashMap<?, ?>)parseJSON2Map(result);
        System.out.println("responseMap："+responseMap);
        StringBuffer responseMapMerged = new StringBuffer();
        ArrayList<String> headColumn = new ArrayList<String>();

        headColumn.add("TRXCODE");
        headColumn.add("BANKCODE");
        headColumn.add("TRXDATE");
        headColumn.add("TRXTIME");
        headColumn.add("COINSTCODE");
        headColumn.add("COINSTCHANNEL");
        headColumn.add("SEQNO");
        headColumn.add("SOURCE");
        headColumn.add("RETCODE");
        headColumn.add("RETMSG");
        headColumn.add("HEADRESERVED");

        resColumnList.addAll(0, headColumn);
        int listLength = resColumnList.size();
        if(responseMap.get("RETCODE")==null)
        {
            System.out.println("操作失败:"+responseMap.get("RETMSG"));
            throw new ArrayIndexOutOfBoundsException("请求参数非法");
            //InvocationTargetException
        }
        JSONObject json=JSONObject.fromObject(responseMap);
        //for循环次数
        int forLen = 0;
        //如果是5849交易
        if("5849".equals(responseMap.get(resColumnList.get(0)))){
        	forLen = listLength-1;
        }else{
        	forLen = listLength;
        }
        for (int i = 0; i < forLen; i++) {
        	Object columnvalue=responseMap.get(resColumnList.get(i));
        	if(columnvalue!=null){
        		responseMapMerged.append(responseMap.get(resColumnList.get(i)));
        	}
        }
        
        if(json.get("SUBPACKS")!=null)
        {
            JSONArray SUBPACKS=(JSONArray)json.get("SUBPACKS");
            System.out.println("SUBPACKS:"+SUBPACKS);
            int Size = SUBPACKS.size();

            for(int i=0;i<Size;i++)
            {
                if(code.equals("5809")){
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("FUISSUER"));
                    responseMapMerged.append(jsonObj.get("PRMNO"));
                    responseMapMerged.append(jsonObj.get("BUYDATE"));
                    responseMapMerged.append(jsonObj.get("SERI_NO"));
                    responseMapMerged.append(jsonObj.get("AMOUNT"));
                    responseMapMerged.append(jsonObj.get("YIELD"));
                    responseMapMerged.append(jsonObj.get("FORINCOME"));
                    responseMapMerged.append(jsonObj.get("INTTOTAL"));
                    responseMapMerged.append(jsonObj.get("INCOME"));
                    responseMapMerged.append(jsonObj.get("INCFLAG"));
                    responseMapMerged.append(jsonObj.get("ENDDATE"));
                    responseMapMerged.append(jsonObj.get("STATE"));
                    responseMapMerged.append(jsonObj.get("BOSAMT"));
                }
                else if(code.equals("5814")){
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("SIG_CARD"));
                    responseMapMerged.append(jsonObj.get("TXNDATE"));
                    responseMapMerged.append(jsonObj.get("TXNTIME"));
                    responseMapMerged.append(jsonObj.get("CANCLDATE"));
                    responseMapMerged.append(jsonObj.get("CANCLTIME"));
                    responseMapMerged.append(jsonObj.get("RESERVED"));
                }
                else if(code.equals("5849")){
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("FUPROD"));
                    responseMapMerged.append(jsonObj.get("PROD_DESC"));
                    responseMapMerged.append(jsonObj.get("CARDNBR"));
                    responseMapMerged.append(jsonObj.get("NAME"));
                    responseMapMerged.append(jsonObj.get("AMOUNT"));
                    responseMapMerged.append(jsonObj.get("LOAN_TERM"));
                    responseMapMerged.append(jsonObj.get("INPDATE"));
                    responseMapMerged.append(jsonObj.get("INPTIME"));
                    responseMapMerged.append(jsonObj.get("STATE"));
                    responseMapMerged.append(jsonObj.get("CARDNBR_SU"));
                    responseMapMerged.append(jsonObj.get("NAME_SU"));
                }
            }
//            responseMapMerged.append(responseMap.get(resColumnList.get(listLength-1)));
        }


        //验签
        String responseSign = (String) responseMap.get("SIGN");

        System.out.println("responseMerged: "+responseMapMerged.toString());

        boolean verifyResult = RSAEncryptUtil.MD5WithRSAVerify(responseMapMerged.toString().getBytes("UTF-8"), getVerifyKey4Client(signPublicPath), responseSign);

        if (!verifyResult){
            System.out.println("验证签名失败...");
            return;
        } else {
            System.out.println("验证签名成功");
        }

        if ("00000000".equals(responseMap.get("RETCODE"))){
            System.out.println("操作成功");
        } else {
            System.out.println("操作失败,错误代码："+responseMap.get("RETCODE"));
        }
    }

    private  static String mergeMap(Map<String, String> map){

        String requestMerged = "";
        StringBuffer buff = new StringBuffer();

        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();

        Map.Entry<String, String> entry;

        while (iter.hasNext()) {

            entry = iter.next();
            System.out.print(entry.getKey() + ", ");
            if (!"SIGN".equals(entry.getKey())){
                buff.append(entry.getValue().trim());
            }

//        value = entry.getValue();

        }
        System.out.println(" ");

        requestMerged = buff.toString();
        return requestMerged;

    }

    /**
     * 根据私钥文件路径读取私钥
     * @param
     * @return
     */
    public static String getSignPrivateKey4Client(String keyPath){
        StringBuffer privateBuffer=new StringBuffer();
        BufferedReader bufferReader = null;
        try {
            //InputStream inputStream = Thread.currentThread().getContextClassLoader()
            //       .getResourceAsStream(signPrivatePath);
            FileInputStream fi=new FileInputStream(keyPath);
            InputStreamReader inputReader = new InputStreamReader(fi);
            bufferReader = new BufferedReader(inputReader);
            // 读取一行
            String line = "";
            while ((line=bufferReader.readLine())!=null) {
                privateBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        	try {
				bufferReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return privateBuffer.toString();
    }
    /**
     * 根据公钥文件路径读取私钥
     * @param
     * @return
     */
    public static String getVerifyKey4Client(String keyPath){
        String key="";
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            //InputStream in = Thread.currentThread().getContextClassLoader()
            //      .getResourceAsStream(signPublicPath);
            FileInputStream fi=new FileInputStream(keyPath);
            //生成一个证书对象并使用从输入流 inStream 中读取的数据对它进行初始化。
            Certificate c = cf.generateCertificate(fi);
            PublicKey publicKey = c.getPublicKey();
            key=new BASE64Encoder().encodeBuffer(publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * json转map
     *
     *  @param jsonStr 源数据
     *
     *  return map
     * */
    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                @SuppressWarnings("unchecked")
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }
    
	public static void main(String[] args) throws FileNotFoundException {
		Test test = new Test();
		try {
			test.test5808();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
