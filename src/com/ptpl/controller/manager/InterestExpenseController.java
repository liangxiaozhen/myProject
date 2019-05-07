package com.ptpl.controller.manager;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.LoanTypeObjectQuote;
import com.ptpl.model.UserGrade;
import com.ptpl.service.InterestExpenseServiceI;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;
/**
 * @author:liuqh
 * @date:2016年07月12日 23:13:12
 * @description:标的利息管理费设置
 */
@Controller
@RequestMapping("/admin/interestExpense")
public class InterestExpenseController {
	@Autowired
	private InterestExpenseServiceI interestExpenseService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteServiceI;

//	/**
//	 * @author cjm
//	 * @return
//	 */
//	@RequestMapping("/list")
//	public ModelAndView list(HttpServletRequest request ,HttpServletResponse response){
//		int num = 1;
//		int pageSize = 20;
//		String sort = "id.desc";
//		Order.formString(sort);
//		PageHelper.startPage(num, pageSize);
//		List<InterestExpense> interestExpenses = interestExpenseService.selectByCondition(null);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for(InterestExpense interestExpense:interestExpenses){
//			//防止程序报空指针异常
//			if(interestExpense.getAddtime() != null){
//				interestExpense.setAddtimestr(sdf.format(interestExpense.getAddtime()));
//			}
//
//			if(interestExpense.getUgrade() != null){
//				Map<String,String> hashMap = getUserGrade(interestExpense.getUgrade());
//				String ugradestr    = hashMap.get("ugradestr");
//				String subugradestr = hashMap.get("subugradestr");
//				if(StringUtil.isNotEmpty(ugradestr)){
//					interestExpense.setUgradestr(ugradestr);
//				}
//
//				if(StringUtil.isNotEmpty(subugradestr)){
//					interestExpense.setSubugradestr(subugradestr);
//				}
//			}
//
//			if(interestExpense.getTtype() != null ){
//				Map<String,String> hashMap = getTtype(interestExpense.getTtype());
//				String ttypestr = hashMap.get("ttypestr");
//				String subttypestr = hashMap.get("subttypestr");
//				if(StringUtil.isNotEmpty(ttypestr)){
//					interestExpense.setTtypestr(ttypestr);
//				}
//
//				if(StringUtil.isNotEmpty(subttypestr)){
//					interestExpense.setSubttypestr(subttypestr);
//				}
//			}
//
//		}
//
//		PageInfo<InterestExpense> pagehelper = new PageInfo<InterestExpense>(interestExpenses);
//		pagehelper.setFirstPage(1);
//		int lasePageNum = 0;
//		if(pagehelper.getTotal() % pageSize ==0){
//			lasePageNum = (int)pagehelper.getTotal() / pageSize;
//		}else{
//			lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
//		}
//		pagehelper.setLastPage(lasePageNum);
//
//		ModelAndView modelAndView = new ModelAndView();	
//		modelAndView.setViewName("admin/interestExpense/list");
//		modelAndView.addObject("pagehelper", pagehelper);
//		return modelAndView;
//	}
//
//	@RequestMapping("/template")
//	public ModelAndView template(HttpServletRequest request ,HttpServletResponse response,InterestExpense interestExpense){
//		String pageS = request.getParameter("pageSize");
//		String pageNo = request.getParameter("pageNo");
//		int num = 1;
//		int pageSize = 20;
//		if(StringUtil.isNotEmpty(pageS)){
//			pageSize = Integer.parseInt(pageS);
//		}
//		if(StringUtil.isNotEmpty(pageNo)){
//			num = Integer.parseInt(pageNo);
//		}
//		String sort = "id.desc";
//		Order.formString(sort);
//		PageHelper.startPage(num, pageSize);
//		List<InterestExpense> interestExpenses = interestExpenseService.selectByCondition(interestExpense);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for(InterestExpense interestExpense2:interestExpenses){
//			//防止程序报空指针异常
//			if(interestExpense2.getAddtime() != null){
//				interestExpense2.setAddtimestr(sdf.format(interestExpense2.getAddtime()));
//			}
//
//			if(interestExpense2.getUgrade() != null){
//				Map<String,String> hashMap = getUserGrade(interestExpense2.getUgrade());
//				String ugradestr    = hashMap.get("ugradestr");
//				String subugradestr = hashMap.get("subugradestr");
//				if(StringUtil.isNotEmpty(ugradestr)){
//					interestExpense2.setUgradestr(ugradestr);
//				}
//
//				if(StringUtil.isNotEmpty(subugradestr)){
//					interestExpense2.setSubugradestr(subugradestr);
//				}
//			}
//
//			if(interestExpense2.getTtype() != null ){
//				Map<String,String> hashMap = getTtype(interestExpense2.getTtype());
//				String ttypestr = hashMap.get("ttypestr");
//				String subttypestr = hashMap.get("subttypestr");
//				if(StringUtil.isNotEmpty(ttypestr)){
//					interestExpense2.setTtypestr(ttypestr);
//				}
//
//				if(StringUtil.isNotEmpty(subttypestr)){
//					interestExpense2.setSubttypestr(subttypestr);
//				}
//			}
//		}
//
//		PageInfo<InterestExpense> pagehelper = new PageInfo<InterestExpense>(interestExpenses);
//		pagehelper.setFirstPage(1);
//		int lasePageNum = 0;
//		if(pagehelper.getTotal() % pageSize ==0){
//			lasePageNum = (int)pagehelper.getTotal() / pageSize;
//		}else{
//			lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
//		}
//		pagehelper.setLastPage(lasePageNum);
//
//		ModelAndView modelAndView = new ModelAndView();	
//		modelAndView.setViewName("admin/interestExpense/listTemplate");
//		modelAndView.addObject("pagehelper", pagehelper);
//		return modelAndView;
//	}
//
//	/**
//	 * 详情信息
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping("/detail")
//	public ModelAndView detail(HttpServletRequest request ,HttpServletResponse response){
//		String opid = request.getParameter("opid");
//		if(StringUtil.isNotEmpty(opid)){
//			InterestExpense interestExpense = interestExpenseService.selectInterestExpenseById(new BigDecimal(opid));
//			if(interestExpense != null){
//				if(interestExpense.getAddtime() != null){
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					interestExpense.setAddtimestr(sdf.format(interestExpense.getAddtime()));
//				}
//
//				if(interestExpense.getUgrade() != null){
//					Map<String,String> hashMap = getUserGrade(interestExpense.getUgrade());
//					String ugradestr    = hashMap.get("ugradestr");
//					if(StringUtil.isNotEmpty(ugradestr)){
//						interestExpense.setUgradestr(ugradestr);
//					}
//
//				}
//
//				if(interestExpense.getTtype() != null ){
//					Map<String,String> hashMap = getTtype(interestExpense.getTtype());
//					String ttypestr = hashMap.get("ttypestr");
//					if(StringUtil.isNotEmpty(ttypestr)){
//						interestExpense.setTtypestr(ttypestr);
//					}
//				}
//
//				ModelAndView andView = new ModelAndView();
//				andView.addObject("interestExpense", interestExpense);
//				andView.setViewName("admin/interestExpense/detailTemplate");
//				return andView;
//			}
//		}
//
//		return null;
//	}
//
//	/**
//	 * 删除
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping("/delete")
//	public Map<String,String> delete(HttpServletRequest request ,HttpServletResponse response){
//		String opid = request.getParameter("opid");
//		Map<String,String> hashMap = new HashMap<>();
//		hashMap.put("result", "fail");
//		if(StringUtil.isEmpty(opid)){
//			hashMap.put("msg", "参数opid 不能为空");
//			return hashMap;
//		}
//
//		int count = 0;
//		count = interestExpenseService.delete(new BigDecimal(opid));
//		if(count > 0){
//			hashMap.put("result", "success");
//			hashMap.put("msg", "删除成功！");
//		}else{
//			hashMap.put("result", "fail");
//			hashMap.put("msg", "因网络响应不及时！删除失败！");
//		}
//		return hashMap;
//	}

	
	
