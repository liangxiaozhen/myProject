package com.ptpl.controller.huifu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

 import com.huifu.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.MD5;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: AddBidInfoTest 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(发标 测试无用) 
* @author chenjiaming
* @date 2016年9月27日 上午9:40:45 
* @version V1.0
 */
@Controller
@RequestMapping("/huifu/AddBidInfoTest")
public class AddBidInfoTest extends BaseController{
	 
	//版本号 Version 必须 固定为 20，如版本升级，能向前兼容
	//消息类型 CmdId 变长 String 必须 每一种消息类型代表一种交易，此处为 AddBidInfo
	//商户客户号 MerCustId 变长 16 位的String 必须 商户的唯一标识
	//项目 ID ProId 变长 16 位的String 必须 标的的唯一标识，为英文和数字组合
	//标的名称 BidName 变长 50 位的 String 必须
    //标的类型 BidType 定长 2 位的 String 必须
			 /* 01：信用 
				02：抵押
				03：债权转让
				99：其他 */
	//发标金额 BorrTotAmt 变长 14 位的 String 必须 单位为元，精确到分，例如 1000.01
	//发标年化利率 YearRate 变长 14 位的String 必须 百分比，保留 2 位小数，例如 24.55
	//应还款总利息 RetInterest 变长 16 位的 String 必须 单位为元，精确到分，例如 1000.01（商户可传根据发标金额和利率计算的值）
	//最后还款日期 LastRetDate 定长 8 位的 String 必须 格式 yyyymmdd
	//计划投标开始日期 BidStartDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
	//计划投标截止日期 BidEndDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
	//借款期限 LoanPeriod 变长 20 位的 String 必须 例如：XX 天、 XX 月、 XX 年
	//还款方式 RetType 定长 2 位的 String 必须
			  /*01：一次 还本付息
				02：等额本金
				03：等额本息
				04：按期付息到期还本
				99：其他*/
	//应还款日期 RetDate 定长8位String 必须 格式 yyyymmdd
	//本息保障 GuarantType 定长 2 位的 String 可选
				/*01：保本保息
				02：保本不保息
				03：不保本不保息*/
	//标的产品类型 BidProdType 定长 2 位的 String 必须
				/*01：房贷类
				02：车贷类
				03：收益权转让类
				04：信用贷款类
				05：股票配资类
				06：银行承兑汇票
				07：商业承兑汇票
				08：消费贷款类
				09：供应链类
				99：其他*/
	//风险控制方式 RiskCtlType 定长 2 位的 String 可选
				/*01：抵（质）押
				02：共管账户
				03：担保
				04：信用无担保 
				99：其他*/
	//推荐机构 Recommer 变长 150 位的String 可选 文本
	//限定最低投标份数 LimitMinBidAmt 变长 7 位的 String 可选 整数
	//限定每份投标金额 LimitBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
	//限定最多投标金额 LimitMaxBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
	//限定最少投标金额 LimitMinBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
	//逾期概率 OverdueProba 变长 5 位的 String 可选 保留 2 位小数
	//逾期是否垫资 BidPayforState 定长 1 位的 String 可选
				/*1：是
				2：否*/
	//借款人类型 BorrType 定长 1 位的 String 必须
				/*01：个人
				02：企业*/
	//借款人 ID BorrCustId 变长 16 位的 String 必须 借款人的唯一标识
	//借款人名称 BorrName 变长 50 位的 String 必须 文本，借款人真实姓名或者借款企业名称
	//借款企业营业执照编号 BorrBusiCode 变长 30 位的 String 可选 借款人类型为企业时为必填
	//借款人证件类型 BorrCertType 定长 2 位的 String 可选 00：身份证（暂只支持身份证）借款人类型为“ 01：个人”时为必须参数
	//借款人证件号码 BorrCertId 变长 18 位的 String 可选 借款人类型为“ 01：个人”时为必须参数
	//借款人手机号码 BorrMobiPhone 变长 11 位的 String 必须
	//借款人固定电话 BorrPhone变长 12 位的String可选
	//借款人工作单位 BorrWork 变长 150 位的String 可选 文本
	//借款人工作年限 BorrWorkYear 变长 3 位的String 可选 单位为年，整数
	//借款人税后月收入 BorrIncome 变长 16 位的String可选 单位为元，保留 2 位小数
	//借款人学历 BorrEdu变长 20 位的String可选 文本
	//借款人婚姻状况 BorrMarriage定长 1 位的String 可选
				/*Y：已婚
				N：未婚*/
	//借款人地址 BorrAddr变长 150 位的String可选 文本
	//借款人电子邮箱 BorrEmail变长 30 位的String可选 文本
	//借款用途 BorrPurpose变长 150 位的String必须 文本
	//编码集 CharSet 变长 String 必须 加签验签的时候，商户需告知汇付其系统编码，汇付在验签的时候进行相应的编码转换验签。因字段中有中文，应为：GBK 或者 UTF-8如果是空，默认 UTF-8
	//商户后台应答地址 BgRetUrl变长 128 位的String必须通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID 字样的字符串，表明商户已经收到该笔交易结果。
	//商户私有域 MerPriv变长 120 位的String可选为商户的自定义字段，该字段在交易完成后由本平台原样返回。
	//入参扩展域 ReqExt变长 512 位的String可选 用于扩展请求参数
	//签名 ChkValue
	
