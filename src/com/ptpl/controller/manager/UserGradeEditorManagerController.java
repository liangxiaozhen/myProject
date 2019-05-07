package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.RemoveName_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RemoveName;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

/**
 * 用户等级编辑 调整用户等级 0-->1 或者 1-->0
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年7月10日 上午9:18:08
 *
 */
@Controller
@RequestMapping(value = "admin/userGradeEditor")
public class UserGradeEditorManagerController extends BaseController {

	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;
	/* 会员等级集合 */
	private List<UserGrade> ugList;

	/**
	 * 查询List
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");
		String loginname = request.getParameter("loginname");// 用户名
		String realname = request.getParameter("realname"); // 真实姓名
		String ugrade = request.getParameter("ugrade"); // 会员等级

		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		map.put("loginname", AES.getEncrypt(loginname)); // 进行加密 存放进map
		map.put("realname", AES.getEncrypt(realname));
		map.put("ugrade", StringUtils.isNotEmpty(ugrade) ? Short.valueOf(ugrade) : null);
		List<UserBaseAccountInfo> list = userBaseAccountInfoService.listUserforUserGradeEditor(map);
		ugList = userGradeService.selective(null);
		PageInfo<Object> pagehelper = initPagehelper(map, list);

		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("loginname", loginname);
		mav.addObject("realname", realname);
		mav.addObject("userGradeList", ugList);
		mav.addObject("ugrade", ugrade);
		mav.setViewName("admin/userGradeEditor/userGradeEditor_List");
		return mav;
	}

	/**
	 * 查询单个User
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年7月10日 下午2:16:30
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryById")
	public ModelAndView queryById(BigDecimal id) {
		if (id != null) {
			UserBaseAccountInfo ubai = userBaseAccountInfoService.getUserforUserGradeEditor(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("userGradeList", ugList);
			mav.addObject("ubai", ubai);
			mav.setViewName("/admin/userGradeEditor/update_userGradeEditor");
			return mav;
		}
		return null;
	}

	/**
	 * 编辑用户等级
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年7月10日 下午2:36:58
	 * @param id
	 */
	@RequestMapping(value = "/update")
	public void update(BigDecimal id, Short ugrade) throws Exception {
		String data = "fail";
		UserAccountSafeInfo uasi = userAccountSafeInfoServiceI.selectBaseId(id);
		if (uasi != null) {
			uasi.setUgrade(ugrade);// 设置用户等级
			int iden = userAccountSafeInfoServiceI.update(uasi);
			if (iden > 0) {
				data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 批量编辑界面
	 * 
	 * @param ids
	 */
	@RequestMapping(value = "/batchUpdateForUI")
	public ModelAndView batchUpdateUI(String ids) {
		if (StringUtil.isNotEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);
			String[] arr = ids.split(",");
			ModelAndView mav = new ModelAndView();
			mav.addObject("ids", ids);
			mav.addObject("count", arr.length);
			mav.addObject("userGradeList", ugList);
			mav.setViewName("/admin/userGradeEditor/batchupdate_userGradeEditor");
			return mav;
		}
		return null;
	}

	/**
	 * 批量编辑用户等级
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年7月10日 下午3:42:13
	 * @param ids
	 * @param ugrade
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchUpdate")
	public void batchUpdate(String ids, Short ugrade) throws Exception {
		String data = "fail";
		if (StringUtils.isNotEmpty(ids) && ugrade != null) {
			int iden = userAccountSafeInfoServiceI.updateBatchUserGrade(ids, ugrade);
			if (iden > 0) {
				data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

}
