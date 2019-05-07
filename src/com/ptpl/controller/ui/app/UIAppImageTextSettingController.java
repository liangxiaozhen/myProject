package com.ptpl.controller.ui.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ImageTextSetting;
import com.ptpl.model.ui.ImageTextSettingVO;
import com.ptpl.service.ImageTextSettingServiceI;

/**
 * Android端 图文模块接口
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年5月3日 上午10:13:52
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/ui/app/")
public class UIAppImageTextSettingController extends BaseController {

	@Autowired
	private ImageTextSettingServiceI imageTextSettingService;

	/**
	 * 获取轮播图
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月6日 上午11:28:26
	 * @throws Exception
	 */
	@RequestMapping(value = "getImage")
	public void getImage(int itino, String terminal) throws Exception {
		ImageTextSetting setting = new ImageTextSetting();
		setting.setIsuse((short) 1);// 启用
		if (StringUtil.isEmpty(terminal))
			return;
		if (terminal.equals("1") || terminal.equals("2")) {
			if (terminal.equals("1")) {
				setting.setAndroidterminal((short) 1);// Android
			}
			if (terminal.equals("2")) {
				setting.setIosterminal((short) 1);// ios
			}
			setting.setItino(itino);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Msg", "失败");
			map.put("status", 0);
			List<ImageTextSettingVO> voList = imageTextSettingService.listImageTextSettingForUI(setting);
			if (voList.size() > 0) {
				map.put("Msg", "成功");
				map.put("status", 1);
				map.put("imgs", voList);
			}
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/html; charset=utf-8");
			sendJsonData(response, JSON.toJSONString(map));
		}
	}
}
