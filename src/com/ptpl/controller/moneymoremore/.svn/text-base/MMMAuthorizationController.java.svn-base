package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserAuthorization;
import com.ptpl.service.UserAuthorizationServiceI;
import com.ptpl.web.util.StringUtil;

/**
 * 
* @ClassName: MMMAuthorizationController 
* @Package com.ptpl.controller.moneymoremore 
* @Description: TODO(乾多多 授权接口 Controller) 
* @author cjm
* @date 2017年2月27日 下午2:49:19 
* @version V1.0
 */
@Controller
@RequestMapping("/moneymoremore/authorization")
public class MMMAuthorizationController extends BaseController{
	
	@Autowired
	private UserAuthorizationServiceI userAuthorizationServiceI;//用户授权记录Service
	
	@RequestMapping("/test")
	public ModelAndView inRepayMentAuthorizationController(HttpServletRequest request,HttpServletResponse response){
 		String  MoneymoremoreId = "m111972";
		String AuthorizeTypeOpen = "";
		String AuthorizeTypeClose = "1,2,3";
		String Remark1 = "609";
		doRepayMentAuthorizationController(request, response, MoneymoremoreId, AuthorizeTypeOpen, AuthorizeTypeClose, Remark1, null, null);
		return null;
	}
	
	@RequestMapping("/test1")
	public void test1(HttpServletRequest request,HttpServletResponse response){
		String  MoneymoremoreId = request.getParameter("MoneymoremoreId");
		String AuthorizeTypeOpen = request.getParameter("AuthorizeTypeOpen");
		String AuthorizeTypeClose = request.getParameter("AuthorizeTypeClose");
		String Remark1 = request.getParameter("Remark1");
		doRepayMentAuthorizationController(request, response, MoneymoremoreId, AuthorizeTypeOpen, AuthorizeTypeClose, Remark1, null, null);
 	}
	
