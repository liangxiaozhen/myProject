package com.ptpl.controller.manager;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RiskGuarantyMoney;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserGrade;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import com.ptpl.web.util.UserGradeUtil;
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
import java.util.*;

/**
 * @author:liuqh
 * @date:2016年07月14日 15:09:51
 * @description:标的风险保证金设置
 */
@Controller
@RequestMapping("/admin/riskGuarantyMoney")
public class RiskGuarantyMoneyController {
    @Autowired
    private RiskGuarantyMoneyServiceI riskGuarantyMoneyService;
    @Autowired
    private UserGradeServiceI userGradeService;
    @Autowired
    private TenderItemServiceI tenderItemService;

    // 转发到增加标的风险保证金设置页面
    @RequestMapping(value = "/insert_RiskGuarantyMoney_Ui/{tid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insert_RiskGuarantyMoney_Ui(@PathVariable String tid) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<UserGrade> uGrades = userGradeService.getAll(null);
        modelAndView.setViewName("admin/riskGuarantyMoney/RiskGuarantyMoney_Inst");
        List<String> gradeList = null;
        if (tid != null && !tid.isEmpty()) {
            gradeList = riskGuarantyMoneyService.selectGradebyTid(new BigDecimal(tid));//查找利息管理表中已设置的会员等级
        }
        modelAndView = UserGradeUtil.mv(uGrades, gradeList, modelAndView, tid);
        if (uGrades.size() <= 0) {
            TenderItem tenderItem = tenderItemService.findTenderItemById(new BigDecimal(tid));
            tenderItem.setIsariskgm((short) 1);
            tenderItemService.update(tenderItem);
            modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/" + tid + ".action");
        }
        return modelAndView;
    }

    //添加保证金通过会员等级
    @RequestMapping(value = "inseretriskbytid", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView insertbyugrade(BigDecimal tid) throws Exception {
        ModelAndView mv = new ModelAndView();
        RiskGuarantyMoney guarantyMoney = new RiskGuarantyMoney();
        guarantyMoney.setTid(tid);
        List<RiskGuarantyMoney> guarantyMoneys = riskGuarantyMoneyService.selectByConditionAndDecorateUgrade(guarantyMoney);//通过tid查询该tid对应的所有记录
        List<UserGrade> grades = userGradeService.getAll(null);//获取所有的会员等级
        String riskstr = "";//定义一个str储存会员名
        for (RiskGuarantyMoney money : guarantyMoneys) {//通过循环取出会员名
            riskstr += money.getUgrade();
        }
        //将字符串转换成list集合
        List<String> list = singleElement(new ArrayList<String>(Arrays.asList(riskstr.split(" "))));
        List<BigDecimal> bigDecimals = new ArrayList<>();//定义一个list集合储存会员等级编号
        for (int i = 0; i < grades.size(); i++) {//双重for循环筛选出选的会员的编号
            for (int j = 0; j < list.size(); j++) {
                if (grades.get(i).getUgradename().equals(list.get(j))) {
                    bigDecimals.add(grades.get(i).getUgrade());
                }
            }
        }
        List<UserGrade> grades2 = userGradeService.getGradeList(bigDecimals);//将筛选出来的数据在数据库进行过滤
        mv.addObject("grades2", grades2);
        mv.addObject("riskguarantymonty", guarantyMoneys.get(0));
        mv.setViewName("admin/riskGuarantyMoney/RiskGuarantyMoney_Inst");
        return mv;
    }

    // 增加标的风险保证金设置
    @RequestMapping(value = "/insertRiskGuarantyMoneytwo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insertRiskGuarantyMoneytwo(RiskGuarantyMoney riskGuarantyMoney, String[] ugrades, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserGrade> userGrades = userGradeService.getAll(null);// 获取全部等级
        String isAll = riskGuarantyMoney.getUgrade();////全部等级或者部分等级，存放1或者2
        String gradeStr = UserGradeUtil.changeString(isAll, ugrades, userGrades);//将所有等级转化为字符串
        riskGuarantyMoney.setUgrade(gradeStr);
        String riskno = "";//首先判断编号是否为空
        if (riskGuarantyMoney.getRiskgmno() == null) {
            riskno = StringUtil.getNoForTenderItem(Marknumber.GUARANTYMONEY_NO);//生成编号
        }
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            riskGuarantyMoney.setAddman(adminUser.getUsername());//添加人
        }
        riskGuarantyMoney.setAddtime(new Date());//添加时间
        riskGuarantyMoney.setRiskgmno(riskno);
        riskGuarantyMoney.setRgmrate(riskGuarantyMoney.getRgmrate() / 100);//风险保证金费率
        riskGuarantyMoneyService.insert(riskGuarantyMoney);//添加
        List<String> gradeList = riskGuarantyMoneyService.selectGradebyTid(riskGuarantyMoney.getTid());//该标已经设置的等级记录
        int count = UserGradeUtil.count(gradeList);//统计所有记录已设置的等级个数
        if (count < userGrades.size()) {
            mv = new ModelAndView("redirect:/admin/riskGuarantyMoney/insert_RiskGuarantyMoney_Ui/" + riskGuarantyMoney.getTid() + ".action");
            return mv;
        }//如果count小于所有等级个数，说明没有设置完，则重定向到转发页面的请求Url

        TenderItem tenderItem = tenderItemService.findTenderItemById(riskGuarantyMoney.getTid());
        tenderItem.setIsariskgm((short) 1);
        tenderItemService.update(tenderItem);
        mv = new ModelAndView("redirect:/admin/tenderItem/tenderItem_tag_UI/" + riskGuarantyMoney.getTid() + ".action");
        return mv;
    }

