package com.ptpl.controller.manager;

import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.LoanTypeObjectQuote;
import com.ptpl.service.LoanTypeObjectQuoteServiceI;
import com.ptpl.web.util.PublicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuj
 * 借款类型对象设置
 */
@Controller
@RequestMapping("/admin/loantype")
public class LoanTypeObjectQuoteController extends BaseController{
   
	@Autowired
	private LoanTypeObjectQuoteServiceI loanTypeObjectQuoteService;//借款类型service
	
	//查询
	@RequestMapping(value="/selectAll",method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView selectAllLoantype(LoanTypeObjectQuote quote,HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		Map<String, Object> map=new HashMap<>();
		initPage(map, pageNum, pageSize);
		List<LoanTypeObjectQuote> list=loanTypeObjectQuoteService.gettypeObjectQuotes(null);
        PageInfo<Object> pagehelper=initPagehelper(map, list);
        mv.addObject("pagehelper", pagehelper);
        mv.setViewName("admin/loanTypeObjectQuote/LoanTypeObjectQuote_List");
		return mv;
	}

   //新增页面
	@RequestMapping(value="/insert_UI",method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView insert_UI() throws Exception{
		return new ModelAndView("admin/loanTypeObjectQuote/loanTypeObjectQuote_Inst");
	}

	//新增
	@RequestMapping(value="/insert",method = {RequestMethod.POST, RequestMethod.GET})
	public void insert(LoanTypeObjectQuote loanTypeObjectQuote,HttpServletRequest request) throws IOException{
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			loanTypeObjectQuote.setAddman(adminUser.getUsername());//添加人
		}
		loanTypeObjectQuote.setAddtime(new Date());//添加时间
		//获取当前标的标项目最大序号
		Short serialno = loanTypeObjectQuoteService.selectMaxSerialNo();
		if (serialno == null) {
			serialno = 1;
		} else if(serialno<30){
			serialno=(short)(serialno+1);
		}else {
			PublicUtil.sendJsonData(response, "添加失败，标类型总数不能大于30个");
			return;
		}

		loanTypeObjectQuote.setSerialno(serialno);
		int i =loanTypeObjectQuoteService.insertSelective(loanTypeObjectQuote);
		if(i>0){
			PublicUtil.sendJsonData(response, "添加成功");
		}
	}

	//修改页面
	@RequestMapping(value="/update_UI",method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView update_UI(BigDecimal id) throws Exception{
		ModelAndView mv=new ModelAndView();
		if(id!=null){
			LoanTypeObjectQuote loanTypeObjectQuote=loanTypeObjectQuoteService.selectByPrimaryKey(id);
			mv.addObject("loanTypeObjectQuote", loanTypeObjectQuote);
		}
		mv.setViewName("admin/loanTypeObjectQuote/loanTypeObjectQuote_Inst");
		return mv;
	}

	//修改
	@RequestMapping(value="/update",method = {RequestMethod.POST, RequestMethod.GET})
	public void updateloantype(LoanTypeObjectQuote loanTypeObjectQuote,HttpServletRequest request) throws IOException{
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			loanTypeObjectQuote.setAddman(adminUser.getUsername());//添加人
		}
		loanTypeObjectQuote.setAddtime(new Date());//添加时间
		int i =loanTypeObjectQuoteService.updateByPrimaryKey(loanTypeObjectQuote);
		if(i>0){
			PublicUtil.sendJsonData(response, "修改成功");
		} else{
			PublicUtil.sendJsonData(response, "修改失败");
		}
	}




	
	//停用和启用
	@RequestMapping(value="toenable",method={RequestMethod.POST,RequestMethod.GET})
	public void Toenable(BigDecimal id,String str) throws Exception{
		if(id!=null&&!str.equals("")){
			LoanTypeObjectQuote objectQuote=new LoanTypeObjectQuote();
			objectQuote.setId(id);
			 if(str.equals("t")){//停用
				 objectQuote.setIsuse((short) 0);//停用
			 }else{//启用
				 objectQuote.setIsuse((short) 1);//启用
			 }
			 int i=loanTypeObjectQuoteService.updateByPrimaryKeySelective(objectQuote);
			 if(i>0){
				 PublicUtil.sendJsonData(response, "操作成功!");
			 }
		}
	}
}
