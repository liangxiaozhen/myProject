package com.ptpl.controller.manager;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.BaseController;
import com.ptpl.model.GlobalSetting;
import com.ptpl.service.GlobalSettingServiceI;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局设置操作
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/globalSetting")
public class GlobalSettingController extends BaseController {

	@Autowired
	private GlobalSettingServiceI globalSettingService;

	// 查询全部记录
	@RequestMapping(value = "sove", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView allGlobalSetting(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = new HashMap<>();
		List<GlobalSetting> list = globalSettingService.allData();
		for(GlobalSetting pagehelper:list){
			mv.addObject("global",pagehelper );
			break;
		}
		mv.setViewName("admin/globalSetting/list");
		return mv;
	}

	
	/*// 去添加页面
	@RequestMapping(value = "toadd", method = { RequestMethod.POST, RequestMethod.GET })
	public String toaddGlobalSetting(HttpServletResponse response, HttpServletRequest request) throws Exception {
		return "admin/globalSetting/add";
	}*/

	/*// 新增
	@RequestMapping(value = "add", method = { RequestMethod.POST, RequestMethod.GET })
	public String addGlobalSetting(HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		HashMap<String, String> map = new HashMap<String, String>();
		List<GlobalSetting> list = globalSettingService.allData();
		if(list.size()>=1){
			map.put("result", "fiel4");
			String str = JSON.toJSONString(map);
			StringUtil.sendJsonData(response, str);
			return null;
		}
		
		BigDecimal id = new BigDecimal(0);
		String websitename = request.getParameter("websitename");
		String title = request.getParameter("title");
		String keyWorld = request.getParameter("keyWorld");
		String description = request.getParameter("description");
		String strStatus = request.getParameter("strStatus");
		String annual = request.getParameter("annualRate");
		String auth = request.getParameter("authtimes");
		String GJIDCode=request.getParameter("GJIDCode");
		String presetStr=request.getParameter("presetStr");
		String urgentMethod=request.getParameter("UrgentMethod");//数字
		String GlobalVerifyCode=request.getParameter("GlobalVerifyCode");
		String UpGrade=request.getParameter("UpGrade");
		
		String autorptimesltd=request.getParameter("autorptimesltd");//系统自动提交还款次数限制  cjm
 		String autorpstinvl=request.getParameter("autorpstinvl");//系统自动提交还款提交间隔 cjm

		String pREAccount = request.getParameter("pREAccount");//红包账户lxf
		String pFeeAccount = request.getParameter("pFeeAccount");//手续费账户lxf
		String newerBidCount = request.getParameter("newerBidCount");//新手投标次数限制lxf
		String newerBidAmount = request.getParameter("newerBidAmount");//新手累投金额限制lxf
		String newerBidDayLimit = request.getParameter("newerBidDayLimit");//新手投标注册时间天数限制lxf
		String newerBidRule = request.getParameter("newerBidRule");//新手投标定义设置lxf

		try {
			
			GlobalSetting gs = new GlobalSetting();
			gs.setId(id);
			gs.setWebsitename(websitename);
			gs.setTitle(title);
			gs.setKeyworld(keyWorld);
			gs.setDescription(description);
			gs.setStrstatus(strStatus);
			gs.setGjidcode(GJIDCode);
			gs.setPresetstr(presetStr);

			if (!annual.trim().isEmpty()) {
				gs.setAnnualrate(Integer.valueOf(annual));
			}

			if (!auth.trim().isEmpty()) {
				gs.setAuthtimes(Integer.valueOf(auth));
			}
			
			if(!urgentMethod.trim().isEmpty()){
				gs.setUrgentmethod(Integer.valueOf(urgentMethod));
			}
			if(!GlobalVerifyCode.trim().isEmpty()){
				gs.setGlobalverifycode(Integer.valueOf(GlobalVerifyCode));
			}
			if(!UpGrade.trim().isEmpty()){
				gs.setUpgrade(Integer.valueOf(UpGrade));
			}
			
			if(StringUtil.isNotEmpty(autorptimesltd)){
				gs.setAutorptimesltd(Integer.valueOf(autorptimesltd.trim()));//系统自动提交还款次数限制  cjm
 			}
			
			if(StringUtil.isNotEmpty(autorpstinvl)){
 				gs.setAutorpstinvl(Integer.valueOf(autorpstinvl.trim()));//系统自动提交还款提交间隔 cjm
			}

			if (pFeeAccount!=null&&!"".equals(pFeeAccount)) {
				gs.setpFeeAccount(pFeeAccount.trim());//lxf
			}
			if (pREAccount!=null&&!"".equals(pREAccount)) {
				gs.setpREAccount(pREAccount.trim());  //lxf
			}
			if (newerBidCount!=null&&!"".equals(newerBidCount)) {
				gs.setNewerBidCount(Integer.valueOf(newerBidCount.trim()));//lxf
			}
			if (newerBidAmount!=null&&!"".equals(newerBidAmount)) {
				gs.setNewerBidAmount(Float.valueOf(newerBidAmount.trim()));//lxf
			}
			if (newerBidDayLimit!=null&&!"".equals(newerBidDayLimit)) {
				gs.setNewerBidDayLimit(Integer.valueOf(newerBidDayLimit.trim()));//lxf
			}
			if(newerBidRule!=null&&!"".equals(newerBidRule)){

				gs.setNewerBidRule(Integer.valueOf(newerBidRule.trim()));//lxf
			}


			int a = globalSettingService.insert(gs);
			if (a > 0) {
				map.put("result", "success");
			} else {
				map.put("result", "fiel2");
			}

			String str = JSON.toJSONString(map);
			StringUtil.sendJsonData(response, str);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
*/
	

	// 修改
	@RequestMapping(value = "updata", method = { RequestMethod.POST, RequestMethod.GET })
	public String UpdataGlobalSetting(HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String Id = request.getParameter("id");
		BigDecimal id = new BigDecimal(Id);
		String websitename = request.getParameter("websitename");
		String title = request.getParameter("title");
		String keyWorld = request.getParameter("keyWorld");
		String description = request.getParameter("description");
		String strStatus = request.getParameter("strStatus");
		String annual = request.getParameter("annualRate");
		String auth = request.getParameter("authtimes");
		String GJIDCode=request.getParameter("GJIDCode");
		String presetStr=request.getParameter("presetStr");
		String UrgentMethod=request.getParameter("UrgentMethod");//数字
		String GlobalVerifyCode=request.getParameter("GlobalVerifyCode");//数字
		String UpGrade=request.getParameter("UpGrade");//数字
		
		String autorptimesltd=request.getParameter("autorptimesltd");//系统自动提交还款次数限制  cjm
 		String autorpstinvl=request.getParameter("autorpstinvl");//系统自动提交还款提交间隔 cjm

		String pREAccount = request.getParameter("pREAccount");//红包账户lxf
		String pFeeAccount = request.getParameter("pFeeAccount");//手续费账户lxf
		String newerBidCount = request.getParameter("newerBidCount");//新手投标次数限制lxf
		String newerBidAmount = request.getParameter("newerBidAmount");//新手累投金额限制lxf
		String newerBidDayLimit = request.getParameter("newerBidDayLimit");//新手投标注册时间天数限制lxf
		String newerBidRule = request.getParameter("newerBidRule");//新手投标定义：1,2
		String failTTime = request.getParameter("failTTime");//流标缓冲时间lxf

		try {
			HashMap<String, String> map = new HashMap<String, String>();
			GlobalSetting gs = new GlobalSetting();
			gs.setId(id);
			gs.setWebsitename(websitename.trim());
			gs.setTitle(title.trim());
			gs.setKeyworld(keyWorld.trim());
			gs.setDescription(description.trim());
			gs.setStrstatus(strStatus.trim());
			gs.setGjidcode(GJIDCode.trim());
			gs.setPresetstr(presetStr.trim());

			if (!annual.trim().isEmpty()) {
				gs.setAnnualrate(Integer.valueOf(annual));
			}
			if (!auth.trim().isEmpty()) {
				gs.setAuthtimes(Integer.valueOf(auth));
			}
			if(!UrgentMethod.trim().isEmpty()){
				gs.setUrgentmethod(Integer.valueOf(UrgentMethod));
			}
			if(!GlobalVerifyCode.trim().isEmpty()){
				gs.setGlobalverifycode(Integer.valueOf(GlobalVerifyCode));
			}
			if(!UpGrade.trim().isEmpty()){
				gs.setUpgrade(Integer.valueOf(UpGrade));
			}
			
			if(StringUtil.isNotEmpty(autorptimesltd)){
				gs.setAutorptimesltd(Integer.valueOf(autorptimesltd.trim()));//系统自动提交还款次数限制  cjm
 			}
			
			if(StringUtil.isNotEmpty(autorpstinvl)){
 				gs.setAutorpstinvl(Integer.valueOf(autorpstinvl.trim()));//系统自动提交还款提交间隔 cjm
			}
			if (pFeeAccount!=null&&!"".equals(pFeeAccount)) {
				gs.setpFeeAccount(pFeeAccount.trim());//lxf
			}
			if (pREAccount!=null&&!"".equals(pREAccount)) {
				gs.setpREAccount(pREAccount.trim());  //lxf
			}

			if (newerBidCount!=null&&!"".equals(newerBidCount)) {
				gs.setNewerBidCount(Integer.valueOf(newerBidCount.trim()));//lxf
			}
			if (newerBidAmount!=null&&!"".equals(newerBidAmount)) {
				gs.setNewerBidAmount(Float.valueOf(newerBidAmount.trim()));//lxf
			}
			if (newerBidDayLimit!=null&&!"".equals(newerBidDayLimit)) {
				gs.setNewerBidDayLimit(Integer.valueOf(newerBidDayLimit.trim()));//lxf
			}
			if(newerBidRule!=null&&!"".equals(newerBidRule)){//lxf
				gs.setNewerBidRule(Integer.valueOf(newerBidRule.trim()));
			}

			if (failTTime!=null&&!"".equals(failTTime)){

				gs.setFailTTime(Integer.valueOf(failTTime.trim()));//lxf
			}

			int a = globalSettingService.updateByPrimaryKeySelective(gs);
			if (a > 0) {
				map.put("result", "success");
			} else {
				map.put("result", "fiel");
			}
			String str = JSON.toJSONString(map);
			StringUtil.sendJsonData(response, str);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// 删除
	@RequestMapping(value = "delete", method = { RequestMethod.POST, RequestMethod.GET })
	public String deletelGlobalSetting(HttpServletResponse response, HttpServletRequest request,
			GlobalSetting globalSetting) throws Exception {
		String str = request.getParameter("id");
		System.out.println(str);
		Map<String, String> map = new HashMap<String, String>();
		if (str.trim() != null) {
			BigDecimal bd = new BigDecimal(str);
			try {
				int a = globalSettingService.deleteByPrimaryKey(bd);
				System.out.println(a);
				if (a > 0) {
					map.put("result", "success");
				} else {
					map.put("result", "fail");
				}
				String tr = JSON.toJSONString(map);
				StringUtil.sendJsonData(response, tr);
				return null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	
	
	
	
	
}
