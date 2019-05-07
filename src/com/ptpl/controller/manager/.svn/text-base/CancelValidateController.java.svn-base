package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.CancelValidate;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.CancelValidateServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: cancelValidate 
 * @Package com.ptpl.controller 
 * @Description: TODO(屏蔽安全验证设置)
 * @author shenggege
 * @date 
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/cancelValidate")
public class CancelValidateController extends BaseController
{
	 
	 /**
	  * 屏蔽安全验证设置service
	  */
	 @Autowired
	 private CancelValidateServiceI cancelValidateService;
	 
	 /**
	  * 用户 service
	  */
	 @Autowired
	 private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	 
	/** 
	 * @Title: list 
	 * @Description: TODO(屏蔽安全验证设置)
	 * @param @param cancelValidate
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author shenggege
	 * @throws
	 */
	 @RequestMapping("/listUI")
	 public ModelAndView list(HttpServletRequest request,CancelValidate cancelValidate)
	 {
		 
		 String pageNum = request.getParameter("pageNum");
		 String pageSize = request.getParameter("pageSize");
		 int num = 1;
		 int size = 20;
		 if(pageNum != null && !"".equals(pageNum)){
			 num = Integer.parseInt(pageNum);
		 }
		 if(pageSize != null && !"".equals(pageSize)){
			 size = Integer.parseInt(pageSize);
		 }
		 
		 String sortString = "id.desc";
		 Order.formString(sortString);
		 PageHelper.startPage(num, size);
		 
		 List<CancelValidate> cancelValidates = cancelValidateService.selectCancelValidateByCondition(cancelValidate);
		 
		 PageInfo<CancelValidate> pagehelper = new PageInfo<CancelValidate>(cancelValidates);
		 pagehelper.setFirstPage(1);
		 
		 int lastPageNum = 0;
		 if(pagehelper.getTotal() % size == 0)
		 {
			 lastPageNum = (int) pagehelper.getTotal() / size;
		 }
		 else
		 {
			 lastPageNum = (int) pagehelper.getTotal() / size + 1;
		 }
		 pagehelper.setLastPage(lastPageNum);
		 
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.addObject("pagehelper", pagehelper);
		 modelAndView.setViewName("admin/cancelValidate/list");
		 return modelAndView;
	 }
	 
	 
 /**
  * @Title:findAddUser
  * @Description: TODO(跳转到增加页面)
  * @param @param 
  * @param @return
  * @return ModelAndView    返回类型 
  * @author shenggege
  * @throws
  */
  @RequestMapping(value="/findAddUser", method={RequestMethod.POST})
  public ModelAndView findAddUser(){
	  ModelAndView modelAndView = new ModelAndView();
	  modelAndView.setViewName("/admin/cancelValidate/insertUser");
	  return modelAndView;
  }
	 
  /**
 * @throws Exception 
   * @throws Exception 
   * @Title:isInsertUser
   * @Description: TODO(判断是否为重复添加)
   * @param @param
   * @param @return
   * @return String 返回类型
   * @author shenggege
   * @throws
   */
   @RequestMapping("/isInsertUser")
   public void isInsertUser(HttpServletResponse response,String username, CancelValidate cancelValidate) throws Exception{
	   List<CancelValidate> list = cancelValidateService.selectCancelValidateByCondition(cancelValidate);
	   if(list.size()>0){
		   String json = JSON.toJSONString("用户已添加!");
		   sendJsonData(response, json);
	   }else{
		   if(username != null && !"".equals(username)){
			   UserBaseAccountInfo ubai = new UserBaseAccountInfo();
			   ubai.setLoginname(username);
			   ubai = userBaseAccountInfoServiceI.getUserBaseAccountInfoByOneCondition(ubai);
			   if(ubai != null){
				   String jsonStr = JSON.toJSONString("添加成功!");
				   sendJsonData(response, jsonStr);
			   }else{
				   String jsonStr = JSON.toJSONString(username+"用户不存在!");
				   sendJsonData(response, jsonStr);
			   }
		   }
	   }
   }
  