    // 增加标的风险保证金设置
    @RequestMapping(value = "/insertRiskGuarantyMoney", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView insertRiskGuarantyMoney(RiskGuarantyMoney riskGuarantyMoney, String[] ugrades, String ugrade,
                                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 判断ugrade是否为1，如果为1，则为全部等级
        if ("1".equals(ugrade)) {
            List<UserGrade> all = userGradeService.getAll(null);
            int size = all.size();
            String ugrade1 = StringUtil.intToString(size);
            riskGuarantyMoney.setUgrade(ugrade1);
        } else if ("2".equals(ugrade)) {
            String ugrade2 = StringUtil.stringArrayToString(ugrades);
            riskGuarantyMoney.setUgrade(ugrade2);
        }
        // 判断ugrade是否为2，如果为2，则为选择了部分，则要获取ugrades数组，并把它变为“001101”的字符串的形式

        System.out.println(riskGuarantyMoney);
        String riskgmno1 = StringUtil.getNameNoForName("GJFCX");
        riskGuarantyMoney.setRiskgmno(riskgmno1);
        riskGuarantyMoneyService.insert(riskGuarantyMoney);
        // 得到风险保证金设置的编号并set进标的风险保证金设置编号字段
        BigDecimal tenderItemId = (BigDecimal) request.getSession().getAttribute("tenderItemId");
        String riskgmno = riskGuarantyMoney.getRiskgmno();
        if (tenderItemId != null) {
            TenderItem tenderItem = PublicUtil.getTenderItem(tenderItemId, tenderItemService);
            tenderItem.setRiskgmno(riskgmno);
            ArrayList<String> urlList = (ArrayList<String>) request.getSession().getAttribute("urlList");
            Short isaudit = (Short) request.getSession().getAttribute("isaudit");
            //如果urlList.size的长度为0，则表示，此刻保存的是最后一个标相关的设置，则把标状态改为审核中
            if (urlList == null) {
                return PublicUtil.setLastItem(request, response, tenderItem, isaudit, tenderItemService);
            }
            // 设置标的相应字段
            tenderItemService.update(tenderItem);
        }
        return PublicUtil.changeUrlForTenderItem(request);
    }

    // 根据条件查找标的风险保证金设置并转发到列表页面
    @RequestMapping(value = "/selectRiskGuarantyMoneyByCondition", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectRiskGuarantyMoneyByCondition(HttpServletRequest request,
                                                           RiskGuarantyMoney riskGuarantyMoney) {

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
        List<RiskGuarantyMoney> RiskGuarantyMoneyList = riskGuarantyMoneyService.selectByConditionAndDecorateUgrade(riskGuarantyMoney);
        PageInfo<RiskGuarantyMoney> pagehelper = new PageInfo<RiskGuarantyMoney>(RiskGuarantyMoneyList);
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
        modelAndView.addObject("riskGuarantyMoney", riskGuarantyMoney);
        // 指定视图
        modelAndView.setViewName("admin/riskGuarantyMoney/RiskGuarantyMoney_List");
        PublicUtil.removeFormSession(request);
        return modelAndView;
    }

    // 根据id查找并返回的详细页面(详情)
    @RequestMapping(value = "/selectRiskGuarantyMoneyByPrimaryKey", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectRiskGuarantyMoneyByPrimaryKey(BigDecimal tid) {
        ModelAndView mv = new ModelAndView();
        RiskGuarantyMoney riskGuarantyMoney = new RiskGuarantyMoney();
        riskGuarantyMoney.setTid(tid);//获取tid
        List<RiskGuarantyMoney> riskGuarantyMoneys = riskGuarantyMoneyService.selectByConditionAndDecorateUgrade(riskGuarantyMoney);//获取id对应的信息
        mv.addObject("riskGuarantyMoney", riskGuarantyMoneys.get(0));
        mv.addObject("riskGuarantyMoneys", riskGuarantyMoneys);
        mv.setViewName("admin/riskGuarantyMoney/RiskGuarantyMoney_Detail");
        return mv;
    }

    // 转发到修改页面(修改)
    @RequestMapping(value = "/toUpdateUi", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toUpdateUi(BigDecimal tid) {
        ModelAndView modelAndView = new ModelAndView();
        RiskGuarantyMoney riskGuarantyMoney = new RiskGuarantyMoney();
        riskGuarantyMoney.setTid(tid);
        List<RiskGuarantyMoney> riskGuarantyMoneys = riskGuarantyMoneyService.selectRiskBytid(tid);
        RiskGuarantyMoney riskGuarantyMoney1 = null;
        if (riskGuarantyMoneys.size() > 0) {
            riskGuarantyMoney1 = riskGuarantyMoneys.get(0);
        }
        if (riskGuarantyMoney1 != null && riskGuarantyMoney1.getChargetype().equals("2")) {//投标时收取
            String ugrade = riskGuarantyMoney1.getUgrade();
            PublicUtil.decorateGrade(modelAndView, userGradeService, ugrade, "ugrade", "ugrades1");
            List<UserGrade> uGrades = userGradeService.getAll(null);
            modelAndView.addObject("uGrades", uGrades);
        }
        modelAndView.addObject("riskGuarantyMoney", riskGuarantyMoney1);
        modelAndView.addObject("riskGuarantyMoneys", riskGuarantyMoneys);
        modelAndView.setViewName("admin/riskGuarantyMoney/RiskGuarantyMoney_Update");
        return modelAndView;
    }

    // 修改
    @RequestMapping(value = "/updateRiskGuarantyMoney", method = {RequestMethod.POST, RequestMethod.GET})
    public String updateRiskGuarantyMoney(RiskGuarantyMoney riskGuarantyMoney, String[] ugrades, HttpServletRequest req) {
        AdminUser adminUser = (AdminUser) req.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            riskGuarantyMoney.setAddman(adminUser.getUsername());//设置修改人
        }
        riskGuarantyMoney.setAddtime(new Date());//设置修改时间
        if (riskGuarantyMoney.getChargetype().toString().equals("1")) {//结标收取
            List<RiskGuarantyMoney> guarantyMoneys = riskGuarantyMoney.getRiskGuarantyMoneys();//获取页面数据
            for (int i = 0; i < guarantyMoneys.size(); i++) {
                riskGuarantyMoney.setMinrgmmoney(guarantyMoneys.get(i).getMinrgmmoney());//结标分段低值
                riskGuarantyMoney.setMaxrgmmoney(guarantyMoneys.get(i).getMaxrgmmoney());//结标分段高值
                riskGuarantyMoney.setRgmquota(guarantyMoneys.get(i).getRgmquota());//定额
                riskGuarantyMoney.setRgmpercent(guarantyMoneys.get(i).getRgmpercent());//百分比
                riskGuarantyMoney.setMaxrgmfee(guarantyMoneys.get(i).getMaxrgmfee());//最高金额
                riskGuarantyMoney.setId(guarantyMoneys.get(i).getId());
                riskGuarantyMoneyService.update(riskGuarantyMoney);
            }
        }
        return "redirect:/riskGuarantyMoney/selectRiskGuarantyMoneyByCondition.action";
    }

    // 删除
    @RequestMapping(value = "/deleteRiskGuarantyMoney", method = {RequestMethod.POST, RequestMethod.GET})
    public void deleteRiskGuarantyMoney(BigDecimal id, BigDecimal tid, HttpServletResponse response) throws IOException {
        PublicUtil.decideBeforeDelete(id, tid, response, tenderItemService, riskGuarantyMoneyService);
    }

    //list去重
    public ArrayList singleElement(ArrayList al) {
        ArrayList newAl = new ArrayList();

        for (Iterator it = al.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (!obj.equals(" ")) {
                if (!newAl.contains(obj)) {
                    newAl.add(obj);
                }
            }
        }
        return newAl;
    }
}