	/**
	 * 修改保存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	public String save(HttpServletRequest request ,HttpServletResponse response){
		String params = request.getParameter("params");
		String isaudit = request.getParameter("isaudit");
		String remark = request.getParameter("remark");
		
		Map<String,String> hashMap = new HashMap<>();
		if(StringUtil.isEmpty(params)){
			hashMap.put("result", "fail");
			hashMap.put("Msg", "参数params 找不到");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
		
 		if(StringUtil.isEmpty(remark)){
 			if(remark.length() > 80){
  				hashMap.put("result", "fail");
 				hashMap.put("Msg", "备注信息字数超出限制！不能超出80字符！");
 				String str = JSON.toJSONString(hashMap);
 				try {
 					StringUtil.sendJsonData(response, str);
 				} catch (IOException e) {
 					e.printStackTrace();
 				}
 				return null;
 			}
		}
 		
 		if(StringUtil.isEmpty(isaudit)){
			hashMap.put("result", "fail");
			hashMap.put("Msg", "参数isaudit找不到");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
		}
 		
 		if(!isaudit.equals("1")){//是否审核
 			isaudit = "0";
 		}
		
   		JSONObject json = JSONObject.fromObject(params);
   		if(!(json.size() > 0)){
   			hashMap.put("result", "fail");
			hashMap.put("Msg", "因网络响应不及时！保存失败！请重新操作！");
			String str = JSON.toJSONString(hashMap);
			try {
				StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
			return null;
   		}
    		
   		List<InterestExpense> interestExpenses = interestExpenseService.selectByCondition(null);
 		if(interestExpenses.size() > 0){//修改
 			for(InterestExpense expense : interestExpenses){
  				interestExpenseService.delete(expense.getId());//删除全部
 			}
 		}
 		
 		for(int i = 0; i < json.size() ;i++){
			String str = json.getString(String.valueOf(i));
			JSONObject json2 = JSONObject.fromObject(str);
			String gfitype   = json2.getString("gfitype");
			String ttype     = json2.getString("ttype");
			String iepercent = json2.getString("iepercent");
			String ugrade    = json2.getString("ugrade");
			String maxiefee  = json2.getString("maxiefee");
			String ttypeStr = StringUtil.getPlaceholder(30);
			if(ttype.equals("10000")){//全部标类型
				for(int i2 = 0 ; i2 < 30 ; i2++){
					ttypeStr = StringUtil.setPlaceholder(ttypeStr, i2);
				}
			}else{
 				String[] ttypeStrs = ttype.split(",");
				for(String string : ttypeStrs){
					ttypeStr = StringUtil.setPlaceholder(ttypeStr, Integer.parseInt(string) - 1);
				}
			}
			
			String ugradeStr = StringUtil.getPlaceholder(30);
			if(ugrade.equals("10000")){//全部会员等级
				for(int i2 = 0 ; i2 < 51 ; i2++){
					ugradeStr = StringUtil.setPlaceholder(ugradeStr, i2);
				}
			}else{
 				String[] ugradeStrs = ugrade.split(",");
				for(String string : ugradeStrs){
					ugradeStr = StringUtil.setPlaceholder(ugradeStr, Integer.parseInt(string));//因为初始会员是 0 所以不进行减1操作
				}
			}
			InterestExpense expense = new InterestExpense();
			expense.setGfitype(new Short(gfitype));  //计算方式（1根据用户等级，2根据标的风险等级
			expense.setTtype(ttypeStr);  //标的类型（1000000…） 30位
			expense.setUgrade(ugradeStr);  //会员等级
			expense.setIepercent(Double.valueOf(iepercent) / 100);  //利息管理费百份比
			expense.setMaxiefee(Double.valueOf(maxiefee));  //该段最高利息管理收费金额
			expense.setIsaudit(new Short(isaudit));  //资金清算是否需要审
 			expense.setAddtime(new Date()); //添加时间
 			if(StringUtil.isNotEmpty(remark)){
  				expense.setRemark(remark);  //备注
 			}
  			interestExpenseService.insertSelective(expense);
 		}
 		
 		hashMap.put("result", "success");
		hashMap.put("Msg", "保存成功");
		String str = JSON.toJSONString(hashMap);
		try {
			StringUtil.sendJsonData(response, str);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 设置/修改
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request ,HttpServletResponse response){
		ModelAndView andView = new ModelAndView();
		/**查出全部的会员等级和标类型**/
		List<UserGrade> allUserGrades = userGradeService.selectiveForNormal(null);
		List<LoanTypeObjectQuote> allLoanTypeObjectQuotes = loanTypeObjectQuoteServiceI.gettypeObjectQuotes(null);

