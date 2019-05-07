package com.huishang.test;

import com.cupdata.zicon.util.RSAEncryptUtil;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.web.util.StringUtil;
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
 * Created by Administrator on 2017/4/10.
 */
public class Test_TerdenItem {
    static String uri = "http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt";
//    static String uri = "http://localhost:8080/dbesb/api/requestEncrypt";

    //加签
    private static String signPrivatePath = HSignUtil.SIGNPRIVATEPATH;
    //加密公钥
    private static String encryptPath = HSignUtil.ENCRYPTPATH;
    //解密
    private static String decryptPath = HSignUtil.DECRYPTPATH;
    //验签
    private static String signPublicPath = HSignUtil.SIGNPUBLICPATH;


    //P2P理财产品信息录入(未上线)

    /**
     * @param biaoHao:输入标号
     * @param biaoDec：输入标的描述
     * @param money：输入标的金额保留二位数
     * @throws Exception
     */
    public void test5846(String biaoHao, String biaoDec, String money, String date, String time) throws Exception {
//        Date date = new Date();
//        String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
//        String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
        String SEQNO = StringUtil.getNo();//交易流水号:年月日时分秒生成的随机数
        LinkedHashMap reqMap = new LinkedHashMap();
        //1-11域请求报文头
        reqMap.put("TRXCODE", "5846");                  //交易代码	    TRXCODE       N   M  4位交易代码
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);     //银行代码	    BANKCODE      N   M  30040000-徽商银行  30050000-南昌银行
        reqMap.put("TRXDATE", date);                 //交易日期	    TRXDATE       N   M  YYYYMMDD
        reqMap.put("TRXTIME", time);                 //交易时间	    TRXTIME       N   M  hhmmss
       /* reqMap.put("COINSTCODE", HSignUtil.COINSTCODE);*/ //合作单位编号	COINSTCODE    A   M  银联数据分配  800114
        reqMap.put("COINSTCODE", "800114"); //合作单位编号	COINSTCODE    A   M  银联数据分配  800114
        reqMap.put("COINSTCHANNEL", "000002");          //合作单位渠道	COINSTCHANNEL A   M  见下方《附录1》
        reqMap.put("SEQNO", SEQNO);                     //交易流水号	SEQNO         N   M  8 ~ 20 位数字流水号

        reqMap.put("SOURCE", "A0");                     //ESB内部渠道	SOURCE        A  M  银联数据分配
        reqMap.put("RETCODE", "");                      //应答码	    RETCODE       A     填空
        reqMap.put("RETMSG", "");                       //应答码描述	RETMSG        A     填空
        reqMap.put("HEADRESERVED", "");                 //报文头保留域	HEADRESERVED  A  C  可选


        //请求所需要的参数
        reqMap.put("FUPROD", biaoHao);                     //标的编号          A 6     M  由产品的发行方定义；需保证唯一性
        reqMap.put("PROD_DESC", biaoDec);              //产品描述          A 60    M  理财产品中文描述
        reqMap.put("CARDNBR", "9930040050240600045");       //借款人电子账号    A 19    M
        reqMap.put("AMOUNT", money);                   //借款金额          N 13,2  M  两位小数
        reqMap.put("INTTYPE", "0");                         //付息方式          N 1     M  0：到期与本金一起归还；1：每月固定日期支付；2：每月不确定日期支付；
//        reqMap.put("INTPAYDAY", "");                        //利息每月支付日    N 2     C  DD付息方式为1时必填；若设置日期大于月份最后一天时，则为该月最后一天支付；
        reqMap.put("LOAN_TERM", "20");                      //项目期限          N 4     M  单位为天
        reqMap.put("YIELD", "1.00000");                     //预期年化收益率    N 8,5   M  五位小数，如年化收益率为10%，需上送1,000,000
//        reqMap.put("CARDNBR_SU", "");                       //担保人电子账号    A 19    C
//        reqMap.put("CARDNBR_MY", "");                       //名义借款人电子帐户A 19    C  名义借款人电子账号
//        reqMap.put("CARDNBR_RE", "");                       //收款人电子帐户    A 19    C  多种借款人模式下必送
//        reqMap.put("ENTRUST_YN", "");                       //受托支付标志      N 1     C  0：非受托支付业务类别  1：受托支付业务类别
//        reqMap.put("RESERVED", "");                         //保留域            A 161   C  允许为空
//        reqMap.put("TRDRESV", "");                          //第三方平台保留域  A 100   C  第三方平台保留使用，原样返回

