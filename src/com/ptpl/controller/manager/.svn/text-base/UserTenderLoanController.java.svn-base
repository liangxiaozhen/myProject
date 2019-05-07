package com.ptpl.controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.TenderItem_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.GuaranteeFee;
import com.ptpl.model.MediacyFee;
import com.ptpl.model.RiskGuarantyMoney;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserMakeALoan;
import com.ptpl.model.UserTender;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.MediacyFeeServiceI;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;


/**
 * 自动扣款（放款）
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/admin/tenderLoans")
public class UserTenderLoanController extends BaseController {	
	
	/** 用户托管账户信息service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	/** 投标放款记录service */
	@Autowired
	UserMakeALoanServiceI userMakeALoanService;
	
	/** 投标记录service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 标的设置service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	/** 标的居间费设置service */
	@Autowired
	MediacyFeeServiceI mediacyFeeService;
	
	/** 标的担保费设置service */
	@Autowired
	GuaranteeFeeServiceI guaranteeFeeService;
	
	/** 标的风险保证金设置service */
	@Autowired
	RiskGuarantyMoneyServiceI riskGuarantyMoneyService;
	
	/**
	 * 查询待放款的标的
	 * @param tenderItem
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAllForLoan")
	public ModelAndView queryAllForLoan(TenderItem tenderItem) throws Exception {
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, pageSize);
		tenderItem.setTstatus((short) 5);
		List<TenderItem> pendingLoans = tenderItemService.selectByCondition(tenderItem);
		PageInfo<Object> pagehelper = initPagehelper(maps, pendingLoans);
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("statusMap", TenderItem_Constant.TSTATUS_MAP);
		// 条件回显
		mv.addObject("echodata", tenderItem);
		// 指定视图
		mv.setViewName("admin/userTenderLoans/UserTenderPendingLoan_List");
		return mv;
	}
	
	/**
	 * 根据标号查询待放款的投标
	 * @param tno
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPendingLoans")
	public ModelAndView queryPendingLoans(String tno) throws Exception {
		List<UserTender> pendingLoans = userTenderService.selectPendingLoanByTno(tno);
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("pendingLoans", pendingLoans);
		// 指定视图
		mv.setViewName("admin/userTenderLoans/UserTenderPendingLoan");
		return mv;
	}
	
	/**
	 * 去放款
	 * TODO 使用类现金时平台代扣收支记录，居间费、担保费、风险保证金的收支记录
	 * @param userTender
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/goLoans")
	public void goLoans(UserTender userTender) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String info = "";
		BigDecimal tenderId = null;
		if(userTender.getOrderno() != null){
			String[] orderNoArray = userTender.getOrderno().split(",");
			for(String orderno : orderNoArray){
				UserTender tenderRecord = userTenderService.findUserTenderByOrderno(orderno); // 根据投标订单号查询投标记录
				tenderId = tenderRecord.getTenderid();
				if(tenderRecord != null){
					System.out.println("待放款的投标订单号=============================="+tenderRecord.getOrderno());
					Map<String, String> param = LoansParams(tenderRecord);
					String result = doPost(param);
					System.out.println("放款结果！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"+result);
					JSONObject json = JSONObject.parseObject(result); // 将返回的json字符串转换为JSONObject6
					String RespCode = json.getString("RespCode");			// 投标放款应答返回码
					System.out.println("放款返回码========================================="+RespCode);
					if(!RespCode.trim().equalsIgnoreCase("000")){ // 放款成功
						info += "放款失败！投标订单号为："+json.getString("SubOrdId");
					}
					map.put("result", info);
				}
			}
			if(info.equalsIgnoreCase("")){
				map.put("result", "全部放款成功！");
				TenderItem tenderItem = tenderItemService.findTenderItemById(tenderId);
				tenderItem.setTstatus((short) 9); // 还款中
				int count = 0;
				count = tenderItemService.update(tenderItem);
				if(count > 0){
					System.out.println("更新标的设置成功！！！！！！！！！！！！！！！！！！！！！！！！");
				}
				calculationValueDate(tenderId);
			}
		}else{
			map.put("result", "没有需要放款的订单！");
		}
		sendJsonData(response, JSON.toJSONString(map));
	}
	
	/**
	 * 汇付天下-自动扣款（放款）接口请求参数
	 * @param tenderParams
	 * @param request
	 * @param userTender
	 * @param tenderItem
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> LoansParams(UserTender tender) throws Exception {
		System.out.println("=================放款请求进来了！=================");
		HuifuParams huifuparam = new HuifuParams();
		huifuparam.setVersion("20"); 																// 版本号
		huifuparam.setCmdId("Loans"); 																// 消息类型
		huifuparam.setOrdId(StringUtil.getNo());													// 放款订单号
		Date loansTime = new Date();
		huifuparam.setOrdDate(StringUtil.formatDate(loansTime, "yyyyMMdd")); 						// 放款订单日期
		UserFsAccountInfo outAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(tender.getOutaccountid()); // 根据投资人baseid查询用户托管信息
		huifuparam.setOutCustId(outAccount.getUsrcustid()); 										// 出账客户号-投资人客户号
		huifuparam.setTransAmt(df1.format(tender.getAmount())); 									// 交易金额-放款金额
		huifuparam.setFee(df1.format(tender.getFee())); 											// 扣款手续费 
		huifuparam.setSubOrdId(tender.getOrderno()); 												// 投标订单号
		huifuparam.setSubOrdDate(StringUtil.formatDate(tender.getTendtime(), "yyyyMMdd")); 			// 投标订单日期
		UserFsAccountInfo inAccount = userFsAccountInfoService.findUserFsAccountInfoByBaseId(tender.getInaccountid()); // 根据借款人baseid查询用户托管信息
		huifuparam.setInCustId(inAccount.getUsrcustid()); 											// 入账客户号-借款人客户号
		if(!huifuparam.getFee().equalsIgnoreCase("0.00")){ // 判断是否收取手续费
			System.out.println("收取手续费！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			MediacyFee mediacyFee = null;
			GuaranteeFee guaranteeFee = null;
			RiskGuarantyMoney riskGuarantyMoney = null;
			if(tender.getMfid() != null){
				mediacyFee = mediacyFeeService.selectByPrimaryKey(tender.getMfid());
			}
			if(tender.getGfid() != null){
				guaranteeFee = guaranteeFeeService.selectByPrimaryKey(tender.getGfid());
			}
			if(tender.getRgmid() != null){
				riskGuarantyMoney = riskGuarantyMoneyService.selectByPrimaryKey(tender.getRgmid());
			}
			huifuparam.setDivCustId(tender.getMrecmancustid());											// 分账商户号
			huifuparam.setDivAcctId(tender.getMrecman());												// 分账账户号
			if(tender.getMediacyfee() == 0 && tender.getGuaranteefee() == 0){
				huifuparam.setDivDetails("[{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+riskGuarantyMoney.getRgmrecman()+"\",\"DivAmt\":\""+df1.format(tender.getRiskguarantyfee())+"\"}]");
			}else if(tender.getGuaranteefee() == 0 && tender.getRiskguarantyfee() == 0){
				huifuparam.setDivDetails("[{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+mediacyFee.getMrecman()+"\",\"DivAmt\":\""+df1.format(tender.getMediacyfee())+"\"}]");
			}else if(tender.getMediacyfee() == 0 && tender.getRiskguarantyfee() == 0){
				huifuparam.setDivDetails("[{\"DivCustId\":\"6000060005459337\",\"DivAcctId\":\""+guaranteeFee.getGfrecman()+"\",\"DivAmt\":\""+df1.format(tender.getGuaranteefee())+"\"}]");
			}else if(tender.getMediacyfee() == 0){
				huifuparam.setDivDetails("[{\"DivCustId\":\"6000060005459337\",\"DivAcctId\":\""+guaranteeFee.getGfrecman()+"\",\"DivAmt\":\""+df1.format(tender.getGuaranteefee())+"\"},{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+riskGuarantyMoney.getRgmrecman()+"\",\"DivAmt\":\""+df1.format(tender.getRiskguarantyfee())+"\"}]");
			}else if (tender.getGuaranteefee() == 0){
				huifuparam.setDivDetails("[{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+mediacyFee.getMrecman()+"\",\"DivAmt\":\""+df1.format(tender.getMediacyfee())+"\"},{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+riskGuarantyMoney.getRgmrecman()+"\",\"DivAmt\":\""+df1.format(tender.getRiskguarantyfee())+"\"}]");
			}else if(tender.getRiskguarantyfee() == 0){
				huifuparam.setDivDetails("[{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+mediacyFee.getMrecman()+"\",\"DivAmt\":\""+df1.format(tender.getMediacyfee())+"\"},{\"DivCustId\":\"6000060005459337\",\"DivAcctId\":\""+guaranteeFee.getGfrecman()+"\",\"DivAmt\":\""+df1.format(tender.getGuaranteefee())+"\"}]");
			}else{
				huifuparam.setDivDetails("[{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+mediacyFee.getMrecman()+"\",\"DivAmt\":\""+df1.format(tender.getMediacyfee())+"\"},{\"DivCustId\":\"6000060005459337\",\"DivAcctId\":\""+guaranteeFee.getGfrecman()+"\",\"DivAmt\":\""+df1.format(tender.getGuaranteefee())+"\"},{\"DivCustId\":\""+huifuparam.getMerCustId()+"\",\"DivAcctId\":\""+riskGuarantyMoney.getRgmrecman()+"\",\"DivAmt\":\""+df1.format(tender.getRiskguarantyfee())+"\"}]");
			}
			huifuparam.setDivAmt(huifuparam.getFee()); 												// 分账金额
			huifuparam.setFeeObjFlag("I");															// 手续费收取对象标志-I--向入款客户号 InCustId 收取
		}
		huifuparam.setIsDefault("N");																// 是否默认 Y--默认添加资金池:这部分资金需要商户调用商户代取现接口，帮助用户做后台取现动作
																									// 		 N--不默认不添加资金池:这部分资金用户可以自己取现
		huifuparam.setIsUnFreeze("Y");																// 是否解冻
		huifuparam.setUnFreezeOrdId(StringUtil.getNo());											// 解冻订单号
		huifuparam.setFreezeTrxId(tender.getFreezetrxid());											// 冻结标识
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuparam.setBgRetUrl(basePath+"/huifu/loans/loansCallBack.action"); 						// 商户后台应答地址
		huifuparam.setMerPriv(HttpClientHandler.getBase64Encode(tender.getTenderid().toString()));	// 标的设置表ID
		TenderItem tenderItem = tenderItemService.findTenderItemById(tender.getTenderid());
		String tNo = tenderItem.getTno();
		huifuparam.setReqExt("{\"ProId\":\""+tNo+"\"}");											// 入参扩展域
		huifuparam.setProId(tNo);																	// 项目ID
		System.out.println("Version========================="+huifuparam.getVersion());
		System.out.println("CmdId========================="+huifuparam.getCmdId());
		System.out.println("MerCustId========================="+huifuparam.getMerCustId());
		System.out.println("OrdId========================="+huifuparam.getOrdId());
		System.out.println("OrdDate========================="+huifuparam.getOrdDate());
		System.out.println("OutCustId========================="+huifuparam.getOutCustId());
		System.out.println("TransAmt========================="+huifuparam.getTransAmt());
		System.out.println("Fee========================="+huifuparam.getFee());
		System.out.println("SubOrdId========================="+huifuparam.getSubOrdId());
		System.out.println("SubOrdDate========================="+huifuparam.getSubOrdDate());
		System.out.println("InCustId========================="+huifuparam.getInCustId());
		System.out.println("DivCustId========================="+huifuparam.getDivCustId());
		System.out.println("DivAcctId========================="+huifuparam.getDivAcctId());
		System.out.println("DivDetails========================="+huifuparam.getDivDetails());
		System.out.println("DivAmt========================="+huifuparam.getDivAmt());
		System.out.println("FeeObjFlag========================="+huifuparam.getFeeObjFlag());
		System.out.println("IsDefault========================="+huifuparam.getIsDefault());
		System.out.println("IsUnFreeze========================="+huifuparam.getIsUnFreeze());
		System.out.println("UnFreezeOrdId========================="+huifuparam.getUnFreezeOrdId());
		System.out.println("FreezeTrxId========================="+huifuparam.getFreezeTrxId());
		System.out.println("BgRetUrl========================="+huifuparam.getBgRetUrl());
		System.out.println("ReqExt========================="+huifuparam.getReqExt());
		System.out.println("ProId========================="+huifuparam.getProId());
		Map<String, String> params = new HashMap<String, String>();
		params.put("Version", huifuparam.getVersion());
		params.put("CmdId", huifuparam.getCmdId());
		params.put("MerCustId", huifuparam.getMerCustId());
		params.put("OrdId", huifuparam.getOrdId());
		params.put("OrdDate", huifuparam.getOrdDate());
		params.put("OutCustId", huifuparam.getOutCustId());
		params.put("TransAmt", huifuparam.getTransAmt());
		params.put("Fee", huifuparam.getFee());
		params.put("SubOrdId", huifuparam.getSubOrdId());
		params.put("SubOrdDate", huifuparam.getSubOrdDate());
		params.put("InCustId", huifuparam.getInCustId());
		params.put("DivDetails", huifuparam.getDivDetails());
		params.put("DivCustId", huifuparam.getDivCustId());
		params.put("DivAcctId", huifuparam.getDivAcctId());
		params.put("DivAmt", huifuparam.getDivAmt());
		params.put("FeeObjFlag", huifuparam.getFeeObjFlag());
		params.put("IsDefault", huifuparam.getIsDefault());
		params.put("IsUnFreeze", huifuparam.getIsUnFreeze());
		params.put("UnFreezeOrdId", huifuparam.getUnFreezeOrdId());
		params.put("FreezeTrxId", huifuparam.getFreezeTrxId());
		params.put("BgRetUrl", huifuparam.getBgRetUrl());
		params.put("MerPriv", huifuparam.getMerPriv());
		params.put("ProId", huifuparam.getProId());
		params.put("ReqExt", huifuparam.getReqExt());
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion()))
				.append(StringUtils.trimToEmpty(huifuparam.getCmdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getOrdDate()))
				.append(StringUtils.trimToEmpty(huifuparam.getOutCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getTransAmt()))
				.append(StringUtils.trimToEmpty(huifuparam.getFee()))
				.append(StringUtils.trimToEmpty(huifuparam.getSubOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getSubOrdDate()))
				.append(StringUtils.trimToEmpty(huifuparam.getInCustId()))
				.append(StringUtils.trimToEmpty(huifuparam.getDivDetails()))
				.append(StringUtils.trimToEmpty(huifuparam.getFeeObjFlag()))
				.append(StringUtils.trimToEmpty(huifuparam.getIsDefault()))
				.append(StringUtils.trimToEmpty(huifuparam.getIsUnFreeze()))
				.append(StringUtils.trimToEmpty(huifuparam.getUnFreezeOrdId()))
				.append(StringUtils.trimToEmpty(huifuparam.getFreezeTrxId()))
				.append(StringUtils.trimToEmpty(huifuparam.getBgRetUrl()))
				.append(StringUtils.trimToEmpty(huifuparam.getMerPriv()))
				.append(StringUtils.trimToEmpty(huifuparam.getReqExt()));
		String plainStr = buffer.toString();
		System.out.println("组装放款加签字符串原文:========="+plainStr);
		inserUserMakeALoan(huifuparam, tender, loansTime);
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);
			params.put("ChkValue", ChkValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}

	public static String doPost(Map<String, String> params) throws ClientProtocolException, IOException
	{
		String result = null;
		HttpPost httpPost = new HttpPost(HttpClientHandler.HTTP_HOST);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if (params != null)
		{
			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
			httpPost.setEntity(formEntiry);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try
			{
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					result = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			} finally
			{
				response.close();
			}
		}
		return result;
	}
	
	/**
	 * 新增投标放款记录
	 * @param huifuParams
	 * @param userTender
	 * @param tenderItem
	 * @param loansTime
	 */
	private void inserUserMakeALoan(HuifuParams huifuParams, UserTender userTender, Date loansTime){
		UserMakeALoan umal = new UserMakeALoan();
		umal.setTenderid(userTender.getTenderid()); 									// 标的号ID
		umal.setOrderno(userTender.getOrderno()); 										// 投标订单号
		umal.setMloanorderno(huifuParams.getOrdId()); 									// 放款订单号 
		umal.setOutaccountid(userTender.getOutaccountid()); 							// 投资方ID
		umal.setInaccountid(userTender.getInaccountid()); 								// 借款方ID
		umal.setAmount(Double.valueOf(huifuParams.getTransAmt())); 						// 放款金额
		umal.setMalbegintime(loansTime); 												// 放款开始时间
		umal.setFee(userTender.getFee()); 												// 手续费（默认0）
		umal.setMediacyfee(userTender.getMediacyfee()); 								// 居间费
		umal.setGuaranteefee(userTender.getGuaranteefee()); 							// 担保费
		umal.setRiskguarantyfee(userTender.getRiskguarantyfee()); 						// 风险保证金
		umal.setIsthaw((short) 0); 														// 是否解冻（0.否，1.是）
		umal.setUnfreezeordid(huifuParams.getUnFreezeOrdId()); 							// 解冻订单号
		umal.setFreezetrxid(huifuParams.getFreezeTrxId()); 								// 冻结标识
		umal.setMalstatus((short) 0); 													// 放款的状态（0.失败，1.成功） 
		umal.setIsblending((short) 0);													// 是否系统勾兑（0.未勾兑，1.已勾兑）
		umal.setIsmanblending((short) 0); 												// 是否人工勾兑（0.未勾兑，1.已勾兑）
		umal.setPaycompany("汇付天下"); 													// 放款通道公司
		umal.setIsaudit((short) 0); 													// 是否审核
		umal.setRemark("投标放款测试"); 													// 备注
		userMakeALoanService.insertSelective(umal);
	}

	/***
	 * 计算起息日
	 * @param tenderItem
	 * @param userTender
	 * @throws Exception
	 */
	private void calculationValueDate(BigDecimal tId) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		TenderItem tenderItem = tenderItemService.findTenderItemById(tId);
		Date valueDate = tenderItem.getValuedate(); // 结标日
		Date formatValueDate = sdf.parse(sdf.format(tenderItem.getValuedate())); // 获取结标日，格式化为时分秒，再转换为Date类型
		UserTender userTender = new UserTender();
		if(tenderItem.getValuerule() == 2){ // 结标日次日
			valueDate = sf.parse(sf.format(valueDate.getTime()+24*3600*1000));
		}else if(tenderItem.getValuerule() == 3){ // 结标日固定时间之后
			Date valuePoint = sdf.parse(tenderItem.getValuepoint()); // 固定时间点
			if(Arith.sub(formatValueDate.getTime(), valuePoint.getTime()) > 0){
				valueDate = sf.parse(sf.format(valueDate.getTime()+24*3600*1000));
			}
		}
		userTender.setValuedate(valueDate);
		userTender.setTenderid(tId);
		int count = 0;
		count = userTenderService.updateValueDateByTenderId(userTender);
		if(count > 0){
			System.out.println("插入起息日成功！！！！！！！！！！！！！！！！！！");
		}
	}
}