  /**
   * @throws Exception 
   * @Title:insertUser
   * @Description: TODO(用户新增)
   * @param @param
   * @param @return
   * @return String 返回类型
   * @author shenggege
   * @throws
   */
   @RequestMapping(value="/insertUser", method={RequestMethod.POST})
   public String insertUser(HttpServletRequest request,HttpServletResponse response,String canceltype,String[] username,CancelValidate cancelValidate,UserBaseAccountInfo userBaseAccountInfo) throws Exception {
	   
	   List<CancelValidate> list = new ArrayList();
	   list.add(cancelValidate);
	   for(CancelValidate e:list){
		   for(int i=0; i<username.length;i++){
			   cancelValidate.setUsername(username[i]);
			   userBaseAccountInfo = userBaseAccountInfoServiceI.getuserloginname(username[i]);
			   cancelValidate.setBaseid(userBaseAccountInfo.getId());
			   cancelValidate.setIp(userBaseAccountInfo.getRegip());
			   cancelValidate.setCookie(userBaseAccountInfo.getRegcookie());
			   cancelValidate.setMobile(userBaseAccountInfo.getMobilephone());
			   cancelValidate.setEmail(userBaseAccountInfo.getEmail());
			   AdminUser adminUser = getAdminUser();
			   if(cancelValidate != null && cancelValidate.getBaseid() != null && cancelValidate.getCanceltype() != null){
				   cancelValidate.setAddman(adminUser.getUsername());
			   }
			   cancelValidate.setAddtime(new Date());
			   //取消验证类型(1.登录验证码 2.注册验证码 3.密码控件  4.登录U盾)
			   if(canceltype.equalsIgnoreCase("1")){
				   cancelValidateService.insertSelective(cancelValidate);
				   String json = JSON.toJSONString("添加成功!");
				   sendJsonData(response, json);
			   }else if(canceltype.equalsIgnoreCase("2")){
				   cancelValidateService.insertSelective(cancelValidate);
				   String json = JSON.toJSONString("添加成功!");
				   sendJsonData(response, json);
			   }else if(canceltype.equalsIgnoreCase("3")){
				   cancelValidateService.insertSelective(cancelValidate);
				   String json = JSON.toJSONString("添加成功!");
				   sendJsonData(response, json);
			   }else if(canceltype.equalsIgnoreCase("4")){
				   cancelValidateService.insertSelective(cancelValidate);
				   String json = JSON.toJSONString("添加成功!");
				   sendJsonData(response, json);
			   }else{
				   String json = JSON.toJSONString("添加失败!");
				   sendJsonData(response, json);
			   }
		   }
	   }
	   return null;
   }
	 
	 
   /**
	* @Title: template 
	* @Description: TODO(跳转修改页面) 
	* @param @param cancelValidate
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author shenggege
	* @throws
	*/
   @RequestMapping(value="/findUpdate",method={RequestMethod.POST})
   public ModelAndView findUpdate(HttpServletRequest request,CancelValidate cancelValidate){
	   ModelAndView modelAndView = new ModelAndView();
	   String id = request.getParameter("id");
	   System.out.println(id);
	   CancelValidate cv = cancelValidateService.findCancelValidateById(new BigDecimal(id));
	   if(cv != null){
		   modelAndView.addObject("cancelValidate",cv);
	   }
	   modelAndView.setViewName("/admin/cancelValidate/update");
	   return modelAndView;
   }
	
	 
	/**
	 * @throws Exception   
	 * @Title: updateCancelUser 
	 * @Description: TODO(修改) 
	 * @param @param request
	 * @param @param response
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author shenggege
	 * @throws
	 */
	 @RequestMapping("/updateCancelUser")
	 public void updateCancelUser(CancelValidate cancelValidate) throws Exception
	 {
		 String id = request.getParameter("id");
		 String canceltype = request.getParameter("canceltype");
		 if(cancelValidate.getId() != null && canceltype != null){
			 AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			 cancelValidate.setAddman(adminUser.getUsername());
			 cancelValidate.setAddtime(new Date());
			 cancelValidateService.updateById(cancelValidate);
			 String json = JSON.toJSONString("编辑成功");
			 sendJsonData(response, json);
		 }
	 }

	 
	/** 
	 * @Title: detail 
	 * @Description: TODO(查看详情信息) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author shenggege
	 * @throws
	 */
	 @RequestMapping(value="/detail")
	 public ModelAndView detail(HttpServletRequest request)
	 {
		 String id = request.getParameter("id");
		 if(StringUtil.isNotEmpty(id))
		 {
			 ModelAndView modelAndView = new ModelAndView();
			 CancelValidate cancelValidate = cancelValidateService.findCancelValidateById(new BigDecimal(id));
			 modelAndView.addObject("cancelValidate", cancelValidate);
			 modelAndView.setViewName("admin/cancelValidate/detail");
			 return modelAndView;
		 }
		 else
		 {			 
			 return null;
		 }
	 }
	 	 
	 /**
	  * @throws
	  * @Title: findDelete
	  * @Description: TODO(跳转用户删除时的模态页面)
	  * @param request
	  * @param cancelValidate
	  * @return
	  * @author shenggege
	  */
	 @RequestMapping(value="/findDelete", method = {RequestMethod.GET, RequestMethod.POST})
	 public ModelAndView findDelete(HttpServletRequest request, CancelValidate cancelValidate){
		 ModelAndView modelAndView = new ModelAndView();
		 String id = request.getParameter("id");
		 if(id != null && id != ""){
		     cancelValidate = cancelValidateService.findCancelValidateById(new BigDecimal(id));
			 modelAndView.addObject("cancelValidate", cancelValidate);
		 }
		 modelAndView.setViewName("/admin/cancelValidate/findDelete");
		 return modelAndView;
	 }
	 
	 
	 
	 /**
	  * @throws Exception   
	  * @Title: remove 
	  * @Description: TODO(删除) 
	  * @param @return  参数说明 
	  * @return String    返回类型 
	  * @author shenggege
	  * @throws
	  */
	   @RequestMapping(value="/deleteUser", method = {RequestMethod.POST,RequestMethod.GET})
	   public void deleteUser(HttpServletRequest request) throws Exception
	 {
		 String id = request.getParameter("id");
		 if(id != null && id != ""){
		    CancelValidate cancelValidate = cancelValidateService.findCancelValidateById(new BigDecimal(id));
		    cancelValidateService.deleteByBaseId(new BigDecimal(id));
		    String jsonStr = JSON.toJSONString("操作:删除成功!");
		    sendJsonData(response, jsonStr);
		  }else{
		     String jsonStr = JSON.toJSONString("操作:删除失败!");	
		     sendJsonData(response, jsonStr);
		  }
	 }	   
	   	   
	  
}
	 