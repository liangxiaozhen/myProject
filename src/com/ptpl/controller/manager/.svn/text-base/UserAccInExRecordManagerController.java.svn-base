package com.ptpl.controller.manager;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping(value = "/admin/userAccInExRecord")
@Scope("prototype")
public class UserAccInExRecordManagerController extends BaseController {

	@Autowired
	private AccInExRecordServiceI accInExRecordService;

	// 用户收支记录
	@RequestMapping(value = "/userAccInExRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView userAccInExRecord(UserBaseAccountInfo ubai) throws ParseException {
		ModelAndView mv = new ModelAndView();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String accountnumber = ubai.getAccountnumber();
		String mobilephone = ubai.getMobilephone();
		String realname = ubai.getRealname();
		String loginname=ubai.getLoginname();
		
		String usrcustid = request.getParameter("usrcustid");
		String start = request.getParameter("recordtimestart");
		String end = request.getParameter("recordtimeend");
		String type= request.getParameter("type");
		Short sType=StringUtils.isNotEmpty(type)?Short.valueOf(type):null;
		Date recordtimestart = null;
		Date recordtimeend = null;
		if (!StringUtil.isNullStr(start)) {
			recordtimestart = sf.parse(start);
		}
		if (!StringUtil.isNullStr(end)) {
			recordtimeend = sf.parse(end);
		}

		request.setAttribute("accountnumber", accountnumber);
		request.setAttribute("mobilephone", mobilephone);
		request.setAttribute("realname", realname);
		request.setAttribute("recordtimestart", start);
		request.setAttribute("recordtimeend", end);
		request.setAttribute("usrcustid", usrcustid);
		request.setAttribute("loginname", loginname);
		request.setAttribute("type",sType);
		Map<String, Object> map = new HashMap<String, Object>();
		pageSize = "10";
		map.put("accountnumber", accountnumber);
		map.put("mobilephone", AES.getEncrypt(mobilephone));
		map.put("realname", AES.getEncrypt(realname));
		map.put("loginname", AES.getEncrypt(loginname));
		map.put("type", sType);
		map.put("recordtimestart", recordtimestart);
		map.put("recordtimeend", recordtimeend);
		map.put("usrcustid", AES.getEncrypt(usrcustid));
		this.initPage(map, pageNum, pageSize);
		List<AccInExRecord> accInExRecord = accInExRecordService.queryAllUserAccInExRecord(map);
		for (AccInExRecord acc : accInExRecord) {
			String showLoginName = acc.getUbai().getLoginname();
			String showMobilephone = acc.getUbai().getMobilephone();
			String showRealName = acc.getUbai().getRealname();
			String showUsrId = acc.getUfai().getUsrcustid();
			if (StringUtils.isNotEmpty(showLoginName)) {
				acc.getUbai().setLoginname(AES.getDecrypt(showLoginName));
			}
			if (StringUtils.isNotEmpty(showMobilephone)) {
				acc.getUbai().setMobilephone(AES.getDecrypt(showMobilephone));
			}
			if (StringUtils.isNotEmpty(showRealName)) {
				acc.getUbai().setRealname(AES.getDecrypt(showRealName));
			}
			if (StringUtils.isNotEmpty(showUsrId)) {
				acc.getUfai().setUsrcustid(AES.getDecrypt(showUsrId));
			}
		}
		PageInfo<Object> pagehelper = this.initPagehelper(map, accInExRecord);
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("df", df);
		mv.addObject("state", state);
		mv.setViewName("admin/userAccount/userAccInExRecord");
		return mv;
	}
}
