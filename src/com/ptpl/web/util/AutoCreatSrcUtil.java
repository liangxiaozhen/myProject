package com.ptpl.web.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
/**
 * @author:liuqh
 * @description:使用该工具类共需要配置两个参数：（参数1：entityName）和（参数2：lowEntityName）
 * 自动生成mapper层接口+service层接口+serviceimpl层实现类+junit/test层测试类+controller层的类+WEB-INF下/jsp/admin下的List.jsp+Detail.jsp+Insert.jsp+Update.jsp的源码
 */
public class AutoCreatSrcUtil {

	//类上面的代码注释
	private static String author = "liuqh";
	private static String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
	private static String description = "";
	
	//参数1：赋值的格式如：Student
	private static String entityName = "InterestExpense";
	//参数2：赋值的格式如：student
	private static  String lowEntityName= "interestExpense";
	//生成文件的名字
	private static String fileName=entityName+"Mapper.java";
	//模板的路径
	private static String templatePath = getPath("template2\\Mapper.txt");
	//生成文件的目录
	private static String dirpath = getPath("src\\com\\ptpl\\mapper");
	
	
	public static void main(String[] args) {
		//生成mapper层对应的接口
		create(templatePath,dirpath,fileName);
		
		//生成service层对应的接口
		fileName=entityName+"ServiceI.java";
		templatePath="template2\\ServiceI.txt";
		dirpath="src\\com\\ptpl\\service";
		create(templatePath,dirpath,fileName);
		
		//生成serviceImpl层对应的实现类
		fileName=entityName+"ServiceImpl.java";
		templatePath="template2\\ServiceImpl.txt";
		dirpath="src\\com\\ptpl\\service\\impl";
		create(templatePath,dirpath,fileName);
		
		//生成junit.test层对应测试类
		fileName=entityName+"Test.java";
		templatePath="template2\\Junit_test.txt";
		dirpath="src\\junit\\test";
		create(templatePath,dirpath,fileName);
				
		//生成controller层对应的类Controller
		fileName=entityName+"Controller.java";
		templatePath="template2\\Controller.txt";
		dirpath="src\\com\\ptpl\\controller";
		create(templatePath,dirpath,fileName);
		
		//生成jsp中的List页面
		fileName=entityName+"_List.jsp";
		templatePath="template2\\List.txt";
		dirpath="WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntityName;
		File dir_list  = new File(dirpath);
		if(!dir_list.exists())dir_list.mkdirs();
		create(templatePath,dirpath,fileName);
		
		//生成jsp中的Detail页面
		fileName=entityName+"_Detail.jsp";
		templatePath="template2\\Detail.txt";
		dirpath="WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntityName;
		File dir_detail  = new File(dirpath);
		if(!dir_detail.exists())dir_detail.mkdirs();
		create(templatePath,dirpath,fileName);
		
		
		//生成jsp中的Update页面
		fileName=entityName+"_Update.jsp";
		templatePath="template2\\Update.txt";
		dirpath="WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntityName;
		File dir_update  = new File(dirpath);
		if(!dir_update.exists())dir_update.mkdirs();
		create(templatePath,dirpath,fileName);
		
		//生成jsp中的Insert页面
		fileName=entityName+"_Insert.jsp";
		templatePath="template2\\Insert.txt";
		dirpath="WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntityName;
		File dir_insert  = new File(dirpath);
		if(!dir_insert.exists())dir_insert.mkdirs();
		create(templatePath,dirpath,fileName);
		
		
		System.out.println("mapper+service+serviceimpl+junit/test+controller+List.jsp+Detail.jsp+Insert.jsp+Update.jsp都成功生成");
	}

	/**
	 * 
	 * @param templatePath
	 * @param dirpath
	 * @param fileName
	 */
	public static void create(String templatePath,String dirpath,String fileName) {
		try {
			//读取模板文件到内存中并更改
			String result = replaceModel(templatePath);
			
			//生成xxx.java文件
			File xxxMapper = new File(dirpath, fileName);
			
			// 写进xxx.java文件
			FileUtils.writeStringToFile(xxxMapper, result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPath(String appendPath) {
		String path = System.getProperty("user.dir");
		if (StringUtil.isEmpty(appendPath)) {
			return path;
		} else {
			return path + "\\" + appendPath;
		}
	}

	public static String replaceModel(String path) throws IOException {
		String result = FileUtils.readFileToString(new File(path), "UTF-8");
		result = result.replaceAll("Pojo", entityName).replaceAll("pojo", lowEntityName).replaceAll(":author", ":"+author).replaceAll(":description", ":"+description).replaceAll(":date", ":"+date);
		return result;
	}
}
