package com.ptpl.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.web.util.MyMapSessionId;
import com.ptpl.model.User;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.UserServiceI;
import com.ptpl.web.util.StringUtil;

@Controller
// 为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如：商品列表：/items/queryItems.action
@RequestMapping("/user")
public class UserController  extends BaseController{
	
	@Autowired
	UserServiceI userService ;
	
//	@RequestMapping(value = "/toadmin", method = { RequestMethod.POST,
//			RequestMethod.GET })
//	public ModelAndView toadmin(HttpServletRequest req,HttpServletResponse res) throws Exception {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("admin/index");
//		Enumeration temp = req.getHeaderNames();
//		while (temp.hasMoreElements()) {
//			String paramName = (String) temp.nextElement();
//			System.out.println(paramName + "=" + req.getHeader(paramName) + "<br>");
//			
//		}
//		return modelAndView;
//	}
	
	@RequestMapping(value = "/touser", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView touser(HttpServletRequest request,HttpServletResponse res){
		
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
//        ServletContext servletContext = webApplicationContext.getServletContext(); 
//		ServletContextEvent event = new ServletContextEvent(servletContext);
//		String ProjectPath = event.getServletContext().getRealPath("/");
		
//		String path = request.getContextPath();
//		String basePath = request.getScheme() + "://"
//				+ request.getServerName() + ":" + request.getServerPort()
//				+ path ; 
		
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);//获取userBaseAccountInfo 对象
		if(userBaseAccountInfo !=null){//对sessionId进行保存
			//对sessionId处理
			HttpSession session=request.getSession();
			String sessionId=session.getId();
			MyMapSessionId m=MyMapSessionId.getInstance();
			m.AddSession(userBaseAccountInfo.getLoginname(), sessionId);//将sessionId以登入名为key，sessionId为value保存
		}
		
		
		logger.info("登录后台入口");
		String folder = "trusteeship/chinapnr/" ;
		String merPrkey = "MerPrK531137.key";
		String pubKey = "PgPubk.key";
		
		String pubkeyPath = request.getSession().getServletContext().getRealPath(folder + pubKey);
		String merPrkeyPath = request.getSession().getServletContext().getRealPath(folder + merPrkey);
		
		try {
			FileInputStream fis = new FileInputStream(pubkeyPath);
			//创建一个长度为1024的竹筒  
	        byte[] bbuf = new byte[1024];  
	        //用于保存实际读取的字节数  
	        int hasRead = 0;  
	        //使用循环来重复“取水”的过程  
	        while((hasRead = fis.read(bbuf))>0)  
	        {  
	            //取出"竹筒"中(字节),将字节数组转成字符串输入  
	            System.out.println(new String(bbuf,0,hasRead));  
	        }  
	        fis.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(pubkeyPath);
		System.out.println(merPrkeyPath);
		
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.setViewName("user/index");
	  modelAndView.setViewName("user/manager/managerCenter");
		return modelAndView;
	}
	
	@RequestMapping(value = "/welcome", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView welcome(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/welcome");
		return modelAndView;
	}

	// 人员查询
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView queryAll(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		String usalary = request.getParameter("usalary");
		request.setAttribute("name", name);
		request.setAttribute("usalary", usalary);
		if(StringUtil.isNullStr(name)){
			name = null;
		}
		if(StringUtil.isNullStr(usalary)){
			usalary = null;
		}
//		int num = 1;
//		int size = 20;
//		if (pageNum != null && !"".equals(pageNum)) {
//			num = Integer.parseInt(pageNum);
//		}
//		if (pageSize != null && !"".equals(pageSize)) {
//			size = Integer.parseInt(pageSize);
//		}
//		String sortString = "id.desc";
//		Order.formString(sortString);

//		PageHelper.startPage(num, size);
		Map<String,Object> map = new HashMap<String,Object>();
//		Integer totalCount = this.userService.findUserCount(map);
		pageSize = "10";
		map.put("name", name);
		map.put("usalary", usalary);
		
		this.initPage(map, pageNum, pageSize);
		List userList = userService.getAllUser(map);
		
		for (Object o : userList) {
			User u = (User)o;
			u.setUserBirthdayStr(sf.format(u.getUserBirthday()));
		}
//		PageInfo<Object> pagehelper = new PageInfo<Object>(userList);
		
//		pagehelper.setFirstPage(1);
//		
//		int lastPageNum =0;
//		if(pagehelper.getTotal()%size==0){
//			lastPageNum = (int)pagehelper.getTotal()/size;
//		}else{
//			lastPageNum = (int)pagehelper.getTotal()/size + 1 ;
//		}
//		
//		pagehelper.setLastPage(lastPageNum);
		
		PageInfo<Object> pagehelper = this.initPagehelper(map,userList);
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当 于request的setAttribut，在jsp页面中通过personList取数据
		// modelAndView.addObject("personList", personList);
		modelAndView.addObject("pagehelper", pagehelper);

		// 指定视图
		// 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
		// modelAndView.setViewName("/jsp/personList.jsp");
		// 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
		modelAndView.setViewName("user/userList");
		return modelAndView;

	}

	

	// 删除
	@RequestMapping(value = "/delete", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response, long id) throws Exception {

		// 调用service 删除
		try {
			int rows = 0;
			rows = userService.deleteByPrimaryKey(id);

			Map<String, String> map = new HashMap<String, String>();
			if (rows > 0) {
				map.put("personId", String.valueOf(id));
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
		return null;

	}
     
	/**
	 * 通过JSON向前台发送数据
	 * 
	 * @param data
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	protected void sendJsonData(HttpServletResponse response, String data)
			throws IOException {
		// HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out;
		out = response.getWriter();
		out.println(data);
		out.flush();
		out.close();
	}
}