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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.China;
import com.ptpl.model.UserBankCard;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserBankCardServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

@Controller
//为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
//比如：商品列表：/items/queryItems.action
@RequestMapping(value="/admin/userbankcard")
public class UserBankCardController extends BaseController {
	
	@Autowired
	UserBankCardServiceI userBankCardService;

	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	
	/**
	 * 获取用户银行卡信息列表
	 */
	@RequestMapping(value="/queryBankInfoList", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView bankinfolist (HttpServletRequest request,UserBankCard userBankCard) throws Exception {		
		
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		
		if(userBankCard!=null && StringUtil.isNotEmpty(userBankCard.getUsername())){
			userBankCard.setUsername(AES.getEncrypt(userBankCard.getUsername()));
		}
		
		// 调用service层的方法得到对象列表
		List<UserBankCard> bankinfolist = userBankCardService.findUserBankInfo(userBankCard);
		if(bankinfolist.size()>0){
			for(UserBankCard uc : bankinfolist){
				uc.setUsername(AES.getDecrypt(uc.getUsername()));
			}
		}
		
		PageInfo<Object> pagehelper = initPagehelper(map, bankinfolist);
		// 条件回显
		String username = request.getParameter("username");
		
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("username", username); // 姓名
		mv.addObject("cardtypemap", Constant.CARDTYPE_MAP); // 卡类型常量MAP
		mv.addObject("bindmodemap", Constant.BINDMODE_MAP); // 绑定方式常量MAP
		mv.addObject("fastbindmap", Constant.ISFASTBINDCARD_MAP); // 是否快捷绑卡常量MAP
		mv.addObject("defaultcardmap", Constant.ISDEFAULTCARD_MAP); // 是否默认取现卡常量MAP
		mv.addObject("bindstatusmap", Constant.BINDSTATUS_MAP); // 绑定状态常量MAP
		// 指定视图
		mv.setViewName("admin/userbankcard/userbankcardList");
		return mv;
	}
	
	/**
	 * 查看用户银行卡信息详情
	 */
	@RequestMapping(value="/queryBankInfoDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryBankInfoDetail (HttpServletResponse response, long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try{
			UserBankCard carddetail = userBankCardService.findDetailById(id); // 查看银行卡详情
			if(carddetail!=null){
				carddetail.setUsername(AES.getDecrypt(carddetail.getUsername()));
				carddetail.setCardno(AES.getDecrypt(carddetail.getCardno()));
			}
			// 格式化时间
			if(carddetail.getBindtime() != null){
				carddetail.setBindtimeStr(sf.format(carddetail.getBindtime())); // 绑定时间
			}
			mv.addObject("carddetail", carddetail);
			mv.addObject("cardtype", Constant.CARDTYPE_MAP.get(carddetail.getCardtype())); // 卡类型
			mv.addObject("bindmode", Constant.BINDMODE_MAP.get(carddetail.getBindmode())); // 绑定方式
			mv.addObject("isfastbindcard", Constant.ISFASTBINDCARD_MAP.get(carddetail.getIsfastbindcard())); // 是否快捷绑卡
			mv.addObject("isdefaultcard", Constant.ISDEFAULTCARD_MAP.get(carddetail.getIsdefaultcard())); // 是否默认取现卡
			mv.addObject("bindstatus", Constant.BINDSTATUS_MAP.get(carddetail.getBindstatus())); // 绑定状态
			mv.setViewName("admin/userbankcard/userbankcard_detail");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return mv;
	}
	
	
	/**
	 * 去新增用户银行卡页面
	 */
	@RequestMapping(value="/gotoInsertPage", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toInsertPage (UserBaseAccountInfo userbaseaccountinfo) throws Exception{	
		ModelAndView mv = new ModelAndView();
		//获取省份列表
		List<China> provinces = userBankCardService.findProvinceByPid();
		// 获取用户基本信息表所有数据
		List<UserBaseAccountInfo> baseaccountinfo = userBaseAccountInfoService.getUserBaseAccountInfos(userbaseaccountinfo);
		mv.addObject("baseaccountinfo", baseaccountinfo);
		mv.addObject("provinces", provinces);
		mv.addObject("cardtype", Constant.CARDTYPE_MAP);
		// 指定视图
		mv.setViewName("admin/userbankcard/insertuserbankcard");
		return mv;
	}
	
	/**
	 * 根据选中的省份获取城市列表
	 */
	@RequestMapping(value="/getCitysList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getCitysList (HttpServletResponse response, String province) throws Exception{
		if (province != null && !"".equals(province)){
			China china = userBankCardService.findIdByName(province);
			long pid = Long.valueOf(china.getId().toString());
			List<China> citys = userBankCardService.findCityByPid(pid);
			String jsonStr = JSON.toJSONString(citys);
			sendJsonData(response, jsonStr);
		}
	}
	
	
	/**
	 * 保存用户银行卡信息
	 */
	@RequestMapping(value = "/saveUserBankInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertuserbankcard(UserBankCard userbankcard) throws Exception {
		ModelAndView mv = new ModelAndView();
		try{
			userBankCardService.insertSelective(userbankcard);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mv.setViewName("redirect:/userbankcard/queryBankInfoList.action");
		}
		return mv;
	}
	
	/**
	 * 跳转到用户银行卡信息修改页面
	 */
	@RequestMapping(value="/gotoUpdatePage", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toeditUserBankInfo (long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 回显用户银行卡信息
		UserBankCard bankinfo = userBankCardService.findDetailById(id);
		// 格式化日期
		if(bankinfo.getBindtime() != null){
			bankinfo.setBindtimeStr(sf.format(bankinfo.getBindtime()));
		}
		// 获取省份列表
		List<China> provinces = userBankCardService.findProvinceByPid();
		mv.addObject("provinces", provinces);
		mv.addObject("bankinfo", bankinfo);
		mv.addObject("cardtype", Constant.CARDTYPE_MAP);
		// 指定视图
		mv.setViewName("admin/userbankcard/updateuserbankcard");
		return mv;
	}
	
	/**
	 * 保存修改的用户银行卡信息
	 */
	@RequestMapping(value="/updateUserBankInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView editUserBankInfo (UserBankCard userbankcard) throws Exception {
		ModelAndView mv = new ModelAndView();
		try{
			userBankCardService.updateUserBankInfo(userbankcard);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mv.setViewName("redirect:/userbankcard/queryBankInfoList.action");
		}
		return mv;
	}
	
	/**
	 * 删除用户银行卡信息
	 */
	@RequestMapping(value="/deleteUserBankInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteUserBankInfo (HttpServletResponse response, long id) throws Exception {
		
		// 调用service 删除
		try {
			int rows = 0;
			rows = userBankCardService.deleteUserBankInfo(id);

			Map<String, String> map = new HashMap<String, String>();
			if (rows > 0) {
				map.put("personId", String.valueOf(id));
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}

			String jsonStr = JSON.toJSONString(map);
			sendJsonData(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}
