package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserMakeALoan;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 投标放款记录Controller
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/admin/userMakeALoan")
public class UserMakeALoanController extends BaseController {

	/** 投标放款记录Service */
	@Autowired
	UserMakeALoanServiceI userMakeALoanService;
	
	/** 标的设置Service */
	@Autowired
	TenderItemServiceI tenderItemService;
	
	/**
	 * 查询投标放款记录
	 * @Title: queryAll
	 * @Description: TODO(查询投标放款记录)
	 * @param record
	 * @throws Exception    设定文件
	 * @return ModelAndView    返回类型
	 */
	@RequestMapping("/queryAll")
	public ModelAndView queryAll(UserMakeALoan record) throws Exception {
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, pageSize);
		
		if(record.getOutaccount()!=null&&StringUtils.isNotBlank(record.getOutaccount().getLoginname())){
			record.getOutaccount().setLoginname(setEncrypt(record.getOutaccount().getLoginname()));
		}
		// 调用service层的方法得到对象列表
		List<UserMakeALoan> loansList = userMakeALoanService.selectAllLoansRecord(record);
		for(UserMakeALoan u:loansList){
			u.setOutaccount(getDecryptionUserBaseAccountInfoDetail(u.getOutaccount()));
		}

		PageInfo<Object> pagehelper = initPagehelper(maps, loansList);
		
		if(record.getOutaccount()!=null && StringUtils.isNotBlank(record.getOutaccount().getLoginname())){
			record.getOutaccount().setLoginname(getDecrypt(record.getOutaccount().getLoginname()));
		}
		
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper", pagehelper);
		// 条件回显
		mv.addObject("echodata", record);
		// 指定视图
		mv.setViewName("admin/userMakeALoan/UserMakeALoan_List");
		return mv;
	}
	
	/**
	 * 查询投标放款记录详情
	 * @Title: queryloanRecordDetail
	 * @Description: TODO(查询投标放款记录详情)
	 * @param id
	 * @throws Exception
	 * @return ModelAndView    返回类型
	 */
	@RequestMapping("/queryloanRecordDetail")
	public ModelAndView queryloanRecordDetail(BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		// 调用service层的方法得到对象列表
		UserMakeALoan detail = userMakeALoanService.selectByPrimaryKey(id);
		detail.getOutaccount().setLoginname(getDecrypt(detail.getOutaccount().getLoginname()));
		detail.getInaccount().setLoginname(getDecrypt(detail.getInaccount().getLoginname()));
		mv.addObject("detail", detail);
		mv.addObject("df", df);
		// 指定视图
		mv.setViewName("admin/userMakeALoan/UserMakeALoan_Detail");
		return mv;
	}
	
	
}
