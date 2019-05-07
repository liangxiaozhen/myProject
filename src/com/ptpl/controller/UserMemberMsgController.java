package com.ptpl.controller;

import com.github.pagehelper.PageInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserIntMsg;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 2017/3/16
 * author: Ywp
 * Description:账户管理消息中心
 * Parameter:
 */
@Controller
@RequestMapping("/user/msg")
public class UserMemberMsgController  extends BaseController{
    @Autowired
    UserMsgSetServiceI userMsgSetServiceI;
    @Autowired
    UserIntMsgServiceI userIntMsgServiceI;

    /**
     * 跳转到消息中心页面
     */
    @RequestMapping(value = "/toMemberMsg", method = { RequestMethod.POST,
            RequestMethod.GET })
    public ModelAndView toMemberMsg(HttpServletRequest req, HttpServletResponse res){
        String pageSize= req.getParameter("pageSize");
        String pageNum=req.getParameter("pageNum");
        if(pageSize==null){
            pageSize="8";
        }
        if(pageNum==null){
            pageNum="1";
        }
        ModelAndView mv=new ModelAndView();
        UserBaseAccountInfo userInfo=getUserBaseAccountInfo();      //获取用户基本信息
        List<UserMsgSet> userMsgSetList=userMsgSetServiceI.selectByBaseId(userInfo.getId());//通过用户ID查找消息设置记录
        Map<String, Object> map=new HashMap<>();
        this.initPage(map,pageNum,pageSize);             //初始化分页查询信息
        List<UserIntMsg> userIntMsgList=userIntMsgServiceI.selectByBaseId(userInfo.getId());//通过用户ID查找全部提醒消息
        for(UserIntMsg userIntMsg:userIntMsgList){
            userIntMsg.setMsgtimeString(userIntMsg.getMsgtime());   //设置日期字符串，便于显示
        }
        PageInfo<Object> pagehelper=initPagehelper(map, userIntMsgList);//初始化分页
        mv.addObject("pagehelper",pagehelper);             //UserIntMsg实体的分页查询
        mv.addObject("userMsgSetList",userMsgSetList);      //消息设置列表
        mv.addObject("baseId",userInfo.getId());         //用户ID
        mv.setViewName("user/manager/membermsg/membermsg");
        return mv;
    }
    /**
     * 批量删除消息
     */
    @RequestMapping(value = "/deleteMsgList", method = { RequestMethod.POST,
            RequestMethod.GET })
    public void deleteMsgList( HttpServletRequest req) throws Exception{
        String aaa=req.getParameter("ids");     //选中checkbox的id集合
        String[] ids= aaa.split("-");       //将传过来的字符串转换为数组
        for(int i=0;i<ids.length;i++){
            BigDecimal id=new BigDecimal(ids[i]);
            userIntMsgServiceI.deleteByPrimaryKey(id);
        }

    }
    /**
     * 批量标记已读消息
     */
    @RequestMapping(value = "/batchIsRead", method = { RequestMethod.POST,
            RequestMethod.GET })
    public void batchIsRead( HttpServletRequest req) throws Exception{
        String aaa=req.getParameter("ids"); //选中checkbox的id集合
        String[] ids= aaa.split("-");       //将传过来的字符串转换为数组
        for(int i=0;i<ids.length;i++){
            BigDecimal id=new BigDecimal(ids[i]);
            UserIntMsg userIntMsg=new UserIntMsg();
            userIntMsg.setId(id);
            userIntMsg.setIsread((short)1);
            userIntMsgServiceI.updateByPrimaryKeySelective(userIntMsg);
        }

    }
    /**
     * 单独标记已读消息
     */
    @RequestMapping(value = "/singleIsRead", method = { RequestMethod.POST,
            RequestMethod.GET })
    public void singleIsRead( HttpServletRequest req,HttpServletResponse res) throws Exception{
        String idString=req.getParameter("id");     //选中checkbox的id
        BigDecimal id=new BigDecimal(idString);
        UserIntMsg userIntMsg=new UserIntMsg();
        userIntMsg.setId(id);
        userIntMsg.setIsread((short)1);
        userIntMsgServiceI.updateByPrimaryKeySelective(userIntMsg);

    }
    /**
     * 保存消息设置
     */
    @RequestMapping(value = "/saveUserMsgSet", method = { RequestMethod.POST,
            RequestMethod.GET })
    public void saveUserMsgSet(UserMsgSet userMsgSet){
            for (UserMsgSet user : userMsgSet.getUserMsgSetList()) {
                if(user.getEmail()==null){
                    user.setEmail((short) 0);
                } if(user.getIntmsg()==null){
                    user.setIntmsg((short) 0);
                } if(user.getSms()==null){
                    user.setSms((short) 0);
                }if(user.getApp()==null){
                    user.setApp((short)0);
                }if(user.getWechat()==null){
                    user.setWechat((short)0);
                }
                user.setBaseid(userMsgSet.getBaseid());
                user.setModifytime(new Date());
                if(user.getId()==null ){
                    userMsgSetServiceI.insertSelective(user);
                }else{
                    userMsgSetServiceI.updateByPrimaryKeySelective(user);
                }
            }
        }
}
