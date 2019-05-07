package com.ptpl.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.huishang.QueryCardInformationController;
import com.ptpl.model.SMSSendRecord;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.service.SMSSendRecordServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.web.Iconstant.Iconstant;
import com.ptpl.web.util.CommonSendSMSUtil;
import com.ptpl.web.util.StringUtil;

/**
 * 用户后台银行卡管理
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/user/bankcard")
public class UserBankCardManagerController extends BaseController {

	@Autowired
	UserBankCardServiceI userBankCardService;
	
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	@Autowired
	SMSSendServiceI sMSSendServiceI;

	/**
	 * 进入银行卡管理页面
	 * @param card
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/queryBankCard")
//	public ModelAndView queryBankCardList(UserBankCard card) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		// 从session中获取当前登录用户基本信息
//		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
//		if(userBaseAccountInfo == null) { //用户未登录
//			mv.setViewName("user/login");
//		}else{
//			BigDecimal baseid = userBaseAccountInfo.getId(); // 获取用户基本信息id
//			// 从session中获取当前登录用户托管账户基本信息
//			UserFsAccountInfo userfsaccountinfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
//			if(userfsaccountinfo != null){
//				HuifuParams huifuparam = new HuifuParams();
//				huifuparam.setUsrCustId(userfsaccountinfo.getUsrcustid()); //从用户托管账户中获取用户客户号
//				System.out.println("用户客户号:=========="+huifuparam.getUsrCustId());
//				JSONArray json = doQueryCardInfo(huifuparam);
//				Iterator<Object> iterator = json.iterator();
//				while(iterator.hasNext()){
//					JSONObject json2 = (JSONObject) iterator.next();
//					String isdefault = json2.getString(HuifuParams_Constant.IsDefault); // 获取卡信息中默认卡标志
//					if(isdefault.equals("Y")){
//						String cardno = json2.getString(HuifuParams_Constant.CardId); // 获取汇付那边默认取现卡卡号
//						UserBankCard userbankcard = userBankCardService.findDetailByCardNo(cardno); // 根据汇付传过来的默认卡卡号查询数据库卡信息
//						if(userbankcard != null){
//							System.out.println("默认卡id=========="+userbankcard.getId());
//							System.out.println("默认卡baseid=========="+userbankcard.getBaseid());
//							userbankcard.setIsdefaultcard(Constant.ISDEFAULTCARD_YES);
//							UserBankCard defaultcard = userBankCardService.findIsDefaultCard(userbankcard); // 查询数据库原来的默认卡信息
//							if(defaultcard != null){
//								if(defaultcard.getCardno().trim().equals(cardno)){
//									System.out.println("没有修改默认卡==================="+defaultcard.getCardno());
//								}else{
//									System.out.println("修改默认卡===================");
//									userBankCardService.updateUserBankInfo(userbankcard); //修改默认取现卡
//									userbankcard.setIsdefaultcard(Constant.ISDEFAULTCARD_NO);
//									userBankCardService.updateDefaultCard(userbankcard); //将用户其他卡设置为非默认取现卡
//								}
//							}
//						}
//					}
//				}
//			}
//			card.setBaseid(baseid);
//			card.setBindstatus(Constant.BINDSTATUS_BIND);
//			List<UserBankCard> cardList = userBankCardService.findUserBankInfo(card); // 根据用户基本信息表ID和绑定状态查询银行卡信息
//			if(cardList != null){
//				for(UserBankCard bank : cardList){
//					if(bank.getCardno() != null){
//						bank.setCardno(bank.getCardno().replaceAll("(\\d{4})\\d*(\\d{4})","$1***********$2"));
//						if(bank.getPaycompany().equals("乾多多")){
//							bank.setBankname(MMMBankCode.getBankName(bank.getBankname()));
//						}
//						System.out.println("加****显示的卡号：=============="+bank.getCardno());
//					}
//				}
//				card.setIsfastbindcard(Constant.ISFASTBINDCARD_KUAIJIE);
//				UserBankCard isfastbindcard = userBankCardService.findIsFastBindCard(card); // 查询用户是否绑定快捷卡
//				mv.addObject("isfastbindcard", isfastbindcard);
//				mv.addObject("cardList", cardList);
//			}
//			Short isReally = userBaseAccountInfo.getIsreally();
//			String mobilephone = userBaseAccountInfo.getMobilephone();
//			String flagRe = "";
//			if(isReally == null || isReally == 0){
//				flagRe = "iden";
//			}else if(StringUtil.isEmpty(mobilephone)){
//				flagRe = "phone";
//			}
//			if(StringUtil.isNotEmpty(mobilephone)){
//				// 电话加密显示
//				userBaseAccountInfo.setMobilephonestr(userBaseAccountInfo.getMobilephone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
//				mv.addObject("mobilephoneStr", userBaseAccountInfo.getMobilephonestr());
//			}
//			mv.addObject("flagRe", flagRe);
//			mv.addObject("realName", userBaseAccountInfo.getRealname());
//			mv.setViewName("user/manager/bindcard/bindcardinfo");
//		}
//		return mv;
//	}

	/**
	 * 进入银行卡管理页面
	 * @Title: bindCard
	 * @Description: TODO(进入银行卡管理页面)
	 * @throws Exception    设定文件
	 * @return ModelAndView    返回类型
	 */
	@RequestMapping("/bindCard")
	public ModelAndView bindCard() throws Exception {
		ModelAndView mv = new ModelAndView();
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		if(userBaseAccountInfo != null) { //用户已登录
			BigDecimal baseid = userBaseAccountInfo.getId(); // 获取用户基本信息id
			UserBankCard boundCard = userBankCardService.selectBoundCardByBaseId(baseid);
			UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userBaseAccountInfo.getId());
			userFsAccountInfo = getDecryptionUserFsAccountInfoDetail(userFsAccountInfo);
			System.out.println(userBaseAccountInfo.getCertificationnumber());
			if(boundCard == null){
				List<UserBankCard> unboundCard = userBankCardService.selectUnBoundCardByBaseId(baseid);
				if(userFsAccountInfo != null && unboundCard.size()==0){
					System.out.println("————————查询更新用户银行卡信息————————");
					// 更新开户时绑定的银行卡信息
					QueryCardInformationController.doParams(request, response, userFsAccountInfo.getBaseid(), userFsAccountInfo.getUsrcustid());
					boundCard = userBankCardService.selectBoundCardByBaseId(baseid);
				}
			}
		  if(boundCard != null){
				if(boundCard.getCardno() != null){
					String cardNo=getDecrypt(boundCard.getCardno());//把银行卡解密
					if(boundCard.getUsername() != null){
						
						String userName=getDecrypt(boundCard.getUsername());//把用户名解密
						boundCard.setUsername(userName.substring(0,1)+"****");
					}
					boundCard.setCardno(cardNo.replaceAll("(\\d{4})\\d*(\\d{4})","$1***********$2"));
					System.out.println("加****显示的卡号：=============="+boundCard.getCardno());
				}
			}
			mv.addObject("boundCard", boundCard);
			Short isReally = userBaseAccountInfo.getIsreally();
			String mobilephone = "";
			if(userFsAccountInfo != null){
				mobilephone = userFsAccountInfo.getFsmobile();
			}
			String flagRe = "";
			if(isReally == null || isReally == 0){
				flagRe = "iden";
			}else if(StringUtil.isEmpty(mobilephone)){
				flagRe = "phone";
			}
			if(StringUtil.isNotEmpty(mobilephone)){
				// 电话加密显示
				String mobilephoneStr = userFsAccountInfo.getFsmobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
				mv.addObject("mobilephoneStr", mobilephoneStr);
			}
			mv.addObject("flagRe", flagRe);
			mv.addObject("realName", userBaseAccountInfo.getRealname());
			mv.addObject("baseid", baseid);
			mv.setViewName("user/manager/bindcard/bindcardinfo");
		}
		return mv;
	}
	
	/**
	 * 图片验证
	 * @Title: verificationCode
	 * @Description: TODO(图片验证)
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/checkSsmCode")
	public void checkSsmCode() throws Exception{
		//获取浏览器传递的验证码
		String code = request.getParameter("code");
  		String tokenCode = (String) request.getSession().getAttribute(Session_Constant.USER_TELEPHONE_CODE);
  		boolean flag = code.equals(tokenCode);
		String msg = "";
		if(flag){//验证码一致
			msg = "1";
		}else{
			msg = "0";
		}
	    sendJsonData(response, msg);
	}
	
	/**
	 * 发送短信
	 * @Title: verificationCode
	 * @Description: TODO(发送短信)
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/sendMsg")
	public void sendMsg() throws Exception{
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		String msg = "";
		if (userBaseAccountInfo != null) {
			// 获取用户电话号码
			String tellp = userBaseAccountInfo.getMobilephone();
			if (StringUtil.isNotEmpty(tellp)) {
				// 调用短信接口发送短信
//				String tellpCode = String.valueOf(StringUtil.getN(6));
				String tellpCode = "111111";
				
 				System.out.println("===========手机验证码是===================" + tellpCode);
				// 短信类容
 				// 发送短信
//				boolean flag = sMSSendServiceI.SMSSend4Code(tellp, tellpCode, userBaseAccountInfo.getLoginname(), userBaseAccountInfo.getId());
 				boolean flag = true;
 				session.setAttribute(Session_Constant.USER_TELEPHONE_CODE, tellpCode);
 				if(flag){
 					// 设置cookie
					request.setAttribute("cookieName", Iconstant.PRECOOKIE);
					String regcookie = StringUtil.getCookieValue(request);
					if (StringUtil.isNull(regcookie)) {
						StringUtil.addCookie(response, request);
					}
					// 把短信验证码放进session作用域
					msg = "1"; // 发送成功
				}else{
					msg = "0"; // 发送失败
				}
			} else {
				msg = "-1"; // 手机号不正确
			}
		} else {
			msg = "logout"; // session 失效 用户重新登录
		}
	    sendJsonData(response, msg);
	}
	
//	/**
//	 * 身份证校验
//	 * @Title: compareIdCard
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @throws Exception
//	 * @return void    返回类型
//	 */
//	@RequestMapping("/compareIdCard")
//	public void compareIdCard() throws Exception{
//		String idCard = request.getParameter("idCard");
//		// 从session中获取当前登录用户基本信息
//		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
//		String certificationnumber = userBaseAccountInfo.getCertificationnumber();
//		String msg = "";
//		if(!idCard.trim().equals("")){
//			if(idCard.trim().equals(certificationnumber)){
//				msg = "1";
//			}else{
//				msg = "0";
//			}
//		}
//	    sendJsonData(response, msg);
//	}
	
