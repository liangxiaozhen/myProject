package com.ptpl.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.FailTCRecord_Constant;
import com.ptpl.model.FailTCRecord;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.FailTCRecordServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 用户后台-标的流标补偿记录
 * @ClassName: UserFailTCRecordController
 * @Description: TODO(用户后台-标的流标补偿记录)
 * @author zhenglm
 * @date 2016年8月30日 下午2:48:03
 *
 */
@Controller
@RequestMapping("/user/failtcrecord")
public class UserFailTCRecordController extends BaseController {
	
	/** 标的流标补偿记录Service */
	@Autowired
	FailTCRecordServiceI failTCRecordService;

	/**
	 * 查询当前用户的标的流标补偿记录
	 * @Title: query
	 * @Description: TODO(查询当前用户的标的流标补偿记录)
	 * @param record
	 * @throws Exception
	 * @return ModelAndView    返回类型
	 */
	@RequestMapping("/query")
	public ModelAndView query(FailTCRecord record) throws Exception {
		ModelAndView mv = new ModelAndView();
		UserBaseAccountInfo userBaseAccountInfo = getUserBaseAccountInfo();
		if(userBaseAccountInfo != null){
			// 处理分页请求
			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");
			Map<String, Object> map = new HashMap<String, Object>();
			initPage(map, pageNum, pageSize);
			record.setInvestorid(userBaseAccountInfo.getId());
			List<FailTCRecord> list = failTCRecordService.findFailTCRecordByInvestorId(record);
			PageInfo<Object> pagehelper = initPagehelper(map, list);
			mv.addObject("pagehelper", pagehelper);
			mv.addObject("df", df);
			mv.addObject("payouttypemap", FailTCRecord_Constant.PAYOUTTYPE_MAP);
			mv.addObject("isgrantmap", FailTCRecord_Constant.ISGRANT_MAP);
			mv.addObject("isauditmap", FailTCRecord_Constant.ISAUDIT_MAP);
			mv.addObject("echodata", record);
		}
		mv.setViewName("user/manager/usertender/failTCRecord");
		return mv;
	}
	
	/**
	 * 流标奖品补偿站外奖品邮寄地址填写
	 * @Title: addressSureUI
	 * @Description: TODO(流标奖品补偿站外奖品邮寄地址填写)
	 * @param id
	 * @return ModelAndView    返回类型
	 */
	@RequestMapping("/addressSureUI")
	public ModelAndView addressSure(BigDecimal id){
		FailTCRecord ftcr = failTCRecordService.selectByPrimaryKey(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("ftcr",ftcr);
		mv.setViewName("user/manager/usertender/address");
		return mv;
	}
	
	/**
	 * 确认流标奖品补偿站外奖品邮寄地址
	 * @Title: addressSure
	 * @Description: TODO(确认流标奖品补偿站外奖品邮寄地址)
	 * @param id
	 * @param remark
	 * @return void    返回类型
	 * @throws IOException 
	 */
	@RequestMapping("/addressSure")
	public void addressSure(BigDecimal id,String remark) throws IOException {
		// 根据id来更新标的流标补偿的收货地址（remark）
		FailTCRecord ftcr = failTCRecordService.selectByPrimaryKey(id);
		int row = 0;
		if(ftcr != null && ftcr.getIsgrant().equals((short) 2)){
			ftcr.setRemark(remark);
			ftcr.setIsgrant((short) 3);// 状态由待确认改为已确认
			row = failTCRecordService.updateByPrimaryKeySelective(ftcr);
		}
		Map<String,String> map = new HashMap<String,String>();
		if(row>0){
			map.put("result", "success");
 		}else{
 			map.put("result", "fail");
 		}
		String jsonStr = JSON.toJSONString(map);
		StringUtil.sendJsonData(response, jsonStr);
	}
}
