package com.ptpl.web.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
/**
 * 
* @ClassName: SendMailTempalte 
* @Package com.ptpl.web.util 
* @Description: TODO(邮件发送模板工具类) 
* @author chenjiaming
* @date 2016年7月29日 下午3:38:18 
* @version V1.0
 */
public class SendMailTempalte {
	
	/**
	 * 
	* @Title: sendEamilCode 
	* @Description: TODO(发送邮箱验证码 ) 
	* @param @param email 发送的目标邮箱账号
	* @param @param emailCode 邮箱随机验证码
	* @param @return  参数说明 
	* @return boolean 返回类型  
	* @author chenjiaming
	* @throws
	 */
	public static boolean sendEamilCode(String email, String emailCode){
			String subjectText = "来着莫邪网的邮件";
			String from_email = "philip_fu@95epay.com";
			String password = "ABC123abc";
			String smtp_server = "smtp.95epay.com";
			String content ="<div>"+
						"	   <table width='760' border='0' cellspacing='0' cellpadding='0'>  "+
				   	 	"		<tbody>  "+
						"   	 		<tr>     "+
						"   	 			<td style='font-size:12px'>   "+
						"   	 				<div style='width:700px;height:30px;line-height:22px;padding:25px 30px 0 30px;font-size:14px;border-bottom:dashed 1px #5b5b5b'>尊敬的莫邪网会员，您好！"+
						"   	 				</div>       "+
						"   	 				<div style='width:700px;line-height:22px;padding:10px 30px 0 30px'>  "+
						"   	 					<p>您于<span style='border-bottom:1px dashed #ccc;' t='5' times='"+StringUtil.formatDate(new Date(),"hh:mm")+"'>"+StringUtil.formatDate(new Date(), "yyyy/MM/dd hh:mm:ss")+"</span> "+
						"   	 					 提交了邮箱验证码申请。"+
						"   	 					</p>验证码<span style='color:red;'>"+
						"   	 					<span style='border-bottom:1px dashed #ccc;z-index:1' t='7' onclick='return false;' data='"+emailCode+"'>"+emailCode+"</span>"+
						"   	 					</span>          "+
						"   	 					<p>请在莫邪网提示的输入框内输入验证码。</p>      "+
						"  	 				</div>     "+
						"   	 			</td>   "+
						"   	 		</tr>  "+
						"   	 	</tbody>"+
					   	"  </table>"+
					   	" <p>这是一封系统邮件，请勿直接回复。</p>"+
					   	" <p><a href='http://www.moye.com' target='_blank'>莫邪网</a></p>"+
					   	"</div>";
			//调用邮箱接口发送邮箱验证码
		   return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
		}
	/**
	 * 
	* @Title: sendEamilForEmailCode 
	* @Description: TODO(发送绑定邮箱验证码 ) 
	* @param @param email 发送的目标邮箱账号
	* @param @param emailCode 邮箱随机验证码
	* @param @return  参数说明 
	* @return boolean 返回类型  
	* @author chenjiaming
	* @throws
	 */
	public static boolean sendEamilForEmailCode(String email, String emailCode){
			String subjectText = "来着莫邪网的邮件";
			String from_email = "philip_fu@95epay.com";
			String password = "ABC123abc";
			String smtp_server = "smtp.95epay.com";
			String content ="<div>"+
						"	   <table width='760' border='0' cellspacing='0' cellpadding='0'>  "+
				   	 	"		<tbody>  "+
						"   	 		<tr>     "+
						"   	 			<td style='font-size:12px'>   "+
						"   	 				<div style='width:700px;height:30px;line-height:22px;padding:25px 30px 0 30px;font-size:14px;border-bottom:dashed 1px #5b5b5b'>尊敬的莫邪网会员，您好！"+
						"   	 				</div>       "+
						"   	 				<div style='width:700px;line-height:22px;padding:10px 30px 0 30px'>  "+
						"   	 					<p>您于<span style='border-bottom:1px dashed #ccc;' t='5' times='"+StringUtil.formatDate(new Date(),"hh:mm")+"'>"+StringUtil.formatDate(new Date(), "yyyy/MM/dd hh:mm:ss")+"</span> "+
						"   	 					 提交了邮箱绑定申请。"+
						"   	 					</p>验证码<span style='color:red;'>"+
						"   	 					<span style='border-bottom:1px dashed #ccc;z-index:1' t='7' onclick='return false;' data='"+emailCode+"'>"+emailCode+"</span>"+
						"   	 					</span>          "+
						"   	 					<p>请在莫邪网提示的输入框内输入验证码完成邮箱验证。</p>      "+
						"  	 				</div>     "+
						"   	 			</td>   "+
						"   	 		</tr>  "+
						"   	 	</tbody>"+
					   	"  </table>"+
					   	" <p>这是一封系统邮件，请勿直接回复。</p>"+
					   	" <p><a href='http://www.moye.com' target='_blank'>莫邪网</a></p>"+
					   	"</div>";
			//调用邮箱接口发送邮箱验证码
		   return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
		}
	
