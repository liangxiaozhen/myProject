package com.ptpl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ptpl.model.LoanCertPicpath;
import com.ptpl.service.LoanCertPicpathServiceI;
@Controller
@RequestMapping("/picpath")
public class LoanCertPicpathController{
	
	@Autowired
	private LoanCertPicpathServiceI loanCertPicpathService;
	
	//跳转至借贷人资料填写处
	@RequestMapping(value = "/Files", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView Files(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/loans/PictureFiles");
		return modelAndView;
	}
	
	//上传图片处理业务
	@RequestMapping(value = "/PictureFiles", method = { RequestMethod.POST,RequestMethod.GET })
	public ModelAndView PictureFiles(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ModelAndView modelandview =new ModelAndView();
		// 1.创建 DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory(); // 使用默认的.
		// 2.创建ServletFileUpload
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 总大小为10m
		upload.setSizeMax(1024 * 1024 * 10);
		//创建对象，用于参数传递给service,好使材料保存至数据库中
		LoanCertPicpath loancertpicpth=new LoanCertPicpath();
		// 用于判断是否是上传操作.
		boolean flag = upload.isMultipartContent(request); 
		//上传时间，并以时间生成文件夹保存数据
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		if (flag) {
			// 解决上传文件名称中文乱码
			upload.setHeaderEncoding("utf-8");
			try {
				// 解决request,得到所有的上传项FileItem
				List<FileItem> items = upload.parseRequest(request);
				// 3.得到所有上传项
				int num =0;//用于保存添加信息的状态
				for (FileItem item : items) {
					if (item.isFormField()) {
						// 非上传组件
						System.out.println("组件名称:" + item.getFieldName());
						// 解决乱码问题
						System.out.println("内容:" + item.getString("utf-8")); 
						if(item.getFieldName().equals("baseid"))
							loancertpicpth.setBaseid( new  BigDecimal(item.getString("utf-8")));
						System.out.println("检测baseid是否拿到值==========="+loancertpicpth.getBaseid());
						if(item.getFieldName().equals("liano"))
							loancertpicpth.setLiano(item.getString());
							System.out.println("检测资料编号是否拿到==="+loancertpicpth.getLiano());
							if(item.getFieldName().equals("certtype"))
								loancertpicpth.setCerttype(new Short(item.getString("utf-8")));
							System.out.println("检测用户类型是否拿到======="+loancertpicpth.getCerttype());
					} else {
						//如果用户当前材料没有，无法上传时，跳过当前循环，进行下一次判断是否有文件上传
						if(item.getFieldName()==null ||item.getFieldName()=="" && item.getName()==null||item.getName()=="")
							continue;
						// 上传组件
						System.out.println("组件名称:" + item.getFieldName());
						System.out.println("上传文件名称:" + item.getName());
						//当前上传文件的类型名
						loancertpicpth.setCertname(item.getFieldName());
						//当前上传的时间
						loancertpicpth.setAddtime(new Date());
						//当前资料编号
						String name = item.getName(); // 上传文件名称
						name = name.substring(name.lastIndexOf("\\") + 1);
						//将新文件以UUID生成新名字，以保证不会重名
						String uuidname= getUUIDFileName(name);
						System.out.println("新照片名：======="+uuidname);					
					 File path= pictureFiles(loancertpicpth.getBaseid(),dateString);
						IOUtils.copy(item.getInputStream(),new FileOutputStream(path+"\\"+uuidname));
						//截取部分，因数据库只需要部分
						loancertpicpth.setCertinfopath((loancertpicpth.getBaseid()+"/"+dateString+"/"+uuidname));
						System.out.println("当前文件的路径。。。。。。。。"+loancertpicpth.getCertinfopath());
						System.out.println("看看作用域liano是否拿到值==========="+loancertpicpth.getLiano());
						System.out.println("看看作用域baseid是否拿到值==========="+loancertpicpth.getBaseid());
						// 删除临时文件
						item.delete();		
						if(loancertpicpth.getCertinfopath() !=null)
						{
						   num =loanCertPicpathService.insert(loancertpicpth);
						//每次操作后，清楚已有路径，防止上述条件被执行
						loancertpicpth.setCertinfopath(null);
					}			
				}
					//循环结束后进入图片展示方法
					if(num>0)
						modelandview.setViewName("redirect:/picpath/picturedetails.action?liano="+loancertpicpth.getLiano());
				}
			} catch (FileUploadException e) {
				// e.printStackTrace();
				response.getWriter().write(e.getMessage());
				return modelandview;
			}
		} else {
			response.getWriter().write("不是上传操作");
			return modelandview;
		}
		return modelandview;
	}
	
	//图片展示详情(用户后台)
	@RequestMapping(value = "/picturedetails", method = { RequestMethod.POST,RequestMethod.GET })
	public  ModelAndView picturedetails(String liano)
	{
		System.out.println("方法进来没有  liano===="+liano);
		ModelAndView modelandview=new ModelAndView();
		List<LoanCertPicpath> piclist=loanCertPicpathService.selectbybaseid(liano);
		 modelandview.addObject("piclist", piclist);
		modelandview.setViewName("user/loans/picture");
		return modelandview;
	}
	
	//图片展示详情(管理后台)
	@RequestMapping(value = "/pictureadmin", method = { RequestMethod.POST,RequestMethod.GET })
	public  ModelAndView pictureadmin(String liano)
	{
		System.out.println("方法进来没有(管理后台)  liano===="+liano);
		ModelAndView modelandview=new ModelAndView();
		List<LoanCertPicpath> piclist=loanCertPicpathService.selectbybaseid(liano);
		 modelandview.addObject("piclist", piclist);
			modelandview.setViewName("admin/loans/pictureadmin");
		return modelandview;
	}
	
		
	//创建上传文件夹
	public File pictureFiles(BigDecimal baseid,String dateString)
	{
		File file =new File("D:/picture/userimage/"+baseid+"/"+dateString);    		
		//如果文件夹不存在则创建    
		if  (!file .exists()  && !file .isDirectory())      
		{       
		    file.mkdirs();
		}
		return file;  
	}
	
	
	// 获取随机名称 a.jpg
	public static String getUUIDFileName(String filename) {
		int index = filename.lastIndexOf(".");
		if (index != -1) {
			return UUID.randomUUID() + filename.substring(index);
		} else {
			return UUID.randomUUID().toString();
		}
	}
}