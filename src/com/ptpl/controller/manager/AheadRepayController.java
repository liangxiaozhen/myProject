package com.ptpl.controller.manager;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.AheadRepay;
import com.ptpl.model.AheadRepayAward;
import com.ptpl.model.AheadRepayMode;
import com.ptpl.model.AheadRepayPlatform;
import com.ptpl.model.Award;
import com.ptpl.model.TenderItem;
import com.ptpl.service.AheadRepayAwardServiceI;
import com.ptpl.service.AheadRepayModeServiceI;
import com.ptpl.service.AheadRepayPlatformServiceI;
import com.ptpl.service.AheadRepayServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:liuqh
 * @date:2016年07月12日 17:31:29
 * @description:标的提前还款设置
 */
@Controller
@RequestMapping("/admin/aheadRepay/")
public class AheadRepayController extends BaseController{
    @Autowired
    private AheadRepayServiceI aheadRepayService;
    @Autowired
    private TenderItemServiceI tenderItemService;
    @Autowired
    private AheadRepayAwardServiceI aheadRepayAwardService;
    @Autowired
    private AheadRepayPlatformServiceI aheadRepayPlatformService;
    @Autowired
    private AwardServiceI awardService;
    @Autowired
    private AheadRepayModeServiceI aheadRepayModeService;


    //列表：提前还款设置方式
    @RequestMapping(value = "/selectAheadRepayModeByCondition", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAheadRepayModeByCondition(HttpServletRequest request, AheadRepayMode aheadRepayMode) throws ParseException {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        PageHelper.orderBy("id desc");
        List<AheadRepayMode> aheadRepayModes = aheadRepayModeService.selectModeByStyle(aheadRepayMode);
        PageInfo<Object> pagehelper = initPagehelper(map, aheadRepayModes);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pagehelper", pagehelper);
        modelAndView.addObject("aheadRepayMode",aheadRepayMode);
        modelAndView.setViewName("admin/aheadRepay/AheadReapy_ModeList");
        PublicUtil.removeFormSession(request);
        return modelAndView;
    }

    //删除：提前还款设置方式
    @RequestMapping(value = "/deleteModeById", method = {RequestMethod.POST, RequestMethod.GET})
    public String deleteModeByIdStyle(BigDecimal id) {
        System.out.println("-------->");
        System.out.println("进入删除..." + id);
        int rows = aheadRepayModeService.deleteByPrimaryKey(id);
        if (rows > 0) {
            return "redirect:/admin/aheadRepay/selectAheadRepayPlatStyle.action";
        }
        return null;
    }

    //详情：提前还款设置方式
    @RequestMapping(value = "/detail_UI")
    public ModelAndView detail_UI(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        System.out.println("进入修改...." + id);
        AheadRepayMode aheadRepayMode = aheadRepayModeService.selectByPrimaryKey(id);
        System.out.println("查出来的对象：" + aheadRepayMode);
        if (aheadRepayMode != null) {
            System.out.println("---------->");
            mv.addObject("aheadRepayMode", aheadRepayMode);
            mv.setViewName("admin/aheadRepay/detail_UI");
        }
        return mv;
    }









    //提前还款补偿平台
    @RequestMapping(value = "/selectAheadRepayPlatStyleBy", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAheadReapyPlatStyle(HttpServletRequest request, AheadRepayPlatform aheadRepayPlatform) throws ParseException {
        System.out.println("进入提前还款补偿平台...");
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        String aheadrepaypno = request.getParameter("aheadrepaypno");
        String start = request.getParameter("startAppTime");
        String end = request.getParameter("endAppTime");
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
        List<AheadRepayPlatform> AheadRepayPlatformList = null;
        if ((aheadrepaypno == null || aheadrepaypno.equals("")) && (start == null || start.equals("")) && (end == null || end.equals(""))) {
            AheadRepayPlatformList = aheadRepayPlatformService.selectAheadRepayByStyle(aheadRepayPlatform);
        } else {
            Date startAppTime = null;
            Date endAppTime = null;
            if (!StringUtil.isNullStr(start)) {
                startAppTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start);
            }
            if (!StringUtil.isNullStr(end)) {
                endAppTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end);
            }
            map.put("aheadrepaypno", aheadrepaypno);
            map.put("startAppTime", startAppTime);
            map.put("endAppTime", endAppTime);
            AheadRepayPlatformList = aheadRepayPlatformService.selectAheadPlatMap(map);
        }

        // 调用service层的方法得到对象列表

        System.out.println("查询出来的记录数：" + AheadRepayPlatformList.size());
        PageInfo<AheadRepayPlatform> pagehelper = new PageInfo<AheadRepayPlatform>(AheadRepayPlatformList);
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
        modelAndView.addObject("aheadrepaypno", aheadrepaypno);
        modelAndView.addObject("startAppTime", start);
        modelAndView.addObject("endAppTime", end);
        //	modelAndView.addObject("aheadRepay", aheadRepay);
        // 指定视图
        modelAndView.setViewName("admin/aheadRepay/AheadReapy_PlatFormList");
        PublicUtil.removeFormSession(request);
        return modelAndView;
    }

