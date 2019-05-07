package com.ptpl.controller.manager;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Marknumber;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Plus;
import com.ptpl.model.TenderItem;
import com.ptpl.service.PlusServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.PublicUtil;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author:liuqh
 * @date:2016年07月14日 14:48:01
 * @description:标的增益设置
 */
@Controller
@RequestMapping("/admin/plus")
public class PlusController {
	@Autowired
	private PlusServiceI plusService;
	@Autowired
	private TenderItemServiceI tenderItemService;


	// 转发到增加标的增益设置页面
	@RequestMapping(value = "/insert_Plus_Ui/{tid}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insert_Plus_Ui(@PathVariable String tid) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/plus/Plus_Inst");
		modelAndView.addObject("tid",tid);
		Plus plus = plusService.findPlusByTid(new BigDecimal(tid));
		if(plus!=null){
			TenderItem tenderItem=tenderItemService.findTenderItemById(new BigDecimal(tid));
			tenderItem.setIsaplus((short)1);
			tenderItemService.update(tenderItem);
			modelAndView.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+tid+".action");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/insertPlustwo", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView insertPlustwo(Plus plus,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String plusno=StringUtil.getNameNoForName(Marknumber.PLUS_NO);//生成编号
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			plus.setAddman(adminUser.getUsername());//设置添加人
		}
		plus.setAddtime(new Date());//添加时间
		if(plus.getAoneqrofit()!=null){
			plus.setAoneqrofit(plus.getAoneqrofit()/100);//允许单张加息收益
		}
		plus.setPlusno(plusno);
		plusService.insert(plus);
		TenderItem tenderItem=tenderItemService.findTenderItemById(plus.getTid());
		tenderItem.setIsaplus((short)1);
		tenderItemService.update(tenderItem);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("redirect:/admin/tenderItem/tenderItem_tag_UI/"+plus.getTid()+".action");
		return mv;
    }
	// 增加
	@RequestMapping(value = "/insertPlus", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView insertPlus(Plus plus,HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println(plus);
		String plusno1=StringUtil.getNameNoForName("GJZY");
		plus.setPlusno(plusno1);
		plusService.insert(plus);
		//得到增益设置的编号并set进标的增益设置编号字段
		BigDecimal tenderItemId = (BigDecimal) request.getSession().getAttribute("tenderItemId");
		String plusno = plus.getPlusno();
		if(tenderItemId!=null){
			TenderItem tenderItem = PublicUtil.getTenderItem(tenderItemId,tenderItemService);
			tenderItem.setPlusno(plusno);
			ArrayList<String> urlList = (ArrayList<String>) request.getSession().getAttribute("urlList");
			Short isaudit = (Short) request.getSession().getAttribute("isaudit");
			//如果urlList.size的长度为0，则表示，此刻保存的是最后一个标相关的设置，则把标状态改为审核中
			if (urlList == null) {
				return PublicUtil.setLastItem(request, response, tenderItem, isaudit,tenderItemService);
			}
			//设置标的相应字段
			tenderItemService.update(tenderItem);
		}
		return  PublicUtil.changeUrlForTenderItem(request);
	}
	
	// 根据条件查找标的增益设置并转发到列表页面
	@RequestMapping(value = "/selectPlusByCondition", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectPlusByCondition(HttpServletRequest request,Plus plus) {

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
		List<Plus> PlusList = plusService.selectByCondition(plus);
		PageInfo<Plus> pagehelper = new PageInfo<Plus>(PlusList);
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
		modelAndView.addObject("plus", plus);
		// 指定视图
		modelAndView.setViewName("admin/plus/Plus_List");
		PublicUtil.removeFormSession(request);
		return modelAndView;
	}

	// 根据id查找xx并返回xx的详细页面(详情)
	@RequestMapping(value = "/selectPlusByPrimaryKey", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView selectPlusByPrimaryKey(BigDecimal tid) {
		ModelAndView modelAndView = new ModelAndView();
		Plus plus=new Plus();
		if(tid!=null){
			plus= plusService.findPlusByTid(tid);
			if(plus!=null){
				modelAndView.addObject("plus", plus);
			}
		} 
		modelAndView.setViewName("admin/plus/Plus_Detail");
		return modelAndView;
	}

	// 转发到修改标的增益设置页面
	@RequestMapping(value = "/toUpdateUi", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView toUpdateUi(BigDecimal tid) {
		ModelAndView modelAndView = new ModelAndView();
		if(tid!=null){
			 Plus plus=plusService.findPlusByTid(tid);
			 if(plus!=null){
				 modelAndView.addObject("plus", plus);
			 }
		}
		modelAndView.setViewName("admin/plus/Plus_Update");
		return modelAndView;
	}

	// 修改
	@RequestMapping(value = "/updatePlus", method = { RequestMethod.POST, RequestMethod.GET })
	public String updatePlus(Plus plus,HttpServletRequest request) {
		AdminUser adminUser=(AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER);
		if(adminUser!=null){
			plus.setAddman(adminUser.getUsername());//设置添加人
		}
		plus.setAddtime(new Date());//添加时间
		plusService.update(plus);
		return "redirect:/plus/selectPlusByCondition.action";
	}
	//删除
	@RequestMapping(value = "/deletePlus", method = { RequestMethod.POST, RequestMethod.GET })
	public void deletePlus(BigDecimal id,BigDecimal tid,HttpServletResponse response) throws IOException{
		PublicUtil.decideBeforeDelete(id,tid,response, tenderItemService,plusService);
	}

}
