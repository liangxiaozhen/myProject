package com.ptpl.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huishang.util.HSignUtil;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserNameRuleModule;
import com.ptpl.model.UserPromo;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserNameRuleModuleServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.web.Iconstant.IUserBackground;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/user/userBaseInfo")
@Scope("prototype")
public class UserBaseAccountInfoController extends BaseController implements IUserBackground {

	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;

	@Autowired
	UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private UserPromoServiceI userPromoService;
	@Autowired
	private UserNameRuleModuleServiceI userNameRuleModuleService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
	// 跳转个人用户信息页面
	@RequestMapping(value = "/toBaseInfo", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView toBaseInfo()
	{
		UserBaseAccountInfo ubai = getUserBaseAccountInfo();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/login");
		if (ubai != null)
		{
			if (StringUtil.isNotEmpty(ubai.getMobilephone()))
			{
				modelAndView.setViewName("user/baseInfo/baseInfo");
				modelAndView.addObject("baseInfo", IUserBackground.BASEINFO);
				modelAndView.addObject("ubai", ubai);
			} else
			{
				modelAndView.setViewName("user/securitycenter/list");
			}
		}
		return modelAndView;
	}
	// 校验用户昵称,邮箱,手机号
	@RequestMapping(value = "/verifyLoginName", method = {RequestMethod.POST, RequestMethod.GET})
	public void verifyLoginName(HttpServletRequest req, HttpServletResponse res,
			UserBaseAccountInfo ubai) throws Exception
	{
		String jsonStr = "";
		jsonStr = JSON.toJSONString("add");
		UserBaseAccountInfo record = userBaseAccountInfoService
				.getUserBaseAccountInfoByOneCondition(ubai);
		if (record != null)
		{
			jsonStr = JSON.toJSONString("exist");
		}
		UserBaseAccountInfo ubai1 = getUserBaseAccountInfo();
		UserNameRuleModule unrm = new UserNameRuleModule();
		unrm.setRegistertype(ubai1.getRegtype());
		unrm = userNameRuleModuleService.selective(unrm).get(0);
		// 判断是否用户名手机号不能相同
		if (unrm.getSetrule().equals("b"))
		{
			if (ubai1.getMobilephone().equals(ubai.getLoginname()))
			{
				jsonStr = JSON.toJSONString("equal");
			}
		}
		/**
		 * 判断用户名是否包含中文
		 */
		if (unrm.getIschinese() != null && unrm.getIschinese().equals((short) 2))
		{
			if (StringUtil.isChinese(ubai.getLoginname()))
			{
				jsonStr = JSON.toJSONString("isChinese");
			}
		}
		// 用户名长度必须小于用户名规则设置的长度限制
		if (unrm.getUsernamelength() != null)
		{
			if (unrm.getUsernamelength().intValue() > ubai.getLoginname().length()
					|| unrm.getUsernamemaxlength() < ubai.getLoginname().length())
			{
				jsonStr = JSON.toJSONString("length");
			}
		}
		sendJsonData(res, jsonStr);
	}
	// 完善个人资料
	@RequestMapping(value = "/finishBaseInfo", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView finishBaseInfo(String loginname)
	{
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNotEmpty(loginname))
		{
			UserBaseAccountInfo newubai = getUserBaseAccountInfo();
			newubai.setLoginname(loginname);
			userBaseAccountInfoService.updateByPrimaryKeySelective(newubai);
			/*
			 * 用户推广设置用户名完善
			 */
			setUserPromo(newubai);
		}
		mv.setViewName("redirect:/user/userBaseInfo/toBaseInfo.action");
		return mv;
	}
	/**
	 * 用户推广设置用户名完善
	 * 
	 * @param newubai
	 */
	private void setUserPromo(UserBaseAccountInfo newubai)
	{
		UserPromo userPromo = new UserPromo();
		// ID
		userPromo.setId(newubai.getId());
		// 用户名
		userPromo.setLoginname(newubai.getLoginname());
		// 推广标记
		userPromo.setUserspecialflag((short) 0);
		// 真实姓名
		userPromo.setName(newubai.getRealname());
		userPromoService.updateByPrimaryKey(userPromo);
	}
	/**
	 * 用户修改头像图片
	 */
	@RequestMapping(value="/updateImg")
	public String updateImg(String loginname,@RequestParam(value="imagepath",required=false)  MultipartFile file , HttpServletRequest request) throws IllegalStateException, IOException{
		//获取物理路径
		UserBaseAccountInfo accountInfo=userBaseAccountInfoService.getHeadImg(setEncrypt(loginname));
		String fileName=file.getOriginalFilename();//获取文件的名字
	    String path=HSignUtil.DISK+"/upload/images/headimg/";  //服务器上的路径
		/*File fileImg=new File(HSignUtil.DISK+"\\upload\\images\\headimg");
		if(!fileImg.exists()){
			fileImg.mkdirs();
			System.out.println("创建文件夹...");
		}*/
		String getImgName=fileName.substring(0, fileName.lastIndexOf("."));//获取文件名
		System.out.println("截取字符串名："+getImgName);
		getImgName=setEncrypt(accountInfo.getId().toString());//将文件名修改成id加密后的名字
		String contentType=file.getContentType();  //获取文件类型
		if(accountInfo.getImagepath()!=null){
			File fileImgDet=new File(accountInfo.getImagepath());  //获取文件路径
			boolean flag = fileImgDet.delete();//删除  将不同格式的图片 同一个用户上传的移除
			System.out.println(flag?"移除成功":"移除失败");
		}
	   if(!file.isEmpty()){
			 file.transferTo(new File(path+getImgName+"."+contentType.substring(contentType.lastIndexOf("/")+1)));  
        }
		if(accountInfo!=null){
			accountInfo.setImagepath(path+getImgName+"."+contentType.substring(contentType.lastIndexOf("/")+1));
		}
	    System.out.println("修改之后的对象："+accountInfo);
		int i=userBaseAccountInfoService.updateHeaderImg(accountInfo);
		System.out.println(i>0?"修改成功":"修改失败");
	        return "redirect:/user/userBaseInfo/flushIndex.action";  
		}
	
	
	@RequestMapping(value = "/getImg", method = { RequestMethod.POST, RequestMethod.GET })
    public void getImg(String loginname,HttpServletResponse response) throws Exception {
	    UserBaseAccountInfo accountInfo=userBaseAccountInfoService.getHeadImg(setEncrypt(loginname));
		String json="0";
		if(accountInfo!=null && accountInfo.getImagepath()!=null){
			System.out.println("获取图片的路径："+accountInfo.getImagepath());
		    json=accountInfo.getImagepath().substring(accountInfo.getImagepath().lastIndexOf("/")+1);//获取最后一的图片名称
		}
		sendJsonData(response,json);
	}
	
	//跳转刷新页面
		@RequestMapping(value="/flushIndex")
		public String flushIndex(){
			return "user/manager/managerCenter";
		}
	//根据用户名查找用户信息
	@RequestMapping(value="/getInfoByLoginName")
	@ResponseBody
	public String getInfoByLoginName(String loginname) throws Exception{
		UserBaseAccountInfo accountInfo=userBaseAccountInfoService.getHeadImg(setEncrypt(loginname));
		if(accountInfo!=null){
			String phone=getDecrypt(accountInfo.getMobilephone());//把手机号码解密
			accountInfo.setMobilephone(phone);
			ObjectMapper mapper=new ObjectMapper();
			String json=mapper.writeValueAsString(accountInfo);
			return json;
		}
			return "0";
	}
	
	public static void main(String[] args) {
		String img="asdfasf.png";
		System.out.println(img.substring(0,img.lastIndexOf(".")));
		//System.out.println(img.replaceAll("ab", "123"));
	}
}
