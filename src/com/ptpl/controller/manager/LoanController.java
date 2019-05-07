package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Loanapp_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.*;
import com.ptpl.service.*;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author liuj 用户借款资料记录
 */
@RequestMapping(value = "/admin/loan")
@Controller
public class LoanController extends BaseController {

    @Autowired
    private loanappServiceI loanappService;
    @Autowired
    private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteService;
    @Autowired
    private UserCommonMaterialServiceI userCommonMaterialService;//用户公共资料service
    @Autowired
    private UserLoanMaterialServiceI userLoanMaterialService;//用户借款资料记录service
    @Autowired
    private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
    @Autowired
    private OftenLoanListServiceI oftenLoanListService;
    @Autowired
    private UserFsAccountInfoServiceI userFsAccountInfoService;
    @Autowired
    private UserBaseAccountInfoServiceI userBaseAccountInfoService;//用户基本信息表

    // 查找所有人的所有借款记录
    @RequestMapping(value = "/selectloanappAll", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectloanAll(HttpServletRequest request, loanapp loanapp) {

        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        int num = 1;
        int size = 9;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }

        PageHelper.startPage(num, size);
        // 调用service层的方法得到对象列表
        List<loanapp> loanapps = loanappService.selectloaninfos(loanapp);
        if (loanapps != null && loanapps.size() > 0) {
            handleManAndAmount(loanapps);
        }
        PageInfo<loanapp> pagehelper = new PageInfo<loanapp>(loanapps);
        pagehelper.setFirstPage(1);

        int lastPageNum = 0;
        if (pagehelper.getTotal() % size == 0) {
            lastPageNum = (int) pagehelper.getTotal() / size;
        } else {
            lastPageNum = (int) pagehelper.getTotal() / size + 1;
        }
        pagehelper.setLastPage(lastPageNum);
        // 把对象放进modelAndView中
        ModelAndView modelAndView = new ModelAndView();
        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        modelAndView.addObject("objectQuotes", objectQuotes);
        modelAndView.addObject("pagehelper", pagehelper);
        // 指定视图
        modelAndView.setViewName("admin/loans/loanselect");
        return modelAndView;
    }

    //借款查看列表页面
    @RequestMapping(value = "/selectloanappAll_look", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectloanappAll_look(HttpServletRequest request, loanapp loanapp) {

        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        int num = 1;
        int size = 9;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }

        PageHelper.startPage(num, size);
        // 调用service层的方法得到对象列表
        List<loanapp> loanapps = loanappService.selectloaninfos(loanapp);
        if (loanapps != null && loanapps.size() > 0) {
            handleManAndAmount(loanapps);
        }
        PageInfo<loanapp> pagehelper = new PageInfo<loanapp>(loanapps);
        pagehelper.setFirstPage(1);

        int lastPageNum = 0;
        if (pagehelper.getTotal() % size == 0) {
            lastPageNum = (int) pagehelper.getTotal() / size;
        } else {
            lastPageNum = (int) pagehelper.getTotal() / size + 1;
        }
        pagehelper.setLastPage(lastPageNum);
        // 把对象放进modelAndView中
        ModelAndView modelAndView = new ModelAndView();
        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        modelAndView.addObject("objectQuotes", objectQuotes);
        //借款申请状态
        modelAndView.addObject("loanapp_appstatus", Loanapp_Constant.LOANAPP_APPSTATUS_MAP);
        modelAndView.addObject("pagehelper", pagehelper);
        // 指定视图
        modelAndView.setViewName("admin/loans/loanselect_look");
        return modelAndView;
    }

    // 借款审核列表页面
    @RequestMapping(value = "/selectloanappAudit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectloanappAudit(HttpServletRequest request, loanapp loanapp) {
        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        int num = 1;
        int size = 9;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }

        PageHelper.startPage(num, size);
        // 查找appstatus=1(待审核) or appstatus=2(审核失败)的申请
        List<loanapp> loanapps = loanappService.selectloanappAudit(loanapp);
        if (loanapps != null && loanapps.size() > 0) {
            handleManAndAmount(loanapps);
        }
        PageInfo<loanapp> pagehelper = new PageInfo<loanapp>(loanapps);
        pagehelper.setFirstPage(1);