	 	/**
	 	 * 
	 	* @Title: sendEamilForEmailCheck 
	 	* @Description: TODO(发送邮件链接 重新填写邮箱账号) 
	 	* @param @param email 发送的邮箱账号
	 	* @param @param emailCode 发送的邮箱随机码
	 	* @param @param toKCode id标识符
	 	* @param @param method 方法 名
	 	* @param @param username 用户名
	 	* @param @param request 获取项目根目录
	 	* @param @return  参数说明 
	 	* @return boolean    返回类型 
	 	* @author chenjiaming
	 	* @throws
	 	 */
		public static boolean sendEamilForEmailCheck(String email, String emailCode,String toKCode,String method,String username,HttpServletRequest request){
				String basePath =StringUtil.getBasePath(request);
				String subjectText = "来着莫邪网的邮件";
				String from_email = "philip_fu@95epay.com";
				String password = "ABC123abc";
				String smtp_server = "smtp.95epay.com";
				String content ="<table width='760' border='0' cellspacing='0' cellpadding='0'>"+
								"	<tbody>  "+
								"		<tr>     "+
								"			<td style='font-size:12px'> "+
								"				<div style='width:700px;height:30px;line-height:22px;padding:25px 30px 0 30px;font-size:14px;border-bottom:dashed 1px #5b5b5b'>"+
								"					<span style='border-bottom:1px dashed #ccc;z-index:1' t='7' onclick='return false;' data='"+username+"'>"+
								"					"+username+""+
 								"					</span>"+
								"					，您好！"+
								"					</div><div style='width:700px;line-height:22px;padding:10px 30px 0 30px'> "+
								"					<p>您于"+
								"						<span style='border-bottom:1px dashed #ccc;' t='5' times='"+StringUtil.formatDate(new Date(), "hh:ss")+"'>"+
								"						"+StringUtil.formatDate(new Date(), "yyyy/MM/dd hh:mm:ss")+""+
								"						</span> "+
								"						 提交了邮箱重置，请点击下面的按钮，进入设置新邮箱页面，完成新邮箱的设置。"+
								"					</p>  "+
								"					<table>"+
								"						<tbody> "+
								"							<tr>  "+
								"								<td align='left'>"+
								"								<br>"+
								"								<a href='"+basePath+"/user/securitycenter/UpdateUserEmailSendCheck.action?method="+method+"&amp;code="+emailCode+"&amp;token="+toKCode+"' target='_blank'>"+
								"									<img src='javascript:;' title='设置新邮件' border='0'>"+
								"								</a>"+
								"								</td>"+
								"							</tr>"+
								"						</tbody>"+
								"					</table> "+
								"					<br>    "+
								"					<p></p> "+
								"					<p>您也可以点击下面的链接，进行新邮箱的设置:</p> "+
								"					<p style='font-family:Arial, Helvetica, sans-serif'>"+
								"						<a href='"+basePath+"/user/securitycenter/UpdateUserEmailSendCheck.action?method="+method+"&amp;code="+emailCode+"&amp;token="+toKCode+"' style='text-decoration:underline' target='_blank'>"+basePath+"/user/securitycenter/UpdateUserEmailSendCheck.action?method="+method+"&amp;code="+emailCode+"&amp;token="+toKCode+""+
								"						</a>"+
								"					</p> "+
								"					<p></p>  "+
								"					<p>如果您不能点击上面链接，还可以将以下链接复制到浏览器地址栏中访问：</p>	"+
								"					<p style='font-family:Arial, Helvetica, sans-serif'>"+
								"					<a href='"+basePath+"/user/securitycenter/UpdateUserEmailSendCheck.action?method="+method+"&amp;code="+emailCode+"&amp;token="+toKCode+"' target='_blank'>"+
								"					"+basePath+"/user/securitycenter/UpdateUserEmailSendCheck.action?method="+method+"&amp;code="+emailCode+"&amp;token="+toKCode+""+
								"					</a>"+
								"					</p>   "+
								"					<p></p> "+
								"					<div style='width:700px;line-height:22px;padding:10px 30px 0 30px'></div>"+
								"				</div>"+
								"			</td> "+
								"		</tr>"+
								"	</tbody>"+
								"</table>"+
								"<p>这是一封系统邮件，请勿直接回复。</p>"+
								"<p><a href='http://www.moye.com' target='_blank'>莫邪网</a></p>";
				//调用邮箱接口发送邮件
			   return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
		}
  		 