//	/**
//	 * 图片验证
//	 * @Title: verificationCode
//	 * @Description: TODO(图片验证)
//	 * @throws Exception
//	 * @return void
//	 */
//	@RequestMapping("/verificationCode")
//	public void verificationCode() throws Exception{
//		//获取浏览器传递的验证码
//		String code = request.getParameter("code");
//		//判断验证码是否正确
//		boolean flag = StringUtil.verifyCodeCompare(code, request);
//		String msg = "";
//		if(flag){//验证码一致
//			msg = "1";
//		}else{
//			msg = "0";
//		}
//	    sendJsonData(response, msg);
//	}
  	 
//	/**
//	 * 保存短信记录
//	 * @Title: saveSSMRecord
//	 * @Description: TODO(保存短信记录)
//	 * @param userBaseAccountInfo
//	 * @param mobile
//	 * @param smscontent
//	 * @param vercode
//	 * @return void    返回类型
//	 */
//	private void saveSMSSendRecord(UserBaseAccountInfo userBaseAccountInfo, String mobile, String smscontent, String vercode) {
//		SMSSendRecord sMSSendRecord = new SMSSendRecord();
//		if (userBaseAccountInfo != null) {
//			sMSSendRecord.setBaseid(userBaseAccountInfo.getId()); // 用户ID
//			sMSSendRecord.setUsername(userBaseAccountInfo.getLoginname()); // 用户名
//		}
//		sMSSendRecord.setMobile(mobile); // 电话
//		sMSSendRecord.setVercode(vercode); // 验证码
//		sMSSendRecord.setSendtime(new Date()); // 发送时间
//		sMSSendRecord.setSmscontent(smscontent); // 短信类容
//		sMSSendRecord.setSendtype((short) 2); // 发送方式（1，手工，2.系统）
//		sMSSendRecord.setRemark(null); // 备注
//		sMSSendRecordService.insertSelective(sMSSendRecord);// 保存短信发送记录
//	}
	
	/**
	 * 校验银行卡号
	 * @Title: compareCardNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param mbbCardNo
	 * @throws Exception
	 * @return void    返回类型
	 */
	@RequestMapping("/compareCardNo")
	public void compareCardNo(String mbbCardNo,String baseid) throws Exception{
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		String msg = "";
		if (userBaseAccountInfo != null){
			if(StringUtil.isNotEmpty(mbbCardNo) && StringUtil.isNotEmpty(baseid)){
				boolean falg = StringUtil.checkBankCard(mbbCardNo);
				falg = true;
				if(falg){
					UserBankCard userBankCard = userBankCardService.selectBoundCardByBaseId(new BigDecimal(baseid));
					if(userBankCard != null){
						msg = "1";
					}else{
						msg = "0";
					}
				}else{
					msg = "2";
				}
			}else{
				msg = "2";
			}
		}else{
			msg = "logout"; // session 失效 用户重新登录
		}
	    sendJsonData(response, msg);
	}
	
	/**
	 * 去汇付解绑银行卡页面
	 * @return
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws IOException 
	 */
