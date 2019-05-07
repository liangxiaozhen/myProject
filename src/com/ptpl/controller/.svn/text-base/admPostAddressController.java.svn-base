package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ptpl.model.UserPostAddress;
import com.ptpl.service.UserPostAddressI;

/**
 * @author liuj
 * 用户邮寄地址控制层
 */
@Controller
@RequestMapping(value="/admin/useraddress")
public class admPostAddressController extends BaseController{
  
	@Autowired
	private UserPostAddressI userPostAddressService;
	
	@RequestMapping(value="/queryUserAddressList",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView UserAddressList(UserPostAddress address) throws Exception{
		ModelAndView mv=new ModelAndView();
		String pageNum=request.getParameter("pageNum");
		String pageSize=request.getParameter("pageSize");
		Map<String, Object> map=new HashMap<>();
		initPage(map, pageNum, pageSize);
		List<UserPostAddress> list=userPostAddressService.selectAllAddress(address);
        PageInfo<Object> pagehelper=initPagehelper(map, list);
        mv.addObject("pagehelper", pagehelper);
        mv.setViewName("admin/userPostAddress/userPostAddressList");
		return mv;
	}
	
	//詳情
	@RequestMapping(value="/PostAddressDetail",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView userPostAddressDetail(BigDecimal id) throws Exception{
		ModelAndView mv=new ModelAndView();
		UserPostAddress address=userPostAddressService.selectByPrimaryKey(id);
		if(address!=null){
			mv.addObject("address", address);
		}
		mv.setViewName("admin/userPostAddress/userPostAddressDetail");
		return mv;
	}
}
