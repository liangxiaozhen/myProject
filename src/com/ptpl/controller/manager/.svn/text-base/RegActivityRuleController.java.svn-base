package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.ManualAward_Constant;
import com.ptpl.constant.RegActAwardRule_Constant;
import com.ptpl.constant.RegActivityRule_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ActivityList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.RegActAwardRule;
import com.ptpl.model.RegActivityRule;
import com.ptpl.service.ActivityListServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.RegActAwardRuleServiceI;
import com.ptpl.service.RegActivityRuleServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * @ClassName: RegActivityRuleController
 * @Description: TODO(管理后台-注册活动规则)
 * @author zhenglm
 * @date 2016年12月6日 上午11:37:13
 */
@Controller
@RequestMapping("admin/activity")
public class RegActivityRuleController extends BaseController {

	/** 注册活动规则Service */
	@Autowired
	RegActivityRuleServiceI regActivityRuleService;
	
	/** 活动列表Service */
	@Autowired
	ActivityListServiceI activityListService;

	/** 注册活动奖励规则Service */
	@Autowired
	RegActAwardRuleServiceI regActAwardRuleService;

	@Autowired
	private AwardServiceI awardService;
	

	/**
	 * @Title: queryActivityList
	 * @Description: TODO(查看注册活动规则列表)
	 * @param activity
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/queryActivityList")
	public ModelAndView queryActivityList(RegActivityRule activity) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String,Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<RegActivityRule> activityList = regActivityRuleService.findRegActivityRuleList(activity); // 查询注册活动列表
		PageInfo<Object> pagehelper = initPagehelper(map, activityList);
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("status_map", RegActivityRule_Constant.STATUS_MAP);
		mv.addObject("isauditalist_map", RegActivityRule_Constant.ISAUDITALIST_MAP);
		// 数据回显
		mv.addObject("echodata", activity);
		// 指定视图
		mv.setViewName("admin/regActivityRule/RegActivityRule_list");
		return mv;
	}
	
	/**
	 * @Title: queryActivityDetail
	 * @Description: TODO(查看注册活动规则详情)
	 * @param actno
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/queryActivityDetail")
	public ModelAndView queryActivityDetail(String actno) throws Exception {
		ModelAndView mv = new ModelAndView();
		RegActivityRule detail = regActivityRuleService.selectByActNo(actno); // 根据活动编号查看注册活动详情
		mv.addObject("detail", detail);
		mv.addObject("isconsiderregtime", RegActivityRule_Constant.ISCONSIDERREGTIME_MAP.get(detail.getIsconsiderregtime()));
		mv.addObject("status", RegActivityRule_Constant.STATUS_MAP.get(detail.getStatus()));
		mv.addObject("isauditalist", RegActivityRule_Constant.ISAUDITALIST_MAP.get(detail.getIsauditalist()));
		mv.addObject("istemplet", RegActivityRule_Constant.ISTEMPLET_MAP.get(detail.getIstemplet()));
		mv.addObject("generatetype", RegActivityRule_Constant.GENERATETYPE_MAP.get(detail.getGeneratetype()));
		List<RegActAwardRule> infoList = regActAwardRuleService.selectByActId(detail.getId()); // 根据注册活动ID查看注册活动奖励规则
		mv.addObject("infoList", infoList);
		System.out.println(RegActAwardRule_Constant.BUSINESS_MAP);
		mv.addObject("business_map", RegActAwardRule_Constant.BUSINESS_MAP);
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
		mv.setViewName("admin/regActivityRule/RegActivityRule_detail");
		return mv;
	}
	
	/**
	 * @Title: toSetRule
	 * @Description: TODO(手动设置注册活动规则页面)
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/toSetRule")
	public ModelAndView toSetRule() throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			String actno = "GJZC"+StringUtil.getNo(); // 随机生成活动编号
			mv.addObject("actno", actno);
			regActivityRuleMap(mv);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 指定视图
			mv.setViewName("admin/regActivityRule/RegActivityRule_insert");
		}
		return mv;
	}
	
	/**
	 * @Title: queryActivityTemplet
	 * @Description: TODO(查询注册活动规则模板)
	 * @param activity
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/queryActivityTemplet")
	public ModelAndView queryActivityTemplet(RegActivityRule activity) throws Exception {
		ModelAndView mv = new ModelAndView();
		activity.setIstemplet(RegActivityRule_Constant.ISTEMPLET_YES);
		List<RegActivityRule> activityList = regActivityRuleService.findRegActivityRuleList(activity); // 查询注册活动模板列表
		Map<String, String> activityMap = new HashMap<String, String>();
		for(RegActivityRule rule : activityList){
			activityMap.put(rule.getActno(), rule.getActname()); // 将活动编号和活动名称传至前台页面
		}
		mv.addObject("activityMap", activityMap);
		mv.setViewName("admin/regActivityRule/RegActivityRule_templet");
		return mv;
	}
	
	/**
	 * @Title: setByTemplet
	 * @Description: TODO(模板生成注册活动)
	 * @param actno
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/setByTemplet")
	public ModelAndView setByTemplet(String actno) throws Exception {
		ModelAndView mv = new ModelAndView();
		RegActivityRule regActivityRule = regActivityRuleService.selectByActNo(actno); // 根据活动编号查询注册活动
		mv.addObject("regActivityRule", regActivityRule);
		String number = "GJZC"+StringUtil.getNo(); // 随机生成活动编号
		mv.addObject("actno", number);
		regActivityRuleMap(mv);
		mv.setViewName("admin/regActivityRule/RegActivityRule_templetInsert");
		return mv;
	}

	/**
	 * @Title: insertRegActivityRule
	 * @Description: TODO(保存设置好的注册活动规则并继续设置奖励规则)
	 * @param activity
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/insertRegActivityRule")
	public ModelAndView insertRegActivityRule(RegActivityRule activity) throws Exception {
		ModelAndView mv = new ModelAndView();
		int rows = 0;
		activity.setGeneratetype(RegActivityRule_Constant.GENERATETYPE_MANUAL); 	// 生成方式-1.模板，2.手动
		rows = insert(activity, mv);												// 保存设置好的注册活动规则
		if(rows > 0){
			// 保存成功跳转到注册活动奖励规则设置页面
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_insert");
		}else{
			// 保存失败停留在注册活动规则设置页面
			mv.setViewName("admin/regActivityRule/RegActivityRule_insert");
		}
		return mv;
	}

	/**
	 * @Title: insertRegActivityRuleByTemplet
	 * @Description: TODO(保存模板生成的注册活动规则并继续模板生成奖励规则)
	 * @param activity
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/insertRegActivityRuleByTemplet")
	public ModelAndView insertRegActivityRuleByTemplet(RegActivityRule activity) throws Exception {
		ModelAndView mv = new ModelAndView();
		int rows = 0;
		activity.setGeneratetype(RegActivityRule_Constant.GENERATETYPE_TEMPLATE); 	// 生成方式-1.模板，2.手动
		rows = insert(activity, mv); 												// 保存模板生成的注册活动规则
		if(rows > 0){
			String templetId = request.getParameter("templetId");
			RegActAwardRule awardRule = new RegActAwardRule();
			awardRule.setActid(new BigDecimal(templetId));
			awardRule.setBusiness(RegActAwardRule_Constant.BUSINESS_REGISTER);
			RegActAwardRule regActAwardRule = regActAwardRuleService.selectByActIdAndBusiness(awardRule);
			Award award = awardService.selectByPrimaryKey(regActAwardRule.getAwardid()); // 查询奖品信息
			mv.addObject("regActAwardRule", regActAwardRule);
			mv.addObject("award", award);
			mv.addObject("templetId", templetId); // 将模板活动ID传到模板新增奖励规则页面
			// 保存成功跳转到注册活动奖励规则设置页面
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_templetInsert");
		}else{
			// 保存失败停留在注册活动规则设置页面
			mv.setViewName("admin/regActivityRule/RegActivityRule_templetInsert");
		}
		return mv;
	}

	/**
	 * @Title: toSetAwardRule
	 * @Description: TODO(继续完善未完成的注册活动奖励规则 )
	 * @param id
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/toSetAwardRule")
	public ModelAndView toSetAwardRule(BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		RegActivityRule regActivity = regActivityRuleService.selectByPrimaryKey(id);
		List<RegActAwardRule> regActAwardRuleList = regActAwardRuleService.selectByActId(id); // 根据注册活动ID查询设置好的奖励规则
		Map<Short, String> business_map = new HashMap<Short, String>();
		if(regActAwardRuleList.size()==0){ // 未设置奖励规则-则首先设置指定业务-注册完成
			business_map.put(RegActAwardRule_Constant.BUSINESS_REGISTER, "注册完成");
			mv.addObject("business_map", business_map);
		}else{
			business_map = new HashMap<Short, String>();
			for(Entry<Short, String> entry : RegActAwardRule_Constant.BUSINESS_MAP.entrySet()){
				business_map.put(entry.getKey(), entry.getValue());
			}
			System.out.println(business_map);
			for(RegActAwardRule regactawardrule : regActAwardRuleList){
				business_map.remove(regactawardrule.getBusiness()); // 移除已经添加的业务
			}
			mv.addObject("business_map", business_map);
			System.out.println(business_map);
		}
		// 指定视图
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
		mv.addObject("actid", id);
		mv.addObject("cover", "cover"); // 隐藏返回上一页按钮
		mv.addObject("judge", regActivity.getIsconsiderregtime());
		mv.setViewName("admin/regActAwardRule/RegActAwardRule_insert");
		return mv;
	}

	/**
	 * @Title: toEditRegActRule
	 * @Description: TODO(跳转至修改注册活动规则页面)
	 * @param @param id
	 * @return ModelAndView
	 */
	@RequestMapping("/toEditRegActRule")
	public ModelAndView toEditRegActRule(BigDecimal id){
		ModelAndView mv = new ModelAndView();
		RegActivityRule detail = regActivityRuleService.selectByPrimaryKey(id);
		mv.addObject("detail", detail);
		regActivityRuleMap(mv);
		List<RegActAwardRule> ruleList = regActAwardRuleService.selectByActId(id); // 查询有无设置奖励规则，无则将继续编辑按钮隐藏，显示新增按钮
		if(ruleList==null)
			mv.addObject("show", "show");
		mv.setViewName("admin/regActivityRule/RegActivityRule_update");
		return mv;
	}
	
	
	
