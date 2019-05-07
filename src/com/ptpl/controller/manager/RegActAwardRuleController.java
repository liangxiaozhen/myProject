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
import com.ptpl.web.util.Arith;

/**
 * @ClassName: RegActAwardRuleController
 * @Description: TODO(管理后台-注册活动奖励规则)
 * @author zhenglm
 * @date 2016年12月6日 下午12:16:05
 *
 */
@Controller
@RequestMapping("admin/actAward")
public class RegActAwardRuleController extends BaseController {

	@Autowired
	RegActAwardRuleServiceI regActAwardRuleService;

	@Autowired
	private AwardServiceI awardService;

	@Autowired
	RegActivityRuleServiceI regActivityRuleService;
	
	@Autowired
	ActivityListServiceI activityListService;

	
	/**
	 * @Title: queryAwardName
	 * @Description: TODO(根据奖品编号获取奖品名称)
	 * @param awardno
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/queryAwardName")
	public void queryAwardName(String awardno) throws Exception {
		try {
			Award award = awardService.selectByAwardNo(awardno);
			String jsonStr = JSON.toJSONString(award);
			sendJsonData(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
	
	/**
	 * @Title: queryRestCopies
	 * @Description: TODO(查询可用的剩余奖品数)
	 * @param awardno
	 * @param awardcopies
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/queryRestCopies")
	public void queryRestCopies(String awardno,long awardcopies) throws Exception {
		try {
			Award award = awardService.selectByAwardNo(awardno);
			Long reset = award.getQuantityrest();
			if(Arith.sub(awardcopies, reset)>0){
				String jsonStr = "大于剩余奖品数";
				sendJsonData(response, jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * @Title: insertRegActAwardRule
	 * @Description: TODO(新增时:继续添加制定注册活动奖励规则)
	 * @param regActAwardRule
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/insertRegActAwardRule")
	public ModelAndView insertRegActAwardRule(RegActAwardRule regActAwardRule) throws Exception {
		ModelAndView mv = new ModelAndView();
		continueInsertAwardRule(regActAwardRule, mv);
		mv.setViewName("admin/regActAwardRule/RegActAwardRule_insert");
		return mv;
	}
	
	/**
	 * @Title: fillByTemplet
	 * @Description: TODO(模板填充注册活动奖励规则)
	 * @param templetId 注册活动模板ID
	 * @param actid 注册活动ID
	 * @param business 业务类型
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/fillByTemplet")
	public ModelAndView fillByTemplet(BigDecimal templetId, BigDecimal actid, Short business) throws Exception{
		ModelAndView mv = new ModelAndView();
		RegActAwardRule awardRule = new RegActAwardRule();
		awardRule.setActid(templetId);  // 选择的注册活动规则模板ID
		awardRule.setBusiness(business); // 选择的业务类型
		RegActAwardRule regActAwardRule = regActAwardRuleService.selectByActIdAndBusiness(awardRule); // 查询注册活动奖励规则模板
		Award award = awardService.selectByPrimaryKey(regActAwardRule.getAwardid()); // 查询奖品信息
		Map<Short, String> business_map = new HashMap<Short, String>();
		business_map.put(regActAwardRule.getBusiness(), RegActAwardRule_Constant.BUSINESS_MAP.get(regActAwardRule.getBusiness())); // 首先设置指定业务-注册完成奖励规则设置
		mv.addObject("regActAwardRule", regActAwardRule);
		mv.addObject("business_map", business_map);
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
		mv.addObject("templetId", templetId);
		mv.addObject("actid", actid);
		mv.addObject("award", award);
		RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(actid);
		mv.addObject("judge", activity.getIsconsiderregtime());
		mv.setViewName("admin/regActAwardRule/RegActAwardRule_templetInsert");
		return mv;
	}

	/**
	 * @Title: insertRegActAwardRule
	 * @Description: TODO(模板新增时：继续模板添加制定注册活动奖励规则)
	 * @param regActAwardRule
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/insertRegActAwardRuleByTemplet")
	public ModelAndView insertRegActAwardRuleByTemplet(RegActAwardRule regActAwardRule) throws Exception {
		ModelAndView mv = new ModelAndView();
		continueInsertAwardRule(regActAwardRule, mv);
		mv.setViewName("admin/regActAwardRule/RegActAwardRule_templetInsert");
		String templetId = request.getParameter("templetId");
		mv.addObject("templetId", templetId);
		return mv;
	}
	
	/**
	 * @Title: queryPreviousRule
	 * @Description: TODO(新增时：返回查询上一个奖励规则)
	 * @param cid 上一个奖励规则的ID
	 * @param templetId  注册活动规则模板ID
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/queryPreviousRule")
	public ModelAndView queryPreviousRule(BigDecimal cid, BigDecimal templetId) throws Exception {
		ModelAndView mv = new ModelAndView();
		RegActAwardRule info = regActAwardRuleService.selectByPrimaryKey(cid);
		mv.addObject("info", info);
		mv.addObject("actid", info.getActid());
		Award award = awardService.selectByPrimaryKey(info.getAwardid());
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
		mv.addObject("work", RegActAwardRule_Constant.BUSINESS_MAP.get(info.getBusiness()));
		mv.addObject("ano", award.getAno());
		mv.addObject("templetId", templetId);
		RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(info.getActid());
		mv.addObject("judge", activity.getIsconsiderregtime());
		mv.setViewName("admin/regActAwardRule/RegActAwardRule_detail");
		return mv;
	}

	/**
	 * @Title: returnPreviousRule
	 * @Description: TODO(编辑时：返回查询上一个奖励规则)
	 * @param @param id 注册活动奖励规则id
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/returnPreviousRule")
	public ModelAndView returnPreviousRule(BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		RegActAwardRule regActAwardRule = regActAwardRuleService.selectByPrimaryKey(id);
		Short business = regActAwardRule.getBusiness();
		regActAwardRule.setBusiness(business);
		RegActAwardRule info = regActAwardRuleService.selectByActIdAndBusiness(regActAwardRule);
		for(int i=business-1;i>=1;i--){
			business = (short) i;
			regActAwardRule.setBusiness(business);
			info = regActAwardRuleService.selectByActIdAndBusiness(regActAwardRule); // 按顺序编辑：主活动->注册完成->姓名匹配->手机号验证->开通托管账户->银行卡绑定->安保密码设置->邮箱验证
			if(info!=null)
				break;
		}
		if(business==1){
			RegActivityRule detail = regActivityRuleService.selectByPrimaryKey(regActAwardRule.getActid());
			mv.addObject("detail", detail);
			mv.addObject("isconsiderregtime_map", RegActivityRule_Constant.ISCONSIDERREGTIME_MAP);
			mv.addObject("isaudit_map", RegActivityRule_Constant.ISAUDITALIST_MAP);
			mv.addObject("istemplet_map", RegActivityRule_Constant.ISTEMPLET_MAP);
			mv.setViewName("admin/regActivityRule/RegActivityRule_update");
		}else{
			mv.addObject("actid", info.getActid());
			Award award = awardService.selectByPrimaryKey(info.getAwardid());
			mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
			mv.addObject("work", RegActAwardRule_Constant.BUSINESS_MAP.get(info.getBusiness()));
			mv.addObject("business", info.getBusiness());
			mv.addObject("ano", award.getAno());
			mv.addObject("info", info);
			RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(regActAwardRule.getActid());
			mv.addObject("judge", activity.getIsconsiderregtime());
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_update");
		}
		return mv;
	}
	

	/**
	 * @Title: insertAwardRule
	 * @Description: TODO(编辑好奖励规则后保存退出)
	 * @param regActAwardRule
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/update")
	public void update(RegActAwardRule regActAwardRule) throws Exception {
		int count = 0;
		updateStatus(regActAwardRule);
		refundAwardcopies(regActAwardRule); // 退还奖品
		count = regActAwardRuleService.updateByPrimaryKeySelective(regActAwardRule);
		deductionAwardcopies(regActAwardRule); // 扣除奖品
		Map<String, String> map = new HashMap<String, String>();
		if(count >0){
			map.put("result", "保存成功！");
		}else{
			map.put("result", "保存失败！");
		}
		String jsonStr = JSON.toJSONString(map);
		sendJsonData(response, jsonStr);
	}

	/**
	 * @Title: deleteAwardRule
	 * @Description: TODO(编辑时：删除奖励规则)
	 * @param regActAwardRule
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/deleteAwardRule")
	public ModelAndView deleteAwardRule(RegActAwardRule regActAwardRule) throws Exception {
		ModelAndView mv = new ModelAndView();
		Short business = regActAwardRule.getBusiness();
		BigDecimal actid = regActAwardRule.getActid();
		int count = 0;
		count = regActAwardRuleService.deleteByPrimaryKey(regActAwardRule.getId()); // 保存注册活动奖励规则
		if(count > 0){
			mv.addObject("result", "删除成功！");
		}else{
			mv.addObject("result", "删除失败！");
		}
		regActAwardRule.setBusiness(business);
		RegActAwardRule info = regActAwardRuleService.selectByActIdAndBusiness(regActAwardRule);
		for(int i=business-1;i>=1;i--){
			business = (short) i;
			regActAwardRule.setBusiness(business);
			info = regActAwardRuleService.selectByActIdAndBusiness(regActAwardRule);
			if(info!=null)
				break;
		}
		if(info==null){
			RegActivityRule detail = regActivityRuleService.selectByPrimaryKey(actid);
			mv.addObject("detail", detail);
			mv.addObject("isconsiderregtime_map", RegActivityRule_Constant.ISCONSIDERREGTIME_MAP);
			mv.addObject("isaudit_map", RegActivityRule_Constant.ISAUDITALIST_MAP);
			mv.addObject("istemplet_map", RegActivityRule_Constant.ISTEMPLET_MAP);
			mv.setViewName("admin/regActivityRule/RegActivityRule_update");
		}else{
			Award award = awardService.selectByPrimaryKey(info.getAwardid());
			mv.addObject("ano", award.getAno());
			mv.addObject("info", info);
			mv.addObject("business", info.getBusiness());
			mv.addObject("work", RegActAwardRule_Constant.BUSINESS_MAP.get(info.getBusiness()));
			mv.addObject("business_map", RegActAwardRule_Constant.BUSINESS_MAP);
			mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
			mv.addObject("actid", actid);
			RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(actid);
			mv.addObject("judge", activity.getIsconsiderregtime());
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_update");
		}
		return mv;
	}
	
	/**
	 * @Title: updateRegAwardRule
	 * @Description: TODO(编辑好奖励规则后继续编辑下一个奖励规则)
	 * @param regActAwardRule
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/updateRegAwardRule")
	public ModelAndView updateRegAwardRule(RegActAwardRule regActAwardRule) throws Exception {
		ModelAndView mv = new ModelAndView();
		Short business = regActAwardRule.getBusiness();
		BigDecimal actid = regActAwardRule.getActid();
		int rows = 0;
		refundAwardcopies(regActAwardRule); // 退还奖品
		rows = regActAwardRuleService.updateByPrimaryKeySelective(regActAwardRule);
		deductionAwardcopies(regActAwardRule); // 扣除奖品
		if(rows>0){
			mv.addObject("result", "修改成功！");
		}else{
			mv.addObject("result", "修改失败！");
		}
		RegActAwardRule info = null;
		int flag = business;
		for(int i=business+1;i<=7;i++){
			regActAwardRule.setBusiness((short) i);
			info = regActAwardRuleService.selectByActIdAndBusiness(regActAwardRule);
			if(info!=null){
				flag = i;
				break;
			}
		}
		List<RegActAwardRule> regActAwardRuleList = regActAwardRuleService.selectByActId(actid);
		if(info==null){
			regActAwardRule.setBusiness(business);
			info = regActAwardRuleService.selectByActIdAndBusiness(regActAwardRule);
		}
		RegActAwardRule rule = null;
		for(int j=flag+1;j<=7;j++){
			regActAwardRule.setBusiness((short) j);
			rule = regActAwardRuleService.selectByActIdAndBusiness(regActAwardRule);
			if(rule != null)
				break;
		}
		if(rule==null && regActAwardRuleList.size()<7)
			mv.addObject("show", "show");
		if(rule == null)
			mv.addObject("yc", "yc");
		Award award = awardService.selectByPrimaryKey(info.getAwardid());
		mv.addObject("ano", award.getAno());
		mv.addObject("info", info);
		mv.addObject("work", RegActAwardRule_Constant.BUSINESS_MAP.get(info.getBusiness()));
		mv.addObject("business", info.getBusiness());
		mv.addObject("business_map", RegActAwardRule_Constant.BUSINESS_MAP);
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
		mv.addObject("actid", actid);
		RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(actid);
		mv.addObject("judge", activity.getIsconsiderregtime());
		mv.setViewName("admin/regActAwardRule/RegActAwardRule_update");
		return mv;
	}
	
	/**
	 * @Title: updateRegActAwardRule
	 * @Description: TODO(新增时：更新注册活动奖励规则)
	 * @param @param regActAwardRule
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	@RequestMapping("/updateRegActAwardRule")
	public ModelAndView updateRegActAwardRule(RegActAwardRule regActAwardRule) throws Exception {
		ModelAndView mv = new ModelAndView();
		int rows = 0;
		refundAwardcopies(regActAwardRule); // 退还奖品
		rows = regActAwardRuleService.updateByPrimaryKeySelective(regActAwardRule);
		deductionAwardcopies(regActAwardRule); // 扣除奖品
		if(rows>0){
			mv.addObject("result", "修改成功！");
		}else{
			mv.addObject("result", "修改失败！");
		}
		List<RegActAwardRule> regActAwardRuleList = regActAwardRuleService.selectByActId(regActAwardRule.getActid());
		// 重新定义一个map,存放剩余未添加的业务类型
		Map<Short, String> business_map = new HashMap<Short, String>();
		for(Entry<Short, String> entry : RegActAwardRule_Constant.BUSINESS_MAP.entrySet()){
			business_map.put(entry.getKey(), entry.getValue());
		}
		System.out.println(business_map);
		for(RegActAwardRule regactawardrule : regActAwardRuleList){
			business_map.remove(regactawardrule.getBusiness()); // 移除已经添加的业务
		}
		System.out.println(business_map);
		mv.addObject("business_map", RegActAwardRule_Constant.BUSINESS_MAP);
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
		mv.addObject("actid", regActAwardRule.getActid());
		String templetId = request.getParameter("templetId");
		mv.addObject("cover", "cover");
		RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(regActAwardRule.getActid());
		mv.addObject("judge", activity.getIsconsiderregtime());
		if(templetId.equals("") || templetId.equals(null)){
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_insert");
		}else{
			mv.addObject("templetId", templetId);
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_templetInsert");
		}
		return mv;
	}
	
	/**
	 * @Title: saveRegActAwardRule
	 * @Description: TODO(新增时：保存设置好的注册活动奖励规则)
	 * @param regActAwardRule
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/saveRegActAwardRule")
	public void saveRegActAwardRule(RegActAwardRule regActAwardRule) throws Exception {
		AdminUser adminUser = getAdminUser();
		regActAwardRule.setAddman(adminUser.getUsername());
		regActAwardRule.setAddtime(new Date());
		int rows = 0;
		rows = regActAwardRuleService.insertRegActAwardRuleSelective(regActAwardRule);
		deductionAwardcopies(regActAwardRule); // 扣除奖品份数
		updateStatus(regActAwardRule); // 修改活动状态
		Map<String, String> map = new HashMap<String, String>();
		if(rows >0){
			map.put("result", "保存成功！");
		}else{
			map.put("result", "保存失败！");
		}
		String jsonStr = JSON.toJSONString(map);
		sendJsonData(response, jsonStr);
	}
	
	/**
	 * @Title: deletePreviousRegActAwardRule
	 * @Description: TODO(新增时：删除注册活动奖励规则)
	 * @param regActAwardRule
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/deletePreviousRegActAwardRule")
	public ModelAndView deletePreviousRegActAwardRule(RegActAwardRule regActAwardRule) throws Exception{
		ModelAndView mv = new ModelAndView();
		RegActAwardRule rule = regActAwardRuleService.selectByPrimaryKey(regActAwardRule.getId());
		BigDecimal actid = regActAwardRule.getActid(); // 获取actid传至新增页面隐藏域
		int rows = 0;
		rows = regActAwardRuleService.deleteByPrimaryKey(regActAwardRule.getId());
		List<RegActAwardRule> regActAwardRuleList = regActAwardRuleService.selectByActId(actid);
		if(regActAwardRuleList.size()==0){
			Map<Short, String> business_map = new HashMap<Short, String>();
			business_map.put((short) 1, "注册完成"); // 首先设置指定业务-注册完成奖励规则设置
			mv.addObject("business_map", business_map);
		}else if(regActAwardRuleList.size()>0){
			if(rule != null){
				RegActAwardRule_Constant.BUSINESS_MAP.put(rule.getBusiness(), RegActAwardRule_Constant.BUSINESS_MAP.get(rule.getBusiness()));
			}
			mv.addObject("business_map", RegActAwardRule_Constant.BUSINESS_MAP);
		}
		if(rows > 0){
			mv.addObject("result", "删除成功！");
		}else{
			mv.addObject("result", "删除失败！");
		}
		mv.addObject("actid", actid);
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
		mv.addObject("cover", "cover");
		String templetId = request.getParameter("templetId");
		RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(actid);
		mv.addObject("judge", activity.getIsconsiderregtime());
		if(templetId.equals("") || templetId.equals(null)){
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_insert");
		}else{
			mv.addObject("templetId", templetId);
			mv.setViewName("admin/regActAwardRule/RegActAwardRule_templetInsert");
		}
		return mv;
	}

	/**
	 * @Title: toSetAwardRule
	 * @Description: TODO(编辑时：继续新增完善未完成的注册活动奖励规则 )
	 * @param actid
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/insertRegAwardRule")
	public ModelAndView insertRegAwardRule(BigDecimal actid) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<RegActAwardRule> regActAwardRuleList = regActAwardRuleService.selectByActId(actid); // 根据注册活动ID查询设置好的奖励规则
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
		mv.addObject("actid", actid);
		mv.addObject("cover", "cover");
		RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(actid);
		mv.addObject("judge", activity.getIsconsiderregtime());
		mv.setViewName("admin/regActAwardRule/RegActAwardRule_insert");
		return mv;
	}


	/**
	 * @Title: continueInsertAwardRule
	 * @Description: TODO(继续添加注册奖励规则)
	 * @param regActAwardRule
	 * @param mv
	 * @return void
	 */
	private void continueInsertAwardRule(RegActAwardRule regActAwardRule, ModelAndView mv) {
		AdminUser adminUser = getAdminUser();
		regActAwardRule.setAddman(adminUser.getUsername());	// 制表人
		regActAwardRule.setAddtime(new Date());	// 制表时间
		int count = 0;
		count = regActAwardRuleService.insertRegActAwardRuleSelective(regActAwardRule); // 保存注册活动奖励规则
		deductionAwardcopies(regActAwardRule); // 扣除奖品份数
		if(count > 0){
			mv.addObject("result", "保存成功！");
		}else{
			mv.addObject("result", "保存失败！");
		}
		List<RegActAwardRule> regActAwardRuleList = regActAwardRuleService.selectByActId(regActAwardRule.getActid());
		// 重新定义一个map,存放剩余未添加的业务类型
		Map<Short, String> business_map = new HashMap<Short, String>();
		for(Entry<Short, String> entry : RegActAwardRule_Constant.BUSINESS_MAP.entrySet()){
			business_map.put(entry.getKey(), entry.getValue());
		}
		System.out.println(business_map);
		for(RegActAwardRule regactawardrule : regActAwardRuleList){
			business_map.remove(regactawardrule.getBusiness()); // 移除已经添加的业务
		}
		System.out.println(business_map);
		mv.addObject("business_map", business_map);
		System.out.println("注册活动规则==="+regActAwardRule.toString());
		mv.addObject("actid", regActAwardRule.getActid()); // 注册活动ID
		mv.addObject("cid", regActAwardRule.getId()); // 注册活动奖励规则ID
		if(regActAwardRuleList.size()==6){
			mv.addObject("hide", "hide");
		}
		RegActivityRule activity = regActivityRuleService.selectByPrimaryKey(regActAwardRule.getActid());
		mv.addObject("judge", activity.getIsconsiderregtime());
		mv.addObject("crestrict_map", RegActAwardRule_Constant.CRESTRICT_MAP);
	}
	
