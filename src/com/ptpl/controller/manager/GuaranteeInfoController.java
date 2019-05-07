package com.ptpl.controller.manager;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.controller.BaseController;
import com.ptpl.model.GuaranteeInfo;
import com.ptpl.service.GuaranteeInfoServiceI;

@Controller
@RequestMapping("/guarantee")
public class GuaranteeInfoController extends BaseController {

	@Autowired
	CommonsMultipartResolver multipartResolver;

	@Autowired
	GuaranteeInfoServiceI guaranteeInfoService;

	/**
	 * 获取担保公司资料列表
	 */
	@RequestMapping("/queryGuaranteeList")
	public ModelAndView queryGuaranteeList(GuaranteeInfo guarantee) throws Exception {

		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		int num = 1;
		int size = 10;
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		PageHelper.startPage(num, size);
		
		List<GuaranteeInfo> guaranteeList = guaranteeInfoService.findGuaranteeList(guarantee);
		for(GuaranteeInfo guar : guaranteeList){
			if(guar.getRegtime() != null) {
				guar.setRegtimeStr(sf.format(guar.getRegtime()));
			}
		}

		PageInfo<GuaranteeInfo> pagehelper = new PageInfo<GuaranteeInfo>(guaranteeList);

		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}

		pagehelper.setLastPage(lastPageNum);
		
		// 条件回显
		String name = request.getParameter("name");
		
		// 返回ModelAndView
		ModelAndView mv = new ModelAndView();
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("name", name);
		// 指定视图
		mv.setViewName("admin/Guarantee/guaranteeinfoList");
		return mv;
	}
	
	/**
	 * 查看担保公司资料详情
	 */
	@RequestMapping("/queryGuaranteeDetail")
	public ModelAndView queryGuaranteeDetail(BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		String obj = request.getParameter("obj");
		try {
			GuaranteeInfo detail = guaranteeInfoService.selectByPrimaryKey(id);
			detail.setRegtimeStr(sf.format(detail.getRegtime()));
			detail.setAddtimeStr(sf.format(detail.getAddtime()));
			// 获取图片路径
			String picPath1 = detail.getQualificationspic();
			String picPath2 = detail.getLicencepic();
			String picPath3 = detail.getOrgcodepic();
			// 获取图片名称
			String picName1 = null;
			if(picPath1 != null){
				picName1 = picPath1.substring(21, picPath1.length());
			}
			String picName2 = null;
			if(picPath2 != null){
				picName2 = picPath2.substring(21, picPath2.length());
			}
			String picName3 = null;
			if(picPath3 != null){
				picName3 = picPath3.substring(21, picPath3.length());
			}
			mv.addObject("picName1", picName1);
			mv.addObject("picName2", picName2);
			mv.addObject("picName3", picName3);
			mv.addObject("detail", detail);
			if("查看详情".equals(obj)){
				System.out.println("查看详情===============");
				mv.setViewName("admin/Guarantee/guaranteeinfoDetail");
			} else {
				System.out.println("修改========================");
				mv.setViewName("admin/Guarantee/guaranteeinfoEdit");
			}
		} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		
    	}
		return mv;	
	}
	
	/**
	 * 跳转到新增页面
	 */
	@RequestMapping("/toInsert")
	public ModelAndView toInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/Guarantee/guaranteeinfoInsert");
		return mv;
	}
	
	/**
	 * 上传图片
	 */
    @RequestMapping("/upload"  )  
    public void upload(HttpServletRequest request,HttpServletResponse response) throws Exception {  
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  

                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                            System.out.println(myFileName);  
                            //重命名上传后的文件名  
                            String suffix = myFileName.substring(myFileName.lastIndexOf('.'));
                    		System.out.println("截取文件后缀--->" + suffix);
                    		String prefix = System.currentTimeMillis() + "";
                    		String newfileName = prefix + suffix;
                    		//创建文件夹
                    		File f = null;
                    		f = new File("D:/picture/guarantee");
                    		if(!f.exists()){
                        		f.mkdirs();
                    		}
                            //定义上传路径  
                            String path = f.getPath() + "/" + newfileName;  
                            File localFile = new File(path); 
                            //上传文件到指定路径
                            file.transferTo(localFile);  
                            System.out.println(localFile);
                            //前台获取上传路径
                            String localFilePath = localFile.toString();
                            Map<String, String> map = new HashMap<>();
                            map.put("name", newfileName);
                            map.put("path", localFilePath);
                            String jsonStr = JSON.toJSONString(map);
                            sendJsonData(response, jsonStr);
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }   
    } 
    
    /**
     * 新增担保公司资料
     */
    @RequestMapping("/saveGuarantee")
    public String saveGuarantee(GuaranteeInfo guarantee) throws Exception {
    	try {
    		int rows = 0;
    		guarantee.setAddtime(new Date());
    		rows = guaranteeInfoService.insertSelective(guarantee);
			Map<String, String> map = new HashMap<String, String>();
			if (rows > 0) {
				map.put("result", "保存成功！");
			} else {
				map.put("result", "保存失败！");
			}
			String jsonStr = JSON.toJSONString(map);
			sendJsonData(response, jsonStr);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		
    	}
		return null;	
    }
    
    /**
     * 修改担保公司资料
     */
    @RequestMapping("/updateGuarantee")
    public String updateGuarantee(GuaranteeInfo guarantee) throws Exception {
    	try {
    		int rows = 0;
    		rows = guaranteeInfoService.updateByPrimaryKeySelective(guarantee);
			Map<String, String> map = new HashMap<String, String>();
			if (rows > 0) {
				map.put("result", "修改成功！");
			} else {
				map.put("result", "修改失败！");
			}
			String jsonStr = JSON.toJSONString(map);
			sendJsonData(response, jsonStr);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		
    	}
    	return null;	
    }
    
	/**
	 * 删除担保公司资料
	 */
	@RequestMapping(value="/deleteGuarantee", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteGuarantee (BigDecimal id) throws Exception {
		
		// 调用service 删除
		try {
			int rows = 0;
			rows = guaranteeInfoService.deleteByPrimaryKey(id);

			Map<String, String> map = new HashMap<String, String>();
			if (rows > 0) {
				map.put("guaraId", String.valueOf(id));
				map.put("result", "success");
			} else {
				map.put("result", "fail");
			}

			String jsonStr = JSON.toJSONString(map);
			sendJsonData(response, jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
    
}
