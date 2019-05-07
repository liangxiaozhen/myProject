package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.cupdata.zicon.util.RSAEncryptUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.constant.Loanapp_Constant;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.constant.TenderItem_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.*;
import com.ptpl.service.*;
import com.ptpl.web.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:liuqh
 * @date:2016年07月14日 15:50:16
 * @description:标的设置
 */
@Controller
@RequestMapping("/admin/tenderItem")
public class TenderItemController extends BaseController {
    static String uri = "http://onlineuat.cupdata.com:50001/dbesbsit/api/requestEncrypt";
//    static String uri = "http://localhost:8080/dbesb/api/requestEncrypt";

    //加签
    private static String signPrivatePath = HSignUtil.SIGNPRIVATEPATH;
    //加密公钥
    private static String encryptPath = HSignUtil.ENCRYPTPATH;
    //解密
    private static String decryptPath = HSignUtil.DECRYPTPATH;
    //验签
    private static String signPublicPath = HSignUtil.SIGNPUBLICPATH;
    @Autowired
    private TenderItemServiceI tenderItemService;// 标的service
    @Autowired
    private UserGradeServiceI userGradeService;// 用户等级service
    @Autowired
    private RemoveNameServiceI removeNameService;// 移除名单service
    @Autowired
    private loanappServiceI loanappService;// 借贷人借贷申请service
    @Autowired
    private UserFsAccountInfoServiceI userFsAccountInfoService;//用户托管账户信息
    @Autowired
    private UserBaseAccountInfoServiceI userBaseAccountInfoService;//用户基本信息表

    @Autowired
    InterestExpenseServiceI expenseServiceI;//标的利息管理费设置
    @Autowired
    RiskGuarantyMoneyServiceI guarantyMoneyServiceI;//标的风险保证金设置
    @Autowired
    private SpecialNameListServiceI specialNameListService;//定向名单
    @Autowired
    private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteService;//借款类型对象设置
    @Autowired
    private DividedPaymentsServiceI dividedPaymentsService;//标的分期还款计划Service
    @Autowired
    private ItemDetailDescServiceI itemDetailDescService;
    @Autowired
    private TenderFrontEndServiceI tenderFrontEndService;
    @Autowired
    TenderFrontEndSingleServiceI tenderFrontEndSingleService;
    @Autowired
    UserTenderServiceI userTenderService;

    protected SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //标管理页面
    @RequestMapping(value = "/selectTenderItemByCondition", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectTenderItemByCondition(HttpServletRequest request, TenderItem tenderItem) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        PageHelper.orderBy("addtime desc");
        List<TenderItem> tenderitems = tenderItemService.selectByAlltend(tenderItem);
        handleItemManAndTamount(tenderitems);
        //根据标id查询投标状态为：1.待审核 4.已完成 5.处理中 6.处理失败,当标的投标状态为这几种中的任意一种时，这个标就不可以流标
        handleFailItem(tenderitems);
        PageInfo<Object> pagehelper = initPagehelper(map, tenderitems);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagehelper", pagehelper);
        //查询条件回显
        modelAndView.addObject("tenderItem", tenderItem);
        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        modelAndView.addObject("objectQuotes", objectQuotes);
        //标状态
        modelAndView.addObject("tenderItem_tstatus", TenderItem_Constant.TSTATUS_MAP);
        modelAndView.setViewName("admin/tenderItem/TenderItem_List");
        return modelAndView;
    }

    //把标录入到徽商列表页面(审核页面)
    @RequestMapping(value = "selectTenderItemByaudit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView selectTenderItemByaudit(HttpServletRequest request, HttpServletResponse response, TenderItem tenderItem) throws Exception {
        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        PageHelper.orderBy("addtime asc");
        // 调用service层的方法得到对象列表
        List<TenderItem> tenderitems = tenderItemService.selectByConditionnotin(tenderItem);
        handleItemManAndTamount(tenderitems);
        PageInfo<Object> pagehelper = initPagehelper(map, tenderitems);
        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("objectQuotes", objectQuotes);
        System.out.println(pagehelper.getSize());
        modelAndView.addObject("pagehelper", pagehelper);
        modelAndView.addObject("tno", tenderItem.getTno());
        modelAndView.addObject("tname", tenderItem.getTname());
        modelAndView.addObject("tenderItem_tstatus", TenderItem_Constant.TSTATUS_MAP);
        // 指定视图
        modelAndView.setViewName("admin/tenderItem/TenderItem_audit");
        return modelAndView;
    }

    //放款页面
    @RequestMapping(value = "/fangKuan_List", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView fangKuan_List(HttpServletRequest request, TenderItem tenderItem) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        PageHelper.orderBy("addtime asc");
        //状态为：待放款
        tenderItem.setTstatus(TenderItem_Constant.T5);
        List<TenderItem> tenderitems = tenderItemService.selectByAlltend(tenderItem);
        handleItemManAndTamount(tenderitems);
        //根据标id查询投标状态为：1.待审核 4.已完成 5.处理中 6.处理失败,当标的投标状态为这几种中的任意一种时，这个标就不可以流标
        handleFailItem(tenderitems);
        PageInfo<Object> pagehelper = initPagehelper(map, tenderitems);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagehelper", pagehelper);
        //查询条件回显
        modelAndView.addObject("tenderItem", tenderItem);
        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        modelAndView.addObject("objectQuotes", objectQuotes);
        //标状态
        modelAndView.addObject("tenderItem_tstatus", TenderItem_Constant.TSTATUS_MAP);
        modelAndView.setViewName("admin/tenderItem/TenderItem_fangkuan");
        return modelAndView;
    }

    /**
     * 为了在前端显示   借款人：用户名-真实名
     *
     * @author :liuqh
     * @date 2017/7/1 9:11
     */
    private void handleItemManAndTamount(List<TenderItem> tenderitems) {
        if (tenderitems != null && tenderitems.size() > 0) {
            for (TenderItem t : tenderitems) {
                //判断小数点后面是否为零
                Double tinterest = t.getTinterest();
                System.out.println(tinterest);
                double interest = Arith.mul(t.getTinterest(), 100);//保留两位小数，4舍5入，解决0.14*100=14.00000000000002的问题
                t.setInterestrate(interest+"%");
                t.setTamountstr(new DecimalFormat("#,###.00").format(t.getTamount()));
                loanapp loanapp = loanappService.selectByPrimaryKey(t.getLoanappid());
                Map<String, String> handle = handle(loanapp);
                if (handle != null && handle.size() > 0) {
                    t.setLoginname(handle.get("loginname"));
                    t.setRepayman(handle.get("realname"));
                }
            }
        }
    }