	/**
	 * @Title: updateStatus
	 * @Description: TODO(更新注册活动状态)
	 * @param regActivityRule
	 * @return short
	 */
	private void updateStatus(RegActAwardRule regActAwardRule) {
		BigDecimal actid = regActAwardRule.getActid();
		RegActivityRule regActivityRule = regActivityRuleService.selectByPrimaryKey(actid);
		short status = regActivityRule.getStatus();
		if(status == 0 || status == 1){
			Date begin = regActivityRule.getActbtime();
			Date end = regActivityRule.getActetime();
			if(new Date().getTime() < begin.getTime()){ // 当前时间小于活动开始时间
				status = RegActivityRule_Constant.STATUS_PENDINGEXECUTE;
			}else if(new Date().getTime() >= begin.getTime() && new Date().getTime() <= end.getTime()){ // 当前时间在活动时间范围内
				status = RegActivityRule_Constant.STATUS_EXECUTING;
			}else if(new Date().getTime() > end.getTime()){
				status = RegActivityRule_Constant.STATUS_OVERDUE;
			}
			regActivityRule.setStatus(status);
			int row = 0;
			row = regActivityRuleService.updateByPrimaryKeySelective(regActivityRule);
			String actno = regActivityRule.getActno();
			ActivityList activityList = new ActivityList();
			activityList.setActno(actno);
			ActivityList activity = activityListService.getActListByAl(activityList);
			activity.setStatus(status);
			activityListService.updateActivityList(activity);
			if(row > 0){
				System.out.println("修改状态成功");
			}
		}
	}
	
