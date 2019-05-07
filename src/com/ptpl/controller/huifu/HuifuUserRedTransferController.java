package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.PlusPayoutRecord;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 现金红包转账
 * @author pxl
 * @date  2016-12-31
 *
 */
@Controller
@RequestMapping("/huifu/giro")
public class HuifuUserRedTransferController {

	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;//用户托管账户信息Service

	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;//用户红包Service
	
	@Autowired
	UserAccountServiceI userAccountService;//用户账户表
	
	@Autowired
	AccInExRecordServiceI accInExRecordService;//账户收支记录表
	
	@Autowired
	AwardServiceI awardService;//奖品
	
	@Autowired
	RedEnveLopeItemServiceI redEnveLopeItemService;//红包发放对账记录表
	
	@Autowired
	ActivityAwardListServiceI activityAwardListService;//获奖名单表
	
	@Autowired
	AdminUserServiceI adminUserService;//用户管理员后台
	
	//当为现金红包时，需要在汇付处从商户后台向用户后台转出一笔金额，成功后需要向现金红包发放对账记录中添加一条数据和用户账户表中添加一条数据
	@RequestMapping("/transferAccounts")
	public synchronized void transferAccounts(HttpServletRequest request,HttpServletResponse response,BigDecimal id) throws Exception{
		
		//向汇付请求转账
		HuifuParams hfp = new HuifuParams(); 
		hfp.setVersion("10");//固定为10 ，如版本升级，能向前兼容
		hfp.setCmdId("Transfer");//消息类型
		hfp.setOrdId(StringUtil.getNo());//订单号
		hfp.setOutCustId("6000060004166478");//出账客户号
		hfp.setOutAcctId("MDT000001");//出账子账户
		/*
		 * 根据id获取对应的获奖记录
		 * 用来获取转账金额
		 */
		ActivityAwardList aal=activityAwardListService.getselectById(id);
		System.out.println("现金红包====="+aal);
		Double amount = aal.getAwardmoney()*aal.getAwardquantity();
		hfp.setTransAmt(amount.toString()+"0");//转账金额
		/*UserFsAccountInfo:用户托管账户信息
		 * 根据baseid查出当前用户的客户号
		 */
		UserFsAccountInfo ufai=userFsAccountInfoService.findUserFsAccountInfoByBaseId(aal.getBaseid());
		
		//用户没有开通托管账户
		String jsonStr = null;
		if(ufai==null){
			jsonStr = JSON.toJSONString("false");
			StringUtil.sendJsonData(response, jsonStr);
			return;
		}else if(ufai.getIsopenfsinfo()==0){
			jsonStr = JSON.toJSONString("false");
			StringUtil.sendJsonData(response, jsonStr);
			return;
		}
		
		System.out.println("用户托管账户信息===="+ufai);
		hfp.setInCustId(ufai.getUsrcustid());//客户号
		
		/* 
		 * 交易完成后，本平台系统把交易结果通过页面方式，发送到该地址上
		 * 注意：
		 * 1) 使用时请注意不要包含中文
		 * 2)URL 中请不要包含“ 附件三：BGRETURL 中禁止的字符串列表” 的字符串
		 * 
		 */
		String basePath = StringUtil.getBasePath(request);
		//hfp.setRetUrl(basePath+"/huifu/giro/reCallback.action");//页面返回URL
		hfp.setBgRetUrl(basePath+"/huifu/giro/userRedReplyAddress.action");//商户后台应答地址
		
		//签名
		String result = userRedSignature(hfp,request,response);
		
		/*
		* 1.修改当前现金红包的状态
	  	* 2.向现金红包发放对账记录表中添加当前信息
	  	* 3.修改用户账户表当中该用户的余额
		*/
		JSONObject json = JSONObject.parseObject(result); // 将返回的json字符串转换为JSONObject6
		String cmdId = json.getString("CmdId");//消息类型
		String respCode = json.getString("RespCode");//应答返回码
		//String respDesc = json.getString("RespDesc");//应答描述
		String ordId = json.getString("OrdId");//订单号
		String outCustId = json.getString("OutCustId");//出账客户号
		String outAcctId = json.getString("OutAcctId");//出账子账户
		String transAmt = json.getString("TransAmt");//交易金额
		String inCustId = json.getString("InCustId");//入账客户号
		String inAcctId = json.getString("InAcctId");//入账子账户
		//String retUrl = json.getString("RetUrl");//页面返回URL
		String bgRetUrl = json.getString("BgRetUrl");//商户后台应答地址
		String chkValue = json.getString("ChkValue");//返回的签名
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(cmdId));
		sb.append(StringUtils.trimToEmpty(respCode));
		sb.append(StringUtils.trimToEmpty(ordId));
		sb.append(StringUtils.trimToEmpty(outCustId));
		sb.append(StringUtils.trimToEmpty(outAcctId));
		sb.append(StringUtils.trimToEmpty(transAmt));
		sb.append(StringUtils.trimToEmpty(inCustId));
		sb.append(StringUtils.trimToEmpty(inAcctId));
		//sb.append(StringUtils.trimToEmpty(retUrl));
		sb.append(StringUtils.trimToEmpty(bgRetUrl));
				