    /**
     * 根据标id查询投标状态为：1.待审核 4.已完成 5.处理中 6.处理失败,当标的投标状态为这几种中的任意一种时，这个标就不可以流标
     *
     * @author :liuqh
     * @date 2017/6/26 10:25
     */
    private void handleFailItem(List<TenderItem> tenderItemList) {
        if (tenderItemList != null && tenderItemList.size() > 0) {
            for (TenderItem t : tenderItemList) {
                List<UserTender> tenderList = userTenderService.findTenderByTstatusAndTenderid(t.getId());
                if (tenderList != null && tenderList.size() > 0) {
                    //有投标资金被放款了或被冻结中
                    t.setIsfroze((short) 1);
                } else {
                    t.setIsfroze((short) 0);
                }
            }
        }
    }

    //查询所有状态的标用于单标显示编辑
    @RequestMapping(value = "/selectTenderItemByConditionForFrontEndSingle", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectTenderItemByConditionForFrontEndSingle(HttpServletRequest request, TenderItem tenderItem) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        PageHelper.orderBy("addtime desc");
        // 调用service层的方法得到对象列表
        List<TenderItem> TenderItemList = tenderItemService.selectByAlltend(tenderItem);
        for (TenderItem t : TenderItemList) {
            //根据标编号查此标的单标前端信息总数
            TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
            tenderFrontEndSingle.setTno(t.getTno());
            List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition(tenderFrontEndSingle);
            t.setTenderItemFrontEndSingleTotal(tenderFrontEndSingles.size());
            List<TenderFrontEndSingle> tenderFrontEndSinglesFilleds = tenderFrontEndSingleService.selectContentIsNotNull(t.getTno());
            t.setTenderItemFrontEndSingleFilled(tenderFrontEndSinglesFilleds.size());
        }
        handleItemManAndTamount(TenderItemList);
        PageInfo<Object> pagehelper = initPagehelper(map, TenderItemList);

        // 把对象放进modelAndView中
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(pagehelper.getSize());
        modelAndView.addObject("pagehelper", pagehelper);
        modelAndView.addObject("tenderItem", tenderItem);
        modelAndView.addObject("objectQuotes", objectQuotes);
        modelAndView.addObject("tenderItem_tstatus", TenderItem_Constant.TSTATUS_MAP);
        // 指定视图
        modelAndView.setViewName("admin/tenderItem/TenderItem_ListForFrontEndSingle");
        return modelAndView;
    }