        //响应报文参数如下：  用于与1-11域响应报文头拼接的响应报文参数名：用于验签
        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("FUPROD");        //标的编号              A  6     C   同请求
        resColumnList.add("PROD_DESC");     //产品描述              A  60    C   同请求
        resColumnList.add("CARDNBR");       //借款人电子账号        A  19    C   同请求
        resColumnList.add("NAME");          //借款人姓名            A  60    C   借款人为企业机构时返回企业名称
        resColumnList.add("AMOUNT");        //借款金额              N  13,2  C   同请求
        resColumnList.add("LOAN_TERM");     //项目期限              N  4     C   同请求
        resColumnList.add("INPDATE");       //发标日期              N  8     C   CCYYMMDD  标的信息录入时的系统日期
        resColumnList.add("STATE");         //记录状态              N  1     C   1：已发标    2：已投标   9：已撤销
//        resColumnList.add("CARDNBR_SU");    //担保人电子账号        A  19    C   同请求
//        resColumnList.add("NAME_SU");       //担保人姓名            A  60    C   担保人为企业机构时返回企业名称
//        resColumnList.add("CARDNBR_MY");    //名义借款人电子账号    A  19    C   同请求
//        resColumnList.add("CARDNBR_RE");    //收款人电子账号        A  19    C   同请求
//        resColumnList.add("RESERVED");      //保留域                A  362   C
//        resColumnList.add("TRDRESV");       //第三方平台保留域      A  100   C   第三方平台保留使用，原样返回


        testCommon2(reqMap, resColumnList, "5846");
    }

    //P2P理财产品信息撤销(未上线)
    public void test5847(String biaoHao, String cardnbr, String money) throws Exception {
        Date date = new Date();
        String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
        String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
        String SEQNO = StringUtil.getNo();//交易流水号:年月日时分秒生成的随机数
        LinkedHashMap reqMap = new LinkedHashMap();
        //1-11域请求报文头
        reqMap.put("TRXCODE", "5847");                  //交易代码	    TRXCODE         M  4位交易代码
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);     //银行代码	    BANKCODE        M  30040000-徽商银行  30050000-南昌银行
        reqMap.put("TRXDATE", TRXDATE);                 //交易日期	    TRXDATE         M  YYYYMMDD
        reqMap.put("TRXTIME", TRXTIME);                 //交易时间	    TRXTIME         M  hhmmss
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); //合作单位编号	COINSTCODE      M  银联数据分配  800114
        reqMap.put("COINSTCHANNEL", "000002");          //合作单位渠道	COINSTCHANNEL   M  见下方《附录1》
        reqMap.put("SEQNO", SEQNO);                     //交易流水号	SEQNO           M  8 ~ 20 位数字流水号

        reqMap.put("SOURCE", "A0");                     //ESB内部渠道	SOURCE          M  银联数据分配
        reqMap.put("RETCODE", "");                      //应答码	    RETCODE            填空
        reqMap.put("RETMSG", "");                       //应答码描述	RETMSG             填空
        reqMap.put("HEADRESERVED", "");                 //报文头保留域	HEADRESERVED   C   可选


        //请求所需要的参数
        reqMap.put("FUPROD", biaoHao);                  //标的编号          FUPROD     A  6     M   需要撤销的标的信息
        reqMap.put("CARDNBR", cardnbr);    //借款人电子账号    CARDNBR    A  19    M
        reqMap.put("AMOUNT", money);                //借款金额          AMOUNT     N  13,2  M   两位小数
        reqMap.put("RESERVED", "");                      //保留域            RESERVED   A  200   C   允许为空
        reqMap.put("TRDRESV", "");                       //第三方保留域      TRDRESV    A  100   C   供第三方平台使用，原样返回

        //响应报文参数如下：  用于与1-11域响应报文头拼接的响应报文参数名：用于验签
        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("FUPROD");                //标的编号          A  6     M  同请求
        resColumnList.add("CARDNBR");               //借款人电子账号    A  19    M  同请求
        resColumnList.add("NAME");                  //借款人姓名        A  60    C
        resColumnList.add("AMOUNT");                //借款金额          N  13,2  C  同请求
        resColumnList.add("INPDATE");               //发标日期          N  8     C  CCYYMMDD标的信息录入时的系统日期
        resColumnList.add("STATE");                 //记录状态          N  1     C  1：已发标   2：已投标  9：已撤销
        resColumnList.add("CARDNBR_SU");            //担保人电子账号    A  19    C
        resColumnList.add("NAME_SU");               //担保人姓名        A  60    C
        resColumnList.add("RESERVED");              //保留域            A  400   C
        resColumnList.add("TRDRESV");               //第三方保留域      A  100   C  供第三方平台使用，原样返回

        testCommon2(reqMap, resColumnList, null);
    }

    //P2P理财产品信息维护单笔查询(未上线)
    public void test5848(String biaoHao) throws Exception {
        Date date = new Date();
        String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
        String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
        String SEQNO = StringUtil.getNo();//交易流水号:年月日时分秒生成的随机数
        LinkedHashMap reqMap = new LinkedHashMap();
        //5848:1-11域请求报文头
        reqMap.put("TRXCODE", "5848");                  //交易代码	    TRXCODE         M  4位交易代码
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);     //银行代码	    BANKCODE        M  30040000-徽商银行  30050000-南昌银行
        reqMap.put("TRXDATE", TRXDATE);                 //交易日期	    TRXDATE         M  YYYYMMDD
        reqMap.put("TRXTIME", TRXTIME);                 //交易时间	    TRXTIME         M  hhmmss
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); //合作单位编号	COINSTCODE      M  银联数据分配  800114
        reqMap.put("COINSTCHANNEL", HSignUtil.COINSTCHANNEL_WEB);          //合作单位渠道	COINSTCHANNEL   M  见下方《附录1》
        reqMap.put("SEQNO", SEQNO);                     //交易流水号	SEQNO           M  8 ~ 20 位数字流水号

        reqMap.put("SOURCE", "A0");                     //ESB内部渠道	SOURCE          M  银联数据分配
        reqMap.put("RETCODE", "");                      //应答码	    RETCODE            填空
        reqMap.put("RETMSG", "");                       //应答码描述	RETMSG             填空
        reqMap.put("HEADRESERVED", "");                 //报文头保留域	HEADRESERVED    C   可选
        //请求所需要的参数
        reqMap.put("FUPROD", biaoHao);                        //标的编号      FUPROD          A 6 M   填入需要查询的标的编号
