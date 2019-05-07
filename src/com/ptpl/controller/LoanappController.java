package com.ptpl.controller;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Loanapp_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.*;
import com.ptpl.service.*;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user/loan")
/**
 * 实现借贷人借贷申请与记录
 * @author lihs
 *
 */
public class LoanappController extends BaseController {
    @Autowired
    private loanInfoAuditServiceI loanInfoAuditService;
    @Autowired
    private loanappServiceI loanappService;
    @Autowired
    UserBaseAccountInfoServiceI userBaseAccountInfoService;
    @Autowired
    private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteService;//借款类型对象
    @Autowired
    private LoanInfoNeedServiceI loanInfoNeedMapperService;
    @Autowired
    private LoanInfoPresetServiceI loanInfoPresetService;
    @Autowired
    private MultiContentSetServiceI multiContentSetService;
    @Autowired
    private UserCommonMaterialServiceI userCommonMaterialService;//用户公共资料
    @Autowired
    private UserLoanMaterialServiceI userLoanMaterialService;//用户借款资料记录
    @Autowired
    private LoanItemQuoteServiceI loanItemQuoteService;//借款资料项目应用
    @Autowired
    private UserFsAccountInfoServiceI userFsAccountInfoService;

    private String message;


    //转跳到我的借款页面
    @RequestMapping(value = "loanappui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loanappui(String period, String index, String appstatus, String appindex, String startdate, String enddate) {
        ModelAndView modelAndView = new ModelAndView();
        // 处理分页请求
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        int num = 1;
        int size = 8;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        String sortString = "id.desc";
        Order.formString(sortString);

        PageHelper.startPage(num, size);

        List<loanapp> list = null;
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
        if (accountInfo != null) {
            loanapp loanapp = new loanapp();
            loanapp.setBaseid(accountInfo.getId());//用户id
            if (appstatus != null && !appstatus.equals("")) {//判断筛选条件是否为空
                if (appstatus.equals("全部")) {
                    loanapp.setAppstatus(null);
                } else if (appstatus.equals("借款审核中")) {
                    loanapp.setAppstatus(Loanapp_Constant.T1);//1.待审核  2.审核失败  3.待建标  4.待录入  5.待投标  6.投标中  7.已流标  8.待放款  9.待生成还款计划  10.还款中  11.已完成
                } else if (appstatus.equals("招标中")) {
                    loanapp.setAppstatus(Loanapp_Constant.T6);//1.待审核  2.审核失败  3.待建标  4.待录入  5.待投标  6.投标中  7.已流标  8.待放款  9.待生成还款计划  10.还款中  11.已完成
                } else if (appstatus.equals("借款成功")) {
                    loanapp.setAppstatus(Loanapp_Constant.T9);//1.待审核  2.审核失败  3.待建标  4.待录入  5.待投标  6.投标中  7.已流标  8.待放款  9.待生成还款计划  10.还款中  11.已完成
                } else {
                    loanapp.setAppstatus(Loanapp_Constant.T2);//1.待审核  2.审核失败  3.待建标  4.待录入  5.待投标  6.投标中  7.已流标  8.待放款  9.待生成还款计划  10.还款中  11.已完成
                }
                modelAndView.addObject("appstatus", appstatus);
                modelAndView.addObject("appindex", appindex);
            }
            if (startdate != null && !startdate.equals("") && enddate != null && !enddate.equals("")) {//判断日期搜索是否为空
                loanapp.setStartdate(startdate);//申请日期
                loanapp.setEnddate(enddate);//申请日期
                list = loanappService.intervalloanapp(loanapp);
            } else {
                if (period != null && !period.equals("")) {//如果period为空 则是页面加载进来的
                    String k = period.replace(",", "");
                    loanapp.setPeriod(k);//筛选条件
                    list = loanappService.intervalloanapp(loanapp);
                    modelAndView.addObject("period", period);
                    modelAndView.addObject("index", index);
                } else {
                    // 调用service层的方法得到对象列表
                    list = loanappService.intervalloanapp(loanapp);
                    // 指定视图
                }
            }
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    //已还款期数
                    list.get(i).setNpers(loanappService.selectcount(list.get(i).getId()));
                }
            }
            PageInfo<loanapp> pagehelper = new PageInfo<loanapp>(list);
            pagehelper.setFirstPage(1);

