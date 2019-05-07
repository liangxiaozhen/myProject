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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Award_Constant;
import com.ptpl.model.DiffAwardSwitch;
import com.ptpl.service.DiffAwardSwitchServiceI;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/admin/diffAwardSwitch")
public class DiffAwardSwitchController {

	@Autowired
	private DiffAwardSwitchServiceI diffAwardSwitchService;
	
	/**
	 * 分类奖品总开关列表
	 */
	@RequestMapping("/selectDiffAwardByCondition")
	public ModelAndView awardMasterSwitch(HttpServletRequest request,HttpServletResponse response,DiffAwardSwitch das){
		
		//System.out.println("das==="+das);
		
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
		List<DiffAwardSwitch> dasList = diffAwardSwitchService.selectByCondition(das);
		
		PageInfo<DiffAwardSwitch> pagehelper = new PageInfo<DiffAwardSwitch>(dasList);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		// 把对象放进modelAndView中
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper",pagehelper);
		mv.addObject("das",das);
		mv.setViewName("admin/award/DiffAward_List");
		return mv;
	}
	
	/*插入分类奖品总开关  UI*/
	@RequestMapping("/insertDiffAwardUi")
	public ModelAndView insertDiffAwardUi(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("attributeData",Award_Constant.attributeData_map);
		mv.setViewName("admin/award/DiffAward_Insert");
		return mv;
	}
	
	/*插入分类奖品总开关 保存到数据库*/
	@RequestMapping("/insertDiffAward")
	public String insertDiffAward(DiffAwardSwitch das){
		//System.out.println("das==="+das);
		das.setAwardType(Award_Constant.attributeData_map.get(das.getAttribute()));
		das.setOperateTime(new Date());
		int row = diffAwardSwitchService.insertSelective(das);
		return "redirect:/admin/diffAwardSwitch/selectDiffAwardByCondition.action";
	}
	
	/*总开关详情*/
	@RequestMapping("/toViewDetail")
	public ModelAndView toViewDetail(BigDecimal id){
		
		DiffAwardSwitch das = diffAwardSwitchService.getDiffAwardSwitchById(id);
		System.out.println("das===="+das);
		ModelAndView mv= new ModelAndView();
		mv.addObject("das",das);
		mv.addObject("awardType",Award_Constant.attributeData_map.get(das.getAttribute()));
		mv.setViewName("admin/award/DiffAward_Detail");
		return mv;
	}
	
	/*更新奖品总开关  Ui*/
	@RequestMapping("/toUpdateSwitch")
	public ModelAndView toUpdateSwitch(BigDecimal id){
		
		ModelAndView mv = toViewDetail(id);//调用总开关详情的ModelAndView
		Map<String, Object> map = mv.getModel();
		DiffAwardSwitch das = (DiffAwardSwitch) map.get("das");
		mv.addObject("awardType",Award_Constant.attributeData_map.get(das.getAttribute()));
		mv.setViewName("admin/award/DiffAward_Update");
		return mv;
	}
	
	/*保存奖品总开关*/
	@RequestMapping("/updateDiffAward")
	public String updateDiffAward(DiffAwardSwitch das){
		das.setOperateTime(new Date());
		int row =diffAwardSwitchService.updateDiffAwardSwitchById(das);
		return "redirect:/admin/diffAwardSwitch/selectDiffAwardByCondition.action";
	}
	/*该开关是否已经设置*/
	@RequestMapping("/attributeIsOnly")
	public void attributeIsOnly(HttpServletResponse response,Short attribute) throws IOException{
		
		DiffAwardSwitch das = diffAwardSwitchService.getDiffAwardSwitchByAttribute(attribute);
		
		Map<String,String> hashMap = new HashMap<String,String>();
		if(das!=null){
			hashMap.put("result", "success");
		}else{
			hashMap.put("result", "fail");
		}
		String jsonStr= JSON.toJSONString(hashMap);
		StringUtil.sendJsonData(response, jsonStr);
		
	}
}