		 /**
		  * 
		 * @Title: sendEamilForEmailReger 
		 * @Description: TODO(发送重置验证链接 最后一步) 
		 * @param @param email 发送的目标邮箱账号
		 * @param @param emailCode 随机码
		 * @param @param toKCode 标识符
		 * @param @param method
		 * @param @param username 用户名
		 * @param @param request
		 * @param @return  参数说明 
		 * @return boolean    返回类型 
		 * @author chenjiaming
		 * @throws
		  */
		public static boolean sendEamilForEmailReger(String email, String emailCode,String toKCode,String method,String username,HttpServletRequest request){
				String basePath =StringUtil.getBasePath(request);
				String subjectText = "来着莫邪网的邮件";
				String from_email = "philip_fu@95epay.com";
				String password = "ABC123abc";
				String smtp_server = "smtp.95epay.com";
				String content ="<table width='760' border='0' cellspacing='0' cellpadding='0'>"+
		 				" <tbody>  "+
		 				" <tr>  "+
		 				"	 <td style='font-size:12px'>  "+
		 				"		 <div style='width:700px;height:30px;line-height:22px;padding:25px 30px 0 30px;font-size:14px;border-bottom:dashed 1px #5b5b5b'>"+
		 				"			 <span style='border-bottom:1px dashed #ccc;z-index:1' t='7' onclick='return false;' data='"+username+"'>"+
		 				"			 "+username+""+
		 				"			 </span>"+
 		 				"			 ，您好！"+
		 				"			 </div><div style='width:700px;line-height:22px;padding:10px 30px 0 30px'>  "+
		 				"			 <p>"+
		 				"				 <span style='border-bottom:1px dashed #ccc;' t='5' times='"+StringUtil.formatDate(new Date(), "hh:ss")+"'>"+
		 				"				 "+StringUtil.formatDate(new Date(), "yyyy/MM/dd hh:mm:ss")+""+
		 				"				 </span> "+
		 				"				 提交了重置邮箱申请，请点击下面的按钮，即可完成验证。"+
		 				"			 </p>"+
		 				"			 <table>"+
		 				"				 <tbody>"+
		 				"					 <tr> "+
		 				"						 <td align='left'>"+
		 				"							 <br>"+
		 				"							 <a href='"+basePath+"/user/securitycenter/UpdateUserEmailSendResetEamilCheck.action?method="+method+"&amp;token="+toKCode+"&amp;temp="+emailCode+"&amp;email="+email+"' target='_blank'>"+
		 				"								<img src='javascript:;' title='验证邮箱' border='0'>"+
		 				"							 </a>"+
		 				"						 </td>"+
		 				"					 </tr>"+
		 				"				 </tbody> "+
		 				"			 </table>"+
		 				"			 <br> "+
		 				"			 <p>"+
		 				"			 </p>"+
		 				"			 <p>"+
		 				"			 为保障您的帐户安全，请在24小时内点击该链接，您也可以将链接复制到浏览器地址栏访问:"+
		 				"			 </p>"+
		 				"			 <p style='font-family:Arial, Helvetica, sans-serif'>"+
		 				"				 <a href='"+basePath+"/user/securitycenter/UpdateUserEmailSendResetEamilCheck.action?method="+method+"&amp;token="+toKCode+"&amp;temp="+emailCode+"&amp;email="+email+"' style='text-decoration:underline' target='_blank'>"+basePath+"/user/securitycenter/UpdateUserEmailSendResetEamilCheck.action?method="+method+"&amp;token="+toKCode+"&amp;temp="+emailCode+"&amp;email="+email+""+
		 				"				 </a>"+
		 				"			 </p>"+
		 				"			 <div style='width:700px;line-height:22px;padding:10px 30px 0 30px'>"+
		 				"			 </div>"+
		 				"		 </div>"+
		 				"	 </td> "+
		 				" </tr>"+
		 			 "</tbody>"+
		 		" </table>"+
		 		"<p>这是一封系统邮件，请勿直接回复。</p>"+
		 		"<p><a href='http://www.moye.com' target='_blank'>莫邪网</a></p>";
				//调用邮箱接口发送邮件
			   return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
		}
		