            int lastPageNum = 0;
            if (pagehelper.getTotal() % size == 0) {
                lastPageNum = (int) pagehelper.getTotal() / size;
            } else {
                lastPageNum = (int) pagehelper.getTotal() / size + 1;
            }
            pagehelper.setLastPage(lastPageNum);
            // 把对象放进modelAndView中
            modelAndView.addObject("pagehelper", pagehelper);
            modelAndView.setViewName("user/manager/borrowing/woDeJieKuan_container");
        } else {
            modelAndView.setViewName("user/manager/login");//如果为空则跳转到登录页面
        }
        return modelAndView;
    }

    @RequestMapping(value = "dakuanui")
    public ModelAndView jiekuanui() {
        return new ModelAndView("user/manager/daikuan");
    }

    @RequestMapping(value = "borrowingRecord", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView borrowingRecord(String peri) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
        if (accountInfo != null) {
            loanapp loanapp = new loanapp();
            loanapp.setBaseid(accountInfo.getId());//用户id
            loanapp.setPeriod(new String(peri.getBytes("ISO8859-1"), "UTF-8"));
            List<loanapp> list = loanappService.intervalloanapp(loanapp);
            if (list.size() > 0) {
                mv.addObject("list", list);
                mv.setViewName("user/manager/jiekuan");
            } else {
            }
        }
        return mv;
    }

    //转跳到我要借款页面
    @RequestMapping(value = "/borrowing", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView borrowing() {
        HashMap<String, String> hashMap = new HashMap<>();
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
        if (accountInfo == null) {
            hashMap.put("result", "请重新登陆");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        UserFsAccountInfo userFsAccountInfo = userFsAccountInfoService.findUserFsAccountInfoByBaseId(accountInfo.getId());
        if (userFsAccountInfo == null || userFsAccountInfo.getUsrcustid() == null || "".equals(userFsAccountInfo.getUsrcustid())) {
            hashMap.put("result", "该用户还没有开通托管账户");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            hashMap.put("result", "success");
            String s = JSON.toJSONString(hashMap);
            try {
                StringUtil.sendJsonData(response, s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //转跳到我要借款页面
    @RequestMapping(value = "/borrowing2", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView borrowing2() {
        ModelAndView mv = new ModelAndView();
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
        if (accountInfo != null) {
            mv.addObject("baseid", accountInfo.getId());//id
            mv.addObject("realname", accountInfo.getRealname());

            List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.selectIsuse();
            if (objectQuotes.size() > 0) {
                mv.addObject("objectQuotes", objectQuotes);
            }
            mv.setViewName("user/manager/borrowing/woYaoJieKuan_container");
        } else {
            mv.setViewName("user/manager/login");
        }
        return mv;
    }


    @RequestMapping(value = "/isaudit", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView isaudit(BigDecimal id) {
        ModelAndView modelAndView = new ModelAndView();
        if (id != null) {
            modelAndView.setViewName("redirect:/user/loan/selectloaninfo.action?baseid=" + id);
        }
        //如果集合大于0，再寻找是否有哪条审核信息通过了审核，通过后方可进行借款申请
        /* if(listloan.size()>0)
         {
			 for(Loaninfoaudit loan : listloan)
			 {
				 if(loan.getAuditstatus()==1)
				 { //进入申请借款页面
					 modelAndView.setViewName("redirect:/loan/selectloaninfo.action?baseid="+id);
				 	break;
			 		}
				 else
				 	{
					 message="您的资料未通过或正在审核，请先去填写资料或者修改资料吧！";
					 	modelAndView.addObject("message", message);
					 	modelAndView.setViewName("redirect:/loan/seleceBaseByid.action?id="+id);
				 	}
			 }
		 }else{
			 message="您还没有填写身份资料，请先去填写资料吧！";
			 modelAndView.addObject("message", message);
			 modelAndView.setViewName("user/loans/loanAudit");
		 }*/
        return modelAndView;
    }


    //保存借款申请(自提交)
    @RequestMapping(value = "/insertloanapp", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView insertloanapp(loanapp loanapp) {
        ModelAndView mv = new ModelAndView();
        loanapp.setLoanno(StringUtil.buildNo("GJ"));//生成编号
        loanapp.setReceiptsamount((double) 0);//入账金额
        loanapp.setApptime(new Date());//申请时间
        loanapp.setMastatus((short) 1);//资料审核状态
        loanapp.setLoanrate(0.0);//利率
        loanapp.setIsaitender((short) 0);//是否允许发标
        loanapp.setAppstatus(Loanapp_Constant.T1);
        int i = loanappService.insertSelective(loanapp);
        if (i > 0) {
            if (loanapp.getApptype().toString().equals("1")) {//自提交
                mv.setViewName("redirect:/user/loan/loanappui.action");
            }
        }
        return mv;
    }

    //待提交信息
    @RequestMapping(value = "/insertloanappadmin", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView insertloanappadmin(loanapp loanapp) {
        System.out.println("资料编号================" + loanapp.getLiano());
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
        String dateString = formatter.format(currentTime);
        //获取代提交人用户名
        AdminUser adminUser = (AdminUser) session.getAttribute(Session_Constant.ADMINUSER);
        loanapp.setProxyman(adminUser.getUsername());
        int x = (int) (Math.random() * 99999);
        //给借款编号赋值
        loanapp.setLoanno(dateString + x);
        //申请时间
        loanapp.setApptime(currentTime);
        loanapp.setReceiptsamount(0.0);
        ModelAndView modelAndView = new ModelAndView();
        loanapp.setLoanrate(0.0);
        int num = loanappService.insert(loanapp);
        if (num > 0)
            modelAndView.setViewName("redirect:/loan/selectloanAll.action");
        return modelAndView;
    }


    //用户借款记录
    @RequestMapping(value = "/selectbaseid", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView selectbaseid(int id) {
        ModelAndView modelAndView = new ModelAndView();
        LoanTypeObjectQuote loanTypeObjectQuote = new LoanTypeObjectQuote();
        List<loanapp> listloanapp = loanappService.selectbaseid(id);
        for (int i = 0; i < listloanapp.size(); i++) {
            loanTypeObjectQuote = loanTypeObjectQuoteService.selectBySerialNo(new BigDecimal(listloanapp.get(i).getLoantype()));
            if (loanTypeObjectQuote != null) {
                listloanapp.get(i).setLoantypestr(loanTypeObjectQuote.getObjectname());
            }
        }
        //如果集合大于0，则进入借款信息展示页面，反之，则表明该用户没有借款信息，就进如申请审核页面
        if (listloanapp.size() > 0) {
            modelAndView.addObject("listloanapp", listloanapp);
            modelAndView.setViewName("user/loans/loanappshow");
        } else {
            modelAndView.setViewName("redirect:/user/loan/selectloaninfo.action?baseid=" + id);
        }
        return modelAndView;
    }


    //详情
    @RequestMapping(value = "/deleteByid", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView deleteByid(BigDecimal id) {
        ModelAndView modelAndView = new ModelAndView();
        loanapp loanapp = loanappService.selectByPrimaryKey(id);
        if (loanapp != null) {
            modelAndView.addObject("loanapp", loanapp);
            modelAndView.setViewName("user/loans/Detail");
        }
        /*if(num>0)
            modelAndView.setViewName("redirect:/loan/selectbaseid.action?id="+baseid);*/
        return modelAndView;
    }


    //根据ID查询当前对像
    @RequestMapping(value = "/selectByloanappid", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView selectByloanappid(BigDecimal id) {
        ModelAndView modelAndView = new ModelAndView();
        loanapp loanapp = new loanapp();
        loanapp = loanappService.selectByPrimaryKey(id);
        //如果对象有数据，则进入该信息页面
        if (loanapp != null) {
            modelAndView.addObject("loanapp", loanapp);
            modelAndView.setViewName("user/loans/auditordetails");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/updateloanapp", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView updateloanapp(loanapp record) {
        ModelAndView modelAndView = new ModelAndView();
        int num = loanappService.updateByPrimaryKeySelective(record);
        if (num > 0)
            modelAndView.setViewName("redirect:/loan/selectbaseid.action?id=" + record.getBaseid());
        return modelAndView;
    }


    //查找所有借款记录
    @RequestMapping(value = "/selectloanAll", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectloanAll(HttpServletRequest request) {

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
        String sortString = "id.desc";
        Order.formString(sortString);

        PageHelper.startPage(num, size);
        // 调用service层的方法得到对象列表
        List<loanapp> loanapp = loanappService.selectloanAll();
        PageInfo<loanapp> pagehelper = new PageInfo<loanapp>(loanapp);
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
        modelAndView.addObject("pagehelper", pagehelper);
        // 指定视图
        modelAndView.setViewName("admin/loans/loanselect");
        return modelAndView;
    }


    //是否通过审核
    @RequestMapping(value = "/loanPass", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loanPass(String id, Short appStatus, String AuditMan, String loanrate) {
        loanapp loanapp = new loanapp();
        loanapp.setId(new BigDecimal(id));
        //借款利率
        loanapp.setLoanrate(Double.valueOf(loanrate));
        //赋值审核状态
        loanapp.setAppstatus(appStatus);
        //赋值审核人
        loanapp.setAuditman(AuditMan);
        //赋值审核的时间
        loanapp.setAudittime(new Date());
        int num = loanappService.updateByPrimaryKeySelective(loanapp);
        ModelAndView modelAndView = new ModelAndView();
        if (num > 0)
            modelAndView.setViewName("redirect:/loan/selectloanAll.action");
        return modelAndView;
    }


    //根据条件查找
    @RequestMapping(value = "/selectloaninfos", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectloaninfos(HttpServletRequest request, loanapp loanapp) {
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
        String sortString = "id.desc";
        Order.formString(sortString);
        PageHelper.startPage(num, size);
        // 调用service层的方法得到对象列表
        List<loanapp> loaninfo = loanappService.selectloaninfos(loanapp);
        PageInfo<loanapp> pagehelper = new PageInfo<loanapp>(loaninfo);
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
        modelAndView.addObject("pagehelper", pagehelper);
        // 指定视图
        modelAndView.setViewName("admin/loans/loanselect");
        return modelAndView;
    }


//    //借款页面(自提交)
//    @RequestMapping(value = "/selectloaninfo", method = {RequestMethod.POST, RequestMethod.GET})
//    public ModelAndView selectloaninfo(BigDecimal baseid) {
//        ModelAndView modelandview = new ModelAndView();
//        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.selectIsuse();
//        if (objectQuotes.size() > 0) {
//            modelandview.addObject("objectQuotes", objectQuotes);
//            modelandview.setViewName("user/loans/loanapplication");
//        }
//        return modelandview;
//
//    }


    //根据借款编号查出当前借款人资料对象
    @RequestMapping(value = "/selectloan", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectloan(String liano) {
        System.out.println(liano);
        ModelAndView modelandview = new ModelAndView();
        Loaninfoaudit loan = loanInfoAuditService.selectloaninfo(liano);
        if (loan != null) {
            modelandview.addObject("gfundsInt", loan);
            modelandview.setViewName("admin/loans/loaninfominute");
        }
        return modelandview;
    }


    //判断用户是否存在
    @RequestMapping(value = "/isloginname", method = {RequestMethod.POST, RequestMethod.GET})
    public void isloginname(HttpServletResponse response, HttpServletRequest request, String loginname) throws Exception {
        //修改乱码问题
        if (StringUtil.isEmpty(loginname)) {
            message = "请填写用户名(登录名)";
            //以流的形式返回给ajax
            PublicUtil.sendJsonData(response, message);
            return;
        }
        String newloginname = new String(loginname.getBytes("utf-8"), "utf-8");
        List<UserBaseAccountInfo> user = userBaseAccountInfoService.userbase(newloginname);
        System.out.println("fangfa");
        String message = "";
        if (user.size() == 0)
            message = "该用户不存在，请仔细查兑";
        //以流的形式返回给ajax
        PublicUtil.sendJsonData(response, message);
    }


    //判断用户是否存在
    @RequestMapping(value = "/isbaseid", method = {RequestMethod.POST, RequestMethod.GET})
    public void isbaseid(HttpServletResponse response, HttpServletRequest request, String loginname) throws Exception {
        //修改乱码问题
        if (StringUtil.isEmpty(loginname)) {
            message = "请填写用户名(登录名)";
            //以流的形式返回给ajax
            PublicUtil.sendJsonData(response, message);
            return;
        }
        String newloginname = new String(loginname.getBytes("utf-8"), "utf-8");
        List<UserBaseAccountInfo> user = userBaseAccountInfoService.userbase(newloginname);
        System.out.println("fangfa");
        String message = "";
        if (user.size() != 0)
            for (UserBaseAccountInfo us : user) {
                message = us.getId().toString();
            }
        //以流的形式返回给ajax
        PublicUtil.sendJsonData(response, message);
    }


    // ajax根据baseid加载身份信息资料
    @RequestMapping(value = "/liano", method = {RequestMethod.POST, RequestMethod.GET})
    public void liano(HttpServletResponse response, BigDecimal baseid) throws IOException {
        System.out.println(baseid);
        if (baseid == null) {
            return;
        }
        List<Loaninfoaudit> loan = loanInfoAuditService.selectliano(baseid);
        String jsonString = JSON.toJSONString(loan);
        PublicUtil.sendJsonData(response, jsonString);
    }

    //公共资料跳转的时候需判断用户是否已经填写资料(没填写则跳转填写页面,填写完则跳转显示页面)
    @RequestMapping(value = "/jumpusercommon", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView jumpusercommon() {
        ModelAndView mv = new ModelAndView();
        UserBaseAccountInfo accountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);//获取用户
        if (accountInfo != null) {
            if (accountInfo.getId() != null) {
                List<UserCommonMaterial> list = userCommonMaterialService.selectAllByBaseid(accountInfo.getId());
                List<String> strings = new ArrayList<>();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getMaterialpic() != null) {
                            strings.add(list.get(i).getMaterialpic());//添加所有的图片路径
                        }
                    }
                    mv.addObject("list", list);
                    mv.setViewName("/user/manager/borrowing/pubdata");//跳转至公共资料显示页面
                } else {
                    mv.setViewName("redirect:/user/loan/usercommon2.action");//跳转至公共资料填写页面
                }
            }
        } else {
            mv.setViewName("user/manager/login");
        }
        return mv;
    }

    //填写公共资料(文本类和选择类)
    @RequestMapping(value = "/usercommon", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView usercommon() {
        ModelAndView mv = new ModelAndView();
        //获得启用的自填类（文本）公共资料
        List<LoanInfoNeed> infoNeeds = loanInfoNeedMapperService.selectloanByQuote();
        if (infoNeeds.size() > 0) {
            mv.addObject("infoNeeds", infoNeeds);
        }
        //获取启用的选择类公共资料
        List<LoanInfoPreset> infoPresets = loanInfoPresetService.selectloanByQuote();
        List<MultiContentSet> contentSets = multiContentSetService.selectAllMult(null);
        if (infoPresets.size() > 0) {
            mv.addObject("infoPresets", infoPresets);
            mv.addObject("contentSets", contentSets);
        }
        //获取启用的(图片类)公共资料
        List<LoanInfoNeed> infoNeds = loanInfoNeedMapperService.selectloanBypic();
        List<LoanInfoNeed> loanInfoNeeds = new ArrayList<>();
        if (infoNeds.size() > 0) {
            for (int i = 0; i < infoNeds.size(); i++) {
                for (int j = 0; j < infoNeds.get(i).getCharlength(); j++) {
                    loanInfoNeeds.add(infoNeds.get(i));
                }
            }
            mv.addObject("infoNeedpics", loanInfoNeeds);
        }

        mv.setViewName("user/manager/borrowing/containerthr");
        return mv;
    }


    //填写公共资料(文本类和选择类)
    @RequestMapping(value = "/usercommon2", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView usercommon2() {
        ModelAndView mv = new ModelAndView();
        //查出公共自填类的项目
        List<LoanItemQuote> public_ZiTians = loanItemQuoteService.public_ZiTian();
        //查出公共选择类的项目(及其对应的选择项也查出来)
        List<LoanItemQuote> public_XuanZes = loanItemQuoteService.public_XuanZe();
        //把公共自填类的项目和公共选择类的项目放在一起
        List<LoanItemQuote> loanItemQuotes = new ArrayList<LoanItemQuote>();
        loanItemQuotes.addAll(public_ZiTians);
        loanItemQuotes.addAll(public_XuanZes);
        //对list进行排序
        int size = loanItemQuotes.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                LoanItemQuote loanItemQuote1 = loanItemQuotes.get(i);
                LoanItemQuote loanItemQuote2 = loanItemQuotes.get(j);
                if (loanItemQuote1.getSeriesno() > loanItemQuote2.getSeriesno()) {
                    List<LoanItemQuote> temp = new ArrayList<LoanItemQuote>();
                    temp.add(loanItemQuote1);
                    loanItemQuotes.set(i, loanItemQuote2);
                    loanItemQuotes.set(j, temp.get(0));
                }
            }
        }
        mv.addObject("loanItemQuotes", loanItemQuotes);
        mv.setViewName("user/manager/borrowing/containerthr");
        return mv;
    }

    //用户公共资料填写(图片类)
    @RequestMapping(value = "/usercommonpic", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView usercommonpic() {
        ModelAndView mv = new ModelAndView();
        List<LoanInfoNeed> infoNeds = loanInfoNeedMapperService.selectloanBypic();//获取启用得公共资料(图片类 )
        List<LoanInfoNeed> loanInfoNeeds = new ArrayList<>();
        if (infoNeds.size() > 0) {
            for (int i = 0; i < infoNeds.size(); i++) {
                for (int j = 0; j < infoNeds.get(i).getCharlength(); j++) {
                    loanInfoNeeds.add(infoNeds.get(i));
                }
            }
            mv.addObject("infoNeedpics", loanInfoNeeds);
        }
        mv.setViewName("user/manager/borrowing/containerthr");
        return mv;
    }

    //用户公共资修改(文本和选择类)修改
    @RequestMapping(value = "/usercommonupdate", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView usercommonupdate() {
        ModelAndView mv = new ModelAndView();
        List<LoanInfoNeed> infoNeeds = loanInfoNeedMapperService.selectloanByQuote();//获取启用得公共资料
        if (infoNeeds.size() > 0) {
            mv.addObject("infoNeeds", infoNeeds);
        }
        List<LoanInfoPreset> infoPresets = loanInfoPresetService.selectloanByQuote();//获取选择类公共资料
        List<MultiContentSet> contentSets = multiContentSetService.selectAllMult(null);
        if (infoPresets.size() > 0) {
            mv.addObject("infoPresets", infoPresets);
            mv.addObject("contentSets", contentSets);
        }
        mv.setViewName("user/loans/PublicloanInfo_update");
        return mv;
    }


    //上传补充资料之前需判断是否上传过公共资料
    @RequestMapping(value = "tobeginupload", method = {RequestMethod.POST, RequestMethod.GET})
    public void tobeginupload(BigDecimal baseid) throws Exception {
        if (baseid != null) {
            String data = "fail";
            List<UserCommonMaterial> commonMaterials = userCommonMaterialService.selectAllByBaseid(baseid);
            if (commonMaterials.size() > 0) {//公共资料已上传
                data = "succ";
            } else {//公共资料没上传
            }
            sendJsonData(response, JSON.toJSONString(data));
        }
    }


    //补充资料填写(文本选择类)
    @RequestMapping(value = "/usercommonbucho", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView usercommonbucho(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            loanapp loanapp = loanappService.selectByPrimaryKey(id);
            mv.addObject("loanno", loanapp.getLoanno());//借款编号
            mv.addObject("loanappid", loanapp.getId());//借款id
            String infotype = "";//变量接收借款类型
            if (loanapp.getLoantype() != null) {
                infotype = loanapp.getLoantype().toString();
                List<LoanInfoNeed> needs = new ArrayList<>();
                List<LoanInfoNeed> infoNeeds = loanInfoNeedMapperService.selectloanByQuoteyy();//获取启用得补充资料
                if (infoNeeds.size() > 0) {
                    for (int i = 0; i < infoNeeds.size(); i++) {//循环获取的补充资料获取引用对象
                        List<Integer> integers = StringUtil.pars(infoNeeds.get(i).getQuoteobject());//解析引用对象成数组
                        if (integers.contains(Integer.parseInt(infotype))) {//如果里面包含借款标的类型就添加到另一个集合中
                            needs.add(infoNeeds.get(i));
                        }
                    }
                    mv.addObject("needs", needs);
                }
                //获取所有的选择类补充资料
                List<LoanInfoPreset> presets = new ArrayList<>();
                List<MultiContentSet> contentSets = multiContentSetService.selectAllMult(null);
                List<LoanInfoPreset> infoPresets = loanInfoPresetService.selectloanByQuoteyy();//获取启用得补充资料
                if (infoPresets.size() > 0) {
                    for (int i = 0; i < infoPresets.size(); i++) {
                        List<Integer> integers = StringUtil.pars(infoPresets.get(i).getQuoteobject());//解析引用对象成数组
                        if (integers.contains(Integer.parseInt(infotype))) {
                            presets.add(infoPresets.get(i));
                        }
                    }
                    mv.addObject("presets", presets);
                    mv.addObject("contentSets", contentSets);
                }
            }
        }
        mv.setViewName("user/loans/PrivloanInfo");
        return mv;
    }

    //补充资料类(图片上传类)
    @RequestMapping(value = "/usercommonpicbucho", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView usercommonpicbucho(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        List<LoanInfoNeed> infoNeedpics = new ArrayList<>();//定义一个集合获取最终的图片资料
        if (id != null) {
            loanapp loanapp = loanappService.selectByPrimaryKey(id);
            mv.addObject("loanno", loanapp.getLoanno());//借款编号
            mv.addObject("loanappid", loanapp.getId());//借款id
            String infotype = "";//变量接收借款类型
            if (loanapp.getLoantype() != null) {
                infotype = loanapp.getLoantype().toString();//标的类型id
                List<LoanInfoNeed> needs = new ArrayList<>();
                List<LoanInfoNeed> infoNeeds = loanInfoNeedMapperService.selectloanBypicyy();//获取启用得补充资料(图片上传类)
                if (infoNeeds.size() > 0) {
                    for (int i = 0; i < infoNeeds.size(); i++) {//象循环获取的补充资料获取引用对
                        List<Integer> integers = StringUtil.pars(infoNeeds.get(i).getQuoteobject());//解析引用对象成集合
                        if (integers.contains(Integer.parseInt(infotype))) {//如果里面包含借款标的类型就添加到另一个集合中
                            needs.add(infoNeeds.get(i));
                        }
                    }
                }
                if (needs.size() > 0) {//如果新集合里面有值
                    for (int i = 0; i < needs.size(); i++) {
                        for (int j = 0; j < needs.get(i).getCharlength(); j++) {//获取图片张数
                            infoNeedpics.add(needs.get(i));
                        }
                    }
                    mv.addObject("infoNeedpics", infoNeedpics);
                } else {
                    loanapp.setMastatus((short) 2);//将资料状态改为待审核状态
                    int i = loanappService.updateByPrimaryKey(loanapp);
                    if (i > 0) {
                        mv.setViewName("redirect:/user/loan/selectbaseid.action?id=" + loanapp.getBaseid());
                    }
                }
            }
        }
        mv.setViewName("user/loans/PrivloanInfoPic");
        return mv;
    }

    //用户修改公共资料
    @RequestMapping(value = "toUpdatepic", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toUpdatepic(BigDecimal baseid) {
        ModelAndView mv = new ModelAndView();
        if (baseid != null) {
            List<UserCommonMaterial> commonMaterials = userCommonMaterialService.selectAllByBaseid(baseid);//根据baseid查询信息
            mv.addObject("commonMaterials", commonMaterials);
        }
        return mv;

    }

    //用户查看补充资料
    @RequestMapping(value = "/lookData", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView lookData(BigDecimal baseid, String loanno) {
        ModelAndView mv = new ModelAndView();
        if (baseid != null && loanno != null) {
            UserLoanMaterial loanMaterial = new UserLoanMaterial();
            loanMaterial.setBaseid(baseid);//baseid用户的id
            loanMaterial.setLoanno(loanno);//借款编号
            loanMaterial.setMaterialtype((short) 2);//补充资料
            List<UserLoanMaterial> loanMaterials = userLoanMaterialService.selectTogether(loanMaterial);
            if (loanMaterials.size() > 0) {//如果有值
                mv.addObject("loanMaterials", loanMaterials);
            }
        }
        mv.setViewName("user/loans/Data_List");
        return mv;
    }

    //用户修改补充资料
    @RequestMapping(value = "/Datachange", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView Datachange(BigDecimal id, BigDecimal baseid, String linno, String materialContent, String loanno) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("id", id);//用户借款资料id
        if (linno != null) {//资料编号不为空
            LoanItemQuote itemQuote = loanItemQuoteService.selectloanByLiqno(linno);
            if (itemQuote.getQuoteproperty().toString().equals("1")) {//如果资料是自填
                LoanInfoNeed infoNeed = loanInfoNeedMapperService.selectByPrimaryKey(itemQuote.getQuoteobjectid());//通过资料id查询自填类信息
                if (infoNeed.getInfotype().toString().equals("2")) {//文本类自填
                    mv.addObject("quotename", infoNeed.getInfoname());//项目名称
                    mv.addObject("charlength", infoNeed.getCharlength());//字符长度限制
                    mv.addObject("materialContent", materialContent);
                    mv.addObject("z", "z");//自填文本类
                } else {//图片类自填
                    mv.addObject("quotename", infoNeed.getInfoname());//项目名称
                    mv.addObject("baseid", baseid);//资料名称
                    mv.addObject("loanno", loanno);//资料编号
                    mv.addObject("p", "p");//自填文本图片
                }
            } else {//如果资料是选择类
                LoanInfoPreset infoPreset = loanInfoPresetService.selectByPrimaryKey(itemQuote.getQuoteobjectid());//通过选择类id查询信息
                List<MultiContentSet> contentSets = multiContentSetService.selectByMultiNo(infoPreset.getMultino());//通过多选内容的编号查找内容
                if (contentSets.size() > 0) {
                    if (infoPreset.getOneormulti().toString().equals("1")) {//单选
                        mv.addObject("contentSets", contentSets);
                        mv.addObject("infoname", infoPreset.getInfoname());//资料名称
                        mv.addObject("litt", "litt");//单选
                    } else {//多选
                        mv.addObject("contentSets", contentSets);
                        mv.addObject("infoname", infoPreset.getInfoname());//资料名称
                        mv.addObject("more", "more");//多选
                    }

                }
            }

        }
        mv.setViewName("user/loans/Datachange");
        return mv;
    }

    //文本类自填和选择类多选的更新
    @RequestMapping(value = "/updatLoanMaterial", method = {RequestMethod.POST, RequestMethod.GET})
    public void updatLoanMaterial(BigDecimal id, String materialcontent) throws Exception {
        String data = "fail";
        if (id != null && materialcontent != null) {//如果资料id和资料内容不为空
            UserLoanMaterial loanMaterial = new UserLoanMaterial();
            loanMaterial.setId(id);//资料id
            loanMaterial.setMaterialcontent(materialcontent);//资料内容
            loanMaterial.setAuditstatus((short) 1);//审核状态1.审核中
            loanMaterial.setAddtime(new Date());//提交时间
            int i = userLoanMaterialService.updateByPrimaryKeySelective(loanMaterial);//更新
            if (i > 0) {
                data = "succ";
            }
        }
        sendJsonData(response, JSON.toJSONString(data));
    }

    //选择类多选内容的更新
    @RequestMapping(value = "/updatemore", method = {RequestMethod.POST, RequestMethod.GET})
    public void updatemore(UserLoanMaterial loanMaterial) throws Exception {
        String data = "fail";
        loanMaterial.setAddtime(new Date());//提交时间
        loanMaterial.setAuditstatus((short) 1);//审核状态1.审核中
        int i = userLoanMaterialService.updateByPrimaryKeySelective(loanMaterial);
        if (i > 0) {
            data = "succ";
        }
        sendJsonData(response, JSON.toJSONString(data));
    }

    //自填类图片的更新
    @RequestMapping(value = "/updatpic", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updatpic(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        response.setContentType("text/html;charset=utf-8");
        ModelAndView modelandview = new ModelAndView();
        // 1.创建 DiskFileItemFactory
        DiskFileItemFactory factory = new DiskFileItemFactory(); // 使用默认的.
        // 2.创建ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 总大小为10m
        upload.setSizeMax(1024 * 1024 * 20);
        //创建对象，用于参数传递给service,好使材料保存至数据库中
        UserLoanMaterial loanMaterial = new UserLoanMaterial();
        // 用于判断是否是上传操作.
        boolean flag = upload.isMultipartContent(request);
        //上传时间，并以时间生成文件夹保存数据
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        if (flag) {
            // 解决上传文件名称中文乱码
            upload.setHeaderEncoding("utf-8");
            try {
                // 解决request,得到所有的上传项FileItem
                List<FileItem> items = upload.parseRequest(request);
                // 3.得到所有上传项
                int num = 0;//用于保存添加信息的状态
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        if (item.getFieldName().equals("id"))
                            loanMaterial.setId(new BigDecimal(item.getString("utf-8")));//id
                        if (item.getFieldName().equals("baseid"))
                            loanMaterial.setBaseid(new BigDecimal(item.getString("utf-8")));//baseid
                        if (item.getFieldName().equals("loanno"))
                            loanMaterial.setLoanno(item.getString("utf-8"));//奖品编号
                    } else {
                        // 上传组件
                        System.out.println("组件名称:" + item.getFieldName());
                        System.out.println("上传文件名称:" + item.getName());
                        //当前上传时间
                        loanMaterial.setAddtime(new Date());
                        //当前资料图片名称
                        String name = item.getName(); // 上传文件名称
                        name = name.substring(name.lastIndexOf("\\") + 1);
                        //将新文件以UUID生成新名字，以保证不会重名
                        String uuidname = getUUIDFileName(name);
                        System.out.println("新文件名:" + uuidname);
                        File path = pictureFiles(loanMaterial.getBaseid(), dateString);
                        IOUtils.copy(item.getInputStream(), new FileOutputStream(path + "\\" + uuidname));
                        //截取部分，因数据库只需要部分
                        loanMaterial.setMaterialpic((loanMaterial.getBaseid() + "/" + dateString + "/" + uuidname));
                        System.out.println("当前文件的路径。。。。。。。。" + loanMaterial.getMaterialpic());
                        System.out.println("看看作用域baseid是否拿到值===========" + loanMaterial.getBaseid());
                        // 删除临时文件
                        item.delete();
                        if (loanMaterial.getMaterialpic() != null) {
                            loanMaterial.setAuditstatus((short) 1);//将审核状态改为审核中
                            num = userLoanMaterialService.updateByPrimaryKeySelective(loanMaterial);
                        }
                    }
                }
                //循环结束后进入图片展示方法
                if (num > 0) {
                    modelandview.setViewName("redirect:/user/loan/lookData.action?baseid=" + loanMaterial.getBaseid() + "&loanno=" + loanMaterial.getLoanno());
                }
            } catch (FileUploadException e) {
                response.getWriter().write(e.getMessage());
                return modelandview;
            }
        } else {
            response.getWriter().write("不是上传操作");
            return modelandview;
        }
        return modelandview;
    }

    private File pictureFiles(BigDecimal baseid, String dateString) {
        File file = new File("D:/picture/userimage/" + baseid + "/" + dateString);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        return file;
    }

    //获取随机名称 a.jpg
    private String getUUIDFileName(String filename) {
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            return UUID.randomUUID() + filename.substring(index);
        } else {
            return UUID.randomUUID().toString();
        }
    }
}

	