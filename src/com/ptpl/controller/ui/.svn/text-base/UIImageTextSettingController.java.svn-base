package com.ptpl.controller.ui;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ImageTextSetting;
import com.ptpl.model.ui.ImageTextSettingVO;
import com.ptpl.service.ImageTextItemServiceI;
import com.ptpl.service.ImageTextSettingServiceI;

/**
 * 图文内容 前端接口
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月22日 下午3:58:56
 *
 */
@Controller
@RequestMapping("/ui/imagetextsetting")
public class UIImageTextSettingController extends BaseController {
	@Autowired
	private ImageTextSettingServiceI imageTextSettingService;

	@Autowired
	private ImageTextItemServiceI imageTextItemService;

	/**
	 * 根据图文项目名称查询图文内容
	 * 
	 * @param itiname
	 * @throws Exception
	 */
	@RequestMapping(value = "getbyitiname")
	public void getByITIName(Integer itino, String title, String pageNum, String pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int size = 12;
		int num = 1;
		if (StringUtil.isNotEmpty(pageSize)) {
			int iPageSize = new Integer(pageSize);
			if (iPageSize > 0) {
				size = iPageSize;
			}
		}
		if (StringUtils.isNotEmpty(pageNum)) {
			int iPageNum = new Integer(pageNum);
			if (iPageNum > 0) {
				num = iPageNum;
			}
		}
		if (itino != null) {
			// 查询数据
			ImageTextItem item = imageTextItemService.selectByPrimaryKey(new BigDecimal(itino));
			if (item != null) {
				List<ImageTextSettingVO> list = imageTextSettingService.getImageTextSettingVO(itino, title);
				int total = list.size();
				int totalPageSize = 0;
				if (total % size == 0) {
					totalPageSize = total / size;
				} else {
					totalPageSize = total / size + 1;
				}
				List<ImageTextSettingVO> voList = new ArrayList<ImageTextSettingVO>();
				int n = 0;
				for (int i = size * (num - 1); i < total; i++) {
					if (n == size)
						break;
					voList.add(list.get(i));
					n++;
				}
				map.put("list", voList);
				map.put("pageNum", num);
				map.put("pageSize", size);
				map.put("totalPageSize", totalPageSize);
				map.put("total", total);
				map.put("itiname", item.getItiname());
				System.out.println(map);

			}
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		sendJsonData(response, JSON.toJSONString(map));
	}

	/**
	 * 获取主页底部
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年7月6日 下午12:07:33
	 * @throws Exception
	 */
	@RequestMapping(value = "getbottom")
	public void getBottom() throws Exception {
		// 判断map是否为空,为空，则new一个Hashmap,并把底部内容存放进去
		Map<String, Object> map = new HashMap<String, Object>();
		ImageTextSetting its = imageTextSettingService.selectByPrimaryKey(new BigDecimal(302));
		if (its != null) {
			map.put("status", 1);
			map.put("msg", "success");
			map.put("content", its.getContent());
		} else {
			map.put("status", 0);
			map.put("msg", "fail");
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/html; charset=utf-8");
		sendJsonData(response, JSON.toJSONString(map));
	}
}
