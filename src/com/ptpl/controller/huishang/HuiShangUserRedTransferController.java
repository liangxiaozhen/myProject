package com.ptpl.controller.huishang;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.QueryBlaneServiceI;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

/**
 * 徽商银行现金红包转账（ptpjx）
 */
@Controller
@RequestMapping("/huishang/giro")
public class HuiShangUserRedTransferController extends BaseController{
	
	//String signPrivatePath = "D:"+File.separator+"wskey"+File.separator+800114+File.separator+"sign_.key";
	//public String signPrivatePath = HSignUtil.SIGNPRIVATEPATH;
	//String md5encryptionPath = "D:"+File.separator+"wskey"+File.separator+800114+File.separator+"encryptions_.cer";
	//String md5encryptionPath = HSignUtil.ENCRYPTPATH;
	//String uri = "https://onlineuat.cupdata.com:50005/dbesbsit/api/requestEncrypt";//测试地址
	//改为路由地址
	//String uri = "http://"+HSignUtil.getApiProps("BASEHOST")+/*HSignUtil.getApiProps("REDTRANSFER")*/"/ptproute/app/to/transferAccount.action";
	//String decryptKeyPath="D:"+File.separator+"wskey"+File.separator+800114+File.separator+"decryptions_.key";	//拼接解密私钥路径
	//String decryptKeyPath= HSignUtil.DECRYPTPATH;
	//String signPublicPath = "D:"+File.separator+"wskey"+File.separator+800114+File.separator+"verify_.cer";
	//String signPublicPath = HSignUtil.SIGNPUBLICPATH;
			
