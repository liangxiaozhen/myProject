package com.ptpl.controller.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ImageTextSetting;
import com.ptpl.service.ImageTextItemServiceI;
import com.ptpl.service.ImageTextSettingServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 图文项目设置Controller
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月10日 上午10:51:48
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin/imageTextItem")
public class ImageTextItemManagerController extends BaseController {
	@Autowired
	ImageTextItemServiceI imageTextItemService;
	
	@Autowired
	ImageTextSettingServiceI imageTextSettingService;
	/**
	 * 查询List
	 * 
	 * @param imageTextItem
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView queryAll(ImageTextItem imageTextItem) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");

		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);

		List<ImageTextItem> list = imageTextItemService.listImageTextItem(imageTextItem);
		PageInfo<Object> pagehelper = initPagehelper(map, list);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/imageTextItem/imageTextItem_list");
		mav.addObject("pagehelper", pagehelper);
		return mav;
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "insert_UI")
	public ModelAndView insert_UI() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/imageTextItem/insert_imageTextItem");
		return mav;
	}

	/**
	 * 保存
	 * 
	 * @param item
	 * @throws Exception
	 */
	@RequestMapping(value = "insert")
	public void saveImageTextItem(ImageTextItem item) throws Exception {
		String data = "fail";
		String itiname = item.getItiname().trim();
		if (StringUtil.isNotEmpty(itiname) && itiname.length() <= 15) {
			/*
			 * 验证项目名称
			 */
			ImageTextItem verifyName = new ImageTextItem();
			verifyName.setItiname(item.getItiname());
			int size = verifyITIName(verifyName);
			/*
			 * size<1 表示项目名称不存在
			 */
			if (size < 1) {
				/*
				 * 获取管理员名称
				 */
				AdminUser user = getAdminUser();
				item.setAddman(user.getUsername());
				item.setAddtime(new Date());
				int iden = imageTextItemService.insertSelective(item);
				if (iden > 0) {
					data = "success";
				}
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_UI")
	public ModelAndView update_UI(BigDecimal id) {
		if (id != null) {
			ImageTextItem item = imageTextItemService.selectByPrimaryKey(id);
			if (item != null) {
				ModelAndView mav = new ModelAndView();
				mav.setViewName("admin/imageTextItem/update_imageTextItem");
				mav.addObject("imageTextItem", item);
				return mav;
			}
		}
		return null;
	}

	/**
	 * 编辑 验证项目名称（先用项目名称查询，如果有，看ID是否和当前一样，一样则通过修改，如果不一样。则拒绝修改，提示项目名称已存在）
	 * 
	 * @param item
	 */
	@RequestMapping(value = "update")
	public void update(ImageTextItem item) throws Exception {
		String data = "fail";
		BigDecimal id = item.getId();
		String itiName = item.getItiname().trim();
		/*
		 * ID和项目名称不为空
		 */
		if (id != null && StringUtil.isNotEmpty(itiName)&&itiName.length()<=15) {
			ImageTextItem model = new ImageTextItem();
			model.setItiname(itiName);
			List<ImageTextItem> list = imageTextItemService.listImageTextItem(model);
			/*
			 * 查询项目名称是否存在
			 */
			if (list.size() <= 0) {
				int iden = imageTextItemService.updateByPrimaryKeySelective(item);
				if (iden > 0) {
					data = "success";
				}
			} else {
				/*
				 * 项目名称存在，查询ID是否一样，一样则是同一条记录，允许修改。 不同则不是同一条记录，不允许修改
				 */
				model = list.get(0);
				if (model.getId().equals(id)) {
					int iden = imageTextItemService.updateByPrimaryKeySelective(item);
					if (iden > 0) {
						data = "success";
					}
				} else {
					data = "exsit";
				}
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 删除界面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "del_UI")
	public ModelAndView del_UI(BigDecimal id) {
		ImageTextItem item = imageTextItemService.selectByPrimaryKey(id);
		if (item != null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("imageTextItem", item);
			mav.setViewName("admin/imageTextItem/delete_imageTextItem");
			return mav;
		}
		return null;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "delete")
	public void removeImageTextItem(BigDecimal id) throws Exception {
		String data = "fail";
		if (id != null) {
			ImageTextSetting imageTextSetting=new ImageTextSetting();
			imageTextSetting.setItino(id.intValue());
			List<ImageTextSetting> list=imageTextSettingService.listImageTextSetting(imageTextSetting);
			if(list.size()>0){
				for(ImageTextSetting del:list){
					imageTextSettingService.deleteByPrimaryKey(del.getId());
				}
			}
			int iden = imageTextItemService.deleteByPrimaryKey(id);
			if (iden > 0) {
				data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 校验项目名称（页面用）
	 * 
	 * @param itiname
	 */
	@RequestMapping(value = "checkITIName")
	public void checkITIName(ImageTextItem item) throws Exception {
		String data = "";
		if (StringUtil.isNotEmpty(item.getItiname())) {
			int size = verifyITIName(item);
			if (size > 0) {
				data = "exist";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 校验项目名称
	 * 
	 * @param item
	 * @return
	 */
	private int verifyITIName(ImageTextItem item) {
		return imageTextItemService.listImageTextItem(item).size();
	}
}