    //根据id删除
    @RequestMapping(value = "/deletePlatById")
    public String deletePlatById(BigDecimal id) {
        System.out.println("进入删除...");
        int rows = aheadRepayPlatformService.deleteByPrimaryKey(id);
        if (rows > 0) {
            return "redirect:/admin/aheadRepay/selectAheadRepayPlatStyleBy.action";
        }
        return null;
    }

    //根据id查看详情
    @RequestMapping(value = "/viewDetailPlatById")
    public ModelAndView viewDetailPlatById(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        AheadRepayPlatform aheadRepayPlatform = aheadRepayPlatformService.selectByPrimaryKey(id);
        System.out.println("查出来的对象：" + aheadRepayPlatform);
        if (aheadRepayPlatform != null) {
            System.out.println("---------->");
            mv.addObject("aheadRepayPlatform", aheadRepayPlatform);
            mv.setViewName("admin/aheadRepay/ViewDetailPlatById");
        }
        return mv;
    }

    //查询增益列表
    @RequestMapping(value = "/selectAheadRepayZYStyle", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAheadRepayZY(HttpServletRequest request, AheadRepayAward aheadRepayAward) throws ParseException {
        System.out.println("进入提前还款补偿增益个人...");
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        String aheadrepayano = request.getParameter("aheadRepayANo");
        String start = request.getParameter("startAppTime");
        String end = request.getParameter("endAppTime");
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
        List<AheadRepayAward> AheadRepayAwardList = null;
        if ((aheadrepayano == null || aheadrepayano.equals("")) && (start == null || start.equals("")) && (end == null || end.equals(""))) {
            AheadRepayAwardList = aheadRepayAwardService.selectAheadRepayZY(aheadRepayAward);
        } else {
            Date startAppTime = null;
            Date endAppTime = null;
            if (!StringUtil.isNullStr(start)) {
                startAppTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start);
            }
            if (!StringUtil.isNullStr(end)) {
                endAppTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end);
            }
            map.put("aheadRepayPNo", aheadrepayano);
            map.put("startAppTime", startAppTime);
            map.put("endAppTime", endAppTime);
            AheadRepayAwardList = aheadRepayAwardService.selectAheadAwardMap(map);
        }

        // 调用service层的方法得到对象列表

