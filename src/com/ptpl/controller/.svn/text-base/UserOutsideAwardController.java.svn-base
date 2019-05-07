package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Red_Constant;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.model.UserOutsideAward;
import com.ptpl.service.UserOutsideAwardServiceI;

@Controller
@RequestMapping(value="/admin/outaward")
public class UserOutsideAwardController extends BaseController{
    
   @Autowired
   private UserOutsideAwardServiceI userOutsideAwardService;
   
   /**
	 * 查询用户站外奖品列表
	 */
   @RequestMapping(value="/queryOutawardList",method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView getalluseraward(UserOutsideAward outsideAward,String uoatype, String status,String issend, String isaudit) throws Exception{
	   // 处理分页请求
	// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			int num = 1;
			int size = 9;
			if (pageNum != null && !"".equals(pageNum)) {
				num = Integer.parseInt(pageNum);
			}
			if (pageSize != null && !"".equals(pageSize)) {
				size = Integer.parseInt(pageSize);
			}
			String sortString = "id.desc";
			Order.formString(sortString);
			PageHelper.startPage(num, size);

			// 调用service层的方法得到对象列表
			if(outsideAward.getUserBaseAccountInfo()!=null && StringUtils.isNotBlank(outsideAward.getUserBaseAccountInfo().getLoginname())){
				outsideAward.getUserBaseAccountInfo().setLoginname(setEncrypt(outsideAward.getUserBaseAccountInfo().getLoginname()));
			}
			List<UserOutsideAward> outawardlist = userOutsideAwardService.getallUserAward(outsideAward);
			for(UserOutsideAward u:outawardlist){
				if(u.getUserBaseAccountInfo()!=null){
					u.setUserBaseAccountInfo(getDecryptionUserBaseAccountInfoDetail(u.getUserBaseAccountInfo()));
				}
			}
			PageInfo<UserOutsideAward> pagehelper = new PageInfo<UserOutsideAward>(outawardlist);
			pagehelper.setFirstPage(1);

			int lastPageNum = 0;
			if (pagehelper.getTotal() % size == 0) {
				lastPageNum = (int) pagehelper.getTotal() / size;
			} else {
				lastPageNum = (int) pagehelper.getTotal() / size + 1;
			}
			pagehelper.setLastPage(lastPageNum);
			
			if(outsideAward.getUserBaseAccountInfo()!=null && StringUtils.isNotBlank(outsideAward.getUserBaseAccountInfo().getLoginname())){
				outsideAward.getUserBaseAccountInfo().setLoginname(getDecrypt(outsideAward.getUserBaseAccountInfo().getLoginname()));
			}
			
			// 把对象放进modelAndView中
			ModelAndView modelAndView = new ModelAndView();
			// 条件回显
			modelAndView.addObject("pagehelper", pagehelper);
			modelAndView.addObject("uoatypemaps",ActAward_Constant.RECTYPE_MAP);//奖品来源
			modelAndView.addObject("statusmaps",ActAward_Constant.AWARDSTATUS_MAP);//奖品发放状态
			modelAndView.addObject("isauditmaps",ActAward_Constant.ISAUDIT_MAP);//是否审核
			modelAndView.addObject("outsideAward",outsideAward);
			
			// 指定视图
			modelAndView.setViewName("admin/UserOutsideAward/outsideAwardList");
			return modelAndView;
	 
   }
   
    /**
  	 * 查询用户站外奖详细信息
  	 */
   @RequestMapping(value="/queryOutawardDetail",method={RequestMethod.POST,RequestMethod.GET})
   public ModelAndView getuserawardDetail(BigDecimal id) throws Exception{
	   ModelAndView mv=new ModelAndView();
	   UserOutsideAward award=userOutsideAwardService.selectByPrimaryKey(id);
	   if (award.getUoatime() != null) {
		   award.setUoatimestr(sf.format(award.getUoatime())); // 处转换发放时间
	   }
	   if (award.getAudittime() != null) {
		   award.setAudittimestr(sf.format(award.getAudittime())); //转换审核时间
	   }
	   if(award.getUserBaseAccountInfo()!=null){
		   award.setUserBaseAccountInfo(getDecryptionUserBaseAccountInfoDetail(award.getUserBaseAccountInfo()));
		}
	   mv.addObject("award", award);
	   //key,value
	   mv.addObject("uoatype", ActAward_Constant.RECTYPE_MAP.get(award.getUoatype())); // 奖品来源
	   mv.addObject("status", ActAward_Constant.AWARDSTATUS_MAP.get(award.getStatus())); // 奖品发放状态 
	   mv.addObject("isuse", ActAward_Constant.AWARD_MAP.get(award.getIsuse())); // 奖品状态
	   mv.addObject("isaudit", ActAward_Constant.ISAUDIT_MAP.get(award.getIsaudit())); // 奖品是否审核 
	   mv.setViewName("admin/UserOutsideAward/outsideAwardDetails");
	   return mv;
   }
        //初始化分页相关信息
   public void initPage(Map<String,Object> map, String pageNum, String pageSize){
 			Integer num = 1;
 			Integer size = 20;
 			if (pageNum != null && !"".equals(pageNum)) {
 				num = Integer.parseInt(pageNum);
 			}
 			if (pageSize != null && !"".equals(pageSize)) {
 				size = Integer.parseInt(pageSize);
 			}
 			PageHelper.startPage(num, size);
 			map.put("pageSize", size);
 		}
}