	/**
	 * @Title: updateRegActivityRuleStatus
	 * @Description: TODO(修改注册活动规则状态（停用、启用、作废）)
	 * @param id
	 * @param operation
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/updateRegActivityRuleStatus")
	public void updateRegActivityRuleStatus(BigDecimal id, String operation) throws Exception{
		RegActivityRule regActivityRule = regActivityRuleService.selectByPrimaryKey(id); // 根据ID查看注册活动规则详情
		int rows = 0;
		Short status = regActivityRule.getStatus();
		if(operation.equalsIgnoreCase("disabled")){
			if(status == 1 || status == 2)
				status = RegActivityRule_Constant.STATUS_STOPPED;
		}else if(operation.equalsIgnoreCase("enable")){
			if(status == 4){
				status = updateStatus(regActivityRule);
				Date begin = regActivityRule.getActbtime(); // 活动开始日期
				Date end = regActivityRule.getActetime();	// 活动结束日期
				Date current = new Date();	// 当前日期
				if(current.getTime() < begin.getTime()){ // 当前时间小于活动开始时间
					status = RegActivityRule_Constant.STATUS_PENDINGEXECUTE;
				}else if(current.getTime() >= begin.getTime() && current.getTime() <= end.getTime()){ // 当前时间在活动时间范围内
					status = RegActivityRule_Constant.STATUS_EXECUTING;
				}else if(current.getTime() > end.getTime()){
					status = RegActivityRule_Constant.STATUS_OVERDUE;
				}
			}
		}else if(operation.equalsIgnoreCase("invalid")){
			if(status == 1 || status == 2 || status == 4)
				status = RegActivityRule_Constant.STATUS_SUPERSEDED;
		}
		regActivityRule.setStatus(status); // 活动状态
		rows = regActivityRuleService.updateByPrimaryKeySelective(regActivityRule);
		String actno = regActivityRule.getActno();
		ActivityList activityList = new ActivityList();
		activityList.setActno(actno);
		ActivityList activity = activityListService.getActListByAl(activityList); // 根据活动编号查询活动列表
		activity.setStatus(status);
		activityListService.updateByPrimaryKeySelective(activity); // 根据活动编号修改活动列表
		System.out.println(rows);
		Map<String, String> map = new HashMap<String, String>();
		if(rows > 0){
			map.put("result", "操作成功！");
		}else{
			map.put("result", "操作失败！");
		}
		String jsonStr = JSON.toJSONString(map);
		sendJsonData(response, jsonStr);
	}

	/**
	 * @Title: deleteRegActivityRule
	 * @Description: TODO(删除注册活动规则)
	 * @param id
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/deleteRegActivityRule")
	public void deleteRegActivityRule(BigDecimal id) throws Exception{
		int rows = 0;
		RegActivityRule regActivityRule = regActivityRuleService.selectByPrimaryKey(id); // 根据id查询注册活动规则
		Short status = regActivityRule.getStatus();
		if(status != 2 && status != 3){
			rows = activityListService.deleteByActNo(regActivityRule.getActno()); 	// 先根据活动编号删除活动列表
			rows = regActAwardRuleService.deleteByActId(id);						// 再根据活动ID删除奖励规则
			rows = regActivityRuleService.deleteByPrimaryKey(id);					// 最后删除活动规则
		}
		Map<String, String> map = new HashMap<String, String>();
		if(rows > 0){
			map.put("result", "删除成功！");
		}else{
			map.put("result", "删除失败！");
		}
		String jsonStr = JSON.toJSONString(map);
		sendJsonData(response, jsonStr);
	}
	
	/**
	 * @Title: updateRegActivityRule
	 * @Description: TODO(只编辑并保存注册活动规，不继续编辑子活动)
	 * @param activity
	 * @throws Exception    设定文件
	 * @return void
	 */
	@RequestMapping("/updateRegActivityRule")
	public void updateRegActivityRule(RegActivityRule activity) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		try{
			int rows = 0;
			short status = updateStatus(activity);
			activity.setStatus(status);
			rows = regActivityRuleService.updateByPrimaryKeySelective(activity);
			if(rows>0){
				map.put("result", "修改成功！");
			}else{
				map.put("result", "修改失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			String jsonStr = JSON.toJSONString(map);
			sendJsonData(response, jsonStr);
		}
	}
	
	/**
	 * @Title: editRegActAwardRule
	 * @Description: TODO(继续编辑子活动)
	 * @param activity
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/editRegActAwardRule")
	public ModelAndView editRegActAwardRule(RegActivityRule activity) throws Exception {
		ModelAndView mv = new ModelAndView();
		int rows = 0;
		short status = updateStatus(activity);
		activity.setStatus(status);
		rows = regActivityRuleService.updateByPrimaryKeySelective(activity);
		if(rows>0){
			mv.addObject("result", "修改成功！");
			// 按顺序编辑：主活动->注册完成->姓名匹配->手机号验证->开通托管账户->银行卡绑定->安保密码设置->邮箱验证
			RegActAwardRule rule = new RegActAwardRule();
			rule.setActid(activity.getId());
			RegActAwardRule info = null;
			int flag = 1;
			for(int i=1;i<=7;i++){ // 循环出下一条存在的奖励规则
				rule.setBusiness((short) i);
				info = regActAwardRuleService.selectByActIdAndBusiness(rule);
				if(info!=null){
					flag = i;
					break;
				}
			}
			List<RegActAwardRule> regActAwardRuleList = regActAwardRuleService.selectByActId(activity.getId()); // 查询设置好的奖励规则个数
			RegActAwardRule awardrule = null;
			for(int j=flag+1;j<=7;j++){ // 继续循环下一条存在的奖励规则
				rule.setBusiness((short) j);
				awardrule = regActAwardRuleService.selectByActIdAndBusiness(rule);
				if(rule != null)
					break;
			}
			if(awardrule == null){
				mv.addObject("yc", "yc");
				if(regActAwardRuleList.size()<7){
					mv.addObject("show", "show");// 如果下一条不存在并且奖励规则总数小于7则显示新增按钮，隐藏继续编辑按钮
				}
			}
			Award award = awardService.selectByPrimaryKey(info.getAwardid());
			mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
			mv.addObject("ano", award.getAno());
			mv.addObject("info", info);
			mv.addObject("work", RegActAwardRule_Constant.BUSINESS_MAP.get(info.getBusiness()));
			mv.addObject("business", info.getBusiness());
			mv.addObject("actid", info.getActid());
			mv.addObject("judge", activity.getIsconsiderregtime());
			// 保存成功跳转到注册活动奖励规则设置页面
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_update");
		}else{
			mv.addObject("result", "修改失败！");
			regActivityRuleMap(mv);
			mv.addObject("detail", activity);
			// 保存成功跳转到注册活动奖励规则设置页面
			mv.setViewName("admin/regActAwardRule/RegActivityRule_update");
		}
		return mv;
	}
	
	/**
	 * @Title: regActivityRuleMap
	 * @Description: TODO(注册活动规则map)
	 * @param mv
	 * @return void
	 */
	private void regActivityRuleMap(ModelAndView mv){
		mv.addObject("isconsiderregtime_map", RegActivityRule_Constant.ISCONSIDERREGTIME_MAP);
		mv.addObject("isaudit_map", RegActivityRule_Constant.ISAUDITALIST_MAP);
		mv.addObject("istemplet_map", RegActivityRule_Constant.ISTEMPLET_MAP);
	}


	/**
	 * @Title: insert
	 * @Description: TODO(新增注册活动规则方法)
	 * @param activity
	 * @param mv
	 * @return int
	 */
	private int insert(RegActivityRule activity, ModelAndView mv){
		int rows = 0;
		activity.setStatus(RegActivityRule_Constant.STATUS_NOCOMPLETE); 			// 活动状态-0.待完善,1.待执行,2.执行中,3.已完成,4.已停用,5.已作废,6.已过期
		AdminUser adminUser = getAdminUser(); // 活动登录的系统管理员信息
		activity.setAddman(adminUser.getUsername()); 								// 制表人
		activity.setAddtime(new Date()); 											// 制表时间
		rows = regActivityRuleService.insertRegActivityRuleSelective(activity); 	// 保存设置好的注册活动规则
		if(rows > 0){
			saveActivityList(activity); // 将设置好的活动规则保存至活动列表
			mv.addObject("result", "保存成功！");
			mv.addObject("actid", activity.getId()); // 将注册活动ID传至前台页面隐藏域
			Map<Short, String> business_map = new HashMap<Short, String>();
			business_map.put(RegActAwardRule_Constant.BUSINESS_REGISTER, "注册完成"); // 首先设置指定业务-注册完成奖励规则设置
			mv.addObject("business_map", business_map);
			mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
			mv.addObject("cover", "cover"); // 隐藏返回上一页按钮
			mv.addObject("judge", activity.getIsconsiderregtime());
		}else{
			mv.addObject("result", "保存失败！");
			String actno = "GJZC"+StringUtil.getNo(); // 随机生成活动编号
			mv.addObject("actno", actno);
			regActivityRuleMap(mv);
		}
		return rows;
	}
	
	/**
	 * @Title: saveActivityList
	 * @Description: TODO(新增活动列表)
	 * @param activity
	 * @return void
	 */
	private void saveActivityList(RegActivityRule activity) {
		ActivityList activityList = new ActivityList();
		activityList.setActno(activity.getActno());						// 活动编号
		activityList.setActname(activity.getActname());					// 活动名称
		activityList.setActtype((short) 1);								// 活动类型-1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖
		activityList.setStatus(activity.getStatus());					// 活动状态-0.待完善，1待执行，2.执行中，3.已完成，4.已停用，5.已作废，6.已过期
		activityList.setIslistaudit(activity.getIsauditalist());		// 获奖名单是否审核
		activityList.setGeneratetype(activity.getGeneratetype());		// 生成方式-1.模板，2.手动
		activityList.setAllowmanual(ManualAward_Constant.RECTYPE_UNALLOW);// 手动执行-1.允许，0.不允许
		activityList.setExecutestatus((short) 4);						// 执行状态-1.手动，2.系统，3.自主，4.联动
		activityList.setActbegintime(activity.getActbtime());			// 活动开始日期
		activityList.setActendtime(activity.getActetime());				// 活动结束日期
		activityList.setMademan(activity.getAddman());					// 制表人
		activityList.setMadetime(activity.getAddtime());				// 制表时间
		activityList.setRemark(activity.getRemark());					// 备注
		activityListService.insertSelective(activityList);				// 保存设置好的注册活动规则同时生成活动列表
	}
	
	/**
	 * @Title: updateStatus
	 * @Description: TODO(更新注册活动状态)
	 * @param regActivityRule
	 * @return short
	 */
	private short updateStatus(RegActivityRule regActivityRule) {
		RegActivityRule rule = regActivityRuleService.selectByPrimaryKey(regActivityRule.getId());
		short status = rule.getStatus();
		if(status == 0 || status == 1){
			Date begin = regActivityRule.getActbtime(); // 活动开始日期
			Date end = regActivityRule.getActetime();	// 活动结束日期
			Date current = new Date();	// 当前日期
			if(current.getTime() < begin.getTime()){ // 当前时间小于活动开始时间
				status = RegActivityRule_Constant.STATUS_PENDINGEXECUTE;
			}else if(current.getTime() >= begin.getTime() && current.getTime() <= end.getTime()){ // 当前时间在活动时间范围内
				status = RegActivityRule_Constant.STATUS_EXECUTING;
			}else if(current.getTime() > end.getTime()){
				status = RegActivityRule_Constant.STATUS_OVERDUE;
			}
			String actno = rule.getActno();
			ActivityList activityList = new ActivityList();
			activityList.setActno(actno);
			ActivityList activity = activityListService.getActListByAl(activityList);
			activity.setStatus(status);
			activity.setActname(regActivityRule.getActname());
			activity.setActbegintime(begin);
			activity.setActendtime(end);
			activity.setRemark(regActivityRule.getRemark());
			activityListService.updateByPrimaryKeySelective(activity);
		}
		return status;
	}
}
