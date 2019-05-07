package com.ptpl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.ManualAwardServiceI;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/user/outsideAward")
public class UserOutsideAwardsController {

    @Autowired
    private ActivityAwardListServiceI activityAwardListService;//获奖人
    @Autowired
    private ManualAwardServiceI manualAwardServiceI;
    @Autowired
    private AwardServiceI awardService;//奖品
    /**
    * @author pxl
    * @date 2016-11-8
    * 查询某个用户的获奖信息
    */
    @RequestMapping("/queryAwardInfo")
    public ModelAndView queryAwardInfo(HttpServletRequest request,ActivityAwardList aals){
       //处理分页
       String pageNum = request.getParameter("pageNum");
       String pageSize = request.getParameter("pageSize");
	   int num= 1;
	   int size=9;
	   if(pageNum != null && ! "".equals(pageNum)){
		   num = Integer.parseInt(pageNum);
	   }
	   if(pageSize != null && !"".equals(pageSize)){
		   size= Integer.parseInt(pageSize);
	   }
	   String sortString = "id.desc";
	   Order.formString(sortString);
	   
	   PageHelper.startPage(num,size);
	   UserBaseAccountInfo userBaseinfo=(UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
	   
	   System.out.println("username>>>>"+userBaseinfo.getLoginname());
	   aals.setUsername(userBaseinfo.getLoginname());
	   List<ActivityAwardList> aalist = activityAwardListService.getAwardInfo(aals);
	   //Set<Award> awardSet = new HashSet<Award>();
	   Map<String,String> awardMap =new HashMap<String,String>();
	   //根据奖品的名称查找出领奖规则
	   for(ActivityAwardList aal:aalist){
		   
		    Award award=awardService.getAwardByName(aal.getAwardname());
		    if(award!=null){
		    	
		    	if(aal.getAwardname().equals(award.getAname())){
		    		awardMap.put(award.getAname(),award.getAwardRules());
		    	}
		    	//    awardSet.add(award);
		    }
	   }
	  
	   PageInfo<ActivityAwardList> pagehelper = new PageInfo<ActivityAwardList>(aalist);
	   pagehelper.setFirstPage(1);
	   
	   int lastPageNum = 0;
	   if(pagehelper.getTotal()% size==0){
		   lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
	   pagehelper.setLastPage(lastPageNum);
	    
	   ModelAndView mv = new ModelAndView();
	   mv.addObject("pagehelper", pagehelper);
	   mv.addObject("aals",aals);
	   mv.addObject("statusmaps",ActAward_Constant.STATUS_MAP);
	   mv.addObject("awardMap",awardMap);
	   mv.setViewName("user/userPosrAddress/activityAwardInfo");
	   return mv;
    }

   /**
    * @author pxl
    * @date 2016-11-8
    * 用户在备注中填写详细的奖品收货信息
    */
   @RequestMapping("/addOrModifyRemark")
   public ModelAndView addOrModifyRemark(ActivityAwardList aals){
	  
	   aals=activityAwardListService.getselectById(aals.getId());
	   //将填写备注时的填写要求回显到弹窗中
	   List<ManualAward> manualAwardList = manualAwardServiceI.getRemarkById(aals.getActid());
	   //根据奖品名称查找出领奖规则
	   Award award=awardService.getAwardByName(aals.getAwardname());
	   
	   ModelAndView mv = new ModelAndView();
	   for(ManualAward manualAward:manualAwardList){
		   String manualAwardRemark = manualAward.getRemark();
		   mv.addObject("manualAwardRemark",manualAwardRemark);
	   }
	   mv.addObject("aals",aals);
	   mv.addObject("award",award);
	   mv.setViewName("user/userPosrAddress/addAwardRemark");
	   return mv;
   }
   
   /**
    * @author pxl
    * @date 2016-11-8
    * 用户填写备注后并确定
    */
   @RequestMapping("/sureAwardRemark")
   public void sureAwardRemark(HttpServletResponse response,ActivityAwardList aals){  
	   aals.setStatus((short)5);//用户确认后  待确认-- 已经确认
	   int rows =activityAwardListService.sureAwardRemark(aals);
	   Map<String,String> map =new HashMap<String,String>();
	   if(rows>0){
		   map.put("result","操作成功");
	   }else{
		   map.put("result", "操作失败");
	   }
	   String jsonStr = JSON.toJSONString(map);
	   try {
		StringUtil.sendJsonData(response, jsonStr);
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
}
