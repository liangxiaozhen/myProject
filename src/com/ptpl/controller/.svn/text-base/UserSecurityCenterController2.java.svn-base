//package com.ptpl.controller;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.mail.Session;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.swing.plaf.synth.SynthSeparatorUI;
//
//import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.alibaba.druid.stat.TableStat.Mode;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.moneymoremore.util.Common;
//import com.ptpl.constant.Session_Constant;
//import com.ptpl.controller.moneymoremore.MMMRealNameMatchingController;
//import com.ptpl.model.EmailRecord;
//import com.ptpl.model.SMSSendRecord;
//import com.ptpl.model.UserAccountSafeInfo;
//import com.ptpl.model.UserBaseAccountInfo;
//import com.ptpl.model.UserFsAccountInfo;
//import com.ptpl.model.UserNameRuleModule;
//import com.ptpl.service.EmailRecordServiceI;
//import com.ptpl.service.SMSSendRecordServiceI;
//import com.ptpl.service.UserAccountSafeInfoServiceI;
//import com.ptpl.service.UserBaseAccountInfoServiceI;
//import com.ptpl.service.UserFsAccountInfoServiceI;
//import com.ptpl.service.UserNameRuleModuleServiceI;
//import com.ptpl.web.Iconstant.Iconstant;
//import com.ptpl.web.util.CCSendMail;
//import com.ptpl.web.util.IdcardValidator;
//import com.ptpl.web.util.MD5;
//import com.ptpl.web.util.SMSSend;
//import com.ptpl.web.util.SendMailTempalte;
//import com.ptpl.web.util.StringUtil;
//
///**
// * 
// * @ClassName: UserSecurityCenterController
// * @Package com.ptpl.controller
// * @Description: TODO(用户安全中心)
// * @author chenjiaming
// * @date 2016年7月15日 下午3:09:35
// * @version V1.0
// */
//@Controller
//@RequestMapping("/user/securitycenter")
//public class UserSecurityCenterController2 extends BaseController {
//	/**
//	 * 短信发送记录service
//	 */
//	@Autowired
//	private SMSSendRecordServiceI sMSSendRecordServiceI;
//	/**
//	 * 邮件发送记录service
//	 */
//	@Autowired
//	private EmailRecordServiceI emailRecordServiceI;
//	/**
//	 * 用户账号安全信息service
//	 */
//	@Autowired
//	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
//
//	/**
//	 * 用户基本信息service
//	 */
//	@Autowired
//	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
//	/**
//	 * 用户名规则设置service
//	 */
//	@Autowired
//	private UserNameRuleModuleServiceI userNameRuleModuleService;
//	
//	@Autowired
//	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
//	/**
//	 * 
//	* @Title: InsertRealNameCheck 
//	* @Description: TODO(实名制验证) 
//	* @param @param request
//	* @param @param response
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/InsertRealNameCheck",method = {RequestMethod.POST,RequestMethod.GET})
//	public String InsertRealNameCheck(HttpServletRequest request,HttpServletResponse response){
//		Map<String,String> hashMap = new HashMap<String,String>();
//		UserBaseAccountInfo accountInfo = getUserBaseAccountInfo();
// 		String realName = request.getParameter("RealName");//姓名
//		String RealNameNumber = request.getParameter("RealNameNumber");//身份证号码
//		if(StringUtil.isEmpty(realName)){//用户名不能为null
//			hashMap.put("result", "realName_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//		} 
//		
//		if(StringUtil.isEmpty(RealNameNumber)){//身份证号码不能为null
//			hashMap.put("result", "RealNameNumber_null");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//		}
//		
//		IdcardValidator idcardValidator = new IdcardValidator();
//		boolean falg = idcardValidator.isValidatedAllIdcard(RealNameNumber);//身份证检查
// 		if(!falg){//身份证校验失败
// 			hashMap.put("result", "fail");
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
//			return  null;
//		}
// 		
//		UserFsAccountInfo userFsAccountInfo2 = userFsAccountInfoServiceI.findUserFsAccountInfoByBaseId(accountInfo.getId());
//		Integer authtimes = 0;/*实名认证次数*/
//		if(userFsAccountInfo2 != null){
//			authtimes = userFsAccountInfo2.getAuthtimes();
//			if(authtimes > 3){
//				hashMap.put("result", "MoreThanCount");//超出匹配次数
// 				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//		}
//		
//		String resy = MMMRealNameMatchingController.doRealNameMatch(request, realName, RealNameNumber);//调用乾多多姓名匹配接口
//   		if(StringUtil.isNotEmpty(resy)){
//			JSONObject jsonObject = JSONObject.parseObject(resy);
//			String  ResultCode = jsonObject.getString("ResultCode");//返回码
//			String Amount = jsonObject.getString("Amount");
//      		if(Amount != null){ 
//     			if(userFsAccountInfo2 == null ){
//      				UserFsAccountInfo userFsAccountInfo = new UserFsAccountInfo();
//     				userFsAccountInfo.setBaseid(accountInfo.getId()); ///*用户ID*/
//     				userFsAccountInfo.setIsopenfsinfo((short)0); ///*是否开通托管账户  1已开通 0未开通*/
//     				userFsAccountInfo.setAuthfee(Double.valueOf(Amount)); ///*姓名匹配手续费*/
//     				userFsAccountInfo.setAuthtimes(1); ///*实名认证次数*/
//     				userFsAccountInfoServiceI.insertSelective(userFsAccountInfo);
//     			}else{
//      				userFsAccountInfo2.setAuthtimes(authtimes  + 1);/*实名认证次数*/
//     				userFsAccountInfo2.setAuthfee(userFsAccountInfo2.getAuthfee() + Double.valueOf(Amount));/*姓名匹配手续费*/
//     				userFsAccountInfoServiceI.updateById(userFsAccountInfo2);
//     			}
//      		}
// 			 if(ResultCode.equals("88")){
//				String IdentityJsonList = jsonObject.getString("IdentityJsonList");
//				String IdentityJsonList2 = Common.UrlDecoder(IdentityJsonList, "utf-8");
//				JSONArray jsonArray = JSONArray.parseArray(IdentityJsonList2);
// 				JSONObject jsonObject2 = JSONObject.parseObject(jsonArray.get(0).toString());
//				String state = jsonObject2.getString("state");//0、匹配失败 1、匹配成功3、待处理
//				if(state.equals("1")){
//					accountInfo.setIsreally((short)1);/*是否实名认证: 1已认证 0 未认证*/
//					accountInfo.setCertificationnumber(RealNameNumber);
//					accountInfo.setRealname(realName);
//					userBaseAccountInfoServiceI.updateByPrimaryKeySelective(accountInfo);
//					hashMap.put("result", "success");//身份证匹配成功
//    				String str = JSON.toJSONString(hashMap);
// 					try {
// 						StringUtil.sendJsonData(response, str);
// 					} catch (IOException e) {
// 		 				e.printStackTrace();
// 					}
// 					return null;
// 				}else{
// 					if(Double.valueOf(Amount) > 0){
// 						accountInfo.setCertificationnumber(RealNameNumber);
// 						accountInfo.setRealname(realName);
// 						userBaseAccountInfoServiceI.updateByPrimaryKeySelective(accountInfo);
//  					}
// 					hashMap.put("result", "RealNameOrNumber_error");//身份证匹配失败
// 					hashMap.put("authtimes", authtimes.toString());
//   					String str = JSON.toJSONString(hashMap);
// 					try {
// 						StringUtil.sendJsonData(response, str);
// 					} catch (IOException e) {
// 		 				e.printStackTrace();
// 					}
// 					return null;
//				}
// 			}else{
//				hashMap.put("result", "RealNameOrNumber_error");//身份证匹配失败
//				hashMap.put("authtimes", authtimes.toString());
// 				String str = JSON.toJSONString(hashMap);
//				try {
//					StringUtil.sendJsonData(response, str);
//				} catch (IOException e) {
//	 				e.printStackTrace();
//				}
//				return null;
//			}
//			
//		}else{
//			hashMap.put("result", "fail");//身份证校验失败
//			String str = JSON.toJSONString(hashMap);
//			try {
//				StringUtil.sendJsonData(response, str);
//			} catch (IOException e) {
// 				e.printStackTrace();
//			}
// 		}
// 		return null;
//	}
//	
//	/**
//	 * 
//	 * @Title: list @Description: TODO(跳转用户安全中心 管理主页面 list 页面) @param @return
//	 *         参数说明 @return String 返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping("/list")
//	public String list(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			// 前台显示加密电话号码 132*****132
//			if (userBaseAccountInfo.getMobilephone() != null) {
//				userBaseAccountInfo.setMobilephonestr(
//						userBaseAccountInfo.getMobilephone().replaceAll("(\\d{3})\\d{4}(\\d{3})", "$1****$2"));
//			}
//			// 前台显示加密邮箱账号********qq.com
//			if (userBaseAccountInfo.getEmail() != null) {
//				userBaseAccountInfo.setEmailstr("***********" + userBaseAccountInfo.getEmail().split("@")[1]);
//			}
//			model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			return "user/securitycenter/list";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * @Title: UserPin @Description: TODO(安全中心 设置/修改交易密码 方法) @param @param
//	 *         request @param @param response @param @return 参数说明 @return String
//	 *         返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/userPin", method = RequestMethod.POST)
//	public String UserPin(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取用户填写的密码
//				String password = request.getParameter("password");
//				// 获取用户填写的验证码
//				String code = request.getParameter("tellCode");
//				if (StringUtil.isNotEmpty(password) && StringUtil.isNotEmpty(code)) {
//					// 获取已经发送给用户的手机验证码
//					String tellCode = (String) request.getSession().getAttribute(Session_Constant.USER_TELEPHONE_CODE);
//					if (StringUtil.isNotEmpty(tellCode) && tellCode.equalsIgnoreCase(code)) {
//						// 手机短信验证码一致 进来了
//						// MD5加密
//						MD5 m = new MD5();
//						String str1 = m.getMD5Info(password);
//						// MD5加密后分割
//						String[] strings = StringUtil.splitString(str1);
//						UserAccountSafeInfo uasi = (UserAccountSafeInfo) request.getSession()
//								.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//						// 用户ID
//						uasi.setBaseid(userBaseAccountInfo.getId());
//						// 密码加密1-3段
//						uasi.setSecuritypasswordone(strings[0]);
//						// 密码加密2-4段
//						uasi.setSecuritypasswordtwo(strings[1]);
//						int count = 0;
//						count = userAccountSafeInfoServiceI.update(uasi);
//						if (count > 0) {
//							maps.put("result", "success");
//						} else {
//							maps.put("result", "fail");
//						}
//					} else {
//						maps.put("result", "code_error");
//					}
//				} else {
//					maps.put("result", "code_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * @Title: UserPin @Description: TODO(安全中心 设置 修改交易密码 方法
//	 *         根据原交易密码比对重新修改交易密码) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型
//	 *         UpdateTrandingPasswordOld @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateTrandingPasswordOld", method = RequestMethod.POST)
//	public String UpdateTrandingPasswordOld(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取用户填写的原来的交易密码密码
//				String oldPsw = request.getParameter("oldPsw");
//				// 获取用户填写的新的交易密码
//				String newPsw = request.getParameter("newPsw");
//				if (StringUtil.isNotEmpty(oldPsw)) {// 原交易密码不为空
//					if (StringUtil.isNotEmpty(newPsw)) {
//						// 新交易密码不为空进来了
//						UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//								.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//						if (userAccountSafeInfo != null) {
//							// MD5加密
//							MD5 m = new MD5();
//							String str1 = m.getMD5Info(oldPsw);
//							// MD5加密后分割
//							String[] strings = StringUtil.splitString(str1);
//							String str3 = userAccountSafeInfo.getSecuritypasswordone();
//							String str4 = userAccountSafeInfo.getSecuritypasswordtwo();
//							// 交易密码比对
//							if (strings[0].equalsIgnoreCase(str3) && strings[1].equalsIgnoreCase(str4)) {
//								str1 = m.getMD5Info(newPsw);
//								// MD5加密后分割
//								strings = StringUtil.splitString(str1);
//								userAccountSafeInfo.setSecuritypasswordone(strings[0]);
//								userAccountSafeInfo.setSecuritypasswordtwo(strings[1]);
//								int count = 0;
//								count = userAccountSafeInfoServiceI.update(userAccountSafeInfo);
//								if (count > 0) {
//									// 交易密码保存成功
//									maps.put("result", "success");
//								} else {
//									// 交易密码保存失败
//									maps.put("result", "fail");
//								}
//							} else {
//								// 原交易密码错误
//								maps.put("result", "oldPsw_error");
//							}
//						}
//					} else {
//						// 新交易密码是空
//						maps.put("result", "newPsw_null");
//					}
//				} else {
//					// 原交易密码是空
//					maps.put("result", "oldPsw_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: tellCode @Description: TODO(调用短信接口 发送短信) @param @return
//	 *         参数说明 @return String 返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/tellCode", method = RequestMethod.POST)
//	public String tellCode(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取用户电话号码
//				String tellp = userBaseAccountInfo.getMobilephone();
//				if (StringUtil.isNotEmpty(tellp)) {
//					// 调用短信接口发送短信
//					String tellpCode = String.valueOf(StringUtil.getN(6));
//					System.out.println("===========手机验证码是===================" + tellpCode);
//					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String smscontent = "安全中心发送手机验证码";
//					SMSSendRecord smsSendRecord = getSSMRecord(userBaseAccountInfo, tellp, smscontent, tellpCode);
//					sMSSendRecordServiceI.insertSelective(smsSendRecord);// 保存短信发送记录
//					// 发送短信
//					// SMSSend.smsSend(tellp, tellpCode,
//					// Iconstant.SMS_COMPANYNAME, sf);
//					// 设置cookie
//					request.setAttribute("cookieName", Iconstant.PRECOOKIE);
//					String regcookie = StringUtil.getCookieValue(request);
//					if (StringUtil.isNull(regcookie)) {
//						StringUtil.addCookie(response, request);
//					}
//					// 把短信验证码放进session作用域
//					session.setAttribute(Session_Constant.USER_TELEPHONE_CODE, tellpCode);
//					maps.put("result", "success");
//				} else {
//					maps.put("result", "fail");
//				}
//			} else {
//				// session 失效 用户重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: question @Description: TODO(普通用户安全中心 安全问题设置 修改 方法) @param @param
//	 *         request @param @param response @param @return 参数说明 @return String
//	 *         返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/question", method = RequestMethod.POST)
//	public String question(HttpServletRequest request, HttpServletResponse response,
//			UserAccountSafeInfo userAccountSafeInfo) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				if (userAccountSafeInfo != null) {
//					String custquestion = userAccountSafeInfo.getCustquestion();
//					if (custquestion != null && custquestion.equals("2")) {
//						// 用户使用系统自带问题进这里
//						// 先查用户账号信息
//						UserAccountSafeInfo uasi = (UserAccountSafeInfo) request.getSession()
//								.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//						if (uasi != null) {
//							uasi.setQuestion1(getQuestion(userAccountSafeInfo.getQuestion1()));
//							uasi.setQuestion2(getQuestion(userAccountSafeInfo.getQuestion2()));
//							uasi.setQuestion3(getQuestion(userAccountSafeInfo.getQuestion3()));
//							uasi.setAnswer1(userAccountSafeInfo.getAnswer1());
//							uasi.setAnswer2(userAccountSafeInfo.getAnswer2());
//							uasi.setAnswer3(userAccountSafeInfo.getAnswer3());
//							uasi.setCustquestion(custquestion);
//							int count = 0;
//							// 更新用户密保问题
//							count = userAccountSafeInfoServiceI.update(uasi);
//							if (count > 0) {
//								maps.put("result", "success");
//							} else {
//								maps.put("result", "fail");
//							}
//						} else {
//							maps.put("result", "error");
//						}
//					} else if (custquestion != null && custquestion.equals("1")) {
//						// 用户自己定义问题进这里
//						// 先查用户账号信息
//						UserAccountSafeInfo uasi = (UserAccountSafeInfo) request.getSession()
//								.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//						if (uasi != null) {
//							uasi.setQuestion1(userAccountSafeInfo.getQuestion1());
//							uasi.setQuestion2(userAccountSafeInfo.getQuestion2());
//							uasi.setQuestion3(userAccountSafeInfo.getQuestion3());
//							uasi.setAnswer1(userAccountSafeInfo.getAnswer1());
//							uasi.setAnswer2(userAccountSafeInfo.getAnswer2());
//							uasi.setAnswer3(userAccountSafeInfo.getAnswer3());
//							uasi.setCustquestion(custquestion);
//							int count = 0;
//							// 更新用户密保问题
//							count = userAccountSafeInfoServiceI.update(uasi);
//							if (count > 0) {
//								maps.put("result", "success");
//							} else {
//								maps.put("result", "fail");
//							}
//						} else {
//							maps.put("result", "error");
//						}
//					}
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: checkphoneCode @Description: TODO(普通用户安全中心 安全密保问题 通过手机修改
//	 *         校验手机验证码) @param @param request @param @param
//	 *         response @param @param userAccountSafeInfo @param @return
//	 *         参数说明 @return String 返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/checkphoneCode", method = RequestMethod.POST)
//	public String checkphoneCode(HttpServletRequest request, HttpServletResponse response,
//			UserAccountSafeInfo userAccountSafeInfo) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取已经发送的短信
//				String tellCode = (String) request.getSession().getAttribute(Session_Constant.USER_TELEPHONE_CODE);
//				// 获取从浏览器发送过来的短信数据
//				String code = request.getParameter("code");
//				System.out.println(code + "============手机验证码=====tellCode========" + tellCode);
//				if (tellCode != null) {
//					if (StringUtil.isNotEmpty(code)) {
//						if (tellCode.equalsIgnoreCase(code)) {
//							// 验证成功
//							maps.put("result", "success");
//						} else {
//							// 手机短信验证码不一致
//							maps.put("result", "fail");
//						}
//					} else {
//						// 手机短信验证码为空值
//						maps.put("result", "code_null");
//					}
//				} else {
//					// 短信未发送
//					maps.put("result", "code_error");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: checkAnswerOld @Description: TODO(普通用户安全中心 安全密保问题 通过手机修改
//	 *         校验原密保问题答案是否一致) @param @param request @param @param
//	 *         response @param @param userAccountSafeInfo @param @return
//	 *         参数说明 @return String 返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/checkAnswerOld", method = RequestMethod.POST)
//	public String checkAnswerOld(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取传递过来的密保问题答案一
//				String anser1 = request.getParameter("answer1");
//				// 获取传递过来的密保问题答案二
//				String anser2 = request.getParameter("answer2");
//				// 获取传递过来的密保问题答案三
//				String anser3 = request.getParameter("answer3");
//				if (StringUtil.isNotEmpty(anser1) && StringUtil.isNotEmpty(anser2) && StringUtil.isNotEmpty(anser3)) {
//					UserAccountSafeInfo userAccountInfo = (UserAccountSafeInfo) request.getSession()
//							.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//					if (userAccountInfo != null) {
//						// 原密保问题答案一校验
//						if (userAccountInfo.getAnswer1() != null) {
//							if (userAccountInfo.getAnswer1().equals(anser1)) {
//								// 原密保问题答案二校验
//								if (userAccountInfo.getAnswer2() != null) {
//									if (userAccountInfo.getAnswer2().equals(anser2)) {
//										// 原密保问题答案三校验
//										if (userAccountInfo.getAnswer3() != null) {
//											if (userAccountInfo.getAnswer3().equals(anser3)) {
//												// 密保答案校验成功
//												maps.put("result", "success");
//											} else {
//												// 密保答案三错误
//												maps.put("result", "answer3_error");
//											}
//										}
//									} else {
//										// 密保答案二错误
//										maps.put("result", "answer2_error");
//									}
//								}
//							} else {
//								// 密保答案一错误
//								maps.put("result", "answer1_error");
//							}
//						}
//					}
//				} else {
//					// 密保问题未获取到
//					maps.put("result", "answer_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: updatepassword @Description: TODO(普通用户安全中心 修改登录密码) @param @param
//	 *         request @param @param response @param @return 参数说明 @return String
//	 *         返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//	public String updatePassword(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				String oldpassword = request.getParameter("oldpassword");
//				String newpassword = request.getParameter("newpassword");
//				if (StringUtil.isNotEmpty(oldpassword) && StringUtil.isNotEmpty(newpassword)) {
//					UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//							.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//					if (userAccountSafeInfo != null) {
//						boolean flag = BCrypt.checkpw(oldpassword, userAccountSafeInfo.getLoginpassword());
//						if (flag) {
//							// 加密密码
//							String pwt = BCrypt.hashpw(newpassword, BCrypt.gensalt());
//							userAccountSafeInfo.setLoginpassword(pwt);
//							int count = 0;
//							// 更新密码
//							count = userAccountSafeInfoServiceI.update(userAccountSafeInfo);
//							if (count > 0) {
//								maps.put("result", "success");
//							} else {
//								maps.put("result", "fail");
//							}
//						} else {
//							// 原密码错误
//							maps.put("result", "password_error");
//						}
//					}
//				} else {
//					// 浏览器传递过来的密码为空
//					maps.put("result", "password_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserPhoneCheckCode @Description: TODO(普通用户安全中心 修改用户手机号码
//	 *         旧手机验证码校验) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserPhoneCheckCode", method = RequestMethod.POST)
//	public String UpdateUserPhoneCheckCode(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				String code = request.getParameter("code");
//				if (StringUtil.isNotEmpty(code)) {
//					String sessionCode = (String) request.getSession()
//							.getAttribute(Session_Constant.USER_TELEPHONE_CODE);
//					if (StringUtil.isNotEmpty(sessionCode)) {
//						System.out.println(code + "====================" + sessionCode);
//						if (code.equalsIgnoreCase(sessionCode)) {
//							// 短信验证成功
//							maps.put("result", "success");
//						} else {
//							// 短信验证码不一致
//							maps.put("result", "code_error");
//						}
//					} else {
//						// 未发送短信
//						maps.put("result", "tellCode_null");
//					}
//				} else {
//					// 手机短信验证码为空
//					maps.put("result", "code_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserPhoneResetPhone @Description: TODO(普通用户安全中心 修改用户手机号码
//	 *         重新设置手机号码) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserPhoneResetPhone", method = RequestMethod.POST)
//	public String UpdateUserPhoneResetPhone(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取用户填写的手机号码
//				String tellPhone = request.getParameter("tellPhone");
//				// 获取用户填写的手机验证码
//				String code = request.getParameter("code");
//				/** boolean flag 判断是新增还是修改手机 */
//				boolean flag = false;
//				/** 手机为空，则为新增 */
//				if (StringUtil.isEmpty(userBaseAccountInfo.getMobilephone())) {
//					flag = true;
//				}
//				if (StringUtil.isNotEmpty(tellPhone)) {// 手机号为空进来了
//					if (StringUtil.isNotEmpty(code)) {// 手机验证码不为空进来了
//														// 查询手机号码是否存在
//						String userCode = (String) request.getSession()
//								.getAttribute(Session_Constant.USER_NEW_TELEPHONE_CODE);
//						if (StringUtil.isNotEmpty(userCode)) {
//							System.out.println(code + "==========" + userCode);
//							UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//							ubai.setMobilephone(tellPhone);
//							UserBaseAccountInfo userBaseAccountInfo2 = userBaseAccountInfoServiceI
//									.getUserBaseAccountInfoByOneCondition(ubai);
//							if (userBaseAccountInfo2 == null) {// 手机号没有存在，可以注册
//								if (userCode.equalsIgnoreCase(code)) {
//									// 重新set新手机号
//									userBaseAccountInfo.setMobilephone(tellPhone);
//									int count = 0;
//									count = userBaseAccountInfoServiceI
//											.updateByPrimaryKeySelective(userBaseAccountInfo);
//									if (count > 0) {
//										// 保存成功
//										maps.put("result", "success");
//										/** 如果为绑定手机号 */
//										if (flag) {
//											/** 根据用户名设置规则完善用户名 */
//											setUserName(userBaseAccountInfo);
//										}
//									} else {
//										// 保存失败
//										maps.put("result", "fail");
//									}
//								} else {
//									// 新手机短信验证码没有比对成功
//									maps.put("result", "code_error");
//								}
//
//							} else {
//								// 手机号已经存在了
//								maps.put("result", "tellPhone_no");
//							}
//						} else {
//							// 新手机短信验证码没有发送
//							maps.put("result", "ncode_null");
//						}
//					} else {
//						// 新手机短信验证码为空
//						maps.put("result", "code_null");
//					}
//				} else {
//					// 新手机为空
//					maps.put("result", "tellPhone_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 绑定手机号后，完善用户名
//	 * 
//	 * @param userBAI
//	 */
//	private void setUserName(UserBaseAccountInfo ubai) {
//		UserNameRuleModule unrm = new UserNameRuleModule();
//		unrm.setRegistertype(ubai.getRegtype());
//		unrm = userNameRuleModuleService.selective(unrm).get(0);
//		// 不可以与手机号，且有用户名。 则用户名清空
//		if (unrm.getSetrule().equals("b") && StringUtil.isNotEmpty(ubai.getLoginname())) {
//			// 用户名和手机号相同
//			if (ubai.getLoginname().equals(ubai.getMobilephone())) {
//				ubai.setLoginname("");
//				userBaseAccountInfoServiceI.updateByPrimaryKeySelective(ubai);
//			}
//		}
//		// 系统自动同步手机号
//		if (unrm.getSetrule().equals("c")) {
//			ubai.setLoginname(ubai.getMobilephone());
//			userBaseAccountInfoServiceI.updateByPrimaryKeySelective(ubai);
//		}
//		// 系统按照规则代填
//		if (unrm.getSetrule().equals("d")) { // 预设前缀字符+随机数
//			if (unrm.getNamerule().equals("a")) {
//				String presetStr = unrm.getPresetstr();
//				String loginName = null;
//				boolean flag = true;
//				// 如果系统匹配的用户名存在，则重新匹配直到用户名不存在
//				while (flag) {
//					loginName = presetStr + StringUtil.getN(unrm.getRandomlength().intValue());
//					UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
//					ubai1.setLoginname(loginName);
//					UserBaseAccountInfo record = userBaseAccountInfoServiceI
//							.getUserBaseAccountInfoByOneCondition(ubai1);
//					if (record == null) {
//						flag = false;
//					}
//				}
//				ubai.setLoginname(loginName);
//				userBaseAccountInfoServiceI.updateByPrimaryKeySelective(ubai);
//			}
//			// 随机数
//			if (unrm.getNamerule().equals("b")) {
//				String loginName = null;
//				boolean flag = true;
//				while (flag) {
//					loginName = String.valueOf(StringUtil.getN(unrm.getRandomlength().intValue()));
//					UserBaseAccountInfo ubai1 = new UserBaseAccountInfo();
//					ubai1.setLoginname(loginName);
//					UserBaseAccountInfo record = userBaseAccountInfoServiceI
//							.getUserBaseAccountInfoByOneCondition(ubai1);
//					if (record == null)
//						flag = false;
//				}
//				ubai.setLoginname(loginName);
//				userBaseAccountInfoServiceI.updateByPrimaryKeySelective(ubai);
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserPhoneResetPhoneSendSsm @Description: TODO(普通用户安全中心
//	 *         修改用户手机号码 发送新手机号验证码) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserPhoneResetPhoneSendSsm", method = RequestMethod.POST)
//	public String UpdateUserPhoneResetPhoneSendSsm(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取新用户手机号码
//				String userPhone = request.getParameter("tellPhone");
//				if (StringUtil.isNotEmpty(userPhone)) {
//					if (StringUtil.isMobile(userPhone)) {
//						UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//						ubai.setMobilephone(userPhone);
//						UserBaseAccountInfo accountInfo = userBaseAccountInfoServiceI
//								.getUserBaseAccountInfoByOneCondition(ubai);
//						if (accountInfo == null) {
//							// 调用短信接口发送短信
//							String tellpCode = String.valueOf(StringUtil.getN(6));
//							System.out.println("===========手机验证码是=======" + tellpCode);
//							SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//							String smscontent = "安全中心修改用户手机号码 发送新手机号验证码";
//							SMSSendRecord smsSendRecord = getSSMRecord(userBaseAccountInfo, userPhone, smscontent,
//									tellpCode);
//							sMSSendRecordServiceI.insertSelective(smsSendRecord);// 保存短信发送记录
//							// 发送短信
//							// SMSSend.smsSend(userPhone, tellpCode,
//							// Iconstant.SMS_COMPANYNAME, sf);
//							// 把短信验证码放进session作用域
//							session.setAttribute(Session_Constant.USER_NEW_TELEPHONE_CODE, tellpCode);
//							maps.put("result", "success");
//						} else {
//							// 手机号码已經註冊了
//							maps.put("result", "tellPhone_exit");
//						}
//					} else {
//						// 手机号码格式正确
//						maps.put("result", "tellPhone_error");
//					}
//				} else {
//					// 手机号码为空
//					maps.put("result", "tellPhone_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: InsertUserEmailSendCode @Description: TODO(普通用户安全中心 设置用户邮箱
//	 *         发送邮箱验证链接) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/InsertUserEmailSendCode", method = RequestMethod.POST)
//	public String InsertUserEmailSendCode(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取新用户邮箱
//				String email = request.getParameter("email");
//				if (StringUtil.isNotEmpty(email)) {
//					UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//					ubai.setEmail(email);
//					UserBaseAccountInfo serBaseAccountInfo = userBaseAccountInfoServiceI
//							.getUserBaseAccountInfoByOneCondition(ubai);
//					if (serBaseAccountInfo == null) {
//						// 验证随机码
//						String emailCode = StringUtil.getRandomStr(30);
//						MD5 md5 = new MD5();
//						// ID标识符
//						String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//						// 方法
//						String method = "eamilbindnewemail";
//						// 用户名
//						String username = userBaseAccountInfo.getLoginname();
//						// 发送重置验证邮件链接
//						EmailRecord emailRecord = getEmailRecord(userBaseAccountInfo, email, toKCode);
//						emailRecord.setRemark("验证用户邮箱 发送邮箱验证链接");
//						/* 邮件补发类型 1绑定邮箱验证链接 2 邮箱重置验证码 3 重置验证链接 4普通邮箱验证码 */
//						emailRecord.setMessagetype((short) 1);
//						emailRecordServiceI.insertSelective(emailRecord);// 保存邮箱发送信息
//						boolean flag = SendMailTempalte.sendEamilForEmailbind(email, emailCode, toKCode, method,
//								username, request);
//						if (flag) {
//							// 把邮箱验证码放进session作用域
//							session.setAttribute(Session_Constant.USER_NEW_EMAIL_CODE, emailCode);
//							maps.put("result", "success");
//						} else {
//							maps.put("result", "fail");
//						}
//					} else {
//						maps.put("result", "email_nonull");
//					}
//				} else {
//					// 邮箱账号为空
//					maps.put("result", "email_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: BindUserEmailSend @Description: TODO(普通用户安全中心 设置用户邮箱
//	 *         校验邮箱验证码并绑定邮箱) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/BindUserEmailSend", method = { RequestMethod.POST, RequestMethod.GET })
//	public String BindUserEmailSend(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			// 获取浏览器传递过来的邮箱随机码
//			String code = request.getParameter("temp");
//			// 获取邮箱账号
//			String email = request.getParameter("email");
//			// 加密随机码
//			String token = request.getParameter("token");
//			// method
//			String method = request.getParameter("method");
//			if (StringUtil.isNotEmpty(method) && method.equalsIgnoreCase(Session_Constant.REPEAT_EMAIL)) {// 邮件补发类型
//				MD5 md5 = new MD5();
//				// ID标识符
//				String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//				if (StringUtil.isNotEmpty(token) && token.equalsIgnoreCase(toKCode)) {
//					if (userBaseAccountInfo.getIsemailverify() != 1) {
//						userBaseAccountInfo.setEmail(email);// 邮件账户
//						userBaseAccountInfo.setEmailverifydate(new Date());// 邮件验证日期
//						userBaseAccountInfo
//								.setIsactive((short) 1);/* 是否激活 1已激活 0未激活 */
//						userBaseAccountInfo.setIsemailverify(
//								(short) 1);/* 邮件验证 1已验证 0未验证 */
//						int count = 0;
//						// 保存
//						count = userBaseAccountInfoServiceI.updateByPrimaryKeySelective(userBaseAccountInfo);
//						if (count > 0) {
//							return "user/securitycenter/success";
//						}
//					}
//				}
//			}
//			if (StringUtil.isNotEmpty(code) && StringUtil.isNotEmpty(token) && StringUtil.isNotEmpty(email)) {
//				MD5 md5 = new MD5();
//				// ID标识符
//				String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//				String scode = (String) request.getSession().getAttribute(Session_Constant.USER_NEW_EMAIL_CODE);
//				if (StringUtil.isNotEmpty(scode) && scode.equalsIgnoreCase(code) && toKCode.equalsIgnoreCase(token)) {
//					userBaseAccountInfo.setEmail(email);// 邮件账户
//					userBaseAccountInfo.setEmailverifydate(new Date());// 邮件验证日期
//					userBaseAccountInfo
//							.setIsactive((short) 1);/* 是否激活 1已激活 0未激活 */
//					userBaseAccountInfo
//							.setIsemailverify((short) 1);/* 邮件验证 1已验证 0未验证 */
//					int count = 0;
//					// 保存
//					count = userBaseAccountInfoServiceI.updateByPrimaryKeySelective(userBaseAccountInfo);
//					if (count > 0) {
//						return "user/securitycenter/success";
//					}
//				}
//			} else {
//				return "redirect:/user/tologin.action";
//			}
//			return null;
//		} else {
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserEmailSendEamil @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         发送邮箱设置链接) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserEmailSendEamil", method = RequestMethod.POST)
//	public String UpdateUserEmailSendEamil(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 验证随机码
//				String emailCode = StringUtil.getRandomStr(30);
//				MD5 md5 = new MD5();
//				// ID标识符
//				String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//				// 方法
//				String method = "eamilbindnewemail";
//				// 用户名
//				String username = userBaseAccountInfo.getLoginname();
//				// 邮箱账号
//				String email = userBaseAccountInfo.getEmail();
//				EmailRecord emailRecord = getEmailRecord(userBaseAccountInfo, email, toKCode);
//				emailRecord.setRemark("重置用户邮箱  发送邮箱重置链接");
//				/* 邮件补发类型 1绑定邮箱链接 2邮箱重置链接 3 邮箱验证链接 4普通邮箱验证码 */
//				emailRecord.setMessagetype((short) 2);
//				emailRecordServiceI.insertSelective(emailRecord);// 保存邮箱发送信息
//				// 发送邮箱重置链接
//				boolean flag = SendMailTempalte.sendEamilForEmailCheck(email, emailCode, toKCode, method, username,
//						request);
//				if (flag) {
//					session.setAttribute(Session_Constant.USER_REGISTER_EMAIL_CODE, emailCode);
//					// 邮件发送成功
//					maps.put("result", "success");
//				} else {
//					// 邮件发送失败
//					maps.put("result", "fail");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserEmailSendCheck @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         邮箱设置链接验证) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserEmailSendCheck", method = { RequestMethod.POST, RequestMethod.GET })
//	public String UpdateUserEmailSendCheck(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			// 获取浏览器传递过来的随机码
//			String code = request.getParameter("code");
//			String token = request.getParameter("token");
//			String method = request.getParameter("method");
//			if (StringUtil.isNotEmpty(method) && method.equalsIgnoreCase(Session_Constant.REPEAT_EMAIL)) {// 邮件补发类型
//				MD5 md5 = new MD5();
//				// ID标识符
//				String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//				if (StringUtil.isNotEmpty(token) && toKCode.equalsIgnoreCase(token)) {
//					return "user/securitycenter/updateUserEmailSetUp";
//				}
//			}
//			if (StringUtil.isNotEmpty(code) && StringUtil.isNotEmpty(token)) {
//				MD5 md5 = new MD5();
//				// ID标识符
//				String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//				String scode = (String) request.getSession().getAttribute(Session_Constant.USER_REGISTER_EMAIL_CODE);
//				if (StringUtil.isNotEmpty(scode) && toKCode.equalsIgnoreCase(token) && scode.equalsIgnoreCase(code)) {// 链接验证成功
//					return "user/securitycenter/updateUserEmailSetUp";
//				} else {
//					return "redirect:/user/tologin.action";
//				}
//			} else {
//				return "redirect:/user/tologin.action";
//			}
//		} else {
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserEmailCheckPhoneCode @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         手机验证码校验) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserEmailCheckPhoneCode", method = RequestMethod.POST)
//	public String UpdateUserEmailCheckPhoneCode(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				// 获取浏览器传递过来的手机验证码
//				String code = request.getParameter("code");
//				if (StringUtil.isNotEmpty(code)) {
//					String scode = (String) request.getSession().getAttribute(Session_Constant.USER_TELEPHONE_CODE);
//					System.out.println(code + "================" + scode);
//					if (StringUtil.isNotEmpty(scode) && scode.equalsIgnoreCase(code)) {
//						maps.put("result", "success");
//					} else {
//						maps.put("result", "fail");
//					}
//				} else {
//					// 邮箱验证码为空
//					maps.put("result", "code_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserEmailSendResetEamil @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         发送邮件重置验证链接) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserEmailSendResetEamil", method = RequestMethod.POST)
//	public String UpdateUserEmailSendResetEamil(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		Map<String, String> maps = new HashMap<String, String>();
//		try {
//			if (userBaseAccountInfo != null) {
//				String email = request.getParameter("email");
//				if (StringUtil.isNotEmpty(email)) {
//					UserBaseAccountInfo ubai = new UserBaseAccountInfo();
//					ubai.setEmail(email);
//					UserBaseAccountInfo info = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
//					if (info == null) {
//						// 验证随机码
//						String emailCode = StringUtil.getRandomStr(30);
//						MD5 md5 = new MD5();
//						// ID标识符
//						String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//						// 方法
//						String method = "eamilbindnewemail";
//						// 用户名
//						String username = userBaseAccountInfo.getLoginname();
//						// 发送重置验证邮件链接
//						EmailRecord emailRecord = getEmailRecord(userBaseAccountInfo, email, toKCode);
//						emailRecord.setRemark("邮箱重置检验链接");
//						/* 邮件补发类型 1绑定邮箱验证码 2 邮箱重置验证码 3 重置验证链接 4普通邮箱验证码 */
//						emailRecord.setMessagetype((short) 3);
//						emailRecordServiceI.insertSelective(emailRecord);// 保存邮箱发送信息
//						boolean flag = SendMailTempalte.sendEamilForEmailReger(email, emailCode, toKCode, method,
//								username, request);
//						session.setAttribute("email", email);
//						if (flag) {
//							session.setAttribute(Session_Constant.USER_EMAILCODE_EMAIL_CODE, emailCode);
//							// 邮件发送成功
//							maps.put("result", "success");
//						} else {
//							// 邮箱发送失败
//							maps.put("result", "fail");
//						}
//					} else {
//						// 邮箱已注册了
//						maps.put("result", "email_nonull");
//					}
//				} else {
//					// 邮箱账号为空
//					maps.put("result", "email_null");
//				}
//			} else {
//				// session失效 重新登录
//				maps.put("result", "logout");
//			}
//			String str = JSON.toJSONString(maps);
//			StringUtil.sendJsonData(response, str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserEmailSendResetEamilCheck @Description: TODO(普通用户安全中心
//	 *         重置用户邮箱 邮件重置链接校验比对) @param @param request @param @param
//	 *         response @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserEmailSendResetEamilCheck", method = { RequestMethod.POST, RequestMethod.GET })
//	public String UpdateUserEmailSendResetEamilCheck(HttpServletRequest request, HttpServletResponse response) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			// 获取标识符
//			String rtoCode = request.getParameter("token");
//			// 获取随机码
//			String random = request.getParameter("temp");
//			String method = request.getParameter("method");
//			if (StringUtil.isNotEmpty(method) && method.equalsIgnoreCase(Session_Constant.REPEAT_EMAIL)) {// 邮件补发类型
//				String email = request.getParameter("email");
//				MD5 md5 = new MD5();
//				// ID标识符
//				String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//				if (StringUtil.isNotEmpty(rtoCode) && rtoCode.equalsIgnoreCase(toKCode)
//						&& StringUtil.isNotEmpty(email)) {
//					UserBaseAccountInfo accountInfo = new UserBaseAccountInfo();
//					accountInfo.setEmail(email);
//					UserBaseAccountInfo userIf = userBaseAccountInfoServiceI
//							.getUserBaseAccountInfoByOneCondition(accountInfo);
//					if (userIf == null) {
//						userBaseAccountInfo.setEmail(email);
//						int count = 0;
//						count = userBaseAccountInfoServiceI.updateByPrimaryKeySelective(userBaseAccountInfo);
//						if (count > 0) {
//							// 校验成功 跳转提示页面
//							return "user/securitycenter/success";
//						}
//					}
//				}
//			}
//			if (StringUtil.isNotEmpty(rtoCode) && StringUtil.isNotEmpty(random)) {
//				MD5 md5 = new MD5();
//				// ID标识符
//				String toKCode = md5.getMD5Info(userBaseAccountInfo.getId() + "moye110");
//				// 随机码
//				String ecode = (String) request.getSession().getAttribute(Session_Constant.USER_EMAILCODE_EMAIL_CODE);
//				String email = (String) request.getSession().getAttribute("email");
//				if (StringUtil.isNotEmpty(toKCode) && StringUtil.isNotEmpty(ecode) && StringUtil.isNotEmpty(email)
//						&& ecode.equalsIgnoreCase(random) && toKCode.equalsIgnoreCase(rtoCode)) {
//					userBaseAccountInfo.setEmail(email);
//					int count = 0;
//					count = userBaseAccountInfoServiceI.updateByPrimaryKeySelective(userBaseAccountInfo);
//					if (count > 0) {
//						// 清除session
//						session.removeAttribute(Session_Constant.USER_EMAILCODE_EMAIL_CODE);
//						// 校验成功 跳转提示页面
//						return "user/securitycenter/success";
//					} else {
//						// 校验失败 跳转首页
//						return "user/index";
//					}
//				} else {
//					// 校验失败 跳转首页
//					return "user/index";
//				}
//			} else {
//				return "redirect:/user/touser.action";
//			}
//		} else {
//			// 没有登录
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	@RequestMapping("/test")
//	public String test() {
//		return "user/securitycenter/success";
//	}
//
//	/********************************************************* 跳转页面方法 *************************************************************************************************/
//
//	/**
//	 * 
//	 * @Title: InsertUserPhoneDivShow @Description: TODO(普通用户安全中心
//	 *         跳转设置用户手机页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/InsertUserPhoneDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String InsertUserPhoneDivShow(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			return "user/securitycenter/insertUserPhone";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateLoginPwdDivShow @Description: TODO(普通用户安全中心
//	 *         跳转更改密码页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateLoginPwdDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String UpdateLoginPwdDivShow(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			// 返回密码更新页面
//			return "user/securitycenter/updatePassWord";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateTradingPwdDivShow @Description: TODO(普通用户安全中心
//	 *         跳转设置交易密码页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/insetTradingPwdDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String insetTradingPwdDivShow(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			return "user/securitycenter/insetTradingPassword";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateTradingPwdDivShow @Description: TODO(普通用户安全中心
//	 *         跳转修改交易密码页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateTradingPwdDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateTradingPwdDivShow(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			return "user/securitycenter/updateTradingPassword";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateTradingPwdDivShow @Description: TODO(普通用户安全中心 修改交易密码
//	 *         跳转根据原交易密码 修改页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateTradingPasswordPassedOldPwd", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateTradingPasswordPassedOldPwd(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			return "user/securitycenter/updateTradingPasswordPassedOldPwd";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateTradingPwdDivShow @Description: TODO(普通用户安全中心 设置密保问题
//	 *         跳转设置密保问题 页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/InsertSecurityQuestionDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String InsertSecurityQuestionDivShow(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			return "user/securitycenter/insertSecurityQuestion";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateSecurityQuestion @Description: TODO(普通用户安全中心
//	 *         跳转更改密保问题页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateSecurityQuestionDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String UpdateSecurityQuestionDivShow(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateSecurityQuestion";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateSecurityQuestionPassedPhone @Description: TODO(普通用户安全中心
//	 *         更改密保问题 跳转根据手机验证码修改页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateSecurityQuestionPassedPhone", method = { RequestMethod.GET, RequestMethod.POST })
//	public String UpdateSecurityQuestionPassedPhone(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateSecurityQuestionPassedPhone";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateSecurityQuestionPassedAnswer @Description: TODO(普通用户安全中心
//	 *         更改密保问题 跳转根据原密保问题修改页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateSecurityQuestionPassedAnswer", method = { RequestMethod.GET, RequestMethod.POST })
//	public String UpdateSecurityQuestionPassedAnswer(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateSecurityQuestionPassedAnswer";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateSecurityQuestionPassedAnswer @Description: TODO(普通用户安全中心
//	 *         更改密保问题 跳转重新设置密保问题页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/FormerAnswerCheckSeccessCallback", method = { RequestMethod.GET, RequestMethod.POST })
//	public String FormerAnswerCheckSeccessCallback(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			model.addAttribute("text1", "回答安全问题");
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateSecurityQuestionReset";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: Phone_Code_Check_SeccessCallback @Description: TODO(普通用户安全中心
//	 *         更改密保问题 跳转重新设置密保问题页面 手机验证码方式验证成功后跳转) @param @param
//	 *         request @param @param model @param @return 参数说明 @return String
//	 *         返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/PhoneCodeCheckSeccessCallback", method = { RequestMethod.GET, RequestMethod.POST })
//	public String PhoneCodeCheckSeccessCallback(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			model.addAttribute("text1", "获取手机验证码");
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateSecurityQuestionReset";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserPhoneDivShow @Description: TODO(普通用户安全中心 跳转更改用户手机
//	 *         页面) @param @param request @param @param model @param @return
//	 *         参数说明 @return String 返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserPhoneDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String UpdateUserPhoneDivShow(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateUserPhone";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateUserPhonePassedPhone @Description: TODO(普通用户安全中心 更改用户手机
//	 *         跳转根据原手机验证码 修改页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateUserPhonePassedPhone", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateUserPhonePassedPhone(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateUserPhonePassedPhone";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserPhoneCheckCodeSuccessCallback @Description:
//	 *         TODO(普通用户安全中心 更改用户手机号码 跳转重新设置手机号码页面) @param @param
//	 *         request @param @param model @param @return 参数说明 @return String
//	 *         返回类型 @author chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserPhoneCheckCodeSuccessCallback", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public String UpdateUserPhoneCheckCodeSuccessCallback(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			model.addAttribute("text1", "验证旧手机号");
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateUserPhoneReset";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: InsertUserEmailDivShow @Description: TODO(普通用户安全中心 设置用户邮箱
//	 *         跳转设置邮箱页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/InsertUserEmailDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String InsertUserEmailDivShow(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			return "user/securitycenter/insertUserEmail";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserEmailDivShow @Description: TODO(普通用户安全中心 修改用户邮箱
//	 *         跳转修改用户邮箱 list页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/UpdateUserEmailDivShow", method = { RequestMethod.GET, RequestMethod.POST })
//	public String UpdateUserEmailDivShow(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			if (userBaseAccountInfo.getEmail() != null) {
//				userBaseAccountInfo.setEmailstr(userBaseAccountInfo.getEmail().split("@")[0].substring(0, 4) + "****"
//						+ userBaseAccountInfo.getEmail().split("@")[0].substring(
//								(userBaseAccountInfo.getEmail().split("@")[0].length() - 4),
//								(userBaseAccountInfo.getEmail().split("@")[0].length()))
//						+ "@" + userBaseAccountInfo.getEmail().split("@")[1]);
//			}
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			return "user/securitycenter/updateUserEmail";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateUserEmailPassedOldEmail @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         跳转根据原邮箱验证码重置 页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateUserEmailPassedOldEmail", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateUserEmailPassedOldEmail(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			if (userBaseAccountInfo.getEmail() != null) {
//				userBaseAccountInfo.setEmailstr(userBaseAccountInfo.getEmail().split("@")[0].substring(0, 4) + "****"
//						+ userBaseAccountInfo.getEmail().split("@")[0].substring(
//								(userBaseAccountInfo.getEmail().split("@")[0].length() - 4),
//								(userBaseAccountInfo.getEmail().split("@")[0].length()))
//						+ "@" + userBaseAccountInfo.getEmail().split("@")[1]);
//			}
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			return "user/securitycenter/updateUserEmailPassedOldEmail";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateuseremaileditByOldPhone @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         跳转根据原手机验证码重置 页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateuseremaileditByOldPhone", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateuseremaileditByOldPhone(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			return "user/securitycenter/updateUserEmailPassedPhone";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateUserEmailResetByOldEmail @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         跳转重新设置新邮箱 页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateUserEmailResetByOldEmail", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateUserEmailResetByOldEmail(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("text", "验证原邮箱");
//			return "user/securitycenter/updateUserEmailReset";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateUserEmailResetByPhone @Description: TODO(普通用户安全中心 重置用户邮箱
//	 *         跳转重新设置新邮箱 页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateUserEmailResetByPhone", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateUserEmailResetByPhone(HttpServletRequest request, Model model) {
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("text", "验证手机验证码");
//			return "user/securitycenter/updateUserEmailReset";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//
//	/**
//	 * 
//	 * @Title: updateUserPhonePassedAnswer @Description: TODO(普通用户安全中心 更改用户手机
//	 *         跳转根据原密保问题修改页面) @param @param request @param @param
//	 *         model @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	@RequestMapping(value = "/updateUserPhonePassedAnswer", method = { RequestMethod.GET, RequestMethod.POST })
//	public String updateUserPhonePassedAnswer(HttpServletRequest request, Model model) {
//  		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", StringUtil.getBasePath(request));
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateUserPhonePassedAnswer";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//	
//	/**
//	 * 
//	* @Title: insertRealNameDivShow 
//	* @Description: TODO(实名认证  ) 
//	* @param @param request
//	* @param @param model
//	* @param @return  参数说明 
//	* @return String    返回类型 
//	* @author cjm
//	* @throws
//	 */
//	@RequestMapping(value = "/insertRealNameDivShow",method = {RequestMethod.GET, RequestMethod.POST })
//	public String insertRealNameDivShow(HttpServletRequest request, Model model){
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
// 			// 返回密码更新页面
//			return "user/securitycenter/insertRealName";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
// 	}
//
//	/**
//	 * 
//	 * @Title: UpdateUserPhoneCheckAnswerSuccessCallback
//	 * @Description: TODO(普通用户安全中心 更改用户手机号码 跳转重新设置手机号码页面)
//	 * @param @param
//	 *            request
//	 * @param @param
//	 *            model
//	 * @param @return
//	 *            参数说明
//	 * @return String 返回类型
//	 * @author chenjiaming
//	 * @throwsUpdateUserPhoneCheckAnswerSuccessCallback
//	 */
//	@RequestMapping(value = "/UpdateUserPhoneCheckAnswerSuccessCallback", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public String UpdateUserPhoneCheckAnswerSuccessCallback(HttpServletRequest request, Model model) {
//		String path = request.getContextPath();
//		String basePath;
//		int prot = request.getServerPort();
//		if (prot == 80) {
//			basePath = request.getScheme() + "://" + request.getServerName() + path;
//		} else {
//			basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
//		}
//		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
//				.getAttribute(Session_Constant.USER);
//		if (userBaseAccountInfo != null) {
//			UserAccountSafeInfo userAccountSafeInfo = (UserAccountSafeInfo) request.getSession()
//					.getAttribute(Session_Constant.USERACCOUNTSAFEINFO);
//			model.addAttribute("userBaseAccountInfo", userBaseAccountInfo);
//			model.addAttribute("basePath", basePath);
//			model.addAttribute("text1", "回答安全问题");
//			if (userAccountSafeInfo != null) {
//				model.addAttribute("userAccountSafeInfo", userAccountSafeInfo);
//			}
//			// 返回密码更新页面
//			return "user/securitycenter/updateUserPhoneReset";
//		} else {
//			// 跳转登录页面
//			return "redirect:/user/tologin.action";
//		}
//	}
//	
//	
//	 
//	/**
//	 * 
//	 * @Title: getSSMRecord @Description: TODO(短信发送记录 信息设置) @param @param
//	 *         userBaseAccountInfo @param @param mobile @param @param
//	 *         smscontent @param @param vercode @param @return 参数说明 @return
//	 *         SMSSendRecord 返回类型 @author chenjiaming @throws
//	 */
//	public SMSSendRecord getSSMRecord(UserBaseAccountInfo userBaseAccountInfo, String mobile, String remark,
//			String vercode) {
//		SMSSendRecord sMSSendRecord = new SMSSendRecord();
//		if (userBaseAccountInfo != null) {
//			/* 用户ID */
//			sMSSendRecord.setBaseid(userBaseAccountInfo.getId());
//			/* 用户名 */
//			sMSSendRecord.setUsername(userBaseAccountInfo.getLoginname());
//		}
//		/* 发送email */
//		sMSSendRecord.setMobile(mobile);
//		/* 短信验证码 */
//		sMSSendRecord.setVercode(vercode);
//		/* 发送时间 */
//		sMSSendRecord.setSendtime(new Date());
//		/* 发送的email 内容 */
//		sMSSendRecord.setRemark(remark);
//		/* 发送方式（1手工,2系统） */
//		sMSSendRecord.setSendtype((short) 2);
//		return sMSSendRecord;
//	}
//
//	/**
//	 * 
//	 * @Title: getEmailRecord @Description: TODO(邮件发送记录 信息设置) @param @param
//	 *         userBaseAccountInfo @param @param email @param @param
//	 *         emilaContent @param @return 参数说明 @return EmailRecord 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	public EmailRecord getEmailRecord(UserBaseAccountInfo userBaseAccountInfo, String email, String emilaContent) {
//		EmailRecord emailRecord = new EmailRecord();
//		if (userBaseAccountInfo != null) {
//			/* 用户ID */
//			emailRecord.setBaseid(userBaseAccountInfo.getId());
//			/* 用户名 */
//			emailRecord.setUsername(userBaseAccountInfo.getLoginname());
//		}
//		/* 发送email */
//		emailRecord.setEmail(email);
//		/* 发送时间 */
//		emailRecord.setSendtime(new Date());
//		/* 发送的email 内容 */
//		emailRecord.setEmailcontent(emilaContent);
//		/* 发送方式（1手工,2系统） */
//		emailRecord.setSendtype((short) 2);
//		return emailRecord;
//	}
//
//	/**
//	 * 
//	 * @Title: getQuestion @Description:
//	 *         TODO(根据浏览器选中的问题value值转成对应的文字答案) @param @param
//	 *         string @param @return 参数说明 @return String 返回类型 @author
//	 *         chenjiaming @throws
//	 */
//	public String getQuestion(String string) {
//		String str = "";
//		switch (string) {
//		case "1":
//			str = "您母亲的生日是？";
//			break;
//		case "2":
//			str = "您母亲的姓名是？";
//			break;
//		case "3":
//			str = "您父亲的生日是？";
//			break;
//		case "4":
//			str = "您父亲的姓名是？";
//			break;
//		case "5":
//			str = "您孩子的生日是？";
//			break;
//		case "6":
//			str = "您孩子的姓名是？";
//			break;
//		case "7":
//			str = "您配偶的生日是？";
//			break;
//		case "8":
//			str = "您配偶的姓名是？";
//			break;
//		case "9":
//			str = "您的出生地是哪里？";
//			break;
//		case "10":
//			str = "您最喜欢什么颜色？";
//			break;
//		case "11":
//			str = "您是什么学历？";
//			break;
//		case "12":
//			str = "您的属相是什么的？";
//			break;
//		case "13":
//			str = "您小学就读的是哪所学校？";
//			break;
//		case "14":
//			str = "您最崇拜谁？";
//			break;
//		case "15":
//			str = "您打字经常用什么输入法？";
//			break;
//		case "16":
//			str = "您是什么时间参加工作的？";
//			break;
//		default:
//			break;
//		}
//		return str;
//	}
//
//	@RequestMapping("/test2")
//	public String test2() {
//		return "user/securitycenter/updateUserEmailSetUp";
//	}
//}
