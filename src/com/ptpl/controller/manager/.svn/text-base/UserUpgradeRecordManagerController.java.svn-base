package com.ptpl.controller.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserUpgradeRecord;
import com.ptpl.service.UserUpgradeRecordServiceI;
import com.ptpl.web.util.AES;

/**
 * 用户等级升级记录Controller
 * 
 * @author xiaoy
 *
 * @date 2016年11月28日 上午10:54:20
 */
@RequestMapping("admin/userUpgradeRecord")
@Controller
@Scope("prototype")
public class UserUpgradeRecordManagerController extends BaseController {
	@Autowired
	private UserUpgradeRecordServiceI userUpgradeRecordService;

	/**
	 * 查询List
	 * 
	 * @param userUpgradeRecord
	 * @return
	 */
	@RequestMapping(value = "queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAll(UserUpgradeRecord userUpgradeRecord) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserUpgradeRecord> list = userUpgradeRecordService.selective(userUpgradeRecord);
		for (UserUpgradeRecord up : list) {
			up.setLoginname(AES.getDecrypt(up.getLoginname()));
		}
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/userUpgradeRecord/userUpgradeRecordList");
		mav.addObject("pagehelper", pagehelper);
		return mav;
	}
}
