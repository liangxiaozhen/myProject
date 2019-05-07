package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.*;
import com.ptpl.service.LoanInfoNeedServiceI;
import com.ptpl.service.LoanInfoPresetServiceI;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import com.ptpl.service.MultiContentSetServiceI;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author liuj
 *         借款资料自填类选项设置
 */
@RequestMapping(value = "/admin/loanInfo")
@Controller
public class LoanInfoController extends BaseController {

    @Autowired
    private LoanInfoNeedServiceI loanInfoNeedMapperService;
    @Autowired
    private LoanInfoPresetServiceI loanInfoPresetService;
    @Autowired
    private MultiContentSetServiceI multiContentSetService;
    @Autowired
    private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteService;

    //选择类添加
    @RequestMapping(value = "/loanInfoPreset_ui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loanInfoPreset_ui() {
            ModelAndView mv = new ModelAndView();
        List<MultiContentSet> contentSets = multiContentSetService.selectALLisneedmult();
        String conte = "";
        for (int i = 0; i < contentSets.size(); i++) {
            conte += contentSets.get(i).getMultino() + " ";
        }
        List<String> list = PublicUtil.singleElement(new ArrayList<String>(Arrays.asList(conte.split(" "))));
        List<MultiContentSet> multiContentSets = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            MultiContentSet contentSet = new MultiContentSet();
            contentSet.setMultino(list.get(i));
            multiContentSets.add(contentSet);
        }
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        mv.addObject("MultiNo", multiContentSets);
        mv.addObject("contentSets", contentSets);
        mv.addObject("objectQuotes", objectQuotes);
        mv.setViewName("admin/loanInfo/loanInfoPreset_Inst");
        return mv;
    }

    //自填类添加
    @RequestMapping(value = "/loanInfoNeed_ui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loanInfoNeed_ui() {
        ModelAndView mv = new ModelAndView();
        //加载引用对象:净值标 车贷 房产标 车贷标
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        mv.addObject("objectQuotes", objectQuotes);
        mv.setViewName("admin/loanInfo/loanInfoNeed_Inst");
        return mv;
    }

    //选择类多选内容设置表
    @RequestMapping(value = "/MultiContentSet_ui", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView MultiContentSet_ui() {
        ModelAndView mv = new ModelAndView();
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        mv.addObject("objectQuotes", objectQuotes);
        mv.setViewName("admin/loanInfo/loanInfoNeed_Inst");
        return mv;
    }

    //查询(自填类)
    @RequestMapping(value = "/selectAllNeed", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAllNeed(LoanInfoNeed infoNeed, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        List<LoanInfoNeed> list = loanInfoNeedMapperService.selectByConditionLoaninfo(infoNeed);
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        mv.addObject("pagehelper", pagehelper);
        mv.setViewName("admin/loanInfo/loanInfoNeed_List");
        return mv;
    }

    //新增(自填类)
    @RequestMapping(value = "/addloanInfoNeed", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addloanInfoNeed(LoanInfoNeed infoNeed, String[] quoteObjects, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            infoNeed.setAddman(adminUser.getUsername());//添加人
        }
        infoNeed.setAddtime(new Date());//添加时间
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        String quoteObject = "";
        if (objectQuotes.size() > 0) {
            quoteObject = StringUtil.setPlaceholderForArr(quoteObjects, objectQuotes.size());
            infoNeed.setQuoteobject(quoteObject);
        }
        String linno = loanInfoNeedMapperService.selectNeedNo();//获取最后一个编号，并判断是否存在
        if (linno != null && !linno.equals("")) {
            String str = linno.substring(0, 4);
            int i = Integer.parseInt(linno.substring(4));
            infoNeed.setLinno(str + (++i));
        } else {
            infoNeed.setLinno("KBJZ10001");
        }
        int i = loanInfoNeedMapperService.insertSelective(infoNeed);
        if (i > 0) {

            mv.setViewName("redirect:/admin/loanInfo/selectAllNeed.action");
        }
        return mv;
    }

    //编辑(自填)
    @RequestMapping(value = "/updateloanInfoNeed", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateloanInfoNeed(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            LoanInfoNeed infoNeed = loanInfoNeedMapperService.selectByPrimaryKey(id);
            List<Integer> allowcugrades = StringUtil.parsStringToList(infoNeed.getQuoteobject());
            List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
            mv.addObject("infoNeed", infoNeed);
            mv.addObject("allowcugrades", allowcugrades);
            mv.addObject("objectQuotes", objectQuotes);
        }
        mv.setViewName("admin/loanInfo/loanInfoNeed_Inst");
        return mv;
    }

    //修改(自填)
    @RequestMapping(value = "/updateloanInfoNeedtwo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateloanInfoNeedtwo(LoanInfoNeed infoNeed, String[] quoteObjects) {
        ModelAndView mv = new ModelAndView();
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            infoNeed.setAddman(adminUser.getUsername());//添加人
        }
        infoNeed.setAddtime(new Date());//添加时间
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        String quoteObject = "";
        if (objectQuotes.size() > 0) {
            quoteObject = StringUtil.setPlaceholderForArr(quoteObjects, objectQuotes.size());
            infoNeed.setQuoteobject(quoteObject);
        }
        int i = loanInfoNeedMapperService.updateByPrimaryKey(infoNeed);
        if (i > 0) {
            mv.setViewName("redirect:/admin/loanInfo/selectAllNeed.action");
        }
        return mv;
    }

    //查询(选择类)
    @RequestMapping(value = "/selectAllPreset", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAllPreset(LoanInfoPreset infoPreset, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        List<LoanInfoPreset> list = loanInfoPresetService.selectByConditionLoaninfo(infoPreset);
        List<MultiContentSet> contentSets = multiContentSetService.selectAllMult(null);
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        mv.addObject("pagehelper", pagehelper);
        mv.addObject("contentSets", contentSets);
        mv.setViewName("admin/loanInfo/loanInfoPreset_List");
        return mv;
    }

    //更新(选择类)
    @RequestMapping(value = "/toupdatePreset", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toupdatePreset(BigDecimal id) {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            LoanInfoPreset infoPreset = loanInfoPresetService.selectByPrimaryKey(id);
            List<Integer> integers = StringUtil.pars(infoPreset.getQuoteobject());
            List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
            List<MultiContentSet> contentSets = multiContentSetService.selectAllMult(null);
            mv.addObject("infoPreset", infoPreset);
            mv.addObject("integers", integers);
            mv.addObject("objectQuotes", objectQuotes);
            mv.addObject("contentSets", contentSets);
        }
        mv.setViewName("admin/loanInfo/loanInfoPreset_Inst");
        return mv;
    }

    //修改(选择类)
    @RequestMapping(value = "/updateloanInfoPreset", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toupdatePreset(LoanInfoPreset infoPreset, String[] quoteobjects) {
        ModelAndView mv = new ModelAndView();
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            infoPreset.setAddman(adminUser.getUsername());
        }
        infoPreset.setAddtime(new Date());
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        String quote = "";
        if (quoteobjects.length > 0) {
            quote = StringUtil.setPlaceholderForArr(quoteobjects, objectQuotes.size());
            infoPreset.setQuoteobject(quote);
        }
        int i = loanInfoPresetService.updateByPrimaryKeySelective(infoPreset);
        if (i > 0) {
            mv.setViewName("redirect:/admin/loanInfo/selectAllPreset.action");
        }
        return mv;
    }

    //添加(选择类)
    @RequestMapping(value = "/addloanInfoPreset", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addloanInfoPerest(LoanInfoPreset infoPreset, String[] quoteobjects) throws Exception {
        ModelAndView mv = new ModelAndView();
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
        if (adminUser != null) {
            infoPreset.setAddman(adminUser.getUsername());//添加人
        }
        infoPreset.setAddtime(new Date());//添加时间
        List<LoanTypeObjectQuote> objectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        String quoteObject = "";
        if (objectQuotes.size() > 0) {
            quoteObject = StringUtil.setPlaceholderForArr(quoteobjects, objectQuotes.size());//将对象转换成对应得字符串
            infoPreset.setQuoteobject(quoteObject);
        }
        String linno = loanInfoPresetService.selectPresetNo();//获取最后一个编号，并判断是否存在
        if (linno != null && !linno.equals("")) {
            String str = linno.substring(0, 3);
            int i = Integer.parseInt(linno.substring(3));
            infoPreset.setLinno(str + (++i));
        } else {
            infoPreset.setLinno("YSZ10001");
        }
        int b = loanInfoPresetService.insertSelective(infoPreset);
        if (b > 0) {
            MultiContentSet contentSet = new MultiContentSet();
            contentSet.setIscite((short) 1);//被引用
            contentSet.setMultino(infoPreset.getMultino());
            multiContentSetService.updateByMultiNo(contentSet);
            mv.setViewName("redirect:/admin/loanInfo/selectAllPreset.action");
        }
        return mv;
    }

    //详情(自填)
    @RequestMapping(value = "/toDetailNeed", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toDetailNeed(BigDecimal id) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            LoanInfoNeed infoNeed = loanInfoNeedMapperService.selectByPrimaryKey(id);
            mv.addObject("infoNeed", infoNeed);
        }
        mv.setViewName("admin/loanInfo/loanInfoNeed_Detail");
        return mv;
    }

    //详情(多选内容)
    @RequestMapping(value = "/toDetailContentSet", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toDetailContentSet(BigDecimal id) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            MultiContentSet contentSet = multiContentSetService.selectByPrimaryKey(id);
            mv.addObject("contentSet", contentSet);
        }
        mv.setViewName("admin/loanInfo/multiContentSet_Detail");
        return mv;
    }

    //删除(自填)
    @RequestMapping(value = "/toDeleteNeed", method = {RequestMethod.POST, RequestMethod.GET})
    public void toDeleteNeed(BigDecimal id) throws Exception {
        if (id != null) {
            String data = "fail";
            LoanInfoNeed infoNeed = loanInfoNeedMapperService.selectByPrimaryKey(id);
            if (infoNeed.getIscite().toString().equals("1")) {
            } else {
                int i = loanInfoNeedMapperService.deleteByPrimaryKey(id);
                if (i > 0) {
                    data = "suc";
                }
            }
            sendJsonData(response, JSON.toJSONString(data));
        }
    }

    //详情(借款资料选择类项目)
    @RequestMapping(value = "/toDetailPreset", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toDetailPreset(BigDecimal id) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            LoanInfoPreset infoPreset = new LoanInfoPreset();
            infoPreset.setId(id);
            List<LoanInfoPreset> infoPresets = loanInfoPresetService.selectByConditionLoaninfo(infoPreset);
            if (infoPresets.size() > 0) {
                mv.addObject("infoPreset", infoPresets.get(0));
            }
        }
        mv.setViewName("admin/loanInfo/loanInfoPreset_Detail");
        return mv;
    }

    //删除(选择)
    @RequestMapping(value = "/toDeletePreset", method = {RequestMethod.POST, RequestMethod.GET})
    public void toDeletePreset(BigDecimal id) throws Exception {
        if (id != null) {
            String data = "";
            LoanInfoPreset infoPreset = loanInfoPresetService.selectByPrimaryKey(id);
            if (infoPreset.getIscite().toString().equals("1")) {
                data = "fail";
            } else {
                int i = loanInfoPresetService.deleteByPrimaryKey(id);
                if (i > 0) {
                    MultiContentSet contentSet = new MultiContentSet();
                    contentSet.setIscite((short) 0);//去掉引用
                    contentSet.setMultino(infoPreset.getMultino());
                    multiContentSetService.updateByMultiNo(contentSet);
                    data = "succ";
                }
            }
            sendJsonData(response, JSON.toJSONString(data));
        }
    }

    //删除(多选内容设置)
    @RequestMapping(value = "/toDeleteContent", method = {RequestMethod.POST, RequestMethod.GET})
    public void toDeleteContent(BigDecimal id) throws Exception {
        if (id != null) {
            String data = "fail";
            MultiContentSet contentSet = multiContentSetService.selectByPrimaryKey(id);
            if (contentSet.getIscite().toString().equals("1")) {
            } else {
                int i = multiContentSetService.deleteByPrimaryKey(id);
                if (i > 0) {
                    data = "succ";
                }
            }
            sendJsonData(response, JSON.toJSONString(data));
        }
    }

    //多选内容名称的编号ajax
    @RequestMapping(value = "/tochange", method = {RequestMethod.POST, RequestMethod.GET})
    public void tochange(String muno) throws IOException {
        if (muno != null) {
            List<MultiContentSet> contentSets = multiContentSetService.selectByMultiNo(muno);
            if (contentSets.size() > 0) {
                List<String> strings = new ArrayList<>();
                for (int i = 0; i < contentSets.size(); i++) {
                    strings.add(i, contentSets.get(i).getOptionname());
                }
                PublicUtil.sendJsonData(response, strings.toString());
            }
        }
    }

    //查询选择类多选内容设置表（MultiContentSet）即选择类子项目设置
    @RequestMapping(value = "/selectAllContentSet", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView selectAllContentSet(MultiContentSet contentSet, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        List<MultiContentSet> list = multiContentSetService.selectAllMult(contentSet);
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        mv.addObject("pagehelper", pagehelper);
        mv.setViewName("admin/loanInfo/multiContentSet_List");
        return mv;
    }

    //增加(选择类多选内容设置表)
    @RequestMapping(value = "/addMultiContentSetone", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addMultiContentSetone(String multino, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (multino != null && !multino.equals("")) {
            mv.addObject("multino", multino);
        } else {
            String str = multiContentSetService.selectLastNo();//DXNRZl0003
            if(str!=null&&!str.equals("")){
                String strfast = str.substring(0, 5);//DXNRZ
                int integer = Integer.parseInt(str.substring(5));
                str = strfast + (++integer);
            }else {
                str="DXNRZ10001";
            }
            mv.addObject("str", str);
        }
        mv.setViewName("admin/loanInfo/multiContentSet_Inst");
        return mv;
    }

    //增加(选择类多选内容设置表)
    @RequestMapping(value = "/addContent", method = {RequestMethod.POST, RequestMethod.GET})
    public void addMultiContentSet(MultiContentSet contentSet, HttpServletRequest request) throws Exception {
        if (contentSet != null) {
            contentSet.setIscite((short) 0);//是否被引用
            int i = multiContentSetService.insertSelective(contentSet);
            if (i > 0) {
                PublicUtil.sendJsonData(response, "增加成功");
            } else {
                PublicUtil.sendJsonData(response, "增加失败");
            }
        } else {
            PublicUtil.sendJsonData(response, "增加失败");
        }
    }

    //更新(多选内容)
    @RequestMapping(value = "/toupdateContentSet", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toupdateContentSet(BigDecimal id) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            MultiContentSet contentSet = multiContentSetService.selectByPrimaryKey(id);
            mv.addObject("contentSet", contentSet);
        }
        mv.setViewName("admin/loanInfo/multiContentSet_Inst");
        return mv;
    }

    //更新
    @RequestMapping(value = "/UpdateContent", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView UpdateContent(MultiContentSet contentSet) {
        ModelAndView mv = new ModelAndView();
        if (contentSet != null) {
            int i = multiContentSetService.updateByPrimaryKey(contentSet);
            if (i > 0) {
                mv.setViewName("redirect:/admin/loanInfo/selectAllContentSet.action");
            }
        }
        return mv;
    }

    //增加(选择类多选内容设置表)
    @RequestMapping(value = "/addMultiContentSettwo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addMultiContentSettwo(MultiContentSet contentSet, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<>();
        initPage(map, pageNum, pageSize);
        List<MultiContentSet> list = multiContentSetService.selectAllMult(contentSet);
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        mv.addObject("pagehelper", pagehelper);
        mv.setViewName("admin/loanInfo/multiContentSet_List");
        return mv;
    }

    //自填类启用和停用
    @RequestMapping(value = "toenableneed", method = {RequestMethod.POST, RequestMethod.GET})
    public void Toenableneed(BigDecimal id, String str) throws Exception {
        if (id != null && !str.equals("")) {
            LoanInfoNeed infoNeed = new LoanInfoNeed();
            infoNeed.setId(id);
            if (str.equals("t")) {//停用
                infoNeed.setIsneed((short) 0);
            }
            if (str.equals("q")) {//启用
                infoNeed.setIsneed((short) 1);
            }
            int i = loanInfoNeedMapperService.updateByPrimaryKeySelective(infoNeed);
            if (i > 0) {
                PublicUtil.sendJsonData(response, "操作成功!");
            }
        }
    }

    //选择类启用和停用
    @RequestMapping(value = "toopen", method = {RequestMethod.POST, RequestMethod.GET})
    public void toopen(BigDecimal id, String str) throws Exception {
        if (id != null && !str.equals("")) {
            LoanInfoPreset infoPreset = new LoanInfoPreset();
            infoPreset.setId(id);
            if (str.equals("t")) {//停用
                infoPreset.setIsneed((short) 0);
            }
            if (str.equals("q")) {//启用
                infoPreset.setIsneed((short) 1);
            }
            int i = loanInfoPresetService.updateByPrimaryKeySelective(infoPreset);
            if (i > 0) {
                PublicUtil.sendJsonData(response, "操作成功!");
            }
        }
    }

    //多选项子项目设置启用和和停用设置
    @RequestMapping(value = "toopenmulti", method = {RequestMethod.POST, RequestMethod.GET})
    public void toopenmulti(BigDecimal id, String str) throws Exception {
        if (id != null && !str.equals("")) {
            MultiContentSet contentSet = new MultiContentSet();
            contentSet.setId(id);
            if (str.equals("f")) {
                contentSet.setIsneed((short) 0);
            }
            if (str.equals("s")) {
                contentSet.setIsneed((short) 1);
            }
            int i = multiContentSetService.updateByPrimaryKeySelective(contentSet);
            if (i > 0) {
                PublicUtil.sendJsonData(response, "操作成功!");
            }
        }
    }
}
