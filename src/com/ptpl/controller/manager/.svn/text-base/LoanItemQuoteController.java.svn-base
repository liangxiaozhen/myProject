package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.*;
import com.ptpl.service.*;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author liuj
 *         借款资料项目引用设置
 */
@RequestMapping(value = "/admin/loanItem")
@Controller
public class LoanItemQuoteController extends BaseController {

    @Autowired
    private LoanItemQuoteServiceI loanItemQuoteService;

    @Autowired
    private LoanInfoNeedServiceI loanInfoNeedMapperService;

    @Autowired
    private LoanInfoPresetServiceI loanInfoPresetService;

    @Autowired
    private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteService;

    //用户公共资料记录
    @Autowired
    private UserCommonMaterialServiceI userCommonMaterialService;

    //查询
    @RequestMapping(value = "/selectAllQuote", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAllQuote(LoanItemQuote itemQuote) throws Exception {
        ModelAndView mv = new ModelAndView();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        List<LoanItemQuote> list = loanItemQuoteService.selectByConditionLoaninfo(itemQuote);
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        mv.addObject("pagehelper", pagehelper);
        mv.setViewName("admin/loanItemQuote/loanItemQuote_List");
        return mv;
    }

    //新增页面
    @RequestMapping(value = "/addloanItemQuote_ui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addloanItemQuote_ui() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<LoanInfoNeed> infoNeeds = loanInfoNeedMapperService.selectNeedByiscite();//查询自填类所有启用且未被引用的项目
        List<LoanInfoPreset> infoPresets = loanInfoPresetService.selectPresetByiscite();//查询选择类启用且未被引用的项目
        mv.addObject("infoNeeds", infoNeeds);
        mv.addObject("infoPresets", infoPresets);
        mv.setViewName("admin/loanItemQuote/loanItemQuote_Inst");
        return mv;
    }

    //新增
    @RequestMapping(value = "/addloanItemQuote", method = {RequestMethod.POST, RequestMethod.GET})
    public void addloanItemQuote(LoanItemQuote itemQuote) throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("result", "失败");
        String quoteproperty = request.getParameter("quoteproperty");
        System.out.println(quoteproperty);
        if (itemQuote.getQuotename() != null && !itemQuote.getQuotename().equals("")) {
            //把所引用的项目id set给Quoteobjectid
            itemQuote.setQuoteobjectid(new BigDecimal(itemQuote.getQuotename().split(",")[0]));
            //所引用的项目名称
            itemQuote.setQuotename(itemQuote.getQuotename().split(",")[2]);
        }
        //获取数据库中Liqno的最大编号，在最大编号加1作为Liqno的值
        String strno = loanItemQuoteService.selectNeedNo();
        if (strno == null || strno.equals("")) {
            itemQuote.setLiqno("YYZ10001");
        } else {
            String str_left = strno.substring(0, 3);
            int i = Integer.parseInt(strno.substring(3));
            itemQuote.setLiqno(str_left + (++i));
        }
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            itemQuote.setAddman(adminUser.getUsername());
        }
        itemQuote.setAddtime(new Date());
        Short seriesno = loanItemQuoteService.selectMaxSeriesno1();
        if (seriesno == null) {
            seriesno = 1;
        } else {
            seriesno=(short)(seriesno+1);
        }
        itemQuote.setSeriesno(seriesno);
        int i = loanItemQuoteService.insertSelective(itemQuote);
        if (i > 0) {//把项目设为引用
            setIscite(itemQuote, (short) 1);
            hashMap.put("result", "成功");
        }
        String str = JSON.toJSONString(hashMap);
        sendJsonData(response, str);
    }

    /**
     * @param itemQuote
     * @Description :把选择类项目或自填类项目改为引用（1） 或 不引用（0）
     */
    private void setIscite(LoanItemQuote itemQuote, short num) {
        if (itemQuote.getQuoteproperty() == 1) {
            LoanInfoNeed infoNeed = new LoanInfoNeed();//自填类
            infoNeed.setIscite(num);
            infoNeed.setId(itemQuote.getQuoteobjectid());
            loanInfoNeedMapperService.updateByPrimaryKeySelective(infoNeed);
        }
        if ((itemQuote.getQuoteproperty()) == 2) {//选择类
            LoanInfoPreset infoPreset = new LoanInfoPreset();
            infoPreset.setIscite(num);
            infoPreset.setId(itemQuote.getQuoteobjectid());
            loanInfoPresetService.updateByPrimaryKeySelective(infoPreset);
        }
    }

    //详情
    @RequestMapping(value = "/toDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toDetail(BigDecimal id) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            LoanItemQuote itemQuote = loanItemQuoteService.selectByPrimaryKey(id);
            //引用项目属性（1自填，2预设置）
            Short quoteproperty = itemQuote.getQuoteproperty();
            if (quoteproperty == 1) {
                loanItemQuoteService.public_ZiTian();
            } else if (quoteproperty == 2) {
                loanItemQuoteService.public_XuanZe();
            }
            mv.addObject("itemQuote", itemQuote);
        }
        mv.setViewName("admin/loanItemQuote/loanItemQuote_Deti");
        return mv;
    }

    //删除
    @RequestMapping(value = "/toDelete", method = {RequestMethod.POST, RequestMethod.GET})
    public void toDelete(BigDecimal id) throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("result", "失败");
        if (id != null) {
            LoanItemQuote contentSet = loanItemQuoteService.selectByPrimaryKey(id);
            List<UserCommonMaterial> userCommonMaterials = userCommonMaterialService.selectByLIQNo(contentSet.getLiqno());
            if (contentSet.getIsuse().toString().equals("1")) {//是否启用

            } else if (userCommonMaterials!=null&&userCommonMaterials.size()>0) {
                hashMap.put("result","项目被引用中");
            } else {
                int i = loanItemQuoteService.deleteByPrimaryKey(id);
                setIscite(contentSet, (short) 0);
                if (i > 0) {
                    hashMap.put("result", "成功");
                }
            }
        }
        String str = JSON.toJSONString(hashMap);
        sendJsonData(response, str);
    }



    //更新
    @RequestMapping(value = "/toUpdate", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toUpdate(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            LoanItemQuote itemQuote = loanItemQuoteService.selectByPrimaryKey(id);
            mv.addObject("itemQuote", itemQuote);
        }
        mv.setViewName("admin/loanItemQuote/loanItemQuote_update");
        return mv;
    }

    //修改
    @RequestMapping(value = "/updateloanItem", method = {RequestMethod.POST, RequestMethod.GET})
    public void updateloanItem(LoanItemQuote itemQuote) throws Exception {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("result", "失败");
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            itemQuote.setAddman(adminUser.getUsername());//添加人
        }
        itemQuote.setAddtime(new Date());//添加时间
        //infoattribute;//资料属性(1公共 ，2补充)
        Short quoteproperty = itemQuote.getInfoattribute();
        if (quoteproperty == 1) {
            //把所有公共资料找出
            List<LoanItemQuote> loanItemQuotes_GongGong = loanItemQuoteService.selectAllQuote_GongGong();
            //得到修改后的序号
            short seriesno = itemQuote.getSeriesno();
            //找出DB中没有修改前的对象
            LoanItemQuote loanItemQuoteInDB = null;
            for (LoanItemQuote liq : loanItemQuotes_GongGong) {
                //如果DB有与修改后的LoanItemQuote排序号一样的就要修改对DB一些数据的Seriesno+1或-1，就要得到修改前的对象
                if (liq.getSeriesno() == seriesno) {
                    loanItemQuoteInDB = getLoanItemQuoteById(loanItemQuotes_GongGong, itemQuote.getId());
                    break;
                    //如果DB没有与修改后的LoanItemQuote排序号一样的，就直接执行修改操作
                }
            }
            if (loanItemQuoteInDB != null) {
                LoanItemQuote LIQDB = getLoanItemQuoteBySeriesno(loanItemQuotes_GongGong, seriesno);
                //得到脚标
                int aaindex = loanItemQuotes_GongGong.indexOf(LIQDB);
                Short seriesnoDB = loanItemQuoteInDB.getSeriesno();
                //得到脚标
                int bbindex = loanItemQuotes_GongGong.indexOf(loanItemQuoteInDB);
                //向前改
                if (seriesnoDB > seriesno) {
                    for (int i = aaindex; i < bbindex; i++) {
                        LoanItemQuote LIQ1 = loanItemQuotes_GongGong.get(i);
                        LIQ1.setSeriesno((short) (LIQ1.getSeriesno() + 1));
                        loanItemQuoteService.updateByPrimaryKeySelective(LIQ1);
                    }
                }
                //向后改
                if (seriesnoDB < seriesno) {
                    for (int i = bbindex + 1; i <= aaindex; i++) {
                        LoanItemQuote LIQ2 = loanItemQuotes_GongGong.get(i);
                        LIQ2.setSeriesno((short) (LIQ2.getSeriesno() - 1));
                        loanItemQuoteService.updateByPrimaryKeySelective(LIQ2);
                    }
                }
                int i = loanItemQuoteService.updateByPrimaryKeySelective(itemQuote);
            } else {
                int i = loanItemQuoteService.updateByPrimaryKeySelective(itemQuote);
            }
            hashMap.put("result", "成功");
            String str = JSON.toJSONString(hashMap);
            sendJsonData(response, str);

        } else if (quoteproperty == 2) {
            //把所有补充资料找出
            List<LoanItemQuote> loanItemQuotes_BuChong = loanItemQuoteService.selectAllQuote_BuChong();
            //得到修改后的序号
            short seriesno = itemQuote.getSeriesno();
            //找出DB中没有修改前的对象
            LoanItemQuote loanItemQuoteInDB = null;
            for (LoanItemQuote liq : loanItemQuotes_BuChong) {
                //如果DB有与修改后的LoanItemQuote排序号一样的就要修改对DB一些数据的Seriesno+1或-1，就要得到修改前的对象
                if (liq.getSeriesno() == seriesno) {
                    loanItemQuoteInDB = getLoanItemQuoteById(loanItemQuotes_BuChong, itemQuote.getId());
                    break;
                    //如果DB没有与修改后的LoanItemQuote排序号一样的，就直接执行修改操作
                }
            }
            if (loanItemQuoteInDB != null) {
                LoanItemQuote LIQDB = getLoanItemQuoteBySeriesno(loanItemQuotes_BuChong, seriesno);
                //得到脚标
                int aaindex = loanItemQuotes_BuChong.indexOf(LIQDB);
                Short seriesnoDB = loanItemQuoteInDB.getSeriesno();
                //得到脚标
                int bbindex = loanItemQuotes_BuChong.indexOf(loanItemQuoteInDB);
                //向前改
                if (seriesnoDB > seriesno) {
                    for (int i = aaindex; i < bbindex; i++) {
                        LoanItemQuote LIQ1 = loanItemQuotes_BuChong.get(i);
                        LIQ1.setSeriesno((short) (LIQ1.getSeriesno() + 1));
                        loanItemQuoteService.updateByPrimaryKeySelective(LIQ1);
                    }
                }
                //向后改
                if (seriesnoDB < seriesno) {
                    for (int i = bbindex + 1; i <= aaindex; i++) {
                        LoanItemQuote LIQ2 = loanItemQuotes_BuChong.get(i);
                        LIQ2.setSeriesno((short) (LIQ2.getSeriesno() - 1));
                        loanItemQuoteService.updateByPrimaryKeySelective(LIQ2);
                    }
                }
                int i = loanItemQuoteService.updateByPrimaryKeySelective(itemQuote);
            } else {
                int i = loanItemQuoteService.updateByPrimaryKeySelective(itemQuote);
            }
            hashMap.put("result", "成功");
            String str = JSON.toJSONString(hashMap);
            sendJsonData(response, str);
        }

    }

    private LoanItemQuote getLoanItemQuoteById(List<LoanItemQuote> loanItemQuotes_GongGong, BigDecimal id) {

        for (LoanItemQuote liq : loanItemQuotes_GongGong) {
            if (liq.getId().equals(id)) {
                return liq;
            }
        }
        return null;
    }

    private LoanItemQuote getLoanItemQuoteBySeriesno(List<LoanItemQuote> loanItemQuotes_GongGong, short seriesno) {
        for (LoanItemQuote liq : loanItemQuotes_GongGong) {
            if (liq.getSeriesno() == seriesno) {
                return liq;
            }
        }
        return null;
    }

    //启用和停用
    @RequestMapping(value = "tostaratQuote", method = {RequestMethod.POST, RequestMethod.GET})
    public void loantostaratQuote(BigDecimal id, String str) throws Exception {
        if (id != null && !str.equals("")) {
            LoanItemQuote itemQuote = new LoanItemQuote();
            itemQuote.setId(id);
            if (str.equals("t")) {//停用
                itemQuote.setIsuse((short) 0);
            }
            if (str.equals("q")) {//启用
                itemQuote.setIsuse((short) 1);
            }
            int i = loanItemQuoteService.updateByPrimaryKeySelective(itemQuote);
            if (i > 0) {
                PublicUtil.sendJsonData(response, "操作成功!");
            }
        }
    }
}
