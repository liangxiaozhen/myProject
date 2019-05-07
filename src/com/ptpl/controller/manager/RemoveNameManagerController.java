package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserGrade;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.StringUtil;

/**
 * 名单管理
 * 
 * @author xiaoy
 *
 * @date 2016年11月10日 上午10:41:56
 */
@Controller
@RequestMapping("/admin/removeName")
@Scope("prototype")
public class RemoveNameManagerController extends BaseController {
	@Autowired
	private RemoveNameServiceI removeNameService;
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoService;
	@Autowired
	private UserGradeServiceI userGradeService;

	/**
	 * 小名单列表
	 * 
	 * @param removeName
	 * @return
	 */
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAllRemoveName(RemoveName removeName) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();

		initPage(map, pageNum, pageSize);
		List<RemoveName> removeNames = removeNameService.selective(removeName);
		for (RemoveName user : removeNames) {
			String loginname = user.getLoginname();
			String realname = user.getRealname();
			if (StringUtil.isNotEmpty(loginname)) {
				user.setLoginname(AES.getDecrypt(loginname));
			}
			if (StringUtil.isNotEmpty(realname)) {
				user.setRealname(AES.getDecrypt(realname));
			}
		}
		PageInfo<Object> pagehelper = initPagehelper(map, removeNames);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("rName", removeName);
		mav.addObject("action", RemoveName_Constant.QUERY_ACTION);
		mav.setViewName(RemoveName_Constant.LIST_VIEW);
		return mav;
	}

	/**
	 * 名单目录查看
	 * 
	 * @param removeName
	 * @return
	 */
	@RequestMapping(value = "/queryAllNameType", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAllName(RemoveName removeName) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<RemoveName> removeNames = removeNameService.selectiveNameType(removeName);
		List<RemoveName> nameTypes = removeNameService.getGroupNameType();
		List<RemoveName> names = null;
		if (StringUtil.isNotEmpty(removeName.getNametype()))
			names = removeNameService.getGroupName(removeName.getNametype());
		PageInfo<Object> pagehelper = initPagehelper(map, removeNames);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("nameTypes", nameTypes);
		mav.addObject("names", names);
		mav.addObject("rName", removeName);
		mav.addObject("action", RemoveName_Constant.QUERYNAMETYPE_ACTION);
		mav.setViewName(RemoveName_Constant.NAMELIST_VIEW);
		return mav;
	}

	/**
	 * 名单目录设置
	 * 
	 * @param removeName
	 * @return
	 */
	@RequestMapping(value = "/queryAllNameTypeForUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAllNameFotUpdate(HttpServletRequest request, HttpServletResponse response,
			RemoveName removeName) throws Exception {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<RemoveName> removeNames = removeNameService.selectiveNameType(removeName);
		List<RemoveName> names = null;
		if (StringUtil.isNotEmpty(removeName.getNametype()))
			names = removeNameService.getGroupName(removeName.getNametype());
		PageInfo<Object> pagehelper = initPagehelper(map, removeNames);
		List<RemoveName> nameTypes = removeNameService.getGroupNameType();
		ModelAndView mav = new ModelAndView();
		mav.addObject("nameTypes", nameTypes);
		mav.addObject("names", names);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("rName", removeName);
		mav.addObject("action", RemoveName_Constant.NAMETYPEUPDATE_ACTION);
		mav.setViewName(RemoveName_Constant.NAMELIST_VIEW);
		return mav;
	}

	/**
	 * 名单设置
	 *
	 * @param removeName
	 * @return
	 */
	@RequestMapping(value = "/queryAllForUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAllForUpdate(RemoveName removeName) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<RemoveName> removeNames = removeNameService.selective(removeName);
		for (RemoveName user : removeNames) {
			String loginname = user.getLoginname();
			String realname = user.getRealname();
			if (StringUtil.isNotEmpty(loginname)) {
				user.setLoginname(AES.getDecrypt(loginname));
			}
			if (StringUtil.isNotEmpty(realname)) {
				user.setRealname(AES.getDecrypt(realname));
			}
		}
		List<RemoveName> names = removeNameService.selectNameAndNameNo();
		PageInfo<Object> pagehelper = initPagehelper(map, removeNames);
		ModelAndView mav = new ModelAndView();
		mav.addObject("names", names);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("rName", removeName);
		mav.addObject("action", RemoveName_Constant.UPDATE_ACTION);
		mav.setViewName(RemoveName_Constant.LIST_VIEW);
		return mav;
	}

	/**
	 * 根据ID 查询记录
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/queryById", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryObject(BigDecimal id, Short uid) {
		if (id != null) {
			RemoveName rName = removeNameService.selectByPrimaryKey(id);
			rName.setRealname(AES.getDecrypt(rName.getRealname().trim()));
			rName.setLoginname(AES.getDecrypt(rName.getLoginname().trim()));
			ModelAndView mav = new ModelAndView();
			List<RemoveName> nameTypes = removeNameService.getGroupNameType();
			List<RemoveName> names = removeNameService.getNameNoAndName(rName.getNametype());
			mav.addObject("nameTypes", nameTypes);
			mav.addObject("names", names);
			mav.addObject("removename", rName);
			mav.setViewName(RemoveName_Constant.URL_MAP.get(uid));
			return mav;
		}
		return null;
	}

	/**
	 * 批量删除 UI
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/batchDelForUI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView batchDelUI(String ids) {
		if (StringUtil.isNotEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);
			String[] arr = ids.split(",");
			ModelAndView mav = new ModelAndView();
			mav.addObject("ids", ids);
			mav.addObject("count", arr.length);
			mav.setViewName(RemoveName_Constant.BATCHDELUI);
			return mav;
		}
		return null;
	}

	/**
	 * 批量编辑 UI
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/batchUpdateForUI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView batchUpdateUI(String ids) {
		if (StringUtil.isNotEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);
			String[] arr = ids.split(",");
			List<RemoveName> nameTypes = removeNameService.getGroupNameType();
			ModelAndView mav = new ModelAndView();
			mav.addObject("ids", ids);
			mav.addObject("count", arr.length);
			mav.addObject("nameTypes", nameTypes);
			mav.setViewName(RemoveName_Constant.BATCHUPDATE);
			return mav;
		}
		return null;
	}

	/**
	 * 查询名单目录详情
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/queryByNameType", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryDetils(String name, String nameType) {

		String nameno = removeNameService.selectNameNoForNT(name, nameType);
		String modelName = getModelName(nameno);
		List<RemoveName> rNames = removeNameService.selectByNameTypeName(name, nameType);
		for (RemoveName user : rNames) {
			String loginname = user.getLoginname();
			String realname = user.getRealname();
			if (StringUtil.isNotEmpty(loginname)) {
				user.setLoginname(AES.getDecrypt(loginname));
			}
			if (StringUtil.isNotEmpty(realname)) {
				user.setRealname(AES.getDecrypt(realname));
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/removename/removename_nametype_Detail");
		mav.addObject("rNames", rNames);
		mav.addObject("name", name);
		mav.addObject("nameType", nameType);
		mav.addObject("modelName", modelName);
		return mav;
	}

	/**
	 * 获取名单编号 引用的模块名称
	 * 
	 * @param nameno
	 * @return
	 */
	private String getModelName(String nameno) {
		List<String> tenderItemNos = removeNameService.getTenderitemRemoveNameNo();
		List<String> manualAwardNos = removeNameService.getManualAwardRewardLNNo();
		List<String> withdrawsCashNos = removeNameService.getWithdrawsCashRstrRemoveNameNo();
		List<String> rechargeRstrNos = removeNameService.getRechargeRstrRemoveNameNo();
		List<String> debtAttornNos = removeNameService.getDebtAttornRemoveNameNo();
		String modelName = "";
		for (String no : tenderItemNos) {
			if (StringUtil.isNotEmpty(no)) {
				int index = no.indexOf(nameno);
				if (index != -1) {
					modelName += " 标的设置 ";
					break;
				}
			}
		}
		for (String no : rechargeRstrNos) {
			if (StringUtil.isNotEmpty(no)) {
				int index = no.indexOf(nameno);
				if (index != -1) {
					modelName += " 充值设置 ";
					break;
				}
			}
		}
		for (String no : withdrawsCashNos) {
			if (StringUtil.isNotEmpty(no)) {
				int index = no.indexOf(nameno);
				if (index != -1) {
					modelName += " 提现设置 ";
					break;
				}
			}
		}
		for (String no : debtAttornNos) {
			if (StringUtil.isNotEmpty(no)) {
				int index = no.indexOf(nameno);
				if (index != -1) {
					modelName += " 标的债券转让 ";
					break;
				}
			}
		}
		for (String no : manualAwardNos) {
			if (StringUtil.isNotEmpty(no)) {
				int index = no.indexOf(nameno);
				if (index != -1) {
					modelName += " 手动颁奖设置 ";
					break;
				}
			}
		}
		return modelName;
	}

	/**
	 * 名单目录 删除UI
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/nameTypForDelUI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryNameTypeForDelUI(String name, String nametype, String nameno) {
		String modelName = getModelName(nameno);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/removename/delete_removename_nametype");
		if (StringUtil.isNotEmpty(modelName)) {
			mav.addObject("modelName", modelName);
		} else {
			mav.addObject("name", name);
			mav.addObject("nametype", nametype);
			mav.addObject("nameno", nameno);
		}
		return mav;
	}

	/**
	 * 名单目录 编辑UI
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/nameTypForUpdateUI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryNameTypeForUpdateUI(RemoveName rName) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("rName", rName);
		mav.setViewName("admin/removename/update_removename_nametype");
		return mav;
	}

	/**
	 * 编辑修改名单列表
	 * 
	 * @param rName
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateRemoveName(RemoveName rName) throws Exception {
		if (rName.getId() != null) {
			AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			rName.setAddman(adminUser.getUsername());
			rName.setAddtime(new Date());
			cleared(rName);
			removeNameService.updateByPrimaryKeySelective(rName);
			String json = JSON.toJSONString("操作：编辑成功。");
			sendJsonData(response, json);
		}
	}

	/**
	 * 批量编辑名单列表
	 * 
	 * @param rName
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public void batchUpdate(RemoveName rName, String ids) throws Exception {
		String name = rName.getName();
		String nameType = rName.getNametype();
		if (StringUtil.isNotEmpty(nameType) && StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(ids)) {
			String[] arr = ids.split(",");
			AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			for (String id : arr) {
				RemoveName removeName = new RemoveName();
				removeName.setId(new BigDecimal(id));
				removeName.setName(name);
				removeName.setNametype(nameType);
				cleared(removeName);
				removeName.setAddman(adminUser.getUsername());
				removeName.setAddtime(new Date());
				removeNameService.updateByPrimaryKeySelective(removeName);
			}
			String json = JSON.toJSONString("操作：编辑成功。");
			sendJsonData(response, json);
		}
	}

	/**
	 * 编辑保存名单目录
	 * 
	 * @param rName
	 * @throws Exception
	 */
	@RequestMapping(value = "/nameTypeUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public void nameTypeUpdate(RemoveName rName) throws Exception {
		String name = rName.getName();
		String nameType = rName.getNametype();
		if (name != "" && nameType != "") {
			String data = "fail";
			String nameno = removeNameService.selectNameNoForNT(name, nameType);
			if (StringUtil.isEmpty(nameno)) {
				data = "success";
				// 编号为空，生成新的编号
				nameno = StringUtil.getNameNoForName("GJRYMD");
				AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
				rName.setOldnameno(rName.getNameno());
				rName.setAddman(adminUser.getUsername());
				rName.setAddtime(new Date());
				rName.setNameno(nameno);
				removeNameService.updateNameType(rName);
			}
			sendJsonData(response, JSON.toJSONString(data));
		}
	}

	/**
	 * 根据ID 删除记录
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteById(BigDecimal id) throws Exception {
		if (id != null) {
			removeNameService.deleteByPrimaryKey(id);
			String jsonStr = JSON.toJSONString("操作：删除成功");
			sendJsonData(response, jsonStr);
		}
	}

	/**
	 * 批量删除名单
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchDel", method = { RequestMethod.POST, RequestMethod.GET })
	public void batchDelete(String ids) throws Exception {
		if (StringUtil.isNotEmpty(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				removeNameService.deleteByPrimaryKey(new BigDecimal(id));
			}
			String data = "提示：操作成功！";
			sendJsonData(response, JSON.toJSONString(data));
		}
	}

	/**
	 * 删除小名单
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/nameTypeDelete", method = { RequestMethod.POST, RequestMethod.GET })
	public void nameTypeDelete(String nameno) throws Exception {
		if (StringUtil.isNotEmpty(nameno)) {
			int iden = removeNameService.deleteName(nameno);
			if (iden > 0) {
				String jsonStr = JSON.toJSONString("操作：删除成功！");
				sendJsonData(response, jsonStr);
			} else {
				String jsonStr = JSON.toJSONString("操作：删除失败！");
				sendJsonData(response, jsonStr);
			}
		}
	}

	/**
	 * 启用
	 * 
	 * @param wdcRate
	 * @throws Exception
	 */
	@RequestMapping(value = "/isUse", method = { RequestMethod.POST, RequestMethod.GET })
	public void isUseRemoveName(String name, String nameType) throws Exception {
		if (name != "" && nameType != "") {
			removeNameService.updateIsUse(name, nameType);
		}
	}

	/**
	 * 停用
	 * 
	 * @param wdcRate
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancelUse", method = { RequestMethod.POST, RequestMethod.GET })
	public void cancelUseRemoveName(String name, String nameType) throws Exception {
		if (name != "" && nameType != "") {
			removeNameService.updateCancelUse(name, nameType);
		}
	}

	/**
	 * 新增目录
	 * 
	 * @param rName
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertName", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertName(RemoveName rName) throws Exception {
		String name = rName.getName();
		String nameType = rName.getNametype();
		if (!name.equals("") && !nameType.equals("")) {
			String data = "fail";
			String nameno = removeNameService.selectNameNoForNT(name, nameType);
			if (!(nameno != null)) {
				AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
				data = "success";
				RemoveName removeName = new RemoveName();
				removeName.setName(name);
				removeName.setNametype(nameType);
				cleared(removeName);
				removeName.setIsuse((short) 0);
				removeName.setAddtime(new Date());
				removeName.setAddman(adminUser.getUsername());
				removeNameService.insertSelective(removeName);
			}
			sendJsonData(response, JSON.toJSONString(data));
		}

	}

	/**
	 * 批量新增小名单
	 * 
	 * @param nameType
	 * @param name
	 * @param remark
	 * @param userID
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchInsert", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertRemoveName(String nameType, String name, String remark, String userID) throws Exception {
		if (StringUtil.isNotEmpty(nameType) && StringUtil.isNotEmpty(name)) {
			AdminUser adminUser = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
			userID = userID.substring(0, userID.length() - 1);
			String[] arr = userID.split(",");
			for (String baseID : arr) {
				if (StringUtil.isNotEmpty(baseID)) {
					BigDecimal bBaseId = new BigDecimal(baseID);
					// 查询 名单编号中，baseid是否存在
					RemoveName removeName = removeNameService.selectByBaseID(baseID, name, nameType);
					if (!(removeName != null)) { // 查询baseid 用户是否存在
						UserBaseAccountInfo ubai = userBaseAccountInfoService.getUserBaseAccountInfoById(bBaseId);
						if (ubai != null) {
							// 增加小名单记录
							removeName = new RemoveName();
							removeName.setName(name);
							removeName.setNametype(nameType);
							cleared(removeName);
							removeName.setAddtime(new Date());
							removeName.setAddman(adminUser.getUsername());
							removeName.setLoginname(ubai.getLoginname());
							removeName.setRealname(ubai.getRealname());
							removeName.setBaseid(bBaseId);
							removeName.setRemark(remark);
							removeNameService.insertSelective(removeName);
						}
					}
				}
			}
		}
		sendJsonData(response, JSON.toJSONString("提示：操作成功！"));
	}

	/**
	 * 跳转 新增小名单UI
	 * 
	 */
	@RequestMapping(value = "/insert_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertRemoveNameUI() {
		ModelAndView mav = new ModelAndView();
		List<RemoveName> nameTypes = removeNameService.getGroupNameType();
		List<RemoveName> names = removeNameService.selectNameAndNameNo();
		List<UserGrade> uGrades = userGradeService.getAll(null);
		mav.addObject("nameTypes", nameTypes);
		mav.addObject("names", names);
		mav.addObject("uGrades", uGrades);
		mav.setViewName(RemoveName_Constant.INSERT_VIEW);
		return mav;
	}

	/**
	 * 跳转 新增小名单目录UI
	 * 
	 */
	@RequestMapping(value = "/insertNameType_UI", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertNameTypeUI() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(RemoveName_Constant.INSERTNAMETYPE_VIEW);
		List<RemoveName> names = removeNameService.getGroupNameType();
		mav.addObject("names", names);
		return mav;
	}

	/**
	 * 获取子目录
	 * 
	 * @param nameType
	 *            主目录
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/getName", method = { RequestMethod.POST, RequestMethod.GET })
	public void getName(String nameType) throws Exception {
		if (nameType != null && !"".equals(nameType)) {
			List<RemoveName> names = removeNameService.getGroupName(nameType);
			String jsonStr = JSON.toJSONString(names);
			sendJsonData(response, jsonStr);
		}
	}

	/**
	 * 获取用户
	 * 
	 * @param rName
	 * @return
	 */
	@RequestMapping(value = "/getUser", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getUser(RemoveName rName) {
		String name = rName.getName();
		String nameType = rName.getNametype();
		String loginName = AES.getEncrypt(rName.getLoginname());
		if (StringUtil.isNotEmpty(nameType) && StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(loginName)) {
			String nameno = removeNameService.selectNameNoForNT(name, nameType);
			RemoveName removeName = new RemoveName();
			removeName.setNameno(nameno);
			removeName.setLoginname(loginName);
			// 名单下的用户
			List<RemoveName> rNames = removeNameService.selectListForInsert(removeName);
			// 所有用户
			List<RemoveName> users = removeNameService.selectUserForInsert(loginName);
			for (RemoveName rN : rNames) {
				boolean flag = false;
				for (RemoveName user : users) {
					// baseID相同，表明小名单中已存在，则排除
					if (user.getBaseid().equals(rN.getBaseid())) {
						users.remove(user);
						flag = true;
					}
					if (flag)
						break;
				}
			}
			for (RemoveName user : users) {
				String loginname = user.getLoginname();
				String realname = user.getRealname();
				if (StringUtil.isNotEmpty(loginname)) {
					user.setLoginname(AES.getDecrypt(loginname));
				}
				if (StringUtil.isNotEmpty(realname)) {
					user.setRealname(AES.getDecrypt(realname));
				}
			}
			ModelAndView mav = new ModelAndView();
			mav.addObject("rNames", users);
			mav.setViewName(RemoveName_Constant.BATCHINSERT);
			return mav;
		}
		return null;
	}

	/**
	 * 获取会员等级
	 * 
	 * @param nameType
	 * @param name
	 * @param uGrade
	 * @return
	 */
	@RequestMapping(value = "/getUgrade", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getUgrade(String nameType, String name, String uGrade) {

		if (StringUtil.isNotEmpty(nameType) && StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(uGrade)) {
			String nameno = removeNameService.selectNameNoForNT(name, nameType);
			RemoveName removeName = new RemoveName();
			removeName.setNameno(nameno);
			// 名单下的用户
			List<RemoveName> rNames = removeNameService.selectListForInsert(removeName);
			// 指定等级下所有用户
			List<RemoveName> uGrades = removeNameService.selectUserUgradeForInsert(uGrade);
			for (RemoveName rN : rNames) {
				boolean flag = false;
				for (RemoveName uG : uGrades) {
					// 判断 baseID是否相等，一样则排除
					if (rN.getBaseid().equals(uG.getBaseid())) {
						flag = true;
						uGrades.remove(uG);
					}
					if (flag)
						break;
				}
			}
			for (RemoveName uG : uGrades) {
				String loginname = uG.getLoginname();
				String realname = uG.getRealname();
				if (StringUtil.isNotEmpty(loginname)) {
					uG.setLoginname(AES.getDecrypt(loginname));
				}
				if (StringUtil.isNotEmpty(realname)) {
					uG.setRealname(AES.getDecrypt(realname));
				}
			}

			ModelAndView mav = new ModelAndView();
			mav.addObject("rNames", uGrades);
			mav.setViewName(RemoveName_Constant.BATCHINSERT);
			return mav;
		}
		return null;
	}

	/**
	 * 获取小名单
	 * 
	 * @param nameType
	 *            大名单
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRemoveName", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryRemoveName(String nameType) throws Exception {
		if (nameType != null && !"".equals(nameType)) {
			List<RemoveName> names = removeNameService.getNameNoAndName(nameType);
			String jsonStr = JSON.toJSONString(names);
			sendJsonData(response, jsonStr);
		}
	}

	/**
	 * 整理名单名称
	 * 
	 * @param record
	 */
	public void cleared(RemoveName record) {
		String nameno = removeNameService.selectNameNoForNT(record.getName(), record.getNametype());
		if (StringUtil.isEmpty(nameno)) {
			// 编号为空，生成新的编号
			nameno = StringUtil.getNameNoForName("GJRYMD");
		}
		record.setNameno(nameno);
	}
}
