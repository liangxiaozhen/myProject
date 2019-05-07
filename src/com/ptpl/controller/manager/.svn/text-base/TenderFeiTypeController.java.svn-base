package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderFeiType;
import com.ptpl.model.TenderFrontEndSingle;
import com.ptpl.service.TenderFeiTypeServiceI;
import com.ptpl.service.TenderFrontEndSingleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/tenderFeiType")
public class TenderFeiTypeController extends BaseController {
    @Autowired
    private TenderFeiTypeServiceI tenderFeiTypeService;
    @Autowired
    private TenderFrontEndSingleServiceI tenderFrontEndSingleService;

    //查询List
    @RequestMapping(value = "list")
    public ModelAndView queryAll() {
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        Map<String, Object> map = new HashMap<String, Object>();
        initPage(map, pageNum, pageSize);
        PageHelper.orderBy("id asc");
        List<TenderFeiType> list = tenderFeiTypeService.selectAllTenderFeiType();
        PageInfo<Object> pagehelper = initPagehelper(map, list);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/tenderFeiType/TenderFeiType_list");
        mav.addObject("pagehelper", pagehelper);
        return mav;
    }

    //新增页面
    @RequestMapping(value = "insert_UI")
    public ModelAndView insert_UI() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/tenderFeiType/insert_TenderFeiType");
        return mav;
    }

    //新增
    @RequestMapping(value = "insert")
    public void saveTenderFrontEndSingle(TenderFeiType tenderFeiType) throws Exception {
        String data = "失败";
        int iden = tenderFeiTypeService.insertSelective(tenderFeiType);
        if (iden > 0) {
            data = "成功";
        }
        sendJsonData(response, JSON.toJSONString(data));
    }


    //修改
    @RequestMapping(value = "/update_UI", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updateloan(BigDecimal id) throws Exception {
        ModelAndView mv = new ModelAndView();
        if (id != null) {
            TenderFeiType tenderFeiType = tenderFeiTypeService.selectByPrimaryKey(id);
            mv.addObject("tenderFeiType", tenderFeiType);
        }
        mv.setViewName("admin/tenderFeiType/insert_TenderFeiType");
        return mv;
    }


    //编辑
    @RequestMapping(value = "update")
    public void update(TenderFeiType tenderFeiType) throws Exception {
        String data = "失败";
        int i = tenderFeiTypeService.updateByPrimaryKeySelective(tenderFeiType);
        if (i > 0) {
            data = "成功";
        }
        sendJsonData(response, JSON.toJSONString(data));
    }

    //删除页面
    @RequestMapping(value = "del_UI")
    public ModelAndView del_UI(BigDecimal id) {
        TenderFeiType tenderFeiType = tenderFeiTypeService.selectByPrimaryKey(id);
        if (tenderFeiType != null) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("tenderFeiType", tenderFeiType);
            mav.setViewName("admin/tenderFeiType/delete_TenderFeiType");
            return mav;
        }
        return null;
    }

    //删除
    @RequestMapping(value = "delete")
    public void removeTenderFrontEndSingle(BigDecimal id) throws Exception {
        String data = "失败";
        if (id != null) {
            TenderFrontEndSingle tenderFrontEndSingle = new TenderFrontEndSingle();
            tenderFrontEndSingle.setInfotype(id.intValue());
            List<TenderFrontEndSingle> tenderFrontEndSingles = tenderFrontEndSingleService.selectByCondition(tenderFrontEndSingle);
            if (tenderFrontEndSingles != null && tenderFrontEndSingles.size() > 0) {
                data = "被引用中，不能删除";
            } else {
                int iden = tenderFeiTypeService.deleteByPrimaryKey(id);
                if (iden > 0) {
                    data = "成功";
                }
            }
        }
        sendJsonData(response, JSON.toJSONString(data));
    }
}