        int lastPageNum = 0;
        if (pagehelper.getTotal() % size == 0) {
            lastPageNum = (int) pagehelper.getTotal() / size;
        } else {
            lastPageNum = (int) pagehelper.getTotal() / size + 1;
        }
        pagehelper.setLastPage(lastPageNum);
        // 把对象放进modelAndView中
        ModelAndView modelAndView = new ModelAndView();
        //标类型
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        modelAndView.addObject("objectQuotes", objectQuotes);
        modelAndView.addObject("pagehelper", pagehelper);
        //借款申请状态
        modelAndView.addObject("loanapp_appstatus", Loanapp_Constant.LOANAPP_APPSTATUS_MAP);
        // 指定视图
        modelAndView.setViewName("admin/loans/loanselect");
        return modelAndView;
    }

    // 新增：待提交页面
    @RequestMapping(value = "/replacesubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView replacesubmit() {
        ModelAndView mod = new ModelAndView();
        ArrayList<String> loginnames = new ArrayList<>();
        handleOftenLoanLists(mod, loginnames);
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.selectIsuse();
        if (objectQuotes.size() > 0) {
            mod.addObject("objectQuotes", objectQuotes);
        }
        mod.setViewName("admin/loans/insertloanapp");
        return mod;
    }

    /**
     * 加载常用借款人
     *
     * @author :liuqh
     * @date :2017/7/24 14:25
     */
    private void handleOftenLoanLists(ModelAndView mod, ArrayList<String> loginnames) {
        List<OftenLoanList> oftenLoanLists = oftenLoanListService.selectOftenLoanListByCondition(null);
        if (oftenLoanLists != null && oftenLoanLists.size() > 0) {
            for (OftenLoanList o : oftenLoanLists) {
                BigDecimal baseid = o.getBaseid();
                UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.selectByPrimaryKey(baseid);
                String loginname = userBaseAccountInfo.getLoginname();
                loginname = AES.getDecrypt(loginname);
                String realname = userBaseAccountInfo.getRealname();
                realname = AES.getDecrypt(realname);
                loginnames.add(loginname + "-" + realname);
            }
            mod.addObject("loginnames", loginnames);
        }
    }

    // 新增:代提交
    @RequestMapping(value = "/insertloanappdt", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insertloanappadmin(loanapp loanapp, HttpServletRequest request) {
        //是否把该用户加入到常用借款人列表中：1是 0否
        String isoften = request.getParameter("isoften");
        HashMap<String, String> hashMap = new HashMap<>();
        String loanUserName = request.getParameter("loanUserName");
        //根据登陆名查找用户对象
        UserBaseAccountInfo userInfo = userBaseAccountInfoServiceI.getuserloginname(AES.getEncrypt(loanUserName));
        if (userInfo == null) {
            hashMap.put("result", "不存在该用户名");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //根据baseid查找“用户托管账户信息”对象，判断该用户是否开通“托管账户”
        UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userInfo.getId());
        if (userFsAccountInfo != null && userFsAccountInfo.getUsrcustid() != null && !"".equals(userFsAccountInfo.getUsrcustid())) {
            loanapp.setBaseid(userInfo.getId());
            loanapp.setLoanno(StringUtil.getNameNoForName("GJJK"));//生成编号
            loanapp.setReceiptsamount((double) 0);//入账金额
            loanapp.setApptime(new Date());//申请时间
            loanapp.setMastatus((short) 1);//资料审核状态
            loanapp.setApptype((short) 2);//代申请
            loanapp.setLoanrate(0.0);//借款利率
            loanapp.setIsaitender((short) 0);//是否允许发标
            loanapp.setAppstatus(Loanapp_Constant.T1);
            AdminUser adminUser = (AdminUser) session.getAttribute(Session_Constant.ADMINUSER);
            loanapp.setProxyman(adminUser.getUsername());//待提交人
            try {
                loanappService.insertOrUpdateLoanappAndOftenLoan(loanapp, isoften);
            } catch (Exception e) {
                e.printStackTrace();
                hashMap.put("result", "fail");
                String s = JSON.toJSONString(hashMap);
                try {
                    StringUtil.sendJsonData(response, s);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            hashMap.put("result", "success");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            hashMap.put("result", "该用户还没有开通托管账户");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //修改：待提交页面
    @RequestMapping(value = "update_UI")
    public ModelAndView update_UI(BigDecimal id) {
        if (id != null) {
            loanapp loanapp1 = loanappService.selectByPrimaryKey(id);
            if (loanapp1 != null) {
                //加载常用借款人
                ModelAndView mav = new ModelAndView();
                ArrayList<String> loginnames = new ArrayList<>();
                handleOftenLoanLists(mav, loginnames);
                //处理登陆名和真实名
                ArrayList<loanapp> loanapps = new ArrayList<>();
                loanapps.add(loanapp1);
                handleManAndAmount(loanapps);
                mav.addObject("loanapp1", loanapp1);
                //加载标类型
                List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
                mav.addObject("objectQuotes", objectQuotes);
                mav.setViewName("admin/loans/update_loanapp");
                return mav;
            }
        }
        return null;
    }

    //修改:待提交
    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateloantype(loanapp loanapp1) throws IOException {
        //是否把该用户加入到常用借款人列表中：1是 0否
        String isoften = request.getParameter("isoften");
        HashMap<String, String> hashMap = new HashMap<>();
        String loanUserName = request.getParameter("loanUserName");
        //根据登陆名查找用户对象
        UserBaseAccountInfo userInfo = userBaseAccountInfoServiceI.getuserloginname(AES.getEncrypt(loanUserName));
        if (userInfo == null) {
            hashMap.put("result", "不存在该用户名");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        //根据baseid查找“用户托管账户信息”对象，判断该用户是否开通“托管账户”
        UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userInfo.getId());
        if (userFsAccountInfo != null && userFsAccountInfo.getUsrcustid() != null && !"".equals(userFsAccountInfo.getUsrcustid())) {
            loanapp1.setApptime(new Date());//添加时间
            loanapp1.setAppstatus(Loanapp_Constant.T1);
            loanapp1.setBaseid(userInfo.getId());
            try {
                loanappService.insertOrUpdateLoanappAndOftenLoan(loanapp1, isoften);
            } catch (Exception e) {
                e.printStackTrace();
                hashMap.put("result", "fail");
                String s = JSON.toJSONString(hashMap);
                try {
                    StringUtil.sendJsonData(response, s);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            hashMap.put("result", "success");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            hashMap.put("result", "该用户还没有开通托管账户");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //详情
    @RequestMapping(value = "/todetailUi", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView todetailUi(BigDecimal id) {
        ModelAndView modelAndView = new ModelAndView();
        loanapp loanapp = loanappService.selectByPrimaryKey(id);
        ArrayList<loanapp> loanapps = new ArrayList<>();
        loanapps.add(loanapp);
        //处理登陆名和真实名
        handleManAndAmount(loanapps);
        if (loanapp != null) {
            modelAndView.addObject("loanapp", loanapp);
            modelAndView.setViewName("user/loans/Detail");
            //标类型
            List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
            modelAndView.addObject("objectQuotes", objectQuotes);
        }
        /*if(num>0)
            modelAndView.setViewName("redirect:/loan/selectbaseid.action?id="+baseid);*/
        return modelAndView;
    }

    /**
     * 处理借款人及金额
     *
     * @author :liuqh
     * @date 2017/7/4 16:55
     */
    private void handleManAndAmount(List<loanapp> loanapps) {
        for (loanapp l : loanapps) {
            l.setLoanamountstr(new DecimalFormat("#,###.00").format(l.getLoanamount()));
            UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoService.getUserBaseAccountInfoById(l.getBaseid());
            if (userBaseAccountInfo != null) {
                String loginname = userBaseAccountInfo.getLoginname();
                loginname = AES.getDecrypt(loginname);
                l.setLoginname(loginname);//登陆名
                String realname = userBaseAccountInfo.getRealname();
                realname = AES.getDecrypt(realname);//用户名称（真实姓名）
                l.setRealname(realname);//指定还款人
            }
        }
    }

    // 借款申请审核:允许建标 或 不允许建标
    @RequestMapping(value = "/loanappPass", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loanPass(String id, Short appstatus) {
        loanapp loanapp = new loanapp();
        loanapp.setId(new BigDecimal(id));
        // 赋值审核状态
        loanapp.setAppstatus(appstatus);

        // 赋值审核人
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            loanapp.setAuditman(adminUser.getUsername());
        }
        // 赋值审核的时间
        loanapp.setAudittime(new Date());
        loanappService.updateByPrimaryKeySelective(loanapp);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/loan/selectloanappAudit.action");
        return modelAndView;
    }

    //预审核(同步公共资料,同步补充资料的审核状体为审核中)
    @RequestMapping(value = "/prepareisadut", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView prepareisadut(BigDecimal id) {
        if (id != null) {
            loanapp loanapp = loanappService.selectByPrimaryKey(id);//通过id获取借款资聊
            List<UserCommonMaterial> commonMaterials = userCommonMaterialService.selectAllByBaseid(loanapp.getBaseid());//通过baseid查询对应的公共资料
            if (commonMaterials.size() > 0) {
                UserLoanMaterial loanMaterial = new UserLoanMaterial();//new一个借款资料对象
                loanMaterial.setMaterialtype((short) 1);//公共
                loanMaterial.setBaseid(loanapp.getBaseid());//用户id
                loanMaterial.setAddtime(new Date());//提交时间
                loanMaterial.setAuditstatus((short) 0);//默认为0.待审核
                loanMaterial.setLoanno(loanapp.getLoanno());//借款编号
                int a = 0;
                for (int i = 0; i < commonMaterials.size(); i++) {//循环同步到用户借款资料记录中
                    loanMaterial.setMaterialname(commonMaterials.get(i).getMaterialname());//资料名称
                    loanMaterial.setMaterialcontent(commonMaterials.get(i).getMaterialcontent());//资料内容
                    loanMaterial.setMaterialpic(commonMaterials.get(i).getMaterialpic());//资料图片
                    loanMaterial.setRemark(commonMaterials.get(i).getRemark());//备注
                    a = userLoanMaterialService.insertSelective(loanMaterial);
                }
                if (a > 0) {
                    loanapp.setMastatus((short) 3);//资料审核状态(3.审核中)
                    loanappService.updateByPrimaryKeySelective(loanapp);
                }
            }
            //查询该借款编号和用户id对应的所有借款资料信息
            if (loanapp.getLoanno() != null && loanapp.getBaseid() != null) {
                userLoanMaterialService.updateMaterialBynonid(loanapp.getLoanno(), loanapp.getBaseid());//查询该编号对应的所有信息
            }
        }
        return new ModelAndView("redirect:/admin/loan/selectloanappAll.action");
    }

    //审核确认
    @RequestMapping(value = "isaudok", method = {RequestMethod.POST, RequestMethod.GET})
    public void isaudok(String loanno, BigDecimal id) throws Exception {
        if (loanno != null && id != null) {
            String data = "fail";
            List<UserLoanMaterial> list = userLoanMaterialService.isaudok(loanno);//查询用户的借款资料是否全审核完毕
            if (list.size() > 0) {//如果size大于0,说明还有至少一项还在审核中
                sendJsonData(response, JSON.toJSONString(data));
            } else {//如果没值说明已经全部审核完毕
                loanapp loanapp = new loanapp();
                int asize = userLoanMaterialService.selectallsize(loanno);//查询该条借款记录对应的用户资料
                if (asize != 0) {
                    int oksize = userLoanMaterialService.selectbyok(loanno);//查询该条借款数据对应的合格的资料个数
                    if (oksize == 0) {//返回值为0,代表没有数据，则为全部不合格
                        loanapp.setMastatus((short) 6);//不合格
                        loanapp.setId(id);
                        loanappService.updateByPrimaryKeySelective(loanapp);
                    }
                    if (oksize > 0 && oksize < asize) {//合格的个数小于总个数
                        loanapp.setMastatus((short) 5);//部分合格
                        loanapp.setId(id);
                        loanappService.updateByPrimaryKeySelective(loanapp);
                    }
                    if (oksize == asize) {//全部合格
                        loanapp.setMastatus((short) 4);//全部合格
                        loanapp.setId(id);
                        loanappService.updateByPrimaryKeySelective(loanapp);
                    }
                    data = "succ";
                    sendJsonData(response, JSON.toJSONString(data));
                }
            }
        }
    }
}
