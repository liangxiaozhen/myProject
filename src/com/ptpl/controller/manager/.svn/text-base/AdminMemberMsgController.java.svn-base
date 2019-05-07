package com.ptpl.controller.manager;

import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserMsgSet;
import com.ptpl.service.UserIntMsgServiceI;
import com.ptpl.service.UserMsgSetServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2017/3/18
 * author: Ywp
 * Description: 管理后台用户消息通知设置
 * Parameter:
 */
@Controller
@RequestMapping("/admin/msg")
public class AdminMemberMsgController extends BaseController{
    @Autowired
    UserMsgSetServiceI userMsgSetServiceI;
    @Autowired
    UserIntMsgServiceI userIntMsgServiceI;

    /**
     * 跳转到用户通知设置页面
     */
    @RequestMapping(value = "/toMsgSet", method = { RequestMethod.POST,
            RequestMethod.GET })
    public ModelAndView toMsgSet(HttpServletRequest req, HttpServletResponse res,UserMsgSet userMsgSet){
        String pageSize= req.getParameter("pageSize");
        String pageNum=req.getParameter("pageNum");
        if(pageSize==null){
            pageSize="10";
        }
        if(pageNum==null){
            pageNum="1";
        }
        ModelAndView mv=new ModelAndView();
        Map<String, Object> map=new HashMap<>();
        this.initPage(map,pageNum,pageSize);
        List<UserMsgSet> list=userMsgSetServiceI.selectBySelective(userMsgSet);
        PageInfo<Object> pagehelper=initPagehelper(map, list);//初始化分页
        mv.addObject("pagehelper",pagehelper);
        mv.addObject("selectInfo",userMsgSet);
        mv.setViewName("admin/adminmsgset/adminMsgSet");
        return  mv;

    }
    
    /**
     * 跳转到消息中心页面
     */
    @RequestMapping(value = "/showDetail", method = { RequestMethod.POST,
            RequestMethod.GET })
    public ModelAndView saveMsgSet(HttpServletRequest req, BigDecimal id){
        ModelAndView mv = new ModelAndView();
        UserMsgSet userMsgSet=userMsgSetServiceI.selectByPrimaryKey(id);
        mv.addObject("userMsgSet",userMsgSet);
        mv.setViewName("admin/adminmsgset/adminMsgSetDetail");
        return mv;
    }


}
