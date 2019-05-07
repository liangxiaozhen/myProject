package com.ptpl.controller.manager;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.UserPromoThirdPartyServiceI;

/**
 * 用户个人推广
 * @author xiaoy
 *
 * @version 2016年11月10日 下午12:01:31
 */
@Controller
@RequestMapping("/admin/userPromoThirdParty")
@Scope("prototype")
public class UserPromoThirdPartyManagerController extends BaseController {
	@Autowired
	UserPromoThirdPartyServiceI userPromoThirdPartyService;
	/**
	 * 启用设置 （三级开关）
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "isUse", method = {RequestMethod.POST, RequestMethod.GET})
	public void isUse(BigDecimal id) throws Exception
	{
		if (id != null)
		{
			UserPromoThirdParty uptp=new UserPromoThirdParty();
			uptp.setId(id);
			uptp.setIsopen((short)1);
			userPromoThirdPartyService.updateByPrimaryKeySelective(uptp);
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}
	/**
	 * 停用设置 （三级开关）
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "cancel", method = {RequestMethod.POST, RequestMethod.GET})
	public void cancel(BigDecimal id) throws Exception
	{
		if (id != null)
		{
			UserPromoThirdParty uptp=new UserPromoThirdParty();
			uptp.setId(id);
			uptp.setIsopen((short)0);
			userPromoThirdPartyService.updateByPrimaryKeySelective(uptp);
			sendJsonData(response, JSON.toJSONString("提示：操作成功。"));
		}
	}
}