		 /**
		  * 
		 * @Title: sendEamilForEmailReger 
		 * @Description: TODO(发送邮箱绑定验证链接 最后一步) 
		 * @param @param email 发送的目标邮箱账号
		 * @param @param emailCode 随机码
		 * @param @param toKCode 标识符
		 * @param @param method
		 * @param @param username 用户名
		 * @param @param request
		 * @param @return  参数说明 
		 * @return boolean    返回类型 
		 * @author chenjiaming
		 * @throws
		  */
		public static boolean sendEamilForEmailbind(String email, String emailCode,String toKCode,String method,String username,HttpServletRequest request){
				String basePath =StringUtil.getBasePath(request);
 				String subjectText = "来着莫邪网的邮件";
				String from_email = "philip_fu@95epay.com";
				String password = "ABC123abc";
				String smtp_server = "smtp.95epay.com";
				String content ="<table width='760' border='0' cellspacing='0' cellpadding='0'>"+
		 				" <tbody>  "+
		 				" <tr>  "+
		 				"	 <td style='font-size:12px'>  "+
		 				"		 <div style='width:700px;height:30px;line-height:22px;padding:25px 30px 0 30px;font-size:14px;border-bottom:dashed 1px #5b5b5b'>"+
		 				"			 <span style='border-bottom:1px dashed #ccc;z-index:1' t='7' onclick='return false;' data='"+username+"'>"+
		 				"			 "+username+""+
		 				"			 </span>"+
		 				"			 ，您好！"+
		 				"			 </div><div style='width:700px;line-height:22px;padding:10px 30px 0 30px'>  "+
		 				"			 <p>"+
		 				"				 <span style='border-bottom:1px dashed #ccc;' t='5' times='"+StringUtil.formatDate(new Date(), "hh:ss")+"'>"+
		 				"				 "+StringUtil.formatDate(new Date(), "yyyy/MM/dd hh:mm:ss")+""+
		 				"				 </span> "+
		 				"				 提交了邮箱绑定申请，请点击下面的按钮，即可完成验证。"+
		 				"			 </p>"+
		 				"			 <table>"+
		 				"				 <tbody>"+
		 				"					 <tr> "+
		 				"						 <td align='left'>"+
		 				"							 <br>"+
		 				"							 <a href='"+basePath+"/user/securitycenter/BindUserEmailSend.action?method="+method+"&amp;token="+toKCode+"&amp;temp="+emailCode+"&amp;email="+email+"' target='_blank'>"+
		 				"								<img src='javascript:;' title='验证邮箱' border='0'>"+
		 				"							 </a>"+
		 				"						 </td>"+
		 				"					 </tr>"+
		 				"				 </tbody> "+
		 				"			 </table>"+
		 				"			 <br> "+
		 				"			 <p>"+
		 				"			 </p>"+
		 				"			 <p>"+
		 				"			 为保障您的帐户安全，请在24小时内点击该链接，您也可以将链接复制到浏览器地址栏访问:"+
		 				"			 </p>"+
		 				"			 <p style='font-family:Arial, Helvetica, sans-serif'>"+
		 				"				 <a href='"+basePath+"/user/securitycenter/BindUserEmailSend.action?method="+method+"&amp;token="+toKCode+"&amp;temp="+emailCode+"&amp;email="+email+"' style='text-decoration:underline' target='_blank'>"+basePath+"/user/securitycenter/BindUserEmailSend.action?method="+method+"&amp;token="+toKCode+"&amp;temp="+emailCode+"&amp;email="+email+""+
		 				"				 </a>"+
		 				"			 </p>"+
		 				"			 <div style='width:700px;line-height:22px;padding:10px 30px 0 30px'>"+
		 				"			 </div>"+
		 				"		 </div>"+
		 				"	 </td> "+
		 				" </tr>"+
		 			 "</tbody>"+
		 		" </table>"+
		 		"<p>这是一封系统邮件，请勿直接回复。</p>"+
		 		"<p><a href='http://www.moye.com' target='_blank'>莫邪网</a></p>";
				//调用邮箱接口发送邮件
			   return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
		}
		
		
		
