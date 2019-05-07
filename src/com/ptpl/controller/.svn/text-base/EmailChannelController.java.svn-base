package com.ptpl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.ptpl.model.EmailChannel;
import com.ptpl.service.EmailChannelServiceI;

@Controller
@RequestMapping("/admin/emaill")
public class EmailChannelController {
	@Autowired
	private EmailChannelServiceI emailchannelservice;
	
	//判断是否有数据，如果有，则进入通道展示页面，否则进入添加通道页面
	@RequestMapping(value = "/selectAll", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView selectAll(HttpServletRequest request)
	{
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
		ModelAndView modelandview=new ModelAndView();
		List<EmailChannel> li=emailchannelservice.selectAll();
		PageInfo<EmailChannel> pagehelper = new PageInfo<EmailChannel>(li);
		pagehelper.setFirstPage(1);
		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		if(li.size()>0)
		{
			modelandview.addObject("pagehelper", pagehelper);
			modelandview.setViewName("admin/email/listEmail");
		}
		else
			modelandview.setViewName("admin/email/emailgalleryset");
		return modelandview;
	}
	//进入添加页面
	@RequestMapping(value = "/skip", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView skip(EmailChannel record)
	{
		ModelAndView modelandview=new ModelAndView();
		modelandview.setViewName("admin/email/emailgalleryset");
		return modelandview;
		
	}
	//添加通道
	@RequestMapping(value = "/insert", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView insert(EmailChannel record)
	{
		ModelAndView modelandview=new ModelAndView();
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateString = formatter.format(currentTime);
		//添加通道时间
		record.setAddtime(currentTime);
		//产生编号
		record.setEmailcno(dateString);
		int num=emailchannelservice.insert(record);
		if(num>0)
			modelandview.setViewName("redirect:/admin/emaill/selectAll.action");
		return modelandview;
	}
	//删除通道
	@RequestMapping(value = "/delete", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView delete(BigDecimal id)
	{
		ModelAndView modelabdview=new ModelAndView();
		int num=emailchannelservice.deleteByPrimaryKey(id);
		if(num>0)
			modelabdview.setViewName("redirect:/admin/emaill/selectAll.action");
		return modelabdview;
	}
	//根据iD查对象
	@RequestMapping(value = "/selectbyid", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView selectbyid(BigDecimal id)
	{
		ModelAndView modelabdview=new ModelAndView();
		EmailChannel em=emailchannelservice.selectByPrimaryKey(id);
		modelabdview.addObject("em", em);
		modelabdview.setViewName("admin/email/updateemail");
		return modelabdview;
	}
	//修改通道
	@RequestMapping(value = "/update", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView update(EmailChannel record)
	{
		ModelAndView modelandview=new ModelAndView();
		Date currentTime = new Date();
		//添加通道时间
		record.setAddtime(currentTime);
		int num=emailchannelservice.updateByPrimaryKeySelective(record);
		if(num>0)
			modelandview.setViewName("redirect:/admin/emaill/selectAll.action");
		return modelandview;
	}
	//是否启用邮箱通道
	@RequestMapping(value = "/isuse", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView update(int isuse,BigDecimal id)
	{
		ModelAndView modelandview=new ModelAndView();
		EmailChannel record=new EmailChannel();
		record.setIsuse((short)isuse);
		record.setId(id);
		int num=emailchannelservice.updateByPrimaryKeySelective(record);
		if(num>0)
			modelandview.setViewName("redirect:/admin/emaill/selectAll.action");
		return modelandview;		
	}
}
