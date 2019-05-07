package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.ptpl.constant.RechargeRstr_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.exception.MoneyException;
import com.ptpl.model.RechargeRate;
import com.ptpl.model.RechargeRstr;
import com.ptpl.model.RemoveName;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.UserGrade;
import com.ptpl.service.RechargeRstrServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;



/**
 * 充值设置限制
 * @author jxy
 *
 */
@Controller
@RequestMapping("/admin/rechargeRstr")
public class RechargeRstrController extends BaseController{
	@Autowired
	private RechargeRstrServiceI  rechargeRstrService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private RemoveNameServiceI removeNameService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;

	/**
	 * 充值设置操作列表
	* @Title: queryAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param rechargeRstr
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAll", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAll(HttpServletRequest request, HttpServletResponse response,RechargeRstr rechargeRstr) throws Exception {
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		
		//数据回显,模糊查询
		String ugradeCode = rechargeRstr.getUgrade();
		if (ugradeCode != null && !"".equals(ugradeCode))
		{
			int length = Integer.parseInt(ugradeCode);
			String index="";
			for(int i=0;i<length;i++){
				index = index+"_";
			}
			String ugrade = index+"1%";
			rechargeRstr.setUgrade(ugrade);
		}
		 if(rechargeRstr.getSelfpayratio()!=null){
	        	rechargeRstr.setSelfpayratio(rechargeRstr.getSelfpayratio()*100);
	        }
		 if(rechargeRstr.getProxypayratio()!=null){
			 rechargeRstr.setProxypayratio(rechargeRstr.getProxypayratio()*100);
		 }
		List<RechargeRstr> rechargeRstrList = rechargeRstrService.getAll(rechargeRstr);
		 if(rechargeRstr.getAddtime()!=null){
			 rechargeRstr.setAddtimeStr(sf.format(rechargeRstr.getAddtime()));
	        }
	        if(rechargeRstr.getAudittime()!=null){
	        	rechargeRstr.setAudittimeStr(sf.format(rechargeRstr.getAudittime()));
	        }

		List<UserGrade> ugrades = userGradeService.getAll(null);
		ModelAndView mav = new ModelAndView(RechargeRstr_Constant.LIST_VIEW);
		PageInfo<Object> pagehelper = initPagehelper(map, rechargeRstrList);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("rechargeRstrList", rechargeRstrList);
		mav.addObject("ugrades", ugrades);
		rechargeRstr.setUgrade(ugradeCode);
		mav.addObject("rechargeRstr", rechargeRstr);
		mav.addObject("df", df);
		return mav;
		}
	/**
	 * 充值设置记录列表
	* @Title: queryAllRecrod 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param rechargeRstr
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAllRecrod", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAllRecrod(HttpServletRequest request, HttpServletResponse response,RechargeRstr rechargeRstr) throws Exception {
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		
		//数据回显,模糊查询
		String ugradeCode = rechargeRstr.getUgrade();
		if (ugradeCode != null && !"".equals(ugradeCode))
		{
			int length = Integer.parseInt(ugradeCode);
			String index="";
			for(int i=0;i<length;i++){
				index = index+"_";
			}
			String ugrade = index+"1%";
			rechargeRstr.setUgrade(ugrade);
		}
		List<RechargeRstr> rechargeRstrList = rechargeRstrService.getAll(rechargeRstr);
		 if(rechargeRstr.getAddtime()!=null){
			 rechargeRstr.setAddtimeStr(sf.format(rechargeRstr.getAddtime()));
	        }
	        if(rechargeRstr.getAudittime()!=null){
	        	rechargeRstr.setAudittimeStr(sf.format(rechargeRstr.getAudittime()));
	        }

		List<UserGrade> ugrades = userGradeService.getAll(null);
		ModelAndView mav = new ModelAndView(RechargeRstr_Constant.LISTRECROD_VIEW);
		PageInfo<Object> pagehelper = initPagehelper(map, rechargeRstrList);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("rechargeRstrList", rechargeRstrList);
		mav.addObject("ugrades", ugrades);
		rechargeRstr.setUgrade(ugradeCode);
		mav.addObject("rechargeRstr", rechargeRstr);
		mav.addObject("df", df);
		return mav;
		}	


	/**
	 * 保存充值设置记录
	* @Title: saveRechargeRate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param rechargeRstr
	* @param @return
	* @param @throws MoneyException  参数说明 
	* @return String    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
	public String saveRechargeRate(HttpServletRequest request, HttpServletResponse response, RechargeRstr rechargeRstr)
			throws MoneyException {
		String ugrade = rechargeRstr.getUgrade(); // 获取到页面选择的
		String[] ugrades = rechargeRstr.getUgrades(); // 获取页面选择的会员等级
		//解析会员等级
		if (ugrade.equals("1")){
			List<UserGrade> uGrades = userGradeService.getAll(null);
			ugrade = StringUtil.getPlaceholder(51);
			System.out.println(ugrade+"会员等级");
			for (UserGrade ug : uGrades){	
				ugrade = StringUtil.setPlaceholder(ugrade,ug.getUgrade().intValue());
				System.out.println(ugrade+"会员等级1");
			}
			System.out.println(ugrade+"会员等级2");
		} else{	
			ugrade = StringUtil.setPlaceholderForArr1(ugrades,51);
		}
		
		rechargeRstr.setIsaudit((short)0);
		rechargeRstr.setIsuse((short)0);
		rechargeRstr.setAddman("张三丰");
		rechargeRstr.setAuditman("张无忌");
		rechargeRstr.setAddtime(new Date());
		rechargeRstr.setAudittime(new Date());
		rechargeRstr.setUgrade(ugrade);
		double Selfpayratio = rechargeRstr.getSelfpayratio();
			//充值人自付比例
			rechargeRstr.setSelfpayratio(Selfpayratio);
			double ProxyPayRatio = 100-Selfpayratio;
			rechargeRstr.setProxypayratio(ProxyPayRatio);
		rechargeRstrService.saveRechargeRstr(rechargeRstr);
		return "redirect:/admin/rechargeRstr/queryAll.action";
	}
	/**
	 * 编辑(修改)
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryEdits", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryEdit(HttpServletRequest request, HttpServletResponse response, long id) throws Exception {
		RechargeRstr rstr = rechargeRstrService.getByIdToRechargeRstr(new BigDecimal(id));
		ModelAndView modelAndView = new ModelAndView();
 		List<UserGrade> ugrades = userGradeService.getAll(null);
		List<Integer> uList = StringUtil.pars(rstr.getUgrade());
		String str = "";
		String ugrade="";
		for (int b : uList) {
			System.out.println(b + "级"); // 根据b这个编号查询数据库,得到会员等级名称
			// 在这里处理会员等级的名称的
			if (userGradeService.getCodeByUserGradeName(new BigDecimal(b)) != null) {
				str += userGradeService.getCodeByUserGradeName(new BigDecimal(b)) + ",";
			}
			ugrade=ugrade+String.valueOf(b)+",";
			System.out.println(ugrade);
		}
		//查询全部定向名单编号
		SpecialNameList sn = new SpecialNameList();
		sn.setIsUse((short)1);
		sn.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlList = specialNameListService.getSpecialNameListByNo(sn);
		
		rstr.setUgradeStr(str.substring(0, str.length()));// 删除最后一个字符*/		
		modelAndView.addObject("rstr", rstr);
		modelAndView.addObject("snlcode", rstr.getSnlid());
		modelAndView.addObject("snlList", snlList);
		modelAndView.addObject("ugrades", ugrades);
		modelAndView.addObject("uList", ugrade.substring(0,ugrade.length()));
		modelAndView.addObject("df1", df1);
		modelAndView.setViewName("admin/rechargeRstr/rechargeRstrEdits");
		return modelAndView;
	}
   //修改(也就是模态框中的保存)
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateRechargeRate(HttpServletRequest request, HttpServletResponse response,
			RechargeRstr rechargeRstr) throws MoneyException {
		
		String ugrade = rechargeRstr.getUgrade(); // 获取到页面选择的
		String[] ugrades = rechargeRstr.getUgrades(); // 获取页面选择的会员等级
		//解析会员等级
		if (ugrade.equals("1"))
		{
			List<UserGrade> uGrades = userGradeService.getAll(null);
			ugrade = StringUtil.getPlaceholder(51);
			for (UserGrade ug : uGrades)
			{	
				ugrade = StringUtil.setPlaceholder(ugrade,
						ug.getUgrade().intValue());
			}
		} else if (ugrade.equals("2"))
		{	
			ugrade = StringUtil.setPlaceholderForArr1(ugrades,51);
		}
		//判断单笔充值金额
		if(rechargeRstr.getLowestmoney()>=rechargeRstr.getHightestmoney()){
			return null; 
		}
		rechargeRstr.setUgrade(ugrade);
		rechargeRstr.setIsaudit((short)0);
		rechargeRstr.setIsuse((short)0);
		double Selfpayratio = rechargeRstr.getSelfpayratio();
		//充值人自付比例
		rechargeRstr.setSelfpayratio(Selfpayratio);
		double ProxyPayRatio = 100-Selfpayratio;
		rechargeRstr.setProxypayratio(ProxyPayRatio);
		    rechargeRstrService.updateRechargeRstr(rechargeRstr);
			return "redirect:/admin/rechargeRstr/queryAll.action";
	}
	 

	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView deleteRechargeRate(HttpServletRequest request, HttpServletResponse response, long id) {
		long row;
		try {
				row = rechargeRstrService.deleteRechargeRstr(new BigDecimal(id));
				Map<String, String> map = new HashMap<String, String>();
				if (row > 0) {
					map.put("rechargeRstrId", String.valueOf(id));
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
		return null;
	}
	//详情
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryDetails", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryDetails(HttpServletRequest request, HttpServletResponse response, BigDecimal id)
			throws Exception {
		RechargeRstr rechargeRstr= rechargeRstrService.getByIdToRechargeRstr(id);
		System.out.println(rechargeRstr.getRemovenamenoStr()+"***********");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rechargeRstr", rechargeRstr);
		modelAndView.addObject("df", df);
		modelAndView.setViewName("admin/rechargeRstr/rechargeRstrDetalis");
		return modelAndView;
	}
	//添加页面的跳转
	@RequestMapping("/insert")
	public ModelAndView insert(){
		List<UserGrade> ugrades = userGradeService.getAll(null);
		List<RemoveName> removenamenos = removeNameService.getRemoveName();
		ModelAndView mav = new ModelAndView(RechargeRstr_Constant.INSERT_VIEW);
		SpecialNameList sn = new SpecialNameList();
		sn.setIsUse((short)1);
		sn.setBusinessType((short)1);//系统业务才可引用
		List<SpecialNameList> snlList = specialNameListService.getSpecialNameListByNo(sn);
		mav.addObject("removenamenos",removenamenos);
		mav.addObject("snlList",snlList);
		mav.addObject("ugrades", ugrades);
		return mav;
	}
	/**
	 * 根据定向名单编号获取定向名单的名称
	 * @param @param code
	 * @return void
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	@RequestMapping(value = "/codeForName", method = { RequestMethod.POST, RequestMethod.GET })
	public void codeForName(String code) throws Exception{
		SpecialNameList sn = new SpecialNameList();
		sn.setBusinessNo(code);
		SpecialNameList snname = specialNameListService.getSnlsByNoOrName(sn);
		String name = snname.getBusinessName();
		String snid = snname.getId().toString();
		String dataStr = name+","+snid; 
		String data=JSON.toJSONString(dataStr);
		sendJsonData(response, data);
	}
	/**
	 * 根据ID 查询记录
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryById", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateAudit(HttpServletRequest request, HttpServletResponse response,
			BigDecimal id) {
		RechargeRstr  rechargeRstr  = rechargeRstrService.getByIdToRechargeRstr(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("rechargeRstr", rechargeRstr);
		mav.addObject("df1", df1);
		mav.setViewName(RechargeRstr_Constant.AUDIT);
		return mav;
	}

	/**
	 * 根据ID 审核记录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditrate", method = {RequestMethod.POST,
			RequestMethod.GET})
	public void auditRate(HttpServletRequest request,
			HttpServletResponse response, RechargeRstr rechargeRstr)	
			throws Exception{
		 RechargeRstr recharge = rechargeRstrService.getByIdToRechargeRstr(rechargeRstr.getId());
		 List<RechargeRstr> ugradeAndRecharTypeList = rechargeRstrService.getIsAudit();
		 List<Integer> typeList=new ArrayList<Integer>();
		 List<RechargeRstr> ratrList = new ArrayList<RechargeRstr>();
		 boolean flag =false;
		 if(ugradeAndRecharTypeList!=null){
			 for (int i = 0; i < ugradeAndRecharTypeList.size(); i++) {//循环遍历已经通过审核的充值设置对象,比对和要审核的对象有没有会员等级相同的
				 flag = StringUtil.getCompareUGrade(recharge.getUgrade(),ugradeAndRecharTypeList.get(i).getUgrade());
				 if(flag){
					 BigDecimal id = ugradeAndRecharTypeList.get(i).getId();  //假如说匹配会员等级成功(或者是说在数据库中查询到有包含所要审核的会员等级),就根据id拿到相应的数据 
					 RechargeRstr recharge2 = rechargeRstrService.getByIdToRechargeRstr(id);
					 ratrList.add(recharge2);
				 }
			 }
			 int inden = 0;
			 for (RechargeRstr rechargeRstr2 : ratrList) {//在会员等级相同的情况下,比对充值方式,如果有相同的,返回0,如果没有返回-1
				 inden = checkAuditRecharType(recharge.getRechartype(),rechargeRstr2.getRechartype());
				 typeList.add(inden);
			 }
			 boolean flag1 = vilidata(typeList);
			 if(flag1){//假如说为真,说明充值方式有相同的数据
				 String jsonStr = JSON.toJSONString("提示："+recharge.getUgradeStr()+"  "+recharge.getRechartype()+"已经通过审核");
				 sendJsonData(response, jsonStr);
			 }else{
				 rechargeRstr.setIsaudit(RechargeRstr_Constant.ISAUDIT_YES);
				 rechargeRstrService.updateRechargeRstr(rechargeRstr);
			 }
		 }else{//如果数据库没有查询到和当前需要审核数据相同的充值设置记录,那么就让他通过
			 rechargeRstr.setIsaudit(RechargeRstr_Constant.ISAUDIT_YES);
			 rechargeRstrService.updateRechargeRstr(rechargeRstr);
		 }
	}
  /**对list中的值循环便历,如果有为0的值,说明会员等级在数据库中有审核通过了的*/
   public boolean vilidata(List<Integer> ugradeList){
	     for (int i = 0; i < ugradeList.size(); i++) {
			  System.out.println(ugradeList.get(i));
			  int a = ugradeList.get(i);
			  if(a==0){
				  return true;//表示有相同的
			  }
		}
	   	return false;
	   
   }
	/**
	 * 验证审核
	 * 
	 * @param ugrade
	 *            当前记录会员等级ugrade
	 * @param ugradeList
	 *            所有审核通过的会员等级
	 * @return
	 */
	private int checkAudit(String ugrade, String ugradeCall)
	{	
		//当前会员等级包含的下标
		String[] arr = StringUtil.getPlaceholderArr(ugrade);
		
			//审核通过的会员等级的下标
			String[] arr1 = StringUtil.getPlaceholderArr(ugradeCall);
			for (String str0 : arr)
			{
				for (String str1 : arr1)
				{
					//如果两者有相同的，返回false
					if (str0.equals(str1))
					{
						return 0;
					}
				}
			}
		
		return -1;
	}
	/**
	 * 验证审核充值方式
	 * @param rechartype  当前需验证的充值方式	
	 * @param rechartypeCall 数据查出的以验证通过的充值方式
	 * @return
	 */
	private int checkAuditRecharType(short rechartype, short rechartypeCall){	
		if (rechartype==rechartypeCall){
			return 0;
		}
		return -1;
	}
	/**
	 * 启用
	 * 
	 * @param request
	 * @param response
	 * @param rechargeRstr
	 * @throws Exception
	 */
	@RequestMapping(value = "/isUse", method = {RequestMethod.POST,
			RequestMethod.GET})
	public void isUseRate(HttpServletRequest request,
			HttpServletResponse response, RechargeRstr rechargeRstr)
			throws Exception
	{
		rechargeRstr.setIsuse(RechargeRstr_Constant.ISUSE_YES);
		rechargeRstrService.updateRechargeRstr(rechargeRstr);
	}

	/**
	 * 停用
	 * 
	 * @param request
	 * @param response
	 * @param rechargeRstr
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancelUse", method = {RequestMethod.POST,
			RequestMethod.GET})
	public void cancelUseRate(HttpServletRequest request,
			HttpServletResponse response, RechargeRstr rechargeRstr)
			throws Exception
	{
		rechargeRstr.setIsuse(RechargeRstr_Constant.ISUSE_NO);
		rechargeRstrService.updateRechargeRstr(rechargeRstr);
	}
}
