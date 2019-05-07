package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.*;
import com.ptpl.service.*;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/18.
 */
@Controller
@RequestMapping("/admin/tenderFrontEnd")
public class TenderFrontEndController extends BaseController {
    @Autowired
    TenderFrontEndServiceI tenderFrontEndService;
    @Autowired
    private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteService;//借款类型service
    @Autowired
    private TenderFrontEndSingleServiceI tenderFrontEndSingleService;//单标前端信息
    @Autowired
    private TenderItemServiceI tenderItemService;
    @Autowired
    private UserCommonMaterialServiceI userCommonMaterialService;
    @Autowired
    private TenderFeiTypeServiceI tenderFeiTypeService;


    //查询List
    @RequestMapping(value = "list")
    public ModelAndView list(TenderFrontEnd tenderFrontEnd) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<String, Object>();
        initPage(map, pageNum, pageSize);
        List<TenderFrontEnd> list = tenderFrontEndService.selectByCondition(tenderFrontEnd);
        if (list != null && list.size() > 0) {
            for (TenderFrontEnd t : list) {
                Integer source = t.getSource();
                //引用
                if (source == 2) {
                    String content = t.getContent();
                    //把所引用的公共资料名作为content展示
                    if (content != null && !content.equals("")) {
                        List<UserCommonMaterial> userCommonMaterials = userCommonMaterialService.selectByLIQNo(content);
                        if (userCommonMaterials != null && userCommonMaterials.size() > 0) {
                            String materialname = userCommonMaterials.get(0).getMaterialname();
                            t.setContent(materialname);
                        }
                    }
                }
            }
        }
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        List<TenderFeiType> tenderFeiTypes = tenderFeiTypeService.selectAllTenderFeiType();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/tenderFrontEnd/tenderFrontEnd_list");
        mav.addObject("tenderFeiTypes",tenderFeiTypes);
        mav.addObject("pagehelper", pagehelper);
        return mav;
    }

    //查看内容
    @RequestMapping(value = "content_UI")
    public ModelAndView content_UI(BigDecimal id) {
        if (id != null) {
            TenderFrontEnd tenderFrontEnd = tenderFrontEndService.selectByPrimaryKey(id);
            Short infoattribute = tenderFrontEnd.getInfoattribute();
            ModelAndView mav = new ModelAndView();
            //图片则要编辑好图片的标题和图处的内容key-value的形式
            if (infoattribute == 1) {
                String pictitle1 = tenderFrontEnd.getPictitle();
                String content1 = tenderFrontEnd.getContent();
                if (pictitle1 != null && !pictitle1.equals("") && content1 != null && !content1.equals("")) {
                    String[] pictitle = pictitle1.split(",");
                    String[] content = content1.split(",");
                    HashMap<String, String> pictitleContent = new HashMap<>();
                    for (int i = 0; i < content.length; i++) {
                        pictitleContent.put(content[i], pictitle[i]);
                    }
                    mav.addObject("pictitleContent", pictitleContent);
                }
            } else if (infoattribute == 4) {
                List<String> contentArr = new ArrayList<>();
                //系统自动初始化8个标签选项:1，2，3，4，5，6，7
                String content = "1,2,3,4,5,6,7";
                if (content != null && !content.equals("")) {
                    String[] con = content.split(",");
                    for (int i = 0; i < con.length; i++) {
                        contentArr.add(con[i]);
                    }
                }
                mav.addObject("contentArr", contentArr);
            }
            mav.addObject("tenderFrontEnd", tenderFrontEnd);
            mav.setViewName("admin/tenderFrontEnd/content_tenderFrontEnd");
            return mav;
        }
        return null;
    }

    //保存内容
    @RequestMapping(value = "saveContent")
    public ModelAndView saveContent(TenderFrontEnd tenderFrontEnd, @RequestParam("files") CommonsMultipartFile[] files) throws Exception {
        //图片
        String data = tenderFrontEndService.updateFrontAndSingle(tenderFrontEnd, files);
        if (data.equals("success")) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("redirect:/admin/tenderFrontEnd/list.action");
            return mv;
        } else {
            PublicUtil.sendJsonData(response, "fail!");
            return null;
        }
    }

    //新增页面
    @RequestMapping(value = "insert_UI")
    public ModelAndView insert_UI() {
        List<LoanTypeObjectQuote> LoanTypeObjectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        List<TenderFeiType> tenderFeiTypes = tenderFeiTypeService.selectAllTenderFeiType();
        logger.info("LoanTypeObjectQuotes:************************************" + LoanTypeObjectQuotes);
        if (LoanTypeObjectQuotes != null && LoanTypeObjectQuotes.size() > 0) {
            logger.info("LoanTypeObjectQuote(1)***********************************:" + LoanTypeObjectQuotes.get(0).getObjectname());
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("loanTypeObjects", LoanTypeObjectQuotes);
        mav.addObject("tenderFeiTypes",tenderFeiTypes);
        mav.setViewName("admin/tenderFrontEnd/insert_TenderFrontEnd");
        return mav;
    }


    //新增
    @RequestMapping(value = "insert")
    public ModelAndView saveTenderFrontEnd(TenderFrontEnd tenderFrontEnd, @RequestParam("files") CommonsMultipartFile[] files) throws Exception {

        //标的类型名 和 标的类型Id,前端传来的是字符串数组类型的数据
        String ttypenames = tenderFrontEnd.getTtypename();
        String[] split = ttypenames.split(",");
        String ttypeName = split[0];
        String ttypeid = split[1];
        //根据标类型id找标类型对象
        LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteService.selectByPrimaryKey(new BigDecimal(ttypeid));
        //得到标类型对象序号(1-30)
        Short serialno = loanTypeObjectQuote.getSerialno();
        //标的类型Id
        tenderFrontEnd.setTtypeid(new BigDecimal(ttypeid));
        //标的类型名
        tenderFrontEnd.setTtypename(ttypeName);
        //获取最后一个编号，并判断是否存在
        String infoNo = tenderFrontEndService.selectLastInfoNo();
        if (infoNo != null && !infoNo.equals("")) {
            String str = infoNo.substring(0, 4);
            int i = Integer.parseInt(infoNo.substring(4));
            tenderFrontEnd.setInfono(str + (++i));
        } else {
            tenderFrontEnd.setInfono("QDJZ10001");
        }
        String data = tenderFrontEndService.insertFrontAndSingle(tenderFrontEnd, files, serialno);
        if (data.equals("success")) {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("redirect:/admin/tenderFrontEnd/list.action");
            return mv;

        } else {
            PublicUtil.sendJsonData(response, "fail!");
            return null;
        }

    }




    //编辑页面
    @RequestMapping(value = "update_UI")
    public ModelAndView update_UI(BigDecimal id) {
        if (id != null) {
            TenderFrontEnd tenderFrontEnd = tenderFrontEndService.selectByPrimaryKey(id);
            if (tenderFrontEnd != null) {
                List<LoanTypeObjectQuote> LoanTypeObjectQuotes = loanTypeObjectQuoteService.gettypeObjectQuotes(null);
                ModelAndView mav = new ModelAndView();
                mav.addObject("loanTypeObjects", LoanTypeObjectQuotes);
                mav.addObject("tenderFrontEnd", tenderFrontEnd);
                mav.setViewName("admin/tenderFrontEnd/update_TenderFrontEndByPic");
                return mav;
            }
        }
        return null;
    }


    //编辑
    @RequestMapping(value = "update")
    public void update(TenderFrontEnd tenderFrontEnd) throws Exception {
        String data = tenderFrontEndService.updateInfoname(tenderFrontEnd);
        sendJsonData(response, JSON.toJSONString(data));
    }



    //删除页面
    @RequestMapping(value = "del_UI")
    public ModelAndView del_UI(BigDecimal id) {
        TenderFrontEnd tenderFrontEnd = tenderFrontEndService.selectByPrimaryKey(id);
        if (tenderFrontEnd != null) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("tenderFrontEnd", tenderFrontEnd);
            mav.setViewName("admin/tenderFrontEnd/delete_TenderFrontEnd");
            return mav;
        }
        return null;
    }

    //删除
    @RequestMapping(value = "delete")
    public void removeTenderFrontEnd(BigDecimal id) throws Exception {
        String data = "fail";
        if (id != null) {
            TenderFrontEnd tenderFrontEnd = tenderFrontEndService.selectByPrimaryKey(id);
            Short status = tenderFrontEnd.getStatus();
            if (status == 1) {
                data = "提示: 项目处于启用状态,无法删除";
            } else {
                data = tenderFrontEndService.deleteFrontAndSingle(tenderFrontEnd);
            }

        }
        sendJsonData(response, JSON.toJSONString(data));
    }

    //启用和停用
    @RequestMapping(value = "tostaratQuote", method = {RequestMethod.POST, RequestMethod.GET})
    public void loantostaratQuote(BigDecimal id, String str) throws Exception {

        if (id != null && !str.equals("")) {
            TenderFrontEnd tenderFrontEnd = new TenderFrontEnd();
            tenderFrontEnd.setId(id);
            if (str.equals("t")) {//停用
                tenderFrontEnd.setStatus((short) 0);
            }
            if (str.equals("q")) {//启用
                tenderFrontEnd.setStatus((short) 1);
            }
            int i = tenderFrontEndService.updateByPrimaryKeySelective(tenderFrontEnd);
            if (i > 0) {
                PublicUtil.sendJsonData(response, "操作成功!");
            }
        }
    }


    //引入页面
    @RequestMapping(value = "Import_UI")
    public ModelAndView ImportUI(BigDecimal id) {
        if (id != null) {
            TenderFrontEnd tenderFrontEnd = tenderFrontEndService.selectByPrimaryKey(id);
            //标的类型Id(loanTypeObjectQuote表Id)
            BigDecimal ttypeid = tenderFrontEnd.getTtypeid();
            LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteService.selectByPrimaryKey(ttypeid);
            Short serialno = loanTypeObjectQuote.getSerialno();
            TenderItem tenderItem = new TenderItem();
            tenderItem.setTpro(serialno);
            //通过标类型查找还没有发标的标
            List<TenderItem> tenderItems = tenderItemService.getTenderItemBytpro(tenderItem);
            if (tenderItem != null && tenderItems.size() > 0) {
                //根据标id找出借款申请对象，再根据借款申请对象找到申请人，再根据申请人找到申请人所填写的公共资料
                List<UserCommonMaterial> userCommonMaterials = userCommonMaterialService.selectByTenderitemId(tenderItems.get(0).getId());
                ModelAndView mav = new ModelAndView();
                if (userCommonMaterials != null && userCommonMaterials.size() > 0) {
                    mav.addObject("userCommonMaterials", userCommonMaterials);
                }
//                mav.addObject("loanTypeObjects", LoanTypeObjectQuotes);
                mav.addObject("tenderFrontEnd", tenderFrontEnd);
                mav.setViewName("admin/tenderFrontEnd/import_content");
                return mav;

            }
        }
        return null;
    }

    //引入  :各个单标信息引入对应的公共资料内容
    @RequestMapping(value = "import")
    public void importCommonMaterial(String liqno, String id) throws Exception {
        //得到标的前端信息对象
        TenderFrontEnd tenderFrontEnd = tenderFrontEndService.selectByPrimaryKey(new BigDecimal(id));
        String data = tenderFrontEndService.updateForImport(liqno,tenderFrontEnd);
        sendJsonData(response, JSON.toJSONString(data));
    }
}
