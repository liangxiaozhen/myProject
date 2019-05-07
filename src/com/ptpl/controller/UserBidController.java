package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Red_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.model.GuaranteeFee;
import com.ptpl.model.MediacyFee;
import com.ptpl.model.Plus;
import com.ptpl.model.RemoveName;
import com.ptpl.model.RiskGuarantyMoney;
import com.ptpl.model.TenderItem;
import com.ptpl.model.TltemRNameLink;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.model.UserTender;
import com.ptpl.model.loanapp;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.MediacyFeeServiceI;
import com.ptpl.service.PlusServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.TltemRNameLinkServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 用户主动投标
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/user/initiativeBid")
public class UserBidController extends BaseController {	
	
	/** 投标记录service */
	@Autowired
	UserTenderServiceI userTenderService;
	
	/** 标的设置service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	/** 用户账户信息安全service */
	@Autowired
	UserAccountSafeInfoServiceI userAccountSafeInfoService;

	/** 用户账户service */
	@Autowired
	UserAccountServiceI userAccountService;
	
	/** 借款申请记录service */
	@Autowired
	loanappServiceI loanappService;
	
	/** 用户托管账户信息service */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	/** 名单表service */
	@Autowired
	RemoveNameServiceI removeNameService;
	
	/** 标的居间费设置service */
	@Autowired
	MediacyFeeServiceI mediacyFeeService;
	
	/** 标的担保费设置service */
	@Autowired
	GuaranteeFeeServiceI guaranteeFeeService;
	
	/** 标的风险保证金设置service */
	@Autowired
	RiskGuarantyMoneyServiceI riskGuarantyMoneyService;
	
	/** 标的增益设置service */
	@Autowired
	PlusServiceI plusService;
	
	/** 投标排除名单关联表service */
	@Autowired
	TltemRNameLinkServiceI tltemRNameLinkService;
	
	/** 用户使用券service */
	@Autowired
	UserInterestRateCouponServiceI userInterestRateCouponService;
	
	/** 用户红包service */
	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;
	
	static Boolean flag = false;
	

