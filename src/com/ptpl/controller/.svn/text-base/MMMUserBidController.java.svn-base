package com.ptpl.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Red_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.model.Award;
import com.ptpl.model.AwardTenderLink;
import com.ptpl.model.GuaranteeFee;
import com.ptpl.model.MediacyFee;
import com.ptpl.model.Plus;
import com.ptpl.model.RiskGuarantyMoney;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.model.UserTender;
import com.ptpl.model.UserTenderPlusLink;
import com.ptpl.model.loanapp;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.AwardTenderLinkServiceI;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.MediacyFeeServiceI;
import com.ptpl.service.PlusServiceI;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.service.UserTenderPlusLinkServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;

/**
 * 用户主动投标
 * @author zhenglm
 */
@Controller
@RequestMapping("/user/tenderTransfer")
public class MMMUserBidController extends BaseController {	
	
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
	
	/** 用户使用券service */
	@Autowired
	UserInterestRateCouponServiceI userInterestRateCouponService;
	
	/** 用户红包service */
	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;
	
	/** 债转设置Service */
	@Autowired
	UserDebtAttornServiceI userDebtAttornService;
	
	/** 投标增益使用关联Service */
	@Autowired
	UserTenderPlusLinkServiceI userTenderPlusLinkService;
	
	/** 奖品设置Service */
	@Autowired
	AwardServiceI awardService;
	
	/** 奖品设置指定标号关联Service */
	@Autowired
	AwardTenderLinkServiceI awardTenderLinkService;
	
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
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, pageSize); // 初始化分页相关信息
		// 筛选出发布中的标的