		String sbStr = sb.toString();
		boolean flag = false;
		
		try {
			flag = SignUtils.verifyByRSA(sbStr,chkValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("flag===="+flag);
		
		if(flag && "000".equals(respCode)){//调用成功
			
			//现金红包发放对账记录表
			RedEnveLopeItem reli=new RedEnveLopeItem();
			reli.setOrderno(ordId);//流水号 对应订单号
			reli.setRedenvelopeno(aal.getAwardno());//红包编号
			reli.setRedenvelopename(aal.getAwardname());//奖品的名称
			reli.setReamount(Float.parseFloat(transAmt));//奖品的金额
			reli.setBaseid(aal.getBaseid());//用户id
			reli.setUsername(aal.getUsername());//用户名
			reli.setBusinesstype(ActAward_Constant.RECTYPE_MAP.get(aal.getAl().getActtype()));//业务类型  5.手动颁奖
			reli.setSendtime(new Date());//处理时间
			reli.setIsblending((short)0);//是否系统勾兑
			reli.setIsmanblending((short)0);//是否人工勾兑
			reli.setPaycompany("汇付天下");//托管通道公司（汇付天下）
			
			/*先根据流水号判断数据库中是否有该条数据，如果有，不再添加*/
			RedEnveLopeItem redeli=redEnveLopeItemService.getByOrderNo(ordId);
			int line = 0;
			if(redeli == null){
				//插入数据
				int rows = redEnveLopeItemService.insert(reli);
				if(rows>0){
					
					//用户账户表
					UserAccount usAc=userAccountService.getUserAccountByBaseId(aal.getBaseid());
					Double balance = usAc.getBalance() + new Double(transAmt);
					Double avlbalance = usAc.getAvlbalance() + new Double(transAmt);
					Double freezeBalance = usAc.getFreezebalance() ==null?0.00 : usAc.getFreezebalance();
					//汇付转账成功后，才给用户余额进账，更新用户账户表
					usAc.setBalance(balance);
					usAc.setAvlbalance(avlbalance);
					//插入数据
					userAccountService.updateUseraccount(usAc);
					
					//账户收支记录表
					AccInExRecord aier = new AccInExRecord();
					aier.setBaseid(aal.getBaseid());//用户的id
					aier.setAieorderno(StringUtil.getNo());//收支记录流水号
					aier.setBorderno(ordId);//业务流水号(这里放的是订单编号)
					aier.setType((short)32);//业务类型   现金
					aier.setInamount(new Double(transAmt));//收入
					aier.setOutamount(0.00);//支出
					aier.setPaccount("MDT000001");//平台账户
					aier.setPinamount(0.00);//平台账户收入
					aier.setPoutamount(0.00);//平台账户支出
					aier.setStatus((short)1);//业务状态  0冻结  1成功 2失败
					aier.setDescription("现金红包转账");//说明
					aier.setBalance(avlbalance);//用户的可用余额
					aier.setFreebalance(freezeBalance);//用户的冻结余额
					aier.setTotalbalance(balance);//用户的总金额
					aier.setRecordtime(new Date());//发生时间
					aier.setRemark("现金红包转账");//备注
					//插入数据
					accInExRecordService.insertSelective(aier);
					
					//插入红包表记录(需要一份一份的插入)
					UserRedEnvelope urel= new UserRedEnvelope();
					
					urel.setBaseid(aal.getBaseid());//用户ID
					urel.setUreno(aal.getAwardno());//奖品编号
					urel.setRectype(aal.getAl().getActtype());//红包获取方式类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖 )
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
				    for(int i = 0;i<aal.getAwardquantity();i++){
				    	int lines = userRedEnvelopeService.insert(urel);
				    }
				    
				    //更新奖品名单的处理人和处理时间
				    ActivityAwardList activityAwardList = new ActivityAwardList();
				    activityAwardList.setId(aal.getId());
				    activityAwardList.setStatus((short)3);//发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败 
				    //后台管理员
					AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER); 
				    activityAwardList.setDealman(au.getUsername());//处理人
				    activityAwardList.setDealtime(new Date());//处理时间
				    line = activityAwardListService.update(activityAwardList);
				    
				}
			}
			
			if(line>0){
				jsonStr = JSON.toJSONString("success");
			}else{
				jsonStr = JSON.toJSONString("fail");
			}
			StringUtil.sendJsonData(response, jsonStr);
		}
		
	}
	