	@RequestMapping("/test2")
	public void test2(HttpServletRequest request,HttpServletResponse response){
 		try {
			request.getRequestDispatcher("/authorizationTest.jsp").forward(request, response);
		} catch (ServletException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: doRepayMentAuthorizationController 
	* @Description: TODO(乾多多 授权开通封装方法) 
	* @param @param request  
	* @param @param response
	* @param @param MoneymoremoreId   乾多多用户标识 必填
	* @param @param AuthorizeTypeOpen	 开启授权类型 1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串  选填 
	* @param @param AuthorizeTypeClose  关闭授权类型 1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串  选填
	* @param @param Remark1  自定义备注 (请用此字段 传 用户baseID) 必填
	* @param @param Remark2 自定义备注 选填
	* @param @param Remark3 自定义备注 选填
	* @param @return  参数说明 
	* @return String    返回类型 
	* @author cjm
	* @throws
	 */
	public static  String doRepayMentAuthorizationController(HttpServletRequest request,HttpServletResponse response,
			String MoneymoremoreId,
			String AuthorizeTypeOpen,
			String AuthorizeTypeClose,
			String Remark1,
			String Remark2,
			String Remark3){
//		String MoneymoremoreId  = ""; // 用户乾多多标识 必填
//		String AuthorizeTypeOpen  = ""; // 开启授权类型 1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串  选填
//		String AuthorizeTypeClose  = ""; // 关闭授权类型 1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串  选填
//		String Remark1  = ""; // 自定义备注 选填
//		String Remark2  = ""; // 自定义备注 选填
//		String Remark3  = ""; // 自定义备注 选填
 		Assert.notNull(MoneymoremoreId, "'授权乾多多用户标识' 不能为空");
 		Assert.notNull(Remark1, "'用户 baseID 不能为null'");
 		
		String RandomTimeStamp  = ""; // 随机时间戳 选填
		String PlatformMoneymoremore  = MMMParams.PlatformMoneymoremore; // 平台乾多多标识  必填
		String ReturnURL  = StringUtil.getBasePath(request) + "/moneymoremore/authorization/authorizationCallBackUrl.action"; // 页面返回网址 必填 
		String NotifyURL  = StringUtil.getBasePath(request) + "/moneymoremore/authorization/authorizationCallBack.action"; // 后台通知网
		String SignInfo  = ""; // 签名信息
		
		/**
		 * MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + 
		 * AuthorizeTypeClose + RandomTimeStamp + Remark1 + 
		 * Remark2 + Remark3 + ReturnURL + NotifyURL
  		 */
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(MoneymoremoreId));
		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
		buffer.append(StringUtils.trimToEmpty(AuthorizeTypeOpen));
		buffer.append(StringUtils.trimToEmpty(AuthorizeTypeClose));
		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
		buffer.append(StringUtils.trimToEmpty(Remark1));
		buffer.append(StringUtils.trimToEmpty(Remark2));
		buffer.append(StringUtils.trimToEmpty(Remark3));
		buffer.append(StringUtils.trimToEmpty(ReturnURL));
		buffer.append(StringUtils.trimToEmpty(NotifyURL));
		String str = buffer.toString();
		
		RsaHelper helper = RsaHelper.getInstance();
 		SignInfo = helper.signData(str, MMMParams.privateString);
 		
 		request.setAttribute("repayMentAuthorizeAction", MMMParams.AUTHORIZATIONACTION);
 		request.setAttribute("MoneymoremoreId", MoneymoremoreId);
 		request.setAttribute("PlatformMoneymoremore", PlatformMoneymoremore);
 		request.setAttribute("AuthorizeTypeOpen", AuthorizeTypeOpen);
 		request.setAttribute("AuthorizeTypeClose", AuthorizeTypeClose);
 		request.setAttribute("RandomTimeStamp", RandomTimeStamp);
 		request.setAttribute("Remark1", Remark1);
 		request.setAttribute("Remark2", Remark2);
 		request.setAttribute("Remark3", Remark3);
 		request.setAttribute("ReturnURL", ReturnURL);
 		request.setAttribute("NotifyURL", NotifyURL);
 		request.setAttribute("SignInfo", SignInfo);
   		try {
			request.getRequestDispatcher("/WEB-INF/MMMPages/authorize/repayMentAuthorize.jsp").forward(request, response);
		} catch (ServletException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @Title: authorizationCallBackUrl 
	* @Description: TODO(乾多多 授权接口 页面回调地址) 
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value = "/authorizationCallBackUrl",method = {RequestMethod.GET,RequestMethod.POST})
	public void authorizationCallBackUrl(HttpServletRequest request,HttpServletResponse response){
		String  MoneymoremoreId = request.getParameter("MoneymoremoreId"); //用户乾多多标识
		String  PlatformMoneymoremore = request.getParameter("PlatformMoneymoremore"); //平台乾多多标识
		String  AuthorizeTypeOpen = request.getParameter("AuthorizeTypeOpen"); //开启授权类型  1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串
		String  AuthorizeTypeClose = request.getParameter("AuthorizeTypeClose"); //1.投标2.还款3.二次分配审核将所有数字用英文逗号(,)连成一个字符串
		String  AuthorizeType = request.getParameter("AuthorizeType"); //1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串
		String  RandomTimeStamp = request.getParameter("RandomTimeStamp"); //随机时间戳
		String  Remark1 = request.getParameter("Remark1"); //自定义备注
		String  Remark2 = request.getParameter("Remark2"); //自定义备注
		String  Remark3 = request.getParameter("Remark3"); //自定义备注
		String  ResultCode = request.getParameter("ResultCode"); //返回码
		String  Message = request.getParameter("Message"); //用户乾多多标识
		String  ReturnTimes = request.getParameter("ReturnTimes"); //返回次数
		String  SignInfo = request.getParameter("SignInfo"); //签名信息
  		/**
 		 * MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + 
 		 * AuthorizeTypeClose + AuthorizeType + RandomTimeStamp + 
 		 * Remark1 + Remark2 + Remark3 + ResultCode
 		 */
 		
 		StringBuffer buffer = new StringBuffer();
 		buffer.append(StringUtils.trimToEmpty(MoneymoremoreId));
 		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
 		buffer.append(StringUtils.trimToEmpty(AuthorizeTypeOpen));
 		buffer.append(StringUtils.trimToEmpty(AuthorizeTypeClose));
 		buffer.append(StringUtils.trimToEmpty(AuthorizeType));
 		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
 		buffer.append(StringUtils.trimToEmpty(Remark1));
 		buffer.append(StringUtils.trimToEmpty(Remark2));
 		buffer.append(StringUtils.trimToEmpty(Remark3));
 		buffer.append(StringUtils.trimToEmpty(ResultCode));
 		String str = buffer.toString();
 		
 		RsaHelper helper = RsaHelper.getInstance();
 		boolean flag = helper.verifySignature(SignInfo, str, MMMParams.publicString);
 		
 		if(flag){
   			 if(ResultCode.equals("88")){
   				 StringBuffer buffer2 = new StringBuffer();
   				if(StringUtil.isNotEmpty(AuthorizeTypeOpen)){
 					String[] str2 = AuthorizeTypeOpen.split(",");
					for(int i = 0 ; i < str2.length ;i++){
						if(str2[i].equals("1")){
							buffer2.append("投标授权开启").append(",");
						}else if(str2[i].equals("2")){
							buffer2.append("还款授权开启").append(",");
						}else if(str2[i].equals("3")){
							buffer2.append("二次分配审核授权开启").append(",");
						}
					}
				}
   				
   				if(StringUtil.isNotEmpty(AuthorizeTypeClose)){
 					String[] str2 = AuthorizeTypeClose.split(",");
					for(int i = 0 ; i < str2.length ;i++){
						if(str2[i].equals("1")){
							buffer2.append("投标授权关闭").append(",");
						}else if(str2[i].equals("2")){
							buffer2.append("还款授权关闭").append(",");
						}else if(str2[i].equals("3")){
							buffer2.append("二次分配审核授权关闭").append(",");
						}
					}
				}
   				
   				buffer2.delete(buffer2.length() - 1, buffer2.length());
   				buffer2.append("操作成功!");
   				request.setAttribute("Message", buffer2.toString());
  				try {
					request.getRequestDispatcher("/WEB-INF/MMMPages/authorize/success.jsp").forward(request, response);
				} catch (ServletException e) {
 					e.printStackTrace();
				} catch (IOException e) {
 					e.printStackTrace();
				}
  			 }else{
  				 request.setAttribute("Message", Message);
  				try {
					request.getRequestDispatcher("/WEB-INF/MMMPages/authorize/success.jsp").forward(request, response);
				} catch (ServletException e) {
 					e.printStackTrace();
				} catch (IOException e) {
 					e.printStackTrace();
				}
  			 }
			
 		}
	}

	/**
	 * 
	* @Title: authorizationCallBack 
	* @Description: TODO(乾多多 授权接口 后台回调地址) 
	* @param @param request
	* @param @param response  参数说明 
	* @return void    返回类型 
	* @author cjm
	* @throws
	 */
	@RequestMapping(value = "/authorizationCallBack",method = {RequestMethod.GET,RequestMethod.POST})
	public void authorizationCallBack(HttpServletRequest request,HttpServletResponse response){
		String  MoneymoremoreId = request.getParameter("MoneymoremoreId"); //用户乾多多标识
		String  PlatformMoneymoremore = request.getParameter("PlatformMoneymoremore"); //平台乾多多标识
		String  AuthorizeTypeOpen = request.getParameter("AuthorizeTypeOpen"); //开启授权类型  1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串
		String  AuthorizeTypeClose = request.getParameter("AuthorizeTypeClose"); //1.投标2.还款3.二次分配审核将所有数字用英文逗号(,)连成一个字符串
		String  AuthorizeType = request.getParameter("AuthorizeType"); //1.投标2.还款3.二次分配审核 将所有数字用英文逗号(,)连成一个字符串
		String  RandomTimeStamp = request.getParameter("RandomTimeStamp"); //随机时间戳
		String  Remark1 = request.getParameter("Remark1"); //自定义备注
		String  Remark2 = request.getParameter("Remark2"); //自定义备注
		String  Remark3 = request.getParameter("Remark3"); //自定义备注
		String  ResultCode = request.getParameter("ResultCode"); //返回码
		String  Message = request.getParameter("Message"); //用户乾多多标识
		String  ReturnTimes = request.getParameter("ReturnTimes"); //返回次数
		String  SignInfo = request.getParameter("SignInfo"); //签名信息
   		
 		/**
 		 * MoneymoremoreId + PlatformMoneymoremore + AuthorizeTypeOpen + 
 		 * AuthorizeTypeClose + AuthorizeType + RandomTimeStamp + 
 		 * Remark1 + Remark2 + Remark3 + ResultCode
 		 */
 		
 		StringBuffer buffer = new StringBuffer();
 		buffer.append(StringUtils.trimToEmpty(MoneymoremoreId));
 		buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore));
 		buffer.append(StringUtils.trimToEmpty(AuthorizeTypeOpen));
 		buffer.append(StringUtils.trimToEmpty(AuthorizeTypeClose));
 		buffer.append(StringUtils.trimToEmpty(AuthorizeType));
 		buffer.append(StringUtils.trimToEmpty(RandomTimeStamp));
 		buffer.append(StringUtils.trimToEmpty(Remark1));
 		buffer.append(StringUtils.trimToEmpty(Remark2));
 		buffer.append(StringUtils.trimToEmpty(Remark3));
 		buffer.append(StringUtils.trimToEmpty(ResultCode));
 		String str = buffer.toString();
 		
 		RsaHelper helper = RsaHelper.getInstance();
 		boolean flag = helper.verifySignature(SignInfo, str, MMMParams.publicString);
 		
 		if(flag){
  			if(ResultCode.equals("88")){
  				UserAuthorization userAuthorization = userAuthorizationServiceI.findUserAuthorizationByBaseId(new BigDecimal(Remark1));
  				StringBuffer buffer2 = new StringBuffer("000") ;
   				if(StringUtil.isNotEmpty(AuthorizeTypeOpen)){
 					String[] str2 = AuthorizeTypeOpen.split(",");
					for(int i = 0 ; i < str2.length ;i++){
						buffer2.replace(Integer.valueOf(str2[i])-1, Integer.valueOf(str2[i]), "1");
					}
				}
   				
   				if(StringUtil.isNotEmpty(AuthorizeTypeClose)){
 					String[] str2 = AuthorizeTypeClose.split(",");
					for(int i = 0 ; i < str2.length ;i++){
						buffer2.replace(Integer.valueOf(str2[i])-1, Integer.valueOf(str2[i]), "0");
					}
				}
   				
  				if(userAuthorization == null){
     				UserAuthorization  userAuthorization2 = new UserAuthorization();
	  				userAuthorization2.setBaseid(new BigDecimal(Remark1)); ///*账户基本信息表ID*/
	  				userAuthorization2.setMoneymoremoreid(MoneymoremoreId); // /*用户的乾多多标识*/
	  				userAuthorization2.setMercustid(PlatformMoneymoremore); //    /*平台乾多多标识*/
	  				userAuthorization2.setAuthorizetype("123");/*授权类型 123 表示 1.投标 2.还款 3.二次分配审核*/ 
	  				userAuthorization2.setAuthorizestatus(buffer2.toString());/*授权状态 011含义 0关闭1开通*/
 	  				userAuthorization2.setRemark("用户授权添加");
	  				userAuthorization2.setAddtime(new Date());//添加时间
	  				userAuthorizationServiceI.insertSelective(userAuthorization2);
 				}else{
 	  				userAuthorization.setAuthorizetype("123");/*授权类型 123表示 1.投标 2.还款 3.二次分配审核*/ 
	  				userAuthorization.setAuthorizestatus(buffer2.toString());/*授权状态 011含义 0关闭1开通*/
 	  				userAuthorization.setRemark("用户授权修改");
 	  				userAuthorization.setUpdatetime(new Date());//修改授权时间
 	  				userAuthorizationServiceI.updateById(userAuthorization);
 				}
  			}
  			 
  			response.setContentType("text/plain;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			try {
				response.getWriter().write("SUCCESS");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
 				e.printStackTrace();
			}
			
 		}
	}
	
}