	@RequestMapping("/doadd")
	public void doadd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		HuifuParams huifuParams = new HuifuParams();
 		huifuParams.setVersion("20");//版本号 Version 必须 固定为 20，如版本升级，能向前兼容
		huifuParams.setCmdId("AddBidInfo");//消息类型 CmdId 变长 String 必须 每一种消息类型代表一种交易，此处为 AddBidInfo
		//商户客户号 MerCustId 变长 16 位的String 必须 商户的唯一标识
		huifuParams.setProId("gjbd2016111014");//项目 ID ProId 变长 16 位的String 必须 标的的唯一标识，为英文和数字组合
		huifuParams.setBidName("贷款买车");//标的名称 BidName 变长 50 位的 String 必须
		huifuParams.setBidType("02");//	标的类型 BidType 定长 2 位的 String 必须
		 /* 01：信用 
			02：抵押
			03：债权转让
			99：其他 */
		huifuParams.setBorrTotAmt("1000.00");//发标金额 BorrTotAmt 变长 14 位的 String 必须 单位为元，精确到分，例如 1000.01
		huifuParams.setYearRate("0.12");//发标年化利率 YearRate 变长 14 位的String 必须 百分比，保留 2 位小数，例如 24.55
		huifuParams.setRetInterest("120.00");//应还款总利息 RetInterest 变长 16 位的 String 必须 单位为元，精确到分，例如 1000.01（商户可传根据发标金额和利率计算的值）
		huifuParams.setLastRetDate("20180927");//最后还款日期 LastRetDate 定长 8 位的 String 必须 格式 yyyymmdd
		huifuParams.setBidStartDate("20161110151503");//计划投标开始日期 BidStartDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
		huifuParams.setBidEndDate("20161125121506");//计划投标截止日期 BidEndDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
		huifuParams.setLoanPeriod("1年");//借款期限 LoanPeriod 变长 20 位的 String 必须 例如：XX 天、 XX 月、 XX 年
		huifuParams.setRetType("03");//还款方式 RetType 定长 2 位的 String 必须
		  /*01：一次 还本付息
			02：等额本金
			03：等额本息
			04：按期付息到期还本
			99：其他*/
		huifuParams.setRetDate("20161212");//应还款日期 RetDate 定长8位String 必须 格式 yyyymmdd
//本息保障 GuarantType 定长 2 位的 String 可选
			/*01：保本保息
			02：保本不保息
			03：不保本不保息*/
		huifuParams.setBidProdType("02");//标的产品类型 BidProdType 定长 2 位的 String 必须
			/*01：房贷类
			02：车贷类
			03：收益权转让类
			04：信用贷款类
			05：股票配资类
			06：银行承兑汇票
			07：商业承兑汇票
			08：消费贷款类
			09：供应链类
			99：其他*/
//风险控制方式 RiskCtlType 定长 2 位的 String 可选
			/*01：抵（质）押
			02：共管账户
			03：担保
			04：信用无担保 
			99：其他*/
//推荐机构 Recommer 变长 150 位的String 可选 文本
//限定最低投标份数 LimitMinBidAmt 变长 7 位的 String 可选 整数
//限定每份投标金额 LimitBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
//限定最多投标金额 LimitMaxBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
//限定最少投标金额 LimitMinBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
//逾期概率 OverdueProba 变长 5 位的 String 可选 保留 2 位小数
//逾期是否垫资 BidPayforState 定长 1 位的 String 可选
			/*1：是
			2：否*/
		huifuParams.setBorrType("01");//借款人类型 BorrType 定长 1 位的 String 必须
			/*01：个人
			02：企业*/
		huifuParams.setBorrCustId("6000060005590676");//借款人 ID BorrCustId 变长 16 位的 String 必须 借款人的唯一标识
		huifuParams.setBorrName("古冰");//借款人名称 BorrName 变长 50 位的 String 必须 文本，借款人真实姓名或者借款企业名称
//借款企业营业执照编号 BorrBusiCode 变长 30 位的 String 可选 借款人类型为企业时为必填
		huifuParams.setBorrCertType("00");//借款人证件类型 BorrCertType 定长 2 位的 String 可选 00：身份证（暂只支持身份证）借款人类型为“ 01：个人”时为必须参数
		huifuParams.setBorrCertId("22240019780516160X");//借款人证件号码 BorrCertId 变长 18 位的 String 可选 借款人类型为“ 01：个人”时为必须参数
		huifuParams.setBorrMobiPhone("13550769023");//借款人手机号码 BorrMobiPhone 变长 11 位的 String 必须
//借款人固定电话 BorrPhone变长 12 位的String可选
//借款人工作单位 BorrWork 变长 150 位的String 可选 文本
//借款人工作年限 BorrWorkYear 变长 3 位的String 可选 单位为年，整数
//借款人税后月收入 BorrIncome 变长 16 位的String可选 单位为元，保留 2 位小数
//借款人学历 BorrEdu变长 20 位的String可选 文本
//借款人婚姻状况 BorrMarriage定长 1 位的String 可选
			/*Y：已婚
			N：未婚*/
//借款人地址 BorrAddr变长 150 位的String可选 文本
//借款人电子邮箱 BorrEmail变长 30 位的String可选 文本
		huifuParams.setBorrPurpose("买车买房");//借款用途 BorrPurpose变长 150 位的String必须 文本
		//编码集 CharSet 变长 String 必须 加签验签的时候，商户需告知汇付其系统编码，汇付在验签的时候进行相应的编码转换验签。因字段中有中文，应为：GBK 或者 UTF-8如果是空，默认 UTF-8
		huifuParams.setBgRetUrl(StringUtil.getBasePath(request)+"/huifu/AddBidInfoTest/callback.action");//商户后台应答地址 BgRetUrl变长 128 位的String必须通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID 字样的字符串，表明商户已经收到该笔交易结果。
//商户私有域 MerPriv变长 120 位的String可选为商户的自定义字段，该字段在交易完成后由本平台原样返回。
//入参扩展域 ReqExt变长 512 位的String可选 用于扩展请求参数
//签名 ChkValue
		getParams(huifuParams, request, response);
	}
	
	public void getParams(HuifuParams huifuParams,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		/*Version+CmdId+MerCustId+ProId+BidType+BorrTotAmt+YearRate+RetInterest+LastRetDate+
		 * BidStartDate+BidEndDate+RetType+RetDate+GuarantType+BidProdType+RiskCtlType+
		 * LimitMinBidAmt+LimitBidSum+LimitMaxBidSum+LimitMinBidSum+BidPayforState+BorrType
		 *+BorrCustId+BorrBusiCode+BorrCertType+BorrCertId+BorrMobiPhone+BorrPhone+
		 *BorrWorkYear+BorrIncome+BorrMarriage+BorrEmail+CharSet+BgRetUrl+MerPriv+ReqExt*/
		StringBuffer bufferStr = new StringBuffer();
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getProId()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBidType()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrTotAmt()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getYearRate()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getRetInterest()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getLastRetDate()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBidStartDate()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBidEndDate()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getRetType()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getRetDate()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getGuarantType()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBidProdType()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getRiskCtlType()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getLimitMinBidAmt()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getLimitBidSum()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getLimitMaxBidSum()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getLimitMinBidSum()));
 		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBidPayforState()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrType()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrCustId()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrBusiCode()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrCertType()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrCertId()));
  		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrMobiPhone()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrPhone()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrWorkYear()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrIncome()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrMarriage()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBorrEmail()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getCharSet()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
		bufferStr.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
		String str = bufferStr.toString();
		String str1 = MD5.md5s(str);
		String ChkValue;
		try {
			ChkValue = SignUtils.encryptByRSA(str1);
			if(StringUtil.isNotEmpty(ChkValue)){
 				huifuParams.setChkValue(ChkValue);
 			}
		} catch (Exception e) {
 			e.printStackTrace();
		}
		 request.setAttribute("Version", huifuParams.getVersion());//版本号 Version 必须 固定为 20，如版本升级，能向前兼容
		 request.setAttribute("CmdId", huifuParams.getCmdId());//消息类型 CmdId 变长 String 必须 每一种消息类型代表一种交易，此处为 AddBidInfo
		 request.setAttribute("MerCustId", huifuParams.getMerCustId());//商户客户号 MerCustId 变长 16 位的String 必须 商户的唯一标识
		 request.setAttribute("ProId", huifuParams.getProId());//项目 ID ProId 变长 16 位的String 必须 标的的唯一标识，为英文和数字组合
		 request.setAttribute("BidName", huifuParams.getBidName());//标的名称 BidName 变长 50 位的 String 必须
		 request.setAttribute("BidType", huifuParams.getBidType());//标的类型 BidType 定长 2 位的 String 必须
			 /* 01：信用 
				02：抵押
				03：债权转让
				99：其他 */
		 request.setAttribute("BorrTotAmt", huifuParams.getBorrTotAmt());//发标金额 BorrTotAmt 变长 14 位的 String 必须 单位为元，精确到分，例如 1000.01
		 request.setAttribute("YearRate", huifuParams.getYearRate());//发标年化利率 YearRate 变长 14 位的String 必须 百分比，保留 2 位小数，例如 24.55
		 request.setAttribute("RetInterest", huifuParams.getRetInterest());//应还款总利息 RetInterest 变长 16 位的 String 必须 单位为元，精确到分，例如 1000.01（商户可传根据发标金额和利率计算的值）
		 request.setAttribute("LastRetDate", huifuParams.getLastRetDate());//最后还款日期 LastRetDate 定长 8 位的 String 必须 格式 yyyymmdd
		 request.setAttribute("BidStartDate", huifuParams.getBidStartDate());//计划投标开始日期 BidStartDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
		 request.setAttribute("BidEndDate", huifuParams.getBidEndDate());//计划投标截止日期 BidEndDate 定长 14 位的 String 必须 格式 yyyyMMddHHmmss
		 request.setAttribute("LoanPeriod", huifuParams.getLoanPeriod());//借款期限 LoanPeriod 变长 20 位的 String 必须 例如：XX 天、 XX 月、 XX 年
		 request.setAttribute("RetType", huifuParams.getRetType());//还款方式 RetType 定长 2 位的 String 必须
				  /*01：一次 还本付息
					02：等额本金
					03：等额本息
					04：按期付息到期还本
					99：其他*/
		 request.setAttribute("RetDate", huifuParams.getRetDate());//应还款日期 RetDate 定长8位String 必须 格式 yyyymmdd
		 request.setAttribute("GuarantType", huifuParams.getGuarantType());//本息保障 GuarantType 定长 2 位的 String 可选
					/*01：保本保息
					02：保本不保息
					03：不保本不保息*/
		 request.setAttribute("BidProdType", huifuParams.getBidProdType());//标的产品类型 BidProdType 定长 2 位的 String 必须
					/*01：房贷类
					02：车贷类
					03：收益权转让类
					04：信用贷款类
					05：股票配资类
					06：银行承兑汇票
					07：商业承兑汇票
					08：消费贷款类
					09：供应链类
					99：其他*/
		 request.setAttribute("RiskCtlType", huifuParams.getRiskCtlType());//风险控制方式 RiskCtlType 定长 2 位的 String 可选
					/*01：抵（质）押
					02：共管账户
					03：担保
					04：信用无担保 
					99：其他*/
		 request.setAttribute("Recommer", huifuParams.getRecommer());//推荐机构 Recommer 变长 150 位的String 可选 文本
		 request.setAttribute("LimitMinBidAmt", huifuParams.getLimitMinBidAmt());//限定最低投标份数 LimitMinBidAmt 变长 7 位的 String 可选 整数
		 request.setAttribute("LimitBidSum", huifuParams.getLimitBidSum());//限定每份投标金额 LimitBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
		 request.setAttribute("LimitMaxBidSum", huifuParams.getLimitMaxBidSum());//限定最多投标金额 LimitMaxBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
		 request.setAttribute("LimitMinBidSum", huifuParams.getLimitMinBidSum());//限定最少投标金额 LimitMinBidSum 变长 16 位的 String 可选 单位为元，精确到分，例如 1000.01
		 request.setAttribute("OverdueProba", huifuParams.getOverdueProba());//逾期概率 OverdueProba 变长 5 位的 String 可选 保留 2 位小数
		 request.setAttribute("BidPayforState", huifuParams.getBidPayforState());//逾期是否垫资 BidPayforState 定长 1 位的 String 可选
					/*1：是
					2：否*/
		 request.setAttribute("BorrType", huifuParams.getBorrType());//借款人类型 BorrType 定长 1 位的 String 必须
					/*01：个人
					02：企业*/
		 request.setAttribute("BorrCustId", huifuParams.getBorrCustId());//借款人 ID BorrCustId 变长 16 位的 String 必须 借款人的唯一标识
		 request.setAttribute("BorrName", huifuParams.getBorrName());//借款人名称 BorrName 变长 50 位的 String 必须 文本，借款人真实姓名或者借款企业名称
		 request.setAttribute("BorrBusiCode", huifuParams.getBorrBusiCode());//借款企业营业执照编号 BorrBusiCode 变长 30 位的 String 可选 借款人类型为企业时为必填
		 request.setAttribute("BorrCertType", huifuParams.getBorrCertType());//借款人证件类型 BorrCertType 定长 2 位的 String 可选 00：身份证（暂只支持身份证）借款人类型为“ 01：个人”时为必须参数
		 request.setAttribute("BorrCertId", huifuParams.getBorrCertId());//借款人证件号码 BorrCertId 变长 18 位的 String 可选 借款人类型为“ 01：个人”时为必须参数
		 request.setAttribute("BorrMobiPhone", huifuParams.getBorrMobiPhone());//借款人手机号码 BorrMobiPhone 变长 11 位的 String 必须
		 request.setAttribute("BorrPhone", huifuParams.getBorrPhone());//借款人固定电话 BorrPhone变长 12 位的String可选
		 request.setAttribute("BorrWork", huifuParams.getBorrWork());//借款人工作单位 BorrWork 变长 150 位的String 可选 文本
		 request.setAttribute("BorrWorkYear", huifuParams.getBorrWorkYear());//借款人工作年限 BorrWorkYear 变长 3 位的String 可选 单位为年，整数
		 request.setAttribute("BorrIncome", huifuParams.getBorrIncome());//借款人税后月收入 BorrIncome 变长 16 位的String可选 单位为元，保留 2 位小数
		 request.setAttribute("BorrEdu", huifuParams.getBorrEdu());//借款人学历 BorrEdu变长 20 位的String可选 文本
		 request.setAttribute("BorrMarriage", huifuParams.getBorrMarriage());//借款人婚姻状况 BorrMarriage定长 1 位的String 可选
					/*Y：已婚
					N：未婚*/
		 request.setAttribute("BorrAddr", huifuParams.getBorrAddr());//借款人地址 BorrAddr变长 150 位的String可选 文本
		 request.setAttribute("BorrEmail", huifuParams.getBorrEmail());//借款人电子邮箱 BorrEmail变长 30 位的String可选 文本
		 request.setAttribute("BorrPurpose", huifuParams.getBorrPurpose());//借款用途 BorrPurpose变长 150 位的String必须 文本
		 request.setAttribute("CharSet", huifuParams.getCharSet());//编码集 CharSet 变长 String 必须 加签验签的时候，商户需告知汇付其系统编码，汇付在验签的时候进行相应的编码转换验签。因字段中有中文，应为：GBK 或者 UTF-8如果是空，默认 UTF-8
		 request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());//商户后台应答地址 BgRetUrl变长 128 位的String必须通过后台异步通知，商户网站都应在应答接收页面输出 RECV_ORD_ID 字样的字符串，表明商户已经收到该笔交易结果。
		 request.setAttribute("MerPriv", huifuParams.getMerPriv());//商户私有域 MerPriv变长 120 位的String可选为商户的自定义字段，该字段在交易完成后由本平台原样返回。
		 request.setAttribute("ReqExt", huifuParams.getReqExt());//入参扩展域 ReqExt变长 512 位的String可选 用于扩展请求参数
		 request.setAttribute("ChkValue", huifuParams.getChkValue());//签名 ChkValue
 		 request.getRequestDispatcher("/WEB-INF/pages/test/AddBidInfoTest.jsp").forward(request, response);
 	}
		
		@RequestMapping("/callback")
		public String callback(HuifuParams huifuParams){
			System.out.println("=========CmdId=============="+huifuParams.getCmdId());
			System.out.println("=========RespCode=============="+huifuParams.getRespCode());
			System.out.println("=========RespDesc=============="+huifuParams.getRespDesc());
			System.out.println("=========MerCustId=============="+huifuParams.getMerCustId());
			System.out.println("=========ProId=============="+huifuParams.getProId());
			System.out.println("=========AuditStat=============="+huifuParams.getAuditStat());
			System.out.println("=========AuditDesc=============="+huifuParams.getAuditDesc());
			System.out.println("=========BgRetUrl=============="+huifuParams.getBgRetUrl());
			System.out.println("=========MerPriv=============="+huifuParams.getMerPriv());
			System.out.println("=========RespExt=============="+huifuParams.getRespExt());
			System.out.println("=========ChkValue=============="+huifuParams.getVersion());
 			
		//消息类型 CmdId 变长 String 必须每一种消息类型代表一种交易，此处为AddBidInfo
		//应答返回码 RespCode定长 3 位的String必须000：调用成功
		 //应答描述 RespDesc 变长 String 必须 返回码的对应中文描述
		//商户客户号 MerCustId变长 16 位的String必须 商户的唯一标识
		//项目 ID ProId变长 16 位的String必须 标的的唯一标识
		//审核状态 AuditStat定长 2 位的String必须
			/*01：通过
			02：拒绝
			03：待上传证照
			04：待审核
			05：待审核证照
			06：状态异常*/
		//根据恒丰返回的：审核通过、审核拒绝，待审
		//核相应返回该字段
		//审核状态描述 AuditDesc变长 256 位的 String可选银行返回标的审查结果为“拒绝”的话会返回拒绝原因。
		//商户后台应答地址 BgRetUrl变长 128 位的String必须通过后台异步通知，商户网站都应在应答接收
 		//商户私有域 MerPriv变长 120 位的String可选
		//返参扩展域 RespExt变长 512 位的String可选 用于扩展返回参数
		//签名 ChkValue String必须
		//CmdId+RespCode+MerCustId+ProId+
		//AuditStat+BgRetUrl+MerPriv+RespExt
		//先对此明文串做 md5 加密，再将 md5 加密后
		//的密文做汇付的 RSA 验签
			return null;
		}
 }