    //转跳到建标列表
    @RequestMapping(value = "selectloanAppbyappStatus", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectloanAppbyappStatus(HttpServletRequest request) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        String loanno = request.getParameter("loanno");
        String loanname = request.getParameter("loanname");
        String start = request.getParameter("startAppTime");
        String end = request.getParameter("endAppTime");

        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        PageHelper.orderBy("apptime asc");
        List<loanapp> applist = null;
        // 调用service层的方法得到对象列表
        if ((loanno == null || loanno.equals("")) && (loanname == null || loanname.equals("")) && (start == null || start.equals("")) && (end == null || end.equals(""))) {
            applist = loanappService.selectloanAppbyappStatus();
        } else {
            Date startAppTime = null;
            Date endAppTime = null;
            if (!StringUtil.isNullStr(start)) {
                startAppTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start);
                System.out.println(startAppTime);
            }
            if (!StringUtil.isNullStr(end)) {
                endAppTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end);
                System.out.println(endAppTime);
            }
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("loanno", loanno);
            map1.put("loanname", "%" + loanname + "%");
            map1.put("startAppTime", startAppTime);
            map1.put("endAppTime", endAppTime);
            modelAndView.addObject("loanno", loanno);
            modelAndView.addObject("loanname", loanname);
            modelAndView.addObject("startAppTime", start);
            modelAndView.addObject("endAppTime", end);
            applist = loanappService.selectByParam(map1);
        }
        if (applist != null && applist.size() > 0) {
            //为了在前端显示   借款人：用户名-真实名
            for (loanapp l : applist) {
                String loanamountstr = new DecimalFormat("#,###.00").format(l.getLoanamount());
                l.setLoanamountstr(loanamountstr);
                Map<String, String> handle = handle(l);
                if (handle != null) {
                    l.setLoginname(handle.get("loginname"));
                    l.setRealname(handle.get("realname"));
                }
            }
        }
        PageInfo<Object> pagehelper = initPagehelper(map, applist);
        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        modelAndView.addObject("objectQuotes", objectQuotes);
        modelAndView.addObject("pagehelper", pagehelper);
        modelAndView.addObject("loanapp_appstatus", Loanapp_Constant.LOANAPP_APPSTATUS_MAP);
        modelAndView.setViewName("admin/tenderItem/TenderItem_fabiao");
        return modelAndView;
    }


    /**
     * 为了在前端显示   借款人：用户名-真实名
     *
     * @author :liuqh
     * @date 2017/7/1 8:25
     */

    private Map<String, String> handle(loanapp l) {
        UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(l.getBaseid());
        HashMap<String, String> hashMap = new HashMap<>();
        if (userBaseAccountInfo != null) {
            String loginname = userBaseAccountInfo.getLoginname();
            loginname = AES.getDecrypt(loginname);
            String realname = userBaseAccountInfo.getRealname();
            realname = AES.getDecrypt(realname);//用户名称（真实姓名）
            hashMap.put("loginname", loginname);
            hashMap.put("realname", realname);
        }
        return hashMap;
    }


    // 新增UI
    @RequestMapping(value = "/insert_TenderItem_Ui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_TenderItem_Ui(String id) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();
        //封装标
        TenderItem tenderItem = handleTenderItem(id);
        modelAndView.addObject("tenderItem", tenderItem);
        modelAndView.addObject("repaymentpromaps", TenderItem_Constant.REPAYMENTPRO_MAP);//还款资金方式
        //封装定向名单
        List<SpecialNameList> nameLists = handleSpecial();
        if (nameLists.size() > 0) {
            modelAndView.addObject("snl2", nameLists);//获取定向名单
        }

        //获取全部等级
        List<UserGrade> uGrades = userGradeService.selectiveForNormal(null);
        modelAndView.addObject("uGrades", uGrades);

        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.selectIsuse();
        if (objectQuotes.size() > 0) {
            modelAndView.addObject("objectQuotes", objectQuotes);
        }
        //封装担保人名单
        Map<String, String> compensatorymans = handleCompensatoryman();
        modelAndView.addObject("compensatorymans", compensatorymans);

        modelAndView.setViewName("admin/tenderItem/TenderItem_inst");
        return modelAndView;
    }

    /**
     * 担保人名单
     *
     * @author :liuqh
     * @date 2017/6/23 20:48
     */
    private Map<String, String> handleCompensatoryman() {
        UserFsAccountInfo userFsAccountInfo = new UserFsAccountInfo();
        userFsAccountInfo.setAccPurpose((short) 4);
        List<UserFsAccountInfo> users = userFsAccountInfoService.findUserFsAccountInfos(userFsAccountInfo);
        HashMap<String, String> compensatorymans = new HashMap<>();
        if (users != null && users.size() > 0) {
            for (UserFsAccountInfo u : users) {
                UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoService.selectByPrimaryKey(u.getBaseid());
                String loginname = AES.getDecrypt(userBaseAccountInfo.getLoginname());
                String realname = AES.getDecrypt(userBaseAccountInfo.getRealname());
                String usrcustid = AES.getDecrypt(u.getUsrcustid());
                BigDecimal baseid = u.getBaseid();
                compensatorymans.put(usrcustid + "," + baseid, loginname + "-" + realname);
            }
        }
        return compensatorymans;
    }

    /**
     * 封装定向名单
     *
     * @author :liuqh
     * @date 2017/6/23 19:55
     */
    private List<SpecialNameList> handleSpecial() {
        //获取定向名单（定向名单内的才可以投标）
        SpecialNameList nameList = new SpecialNameList();
        nameList.setBusinessType((short) 1);
        List<SpecialNameList> snl2 = specialNameListService.getSpecialNameLists(nameList);
        List<SpecialNameList> nameLists = new ArrayList<>();
        String systemBusType = "";
        for (int i = 0; i < snl2.size(); i++) {
            systemBusType = snl2.get(i).getSystemBusType();
            for (int j = 0; j < systemBusType.length(); j++) {
                char sbt = systemBusType.charAt(j);
                if (sbt == '1') {
                    if (j == 0) {
                        //系统业务具体类型为：第一位是1 （标的设置）（1标的设置，2债转转让人，3债转承接人，4单标活动，5累投活动，6自主领奖活动，7手动颁奖活动，8.充值人，9正常会员，10体验会员，11奖品设置，12提低卷活动， ）
                        nameLists.add(snl2.get(i));
                    } else {
                        continue;
                    }
                }
            }
        }
        return nameLists;
    }

    /**
     * 封装标
     *
     * @author :liuqh
     * @date 2017/6/23 19:54
     */
    private TenderItem handleTenderItem(String id) {
        //借款申请对象
        loanapp loanapp = loanappService.selectByPrimaryKey(new BigDecimal(id));
        Short loantype = loanapp.getLoantype();
        TenderItem tenderItem = new TenderItem();
        tenderItem.setTpro(loantype);//标类型
        tenderItem.setTname(loanapp.getLoanname());//借款名称
        tenderItem.setLoanappid(loanapp.getId());// 借款申请ID
        tenderItem.setTamount(loanapp.getLoanamount());// 借款金额
        tenderItem.setTamountstr(new DecimalFormat("#,###.00").format(loanapp.getLoanamount()));

        tenderItem.setLoantime(Integer.parseInt(loanapp.getAppday().toString()));// 借款期限
        tenderItem.setDayormonth(loanapp.getUnit());// 单位
        tenderItem.setRepaymentpro(loanapp.getRepaymenttype());// 还款方式(等额本息，等额本金，先息后本，一次性还本付息)
        tenderItem.setIsappointtender(loanapp.getIsappointtender());// 是否为约标


        UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(loanapp.getBaseid());
        String loginname = userBaseAccountInfo.getLoginname();
        loginname = AES.getDecrypt(loginname);
        tenderItem.setLoginname(loginname);//登陆名
        String realname = userBaseAccountInfo.getRealname();
        realname = AES.getDecrypt(realname);//用户名称（真实姓名）
        tenderItem.setRepayman(realname);//指定还款人

        return tenderItem;
    }


    // 新增
    @RequestMapping(value = "/insertTenderItem", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insertTenderItem(TenderItem tenderItem, String[] allowcugrades, String[] ugrestricts, AheadRepayMode aheadRepayMode) throws Exception {
        //处理允许投标的会员等级
        handleUgrestrict(tenderItem, ugrestricts);
        //处理允许撤回的会员等级
        handleAllowGrade(tenderItem, allowcugrades);
        tenderItem.setTno(StringUtil.buildNo(Marknumber.T_NO));//生成标号
        tenderItem.setFinishtamount((double) 0);//初始化入账金额
        ModelAndView modelAndView = new ModelAndView();
        tenderItem.setTstatus(TenderItem_Constant.T1);// 待录入(没在徽商录入标)
        tenderItem.setOnanddown(TenderItem_Constant.ONANDDOWN_0);//标上架状态（0待上架)
        tenderItem.setIsrecorded((short) 0);//已经发送徽商系统录入（0否，1是)
        tenderItem.setAddtime(new Date());
        tenderItem.setTinterest(Arith.div(tenderItem.getTinterest(),100));
        //处理提前还款方式设置对象
        if(tenderItem.getIsaaheadrepay()==1){
            handleAheadRepayMode(aheadRepayMode);
        }else if(tenderItem.getIsaaheadrepay()==0){
            aheadRepayMode=null;
        }
        //保存新标，更改借款申请的状态，保存提前还款方式设置，同时往单标前端信息表插入
        int i = tenderItemService.insertItemAndOther(tenderItem, aheadRepayMode);
        if (i > 0) {
            //转跳到审核页面
            modelAndView.setViewName("redirect:/admin/tenderItem/selectTenderItemByaudit.action");
        } else {
            //跳回新增页面
            modelAndView.addObject("result", "fail");
            modelAndView.setViewName("redirect:/admin/tenderItem/insert_TenderItem_Ui.action?id="+tenderItem.getLoanappid());
        }
        return modelAndView;
    }

    /**
     * 处理提前还款方式设置对象
     *
     * @author :liuqh
     * @date 2017/6/25 9:29
     */
    private void handleAheadRepayMode(AheadRepayMode aheadRepayMode) {
        //具体期数,如果这个不为空，说明是多期提前，就要set具体期数
        if (aheadRepayMode.getAperiodqi() != null) {
            aheadRepayMode.setAperiods(aheadRepayMode.getAperiodqi());
        }
        //先息后本
        if (aheadRepayMode.getRepaytype().toString().equals("4")) {
            if (aheadRepayMode.getArepaymode().toString().equals("2")) {//部分提前
                aheadRepayMode.setIntmode(aheadRepayMode.getIntmodequane());//全额利息
            }
        } else {
            if (aheadRepayMode.getAperiods() != null && !aheadRepayMode.getAperiods().equals("")) {
                //当期提前
                if (aheadRepayMode.getAperiods().toString().equals("1")) {
                    aheadRepayMode.setIntmode(aheadRepayMode.getIntmodetian());//占天利息
                //多期提前
                } else {
                    aheadRepayMode.setIntmode(aheadRepayMode.getIntmodequane());//全额利息
                }
            }
        }

        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            aheadRepayMode.setAddman(adminUser.getUsername());//添加人
        }
        aheadRepayMode.setAddtime(new Date());//添加时间
    }

    /**
     * 处理允许撤回的会员等级
     *
     * @author :liuqh
     * @date 2017/6/22 9:46
     */
    private void handleAllowGrade(TenderItem tenderItem, String[] allowcugrades) {
        List<UserGrade> userGrades = userGradeService.getAll(null);//查询全部会员等级
        String allowcugrade = tenderItem.getAllowcugrade();//定义一个字符存储允许撤回选择的等级
        if (allowcugrade != null && !allowcugrade.equals("")) {//判断是否为空
            if (allowcugrade.equals("1")) {//全部等级
                allowcugrade = StringUtil.getPlaceholder(51);//生成长度为51位0的字符串0000000000000...
                for (UserGrade grade : userGrades) {
                    allowcugrade = StringUtil.setPlaceholder(allowcugrade, grade.getUgrade().intValue());//转换成对应字符串
                }
            }
            if (allowcugrade.equals("2")) {//部分等级
                if (allowcugrades != null) {//判断可撤回数组是否为空
                    allowcugrade = StringUtil.setPlaceholderForArr1(allowcugrades, 51);//装换成对应字符串
                }
            }
            tenderItem.setAllowcugrade(allowcugrade);//重新赋值的可撤回等级
        }
    }

    /**
     * 处理允许投标的会员等级
     *
     * @author :liuqh
     * @date 2017/6/22 9:46
     */
    private void handleUgrestrict(TenderItem tenderItem, String[] ugrestricts) {
        List<UserGrade> userGrades = userGradeService.getAll(null);//查询全部会员等级
        String ugrestrict = tenderItem.getUgrestrict();//定义一个字符存储允许撤回选择的等级
        if (ugrestrict != null && !ugrestrict.equals("")) {//判断是否为空
            if (ugrestrict.equals("1")) {//全部等级
                ugrestrict = StringUtil.getPlaceholder(51);//生成长度为51位0的字符串0000000000000...
                for (UserGrade grade : userGrades) {
                    ugrestrict = StringUtil.setPlaceholder(ugrestrict, grade.getUgrade().intValue());//转换成对应字符串
                }
            }
            if (ugrestrict.equals("2")) {//部分等级
                if (ugrestricts != null) {//判断可撤回数组是否为空
                    ugrestrict = StringUtil.setPlaceholderForArr1(ugrestricts, 51);//装换成对应字符串
                }
            }
            tenderItem.setUgrestrict(ugrestrict);//重新赋值允许投标的会员等级
        }
    }

    // 修改UI
    @RequestMapping(value = "/toUpdateUi", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toUpdateUi(String id) {
        TenderItem tenderItem = new TenderItem();
        tenderItem.setId(new BigDecimal(id));
        List<TenderItem> tenderItems = tenderItemService.selectByCondition(tenderItem);
        TenderItem tenderItem1 = null;
        if (tenderItems.size() > 0) {
            tenderItem1 = tenderItems.get(0);
        }
        tenderItem1.setTamountstr(new DecimalFormat("#,###.00").format(tenderItem1.getTamount()));
        double interest = Arith.mul(tenderItem1.getTinterest(), 100);
        tenderItem1.setTinterest(interest);
        ModelAndView modelAndView = new ModelAndView();
        // 允许投标的会员等级
        String ugrestrict = tenderItem1.getUgrestrict();
        PublicUtil.decorateGrade(modelAndView, userGradeService, ugrestrict, "ugrestrict", "ugrestrict1");
        //获取：用户等级
        List<UserGrade> uGrades = userGradeService.selectiveForNormal(null);
        modelAndView.addObject("uGrades", uGrades);
        //封装定向名单
        List<SpecialNameList> nameLists = handleSpecial();
        if (nameLists.size() > 0) {
            modelAndView.addObject("snl2", nameLists);//获取定向名单
        }
        //还款资金方式
        modelAndView.addObject("repaymentpromaps", TenderItem_Constant.REPAYMENTPRO_MAP);
        //登陆名
        BigDecimal loanappid = tenderItem1.getLoanappid();
        loanapp loanapp = loanappService.selectByPrimaryKey(loanappid);
        UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(loanapp.getBaseid());
        String loginname = userBaseAccountInfo.getLoginname();
        loginname = AES.getDecrypt(loginname);
        tenderItem1.setLoginname(loginname);//登陆名

        String realname = userBaseAccountInfo.getRealname();
        realname = AES.getDecrypt(realname);//用户名称（真实姓名）
        tenderItem1.setRepayman(realname);//指定还款人

        //封装担保人名单
        Map<String, String> compensatorymans = handleCompensatoryman();
        modelAndView.addObject("compensatorymans", compensatorymans);
        //得到小名单
        List<RemoveName> selectNameAndNameNo = removeNameService.selectNameAndNameNo();
        modelAndView.addObject("selectNameAndNameNo", selectNameAndNameNo);
        modelAndView.addObject("tenderItem", tenderItem1);
        modelAndView.addObject("tenderItem_tstatus", TenderItem_Constant.TSTATUS_MAP);
        modelAndView.setViewName("admin/tenderItem/TenderItem_Update");
        return modelAndView;
    }

    // 修改
    @RequestMapping(value = "/updateTenderItem", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateTenderItem(TenderItem tenderItem, String[] allowcugrades, String[] ugrestricts, AheadRepayMode aheadRepayMode)throws Exception {
        //处理允许投标的会员等级
        handleUgrestrict(tenderItem, ugrestricts);
        //处理允许撤回的会员等级
        handleAllowGrade(tenderItem, allowcugrades);
        tenderItem.setTno(StringUtil.buildNo(Marknumber.T_NO));//生成标号
        tenderItem.setFinishtamount((double) 0);//初始化入账金额
        ModelAndView modelAndView = new ModelAndView();
        tenderItem.setOnanddown(TenderItem_Constant.ONANDDOWN_0);//标上架状态（0待上架)
        tenderItem.setIsrecorded((short) 0);//已经发送徽商系统录入（0否，1是)
        tenderItem.setAddtime(new Date());
        tenderItem.setTinterest(Arith.div(tenderItem.getTinterest(),100));
        //处理提前还款方式设置对象
        if(tenderItem.getIsaaheadrepay()==1){
            handleAheadRepayMode(aheadRepayMode);
            aheadRepayMode.setTid(tenderItem.getId());
            BigDecimal id = tenderItem.getAheadRepayMode().getId();
            if(id!=null){
                aheadRepayMode.setId(id);
            }else{
                aheadRepayMode.setId(null);
            }

        }
        //保存新标，更改借款申请的状态，保存提前还款方式设置，同时往单标前端信息表插入
        int i = tenderItemService.updateItemAndOther(tenderItem, aheadRepayMode);
        if (i > 0) {
            //转跳到标管理页面
            modelAndView.setViewName("redirect:/admin/tenderItem/selectTenderItemByCondition.action");
        } else {
            //跳回修改页面
            modelAndView.addObject("result", "fail");
            modelAndView.setViewName("redirect:/admin/tenderItem/toUpdateUi.action?id="+tenderItem.getId());
        }
        return modelAndView;
    }

    // 详细页面
    @RequestMapping(value = "/selectTenderItemByPrimaryKey", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectTenderItemByPrimaryKey(String id) {
        TenderItem tenderItem = new TenderItem();
        tenderItem.setId(new BigDecimal(id));
        List<TenderItem> tenderItems = tenderItemService.selectByConditionAndDecorateUgrade(tenderItem);
        TenderItem tenderItem1 = null;
        ModelAndView modelAndView = new ModelAndView();
        if (tenderItems.size() > 0) {
            tenderItem1 = tenderItems.get(0);
            //标类型
            LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteService.selectBySerialNo(new BigDecimal(tenderItem1.getTpro()));
            modelAndView.addObject("tproStr", loanTypeObjectQuote.getObjectname());
            //标状态
            Short key1 = tenderItem1.getTstatus();
            String tstatus = TenderItem_Constant.TSTATUS_MAP.get(key1);
            modelAndView.addObject("tstatus", tstatus);
            //标上架状态
            Short key2 = tenderItem1.getOnanddown();
            String onanddown = TenderItem_Constant.ONANDDOWN_MAP.get(key2);
            modelAndView.addObject("onanddown", onanddown);
            //金额
            Double tamount = tenderItem1.getTamount();
            String tamountstr = new DecimalFormat("#,###.00").format(tamount);
            double interest = Arith.mul(tenderItem1.getTinterest(), 100);
            tenderItem1.setTinterest(interest);
            tenderItem1.setTamountstr(tamountstr);
        }

        modelAndView.addObject("tenderItem", tenderItem1);
        modelAndView.setViewName("admin/tenderItem/TenderItem_Detail");
        return modelAndView;
    }

    // 删除标的信息
    @RequestMapping(value = "/deleteTenderItem", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteTenderItem(BigDecimal id) {
        if (id != null) {
            tenderItemService.delete(id);
        }
        return "redirect:/admin/tenderItem/selectTenderItemByCondition.action";
    }


    //标审核通过（徽商标录入）
    @RequestMapping(value = "through", method = {RequestMethod.POST, RequestMethod.GET})
    public String through(BigDecimal id, String operate) throws Exception {
        if (id != null) {
            TenderItem item = tenderItemService.findTenderItemById(id);
            loanapp loanapp = loanappService.selectByPrimaryKey(item.getLoanappid());
            String str;
            HashMap<String, String> result = new HashMap<>();
            //放弃录入
            if (operate != null && "0".equals(operate)) {
                item.setTstatus(TenderItem_Constant.T11);//录入放弃
                loanapp.setAppstatus(Loanapp_Constant.T14);
                tenderItemService.updateStatusForItemAndLoan(item,loanapp);
                result.put("result", "fangqi");
                str = JSON.toJSONString(result);
                sendJsonData(response, str);
                return null;
            } else if (operate != null && "1".equals(operate)) {
                //过了投标期
                if (item.getTendtime().getTime() < new Date().getTime()) {
                    item.setTstatus(TenderItem_Constant.T10);//录入过期
                    loanapp.setAppstatus(Loanapp_Constant.T13);
                    tenderItemService.updateStatusForItemAndLoan(item,loanapp);
                    result.put("result", "timeout");
                    str = JSON.toJSONString(result);
                    sendJsonData(response, str);
                    return null;
                }
                loanapp loanapp1 = loanappService.selectByPrimaryKey(item.getLoanappid());
                UserFsAccountInfo accountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(loanapp1.getBaseid());
                String usrcustid = accountInfo.getUsrcustid();
                usrcustid = AES.getDecrypt(usrcustid);
                Short repaymentpro = item.getRepaymentpro();
                //计息方式的转换
                if (1 == repaymentpro) {
                    repaymentpro = 0;
                } else {
                    repaymentpro = 2;
                }
                Integer loantime = item.getLoantime();
                String dayormonth = item.getDayormonth();
                //月或年转换成天，因为徽商那边要求传天
                if ("月".equals(dayormonth)) {
                    loantime *= 30;
                } else if ("年".equals(dayormonth)) {
                    loantime *= 365;
                }
                Double tinterest = item.getTinterest();
                tinterest *= 100;
                String state = test5846(item.getTno(), item.getTdesc(), item.getTamount().toString(), repaymentpro.toString(), loantime.toString(),item.getCompensatoryman(), tinterest.toString() + "0000", usrcustid);
                //录入成功
                if ("1".equals(state)) {
                    //标的开始时间
                    Date tbegintime = item.getTbegintime();
                    Date date = new Date();
                    //还没到投标期
                    if (date.getTime() < tbegintime.getTime()) {
                        item.setTstatus(TenderItem_Constant.T2);//待投标
                        loanapp.setAppstatus(Loanapp_Constant.T5);//待投标
                        //在投标期中
                    } else if (item.getTendtime().getTime() > date.getTime() && date.getTime() > tbegintime.getTime()) {
                        item.setTstatus(TenderItem_Constant.T3);//投标中
                        item.setOnanddown((short) 1);//上架
                        item.setPutontime(new Date());
                        loanapp.setAppstatus(Loanapp_Constant.T6);//投标中
                        //过期了
                    } else if (item.getTendtime().getTime() < date.getTime()) {
                        item.setTstatus(TenderItem_Constant.T4);//已流标
                        item.setOnanddown((short) 2);//下架
                        item.setPullofftime(new Date());
                        loanapp.setAppstatus(Loanapp_Constant.T7);//已流标
                    }
                    item.setIsrecorded((short) 1);//已经发送徽商系统录入（0否，1是)
                    tenderItemService.updateStatusForItemAndLoan(item,loanapp);
                    result.put("result", "success");
                    str = JSON.toJSONString(result);
                    sendJsonData(response, str);
                    return null;
                } else {
                    item.setTstatus(TenderItem_Constant.T9);//录入失败
                    loanapp.setAppstatus(Loanapp_Constant.T12);//录入失败
                    tenderItemService.updateStatusForItemAndLoan(item,loanapp);
                    result.put("result", "fail");
                    str = JSON.toJSONString(result);
                    sendJsonData(response, str);
                    return null;
                }

            }
        }
        return null;
    }

    //上架前判断
    @RequestMapping(value = "putawayBefore", method = {RequestMethod.POST})
    public String putawayBefore(String id) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(id));
        Short tstatus = tenderItem.getTstatus();
        //标状态为已流标(已流标的标不能再上架)
        if (tstatus == 4) {
            hashMap.put("result", "tstatus_4");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //根据标编号查此标的单标前端信息总数
        TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
        tenderFrontEndSingle.setTno(tenderItem.getTno());
        List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition(tenderFrontEndSingle);
        int total = tenderFrontEndSingles.size();
        //根据标编号查此标的单标前端信息已填数量
        List<TenderFrontEndSingle> tenderFrontEndSinglesFilleds = tenderFrontEndSingleService.selectContentIsNotNull(tenderItem.getTno());
        int filleds = tenderFrontEndSinglesFilleds.size();
        //标的单标前端信息未填数量
        int unfilleds = total - filleds;
        if(unfilleds>0){
            hashMap.put("result", unfilleds);
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            hashMap.put("result", 0);
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    //上架
    @RequestMapping(value = "shelves", method = {RequestMethod.POST})
    public String shelves(String id) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(id));
        tenderItem.setOnanddown((short) 1);//将标的状态改成上架状态
        tenderItem.setPutontime(new Date());//上架时间
        int i = tenderItemService.update(tenderItem);
        if (i > 0) {
            hashMap.put("result", "shelves_success");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            hashMap.put("result", "shelves_lose");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    //下架
    @RequestMapping(value = "theshelves", method = {RequestMethod.POST})
    public void theshelves(BigDecimal id, HttpServletResponse response) throws IOException {
        String data = "fail";
        if (id != null) {
            TenderItem item = new TenderItem();
            item.setId(id);
            item.setOnanddown((short) 2);//将标的状态改成下架状态
            item.setPullofftime(new Date());//下架时间
            int i = tenderItemService.update(item);
            if (i > 0) {
                data = "succ";
            }
        }
        PublicUtil.sendJsonData(response, JSON.toJSONString(data));
    }

    //流标
    @RequestMapping(value = "flowstandard", method = {RequestMethod.GET, RequestMethod.POST})
    public String flowstandard(String id) {
        Map<String, String> hashMap = new HashMap<String, String>();
        if (StringUtil.isEmpty(id)) {
            hashMap.put("result", "id_null");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(id));//通过id获取标的信息
        Short onanddown = tenderItem.getOnanddown();
        //上架状态为已上架
        if (onanddown == 1) {
            hashMap.put("result", "onanddown_on");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        List<TenderItem> tenderItemList = new ArrayList<TenderItem>();
        tenderItemList.add(tenderItem);
        handleFailItem(tenderItemList);
        Short isfroze = tenderItem.getIsfroze();
        //有投标资金被放款或被冻结中
        if (isfroze == 1) {
            hashMap.put("result", "fail");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        if (isfroze == 0) {
            Date pullofftime = tenderItem.getPullofftime();//标的下架时间
            Date date = new Date();//当期时间
            long timer = (60 * 30 * 1000);//30分钟
            //下架时间没有超过30分钟，不能流标,因为在这30分钟内，如果没有一笔投标已放款，只是冻结中的话，可以调用撤销接口撤销
            if ((date.getTime() - pullofftime.getTime()) < timer) {
                hashMap.put("result", "pullofftime_error");
                String str = JSON.toJSONString(hashMap);
                try {
                    StringUtil.sendJsonData(response, str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }


        tenderItem.setTstatus(TenderItem_Constant.T4);//修改为流标
        int i = tenderItemService.update(tenderItem);
        if (i > 0) {
            hashMap.put("result", "flows_success");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            hashMap.put("result", "flows_lose");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * @param tenderItemId 参数说明 标的ID
     * @return boolean    返回类型
     * 返回ture 生成还款计划成功
     * 返回false 生成还款计划失败
     * @throws
     * @throws
     * @Title: AutoGenerateDividedPayments
     * @Description: TODO(自动生成 标的分期还款计划 （借款人的还款计划表） )
     * @author cjm
     */
    public boolean AutoGeneratechagne(BigDecimal tenderItemId) {
        boolean falg = false;
        if (tenderItemId == null) {
            return falg;
        }

        TenderItem tenderItem = tenderItemService.findTenderItemById(tenderItemId);//标的信息
        if (tenderItem == null) {
            return falg;
        }

        DividedPayments dividedPayments33 = new DividedPayments();
        dividedPayments33.setTenderid(tenderItemId);
        List<DividedPayments> dividedPayments34 = dividedPaymentsService.findDividedPaymentss(dividedPayments33);
        if (dividedPayments34.size() > 0) {//已经生成过标的还款计划了
            return falg;//生成失败
        } else {
            falg = true;//生成成功
            return falg;
        }
    }

    //生成还款计划
    @RequestMapping(value = "/ManuallyGeneratedRepayMent", method = RequestMethod.POST)
    public String ManuallyGeneratedRepayMent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> hashMap = new HashMap<String, String>();
        String opid = request.getParameter("opid");//标ID
        if (StringUtil.isEmpty(opid)) {//标的ID为null
            hashMap.put("result", "opid_null");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(opid));
        if (tenderItem.getOnanddown().equals(2)) {//标的状态是下架状态、
            Date pullofftime = tenderItem.getPullofftime();//标的下架时间
            Date date = new Date();//当期时间
            long timer = (60 * 30 * 1000);//30分钟
            //long timer = (0);//30分钟
            if ((date.getTime() - pullofftime.getTime()) < timer) {//下架时间没有超过30分钟，不能生成还款计划
                hashMap.put("result", "pullofftime_error");
                String str = JSON.toJSONString(hashMap);
                try {
                    StringUtil.sendJsonData(response, str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }

		/*必须满足标的状态是  (满标or已下架or已过期   ) 才可以生成还款计划 */
       /* if (!(tenderItem.getTstatus().equals((short) 5)
                || tenderItem.getTstatus().equals((short) 8)
                || tenderItem.getTstatus().equals((short) 9))) {
            hashMap.put("result", "tstatus_error");//标的状态没有满足满标or已下架or已过期，不能生成还款计划
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }*/

        //判断是否还有待审核的投标
       /* boolean falg = AutoGenerateRepayMentUtil.checkGenerateRepay(tenderItem.getId());
        if (!falg) {//还有待审核的投标 不能生成还款计划
            hashMap.put("result", "audit_error");
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }*/


        //生成借款人具体还款计划
        boolean fala = AutoGenerateRepayMentUtil.AutoGenerateDividedPayments(tenderItem.getId());
        if (fala) {
            AutoGenerateRepayMentUtil.AutoGenerateRepayMents(tenderItem.getId());//生成投资人具体还款计划
            tenderItem.setTstatus(TenderItem_Constant.T7);//更改标的状态为 还款中
            loanapp loanapp = loanappService.selectByPrimaryKey(tenderItem.getLoanappid());
            loanapp.setAppstatus(Loanapp_Constant.T10);//还款中
            tenderItemService.updateStatusForItemAndLoan(tenderItem,loanapp);
            hashMap.put("result", "success"); //生成还款计划成功
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            hashMap.put("result", "fail"); //生成还款计划失败
            String str = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    /**
     * 发标
     *
     * @return
     */
    @RequestMapping(value = "/tender", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView tender() {
        TenderItem tenderItem = new TenderItem();
        tenderItem.setId(new BigDecimal("701"));
        List<TenderItem> tenderItems = tenderItemService.selectByConditionAndDecorateUgrade(tenderItem);
        TenderItem tenderItem1 = null;
        if (tenderItems.size() > 0) {
            tenderItem1 = tenderItems.get(0);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tenderItem", tenderItem1);
        modelAndView.setViewName("admin/tenderItem/tender");
        return modelAndView;
    }

    @RequestMapping(value = "ItemDetailDesc", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView det() {
        return new ModelAndView("admin/tenderItem/ItemDetailDesc");
    }

    //标的详情添加
    @RequestMapping(value = "additemdetaildesc", method = {RequestMethod.POST})
    public ModelAndView Additemdetaildesc(ItemDetailDesc detailDesc, HttpServletRequest request) {
        //String id=request.getParameter("id");
        detailDesc.setTid(new BigDecimal("1203"));
        List<ItemDetailDesc> descs = detailDesc.getCategorynames();//项目介绍-信息名称
        List<ItemDetailDesc> descs3 = detailDesc.getCategorynameDetail();//项目详情-信息名称
        List<ItemDetailDesc> descs2 = detailDesc.getItemDetailDescs();//(企业信息)
        List<ItemDetailDesc> moduletypes = detailDesc.getModuletypes();//模块类别
        List<ItemDetailDesc> infotypes = detailDesc.getInfotypes();//信息类型
        for (int i = 0; i < descs.size(); i++) {//项目介绍
            detailDesc.setModuletype(moduletypes.get(0).getModuletype());//模块类别
            detailDesc.setCategoryname(descs.get(i).getCategoryname());//信息名称
            detailDesc.setCategorydetail(descs.get(i).getCategorydetail());//详细信息
            itemDetailDescService.insertSelective(detailDesc);
        }
        for (int i = 0; i < descs3.size(); i++) {//项目详情(基础描述)
            detailDesc.setModuletype(moduletypes.get(1).getModuletype());
            detailDesc.setCategoryname(descs3.get(i).getCategoryname());//信息名称
            detailDesc.setCategorydetail(descs3.get(i).getCategorydetail());//详细信息
            detailDesc.setInfotype(infotypes.get(0).getInfotype());//信息类型
            detailDesc.setInfoserialno(1);//序号
            itemDetailDescService.insertSelective(detailDesc);
        }
        for (int i = 0; i < descs2.size(); i++) {//企业信息
            detailDesc.setModuletype(moduletypes.get(1).getModuletype());
            detailDesc.setCategoryname(descs2.get(i).getCategoryname());//信息名称
            detailDesc.setCategorydetail(descs2.get(i).getCategorydetail());//详细信息
            detailDesc.setInfotype(infotypes.get(1).getInfotype());//信息类型
            detailDesc.setInfoserialno(2);//序号
            itemDetailDescService.insertSelective(detailDesc);
        }
        return new ModelAndView("redirect:/admin/tenderItem/selectTenderItemByCondition.action");

    }

    //分期发标(判断输入金额是否超标)
    @RequestMapping(value = "amount", method = {RequestMethod.GET, RequestMethod.POST})
    public void amount(BigDecimal loanid, Double nowmoney, HttpServletResponse response) throws IOException {
        String data = "fail";
        if (loanid != null && nowmoney != null) {
            loanapp loanapp = loanappService.selectByPrimaryKey(loanid);//现根据id查询该借款总金额
            Double tamount = tenderItemService.selectByloanid(loanid);//查询已投总金额
            if (tamount != null) {
                double allmoney = nowmoney + tamount;//输入金额+已投总金额,现投金额
                if (allmoney <= loanapp.getLoanamount()) {//如果现投金额小于借款金额(说明可以继续发标)
                    data = "succ";
                } else {//若果现投金额大于借款金额，则无法发标
                }
            } else {//说明是第一次发标
                data = "succ";
            }
            PublicUtil.sendJsonData(response, JSON.toJSONString(data));
        }
    }

    //查看已发金额(针对分期发标的)
    @RequestMapping(value = "alreadymoney", method = {RequestMethod.GET, RequestMethod.POST})
    public void alreadymoney(BigDecimal loanid, HttpServletResponse response) throws IOException {
        if (loanid != null) {
            Double tamount = tenderItemService.selectByloanid(loanid);//查询已发总金额
            if (tamount != null) {//若果已投金额为null,则已投金额为0
                PublicUtil.sendJsonData(response, "已发金额" + tamount);
            } else {
                PublicUtil.sendJsonData(response, "已发金额为:0.00");
            }
        }
    }

    /**
     * @param biaoHao      标号
     * @param biaoDec      标的描述
     * @param money        借款金额
     * @param repaymentpro 还款方式
     * @param loantime     项目期限
     * @param yield        预期年化收益率
     * @return
     * @throws Exception
     */

    public String test5846(String biaoHao, String biaoDec, String money, String repaymentpro, String loantime,String compensatoryman, String yield, String cardnbr) throws Exception {
        Date date = new Date();
        String TRXDATE = StringUtil.formatDate(date, "yyyyMMdd");//交易日期	TRXDATE
        String TRXTIME = StringUtil.formatDate(date, "HHmmss");//交易时间	TRXTIME
        String SEQNO = StringUtil.getNo();//交易流水号:年月日时分秒生成的随机数
        LinkedHashMap reqMap = new LinkedHashMap();
        //1-11域请求报文头
        reqMap.put("TRXCODE", "5846");                  //交易代码	    TRXCODE       N   M  4位交易代码
        reqMap.put("BANKCODE", HSignUtil.BANKCODE);     //银行代码	    BANKCODE      N   M  30040000-徽商银行  30050000-南昌银行
        reqMap.put("TRXDATE", TRXDATE);                 //交易日期	    TRXDATE       N   M  YYYYMMDD
        reqMap.put("TRXTIME", TRXTIME);                 //交易时间	    TRXTIME       N   M  hhmmss
        reqMap.put("COINSTCODE", HSignUtil.COINSTCODE); //合作单位编号	COINSTCODE    A   M  银联数据分配  800114
        reqMap.put("COINSTCHANNEL", HSignUtil.COINSTCHANNEL_WEB);          //合作单位渠道	COINSTCHANNEL A   M  见下方《附录1》
        reqMap.put("SEQNO", SEQNO);                     //交易流水号	SEQNO         N   M  8 ~ 20 位数字流水号

        reqMap.put("SOURCE", "A0");                     //ESB内部渠道	SOURCE        A  M  银联数据分配
        reqMap.put("RETCODE", "");                      //应答码	    RETCODE       A     填空
        reqMap.put("RETMSG", "");                       //应答码描述	RETMSG        A     填空
        reqMap.put("HEADRESERVED", "");                 //报文头保留域	HEADRESERVED  A  C  可选

        //请求所需要的参数
        reqMap.put("FUPROD", biaoHao);                     //标的编号          A 6     M  由产品的发行方定义；需保证唯一性
        reqMap.put("PROD_DESC", biaoDec);              //产品描述          A 60    M  理财产品中文描述
        reqMap.put("CARDNBR", cardnbr);       //借款人电子账号    A 19    M
        reqMap.put("AMOUNT", money);                   //借款金额          N 13,2  M  两位小数
        reqMap.put("INTTYPE", repaymentpro);                         //付息方式          N 1     M  0：到期与本金一起归还；1：每月固定日期支付；2：每月不确定日期支付；
        if ("1".equals(repaymentpro)) {
            //我定为每月20号还
            reqMap.put("INTPAYDAY", "20");                        //利息每月支付日    N 2     C  DD付息方式为1时必填；若设置日期大于月份最后一天时，则为该月最后一天支付；
        }
        reqMap.put("LOAN_TERM", loantime);                      //项目期限          N 4     M  单位为天
        reqMap.put("YIELD", yield);                     //预期年化收益率    N 8,5   M  五位小数，如年化收益率为10%，需上送1,000,000
        if(compensatoryman!=null&&!"".equals(compensatoryman)){
            reqMap.put("CARDNBR_SU",compensatoryman);//担保人电子账号  C
        }

        //响应报文参数如下：  用于与1-11域响应报文头拼接的响应报文参数名：用于验签
        ArrayList<String> resColumnList = new ArrayList();
        resColumnList.add("FUPROD");        //标的编号              A  6     C   同请求
        resColumnList.add("PROD_DESC");     //产品描述              A  60    C   同请求
        resColumnList.add("CARDNBR");       //借款人电子账号        A  19    C   同请求
        resColumnList.add("NAME");          //借款人姓名            A  60    C   借款人为企业机构时返回企业名称
        resColumnList.add("AMOUNT");        //借款金额              N  13,2  C   同请求
        resColumnList.add("LOAN_TERM");     //项目期限              N  4     C   同请求
        resColumnList.add("INPDATE");       //发标日期              N  8     C   CCYYMMDD  标的信息录入时的系统日期
        resColumnList.add("STATE");         //记录状态              N  1     C   1：已发标    2：已投标   9：已撤销
        if(compensatoryman!=null&&!"".equals(compensatoryman)){
            resColumnList.add("CARDNBR_SU");//担保人电子账号  C
            resColumnList.add("NAME_SU");//担保人姓名  C
        }
        String result = testCommon2(reqMap, resColumnList, "5846");
        return result;
    }

    public String testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList, String code) throws Exception {
        String result = testCommon2(reqMap, resColumnList, code, "v20160602");//v20161215/v20160602
        return result;
    }

    //公共方法调用2
    public String testCommon2(LinkedHashMap reqMap, ArrayList<String> resColumnList, String code, String version) throws Exception {


        Map map = new HashMap();
        map.put("BANKCODE", reqMap.get("BANKCODE"));
        map.put("COINSTCODE", reqMap.get("COINSTCODE"));
        map.put("APIVERSION", version);

        //发到路由through
        String result1 = HSignUtil.HttpClientTask(reqMap, version);
        if(result1.contains("请求参数非法")){
            return "0";
        }

        return callBack(resColumnList, result1);
    }

    /**
     * 处理路由返回一结果
     *
     * @param resColumnList
     * @param result1
     * @return
     * @throws Exception
     */
    private String callBack(ArrayList<String> resColumnList, String result1) throws Exception {
        Map jsonMap = JSONObject.fromObject(result1);
        String resultData = jsonMap.get("responseData").toString();

        //解密调整
        String decryptKeyPath = decryptPath;    //拼接解密私钥路径
        String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
        String result = RSAUtils.decryptRSAs(resultData, decryptKey4Server);//对请求数据解密
        System.out.println("解密成功..........");
        System.out.println("解密后的内容：" + result);


        HashMap responseMap = (HashMap) parseJSON2Map(result);
        System.out.println("解密后的内容转为map格式后内容如下：" + responseMap);
        StringBuffer responseMapMerged = new StringBuffer();
        ArrayList headColumn = new ArrayList();

        //1-11域响应报文头
        headColumn.add("TRXCODE");          //交易代码      M  与请求一致
        headColumn.add("BANKCODE");         //银行代码      M  与请求一致
        headColumn.add("TRXDATE");          //交易日期      M  与请求一致
        headColumn.add("TRXTIME");          //交易时间      M  与请求一致
        headColumn.add("COINSTCODE");       //合作单位编号  M  与请求一致
        headColumn.add("COINSTCHANNEL");    //合作单位渠道  M  与请求一致
        headColumn.add("SEQNO");            //交易流水号    M  与请求一致

        headColumn.add("SOURCE");           //ESB内部渠道	M  与请求一致
        headColumn.add("RETCODE");          //应答码	    M
        headColumn.add("RETMSG");           //应答码描述	C
        headColumn.add("HEADRESERVED");     //报文头保留域	C

        resColumnList.addAll(0, headColumn);
        int listLength = resColumnList.size();
        if (responseMap.get("RETCODE") == null) {
            System.out.println("操作失败:" + responseMap.get("RETMSG"));
            throw new ArrayIndexOutOfBoundsException("请求参数非法");
            //InvocationTargetException
        }
        JSONObject json = JSONObject.fromObject(responseMap);
        //for循环次数
        int forLen = 0;
        //如果是5849交易
        if ("5849".equals(responseMap.get(resColumnList.get(0)))) {
            forLen = listLength - 1;
        } else {
            forLen = listLength;
        }
        for (int i = 0; i < forLen; i++) {
            Object columnvalue = responseMap.get(resColumnList.get(i));
            if (columnvalue != null) {
                responseMapMerged.append(responseMap.get(resColumnList.get(i)));
            }
        }
        //验签
        String responseSign = (String) responseMap.get("SIGN");

        System.out.println("responseMerged: " + responseMapMerged.toString());

        boolean verifyResult = RSAEncryptUtil.MD5WithRSAVerify(responseMapMerged.toString().getBytes("UTF-8"), getVerifyKey4Client(signPublicPath), responseSign);
        System.out.println("验签结果：" + verifyResult);
        if (!verifyResult) {
            System.out.println("验证签名失败...");
            return null;
        } else {
            System.out.println("验证签名成功");
        }
        System.out.println("responseMap:" + responseMap);
        if ("00000000".equals(responseMap.get("RETCODE"))) {
            System.out.println("操作成功");
            return responseMap.get("STATE").toString();
        } else {
            System.out.println("操作失败,错误代码：" + responseMap.get("RETCODE"));
        }
        return null;
    }


    /**
     * 根据私钥文件路径读取私钥
     *
     * @param
     * @return
     */
    public static String getSignPrivateKey4Client(String keyPath) {
        StringBuffer privateBuffer = new StringBuffer();
        try {
            //InputStream inputStream = Thread.currentThread().getContextClassLoader()
            //       .getResourceAsStream(signPrivatePath);
            FileInputStream fi = new FileInputStream(keyPath);
            InputStreamReader inputReader = new InputStreamReader(fi);
            BufferedReader bufferReader = new BufferedReader(inputReader);
            // 读取一行
            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                privateBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateBuffer.toString();
    }

    /**
     * 根据公钥文件路径读取私钥
     *
     * @param
     * @return
     */
    public static String getVerifyKey4Client(String keyPath) {
        String key = "";
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            //InputStream in = Thread.currentThread().getContextClassLoader()
            //      .getResourceAsStream(signPublicPath);
            FileInputStream fi = new FileInputStream(keyPath);
            //生成一个证书对象并使用从输入流 inStream 中读取的数据对它进行初始化。
            Certificate c = cf.generateCertificate(fi);
            PublicKey publicKey = c.getPublicKey();
            key = new BASE64Encoder().encodeBuffer(publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * json转map
     *
     * @param jsonStr 源数据
     *                <p>
     *                return map
     */
    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<JSONObject> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

}
