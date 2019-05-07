package com.ptpl.controller.manager;

import java.math.BigDecimal;
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
import com.ptpl.constant.RechargeRate_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RechargeQuotaFee;
import com.ptpl.model.RechargeRate;
import com.ptpl.model.RechargeSNLLink;
import com.ptpl.model.SpecialNameList;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.RechargeQuotaFeeServiceI;
import com.ptpl.service.RechargeRateServiceI;
import com.ptpl.service.RechargeSNLLinkServiceI;
import com.ptpl.service.SpecialNameListServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.Arith;

/**
 * 和表单<c:forEach items="${pagehelper.list }" var="rechargeRate">一样
 * 
 * @author admin
 *
 */
@Controller
@RequestMapping("/admin/rechargeRate")
public class RechargeRateController  extends BaseController{
	@Autowired
	private RechargeRateServiceI rechargeRateService;
	@Autowired
	private RechargeQuotaFeeServiceI rechargeQuotaFeeService;
	@Autowired
	private RechargeSNLLinkServiceI rechargeSNLLinkService;
	@Autowired
	private SpecialNameListServiceI specialNameListService;


	/**
	 * 充值费率操作列表
	* @Title: queryAll 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param rechargeRate
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryAll()throws Exception {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		List<RechargeRate> rechargeRateList = rechargeRateService.getAll();
		PageInfo<Object> pagehelper = initPagehelper(map, rechargeRateList);
		//为了导航查询能取到值
		ModelAndView modelAndView = new ModelAndView(RechargeRate_Constant.LIST);
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("df", df);
		return modelAndView;
	}

	/**
	 * 详情 页面点击详情进入到这个方法,返回到详情页面,最后把详情页面加载到List的模态框
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryDetails", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryDetails(HttpServletRequest request, HttpServletResponse response, BigDecimal id)
			throws Exception {
		RechargeRate recharge = rechargeRateService.getById(id);
		List<RechargeSNLLink>  rsList = rechargeSNLLinkService.selectByrrid(recharge.getId());
		if(rsList.size()>0){
			for (RechargeSNLLink rechargeSNLLink : rsList) {
				 if(rechargeSNLLink!=null){
					 SpecialNameList sp = new SpecialNameList();
						sp.setId(rechargeSNLLink.getSnlid());
						SpecialNameList specialNameList = specialNameListService.getSpecialNameList(sp);//根据定向名单编号查询到定向名单id也就是snlid
						if(specialNameList!=null){
							rechargeSNLLink.setCode(specialNameList.getBusinessNo());
							rechargeSNLLink.setCodename(specialNameList.getBusinessName());
						}
				 }
			}
			recharge.setRechargeSNLLinkList(rsList);
		}
		ModelAndView modelAndView = new ModelAndView(RechargeRate_Constant.DETALIS);
		// 加入时间有空的情况下就不转换时间,也不加载时间字段,这样就可以避免在点击下一页的时候报null指针异常
		modelAndView.addObject("recharge", recharge);
		modelAndView.addObject("df", df);
		modelAndView.addObject("sf", sf);
		return modelAndView;
	}
	
	/**
	 * 编辑和上面详情雷同
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryEdits", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryEdit(HttpServletRequest request, HttpServletResponse response, long id) throws Exception {
		//得到要修改的当前费率表
		RechargeRate rechargeRate = rechargeRateService.getById(new BigDecimal(id));
		if(rechargeRate.getCharrate()!=null){
			rechargeRate.setCharrate(Arith.mul(rechargeRate.getCharrate(), 100));
		}
		//根据费率表的id查询到定额手续费表和定向名单表的数据
		List<RechargeQuotaFee>  rqList = rechargeQuotaFeeService.selectByrrid(rechargeRate.getId());
		List<RechargeSNLLink>  rsList = rechargeSNLLinkService.selectByrrid(rechargeRate.getId());
		if(rqList.size()>0){
			rechargeRate.setRechargeQuotaFeeList(rqList);
		}
		if(rsList.size()>0){
			for (RechargeSNLLink rechargeSNLLink : rsList) {
				SpecialNameList sp = new SpecialNameList();
				sp.setId(rechargeSNLLink.getSnlid());
				SpecialNameList specialNameList = specialNameListService.getSpecialNameList(sp);//根据定向名单编号查询到定向名单id也就是snlid
				if(specialNameList!=null){
					rechargeSNLLink.setCode(specialNameList.getBusinessNo());
				}
			}
			rechargeRate.setRechargeSNLLinkList(rsList);
		}
		String ck = "";
		if(rechargeRate.getMinfee()!=null){
			ck="1";
		}else{
			ck = "0";
		}
		ModelAndView modelAndView = new ModelAndView(RechargeRate_Constant.EDITS);
		modelAndView.addObject("rr", rechargeRate);
		modelAndView.addObject("ck", ck);
		modelAndView.addObject("df1", df1); 
		return modelAndView;
	}
	/**
	 * 修改
	* @Title: updateRechargeRate 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param response
	* @param @param rechargeRate
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author jiangxueyou 
	* @throws
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateRechargeRate(RechargeRate rechargeRate) {
			System.out.println(rechargeRate);
			//获取登录的用户
			AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER); 
			System.out.println(au.getUsername());
			rechargeRate.setAddman(au.getUsername());//获取登录名变为设置人
			rechargeRate.setAddtime(new Date());//设置时间
			rechargeRate.setPaycompany("徽商银行");//支付公司
			
			if(rechargeRate.getChargetype()==1){//定额
				rechargeRate.setMinmoney(null);
				rechargeRate.setMaxmoney(null);
				rechargeRate.setCharrate(null); 
				rechargeRate.setMinfee(null);
				rechargeRate.setMaxfee(null);
				int inden = rechargeRateService.update(rechargeRate);
				System.out.println(inden);
			}else{//百分比:就把所有的定额相关的数据清空
				List<RechargeQuotaFee> rfeeList  = rechargeQuotaFeeService.selectByrrid(rechargeRate.getId());
				for (RechargeQuotaFee rechargeQuotaFee : rfeeList) {
					rechargeQuotaFeeService.deleteByPrimaryKey(rechargeQuotaFee.getId());
				}
				if(rechargeRate.getCharrate()!=null){//处理费率,保存进数据库时进行处理
					rechargeRate.setCharrate(Arith.div(rechargeRate.getCharrate(), 100, 5));
				}
				int inden = rechargeRateService.update(rechargeRate);
				System.out.println(inden);
			}
		
		//假如是定额的时候,除了上面保存充值费率表还要保存充值定额手续费表和充值名单排出表
		if(rechargeRate!=null){
			//获取定额手续费表
			RechargeQuotaFee[] rfArray = rechargeRate.getRechargeQuotaFee();
			if(rfArray.length>0){
				for (int i = 0; i < rfArray.length; i++) {
					System.out.println(rfArray[i].getId()+"**********"+i);
					if(rfArray[i].getId()!=null){//如果能找到充值定额表的id,就证明是修改!如果找不到就添加
						RechargeQuotaFee rfee  = rechargeQuotaFeeService.selectByPrimaryKey(rfArray[i].getId());
						if(rfee!=null){
							rfee.setMinmoney(rfArray[i].getMinmoney());
							rfee.setRrid(rechargeRate.getId());
							rfee.setMaxmoney(rfArray[i].getMaxmoney());
							rfee.setQuotafee(rfArray[i].getQuotafee());
							int rfeeindex = rechargeQuotaFeeService.updateByPrimaryKeySelective(rfee);
							System.out.println(rfeeindex);
						}
					}else{//没有找到,所以添加
						RechargeQuotaFee rfee2 = rfArray[i];
						if(rfee2.getMaxmoney()!=null && rfee2.getMinmoney()!=null){
							rfee2.setRrid(rechargeRate.getId());
							int insertindex = rechargeQuotaFeeService.insertSelective(rfee2);
							System.out.println(insertindex);
						}
					}
				}
			}
			RechargeSNLLink[] rkArray = rechargeRate.getRechargeSNLLink();
			if(rkArray.length>0){
				for (int i = 0; i < rkArray.length; i++) {
					if(rkArray[i].getId()!=null){
						RechargeSNLLink rsnlid = rechargeSNLLinkService.selectByPrimaryKey(rkArray[i].getId());
						if(rsnlid!=null){//假如有这个对象!说明需要修改!
							//根据code查询到snlid然后保存到排除人名单表中
							SpecialNameList sp = new SpecialNameList();
							sp.setBusinessNo(rkArray[i].getCode());
							SpecialNameList specialNameList =  specialNameListService.getSnlsByNoOrName(sp);//根据定向名单标号查询到定向编号查询到定向名單對象
							rsnlid.setSnlid(specialNameList.getId());
							int snlids = rechargeSNLLinkService.updateByPrimaryKeySelective(rsnlid);
							System.out.println(snlids);
						}
					}else{//没有这个对象就直接添加一条对象
						if(rkArray[i].getCode()!=null){//以免出现null指针异常
							rkArray[i].setRrid(rechargeRate.getId());
							//根据定向名单标号查询到定向编号查询到定向名單對象
							SpecialNameList sp = new SpecialNameList();
							sp.setBusinessNo(rkArray[i].getCode());
							SpecialNameList specialNameList =  specialNameListService.getSnlsByNoOrName(sp);
							rkArray[i].setSnlid(specialNameList.getId());
							int adds = rechargeSNLLinkService.insertSelective(rkArray[i]);
							System.out.println(adds);
						}
					}
				}
			}		
		}
		return "redirect:/admin/rechargeRate/query.action";
	}
	/**
	 * 根据页面定向名单编号查询系统业务类型是不是包含充值人业务
	 * @param @param code
	 * @return void
	 * @author jiangxueyou
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getSpecialNameList", method = { RequestMethod.POST, RequestMethod.GET })
	public void getSpecialNameList(String code) throws Exception {
		String data = "yes";
		if(code!=""){
			SpecialNameList sp = new SpecialNameList();
			sp.setBusinessNo(code);
			SpecialNameList specialNameList = specialNameListService.getSnlsByNoOrName(sp);
			if(specialNameList!=null){
				String SystemBusType = specialNameList.getSystemBusType();
				if(SystemBusType!=null){
					char c = SystemBusType.charAt(7);
					if(c!='1'){
						data = "no";
					}
				}
			}else{
				data="inputerror";
			}
		}else{
			data = "snlidisempty";
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
	
	
	
	/**
	 * 删除排出人名单表
	 * @param @param relinkid
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public void deletesnlk(String relinkid) {
		long row;
		String str = "";
		try {	
				if(relinkid!=null){
					row = rechargeSNLLinkService.deleteByPrimaryKey(new BigDecimal(relinkid));
					if (row > 0) {
						str = "success";
					} else {
						str = "fail";
					}
				}

				String jsonStr = JSON.toJSONString(str);
				sendJsonData(response, jsonStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	/**
	 * 删除定额手续费表记录
	 * @param @param relinkid
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/deletefee", method = { RequestMethod.POST, RequestMethod.GET })
	public void deletefee(String feeid) {
		long row;
		String str = "";
		try {	
			if(feeid!=null){
				row = rechargeQuotaFeeService.deleteByPrimaryKey(new BigDecimal(feeid));
				if (row > 0) {
					str = "success";
				} else {
					str = "fail";
				}
			}
			
			String jsonStr = JSON.toJSONString(str);
			sendJsonData(response, jsonStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
