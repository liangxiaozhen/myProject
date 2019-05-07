package com.ptpl.controller.huishang;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.huishang.util.HSignUtil;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.model.UserTender;
import com.ptpl.model.UserTenderPlusLink;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.service.UserTenderPlusLinkServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

public class UserTenderCancleController extends BaseController {
	
	/** 投标记录Service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 标的设置Service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	/** 用户账户Service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	/** 用户托管账户信息Service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	/** 投标增益使用关联Service */
	@Autowired
	UserTenderPlusLinkServiceI userTenderPlusLinkService;
	
	/** 用户使用券service */
	@Autowired
	UserInterestRateCouponServiceI userInterestRateCouponService;
	
	/** 用户红包service */
	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;
	
	
	/**
	 * 徽商银行投标撤销请求
	 * @Title: doParams
	 * @Description: TODO(徽商银行投标撤销请求)
	 * @return void    返回类型
	 */
	public String doParams(UserTender ut) {
		LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
		// 请求报文头
		reqMap.put("TRXCODE", "5807"); // 交易代码TRXCODE——4位交易代码
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
		UserFsAccountInfo uf = userFsAccountInfoService.findUserFsAccountInfoByBaseId(ut.getOutaccountid()); // 查询投资人托管账户信息
        reqMap.put("CARDNBR", uf.getUsrcustid()); // 电子账号CARDNBR
        reqMap.put("PINFLAG", "0"); // 使用密码标志PINFLAG——（0：不使用密码 、1：使用消费密码、2：使用查询密码）
        reqMap.put("PIN", ""); // 密码PIN
        reqMap.put("SERI_NO", HSignUtil.COINSTCODE+StringUtil.getNo()); // 必填；用于交易的唯一性标识，需前台保证唯一性;本撤销交易的申请流水号;六位合作单位编号+各平台流水号生成规则
        reqMap.put("OLDSEQNO", ut.getOrderno()); // 原投标交易申请流水号
        reqMap.put("AMOUNT", df1.format(ut.getRealamount())); // 投标金额
        reqMap.put("FUISSUER", HSignUtil.FUISSUER); // 产品发行方
        TenderItem item = tenderItemService.findTenderItemById(ut.getTenderid()); // 查询标的详情
        reqMap.put("PRODUCT", item.getTno()); // 标的编号
		String result = null;
		try {
			result = HSignUtil.HttpClientTask(reqMap, "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return callBack(result, ut);
	}
	
	/**
	 * 徽商银行投标撤销请求数据加签加密并发送HttpClient请求
	 * @Title: HttpClientTask
	 * @Description: TODO(徽商银行投标撤销请求数据加签加密并发送HttpClient请求)
	 * @param reqMap
	 * @param version
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 * @return String    返回类型
	 */
	/*private String HttpClientTask(LinkedHashMap<String, String> reqMap,String version) throws UnsupportedEncodingException, Exception{
		String TaskResult = "";
		if(reqMap.size() > 0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("BANKCODE",reqMap.get("BANKCODE")); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
			map.put("COINSTCODE",reqMap.get("COINSTCODE")); // 合作单位渠道COINSTCHANNEL——000002
			map.put("APIVERSION",version); // 为报文版本号1、5874/5875的版本号为：v20160727；2、7601/5810/5812的版本号为：v20160907；3、5864/5913的版本号可送：v20161215；4、其他接口送的版本号为：v20160602。
			String sign = HSignUtil.getRASSign(reqMap); // 加签名
			System.out.println(sign);
			reqMap.put("SIGN", sign);
			String data = HSignUtil.getEncryptRSAByte(reqMap); // 进行摘要并对摘要进行加密
			map.put("reqMap",data); // 加密后的json参数
			TaskResult = HSignUtil.doHttpClient(map); // 发送HttpClient请求
		}
		return TaskResult;
	}*/
	
	/**
	 * 拼接徽商银行投标撤销返回数据验证签名并修改用户银行卡信息
	 * @Title: callBack
	 * @Description: TODO(拼接徽商银行投标撤销返回数据验证签名并修改用户银行卡信息)
	 * @param respoResult
	 * @return void    返回类型
	 */
	private String callBack(String respoResult, UserTender userTender){
		String result = HSignUtil.getDecryptRSAByte(respoResult); // 接受数据后进行解密
		System.out.println(result);
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
		String FUISSUER = jsonObject.getString("FUISSUER"); // 产品发行方
		String PRMNO = jsonObject.getString("PRMNO"); // 标的编号
		String FRZAMT = jsonObject.getString("FRZAMT"); // 投标金额
		String FORINCOME = jsonObject.getString("FORINCOME"); // 逾期收益
		String BUYDATE = jsonObject.getString("BUYDATE"); // 投标日期
		String STATE = jsonObject.getString("STATE"); // 记录状态
		String BOSAMT = jsonObject.getString("BOSAMT"); // 抵扣红包金额
		String RESERVED = jsonObject.getString("RESERVED"); // 保留域

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
		buffer.append(StringUtils.trimToEmpty(FUISSUER));
		buffer.append(StringUtils.trimToEmpty(PRMNO));
		buffer.append(StringUtils.trimToEmpty(FRZAMT));
		buffer.append(StringUtils.trimToEmpty(FORINCOME));
		buffer.append(StringUtils.trimToEmpty(BUYDATE));
		buffer.append(StringUtils.trimToEmpty(STATE));
		buffer.append(StringUtils.trimToEmpty(BOSAMT));
		buffer.append(StringUtils.trimToEmpty(RESERVED));
		String str = buffer.toString();

		boolean verifyResult = HSignUtil.getVerify(str, responseSign);

		if (!verifyResult){
			System.out.println("验证签名失败...");
		} else {
			System.out.println("验证签名成功");
		}
		if ("00000000".equals(jsonObject.getString("RETCODE"))){
			System.out.println("操作成功");
			if(!userTender.getTstatus().equals((short) 3)){ // 防止重复更新数据
				updateTenderItem(userTender.getTenderid(), FRZAMT);
				updateInvestorAccount(CARDNBR, FRZAMT);
				thawPlus(userTender);
			}
    		// 更新投标记录
    		userTender.setTstatus(TenderRecord_Constant.TSTATUS_REVOKE); 	// 投标状态-3.撤销
			userTenderService.updateByPrimaryKeySelective(userTender);
		} else {
			System.out.println("操作失败,错误代码："+jsonObject.getString("RETCODE"));
		}
		return RETCODE;
	}

	/**
	 * 更新标的设置，已完成投标金额
	 * @param huifuParam
	 * @throws Exception 
	 */
	private void updateTenderItem(BigDecimal tid, String Amount) {
		// 修改标的设置表
		TenderItem item = tenderItemService.findTenderItemById(tid);	// 获得标的设置详情
		if(item.getFinishtamount()==null)
			item.setFinishtamount((double) 0);
		item.setFinishtamount(Arith.sub(item.getFinishtamount(), Double.valueOf(Amount)));	// 已完成投标金额=已完成金额+当前投标金额
		int count = 0;
		count = tenderItemService.update(item);
		if(count > 0){
			System.out.println("更新标的设置成功！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 更新投资人用户账户表-冻结余额
	 * @param huifuParam
	 */
	private void updateInvestorAccount(String usrcustid, String Amount) {
		UserAccount account = new UserAccount();
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(usrcustid);
		BigDecimal outAccountId = userFsAccountInfo.getBaseid();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(outAccountId); 												// 根据baseid查询用户账户信息
		double balance = userAccount.getBalance();																						// 用户总资产不变
		double avlbalance = Arith.add(userAccount.getAvlbalance(), Double.valueOf(Amount)); 											// 用户可用余额 = 用户可用余额+投标金额
		if(userAccount.getFreezebalance() == null)
			userAccount.setFreezebalance((double) 0);
		double freezeBalance = Arith.sub(userAccount.getFreezebalance(), Double.valueOf(Amount)); 										// 用户冻结余额 = 用户冻结余额-投标金额
		account.setBaseid(outAccountId);																								// baseId
		account.setBalance(balance);																									// 用户总资产
		account.setAvlbalance(avlbalance);																								// 可用余额
		account.setFreezebalance(freezeBalance);																						// 冻结余额
		int count = 0;
		count = userAccountService.updateUseraccount(account); // 根据basid更新用户账户表
		if(count > 0){
			System.out.println("更新投资人用户账户成功！！！！！！！！！！！！！！！！！！！！！！！！");
			System.out.println("账户总资产============================="+account.getBalance());
		}
	}

	/**
	 * 解冻增益
	 * @Title: thawPlus
	 * @Description: TODO()
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	private void thawPlus(UserTender userTender) {
		List<UserTenderPlusLink> links = userTenderPlusLinkService.findUserTenderPlusLinkByUtId(userTender.getId());
		if(links != null){
			for(UserTenderPlusLink utpl : links){
				BigDecimal icid = utpl.getIcid();
				if(icid != null){
					UserInterestRateCoupon coupon = userInterestRateCouponService.findCouponDetailById(icid);
					coupon.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
					userInterestRateCouponService.updateByPrimaryKeySelective(coupon);
				}
				BigDecimal reid = utpl.getReid();
				if(reid != null){
					UserRedEnvelope red = userRedEnvelopeService.findUserRedEnvelopeById(reid);
					red.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
					userRedEnvelopeService.updateByPrimaryKeySelective(red);
				}
			}
		}
	}
	
}
