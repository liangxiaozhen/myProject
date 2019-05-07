package com.ptpl.web.util;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.Enum.AcccountChangeType;
import com.ptpl.model.Enum.BusinessType;
import com.ptpl.model.Enum.DepositStatus;
import com.ptpl.model.Enum.DepositType;
import com.ptpl.model.Enum.ImgageType;
import com.ptpl.model.Enum.PayType;
import com.ptpl.model.Enum.ProfessionType;
import com.ptpl.model.Enum.SelectAmountType;
import com.ptpl.model.Enum.TradeStatus;
import com.ptpl.model.Enum.TradeType;
import com.ptpl.model.Enum.TransferBankType;
import com.ptpl.model.Enum.TransferType;
import com.ptpl.model.Enum.WithDrawStatus;

public class StateUtils {
	/**
	 * 获取用户是否认证
	 * 
	 * @return
	 */
	public String getIsReallyState(int local) {
		String stateName = null;
		switch (local) {
		case 0:
			stateName = "已认证";
			break;
		case 1:
			stateName = "未认证";
			break;
		case 2:
			stateName = "申请认证";
			break;
		case 3:
			stateName = "认证拒绝";
			break;
		default:
			stateName = "";
			break;
		}
		return stateName;
	}

	/**
	 * 获取交易类型
	 * 
	 * @return
	 */
	public String getTradeTypeState(String local) {
		if (local.equals("Secured")) {
			return "担保交易";
		}
		if (local.equals("PromptArrival")) {
			return "即时到账";
		}
		return "";
	}

	/**
	 * 获取密保问题
	 */
	public String getQuestion(int ques) {
		String question = null;
		 ServletRequestAttributes attr = (ServletRequestAttributes) 
	                RequestContextHolder.currentRequestAttributes(); 
		HttpServletRequest request = attr.getRequest();
		HttpSession session = request.getSession();
		UserAccountSafeInfo safe = (UserAccountSafeInfo) session
				.getAttribute("accountSafeInfo");

		switch (ques) {
		case 0:
			question = safe.getCustquestion();
			break;// 自定义问题
		case 1:
			question = "我爸爸的名字是？";
			break;
		case 2:
			question = "我妈妈的名字是？";
			break;
		case 3:
			question = "我爸爸的生日是？";
			break;
		case 4:
			question = "我妈妈的生日是？";
			break;
		case 5:
			question = "我妻子的名字是？";
			break;
		case 6:
			question = "我丈夫的名字是？";
			break;
		case 7:
			question = "我的出生地？";
			break;
		case 8:
			question = "我的小学校名？";
			break;
		}
		return question;
	}

	public String getProfessionState(ProfessionType local) {
		String p = "";
		switch (local) {
		case Seller:
			p = "销售/零售/采购等业务员";
			break;
		case Dining:
			p = "餐饮/旅游/美容/家政/教育等服务人员";
			break;
		case IT:
			p = "IT/教育/律师/化工/机械/设计等技术人员";
			break;
		case Servants:
			p = "公务员";
			break;
		case Administrative:
			p = "行政/后勤人员";
			break;
		case Agriculture:
			p = "农/林/牧/渔业劳动者";
			break;
		case ProductionTransport:
			p = "生产/运输工人";
			break;
		case Other:
			p = "其他（包括学生等）";
			break;
		}
		return p;
	}

	public String getTransferBankType(TransferBankType local) {
		String p = "";
		switch (local) {
		case Pending:
			p = "<span style='color:red'>等待付款</span>";
			break;
		case Success:
			p = "<span style='color:green'>成功</span>";
			break;
		case Failed:
			p = "<span style='color:red'>失败</span>";
			break;
		case Processing:
			p = "<span style='color:#bf6000'>处理中</span>";
			break;
		case Returned:
			p = "<span style='color:red'>已退回</span>";
			break;
		case Audit:
			p = "<span style='color:blue'>已审核</span>";
			break;
		case ProcessBank:
			p = "<span style='color:blue'>已处理</span>";
			break;
		case BankComSuccess:
			p = "<span style='color:blue'>成功（B）</span>";
			break;
		case BankComAudit:
			p = "<span style='color:green'>已审核（B）</span>";
			break;
		case Abnormal01:
			p = "<span style='color:red'>处理中异常（B）</span>";
			break;
		case Abnormal02:
			p = "<span style='color:red'>结果异常（B）</span>";
			break;
		case Checked:
			p = "<span style='color:red'>已核对（B）</span>";
			break;
		}
		return p;
	}

