package com.ptpl.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.huishang.util.HSignUtil;
import com.huishang.util.RSAUtils;
import com.ptpl.core.annotation.Token;
import com.ptpl.model.RepaymentFrz;
import com.ptpl.model.SMSChannel;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.RepaymentFrzServiceI;
import com.ptpl.web.util.AES;
import com.ptpl.web.util.IdcardValidator;
import com.ptpl.web.util.SendMailTempalte;
import com.ptpl.web.util.StringUtil;

import net.sf.json.JSONObject;

/**
  * 
 * @ClassName: TestController 
 * @Description: TODO(测试类) 
 * @author cjm 
 * @date 2017年3月14日 上午9:55:30 
 *
  */

@RequestMapping("/test")
@Controller
 public class TestController extends BaseController{
	@Autowired
	private RepaymentFrzServiceI repaymentFrzServiceI;
	
 	@RequestMapping("/cookie")
 	@Transactional
 	public void test32432543(HttpServletRequest request,HttpServletResponse response){
 		try{
	    	RepaymentFrz repaymentFrz = new RepaymentFrz();
	    	repaymentFrz.setRetcode("测试");     //银行返回码
	    	repaymentFrz.setAuditman("测试");    //审核人  无需审核时，审核人为系统
	    	repaymentFrz.setCardnbr("测试");   //电子账号
	    	repaymentFrzServiceI.insertSelective(repaymentFrz);
	    	
	    	RepaymentFrz repaymentFrz1 = new RepaymentFrz();
	    	repaymentFrz1.setRetcode("测试1dfdsfddgfgfdgfdffffffffffffff");     //银行返回码
	    	repaymentFrz1.setAuditman("测试1");    //审核人  无需审核时，审核人为系统
	    	repaymentFrz1.setCardnbr("测试1");   //电子账号
	    	repaymentFrzServiceI.insertSelective(repaymentFrz1);
	    	
	    	RepaymentFrz repaymentFrz2 = new RepaymentFrz();
	    	repaymentFrz2.setRetcode("测试2");     //银行返回码
	    	repaymentFrz2.setAuditman("测试2");    //审核人  无需审核时，审核人为系统
	    	repaymentFrz2.setCardnbr("测试2");   //电子账号
	    	repaymentFrzServiceI.insertSelective(repaymentFrz2);
	    	
	    	RepaymentFrz repaymentFrz3 = new RepaymentFrz();
	    	repaymentFrz3.setRetcode("测试3");     //银行返回码
	    	repaymentFrz3.setAuditman("测试3");    //审核人  无需审核时，审核人为系统
	    	repaymentFrz3.setCardnbr("测试3");   //电子账号
	    	repaymentFrzServiceI.insertSelective(repaymentFrz3);
	    	
	    	RepaymentFrz repaymentFrz4 = new RepaymentFrz();
	    	repaymentFrz4.setRetcode("测试4"); //银行返回码
	    	repaymentFrz4.setAuditman("测试4"); //审核人  无需审核时，审核人为系统
	    	repaymentFrz4.setCardnbr("测试4"); //电子账号
	    	repaymentFrzServiceI.insertSelective(repaymentFrz4);
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 
		    throw new RuntimeException();
	    }
 		
 	}
	
	@RequestMapping("/cookie1")
	public void test324325431(HttpServletRequest request,HttpServletResponse response){
		PostMethod method;
        HttpClient httpClient = new HttpClient();
        StringPart sp = null;
        FilePart fp = null;
        String[] filePath = {"D:"};
        String[] fileName = {"test.txt"};
         Map<String,Object> params = null;
        JSONObject paramsJson = null;
        String pam = null;
        Part[] parts = null;
         for(int i=0;i < filePath.length;i++)
        {
            params = new HashMap<String,Object>();
            params.put("coinstCode", HSignUtil.COINSTCODE);
            params.put("bankCode",HSignUtil.BANKCODE);
            params.put("transDate", "201563");
            params.put("fileName",fileName[i]);
            
            File file1 = new File(filePath[i],fileName[i]);
              params.put("sign", "fdsfdsfdsfds");
            
            
            paramsJson = JSONObject.fromObject(params);
            pam = paramsJson.toString();
            sp = new StringPart("parameters",pam);
            sp.setCharSet("GBK");//这里要设置字符编码，不然会乱码
             
            if(file1.exists())
            {
                 try {
					fp = new FilePart("file",file1);
				} catch (FileNotFoundException e) {
 					e.printStackTrace();
				}
            }
  
            method = new PostMethod("http://113.87.88.48:8080/ptpjx/test/cookie.action");
            parts = new Part[]{fp,sp};
            method.getParams().setContentCharset("UTF-8");
            method.setRequestEntity(new MultipartRequestEntity(parts,method.getParams()));
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            try {
				httpClient.executeMethod(method);
			} catch (HttpException e) {
 				e.printStackTrace();
			} catch (IOException e) {
 				e.printStackTrace();
			}
         }
	
	 
	}
 	
	@RequestMapping(value = "/testtoken",method = {RequestMethod.GET,RequestMethod.POST})
	@Token(save = true)
	public void testtoken(HttpServletRequest request ,HttpServletResponse response){
		
		
		try {
			request.getRequestDispatcher("/test.jsp").forward(request, response);
		} catch (ServletException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
 	}
	
	@RequestMapping(value = "/token",method = {RequestMethod.GET,RequestMethod.POST})
	@Token(remove = true)
	public void test3423(HttpServletRequest request ,HttpServletResponse response){
		System.out.println("=============进来了====================");
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
 			e.printStackTrace();
		}
 		System.out.println("=============进来了dfdend====================");
		
	}
	
	
	@RequestMapping("/upload")
	public void test110(HttpServletRequest request ,HttpServletResponse response) throws IllegalStateException, IOException{
		
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            Iterator<String> iter = multiRequest.getFileNames();  
             while(iter.hasNext()){
            	 MultipartFile file = multiRequest.getFile(iter.next());  
             	 if(file != null){
             		 String path = "d:/" + "tetshgdf" +file.getOriginalFilename();;  
                     File localFile = new File(path);
                     if(!localFile.exists()){
                    	 localFile.mkdirs();
                     }
                     file.transferTo(localFile);
            	 }
            }
		}
			
		
		
		
		
		
		
	}
	 /*登录页面*/
	@RequestMapping("/test")
	public String test(HttpServletRequest request,HttpServletResponse response){
		 
  		return "forward:/WEB-INF/jsp/user/manager/securitycenter/tradePassword.jsp";
	}
	
	/*安全中心页面*/
	@RequestMapping("/test2")
	public String test2(HttpServletRequest request,HttpServletResponse response){
		return "forward:/WEB-INF/jsp/user/manager/securitycenter/list.jsp";
	}
	
	/*找回密码页面*/
	@RequestMapping("/test3")
	public String test3(HttpServletRequest request,HttpServletResponse response){
		return "forward:/WEB-INF/jsp/user/manager/findpwd/findpwd.jsp";
	}
	
	
	/*找回密码页面*/
	@RequestMapping("/test4")
	public String test4(HttpServletRequest request,HttpServletResponse response){
		return "forward:/WEB-INF/jsp/user/manager/findpwd/emailpwd.jsp";
	}
	
	/*找回密码页面*/
	@RequestMapping("/test5")
	public String test5(HttpServletRequest request,HttpServletResponse response){
		return "forward:/WEB-INF/jsp/user/manager/findpwd/mibaopwd.jsp";
	}
	
	/*找回密码页面*/
	@RequestMapping("/test6")
	public String test6(HttpServletRequest request,HttpServletResponse response){
		return "forward:/WEB-INF/jsp/user/manager/findpwd/pwdtype.jsp";
	}
	
	
	/*找回密码页面*/
	@RequestMapping("/test7")
	public String test7(HttpServletRequest request,HttpServletResponse response){
		String email = "428587670@qq.com";
		String emailCode = "";
		String toKCode = "";
		String method = "";
		String username = "fdsfdsfdsf";
		String from_email = "philip_fu@95epay.com";
		String password = "ABC123abc";
		String smtp_server = "smtp.95epay.com";
	 
 		
		boolean falg = SendMailTempalte.sendEamilForFindPwd(email, emailCode, toKCode, method, username, from_email, password, smtp_server, request);
		System.out.println(falg+"===============");
		return null;
	}
	
	/*还款页面*/
	@RequestMapping("/test8")
	public String test8(HttpServletRequest request,HttpServletResponse response){
		return "forward:/WEB-INF/jsp/user/manager/repayment/repayment.jsp";
	}
	
	
	
	 
	public static void main(String[] args) {
		 System.out.println("/jsp/user/".substring(1,"/jsp/user/".length()));
	}
	
}