	/**
	 * 签名(加签)
	 * @throws Exception 
	 * 
	 */
	public String userRedSignature(HuifuParams hfp,HttpServletRequest request,HttpServletResponse response) throws Exception{
		/*
		 * 加签验签的时候，商户需告知汇付其系统编码
		 * 汇付在验签的时候进行相应的编码转换验签
		 * Version +CmdId + OrdId + OutCustId + OutAcctId + TransAmt+
		 * InCustId+ InAcctId+RetUrl + BgRetUrl+ MerPriv
		 */
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(hfp.getVersion()));
		sb.append(StringUtils.trimToEmpty(hfp.getCmdId()));
		sb.append(StringUtils.trimToEmpty(hfp.getOrdId()));
		sb.append(StringUtils.trimToEmpty(hfp.getOutCustId()));
		sb.append(StringUtils.trimToEmpty(hfp.getOutAcctId()));
		sb.append(StringUtils.trimToEmpty(hfp.getTransAmt()));
		sb.append(StringUtils.trimToEmpty(hfp.getInCustId()));
		//sb.append(StringUtils.trimToEmpty(hfp.getRetUrl()));
		sb.append(StringUtils.trimToEmpty(hfp.getBgRetUrl()));
		
		String chkValue = sb.toString();
		System.out.println("chkValue>>>>"+chkValue);
		
		hfp.setChkValue(chkValue);//签名
		
		String ChkValue = SignUtils.encryptByRSA(chkValue);//RSA方式加签
		
		Map<String,String> hfMap = new HashMap<String,String>();
		hfMap.put("Version", hfp.getVersion());
		hfMap.put("CmdId", hfp.getCmdId());
		hfMap.put("OrdId", hfp.getOrdId());
		hfMap.put("OutCustId", hfp.getOutCustId());
		hfMap.put("OutAcctId", hfp.getOutAcctId());
		hfMap.put("TransAmt", hfp.getTransAmt());
		hfMap.put("InCustId", hfp.getInCustId());
		//hfMap.put("RetUrl", hfp.getRetUrl());
		hfMap.put("BgRetUrl", hfp.getBgRetUrl());
		hfMap.put("ChkValue", ChkValue);	
		
