package com.ptpl.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.Award;
import com.ptpl.model.ManualAward;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserPostAddress;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.ManualAwardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserPostAddressI;
import com.ptpl.web.util.StringUtil;

/**
 * @author liuj
 * @description:用户奖品的邮寄地址controller
 * */
@Controller
@RequestMapping(value="/userAddrss")
public class UserPostAddressController extends BaseController{

   //用户基本信息service
   @Autowired
   private UserBaseAccountInfoServiceI userBaseAccountInfoService;
   //用户奖品邮寄地址service
   @Autowired
   private UserPostAddressI userPostAddressService;
   
   
   //显示用户邮寄地址
   @RequestMapping(value="/toAddress",method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView toPostAddress(HttpServletRequest request, HttpServletResponse response) throws Exception{
	   ModelAndView mv=new ModelAndView();  
	   String pageNum = request.getParameter("pageNum");
	   String pageSize = request.getParameter("pageSize");
	   UserBaseAccountInfo info=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
	   if(null==info){
		   mv.setViewName("user/login");
	   }else{
		      List<UserPostAddress> list=userPostAddressService.selectByuserID(info.getId());
		      if(null==list){
		    	  mv.addObject("list", list);
		    	  mv.setViewName("user/userPosrAddress/postAddress");
		      }else{
		    	  Map<String, Object> map=new HashMap<String,Object>();
		    	  initPage(map, pageNum, pageSize);
		    	  PageInfo<Object> pagehelper=initPagehelper(map, list);
		    	  mv.addObject("pagehelper", pagehelper);
		    	  mv.setViewName("user/userPosrAddress/postAddress");
		      }
	   }
	   return mv;
   }
   
 //更新邮寄地址
   @RequestMapping(value="/wirtePostAddress",method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView userWriteAddress(HttpServletRequest request,UserPostAddress address) throws Exception{
	     ModelAndView mv=new ModelAndView();
	     UserBaseAccountInfo info=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
	     if(null!=info){
	      	  userPostAddressService.updateByPrimaryKeySelective(address);
	      	  mv.setViewName("redirect:/userAddrss/toAddress.action");
	     }
	     return mv;
   }
   
 //添加邮寄地址
   @RequestMapping(value="/AdduserAddress",method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView insetrtAddress(HttpServletRequest request,UserPostAddress address) throws Exception{
	   ModelAndView mv=new ModelAndView();
	     UserBaseAccountInfo info=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
	     if(null!=info){
	    	  address.setBaseid(info.getId());
	    	  String defAddress=request.getParameter("Defaultaddress");
	    	  if("".equals(defAddress)||null==defAddress){
	    		  address.setIsdefaddress(new BigDecimal(0));
	    		  userPostAddressService.insertSelective(address);
	    	  }else {
	    		  List<UserPostAddress> list=userPostAddressService.selectByuserID(info.getId());
	    		  if(null!=list){
	    			  for (int i = 0; i < list.size(); i++) {
	    				   for (UserPostAddress userPostAddress : list) {
	    					   userPostAddress.setIsdefaddress(new BigDecimal(0));
	    					   userPostAddressService.updateByPrimaryKeySelective(userPostAddress);
						}
					}
	    		  }
	    		  address.setIsdefaddress(new BigDecimal(1));
	    		  userPostAddressService.insertSelective(address);
			}
	    	  mv.setViewName("redirect:/userAddrss/toAddress.action");
	     }
	     return mv;
   }
   
 //修改邮寄地址
   @RequestMapping(value="/queryuserAddressDetail",method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView userAddressDetail(BigDecimal id) throws Exception{
	    ModelAndView mv=new ModelAndView();
	    UserPostAddress address=userPostAddressService.selectByPrimaryKey(id);
	    mv.addObject("address", address);
	    mv.setViewName("user/userPosrAddress/changeaddress");
	    return mv;
   }
   
 //删除邮寄地址
   @RequestMapping(value="/deleteuserAddress",method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView deleteuserAddress(BigDecimal id) throws Exception{
	   ModelAndView mv=new ModelAndView();
	   UserBaseAccountInfo info=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
	   if(info!=null){
	   userPostAddressService.deleteByPrimaryKey(id);
	   mv.setViewName("redirect:/userAddrss/toAddress.action");
	   }
	   return mv;
   }
   
}