//		tenderItem.setTstatus((short) 4);
		List<TenderItem> tenderItemList = tenderItemService.selectByCondition(tenderItem);
		PageInfo<Object> pagehelper = initPagehelper(maps, tenderItemList);
		mv.addObject("pagehelper", pagehelper);
		mv.setViewName("user/financialProject/FinancialProject_List");
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
		if (userBaseAccountInfo != null) {
			if(id != null){
				TenderItem item = tenderItemService.findTenderItemById(id);
				if(item != null){
					if(item.getFinishtamount() == null){
						item.setFinishtamount((double) 0); // 完成金额为空则先设置为0，避免后面计算报算数异常
					}
					double surplusAmout = Arith.sub(item.getTamount(), item.getFinishtamount()); // 计算剩余可投资金额
					UserAccount userAccount = userAccountService.getUserAccountByBaseId(userBaseAccountInfo.getId()); // 根据baseid查询用户账户信息
					List<UserInterestRateCoupon> interestRateCoupons = null;
					List<UserRedEnvelope> likeVouchers = null;
					List<UserRedEnvelope> fakeVouchers = null;
					if(item.getIsaplus() != null && item.getIsaplus() == 1) { // 判断是否允许使用增益 
						System.out.println("此项目可以使用增益！！！！！！");
						Plus plus = plusService.findPlusByTid(item.getId()); // 根据标的ID查询标的增益设置表
						if(plus != null){
							if(plus.getIsaint() != null && plus.getIsaint() == 1) { // 判断是否允许使用加息券
								System.out.println("此项目可以使用加息券！！！！！！");
								UserInterestRateCoupon coupon = new UserInterestRateCoupon();
								coupon.setBaseid(userBaseAccountInfo.getId()); // 用户baseid
								coupon.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
								coupon.setUirctype((short) 6); // 奖品类型——1.现金券，2.类现金券，3.假现金券，4.交易积分，5.系统积分，6.加息券
								interestRateCoupons = userInterestRateCouponService.findIsUseByBaseid(coupon); // 查询当前用户可使用的加息券
								Iterator<UserInterestRateCoupon> rate = interestRateCoupons.iterator(); // 迭代筛选出不符合条件的加息券
								while(rate.hasNext()){
									UserInterestRateCoupon Interest = rate.next();
									if(Arith.sub(Interest.getIcrate(), plus.getAoneqrofit()) > 0){
										rate.remove(); // 超过允许单张加息券收益的加息券去掉
									}
								}
								
							}
							if(plus.getIsavoucher() != null && plus.getIsavoucher() == 1){ // 判断是否允许使用类现金
								UserRedEnvelope red = new UserRedEnvelope();
								red.setBaseid(userBaseAccountInfo.getId()); // 用户baseid
								red.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
								red.setRetype(Red_Constant.RETYPE_LEIXIANJIN); // 红包类型——1.现金，2.类现金，3.假现金
								likeVouchers = userRedEnvelopeService.selectByBaseid(red); // 查询当前用户可使用的类现金
								Iterator<UserRedEnvelope> like = likeVouchers.iterator(); // 迭代筛选出不符合条件的类现金
								while(like.hasNext()){
									if(Arith.sub(like.next().getRedenvelope(), plus.getAonevamount()) > 0){ // 超过允许单张类现金额度的类现金去掉
										like.remove();
									}
								}
							}
							if(plus.getIsalikevoucher() != null && plus.getIsalikevoucher() == 1){ // 判断是否允许使用假现金
								UserRedEnvelope red = new UserRedEnvelope();
								red.setBaseid(userBaseAccountInfo.getId()); // 用户baseid
								red.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
								red.setRetype(Red_Constant.RETYPE_JIAXIANJIN); // 红包类型——1.现金，2.类现金，3.假现金
								fakeVouchers = userRedEnvelopeService.selectByBaseid(red); // 查询当前用户可使用的假现金
								Iterator<UserRedEnvelope> fake = fakeVouchers.iterator(); // 迭代筛选出不符合条件的假现金
								while(fake.hasNext()){
									if(Arith.sub(fake.next().getRedenvelope(), plus.getAonelvamount()) > 0){ // 超过允许单张假现金额度的假现金去掉
										fake.remove();
									}
								}
							}
						}
					}
					mv.addObject("avlbalance", userAccount.getAvlbalance()); // 用户账户可用余额
					mv.addObject("interestRateCoupons", interestRateCoupons); // 可用加息券
					mv.addObject("likeVouchers", likeVouchers); // 可用类现金
					mv.addObject("fakeVouchers", fakeVouchers); // 可用假现金
					mv.addObject("surplusAmout", surplusAmout); // 项目可投资余额
					mv.addObject("iscancheattender", userBaseAccountInfo.getIscancheattender());  // 当前用户是否可以假投标
					mv.addObject("item", item); // 标的信息
					mv.setViewName("user/financialProject/FinancialProject");
				}
			}
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
	 * TODO	1.假投标（只修改完成投标金额与新增投标记录）
	 * @param id（标的设置表ID）
	 * @param amount（投标金额）
	 * @param tpass（约标码）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tenderCheck")
	public synchronized String tenderCheck(String id, String amount, String tpass, String uircid, String ureid) throws Exception{
		System.out.println("进入投标校验！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		Map<String, String> map = new HashMap<String, String>();
		String info = "success";
		// 从session中获取当前登录用户基本信息
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		if(userBaseAccountInfo == null)
			return "redirect:user/login.action";
		if(amount == null || amount.trim().equals("")){  // 第一步：判断投标金额是否为空
			info = "投标金额不能为空！";
		}else{
			BigDecimal baseId = userBaseAccountInfo.getId(); // 投资人baseid
			if(id == null || id.trim().equals("")){ // 第二步：判断前台传来的id是否为空
				info = "不存在的标！";
			}else{
				TenderItem item = tenderItemService.findTenderItemById(new BigDecimal(id));	// 根据id查询标的设置详情
				if (item == null) { // 第三步：判断标的是否存在
					info = "不存在的标！";
				}else{
					String c = isClient(request);
					if(!item.getCrestrict().contains(c)){ // 判断当前客户端是否限制投标
						info = "当前客户端限制投此标的";
					}else{
						if(new Date().getTime() >= item.getTendtime().getTime()){ // 第四步：判断标的是否下架
							info = "此项目已经下架";
						}else{
							if(item.getFinishtamount() == null)
								item.setFinishtamount(0.00);
							if(Arith.sub(item.getFinishtamount(), item.getTamount()) >= 0){ // 第五步：判断是否满标
								info = "项目已满标";
							}else{
								loanapp loan = loanappService.selectByPrimaryKey(item.getLoanappid()); // 根据借款申请ID查询借款人申请信息
								if(loan != null && baseId.equals(loan.getBaseid())){ // 第六步：判断投资人是否是借款人
									info = "投资人不能为借款人";
								}else{
									BigDecimal snlid = item.getSnlid(); // 定向名单列表Id
									if(!IsRightOrExcept(snlid, userBaseAccountInfo)){ // 第七步：判断投资人是否在定向包含名单
										info = "您在定向排除名单中";
									}else{
										Double famount = Double.valueOf(amount); // 填入的投资金额转换数据类型
										UserAccount ua = userAccountService.getUserAccountByBaseId(baseId);
										Double avlbalance = ua.getAvlbalance(); // 投资人账户可用余额
										if(Arith.sub(avlbalance, famount) < 0){ // 第七步：判断投资金额是否大于账户余额
											info = "投资金额大于账户余额";
										}else{
											Map<String, Object> condition = new HashMap<>();
											condition.put("tenderid", item.getId()); // 标的ID
											condition.put("outaccountid", baseId); // 投资人ID
											condition.put("utproperty", TenderRecord_Constant.UTPROPERTY_ORIGINAL); // 投标属性--原始投标
											condition.put("tstatus", TenderRecord_Constant.TSTATUS_PENDINGAUDIT); // 投标状态--待审核（投标成功-未放款）
											List<UserTender> total = userTenderService.findUserTenderRecord(condition); // 查询投资人当前标的投标成功的原始投资记录
											double finishAmount = 0; // 投资人当前标的已完成的投资总金额（包含当前的投资金额）
											for(UserTender userTender : total){
												finishAmount = Arith.add(finishAmount, userTender.getAmount()); // 查询投资人当前标的已投总金额
											}
											System.out.println("投资人已完成投标金额"+finishAmount);
											System.out.println("投资人已完成的投标次数========================="+total.size());
											if(total.size() >= item.getOnettimes()){ // 第八步：判断用户已投标次数是否超过单人投标限制
												info = "单人投标限制"+item.getOnettimes()+"次";
											}else{
												double totalAmount = Arith.add(famount, finishAmount); // 当前投标金额+已投金额
												if(Arith.sub(totalAmount, item.getTotalmoneyrestrict()) > 0){ // 第九步：判断是否超过累投金额
													info = "超过累投金额限制！";
												}else{
													Double minoncetamount = item.getMinoncetamount(); // 单笔允许最低投资金额
													Double maxoncetamount = item.getMaxoncetamount(); // 单笔允许最高投资金额
													if(Arith.sub(minoncetamount, famount) > 0){ // 第十步：判断是否低于单笔允许最低投资金额
														info = "不能低于单笔允许最低投资金额！";
													}else{
														if(Arith.sub(maxoncetamount, famount) < 0){ // 第十一步：判断是否高于单笔允许最高投资金额
															info = "不能高于单笔允许最高投资金额！";
														}else{
															double remaining = Arith.add(famount, finishAmount); // 当前投标金额+已完成投标金额
															System.out.println("当前投标金额+已完成投标金额=========="+remaining);
															if(Arith.sub(item.getTamount(), remaining) < 0){ // 第十二步：判断剩余可投标金额
																System.out.println("剩余可投标金额=========="+Arith.sub(item.getTamount(), remaining));
																info = "当前投标金额大于剩余可投标金额";
															}else{
																if(item.getIsappointtender() == 0){ // 第十三步：判断是否为约标
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
																// 校验增益是否可用于投标
																String message = checkPlus(item, uircid, ureid, amount);
																if(!message.equalsIgnoreCase("")){
																	info = message;
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
	 * 校验增益是否能用于投标
	 * @Title: checkPlus
	 * @Description: TODO(1.判断是否允许使用加息券（单次张数、总计张数、单张收益）,
	 * 2.判断是否允许使用类现金（单次张数、总计张数、单张金额）,
	 * 3.判断是否允许使用假现金（单次张数、总计张数、单张金额）,
	 * 4.判断奖品设置表中投标使用限制)
	 * @param item
	 * @param uircno
	 * @param ureno
	 * @return String
	 */
	private String checkPlus(TenderItem item, String uircid, String ureid, String amount) {
		String message = "";
		if(item.getIsaplus() == 1){ // 判断是否允许使用增益
			Plus plus = plusService.findPlusByTid(item.getId()); // 根据标的ID查询标的增益设置表
			List<UserTenderPlusLink> linkList = userTenderPlusLinkService.findUserTenderPlusLinkByUtId(item.getId()); //
			if(plus!=null){
				if(StringUtil.isNotEmpty(uircid)){
					if(plus.getIsaint()!=null && plus.getIsaint() == 1){ // 判断是否允许使用加息券
						String[] uircids = uircid.split(",");
						UserInterestRateCoupon userInterestRateCoupon = null;
						for(String id : uircids){
							userInterestRateCoupon = userInterestRateCouponService.findUserInterestRateCouponById(new BigDecimal(id));
							if(userInterestRateCoupon==null){
								return message = "加息券不存在";
							}
							Integer aOnceInt = plus.getAonceint(); // 单次允许使用加息券张数
							if(uircids.length>aOnceInt){
								return message = "超过单次允许使用加息券张数";
							}else{
								Double aOneQrofit = plus.getAoneqrofit(); // 允许单张加息券收益
								if(userInterestRateCoupon.getIcrate()>aOneQrofit){
									return message = "超过单张加息券收益";
								}
							}
							String info = awardLimit(userInterestRateCoupon.getUircno(), item, Double.valueOf(amount));
							if(!info.equalsIgnoreCase("success")){
								return info;
							}
						}
						Integer aTotalInt = plus.getAtotalint(); // 允许使用加息券总张数
						int icNum = 0;
						for(UserTenderPlusLink link : linkList){
							if(link.getIcid()!=null){
								icNum += 1;
							}
						}
						if(icNum > aTotalInt){
							return message = "超过允许使用加息券总张数";
						}
						message = "success";
					}
				}
				if(StringUtil.isNotEmpty(ureid)){
					String[] ureids = ureid.split(",");
					for(String id : ureids){
						UserRedEnvelope userRedEnvelope = userRedEnvelopeService.findUserRedEnvelopeById(new BigDecimal(id));
						if(userRedEnvelope==null){
							return message = "红包不存在";
						}else{
							if(userRedEnvelope.getRetype()==2){
								if(plus.getIsavoucher()!=null && plus.getIsavoucher() == 1){ // 判断是否允许使用类现金
									Integer aOnceVoucher = plus.getAoncevoucher(); // 单次允许类现金卷张数
									if(ureids.length > aOnceVoucher){
										return message = "超过单次允许使用类现金卷张数";
									}else{
										Double aOneVAmount = plus.getAonevamount(); // 允许单张类现金额度
										if(userRedEnvelope.getRedenvelope()>aOneVAmount){
											return message = "超过单张类现金额度";
										}
									}
									Integer aTotalVoucher = plus.getAtotalvoucher(); // 总计允许类现金卷张数
									int voucherNum = 0;
									for(UserTenderPlusLink link : linkList){
										if(link.getIcid()!=null){
											voucherNum += 1;
										}
									}
									if(voucherNum > aTotalVoucher){
										return message = "超过允许使用类现金总张数";
									}
									message = "success";
								}
							}else if(userRedEnvelope.getRetype()==3){
								if(plus.getIsalikevoucher()!=null && plus.getIsalikevoucher() == 1){ // 判断是否允许使用假现金
									Integer aOnceLikeVoucher = plus.getAoncelikevoucher(); // 单次允许假现金卷张数
									if(ureids.length > aOnceLikeVoucher){
										return message = "超过单次允许使用假现金卷张数";
									}else{
										Double aOneLVAmount = plus.getAonelvamount(); // 允许单张假现金额度
										if(userRedEnvelope.getRedenvelope()>aOneLVAmount){
											return message = "超过单张假现金额度";
										}
									}
									Integer aTotalLikeVoucher = plus.getAtotallikevoucher(); // 总计允许假现金卷张数
									int likeVoucherNum = 0;
									for(UserTenderPlusLink link : linkList){
										if(link.getIcid()!=null){
											likeVoucherNum += 1;
										}
									}
									if(likeVoucherNum > aTotalLikeVoucher){
										return message = "超过允许使用假现金总张数";
									}
									message = "success";
								}
							}
						}
						String info = awardLimit(userRedEnvelope.getUreno(), item, Double.valueOf(amount));
						if(!info.equalsIgnoreCase("success")){
							return info;
						}
					}
				}
			}
		}else{
			message = "此项目不允许使用增益";
		}
		return message;
	}
	
	/**
	 * 奖品投标使用限制
	 * @Title: awardLimit
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param awardNo
	 * @param item
	 * @param amount
	 * @return String
	 */
	private String awardLimit(String awardNo, TenderItem item, double amount){
		Award award = awardService.selectByAwardNo(awardNo);
		if(award.getTattribute() != null){
			Short tpro = item.getTpro();
			char[] tAttribute = award.getTattribute().toCharArray();
			if(tAttribute[tpro-1] != '1'){
				return "投标属性限制不满足，不能使用增益"+awardNo;
			}
			double loantime = 0;
			if(item.getDayormonth().equalsIgnoreCase("年")){
				loantime = Arith.mul(item.getLoantime(), 365);
			}else if(item.getDayormonth().equalsIgnoreCase("月")){
				loantime = Arith.mul(item.getLoantime(), 30);
			}
			if(loantime < award.getTdayLimitl()){
				return "借款周期小于投标期限限制最低值，不能使用增益"+awardNo;
			}
			if(loantime > award.getTdayrestrict()){
				return "借款周期大于投标期限限制最高值，不能使用增益"+awardNo;
			}
			if(Arith.sub(item.getTinterest(), award.getTmlrrestrict()) < 0){
				return "投标收益低于限制最低值，不能使用增益"+awardNo;
			}
			if(Arith.sub(item.getTinterest(), award.getTmhrrestrict()) > 0){
				return "投标收益高于限制最高值，不能使用增益"+awardNo;
			}
			if(item.getRepaymentpro() == award.getTrtype()){
				return "投标还款方式不满足，不能使用增益"+awardNo;
			}
		}else{
			List<AwardTenderLink> awardTenderLink = awardTenderLinkService.getAwardTenderLinksByawardId(award.getId());
			Boolean linkFlag = false;
			for(AwardTenderLink link : awardTenderLink){
				if(link.getTenderId().equals(item.getId())){
					linkFlag = true;
				}
			}
			if(!linkFlag){
				return "指定标号不满足，不能使用增益"+awardNo;
			}
		}
		if(Arith.sub(amount, award.getTminmoney()) < 0){
			return "投标金额低于限制最低值，不能使用增益"+awardNo;
		}
		if(Arith.sub(amount, award.getTmaxmoney()) > 0){
			return "投标金额高于限制最高值，不能使用增益"+awardNo;
		}
		return "success";
	}

	/**
	 * @Title: IsRightOrExcept
	 * @Description: TODO(判断用户是否在定向包含名单)
	 * @param snlid
	 * @param userBaseAccountInfo
	 * @return void
	 */
	private boolean IsRightOrExcept(BigDecimal snlid, UserBaseAccountInfo userBaseAccountInfo) {
		Boolean bool = false;
		Set<UserBaseAccountInfo> set = userDebtAttornService.getSpecialNameList(snlid); // 根据定向名单列表Id查询业务对象名单设置包含的所有用户
		Iterator<UserBaseAccountInfo> it = set.iterator();
		while(it.hasNext()){
			BigDecimal basid = it.next().getId();
			System.out.println("baseid=============="+basid);
			System.out.println("baseid--------------"+userBaseAccountInfo.getId());
			if(basid.equals(userBaseAccountInfo.getId())){
				System.out.println(bool);
				bool = true;
				break;
			}
		}
		return bool;
	}
	
	/**
	 * @Title: isClient
	 * @Description: TODO(判断投标来源客户端是否允许投标)
	 * @param request
	 * @return String
	 */
    public String isClient(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if(userAgent == null || userAgent.indexOf("windows nt") == -1 ? false : true){ // 判断当前客户端是否为PC
        	return "d";
        }else if(userAgent == null || userAgent.indexOf("android") == -1 ? false : true){ // 判断当前客户端是否为android
        	return "a";
        }else if(userAgent == null || userAgent.indexOf("iphone") == -1 ? false : true){ // 判断当前客户端是否为iPhone
        	return "p";
        }else if(userAgent == null || userAgent.indexOf("wap") == -1 ? false : true){ // 判断当前客户端是否为wap
        	return "o";
        }else if(userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true){ // 判断当前客户端是否为微信
        	return "w";
        }
        return "";
    }
    
	/**
	 * @Title: tenderClient
	 * @Description: TODO(获取投标来源客户端)
	 * @param request
	 * @return String
	 */
    public String getTenderClient(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if(userAgent == null || userAgent.indexOf("windows nt") == -1 ? false : true){ // 判断当前客户端是否为PC
        	return "PC";
        }else if(userAgent == null || userAgent.indexOf("android") == -1 ? false : true){ // 判断当前客户端是否为android
        	return "安卓";
        }else if(userAgent == null || userAgent.indexOf("iphone") == -1 ? false : true){ // 判断当前客户端是否为iPhone
        	return "苹果";
        }else if(userAgent == null || userAgent.indexOf("wap") == -1 ? false : true){ // 判断当前客户端是否为wap
        	return "wap";
        }else if(userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true){ // 判断当前客户端是否为微信
        	return "微信";
        }
        return null;
    }

	/**
	 * 主动投标请求参数 1.保存投标记录
	 * @param id（标的设置表ID）
	 * @param amount（投标金额）
	 * @throws Exception
	 */
	@RequestMapping("/initiativeTender")
	private synchronized void initiativeTender(BigDecimal id, String amount, String uircid, String ureid) throws Exception {
		if(!flag)
			return;
		flag = false;
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		if(userFsAccountInfo == null)
			return;
		System.out.println("乾多多主动投标请求参数设置！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		Date tenderTime = new Date();
		TenderItem item = tenderItemService.findTenderItemById(id); // 获得标的设置详情
		loanapp loan = loanappService.selectByPrimaryKey(item.getLoanappid()); // 根据借款申请ID查询借款人申请信息
		if (loan == null)
			return;
		UserFsAccountInfo uf = userFsAccountInfoService.findUserFsAccountInfoByBaseId(loan.getBaseid()); // 根据借款人basid查询托管信息
		
		String OrderNo = StringUtil.getNo(); // 网贷平台订单号（投标订单号）
		Double Amount = Double.valueOf(amount); // 金额
//		Integer NeedAudit = 1; // 通过是否需要审核-空.需要审核，1.自动通过
		
		UserTender ut = new UserTender();
		ut.setTenderid(item.getId()); // 标的号id
		ut.setUtproperty(TenderRecord_Constant.UTPROPERTY_ORIGINAL); // 投标属性-1.原始投标，2.债转投标，3.假投标
		ut.setOutaccountid(userFsAccountInfo.getBaseid()); // 投资方ID
		UserTender userTender = userTenderService.selectInitialOrderByBaseidAndTid(ut);
		if(userTender!=null){
			userTender.setOrderno(OrderNo); // 投标订单号
			userTender.setAmount(Amount); // 投标金额
			userTender.setRealamount(ut.getAmount()); // 本金金额
			userTender.setVoucheramount((double) 0); // 类现金金额
			userTender.setLikevoucheramount((double) 0); // 假现金金额
			userTender.setTbegintime(tenderTime); // 投标开始时间
			// 判断是否设置居间费
			if (item.getIsamediacy() != null && item.getIsamediacy() == 1) {
				calculationMediacyFee(item, userTender);
			} else {
				userTender.setMediacyfee((double) 0);
			}
			// 判断是否设置担保
			if (item.getIsaguarantee() != null && item.getIsaguarantee() == 1) {
				calculationGuaranteeFee(item, userTender);
			} else {
				userTender.setGuaranteefee((double) 0);
			}
			// 判断是否设置风险保证金
			if (item.getIsariskgm() != null && item.getIsariskgm() == 1) {
				calculationRiskGuarantyMoney(item, userTender, loan.getBaseid());
			} else {
				userTender.setRiskguarantyfee((double) 0);
			}
			userTender.setFee(Arith.add(Arith.add(userTender.getMediacyfee(), userTender.getGuaranteefee()), userTender.getRiskguarantyfee())); // 手续费（居间服务费+担保费+风险保证金）
			userTenderService.updateByPrimaryKeySelective(userTender);
			usePlus(item, uircid, ureid, userTender, amount);
		}else{
			ut.setOrderno(OrderNo); // 投标订单号
			ut.setInaccountid(loan.getBaseid()); // 借款方用户ID
			ut.setAmount(Amount); // 投标金额
			ut.setRealamount(ut.getAmount()); // 本金金额
			ut.setVoucheramount((double) 0); // 类现金金额
			ut.setLikevoucheramount((double) 0); // 假现金金额
			ut.setIsda(TenderRecord_Constant.ISDA_NO); // 是否债转-未债转
			ut.setTendertype(TenderRecord_Constant.TENDERTYPE_MANUAL); // 投标方式-手动投标
			ut.setIsrepayend(TenderRecord_Constant.ISREPAYEND_NO); // 还款完成-否
			ut.setTbegintime(tenderTime); // 投标开始时间
			// 判断是否设置居间费
			if (item.getIsamediacy() != null && item.getIsamediacy() == 1) {
				calculationMediacyFee(item, ut);
			} else {
				ut.setMediacyfee((double) 0);
			}
			// 判断是否设置担保
			if (item.getIsaguarantee() != null && item.getIsaguarantee() == 1) {
				calculationGuaranteeFee(item, ut);
			} else {
				ut.setGuaranteefee((double) 0);
			}
			// 判断是否设置风险保证金
			if (item.getIsariskgm() != null && item.getIsariskgm() == 1) {
				calculationRiskGuarantyMoney(item, ut, loan.getBaseid());
			} else {
				ut.setRiskguarantyfee((double) 0);
			}
			ut.setFee(Arith.add(Arith.add(ut.getMediacyfee(), ut.getGuaranteefee()), ut.getRiskguarantyfee())); // 手续费（居间服务费+担保费+风险保证金）
			// 判断是否允许债转
			if (item.getIsadebtattorn() == null || item.getIsadebtattorn() == 0) { // 未设置标的债转或标的不允许债权转让
				ut.setIsallowda(TenderRecord_Constant.ISALLOWDA_NO); // 不能债转
			} else if (item.getIsadebtattorn() == 1) { // 允许债权转让
				ut.setIsallowda(TenderRecord_Constant.ISALLOWDA_YES); // 能债转
			}
			ut.setTransfertype(TenderRecord_Constant.TRANSFERTYPE_ACCOUNT); // 转账类型-账户转账
			ut.setOriginclient(getTenderClient(request)); // 投标设备来源
			ut.setTproperty(item.getTpro()); // 标的属性
			ut.setAppointtenderpass(item.getTpass()); // 约标码
			ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_FREEZE); // 是否冻结-冻结
			ut.setTstatus(TenderRecord_Constant.TSTATUS_INITIAL); // 投标的状态-初始
			ut.setIsblending(TenderRecord_Constant.ISBLENDING_NO); // 是否系统勾兑
			ut.setIsmanblending(TenderRecord_Constant.ISMANBLENDING_NO); // 是否人工勾兑
			ut.setPaycompany("乾多多"); // 投标通道公司-汇付天下
			ut.setIsaudit(TenderRecord_Constant.ISAUDIT_NO); // 是否审核
			ut.setRemark("乾多多投标测试用"); // 备注
			System.out.println("居间服务费=======================" + ut.getMediacyfee());
			System.out.println("居间服务费收款人=======================" + ut.getMrecman());
			System.out.println("居间费收款人商户号=======================" + ut.getMrecmancustid());
			System.out.println("担保费=======================" + ut.getGuaranteefee());
			System.out.println("担保服务费收款人=======================" + ut.getGfrecman());
			System.out.println("担保费收款人商户号=======================" + ut.getGfrecmancustid());
			System.out.println("风险保证金=======================" + ut.getRiskguarantyfee());
			System.out.println("风险保证金收款人=======================" + ut.getRgmrecman());
			System.out.println("风险保证金收款人商户号=======================" + ut.getRgmrecmancustid());
			int count = 0;
			count = userTenderService.insertSelective(ut); // 保存部分投标记录
			if (count > 0) {
				System.out.println("保存投标记录成功！！！！！！！！！！！！！！！！！！！！！！！！");
			}
			usePlus(item, uircid, ureid, ut, amount);
		}
		
		String LoanOutMoneymoremore = userFsAccountInfo.getMoneymoremoreid(); // 付款人乾多多标识
		String LoanInMoneymoremore = uf.getMoneymoremoreid(); // 收款人乾多多标识
		String BatchNo = item.getTno(); // 网贷平台标号
		Double FullAmount = item.getTamount(); // 满标标额（所有标号相同的转账记录中，以第一笔转账成功的记录中的标额为准，之后的转账可以不填标额）
		String TransferName = "投标"; // 用途（投标）
		String Remark = "乾多多投标转账测试"; // 备注
		UserTender userTenderRecord = userTenderService.findUserTenderByOrderno(OrderNo);
		String mediacyfee = "{\\\"LoanInMoneymoremore\\\":\\\""+"p2089"+"\\\",\\\"Amount\\\":\\\""+df1.format(userTenderRecord.getMediacyfee())+"\\\",\\\"TransferName\\\":\\\""+"居间费"+"\\\",\\\"Remark\\\":\\\""+"平台收取居间服务费"+"\\\"}";
		String guaranteefee = "{\\\"LoanInMoneymoremore\\\":\\\""+"p2089"+"\\\",\\\"Amount\\\":\\\""+df1.format(userTenderRecord.getGuaranteefee())+"\\\",\\\"TransferName\\\":\\\""+"担保费"+"\\\",\\\"Remark\\\":\\\""+"平台收取担保服务费"+"\\\"}";
		String riskguarantyfee = "{\\\"LoanInMoneymoremore\\\":\\\""+"p2089"+"\\\",\\\"Amount\\\":\\\""+df1.format(userTenderRecord.getRiskguarantyfee())+"\\\",\\\"TransferName\\\":\\\""+"风险保证金"+"\\\",\\\"Remark\\\":\\\""+"平台收取风险保证金"+"\\\"}";
		String SecondaryJsonList = ""; // 二次分配列表
		String LoanJsonList = ""; // 转账列表
		if(userTenderRecord.getFee() != 0){
			if(userTenderRecord.getMediacyfee() == 0 && userTenderRecord.getGuaranteefee() == 0){
				SecondaryJsonList = "["+riskguarantyfee+"]";
			}else if(userTenderRecord.getMediacyfee() == 0 && userTenderRecord.getRiskguarantyfee() == 0){
				SecondaryJsonList = "["+guaranteefee+"]";
			}else if(userTenderRecord.getGuaranteefee() == 0 && userTenderRecord.getRiskguarantyfee() == 0){
				SecondaryJsonList = "["+mediacyfee+"]";
			}else if(userTenderRecord.getMediacyfee() == 0){
				SecondaryJsonList = "["+guaranteefee+","+riskguarantyfee+"]";
			}else if(userTenderRecord.getGuaranteefee() == 0){
				SecondaryJsonList = "["+mediacyfee+","+riskguarantyfee+"]";
			}else if(userTenderRecord.getRiskguarantyfee() == 0){
				SecondaryJsonList = "["+mediacyfee+","+guaranteefee+"]";
			}else{
				SecondaryJsonList = "["+mediacyfee+","+guaranteefee+","+riskguarantyfee+"]";
			}
			LoanJsonList = "[{\"LoanOutMoneymoremore\":\""+LoanOutMoneymoremore+"\",\"LoanInMoneymoremore\":\""+LoanInMoneymoremore+"\",\"OrderNo\":\""+OrderNo+"\",\"BatchNo\":\""+BatchNo+"\",\"Amount\":\""+Amount+"\",\"FullAmount\":\""+FullAmount+"\",\"TransferName\":\""+TransferName+"\",\"Remark\":\""+Remark+"\",\"SecondaryJsonList\":\""+SecondaryJsonList+"\"}]"; // 转账列表
		}else{
			LoanJsonList = "[{\"LoanOutMoneymoremore\":\""+LoanOutMoneymoremore+"\",\"LoanInMoneymoremore\":\""+LoanInMoneymoremore+"\",\"OrderNo\":\""+OrderNo+"\",\"BatchNo\":\""+BatchNo+"\",\"Amount\":\""+Amount+"\",\"FullAmount\":\""+FullAmount+"\",\"TransferName\":\""+TransferName+"\",\"Remark\":\""+Remark+"\"}]"; // 转账列表
		}
		String PlatformMoneymoremore = MMMParams.PlatformMoneymoremore; // 平台乾多多标识
		Integer TransferAction = 1; // 转账类型-1.投标,2.还款,3.其他
		Integer Action = 1; // 操作类型-1.手动转账，2.自动转账
		Integer TransferType = 2; // 转账方式-1.桥连，2.直连
		Integer ArrivalTime = 1; // 到账时间-空.实时转账，1.普通转账，2.次日转账
//		Integer DelayTransfer = 1; // 是否半自动批处理(暂不可用)-空.否,1.是（启用该功能的条件:1.直连，2.付款人唯一，3.公共账户余额不参与转账）
		
		String Remark1 = ""; // 自定义备注
		String Remark2 = ""; // 自定义备注
		String Remark3 = ""; // 自定义备注
		String ReturnURL = StringUtil.getBasePath(request)+"/moneymoremore/UserTender/userTenderReturnUrl.action"; // 页面返回网址
		String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/UserTender/userTenderCallBack.action"; // 后台通知网址
		
		System.out.println("乾多多主动投标请求");
		System.out.println("开始拼接乾多多主动投标接口请求参数！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		/*LoanJsonList + PlatformMoneymoremore + TransferAction + 
		  Action + TransferType + ArrivalTime + NeedAudit + 
		  DelayTransfer + RandomTimeStamp + Remark1 + 
		  Remark2 + Remark3 + ReturnURL + NotifyURL*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(LoanJsonList))
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(TransferAction)
				.append(Action)
				.append(TransferType)
				.append(ArrivalTime)
//				.append(NeedAudit)
//				.append(DelayTransfer)
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ReturnURL))
				.append(StringUtils.trimToEmpty(NotifyURL));
		String plainStr = buffer.toString();
      System.out.println("投标转账提交参数原文：=============="+plainStr);
		//私钥签名
		String privateResult = "";
		
		RsaHelper rsa = RsaHelper.getInstance();
		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
		
		request.setAttribute("LoanJsonList", URLEncoder.encode(LoanJsonList, "UTF-8"));
		request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
		request.setAttribute("TransferAction", TransferAction);
		request.setAttribute("Action", Action);
		request.setAttribute("TransferType", TransferType);
		request.setAttribute("ArrivalTime", ArrivalTime);
//		request.setAttribute("NeedAudit", NeedAudit);
//		request.setAttribute("DelayTransfer", DelayTransfer);
		request.setAttribute("Remark1", Remark1);
		request.setAttribute("Remark2", Remark2);
		request.setAttribute("Remark3", Remark3);
		request.setAttribute("ReturnURL", ReturnURL);
		request.setAttribute("NotifyURL", NotifyURL);
		request.setAttribute("SignInfo", privateResult);
		try {
			request.getRequestDispatcher("/WEB-INF/MMMPages/UserTender/UserTender.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 投标时使用增益
	 * @Title: usePlus
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param item
	 * @param uircid
	 * @param ureid
	 * @param ut
	 * @param amount
	 * @param huifuparam
	 * @return void
	 */
	private void usePlus(TenderItem item, String uircid, String ureid, UserTender ut, String amount){
		String info = checkPlus(item, uircid, ureid, amount);
		if(info.equalsIgnoreCase("success")){
			UserTender tenderRecord = userTenderService.findUserTenderByOrderno(ut.getOrderno());
			if(StringUtil.isNotEmpty(uircid)){
				String[] uircids = uircid.split(",");
				for(String cId : uircids){
					UserInterestRateCoupon userInterestRateCoupon = userInterestRateCouponService.findUserInterestRateCouponById(new BigDecimal(cId));
					userInterestRateCoupon.setIsuse(ActAward_Constant.AWARD_ALFROZEN);
					userInterestRateCouponService.updataCouponById(userInterestRateCoupon); // 冻结加息券
					UserTenderPlusLink plusLink = new UserTenderPlusLink();
					plusLink.setUtid(tenderRecord.getId());
					plusLink.setIcid(new BigDecimal(cId));
					userTenderPlusLinkService.insertSelective(plusLink);
				}
			}
			if(StringUtil.isNotEmpty(ureid)){
				String[] ureids = ureid.split(",");
				Double VocherAmt = (double) 0;
				Double fakeAmount = (double) 0;
				for(String rid : ureids){
					UserRedEnvelope userRedEnvelope = userRedEnvelopeService.findUserRedEnvelopeById(new BigDecimal(rid));
					userRedEnvelope.setIsuse(ActAward_Constant.AWARD_ALFROZEN);
					userRedEnvelopeService.updateRedEnvById(userRedEnvelope); // 冻结红包
					if(userRedEnvelope.getRetype() == 2){
						VocherAmt = Arith.add(VocherAmt, userRedEnvelope.getRedenvelope());
					}
					if(userRedEnvelope.getRetype() == 3){
						fakeAmount = Arith.add(fakeAmount, userRedEnvelope.getRedenvelope());
					}
					UserTenderPlusLink plusLink = new UserTenderPlusLink();
					plusLink.setUtid(tenderRecord.getId());
					plusLink.setReid(new BigDecimal(rid));
					userTenderPlusLinkService.insertSelective(plusLink);
				}
				tenderRecord.setVoucheramount(VocherAmt);  // 类现金
				tenderRecord.setLikevoucheramount(fakeAmount); // 假现金
				double realamount = Arith.sub(Arith.sub(tenderRecord.getAmount(), tenderRecord.getVoucheramount()), tenderRecord.getLikevoucheramount());
				tenderRecord.setRealamount(realamount);
			}
			userTenderService.updateByPrimaryKeySelective(tenderRecord);
		}
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
		for (MediacyFee mediacyFee : mediacyFeeList) {
			// 判断居间费收费类型-1.结标时收取2.投标时收取
			if (mediacyFee.getChargetype() == 1) { // 结标时收取居间费
				if (tamount > mediacyFee.getMinnmmoney() && tamount <= mediacyFee.getMaxnmmoney()) { // 判断结标金额所在段
					if (mediacyFee.getMfquota() != null) { // 判断是否按定额收取
						System.out.println("居间服务费定额" + mediacyFee.getMfquota());
						fee = mediacyFee.getMfquota();
						mfId = mediacyFee.getId();
					} else if (mediacyFee.getMfpercent() != null) { // 判断是否是按百分比收取
						System.out.println("居间服务费百分比" + mediacyFee.getMfpercent());
						if (Arith.mul(tamount, mediacyFee.getMfpercent()) < mediacyFee.getMinmffee()) {
							fee = mediacyFee.getMinmffee(); // 若小于该段最低居间费收费金额则居间费为该段最低居间费收费金额
							mfId = mediacyFee.getId();
						} else if (Arith.mul(tamount, mediacyFee.getMfpercent()) > mediacyFee.getMaxmffee()) {
							fee = mediacyFee.getMaxmffee(); // 若大于该段最低居间费收费金额则居间费为该段最高居间费收费金额
							mfId = mediacyFee.getId();
						} else {
							fee = Arith.mul(tamount, mediacyFee.getMfpercent());
							mfId = mediacyFee.getId();
						}
					}
				}
			} else if (mediacyFee.getChargetype() == 2) { // 投标时收取居间费
				System.out.println("居间费费率" + mediacyFee.getMfrate());
				if (Arith.mul(tamount, mediacyFee.getMfrate()) < mediacyFee.getMinmfamount()) {
					fee = mediacyFee.getMinmfamount(); // 若小于居间费最低收费则居间费为居间费最低收费
					mfId = mediacyFee.getId();
				} else if (Arith.mul(tamount, mediacyFee.getMfrate()) > mediacyFee.getMaxmfamount()) {
					fee = mediacyFee.getMaxmfamount(); // 若大于居间费最高收费则居间费为居间费最高收费
					mfId = mediacyFee.getId();
				} else {
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
		for (GuaranteeFee guaranteeFee : guaranteeFeeList) {
			if (guaranteeFee.getChargetype() == 1) { // 结标时收取担保费
				if (tamount > guaranteeFee.getMinnmmoney() && tamount <= guaranteeFee.getMaxnmmoney()) { // 判断结标金额所在段
					if (guaranteeFee.getGfquota() != null) { // 判断是否按定额收取
						System.out.println("担保费定额" + guaranteeFee.getGfquota());
						fee = guaranteeFee.getGfquota();
						gfId = guaranteeFee.getId();
					} else if (guaranteeFee.getGfpercent() != null) { // 判断是否是按百分比收取
						System.out.println("担保费百分比" + guaranteeFee.getGfpercent());
						if (Arith.mul(tamount, guaranteeFee.getGfpercent()) < guaranteeFee.getMingffee()) {
							fee = guaranteeFee.getMingffee(); // 若小于该段最低担保费收费金额则居间费为该段最低担保费收费金额
							gfId = guaranteeFee.getId();
						} else if (Arith.mul(tamount, guaranteeFee.getGfpercent()) > guaranteeFee.getMaxgffee()) {
							fee = guaranteeFee.getMaxgffee(); // 若大于该段最低担保费收费金额则居间费为该段最高担保费收费金额
							gfId = guaranteeFee.getId();
						} else {
							fee = Arith.mul(tamount, guaranteeFee.getGfpercent());
							gfId = guaranteeFee.getId();
						}
					}
				}
			} else if (guaranteeFee.getChargetype() == 2) { // 投标时收取担保费
				System.out.println("担保费费率" + guaranteeFee.getGfrate());
				if (Arith.mul(tamount, guaranteeFee.getGfrate()) < guaranteeFee.getMingfamount()) {
					fee = guaranteeFee.getMingfamount(); // 若小于担保费最低收费则居间费为担保费最低收费
					gfId = guaranteeFee.getId();
				} else if (Arith.mul(tamount, guaranteeFee.getGfrate()) > guaranteeFee.getMaxgfamount()) {
					fee = guaranteeFee.getMaxgfamount(); // 若大于担保费最高收费则居间费为担保费最高收费
					gfId = guaranteeFee.getId();
				} else {
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
		for (RiskGuarantyMoney riskMoney : riskFeeList) { // 结标时收取风险保证金
			if (riskMoney.getChargetype() == 1) {
				if (tamount > riskMoney.getMinrgmmoney() && tamount <= riskMoney.getMaxrgmmoney()) { // 判断结标金额所在段
					if (riskMoney.getRgmquota() != null) { // 判断是否按定额收取
						System.out.println("风险保证金定额" + riskMoney.getRgmquota());
						fee = riskMoney.getRgmquota();
						rgmID = riskMoney.getId();
					} else if (riskMoney.getRgmpercent() != null) { // 判断是否是按百分比收取
						System.out.println("风险保证金百分比" + riskMoney.getRgmpercent());
						if (Arith.mul(tamount, riskMoney.getRgmpercent()) > riskMoney.getMaxrgmfee()) {
							fee = riskMoney.getMaxrgmfee(); // 若大于该段最高风险保证金额则风险保证金为该段最高风险保证金额
							rgmID = riskMoney.getId();
						} else {
							fee = Arith.mul(tamount, riskMoney.getRgmpercent());
							rgmID = riskMoney.getId();
						}
					}
				}
			} else if (riskMoney.getChargetype() == 2) {
				UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(baseId);// 根据baseid查询用户账户安全信息表
				Short indexStr = userAccountSafeInfo.getUgrade(); // 获取借款用户等级
				System.out.println("用户等级==========================" + indexStr);
				char[] ugrade = riskMoney.getUgrade().toCharArray(); // 标的设置表允许投标等级转换为字符数组
				if (ugrade[indexStr - 1] == '1') { // 判断投资用户是否包含在风险保证金的会员等级中
					if (Arith.mul(tamount, riskMoney.getRgmrate()) > riskMoney.getMaxrgmamount()) {
						fee = riskMoney.getMaxrgmamount(); // 若大于风险保证金最高收费则风险保证金为风险保证金最高收费
						rgmID = riskMoney.getId();
					} else {
						fee = Arith.mul(tamount, riskMoney.getRgmrate());
						rgmID = riskMoney.getId();
					}
				}
			}
		}
		userTender.setRgmid(rgmID); // 风险保证金设置表ID
		userTender.setRiskguarantyfee(fee); // 风险保证金
	}
	/**
	 * 假投标
	 * @Title: fakeTender
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @param amount
	 * @param tpass
	 * @param uircid
	 * @param ureid
	 * @throws Exception
	 * @return void    返回类型
	 */
	@RequestMapping("/fakeTender")
	public void fakeTender(BigDecimal id, String amount, String tpass, String uircid, String ureid) throws Exception {
		UserTender ut = new UserTender();
		TenderItem item = tenderItemService.findTenderItemById(id); // 获得标的设置详情
		ut.setTenderid(item.getId()); // 标的号id
		ut.setOrderno(StringUtil.getNo()); // 投标订单号
		ut.setUtproperty(TenderRecord_Constant.UTPROPERTY_FAKE); // 投标属性-1.原始投标，2.债转投标，3.假投标
		// 从session中获取当前登录用户托管账户基本信息
		UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);	
		ut.setOutaccountid(userFsAccountInfo.getBaseid()); // 投资方ID
		UserTender userTender = userTenderService.selectInitialOrderByBaseidAndTid(ut);
		loanapp loan = loanappService.selectByPrimaryKey(item.getLoanappid()); // 根据借款申请ID查询借款人申请信息
		ut.setInaccountid(loan.getBaseid()); // 借款方用户ID
		ut.setAmount(Double.valueOf(amount)); // 投标金额
		ut.setRealamount(ut.getAmount()); // 本金金额
		ut.setVoucheramount((double) 0); // 类现金金额
		ut.setLikevoucheramount((double) 0); // 假现金金额
		ut.setIsda(TenderRecord_Constant.ISDA_NO); // 是否债转-未债转
		ut.setTendertype(TenderRecord_Constant.TENDERTYPE_MANUAL); // 投标方式-手动投标
		ut.setIsrepayend(TenderRecord_Constant.ISREPAYEND_NO); // 还款完成-否
		ut.setTbegintime(new Date()); // 投标开始时间
		// 判断是否设置居间费
		if (item.getIsamediacy() != null && item.getIsamediacy() == 1) {
			calculationMediacyFee(item, ut);
		} else {
			ut.setMediacyfee((double) 0);
		}
		// 判断是否设置担保
		if (item.getIsaguarantee() != null && item.getIsaguarantee() == 1) {
			calculationGuaranteeFee(item, ut);
		} else {
			ut.setGuaranteefee((double) 0);
		}
		// 判断是否设置风险保证金
		if (item.getIsariskgm() != null && item.getIsariskgm() == 1) {
			calculationRiskGuarantyMoney(item, ut, loan.getBaseid());
		} else {
			ut.setRiskguarantyfee((double) 0);
		}
		ut.setFee(Arith.add(Arith.add(ut.getMediacyfee(), ut.getGuaranteefee()), ut.getRiskguarantyfee())); // 手续费（居间服务费+担保费+风险保证金）
		// 判断是否允许债转
		if (item.getIsadebtattorn() == null || item.getIsadebtattorn() == 0) { // 未设置标的债转或标的不允许债权转让
			ut.setIsallowda(TenderRecord_Constant.ISALLOWDA_NO); // 不能债转
		} else if (item.getIsadebtattorn() == 1) { // 允许债权转让
			ut.setIsallowda(TenderRecord_Constant.ISALLOWDA_YES); // 能债转
		}
		ut.setTransfertype(TenderRecord_Constant.TRANSFERTYPE_ACCOUNT); // 转账类型-账户转账
		ut.setOriginclient(getTenderClient(request)); // 投标设备来源
		ut.setTproperty(item.getTpro()); // 标的属性
		ut.setAppointtenderpass(item.getTpass()); // 约标码
		ut.setIsfreeze(TenderRecord_Constant.ISFREEZE_NOT_FREEZE); // 是否冻结-不冻结
		ut.setTstatus(TenderRecord_Constant.TSTATUS_INITIAL); // 投标的状态-初始
		ut.setIsblending(TenderRecord_Constant.ISBLENDING_NO); // 是否系统勾兑
		ut.setIsmanblending(TenderRecord_Constant.ISMANBLENDING_NO); // 是否人工勾兑
		ut.setPaycompany("汇付天下"); // 投标通道公司-汇付天下
		ut.setIsaudit(TenderRecord_Constant.ISAUDIT_NO); // 是否审核
		ut.setRemark("测试用"); // 备注
		System.out.println("居间服务费=======================" + ut.getMediacyfee());
		System.out.println("居间服务费收款人=======================" + ut.getMrecman());
		System.out.println("居间费收款人商户号=======================" + ut.getMrecmancustid());
		System.out.println("担保费=======================" + ut.getGuaranteefee());
		System.out.println("担保服务费收款人=======================" + ut.getGfrecman());
		System.out.println("担保费收款人商户号=======================" + ut.getGfrecmancustid());
		System.out.println("风险保证金=======================" + ut.getRiskguarantyfee());
		System.out.println("风险保证金收款人=======================" + ut.getRgmrecman());
		System.out.println("风险保证金收款人商户号=======================" + ut.getRgmrecmancustid());
		int count = 0;
		count = userTenderService.insertSelective(ut); // 保存部分投标记录
		if (count > 0) {
			System.out.println("保存投标记录成功！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	
	/**
	 * 发送乾多多转账请求
	 * @Title: MMMTransferRequest
	 * @Description: TODO(发送乾多多转账请求)
	 * @param request
	 * @param response
	 * @param userFsAccountInfo 投资人托管账户信息
	 * @param uf 借款人托管账户信息
	 * @param item 标的设置信息
	 * @param userTenderRecord 投标记录信息
	 * @return void    返回类型
	 */
	public static synchronized void postMMMTransferRequest(HttpServletRequest request, HttpServletResponse response,UserFsAccountInfo userFsAccountInfo, UserFsAccountInfo uf, TenderItem item, UserTender userTenderRecord) {
		String LoanOutMoneymoremore = userFsAccountInfo.getMoneymoremoreid(); // 付款人乾多多标识
		String LoanInMoneymoremore = uf.getMoneymoremoreid(); // 收款人乾多多标识
		
		String OrderNo = userTenderRecord.getOrderno(); // 网贷平台订单号（投标订单号）
		Double Amount = userTenderRecord.getAmount(); // 金额
//		Integer NeedAudit = 1; // 通过是否需要审核-空.需要审核，1.自动通过
		String BatchNo = item.getTno(); // 网贷平台标号
		Double FullAmount = item.getTamount(); // 满标标额（所有标号相同的转账记录中，以第一笔转账成功的记录中的标额为准，之后的转账可以不填标额）
		String TransferName = "投标"; // 用途（投标）
		String Remark = "乾多多投标转账测试"; // 备注
//		UserTender userTenderRecord = userTenderService.findUserTenderByOrderno(OrderNo);
		String mediacyfee = "{\\\"LoanInMoneymoremore\\\":\\\""+"p2089"+"\\\",\\\"Amount\\\":\\\""+df1.format(userTenderRecord.getMediacyfee())+"\\\",\\\"TransferName\\\":\\\""+"居间费"+"\\\",\\\"Remark\\\":\\\""+"平台收取居间服务费"+"\\\"}";
		String guaranteefee = "{\\\"LoanInMoneymoremore\\\":\\\""+"p2089"+"\\\",\\\"Amount\\\":\\\""+df1.format(userTenderRecord.getGuaranteefee())+"\\\",\\\"TransferName\\\":\\\""+"担保费"+"\\\",\\\"Remark\\\":\\\""+"平台收取担保服务费"+"\\\"}";
		String riskguarantyfee = "{\\\"LoanInMoneymoremore\\\":\\\""+"p2089"+"\\\",\\\"Amount\\\":\\\""+df1.format(userTenderRecord.getRiskguarantyfee())+"\\\",\\\"TransferName\\\":\\\""+"风险保证金"+"\\\",\\\"Remark\\\":\\\""+"平台收取风险保证金"+"\\\"}";
		String SecondaryJsonList = ""; // 二次分配列表
		String LoanJsonList = ""; // 转账列表
		if(userTenderRecord.getFee() != 0){
			if(userTenderRecord.getMediacyfee() == 0 && userTenderRecord.getGuaranteefee() == 0){
				SecondaryJsonList = "["+riskguarantyfee+"]";
			}else if(userTenderRecord.getMediacyfee() == 0 && userTenderRecord.getRiskguarantyfee() == 0){
				SecondaryJsonList = "["+guaranteefee+"]";
			}else if(userTenderRecord.getGuaranteefee() == 0 && userTenderRecord.getRiskguarantyfee() == 0){
				SecondaryJsonList = "["+mediacyfee+"]";
			}else if(userTenderRecord.getMediacyfee() == 0){
				SecondaryJsonList = "["+guaranteefee+","+riskguarantyfee+"]";
			}else if(userTenderRecord.getGuaranteefee() == 0){
				SecondaryJsonList = "["+mediacyfee+","+riskguarantyfee+"]";
			}else if(userTenderRecord.getRiskguarantyfee() == 0){
				SecondaryJsonList = "["+mediacyfee+","+guaranteefee+"]";
			}else{
				SecondaryJsonList = "["+mediacyfee+","+guaranteefee+","+riskguarantyfee+"]";
			}
			LoanJsonList = "[{\"LoanOutMoneymoremore\":\""+LoanOutMoneymoremore+"\",\"LoanInMoneymoremore\":\""+LoanInMoneymoremore+"\",\"OrderNo\":\""+OrderNo+"\",\"BatchNo\":\""+BatchNo+"\",\"Amount\":\""+Amount+"\",\"FullAmount\":\""+FullAmount+"\",\"TransferName\":\""+TransferName+"\",\"Remark\":\""+Remark+"\",\"SecondaryJsonList\":\""+SecondaryJsonList+"\"}]"; // 转账列表
		}else{
			LoanJsonList = "[{\"LoanOutMoneymoremore\":\""+LoanOutMoneymoremore+"\",\"LoanInMoneymoremore\":\""+LoanInMoneymoremore+"\",\"OrderNo\":\""+OrderNo+"\",\"BatchNo\":\""+BatchNo+"\",\"Amount\":\""+Amount+"\",\"FullAmount\":\""+FullAmount+"\",\"TransferName\":\""+TransferName+"\",\"Remark\":\""+Remark+"\"}]"; // 转账列表
		}
		String PlatformMoneymoremore = MMMParams.PlatformMoneymoremore; // 平台乾多多标识
		Integer TransferAction = 1; // 转账类型-1.投标,2.还款,3.其他
		Integer Action = 1; // 操作类型-1.手动转账，2.自动转账
		Integer TransferType = 2; // 转账方式-1.桥连，2.直连
		Integer ArrivalTime = 1; // 到账时间-空.实时转账，1.普通转账，2.次日转账
//		Integer DelayTransfer = 1; // 是否半自动批处理(暂不可用)-空.否,1.是（启用该功能的条件:1.直连，2.付款人唯一，3.公共账户余额不参与转账）
		
		String Remark1 = ""; // 自定义备注
		String Remark2 = ""; // 自定义备注
		String Remark3 = ""; // 自定义备注
		String ReturnURL = StringUtil.getBasePath(request)+"/moneymoremore/UserTender/userTenderReturnUrl.action"; // 页面返回网址
		String NotifyURL = StringUtil.getBasePath(request)+"/moneymoremore/UserTender/userTenderCallBack.action"; // 后台通知网址
		
		System.out.println("乾多多主动投标请求");
		System.out.println("开始拼接乾多多主动投标接口请求参数！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		
		// 组装加签字符串原文
		// 注意加签字符串的组装顺序参 请照接口文档
		/*LoanJsonList + PlatformMoneymoremore + TransferAction + 
		  Action + TransferType + ArrivalTime + NeedAudit + 
		  DelayTransfer + RandomTimeStamp + Remark1 + 
		  Remark2 + Remark3 + ReturnURL + NotifyURL*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(LoanJsonList))
				.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
				.append(TransferAction)
				.append(Action)
				.append(TransferType)
				.append(ArrivalTime)
//				.append(NeedAudit)
//				.append(DelayTransfer)
				.append(StringUtils.trimToEmpty(Remark1))
				.append(StringUtils.trimToEmpty(Remark2))
				.append(StringUtils.trimToEmpty(Remark3))
				.append(StringUtils.trimToEmpty(ReturnURL))
				.append(StringUtils.trimToEmpty(NotifyURL));
		String plainStr = buffer.toString();
		System.out.println("投标转账提交参数原文：=============="+plainStr);
		//私钥签名
		String privateResult = "";
		
		RsaHelper rsa = RsaHelper.getInstance();
		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
		
		try {
			request.setAttribute("LoanJsonList", URLEncoder.encode(LoanJsonList, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
		request.setAttribute("TransferAction", TransferAction);
		request.setAttribute("Action", Action);
		request.setAttribute("TransferType", TransferType);
		request.setAttribute("ArrivalTime", ArrivalTime);
//		request.setAttribute("NeedAudit", NeedAudit);
//		request.setAttribute("DelayTransfer", DelayTransfer);
		request.setAttribute("Remark1", Remark1);
		request.setAttribute("Remark2", Remark2);
		request.setAttribute("Remark3", Remark3);
		request.setAttribute("ReturnURL", ReturnURL);
		request.setAttribute("NotifyURL", NotifyURL);
		request.setAttribute("SignInfo", privateResult);
		try {
			request.getRequestDispatcher("/WEB-INF/MMMPages/UserTender/UserTender.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
