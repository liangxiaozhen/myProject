package com.ptpl.controller.manager;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.AdminUser;
import com.ptpl.model.ImageTextItem;
import com.ptpl.model.ImageTextSetting;
import com.ptpl.service.ImageTextItemServiceI;
import com.ptpl.service.ImageTextSettingServiceI;

/**
 * 图文内容添加Controller
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年2月13日 下午2:39:13
 *
 */
@RequestMapping(value = "/admin/imageTextSetting")
@Scope("prototype")
@Controller
public class ImageTextSettingManagerController extends BaseController {
	@Autowired
	private ImageTextSettingServiceI imageTextSettingService;
	@Autowired
	private ImageTextItemServiceI imageTextItemService;

	/**
	 * 获取图文添加内容List
	 * 
	 * @param imageTextSetting
	 * @return
	 */
	@RequestMapping(value = "list")
	public ModelAndView queryAll(ImageTextSetting imageTextSetting) {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();
		initPage(map, pageNum, pageSize);
		List<ImageTextSetting> list = imageTextSettingService.listImageTextSetting(imageTextSetting);
		PageInfo<Object> pagehelper = initPagehelper(map, list);
		List<ImageTextItem> imageTextItemList = imageTextItemService.listImageTextItem(null);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("imageTextItemList", imageTextItemList);
		mav.setViewName("admin/imageTextSetting/imageTextSetting_list");
		return mav;
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "insert_UI")
	public ModelAndView insert_UI() {
		ImageTextItem item = new ImageTextItem();
		item.setIsuse((short) 1);
		/*
		 * 查询 启用状态下LIST
		 */
		List<ImageTextItem> list = imageTextItemService.listImageTextItem(item);
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("admin/imageTextSetting/insert_imageTextSetting");
		return mav;
	}

	/**
	 * 新增
	 * 
	 * @param imageTextSetting
	 */
	@RequestMapping(value = "insert")
	public void insert(ImageTextSetting imageTextSetting) throws Exception {
		String data = "fail";
		String title = imageTextSetting.getTitle().trim();
		Integer itino = imageTextSetting.getItino();
		String subtitle1 = imageTextSetting.getSubtitle1().trim();
		String subtitle2 = imageTextSetting.getSubtitle2().trim();
		String subtitle3 = imageTextSetting.getSubtitle3().trim();
		if (StringUtil.isNotEmpty(title) && itino != null) {
			// 判断副标题字数不能超过50
			if (subtitle1.length() <= 50 && subtitle2.length() <= 50 && subtitle3.length() <= 50) {
				/*
				 * 设置发布人和发布时间
				 */
				AdminUser admin = getAdminUser();
				imageTextSetting.setOperator(admin.getUsername());
				imageTextSetting.setIssuetime(new Date());
				int iden = imageTextSettingService.insertSelective(imageTextSetting);
				if (iden > 0) {
					data = "success";
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
	 * @throws Exception
	 */
	@RequestMapping(value = "delete_UI")
	public ModelAndView delete_UI(BigDecimal id) throws Exception {
		if (id != null) {
			ImageTextSetting imageTextSetting = imageTextSettingService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("imageTextSetting", imageTextSetting);
			mav.setViewName("admin/imageTextSetting/delete_imageTextSetting");
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
	public void delete(BigDecimal id) throws Exception {
		String data = "fail";
		if (id != null) {
			int iden = imageTextSettingService.deleteByPrimaryKey(id);
			if (iden > 0) {
				data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "queryById")
	public ModelAndView queryById(BigDecimal id) {
		if (id != null) {
			ImageTextSetting imageTextSetting = imageTextSettingService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.addObject("imageTextSetting", imageTextSetting);
			mav.setViewName("admin/imageTextSetting/imageTextSetting_Detail");
			return mav;
		}
		return null;
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
			ImageTextSetting image = imageTextSettingService.selectByPrimaryKey(id);
			if (image != null) {
				/*
				 * 获取可用状态的项目名称
				 */
				ImageTextItem item = new ImageTextItem();
				item.setIsuse((short) 1);
				List<ImageTextItem> list = imageTextItemService.listImageTextItem(item);

				ModelAndView mav = new ModelAndView();
				mav.setViewName("admin/imageTextSetting/update_imageTextSetting");
				mav.addObject("list", list);
				mav.addObject("imageTextSetting", image);
				return mav;
			}
		}
		return null;
	}

	/**
	 * 编辑
	 * 
	 * @param imageTextSetting
	 * @throws Exception
	 */
	@RequestMapping(value = "update")
	public void update(ImageTextSetting imageTextSetting) throws Exception {
		String data = "fail";
		String title = imageTextSetting.getTitle().trim();
		Integer itino = imageTextSetting.getItino();
		String subtitle1 = imageTextSetting.getSubtitle1().trim();
		String subtitle2 = imageTextSetting.getSubtitle2().trim();
		String subtitle3 = imageTextSetting.getSubtitle3().trim();
		Long serialNo = imageTextSetting.getSerialno();
		if (StringUtil.isNotEmpty(title) && itino != null && serialNo != null) {
			// 判断副标题字数不能超过15
			if (subtitle1.length() <= 50 && subtitle2.length() <= 50 && subtitle3.length() <= 50) {
				/*
				 * 设置发布人和发布时间
				 */
				AdminUser admin = getAdminUser();
				imageTextSetting.setOperator(admin.getUsername());
				/*
				 * 对序号进行判断和排序
				 */
				int iden = imageTextSettingService.updateByPrimaryKeySelective(imageTextSetting);
				if (iden > 0) {
					data = "success";
				}
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 查看内容
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "content_UI")
	public ModelAndView content_UI(BigDecimal id) {
		if (id != null) {
			ImageTextSetting imageTextSetting = imageTextSettingService.selectByPrimaryKey(id);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("admin/imageTextSetting/content_imageTextSetting");
			mav.addObject("imageTextSetting", imageTextSetting);
			return mav;
		}
		return null;
	}

	/**
	 * 保存内容
	 * 
	 * @param imageTextSetting
	 * @throws Exception
	 */
	@RequestMapping(value = "saveContent")
	public void saveContent(ImageTextSetting imageTextSetting) throws Exception {
		BigDecimal id = imageTextSetting.getId();
		String data = "fail";
		System.out.println(imageTextSetting.getContent().length());
		if (id != null) {
			/*
			 * 设置发布人和发布时间
			 */
			AdminUser admin = getAdminUser();
			imageTextSetting.setOperator(admin.getUsername());

			int iden = imageTextSettingService.updateByContent(imageTextSetting);
			if (iden > 0) {
				data = "success";
			}
		}
		sendJsonData(response, JSON.toJSONString(data));
	}

	/**
	 * 上传标题图
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "upload")
	public void upload() throws Exception {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();

					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String suffix = myFileName.substring(myFileName.lastIndexOf('.'));
						System.out.println("截取文件后缀--->" + suffix);
						String prefix = System.currentTimeMillis() + "";
						String newfileName = prefix + suffix;
						// 创建文件夹
						File f = null;
						f = new File("/upload/images/title");
						if (!f.exists()) {
							f.mkdirs();
						}
						// 定义上传路径
						String path = f.getPath() + "/" + newfileName;
						File localFile = new File(path);
						// 上传文件到指定路径
						file.transferTo(localFile);
						System.out.println(localFile);
						String jsonStr = JSON.toJSONString("/upload/" + newfileName);
						sendJsonData(response, jsonStr);
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}

		}
	}
}
