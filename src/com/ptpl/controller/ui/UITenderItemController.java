package com.ptpl.controller.ui;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.TenderItem_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ItemDetailDesc;
import com.ptpl.model.TenderFeiType;
import com.ptpl.model.TenderFrontEndSingle;
import com.ptpl.model.TenderItem;
import com.ptpl.service.ItemDetailDescServiceI;
import com.ptpl.service.TenderFrontEndSingleServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 标模块 前端接口
 */
@RequestMapping(value = "/send")
@Controller
public class UITenderItemController extends BaseController {

    @Autowired
    private TenderItemServiceI tenderItemService;// 标的service

    @Autowired
    private ItemDetailDescServiceI itemDetailDescService;//标的详情

    @Autowired
    private TenderFrontEndSingleServiceI tenderFrontEndSingleService;

    @RequestMapping(value = "ui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView ui() {
        return new ModelAndView("admin/loans/NewFile");
    }

    //标项目模块（旧的）
    @RequestMapping(value = "callbackmoduleType", method = {RequestMethod.GET, RequestMethod.POST})
    public void desc(HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        List<ItemDetailDesc> descs = itemDetailDescService.callbackmoduleType();
        out.println(serialize(descs));
        out.flush();
        out.close();
    }

    //标模块详情（旧的）
    @RequestMapping(value = "callbackitemDesc", method = {RequestMethod.GET, RequestMethod.POST})
    public void callbackitemDesc(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        String id = request.getParameter("id");//标的ID
        String module = request.getParameter("moduletype");//接受返回的参数 模块类别
        if (id != null && !id.equals("") && module != null && !module.equals("")) {
            String moduletype = new String(module.getBytes("ISO8859-1"), "UTF-8");
            if (moduletype != null && !moduletype.equals("")) {//如果都不为空
                List<ItemDetailDesc> descs = itemDetailDescService.callbackitemDesc(moduletype, new BigDecimal(id));
                if (descs.size() > 0) {
                    out.println(serialize(descs));
                } else {
                    out.println("输入的参数值有误!");
                }
            } else {
                out.println("输入的参数为空!");
            }
        } else {
            out.println("输入的参数为空!");
        }
    }


    //PC端单标前端信息
    @RequestMapping(value = "selectByTno", method = {RequestMethod.GET, RequestMethod.POST})
    public void selectByTno(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        String tno = request.getParameter("tno");
        HashMap<String, Object> map = new HashMap<>();
        if (tno != null && !"".equals(tno)) {
            List<TenderFrontEndSingle> singles = tenderFrontEndSingleService.selectByTno(tno);
            if (singles != null && singles.size() > 0) {
                for (TenderFrontEndSingle t : singles) {
                    TenderFeiType tenderFeiType = t.getTenderFeiType();
                    if (tenderFeiType != null) {
                        String typename = tenderFeiType.getTypename();
                        t.setTypename(typename);
                        t.setTenderFeiType(null);
                        t.setInfotype(null);
                    }

                    //这里要加if（类别不为"详细描述"时）才执行如下----以后可能要加判断
                    String content = t.getContent();
                    if (content != null && !"".equals(content)) {
                        content = content.replace("<p>", "").replace("</p>", "").replace("<br/>", "");
                    }
                    t.setContent(content);
                }
                map.put("list", singles);
                sendJsonData(response, JSON.toJSONString(map));
            }
        } else {
            map.put("result", "没有数据");
            sendJsonData(response, JSON.toJSONString(map));
        }

    }


