package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.service.RedEnveLopeItemServiceI;

@Controller
@RequestMapping("/admin/checkRecord")
public class RedCheckRecordController extends BaseController{

	@Autowired
	private RedEnveLopeItemServiceI redEnveLopeItemService;
	
	@RequestMapping("/queryAllRedByCondition")
	public ModelAndView queryAllRedByCondition(HttpServletRequest request,RedEnveLopeItem reli){
		
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
		
		List<RedEnveLopeItem>  reliList = redEnveLopeItemService.selectByCondition(reli);
		for(RedEnveLopeItem r:reliList){
			r.setUsername(getDecrypt(r.getUsername()));
		}
		PageInfo<RedEnveLopeItem> pagehelper = new PageInfo<RedEnveLopeItem>(reliList);
		
		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		// 把对象放进modelAndView中
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("reli",reli);
		mv.setViewName("admin/Bonus/RedEnvelopeItem_List");
		return mv;
	}
	
	//查看详情
	@RequestMapping("/toViewDetail")
	public ModelAndView toViewDetail(BigDecimal id){
		
		//根据id获得现金红包发放对账记录表
		
		RedEnveLopeItem reli = redEnveLopeItemService.selectByPrimaryKey(id);
		reli.setUsername(getDecrypt(reli.getUsername()));
		ModelAndView mv = new ModelAndView();
		mv.addObject("reli",reli);
		mv.setViewName("admin/Bonus/RedEnvelopeItem_Detail");
		return mv;
	}
}
