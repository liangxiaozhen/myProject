package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderFrontEnd;
import com.ptpl.model.TenderFrontEndSingle;
import com.ptpl.service.TenderFrontEndServiceI;
import com.ptpl.service.TenderFrontEndSingleServiceI;
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
@RequestMapping("/admin/tenderFrontEndSingle")
public class TenderFrontEndSingleController extends BaseController {
    @Autowired
    TenderFrontEndSingleServiceI tenderFrontEndSingleService;
    @Autowired
    private TenderFrontEndServiceI tenderFrontEndService;



    //查询List
    @RequestMapping(value = "list")
    public ModelAndView queryAll(TenderFrontEndSingle tenderFrontEndSingle) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<String, Object>();
        initPage(map, pageNum, pageSize);
        List<TenderFrontEndSingle> list = tenderFrontEndSingleService.selectByCondition(tenderFrontEndSingle);
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/tenderFrontEndSingle/tenderFrontEndSingle_list");
        mav.addObject("pagehelper", pagehelper);
        return mav;
    }


    //新增
    @RequestMapping(value = "insert")
    public void saveTenderFrontEndSingle(TenderFrontEndSingle tenderFrontEndSingle) throws Exception {
        String data = "fail";
        int iden = tenderFrontEndSingleService.insertSelective(tenderFrontEndSingle);
        if (iden > 0) {
            data = "success";
        }
        sendJsonData(response, JSON.toJSONString(data));
    }

    //查看内容
    @RequestMapping(value = "content_UI")
    public ModelAndView content_UI(BigDecimal id,Integer index) {
        if (id != null) {
            TenderFrontEndSingle tenderFrontEndSingle = tenderFrontEndSingleService.selectByPrimaryKey(id);
            //内容属性（1图片，2文字，3下拉，4标签）
            Short infoattribute = tenderFrontEndSingle.getInfoattribute();
            Short isfixed = tenderFrontEndSingle.getIsfixed();
            ModelAndView mav = new ModelAndView();
            List<String> contentArr = new ArrayList<String>();
            //图片则要编辑好图片的标题和图处的内容key-value的形式
            if (infoattribute == 1) {
                String pictitle1 = tenderFrontEndSingle.getPictitle();
                String content1 = tenderFrontEndSingle.getContent();
                if (pictitle1 != null && !pictitle1.equals("") && content1 != null && !content1.equals("")) {
                    String[] pictitle = pictitle1.split(",");
                    String[] content = content1.split(",");
                    HashMap<String, String> pictitleContent = new HashMap<>();
                    for (int i = 0; i < pictitle.length; i++) {
                        pictitleContent.put(content[i], pictitle[i]);
                    }
                    mav.addObject("pictitleContent", pictitleContent);
                }
                //内容属性：下拉 或 应用范围为唯一且内容属性为标签时
            } else if (infoattribute == 3 || (isfixed == 2 && infoattribute == 4)) {
                String infono = tenderFrontEndSingle.getInfono();
                TenderFrontEnd tenderFrontEnd = tenderFrontEndService.selectByInfono(infono);
                String content = tenderFrontEnd.getContent();
                if (content != null && !content.equals("")) {
                    String[] con = content.split(",");
                    for (int i = 0; i < con.length; i++) {
                        contentArr.add(con[i]);
                    }
                }
            }
            List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition2(tenderFrontEndSingle.getTno());
            //来源为新增且内容为null的对象的数量如果大于1，就在页面中显示“保存并填写下一个”按钮
            if(tenderFrontEndSingles!=null&&tenderFrontEndSingles.size()>1){
                BigDecimal id2 = null;
                for(int i=0;i<tenderFrontEndSingles.size();i++){
                    BigDecimal id1 = tenderFrontEndSingles.get(i).getId();
                    if(id.equals(id1)&&i<(tenderFrontEndSingles.size()-1)){
                        mav.addObject("hasNext","1");
                    }
                }
            }
            mav.addObject("contentArr", contentArr);
            findTenderFrontEndByInfo(tenderFrontEndSingle, mav);
            mav.addObject("tenderFrontEndSingle", tenderFrontEndSingle);
            mav.addObject("index", index);
            mav.setViewName("admin/tenderFrontEndSingle/content_tenderFrontEndSingle");
            return mav;
        }
        return null;
    }


    //查看内容
    @RequestMapping(value = "fill_next")
    public ModelAndView file_next(String tno,BigDecimal id,Integer index) {
        List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition2(tno);
        if(tenderFrontEndSingles!=null&&tenderFrontEndSingles.size()>0){
            BigDecimal id2 = null;
            for(int i=0;i<tenderFrontEndSingles.size();i++){
                BigDecimal id1 = tenderFrontEndSingles.get(i).getId();
                if(id.equals(id1)&&i<(tenderFrontEndSingles.size()-1)){
                    TenderFrontEndSingle tenderFrontEndSingle = tenderFrontEndSingles.get(i + 1);
                    id2 = tenderFrontEndSingle.getId();
                    break;
                }
            }
            ModelAndView mv = new ModelAndView();
            mv.setViewName("redirect:/admin/tenderFrontEndSingle/content_UI.action?id=" + id2+"&index="+(index+1));
            return mv;
        }
        return null;
    }

    //保存内容
    @RequestMapping(value = "saveContent")
    public ModelAndView saveContent(TenderFrontEndSingle tenderFrontEndSingle, @RequestParam("files") CommonsMultipartFile[] files,String hasNext,Integer index) throws Exception {
        String data = "fail";
        Short infoattribute = tenderFrontEndSingle.getInfoattribute();
        tenderFrontEndSingle.setIsedit((short)1);
        //图片
        if (infoattribute == 1) {
            String newfileName = "";
            newfileName = PublicUtil.updateFiles(files, newfileName);
            //有部分图或全部图被删除同时也有新增图，则走以下代码
            if (!newfileName.equals("")) {
                //去除最后一个逗号
                newfileName = newfileName.substring(0, newfileName.lastIndexOf(','));
                String content = tenderFrontEndSingle.getContent();
                //用户操作时把图片删除完的那一刻content就为null，然后用户又新增加，但content还是为null，所以在这么情况下就要以下判断
                if (content == null) {
                    content = newfileName;
                } else {
                    content = content + "," + newfileName;
                }
                tenderFrontEndSingle.setContent(content);
                tenderFrontEndSingle.setIsdisplay((short)1);
                int iden = tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
                if (iden > 0) {
                    data = "success";
                }
                //删除了一部分或全部图片但没新增的图片则走以下代码
            } else {
                if (tenderFrontEndSingle.getPictitle() == null || tenderFrontEndSingle.getContent() == null) {
                    //为了避免为空时不保存
                    tenderFrontEndSingle.setPictitle("");
                    tenderFrontEndSingle.setContent("");
                }
                tenderFrontEndSingle.setIsdisplay((short)1);
                int iden = tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
                if (iden > 0) {
                    data = "success";
                }
            }
            //文本
        } else if (infoattribute == 2) {
            tenderFrontEndSingle.setIsdisplay((short)1);
            int iden = tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
            if (iden > 0) {
                data = "success";
            }
            //下拉
        } else if (infoattribute == 3) {
            tenderFrontEndSingle.setIsdisplay((short)1);
            int iden = tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
            if (iden > 0) {
                data = "success";
            }
            //标签
        } else if (infoattribute == 4) {
            tenderFrontEndSingle.setIsdisplay((short)1);
            int iden = tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
            if (iden > 0) {
                data = "success";
            }
        }
        if(hasNext!=null&&hasNext.equals("1")){
            List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition2(tenderFrontEndSingle.getTno());
            if(tenderFrontEndSingles!=null&&tenderFrontEndSingles.size()>0){
                BigDecimal id2 = null;
                for(int i=0;i<tenderFrontEndSingles.size();i++){
                    BigDecimal id1 = tenderFrontEndSingles.get(i).getId();
                    BigDecimal id = tenderFrontEndSingle.getId();
                    if(id.equals(id1)&&i<(tenderFrontEndSingles.size()-1)){
                        TenderFrontEndSingle tenderFrontEndSingle1 = tenderFrontEndSingles.get(i + 1);
                        id2 = tenderFrontEndSingle1.getId();
                        break;
                    }
                }
                ModelAndView mv = new ModelAndView();
                mv.setViewName("redirect:/admin/tenderFrontEndSingle/content_UI.action?id=" + id2+"&index="+(index+1));
                return mv;
            }
        }else{
            if (data.equals("success")) {
                ModelAndView mv = new ModelAndView();
                String tno = tenderFrontEndSingle.getTno();
                mv.setViewName("redirect:/admin/tenderFrontEndSingle/listByTenderTno.action?tno=" + tno);
                return mv;
            } else {
                PublicUtil.sendJsonData(response, "fail!");
                return null;
            }
        }

        return null;
    }


    //编辑序号页面
    @RequestMapping(value = "update_UI")
    public ModelAndView update_UI(BigDecimal id) {
        if (id != null) {
            TenderFrontEndSingle tenderFrontEndSingle = tenderFrontEndSingleService.selectByPrimaryKey(id);
            if (tenderFrontEndSingle != null) {
                ModelAndView mav = new ModelAndView();
                mav.addObject("tenderFrontEndSingle", tenderFrontEndSingle);
                mav.setViewName("admin/tenderFrontEndSingle/update_TenderFrontEndSingle");
                return mav;
            }
        }
        return null;
    }


    //编辑
    @RequestMapping(value = "update")
    public void update(TenderFrontEndSingle tenderFrontEndSingle) throws Exception {
        String data = "fail";
        TenderFrontEndSingle condition = new TenderFrontEndSingle();
        condition.setTno(tenderFrontEndSingle.getTno());
        //根据标编号找出所有单标信息
        List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition(condition);
        //得到修改后的序号
        Integer sno = tenderFrontEndSingle.getSno();
        //找出DB中没有修改前的对象
        TenderFrontEndSingle enderFrontEndSingle = null;
        TenderFrontEndSingle tenderFrontEndSingleDB = getTenderFrontEndSingleById(tenderFrontEndSingles, tenderFrontEndSingle.getId());
        //得到修改前的编号
        Integer snoBefore = tenderFrontEndSingleDB.getSno();
        //根据修改后的序号去查
        TenderFrontEndSingle other = getTenderFrontEndSingle(tenderFrontEndSingles, sno);
        //如果有数据就要迭代更改一些排序号
        if (other != null) {
            TenderFrontEndSingle tBySno = getTenderFrontEndSingle(tenderFrontEndSingles, sno);
            //得到脚标
            int aaindex = tenderFrontEndSingles.indexOf(tBySno);
            //得到脚标
            int bbindex = tenderFrontEndSingles.indexOf(tenderFrontEndSingleDB);
            //向前改
            if (snoBefore > sno) {
                for (int i = aaindex; i < bbindex; i++) {
                    TenderFrontEndSingle t2 = tenderFrontEndSingles.get(i);
                    t2.setSno(t2.getSno() + 1);
                    tenderFrontEndSingleService.updateByPrimaryKeySelective(t2);
                }
            }
            //向后改
            if (snoBefore < sno) {
                for (int i = bbindex + 1; i <= aaindex; i++) {
                    TenderFrontEndSingle t3 = tenderFrontEndSingles.get(i);
                    t3.setSno(t3.getSno() - 1);
                    tenderFrontEndSingleService.updateByPrimaryKeySelective(t3);
                }
            }
            tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
            //如果没有数据就直接更改
        } else {
            int iden = tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
        }
        data = "success";
        sendJsonData(response, JSON.toJSONString(data));
    }

    //删除页面
    @RequestMapping(value = "del_UI")
    public ModelAndView del_UI(BigDecimal id) {
        TenderFrontEndSingle tenderFrontEndSingle = tenderFrontEndSingleService.selectByPrimaryKey(id);
        if (tenderFrontEndSingle != null) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("tenderFrontEndSingle", tenderFrontEndSingle);
            mav.setViewName("admin/tenderFrontEndSingle/delete_TenderFrontEndSingle");
            return mav;
        }
        return null;
    }

    //删除
    @RequestMapping(value = "delete")
    public void removeTenderFrontEndSingle(BigDecimal id) throws Exception {
        String data = "fail";
        if (id != null) {
            int iden = tenderFrontEndSingleService.deleteByPrimaryKey(id);
            if (iden > 0) {
                data = "success";
            }
        }
        sendJsonData(response, JSON.toJSONString(data));
    }

    //显示和不显示
    @RequestMapping(value = "tostaratQuote", method = {RequestMethod.POST, RequestMethod.GET})
    public void loantostaratQuote(BigDecimal id, String str) throws Exception {

        if (id != null && !str.equals("")) {
            TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
            tenderFrontEndSingle.setId(id);
            if (str.equals("h")) {//不显示
                tenderFrontEndSingle.setIsdisplay((short) 0);
            }
            if (str.equals("s")) {//显示
                tenderFrontEndSingle.setIsdisplay((short) 1);
            }
            int i = tenderFrontEndSingleService.updateByPrimaryKeySelective(tenderFrontEndSingle);
            if (i > 0) {
                PublicUtil.sendJsonData(response, "操作成功!");
            }
        }
    }

    /**
     * 封装source和isfixed字段到单标信息对象
     * @author :liuqh
     * @date :2017/7/8 10:50
     */
    private void findTenderFrontEndByInfo(TenderFrontEndSingle tenderFrontEndSingle, ModelAndView mav) {
        TenderFrontEnd condition = new TenderFrontEnd();
        condition.setInfono(tenderFrontEndSingle.getInfono());
        List<TenderFrontEnd> tenderFrontEnds = tenderFrontEndService.selectByCondition(condition);
        if (tenderFrontEnds != null && tenderFrontEnds.size() > 0) {
            TenderFrontEnd tenderFrontEnd = tenderFrontEnds.get(0);
            Integer source = tenderFrontEnd.getSource();
            Short isfixed = tenderFrontEnd.getIsfixed();
            tenderFrontEndSingle.setSource(source);
            tenderFrontEndSingle.setIsfixed(isfixed);
        }
    }

    //根据标编号查询
    @RequestMapping(value = "listByTenderTno")
    public ModelAndView listByTenderTno(String tno) {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<String, Object>();
        initPage(map, pageNum, pageSize);
        //新增的项目对已发标的标无效，所以已发的标只能从单标前端信息表中查
        TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
        tenderFrontEndSingle.setTno(tno);
        List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition(tenderFrontEndSingle);
        PageInfo<Object> pagehelper = initPagehelper(map, tenderFrontEndSingles);
        ModelAndView mav = new ModelAndView();
        mav.addObject("tno", tno);
        mav.setViewName("admin/tenderFrontEndSingle/tenderFrontEndSingle_list");
        mav.addObject("pagehelper", pagehelper);
        return mav;
    }



    /**
     * 找到与修改后编号一样的对象
     * @author :liuqh
     * @date :2017/7/8 10:42
     */
    private TenderFrontEndSingle getTenderFrontEndSingle(List<TenderFrontEndSingle> TenderFrontEndSingles, Integer sno) {
        for (TenderFrontEndSingle t : TenderFrontEndSingles) {
            if (t.getSno() == sno) {
                return t;
            }
        }
        return null;
    }

    /**
     * 找出DB中没有修改前的对象
     * @author :liuqh
     * @date :2017/7/8 10:43
     */
    private TenderFrontEndSingle getTenderFrontEndSingleById(List<TenderFrontEndSingle> TenderFrontEndSingles, BigDecimal id) {

        for (TenderFrontEndSingle t : TenderFrontEndSingles) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }


}
