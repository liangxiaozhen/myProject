package com.ptpl.controller;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Red_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.model.*;
import com.ptpl.service.*;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 用户主动投标
 *
 * @author zhenglm
 * @ClassName: UserTenderBaseController
 * @date 2017年3月2日 下午2:14:19
 */
@Controller
@RequestMapping("/user/tenderBase")
public class UserTenderBaseController extends BaseController {

    /**
     * 投标记录service
     */
    @Autowired
    UserTenderServiceI userTenderService;

    /**
     * 标的设置service
     */
    @Autowired
    TenderItemServiceI tenderItemService;

    /**
     * 用户账户信息安全service
     */
    @Autowired
    UserAccountSafeInfoServiceI userAccountSafeInfoService;

    /**
     * 用户账户service
     */
    @Autowired
    UserAccountServiceI userAccountService;

    /**
     * 借款申请记录service
     */
    @Autowired
    loanappServiceI loanappService;

    /**
     * 用户托管账户信息service
     */
    @Autowired
    UserFsAccountInfoServiceI userFsAccountInfoService;

    /**
     * 标的居间费设置service
     */
    @Autowired
    MediacyFeeServiceI mediacyFeeService;

    /**
     * 标的担保费设置service
     */
    @Autowired
    GuaranteeFeeServiceI guaranteeFeeService;

    /**
     * 标的风险保证金设置service
     */
    @Autowired
    RiskGuarantyMoneyServiceI riskGuarantyMoneyService;

    /**
     * 标的增益设置service
     */
    @Autowired
    PlusServiceI plusService;

    /**
     * 用户使用券service
     */
    @Autowired
    UserInterestRateCouponServiceI userInterestRateCouponService;

    /**
     * 用户红包service
     */
    @Autowired
    UserRedEnvelopeServiceI userRedEnvelopeService;

    /**
     * 债转设置Service
     */
    @Autowired
    UserDebtAttornServiceI userDebtAttornService;

    /**
     * 投标增益使用关联Service
     */
    @Autowired
    UserTenderPlusLinkServiceI userTenderPlusLinkService;

    /**
     * 奖品设置Service
     */
    @Autowired
    AwardServiceI awardService;

    /**
     * 奖品设置指定标号关联Service
     */
    @Autowired
    AwardTenderLinkServiceI awardTenderLinkService;
    /**
     * 标全局设置Service
     */
    @Autowired
    private GlobalSettingServiceI globalSettingService;

    static Boolean flag = false;