		List<InterestExpense> interestExpenses = interestExpenseService.selectByCondition(null);
		andView.setViewName("admin/interestExpense/edit");
		if(interestExpenses.size() > 0){//修改
			for(InterestExpense interestExpense : interestExpenses){
				//会员等级
				List<UserGrade> userGrades = new ArrayList<>();
				if(interestExpense.getUgrade() != null){
					List<Integer> pa1 = StringUtil.pars(interestExpense.getUgrade());
					if(pa1.size() > 0){
						for(Integer integer : pa1){
							UserGrade userGrade = new UserGrade();
							userGrade.setUgrade(new BigDecimal(integer));
							List<UserGrade> userGrades2 = userGradeService.selectiveForNormal(userGrade);
							if(userGrades2.size() > 0){
  								userGrades.add(userGrades2.get(0));
 							}
						}
					}
				}

				if(userGrades.size() > 0){
					interestExpense.setUsergrades(userGrades);
					if(userGrades.size() == allUserGrades.size() ){//全部会员等级
						interestExpense.setIsusergrades((short)1);
					}
				}
  
				//标类型
				List<LoanTypeObjectQuote> loanTypeObjectQuotes = new ArrayList<>();
				if(interestExpense.getTtype() != null){
					List<Integer> pa1 = StringUtil.parsStringToList(interestExpense.getTtype());
					if(pa1.size() > 0){
						for(Integer integer : pa1){
							LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteServiceI.selectBySerialNo(new BigDecimal(integer));
							if(loanTypeObjectQuote != null){
								loanTypeObjectQuotes.add(loanTypeObjectQuote);
							}
						}
					}
				}

				if(loanTypeObjectQuotes.size() > 0){
					interestExpense.setLoantypeobjectquotes(loanTypeObjectQuotes);
					if(loanTypeObjectQuotes.size() == allLoanTypeObjectQuotes.size()){//全部标类型
						interestExpense.setIsloantypeobjectquotes((short)1);
					}
				}
			}
			andView.addObject("allUserGrades", allUserGrades);//全部会员等级
			andView.addObject("allLoanTypeObjectQuotes", allLoanTypeObjectQuotes);//全部标类型
			andView.addObject("interestExpenses", interestExpenses);//全部利息管理费设置
			andView.addObject("interestExpense", interestExpenses.get(0));//单个利息管理费设置
 			andView.setViewName("admin/interestExpense/update");
			return andView;
		}
 
