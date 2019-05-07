package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huishang.file.FileTransferDemoEncrypted;
import com.huishang.file.RSAUtils;
import com.huishang.util.HSignUtil;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.BacthFileRecord_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.huishang.model.FinTranResult;
import com.ptpl.model.AdminUser;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserMakeALoan;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.model.UserTender;
import com.ptpl.model.UserTenderPlusLink;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.GuaranteeFeeRecordServiceI;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.MediacyFeeRecordServiceI;
import com.ptpl.service.MediacyFeeServiceI;
import com.ptpl.service.RiskGuarantyFeeRecordServiceI;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.service.UserTenderPlusLinkServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.huishang.util.HSignUtil.FINTRAN;
import static com.huishang.util.HSignUtil.FUISSUER;

/**
 * @author zhenglm
 * @ClassName: UserTenderController
 * @Description: TODO(管理后台-投标记录)
 * @date 2016年12月8日 上午10:46:47
 */
@Controller
// 为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如：商品列表：/items/queryItems.action
@RequestMapping("/admin/tender")
public class UserTenderController extends BaseController {

    /**
     * 投标记录Service
     */
    @Autowired
    UserTenderServiceI userTenderService;

    /**
     * 投标放款记录service
     */
    @Autowired
    UserMakeALoanServiceI userMakeALoanService;

    /**
     * 标的设置Service
     */
    @Autowired
    TenderItemServiceI tenderItemService;

    /**
     * 用户托管账户信息Service
     */
    @Autowired
    UserFsAccountInfoServiceI userFsAccountInfoService;

    /**
     * 批量文件记录Service
     */
    @Autowired
    BacthFileRecordServiceI bacthFileRecordService;

    // 生成批次号
    @Autowired
    private OracleSequenceMaxValueIncrementer fintrannumber;

    /**
     * 用户账户service
     */
    @Autowired
    UserAccountServiceI userAccountService;

    /**
     * 账户收支记录service
     */
    @Autowired
    AccInExRecordServiceI accInExRecordService;

    /**
     * 标的居间费记录service
     */
    @Autowired
    MediacyFeeRecordServiceI mediacyFeeRecordService;

    /**
     * 标的担保费记录service
     */
    @Autowired
    GuaranteeFeeRecordServiceI guaranteeFeeRecordService;

    /**
     * 标的风险保证金记录service
     */
    @Autowired
    RiskGuarantyFeeRecordServiceI riskGuarantyFeeRecordService;

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
     * 借款申请记录Service
     */
    @Autowired
    loanappServiceI loanappService;

    /**
     * 投标增益使用关联Service
     */
    @Autowired
    UserTenderPlusLinkServiceI userTenderPlusLinkService;

    /**
     * 用户红包Service
     */
    @Autowired
    UserRedEnvelopeServiceI userRedEnvelopeService;

    /**
     * 用户使用券Service
     */
    @Autowired
    UserInterestRateCouponServiceI userInterestRateCouponService;