//        reqMap.put("RESERVED","");                      //保留域        RESERVED        A 200 C  允许为空
//        reqMap.put("TRDRESV","");                       //第三方保留域  TRDRESV         A 100 C  供第三方平台使用，原样返回

        //响应报文参数如下：  用于与1-11域响应报文头拼接的响应报文参数名：用于验签
        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("FUPROD");            //标的编号                A  6  C     同请求
        resColumnList.add("PROD_DESC");         //产品描述                A  60  C
        resColumnList.add("CARDNBR");           //借款人电子账号          A  19  C
        resColumnList.add("NAME");              //借款人姓名              A  60  C   借款人为企业机构时返回企业名称
        resColumnList.add("AMOUNT");            //借款金额                N  13,2  C
        resColumnList.add("LOAN_TERM");         //项目期限                N  4  C  单位为天数
        resColumnList.add("INPDATE");           //发标日期                N  8  C   CCYYMMDD标的信息录入时的系统日期
        resColumnList.add("STATE");             //记录状态                N  1  C   1：已发标  2：已投标  9：已撤销
        resColumnList.add("CARDNBR_SU");        //担保人电子帐号          A  19  C
        resColumnList.add("NAME_SU");           //担保人姓名              A  60  C  担保人为企业机构时返回企业名称
        resColumnList.add("CARDNBR_MY");        //名义借款人电子账号      A  19  C  名义借款人电子账号
        resColumnList.add("RESERVED");          //保留域                  A  381  C
        resColumnList.add("TRDRESV");           //第三方保留域            A  100  C  供第三方平台使用，原样返回

        testCommon2(reqMap, resColumnList, "5848");
    }

    //P2P理财产品信息维护多笔查询(未上线)    P2P标的信息多笔查询
    public List<Map> test5849(String biaozi, String lastdate, String lasttime, String lastitemno) throws Exception {
        Date date = new Date();
        String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
        String TRXTIME = StringUtil.formatDate(date, "hhmmss");//交易时间	TRXTIME
        String SEQNO = StringUtil.getNo();//交易流水号:年月日时分秒生成的随机数
        LinkedHashMap reqMap = new LinkedHashMap();
        //1-11域请求报文头
        reqMap.put("TRXCODE", "5849");                  //交易代码	    TRXCODE       N   M  4位交易代码
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);     //银行代码	    BANKCODE      N   M  30040000-徽商银行  30050000-南昌银行
        reqMap.put("TRXDATE", TRXDATE);                 //交易日期	    TRXDATE       N   M  YYYYMMDD
        reqMap.put("TRXTIME", TRXTIME);                 //交易时间	    TRXTIME       N   M  hhmmss
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); //合作单位编号	COINSTCODE    A   M  银联数据分配  800114
        reqMap.put("COINSTCHANNEL", "000002");          //合作单位渠道	COINSTCHANNEL A   M  见下方《附录1》
        reqMap.put("SEQNO", SEQNO);                     //交易流水号	SEQNO         N   M  8 ~ 20 位数字流水号

        reqMap.put("SOURCE", "A0");                     //ESB内部渠道	SOURCE        A  M  银联数据分配
        reqMap.put("RETCODE", "");                      //应答码	    RETCODE       A     填空
        reqMap.put("RETMSG", "");                       //应答码描述	RETMSG        A     填空
        reqMap.put("HEADRESERVED", "");                 //报文头保留域	HEADRESERVED  A  C  可选


        //请求所需要的参数
        reqMap.put("STARTDATE", "20170201");    //起始日期      N  8   C     为空时，查询起始日期为系统当前日期
        reqMap.put("ENDDATE", "20170812");       //结束日期      N  8   C     为空时，查询结束日期为系统当前日期
        reqMap.put("RTN_IND", biaozi);              //翻页标志      A  1   M     首次查询上送空；翻页查询上送1；
        reqMap.put("INPDATE", lastdate);              //发标日期      N  8   C     翻页控制使用；首次查询上送空；翻页查询时上送上页返回的最后一条记录的发标日期；
        reqMap.put("INPDTIME", lasttime);             //发标时间      N  6   C     翻页控制使用；首次查询上送空；翻页查询时上送上页返回的最后一条记录的发标时间；
        reqMap.put("FUPROD", lastitemno);               //标的编号      A  6   C     翻页控制使用；首次查询上送空；翻页查询时上送上页返回的最后一条记录的标的编号；
        reqMap.put("RESERVED", "");             //保留域        A  200 C     允许为空
        reqMap.put("TRDRESV", "");              //第三方保留域  A  100 C     供第三方使用，允许返回空


        //响应报文参数如下：  用于与1-11域响应报文头拼接的响应报文参数名：用于验签
        ArrayList<String> resColumnList = new ArrayList();

        resColumnList.add("COMREV");        //公共保留域             A  100  C
        resColumnList.add("CURRNO");        //本次返回交易条数       N  2    M
        resColumnList.add("RTN_IND");       //翻页标志               A  1    C  0：查询完毕；1：需继续翻页查询；
        resColumnList.add("FUPROD");        //标的编号               A  6    C
        resColumnList.add("PROD_DESC");     //产品描述               A  60   C
        resColumnList.add("CARDNBR");       //借款人电子账号         A  19   C
        resColumnList.add("NAME");          //借款人姓名             A  60   C
        resColumnList.add("AMOUNT");        //借款金额               N  13,2 C  两位小数
        resColumnList.add("LOAN_TERM");     //项目期限               N  4    C
        resColumnList.add("INPDATE");       //发标日期               N  8    C
        resColumnList.add("INPTIME");       //发标时间               N  6    C
        resColumnList.add("STATE");         //状态                   N  1    C 1：已发标  2：已投标  9：已撤销
        resColumnList.add("CARDNBR_SU");    //担保人电子账号         A  19   C
        resColumnList.add("NAME_SU");       //担保人姓名             A  60   C
        resColumnList.add("CARDNBR_MY");    //名义借款人电子账号     A  19   C 名义借款人电子账号
        resColumnList.add("RESERVED");      //保留域                 A  1    C
        //[循环域说明]：15- 27重复 5 次
        resColumnList.add("TRDRESV");       //第三方保留域           A  100  C

        return testCommon2(reqMap, resColumnList, "5849");
    }

    public List<Map> testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList, String code) throws Exception {
        return testCommon2(reqMap, resColumnList, code, "v20160602");//v20161215/v20160602
    }

    //公共方法调用2
    public List<Map> testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList, String code, String version) throws Exception {
        //reqMap是提交的所有参数
        // 把map中所有非SIGN作为key的value都拼接成字符串
        String requestMapMerged = mergeMap(reqMap);
        Map map = new HashMap();
        map.put("BANKCODE", reqMap.get("BANKCODE"));
        map.put("COINSTCODE", reqMap.get("COINSTCODE"));
        map.put("APIVERSION", version);

        //加签
        String sign1 = RSAEncryptUtil.MD5WithRSASign(requestMapMerged.getBytes("UTF-8"), getSignPrivateKey4Client(signPrivatePath));
        System.out.println("加签成功.........");
        reqMap.put("SIGN", sign1);
        JSONObject jsMap = JSONObject.fromObject(reqMap);
        System.out.println("加签后的数据：" + jsMap);
        //String data=RSAUtil2.encryptRSA(jsMap.toString(), getVerifyKey4Client(encryptPath));

        //加密
        String js = jsMap.toString();
        String md5encryptionPath = encryptPath;
        String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath);    //获取加密公钥字符串
        String data = RSAUtils.encryptRSAByte(js.getBytes("UTF-8"), encryptionKey4Server);//进行摘要并对摘要进行加密
        System.out.println("加密成功.........");

        map.put("reqMap", data);
        HttpClient httpClient;
        PostMethod method;
        httpClient = new HttpClient();
        JSONObject reqdata = JSONObject.fromObject(map);
        System.out.println("发送请求至：" + uri);
        method = new PostMethod(uri);
        method.setParameter("reqdata", reqdata.toString());
        httpClient.executeMethod(method);
        String response = method.getResponseBodyAsString();
        System.out.println("徽商返回的原始结果：" + response);
        Map jsonMap = JSONObject.fromObject(response);
        System.out.println("徽商返回的原始结果转为map格式：" + jsonMap);
        String resultData = jsonMap.get("responseData").toString();
        //String result=RSAUtil2.decryptRSA(resultData,getSignPrivateKey4Client(decryptPath));
        System.out.println("徽商返回的原始结果转为map格式中get出responseData的内容：" + resultData);

        //解密调整
        String decryptKeyPath = decryptPath;    //拼接解密私钥路径
        String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        String result = RSAUtils.decryptRSAs(resultData, decryptKey4Server);//对请求数据解密
        System.out.println("解密成功..........");
        System.out.println("解密后的内容：" + result);


        HashMap responseMap = (HashMap) parseJSON2Map(result);
        System.out.println("解密后的内容转为map格式后内容如下：" + responseMap);
        StringBuffer responseMapMerged = new StringBuffer();
        ArrayList headColumn = new ArrayList();

        //1-11域响应报文头
        headColumn.add("TRXCODE");          //交易代码      M  与请求一致
        headColumn.add("BANKCODE");         //银行代码      M  与请求一致
        headColumn.add("TRXDATE");          //交易日期      M  与请求一致
        headColumn.add("TRXTIME");          //交易时间      M  与请求一致
        headColumn.add("COINSTCODE");       //合作单位编号  M  与请求一致
        headColumn.add("COINSTCHANNEL");    //合作单位渠道  M  与请求一致
        headColumn.add("SEQNO");            //交易流水号    M  与请求一致

        headColumn.add("SOURCE");           //ESB内部渠道	M  与请求一致
        headColumn.add("RETCODE");          //应答码	    M
        headColumn.add("RETMSG");           //应答码描述	C
        headColumn.add("HEADRESERVED");     //报文头保留域	C

        resColumnList.addAll(0, headColumn);
        int listLength = resColumnList.size();
        if (responseMap.get("RETCODE") == null) {
            System.out.println("操作失败:" + responseMap.get("RETMSG"));
            throw new ArrayIndexOutOfBoundsException("请求参数非法");
            //InvocationTargetException
        }
        JSONObject json = JSONObject.fromObject(responseMap);
        //for循环次数
        int forLen = 0;
        //如果是5849交易
        if ("5849".equals(responseMap.get(resColumnList.get(0)))) {
            forLen = listLength - 1;
        } else {
            forLen = listLength;
        }
        for (int i = 0; i < forLen; i++) {
            Object columnvalue = responseMap.get(resColumnList.get(i));
            if (columnvalue != null) {
                responseMapMerged.append(responseMap.get(resColumnList.get(i)));
            }
        }

        if (json.get("SUBPACKS") != null) {
            JSONArray SUBPACKS = (JSONArray) json.get("SUBPACKS");
            System.out.println("SUBPACKS:" + SUBPACKS);
            int Size = SUBPACKS.size();

            for (int i = 0; i < Size; i++) {
                if (code.equals("5620")) {
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
                } else if (code.equals("5631")) {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("UPDATADATE"));
                    responseMapMerged.append(jsonObj.get("YIELD"));
                    responseMapMerged.append(jsonObj.get("YIELD_SIGN"));
                    responseMapMerged.append(jsonObj.get("FUNDINCOME"));
                    responseMapMerged.append(jsonObj.get("FUNDINC_SIGN"));
                } else if (code.equals("5800")) {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("CARDNBR"));
                    responseMapMerged.append(jsonObj.get("OPENDATE"));
                    responseMapMerged.append(jsonObj.get("CD_STAT"));
                    responseMapMerged.append(jsonObj.get("FRZ_STAT"));
                    responseMapMerged.append(jsonObj.get("PINLOS_CD"));
                    responseMapMerged.append(jsonObj.get("PRODUCT"));
                    responseMapMerged.append(jsonObj.get("PRO_DESC"));
                } else if (code.equals("5801")) {
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
                } else if (code.equals("5809")) {
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
                } else if (code.equals("5814")) {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("SIG_CARD"));
                    responseMapMerged.append(jsonObj.get("TXNDATE"));
                    responseMapMerged.append(jsonObj.get("TXNTIME"));
                    responseMapMerged.append(jsonObj.get("CANCLDATE"));
                    responseMapMerged.append(jsonObj.get("CANCLTIME"));
                } else if (code.equals("5815")) {

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
                } else if (code.equals("5819")) {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("SERI_NO"));
                    responseMapMerged.append(jsonObj.get("CARDNBRI"));
                    responseMapMerged.append(jsonObj.get("NAMEI"));
                    responseMapMerged.append(jsonObj.get("BUYDATE"));
                    responseMapMerged.append(jsonObj.get("AMOUNT"));
                    responseMapMerged.append(jsonObj.get("TRPRICE"));
                    responseMapMerged.append(jsonObj.get("FEE"));
                    responseMapMerged.append(jsonObj.get("INCOME"));
                } else if (code.equals("5826")) {
                    JSONObject jsonObj = SUBPACKS.getJSONObject(i);
                    responseMapMerged.append(jsonObj.get("CARDNBR"));
                    responseMapMerged.append(jsonObj.get("LOANTYPE"));
                    responseMapMerged.append(jsonObj.get("LOANLIMIT"));
                    responseMapMerged.append(jsonObj.get("EXPDATE"));
                    responseMapMerged.append(jsonObj.get("OPENDATE"));

                } else if (code.equals("5829")) {
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
                } else if (code.equals("5849")) {
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
            responseMapMerged.append(responseMap.get(resColumnList.get(listLength - 1)));
        }


        //验签
        String responseSign = (String) responseMap.get("SIGN");

        System.out.println("responseMerged: " + responseMapMerged.toString());

        boolean verifyResult = RSAEncryptUtil.MD5WithRSAVerify(responseMapMerged.toString().getBytes("UTF-8"), getVerifyKey4Client(signPublicPath), responseSign);
        System.out.println("验签结果：" + verifyResult);
        if (!verifyResult) {
            System.out.println("验证签名失败...");
            return null;
        } else {
            System.out.println("验证签名成功");
        }
        System.out.println("responseMap:" + responseMap);
        System.out.println("STATE:" + responseMap.get("STATE"));
        if ("00000000".equals(responseMap.get("RETCODE"))) {
            System.out.println("操作成功");
            List<Map> subpacks = (List<Map>) responseMap.get("SUBPACKS");

//            test5849(String biaozi,String lastdate,String lasttime,String lastitemno)
            if(subpacks!=null&&subpacks.size()!=0){

                return subpacks;
            }else{
                return null;
            }
            /*String lastdate = (String) obj.get("INPDATE");
            String lasttime = (String) obj.get("INPTIME");
            String lastitemno = (String) obj.get("CARDNBR");
            System.out.println(lastdate);
            System.out.println(lasttime);
            System.out.println(lastitemno);*/
        } else {
            System.out.println("操作失败,错误代码：" + responseMap.get("RETCODE"));
            return null;
        }
    }

    //把key不是sign的value拼在一起
    private static String mergeMap(Map map) {

        String requestMerged = "";
        StringBuffer buff = new StringBuffer();

        Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();

        Map.Entry<String, String> entry;

        while (iter.hasNext()) {

            entry = iter.next();
            System.out.print(entry.getKey() + ", ");
            if (!"SIGN".equals(entry.getKey())) {
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
     *
     * @param
     * @return
     */
    public static String getSignPrivateKey4Client(String keyPath) {
        StringBuffer privateBuffer = new StringBuffer();
        try {
            //InputStream inputStream = Thread.currentThread().getContextClassLoader()
            //       .getResourceAsStream(signPrivatePath);
            FileInputStream fi = new FileInputStream(keyPath);
            InputStreamReader inputReader = new InputStreamReader(fi);
            BufferedReader bufferReader = new BufferedReader(inputReader);
            // 读取一行
            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                privateBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateBuffer.toString();
    }

    /**
     * 根据公钥文件路径读取私钥
     *
     * @param
     * @return
     */
    public static String getVerifyKey4Client(String keyPath) {
        String key = "";
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            //InputStream in = Thread.currentThread().getContextClassLoader()
            //      .getResourceAsStream(signPublicPath);
            FileInputStream fi = new FileInputStream(keyPath);
            //生成一个证书对象并使用从输入流 inStream 中读取的数据对它进行初始化。
            Certificate c = cf.generateCertificate(fi);
            PublicKey publicKey = c.getPublicKey();
            key = new BASE64Encoder().encodeBuffer(publicKey.getEncoded());
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
     * @param jsonStr 源数据
     *                <p>
     *                return map
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
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
        Test_TerdenItem tr = new Test_TerdenItem();
        //录入标
//         tr.test5846("bbbbb6","测试bbbbb6元","1000.00","20170422","000005");
        //标的信息维护单笔查询
        tr.test5848("GJofi5");
        //撤销标
//        tr.test5847("GJofi5","9930040290000650026","1000.00");
//        标的信息多笔查询
//        test5849(String biaozi,String lastdate,String lasttime,String lastitemno)

       /* List<Map> obj = tr.test5849("", "", "", "");
        Map obj1 = (Map) obj.get(obj.size()-1);

        String lastdate = (String) obj1.get("INPDATE");
        String lasttime = (String) obj1.get("INPTIME");
        String lastitemno = (String) obj1.get("FUPROD");
        System.out.println(lastdate);
        System.out.println(lasttime);
        System.out.println(lastitemno);
        List<Map> maps = new ArrayList<Map>();
        maps.addAll(obj);
        int times = 0;
        for (int i = 0; i < 10; i++) {
            List<Map> obj2 = tr.test5849("1", lastdate, lasttime, lastitemno);
            if(obj2!=null&&obj2.size()!=0){
                //最后一条记录
                Map obj3 = (Map) obj2.get(obj2.size()-1);
                //最后一条记录的发标日期
                lastdate = (String) obj3.get("INPDATE");
                //最后一条记录的发标时间
                lasttime = (String) obj3.get("INPTIME");
                //最后一条记录的标的编号
                lastitemno = (String) obj3.get("FUPROD");
                maps.addAll(obj2);
            }else{
                times=i;
                break;
            }
            System.out.println("iiiiiiiiiiiiiiiiiiiiii="+i);
        }
        System.out.println("times:"+times);
        System.out.println(maps);
*/

//        String request="zhuliang";
//        //签名
//        String sign1 = RSAEncryptUtil.MD5WithRSASign(request.getBytes("UTF-8"), getSignPrivateKey4Client(signPublicPath));
//        System.out.println(sign1);
    }
}
