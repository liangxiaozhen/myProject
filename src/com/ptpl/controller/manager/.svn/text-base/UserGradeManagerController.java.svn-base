package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

/**
 * 会员等级设置
 * 
 * @author xiaoy
 *
 * @date 2016年11月30日 上午10:06:55
 */
@Controller
@RequestMapping("/admin/userGrade")
public class UserGradeManagerController extends BaseController {
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;

	/**
	 * 查看正常等级
	 * 
	 * @param userGrade
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	private ModelAndView queryAll(UserGrade userGrade) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserGrade> usergrades = userGradeService.selectiveForNormal(userGrade);
		List<UserGrade> queryList = userGradeService.selectiveForNormal(null);
		for (UserGrade ug : usergrades) {
			// 会员等级人员
			int number = userAccountSafeInfoService.getCountByUgrade(ug.getUgrade().intValue());
			ug.setNumber(number);
		}
		PageInfo<Object> pagehelper = initPagehelper(map, usergrades);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("userGrade", userGrade);
		modelAndView.addObject("queryList", queryList);
		modelAndView.setViewName("admin/userGrade/userGradeList");
		return modelAndView;
	}

	/**
	 * 查看废弃等级
	 * 
	 * @param userGrade
	 * @return
	 */
	@RequestMapping(value = "/queryAllStop", method = { RequestMethod.POST, RequestMethod.GET })
	private ModelAndView queryAllStop(UserGrade userGrade) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<UserGrade> usergrades = userGradeService.selectiveForStop(userGrade);
		PageInfo<Object> pagehelper = initPagehelper(map, usergrades);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("userGrade", userGrade);
		modelAndView.setViewName("admin/userGrade/userGradeStopList");
		return modelAndView;
	}

	/**
	 * 新增时，校验等级序号，等级名称
	 * 
	 * @param userGrade
	 * @throws Exception
	 */
	@RequestMapping(value = "verify", method = { RequestMethod.POST, RequestMethod.GET })
	private void verify(UserGrade userGrade) throws Exception {
		boolean flag = userGradeService.selective(userGrade).isEmpty();
		String data = "fail";
		if (flag) {
			data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 编辑时，校验等级序号，等级名称
	 * 
	 * @param userGrade
	 * @throws Exception
	 */
	@RequestMapping(value = "verifyUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	private void verifyUpdate(UserGrade userGrade) throws Exception {
		int iden = userGradeService.verifyName(userGrade);
		String data = "fail";
		if (iden == 0) {
			data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 跳转新增会员等级页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "insert_UI", method = { RequestMethod.POST, RequestMethod.GET })
	private ModelAndView insert_UI() {
		List<SpecialNameList> normalList = specialNameListService.getSpecialNameListForUserGrade();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/userGrade/insert_userGrade");
		mav.addObject("normalSpecialNameList", normalList);
		return mav;
	}

	/**
	 * 新增会员等级 m
	 * 
	 * @param userGrade
	 * @throws Exception
	 */
	@RequestMapping(value = "insert", method = { RequestMethod.POST, RequestMethod.GET })
	private void insert(UserGrade userGrade) throws Exception {
		String data = "fail";
		/*
		 * 验证等级名称
		 */
		UserGrade ug = new UserGrade();
		ug.setUgradename(userGrade.getUgradename());
		boolean flag = userGradeService.selective(ug).isEmpty();
		/*
		 * 判断等级名称是否存在
		 */
		if (flag) {
			int size = userGradeService.selective(null).size();
			if (size < 51) {
				/*
				 * 设置人和设置时间
				 */
				AdminUser admin = getAdminUser();
				userGrade.setAddman(admin.getUsername());
				userGrade.setAddtime(new Date());
				int iden = userGradeService.insertSelective(userGrade);
				if (iden > 0)
					data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 启用会员等级
	 * 
	 * @param id
	 * @throws Exception
	 */
	// @RequestMapping(value = "isUse", method = { RequestMethod.POST,
	// RequestMethod.GET })
	// private void isUse(BigDecimal id) throws Exception {
	// String data = "fail";
	// if (id != null && id.intValue() != 0) {
	// /*
	// * 设置人和设置时间
	// */
	// AdminUser admin = getAdminUser();
	// userGrade.setAddman(admin.getUsername());
	// userGrade.setAddtime(new Date());
	// int iden = userGradeService.updateByPrimaryKeySelective(userGrade);
	// /*
	// * iden>0,修改成功
	// */
	// if (iden > 0)
	// data = "success";
	// }
	// }
	// sendJsonData(response, JSON.toJSONString(data));
	// }

	/**
	 * 停用会员等级 m
	 * 
	 * @param id
	 * @throws Exception
	 */
	// @RequestMapping(value = "cancel", method = { RequestMethod.POST,
	// RequestMethod.GET })
	// private void cancel(BigDecimal id) throws Exception {
	// String data = "fail";
	// if (id != null && id.intValue() != 0) {
	// UserGrade userGrade = userGradeService.selectByPrimaryKey(id);
	// if (userGrade.getStatus().intValue() == 1) {
	// userGrade.setStatus((short) 0);
	// /*
	// * 设置人和设置时间
	// */
	// AdminUser admin = getAdminUser();
	// userGrade.setAddman(admin.getUsername());
	// userGrade.setAddtime(new Date());
	//
	// UserGradeExp userGradeExp = new UserGradeExp();
	// userGradeExp.setUgrade(userGrade.getUgrade().shortValue());
	// userGradeExp.setStatus((short) 0);
	// userGradeExpService.updateByStatus(userGradeExp);
	// int iden = userGradeService.updateByPrimaryKeySelective(userGrade);
	// if (iden > 0)
	// data = "success";
	// }
	// }
	// sendJsonData(response, JSON.toJSONString(data));
	// }

	/**
	 * 废弃会员等级 m
	 * 
	 * @param id
	 * @throws Exception
	 */
	// @RequestMapping(value = "stop", method = { RequestMethod.POST,
	// RequestMethod.GET })
	// private void stop(BigDecimal id) throws Exception {
	// String data = "fail";
	// if (id != null && id.intValue() != 0) {
	// UserGrade userGrade = userGradeService.selectByPrimaryKey(id);
	// // 当前会员等级状态必须为停用
	// if (userGrade.getStatus().intValue() == 0) {
	// // 当前序号
	// int rankNo = userGrade.getRankno().intValue();
	// List<UserGrade> list = userGradeService.selectiveForNormal(null);
	// for (UserGrade ug : list) {
	// // 循环中的序号，大于当前序号的都-1
	// int no = ug.getRankno().intValue();
	// if (no > rankNo) {
	// no--;
	// ug.setRankno((short) no);
	// userGradeService.updateByPrimaryKeySelective(ug);
	// }
	// }
	// /*
	// * 设置人和设置时间
	// */
	// AdminUser admin = getAdminUser();
	// userGrade.setAddman(admin.getUsername());
	// userGrade.setAddtime(new Date());
	// int iden = userGradeService.updateToStop(userGrade);
	// if (iden > 0)
	// data = "success";
	// }
	// }
	// sendJsonData(response, JSON.toJSONString(data));
	// }

	/**
	 * 转为正常会员 m
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "toNormal", method = { RequestMethod.POST, RequestMethod.GET })
	private void toNormal(BigDecimal id, int rankNo) throws Exception {
		String data = "fail";
		if (id != null && id.intValue() != 0) {
			UserGrade userGrade = userGradeService.selectByPrimaryKey(id);
			// 当前会员等级状态必须为停用
			if (userGrade.getStatus().intValue() == 2) {
				/*
				 * 设置人和设置时间
				 */
				AdminUser admin = getAdminUser();
				userGrade.setAddman(admin.getUsername());
				userGrade.setAddtime(new Date());
				userGrade.setRankno((short) rankNo);
				userGrade.setStatus((short) 0);
				int iden = userGradeService.updateToNormal(userGrade);
				if (iden > 0)
					data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 跳转编辑页面 m
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI", method = { RequestMethod.POST, RequestMethod.GET })
	private ModelAndView update_UI(BigDecimal id) {
		List<SpecialNameList> normalList = specialNameListService.getSpecialNameListForUserGrade();
		UserGrade userGrade = userGradeService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/userGrade/update_userGrade");
		mav.addObject("userGrade", userGrade);
		mav.addObject("normalSpecialNameList", normalList);
		return mav;
	}

	/**
	 * 编辑会员等级 m
	 * 
	 * @param userGrade
	 * @throws Exception
	 */
	@RequestMapping(value = "update", method = { RequestMethod.POST, RequestMethod.GET })
	private void update(UserGrade userGrade) throws Exception {
		String data = "fail";
		if (userGrade != null && userGrade.getRankno().intValue() != 0) {
			/*
			 * 验证等级名称
			 */
			int iden = userGradeService.verifyName(userGrade);
			/*
			 * 判断等级名称是否存在
			 */
			if (iden == 0 && userGrade.getUgrade() == null) {
				/*
				 * 设置人和设置时间
				 */
				AdminUser admin = getAdminUser();
				userGrade.setAddman(admin.getUsername());
				userGrade.setAddtime(new Date());
				int count = userGradeService.updateByPrimaryKey(userGrade);
				if (count > 0)
					data = "success";
			}
		} else {
			int count = userGradeService.updateByPrimaryKeySelective(userGrade);
			if (count > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 废弃等级编辑
	 * 
	 * @param userGrade
	 * @throws Exception
	 */
	@RequestMapping(value = "updateForStop", method = { RequestMethod.POST, RequestMethod.GET })
	private void updateForStop(UserGrade userGrade) throws Exception {
		String data = "fail";
		if (userGrade != null) {
			/*
			 * 设置人和设置时间
			 */
			AdminUser admin = getAdminUser();
			userGrade.setAddman(admin.getUsername());
			userGrade.setAddtime(new Date());
			int count = userGradeService.updateForStop(userGrade);
			if (count > 0)
				data = "success";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "queryById")
	private ModelAndView queryById(BigDecimal id) {
		if (id != null) {
			ModelAndView mav = new ModelAndView();
			UserGrade userGrade = userGradeService.selectByPrimaryKey(id);
			mav.setViewName("admin/userGrade/userGrade_Detail");
			mav.addObject("userGrade", userGrade);
			return mav;
		}
		return null;
	}

	/**
	 * 查看定向名单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "querySNL")
	private ModelAndView querySNL(BigDecimal id) {
		if (id != null) {
			ModelAndView mav = new ModelAndView();
			UserGrade userGrade = userGradeService.selectByPrimaryKey(id);
			short isSpecify = userGrade.getIsspecify();
			if (isSpecify == 1) {
				Set<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService.getUserForSNLID(userGrade.getSnlid());
				for (UserBaseAccountInfo ubai : ubaiList) {
					String loginName = ubai.getLoginname();
					String realName = ubai.getRealname();
					if (StringUtil.isNotEmpty(loginName)) {
						ubai.setLoginname(AES.getDecrypt(loginName));
					}
					if (StringUtil.isNotEmpty(realName)) {
						ubai.setRealname(AES.getDecrypt(realName));
					}
				}
				mav.setViewName("admin/userGrade/userGrade_SNL");
				mav.addObject("ubaiList", ubaiList);
				return mav;
			}
		}
		return null;
	}

	/**
	 * 查看会员等级中的用户
	 * 
	 * @param ugrade
	 * @return
	 */
	@RequestMapping(value = "queryUsers")
	private ModelAndView queryUsers(Short ugrade) {
		if (ugrade != null) {
			// String pageNum = request.getParameter("pageNum");
			// String pageSize = request.getParameter("pageSize");
			// Map<String, Object> map = new HashMap<String, Object>();
			// initPage(map, pageNum, pageSize);
			List<UserBaseAccountInfo> ubaiList = userBaseAccountInfoService.selectUsersByNormaluGrade(ugrade);
			for (UserBaseAccountInfo ubai : ubaiList) {
				String loginName = ubai.getLoginname();
				String realName = ubai.getRealname();
				if (StringUtil.isNotEmpty(loginName)) {
					ubai.setLoginname(AES.getDecrypt(loginName));
				}
				if (StringUtil.isNotEmpty(realName)) {
					ubai.setRealname(AES.getDecrypt(realName));
				}
			}
			// PageInfo<Object> pagehelper = initPagehelper(map, ubaiList);
			ModelAndView mav = new ModelAndView();
			// mav.addObject("pagehelper", pagehelper);
			mav.addObject("ubaiList", ubaiList);
			mav.setViewName("admin/userGrade/userGrade_number");
			return mav;
		}
		return null;
	}
}
