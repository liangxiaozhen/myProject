package com.ptpl.controller.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping(value="/admin/userBaseAccountInfo")
public class UserBaseAccountInfoManagerController extends BaseController {
	
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	
	//用户信息列表查询
	@RequestMapping(value = "/userBaseInfoList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView userBaseInfoList(UserBaseAccountInfo uinfo,HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String accountnumber = uinfo.getAccountnumber();
		String mobilephone = uinfo.getMobilephone();
		String loginname = uinfo.getLoginname();
//		request.setAttribute("accountnumber", accountnumber);
//		request.setAttribute("mobilephone", mobilephone);
		if(StringUtil.isNullStr(accountnumber)){
			accountnumber = null;
		}
		if(StringUtil.isNullStr(mobilephone)){
			mobilephone = null;
		}
		if(StringUtil.isNullStr(loginname)){
			loginname = null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		pageSize = "20";
		map.put("accountnumber", AES.getEncrypt(accountnumber));
		map.put("mobilephone", AES.getEncrypt(mobilephone));
		map.put("loginname", AES.getEncrypt(loginname));
		
		this.initPage(map, pageNum, pageSize);
		List<UserBaseAccountInfo>userBaseInfoList = userBaseAccountInfoService.getAllUserBaseAccountInfo(map);
		if(userBaseInfoList.size()>0){
			for(UserBaseAccountInfo ubai : userBaseInfoList){
				ubai.setLoginname(AES.getDecrypt(ubai.getLoginname()));
				ubai.setRealname(AES.getDecrypt(ubai.getRealname()));
				ubai.setEmail(AES.getDecrypt(ubai.getEmail()));
				ubai.setMobilephone(AES.getDecrypt(ubai.getMobilephone()));
				ubai.setCertificationnumber(AES.getDecrypt(ubai.getCertificationnumber()));
			}
		}
		for (Object o : userBaseInfoList) {
			UserBaseAccountInfo u = (UserBaseAccountInfo)o;
		}
		
		PageInfo<Object> pagehelper = this.initPagehelper(map,userBaseInfoList);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过personList取数据
		// modelAndView.addObject("personList", personList);
		
		modelAndView.addObject("uinfo",uinfo);
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.setViewName("admin/userInfo/userBaseInfoList");
		return modelAndView;

	}
}