	@Autowired
	private ActivityAwardListServiceI activityAwardListService;//获奖名单表
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoService;//用户托管账户信息Service
	@Autowired
	private RedEnveLopeItemServiceI redEnveLopeItemService;//红包发放对账记录表
	@Autowired
	private UserAccountServiceI userAccountService;//用户账户表
	@Autowired
	private AccInExRecordServiceI accInExRecordService;//账户收支记录表
	@Autowired
	private UserRedEnvelopeServiceI userRedEnvelopeService;//用户红包Service
	@Autowired
	private AwardServiceI awardService;//奖品
	@Autowired
	private QueryBlaneServiceI queryBlaneService;//余额查询
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/redTransferAccounts")
	public synchronized void redTransferAccounts(HttpServletResponse response,BigDecimal id) throws Exception{
		
		//根据id获得对应的获奖名单
		//id = new BigDecimal(1901);
		System.out.println("====id===="+id);
		ActivityAwardList aal = activityAwardListService.getselectById(id);
		System.out.println("====aal==="+aal);
		Double amount = aal.getAwardmoney()*aal.getAwardquantity();//转账金额
		System.out.println("获奖名单的id："+id);
		System.out.println("转账金额："+amount);
		//用户的托管账号
		UserFsAccountInfo ufai=userFsAccountInfoService.findUserFsAccountInfoByBaseId(aal.getBaseid());
		//用户没有开通托管账户
		String jsonStr = null;
		if(ufai==null){
			jsonStr = JSON.toJSONString("false");
			try {
				StringUtil.sendJsonData(response, jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else if(ufai.getIsopenfsinfo()==0){
			jsonStr = JSON.toJSONString("false");
			try {
				StringUtil.sendJsonData(response, jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		//获取徽商红包账户
		UserFsAccountInfo u = new UserFsAccountInfo();
		u.setAccounttype((short)2);//账户类型  1.个人    2.企业
		u.setAccPurpose((short)2);//账户用途（1普通，2红包，3手续费）
		u.setMercustid(/*"800114"*/HSignUtil.COINSTCODE);//商户客户号
		UserFsAccountInfo userFsAccountInfo=userFsAccountInfoService.getUsrCustId(u);
		//根据托管账户的baseid查询用户账户表，查看余额是否足够
		UserAccount ua = userAccountService.getUserAccountByBaseId(userFsAccountInfo.getBaseid());
		if(ua.getAvlbalance()<amount){
			jsonStr = JSON.toJSONString("unEnough");
			try {
				StringUtil.sendJsonData(response, jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		LinkedHashMap reqMap = new LinkedHashMap();//保存了记录的插入顺序
		//报文头的请求参数
		reqMap.put("TRXCODE", "7603");//交易代码
		reqMap.put("BANKCODE", /*"30040000"*/HSignUtil.BANKCODE);//银行代码 徽商银行
		Date date = new Date();
		reqMap.put("TRXDATE", new SimpleDateFormat("yyyyMMdd").format(date));//交易日期
		reqMap.put("TRXTIME", new SimpleDateFormat("HHmmss").format(date));//交易时间
		reqMap.put("COINSTCODE", /*"800114"*/HSignUtil.COINSTCODE);//合作单位编号
		reqMap.put("COINSTCHANNEL", /*"000002"*/HSignUtil.COINSTCHANNEL_WEB);//合作单位渠道 网页
		reqMap.put("SEQNO", StringUtil.getNo());//交易流水号
		reqMap.put("SOURCE", /*"A0"*/HSignUtil.SOURCE);//ESB内部渠道
		reqMap.put("RETCODE", "");//应答码
		reqMap.put("RETMSG", "");//应答码描述
		reqMap.put("HEADRESERVED", id.toString().trim());//报文头保留域
		//请求报文
		reqMap.put("CARDNBR", getDecrypt(userFsAccountInfo.getUsrcustid())/*"9930040050240250015"*/);//转出电子账户 账户：徽商红包账户  密码：123456a 手机号：18617038010  春悦
		reqMap.put("CARDNBRIN",getDecrypt(ufai.getUsrcustid()));//转入电子账户   测试账户： 9930040050240300026
	    reqMap.put("CURRENCY", "156");//币种
	    reqMap.put("AMOUNT", amount.toString().trim());//金额
	    reqMap.put("DESLINEFLAG", "0");//是否使用交易描述
	    
	    ArrayList<String> resColumnList = new ArrayList();
        resColumnList.add("CARDNBR");
        resColumnList.add("CARDNBRIN");
        resColumnList.add("CURRENCY");
        resColumnList.add("AMOUNT");
        
        
        //平台账户余额
//		Map<String,String> r58633 = queryBlaneService.queryBlane("9930040050240250015");
//		String AVAIL_BAL3 = r58633.get("AVAIL_BAL");//可用余额
//		String CURR_BAL3 = r58633.get("CURR_BAL");//账户余额
//		System.out.println("AVAIL_BAL3: "+AVAIL_BAL3);
//		System.out.println("CURR_BAL3: "+CURR_BAL3);
		
		//收钱方的账户余额
//		Map<String,String> r58634 = queryBlaneService.queryBlane("9930040050240150033");
//		String AVAIL_BAL4 = r58634.get("AVAIL_BAL");//可用余额
//		String CURR_BAL4 = r58634.get("CURR_BAL");//账户余额
//		System.out.println("AVAIL_BAL4: "+AVAIL_BAL4);
//		System.out.println("CURR_BAL4: "+CURR_BAL4);
	    
        int line = testCommon2(reqMap, resColumnList,/*null,*/"v20160602");
        //String jsonStr = null;
        if(line>0){
        	jsonStr = JSON.toJSONString("success");
        }else{
        	jsonStr = JSON.toJSONString("fail");
        }
        StringUtil.sendJsonData(response, jsonStr);
	}
	
	//公共方法调用
	@SuppressWarnings("unchecked")
	public int testCommon2(LinkedHashMap<String,String> reqMap, ArrayList<String> resColumnList,/*String code,*/String version) throws UnsupportedEncodingException, Exception{
		
		/*String requestMapMerged = mergeMap(reqMap);
		//用私钥对信息生成数字签名
		String sign = RSAUtils.MD5WithRSASign(requestMapMerged.getBytes("UTF-8"),getSignPrivateKey4Client(signPrivatePathHSignUtil.SIGNPRIVATEPATH));
		reqMap.put("SIGN", sign);
		JSONObject jsMap = JSONObject.fromObject(reqMap);
		//根据公钥文件读取验签公钥
		String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPathHSignUtil.ENCRYPTPATH);
		//进行摘要并对摘要进行加密
		String data = RSAUtils.encryptRSAByte(jsMap.toString().getBytes("UTF-8"),encryptionKey4Server);
		reqMap.put("reqMapEncrypt", data);//加密数据
		
		JSONObject params = JSONObject.fromObject(reqMap);
		Map map = new HashMap();
		map.put("BANKCODE", reqMap.get("BANKCODE"));//银行代码 徽商银行
		map.put("COINSTCODE", reqMap.get("COINSTCODE"));//合作单位编号
		map.put("APIVERSION", version);//版本号
		map.put("reqMap", dataparams);
		JSONObject reqdata = JSONObject.fromObject(map);
		//发送到路由的地址
		PostMethod method = new PostMethod(uriHSignUtil.URI);
		method.setParameter("reqdata", reqdata.toString());
		System.out.println("reqdata.toString: "+reqdata.toString());
		//提交  执行
		new HttpClient().executeMethod(method);*/
				
		//返回参数
		String response = /*method.getResponseBodyAsString()*/HSignUtil.HttpClientTask(reqMap,version);
		System.out.println("接收端的返回值response： "+response);
		
		Map jsonMap = JSONObject.fromObject(response);
		System.out.println("发送端项目的jsonMap："+jsonMap);
		String resultData = jsonMap.get("responseData").toString();
				
		//解密调整
		String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(/*decryptKeyPath*/HSignUtil.DECRYPTPATH);
		//对请求数据进行解密
		String result = RSAUtils.decryptRSAs(resultData, decryptKey4Server);
		
		HashMap responseMap = (HashMap) parseJSON2Map(result);
		System.out.println("responseMap: "+responseMap);
		System.out.println("发送端项目的返回码： "+responseMap.get("RETCODE"));
		
		ArrayList headColumn = new ArrayList();
		headColumn.add("TRXCODE");//交易代码
		headColumn.add("BANKCODE");//银行代码
		headColumn.add("TRXDATE");//交易日期
		headColumn.add("TRXTIME");//交易时间
		headColumn.add("COINSTCODE");//合作单位编号
		headColumn.add("COINSTCHANNEL");//合作单位渠道
		headColumn.add("SEQNO");//交易流水号
		headColumn.add("SOURCE");//ESB内部渠道
		headColumn.add("RETCODE");//应答码
		headColumn.add("RETMSG");//应答码描述
		headColumn.add("HEADRESERVED");//报文头保留域
		
		resColumnList.addAll(0, headColumn);
		if(responseMap.get("RETCODE")==null){
			System.out.println("操作失败： "+responseMap.get("RETMSG"));
			throw new ArrayIndexOutOfBoundsException("请求参数非法");
		}
		
		JSONObject json = JSONObject.fromObject(responseMap);
		int listLength = resColumnList.size();
		int forLen = 0;
		if("5849".equals(responseMap.get(resColumnList.get(0)))){
			forLen = listLength - 1;
		}else{
			forLen = listLength;
		}
		StringBuffer responseMapMerged = new StringBuffer();
		for(int i = 0;i<forLen;i++){
			Object columnvalue = responseMap.get(resColumnList.get(i));
			if(columnvalue != null){
				responseMapMerged.append(responseMap.get(resColumnList.get(i)));
			}
		}
		
		if(json.get("SUBPACKS")!=null){
			
			JSONArray SUBPACKS=(JSONArray)json.get("SUBPACKS");
			System.out.println("SUBPACKS:"+SUBPACKS);
			int Size = SUBPACKS.size();
			responseMapMerged.append(responseMap.get(resColumnList.get(listLength-1)));
		}
		//验签
		String responseSign = (String) responseMap.get("SIGN");
		//校验数字签名
		boolean verifyResult = RSAUtils.MD5WithRSAVerify(responseMapMerged.toString().getBytes("UTF-8"), getVerifyKey4Client(/*signPublicPath*/HSignUtil.SIGNPUBLICPATH), responseSign);
		
		if(!verifyResult){
			System.out.println("验证签名失败。。。。");
			return 0;
		}else{
			System.out.println("验证签名成功");
		}
		
		int line=0;
		
		if(verifyResult && "00000000".equals(responseMap.get("RETCODE"))){
			System.out.println("===========操作成功==========");
			System.out.println("交易代码：        "+responseMap.get("TRXCODE"));
			System.out.println("银行代码：        "+responseMap.get("BANKCODE"));
			System.out.println("交易日期：        "+responseMap.get("TRXDATE"));
			System.out.println("交易时间：        "+responseMap.get("TRXTIME"));
			System.out.println("合作单位编号："+responseMap.get("COINSTCODE"));
			System.out.println("合作单位渠道： "+responseMap.get("COINSTCHANNEL"));
			System.out.println("交易流水号：     "+responseMap.get("SEQNO"));//
			System.out.println("ESB内部渠道： "+responseMap.get("SOURCE"));
			System.out.println("应答码：              "+responseMap.get("RETCODE"));//
			System.out.println("应答码描述：      "+responseMap.get("RETMSG"));
			System.out.println("报文头保留域：  "+responseMap.get("HEADRESERVED"));//
			System.out.println("===================================");
			System.out.println("转出电子账户：  "+responseMap.get("CARDNBR"));
			System.out.println("转入电子账户：  "+responseMap.get("CARDNBRIN"));
			System.out.println("币种：                  "+responseMap.get("CURRENCY"));
			System.out.println("金额：                  "+responseMap.get("AMOUNT"));//
			System.out.println("保留域：              "+responseMap.get("RESERVE"));
			
			//平台账户余额
//			Map<String,String> r58631 = queryBlaneService.queryBlane("9930040050240250015");
//			String AVAIL_BAL1 = r58631.get("AVAIL_BAL");//可用余额
//			String CURR_BAL1 = r58631.get("CURR_BAL");//账户余额
//			System.out.println("AVAIL_BAL1: "+AVAIL_BAL1);
//			System.out.println("CURR_BAL1: "+CURR_BAL1);
			
			//收钱方的账户余额
//			Map<String,String> r58632 = queryBlaneService.queryBlane((String)responseMap.get("CARDNBRIN"));
//			String AVAIL_BAL2 = r58632.get("AVAIL_BAL");//可用余额
//			String CURR_BAL2 = r58632.get("CURR_BAL");//账户余额
//			System.out.println("AVAIL_BAL2: "+AVAIL_BAL2);
//			System.out.println("CURR_BAL2: "+CURR_BAL2);
			
			
			//处理自己的业务逻辑
			//通过获奖名单里面红包的id获取对应的红包记录
			ActivityAwardList aal=activityAwardListService.getselectById(new BigDecimal(responseMap.get("HEADRESERVED").toString().trim()));
			//现金红包发放对账记录表
			RedEnveLopeItem reli=new RedEnveLopeItem();
			reli.setOrderno(responseMap.get("SEQNO").toString().trim());//记录表的流水号 对应订单号
			reli.setRedenvelopeno(aal.getAwardno());//红包编号
			reli.setRedenvelopename(aal.getAwardname());//奖品的名称
			reli.setReamount(Float.parseFloat(responseMap.get("AMOUNT").toString().trim()));//奖品的金额
			reli.setBaseid(aal.getBaseid());//用户id
			reli.setUsername(aal.getUsername());//用户名
			reli.setBusinesstype(ActAward_Constant.RECTYPE_MAP.get(aal.getAl().getActtype()));//业务类型  5.手动颁奖
			reli.setSendtime(new Date());//处理时间
			reli.setIsblending((short)0);//是否系统勾兑
			reli.setIsmanblending((short)0);//是否人工勾兑
			reli.setPaycompany("徽商银行");//托管通道公司（徽商）
			/*先根据流水号判断数据库中是否有该条数据，如果有，不再添加*/
			RedEnveLopeItem redeli=redEnveLopeItemService.getByOrderNo(responseMap.get("SEQNO").toString().trim());
			if(redeli == null){
				//插入数据
				int rows = redEnveLopeItemService.insert(reli);
				if(rows>0){
					//用户账户表
					/*UserAccount usAc=userAccountService.getUserAccountByBaseId(aal.getBaseid());
					Double balance = usAc.getBalance() + new Double(responseMap.get("AMOUNT").toString());
					Double avlbalance = usAc.getAvlbalance() + new Double(responseMap.get("AMOUNT").toString());
					Double freezeBalance = usAc.getFreezebalance() ==null?0.00 : usAc.getFreezebalance();
					//汇付转账成功后，才给用户余额进账，更新用户账户表
					usAc.setBalance(balance);
					usAc.setAvlbalance(avlbalance);*/
					//查询托管账户的可用余额和账户余额
					Map<String,String> r5863 = queryBlaneService.queryBlane((String)responseMap.get("CARDNBRIN"));
					String AVAIL_BAL = r5863.get("AVAIL_BAL");//可用余额
					String CURR_BAL = r5863.get("CURR_BAL");//账户余额
					//插入数据
					/*UserAccount usAc = new UserAccount();
					usAc.setBaseid(aal.getBaseid());
					usAc.setBalance(Double.parseDouble(CURR_BAL));//用户总资产
					usAc.setAvlbalance(Double.parseDouble(AVAIL_BAL));//可用余额
					userAccountService.updateUseraccount(usAc);
					userAccountService.updateUserAccountBalAvl(usAc);*/
					
					int iiii = queryBlaneService.queryBlaneAndUpdateUserAccount((String)responseMap.get("CARDNBRIN"),aal.getBaseid().toString());
					
					//根据电子账户查找出用户的托管账号baseid
					UserFsAccountInfo userFsAccountInfo=userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(AES.getEncrypt((String)responseMap.get("CARDNBR")));
					if(userFsAccountInfo!=null){
						/*Map<String,String> r58631 = queryBlaneService.queryBlane((String)responseMap.get("CARDNBR"));
						String AVAIL_BAL1 = r58631.get("AVAIL_BAL");//可用余额
						String CURR_BAL1 = r58631.get("CURR_BAL");//账户余额
*/						//插入数据
						/*UserAccount usAc1 = new UserAccount();
						usAc1.setBaseid(userFsAccountInfo.getBaseid());
						usAc1.setBalance(Double.parseDouble(CURR_BAL1));//用户总资产
						usAc1.setAvlbalance(Double.parseDouble(AVAIL_BAL1));//可用余额
						userAccountService.updateUseraccount(usAc1);*/
						int nnnn = queryBlaneService.queryBlaneAndUpdateUserAccount((String)responseMap.get("CARDNBR"),userFsAccountInfo.getBaseid().toString());
					}
					
					//账户收支记录表
					AccInExRecord aier = new AccInExRecord();
					aier.setBaseid(aal.getBaseid());//用户的id
					aier.setAieorderno(StringUtil.getNo());//收支记录流水号
					aier.setBorderno(responseMap.get("SEQNO").toString());//业务流水号(这里放的是订单编号)
					aier.setType((short)32);//业务类型   现金
					aier.setInamount(new Double(responseMap.get("AMOUNT").toString()));//收入
					aier.setOutamount(0.00);//支出
					aier.setPaccount(responseMap.get("CARDNBR").toString().trim());//平台账户
					aier.setPinamount(0.00);//平台账户收入,平台产生的费用
					aier.setPoutamount(0.00);//平台账户支出,平台产生的费用
					aier.setStatus((short)1);//业务状态  0冻结  1成功 2失败
					aier.setDescription("现金红包转账");//说明
					aier.setBalance(/*avlbalance*/Double.parseDouble(AVAIL_BAL));//用户的可用余额
					//aier.setFreebalance(freezeBalance);//用户的冻结余额
					aier.setTotalbalance(Double.parseDouble(CURR_BAL)/*balance*/);//用户的总金额
					aier.setRecordtime(new Date());//发生时间
					aier.setRemark("现金红包转账");//备注
					//插入数据
					accInExRecordService.insertSelective(aier);
					
					//插入红包表记录(需要一份一份的插入)
					UserRedEnvelope urel= new UserRedEnvelope();
					urel.setBaseid(aal.getBaseid());//用户ID
					urel.setUreno(aal.getAwardno());//奖品编号
					urel.setRectype(aal.getAl().getActtype());//红包获取方式类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖 7.完善资料)
					urel.setRetype(aal.getAwardattribute());//红包类型（1.现金，2.类现金，3.假现金）
					urel.setRestime(new Date());//红包发放时间
					urel.setRedenvelope(aal.getAwardmoney());//红包金额
					urel.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
					urel.setIsuse((short)4);//奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）
					//根据奖品的编号获取奖品（到期时间）
					Award a = awardService.selectByAwardNo(aal.getAwardno());
				    urel.setRefailtime(a.getEndtime());//到期时间
				    if(aal.getAuditman()==null||"".equals(aal.getAuditman())){
				    	urel.setIsaudit((short)1);//是否审核（0.是，1.否）
				    }else{
				    	urel.setIsaudit((short)0);//是否审核（0.是，1.否）
				    }
				    urel.setAuditman(aal.getAuditman());//审核人
				    urel.setAudittime(aal.getAudittime());//审核时间
				    urel.setPurpose("该红包可以直接提现");
				    urel.setRemark(aal.getRemark());//备注
				    urel.setRedealtime(new Date());//红包的入账时间
				    for(int k = 0;k<aal.getAwardquantity();k++){
				    	int lines = userRedEnvelopeService.insert(urel);
				    }
					
				    //更新奖品名单的处理人和处理时间 
				    ActivityAwardList activityAwardList = new ActivityAwardList();
				    activityAwardList.setId(aal.getId());
				    activityAwardList.setStatus((short)3);//发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败 
				    HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				    AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
				    activityAwardList.setDealman(au.getUsername());//处理人
				    activityAwardList.setDealtime(new Date());//处理时间
				    line = activityAwardListService.update(activityAwardList);
				    
				}
			}
		}else{
			System.out.println("操作失败，错误代码： "+responseMap.get("RETCODE"));
			line = 0;
		}
		
		return line;
	}
	
	/**
     * 根据公钥文件路径读取私钥
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
            //value = entry.getValue();
        }
        requestMerged = buff.toString();
        return requestMerged;
    }
	/**
     * 根据私钥文件路径读取私钥
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
     * json转map
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
    
}
