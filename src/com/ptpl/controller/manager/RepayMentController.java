package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AheadRealRepayment;
import com.ptpl.model.RepayMent;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.AheadRealRepaymentServiceI;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.RepayMentServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.StringUtil;
/**
 * 
 * @ClassName: RepayMentController 
 * @Package com.ptpl.controller 
 * @Description: TODO(还款记录 控制层 ) 
 * @author chenjiaming
 * @date 2016年09月08日 14:46:55
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/repayMent")
public class RepayMentController extends BaseController{

	@Autowired
	private RepayMentServiceI repayMentService;
 
 	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
 
 	@Autowired
	private AheadRealRepaymentServiceI aheadRealRepaymentServiceI;
  
 	/**
	 * 
	 * @Title: list 
	 * @Description: TODO(投资人还款计划 记录查询) 
	 * @param @param repayMent
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	 */
	@RequestMapping("/list")
	public ModelAndView list(){
		int num = 1;
		int pageSize = 20;
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, pageSize);
		Map<String,Object> maps = new HashMap<String,Object>();
 		maps.put("planstatus", (short)1);//还款计划状态(1有效，2无效)
		List<RepayMent> repayMents = repayMentService.findListRepayMentJoinTendAndUserInfoByConditions(maps);
		for(RepayMent ment : repayMents){
			if(ment.getUseroutaccountid() != null){
				ment.setUseroutaccountid(getDecryptionUserBaseAccountInfoDetail(ment.getUseroutaccountid()));
			}

			if(ment.getUserinaccountid() != null){
				ment.setUserinaccountid(getDecryptionUserBaseAccountInfoDetail(ment.getUserinaccountid()));
			}

			if(ment.getUserproxyaccountid() != null){
				ment.setUserproxyaccountid(getDecryptionUserBaseAccountInfoDetail(ment.getUserproxyaccountid()));
			}
		}
		PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if(pagehelper.getTotal() % pageSize ==0){
			lasePageNum = (int)pagehelper.getTotal() / pageSize;
		}else{
			lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
		}
		pagehelper.setLastPage(lasePageNum);

		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("admin/repayMent/list");
		modelAndView.addObject("pagehelper", pagehelper);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: template 
	 * @Description: TODO(投资人还款计划  ,分页模板，根据用户名模糊查询通通进这里) 
	 * @param @param request
	 * @param @param response
	 * @param @param repayMent
	 * @param @return  参数说明 
	 * @return ModelAndView    返回类型 
	 * @author chenjiaming
	 * @throws
	 */
	@RequestMapping("/template")
	public ModelAndView template(HttpServletRequest request,HttpServletResponse response,RepayMent repayMent){
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
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("isaudit", repayMent.getIsaudit());//是否审核 1是0否
		maps.put("planstatus", repayMent.getPlanstatus());//还款计划状态(1有效，2无效)
		maps.put("repaystatus", repayMent.getRepaystatus());//还款状态(1未还款，2已还款，3已提前还款，4处理中)
		maps.put("periods", repayMent.getPeriods());//还款期数
		maps.put("isdarepay", repayMent.getIsdarepay());// 是否债转还款(投标记录发生过债转 1是0否)
		maps.put("rmode", repayMent.getRmode());//还款模式（1人工，2系统，3线下）
		maps.put("isoverdue", repayMent.getIsoverdue());//是否逾期
		maps.put("isproxyrepay", repayMent.getIsproxyrepay());//是否代偿

		String tno = request.getParameter("tno");//标号
		String tname = request.getParameter("tname");//标名称
		String inloginname = request.getParameter("inloginname");//投资人用户名
		String outloginname = request.getParameter("outloginname");//还款人用户名
		String proxyloginname = request.getParameter("proxyloginname");//代还人用户名
		String utorderno = request.getParameter("utorderno");//投标流水号
 		String rorderno = request.getParameter("rorderno");//还款流水号
		String rbatchno = request.getParameter("rbatchno");//还款流水号
		String isahead = request.getParameter("isahead");//是否提前还款
 		if(StringUtil.isNotEmpty(tno)){
			maps.put("tno", tno.trim());
		}

		if(StringUtil.isNotEmpty(rbatchno)){
			maps.put("rbatchno", rbatchno.trim());
		}
		if(StringUtil.isNotEmpty(tname)){
			maps.put("tname", tname.trim());
		}

		if(StringUtil.isNotEmpty(inloginname)){
			maps.put("inloginname", inloginname.trim());
		}

		if(StringUtil.isNotEmpty(outloginname)){
			maps.put("outloginname", outloginname.trim());
		}

		if(StringUtil.isNotEmpty(proxyloginname)){
			maps.put("proxyloginname", proxyloginname.trim());
		}

		if(StringUtil.isNotEmpty(utorderno)){
			maps.put("utorderno", utorderno.trim());
		}
 
		if(StringUtil.isNotEmpty(rorderno)){
			maps.put("rorderno", rorderno.trim());
		}

		if(StringUtil.isNotEmpty(rbatchno)){
			maps.put("rbatchno", rbatchno.trim());
		}

		if(StringUtil.isNotEmpty(isahead)){
			maps.put("isahead", isahead.trim());
		} 
		List<RepayMent> repayMents = repayMentService.findListRepayMentJoinTendAndUserInfoByConditions(maps);
		for(RepayMent ment : repayMents){
			if(ment.getUseroutaccountid() != null){
				ment.setUseroutaccountid(getDecryptionUserBaseAccountInfoDetail(ment.getUseroutaccountid()));
			}

			if(ment.getUserinaccountid() != null){
				ment.setUserinaccountid(getDecryptionUserBaseAccountInfoDetail(ment.getUserinaccountid()));
			}

			if(ment.getUserproxyaccountid() != null){
				ment.setUserproxyaccountid(getDecryptionUserBaseAccountInfoDetail(ment.getUserproxyaccountid()));
			}
		}
		PageInfo<RepayMent> pagehelper = new PageInfo<RepayMent>(repayMents);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if(pagehelper.getTotal() % pageSize ==0){
			lasePageNum = (int)pagehelper.getTotal() / pageSize;
		}else{
			lasePageNum = (int)pagehelper.getTotal() / pageSize+1 ;
		}
		pagehelper.setLastPage(lasePageNum);

		ModelAndView modelAndView = new ModelAndView();	
		modelAndView.setViewName("admin/repayMent/listTemplate");
		modelAndView.addObject("pagehelper", pagehelper);
		return modelAndView;
	}
  
	/**
	 * 
	 * @Title: detail 
	 * @Description: TODO(查看还款记录详情信息) 
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
			RepayMent  repayMent = repayMentService.findRepayMentById(new BigDecimal(opid));
			if(repayMent != null ){
				UserBaseAccountInfo ontUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getOutaccountid());
				if(ontUserBaseAccountInfo != null && ontUserBaseAccountInfo.getLoginname() != null){
					ontUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(ontUserBaseAccountInfo);//解密加密后的数据
					repayMent.setUseroutaccountid(ontUserBaseAccountInfo);//借款人用户名
				}

				UserBaseAccountInfo intUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getInaccountid());
				if(intUserBaseAccountInfo != null && intUserBaseAccountInfo.getLoginname() != null){
					intUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(intUserBaseAccountInfo);//解密加密后的数据
					repayMent.setUserinaccountid(intUserBaseAccountInfo);//投资人用户名
				}

				UserBaseAccountInfo proxyUserBaseAccountInfo = userBaseAccountInfoServiceI.getUserBaseAccountInfoById(repayMent.getInaccountid());
				if(proxyUserBaseAccountInfo != null && proxyUserBaseAccountInfo.getLoginname() != null){
					proxyUserBaseAccountInfo = getDecryptionUserBaseAccountInfoDetail(proxyUserBaseAccountInfo);//解密加密后的数据
					repayMent.setUserproxyaccountid(proxyUserBaseAccountInfo);//投资人用户名
				}
			}
			AheadRealRepayment aheadRealRepayment = aheadRealRepaymentServiceI.findAheadRealRepaymentByRordernoAndBacthNo(repayMent.getRorderno(),repayMent.getRbatchno());
			if(aheadRealRepayment != null){
				modelAndView.addObject("aheadRealRepayment", aheadRealRepayment);
			}
			//   		     }
			modelAndView.addObject("repayMent", repayMent);
			modelAndView.setViewName("admin/repayMent/detailTemplate");
			return modelAndView; 
		}else{
			return null;
		}
	}
 }
