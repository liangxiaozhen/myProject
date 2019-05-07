package com.huishang.test;

import com.cupdata.zicon.util.RSAEncryptUtil;
import com.huishang.util.RSAUtils;
import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import sun.misc.BASE64Encoder;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by myriel on 2014/12/28.
 */
public class TransactionDemoEncrypted extends TestCase {

  /* sdfasdfasddddddddddda*/
    static String uri = "http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt";
//    static String uri = "http://localhost:8080/dbesb/api/requestEncrypt";


    private  static String signPrivatePath="D:/DEMO/30040000/000047/sign_.key";

    private static  String signPublicPath="D:/DEMO/30040000/00047/verify_.cer";

    private  static String decryptPath="D:/DEMO/decrypts.key";

    private static  String encryptPath="d:/DEMO/encryptions.cer";


    //查询账户是否已设置密码
    public void test5004() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5004");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20160822");
        reqMap.put("TRXTIME", "143000");
        reqMap.put("COINSTCODE", "000020");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000000095");
        reqMap.put("SOURCE", "PF");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");

        reqMap.put("CARDNO", "6288818881000100917");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("PIN_RSTS");

        testCommon2(reqMap, resColumnList,null);
    }

    //按证件号查询持卡人电子账户号
    public void test5620() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5620");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150903");
        reqMap.put("TRXTIME", "110200");
        reqMap.put("COINSTCODE", "000020");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000000010");
        reqMap.put("SOURCE", "PF");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");


        reqMap.put("KEYTYPE", "01");
        reqMap.put("CUSTID", "630105198506020131");
        reqMap.put("RTN_IND", "");
        reqMap.put("CARDNBR", "");
        reqMap.put("CYBANK_NO", "");


        ArrayList<String> resColumnList = new ArrayList();


        resColumnList.add("KEYTYPE");
        resColumnList.add("CUSTID");
        resColumnList.add("NAME");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");

        testCommon2(reqMap, resColumnList,"5620");
    }

    //基金收益信息查询
    public void test5631() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5631");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150902");
        reqMap.put("TRXTIME", "141400");
        reqMap.put("COINSTCODE", "000020");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150402163000001003");
        reqMap.put("SOURCE", "PF");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("FUCOMCODE","HA");
        reqMap.put("FUCODE", "888");
        reqMap.put("STARTDATE", "20151012");
        reqMap.put("ENDDATE", "20151019");



        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("FUCOMCODE");
        resColumnList.add("FUCODE");
        resColumnList.add("STATE");
        resColumnList.add("YIELDNOW");
        resColumnList.add("YIELDNOW_SIGN");
        resColumnList.add("STARTDATE");
        resColumnList.add("ENDDATE");
        resColumnList.add("CURRNO");

        testCommon2(reqMap, resColumnList,"5631");
    }

    //按证件号查询持卡人电子账户号
    public void test5800() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5800");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150504");
        reqMap.put("TRXTIME", "133000");
        reqMap.put("COINSTCODE", "000033");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000001006");
        reqMap.put("SOURCE", "PK");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");

        reqMap.put("KEYTYPE", "01");
        reqMap.put("CUSTID", "320681198108111012");
        reqMap.put("RTN_IND", "");
        reqMap.put("CARDNBR", "");


        ArrayList<String> resColumnList = new ArrayList();


        resColumnList.add("KEYTYPE");
        resColumnList.add("CUSTID");
        resColumnList.add("NAME");
        resColumnList.add("COUNT");
        resColumnList.add("RTN_IND");


        testCommon2(reqMap, resColumnList,"5800");
    }

    //电子账户交易明细查询
    public void test5801() throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5801");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150903");
        reqMap.put("TRXTIME", "110200");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150213163000000114");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR", "6288818881000101949");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("STARTDATE", "");
        reqMap.put("ENDDATE", "");
        reqMap.put("RTN_IND", "");
        reqMap.put("NX_INPD", "");
        reqMap.put("NX_RELD", "");
        reqMap.put("NX_INPT", "");
        reqMap.put("NX_TRNN", "");
        reqMap.put("TRANTYPE", "");
        reqMap.put("TYPE_FLAG", "");
        ArrayList<String> resColumnList = new ArrayList();
        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("STARTDATE");
        resColumnList.add("ENDDATE");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");

        testCommon2(reqMap, resColumnList,"5801");
    }


    //P2P产品购买自动投标签约
    public void test5802() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5802");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150821");
        reqMap.put("TRXTIME", "161000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150821000000001001");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000101949");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");

        reqMap.put("SERI_NO", "20150821001");
        reqMap.put("AMOUNT", "1000");
        reqMap.put("REMARK", "投标签约中");


        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("SERI_NO");
        resColumnList.add("AMOUNT");
        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("SIGNFLAG");
        resColumnList.add("TXNDATE");
        resColumnList.add("TXNTIME");

        testCommon2(reqMap, resColumnList,null);
    }


    //P2P产品购买自动投标签约取消
    public void test5803() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5803");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150311");
        reqMap.put("TRXTIME", "183000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000001003");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000100875");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "12121212");
        reqMap.put("SERI_NO", "224");
        reqMap.put("ORISERI_NO", "666");
        reqMap.put("REMARK", "P2P产品");

        ArrayList<String> resColumnList = new ArrayList();

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


    //P2P产品购买自动投标签约查询
    public void test5804() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5804");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150410");
        reqMap.put("TRXTIME", "143000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000000003");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000101949");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("REMARK", "P2P产品查询");

        ArrayList<String> resColumnList = new ArrayList();

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


    //P2P产品自动投标
    public void test5805() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5805");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150821");
        reqMap.put("TRXTIME", "161200");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150821000000000002");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR", "6288818881000101949");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "201508210002");
        reqMap.put("AMOUNT", "200");
        reqMap.put("AUTHCODE", "20150821001");
        reqMap.put("FUISSUER", "PA");
        reqMap.put("PRODUCT", "PAH003");
        reqMap.put("INTDATE", "20151224");
        reqMap.put("INTTYPE", "0");
        reqMap.put("INTPAYDAY", "");
        reqMap.put("ENDDATE", "20160420");
        reqMap.put("YIELD", "12");
        reqMap.put("FRZFLAG", "0");
        reqMap.put("REMARK", "P2P自动投标");


        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("FRZAMT");
        resColumnList.add("FORINCOME");
        resColumnList.add("BUYDATE");
        resColumnList.add("STATE");
        resColumnList.add("AUTHCODE");
        testCommon2(reqMap, resColumnList,null);
    }


    //P2P产品投标申请
    public void test5806() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5806");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150703");
        reqMap.put("TRXTIME", "101000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150703230000010165");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000100917");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "070301");
        reqMap.put("AMOUNT", "100");
        reqMap.put("FUISSUER", "AQ");
        reqMap.put("PRODUCT", "0001");
        reqMap.put("INTDATE", "20151212");
        reqMap.put("INTTYPE", "0");
        reqMap.put("INTPAYDAY", "10");
        reqMap.put("ENDDATE", "20151230");
        reqMap.put("YIELD", "12");
        reqMap.put("FRZFLAG", "0");
        reqMap.put("BOSAMT_YN", "0");
        reqMap.put("BOSAMT", "10");
        reqMap.put("REMARK", "P2P产品查询");

        ArrayList<String> resColumnList = new ArrayList();

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

    //P2P产品投标撤销
    public void test5807() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5807");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150312");
        reqMap.put("TRXTIME", "143400");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150402164000001063");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");


        reqMap.put("CARDNBR", "6288818881000100917");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "990452");
        reqMap.put("OLDSEQNO", "909");
        reqMap.put("AMOUNT", "50");
        reqMap.put("FUISSUER", "AQ");
        reqMap.put("PRODUCT", "000100");

        ArrayList<String> resColumnList = new ArrayList();

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


    //P2P产品投标查询
    public void test5808() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5808");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150410");
        reqMap.put("TRXTIME", "142600");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000000022");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR","6288818881000100917");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "902");
        reqMap.put("REMARK", "P2P产品投标查询");

        ArrayList<String> resColumnList = new ArrayList();

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


    //产品投标明细查询
    public void test5809() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5809");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150325");
        reqMap.put("TRXTIME", "134700");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000001123");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000100917");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("FUISSUER", "");
        reqMap.put("PRODUCT", "");
        reqMap.put("STATE", "");
        reqMap.put("RTN_IND", "");
        reqMap.put("BUYDATE", "");
        reqMap.put("SERI_NO", "");
        reqMap.put("FUISSUER_PAGE", "");
        reqMap.put("PRODUCT_PAGE", "");
        reqMap.put("REMARK", "");

        ArrayList<String> resColumnList = new ArrayList();

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


    //直销银行电子账户开立
    public void test5810() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5810");
        reqMap.put("BANKCODE", "30050000");

        reqMap.put("TRXDATE", "20150714");
        reqMap.put("TRXTIME", "175200");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150421163000001118");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");

        reqMap.put("KEYTYPE", "01");
        reqMap.put("IDNO", "630105198506020131");
        reqMap.put("SURNAME", "\\u9676\\u71d5");

        reqMap.put("MOBILE", "18677771111");
        reqMap.put("PRODUCT", "0001");
        reqMap.put("SMSFLAG", "1");
        reqMap.put("RISK_YN", "1");
        reqMap.put("RISK_LEL", "");
        reqMap.put("ACC_TYPE", "1");
        reqMap.put("FUCOMCODE", "HA");
        reqMap.put("ADNO", "recommandcode");
        reqMap.put("RECARD", "6222988812340046");
        reqMap.put("GENDER", "M");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("KEYTYPE");
        resColumnList.add("IDNO");
        resColumnList.add("NAME");
        resColumnList.add("CARDNBR");

        testCommon2(reqMap, resColumnList,null);
    }


    //直销银行电子账户签约卡绑定
    public void test5812() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5812");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150702");
        reqMap.put("TRXTIME", "131100");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150421223000001018");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");



        reqMap.put("CARDNBR","6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SIG_CARD", "6222988812340046");
        reqMap.put("SURNAME", "孟祥超");
        reqMap.put("KEYTYPE", "01");
        reqMap.put("IDNO", "211224199601057516");
        reqMap.put("MOBILE", "18611798521");

        reqMap.put("REMARK", "绑定");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("SIGNFLAG");
        resColumnList.add("TXNDATE");
        resColumnList.add("TXNTIME");
        testCommon2(reqMap, resColumnList,null);
    }


    //直销银行电子账户签约卡绑定关系取消
    public void test5813() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5813");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150818");
        reqMap.put("TRXTIME", "172600");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150818000000001003");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");



        reqMap.put("CARDNBR","6288818881000101873");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SIG_CARD", "6222988812340045");
        reqMap.put("SURNAME", "孙国亚");
        reqMap.put("KEYTYPE", "01");
        reqMap.put("IDNO", "130430198603122177");
        reqMap.put("MOBILE", "13381167229");

        reqMap.put("REMARK", "解绑");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("SIGNFLAG");
        resColumnList.add("TXNDATE");
        resColumnList.add("TXNTIME");
        resColumnList.add("CANCLDATE");
        resColumnList.add("CANCLTIME");
        testCommon2(reqMap, resColumnList,null);
    }


    //绑定卡关系查询
    public void test5814() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5814");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150818");
        reqMap.put("TRXTIME", "160900");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150421223000001011");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");



        reqMap.put("CARDNBR","6288818881000100917");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("STATE", "1");
        reqMap.put("REMARK","绑定卡关系查询");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CURRNO");



        testCommon2(reqMap, resColumnList, "5814");
    }


    //电子账户收益查询
    public void test5815() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5815");
        reqMap.put("BANKCODE", "30040000");
        reqMap.put("TRXDATE", "20160512");
        reqMap.put("TRXTIME", "140500");
        reqMap.put("COINSTCODE", "000001");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20160512163000001018");
        reqMap.put("SOURCE", "P1");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR","6288818881000101949");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("INQ_TYP","9");
        reqMap.put("RTN_IND", "");
        reqMap.put("NX_FITYPE", "");
        reqMap.put("NX_FICODE", "");
        reqMap.put("NX_FICODE1", "123");
        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("ACC_TYPE");
        resColumnList.add("AVAIL_BAL");
        resColumnList.add("CURR_BAL");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");

        testCommon2(reqMap, resColumnList,"5815","v20160505");
    }


    //P2P产品借款人还款冻结
    public void test5816() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5816");
        reqMap.put("BANKCODE", "88812900");
        reqMap.put("TRXDATE", "20150505");
        reqMap.put("TRXTIME", "100000");
        reqMap.put("COINSTCODE", "300401");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150402163000000003");
        reqMap.put("SOURCE", "01");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR", "6288818881000100917");
        reqMap.put("SERI_NO", "001");
        reqMap.put("AMOUNT", "121");
        reqMap.put("FUISSUER", "AQ");
        reqMap.put("PRODUCT", "0001");
        reqMap.put("REMARK", "还款冻结");


        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRODUCT");
        resColumnList.add("AMOUNT");


        testCommon2(reqMap, resColumnList,null);
    }


    //债权转让
    public void test5817() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5817");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150514");
        reqMap.put("TRXTIME", "100000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150402163000001003");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBRI", "6288818881000100917");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "20150519");
        reqMap.put("OLDSEQNO", "05155");
        reqMap.put("CARDNBRO", "6288818881000100875");
        reqMap.put("TBALACE", "1000.00");
        reqMap.put("AMOUNT", "10.00");
        reqMap.put("TRPRICE", "10.00");
        reqMap.put("INTDATE", "20150520");
        reqMap.put("YIELD", "0.10000");
        reqMap.put("FEEWAY", "0");
        reqMap.put("FEE", "0");
        reqMap.put("REMARK", "转让");



        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBRI");
        resColumnList.add("NAMEI");
        resColumnList.add("CARDNBR");
        resColumnList.add("NAMEO");
        resColumnList.add("AMOUNT");
        resColumnList.add("TRPRICE");
        resColumnList.add("LBALACE");
        resColumnList.add("FEE");
        resColumnList.add("INCOME");

        testCommon2(reqMap, resColumnList,null);
    }


    //债权转让查询
    public void test5818()throws Exception
    {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5818");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150521");
        reqMap.put("TRXTIME", "110200");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150213163000000003");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBRI", "6288818881000100917");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("SERI_NO", "20150519008");
        reqMap.put("REMARK", "test");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBRI");
        resColumnList.add("NAMEI");
        resColumnList.add("CARDNBRO");
        resColumnList.add("NAMEO");
        resColumnList.add("AMOUNT");
        resColumnList.add("TRPRICE");
        resColumnList.add("LBALACE");
        resColumnList.add("FEE");
        resColumnList.add("INCOME");
        testCommon2(reqMap, resColumnList,null);
    }


    //债权转让明细查询
    public void test5819()throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5819");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150903");
        reqMap.put("TRXTIME", "110200");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150213163000000003");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000100875");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("OLDSEQNO", "05155");
        reqMap.put("CARDNBRI", "6288818881000100917");
        reqMap.put("RTN_IND", "");
        reqMap.put("SERI_NO", "");
        reqMap.put("REMARK", "");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");

        testCommon2(reqMap, resColumnList,"5819");
    }

    //还款冻结撤销
    public void test5821()throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5821");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150715");
        reqMap.put("TRXTIME", "094100");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150715163120002011");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR","6288818881000101790");
        reqMap.put("SERI_NO","1234657984165446123154561354567142547853");
        reqMap.put("OLDSEQNO","1674654564561456485453124561234561234154");
        reqMap.put("AMOUNT","00000000100.00");
        reqMap.put("FUISSUER","AQ");
        reqMap.put("PRODUCT","123456");
        reqMap.put("REMARK","");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("FRZAMT");
        resColumnList.add("STATE");
        resColumnList.add("RESERVED");

        testCommon2(reqMap, resColumnList,null);
    }

    //还款冻结查询
    public void test5822()throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5822");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150715");
        reqMap.put("TRXTIME", "094100");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150715163120000111");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR","6288818881000101790");
        reqMap.put("PINFLAG","0");
        reqMap.put("PIN","");
        reqMap.put("SERI_NO","1674654564561456485453124561234561234154");
        reqMap.put("REMARK","");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("FRZAMT");
        resColumnList.add("STATE");
        resColumnList.add("RESERVED");

        testCommon2(reqMap, resColumnList,null);
    }

    //还款冻结明细查询
    public void test5823()throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5823");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150715");
        reqMap.put("TRXTIME", "094100");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150715163120001001");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR","6288818881000101790");
        reqMap.put("PINFLAG","0");
        reqMap.put("PIN"," ");
        reqMap.put("FUISSUER","");
        reqMap.put("PRODUCT","");
        reqMap.put("STATE","0");
        reqMap.put("RTN_IND","");
        reqMap.put("FRZDATE","");
        reqMap.put("SERI_NO","");
        reqMap.put("FUISSUER1","");
        reqMap.put("PRODUCT1","");
        reqMap.put("REMARK","");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");
        resColumnList.add("FUISSUER");
        resColumnList.add("PRMNO");
        resColumnList.add("BUYDATE");
        resColumnList.add("SERI_NO");
        resColumnList.add("AMOUNT");
        resColumnList.add("STATE");

        testCommon2(reqMap, resColumnList,"5823");
    }

    //历史交易明细查询（逆序）
    public void test5824()throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5824");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150715");
        reqMap.put("TRXTIME", "094100");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000006");
        reqMap.put("SEQNO", "20150715163120000101");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNO", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", " ");
        reqMap.put("STARTDATE", "20150701");
        reqMap.put("ENDDATE", "20150715");
        reqMap.put("RTN_IND", "");
        reqMap.put("NX_INPD", "");
        reqMap.put("NX_RELD", "");
        reqMap.put("NX_INPT", "");
        reqMap.put("NX_TRNN", "");
        reqMap.put("TRANTYPE", "");
        reqMap.put("TYPE_FLAG", "");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("STARTDATE");
        resColumnList.add("ENDDATE");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");
        resColumnList.add("HS_VALD");
        resColumnList.add("HS_INPD");
        resColumnList.add("HS_RELD");
        resColumnList.add("HS_INPT");
        resColumnList.add("HS_TRNN");
        resColumnList.add("TRANTYPE");
        resColumnList.add("O_R_FLAG");
        resColumnList.add("BILL_AMT");
        resColumnList.add("BILL_AMS");
        resColumnList.add("AUTHCODE");
        resColumnList.add("DESLINE");
        resColumnList.add("CURR_BAL");
        resColumnList.add("NOTE");
        resColumnList.add("FORCARDNBR");

        testCommon2(reqMap, resColumnList,"5824");

    }

    //贷款账户开立
    public void test5825()throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5825");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150715");
        reqMap.put("TRXTIME", "094100");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150715163120010001");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");



        reqMap.put("CARDNBR", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("LOANTYPE", "CD");
        reqMap.put("LOANLIMIT", "30000");
        reqMap.put("EXPDATE", "20160306");


        ArrayList<String> resColumnList = new ArrayList();
        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CUSTID");
        resColumnList.add("LOANTYPE");
        resColumnList.add("LOANLIMIT");
        resColumnList.add("EXPDATE");
        resColumnList.add("OPENDATE");
        testCommon2(reqMap, resColumnList,null);
    }

    //贷款账户开立查询
    public void test5826()throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5826");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150716");
        reqMap.put("TRXTIME", "093800");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150714163000100004");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("RTN_IND", "");
        reqMap.put("LOANTYPE", "CD");
        reqMap.put("REMARK", "地大物博呵呵哈哈嘻嘻啦啦");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("NAME");
        resColumnList.add("CUSTID");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");

        testCommon2(reqMap, resColumnList,"5826");
    }


    //单笔贷款发放查询
    public void test5827()throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5827");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150714");
        reqMap.put("TRXTIME", "142800");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150714163000010004");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("ORDERNO", "2015072800006");
        reqMap.put("REMARK", "");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CUSTID");
        resColumnList.add("ORDERNO");
        resColumnList.add("DATE");
        resColumnList.add("OPENTIME");
        resColumnList.add("RELDATE");
        resColumnList.add("INTDATE");
        resColumnList.add("ENDDATE");
        resColumnList.add("LOANAMT");
        resColumnList.add("INTDUE");
        resColumnList.add("FEEDUE");
        resColumnList.add("STATE");
        resColumnList.add("PROD");
        resColumnList.add("INTRATE");
        resColumnList.add("INTRATEO");
        resColumnList.add("FEERATE");
        resColumnList.add("FEERATEO");
        testCommon2(reqMap, resColumnList,null);
    }


    //单笔贷款明细查询
    public void test5828()throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5828");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150714");
        reqMap.put("TRXTIME", "142800");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150714163000001004");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("ORDERNO", "20150715000001");


        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CUSTID");
        resColumnList.add("ORDERNO");
        resColumnList.add("STATE");
        resColumnList.add("PROD");
        resColumnList.add("DATE");
        resColumnList.add("OPENTIME");
        resColumnList.add("RELDATE");
        resColumnList.add("INTDATE");
        resColumnList.add("ENDDATE");
        resColumnList.add("SUBDATE");
        resColumnList.add("INTRATE");
        resColumnList.add("INTRATEO");
        resColumnList.add("FEERATE");
        resColumnList.add("FEERATEO");

        resColumnList.add("OPEN_AMT");
        resColumnList.add("BALANCE");
        resColumnList.add("INTGET");
        resColumnList.add("INTGET_O");
        resColumnList.add("INTOWE_TOT");
        resColumnList.add("INTOWE");
        resColumnList.add("INTOWE_O");
        resColumnList.add("FEEGET_TOT");
        resColumnList.add("FEEGET_TOT");
        resColumnList.add("FEEGET_O");
        resColumnList.add("FEEOWE_TOT");
        resColumnList.add("FEEOWE");
        resColumnList.add("FEEOWE_O");


        testCommon2(reqMap, resColumnList,null);
    }

    //多笔贷款信息查询
    public void test5829()throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5829");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150714");
        reqMap.put("TRXTIME", "142800");
        reqMap.put("COINSTCODE", "000013");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150714163000000104");
        reqMap.put("SOURCE", "P8");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("STATE", "");
        reqMap.put("RTN_IND", "");
        reqMap.put("ACCO_SEQ", "");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CUSTID");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");

        testCommon2(reqMap, resColumnList,"5829");
    }


    //贷款利息试算
    public void test5830()throws Exception {
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5830");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150714");
        reqMap.put("TRXTIME", "142800");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150714163000001004");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");


        reqMap.put("CARDNBR", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", "");
        reqMap.put("ORDERNO", "2015072800002");
        reqMap.put("REMARK", "");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("CUSTID");
        resColumnList.add("AVAIL_BAL");
        resColumnList.add("ORDERNO");
        resColumnList.add("DATE");
        resColumnList.add("OPENTIME");
        resColumnList.add("RELDATE");
        resColumnList.add("INTDATE");
        resColumnList.add("ENDDATE");
        resColumnList.add("LOANAMT");
        resColumnList.add("STATE");
        resColumnList.add("PROD");
        resColumnList.add("TOTALAMT");
        resColumnList.add("INTRATEO");
        resColumnList.add("INTE");
        resColumnList.add("INTO");
        resColumnList.add("FEE");
        resColumnList.add("FEEO");

        testCommon2(reqMap, resColumnList,null);
    }

    //企业账户查询
    public void test5840() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5840");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150714");
        reqMap.put("TRXTIME", "142800");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "20150714163000001214");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");

        reqMap.put("CARDNBR", "6288818881000101790");
        reqMap.put("PINFLAG", "0");
        reqMap.put("PIN", " ");
        reqMap.put("REMARK", "");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("MOPHONE");
        resColumnList.add("RACECODE");
        resColumnList.add("CUSTRREF");
        resColumnList.add("CACCOUNT");
        resColumnList.add("BUSID");
        resColumnList.add("TAXID");

        testCommon2(reqMap, resColumnList,null);
    }

  //P2P理财产品信息录入(未上线)
    public void test5846() throws Exception{
    	 LinkedHashMap reqMap = new LinkedHashMap();
         reqMap.put("TRXCODE", "5846");
         reqMap.put("BANKCODE", "30040000");
         reqMap.put("TRXDATE", "20160331");
         reqMap.put("TRXTIME", "142800");
         reqMap.put("COINSTCODE", "000001");
         reqMap.put("COINSTCHANNEL", "000002");
         reqMap.put("SEQNO", "2015071416300000047");
         reqMap.put("SOURCE", "P1");
         reqMap.put("RETCODE", "");
         reqMap.put("RETMSG", "");
         reqMap.put("HEADRESERVED", "");

         reqMap.put("FUPROD","000101");
         reqMap.put("PROD_DESC","产品描述2");
         reqMap.put("CARDNBR","6236640010000001031");
         reqMap.put("AMOUNT","1000");
         reqMap.put("INTPAYDAY","15");
         reqMap.put("LOAN_TERM","60");
         reqMap.put("YIELD","11.0");
         reqMap.put("CARDNBR_SU","6236640010000001056");
         reqMap.put("TRDRESV","123@4561");

         ArrayList<String> resColumnList = new ArrayList();

         resColumnList.add("FUPROD");
         resColumnList.add("PROD_DESC");
         resColumnList.add("CARDNBR");
         resColumnList.add("NAME");
         resColumnList.add("AMOUNT");
         resColumnList.add("LOAN_TERM");
         resColumnList.add("INPDATE");
         resColumnList.add("STATE");
         resColumnList.add("CARDNBR_SU");
         resColumnList.add("NAME_SU");
         resColumnList.add("TRDRESV");

         testCommon2(reqMap, resColumnList,null);
    }

    //P2P理财产品信息撤销(未上线)
    public void test5847() throws Exception{
    	 LinkedHashMap reqMap = new LinkedHashMap();
         reqMap.put("TRXCODE", "5847");
         reqMap.put("BANKCODE", "30040000");
         reqMap.put("TRXDATE", "20160331");
         reqMap.put("TRXTIME", "142800");
         reqMap.put("COINSTCODE", "000001");
         reqMap.put("COINSTCHANNEL", "000002");
         reqMap.put("SEQNO", "2015071416300000047");
         reqMap.put("SOURCE", "P1");
         reqMap.put("RETCODE", "");
         reqMap.put("RETMSG", "");
         reqMap.put("HEADRESERVED", "");

         reqMap.put("FUPROD", "000100");
         reqMap.put("CARDNBR","6288818881000101790");
         reqMap.put("AMOUNT","1000");
         reqMap.put("TRDRESV", "123@456");

         ArrayList<String> resColumnList = new ArrayList();

         resColumnList.add("FUPROD");
         resColumnList.add("CARDNBR");
         resColumnList.add("NAME");
         resColumnList.add("AMOUNT");
         resColumnList.add("INPDATE");
         resColumnList.add("STATE");
         resColumnList.add("CARDNBR_SU");
         resColumnList.add("NAME_SU");
         resColumnList.add("TRDRESV");

         testCommon2(reqMap, resColumnList,null);
    }

    //P2P理财产品信息维护单笔查询(未上线)
    public void test5848() throws Exception{
    	LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5848");
        reqMap.put("BANKCODE", "30040000");
        reqMap.put("TRXDATE", "20160331");
        reqMap.put("TRXTIME", "142800");
        reqMap.put("COINSTCODE", "000001");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "2015071416300000047");
        reqMap.put("SOURCE", "P1");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");

        reqMap.put("FUPROD", "000100");
        reqMap.put("TRDRESV", "123@456");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("FUPROD");
        resColumnList.add("PROD_DESC");
        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("AMOUNT");
        resColumnList.add("LOAN_TERM");
        resColumnList.add("INPDATE");
        resColumnList.add("STATE");
        resColumnList.add("CARDNBR_SU");
        resColumnList.add("NAME_SU");
        resColumnList.add("TRDRESV");

        testCommon2(reqMap, resColumnList,null);
    }

    //P2P理财产品信息维护单笔查询(未上线)
    public void test5849() throws Exception{
    	LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "5849");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20160331");
        reqMap.put("TRXTIME", "142800");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL", "000002");
        reqMap.put("SEQNO", "2015071416300000046");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");

        reqMap.put("STARTDATE", "20160325");
        reqMap.put("ENDDATE", "");
        reqMap.put("RTN_IND", "");
        reqMap.put("INPDATE", "");
        reqMap.put("INPDTIME", "");
        reqMap.put("FUPROD", "");
        reqMap.put("TRDRESV", "0114@123");

        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("COMREV");
        resColumnList.add("CURRNO");
        resColumnList.add("RTN_IND");
        resColumnList.add("FUPROD");
        resColumnList.add("PROD_DESC");
        resColumnList.add("CARDNBR");
        resColumnList.add("NAME");
        resColumnList.add("AMOUNT");
        resColumnList.add("LOAN_TERM");
        resColumnList.add("INPDATE");
        resColumnList.add("INPTIME");
        resColumnList.add("STATE");
        resColumnList.add("CARDNBR_SU");
        resColumnList.add("NAME_SU");
        resColumnList.add("TRDRESV");

        testCommon2(reqMap, resColumnList,"5849");
    }

    //直销银行账户资金转入
    public void test7601() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "7601");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150825");
        reqMap.put("TRXTIME", "113000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150825000000000134");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "aaaaaaaaaaaaaaaaaaaaa");
        reqMap.put("HEADRESERVED", "bbbbbbbbbbbbbbbbbbbbbbb");



        reqMap.put("CARDNBR","6288818881000101956");
        reqMap.put("CARD_BIND", "6225682141000002950");
        reqMap.put("CURRENCY", "156");
        reqMap.put("AMOUNT", "1000.00");
        reqMap.put("KEYTYPE", "01");
        reqMap.put("IDNO", "630105198506020131");
        reqMap.put("NAME", "陶燕");
        reqMap.put("MOBILE", "18977771111");

        reqMap.put("AUTH_FLAG", "Y");

        reqMap.put("AUTHSEQ", "");

        reqMap.put("BANK_CODE", "");
        reqMap.put("BANK_NAME_EN", "");
        reqMap.put("BANK_NAME_CN", "");
        reqMap.put("ISS_BANK_PROVINCE", "");
        reqMap.put("ISS_BANK_CITY", "");



        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("CURRENCY");
        resColumnList.add("AMOUNT");
        testCommon2(reqMap, resColumnList,null);
    }

    //P2P产品红包转账
    public void test7603() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "7603");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150703");
        reqMap.put("TRXTIME", "101000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150713163000000102");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");


        reqMap.put("CARDNBR", "6288818881000100875");
        reqMap.put("CARDNBRIN", "6288818881000100917");
        reqMap.put("CURRENCY", "156");
        reqMap.put("AMOUNT", "10");


        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("CARDNBRIN");
        resColumnList.add("CURRENCY");
        resColumnList.add("AMOUNT");

        testCommon2(reqMap, resColumnList,null);
    }

    //P2P产品红包转账撤销
    public void test7620() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "7620");
        reqMap.put("BANKCODE", "30050000");
        reqMap.put("TRXDATE", "20150703");
        reqMap.put("TRXTIME", "101000");
        reqMap.put("COINSTCODE", "000005");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150713163000000002");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");

        reqMap.put("TRXDATE_ORI", "20150703");
        reqMap.put("TRXTIME_ORI", "101000");
        reqMap.put("SEQNO_ORI", "20150713163000000001");
        reqMap.put("CARDNBR", "6288818881000100875");
        reqMap.put("CARDNBRIN", "6288818881000100917");
        reqMap.put("CURRENCY", "156");
        reqMap.put("AMOUNT", "10");


        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("CARDNBR");
        resColumnList.add("CARDNBRIN");
        resColumnList.add("CURRENCY");
        resColumnList.add("AMOUNT");

        testCommon2(reqMap, resColumnList,null);
    }

    //一元电商平台消费接口
    public void test3060() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "3060");
        reqMap.put("BANKCODE", "30040000");
        reqMap.put("TRXDATE", "20150703");
        reqMap.put("TRXTIME", "101000");
        reqMap.put("COINSTCODE", "000047");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150713163000000002");
        reqMap.put("SOURCE", "D1");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");

        reqMap.put("ACCT_NO", "6288818881000100875");
        reqMap.put("PINFLAG", "1");
        reqMap.put("PIN", "123456");
        reqMap.put("CURRENCY", "156");
        reqMap.put("AMOUNT", "10");
        reqMap.put("OUTSIDEID", "2016081710040001");
        reqMap.put("CONSUME_TYPE", "00");
        reqMap.put("TXNDESC", "");
        reqMap.put("RESERVE", "");


        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("ACCT_NO");
        resColumnList.add("CURRENCY");
        resColumnList.add("AMOUNT");
        resColumnList.add("CLRDATE");
        resColumnList.add("OUTSIDEID");
        resColumnList.add("RESERVE");

        testCommon2(reqMap, resColumnList,null);
    }

    // 一元购电商平台
    public void test3059() throws Exception{
        LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "3059");
        reqMap.put("BANKCODE", "30040000");
        reqMap.put("TRXDATE", "20150703");
        reqMap.put("TRXTIME", "101000");
        reqMap.put("COINSTCODE", "000001");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150713163000000002");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");
        reqMap.put("CARDNBR", "1111111111111111111");
        reqMap.put("CURRENCY", "123");
        reqMap.put("AMOUNT", "100");
        reqMap.put("ORI_PINFLAG", "0");
        reqMap.put("ORI_DATE", "");
        reqMap.put("ORI_TIME", "");
        reqMap.put("ORI_SEQNO", "");
        reqMap.put("OUTSIDEID", "");
        reqMap.put("CONSUME_TYPE", "00");
        reqMap.put("CANCEL_TYPE", "00");
        reqMap.put("TXNDESC", "");
        reqMap.put("RESERVE", "");


        ArrayList<String> resColumnList = new ArrayList();
        resColumnList.add("CARDNBR");
        resColumnList.add("CLRDATE");
        resColumnList.add("RESERVE");

        testCommon2(reqMap, resColumnList,null);
    }

    // 学费代扣接口
    public void test3205() throws Exception{
    	LinkedHashMap reqMap = new LinkedHashMap();
        reqMap.put("TRXCODE", "3025");
        reqMap.put("BANKCODE", "30040000");
        reqMap.put("TRXDATE", "20161102");
        reqMap.put("TRXTIME", "101000");
        reqMap.put("COINSTCODE", "000047");
        reqMap.put("COINSTCHANNEL","000002");
        reqMap.put("SEQNO","20150713163000000002");
        reqMap.put("SOURCE", "P3");
        reqMap.put("RETCODE", "");
        reqMap.put("RETMSG", "");
        reqMap.put("HEADRESERVED", "");
        reqMap.put("ACCT_NO", "6222222222222222");
        reqMap.put("CURRENCY", "156");
        reqMap.put("AMOUNT", "100");
        reqMap.put("KEYTYPE", "01");
        reqMap.put("IDNO", "340827198501010012");
        reqMap.put("SURNAME", "小明");
        reqMap.put("MOBILE", "17775339631");
        reqMap.put("ORDER_ID", "20161102094200001");
        reqMap.put("PAYITEMNAME", "安医大学费代缴");
        reqMap.put("RESERVED", "");


        ArrayList<String> resColumnList = new ArrayList();
        resColumnList.add("ACCT_NO");
        resColumnList.add("CURRENCY");
        resColumnList.add("AMOUNT");
        resColumnList.add("RESERVED");

        testCommon2(reqMap, resColumnList,null);
    }



    public void testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList,String code) throws  Exception{
    	testCommon2(reqMap, resColumnList,code,"");
    }

    //公共方法调用2
    public void testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList,String code,String version) throws  Exception
    {
        String requestMapMerged = mergeMap(reqMap);
        Map map=new HashMap();
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
        String md5encryptionPath="D:/DEMO/30040000/000047/encryptions_.cer";
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
        Map jsonMap= JSONObject.fromObject(response);
        String resultData=jsonMap.get("responseData").toString();
        //String result=RSAUtil2.decryptRSA(resultData,getSignPrivateKey4Client(decryptPath));
        //解密调整
        String decryptKeyPath="D:/DEMO/30040000/000047/decryptions_.key";	//拼接解密私钥路径
        String decryptKey4Server=RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        String result=RSAUtils.decryptRSAs(resultData,decryptKey4Server);//对请求数据解密

        HashMap responseMap=(HashMap)parseJSON2Map(result);
        System.out.println("responseMap："+responseMap);
        StringBuffer responseMapMerged = new StringBuffer();
        ArrayList headColumn = new ArrayList();

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
                if(code.equals("5620"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("CARDNBR"));
                    responseMapMerged.append(jsonObj.get("MAFLAG"));
                    responseMapMerged.append(jsonObj.get("OPENDATE"));
                    responseMapMerged.append(jsonObj.get("EMBNAME"));
                    responseMapMerged.append(jsonObj.get("CD_STAT"));
                    responseMapMerged.append(jsonObj.get("FRZ_STAT"));
                    responseMapMerged.append(jsonObj.get("PINLOS_CD"));
                    responseMapMerged.append(jsonObj.get("OPEN_BRNO"));
                    responseMapMerged.append(jsonObj.get("PRO_DESC"));
                }else if (code.equals("5631"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("UPDATADATE"));
                    responseMapMerged.append(jsonObj.get("YIELD"));
                    responseMapMerged.append(jsonObj.get("YIELD_SIGN"));
                    responseMapMerged.append(jsonObj.get("FUNDINCOME"));
                    responseMapMerged.append(jsonObj.get("FUNDINC_SIGN"));
                }else if(code.equals("5800"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("CARDNBR"));
                    responseMapMerged.append(jsonObj.get("OPENDATE"));
                    responseMapMerged.append(jsonObj.get("CD_STAT"));
                    responseMapMerged.append(jsonObj.get("FRZ_STAT"));
                    responseMapMerged.append(jsonObj.get("PINLOS_CD"));
                    responseMapMerged.append(jsonObj.get("PRODUCT"));
                    responseMapMerged.append(jsonObj.get("PRO_DESC"));
                }else if(code.equals("5801"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("HS_VALD"));
                    responseMapMerged.append(jsonObj.get("HS_INPD"));
                    responseMapMerged.append(jsonObj.get("HS_RELD"));
                    responseMapMerged.append(jsonObj.get("HS_INPT"));
                    responseMapMerged.append(jsonObj.get("HS_TRNN"));
                    responseMapMerged.append(jsonObj.get("TRN_CARD"));
                    responseMapMerged.append(jsonObj.get("TRANTYPE"));
                    responseMapMerged.append(jsonObj.get("O_R_FLAG"));
                    responseMapMerged.append(jsonObj.get("BILL_AMT"));
                    responseMapMerged.append(jsonObj.get("BILL_AMS"));
                    responseMapMerged.append(jsonObj.get("DESLINE1"));
                    responseMapMerged.append(jsonObj.get("CUR_NUM"));
                    responseMapMerged.append(jsonObj.get("CURR_BAL"));
                    responseMapMerged.append(jsonObj.get("FORCARDNBR"));
                }else if(code.equals("5809"))
                {
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
                }else if(code.equals("5814"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("SIG_CARD"));
                    responseMapMerged.append(jsonObj.get("TXNDATE"));
                    responseMapMerged.append(jsonObj.get("TXNTIME"));
                    responseMapMerged.append(jsonObj.get("CANCLDATE"));
                    responseMapMerged.append(jsonObj.get("CANCLTIME"));
                }else if(code.equals("5815"))
                {

                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("FINAN_TYP"));
                    responseMapMerged.append(jsonObj.get("FINAN_CODE"));
                    responseMapMerged.append(jsonObj.get("LAST_INCOME"));
                    responseMapMerged.append(jsonObj.get("LAST_OPDATE"));
                    responseMapMerged.append(jsonObj.get("LAST_EXINCOME"));
                    responseMapMerged.append(jsonObj.get("LAST_INTRATE"));
                    responseMapMerged.append(jsonObj.get("INCOME7"));
                    responseMapMerged.append(jsonObj.get("INCOME30"));
                    responseMapMerged.append(jsonObj.get("INCOME"));
                    responseMapMerged.append(jsonObj.get("LAST_RANK"));
                    responseMapMerged.append(jsonObj.get("INVESTOR_QTY"));
                }else if(code.equals("5819"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("SERI_NO"));
                    responseMapMerged.append(jsonObj.get("CARDNBRI"));
                    responseMapMerged.append(jsonObj.get("NAMEI"));
                    responseMapMerged.append(jsonObj.get("BUYDATE"));
                    responseMapMerged.append(jsonObj.get("AMOUNT"));
                    responseMapMerged.append(jsonObj.get("TRPRICE"));
                    responseMapMerged.append(jsonObj.get("FEE"));
                    responseMapMerged.append(jsonObj.get("INCOME"));
                }else if(code.equals("5826"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("CARDNBR"));
                    responseMapMerged.append(jsonObj.get("LOANTYPE"));
                    responseMapMerged.append(jsonObj.get("LOANLIMIT"));
                    responseMapMerged.append(jsonObj.get("EXPDATE"));
                    responseMapMerged.append(jsonObj.get("OPENDATE"));

                }else if(code.equals("5829"))
                {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("ORDERNO"));
                    responseMapMerged.append(jsonObj.get("STATE"));
                    responseMapMerged.append(jsonObj.get("PROD"));
                    responseMapMerged.append(jsonObj.get("ACCO_SEQ"));
                    responseMapMerged.append(jsonObj.get("DATE"));
                    responseMapMerged.append(jsonObj.get("OPENTIME"));
                    responseMapMerged.append(jsonObj.get("RELDATE"));
                    responseMapMerged.append(jsonObj.get("INTDATE"));
                    responseMapMerged.append(jsonObj.get("ENDDATE"));
                    responseMapMerged.append(jsonObj.get("SUBDATE"));
                    responseMapMerged.append(jsonObj.get("INTRATE"));
                    responseMapMerged.append(jsonObj.get("INTRATEO"));
                    responseMapMerged.append(jsonObj.get("FEERATE"));
                    responseMapMerged.append(jsonObj.get("FEERATEO"));
                    responseMapMerged.append(jsonObj.get("OPEN_AMT"));
                    responseMapMerged.append(jsonObj.get("PAIDAMT"));
                    responseMapMerged.append(jsonObj.get("INTGET"));
                    responseMapMerged.append(jsonObj.get("INTGET_O"));
                    responseMapMerged.append(jsonObj.get("FEEGET"));
                    responseMapMerged.append(jsonObj.get("FEEGET_O"));
                }else if(code.equals("5849"))
                {
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
            responseMapMerged.append(responseMap.get(resColumnList.get(listLength-1)));
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

    private  static String mergeMap(Map map){

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
        try {
            //InputStream inputStream = Thread.currentThread().getContextClassLoader()
            //       .getResourceAsStream(signPrivatePath);
            FileInputStream fi=new FileInputStream(keyPath);
            InputStreamReader inputReader = new InputStreamReader(fi);
            BufferedReader bufferReader = new BufferedReader(inputReader);
            // 读取一行
            String line = "";
            while ((line=bufferReader.readLine())!=null) {
                privateBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements TrustManager,
            X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }


        public void checkServerTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }
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
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
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



    public static void main(String[] args) throws Exception {
        String request="zhuliang";
        //签名
        String sign1 = RSAEncryptUtil.MD5WithRSASign(request.getBytes("UTF-8"), getSignPrivateKey4Client(signPublicPath));
        System.out.println(sign1);
    }

}