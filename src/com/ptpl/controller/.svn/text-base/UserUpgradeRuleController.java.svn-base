package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Red_Constant;
import com.ptpl.constant.UpgradeWay_Constant;
import com.ptpl.model.UserOutsideAward;
import com.ptpl.model.UserUpgradeRule;
import com.ptpl.service.UserUpgradeRecordServiceI;
import com.ptpl.service.UserUpgradeRuleServiceI;

/**
 * @author liuj
 * 用户等级升级规则controller
 */
@Controller
@RequestMapping(value="/admin/gradeRule")
public class UserUpgradeRuleController extends BaseController{
	
	@Autowired
	private UserUpgradeRuleServiceI userUpgradeRuleService;
   
	@RequestMapping(value="/queryRuleList",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView usergradeRuleList(UserUpgradeRule rule) throws Exception{
		 // 处理分页请求
		   String pageNum = request.getParameter("pageNum");
		   String pageSize = request.getParameter("pageSize");
		   Map<String, Object> map=new HashMap<String, Object>();
		   initPage(map, pageNum, pageSize);
		   // 调用service层的方法得到对象列表
		   List<UserUpgradeRule> rulelist =userUpgradeRuleService.getallUserRule(rule);
		    //讲数据存储至pageinfo中
		    PageInfo<Object> pagehelper=initPagehelper(map, rulelist);
		     // 返回ModelAndView
		 		ModelAndView mv = new ModelAndView();
		 		mv.addObject("pagehelper", pagehelper);
		 		mv.addObject("methodmaps", UpgradeWay_Constant.UPGRADEWAY_MAP);//升级方式
		 		// 指定视图
		 		mv.setViewName("admin/userUpgradeRule/UserUpgradeRule");
		 		return mv;
	}
	
	//删除
	@RequestMapping(value="/deluserRule",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView deleteUserRule(BigDecimal id) throws Exception{
		  ModelAndView mv=new ModelAndView();
		  userUpgradeRuleService.deleteByPrimaryKey(id);
		  mv.setViewName("redirect:/admin/gradeRule/queryRuleList.action");
		  return mv;
	}
	 
	//查看详情
	@RequestMapping(value="/userRuleDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView ruledetail(BigDecimal id) throws Exception{
		  ModelAndView mv=new ModelAndView();
		  UserUpgradeRule rule=userUpgradeRuleService.selectByPrimaryKey(id);
		  if(null!=rule){
		  mv.addObject("rule", rule);
		  mv.addObject("methods", UpgradeWay_Constant.UPGRADEWAY_MAP.get(rule.getMethod().shortValue()));
		  mv.setViewName("admin/userUpgradeRule/UserUpgradeRuleDetail");
		  }
		  return mv;
	}
	//查看详情2
		@RequestMapping(value="/userRuleDetailt",method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView ruledetailt(BigDecimal id) throws Exception{
			  ModelAndView mv=new ModelAndView();
			  UserUpgradeRule rule=userUpgradeRuleService.selectByPrimaryKey(id);
			  if(null!=rule){
			  mv.addObject("ruletwo", rule);
			  mv.addObject("methodst", UpgradeWay_Constant.UPGRADEWAY_MAP.get(rule.getMethod().shortValue()));
			  mv.addObject("methodmaps",UpgradeWay_Constant.UPGRADEWAY_MAP);//使用方式maps集合
			  mv.setViewName("admin/userUpgradeRule/UserUpgradeRuleDetail");
			  }
			  return mv;
		}
		
		//修改
		@RequestMapping(value="/updateRule",method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView updateuserRule(UserUpgradeRule record) throws Exception{
			  ModelAndView mv=new ModelAndView();
			  userUpgradeRuleService.updateByPrimaryKeySelective(record);
			  mv.setViewName("redirect:/admin/gradeRule/queryRuleList.action");
			  return mv;
		}
		//添加
		@RequestMapping(value="/adduserRule",method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView adduserRule(HttpServletRequest request,UserUpgradeRule record) throws Exception{
			  ModelAndView mv=new ModelAndView();
			  String unit_year=request.getParameter("unit_year");
			  String unit_mouth=request.getParameter("unit_mouth");
			  String unit_day=request.getParameter("unit_day");
			  if(unit_year.length()!=0){
				  record.setUnit(unit_year+"年");
				  userUpgradeRuleService.insertSelective(record);
			  }else if(unit_mouth.length()!=0){
				  record.setUnit(unit_mouth+"月");
				  userUpgradeRuleService.insertSelective(record);
			  }else if(unit_day.length()!=0){
				  record.setUnit(unit_day+"日");
				  userUpgradeRuleService.insertSelective(record);
			  }
			  mv.setViewName("redirect:/admin/gradeRule/queryRuleList.action");
			  return mv;
		}
		
		@RequestMapping(value="/jump",method={RequestMethod.POST,RequestMethod.GET})
		public ModelAndView jump(HttpServletRequest request) throws Exception{
			 ModelAndView mv=new ModelAndView();
		      String flag=request.getParameter("flag");
		      if("jump_left".equals(flag)){
		    	  mv.addObject("methodmaps",UpgradeWay_Constant.UPGRADEWAY_MAP);//使用方式maps集合
		    	  mv.setViewName("admin/userUpgradeRule/UserUpgradeRuleAdd");	         
		      }
			 if("jump_right".equals(flag)){
				 mv.setViewName("redirect:/admin/gradeRule/queryRuleList.action");
			 }
			  return mv;
		}
}