		//新增页面
		andView.addObject("allUserGrades", allUserGrades);//全部会员等级
		andView.addObject("allLoanTypeObjectQuotes", allLoanTypeObjectQuotes);//全部标类型
		andView.setViewName("admin/interestExpense/edit");
 		return andView;

	}

	/**
	 * 返回标类型名称
	 * @param ttype
	 * @return
	 */
	public Map<String,String> getTtype(String ttype){
		Map<String,String> hashMap = new HashMap<>();
		List<Integer> pa1 = StringUtil.pars(ttype);
		String ttypestr = "";
		String subttypestr = "";
		if(pa1.size() > 0){
			int i = 0;
			for(Integer integer : pa1){
				LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteServiceI.selectBySerialNo(new BigDecimal(integer));
				if(loanTypeObjectQuote != null){
					i++;
					if(i <= 2){
						subttypestr += loanTypeObjectQuote.getObjectname() + ",";
						ttypestr += loanTypeObjectQuote.getObjectname() + ",";
					}else{
						ttypestr += loanTypeObjectQuote.getObjectname() + ",";
					}
				}
			}

			if(StringUtil.isNotEmpty(ttypestr)){
				ttypestr 	= ttypestr.substring(0,ttypestr.lastIndexOf(","));
			}

			if(StringUtil.isNotEmpty(subttypestr)){
				subttypestr = subttypestr.substring(0,subttypestr.lastIndexOf(","));
				if(i > 2){
					subttypestr += ".....";
				}
			}

		}
		hashMap.put("ttypestr", ttypestr);
		hashMap.put("subttypestr", subttypestr);
		return hashMap;
	}

	/**
	 * 返回 会员等级名称
	 * @param ugrade
	 * @return
	 */
	public Map<String,String> getUserGrade(String ugrade){
		Map<String,String> hashMap = new HashMap<>();
		List<Integer> pa1 = StringUtil.pars(ugrade);
		String ugradestr = "";
		String subugradestr = "";
		if(pa1.size() > 0){
			int i = 0;
			for(Integer integer : pa1){
				UserGrade userGrade = new UserGrade();
				userGrade.setUgrade(new BigDecimal(integer));
				List<UserGrade> userGrades = userGradeService.selective(userGrade);
				if(userGrades.size() > 0){
					UserGrade userGrade2 = userGrades.get(0);
					i++;
					if(i <= 2){
						subugradestr += userGrade2.getUgradename() + ",";
						ugradestr += userGrade2.getUgradename() + ",";
					}else{
						ugradestr += userGrade2.getUgradename() + ",";
					}
				}
			}

			if(StringUtil.isNotEmpty(subugradestr)){
				subugradestr = subugradestr.substring(0,subugradestr.lastIndexOf(","));
				if(i > 2){
					subugradestr += ".....";
				}
			}

			if(StringUtil.isNotEmpty(ugradestr)){
				ugradestr = ugradestr.substring(0,ugradestr.lastIndexOf(","));
			}
		}
		hashMap.put("ugradestr", ugradestr);
		hashMap.put("subugradestr", subugradestr);
		return hashMap;
	}

	/**
	 * 20170624 需求变更 标的利息管理费设置独立，不在和标关联  cjm  源代码注释
	 */
	//	// 转发到增加利息管理费设置页面
	//	@RequestMapping(value = "/insert_InterestExpense_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	//	public ModelAndView insert_InterestExpense_Ui(@PathVariable String tid,HttpServletRequest request) throws Exception{
	//		ModelAndView modelAndView = new ModelAndView();
	//		List<UserGrade> uGrades = userGradeService.getAll(null);
	//		modelAndView.setViewName("admin/interestExpense/InterestExpense_Inst");
	//		List<String> gradeList=null;
	//		if(tid!=null&&!tid.isEmpty()){
	//			gradeList=interestExpenseService.selectGradebyTid(new BigDecimal(tid)); //查找利息管理表中已设置的会员等级
	//		}
	//		modelAndView=  UserGradeUtil.mv(uGrades,gradeList,modelAndView,tid);
	//		if(uGrades.size()<=0){
	//			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
	//			tenderItem.setIsaintexp((short)1);
	//			tenderItemService.update(tenderItem);
	//			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
	//		}
	//		return modelAndView;
	//	}
	//	
	//	//通过会员等级来去重获取剩下的会员
	//	@RequestMapping(value = "/selectinterBytid", method = { RequestMethod.POST, RequestMethod.GET })
	//    public ModelAndView selectinteresBytid(BigDecimal tid) throws Exception{
	//		ModelAndView mv=new ModelAndView();
	//		InterestExpense condition=new InterestExpense();
	//		condition.setTid(tid);
	//	    List<InterestExpense> list=interestExpenseService.selectByConditionAndDecorateUgrade(condition);//通过tid获取对应的数据
	//		List<UserGrade> grades=userGradeService.getAll(null);//获取哦所有的会员等级
	//		String gfund="";//定义一个字符串来储存
	//		for (int i = 0; i < list.size(); i++) {
	//			gfund+=list.get(i).getUgrade();
	//		}
	//		System.out.println(gfund);
	//		//首先截取以空格为分隔符的字符串gfund，保存在list--arrays里,然后去重,直接将字符串转换成list集合 使用Arrays.asList()方法
	//		List<String> arrays = singleElement(new ArrayList<String>(Arrays.asList(gfund.split(" "))));
	//		//String[] arr = (String[])arrays.toArray(new String[grades.size()]);//将list转换成数组
	//		//通过双重for循环对比找出等级相同的的编号存储在list中
	//		List<BigDecimal> listtwo =new ArrayList<>();
	//		for (int i = 0; i < grades.size(); i++) {
	//			for (int j = 0; j < arrays.size(); j++) {
	//				if(grades.get(i).getUgradename().equals(arrays.get(j))){
	//					listtwo.add(grades.get(i).getUgrade());
	//				}
	//			}
	//		}
	//		List<UserGrade> userGrades=userGradeService.getGradeList(listtwo);//将循环比较得到的list编号在数据库筛选出去
	//		mv.addObject("userGrades", userGrades);
	//		mv.addObject("insertInterpense", list.get(0));
	//		mv.setViewName("admin/interestExpense/InterestExpense_Inst");
	//		return mv;
	//	}
	//	
	//	@RequestMapping(value = "/insertInterestExpensetwo", method = { RequestMethod.POST, RequestMethod.GET })
	//    public ModelAndView insertInterestExpense(InterestExpense interestExpense,String[] ugrades,HttpServletRequest request,HttpServletResponse response) throws Exception{
	//		ModelAndView mv=new ModelAndView();
	//		List<UserGrade> userGrades=userGradeService.getAll(null);// 获取全部等级
	//		String isAll=interestExpense.getUgrade();////全部等级或者部分等级，存放1或者2
	//		String gradeStr=UserGradeUtil.changeString(isAll,ugrades,userGrades);//将所有等级转化为字符串
	//		interestExpense.setUgrade(gradeStr);
	//		interestExpense.setIntexpno(StringUtil.getNoForTenderItem(Marknumber.EXPENSE_NO));//生成编号
	//		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
	//		if(adminUser!=null){
	//			interestExpense.setAddman(adminUser.getUsername());//添加人
	//		}
	//		interestExpense.setAddtime(new Date());//添加时间
	//		if(interestExpense.getIepercent()!=null){
	//		   interestExpense.setIepercent(interestExpense.getIepercent()/100);//利息管理费百分比
	//		}
	//		interestExpenseService.insert(interestExpense);//添加
	//		List<String> gradeList=interestExpenseService.selectGradebyTid(interestExpense.getTid());//该标已经设置的等级记录
	//		int count=UserGradeUtil.count(gradeList);//统计所有记录已设置的等级个数
	//		if(count<userGrades.size()){
	//			mv=new ModelAndView("redirect:/admin/interestExpense/insert_InterestExpense_Ui/"+interestExpense.getTid()+".action");
	//			return  mv;
	//		}//如果count小于所有等级个数，说明没有设置完，则重定向到转发页面的请求Url
	//
	//		TenderItem tenderItem=tenderItemService.findTenderItemById(interestExpense.getTid());
	//		tenderItem.setIsaintexp((short)1);
	//		tenderItemService.update(tenderItem);
	//		 mv=new ModelAndView("redirect:/admin/tenderItem/tenderItem_tag_UI/"+interestExpense.getTid()+".action");
	//		return mv;
	//	}
	//	// 增加利息管理费设置
	//	@RequestMapping(value = "/insertInterestExpense", method = { RequestMethod.POST, RequestMethod.GET })
	//	public ModelAndView insertInterestExpense(InterestExpense interestExpense,String[] ugrades,String ugrade,HttpServletRequest request,HttpServletResponse response) throws IOException {
	//		//判断ugrade是否为1，如果为1，则为全部等级
	//				if("1".equals(ugrade)){
	//					List<UserGrade> all = userGradeService.getAll(null);
	//					int size = all.size();
	//					String ugrade1 = StringUtil.intToString(size);
	//					interestExpense.setUgrade(ugrade1);
	//				}else if("2".equals(ugrade)){
	//						String ugrade2 = StringUtil.stringArrayToString(ugrades);
	//						interestExpense.setUgrade(ugrade2);
	//				}
	//				//判断ugrade是否为2，如果为2，则为选择了部分，则要获取ugrades数组，并把它变为“001101”的字符串的形式
	//				
	//			System.out.println(interestExpense);
	//			String intexpno1=StringUtil.getNameNoForName("GJLX");
	//			interestExpense.setIntexpno(intexpno1);
	//			interestExpenseService.insert(interestExpense);
	//			//得到利息管理费设置的编号并set进标的利息管理费设置编号字段
	//			BigDecimal tenderItemId = (BigDecimal) request.getSession().getAttribute("tenderItemId");
	//			String intexpno = interestExpense.getIntexpno();
	//			if(tenderItemId!=null){
	//				TenderItem tenderItem = PublicUtil.getTenderItem(tenderItemId,tenderItemService);
	//				tenderItem.setIntexpno(intexpno);
	//				ArrayList<String> urlList = (ArrayList<String>) request.getSession().getAttribute("urlList");
	//				Short isaudit = (Short) request.getSession().getAttribute("isaudit");
	//				//如果urlList.size的长度为0，则表示，此刻保存的是最后一个标相关的设置，则把标状态改为审核中
	//				if (urlList == null) {
	//					return PublicUtil.setLastItem(request, response, tenderItem, isaudit,tenderItemService);
	//				}
	//				//设置标的相应字段
	//				tenderItemService.update(tenderItem);
	//			}
	//		return  PublicUtil.changeUrlForTenderItem(request);
	//	}
	//	
	//	// 根据条件查找利息管理费设置并转发到列表页面
	//	@RequestMapping(value = "/selectInterestExpenseByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	//	public ModelAndView selectInterestExpenseByCondition(HttpServletRequest request,InterestExpense interestExpense) throws Exception {
	//
	//		// 处理分页请求
	//		String pageNum = request.getParameter("pageNum");
	//		String pageSize = request.getParameter("pageSize");
	//		int num = 1;
	//		int size = 9;
	//		if (pageNum != null && !"".equals(pageNum)) {
	//			num = Integer.parseInt(pageNum);
	//		}
	//		if (pageSize != null && !"".equals(pageSize)) {
	//			size = Integer.parseInt(pageSize);
	//		}
	//		String sortString = "id.desc";
	//		Order.formString(sortString);
	//
	//		PageHelper.startPage(num, size);
	//		// 调用service层的方法得到对象列表
	//		List<InterestExpense> InterestExpenseList = interestExpenseService.selectByConditionAndDecorateUgrade(interestExpense);
	//		PageInfo<InterestExpense> pagehelper = new PageInfo<InterestExpense>(InterestExpenseList);
	//		pagehelper.setFirstPage(1);
	//
	//		int lastPageNum = 0;
	//		if (pagehelper.getTotal() % size == 0) {
	//			lastPageNum = (int) pagehelper.getTotal() / size;
	//		} else {
	//			lastPageNum = (int) pagehelper.getTotal() / size + 1;
	//		}
	//		pagehelper.setLastPage(lastPageNum);
	//		// 把对象放进modelAndView中
	//		ModelAndView modelAndView = new ModelAndView();
	//		modelAndView.addObject("pagehelper", pagehelper);
	//		modelAndView.addObject("interestExpense", interestExpense);
	//		// 指定视图
	//		modelAndView.setViewName("admin/interestExpense/InterestExpense_List");
	//		PublicUtil.removeFormSession(request);
	//		return modelAndView;
	//	}
	//
	//	// 根据tid查找利息管理费设置并返回到详细页面(详情)
	//	@RequestMapping(value = "/selectInterestExpenseByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	//	public ModelAndView selectInterestExpenseByPrimaryKey(BigDecimal tid) throws Exception {
	//		List<InterestExpense> interestExpenses=new ArrayList<>();
	//		if(tid!=null){
	//			InterestExpense expense=new InterestExpense();
	//			expense.setTid(tid);
	//			interestExpenses = interestExpenseService.selectByConditionAndDecorateUgrade(expense);
	//		}
	//		ModelAndView modelAndView = new ModelAndView();
	//		modelAndView.addObject("interestExpense", interestExpenses.get(0));
	//		modelAndView.addObject("interestExpenses", interestExpenses);
	//		modelAndView.setViewName("admin/interestExpense/InterestExpense_Detail");
	//		return modelAndView;
	//	}
	//	
	//	// 转发到修改利息管理费设置页面(修改)
	//	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	//	public ModelAndView toUpdateUi(BigDecimal tid) throws Exception {
	//		ModelAndView mv = new ModelAndView();
	//		InterestExpense interestExpense = new InterestExpense();
	//		interestExpense.setTid(tid);
	//		List<InterestExpense> interestExpenses = interestExpenseService.selectByConditionAndDecorateUgrade(interestExpense);
	//		if(interestExpenses.size()>0){
	//			 mv.addObject("interestExpenses",interestExpenses);
	//			 mv.addObject("interestExpense",interestExpenses.get(0));
	//		} 
	//		mv.setViewName("admin/interestExpense/InterestExpense_Update");
	//		return mv;
	//		/*String ugrade = interestExpense1.getUgrade();
	//		List<Integer> integer=StringUtil.pars(ugrade);
	//		for (int i = 0; i < integer.size(); i++) {
	//			System.out.println(integer.get(i));
	//		} 
	//	 	PublicUtil.decorateGrade(modelAndView,userGradeService,ugrade,"ugrade","ugrades1");
	//		List<UserGrade> uGrades = userGradeService.getAll(null);
	//		modelAndView.addObject("uGrades",uGrades);
	//		modelAndView.addObject("interestExpense", interestExpense1);
	//		modelAndView.setViewName("admin/interestExpense/InterestExpense_Update");
	//		return modelAndView;*/
	//	}
	//
	//	// 修改
	//	@RequestMapping(value = "/updateInterestExpense", method = { RequestMethod.POST, RequestMethod.GET })
	//	public String updateInterestExpense(InterestExpense interestExpense,HttpServletRequest req) {
	//		AdminUser adminUser=(AdminUser) req.getSession().getAttribute(Session_Constant.ADMINUSER);
	//		if(adminUser!=null){
	//			interestExpense.setAddman(adminUser.getUsername());//修改人
	//		}
	//		interestExpense.setAddtime(new Date());//修改时间
	//		List<InterestExpense> expenses=interestExpense.getExpenses();//获取数据
	//		for (int i = 0; i < expenses.size(); i++) {
	//			interestExpense.setIepercent(expenses.get(i).getIepercent()/100);//利息管理费百分比
	//			interestExpense.setMiniefee(expenses.get(i).getMiniefee());//最低收费
	//			interestExpense.setMaxiefee(expenses.get(i).getMaxiefee());//最低收费
	//			interestExpenseService.update(interestExpense);
	//		}
	//		return "redirect:/admin/interestExpense/selectInterestExpenseByCondition.action";
	//	}
	//	//删除
	//	@RequestMapping(value = "/deleteInterestExpense", method = { RequestMethod.POST, RequestMethod.GET })
	//	public void deleteRiskGuarantyMoney(BigDecimal id,BigDecimal tid,HttpServletResponse response) throws IOException{
	//		PublicUtil.decideBeforeDelete(id,tid, response, tenderItemService,interestExpenseService);
	//	}
	//
	//	    //list去重
	//		public  ArrayList singleElement(ArrayList al)  
	//	    {  
	//	        ArrayList newAl = new ArrayList();  
	//	  
	//	        for(Iterator it = al.iterator(); it.hasNext();)  
	//	        {  
	//	            Object obj = it.next();
	//	            if(!obj.equals(" ")){
	//	            	 if(!newAl.contains(obj))  
	//	 	            {  
	//	 	                newAl.add(obj);  
	//	 	            }  
	//	            }
	//	        }  
	//	        return newAl;  
	//	    }  
}
