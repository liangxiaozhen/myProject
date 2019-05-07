package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.SMSSendRecord;
import com.ptpl.service.SMSSendRecordServiceI;
import com.ptpl.service.SMSSendServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
 * @ClassName: SMSSendRecordController
 * @Package com.ptpl.controller
 * @Description: TODO(短信发送记录 控制层 )
 * @author chenjiaming
 * @date 2016年09月02日 17:17:39
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/sMSSendRecord")
public class SMSSendRecordController extends BaseController {

	@Autowired
	private SMSSendRecordServiceI sMSSendRecordService;
	@Autowired
	private SMSSendServiceI smsSendService;

	/**
	 * 
	 * @Title: list @Description: TODO(短信发送记录查询通用方法) @param @param
	 * sMSSendRecord @param @return 参数说明 @return ModelAndView 返回类型 @author
	 * chenjiaming @throws
	 */
	@RequestMapping("/list")
	public ModelAndView list(SMSSendRecord sMSSendRecord) {
		int num = 1;
		int pageSize = 20;
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, pageSize);
		List<SMSSendRecord> sMSSendRecords = sMSSendRecordService.findSMSSendRecords(sMSSendRecord);

		PageInfo<SMSSendRecord> pagehelper = new PageInfo<SMSSendRecord>(sMSSendRecords);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if (pagehelper.getTotal() % pageSize == 0) {
			lasePageNum = (int) pagehelper.getTotal() / pageSize;
		} else {
			lasePageNum = (int) pagehelper.getTotal() / pageSize + 1;
		}
		pagehelper.setLastPage(lasePageNum);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/sMSSendRecord/list");
		modelAndView.addObject("pagehelper", pagehelper);
		return modelAndView;
	}

	/**
	 * 
	 * @Title: template @Description: TODO(短信发送记录模板方法
	 * ,下一页，根据用户名模糊查询通通进这里) @param @param request @param @param
	 * response @param @param sMSSendRecord @param @return 参数说明 @return
	 * ModelAndView 返回类型 @author chenjiaming @throws
	 */
	@RequestMapping("/template")
	public ModelAndView template(HttpServletRequest request, HttpServletResponse response,
			SMSSendRecord sMSSendRecord) {
		String pageS = request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		int num = 1;
		int pageSize = 20;
		if (StringUtil.isNotEmpty(pageS)) {
			pageSize = Integer.parseInt(pageS);
		}
		if (StringUtil.isNotEmpty(pageNo)) {
			num = Integer.parseInt(pageNo);
		}
		// 根据Id排序
		String sort = "id.desc";
		Order.formString(sort);
		PageHelper.startPage(num, pageSize);
		// 查询全部用户方法
		List<SMSSendRecord> sMSSendRecords = sMSSendRecordService.findSMSSendRecords(sMSSendRecord);

		PageInfo<SMSSendRecord> pagehelper = new PageInfo<SMSSendRecord>(sMSSendRecords);
		pagehelper.setFirstPage(1);
		int lasePageNum = 0;
		if (pagehelper.getTotal() % pageSize == 0) {
			lasePageNum = (int) pagehelper.getTotal() / pageSize;
		} else {
			lasePageNum = (int) pagehelper.getTotal() / pageSize + 1;
		}
		pagehelper.setLastPage(lasePageNum);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/sMSSendRecord/listTemplate");
		modelAndView.addObject("pagehelper", pagehelper);
		return modelAndView;
	}

	/**
	 * 补发短信
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年6月6日 上午9:22:00
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "sendAgain")
	public void sendAgain(BigDecimal id) throws Exception {
		if (id != null) {
			SMSSendRecord record = sMSSendRecordService.selectByPrimaryKey(id);
			Map<String, String> map = smsSendService.SMSSendAgain(record);
			sendJsonData(response, JSON.toJSONString(map.get("msg")));
		}
	}
}