    /**
     * 条件分页查询投标记录
     *
     * @param usertender
     * @return ModelAndView
     * @throws Exception
     * @Title: queryBidRecordList
     * @Description: TODO(条件分页查询投标记录)
     */
    @RequestMapping(value = "/queryBidRecordList", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView queryBidRecordList(UserTender usertender) throws Exception {
        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> maps = new HashMap<String, Object>();
        initPage(maps, pageNum, pageSize);
        // 调用service层的方法得到对象列表
        if (usertender.getOutaccount() != null && StringUtils.isNotBlank(usertender.getOutaccount().getLoginname())) {
            //投资人
            usertender.getOutaccount().setLoginname(setEncrypt(usertender.getOutaccount().getLoginname()));
        }
        if (usertender.getInaccount() != null && StringUtils.isNotBlank(usertender.getInaccount().getLoginname())) {
            //借款人
            usertender.getInaccount().setLoginname(setEncrypt(usertender.getInaccount().getLoginname()));
        }
        List<UserTender> tenderList = userTenderService.findTenderList(usertender);
        for (UserTender u : tenderList) {
            u.setOutaccount(getDecryptionUserBaseAccountInfoDetail(u.getOutaccount()));
            u.setInaccount(getDecryptionUserBaseAccountInfoDetail(u.getInaccount()));
        }
        PageInfo<Object> pagehelper = initPagehelper(maps, tenderList);

        //需要解密
        if (usertender.getOutaccount() != null && StringUtils.isNotBlank(usertender.getOutaccount().getLoginname())) {
            //投资人
            usertender.getOutaccount().setLoginname(getDecrypt(usertender.getOutaccount().getLoginname()));
        }
        if (usertender.getInaccount() != null && StringUtils.isNotBlank(usertender.getInaccount().getLoginname())) {
            //借款人
            usertender.getInaccount().setLoginname(getDecrypt(usertender.getInaccount().getLoginname()));
        }
        // 返回ModelAndView
        ModelAndView mv = new ModelAndView();
        mv.addObject("pagehelper", pagehelper);
        mv.addObject("tproperty_map", TenderRecord_Constant.TPROPERTY_MAP); // 标的属性常量map
        mv.addObject("originclient_map", TenderRecord_Constant.ORIGINCLIENT_MAP); // 投标设备来源常量map
        mv.addObject("tstatus_map", TenderRecord_Constant.TSTATUS_MAP); // 投标的状态常量map
        mv.addObject("isblending_map", TenderRecord_Constant.ISBLENDING_MAP); // 是否系统勾兑常量map
        mv.addObject("ismanblending_map", TenderRecord_Constant.ISMANBLENDING_MAP); // 是否人工勾兑常量map
        mv.addObject("utproperty_map", TenderRecord_Constant.UTPROPERTY_MAP); // 投标属性常量map
        // 条件回显
        mv.addObject("echodata", usertender);
        mv.addObject("action", "queryBidRecordList.action");
        // 指定视图
        mv.setViewName("admin/usertender/usertenderList");
        return mv;
    }

    /**
     * 查询投标待审核记录
     *
     * @param usertender
     * @return ModelAndView
     * @throws Exception
     * @Title: queryBidRecordForAudit
     * @Description: TODO(查询投标待审核记录)
     */
    @RequestMapping(value = "/queryBidRecordForAudit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView queryBidRecordForAudit(UserTender usertender) throws Exception {
        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> maps = new HashMap<String, Object>();
        // 返回ModelAndView
        ModelAndView mv = new ModelAndView();
        // 根据标的号查询投标记录
        String tno = request.getParameter("tno");
        if (StringUtil.isNotEmpty(tno)) {
            TenderItem tenderItem = tenderItemService.seltendbytno(tno);
            usertender.setTenderid(tenderItem.getId());
        }
        usertender.setUtproperty(TenderRecord_Constant.UTPROPERTY_ORIGINAL);
        usertender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT);
        initPage(maps, pageNum, pageSize);
        if (usertender.getOutaccount() != null && StringUtils.isNotBlank(usertender.getOutaccount().getLoginname())) {
            usertender.getOutaccount().setLoginname(setEncrypt(usertender.getOutaccount().getLoginname()));
        }
        if (usertender.getInaccount() != null && StringUtils.isNotBlank(usertender.getInaccount().getLoginname())) {
            usertender.getInaccount().setLoginname(setEncrypt(usertender.getInaccount().getLoginname()));
        }
        // 调用service层的方法得到对象列表
        List<UserTender> tenderList = userTenderService.findTenderList(usertender);
        System.out.println(tenderList.size());
        for (UserTender u : tenderList) {
            u.setOutaccount(getDecryptionUserBaseAccountInfoDetail(u.getOutaccount()));
            u.setInaccount(getDecryptionUserBaseAccountInfoDetail(u.getInaccount()));
        }
        usertender.setTstatus(TenderRecord_Constant.TSTATUS_FAIL);
        List<UserTender> tenderList1 = userTenderService.findTenderList(usertender);
        System.out.println(tenderList1.size());
        PageInfo<Object> pagehelper = initPagehelper(maps, tenderList);

        if (usertender.getOutaccount() != null && StringUtils.isNotBlank(usertender.getOutaccount().getLoginname())) {
            usertender.getOutaccount().setLoginname(getDecrypt(usertender.getOutaccount().getLoginname()));
        }
        if (usertender.getInaccount() != null && StringUtils.isNotBlank(usertender.getInaccount().getLoginname())) {
            usertender.getInaccount().setLoginname(getDecrypt(usertender.getInaccount().getLoginname()));
        }
        mv.addObject("pagehelper", pagehelper);
        mv.addObject("tproperty_map", TenderRecord_Constant.TPROPERTY_MAP); // 标的属性常量map
        mv.addObject("originclient_map", TenderRecord_Constant.ORIGINCLIENT_MAP); // 投标设备来源常量map
        mv.addObject("tstatus_map", TenderRecord_Constant.TSTATUS_MAP); // 投标的状态常量map
        mv.addObject("isblending_map", TenderRecord_Constant.ISBLENDING_MAP); // 是否系统勾兑常量map
        mv.addObject("ismanblending_map", TenderRecord_Constant.ISMANBLENDING_MAP); // 是否人工勾兑常量map
        mv.addObject("utproperty_map", TenderRecord_Constant.UTPROPERTY_MAP); // 投标属性常量map
        // 条件回显
        mv.addObject("echodata", usertender);
        mv.addObject("action", "queryBidRecordForAudit.action");
        // 指定视图
        mv.setViewName("admin/usertender/usertenderList");
        return mv;
    }

    /**
     * 投标记录详情
     *
     * @param id
     * @return ModelAndView
     * @throws Exception
     * @Title: queryBidRecordDetail
     * @Description: TODO(投标记录详情)
     */
    @RequestMapping(value = "/queryBidRecordDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView queryBidRecordDetail(long id) throws Exception {
        // 返回ModelAndView
        ModelAndView mv = new ModelAndView();
        // 调用service层的方法得到对象详情
        UserTender tender = userTenderService.findUserTenderById(id);
        tender.setOutaccount(getDecryptionUserBaseAccountInfoDetail(tender.getOutaccount()));
        tender.setInaccount(getDecryptionUserBaseAccountInfoDetail(tender.getInaccount()));
        userTenderService.dealTime(tender);
        // 返回ModelAndView
        mv.addObject("tender", tender);
        mv.addObject("tproperty", TenderRecord_Constant.TPROPERTY_MAP.get(tender.getTproperty())); // 标的属性
        mv.addObject("tstatus", TenderRecord_Constant.TSTATUS_MAP.get(tender.getTstatus())); // 投标的状态
        mv.addObject("transfertype", TenderRecord_Constant.TRANSFERTYPE_MAP.get(tender.getTransfertype())); // 转账类型
        mv.addObject("isblending", TenderRecord_Constant.ISBLENDING_MAP.get(tender.getIsblending())); // 是否系统勾兑
        mv.addObject("ismanblending", TenderRecord_Constant.ISMANBLENDING_MAP.get(tender.getIsmanblending())); // 是否人工勾兑
        mv.addObject("isfreeze", TenderRecord_Constant.ISFREEZE_MAP.get(tender.getIsfreeze())); // 是否冻结
        mv.addObject("isda", TenderRecord_Constant.ISDA_MAP.get(tender.getIsda())); // 是否债转
        mv.addObject("tendertype", TenderRecord_Constant.TENDERTYPE_MAP.get(tender.getTendertype())); // 投标方式
        mv.addObject("isrepayend", TenderRecord_Constant.ISREPAYEND_MAP.get(tender.getIsrepayend())); // 还款完成
        mv.addObject("isallowda", TenderRecord_Constant.ISALLOWDA_MAP.get(tender.getIsallowda())); // 能否债转
        // 指定视图
        mv.setViewName("admin/usertender/usertenderDetail");
        return mv;
    }

