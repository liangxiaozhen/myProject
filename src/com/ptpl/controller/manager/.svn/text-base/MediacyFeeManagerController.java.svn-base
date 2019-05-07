package com.ptpl.controller.manager;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.InterestExpense;
import com.ptpl.model.LoanTypeObjectQuote;
import com.ptpl.model.MediacyFee;
import com.ptpl.model.UserGrade;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import com.ptpl.service.MediacyFeeServiceI;
import com.ptpl.service.UserGradeServiceI;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * 标的居间费设置Controller
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年6月24日 下午2:19:35
 *
 */
@Controller
@RequestMapping("/admin/mediacyFee")
public class MediacyFeeManagerController extends BaseController {
	@Autowired
	private MediacyFeeServiceI mediacyFeeService;
	@Autowired
	private UserGradeServiceI userGradeService;
	@Autowired
	private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteServiceI;

	/**
	 * 查询标的居间费设置
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月24日 下午3:23:49
	 * @param mediacyFee
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView listMediacyFee() {
		ModelAndView mav = new ModelAndView();
		/** 查出全部的会员等级和标类型 **/
		List<UserGrade> allUserGrades = userGradeService.selectiveForNormal(null);
		List<LoanTypeObjectQuote> allLoanTypeObjectQuotes = loanTypeObjectQuoteServiceI.gettypeObjectQuotes(null);
		List<MediacyFee> mediacyFeeList = mediacyFeeService.listMediacyFee(null);
		mav.setViewName("admin/interestExpense/edit");
		if (mediacyFeeList.size() > 0) {// 修改
			for (MediacyFee mediacyFee : mediacyFeeList) {
				// 会员等级
				List<UserGrade> userGrades = new ArrayList<>();
				if (mediacyFee.getUgrade() != null) {
					List<Integer> pa1 = StringUtil.pars(mediacyFee.getUgrade());
					if (pa1.size() > 0) {
						for (Integer integer : pa1) {
							UserGrade userGrade = new UserGrade();
							userGrade.setUgrade(new BigDecimal(integer));
							List<UserGrade> userGrades2 = userGradeService.selectiveForNormal(userGrade);
							if (userGrades2.size() > 0) {
								userGrades.add(userGrades2.get(0));
							}
						}
					}
				}

				if (userGrades.size() > 0) {
					mediacyFee.setUsergrades(userGrades);
					if (userGrades.size() == allUserGrades.size()) {// 全部会员等级
						mediacyFee.setIsusergrades((short) 1);
					}
				}

				// 标类型
				List<LoanTypeObjectQuote> loanTypeObjectQuotes = new ArrayList<>();
				if (mediacyFee.getTtype() != null) {
					List<Integer> pa1 = StringUtil.parsStringToList(mediacyFee.getTtype());
					if (pa1.size() > 0) {
						for (Integer integer : pa1) {
							LoanTypeObjectQuote loanTypeObjectQuote = loanTypeObjectQuoteServiceI
									.selectBySerialNo(new BigDecimal(integer));
							if (loanTypeObjectQuote != null) {
								loanTypeObjectQuotes.add(loanTypeObjectQuote);
							}
						}
					}
				}

				if (loanTypeObjectQuotes.size() > 0) {
					mediacyFee.setLoantypeobjectquotes(loanTypeObjectQuotes);
					if (loanTypeObjectQuotes.size() == allLoanTypeObjectQuotes.size()) {// 全部标类型
						mediacyFee.setIsloantypeobjectquotes((short) 1);
					}
				}
			}
			mav.addObject("allUserGrades", allUserGrades);// 全部会员等级
			mav.addObject("allLoanTypeObjectQuotes", allLoanTypeObjectQuotes);// 全部标类型
			mav.addObject("mediacyFees", mediacyFeeList);// 全部利息管理费设置
			mav.addObject("mediacyFee", mediacyFeeList.get(0));// 单个利息管理费设置
			mav.setViewName("admin/mediacyFee/MediacyFee_Update");
			return mav;
		}

		// 新增页面
		mav.addObject("allUserGrades", allUserGrades);// 全部会员等级
		mav.addObject("allLoanTypeObjectQuotes", allLoanTypeObjectQuotes);// 全部标类型
		mav.setViewName("admin/mediacyFee/MediacyFee_List");
		return mav;
	}

	/**
	 * 修改保存
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	public String save() {
		String params = request.getParameter("params");
		String isaudit = request.getParameter("isaudit");
		String remark = request.getParameter("remark");

		Map<String, String> hashMap = new HashMap<>();
		if (StringUtil.isEmpty(params)) {
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

		if (StringUtil.isEmpty(remark)) {
			if (remark.length() > 80) {
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

		if (StringUtil.isEmpty(isaudit)) {
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

		if (!isaudit.equals("1")) {// 是否审核
			isaudit = "0";
		}

		JSONObject json = JSONObject.fromObject(params);
		if (!(json.size() > 0)) {
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

		List<MediacyFee> mediacyFeeList = mediacyFeeService.listMediacyFee(null);
		if (mediacyFeeList.size() > 0) {// 修改
			for (MediacyFee mediacyFee : mediacyFeeList) {
				mediacyFeeService.deleteByPrimaryKey(mediacyFee.getId());// 删除全部
			}
		}

		for (int i = 0; i < json.size(); i++) {
			String str = json.getString(String.valueOf(i));
			JSONObject json2 = JSONObject.fromObject(str);
			String gfitype = json2.getString("gfitype");
			String ttype = json2.getString("ttype");
			String mfrate = json2.getString("mfrate");
			String ugrade = json2.getString("ugrade");
			String maxiefee = json2.getString("maxiefee");
			String ttypeStr = StringUtil.getPlaceholder(30);
			if (ttype.equals("10000")) {// 全部标类型
				for (int i2 = 0; i2 < 30; i2++) {
					ttypeStr = StringUtil.setPlaceholder(ttypeStr, i2);
				}
			} else {
				String[] ttypeStrs = ttype.split(",");
				for (String string : ttypeStrs) {
					ttypeStr = StringUtil.setPlaceholder(ttypeStr, Integer.parseInt(string) - 1);
				}
			}

			String ugradeStr = StringUtil.getPlaceholder(30);
			if (ugrade.equals("10000")) {// 全部会员等级
				for (int i2 = 0; i2 < 51; i2++) {
					ugradeStr = StringUtil.setPlaceholder(ugradeStr, i2);
				}
			} else {
				String[] ugradeStrs = ugrade.split(",");
				for (String string : ugradeStrs) {
					ugradeStr = StringUtil.setPlaceholder(ugradeStr, Integer.parseInt(string));// 因为初始会员是
																								// 0
																								// 所以不进行减1操作
				}
			}
			MediacyFee mediacyFee=new MediacyFee();
			mediacyFee.setGfitype(new Short(gfitype)); // 计算方式（1根据用户等级，2根据标的风险等级
			mediacyFee.setTtype(ttypeStr); // 标的类型（1000000…） 30位
			mediacyFee.setUgrade(ugradeStr); // 会员等级
			mediacyFee.setMfrate(Double.valueOf(mfrate) / 100); // 利息管理费百份比
			mediacyFee.setMaxmfamount(Double.valueOf(maxiefee)); // 该段最高利息管理收费金额
			mediacyFee.setIsaudit(new Short(isaudit)); // 资金清算是否需要审
			mediacyFee.setAddtime(new Date()); // 添加时间
			if (StringUtil.isNotEmpty(remark)) {
				mediacyFee.setRemark(remark); // 备注
			}
			mediacyFeeService.insertSelective(mediacyFee);
			
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
}
