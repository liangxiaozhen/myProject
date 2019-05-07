package com.ptpl.web.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class TzAutoProjectUtil {
	
	private static String author="cjm";
	private static  String description = "提前实际还款记录实体";
	private static  String date = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
	private static  String entity = "AheadRealRepayment";
	private static  String lowEntity = "aheadRealRepayment";
	
	public static void createJunit(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\junit.txt");
			//写入到磁盘里面去
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\junit\\test\\");
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+"Test.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(entity+"Test.java"+"=======创建成功=======");
 		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(entity+"Test.java"+"=======创建不成功=======");
 		}
	}
	 
	public static void createEntity(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\entity.txt");
			//写入到磁盘里面去
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\model\\");
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+".java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createWeb(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\web.txt");
			//写入到磁盘里面去
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\controller\\manager\\");
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+"Controller.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(entity+"Controller.java=======创建成功=======");
 		} catch (Exception e) {
			System.out.println(entity+"Controller.java=======创建不成功=======");
 			e.printStackTrace();
		}
	}
	public static void createDao(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\dao.txt");
			//写入到磁盘里面去
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\dao\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, "I"+entity+"Dao.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	 
	public static void createDaoImpl(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\daoImpl.txt");
			//写入到磁盘里面去
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\dao\\"+lowEntity+"\\impl");
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+"DaoImpl.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 
	public static void createService(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\service.txt");
			//写入到磁盘里面去
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\service\\");
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+"ServiceI.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(entity+"ServiceI.java=======创建成功=======");
		} catch (Exception e) {
			System.out.println(entity+"ServiceI.java=======创建失败=======");
			e.printStackTrace();
		}
	}
	
	public static void createMapper(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\mapper.txt");
			//写入到磁盘里面去
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\mapper\\");
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+"Mapper.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(entity+"Mapper.java=======创建成功=======");
		} catch (Exception e) {
			System.out.println(entity+"Mapper.java=======创建失败=======");
			e.printStackTrace();
		}
	}
	 
	public static void createServiceImpl(){
		try {
			//模板页面
			String serviceTemplate = getPath("template\\serviceImpl.txt");
			//写入到磁盘里面去
			String result = replaceModel(serviceTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\service\\impl\\");
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+"ServiceImpl.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(entity+"ServiceImpl.java=======创建成功=======");
		} catch (Exception e) {
			System.out.println(entity+"ServiceImpl.java=======创建失败=======");
			e.printStackTrace();
		}
	}
	
	public static void createAction(){
		try {
			//模板页面
			String serviceTemplate = getPath("template\\action.txt");
			//写入到磁盘里面去
			String result = replaceModel(serviceTemplate);
			//要生成的根目录
			String daoRoot =  getPath("src\\com\\ptpl\\web\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, entity+"Action.java");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	 
	public static void createList(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\list.txt");
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, "list.jsp");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(lowEntity+"list.jsp=======创建成功=======");
 		} catch (Exception e) {
			System.out.println(lowEntity+"list.jsp=======创建失败=======");
 			e.printStackTrace();
		}
	}
	
 
	public static void createListTemplate(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\listTemplate.txt");
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, "listTemplate.jsp");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(lowEntity+"listTemplate.jsp=======创建成功=======");
 		} catch (Exception e) {
			System.out.println(lowEntity+"listTemplate.jsp=======创建不成功=======");
 			e.printStackTrace();
		}
	}
	
	public static void createdetailTemplate(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\detailTemplate.txt");
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, "detailTemplate.jsp");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(lowEntity+"detailTemplate.jsp=======创建成功=======");
 		} catch (Exception e) {
			System.out.println(lowEntity+"detailTemplate.jsp=======创建不成功=======");
 			e.printStackTrace();
		}
	}
	
	public static void createEdit(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\edit.txt");
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("WebRoot\\WEB-INF\\jsp\\admin\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, "edit.jsp");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
			System.out.println(lowEntity+"edit.jsp=======创建成功=======");
 		} catch (Exception e) {
			System.out.println(lowEntity+"edit.jsp=======创建不成功=======");
 			e.printStackTrace();
		}
	}
	
	public static void createJs(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\js.txt");
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("WebRoot\\js\\admin\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, "gj_"+lowEntity+".js");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	 
	public static void createAdd(){
		try {
			//模板页面
			String daoTemplate = getPath("template\\edit.txt");
			String result = replaceModel(daoTemplate);
			//要生成的根目录
			String daoRoot =  getPath("WebRoot\\WEB-INF\\pages\\"+lowEntity);
			File rootPath  = new File(daoRoot);
			//如果不存在那么久创建
			if(!rootPath.exists())rootPath.mkdirs();
			//产生接口文件
			File daoJava = new File(rootPath, "edit.jsp");
			//讲模板中替换好的数据通过写入目录中去
			FileUtils.writeStringToFile(daoJava, result, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String replaceModel(String path) throws IOException{
		String result = FileUtils.readFileToString(new File(path),"UTF-8");
		result = result.replaceAll("\\[entity\\]", entity)
				.replaceAll("\\[lowEntity\\]", lowEntity)
				.replaceAll("\\[author\\]", author)
				.replaceAll("\\[description\\]", description)
				.replaceAll("\\[date\\]", date);
		return result;
	}
	
	
	
 	public static String getPath(String appendPath){
		String path = System.getProperty("user.dir");
		if(StringUtil.isEmpty(appendPath)){
			return path;
		}else{
			return path+"\\"+appendPath;
		}
	}
	
	
 
//	public static void createEntity() throws IOException{
//		String newClassName = getHomeDir("src/com/tz/model")+"/"+entityClass+".java";
//		String actionTempContent = TmFileUtil.readFile(getHomeDir("src/template")+"entity.txt");
//		new File(newClassName).getParentFile().mkdirs();
//		if(!TmFileUtil.isExist(newClassName)){
//			bulidClass(actionTempContent,newClassName,entitypackage);
//			System.out.println("[TM构建类][Entity]===>  " +newClassName+"  [OK]");
//		}else{
//			System.out.println("[TM构建类][Entity]===>  " +entityClass+".java 该Dao类以及存在是否覆盖[y/n]!");
//			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//			String line = reader.readLine();
//			if(line!=null && line.equalsIgnoreCase("y")){
//				bulidClass(actionTempContent,newClassName,entitypackage);
//				System.out.println("[TM构建类][Entity]===>  " +newClassName+"  [覆盖OK]");
//			}
//		}
//	}
//	
	
	public static void main(String[] args) throws IOException {
//		Properties properties = System.getProperties();
//		for (Map.Entry<Object, Object> entry: properties.entrySet()) {
//			System.out.println(entry.getKey()+"==="+entry.getValue());
//		}
//	    createEntity();
//		createDao();
//		createAdd();
//		createAction();
//   	createDaoImpl();
		
		
		
//		createService();
//		createServiceImpl();
//		createMapper();
//		createJunit();
		createWeb();
////		
		createJs();
 		createList();
		createListTemplate();
		createdetailTemplate();
//		createEdit();
	}
}