        System.out.println("查询出来的记录数：" + AheadRepayAwardList.size());
        PageInfo<AheadRepayAward> pagehelper = new PageInfo<AheadRepayAward>(AheadRepayAwardList);
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
        modelAndView.addObject("aheadRepayPNo", aheadrepayano);
        modelAndView.addObject("startAppTime", start);
        modelAndView.addObject("endAppTime", end);
        //	modelAndView.addObject("aheadRepay", aheadRepay);
        // 指定视图
        modelAndView.setViewName("admin/aheadRepay/AheadReapy_AwardList");
        PublicUtil.removeFormSession(request);
        return modelAndView;
    }

    //根据id删除
    @RequestMapping(value = "/deleteAwardById")
    public String deleteAwardById(BigDecimal id) {
        int rows = aheadRepayAwardService.deleteByPrimaryKey(id);
        if (rows > 0) {
            return "redirect:/admin/aheadRepay/selectAheadRepayZYStyle.action";
        }
        return null;
    }

    //根据id查看详情
    @RequestMapping(value = "/viewDetailById")
    public ModelAndView viewDetailById(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        AheadRepayAward aheadRepayAward = aheadRepayAwardService.selectByPrimaryKey(id);
        System.out.println("查出来的对象：" + aheadRepayAward);
        if (aheadRepayAward != null) {
            System.out.println("---------->");
            mv.addObject("aheadRepayAward", aheadRepayAward);
            mv.setViewName("admin/aheadRepay/ViewAwordDeatilById");
        }
        return mv;
    }


    // 增加(本金利息)(表二)
    @RequestMapping(value = "/insertAheadRepay", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insertAheadRepay(AheadRepay aheadRepay, HttpServletRequest request, HttpServletResponse response) throws IOException {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            aheadRepay.setAddman(adminUser.getUsername());//添加人
        }
        aheadRepay.setAddtime(new Date());//添加時間
        aheadRepay.setAheadrepayno(StringUtil.getNameNoForName(Marknumber.AHEADREPAY_NO));//生成编号
        List<AheadRepay> aheadRepays = aheadRepay.getAheadRepays();//获取数据
        for (int i = 0; i < aheadRepays.size(); i++) {
            if (aheadRepays.get(i).getMinnoreceiveint() == null) {//取出无用的数据
                aheadRepays.remove(i);
            }
        }
        for (int i = 0; i < aheadRepays.size(); i++) {
            aheadRepay.setMinnoreceiveint(aheadRepays.get(i).getMinnoreceiveint());//最小利息
            aheadRepay.setMaxnoreceiveint(aheadRepays.get(i).getMaxnoreceiveint());//最高利息
            aheadRepay.setAwardtype(aheadRepays.get(i).getAwardtype());//奖励方式
            aheadRepay.setLoanpenaltyname(aheadRepays.get(i).getLoanpenaltyname());//借款人罚金名称
            aheadRepay.setPenaltyquota(aheadRepays.get(i).getPenaltyquota());//定额
            if (aheadRepays.get(i).getPenaltyrate() != null && !aheadRepays.get(i).getPenaltyrate().equals("")) {
                aheadRepay.setPenaltyrate(aheadRepays.get(i).getPenaltyrate() / 100);//百分比
            }
            aheadRepay.setMaxpenalty(aheadRepays.get(i).getMaxpenalty());//最大值
            if (aheadRepays.get(i).getPawardname() != null && !aheadRepays.get(i).getPawardname().equals("")) {//奖品编号，名称
                aheadRepay.setPawardno(aheadRepays.get(i).getPawardname().split(",")[0]);
                aheadRepay.setPawardname(aheadRepays.get(i).getPawardname().split(",")[1]);
            }
            aheadRepayService.insert(aheadRepay);
        }
        TenderItem tenderItem = tenderItemService.findTenderItemById(aheadRepay.getTid());
        tenderItem.setIspicompensateon((short) 1);
        tenderItemService.update(tenderItem);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + aheadRepay.getTid() + ".action");
        return mv;
    }

    //添加(增益利息补偿)(表三)
    @RequestMapping(value = "/insertAheadRepayAward", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView insertAheadRepayAward(AheadRepayAward aheadRepayAward, HttpServletRequest request, HttpServletResponse response) throws Exception {
        aheadRepayAward.setAheadrepayano(StringUtil.getNameNoForName(Marknumber.AHEADAWARD_NO));//生成编号
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            aheadRepayAward.setAddman(adminUser.getUsername());//添加人
        }
        aheadRepayAward.setAddtime(new Date());//添加时间
        List<AheadRepayAward> awards = aheadRepayAward.getAheadRepayAwards();
        for (int i = 0; i < awards.size(); i++) {
            if (awards.get(i).getMinplusnoreceiveint() == null) {
                awards.remove(i);
            }
        }
        for (int i = 0; i < awards.size(); i++) {
            aheadRepayAward.setMinplusnoreceiveint(awards.get(i).getMinplusnoreceiveint());//最小利息
            aheadRepayAward.setMaxplusnoreceiveint(awards.get(i).getMaxplusnoreceiveint());//最高利息
            aheadRepayAward.setPlusawardtype(awards.get(i).getPlusawardtype());//奖励方式
            aheadRepayAward.setPluspenaltyname(awards.get(i).getPluspenaltyname());//奖励名称
            aheadRepayAward.setPluspenaltyquota(awards.get(i).getPluspenaltyquota());//定额
            if (awards.get(i).getPluspenaltyrate() != null && !awards.get(i).getPluspenaltyrate().equals("")) {
                aheadRepayAward.setPluspenaltyrate(awards.get(i).getPluspenaltyrate() / 100);//百分比
            }
            aheadRepayAward.setPlusmaxpenalty(awards.get(i).getPlusmaxpenalty());//最大值
            if (awards.get(i).getPluspawardname() != null && !awards.get(i).getPluspawardname().equals("")) {
                aheadRepayAward.setPluspawardname(awards.get(i).getPluspawardname().split(",")[1]);//	奖品名称
                aheadRepayAward.setPluspawardno(awards.get(i).getPluspawardname().split(",")[0]);//奖品编号
            }
            aheadRepayAwardService.insertSelective(aheadRepayAward);
        }
        TenderItem tenderItem = tenderItemService.findTenderItemById(aheadRepayAward.getTid());
        tenderItem.setIspluscompensateon((short) 1);
        tenderItemService.update(tenderItem);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + aheadRepayAward.getTid() + ".action");
        return mv;
    }

    //添加补偿平台信息(表四)
    @RequestMapping(value = "/insertAheadRepayPlatform", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView insertAheadRepayPlatform(AheadRepayPlatform platform, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            platform.setAddman(adminUser.getUsername());//添加人
        }
        platform.setAddtime(new Date());//设置时间
        platform.setAheadrepaypno(StringUtil.getNameNoForName(Marknumber.AHEADPLATFORM_NO));//生成编号
        List<AheadRepayPlatform> platforms = platform.getPlatforms();//获取数据
        for (int i = 0; i < platforms.size(); i++) {
            if (platforms.get(i).getMinallnoreceiveint() == null) {
                platforms.remove(i);
            }
        }
        for (int i = 0; i < platforms.size(); i++) {
            platform.setMinallnoreceiveint(platforms.get(i).getMinallnoreceiveint());//最小利息
            platform.setMaxallnoreceiveint(platforms.get(i).getMaxallnoreceiveint());//最大利息
            platform.setAwardplatquota(platforms.get(i).getAwardplatquota());//定额
            if (platforms.get(i).getAwardplatrate() != null && !platforms.get(i).getAwardplatrate().equals("")) {
                platform.setAwardplatrate(platforms.get(i).getAwardplatrate() / 100);//百分比
            }
            platform.setAwardplatminmoney(platforms.get(i).getAwardplatminmoney());//最小值
            platform.setAwardplatmaxmoney(platforms.get(i).getAwardplatmaxmoney());//最高值
            aheadRepayPlatformService.insertSelective(platform);
        }
        TenderItem tenderItem = tenderItemService.findTenderItemById(platform.getTid());
        tenderItem.setIsforplatformon((short) 1);
        tenderItemService.update(tenderItem);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + platform.getTid() + ".action");
        return mv;
    }

    // 转发到增加页面(本金利息补偿)
    @RequestMapping(value = "/insert_AheadRepay_Ui/{tid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_AheadRepay_Ui(@PathVariable String tid) {
        ModelAndView mv = new ModelAndView();
        List<Award> awards = awardService.selectByCondition(null);
        mv.addObject("awards", awards);
        mv.addObject("tid", tid);
        mv.setViewName("admin/aheadRepay/AheadRepay_Inst");
        List<AheadRepay> aheadRepays = aheadRepayService.selectAhpayBytid(new BigDecimal(tid));
        if (aheadRepays != null && aheadRepays.size() > 0) {
            TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(tid));
            tenderItem.setIspicompensateon((short) 1);
            tenderItemService.update(tenderItem);
            mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + tid + ".action");
        }
        return mv;
    }

    // 转发到个人奖品奖励设置(增益利息补偿)
    @RequestMapping(value = "/insert_AheadRepayAward_Ui/{tid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_AheadRepayAward_Ui(@PathVariable String tid) {
        ModelAndView mv = new ModelAndView();
        List<Award> awards = awardService.selectByCondition(null);
        mv.addObject("awards", awards);
        mv.addObject("tid", tid);
        mv.setViewName("admin/aheadRepay/AheadRepayAward_Inst");
        List<AheadRepayAward> aheadRepayAwards = aheadRepayAwardService.selectahpayAwardBytid(new BigDecimal(tid));
        if (aheadRepayAwards != null && aheadRepayAwards.size() > 0) {
            TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(tid));
            tenderItem.setIspluscompensateon((short) 1);
            tenderItemService.update(tenderItem);
            mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + tid + ".action");
        }
        return mv;
    }

    // 转发到标的提前还款奖励平台设置(补偿平台)
    @RequestMapping(value = "/insert_AheadRepayPlatform_Ui/{tid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_AheadRepayAwardPlatform_Ui(@PathVariable String tid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("tid", tid);
        mv.setViewName("admin/aheadRepay/AheadRepayPlatform_Inst");
        List<AheadRepayPlatform> aheadRepayPlatforms = aheadRepayPlatformService.selectPayplatBytid(new BigDecimal(tid));
        if (aheadRepayPlatforms != null && aheadRepayPlatforms.size() > 0) {
            TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(tid));
            tenderItem.setIsforplatformon((short) 1);
            tenderItemService.update(tenderItem);
            mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + tid + ".action");
        }
        return mv;
    }

    // 根据条件查找并转发到列表页面
    @RequestMapping(value = "/selectAheadRepayByCondition", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAheadRepayByCondition(HttpServletRequest request, AheadRepay aheadRepay) {

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
        List<AheadRepay> AheadRepayList = aheadRepayService.selectByCondition(aheadRepay);
        PageInfo<AheadRepay> pagehelper = new PageInfo<AheadRepay>(AheadRepayList);
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
        modelAndView.addObject("aheadRepay", aheadRepay);
        // 指定视图
        modelAndView.setViewName("admin/aheadRepay/AheadRepay_List");
        PublicUtil.removeFormSession(request);
        return modelAndView;
    }

    // 根据id查找并返回详细页面(查看详情)
    @RequestMapping(value = "/selectAheadRepayByPrimaryKey", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAheadRepayByPrimaryKey(BigDecimal tid) {
        ModelAndView modelAndView = new ModelAndView();
        if (tid != null) {
            List<AheadRepay> aheadRepays = aheadRepayService.selectAhpayBytid(tid);
            if (aheadRepays.size() > 0) {
                modelAndView.addObject("aheadRepays", aheadRepays);
                modelAndView.addObject("aheadRepay", aheadRepays.get(0));
            }
        }
        modelAndView.setViewName("admin/aheadRepay/AheadRepay_Detail");
        return modelAndView;
    }

    // 转发到修改页面
    @RequestMapping(value = "/toUpdateUi", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toUpdateUi(BigDecimal tid) {
        ModelAndView mv = new ModelAndView();
        if (tid != null) {
            List<AheadRepay> aheadRepays = aheadRepayService.selectAhpayBytid(tid);
            if (aheadRepays.size() > 0) {
                mv.addObject("aheadRepays", aheadRepays);
                mv.addObject("aheadRepay", aheadRepays.get(0));
            }
        }
        mv.setViewName("admin/aheadRepay/AheadRepay_Update");
        return mv;
    }

    // 修改
    @RequestMapping(value = "/updateAheadRepay", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateAheadRepay(AheadRepay aheadRepay) {
        System.out.println(aheadRepay);
        aheadRepayService.update(aheadRepay);
        return "redirect:/admin/aheadRepay/selectAheadRepayByCondition.action";
    }

    //删除
    @RequestMapping(value = "/deleteAheadRepay", method = {RequestMethod.POST, RequestMethod.GET})
    public void deleteAheadRepay(BigDecimal id, BigDecimal tid, HttpServletResponse response) throws IOException {
        PublicUtil.decideBeforeDelete(id, tid, response, tenderItemService, aheadRepayService);
    }

}