	/**
	 * @Title: refundAwardcopies
	 * @Description: TODO(退还奖品数量)
	 * @param regActAwardRule
	 * @return void
	 */
	private void refundAwardcopies(RegActAwardRule regActAwardRule) {
		BigDecimal awardid = regActAwardRule.getAwardid(); // 获取奖品ID
		RegActAwardRule awardRule = regActAwardRuleService.selectByPrimaryKey(regActAwardRule.getId());
		if(awardid==null){
			awardid = awardRule.getAwardid();
		}
		Integer Awardcopies = awardRule.getAwardcopies(); // 获取规则中原设置的奖品份数
		Award award = awardService.selectByPrimaryKey(awardid); // 查询奖品信息
		Long quantityrest = award.getQuantityrest(); // 奖品原剩余数量
		quantityrest = (long) Arith.add(quantityrest, Awardcopies); // 奖品原剩余数量+规则原设置的奖品份数
		int count = 0;
		count = awardService.update(award);
		if(count > 0){
			System.out.println("退还后奖品数量"+award.getQuantityrest());
			System.out.println("退还奖品数量成功");
		}
	}
	

	/**
	 * @Title: deductionAwardcopies
	 * @Description: TODO(扣除奖品数量)
	 * @param regActAwardRule
	 * @return void
	 */
	private void deductionAwardcopies(RegActAwardRule regActAwardRule) {
		BigDecimal awardid = regActAwardRule.getAwardid(); // 获取奖品ID
		RegActAwardRule awardRule = regActAwardRuleService.selectByPrimaryKey(regActAwardRule.getId());
		if(awardid==null){
			awardid = awardRule.getAwardid();
		}
		Integer Awardcopies = regActAwardRule.getAwardcopies(); // 获取规则中设置的奖品份数
		Award award = awardService.selectByPrimaryKey(awardid); // 查询奖品信息
		Long quantityrest = award.getQuantityrest(); // 奖品原剩余数量
		quantityrest = (long) Arith.sub(quantityrest, Awardcopies); // 奖品原剩余数量-规则中用掉的
		int count = 0;
		count = awardService.update(award);
		if(count > 0){
			System.out.println("扣除后奖品数量"+award.getQuantityrest());
			System.out.println("扣除奖品数量成功");
		}
	}
}