//	@RequestMapping("/unBindCardPage")
//	public String unBindCardPage() throws IOException{
//		response.sendRedirect("http://mertest.chinapnr.com/p2puser/");
//		return null;
//	}
    //根据baseid查询银行卡的绑定信息
	@RequestMapping(value="/getInfoByBaseiD")
	@ResponseBody
	public String getInfoByBaseiD(BigDecimal baseid) throws JsonGenerationException, JsonMappingException, IOException{
		List<UserBankCard>  userBankCardList = userBankCardService.getInfoByBaseid(baseid);
		//System.err.println("进入银行...");
		UserBankCard userBankCard=null;
	//	System.err.println(userBankCardList.size());
		if(userBankCardList.size()!=0){
			 userBankCard=userBankCardList.get(0);
			// System.err.println("银行卡绑定状态："+userBankCard.getBindstatus());
				if(userBankCard!=null){
					ObjectMapper mapper=new ObjectMapper();
					String json=mapper.writeValueAsString(userBankCard);
					return json;
				}
				
		}
		return "0";//没有这行记录
		
	}
	
	//根据baseid查询银行交易密码是否设置
	@RequestMapping(value="/getPassWordStutsByBaseID")
	@ResponseBody
	public String getPassWordStutsByBaseID(BigDecimal baseid) throws JsonGenerationException, JsonMappingException, IOException{
		UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(baseid);
		//System.err.println("交易密码："+userFsAccountInfo.getTradepass());
		if(userFsAccountInfo!=null){
			ObjectMapper mapper=new ObjectMapper();
			String json=mapper.writeValueAsString(userFsAccountInfo);
			return json;
		}
		return "0";
		
	}
}