	public String getWithDrawStatusMerchant(WithDrawStatus local) {
		String p = "";
		switch (local) {
		case Pending:
			p = "<span style='color:green'>处理中</span>";
			break;
		case Sucess:
			p = "<span style='color:blue'>成功</span>";
			break;
		case Audit:
			p = "<span style='color:green'>处理中</span>";
			break;
		case Return:
			p = "已退回";
			break;
		case PlatformAudit:
			p = "<span style='color:green'>待平台审核</span>";
			break;
		case BankComSucess:
			p = "<span style='color:blue'>成功（B）</span>";
			break;
		case BankComAudit:
			p = "<span style='color:green'>已审核（B）</span>";
			break;
		case Abnormal01:
			p = "<span style='color:red'>处理异常（B）</span>";
			break;
		case Abnormal02:
			p = "<span style='color:red'>结果异常（01B）</span>";
			break;
		case Checked:
			p = "<span style='color:blue'>已核对（02B）</span>";
			break;
		}
		return p;
	}
	
	public String getWithDrawStatusMerchant(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "<span style='color:green'>处理中</span>";
			break;
		case 1:
			p = "<span style='color:blue'>成功</span>";
			break;
		case 2:
			p = "<span style='color:green'>处理中</span>";
			break;
		case 3:
			p = "已退回";
			break;
		case 4:
			p = "<span style='color:green'>待平台审核</span>";
			break;
		case 5:
			p = "<span style='color:blue'>成功</span>";
			break;
		case 6:
			p = "<span style='color:green'>已审核</span>";
			break;
		case 7:
			p = "<span style='color:red'>异常</span>";
			break;
		case 8:
			p = "<span style='color:red'>异常</span>";
			break;
		case 9:
			p = "<span style='color:blue'>已核对</span>";
			break;
		}
		return p;
	}

	public String getWithDrawStatus(WithDrawStatus local) {
		String p = "";
		switch (local) {
		case Pending:
			p = "已申请";
			break;
		case Sucess:
			p = "成功";
			break;
		case Audit:
			p = "已审核";
			break;
		case Return:
			p = "已退回";
			break;
		case PlatformAudit:
			p = "待平台审核";
			break;
		case BankComSucess:
			p = "<span style='color:blue'>成功（B）</span>";
			break;
		case BankComAudit:
			p = "<span style='color:green'>已审核（B）</span>";
			break;
		case Abnormal01:
			p = "<span style='color:red'>处理异常（01B）</span>";
			break;
		case Abnormal02:
			p = "<span style='color:red'>结果异常（02B）</span>";
			break;
		case Checked:
			p = "<span style='color:blue'>已核对（B）</span>";
			break;
		}
		return p;
	}
	
	public String getWithDrawStatus(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "已申请";
			break;
		case 1:
			p = "成功";
			break;
		case 2:
			p = "已审核";
			break;
		case 3:
			p = "已退回";
			break;
		case 4:
			p = "待平台审核";
			break;
		case 5:
			p = "成功（B）";
			break;
		case 6:
			p = "已审核（B）";
			break;
		case 7:
			p = "处理异常（01B）";
			break;
		case 8:
			p = "结果异常（02B）";
			break;
		case 9:
			p = "已核对（B）";
			break;
		}
		return p;
	}

	public String getDepositStatusType(DepositStatus local) {
		String p = "";
		switch (local) {
		case pending:
			p = "待处理";
			break;
		case success:
			p = "成功";
			break;
		case failed:
			p = "失败";
			break;
		case cancel:
			p = "取消";
			break;
		}
		return p;
	}

	public String getAmountType(SelectAmountType local) {
		String p = "";
		switch (local) {
		case oneDay:
			p = "单天";
			break;
		case sumDay:
			p = "总计";
			break;

		}
		return p;
	}

	public String getDepositBankType(DepositType local) {
		String p = "";
		switch (local) {
		case other:
			p = "网银(银联支付)";
			break;
		case advice:
			p = "后台转入";
			break;
		case post:
			p = "post直连";
			break;
		case icbc:
			p = "工商银行";
			break;
		case abc:
			p = "农业银行";
			break;
		case bocsh:
			p = "中国银行";
			break;
		case ccb:
			p = "建设银行";
			break;
		case cmb:
			p = "招商银行";
			break;
		case spdb:
			p = "浦发银行";
			break;
		case gdb:
			p = "广发银行";
			break;
		case bocom:
			p = "交通银行";
			break;
		case psbc:
			p = "邮政储蓄银行";
			break;
		case cncb:
			p = "中信银行";
			break;
		case cmbc:
			p = "民生银行";
			break;
		case ceb:
			p = "光大银行";
			break;
		case hxb:
			p = "华夏银行";
			break;
		case cib:
			p = "兴业银行";
			break;
		case bos:
			p = "上海银行";
			break;
		case srcb:
			p = "上海农商";
			break;
		case pab:
			p = "平安银行";
			break;
		case bccb:
			p = "北京银行";
			break;
		case boc:
			p = "中行";
			break;
		case NKSettlement:
			p = "结算充值";
			break;
		case DKDeposit:
			p = "代扣充值";
			break;
		case DKDepositFee:
			p = "代扣充值手续费";
			break;
		case fastpay:
			p = "快捷支付";
			break;
		case remit:
			p = "汇款充值";
			break;
		case remitfee:
			p = "汇款充值手续费";
			break;
		case fastpayfee:
			p = "快捷支付手续费";
			break;
		case b2b:
			p = "企业网银";
			break;
		case b2bfee:
			p = "企业网银手续费";
			break;
		case withdrawsdiscount:
			p = "提现分润";
			break;
		}
		return p;
	}

	public String getloginStatus(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "邮箱";
			break;
		case 1:
			p = "手机";
			break;
		case 2:
			p = "邮箱/手机";
			break;
		}
		return p;
	}

	public String getaccountStatus(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "个人";
			break;
		case 1:
			p = "企业";
			break;
		case 2:
			p = "个人护照";
			break;
		}
		return p;
	}

	public String getUserStatus(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "未冻结";
			break;
		case 1:
			p = "已冻结";
			break;
		}
		return p;
	}

	public String getIsReallyStatus(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "实名认证";
			break;
		case 1:
			p = "未认证";
			break;
		}
		return p;
	}

	/**
	 * 账户余额变动类型
	 */
	public String getAcccountChangeType(AcccountChangeType local) {
		String p = "";
		switch (local) {
		case Add:
			p = "收入";
			break;
		case Subtract:
			p = "支出";
			break;
		}
		return p;
	}

	/**
	 * 业务类型
	 */
	public String getBusinessType(BusinessType local) {
		String p = "";
		switch (local) {
		case AccountTransfer:
			p = "账户转账";
			break;
		case BankTransfer:
			p = "银行卡转账";
			break;
		case WithDraw:
			p = "提现";
			break;
		case Deposit:
			p = "充值";
			break;

		}
		return p;
	}

	/**
	 * 推广Id
	 */
	public String getSpreadId(String spreadId) {
		String p = AES.getEncrypt(spreadId);
		return p;
	}

	/**
	 * 图片类型
	 */
	public String getImgageType(ImgageType local) {
		String p = "";
		switch (local) {
		case IDCARD:
			p = "身份证";
			break;
		case BusinessLicense:
			p = "营业执照";
			break;
		case PassPort:
			p = "护照";
			break;
		}
		return p;
	}

	/**
	 * 付款状态
	 */
	public String getTradeStatusType(TradeStatus local) {
		String p = "";
		switch (local) {
		case NoPayment:
			p = "未付款";
			break;
		case Success:
			p = "已付款";
			break;
		case ConfirmSuccess:
			p = "确认付款";
			break;
		case CloseTrade:
			p = "交易关闭";
			break;

		}
		return p;
	}
	
	/**
	 * 付款状态(数字)
	 */
	public String getTradeStatusTypeByNum(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "未付款";
			break;
		case 1:
			p = "已付款";
			break;
		case 2:
			p = "确认付款";
			break;
		case 3:
			p = "交易关闭";
			break;

		}
		return p;
	}
	
	/**
	 * 到账状态(数字)
	 */
	public String getTransferedByNum(int local) {
		String p = "";
		switch (local) {
		case 0:
			p = "未到账";
			break;
		case 1:
			p = "已到账";
			break;
		}
		return p;
	}

	/**
	 * 付款类型
	 */
	public String getPayTypeType(PayType local) {
		String p = "";
		switch (local) {
		case Balance:
			p = "余额付款";
			break;
		case Netbank:
			p = "网银";
			break;
		case CreditCard:
			p = "信用卡";
			break;

		}
		return p;
	}

	/**
	 * 交易类型
	 */
	public String getTradeType(TradeType local) {
		String p = "";
		switch (local) {
		case Secured:
			p = "担保交易";
			break;
		case PromptArrival:
			p = "即时到帐";
			break;

		}
		return p;
	}

	/**
	 * 转账到银行卡类型
	 */
	public String getTransferType(TransferType local) {
		String p = "";
		switch (local) {
		case CreditCard:
			p = "信用卡还款";
			break;
		case BankCard:
			p = "转账到银行卡";
			break;
		case APIXiafa:
			p = "API提现";
			break;
		case Xiafa:
			p = "下发到银行卡";
			break;
		}
		return p;
	}

	/**
	 * 网贷平台 操作类型
	 */
	public String getLoanTypeNoAES(int local)
	{
		String p = "";
		switch (local)
		{
			case 1:
				p = "即时转账";
				break;
			case 2:
				p = "冻结转账";
				break;
			case 3:
				p = "解冻";
				break;
			case 4:
				p = "退款";
				break;
		}
		return p;
	}
	
	/**
	 * 网贷平台 操作类型 加密
	 */
	public String getLoanType(String local)
	{
		int il = ConvertUtil.getInt(local, -1);
		if (il <= 0)
		{
			local = AES.getDecrypt(local);
		}
		il = ConvertUtil.getInt(local, -1);
		String p = "";
		switch (il)
		{
			case 1:
				p = "即时转账";
				break;
			case 2:
				p = "冻结转账";
				break;
			case 3:
				p = "解冻";
				break;
			case 4:
				p = "退款";
				break;
		}
		return p;
	}
	
	/**
	 * 网贷平台 提现审核状态
	 */
	public String getWithDrawAuditStatus(String local)
	{
		int il = ConvertUtil.getInt(local, -1);
		String p = "";
		switch (il)
		{
			case -1:
				p = "不审核";
				break;
			case 0:
				p = "平台审核中";
				break;
			case 1:
				p = "平台通过";
				break;
			case 2:
				p = "平台退回";
				break;
		}
		return p;
	}
	
	/**
	 * 网贷平台 分润结算状态
	 */
	public String getDiscountSettleStatus(String local)
	{
		int il = ConvertUtil.getInt(local, -1);
		String p = "";
		switch (il)
		{
			case -1:
				p = "未结算";
				break;
			case 1:
				p = "已结算";
				break;
		}
		return p;
	}
	
	/**
	 * 根据银行缩写 获取图片名
	 */
	public String getBankPicName(String bankcodeen)
	{
		if(StringUtils.isBlank(bankcodeen))
		{
			return "";
		}
		
		if(bankcodeen.equals("BOC"))
		{
			return "bocsh";
		}
		else if(bankcodeen.equals("ICBC"))
		{
			return "icbc";
		}
		else if(bankcodeen.equals("ABC"))
		{
			return "abc";
		}
		else if(bankcodeen.equals("BCM"))
		{
			return "bocom";
		}
		else if(bankcodeen.equals("CCB"))
		{
			return "ccb";
		}
		else if(bankcodeen.equals("SPDB"))
		{
			return "spdb";
		}
		else if(bankcodeen.equals("CMB"))
		{
			return "cmb";
		}
		else if(bankcodeen.equals("CMBC"))
		{
			return "cmbc";
		}
		else if(bankcodeen.equals("CITIC"))
		{
			return "cncb";
		}
		else if(bankcodeen.equals("HXB"))
		{
			return "hxb";
		}
		else if(bankcodeen.equals("CEB"))
		{
			return "ceb";
		}
		else if(bankcodeen.equals("BOS"))
		{
			return "bos";
		}
		else if(bankcodeen.equals("HZB"))
		{
			return "hzcb";
		}
		else if(bankcodeen.equals("NBCB"))
		{
			return "nbbank";
		}
		else
		{
			return "";
		}
	}
	public static String getBankName(String bankName) {
		String p = "";
		switch (bankName) {
		case "ICBC":
			p = "工商银行";
			break;
		case "ABC":
			p = "农业银行";
			break;
		case "BOCSH":
			p = "中国银行";
			break;
		case "CCB":
			p = "建设银行";
			break;
		case "CMB":
			p = "招商银行";
			break;
		case "SPDB":
			p = "浦发银行";
			break;
		case "GDB":
			p = "广发银行";
			break;
		case "BOCOM":
			p = "交通银行";
			break;
		case "PSBC":
			p = "邮政储蓄银行";
			break;
		case "CNCB":
			p = "中信银行";
			break;
		case "CMBC":
			p = "民生银行";
			break;
		case "CEB":
			p = "光大银行";
			break;
		case "HXB":
			p = "华夏银行";
			break;
		case "CIB":
			p = "兴业银行";
			break;
		case "BOS":
			p = "上海银行";
			break;
		case "SRCB":
			p = "上海农商";
			break;
		case "PAD":
			p = "平安银行";
			break;
		case "BCCB":
			p = "北京银行";
			break;
		case "BOC":
			p = "中行";
			break;
		}
		return p;
	}
	//收支记录费用类型
	public static String getUserAccountInExType(String type){
		String p = "";
		Integer no = 0;
		if(!StringUtil.isNullStr(type)){
			no = Integer.valueOf(type);
		}
		switch (no) {
		case 1:
			p = "充值";
			break;
		case 2:
			p = "充值手续费";
			break;
		case 3:
			p = "提现";
			break;
		case 4:
			p = "提现手续费";
			break;
		case 5:
			p = "投标";
			break;
		case 6:
			p = "还款";
			break;
		case 7:
			p = "会员升级费";
			break;
		case 8:
			p = "提现服务费";
			break;
		case 9:
			p = "站岗利息";
			break;
		case 10:
			p = "流标补偿";
			break;
		case 11:
			p = "逾期罚金";
			break;
		case 12:
			p = "提前还款奖励";
			break;
		case 13:
			p = "提前还款罚金";
			break;
		case 14:
			p = "债转手续费";
			break;
		case 15:
			p = "居间费";
			break;
		case 16:
			p = "担保费";
			break;
		case 17:
			p = "利息管理费";
			break;
		case 18:
			p = "保证金";
			break;
		case 19:
			p = "假现金利息";
			break;
		case 20:
			p = "类现金利息";
			break;
		case 21:
			p = "加息券利息";
			break;
		case 22:
			p = "本金利息";
			break;
		case 23:
			p = "类现金";
			break;
		case 24:
			p = "本金";
			break;
		case 25:
			p = "逾期滞纳金";
			break;
		case 26:
			p = "失效类现金";
			break;
		case 27:
			p = "失效滞纳金";
			break;
		case 28:
			p = "失效类现金滞纳金";
			break;
  		case 29:
			p = "失效类现金利息";
			break;
 		case 30:
 			p = "债转";
 			break;
 		case 31:
 			p = "现金";
 			break;
 		case 32:
 			p = "姓名匹配";
 			break;
		default:
			p = "未知";
			break; 
		}
		return p;
	}
}