	/**
	 * 跳转至理财项目页面
	 * @param tenderItem
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryTender")
	public ModelAndView queryTender(TenderItem tenderItem) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		if (userBaseAccountInfo == null) {
			mv.setViewName("user/login");
		}else{
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			Map<String, Object> maps = new HashMap<String, Object>();
			initPage(maps, pageNum, pageSize);
//			tenderItem.setTstatus((short) 4);
			List<TenderItem> tenderItemList = tenderItemService.selectByCondition(tenderItem);
			PageInfo<Object> pagehelper = initPagehelper(maps, tenderItemList);
			mv.addObject("pagehelper", pagehelper);
			mv.setViewName("user/financialProject/FinancialProject_List");
		}
		return mv;
	}

	/**
	 * 去投标
	 * @param tenderItem
	 * @param id（标的设置表ID）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("investing")
	public ModelAndView investing(BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		if (userBaseAccountInfo == null) {
			mv.setViewName("user/login");
		}else{
			TenderItem item = tenderItemService.findTenderItemById(id);
			if(item.getFinishtamount() == null){
				item.setFinishtamount((double) 0);
			}
			double surplusAmout = Arith.sub(item.getTamount(), item.getFinishtamount());
			UserAccount userAccount = userAccountService.getUserAccountByBaseId(userBaseAccountInfo.getId()); // 根据baseid查询用户账户信息
			List<Object> list = new ArrayList<Object>();
			if(item.getIsaplus() != null && item.getIsaplus() == 1){ // 判断是否允许使用增益
				System.out.println("此项目可以使用增益！！！！！！");
				Plus plus = plusService.findPlusByTid(item.getId()); // 根据标的ID查询标的增益设置表
				if(plus != null){
					if(plus.getIsaint() != null && plus.getIsaint() == 1){ // 判断是否允许使用加息券
						System.out.println("此项目可以使用加息券！！！！！！");
						UserInterestRateCoupon coupon = new UserInterestRateCoupon();
						coupon.setBaseid(userBaseAccountInfo.getId());
						coupon.setIsuse(ActAward_Constant.AWARD_CANUSE); // 可使用
						coupon.setUirctype(ActAward_Constant.UIRCTYPE_JIAXI); // 加息券
						List<UserInterestRateCoupon> interestRateCoupons = userInterestRateCouponService.findIsUseByBaseid(coupon);
						list.addAll(interestRateCoupons);
					}
					if(plus.getIsavoucher() != null && plus.getIsavoucher() == 1){ // 判断是否允许使用类现金
						UserRedEnvelope red = new UserRedEnvelope();
						red.setBaseid(userBaseAccountInfo.getId());
						red.setIsuse(ActAward_Constant.AWARD_CANUSE);
						red.setRetype(Red_Constant.RETYPE_LEIXIANJIN);
						List<UserRedEnvelope> likeVouchers = userRedEnvelopeService.selectByBaseid(red);
						list.addAll(likeVouchers);
						System.out.println("此项目可以使用类现金！！！！！！");
					}
					if(plus.getIsalikevoucher() != null && plus.getIsalikevoucher() == 1){ // 判断是否允许使用假现金
						UserRedEnvelope red = new UserRedEnvelope();
						red.setBaseid(userBaseAccountInfo.getId());
						red.setIsuse(ActAward_Constant.AWARD_CANUSE);
						red.setRetype(Red_Constant.RETYPE_JIAXIANJIN);
						List<UserRedEnvelope> fakeVouchers = userRedEnvelopeService.selectByBaseid(red);
						list.addAll(fakeVouchers);
						System.out.println("此项目可以使用假现金！！！！！！");
					}
				}
			}
			mv.addObject("avlbalance", userAccount.getAvlbalance());
			mv.addObject("list", list);
			mv.addObject("surplusAmout", surplusAmout);
			mv.addObject("iscancheattender", userBaseAccountInfo.getIscancheattender());
			mv.addObject("item", item);
			mv.setViewName("user/financialProject/FinancialProject");
		}
		return mv;
	}
	
	/**
	 * 主动投标校验
	 * 1.判断标的是否存在
	 * 2.判断标的是否满标
	 * 3.判断投资人是否是借款人
	 * 4.判断是否已下架
	 * 5.判断投资人是否是允许投标等级
	 * 6.判断投资人是否在排除人员名单
	 * 7.判断投资是否超过单人投标次数限制
	 * 8.判断投资人输入的投资金额是否为空
	 * 9.判断投资人投资金额是否大于账户余额
	 * 10.判断投资人投资金额是否超过累投金额限制
	 * 11.判断投资人投资金额是否低于单笔允许最低投资金额
	 * 12.判断投资人投资金额是否高于单笔允许最高投资金额
	 * 13.判断投资人投资金额是否大于剩余可投标金额
	 * 14.判断投资人约标码是否有效
	 * 15.判断是否允许使用加息券（单次张数、总计张数、单张收益）
	 * 16.判断是否允许使用类现金（单次张数、总计张数、单张金额）
	 * 17.判断是否允许使用假现金（单次张数、总计张数、单张金额）
	 * TODO	1.假投标（只修改完成投标金额与新增投标记录） 2.投标客户端来源 
	 * @param id（标的设置表ID）
	 * @param amount（投标金额）
	 * @param tpass（约标码）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tenderCheck")
	public synchronized String tenderCheck(String id, String amount, String tpass) throws Exception{
		System.out.println("进入投标校验！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		Map<String, String> map = new HashMap<String, String>();
		String info = "success";
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		if(userBaseAccountInfo == null)
			return "redirect:user/login.action";
		BigDecimal baseId = userBaseAccountInfo.getId(); // 投资人baseid
		if(id == null || id.trim().equals("")){ // 判断前台传来的id是否为空
			info = "不存在的标！";
		}else{
			TenderItem item = tenderItemService.findTenderItemById(new BigDecimal(id));	// 根据id查询标的设置详情
			if (item == null) { // 判断标的是否存在
				info = "不存在的标！";
			}else{
				if(item.getFinishtamount() == null){
					item.setFinishtamount(0.00);
				}
				if(Arith.sub(item.getFinishtamount(), item.getTamount()) >= 0){	// 判断满标
					info = "已满标";
				}else{
					loanapp loan = loanappService.selectByPrimaryKey(item.getLoanappid()); // 根据借款申请ID查询借款人申请信息
					if(loan != null && baseId.toString().equalsIgnoreCase(loan.getBaseid().toString())){ // 判断投资人人是否是标的设置中的还款人
						info = "投资人不能为借款人";
					}else{
						if(new Date().getTime() >= item.getTendtime().getTime()){
							info = "此项目已经下架";
						}else{
							UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(baseId);//	根据baseid查询用户账户安全信息表
							Short indexStr = userAccountSafeInfo.getUgrade(); // 获取投资用户等级
							System.out.println("Ugrestrict=========================="+item.getUgrestrict());
							System.out.println("用户等级=========================="+indexStr);
							if(item.getUgrestrict() != null){
								char[] ugrestrict = item.getUgrestrict().toCharArray(); // 标的设置表允许投标等级转换为字符数组
								if(ugrestrict.length < indexStr || ugrestrict[indexStr-1] == '0'){ // 判断投资用户是否在允许投标的会员等级中
									info = "您所在等级限制投此标！";
								}else{
									RemoveName removeName = null;
									List<TltemRNameLink> tenderRemoveName = tltemRNameLinkService.findTenderRemoveNameByTid(item.getId());
									if(tenderRemoveName != null){ // 投标排除名单关联表为空则投资人不在投标排除名单
										// 判断投资用户是否在投标排除名单中（排除名单会有多个）
										RemoveName rName = new RemoveName();
										for(TltemRNameLink name: tenderRemoveName){
											rName.setBaseid(baseId);
											rName.setNameno(name.getRnameno());
											removeName = removeNameService.selectByNameNoAndBaseId(rName); // 跟据名单编号和baseid查询投资用户是否在排除名单中
										}
									}
									if(removeName != null){
										info = "您在投标排除名单！";
									}else{
										UserTender tender = new UserTender();
										tender.setTenderid(item.getId());
										tender.setOutaccountid(baseId);
										tender.setUtproperty(TenderRecord_Constant.UTPROPERTY_ORIGINAL);
										tender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT);
										List<UserTender> total = userTenderService.findTenderList(tender); // 查询投资人当前标的投资记录
										double finishAmount = 0;
										for(UserTender userTender : total){
											finishAmount = Arith.add(finishAmount, userTender.getAmount()); // 查询投资人当前标的已投总金额
										}
										System.out.println("投资人已投标金额"+finishAmount);
										System.out.println("投资人已投标次数========================="+total.size());
										if(total.size() >= item.getOnettimes()){ // 判断用户已投标次数是否超过单人投标限制
											info = "单人投标限制"+item.getOnettimes()+"次";
										}else{
											if(amount == null || amount.trim().equals("")){  // 判断投标金额是否为空
												info = "投标金额不能为空！";
											}else{
												Double famount = Double.valueOf(amount); // 填入的投资金额转换数据类型
												UserAccount ua = userAccountService.getUserAccountByBaseId(baseId);
												Double avlbalance = ua.getAvlbalance(); // 投资人账户可用余额
												if(Arith.sub(avlbalance, famount) < 0){ // 判断投资金额是否大于账户余额
													info = "投资金额大于账户余额";
												}else{
													double totalAmount = Arith.add(famount, finishAmount); // 当前投标金额+已投金额
													if(Arith.sub(totalAmount, item.getTotalmoneyrestrict()) > 0){
														info = "超过累投金额限制！";
													}else{
														Double minoncetamount = item.getMinoncetamount(); // 单笔允许最低投资金额
														Double maxoncetamount = item.getMaxoncetamount(); // 单笔允许最高投资金额
														if(Arith.sub(minoncetamount, famount) > 0){ // 判断是否低于单笔允许最低投资金额
															info = "不能低于单笔允许最低投资金额！";
														}else{
															if(Arith.sub(maxoncetamount, famount) < 0){ // 判断是否高于单笔允许最高投资金额
																info = "不能高于单笔允许最高投资金额！";
															}else{
																double remaining = Arith.add(famount, item.getFinishtamount()); // 当前投标金额+已完成投标金额
																System.out.println("当前投标金额+已完成投标金额=========="+remaining);
																if(Arith.sub(item.getTamount(), remaining) < 0){ // 剩余可投标金额
																	System.out.println("剩余可投标金额=========="+Arith.sub(item.getTamount(), remaining));
																	info = "当前投标金额大于剩余可投标金额";
																}else{
																	if(item.getIsappointtender() == 0){ // 判断是否为约标
																		System.out.println("约标码================"+item.getTpass());
																		System.out.println("输入的约标码================"+tpass);
																		if(tpass == null || tpass.trim().equals("")){
																			info = "约标码不能为空";
																		}else{
																			if(!tpass.trim().equalsIgnoreCase(item.getTpass())){
																				info = "您输入的约标码有误";
																			}
																		}
																	}
//																	if(item.getIsaplus() == 1){ // 判断是否允许使用增益
//																		System.out.println("此项目可以使用增益！！！！！！");
//																		Plus condition = new Plus();
//																		condition.setTid(item.getId()); // 标号ID
//																		List<Plus> plusList = plusService.selectByCondition(condition);
//																		Plus plus = plusList.get(0);
//																		if(plus.getIsaint() == 1){ // 判断是否允许使用加息券
//																			System.out.println("此项目可以使用加息券！！！！！！");
//																			Integer aOnceInt = plus.getAonceint(); // 单次允许使用加息张数
//																			Integer aTotalInt = plus.getAtotalint(); // 允许使用加息总张数
//																			Double aOneQrofit = plus.getAoneqrofit(); // 允许单张加息收益
//																		}
//																		if(plus.getIsavoucher() == 1){ // 判断是否允许使用类现金
//																			System.out.println("此项目可以使用类现金！！！！！！");
//																			Integer aOnceVoucher = plus.getAoncevoucher(); // 单次允许类现金卷张数
//																			Integer aTotalVoucher = plus.getAtotalvoucher(); // 总计允许类现金卷张数
//																			Double aOneVAmount = plus.getAonevamount(); // 允许单张类现金额度
//																		}
//																		if(plus.getIsalikevoucher() == 1){ // 判断是否允许使用假现金
//																			System.out.println("此项目可以使用假现金！！！！！！");
//																			Integer aOnceLikeVoucher = plus.getAoncelikevoucher(); // 单次允许假现金卷张数
//																			Integer aTotalLikeVoucher = plus.getAtotallikevoucher(); // 总计允许假现金卷张数
//																			Double aOneLVAmount = plus.getAonelvamount(); // 允许单张假现金额度
//																		}
//																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		map.put("info", info); 
		sendJsonData(response, JSON.toJSONString(map));
		System.out.println(info);
		if(info.equalsIgnoreCase("success")){
			flag = true;
		}
		return null;
	}
	
	/**
	 * 主动投标请求参数
	 * 1.保存投标记录
	 * @param id（标的设置表ID）
	 * @param amount（投标金额）
	 * @throws Exception
	 */
	@RequestMapping("/initiativeTender")
	private synchronized void initiativeTender(BigDecimal id, String amount) throws Exception {
		if(!flag)
			return;
		flag = false;
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		if(userFsAccountInfo == null)
			return;
		System.out.println("主动投标请求参数设置！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		HuifuParams huifuparam = new HuifuParams();
		huifuparam.setVersion("20"); 																		// 版本号
		huifuparam.setCmdId("InitiativeTender"); 															// 消息类型，此处为InitiativeTender
		huifuparam.setOrdId(StringUtil.getNo());															// 投标订单号
		Date tenderTime = new Date();
		huifuparam.setOrdDate(StringUtil.formatDate(tenderTime, "yyyyMMdd")); 								// 投标订单日期
		huifuparam.setTransAmt(df1.format(Double.valueOf(amount))); 										// 交易金额
		huifuparam.setUsrCustId(userFsAccountInfo.getUsrcustid()); 											// 用户客户号-投资人客户号
		huifuparam.setMaxTenderRate("0.50"); 																// 最大投资手续费率-阀值（放款：手续费<=放款金额*最大投资手续费率MaxTenderRate）
		TenderItem item = tenderItemService.findTenderItemById(id);	 // 获得标的设置详情
		loanapp loan = loanappService.selectByPrimaryKey(item.getLoanappid()); // 根据借款申请ID查询借款人申请信息
		if(loan == null)
			return;
		UserFsAccountInfo uf = userFsAccountInfoService.findUserFsAccountInfoByBaseId(loan.getBaseid()); 	// 根据借款人basid查询托管信息
		huifuparam.setBorrowerCustId(uf.getUsrcustid()); 													// 借款人客户号
		huifuparam.setBorrowerAmt(huifuparam.getTransAmt()); 												// 借款金额
		huifuparam.setBorrowerRate("1.00"); 																// 借款手续费率-阀值（还款：本次还款金额（TransAmt+fee）+已还款金额（TransAmt+fee）<=投标金额*（1+借款手续费率BorrowerRate））
		huifuparam.setProId(item.getTno()); 																// 项目ID
		huifuparam.setBorrowerDetails("[{\"BorrowerCustId\":\""+huifuparam.getBorrowerCustId()+"\",\"BorrowerAmt\":\""+huifuparam.getBorrowerAmt()+"\",\"BorrowerRate\":\""+huifuparam.getBorrowerRate()+"\",\"ProId\":\""+huifuparam.getProId()+"\"}]"); // 借款人信息
		huifuparam.setIsFreeze("Y"); 																		// 是否冻结-默认冻结，以免放款时余额不足
		huifuparam.setFreezeOrdId(huifuparam.getOrdId()); 													// 冻结订单号
		// 动态获取项目路径
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuparam.setRetUrl(basePath+"/huifu/initiativeTender/initiativeTenderRetUrl.action"); 			// 页面返回URL
		huifuparam.setBgRetUrl(basePath+"/huifu/initiativeTender/initiativeTenderCallBack.action"); 		// 商户后台应答地址
		// 若为中文，请用Base64转码
		huifuparam.setMerPriv(HttpClientHandler.getBase64Encode(item.getId().toString())); 					// 商户私有域（将标的设置表id放入）
//		huifuparam.setReqExt("{\"Vocher\":{\"AcctId\":\"SDT000001\",\"VocherAmt\":\"5.00\"}}"); // 入参扩展域
//		huifuparam.setAcctId(""); // 代金券出账子账户
//		huifuparam.setVocherAmt(""); // 代金券金额
		UserTender ut = new UserTender();
		ut.setTenderid(item.getId());									// 标的号id
		ut.setOrderno(huifuparam.getOrdId());							// 投标订单号
		ut.setUtproperty(TenderRecord_Constant.UTPROPERTY_ORIGINAL);	// 投标属性-1.原始投标，2.债转投标，3.假投标
		ut.setOutaccountid(userFsAccountInfo.getBaseid());				// 投资方ID
		ut.setInaccountid(loan.getBaseid()); 							// 借款方用户ID
		ut.setAmount(Double.valueOf(huifuparam.getTransAmt())); 		// 投标金额
		ut.setIsda(TenderRecord_Constant.ISDA_NO); 						// 是否债转-未债转
		ut.setTendertype(TenderRecord_Constant.TENDERTYPE_MANUAL); 		// 投标方式-手动投标
		ut.setIsrepayend(TenderRecord_Constant.ISREPAYEND_NO); 			// 还款完成-否
		ut.setTbegintime(tenderTime); 									// 投标开始时间
		// 判断是否设置居间费
		if(item.getIsamediacy() != null && item.getIsamediacy() == 1){
			calculationMediacyFee(item, ut);
		}else{
			ut.setMediacyfee((double) 0);
		}
		// 判断是否设置担保
		if(item.getIsaguarantee() != null && item.getIsaguarantee() == 1){
			calculationGuaranteeFee(item, ut);
		}else{
			ut.setGuaranteefee((double) 0);
		}
		// 判断是否设置风险保证金
		if(item.getIsariskgm() != null && item.getIsariskgm() == 1){
			calculationRiskGuarantyMoney(item, ut, loan.getBaseid());
		}else{
			ut.setRiskguarantyfee((double) 0);
		}
		// 判断是否允许债转
		if(item.getIsadebtattorn() == null || item.getIsadebtattorn() == 0){ // 未设置标的债转或标的不允许债权转让
			ut.setIsallowda(TenderRecord_Constant.ISALLOWDA_NO);		// 不能债转
		}else if(item.getIsadebtattorn() == 1){ // 允许债权转让
			ut.setIsallowda(TenderRecord_Constant.ISALLOWDA_YES);		// 能债转
		}
		ut.setFee(Arith.add(Arith.add(ut.getMediacyfee(), ut.getGuaranteefee()), ut.getRiskguarantyfee())); // 手续费（居间服务费+担保费+风险保证金）
		ut.setTransfertype(TenderRecord_Constant.TRANSFERTYPE_ACCOUNT); // 转账类型-账户转账
		if(System.getProperty("os.name").contains("Windows")){
			System.out.println("==========================PC");
			ut.setOriginclient(TenderRecord_Constant.ORIGINCLIENT_PC);	// 投标设备来源
		}
		ut.setTproperty(item.getTpro()); 								// 标的属性
		ut.setAppointtenderpass(item.getTpass()); 						// 约标码
		if(huifuparam.getIsFreeze().trim().equals("N")){
			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_NOT_FREEZE); 	// 是否冻结-不冻结
		}else if(huifuparam.getIsFreeze().trim().equals("Y")){
			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_FREEZE); 		// 是否冻结-冻结
		}
		ut.setTstatus(TenderRecord_Constant.TSTATUS_INITIAL); 			// 投标的状态-初始
		ut.setIsblending(TenderRecord_Constant.ISBLENDING_NO); 			// 是否系统勾兑
		ut.setIsmanblending(TenderRecord_Constant.ISMANBLENDING_NO); 	// 是否人工勾兑
		ut.setPaycompany("汇付天下");										// 投标通道公司-汇付天下
		ut.setIsaudit(TenderRecord_Constant.ISAUDIT_NO); 				// 是否审核
		ut.setRemark("测试用");								// 备注
		System.out.println("居间服务费======================="+ut.getMediacyfee());
		System.out.println("居间服务费收款人======================="+ut.getMrecman());
		System.out.println("居间费收款人商户号======================="+ut.getMrecmancustid());
		System.out.println("担保费======================="+ut.getGuaranteefee());
		System.out.println("担保服务费收款人======================="+ut.getGfrecman());
		System.out.println("担保费收款人商户号======================="+ut.getGfrecmancustid());
		System.out.println("风险保证金======================="+ut.getRiskguarantyfee());
		System.out.println("风险保证金收款人======================="+ut.getRgmrecman());
		System.out.println("风险保证金收款人商户号======================="+ut.getRgmrecmancustid());
		int count = 0;
		count = userTenderService.insertSelective(ut); // 保存部分投标记录
		if(count > 0){
			System.out.println("保存投标记录成功！！！！！！！！！！！！！！！！！！！！！！！！");
		}
		hfInitiativeTender(huifuparam);
	}

	/**
	 * 计算标的居间服务费
	 * @param item
	 * @param userTender
	 */
	private void calculationMediacyFee(TenderItem item, UserTender userTender) {
		System.out.println("计算标的居间服务费！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		MediacyFee mediacy = new MediacyFee();
		mediacy.setTid(item.getId()); // 标号ID
		List<MediacyFee> mediacyFeeList = mediacyFeeService.listMediacyFee(mediacy); // 根据标的设置表ID关联标的居间费设置表
		Double fee = (double) 0; // 居间费
		BigDecimal mfId = null; // 居间费设置表ID
		Double tamount = userTender.getAmount(); // 标的金额（投标金额）
		for(MediacyFee mediacyFee : mediacyFeeList){
			// 判断居间费收费类型-1.结标时收取2.投标时收取
			if(mediacyFee.getChargetype() == 1){ // 结标时收取居间费
				if(tamount > mediacyFee.getMinnmmoney() && tamount <= mediacyFee.getMaxnmmoney()){ // 判断结标金额所在段
					if(mediacyFee.getMfquota() != null){ // 判断是否按定额收取
						System.out.println("居间服务费定额"+mediacyFee.getMfquota());
						fee = mediacyFee.getMfquota();
						mfId = mediacyFee.getId();
					}else if(mediacyFee.getMfpercent() != null){ // 判断是否是按百分比收取
						System.out.println("居间服务费百分比"+mediacyFee.getMfpercent());
						if(Arith.mul(tamount, mediacyFee.getMfpercent()) < mediacyFee.getMinmffee()){
							fee = mediacyFee.getMinmffee(); // 若小于该段最低居间费收费金额则居间费为该段最低居间费收费金额
							mfId = mediacyFee.getId();
						}else if(Arith.mul(tamount, mediacyFee.getMfpercent()) > mediacyFee.getMaxmffee()){
							fee = mediacyFee.getMaxmffee(); // 若大于该段最低居间费收费金额则居间费为该段最高居间费收费金额
							mfId = mediacyFee.getId();
						}else{
							fee = Arith.mul(tamount, mediacyFee.getMfpercent());
							mfId = mediacyFee.getId();
						}
					}
				}
			}else if(mediacyFee.getChargetype() == 2){ // 投标时收取居间费
				System.out.println("居间费费率"+mediacyFee.getMfrate());
				if(Arith.mul(tamount, mediacyFee.getMfrate()) < mediacyFee.getMinmfamount()){
					fee = mediacyFee.getMinmfamount(); // 若小于居间费最低收费则居间费为居间费最低收费
					mfId = mediacyFee.getId();
				}else if(Arith.mul(tamount, mediacyFee.getMfrate()) > mediacyFee.getMaxmfamount()){
					fee = mediacyFee.getMaxmfamount(); // 若大于居间费最高收费则居间费为居间费最高收费
					mfId = mediacyFee.getId();
				}else{
					fee = Arith.mul(tamount, mediacyFee.getMfrate());
					mfId = mediacyFee.getId();
				}
			}
		}
		userTender.setMfid(mfId); // 居间费设置表ID
		userTender.setMediacyfee(fee); // 居间服务费
	}

	/**
	 * 计算标的担保费
	 * @param item
	 * @param userTender
	 */
	private void calculationGuaranteeFee(TenderItem item, UserTender userTender) {
		System.out.println("计算标的担保费！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		GuaranteeFee guarantee = new GuaranteeFee();
		guarantee.setTid(item.getId()); // 标号ID
		List<GuaranteeFee> guaranteeFeeList = guaranteeFeeService.selectByCondition(guarantee); // 根据标的设置表ID关联标的担保费设置表
		Double fee = (double) 0; // 担保费
		BigDecimal gfId = null; // 担保费设置表ID
		Double tamount = userTender.getAmount(); // 标的金额（投标金额）
		for(GuaranteeFee guaranteeFee : guaranteeFeeList){
			if(guaranteeFee.getChargetype() == 1){ // 结标时收取担保费
				if(tamount > guaranteeFee.getMinnmmoney() && tamount <= guaranteeFee.getMaxnmmoney()){ // 判断结标金额所在段
					if(guaranteeFee.getGfquota() != null){ // 判断是否按定额收取
						System.out.println("担保费定额"+guaranteeFee.getGfquota());
						fee = guaranteeFee.getGfquota();
						gfId = guaranteeFee.getId();
					}else if(guaranteeFee.getGfpercent() != null){ // 判断是否是按百分比收取
						System.out.println("担保费百分比"+guaranteeFee.getGfpercent());
						if(Arith.mul(tamount, guaranteeFee.getGfpercent()) < guaranteeFee.getMingffee()){
							fee = guaranteeFee.getMingffee(); // 若小于该段最低担保费收费金额则居间费为该段最低担保费收费金额
							gfId = guaranteeFee.getId();
						}else if(Arith.mul(tamount, guaranteeFee.getGfpercent()) > guaranteeFee.getMaxgffee()){
							fee = guaranteeFee.getMaxgffee(); // 若大于该段最低担保费收费金额则居间费为该段最高担保费收费金额
							gfId = guaranteeFee.getId();
						}else{
							fee = Arith.mul(tamount, guaranteeFee.getGfpercent());
							gfId = guaranteeFee.getId();
						}
					}
				}
			}else if(guaranteeFee.getChargetype() == 2){ // 投标时收取担保费
				System.out.println("担保费费率"+guaranteeFee.getGfrate());
				if(Arith.mul(tamount, guaranteeFee.getGfrate()) < guaranteeFee.getMingfamount()){
					fee = guaranteeFee.getMingfamount(); // 若小于担保费最低收费则居间费为担保费最低收费
					gfId = guaranteeFee.getId();
				}else if(Arith.mul(tamount, guaranteeFee.getGfrate()) > guaranteeFee.getMaxgfamount()){
					fee = guaranteeFee.getMaxgfamount(); // 若大于担保费最高收费则居间费为担保费最高收费
					gfId = guaranteeFee.getId();
				}else{
					fee = Arith.mul(tamount, guaranteeFee.getGfrate());
					gfId = guaranteeFee.getId();
				}
			}
		}
		userTender.setGfid(gfId); // 担保费设置表ID
		userTender.setGuaranteefee(fee); // 担保服务费
	}

	/**
	 * 计算标的风险保证金
	 * @param item
	 * @param userTender
	 * @param baseId
	 */
	private void calculationRiskGuarantyMoney(TenderItem item, UserTender userTender, BigDecimal baseId) {
		System.out.println("计算标的风险保证金！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		RiskGuarantyMoney risk = new RiskGuarantyMoney();
		risk.setTid(item.getId()); // 标号ID
		List<RiskGuarantyMoney> riskFeeList = riskGuarantyMoneyService.selectByCondition(risk); // 根据标的设置表ID关联标的风险保证金设置表
		Double fee = (double) 0; // 风险保证金
		BigDecimal rgmID = null; // 风险保证金设置表ID
		Double tamount = userTender.getAmount(); // 标的金额（投标金额）
		for(RiskGuarantyMoney riskMoney : riskFeeList){ // 结标时收取风险保证金
			if(riskMoney.getChargetype() == 1){
				if(tamount > riskMoney.getMinrgmmoney() && tamount <= riskMoney.getMaxrgmmoney()){ // 判断结标金额所在段
					if(riskMoney.getRgmquota() != null){ // 判断是否按定额收取
						System.out.println("风险保证金定额"+riskMoney.getRgmquota());
						fee = riskMoney.getRgmquota();
						rgmID = riskMoney.getId();
					}else if(riskMoney.getRgmpercent() != null){ // 判断是否是按百分比收取
						System.out.println("风险保证金百分比"+riskMoney.getRgmpercent());
						if(Arith.mul(tamount, riskMoney.getRgmpercent()) > riskMoney.getMaxrgmfee()){
							fee = riskMoney.getMaxrgmfee(); // 若大于该段最高风险保证金额则风险保证金为该段最高风险保证金额
							rgmID = riskMoney.getId();
						}else{
							fee = Arith.mul(tamount, riskMoney.getRgmpercent());
							rgmID = riskMoney.getId();
						}
					}
				}
			}else if(riskMoney.getChargetype() == 2){
				UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(baseId);//	根据baseid查询用户账户安全信息表
				Short indexStr = userAccountSafeInfo.getUgrade(); // 获取借款用户等级
				System.out.println("用户等级=========================="+indexStr);
				char[] ugrade = riskMoney.getUgrade().toCharArray(); // 标的设置表允许投标等级转换为字符数组
				if(ugrade[indexStr-1] == '1'){ // 判断投资用户是否包含在风险保证金的会员等级中
					if(Arith.mul(tamount, riskMoney.getRgmrate()) > riskMoney.getMaxrgmamount()){
						fee = riskMoney.getMaxrgmamount(); // 若大于风险保证金最高收费则风险保证金为风险保证金最高收费
						rgmID = riskMoney.getId();
					}else{
						fee = Arith.mul(tamount, riskMoney.getRgmrate());
						rgmID = riskMoney.getId();
					}
				}
			}
		}
		userTender.setRgmid(rgmID);; // 风险保证金设置表ID
		userTender.setRiskguarantyfee(fee); // 风险保证金
	}

	/**
	 * 汇付天下-主动投标接口请求参数拼接
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public synchronized void hfInitiativeTender(HuifuParams huifuparam) throws Exception {
		System.out.println("开始拼接主动投标接口请求参数！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuparam.getVersion()))						// 版本号
				.append(StringUtils.trimToEmpty(huifuparam.getCmdId()))						// 消息类型-InitiativeTender
				.append(StringUtils.trimToEmpty(huifuparam.getMerCustId()))					// 商户号
				.append(StringUtils.trimToEmpty(huifuparam.getOrdId()))						// 订单号-投标订单号
				.append(StringUtils.trimToEmpty(huifuparam.getOrdDate()))					// 订单日期-投标订单日期
				.append(StringUtils.trimToEmpty(huifuparam.getTransAmt()))					// 交易金额-投标金额
				.append(StringUtils.trimToEmpty(huifuparam.getUsrCustId()))					// 用户客户号-投资人客户号
				.append(StringUtils.trimToEmpty(huifuparam.getMaxTenderRate()))				// 最大投资手续费率
				.append(StringUtils.trimToEmpty(huifuparam.getBorrowerDetails()))			// 借款手续费率
				.append(StringUtils.trimToEmpty(huifuparam.getIsFreeze()))					// 是否冻结
				.append(StringUtils.trimToEmpty(huifuparam.getFreezeOrdId()))				// 冻结订单号
				.append(StringUtils.trimToEmpty(huifuparam.getRetUrl()))					// 页面返回 URL
				.append(StringUtils.trimToEmpty(huifuparam.getBgRetUrl()))					// 商户后台应答地址
				.append(StringUtils.trimToEmpty(huifuparam.getMerPriv()))					// 商户私有域
				.append(StringUtils.trimToEmpty(huifuparam.getReqExt()));					// 入参扩展域
		String plainStr = buffer.toString();
		System.out.println("组装主动投标加签字符串原文:========="+plainStr);							// 组装主动投标加签字符串原文
		try {
			String ChkValue = SignUtils.encryptByRSA(plainStr);							// 主动投标加签字符串加签文
 			if(StringUtil.isNotEmpty(ChkValue)){
 				huifuparam.setChkValue(ChkValue);
 			}
			request.setAttribute("Version", huifuparam.getVersion());
			request.setAttribute("CmdId", huifuparam.getCmdId());
			request.setAttribute("MerCustId", huifuparam.getMerCustId());
			request.setAttribute("OrdId", huifuparam.getOrdId());
			request.setAttribute("OrdDate", huifuparam.getOrdDate());
			request.setAttribute("TransAmt", huifuparam.getTransAmt());
			request.setAttribute("UsrCustId", huifuparam.getUsrCustId());
			request.setAttribute("MaxTenderRate", huifuparam.getMaxTenderRate());
			request.setAttribute("BorrowerDetails", huifuparam.getBorrowerDetails().replaceAll("\"", "&quot;"));
			request.setAttribute("BorrowerCustId", huifuparam.getBorrowerCustId());
			request.setAttribute("BorrowerAmt", huifuparam.getBorrowerAmt());
			request.setAttribute("BorrowerRate", huifuparam.getBorrowerRate());
			request.setAttribute("ProId", huifuparam.getProId());
			request.setAttribute("IsFreeze", huifuparam.getIsFreeze());
			request.setAttribute("FreezeOrdId", huifuparam.getFreezeOrdId());
			request.setAttribute("RetUrl", huifuparam.getRetUrl());
			request.setAttribute("BgRetUrl", huifuparam.getBgRetUrl());
			request.setAttribute("MerPriv", huifuparam.getMerPriv());
			if(huifuparam.getReqExt() != null){
				request.setAttribute("ReqExt", huifuparam.getReqExt().replaceAll("\"", "&quot;"));
			}
			request.setAttribute("AcctId", huifuparam.getAcctId());
			request.setAttribute("VocherAmt", huifuparam.getVocherAmt());
			request.setAttribute("ChkValue", ChkValue);
			request.getRequestDispatcher("/WEB-INF/pages/InitiativeTender/InitiativeTender.jsp").forward(request, response);
		} catch (Exception e) {
 			e.printStackTrace();
		}
	}

}
