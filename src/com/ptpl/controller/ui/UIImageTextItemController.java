package com.ptpl.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.StringUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ui.ImageTextItemVO;
import com.ptpl.service.ImageTextItemServiceI;

/**
 * 图文设置 前端接口
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月22日 上午10:17:24
 *
 */
@Controller
@RequestMapping("/ui/imagetextitem")
public class UIImageTextItemController extends BaseController {
	@Autowired
	private ImageTextItemServiceI imageTextItemService;

	/**
	 * 获取可用状态下的图文设置
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "list")
	public void list() throws Exception {
		List<ImageTextItemVO> list = imageTextItemService.listImageTextItemVO();
		String data = JSON.toJSONString(list);
		System.out.println(data);
		response.setHeader("Access-Control-Allow-Origin", "*");
		sendJsonData(response, data);
	}
}
