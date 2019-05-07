package com.ptpl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.model.GfundsInt;
import com.ptpl.model.Loaninfoaudit;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.LoanCertPicpathServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.loanInfoAuditServiceI;
import com.ptpl.web.util.PublicUtil;

@Controller
@RequestMapping("/loan")
/**
 * 实现借贷人资料的审核
 * @author lihs
 *
 */

public class loanInfoAuditController extends BaseController {
	@Autowired
	private loanInfoAuditServiceI loanInfoAuditService;
	@Autowired
	private LoanCertPicpathServiceI loanCertPicpathService;
	
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	//跳转至借贷人资料填写处
	@RequestMapping(value = "/touser", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView touser(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/loans/loanAudit");
		return modelAndView;
	}
	
	//添加审核信息
	@RequestMapping(value = "/insertloan", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView insertloan(Loaninfoaudit loaninfoaudit){
	        loaninfoaudit.setAddtime(new Date());
		ModelAndView modelAndView = new ModelAndView();
		Date currentTime=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateString = formatter.format(currentTime);
		int x=(int)(Math.random()*99999);
		String actNo="GJKE"+dateString+x;
		loaninfoaudit.setLiano(actNo);
		int num=loanInfoAuditService.insert(loaninfoaudit);
		if(num>0)
		{	modelAndView.addObject("actNo", actNo);
			modelAndView.setViewName("user/loans/PictureFiles");
		}else{
			modelAndView.setViewName("userReg");
		}
		return modelAndView;
	}
	//查询当前用户是否已有申请
	@RequestMapping(value = "/seleceBaseByid", method = { RequestMethod.POST,
	RequestMethod.GET })
	public ModelAndView seleceBaseByid(int id)
	{
		ModelAndView modelAndView = new ModelAndView();	
		List<Loaninfoaudit>	 listloan=	loanInfoAuditService.selectBaseByid(id);
		 //如果集合大于0，则进入审核信息展示页面，反之，则表明该用户没有审核信息，就进如申请审核页面
		 if(listloan.size()>0)
		 {
			 modelAndView.addObject("listloan", listloan);
			 modelAndView.setViewName("user/loans/audit");
		 }else{
			 modelAndView.setViewName("user/loans/loanAudit");
		 }
		return modelAndView;
	}
	//根据ID查询当前对像
	@RequestMapping(value = "/selectByPrimaryKey", method = { RequestMethod.POST,
	RequestMethod.GET })
	public ModelAndView selectByPrimaryKey(BigDecimal id)
	{
		System.out.println("检测方法是否运行，并且ID参数="+id);
		ModelAndView modelAndView = new ModelAndView();
		Loaninfoaudit loaninfoaudit=new Loaninfoaudit();
		loaninfoaudit=loanInfoAuditService.selectByPrimaryKey(id);
		//如果对象有数据，则进入该信息页面
		if(loaninfoaudit !=null)
		{
			 modelAndView.addObject("loaninfoaudit", loaninfoaudit);
			 modelAndView.setViewName("user/loans/auditloaninfo");
		}
		return modelAndView;
	}
	
	//删除单条审核记录
	@RequestMapping(value = "/deleteByPrimaryKey", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView deleteByPrimaryKey(BigDecimal id,int baseid,String liano)
	{
		System.out.println("检测方法是否运行，并且ID参数="+id);
		ModelAndView modelAndView = new ModelAndView();
		int num=loanInfoAuditService.deleteByPrimaryKey(id);
		//删除审核资料时，其上传的图片材料也清楚掉
		int sun=loanCertPicpathService.deleteBybaseid(liano);
		if(num>0)
			modelAndView.setViewName("redirect:/loan/seleceBaseByid.action?id="+baseid);
		return modelAndView;	
	}
	//修改单条审核记录
		@RequestMapping(value = "/updateioaninfo", method = { RequestMethod.POST,
				RequestMethod.GET })
	public ModelAndView updateioaninfo(Loaninfoaudit loaninfoaudit)
	{
			System.out.println("检测修改方法是否运行，并且ID参数="+loaninfoaudit.getId());
			ModelAndView modelAndView = new ModelAndView();
			int num=loanInfoAuditService.updateByPrimaryKeySelective(loaninfoaudit);
			if(num>0)
				modelAndView.setViewName("redirect:/loan/seleceBaseByid.action?id="+loaninfoaudit.getBaseid());
			return modelAndView;	
	}
		
		
		
		//查找所有审核信息用于管理
		@RequestMapping(value = "/selectAll", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView selectAll(HttpServletRequest request) {

			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			int num = 1;
			int size = 9;
			if (pageNum != null && !"".equals(pageNum)) {
				num = Integer.parseInt(pageNum);
			}
			if (pageSize != null && !"".equals(pageSize)) {
				size = Integer.parseInt(pageSize);
			}
			String sortString = "id.desc";
			Order.formString(sortString);

			PageHelper.startPage(num, size);
			// 调用service层的方法得到对象列表
			List<Loaninfoaudit> loaninfoaudit = loanInfoAuditService.selectAll();
			PageInfo<Loaninfoaudit> pagehelper = new PageInfo<Loaninfoaudit>(loaninfoaudit);
			pagehelper.setFirstPage(1);
			int lastPageNum = 0;
			if (pagehelper.getTotal() % size == 0) {
				lastPageNum = (int) pagehelper.getTotal() / size;
			} else {
				lastPageNum = (int) pagehelper.getTotal() / size + 1;
			}
			pagehelper.setLastPage(lastPageNum);
			// 把对象放进modelAndView中
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pagehelper", pagehelper);
			// 指定视图
			modelAndView.setViewName("admin/loans/loaninfo");
			return modelAndView;
		}
		//查找所有审核信息用于查看
		@RequestMapping(value = "/selectAllsee", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView selectAllsee(HttpServletRequest request) {

			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			int num = 1;
			int size = 9;
			if (pageNum != null && !"".equals(pageNum)) {
				num = Integer.parseInt(pageNum);
			}
			if (pageSize != null && !"".equals(pageSize)) {
				size = Integer.parseInt(pageSize);
			}
			String sortString = "id.desc";
			Order.formString(sortString);

			PageHelper.startPage(num, size);
			// 调用service层的方法得到对象列表
			List<Loaninfoaudit> loaninfoaudit = loanInfoAuditService.selectAll();
	
			PageInfo<Loaninfoaudit> pagehelper = new PageInfo<Loaninfoaudit>(loaninfoaudit);
			pagehelper.setFirstPage(1);
			int lastPageNum = 0;
			if (pagehelper.getTotal() % size == 0) {
				lastPageNum = (int) pagehelper.getTotal() / size;
			} else {
				lastPageNum = (int) pagehelper.getTotal() / size + 1;
			}
			pagehelper.setLastPage(lastPageNum);
			// 把对象放进modelAndView中
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pagehelper", pagehelper);
			// 指定视图
			modelAndView.setViewName("admin/loans/loaninfosee");
			return modelAndView;
		}
		//是否通过审核
		@RequestMapping(value = "/updatePass", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView  updatePass(String id,Short auditstatus,String username)
		{
			System.out.println("审核通过获取的ID======"+id);
			Loaninfoaudit loaninfoaudit=new Loaninfoaudit();
			loaninfoaudit.setId(new BigDecimal(id));
			System.out.println("审核通过获取的ID然后赋值的ID======"+id);
			//赋值审核状态
			loaninfoaudit.setAuditstatus(auditstatus);
			//赋值审核人
			loaninfoaudit.setAuditman(username);
			//赋值审核的时间
			loaninfoaudit.setAudittime(new Date());
			int num=loanInfoAuditService.updateByPrimaryKeySelective(loaninfoaudit);
			ModelAndView modelAndView = new ModelAndView();
			if(num>0)
				modelAndView.setViewName("redirect:/loan/selectAll.action");
			return modelAndView;
			
		}
		//根据条件查找
		@RequestMapping(value = "/selectIdAndauditstatus", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView selectIdAndauditstatus(HttpServletRequest request,Loaninfoaudit loaninfoaudit) {
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			int num = 1;
			int size = 9;
			if (pageNum != null && !"".equals(pageNum)) {
				num = Integer.parseInt(pageNum);
			}
			if (pageSize != null && !"".equals(pageSize)) {
				size = Integer.parseInt(pageSize);
			}
			String sortString = "id.desc";
			Order.formString(sortString);
			PageHelper.startPage(num, size);
			// 调用service层的方法得到对象列表
			List<Loaninfoaudit> loaninfo = loanInfoAuditService.selectIdAndauditstatus(loaninfoaudit);
			PageInfo<Loaninfoaudit> pagehelper = new PageInfo<Loaninfoaudit>(loaninfo);
			pagehelper.setFirstPage(1);
			int lastPageNum = 0;
			if (pagehelper.getTotal() % size == 0) {
				lastPageNum = (int) pagehelper.getTotal() / size;
			} else {
				lastPageNum = (int) pagehelper.getTotal() / size + 1;
			}
			pagehelper.setLastPage(lastPageNum);
			// 把对象放进modelAndView中
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pagehelper", pagehelper);
			// 指定视图
			modelAndView.setViewName("admin/loans/loaninfo");
			return modelAndView;
		}
		//根据条件查找（查看页）
		@RequestMapping(value = "/selectIdAndauditstatussee", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView selectIdAndauditstatussee(HttpServletRequest request,Loaninfoaudit loaninfoaudit) {
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			int num = 1;
			int size = 9;
			if (pageNum != null && !"".equals(pageNum)) {
				num = Integer.parseInt(pageNum);
			}
			if (pageSize != null && !"".equals(pageSize)) {
				size = Integer.parseInt(pageSize);
			}
			String sortString = "id.desc";
			Order.formString(sortString);
			PageHelper.startPage(num, size);
			// 调用service层的方法得到对象列表
			List<Loaninfoaudit> loaninfo = loanInfoAuditService.selectIdAndauditstatus(loaninfoaudit);
			PageInfo<Loaninfoaudit> pagehelper = new PageInfo<Loaninfoaudit>(loaninfo);
			pagehelper.setFirstPage(1);
			int lastPageNum = 0;
			if (pagehelper.getTotal() % size == 0) {
				lastPageNum = (int) pagehelper.getTotal() / size;
			} else {
				lastPageNum = (int) pagehelper.getTotal() / size + 1;
			}
			pagehelper.setLastPage(lastPageNum);
			// 把对象放进modelAndView中
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("pagehelper", pagehelper);
			// 指定视图
			modelAndView.setViewName("admin/loans/loaninfosee");
			return modelAndView;
		}
		
		//详细资料的查看
		@RequestMapping(value = "/selectminute", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView selectminute(BigDecimal id) {
			System.out.println(""+id);
			Loaninfoaudit loa=loanInfoAuditService.selectminute(id);
			ModelAndView mandv=new  ModelAndView();
			mandv.addObject("gfundsInt", loa);
			mandv.setViewName("admin/loans/loaninfominute");
			return mandv;
		}
}

