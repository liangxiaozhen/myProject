package com.ptpl.controller.manager;
 

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.Award;
import com.ptpl.model.RemoveName;
import com.ptpl.model.UserGrade;
import com.ptpl.model.WCCAwardRule;
import com.ptpl.model.WithdrawsCashCoupon;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.RemoveNameServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.service.WCCAwardRuleServiceI;
import com.ptpl.service.WithdrawsCashCouponServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
* @ClassName: WithdrawsCashCouponController 
* @Package com.ptpl.controller 
* @Description: TODO(提抵卷活动规则 控制层 ) 
* @author chenjiaming
* @date 2016年07月07日 16:15:49
* @version V1.0
 */
@Controller
@RequestMapping("/admin/withdrawsCashCoupon")
public class WithdrawsCashCouponController extends BaseController{
	/**
	 * 提抵卷活动规则Service
	 */
	 @Autowired
	 private WithdrawsCashCouponServiceI withdrawsCashCouponService;
	 /**
	  * 排除名单Service
	  */
	 @Autowired
	 private RemoveNameServiceI removeNameServiceI;
	 /**
	  * 奖品Service
	  */
	 @Autowired
	 private AwardServiceI awardService;
	 /**
	  *会员等级Service
	  */
 	 @Autowired
	 private UserGradeServiceI userGradeServiceI; 
 	 /**
	  *提低卷奖励规则设置Service
	  */
 	 @Autowired
	 private WCCAwardRuleServiceI wCCAwardRuleServiceI; 
	 /**
	  * 
	 * @Title: list 
	 * @Description: TODO(提抵卷活动规则查询通用方法) 
	 * @param @param withdrawsCashCoupon
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/list")
 	 public ModelAndView list(WithdrawsCashCoupon withdrawsCashCoupon){
  		 int num = 1;
		 int pageSize = 20;
		  
		 //根据Id排序
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 //查询全部用户方法
       	 List<WithdrawsCashCoupon> withdrawsCashCoupons = withdrawsCashCouponService.findWithdrawsCashCoupons(withdrawsCashCoupon);
       	 for(WithdrawsCashCoupon withdrawsCashCoupon2: withdrawsCashCoupons){
       		 if(withdrawsCashCoupon2.getActbtime() !=null){
       			 /*返回前台活动生效日期时间字符串*/
       			 withdrawsCashCoupon2.setActbtimestr(sf.format(withdrawsCashCoupon2.getActbtime()));
        	}
       		 if(withdrawsCashCoupon2.getActetime() !=null){
        		/*返回前台活动截止日期时间字符串*/
       			 withdrawsCashCoupon2.setActetimestr(sf.format(withdrawsCashCoupon2.getActetime()));
       		 }
       		 if(withdrawsCashCoupon2.getAddtime() !=null){
       			 /*返回前台制表时间字符串*/
       			 withdrawsCashCoupon2.setAddtimestr(sf.format(withdrawsCashCoupon2.getAddtime()));
        		 }
        	 }  
        PageInfo<WithdrawsCashCoupon> pagehelper = new PageInfo<WithdrawsCashCoupon>(withdrawsCashCoupons);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
         ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/withdrawsCashCoupon/list");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	/**
	 * 
	* @Title: template 
	* @Description: TODO(提抵卷活动规则模板方法 ,下一页，根据用户名模糊查询通通进这里) 
	* @param @param request
	* @param @param response
	* @param @param withdrawsCashCoupon
	* @param @return  参数说明 
	* @return ModelAndView    返回类型 
	* @author chenjiaming
	* @throws
	 */
	 @RequestMapping(value="/template",method={RequestMethod.POST,RequestMethod.GET})
 	 public ModelAndView template(HttpServletRequest request,HttpServletResponse response,WithdrawsCashCoupon withdrawsCashCoupon){
		 String pageS = request.getParameter("pageSize");
		 String pageNo = request.getParameter("pageNo");
    	 int num = 1;
		 int pageSize = 20;
		 if(StringUtil.isNotEmpty(pageS)){
			 pageSize = Integer.parseInt(pageS);
		 }
		 if(StringUtil.isNotEmpty(pageNo)){
			 num = Integer.parseInt(pageNo);
		 }
		 //根据Id排序
		 String sort = "id.desc";
		 Order.formString(sort);
		 PageHelper.startPage(num, pageSize);
		 //查询全部用户方法
       	 List<WithdrawsCashCoupon> withdrawsCashCoupons = withdrawsCashCouponService.findWithdrawsCashCoupons(withdrawsCashCoupon);
       	 for(WithdrawsCashCoupon withdrawsCashCoupon2: withdrawsCashCoupons){
       		 if(withdrawsCashCoupon2.getActbtime() !=null){
       			 /*返回前台活动生效日期时间字符串*/
       			 withdrawsCashCoupon2.setActbtimestr(sf.format(withdrawsCashCoupon2.getActbtime()));
        	}
       		 if(withdrawsCashCoupon2.getActetime() !=null){
        		/*返回前台活动截止日期时间字符串*/
       			 withdrawsCashCoupon2.setActetimestr(sf.format(withdrawsCashCoupon2.getActetime()));
       		 }
       		 if(withdrawsCashCoupon2.getAddtime() !=null){
       			 /*返回前台制表时间字符串*/
       			 withdrawsCashCoupon2.setAddtimestr(sf.format(withdrawsCashCoupon2.getAddtime()));
        		 }
        	 }
       	 PageInfo<WithdrawsCashCoupon> pagehelper = new PageInfo<WithdrawsCashCoupon>(withdrawsCashCoupons);
      	 pagehelper.setFirstPage(1);
      	 int lasePageNum = 0;
      	 if(pagehelper.getTotal() % pageSize ==0){
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize;
      	 }else{
      		 lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
      	 }
      	 pagehelper.setLastPage(lasePageNum);
      	 
      	 ModelAndView modelAndView = new ModelAndView();	
      	 modelAndView.setViewName("admin/withdrawsCashCoupon/listTemplate");
      	 modelAndView.addObject("pagehelper", pagehelper);
 		 return modelAndView;
 	 }
	 
	 /**
	  * 
	 * @Title: update 
	 * @Description: TODO(提抵卷活动规则更新) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value = "/update",method = RequestMethod.POST)
	 public String update(WithdrawsCashCoupon withdrawsCashCoupon,HttpServletResponse response){
   		 if(withdrawsCashCoupon !=null && withdrawsCashCoupon.getId() != null){
			 try {
				//提低卷活动规则设置 会员等级处理 全部会员等级还是
				 String ugrade = withdrawsCashCoupon.getUgrade();
				 String removenameno = withdrawsCashCoupon.getRemovenameno();
	 			 //选择全部会员等级
				 if(ugrade.equals("1")){
					 //生成长度未30位0的字符串0000000000000000000000000000000000
					 ugrade = StringUtil.getPlaceholder(30);
	  				 //查询全部会员等级
					 List<UserGrade> userGrades = userGradeServiceI.getAll(null);
					 for(UserGrade userGrade2: userGrades){
						 ugrade = 	StringUtil.setPlaceholder(ugrade, userGrade2.getUgrade().intValue()-1);	
					 }
				 }else if(ugrade.equals("2")){
					 //获取选中的部分会员等级
					 String[] ugrades = withdrawsCashCoupon.getUgrades();
					 //选择的部分会员等级
					 ugrade = StringUtil.setPlaceholderForArr(ugrades, 30);
				 }
				 //选择全部排除名单
				 if(removenameno.equals("1")){
					 //分组查询全部排除名单
			  		 List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
			  		removenameno = "";
					for(RemoveName removeName: removeNames){
	 					removenameno += removeName.getNameno()+",";
					}
				 }else if(removenameno.equals("2")){
					 //选择的排除名单编号
					 String[] removenamenos = withdrawsCashCoupon.getRemovenamenos();
					 removenameno = "";
					 for(String removenameno2 : removenamenos){
 	 					 removenameno +=removenameno2+",";
					 }
	 			 }
	   			 //重新赋值会员等级编号111010111011100000001000000000
				 withdrawsCashCoupon.setUgrade(ugrade);
				 //重新赋值排除名单表编号
				 withdrawsCashCoupon.setRemovenameno(removenameno);
				 int count = 0;
				 count = withdrawsCashCouponService.updateByPrimaryKeySelective(withdrawsCashCoupon);
				 Map<String,String> map = new HashMap<String,String>();
				 if(count >0){
					 map.put("result", "update_success");
				 }else{
					 map.put("result", "update_fail");
				 }
				 String str = JSON.toJSONString(map);
				 StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
  				e.printStackTrace();
			}
		 }
 		 return null;
	 }
	  
	 /**
	  * 活动启用 停用设置方法
	  * @param withdrawsCashCoupon
	  * @param response
	  * @return
	  */
	 @RequestMapping(value="/status",method=RequestMethod.POST)
	public String status(WithdrawsCashCoupon withdrawsCashCoupon,HttpServletResponse response){
		 if(withdrawsCashCoupon !=null && withdrawsCashCoupon.getId() !=null){
			 try {
  				 int count = 0;
				 count = withdrawsCashCouponService.updateByPrimaryKeySelective(withdrawsCashCoupon);
				 Map<String,String> map = new HashMap<String,String>();
				 if(count >0){
					 map.put("result", "success");
				 }else{
					 map.put("result", "fail");
				 }
				 String str = JSON.toJSONString(map);
				 StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
  				e.printStackTrace();
			}
			 return null;
		 }else {
			 return null;
		 }
	 }
	 /**
	  * 
	 * @Title: save 
	 * @Description: TODO(保存 提低卷活动规则设置和提低卷奖励规则设置) 
	 * @param @param withdrawsCashCoupon
	 * @param @param wCCAwardRule
	 * @param @param response
	 * @param @param request
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/save",method={RequestMethod.GET,RequestMethod.POST})
	 public String save(WithdrawsCashCoupon withdrawsCashCoupon,WCCAwardRule wCCAwardRule,HttpServletResponse response,HttpServletRequest request){
		 if(withdrawsCashCoupon != null && wCCAwardRule !=null){
			 HashMap<String,String> maps = new HashMap<String,String>();
			 //提低卷活动规则设置 会员等级处理 全部会员等级还是
			 String ugrade = withdrawsCashCoupon.getUgrade();
			 String removenameno = withdrawsCashCoupon.getRemovenameno();
 			 //选择全部会员等级
			 if(ugrade.equals("1")){
				 //生成长度未30位0的字符串0000000000000000000000000000000000
				 ugrade = StringUtil.getPlaceholder(30);
  				 //查询全部会员等级
				 List<UserGrade> userGrades = userGradeServiceI.getAll(null);
				 for(UserGrade userGrade2: userGrades){
					 ugrade = 	StringUtil.setPlaceholder(ugrade, userGrade2.getUgrade().intValue()-1);	
				 }
			 }else if(ugrade.equals("2")){
				 //获取选中的部分会员等级
				 String[] ugrades = withdrawsCashCoupon.getUgrades();
				 //选择的部分会员等级
				 ugrade = StringUtil.setPlaceholderForArr(ugrades, 30);
			 }
			 //选择全部排除名单
			 if(removenameno.equals("1")){
				 //分组查询全部排除名单
		  		 List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
		  		 //清空 1
		  		removenameno ="";
				for(RemoveName removeName: removeNames){
 					removenameno += removeName.getNameno()+",";
				}
			 }else if(removenameno.equals("2")){
				 //选择的排除名单编号
				 String[] removenamenos = withdrawsCashCoupon.getRemovenamenos();
				//清空 2
			  	removenameno ="";
				 for(String removenameno2 : removenamenos){
 					 removenameno +=removenameno2+",";
				 }
 			 }
   			 //重新赋值会员等级编号111010111011100000001000000000
			 withdrawsCashCoupon.setUgrade(ugrade);
			 //重新赋值排除名单表编号
			 withdrawsCashCoupon.setRemovenameno(removenameno);
			 //生成制表时间
			 withdrawsCashCoupon.setAddtime(new Date());
			 //设置活动状态是否可以启用1可以 0不可以启用
			 withdrawsCashCoupon.setStatus(0);
			 //设置是否需要审核 默认需要审核1已经审核  0未审核
			 withdrawsCashCoupon.setIsauditalist(0);
			 //保存提低券活动规则设置
			 try {
				 int count = 0;
				 //保存提低卷活动规则设置
				 count =withdrawsCashCouponService.insertSelective(withdrawsCashCoupon);
				 if(count > 0){
 					 if(withdrawsCashCoupon.getId() !=null){
 						 //提抵卷奖励规则制表人名字
 						 String addman_addman = request.getParameter("addman_addman");
 						 //提抵卷奖励规则备注
 						 String remark_remark = request.getParameter("remark_remark");
 						 //获取前台选中的全部会员等级 1 全部 2 选中部分会员等级
 						 String ugrade_ugrade = request.getParameter("ugrade_ugrade");
  						//选择全部会员等级
 						 if(ugrade_ugrade.equals("1")){
 							 //生成长度未30位0的字符串0000000000000000000000000000000000
 							ugrade_ugrade = StringUtil.getPlaceholder(30);
 			  				 //查询全部会员等级
 							 List<UserGrade> userGrades2 = userGradeServiceI.getAll(null);
 							 for(UserGrade userGrade3: userGrades2){
 								ugrade_ugrade =StringUtil.setPlaceholder(ugrade_ugrade, userGrade3.getUgrade().intValue()-1);	
 							 }
 						 }else if(ugrade_ugrade.equals("2")){
 							 //获取选中的部分会员等级
 							 String[] ugrade2 = wCCAwardRule.getUgradesx();
 							 //选择的部分会员等级
 							ugrade_ugrade = StringUtil.setPlaceholderForArr(ugrade2, 30);
 						 }
  						 //提低卷活动规则ID
 						 wCCAwardRule.setActid(withdrawsCashCoupon.getId());
 						 //提低卷奖励规则制表人
 						 wCCAwardRule.setAddman(addman_addman);
 						 //提低卷奖励规则备注
 						 wCCAwardRule.setRemark(remark_remark);
 						 //提低卷奖励规则会员等级
 						 wCCAwardRule.setUgrade(ugrade_ugrade);
 						 //设置制表时间
 						wCCAwardRule.setAddtime(new Date());
 						int count2 = 0;
 						count2 = wCCAwardRuleServiceI.insertSelective(wCCAwardRule);
 						if(count2 > 0){
 							 maps.put("result", "success");
  						}else{
  							 maps.put("result", "fail");
   						}
  					 }
				 }else{
					 maps.put("result", "fail");
				 }
				 String str = JSON.toJSONString(maps);
				 StringUtil.sendJsonData(response, str);
			} catch (IOException e) {
 				e.printStackTrace();
			}
 		 }
  		 return null;
	 }
	 
 	 /**
	  * 
	 * @Title: insert 
	 * @Description: TODO(跳转设置提抵卷活动规则和提抵卷奖励规则页面) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/edit")
	 public String edit(UserGrade userGrade,Model model,HttpServletRequest request){
		String opid = request.getParameter("opid");
		if(StringUtil.isNotEmpty(opid)){
			//id不未空 是更新操作
			BigDecimal id = new BigDecimal(opid);
			WithdrawsCashCoupon withdrawsCashCoupon = withdrawsCashCouponService.selectByPrimaryKey(id);
			 String params = "";
		     if(withdrawsCashCoupon !=null){
		    	 String ugrade = withdrawsCashCoupon.getUgrade();
		    	 List<Integer> lists = StringUtil.pars(ugrade);
		    	 for(Integer lit:lists){
		    		params +=lit+",";
		    	 }
		     }
		     model.addAttribute("params", params);
			if(withdrawsCashCoupon.getActbtime() !=null){
    			 /*返回前台活动生效日期时间字符串*/
				withdrawsCashCoupon.setActbtimestr(sf.format(withdrawsCashCoupon.getActbtime()));
	 		   }
			 if(withdrawsCashCoupon.getActetime() !=null){
	 		/*返回前台活动截止日期时间字符串*/
				 withdrawsCashCoupon.setActetimestr(sf.format(withdrawsCashCoupon.getActetime()));
			 }
			 if(withdrawsCashCoupon.getAddtime() !=null){
				 /*返回前台制表时间字符串*/
				 withdrawsCashCoupon.setAddtimestr(sf.format(withdrawsCashCoupon.getAddtime()));
	 		 }
			model.addAttribute("coupon", withdrawsCashCoupon);
			/*查询全部排除名单*/
			List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
			/*查询全部会员等级*/
			List<UserGrade> userGrades =userGradeServiceI.getAll(userGrade);
			model.addAttribute("userGrades", userGrades);
			model.addAttribute("removeNames", removeNames);
			return "admin/withdrawsCashCoupon/edit";
 		} else {
 			return null;
 		}
	 }
	 @RequestMapping("/insert")
	 public String insert(UserGrade userGrade,Model model,HttpServletRequest request){
 			/**
			 * 查询全部奖品
			 */
 		 List<Award> awards = awardService.selectByCondition(null);
  		/*查询全部排除名单*/
		 List<RemoveName> removeNames = removeNameServiceI.selectNameNamNoNameType();
		 /*查询全部会员等级*/
		 List<UserGrade> userGrades =userGradeServiceI.getAll(userGrade);
		 model.addAttribute("userGrades", userGrades);
		 model.addAttribute("removeNames", removeNames);
		 model.addAttribute("awardList", awards);
		 return "admin/withdrawsCashCoupon/lowinsert";
	 }

	 /**
	  * 
	 * @Title: detail 
	 * @Description: TODO(查看提低卷活动规则 详情信息) 
	 * @param @return  参数说明 
	 * @return String    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping(value="/detail")
	 public ModelAndView detail(HttpServletRequest request){
 		 String opid = request.getParameter("opid");
 		 if(StringUtil.isNotEmpty(opid)){
 			 ModelAndView modelAndView = new ModelAndView();
  		     WithdrawsCashCoupon coupon = withdrawsCashCouponService.selectByPrimaryKey(new BigDecimal(opid));
	  		 if(coupon.getActbtime() !=null){
	     			 /*返回前台活动生效日期时间字符串*/
	  			 coupon.setActbtimestr(sf.format(coupon.getActbtime()));
	  		   }
     		 if(coupon.getActetime() !=null){
      		/*返回前台活动截止日期时间字符串*/
     			coupon.setActetimestr(sf.format(coupon.getActetime()));
     		 }
     		 if(coupon.getAddtime() !=null){
     			 /*返回前台制表时间字符串*/
     			coupon.setAddtimestr(sf.format(coupon.getAddtime()));
      		 }
  		     modelAndView.addObject("coupon", coupon);
  		     modelAndView.setViewName("admin/withdrawsCashCoupon/detailTemplate");
 			 return modelAndView; 
 		 }else{
 			 return null;
 		 }
		  
  	 }
	 /**
	  * 
	 * @Title: findAname 
	 * @Description: TODO(根据奖品ID查询奖品名称) 
	 * @param   参数说明 
	 * @return void    返回类型 
	 * @author chenjiaming
	 * @throws
	  */
	 @RequestMapping("/findAname")
	 public void findAname(Award award,HttpServletResponse response){
 		 if(award !=null && award.getId() !=null){
			 try {
   				 List<Award> awards = awardService.selectByCondition(award);
					Award award1 =null;
					if(awards.size()>0){
						award1 = awards.get(0);
					}
				 String data = JSON.toJSONString(award1.getAname());
				 StringUtil.sendJsonData(response, data);
 			} catch (IOException e) {
 				e.printStackTrace();
			}
		 }
 	 }
	 
 }
