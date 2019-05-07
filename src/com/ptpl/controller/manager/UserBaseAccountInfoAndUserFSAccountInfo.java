package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: UserBaseAccountInfoAndUserFSAccountInfo 
* @Package com.ptpl.controller.manager 
* @Description: TODO(用户信息和用户汇付天下托管账号开户信息  Controller) 
* @author cjm
* @date 2016年10月4日 下午1:59:05 
* @version V1.0
 */
@Controller
@RequestMapping("/admin/baseAndFsa")
public class UserBaseAccountInfoAndUserFSAccountInfo extends BaseController{

	/**
	 * 用户基本信息 Service
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	/**
	  * 
	 * @Title: list 
	 * @Description: TODO(用户信息和用户汇付天下托管账号开户信息) 
	 * @param @param  
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
	 public ModelAndView list(){
 		 int num = 1;
		 int pageSize = 20;
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
      	 List<UserBaseAccountInfo> userBaseAccountInfos = userBaseAccountInfoServiceI.getUserBaseAccountInfoAndUserFSAccountInfo(null);
      	 for(UserBaseAccountInfo accountInfo : userBaseAccountInfos){
      		accountInfo = getDecryptionUserBaseAccountInfoDetail(accountInfo);
      		if(accountInfo.getUserfsaccountinfo() != null ){
      			accountInfo.setUserfsaccountinfo(getDecryptionUserFsAccountInfoDetail(accountInfo.getUserfsaccountinfo()));
      		}
      	 }
      	 PageInfo<UserBaseAccountInfo> pagehelper = new PageInfo<UserBaseAccountInfo>(userBaseAccountInfos);
     	 pagehelper.setFirstPage(1);
     	 int lasePageNum = 0;
     	 if(pagehelper.getTotal() % pageSize ==0){
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
     	 }else{
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
     	 }
     	 pagehelper.setLastPage(lasePageNum);
     	 
     	 ModelAndView modelAndView = new ModelAndView();	
     	 modelAndView.setViewName("admin/userbaseandfsainfo/list");
     	 modelAndView.addObject("pagehelper", pagehelper);
		 return modelAndView;
	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO() 
	* @param @param request
	* @param @param response
	* @param @param  
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping("/template")
	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,UserBaseAccountInfo userBaseAccountInfo){
		 String pageS = request.getParameter("pageSize");
		 String pageNo = request.getParameter("pageNo");
		 int num = 1;
		 int pageSize = 20;
		 if(StringUtil.isNotEmpty(pageS)){
			 pageSize = Integer.parseInt(pageS);
		 }
		 if(StringUtil.isNotEmpty(pageNo)){
			 num = Integer.parseInt(pageNo);
		 }
		 //根据Id排序
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 if(userBaseAccountInfo.getEmail() != null  && StringUtil.isNotEmpty(userBaseAccountInfo.getEmail())){
			 userBaseAccountInfo.setEmail(setEncrypt(userBaseAccountInfo.getEmail().trim()));
		 }
		 
		if(userBaseAccountInfo.getMobilephone() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getMobilephone())){
			userBaseAccountInfo.setMobilephone(setEncrypt(userBaseAccountInfo.getMobilephone().trim()));
		 }
		
		if(userBaseAccountInfo.getRealname() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getRealname())){
			userBaseAccountInfo.setRealname(setEncrypt(userBaseAccountInfo.getRealname().trim()));
		}
		
		if(userBaseAccountInfo.getLoginname() != null && StringUtil.isNotEmpty(userBaseAccountInfo.getLoginname())){
			userBaseAccountInfo.setLoginname(setEncrypt(userBaseAccountInfo.getLoginname().trim()));
		}
		 //查询全部用户方法
      	 List<UserBaseAccountInfo> userBaseAccountInfos = userBaseAccountInfoServiceI.getUserBaseAccountInfoAndUserFSAccountInfo(userBaseAccountInfo);
      	 for(UserBaseAccountInfo accountInfo : userBaseAccountInfos){
      		accountInfo = getDecryptionUserBaseAccountInfoDetail(accountInfo);
      		if(accountInfo.getUserfsaccountinfo() != null ){
      			accountInfo.setUserfsaccountinfo(getDecryptionUserFsAccountInfoDetail(accountInfo.getUserfsaccountinfo()));
      		}
      	 }
     	 PageInfo<UserBaseAccountInfo> pagehelper = new PageInfo<UserBaseAccountInfo>(userBaseAccountInfos);
     	 pagehelper.setFirstPage(1);
     	 int lasePageNum = 0;
     	 if(pagehelper.getTotal() % pageSize ==0){
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
     	 }else{
     		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
     	 }
     	 pagehelper.setLastPage(lasePageNum);
     	 
     	 ModelAndView modelAndView = new ModelAndView();	
     	 modelAndView.setViewName("admin/userbaseandfsainfo/listTemplate");
     	 modelAndView.addObject("pagehelper", pagehelper);
		 return modelAndView;
	 }
	 
	 @RequestMapping("/detail")
	 public ModelAndView detail(HttpServletRequest request,HttpServletResponse response){
		String opid =  request.getParameter("opid");
		if(StringUtil.isNotEmpty(opid)){
			ModelAndView andView = new ModelAndView();
			UserBaseAccountInfo userBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoAndUserFSAccountInfoById(new BigDecimal(opid));
			userBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(userBaseAccountInfo);
      		if(userBaseAccountInfo.getUserfsaccountinfo() != null ){
      			userBaseAccountInfo.setUserfsaccountinfo(getDecryptionUserFsAccountInfoDetail(userBaseAccountInfo.getUserfsaccountinfo()));
      		}
 			andView.addObject("userBaseAccountInfo", userBaseAccountInfo);
			andView.setViewName("admin/userbaseandfsainfo/detailTemplate");
			return andView;
		}else{
 			return null;
		}
	 }
}