		//后台提交 调用汇付商户转账接口
		String result = doPost(hfMap);
		
		System.out.println("huifu的result==="+result);
		
		return result;
		
	}
	
	// 如果关注性能问题可以考虑使用HttpClientConnectionManager
   	public static String doPost(Map<String,String> hfMap) throws ClientProtocolException, IOException{
   		
   		String result = null;
   		if (hfMap != null){
   			// 目标地址 
   			HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);//http://mertest.chinapnr.com/muser/publicRequests
   			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(hfMap);
   			httpPost.setEntity(formEntiry);
   			
   			CloseableHttpClient httpclient = HttpClients.createDefault();
   			// 执行    
   			CloseableHttpResponse response = httpclient.execute(httpPost);
   			try{
   			
   				HttpEntity entity = response.getEntity();
   				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
   					//把内容转成字符串 
   					result = EntityUtils.toString(entity, "UTF-8");
   					//关闭HttpEntity流
   				    EntityUtils.consume(entity);
   			} finally{
   				response.close();
   			}
   		}
   		return result;
   	}
	
   	/* 
   	 * 此方法用于接收汇付回调数据并在里面处理业务
  	 * 
  	 */
  	@RequestMapping("/userRedReplyAddress")
  	private void userRedReplyAddress(HttpServletRequest request,HttpServletResponse response,HuifuParams hfp) throws IOException{
  		
  		System.out.println("消息类型============="+hfp.getCmdId());
  		System.out.println("应答返回码============"+hfp.getRespCode());
		System.out.println("应答描述============="+hfp.getRespDesc());
		System.out.println("订单号==============="+hfp.getOrdId());
		System.out.println("出账客户号============"+hfp.getOutCustId());
		System.out.println("出账子账户============"+hfp.getOutAcctId());
		System.out.println("交易金额=============="+hfp.getTransAmt());
		System.out.println("入账客户号============="+hfp.getInCustId());
		System.out.println("入账子账户============="+hfp.getInAcctId());
		System.out.println("页面返回URL==========="+hfp.getRetUrl());
		System.out.println("商户后台应答地址========="+hfp.getBgRetUrl());
		//System.out.println("商户私有域============="+hfp.getMerPriv());//存放获奖名单里面用户红包id
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(hfp.getCmdId()));
		sb.append(StringUtils.trimToEmpty(hfp.getRespCode()));
		//sb.append(StringUtils.trimToEmpty(hfp.getRespDesc()));
		sb.append(StringUtils.trimToEmpty(hfp.getOrdId()));
		sb.append(StringUtils.trimToEmpty(hfp.getOutCustId()));
		sb.append(StringUtils.trimToEmpty(hfp.getOutAcctId()));
		sb.append(StringUtils.trimToEmpty(hfp.getTransAmt()));
		sb.append(StringUtils.trimToEmpty(hfp.getInCustId()));
		sb.append(StringUtils.trimToEmpty(hfp.getInAcctId()));
		//sb.append(StringUtils.trimToEmpty(hfp.getRetUrl()));
		sb.append(StringUtils.trimToEmpty(hfp.getBgRetUrl()));
		//sb.append(StringUtils.trimToEmpty(hfp.getMerPriv()));
		
		String sbStr = sb.toString();
		boolean flag = false;
		
		try {
			flag = SignUtils.verifyByRSA(sbStr, hfp.getChkValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("flag===="+flag);
		
		if (flag && StringUtils.isNotBlank(hfp.getOrdId())){
			PrintWriter out = response.getWriter();
			out.print("RECV_ORD_ID_".concat(hfp.getOrdId()));
		}
		
  	}
	
  	/*//页面返回
  	@RequestMapping("/reCallback")
  	public void reCallback(){
  		
  		System.out.println("进来了");
  		
  	}*/
  	
}