    /**
     * 审核页面
     *
     * @param orderno
     * @return ModelAndView
     * @Title: queryForAudit
     * @Description: TODO(审核页面)
     */
    @RequestMapping("/queryForAudit")
    public ModelAndView queryForAudit(String orderno, String bankDate) {
        String[] arr = orderno.split(",");
        List<String> orderList = new ArrayList<String>();
        for (String no : arr) {
            UserTender userTender = userTenderService.findUserTenderByOrderno(no);
            orderList.add(userTender.getOrderno());
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("count", orderList.size());
        mv.addObject("orderno", orderno);
        if (StringUtils.isNotBlank(bankDate)) {
            mv.addObject("bankDate", bankDate.trim());
        }
        mv.setViewName("admin/usertender/usertender_Audit");
        return mv;
    }

    /**
     * @param tender
     * @return void
     * @Title: checkTenderRecord
     * @Description: TODO(审核投标记录)
     */
    @RequestMapping("/checkTenderRecord")
    public synchronized void checkTenderRecord(UserTender tender) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        AdminUser admin = getAdminUser(); // 从Session中获取管理员信息
        String ordernos = tender.getOrderno(); // 获取页面传过来的订单号
        String isaudit = request.getParameter("isaudit"); // 获取页面传过来的审核状态
        String bankDate = request.getParameter("bankDate"); //测试徽商时,上传批量文件需要的日期
        if (StringUtil.isNotEmpty(ordernos) && StringUtil.isNotEmpty(isaudit) && StringUtil.isNotEmpty(bankDate)) { // 判断订单号和审核状态非空
            if (isaudit.equals("2") || isaudit.equals("3")) { // 判断审核状态是否为预期值
                String[] arr = ordernos.split(","); // 转换订单号为字符串数组
                UserTender userTender = null;
                String batchNumber = StringUtil.stringLeftPading(6, fintrannumber.nextStringValue(), 1);
//				Calendar c = Calendar.getInstance();
//				c.set(2016, 04, 12);
//				String date = StringUtil.formatDate(c.getTime(), "yyyyMMdd");
                System.out.println(bankDate);
                File file = null;
                for (String no : arr) {
                    userTender = userTenderService.findUserTenderByOrderno(no); // 根据订单号查询投标记录
                    if (userTender == null) { // 判断投标记录是否存在
                        map.put("result", "订单号" + no + "无效");
                        break;
                    } else {
                        if (userTender.getUtproperty() == 1 && userTender.getTstatus() == 1) { // 判断是否为原始投标且为待审核状态
                            if (isaudit.equals("2")) {
                                userTender.setAuditman(admin.getUsername());
                                userTender.setAudittime(new Date());
                                userTender.setTstatus(TenderRecord_Constant.TSTATUS_PROCESSING);
                                // 调用service 修改审核状态
                                int rows = 0;
                                rows = userTenderService.updateByOrderNO(userTender);
                                if (rows == 0) {
                                    map.put("result", "订单号" + userTender.getOrderno() + "审核失败");
                                }
                                String loanno = StringUtil.getNo(); // 生成放款订单号
                                UserFsAccountInfo uf1 = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userTender.getOutaccountid());
                                UserFsAccountInfo uf2 = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userTender.getInaccountid());
                                //文件输出流  如果文件名是一个目录，会抛出异常
                                //3004:徽商银行的银行编号
                                //放在同一天的文件夹内
                                file = new File(HSignUtil.FILEUPLOAD + FINTRAN + File.separator + StringUtil.formatDate(new Date(), "yyyyMMdd"));
                                //如果文件夹不存在则创建
                                if (!file.exists() && !file.isDirectory()) {
                                    file.mkdirs();
                                }
                                FileOutputStream fos = new FileOutputStream(file + File.separator + "3004-" + HSignUtil.FUISSUER + "-Z01-FINTRAN-" + batchNumber + "-" + bankDate, true);
                                //采用GBK编码
                                PrintWriter out = new PrintWriter(new OutputStreamWriter(fos, "GBK"), true);
                                out.print(batchNumber);// 批次号  6
                                out.print("001");// 业务类别 3
                                out.print(bankDate); // 日期8
                                out.print(AES.getDecrypt(uf1.getUsrcustid())); // 扣款账号
                                String[] array = df1.format(userTender.getRealamount()).split("\\.");
                                out.print(StringUtil.stringLeftPading(12, array[0] + array[1], 1)); // 扣账(本金)金额
                                System.out.println(StringUtil.stringLeftPading(12, array[0] + array[1], 1));
                                out.print(StringUtil.stringLeftPading(12, "0", 1)); // 扣账利息金额
                                out.print(AES.getDecrypt(uf2.getUsrcustid())); // 转入账号
                                out.print(156);//币种  人民币
                                out.print("0");// 转出方手续费扣款方式
                                out.print(StringUtil.stringLeftPading(11, "0", 1)); // 转出方手续费扣款金额
                                out.print("0");// 转入方手续费扣款方式
                                if (userTender.getFee().equals((double) 0)) {
                                    out.print(StringUtil.stringLeftPading(11, "0", 1)); // 转入方手续费扣款金额
                                } else {
                                    String[] array1 = df1.format(userTender.getFee()).split("\\.");
                                    out.print(StringUtil.stringLeftPading(11, array1[0] + array1[1], 1)); // 转入方手续费扣款金额
                                }
                                TenderItem item = tenderItemService.findTenderItemById(userTender.getTenderid());
                                out.print(item.getTno()); // 标的编号
                                out.print(userTender.getAuthcode()); // 投标申请授权码
                                out.print(StringUtil.stringLeftPading(1, "", 2)); // 还款结束标志
                                out.print(StringUtil.stringLeftPading(100, "", 2)); // 第三方保留域
                                out.print(StringUtil.stringRightPading(40, userTender.getOrderno(), 2)); // 投标申请流水号
                                out.print(StringUtil.stringLeftPading(60, "", 2)); // 保留域
                                out.print("\n");
                                out.flush();
                                out.close();
                                inserUserMakeALoan(userTender, loanno); // 查询放款记录
                            }
                            if (isaudit.equals("3")) {
                                String return_code = doParams(userTender);
                                if (return_code.equals("00000000")) {
                                    map.put("result", "操作成功！");
                                }
                            }
                        }
                    }
                }
                if (isaudit.equals("2")) {
                    String code = LoansHttpUpload((file + File.separator).toString(), "3004-" + FUISSUER + "-Z01-FINTRAN-" + batchNumber + "-" + bankDate, bankDate, batchNumber);
                    if (code.equals("0000")) {
                        map.put("result", "上传成功！");
                    }
                }
            } else {
                map.put("result", "操作失败！");
            }
        } else {
            map.put("result", "操作失败！");
        }
        String jsonStr = JSON.toJSONString(map);
        sendJsonData(response, jsonStr);
    }

    // HttpClient文件批量上传
    private String LoansHttpUpload(String path, String name, String date, String batchNumber) throws Exception {
        PostMethod method;
        HttpClient httpClient = new HttpClient();
        StringPart sp = null;
        FilePart fp = null;
        String[] filePath = {path};
        String[] fileName = {name};
        String return_code = null;
        JSONObject map = null;
        Map<String, Object> params = null;
        JSONObject paramsJson = null;
        String pam = null;
        Part[] parts = null;
        for (int i = 0; i < filePath.length; i++) {
            params = new HashMap<String, Object>();
            params.put("coinstCode", HSignUtil.COINSTCODE); // 合作单位编号
            params.put("bankCode", HSignUtil.BANKCODE); // 银行代码
            params.put("transDate", date); // 交易日期
            params.put("fileName", fileName[i]); // 文件名称
            params.put("fileType", BacthFileRecord_Constant.RONGZIKOUKUAN); // 文件类型

            File file1 = new File(path + name);
            String md5encryptionPath = HSignUtil.ENCRYPTPATH;
            String encryptionKey4Server = RSAUtils.getVerifyKey4Client(md5encryptionPath); // 获取加密公钥字符串
            File tmp = new File(file1.getPath() + ".bak");
            tmp.createNewFile();
            FileTransferDemoEncrypted.transFormToTmpFile(file1, tmp);
            String encryptData = RSAUtils.encryptRSAByte(RSAUtils.getFileMD5String(tmp), encryptionKey4Server);// 进行摘要并对摘要进行加密
            params.put("sign", encryptData);

            paramsJson = JSONObject.fromObject(params);
            pam = paramsJson.toString();
            sp = new StringPart("parameters", pam);
            sp.setCharSet("GBK");// 这里要设置字符编码，不然会乱码

            File file2 = new File(path + name + ".txt");
            String keypath = HSignUtil.ENCRYPTION_FILE;
            BufferedReader bre = new BufferedReader(new FileReader(new File(keypath)));
            String keyPath = bre.readLine();
            FileTransferDemoEncrypted.encryptHand(file1, file2, keyPath);// 加密处理
            if (file2.exists()) {
                fp = new FilePart("file", file2);
            }
           method = new PostMethod(HSignUtil.BATCREQHURL);
            parts = new Part[]{fp, sp};
            method.getParams().setContentCharset("UTF-8");
            method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            httpClient.executeMethod(method);

            String responseMaps = method.getResponseBodyAsString();
            map = JSONObject.fromObject(responseMaps);
            return_code = (String) map.get("return_code");
            BacthFileRecord bfr = new BacthFileRecord();
            bfr.setFilePath(path); // 文件路径
            bfr.setSendFileName("3004-" + HSignUtil.FUISSUER + "-Z01-FINTRAN-" + batchNumber + "-" + date); // 上传文件名称
            bfr.setGetFileName("3004-" + HSignUtil.FUISSUER + "-Z01-FINTRAN-RESULT-" + batchNumber + "-" + date); // 下载文件的名称
            bfr.setCoinstCode(HSignUtil.COINSTCODE); // 平台编号
            bfr.setPName("干将P2P平台"); // 平台名称
            bfr.setIsDealResult((short) 0); // 是否已处理结果文件   0.否   1.是
            bfr.setFileType((short) 3);//业务文件类型  1.开户  2.红包转账 3.融资扣款
            bfr.setRemark("融资扣款");
            bfr.setSubmitTime(new Date());//上传文件时间
            if (return_code.equals("0000")) {
                bfr.setIsSend((short) 1);//是否已发送银行
                bfr.setSendResult((short) 1);//发送结果
                System.out.println("文件上传成功");
                if (logger.isDebugEnabled()) {
                    logger.debug("UserTenderController.java 调用融资扣款文件LoansHttpUpload方法,上传成功,上传文件名：" + name + "文件上传成功，返回码：" + return_code);
                }
            } else if (return_code.equals("0004")) {
                bfr.setIsSend((short) 0);//是否已发送银行
                bfr.setSendResult((short) 0);//发送结果
                System.out.println("文件已上传，请勿重复上传");
                if (logger.isDebugEnabled()) {
                    logger.debug("UserTenderController.java 调用融资扣款文件LoansHttpUpload方法,上传失败,上传文件名：" + name + "失败原因：文件已上传，请勿重复上传；返回码：" + return_code);
                }
            } else if (return_code.equals("0003")) {
                bfr.setIsSend((short) 0);//是否已发送银行
                bfr.setSendResult((short) 0);//发送结果
                System.out.println("上传文件过大");
                if (logger.isDebugEnabled()) {
                    logger.debug("UserTenderController.java 调用融资扣款文件LoansHttpUpload方法,上传失败,上传文件名：" + name + "失败原因：文件上传过大；返回码：" + return_code);
                }
            }
            if (return_code.equals("0000")) {
                bacthFileRecordService.insert(bfr);
            }
        }
        return return_code;
    }

    /**
     * 新增投标放款记录
     *
     * @param userTender
     * @param loanno
     * @return void    返回类型
     * @Title: inserUserMakeALoan
     * @Description: TODO(新增投标放款记录)
     */
    private void inserUserMakeALoan(UserTender userTender, String loanno) {
        UserMakeALoan umal = new UserMakeALoan();
        umal.setTenderid(userTender.getTenderid());                                    // 标的号ID
        umal.setOrderno(userTender.getOrderno());                                        // 投标订单号
        umal.setMloanorderno(loanno);                                                    // 放款订单号
        umal.setOutaccountid(userTender.getOutaccountid());                            // 投资方ID
        umal.setInaccountid(userTender.getInaccountid());                                // 借款方ID
        umal.setAmount(userTender.getRealamount());                                    // 放款金额
        umal.setMalbegintime(new Date());                                                // 放款开始时间
        umal.setFee(userTender.getFee());                                                // 手续费（默认0）
        umal.setMediacyfee(userTender.getMediacyfee());                                // 居间费
        umal.setGuaranteefee(userTender.getGuaranteefee());                            // 担保费
        umal.setRiskguarantyfee(userTender.getRiskguarantyfee());                        // 风险保证金
        umal.setIsthaw((short) 0);                                                        // 是否解冻（0.否，1.是）
        umal.setMalstatus((short) 0);                                                    // 放款的状态（0.处理中，1.成功，2.失败）
        umal.setIsblending((short) 0);                                                    // 是否系统勾兑（0.未勾兑，1.已勾兑）
        umal.setIsmanblending((short) 0);                                                // 是否人工勾兑（0.未勾兑，1.已勾兑）
        umal.setPaycompany("徽商银行");                                                    // 放款通道公司
        umal.setIsaudit((short) 0);                                                    // 是否审核
        umal.setRemark("徽商银行投标放款测试");                                                // 备注
        userMakeALoanService.insertSelective(umal);
    }


    /**
     * 徽商银行投标撤销请求
     *
     * @return void    返回类型
     * @Title: doParams
     * @Description: TODO(徽商银行投标撤销请求)
     */
    private String doParams(UserTender ut) {
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
        reqMap.put("COINSTCHANNEL", "000002"); // 合作单位渠道COINSTCHANNEL——000001-手机APP、000002-网页、000003-微信、000004-行内核心、000005-ESB
        reqMap.put("SEQNO", StringUtil.getNo()); // 交易流水号SEQNO——8 ~ 20 位数字流水号
        reqMap.put("SOURCE", HSignUtil.SOURCE); // ESB内部渠道SOURCE——A0
        reqMap.put("RETCODE", ""); // 应答码RETCODE——填空
        reqMap.put("RETMSG", ""); // 应答码描述RETMSG——填空
        reqMap.put("HEADRESERVED", ""); // 报文头保留域HEADRESERVED——可选（将baseid放入报文头保留域）
        // 请求报文
        UserFsAccountInfo uf = userFsAccountInfoService.findUserFsAccountInfoByBaseId(ut.getOutaccountid()); // 查询投资人托管账户信息
        reqMap.put("CARDNBR", AES.getDecrypt(uf.getUsrcustid())); // 电子账号CARDNBR
        reqMap.put("PINFLAG", "0"); // 使用密码标志PINFLAG——（0：不使用密码 、1：使用消费密码、2：使用查询密码）
        reqMap.put("PIN", ""); // 密码PIN
        reqMap.put("SERI_NO", HSignUtil.COINSTCODE + StringUtil.getNo()); // 必填；用于交易的唯一性标识，需前台保证唯一性;本撤销交易的申请流水号;六位合作单位编号+各平台流水号生成规则
        reqMap.put("OLDSEQNO", ut.getOrderno()); // 原投标交易申请流水号
        reqMap.put("AMOUNT", df1.format(ut.getRealamount())); // 投标金额
        reqMap.put("FUISSUER", FUISSUER); // 产品发行方
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
     *
     * @param respoResult
     * @return void    返回类型
     * @Title: callBack
     * @Description: TODO(拼接徽商银行投标撤销返回数据验证签名并修改用户银行卡信息)
     */
    private String callBack(String respoResult, UserTender userTender) {
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
        System.out.println("========交易代码TRXCODE================" + TRXCODE);
        System.out.println("========银行代码BANKCODE================" + BANKCODE);
        System.out.println("========交易日期TRXDATE================" + TRXDATE);
        System.out.println("========交易时间TTRXTIME================" + TRXTIME);
        System.out.println("========合作单位编号COINSTCODE================" + COINSTCODE);
        System.out.println("========合作单位渠道COINSTCHANNEL================" + COINSTCHANNEL);
        System.out.println("========交易流水号SEQNO================" + SEQNO);
        System.out.println("========ESB内部渠道SOURCE================" + SOURCE);
        System.out.println("========应答码RETCODE================" + RETCODE);
        System.out.println("========应答码描述RETMSG================" + RETMSG);
        System.out.println("========HEADRESERVED================" + HEADRESERVED);
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

        if (!verifyResult) {
            System.out.println("验证签名失败...");
        } else {
            System.out.println("验证签名成功");
        }
        if ("00000000".equals(jsonObject.getString("RETCODE"))) {
            System.out.println("操作成功");
            if (!userTender.getTstatus().equals((short) 3)) { // 防止重复更新数据
                updateTenderItem(userTender.getTenderid(), FRZAMT);
                updateInvestorAccount(AES.getEncrypt(CARDNBR), FRZAMT);
                thawPlus(userTender);
            }
            // 更新投标记录
            userTender.setTstatus(TenderRecord_Constant.TSTATUS_REVOKE);    // 投标状态-3.撤销
            userTenderService.updateByPrimaryKeySelective(userTender);
        } else {
            System.out.println("操作失败,错误代码：" + jsonObject.getString("RETCODE"));
        }
        return RETCODE;
    }

    /**
     * 更新标的设置，已完成投标金额
     */
    private void updateTenderItem(BigDecimal tid, String Amount) {
        // 修改标的设置表
        TenderItem item = tenderItemService.findTenderItemById(tid);    // 获得标的设置详情
        if (item.getFinishtamount() == null)
            item.setFinishtamount((double) 0);
        item.setFinishtamount(Arith.sub(item.getFinishtamount(), Double.valueOf(Amount)));    // 已完成投标金额=已完成金额+当前投标金额
        int count = 0;
        count = tenderItemService.update(item);
        if (count > 0) {
            System.out.println("更新标的设置成功！！！！！！！！！！！！！！！！！！！！！！！！");
        }
    }

    /**
     * 更新投资人用户账户表-冻结余额
     */
    private void updateInvestorAccount(String usrcustid, String Amount) {
        UserAccount account = new UserAccount();
        UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByUsrCustId(usrcustid);
        BigDecimal outAccountId = userFsAccountInfo.getBaseid();
        UserAccount userAccount = userAccountService.getUserAccountByBaseId(outAccountId);                                                // 根据baseid查询用户账户信息
        double balance = userAccount.getBalance();                                                                                        // 用户总资产不变
        double avlbalance = Arith.add(userAccount.getAvlbalance(), Double.valueOf(Amount));                                            // 用户可用余额 = 用户可用余额+投标金额
        if (userAccount.getFreezebalance() == null)
            userAccount.setFreezebalance((double) 0);
        double freezeBalance = Arith.sub(userAccount.getFreezebalance(), Double.valueOf(Amount));                                        // 用户冻结余额 = 用户冻结余额-投标金额
        account.setBaseid(outAccountId);                                                                                                // baseId
        account.setBalance(balance);                                                                                                    // 用户总资产
        account.setAvlbalance(avlbalance);                                                                                                // 可用余额
        account.setFreezebalance(freezeBalance);                                                                                        // 冻结余额
        int count = 0;
        count = userAccountService.updateUseraccount(account); // 根据basid更新用户账户表
        if (count > 0) {
            System.out.println("更新投资人用户账户成功！！！！！！！！！！！！！！！！！！！！！！！！");
            System.out.println("账户总资产=============================" + account.getBalance());
        }
    }

    /**
     * 解冻增益
     * @return void    返回类型
     * @throws
     * @Title: thawPlus
     * @Description: TODO()
     */
    private void thawPlus(UserTender userTender) {
        List<UserTenderPlusLink> links = userTenderPlusLinkService.findUserTenderPlusLinkByUtId(userTender.getId());
        if (links != null) {
            for (UserTenderPlusLink utpl : links) {
                BigDecimal icid = utpl.getIcid();
                if (icid != null) {
                    UserInterestRateCoupon coupon = userInterestRateCouponService.findCouponDetailById(icid);
                    coupon.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
                    userInterestRateCouponService.updateByPrimaryKeySelective(coupon);
                }
                BigDecimal reid = utpl.getReid();
                if (reid != null) {
                    UserRedEnvelope red = userRedEnvelopeService.findUserRedEnvelopeById(reid);
                    red.setIsuse(ActAward_Constant.AWARD_CANUSE); // 奖品状态——1.未到期，2.可使用，3.已冻结，4.已使用，5.已到期，6.已作废
                    userRedEnvelopeService.updateByPrimaryKeySelective(red);
                }
            }
        }
    }


    private static FinTranResult readRedResult(String line) throws IOException {
        FinTranResult ft = new FinTranResult();
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");

        byte[] data = line.getBytes("GBK");
        byte[] BATCH = new byte[6];
        System.arraycopy(data, 0, BATCH, 0, 6);
        String batch = new String(BATCH);
        ft.setBatch(batch);
        System.out.println("批次号（6）：" + batch);

        byte[] TYPE = new byte[3];
        System.arraycopy(data, 6, TYPE, 0, 3);
        String type = new String(TYPE);
        ft.setType(type);
        System.out.println("业务类别（3）：" + type);

        byte[] DATE = new byte[8];
        System.arraycopy(data, 9, DATE, 0, 8);
        String date = new String(DATE);
        try {
            ft.setDate(sf1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("日期（8）：" + date);

        byte[] RSPCODE = new byte[2];
        System.arraycopy(data, 17, RSPCODE, 0, 2);
        String rspcode = new String(RSPCODE);
        ft.setRspcode(rspcode);
        System.out.println("处理响应码（2）：" + rspcode);

        byte[] CARDNBRO = new byte[19];
        System.arraycopy(data, 19, CARDNBRO, 0, 19);
        String cardnnbro = new String(CARDNBRO);
        ft.setCardnbro(cardnnbro);
        System.out.println("扣款电子账号（19）：" + cardnnbro);

        byte[] AMOUNT = new byte[12];
        System.arraycopy(data, 38, AMOUNT, 0, 12);
        String str = new String(AMOUNT);
        Double amount = new Double(str.substring(0, 10) + "." + str.substring(10));
        ft.setAmount(amount);
        System.out.println("扣账（本金）金额（12）：" + amount);

        byte[] INTAMT = new byte[12];
        System.arraycopy(data, 50, INTAMT, 0, 12);
        String str1 = new String(INTAMT);
        Double intamt = new Double(str1.substring(0, 10) + "." + str1.substring(10));
        ft.setIntamt(Double.valueOf(intamt));
        System.out.println("扣账利息金额（12）：" + intamt);

        byte[] BILLAMOUNT = new byte[12];
        System.arraycopy(data, 62, BILLAMOUNT, 0, 12);
        String str2 = new String(BILLAMOUNT);
        Double billamount = new Double(str2.substring(0, 10) + "." + str2.substring(10));
        ft.setBillamount(Double.valueOf(billamount));
        System.out.println("实际扣账金额（12）：" + billamount);

        byte[] CARDNBRI = new byte[19];
        System.arraycopy(data, 74, CARDNBRI, 0, 19);
        String cardnbri = new String(CARDNBRI);
        ft.setCardnbri(cardnbri);
        System.out.println("转入电子账号（19）：" + cardnbri);

        byte[] CURR = new byte[3];
        System.arraycopy(data, 93, CURR, 0, 3);
        String curr = new String(CURR);
        ft.setCurr(curr);
        System.out.println("币种（3）：" + curr);

        byte[] OUTFEEWAY = new byte[1];
        System.arraycopy(data, 96, OUTFEEWAY, 0, 1);
        String outfeeway = new String(OUTFEEWAY);
        ft.setOutfeeway(Short.valueOf(outfeeway));
        System.out.println("转出方手续费扣款方式（1）：" + outfeeway);

        byte[] OUTFEEAMT = new byte[11];
        System.arraycopy(data, 97, OUTFEEAMT, 0, 11);
        String str6 = new String(OUTFEEAMT);
        Double outfeeamt = new Double(str6.substring(0, 9) + "." + str6.substring(9));
        ft.setOutfeeamt(Double.valueOf(outfeeamt));
        System.out.println("转出方手续费扣款金额（11）：" + outfeeamt);

        byte[] ROUTFEEAMT = new byte[11];
        System.arraycopy(data, 108, ROUTFEEAMT, 0, 11);
        String str3 = new String(ROUTFEEAMT);
        Double routfeeamt = new Double(str3.substring(0, 9) + "." + str3.substring(9));
        ft.setRoutfeeamt(Double.valueOf(routfeeamt));
        System.out.println("转出方手续费实际扣款金额（11）：" + routfeeamt);

        byte[] INFEEWAY = new byte[1];
        System.arraycopy(data, 119, INFEEWAY, 0, 1);
        String infeeway = new String(INFEEWAY);
        ft.setInfeeway(Short.valueOf(infeeway));
        System.out.println("转入方手续费扣款方式（1）：" + infeeway);

        byte[] INFEEAMT = new byte[11];
        System.arraycopy(data, 120, INFEEAMT, 0, 11);
        String str4 = new String(INFEEAMT);
        Double infeeamt = new Double(str4.substring(0, 9) + "." + str4.substring(9));
        ft.setInfeeamt(Double.valueOf(infeeamt));
        System.out.println("转入方手续费扣款金额（11）：" + infeeamt);

        byte[] RIFEEAMT = new byte[11];
        System.arraycopy(data, 131, RIFEEAMT, 0, 11);
        String str5 = new String(RIFEEAMT);
        Double rifeeamt = new Double(str5.substring(0, 9) + "." + str5.substring(9));
        ft.setRifeeamt(Double.valueOf(rifeeamt));
        System.out.println("转出方手续费实际扣款金额（11）：" + rifeeamt);

        byte[] PRODUCT = new byte[6];
        System.arraycopy(data, 142, PRODUCT, 0, 6);
        String product = new String(PRODUCT);
        ft.setProduct(product);
        System.out.println("标的编号（6）：" + product);

        byte[] SERIALNO = new byte[40];
        System.arraycopy(data, 148, SERIALNO, 0, 40);
        String serialno = new String(SERIALNO);
        ft.setSerialno(serialno);
        System.out.println("投标申请流水号（40）：" + serialno);

        byte[] THDRESE = new byte[100];
        System.arraycopy(data, 188, THDRESE, 0, 100);
        String thdrese = new String(THDRESE);
        ft.setThdrese(thdrese);
        System.out.println("第三方保留域（100）：" + thdrese);

        byte[] RESE = new byte[100];
        System.arraycopy(data, 288, RESE, 0, 100);
        String rese = new String(RESE);
        ft.setRese(rese);
        System.out.println("保留域（100）：" + rese);
        System.out.println("--------分割线---------");
        return ft;
    }

    @RequestMapping("/test")
    public void test(String date) {
        //从批量文件记录表中查找出已成功发送，没有处理的记录
        BacthFileRecord bfr = new BacthFileRecord();
        bfr.setCoinstCode(HSignUtil.COINSTCODE);//平台编号
        bfr.setFileType((short) 3);//业务文件类型  1.开户   2.红包转账  3.融资扣款
        bfr.setSendResult((short) 1);//发送结果（是否成功）   0.失败    1.成功
        bfr.setIsDealResult((short) 0);//是否已处理结果文件     0.否   1.是
        List<BacthFileRecord> bfrList = bacthFileRecordService.getBacthFileRecords(bfr);
        for (BacthFileRecord r : bfrList) {
            try {
                String return_code = LoansHttDownload(r.getGetFileName(), r.getSendFileName().substring(r.getSendFileName().length() - 8));
                if (return_code.equals("0000")) {
//					String filePath = new FileHttpDownload().httpDownloadforTar(r.getGetFileName(),r);
                    FileInputStream fis = new FileInputStream(HSignUtil.FILEUPLOAD + FINTRAN + File.separator + StringUtil.formatDate(new Date(), "yyyyMMdd") + File.separator + r.getGetFileName());
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis, "GBK"));
                    String returnMsg=userTenderService.editFile(r,br );
                    // 关闭流
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/downloadFile")
    private void downloadFile(String id) throws Exception {
        BigDecimal fileId = new BigDecimal(id);
        BacthFileRecord bacthFileRecord = bacthFileRecordService.getBacthFileRecordById(fileId);
        try {
            String return_code = LoansHttDownload(bacthFileRecord.getGetFileName(), bacthFileRecord.getSendFileName().substring(bacthFileRecord.getSendFileName().length() - 8));
            if (return_code.equals("0000")) {
                FileInputStream fis = new FileInputStream(HSignUtil.FILEUPLOAD + FINTRAN + File.separator + StringUtil.formatDate(new Date(), "yyyyMMdd") + File.separator + bacthFileRecord.getGetFileName());
                BufferedReader br = new BufferedReader(new InputStreamReader(fis, "GBK"));
                String returnMsg=userTenderService.editFile(bacthFileRecord,br );
                sendJsonData(response,returnMsg);
                // 关闭流
                fis.close();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private String LoansHttDownload(String name, String date) throws Exception {
        System.out.println(name);
        String[] fileName = {name};
        String return_code = null;
        JSONObject map = null;
        Map<String, Object> params = new HashMap<String, Object>();
        for (int i = 0; i < fileName.length; i++) {
            params.put("coinstCode", HSignUtil.COINSTCODE); // 合作单位编号
            params.put("bankCode", HSignUtil.BANKCODE); // 银行代码
            params.put("transDate", date); // 交易日期
            params.put("fileName", fileName[i]);
            JSONObject paramsJson = JSONObject.fromObject(params);
            String pam = paramsJson.toString();
            HttpClient httpClient;
            PostMethod method;
            httpClient = new HttpClient();
			/*method = new PostMethod("http://onlineuat.cupdata.com:50001/dbesbsit/file/downloadEncryptWithSign");*/
            method = new PostMethod(HSignUtil.BATCHRESURL);//通过路由进行文件下载
            method.getParams().setContentCharset("GBK");
            method.setParameter("parameters", pam);
            httpClient.executeMethod(method);

            String responseMap = method.getResponseBodyAsString();
            map = JSONObject.fromObject(responseMap);
            FileOutputStream out = null;
            return_code = (String) map.get("return_code");

            String sign = (String) map.get("sign");
            String decryptKeyPath = HSignUtil.DECRYPTPATH; // 拼接解密私钥路径
            String decryptKey4Server = RSAUtils.getSignPrivateKey4Client(decryptKeyPath);
            String signdecrypt = new String(Hex.encodeHex(RSAUtils.decryptRSAByte(sign, decryptKey4Server)));

            if (return_code.equals("0000")) {
                String file = (String) map.get("file");
                byte[] _file = file.getBytes("GBK");
                File newFile = new File(HSignUtil.FILEUPLOAD + FINTRAN + File.separator + StringUtil.formatDate(new Date(), "yyyyMMdd"));
                File file1 = new File(newFile + "/" + fileName[i]);
                File file2 = new File(newFile + "/" + fileName[i] + FileTransferDemoEncrypted.BAK);
                String newFileName = file1.getPath();
                if (!newFile.exists() && !newFile.isDirectory())// 判断文件夹是否存在，不存在就创建
                {
                    newFile.mkdirs();
                }
                out = new FileOutputStream(newFile + "/" + fileName[i]);
                String str = new String(_file, "GBK");
                out.write(str.getBytes());
                String keypath = HSignUtil.ENCRYPTION_FILE;
                BufferedReader bre = new BufferedReader(new FileReader(new File(keypath)));
                String keyPath = bre.readLine();
                //
                FileTransferDemoEncrypted.decryptHand(file1, file2, keyPath);
                out.flush();
                out.close();
                File tmp = new File(file2.getPath() + ".bak");
                tmp.createNewFile();
                FileTransferDemoEncrypted.transFormToTmpFile(file2, tmp);
                String md5 = new String(Hex.encodeHex(RSAUtils.getFileMD5String(tmp)));

                if (md5.equals(signdecrypt)) {
                    System.out.println("文件验签成功！");
                    System.out.println("摘要内容：" + signdecrypt);
                } else {
                    System.out.println("文件验签失败！");
                    System.out.println("sign摘要内容：" + signdecrypt);
                    System.out.println("系统计算摘要内容：" + md5);
                }
                if (file1.exists()) {
                    file1.delete();
                }
                if (file2.exists()) {
                    file2.renameTo(new File(newFileName));
                }

                System.out.println("文件下载成功");
                if (logger.isDebugEnabled()) {
                    logger.debug("UserTenderController.java 调用融资扣款处理结果文件LoansHttDownload方法,下载成功,下载文件名：" + name + "下载成功，返回码：" + return_code);
                }
            } else if (return_code.equals("0002")) {
                System.out.println("下载请求参数无法识别");
                if (logger.isDebugEnabled()) {
                    logger.debug("UserTenderController.java 调用融资扣款处理结果文件LoansHttDownload方法,下载失败,下载文件名：" + name + "失败原因：下载请求参数无法识别，返回码：" + return_code);
                }
            }
        }
        return return_code;
    }
}