		/**
		 * 
		* @Title: sendEamilForFindPwd 
		* @Description: TODO(发送邮件链接（重置密码）) 
		* @param @param email  要发送的email 地址
		* @param @param emailCode 加密后的email
		* @param @param toKCode  加密后的uid
		* @param @param code   时间
		* @param @param username  用户名
		* @param @param from_email  发送的邮箱账号  
		* @param @param password   密码
		* @param @param smtp_server 服务器
		* @param @param request  请求
		* @param @return    设定文件 
		* @return boolean    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static boolean sendEamilForFindPwd(String email, String emailCode,String toKCode,String code,String username,String from_email,String password,String smtp_server,HttpServletRequest request){
			String subjectText = "来着莫邪网的邮件";
 			String basePath =StringUtil.getBasePath(request);
			String content ="<div style='width:640px; background:#fff; border:solid 1px #efefef; margin:0 auto; padding:35px 0 35px 0'>"+
					"	<table width='560' border='0' align='center' cellpadding='0' cellspacing='0' style=' margin:0 auto; margin-left:30px; margin-right:30px;'>"+
					"	    <tbody><tr>"+
					"		<td><img src=''></td>"+
					"	    </tr>"+
					"	    <tr>"+
					"	      <td>"+
					"	      <h3 style='font-weight:normal; font-size:18px'>您好！亲爱的<span style='font-weight:bold; margin-left:5px;'>"+username+"</span></h3>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>干将网贷找回登录密码通知:</p>"+
					"	      <p style='color:#666; font-size:14px'>请在24小时内点击下面链接找回您的登录密码： "+
					"	      <a href='"+basePath+"/findpwd/emailPwdByEmailLink.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+"' target='_blank' style='display:block; margin-top:10px; color:#2980b9; line-height:24px; text-decoration:none;word-break:break-all; width:575px;'>"+
					"	"+basePath+"/findpwd/emailPwdByEmailLink.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+" </a>"+
					"	      </p>"+
					"	      <p style='margin:0 0 5px 0; padding:0 0 3px 0;'>"+
					"	<a href='"+basePath+"/findpwd/emailPwdByEmailLink.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+"' style='display:inline-block; width:105px; text-align:center; background:#2980b9; color:#fff;  font-size:16px; text-decoration:none; line-height:34px; padding:0;border-radius:5px;' target='_blank'>查看详情</a></p>"+
					"	      <p style='margin:10px 0 5px 0; padding:3px 0;color:#666; font-size:14px'>如果上面不是链接形式，请将地址复制到您的浏览器（例如：IE）的地址栏再访问。</p>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>如果链接已经失效，请重新到干将网贷网站找回您的密码！谢谢您的合作<br>更多关于你我贷信息，请访问干将网贷网站： <a href='"+basePath+"' target='_blank'>"+basePath+"</a></p>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>感谢对你我贷的支持。</p>"+
					"	      <p style='text-align:center'><img src=''></p>"+
					"	      </td>"+
					"	    </tr>"+
					"	    </tbody>"+
					"	</table>"+
					"</div>";
			return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
		}
		
		
		
		
		/**
		 * 
		* @Title: sendEamilForBindEmail 
		* @Description: TODO(发送邮件链接（邮箱验证/邮箱绑定）) 
		* @param @param email  要发送的email 地址
		* @param @param emailCode 加密后的email
		* @param @param toKCode  加密后的uid
		* @param @param code   时间
		* @param @param username  用户名
		* @param @param from_email  发送的邮箱账号  
		* @param @param password   密码
		* @param @param smtp_server 服务器
		* @param @param request  请求
		* @param @return    设定文件 
		* @return boolean    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static boolean sendEamilForBindEmail(String email, String emailCode,String toKCode,String code,String username,String from_email,String password,String smtp_server,HttpServletRequest request){
			String subjectText = "来着干将网贷邮件";
 			String basePath =StringUtil.getBasePath(request);
			String content ="<div style='width:640px; background:#fff; border:solid 1px #efefef; margin:0 auto; padding:35px 0 35px 0'>"+
					"	<table width='560' border='0' align='center' cellpadding='0' cellspacing='0' style=' margin:0 auto; margin-left:30px; margin-right:30px;'>"+
					"	    <tbody><tr>"+
					"		<td><img src=''></td>"+
					"	    </tr>"+
					"	    <tr>"+
					"	      <td>"+
					"	      <h3 style='font-weight:normal; font-size:18px'>您好！亲爱的<span style='font-weight:bold; margin-left:5px;'>"+username+"</span></h3>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>干将网贷邮箱验证通知:</p>"+
					"	      <p style='color:#666; font-size:14px'>请在24小时内点击下面链接完全邮箱验证： "+
					"	      <a href='"+basePath+"/user/securitycenter/bindEmail.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+"' target='_blank' style='display:block; margin-top:10px; color:#2980b9; line-height:24px; text-decoration:none;word-break:break-all; width:575px;'>"+
					"	"+basePath+"/user/securitycenter/bindEmail.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+" </a>"+
					"	      </p>"+
					"	      <p style='margin:0 0 5px 0; padding:0 0 3px 0;'>"+
					"			<a href='"+basePath+"/user/securitycenter/bindEmail.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+"' style='display:inline-block; width:105px; text-align:center; background:#2980b9; color:#fff;  font-size:16px; text-decoration:none; line-height:34px; padding:0;border-radius:5px;' target='_blank'>查看详情</a></p>"+
					"	      <p style='margin:10px 0 5px 0; padding:3px 0;color:#666; font-size:14px'>如果上面不是链接形式，请将地址复制到您的浏览器（例如：IE）的地址栏再访问。</p>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>如果链接已经失效，请重新到干将网贷网站进行邮箱验证！谢谢您的合作<br>更多关于干将网贷信息，请访问干将网贷官方网站： <a href='"+basePath+"' target='_blank'>"+basePath+"</a></p>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>感谢对干将网贷的支持。</p>"+
					"	      <p style='text-align:center'><img src=''></p>"+
					"	      </td>"+
					"	    </tr>"+
					"	    </tbody>"+
					"	</table>"+
					"</div>";
			return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
 		}
		
		
		/**
		 * 
		* @Title: sendEamilForUpdateEmail 
		* @Description: TODO(发送邮件链接（邮箱验证/邮箱绑定）) 
		* @param @param email  要发送的email 地址
		* @param @param emailCode 加密后的email
		* @param @param toKCode  加密后的uid
		* @param @param code   时间
		* @param @param username  用户名
		* @param @param from_email  发送的邮箱账号  
		* @param @param password   密码
		* @param @param smtp_server 服务器
		* @param @param request  请求
		* @param @return    设定文件 
		* @return boolean    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static boolean sendEamilForUpdateEmail(String email, String emailCode,String toKCode,String code,String username,String from_email,String password,String smtp_server,HttpServletRequest request){
			String subjectText = "来着干将网贷邮件";
 			String basePath = StringUtil.getBasePath(request);
 			String content ="<div style='width:640px; background:#fff; border:solid 1px #efefef; margin:0 auto; padding:35px 0 35px 0'>"+
					"	<table width='560' border='0' align='center' cellpadding='0' cellspacing='0' style=' margin:0 auto; margin-left:30px; margin-right:30px;'>"+
					"	    <tbody><tr>"+
					"		<td><img src=''></td>"+
					"	    </tr>"+
					"	    <tr>"+
					"	      <td>"+
					"	      <h3 style='font-weight:normal; font-size:18px'>您好！亲爱的<span style='font-weight:bold; margin-left:5px;'>"+username+"</span></h3>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>干将网贷邮箱验证通知:</p>"+
					"	      <p style='color:#666; font-size:14px'>请在24小时内点击下面链接完全邮箱重置： "+
					"	      <a href='"+basePath+"/user/securitycenter/updateEmail.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+"' target='_blank' style='display:block; margin-top:10px; color:#2980b9; line-height:24px; text-decoration:none;word-break:break-all; width:575px;'>"+
					"	"+basePath+"/user/securitycenter/updateEmail.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+" </a>"+
					"	      </p>"+
					"	      <p style='margin:0 0 5px 0; padding:0 0 3px 0;'>"+
					"			<a href='"+basePath+"/user/securitycenter/updateEmail.action?email="+emailCode+"&amp;toKCode="+toKCode+"&amp;code="+code+"' style='display:inline-block; width:105px; text-align:center; background:#2980b9; color:#fff;  font-size:16px; text-decoration:none; line-height:34px; padding:0;border-radius:5px;' target='_blank'>查看详情</a></p>"+
					"	      <p style='margin:10px 0 5px 0; padding:3px 0;color:#666; font-size:14px'>如果上面不是链接形式，请将地址复制到您的浏览器（例如：IE）的地址栏再访问。</p>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>如果链接已经失效，请重新到干将网贷网站进行邮箱验证！谢谢您的合作<br>更多关于干将网贷信息，请访问干将网贷官方网站： <a href='"+basePath+"' target='_blank'>"+basePath+"</a></p>"+
					"	      <p style='margin:5px 0; padding:3px 0;color:#666; font-size:14px'>感谢对干将网贷的支持。</p>"+
					"	      <p style='text-align:center'><img src=''></p>"+
					"	      </td>"+
					"	    </tr>"+
					"	    </tbody>"+
					"	</table>"+
					"</div>";
			return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
 		}
		
		/**
		 * 
		* @Title: sendEamilCodeByUpdatePhone 
		* @Description: TODO(发送邮箱验证码 (根据原邮箱修改 手机号码)) 
		* @param @param email  发送的email
		* @param @param code   验证码
		* @param @param username  用户名
		* @param @param from_email 发送邮箱
		* @param @param password  发送邮箱密码
		* @param @param smtp_server 服务器
		* @param @return    设定文件 
		* @return boolean    返回类型 
		* @author   cjm  
		* @throws
		 */
		public static boolean sendEamilCodeByUpdatePhone(String email,String code,String username,String from_email,String password,String smtp_server){
				String subjectText = "来着干将网贷邮件";
 				String content ="<div>"+
							"	   <table width='760' border='0' cellspacing='0' cellpadding='0'>  "+
					   	 	"		<tbody>  "+
							"   	 		<tr>     "+
							"   	 			<td style='font-size:12px'>   "+
							"   	 				<div style='width:700px;height:30px;line-height:22px;padding:25px 30px 0 30px;font-size:14px;border-bottom:dashed 1px #5b5b5b'>尊敬的干将网贷会员，"+username+"您好！"+
							"   	 				</div>       "+
							"   	 				<div style='width:700px;line-height:22px;padding:10px 30px 0 30px'>  "+
							"   	 					<p>您于<span style='border-bottom:1px dashed #ccc;' t='5' times='"+StringUtil.formatDate(new Date(),"hh:mm")+"'>"+StringUtil.formatDate(new Date(), "yyyy/MM/dd hh:mm:ss")+"</span> "+
							"   	 					 提交了根据邮箱验证码修改手机号码的申请。"+
							"   	 					</p>邮箱验证码<span style='color:red;'>"+
							"   	 					<span style='border-bottom:1px dashed #ccc;z-index:1' t='7' onclick='return false;' data='"+code+"'>"+code+"</span>"+
							"   	 					</span>          "+
							"   	 					<p>请在干将网贷提示的输入框内输入验证码。</p>      "+
							"  	 				</div>     "+
							"   	 			</td>   "+
							"   	 		</tr>  "+
							"   	 	</tbody>"+
						   	"  </table>"+
						   	" <p>这是一封系统邮件，请勿直接回复。</p>"+
						   	" <p><a href='http://www.moye.com' target='_blank'>莫邪网</a></p>"+
						   	"</div>";
				//调用邮箱接口发送邮箱验证码
			   return CCSendMail.setSendMail2(email,content,subjectText,from_email,password,smtp_server);
			}
		
	
}