    /**
     * 去投标页面
     *
     * @param id 标的设置表ID
     * @return ModelAndView    返回类型
     * @Title: investing
     * @Description: TODO(查询用户账户可用余额，筛选出可用的加息券、类现金、假现金)
     */
    @RequestMapping("investing")
    public ModelAndView investing(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        // 从session中获取当前登录用户基本信息
        UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
        if (userBaseAccountInfo != null) {
            BigDecimal baseid = userBaseAccountInfo.getId();
            if (id != null) {
                TenderItem item = tenderItemService.findTenderItemById(id);
                if (item != null) {
                    UserAccount userAccount = userAccountService.getUserAccountByBaseId(baseid); // 根据baseid查询用户账户信息
                    List<UserInterestRateCoupon> interestRateCoupons = null;
                    List<UserRedEnvelope> likeVouchers = null;
                    List<UserRedEnvelope> fakeVouchers = null;
                    if (item.getIsaplus() != null && item.getIsaplus() == 1) { // 判断是否允许使用增益
                        System.out.println("此项目可以使用增益！！！！！！");
                        Plus plus = plusService.findPlusByTid(item.getId()); // 根据标的ID查询标的增益设置表
                        if (plus != null) {
                            UserTenderPlusLink userTenderPlusLink = new UserTenderPlusLink();
                            userTenderPlusLink.setBaseid(baseid);
                            userTenderPlusLink.setTid(item.getId());
                            List<UserTenderPlusLink> linkList = userTenderPlusLinkService.selectByTidAndBaseid(userTenderPlusLink);
                            if (plus.getIsaint() != null && plus.getIsaint() == 1) { // 判断是否允许使用加息券
                                System.out.println("此项目可以使用加息券！！！！！！");
                                UserInterestRateCoupon coupon = new UserInterestRateCoupon();
                                coupon.setBaseid(userBaseAccountInfo.getId()); // 用户baseid
                                coupon.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
                                coupon.setUirctype((short) 6); // 奖品类型——1.现金券，2.类现金券，3.假现金券，4.交易积分，5.系统积分，6.加息券
                                interestRateCoupons = userInterestRateCouponService.findIsUseByBaseid(coupon); // 查询当前用户可使用的加息券
                                Iterator<UserInterestRateCoupon> rate = interestRateCoupons.iterator(); // 迭代筛选出不符合条件的加息券
                                while (rate.hasNext()) {
                                    UserInterestRateCoupon Interest = rate.next();
                                    String str = awardLimit(Interest.getUircno(), item);
                                    String str1 = checkCouponTotal(linkList, plus);
                                    if (Arith.sub(Interest.getIcrate(), plus.getAoneqrofit()) > 0 || !str.equalsIgnoreCase("success") || !str1.equalsIgnoreCase("success")) {
                                        rate.remove(); // 去掉不符合条件的加息券
                                    }
                                }
                            }
                            if (plus.getIsavoucher() != null && plus.getIsavoucher() == 1) { // 判断是否允许使用类现金
                                UserRedEnvelope red = new UserRedEnvelope();
                                red.setBaseid(userBaseAccountInfo.getId()); // 用户baseid
                                red.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
                                red.setRetype(Red_Constant.RETYPE_LEIXIANJIN); // 红包类型——1.现金，2.类现金，3.假现金
                                likeVouchers = userRedEnvelopeService.selectByBaseid(red); // 查询当前用户可使用的类现金
                                Iterator<UserRedEnvelope> like = likeVouchers.iterator(); // 迭代筛选出不符合条件的类现金
                                while (like.hasNext()) {
                                    UserRedEnvelope likecash = like.next();
                                    String str = awardLimit(likecash.getUreno(), item);
                                    String str1 = checklikeCashTotal(linkList, plus);
                                    if (Arith.sub(likecash.getRedenvelope(), plus.getAonevamount()) > 0 || !str.equalsIgnoreCase("success") || !str1.equalsIgnoreCase("success")) {
                                        like.remove(); // 去掉不符合条件的类现金
                                    }
                                }
                            }
                            if (plus.getIsalikevoucher() != null && plus.getIsalikevoucher() == 1) { // 判断是否允许使用假现金
                                UserRedEnvelope red = new UserRedEnvelope();
                                red.setBaseid(userBaseAccountInfo.getId()); // 用户baseid
                                red.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
                                red.setRetype(Red_Constant.RETYPE_JIAXIANJIN); // 红包类型——1.现金，2.类现金，3.假现金
                                fakeVouchers = userRedEnvelopeService.selectByBaseid(red); // 查询当前用户可使用的假现金
                                Iterator<UserRedEnvelope> fake = fakeVouchers.iterator(); // 迭代筛选出不符合条件的假现金
                                while (fake.hasNext()) {
                                    UserRedEnvelope fakecash = fake.next();
                                    String str = awardLimit(fakecash.getUreno(), item);
                                    String str1 = checkfakeCashTotal(linkList, plus);
                                    if (Arith.sub(fakecash.getRedenvelope(), plus.getAonelvamount()) > 0 || !str.equalsIgnoreCase("success") || !str1.equalsIgnoreCase("success")) {
                                        fake.remove(); // 去掉不符合条件的假现金
                                    }
                                }
                            }
                        }
                    }
                    mv.addObject("avlbalance", userAccount.getAvlbalance()); // 用户账户可用余额
                    mv.addObject("interestRateCoupons", interestRateCoupons); // 可用加息券
                    mv.addObject("likeVouchers", likeVouchers); // 可用类现金
                    mv.addObject("fakeVouchers", fakeVouchers); // 可用假现金
                    mv.addObject("df", df);//格式化余额
//					if(userBaseAccountInfo.getIscancheattender().equals((short) 1) && item.getIsfaketender().equals((short) 1)){
//						mv.addObject("iscancheattender", 1);  // 当前用户是否可以假投标
//					}else{
//						mv.addObject("iscancheattender", 0);  // 当前用户是否可以假投标
//					}
                }
            }
        } else {
            mv.addObject("status", "logout");
        }
        mv.addObject("id", id);
        mv.setViewName("front/usertender/xiangmu_inform");
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
     *
     * @param id（标的设置表ID）
     * @param amount（投标金额）
     * @param tpass（约标码）
     * @return
     * @throws Exception
     */
    @RequestMapping("/tenderCheck")
    public synchronized void tenderCheck(String id, String amount, String tpass, String uircid, String ureid) throws Exception {
        System.out.println("进入投标校验！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        Map<String, String> map = new HashMap<String, String>();
        String info = judgeCondition(id, amount, tpass, uircid, ureid);
        map.put("info", info);
        sendJsonData(response, JSON.toJSONString(map));
        System.out.println(info);
        if (info.equalsIgnoreCase("success")) {
            flag = true;
        }
    }

    /**
     * 投标限制条件校验
     *
     * @param id     标的id
     * @param amount 投标金额
     * @param tpass  约标码
     * @param uircid 加息券id
     * @param ureid  红包id
     * @return String    返回类型
     * @Title: judgeCondition
     * @Description: TODO(投标限制条件校验)
     */
    private String judgeCondition(String id, String amount, String tpass, String uircid, String ureid) {
        // 从session中获取当前登录用户基本信息
        UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
//		UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(858));
        if (userBaseAccountInfo == null)
            return "logout";
        if (amount == null || amount.trim().equals("")) // 判断投标金额是否为空
            return "投标金额不能为空！";
        BigDecimal baseId = userBaseAccountInfo.getId(); // 投资人baseid
        TenderItem item = null;
        if (id == null || id.trim().equals("")) { // 判断前台传来的id是否为空
            return "不存在的标！";
        } else {
            item = tenderItemService.findTenderItemById(new BigDecimal(id));    // 根据id查询标的设置详情
            if (item == null) // 判断标的是否存在
                return "不存在的标！";
        }
        //投资人设置(1所有人，2新手，3会员等级，4定向名单 )
        if (item.getInvesterrange() != null) {
            switch (item.getInvesterrange()) {
                case 2:
                    List<GlobalSetting> list = globalSettingService.allData();
                    GlobalSetting global = list.get(0);
                    Boolean isGreenhand = isGreenhand(userBaseAccountInfo, global);
                    if (!isGreenhand) {
                        return "该标只能允许新手投标";
                    }
                    break;
                case 3:
                    UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(baseId);// 根据baseid查询用户账户安全信息表
                    Short indexStr = userAccountSafeInfo.getUgrade(); // 获取借款用户等级
                    System.out.println("用户等级==========================" + indexStr);
                    List<Integer> pa1 = StringUtil.pars(item.getUgrestrict());
                    if (!pa1.contains(indexStr.intValue())) {
                        return "您不在允许投标用户等级范围内";
                    }
                    break;
                case 4:
                    BigDecimal snlid = item.getSnlid(); // 定向名单列表Id
                    if (!IsRightOrExcept(snlid, userBaseAccountInfo)) { // 判断投资人是否在定向包含名单
                        return "您不在定向名单中";
                    }
                    break;
                default:
                    break;
            }
        }
        String c = isClient(request);
        if (!item.getCrestrict().contains(c)) // 判断当前客户端是否限制投标
            return "当前客户端限制投此标的";
        if (new Date().getTime() >= item.getTendtime().getTime()) // 判断标的是否过期
            return "此项目已经过期";
        if (item.getTstatus().equals((short) 7))
            return "项目已流标";
        if (!item.getTstatus().equals((short) 3))
            return "标的状态不是投标中，不能投标";
        if (item.getFinishtamount() == null)
            item.setFinishtamount(0.00);
        if (Arith.sub(item.getFinishtamount(), item.getTamount()) >= 0) // 判断是否满标
            return "项目已满标";
        loanapp loan = loanappService.selectByPrimaryKey(item.getLoanappid()); // 根据借款申请ID查询借款人申请信息
        if (loan != null && baseId.equals(loan.getBaseid())) // 判断投资人是否是借款人
            return "投资人不能为借款人";
        Double famount = Double.valueOf(amount); // 填入的投资金额转换数据类型
        UserAccount ua = userAccountService.getUserAccountByBaseId(baseId);
        Double avlbalance = ua.getAvlbalance(); // 投资人账户可用余额
        Double leftMoney = item.getTamount() - item.getFinishtamount();//标中剩余可投标金额
        if (Arith.sub(famount, leftMoney) > 0) // 判断剩余可投标金额
            return "当前投标金额大于剩余可投标金额";
        if (item.getIsmultiple() == 1) {
            if (famount % item.getMinoncetamount() != 0) {
                if (leftMoney < item.getMinoncetamount() && leftMoney > famount) {
                    return "剩余金额小于最小投资金额，需一次性投完";
                } else {
                    return "投资金额必须为最小金额的整数倍";
                }
            }
        } else {
            if (leftMoney < item.getMinoncetamount() && leftMoney > famount) {
                return "剩余金额小于最小金额，需一次性投完";
            }
        }
        if (Arith.sub(avlbalance, famount) < 0) // 判断投资金额是否大于账户余额
            return "投资金额大于账户余额";
        if (Arith.sub(famount, item.getMinoncetamount()) < 0 && Arith.sub(leftMoney, item.getMinoncetamount()) > 0)
            return "起投金额为" + df1.format(item.getMinoncetamount()) + "元";
        if (item.getIsmultiple().equals((short) 1)) {
            if (famount % item.getMinoncetamount() != 0)
                return "投标金额为起投金额" + item.getMinoncetamount() + "整数倍";
        }
        Map<String, Object> condition = new HashMap<>();
        condition.put("tenderid", item.getId()); // 标的ID
        condition.put("outaccountid", baseId); // 投资人ID
        condition.put("utproperty", TenderRecord_Constant.UTPROPERTY_ORIGINAL); // 投标属性--原始投标
        condition.put("tstatus", TenderRecord_Constant.TSTATUS_PENDINGAUDIT); // 投标状态--待审核（投标成功-未放款）
        List<UserTender> total = userTenderService.findUserTenderRecord(condition); // 查询投资人当前标的投标成功的原始投资记录
        double finishAmount = 0; // 投资人当前标的已完成的投资总金额（包含当前的投资金额）
        for (UserTender userTender : total) {
            finishAmount = Arith.add(finishAmount, userTender.getAmount()); // 查询投资人当前标的已投总金额
        }
        System.out.println("投资人已完成投标金额" + finishAmount);
        System.out.println("投资人已完成的投标次数=========================" + total.size());
        if (total.size() >= item.getOnettimes()) // 判断用户已投标次数是否超过单人投标限制
            return "单人投标限制" + item.getOnettimes() + "次";
        double totalAmount = Arith.add(famount, finishAmount); // 当前投标金额+已投金额
        if (Arith.sub(totalAmount, item.getTamount()) > 0) // 判断是否超过累投金额
            return "超过累投金额限制！";
        Double minoncetamount = item.getMinoncetamount(); // 单笔允许最低投资金额
        Double maxoncetamount = item.getMaxoncetamount(); // 单笔允许最高投资金额
        if (Arith.sub(minoncetamount, famount) > 0 && Arith.sub(leftMoney, item.getMinoncetamount()) > 0) // 判断是否低于单笔允许最低投资金额
            return "不能低于单笔允许最低投资金额:" + minoncetamount + "元!";
        if (Arith.sub(maxoncetamount, famount) < 0) // 判断是否高于单笔允许最高投资金额
            return "不能高于单笔允许最高投资金额:" + maxoncetamount + "元!";
        if (item.getIsappointtender() != null) {
            if (item.getIsappointtender() == 1) { // 判断是否为约标
                System.out.println("约标码================" + item.getTpass());
                System.out.println("输入的约标码================" + tpass);
                if (tpass == null || tpass.trim().equals("")) {
                    return "约标码不能为空";
                } else {
                    if (!tpass.trim().equalsIgnoreCase(item.getTpass())) {
                        return "您输入的约标码有误";
                    }
                }
            }
        }
        if (StringUtil.isNotEmpty(uircid) || StringUtil.isNotEmpty(ureid)) {
            // 校验增益是否可用于投标
            String message = checkPlus(baseId, item, uircid, ureid, amount);
            if (!message.equalsIgnoreCase("success")) {
                return message;
            }
        }
        return "success";
    }

    /*
     *判断投标人是否为新手
     */
    private Boolean isGreenhand(UserBaseAccountInfo userBaseAccountInfo, GlobalSetting global) {
        Boolean isGreenhand = true;
        BigDecimal baseId = userBaseAccountInfo.getId();//用户id
        Date regdate = userBaseAccountInfo.getRegdate();//用户注册时间
        Integer daysBetween = StringUtil.daysBetween(regdate, new Date());//用户注册天数
        List<UserTender> list = userTenderService.findRecordByBaseId(baseId);//用户投标次数
        Integer count = list.size();//用户投标次数
        double amountAll = 0;   //用户总投标金额
        for (UserTender userTender : list) {
            amountAll += userTender.getAmount();
        }
        Integer newerBidRule = global.getNewerBidRule();//新手投标判定规则
        Float newerBidAmount = global.getNewerBidAmount();//新手累投金额限制
        Integer newerBidCount = global.getNewerBidCount();//新手投标次数限制
        Integer newerBidDayLimit = global.getNewerBidDayLimit();//新手投标注册时间天数限制
        if (newerBidRule != null) {
            if (newerBidRule == 1) {
                if (newerBidCount != null && newerBidDayLimit != null) {
                    if ((count > newerBidCount) && (daysBetween > newerBidDayLimit)) {
                        isGreenhand = false;
                    }
                }
            } else if (newerBidRule == 2) {
                if (newerBidAmount != null && newerBidDayLimit != null) {
                    if ((amountAll > newerBidAmount) && (daysBetween > newerBidDayLimit)) {
                        isGreenhand = false;
                    }
                }
            }
        }
        return isGreenhand;
    }

    /**
     * 校验增益是否能用于投标
     *
     * @param item
     * @param uircno
     * @param ureno
     * @return String
     * @Title: checkPlus
     * @Description: TODO(1.判断是否允许使用加息券（单次张数、总计张数、单张收益）,
     *2.判断是否允许使用类现金（单次张数、总计张数、单张金额）,
     *3.判断是否允许使用假现金（单次张数、总计张数、单张金额）,
     *4.判断奖品设置表中投标使用限制)
     */
    private String checkPlus(BigDecimal baseid, TenderItem item, String uircid, String ureid, String amount) {
        String message = "success";
        if (StringUtil.isNotEmpty(item.getIsaplus()) && item.getIsaplus() == 1) { // 判断是否允许使用增益
            Plus plus = plusService.findPlusByTid(item.getId()); // 根据标的ID查询标的增益设置表
            UserTenderPlusLink userTenderPlusLink = new UserTenderPlusLink();
            userTenderPlusLink.setBaseid(baseid);
            userTenderPlusLink.setTid(item.getId());
            List<UserTenderPlusLink> linkList = userTenderPlusLinkService.selectByTidAndBaseid(userTenderPlusLink); // 根据用户id和标的id查询投标增益使用关联表
            if (plus != null) {
                if (StringUtil.isNotEmpty(uircid)) {
                    if (plus.getIsaint() != null && plus.getIsaint() == 1) { // 判断是否允许使用加息券
                        String[] uircids = uircid.split(",");
                        UserInterestRateCoupon userInterestRateCoupon = null;
                        for (String id : uircids) {
                            userInterestRateCoupon = userInterestRateCouponService.findUserInterestRateCouponById(new BigDecimal(id));
                            if (userInterestRateCoupon == null) {
                                return message = "加息券不存在";
                            }
                            Integer aOnceInt = plus.getAonceint(); // 单次允许使用加息券张数
                            if (uircids.length > aOnceInt) {
                                return message = "超过单次允许使用加息券张数";
                            } else {
                                Double aOneQrofit = plus.getAoneqrofit(); // 允许单张加息券收益
                                if (userInterestRateCoupon.getIcrate() > aOneQrofit) {
                                    return message = "超过单张加息券收益";
                                }
                            }
                            String info = awardLimit(userInterestRateCoupon.getUircno(), item, Double.valueOf(amount));
                            if (!info.equalsIgnoreCase("success")) {
                                return info;
                            }
                        }
                        if (!checkCouponTotal(linkList, plus).equalsIgnoreCase("success")) {
                            return checkCouponTotal(linkList, plus);
                        }
                    }
                }
                if (StringUtil.isNotEmpty(ureid)) {
                    String[] ureids = ureid.split(",");
                    for (String id : ureids) {
                        UserRedEnvelope userRedEnvelope = userRedEnvelopeService.findUserRedEnvelopeById(new BigDecimal(id));
                        if (userRedEnvelope == null) {
                            return message = "红包不存在";
                        } else {
                            if (userRedEnvelope.getRetype() == 2) {
                                if (plus.getIsavoucher() != null && plus.getIsavoucher() == 1) { // 判断是否允许使用类现金
                                    Integer aOnceVoucher = plus.getAoncevoucher(); // 单次允许类现金卷张数
                                    if (ureids.length > aOnceVoucher) {
                                        return message = "超过单次允许使用类现金卷张数";
                                    } else {
                                        Double aOneVAmount = plus.getAonevamount(); // 允许单张类现金额度
                                        if (userRedEnvelope.getRedenvelope() > aOneVAmount) {
                                            return message = "超过单张类现金额度";
                                        }
                                    }
                                    if (!checklikeCashTotal(linkList, plus).equalsIgnoreCase("success")) {
                                        return checklikeCashTotal(linkList, plus);
                                    }
                                }
                            } else if (userRedEnvelope.getRetype() == 3) {
                                if (plus.getIsalikevoucher() != null && plus.getIsalikevoucher() == 1) { // 判断是否允许使用假现金
                                    Integer aOnceLikeVoucher = plus.getAoncelikevoucher(); // 单次允许假现金卷张数
                                    if (ureids.length > aOnceLikeVoucher) {
                                        return message = "超过单次允许使用假现金卷张数";
                                    } else {
                                        Double aOneLVAmount = plus.getAonelvamount(); // 允许单张假现金额度
                                        if (userRedEnvelope.getRedenvelope() > aOneLVAmount) {
                                            return message = "超过单张假现金额度";
                                        }
                                    }
                                    if (!checkfakeCashTotal(linkList, plus).equalsIgnoreCase("success")) {
                                        return checkfakeCashTotal(linkList, plus);
                                    }
                                }
                            }
                        }
                        String info = awardLimit(userRedEnvelope.getUreno(), item, Double.valueOf(amount));
                        if (!info.equalsIgnoreCase("success")) {
                            return info;
                        }
                    }
                }
            }
        } else {
            message = "此项目不允许使用增益";
        }
        return message;
    }

    /**
     * @param linkList
     * @param plus
     * @return String    返回类型
     * @Title: checkCouponTotal
     * @Description: TODO(判断是否超过允许使用加息券总张数)
     */
    private String checkCouponTotal(List<UserTenderPlusLink> linkList, Plus plus) {
        Integer aTotalInt = plus.getAtotalint(); // 允许使用加息券总张数
        int icNum = 0;
        for (UserTenderPlusLink link : linkList) {
            if (link.getIcid() != null) {
                icNum += 1;
            }
        }
        if (icNum > aTotalInt) {
            return "超过允许使用加息券总张数";
        }
        return "success";
    }

    /**
     * @param linkList
     * @param plus
     * @return String    返回类型
     * @Title: checklikeCashTotal
     * @Description: TODO(判断是否超过允许类现金卷张数)
     */
    private String checklikeCashTotal(List<UserTenderPlusLink> linkList, Plus plus) {
        Integer aTotalVoucher = plus.getAtotalvoucher(); // 总计允许类现金卷张数
        int voucherNum = 0;
        for (UserTenderPlusLink link : linkList) {
            if (link.getReid() != null) {
                voucherNum += 1;
            }
        }
        if (voucherNum > aTotalVoucher) {
            return "超过允许使用类现金总张数";
        }
        return "success";
    }

    /**
     * @param linkList
     * @param plus
     * @return String    返回类型
     * @Title: checkCouponTotal
     * @Description: TODO(判断是否超过允许使用假现金总张数)
     */
    private String checkfakeCashTotal(List<UserTenderPlusLink> linkList, Plus plus) {
        Integer aTotalInt = plus.getAtotallikevoucher(); // 允许使用假现金总张数
        int icNum = 0;
        for (UserTenderPlusLink link : linkList) {
            if (link.getReid() != null) {
                icNum += 1;
            }
        }
        if (icNum > aTotalInt) {
            return "超过允许使用假现金总张数";
        }
        return "success";
    }


    /**
     * 奖品-投标使用限制
     *
     * @param awardNo
     * @param item
     * @param amount
     * @return String
     * @Title: awardLimit
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    private String awardLimit(String awardNo, TenderItem item, double amount) {
        Award award = awardService.selectByAwardNo(awardNo);
        if (Arith.sub(amount, award.getTminmoney()) < 0) {
            return "投标金额低于限制最低值，不能使用增益" + awardNo;
        }
        if (Arith.sub(amount, award.getTmaxmoney()) > 0) {
            return "投标金额高于限制最高值，不能使用增益" + awardNo;
        }
        return awardLimit(awardNo, item);
    }

    /**
     * 奖品-投标使用限制
     *
     * @param awardNo
     * @param item
     * @return String    返回类型
     * @Title: awardLimit
     */
    private String awardLimit(String awardNo, TenderItem item) {
        Award award = awardService.selectByAwardNo(awardNo);
        if (award.getTattribute() != null) {
            Short tpro = item.getTpro();
            char[] tAttribute = award.getTattribute().toCharArray();
            if (tAttribute[tpro - 1] != '1') {
                return "投标属性限制不满足，不能使用增益" + awardNo;
            }
            double loantime = item.getLoantime();
            if (item.getDayormonth().equalsIgnoreCase("年")) {
                loantime = Arith.mul(item.getLoantime(), 365);
            } else if (item.getDayormonth().equalsIgnoreCase("月")) {
                loantime = Arith.mul(item.getLoantime(), 30);
            }
            if (loantime < award.getTdayLimitl()) {
                return "借款周期小于投标期限限制最低值，不能使用增益" + awardNo;
            }
            if (loantime > award.getTdayrestrict()) {
                return "借款周期大于投标期限限制最高值，不能使用增益" + awardNo;
            }
            if (Arith.sub(item.getTinterest(), award.getTmlrrestrict()) < 0) {
                return "投标收益低于限制最低值，不能使用增益" + awardNo;
            }
            if (Arith.sub(item.getTinterest(), award.getTmhrrestrict()) > 0) {
                return "投标收益高于限制最高值，不能使用增益" + awardNo;
            }
            if (item.getRepaymentpro() == award.getTrtype()) {
                return "投标还款方式不满足，不能使用增益" + awardNo;
            }
        } else {
            List<AwardTenderLink> awardTenderLink = awardTenderLinkService.getAwardTenderLinksByawardId(award.getId());
            Boolean linkFlag = false;
            for (AwardTenderLink link : awardTenderLink) {
                if (link.getTenderId().equals(item.getId())) {
                    linkFlag = true;
                }
            }
            if (!linkFlag) {
                return "指定标号不满足，不能使用增益" + awardNo;
            }
        }
        return "success";
    }


    /**
     * @param snlid
     * @param userBaseAccountInfo
     * @return void
     * @Title: IsRightOrExcept
     * @Description: TODO(判断用户是否在定向包含名单)
     */
    private boolean IsRightOrExcept(BigDecimal snlid, UserBaseAccountInfo userBaseAccountInfo) {
        Boolean bool = false;
        Set<UserBaseAccountInfo> set = userDebtAttornService.getSpecialNameList(snlid); // 根据定向名单列表Id查询业务对象名单设置包含的所有用户
        Iterator<UserBaseAccountInfo> it = set.iterator();
        while (it.hasNext()) {
            BigDecimal basid = it.next().getId();
            if (basid.equals(userBaseAccountInfo.getId())) {
                System.out.println(bool);
                bool = true;
                break;
            }
        }
        return bool;
    }

    /**
     * @param request
     * @return String
     * @Title: isClient
     * @Description: TODO(判断投标来源客户端是否允许投标)
     */
    public String isClient(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if (userAgent == null || userAgent.indexOf("windows nt") == -1 ? false : true) { // 判断当前客户端是否为PC
            return "d";
        } else if (userAgent == null || userAgent.indexOf("android") == -1 ? false : true) { // 判断当前客户端是否为android
            return "a";
        } else if (userAgent == null || userAgent.indexOf("iphone") == -1 ? false : true) { // 判断当前客户端是否为iPhone
            return "p";
        } else if (userAgent == null || userAgent.indexOf("wap") == -1 ? false : true) { // 判断当前客户端是否为wap
            return "o";
        } else if (userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true) { // 判断当前客户端是否为微信
            return "w";
        }
        return "";
    }

    /**
     * @param request
     * @return String
     * @Title: tenderClient
     * @Description: TODO(获取投标来源客户端)
     */
    public String getTenderClient(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if (userAgent == null || userAgent.indexOf("windows nt") == -1 ? false : true) { // 判断当前客户端是否为PC
            return "PC";
        } else if (userAgent == null || userAgent.indexOf("android") == -1 ? false : true) { // 判断当前客户端是否为android
            return "安卓";
        } else if (userAgent == null || userAgent.indexOf("iphone") == -1 ? false : true) { // 判断当前客户端是否为iPhone
            return "苹果";
        } else if (userAgent == null || userAgent.indexOf("wap") == -1 ? false : true) { // 判断当前客户端是否为wap
            return "wap";
        } else if (userAgent == null || userAgent.indexOf("micromessenger") == -1 ? false : true) { // 判断当前客户端是否为微信
            return "微信";
        }
        return null;
    }

    /**
     * 主动投标请求参数 1.保存投标记录
     *
     * @param id（标的设置表ID）
     * @param amount（投标金额）
     * @throws Exception
     */
    @RequestMapping("/initiativeTender")
    public synchronized void initiativeTender(BigDecimal id, String amount, String uircid, String ureid) throws Exception {
        if (!flag)
            return;
        flag = false;
        // 从session中获取当前登录用户托管账户基本信息
        UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
        if (userFsAccountInfo == null)
            return;
        BigDecimal baseid = userFsAccountInfo.getBaseid();
        System.out.println("乾多多主动投标请求参数设置！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        Date tenderTime = new Date();
        TenderItem item = tenderItemService.findTenderItemById(id); // 获得标的设置详情
        loanapp loan = loanappService.selectByPrimaryKey(item.getLoanappid()); // 根据借款申请ID查询借款人申请信息
        if (loan == null)
            return;
        UserTender ut = new UserTender();
        ut.setTenderid(item.getId()); // 标的号id
        ut.setUtproperty(TenderRecord_Constant.UTPROPERTY_ORIGINAL); // 投标属性-1.原始投标，2.债转投标，3.假投标
        ut.setOutaccountid(baseid); // 投资方ID
        String OrderNo = StringUtil.getNo(); // 生成订单号
        ut.setOrderno(OrderNo); // 投标订单号
        ut.setInaccountid(loan.getBaseid()); // 借款方用户ID
        ut.setAmount(Double.valueOf(amount)); // 投标金额
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
        ut.setTstatus(TenderRecord_Constant.TSTATUS_INITIAL); // 投标的状态-取消
        ut.setIsblending(TenderRecord_Constant.ISBLENDING_NO); // 是否系统勾兑
        ut.setIsmanblending(TenderRecord_Constant.ISMANBLENDING_NO); // 是否人工勾兑
        ut.setPaycompany("徽商银行"); // 投标通道公司-徽商银行
        ut.setRemark("徽商银行投标测试用"); // 备注
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
            //1.记录到投标增益使用关联表(券类的或红包类的)    2.处理用户红包表（冻结红包）  3.更新投标记录中的假现金 类现字段
            usePlus(item, uircid, ureid, ut, amount);
        }
        UserTender userTenderRecord = userTenderService.findUserTenderByOrderno(OrderNo);
        bidApplyPost(userFsAccountInfo, item, userTenderRecord);
    }

    /**
     * 投标时使用增益
     *
     * @param item
     * @param uircid
     * @param ureid
     * @param ut
     * @param amount
     * @return void
     * @Title: usePlus
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    private void usePlus(TenderItem item, String uircid, String ureid, UserTender ut, String amount) {
        BigDecimal baseid = ut.getOutaccountid();
        String info = checkPlus(baseid, item, uircid, ureid, amount);
        if (info.equalsIgnoreCase("success")) {
            UserTender tenderRecord = userTenderService.findUserTenderByOrderno(ut.getOrderno());
            if (StringUtil.isNotEmpty(uircid)) {
                String[] uircids = uircid.split(",");
                //记录到投标增益使用关联表(券类的)(投标id:utId   标的Id:tid   投资人Id:Baseid    加息券Id:ICId   红包Id:REId)
                for (String cId : uircids) {
                    UserInterestRateCoupon userInterestRateCoupon = userInterestRateCouponService.findUserInterestRateCouponById(new BigDecimal(cId));
                    userInterestRateCoupon.setIsuse(ActAward_Constant.AWARD_ALFROZEN);
                    userInterestRateCoupon.setUsedate(new Date());
                    userInterestRateCouponService.updataCouponById(userInterestRateCoupon); // 冻结加息券
                    UserTenderPlusLink plusLink = new UserTenderPlusLink();
                    plusLink.setUtid(tenderRecord.getId());
                    plusLink.setIcid(new BigDecimal(cId));
                    plusLink.setBaseid(baseid);
                    plusLink.setTid(item.getId());
                    userTenderPlusLinkService.insertSelective(plusLink);
                }
            }
            //记录到投标增益使用关联表（红包类的）(投标id:utId   标的Id:tid   投资人Id:Baseid    加息券Id:ICId   红包Id:REId)
            if (StringUtil.isNotEmpty(ureid)) {
                String[] ureids = ureid.split(",");
                Double VocherAmt = (double) 0;
                Double fakeAmount = (double) 0;
                //用户红包表(红包类型:现金，类现金，假现金)
                for (String rid : ureids) {
                    UserRedEnvelope userRedEnvelope = userRedEnvelopeService.findUserRedEnvelopeById(new BigDecimal(rid));
                    //是否使用
                    userRedEnvelope.setIsuse(ActAward_Constant.AWARD_ALFROZEN);
                    //使用时间
                    userRedEnvelope.setUsedate(new Date());
                    userRedEnvelopeService.updateRedEnvById(userRedEnvelope); // 冻结红包
                    //红包类型（现金，类现金，假现金）
                    if (userRedEnvelope.getRetype() == 2) {
                        //是多张汇总金额
                        VocherAmt = Arith.add(VocherAmt, userRedEnvelope.getRedenvelope());
                    }
                    //红包类型（现金，类现金，假现金）
                    if (userRedEnvelope.getRetype() == 3) {
                        //是多张汇总金额
                        fakeAmount = Arith.add(fakeAmount, userRedEnvelope.getRedenvelope());
                    }
                    //投标增益使用关联实体类
                    UserTenderPlusLink plusLink = new UserTenderPlusLink();
                    plusLink.setUtid(tenderRecord.getId());
                    plusLink.setReid(new BigDecimal(rid));
                    plusLink.setBaseid(baseid);
                    plusLink.setTid(item.getId());
                    userTenderPlusLinkService.insertSelective(plusLink);
                }
                tenderRecord.setVoucheramount(VocherAmt);  // 类现金
                tenderRecord.setLikevoucheramount(fakeAmount); // 假现金
                double totalAmount = Arith.add(Arith.add(tenderRecord.getRealamount(), tenderRecord.getVoucheramount()), tenderRecord.getLikevoucheramount());
                tenderRecord.setAmount(totalAmount);
            }
            userTenderService.updateByPrimaryKeySelective(tenderRecord);
        }
    }

    /**
     * 计算标的居间服务费
     *
     * @param item
     * @param userTender
     */
    private void calculationMediacyFee(TenderItem item, UserTender userTender) {
        List<MediacyFee> mediacyFeeList = mediacyFeeService.listMediacyFee(null); // 查找居间费设置表所有记录
        Double fee = (double) 0; // 居间费
        Double tamount = userTender.getAmount(); // 标的金额（投标金额）
        Short ugrade = getUserAccountSafeInfo().getUgrade();//当前用户等级
        Short tpro = item.getTpro();//标的借款类型
        for (MediacyFee mediacyFee : mediacyFeeList) {
            String gradeStr = mediacyFee.getUgrade();
            String ttypeStr = mediacyFee.getTtype();
            if(gradeStr.charAt(ugrade)=='1'){
                if(ttypeStr.charAt(tpro-1)=='1'){
                    System.out.println("居间费费率" + mediacyFee.getMfrate());
                    if (Arith.mul(tamount, mediacyFee.getMfrate()) > mediacyFee.getMaxmfamount()) {
                        fee = mediacyFee.getMaxmfamount(); // 若大于居间费最高收费则居间费为居间费最高收费
                    } else {
                        fee = Arith.mul(tamount, mediacyFee.getMfrate());
                    }
                    break;
                }
            }
        }
        userTender.setMediacyfee(fee); // 居间服务费
    }

    /**
     * 计算标的担保费
     *
     * @param item
     * @param userTender
     */
    private void calculationGuaranteeFee(TenderItem item, UserTender userTender) {
        List<GuaranteeFee> guaranteeFeeList = guaranteeFeeService.selectByCondition(null); // 查找担保费设置表所有记录
        Double fee = (double) 0; // 担保费
        Double tamount = userTender.getAmount(); // 标的金额（投标金额）
        Short ugrade = getUserAccountSafeInfo().getUgrade();//当前用户等级
        Short tpro = item.getTpro();//标的借款类型
        for (GuaranteeFee guaranteeFee : guaranteeFeeList) {
            String gradeStr = guaranteeFee.getUgrade();
            String ttypeStr = guaranteeFee.getTType();
            if(gradeStr.charAt(ugrade)=='1'){
                if(ttypeStr.charAt(tpro-1)=='1'){
                    if (Arith.mul(tamount, guaranteeFee.getGfrate()) > guaranteeFee.getMaxgfamount()) {
                        fee = guaranteeFee.getMaxgfamount(); // 若大于担保费最高收费则居间费为担保费最高收费
                    } else {
                        fee = Arith.mul(tamount, guaranteeFee.getGfrate());
                    }
                    break;
                }
            }

        }
        userTender.setGuaranteefee(fee); // 担保服务费
    }

    /**
     * 计算标的风险保证金
     *
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
     *
     * @param id
     * @param amount
     * @return void    返回类型
     * @Title: fakeTender
     * @Description: TODO(假投标)
     */
    @RequestMapping("/fakeTender")
    public void fakeTender(BigDecimal id, String amount) {
        UserTender ut = new UserTender();
        TenderItem item = tenderItemService.findTenderItemById(id); // 获得标的设置详情
        ut.setTenderid(item.getId()); // 标的号id
        ut.setOrderno(StringUtil.getNo()); // 投标订单号
        ut.setUtproperty(TenderRecord_Constant.UTPROPERTY_FAKE); // 投标属性-1.原始投标，2.债转投标，3.假投标
        // 从session中获取当前登录用户托管账户基本信息
        UserFsAccountInfo userFsAccountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
        ut.setOutaccountid(userFsAccountInfo.getBaseid()); // 投资方ID
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
        ut.setMediacyfee((double) 0);
        ut.setGuaranteefee((double) 0);
        ut.setRiskguarantyfee((double) 0);
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
        ut.setIsaudit(TenderRecord_Constant.ISAUDIT_NO); // 是否审核
        ut.setRemark("测试用"); // 备注
        int count = 0;
        count = userTenderService.insertSelective(ut); // 保存部分投标记录
        if (count > 0) {
            System.out.println("保存投标记录成功！！！！！！！！！！！！！！！！！！！！！！！！");
            // 修改标的设置表
            item.setFinishtamount(Arith.add(item.getFinishtamount(), Double.valueOf(amount)));    // 已完成投标金额=已完成金额+当前投标金额
            if (Arith.sub(item.getTamount(), item.getFinishtamount()) <= 0) {
                item.setTstatus((short) 5); // 满标
            }
            int num = 0;
            num = tenderItemService.update(item);
            if (num > 0) {
                System.out.println("更新标的设置成功！！！！！！！！！！！！！！！！！！！！！！！！");
            }
        }
    }

    /**
     * 发起徽商银行投标申请
     *
     * @param request
     * @param response
     * @param userFsAccountInfo
     * @param item
     * @param ut
     * @return void    返回类型
     * @Title: bidApplyPost
     * @Description: TODO(发起徽商银行投标申请)
     */
    @RequestMapping(value = "/bidApplyPost", method = {RequestMethod.POST, RequestMethod.GET})
    public synchronized void bidApplyPost(UserFsAccountInfo userFsAccountInfo, TenderItem item, UserTender ut) {
        // 动态获取项目路径
        String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
        LinkedHashMap<String, String> reqMap = new LinkedHashMap<String, String>();
        reqMap.put("VERSION", "1.1"); // 接口版本号——调用的接口版本，固定1.1
        Date date = new Date();
        String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd"); // 交易日期TRXDATE——8位YYYYMMDD
        String TRXTIME = StringUtil.formatDate(date, "HHmmss"); // 交易时间TRXTIME——6为HHmmss
        reqMap.put("TRXDATE", TRXDATE); // 交易日期TRXDATE——8位YYYYMMDD
        reqMap.put("TRXTIME", TRXTIME); // 交易时间TRXTIME——6为HHmmss
        reqMap.put("BANKCODE", HSignUtil.BANKCODE); // 银行代码BANKCODE——30040000-徽商银行、30050000-南昌银行
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); // 合作单位编号COINSTCODE——800114
        reqMap.put("COINSTCHANNEL", "000002"); // 合作单位渠道COINSTCHANNEL——000001-手机APP、000002-网页、000003-微信、000004-行内核心、000005-ESB
        reqMap.put("CARDNBR", userFsAccountInfo.getUsrcustid()); // 电子账号CARDNBR
        reqMap.put("SERI_NO", ut.getOrderno()); // 申请流水号SERI_NO——必填；用于交易的唯一性标识
        System.out.println("申请流水号===============" + reqMap.get("SERI_NO"));
        System.out.println(ut.getRealamount());
        reqMap.put("AMOUNT", df1.format(ut.getRealamount())); // 投标金额AMOUNT
        reqMap.put("FUISSUER", HSignUtil.FUISSUER); // 产品发行方FUISSUER——14
        reqMap.put("PRODUCT", item.getTno()); // 标的编号PRODUCT——标的信息录入时的标的编号，长标的控制开关关闭时必送——选填
        reqMap.put("INTDATE", "20170501"); // 起息日INTDATE——YYYYMMDD
        String INTTYPE = item.getRepaymentpro().toString();
        //还款资金方式的转换
        if (INTTYPE.equals("1")) {
            INTTYPE = "0";
        } else {
            INTTYPE = "2";
        }
        reqMap.put("INTTYPE", INTTYPE); // 付息方式INTTYPE——0：到期与本金一起归还；1：每月固定日期支付；2：每月不确定日期支付；
        reqMap.put("INTPAYDAY", "20"); // 利息每月支付日INTPAYDAY——DD，付息方式为1时必填；若设置日期大于月份最后一天时，则为该月最后一天支付；
        reqMap.put("ENDDATE", StringUtil.formatDate(item.getTendtime(), "yyyyMMdd")); // 产品到期日ENDDATE——YYYYMMDD
        reqMap.put("YIELD", df.format(Arith.mul(item.getTinterest(), 100))); // 预期年化收益率YIELD——5位小数  如年化收益率为10%，需上送10.00000
        reqMap.put("FRZFLAG", "1"); // 是否冻结金额FRZFLAG——0.不冻结；1.冻结
        reqMap.put("BOSAMT_YN", ut.getVoucheramount().equals((double) 0) ? "0" : "1"); // 是否使用红包BOSAMT_YN——0.不使用红包；1.使用红包
        reqMap.put("BOSAMT", df1.format(ut.getVoucheramount())); // 抵扣红包金额BOSAMT——两位小数
        reqMap.put("TRANSDT", ""); // 交易时间TRANSDT——选填
        reqMap.put("BIDNBR", ""); // 标的编号BIDNBR-40——选填
        reqMap.put("REMARK", ""); // 备注REMARK——选填
        reqMap.put("TRDRESV", basePath); // 第三方保留域TRDRESVFORGERPWD_URL——原样返回——选填

        reqMap.put("FORGERPWD_URL", basePath + "/huishang/tradePassword/tradePsswordList.action"); // 忘记密码跳转链接FORGERPWD_URL——用于投标页面中的忘记密码跳转——选填
        reqMap.put("TRANSACTION_URL", basePath + "/user/tenderBase/investing.action?id=" + item.getId()); // 返回交易页面链接TRANSACTION_URL——用于投标后，跳转原交易页面

        if (basePath.contains("my.ganjiangps.com")) {
            //服务器接收徽商投标申请成功的地址
            reqMap.put("REBACK_URL", "http://my.ganjiangps.com/huishang/bidApply/bidApplyCallBack.action"); // 后台响应链接REBACK_URL——用于接收后台响应——选填
        } else {
            //服务器接收徽商投标申请成功后，转发数据回发起端的地址（徽商后台通知地址只会发到已经在徽商备案的域名）
            reqMap.put("REBACK_URL", "http://my.ganjiangps.com/huishang/bidApply/bidApplyCallBackToLocal.action");
        }


        reqMap.put("SUCCESSRESULT_URL", basePath + "/user/tenderBase/investing.action?id=" + item.getId()); // 交易成功跳转链接SUCCESSRESULT_URL——选填
        try {
            String sign = HSignUtil.getRASSign(reqMap);
            reqMap.put("SIGN", sign);
            System.out.println(sign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        try {
            request.setAttribute("reqMap", reqMap);
            request.getRequestDispatcher("/WEB-INF/pages/InitiativeTender/InitiativeTender.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

