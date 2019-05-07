package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 乾多多提现银行卡绑定接口
 * @ClassName: MMMUserBindCardController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zhenglm
 * @date 2017年2月22日 下午2:13:01
 * http://219.133.11.55:8882/ptpjx/moneymoremore/UserBindCard/bindCard.action
 */
@Controller
@RequestMapping("/moneymoremore/UserBindCard")
public class MMMUserBindCardController extends BaseController{
	
	/** 用户银行卡信息 */
	@Autowired
	UserBankCardServiceI userBankCardService;
	
	/** 用户基本信息  ServiceI */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	
	/**
	 * 乾多多绑定银行卡请求
	 * @Title: bindCard
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @return void    返回类型
	 */
	@RequestMapping(value = "/bindCard",method = {RequestMethod.POST,RequestMethod.GET})
	public void bindCard(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("-----------发起乾多多提现银行卡绑定请求-----------");
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		
		String MoneymoremoreId = userFsAccountInfo.getMoneymoremoreid(); // 用户乾多多标识
		System.out.println("用户乾多多标识==="+MoneymoremoreId);
		String PlatformMoneymoremore = MMMParams.PlatformMoneymoremore; // 平台乾多多标识
		Integer Action = 2; // 操作类型-1.用户认证,2.提现银行卡绑定,3.代扣授权,4.取消代扣授权,5.汇款绑卡确认
		
// 		String CardNo = ""; // 汇款绑卡确认、取消代扣授权必填
// 		String WithholdBeginDate = ""; // 代扣开始日期（代扣授权选填）
// 		String WithholdEndDate = ""; // 代扣结束日期（代扣授权选填）	
// 		Double SingleWithholdLimit = null; // 单笔代扣限额（代扣授权选填）
// 		Double TotalWithholdLimit = null; // 代扣总限额（代扣授权选填）
		
		String RandomTimeStamp = ""; // 随机时间戳
		String Remark1 = userFsAccountInfo.getBaseid().toString(); // 自定义备注（这里我存放了用户baseid）
		System.out.println("baseid==="+Remark1);
		String Remark2 = ""; // 自定义备注
		String Remark3 = ""; // 自定义备注
		String ReturnURL = StringUtil.getBasePath(request)+"/moneymoremore/UserBindCard/bindCardReturnUrl.action"; // 页面返回网址
		String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/UserBindCard/bindCardCallBack.action"; // 后台通知网址
  		 
		/*MoneymoremoreId + PlatformMoneymoremore + Action + CardNo + 
		WithholdBeginDate + WithholdEndDate + SingleWithholdLimit + 
		TotalWithholdLimit + RandomTimeStamp + Remark1 + Remark2 + 
		Remark3 + ReturnURL + NotifyURL*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(MoneymoremoreId))
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(Action)
//				.append(StringUtils.trimToEmpty(CardNo))
//				.append(StringUtils.trimToEmpty(WithholdBeginDate))
//				.append(StringUtils.trimToEmpty(WithholdEndDate))
//				.append(SingleWithholdLimit)
//				.append(TotalWithholdLimit)
				.append(StringUtils.trimToEmpty(RandomTimeStamp))
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ReturnURL))
				.append(StringUtils.trimToEmpty(NotifyURL));
 		String plainStr = buffer.toString();
 		//私钥签名
 		String privateResult = "";
 		
 		RsaHelper rsa = RsaHelper.getInstance();
 		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
 		
  		request.setAttribute("MoneymoremoreId", MoneymoremoreId);
		request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
		request.setAttribute("Action", Action);
//		request.setAttribute("CardNo", CardNo);
//		request.setAttribute("WithholdBeginDate", WithholdBeginDate);
//		request.setAttribute("WithholdEndDate", WithholdEndDate);
//		request.setAttribute("SingleWithholdLimit", SingleWithholdLimit);
//		request.setAttribute("TotalWithholdLimit", TotalWithholdLimit);
		request.setAttribute("RandomTimeStamp", RandomTimeStamp);
		request.setAttribute("Remark1", Remark1);
		request.setAttribute("Remark2", Remark2);
		request.setAttribute("Remark3", Remark3);
		request.setAttribute("ReturnURL", ReturnURL);
		request.setAttribute("NotifyURL", NotifyURL);
		request.setAttribute("SignInfo", privateResult);
		try {
			request.getRequestDispatcher("/WEB-INF/MMMPages/UserBindCard/UserBindCard.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
 			e.printStackTrace();
		}
	} 

	/**
	 * 乾多多提现银行卡绑定后台应答地址
	 * @Title: bindCardCallBack
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @throws Exception
	 * @return void    返回类型
	 */
	@RequestMapping(value= "/bindCardCallBack")
	public void bindCardCallBack(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("-----------进入乾多多提现银行卡绑定后台应答-----------");
		
 		RsaHelper rsa = RsaHelper.getInstance();
 		
		// 返回参数
		String MoneymoremoreId = request.getParameter("MoneymoremoreId"); // 用户乾多多标识
		System.out.println("用户乾多多标识======="+MoneymoremoreId);
		String PlatformMoneymoremore = request.getParameter("PlatformMoneymoremore"); // 平台乾多多标识
		System.out.println("平台乾多多标识======="+PlatformMoneymoremore);
		String Action = request.getParameter("Action"); // 操作类型-1.用户认证,2.提现银行卡绑定,3.代扣授权,4.取消代扣授权,5.汇款绑卡确认
		System.out.println("操作类型======="+Action);
		String CardType = request.getParameter("CardType"); // 银行卡类型（银行卡绑定、代扣授权、取消代扣授权、汇款绑卡确认必填）-0.借记卡、1.信用卡
		System.out.println("银行卡类型======="+CardType);
		String BankCode = request.getParameter("BankCode"); // 银行代码（银行卡绑定、代扣授权、取消代扣授权、汇款绑卡确认必填）
		System.out.println("银行代码======="+BankCode);
		String CardNo = rsa.decryptData(request.getParameter("CardNo"), RsaHelper.privateString); // 银行卡号（银行卡绑定、代扣授权、取消代扣授权、汇款绑卡确认必填）-原卡号需经过加密后抛送SignInfo中的卡号是未经加密的原卡号
		System.out.println("银行卡号======="+CardNo);
		String BranchBankName = request.getParameter("BranchBankName"); // 开户行支行名称（银行卡绑定、代扣授权、取消代扣授权、汇款绑卡确认必填）
		System.out.println("开户行支行名称代码======="+BranchBankName);
		String Province = request.getParameter("Province"); // 开户行省份（银行卡绑定、汇款绑卡确认必填）
		System.out.println("开户行省份代码======="+Province);
		String City = request.getParameter("City"); // 开户行城市（银行卡绑定、汇款绑卡确认必填）
		System.out.println("开户行城市代码======="+City);
//		String WithholdBeginDate = request.getParameter("WithholdBeginDate"); // 代扣开始日期（代扣授权选填）
//		String WithholdEndDate = request.getParameter("WithholdEndDate"); // 代扣结束日期（代扣授权选填）
//		String SingleWithholdLimit = request.getParameter("SingleWithholdLimit"); // 单笔代扣限额（代扣授权选填）
//		String TotalWithholdLimit = request.getParameter("TotalWithholdLimit"); // 代扣总限额（代扣授权选填）
		String RandomTimeStamp = request.getParameter("RandomTimeStamp"); // 随机时间戳
		String Remark1 = request.getParameter("Remark1"); // 自定义备注
		System.out.println("baseid=========="+Remark1);
		String Remark2 = request.getParameter("Remark2"); // 自定义备注
		String Remark3 = request.getParameter("Remark3"); // 自定义备注
		String ResultCode = request.getParameter("ResultCode"); // 返回码（88表示成功）
		System.out.println("返回码======="+ResultCode);
		String Message = request.getParameter("Message"); // 返回信息
		System.out.println("返回信息======="+Message);
		String ReturnTimes = request.getParameter("ReturnTimes"); // 返回次数
		System.out.println("返回次数======="+ReturnTimes);
		String SignInfo = request.getParameter("SignInfo"); // 签名信息
		System.out.println(SignInfo);
		BigDecimal baseid = new BigDecimal(Remark1); // 从自定义备注中获取用户baseid
        StringBuffer buffer = new StringBuffer();
        buffer.append(StringUtils.trimToEmpty(MoneymoremoreId))
        		.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
        		.append(StringUtils.trimToEmpty(Action))
        		.append(StringUtils.trimToEmpty(CardType))
        		.append(StringUtils.trimToEmpty(BankCode))
        		.append(StringUtils.trimToEmpty(CardNo))
        		.append(StringUtils.trimToEmpty(BranchBankName))
        		.append(StringUtils.trimToEmpty(Province))
        		.append(StringUtils.trimToEmpty(City))
//        		.append(StringUtils.trimToEmpty(WithholdBeginDate))
//        		.append(StringUtils.trimToEmpty(WithholdEndDate))
//        		.append(StringUtils.trimToEmpty(SingleWithholdLimit))
//        		.append(StringUtils.trimToEmpty(TotalWithholdLimit))
        		.append(StringUtils.trimToEmpty(RandomTimeStamp))
        		.append(StringUtils.trimToEmpty(Remark1))
        		.append(StringUtils.trimToEmpty(Remark2))
        		.append(StringUtils.trimToEmpty(Remark3))
        		.append(StringUtils.trimToEmpty(ResultCode));
        String plainStr = buffer.toString();
        System.out.println("乾多多提现银行卡绑定应答获取的返回参数拼接："+plainStr);
        boolean flag = false;
        try {
     		flag = rsa.verifySignature(SignInfo, plainStr, RsaHelper.publicString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag){ // 验证签名成功
        	if(ResultCode.equals("88")){ // 返回码88成功
        		// 保存返回参数到数据库
        		System.out.println("-----------乾多多提现银行卡绑定成功后保存到数据库-----------");
            	if(!CardNo.equals("") && userBankCardService.findDetailByCardNo(CardNo) == null){ // 没有相同的卡号
                	UserBankCard userBankCard = new UserBankCard();
                	userBankCard.setBaseid(baseid); // 用户ID
                	UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(baseid); // 根据id获取用户基本信息
                	if(userBaseAccountInfo.getAccounttype()==1){ // 用户类型为个人
                    	userBankCard.setCardtype(Constant.CARDTYPE_JIEJIKA); // 卡类型（1.信用卡，2.借记卡，3.企业账户）
                	}else{ // 用户类型为企业
                    	userBankCard.setCardtype(Constant.CARDTYPE_QIYE); // 卡类型（1.信用卡，2.借记卡，3.企业账户）
                	}
                	userBankCard.setBindtime(new Date()); // 绑定银行卡时间
                	userBankCard.setBankname(BankCode); // 银行
                	userBankCard.setUsername(userBaseAccountInfo.getRealname()); // 姓名
                	userBankCard.setCardno(CardNo); // 卡号
                	userBankCard.setProvince(Province); // 开户行省份代码
                	userBankCard.setCity(City); // 开户行城市代码
                	userBankCard.setSubbranch(BranchBankName); // 开户行支行名称
                	userBankCard.setBindmode(Constant.BINDMODE_JIEKOU); // 绑定方式  这里是接口绑定（ 1.接口，2.人工）
                	userBankCard.setIsfastbindcard(Constant.ISFASTBINDCARD_PUTONG); // 是否快捷绑卡（默认不开通）充值时可开通快捷卡（1.普通卡，2.快捷卡）
                	userBankCard.setBindstatus(Constant.BINDSTATUS_BIND); // 绑定状态（1.已绑定，2.已解绑）
                	userBankCard.setPaycompany("乾多多"); // 绑定通道
                	userBankCard.setRemark("乾多多银行卡绑定接口测试"); // 备注
                	userBankCard.setIsdefaultcard(Constant.ISDEFAULTCARD_NO); // 是否默认取现卡
                    System.out.println("银行名称：====================="+userBankCard.getBankname());
                	userBankCardService.insertSelective(userBankCard); // 保存用户银行卡信息
                    System.out.println("卡号：====================="+userBankCard.getCardno());
            	}
        	}
        	response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			try {
				// 后台通知结果后，将判断http响应码为200且页面上含有“SUCCESS”字符串方为通知成功
				response.getWriter().write("SUCCESS");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        if (!flag) {
            System.out.println("验证签名失败...");
        }
	}
	
	/**
	 * 乾多多提现银行卡绑定页面返回地址
	 * @Title: bindCardReturnUrl
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @return void    返回类型
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value= "/bindCardReturnUrl")
	public void bindCardReturnUrl(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		System.out.println("-----------进入乾多多提现银行卡绑定页面返回地址-----------");
		String ResultCode = request.getParameter("ResultCode");
		String Message = URLDecoder.decode(request.getParameter("Message"), "UTF-8");
		request.setAttribute("ResultCode", ResultCode);
		request.setAttribute("Message", Message);
		request.getRequestDispatcher("/WEB-INF/MMMPages/UserBindCard/UserBindCard_Success.jsp").forward(request, response);
	}
	
}