    //首页新标专区
    @RequestMapping(value = "moremoneyhomepage", method = {RequestMethod.POST, RequestMethod.GET})
    public void moremoneysend(HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        int num = 1;
        int size = 5;
        PageHelper.startPage(num, size);
        PageHelper.orderBy("putontime desc");//上架时间
        //Displaytype=1明标  ONANDDOWN=1已上架 按上架时间排序
        List<TenderItem> items = tenderItemService.callbackdesk();
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                double v = items.get(i).getTinterest() * 100;
                double interest = Arith.mul(items.get(i).getTinterest(), 100);//保留两位小数，4舍5入，解决0.14*100=14.00000000000002的问题
                items.get(i).setInterestrate(tochange(interest) + "%");//利率
                items.get(i).setProgressbar((convert(items.get(i).getFinishtamount() / items.get(i).getTamount())) * 100 + "%");//投标进度
                items.get(i).setLastmoney(formatting(items.get(i).getTamount() - items.get(i).getFinishtamount()));//剩余可投金额
                items.get(i).setTstatusStr(changeShortToString(items.get(i).getTstatus()));
                items.get(i).setTamountstr(formatting(items.get(i).getTamount()));
                items.get(i).setTbegintime(null);
                items.get(i).setTstatus(null);
                items.get(i).setFinishtamount(null);
                items.get(i).setTamount(null);
                items.get(i).setTinterest(null);
            }
            out.println(serialize(items));
        } else {
            out.println(serialize("暂无数据"));
        }
        out.flush();
        out.close();
    }


    //按条件筛选标
    @RequestMapping(value = "moremoneyclk", method = {RequestMethod.POST, RequestMethod.GET})
    public String moremoneyclk(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        String loantimes[] = request.getParameterValues("loantime");//获取投标期限["0,1","2,3","4,5,6","7,8,9,10,11,12","-2"]（-2代表12个月以上）  ["-1"](全部)
    /*      loantimes= new String[]{"2,3","-2"};*/
        String repaymentpros[] = request.getParameterValues("repaymentpro");//获取还款方式["1","2","3","4"]  ["-1"]
//        String repaymentpros[]={"1","2","3","4"};
        String tstatuss[] = request.getParameterValues("tstatus");//标的状态["3","5"]  ["-1"]  3（正在招标） 5（成功借款）

//        String tstatuss[] = {"3","5"};
        Map<String, Object> conditions = new HashMap<String, Object>();
        //判断是否为空 投标期限
        if (loantimes != null) {
            //如果投标期限的数组的长度只有一位,那么可能是-1(全部)和-2(12份以上)
            if (loantimes.length == 1) {
                if (loantimes[0].equals("-1")) {//选择 全部选择项
                    loantimes = new String[]{"0,1", "2,3", "4,5,6", "7,8,9,10,11,12", "-2"};//选择全部时
                    conditions.put("loantimes", changlist(loantimes));
                    conditions.put("selectDay", "selectDay");//选择全部时，把还款期限为天的带上
                    conditions.put("yeargo", "yeargo");
                } else if (loantimes[0].equals("-2")) {//选择12月份以上
                    conditions.put("yeargo", "yeargo");
                } else if (loantimes[0].equals("0,1")) {
                    conditions.put("loantimes", changlist(loantimes));
                    conditions.put("selectDay", "selectDay");//选择0,1时，把还款期限为天的带上
                } else {// "7,8,9,10,11,12"或"4,5,6"或...
                    conditions.put("loantimes", changlist(loantimes));
                }
            } else {//如果投标期限的数组长度不知一位 那么其中可能包含 -2
                for (int i = 0; i < loantimes.length; i++) {//循环投标期限
                    if (loantimes[i].equals("-2")) {//12月份以上
                        conditions.put("yeargo", "yeargo");
                    }
                    if (loantimes[i].equals("0,1")) {
                        conditions.put("selectDay", "selectDay");//选择0,1时，把还款期限为天的带上
                    }
                }
                conditions.put("loantimes", changlist(loantimes));//投标期限
            }
        }

        //还款方式 不为空
        if (repaymentpros != null) {
            if (repaymentpros.length == 1) {//如果还款方式长度只有 一位 ，那么可能是任意一位
                if (repaymentpros[0].equals("-1")) {//全部
                    conditions.put("repaymentpros", null);
                } else {//如果不是全部
                    conditions.put("repaymentpros", repaymentpros);
                }
            } else {//如果数组长度不止一位
                conditions.put("repaymentpros", changlist(repaymentpros));
            }
        }

        //招标状态
        if (tstatuss != null) {
            //如果招标状态数组长度只有一个 ，那么可能是任意一位
            if (tstatuss.length == 1) {
                if (tstatuss[0].equals("-1")) {//全部
                    String[] str = {"2", "3", "4", "5", "6", "7", "8"};
                    conditions.put("tstatuss", changlist(str));
                } else {
                    conditions.put("tstatuss", tstatuss);
                }
            } else {//如果数组长度 不止一位
                conditions.put("tstatuss", changlist(tstatuss));
            }
        //为空的时候也设定值
        } else {
            String[] str = {"2", "3", "4", "5", "6", "7", "8"};
            conditions.put("tstatuss", changlist(str));
        }
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        int num = 1;
        int size = 12;
        if (pageNum != null && !"".equals(pageNum)) {
            num = Integer.parseInt(pageNum);
        }
        if (pageSize != null && !"".equals(pageSize)) {
            size = Integer.parseInt(pageSize);
        }
        PageHelper.startPage(num, size);
        PageHelper.orderBy("putontime desc");//上架时间
        List<TenderItem> items = tenderItemService.getMapConditionsList(conditions);
        HashMap<String, Object> map = new HashMap<>();
        if (items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setInterestrate(tochange(items.get(i).getTinterest()) + "%");//利率
                items.get(i).setProgressbar((convert(items.get(i).getFinishtamount() / items.get(i).getTamount())) * 100 + "%");//投标进度
                items.get(i).setTamountstr(formatting(items.get(i).getTamount()));
                items.get(i).setTstatusStr(changeShortToString((short) items.get(i).getTstatus()));
                items.get(i).setLastmoney(formatting(items.get(i).getTamount() - items.get(i).getFinishtamount()));//剩余可投金额
                items.get(i).setTbegintime(null);
                items.get(i).setTstatus(null);
                items.get(i).setFinishtamount(null);
                items.get(i).setTinterest(null);
                items.get(i).setTamount(null);
                items.get(i).setPutontime(null);
            }
            PageInfo<TenderItem> pagehelper = new PageInfo<TenderItem>(items);
            map.put("list", pagehelper.getList());
            map.put("pageNum", pagehelper.getPageNum());
            map.put("pageSize", pagehelper.getPageSize());
            map.put("totalPageSize", pagehelper.getLastPage());
            map.put("total", pagehelper.getTotal());
            System.out.println(map);
        } else {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("result", "暂无记录");//用户未登陆
            String str = JSON.toJSONString(hashMap);
            StringUtil.sendJsonData(response, str);
        }
        sendJsonData(response, JSON.toJSONString(map));
        return null;
    }

    //投标界面中标的详情（标的进度、剩余时间等）
    @RequestMapping(value = "moremoneydetail", method = {RequestMethod.POST, RequestMethod.GET})
    public void moremoneydetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out;
        out = response.getWriter();
        String id = request.getParameter("id");//获取传回的参数标的id
        if (id != null && !id.equals("")) {
            TenderItem tenderItem = tenderItemService.callbackByid(new BigDecimal(id));//通过标的id获取标的信息
            if (tenderItem != null) {
                tenderItem.setTamountstr(formatting(tenderItem.getTamount()));//转换金额格式
                tenderItem.setInterestrate(tenderItem.getTinterest() + "%");//年利率
                tenderItem.setProgressbar(convert((tenderItem.getFinishtamount() / tenderItem.getTamount()) * 100) + "%");//投标进度
                tenderItem.setLastmoney(tenderItem.getTamount() - tenderItem.getFinishtamount() == 0 ? "0" : formatting(tenderItem.getTamount() - tenderItem.getFinishtamount()));//剩余金额
                tenderItem.setTbegintimeStr(changedate(tenderItem.getTbegintime()));//标的开始时间
                tenderItem.setTendtimestr(changedate(tenderItem.getTendtime()));//标的结束时间
                tenderItem.setRepaymentprostr(TenderItem_Constant.REPAYMENTPRO_MAP.get(tenderItem.getRepaymentpro()));//还款方式
                tenderItem.setValuerulestr(TenderItem_Constant.VALUERULE_MAP.get(tenderItem.getValuerule()));//起息日
                tenderItem.setIsmultiplestr(tenderItem.getIsmultiple() == 1 ? "起投金额整数倍" : "非起投金额整数倍");
                tenderItem.setMinoncetamountStr("最低" + StringUtil.quLing(String.valueOf(tenderItem.getMinoncetamount())) + "元");
                tenderItem.setTstatusStr(changeShortToString((short) tenderItem.getTstatus()));
                tenderItem.setTbegintime(null);
                tenderItem.setTendtime(null);
                tenderItem.setMinoncetamount(null);
                tenderItem.setIsmultiple(null);
                tenderItem.setTstatus(null);
                tenderItem.setTamount(null);//标的金额
                tenderItem.setTinterest(null);//标的利息
                tenderItem.setLastretdate(null);//最后还款日期
                tenderItem.setRetdate(null);//首次还款日期
                tenderItem.setRepaymentpro(null);
                tenderItem.setFinishtamount(null);
                tenderItem.setValuerule(null);
                out.println(serialize(tenderItem));
            } else {
                out.println("该参数值无效!");
            }
        } else {
            out.println("输入的参数有误!");
        }
        out.flush();
        out.close();
    }

    //数组转list集合
    static List<String> changlist(String[] str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i] + ",");
        }
        List<String> strings = Arrays.asList(sb.toString().split(","));
        return strings;
    }

    //对象转jsoN
    public static <T> String serialize(T object) {
        return JSON.toJSONString(object);
    }


    //截取小数点后两位
    static double convert(double value) {
        long l1 = Math.round(value * 100);//四舍五入
        double ret = l1 / 100.0;
        return ret;
    }

    //判断小数点后面是否为零
    static Object tochange(double val) {
        int fi = (int) val;
        if (val == fi)
            return fi;
        else
            return val;
    }

    //将一个数如:40000变成40,000.00格式
    static String formatting(double val) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        if (val == 0) {
            return "0.00";
        } else {
            return df.format(val);
        }
    }

    //还款方式的
    //日期转换
    static String changedate(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    //两个日期相差月数
    public static int daysBetween(Date bdate, Date smdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    //把封装标的状态
    private String changeShortToString(short tstatus) {
        String tstatusStr = "";
        switch (tstatus) {
            //待投标
            case 2:
                tstatusStr = "待发布";
                break;
            //投标中
            case 3:
                tstatusStr = "立即投资";
                break;
            //已流标
            case 4:
                tstatusStr = "已流标";
                break;
            //待放款
            case 5:
                tstatusStr = "满标待审";
                break;
            //待生成还款计划
            case 6:
                tstatusStr = "满标待审";
                break;
            //还款中
            case 7:
                tstatusStr = "还款中";
                break;
            //已完成
            case 8:
                tstatusStr = "已完成";
                break;
        }
        return tstatusStr;
    }
}
