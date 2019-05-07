package com.ptpl.controller.third;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ThirdPbLogin;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.service.ThirdPbLoginServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;

import net.sf.json.JSONObject;

/**
 * 第三方授权
 * @author 苏景焱
 *
 */
@Controller
@RequestMapping("/third")
public class ThirdBingdingController extends BaseController {
	
	@Autowired
	private ThirdPbLoginServiceI thirdPbLoginServiceI;
	@Autowired
	private UserAccountSafeInfoServiceI userAccountSafeInfoServiceI;

	/*public ModelAndView bingDing(@RequestParam(value="userBaseAccountInfo",required=false)UserBaseAccountInfo userBaseAccountInfo) throws Exception{*/
	@RequestMapping(value = "/bingDing", method = {RequestMethod.POST,RequestMethod.GET})
	public String bingDing(HttpServletRequest request) throws Exception{
		Map<String,Object> appNameMap=new HashMap<String,Object>();
		appNameMap.put("qqState", "");
		appNameMap.put("wbState", "");
		appNameMap.put("thirdPbLogin", "");
		//String user = (String) request.getSession().getAttribute(Session_Constant.USER);
		UserBaseAccountInfo user= (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//System.out.println(user);
			if(user==null||user.toString().isEmpty()){
					Object thirdObj=request.getSession().getAttribute("thirdPbLogin");
					JSONObject jsonT = JSONObject.fromObject(thirdObj);
					if(jsonT==null||jsonT.isEmpty()){
						request.setAttribute("appNameMap",appNameMap);
						return "user/third/bingDing";
					}else{
						if(jsonT.getString("thirdAPPName").equals("QQ")){
							appNameMap.put("qqState", "hadBD");
						}else if(jsonT.getString("thirdAPPName").equals("WB")){
							appNameMap.put("wbState", "hadBD");
						}
						request.getSession().setAttribute("thirdPbLogin", jsonT);
						request.setAttribute("appNameMap",appNameMap);
						return "user/third/bingDing";
					}
			}else{
					List<ThirdPbLogin> tlList= thirdPbLoginServiceI.findThirdPbLoginById(user.getId());
					if(tlList==null||tlList.isEmpty()){
						request.setAttribute("appNameMap",appNameMap);
						return "user/third/bingDing";
					}else{
						for(ThirdPbLogin tl :tlList){
							if(tl==null||tl.getThirdAPPName().isEmpty()||tl.getThirdAPPName()==null||tl.getThirdAPPName().equals("")){
								request.setAttribute("appNameMap",appNameMap);
								return "user/third/bingDing";
							}else{
								if(tl.getThirdAPPName().equals("QQ")){
									appNameMap.put("qqState", "hadBD");
									request.getSession().setAttribute("thirdPbLogin", tl);
								}else if(tl.getThirdAPPName().equals("WB")){
									appNameMap.put("wbState", "hadBD");
									appNameMap.put("thirdPbLogin", tl);
								}
							}
						}
						request.setAttribute("appNameMap",appNameMap);
						return "user/third/bingDing";
					}
			}
	}
	
	
	@RequestMapping("/bingDingQQ")
	public String  bingDingQQ(HttpServletRequest request){
		Map<String,Object> appNameMap=new HashMap<String,Object>();
		UserBaseAccountInfo user= (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		if(user!=null&&(!user.toString().isEmpty())){
				List<ThirdPbLogin> tlList= thirdPbLoginServiceI.findThirdPbLoginById(user.getId());
				if(tlList!=null&&(!tlList.isEmpty())){
					for(ThirdPbLogin tl :tlList){
						if(tl!=null&&tl.getThirdAPPName()!=null&&(!tl.getThirdAPPName().equals(""))){
							if(tl.getThirdAPPName().equals("QQ")){
								appNameMap.put("qqState", "hadBD");
								appNameMap.put("thirdPbLogin", tl);
							}else if(tl.getThirdAPPName().equals("WB")){
								appNameMap.put("wbState", "hadBD");
								appNameMap.put("thirdPbLogin", tl);
							}
						}
					}
				}	
		}	
		return "third/weibo";
	}
	@RequestMapping("/bingDingWB")
	public String  bingDingWB(HttpServletRequest request){
		
		return "third/weibo";
	}
	@RequestMapping("/removebingDingQQ")
	public void removebingDingQQ(HttpServletResponse response) throws IOException{
		UserBaseAccountInfo user= (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		System.out.println(".....................................>>>"+user);
		if(user!=null&&(!user.toString().isEmpty())){
			ThirdPbLogin thirdU=new ThirdPbLogin();
			thirdU.setBaseid(user.getId());
			thirdU.setThirdAPPName("QQ");
			int deleRes=thirdPbLoginServiceI.deleteThirdUserByBaseIdAndThirdName(thirdU);
			PrintWriter out= null;
	        out = response.getWriter();
			if(deleRes>=1){
		        out.print("success");
		        out.flush();
		        //out.close();
			}else{
		        out.print("fail");
		        out.flush();
		        //out.close();
			}
		}
	}
	@RequestMapping("/removebingDingWB")
	public void removebingDingWB(HttpServletResponse response) throws IOException{
		/*HttpSession session2 = request.getSession();
		Object attribute = session2.getAttribute(Session_Constant.USER);
		UserBaseAccountInfo user= (UserBaseAccountInfo)attribute;*/
		UserBaseAccountInfo user= (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		System.out.println(".....................................>>>>>>"+user);
		if(user!=null&&(!user.toString().isEmpty())){
			ThirdPbLogin thirdU=new ThirdPbLogin();
			thirdU.setBaseid(user.getId());
			thirdU.setThirdAPPName("WB");
			int deleRes=thirdPbLoginServiceI.deleteThirdUserByBaseIdAndThirdName(thirdU);
			PrintWriter out= null;
	        out = response.getWriter();
			if(deleRes>=1){
		        out.print("success");
		        out.flush();
		        //out.close();
			}else{
		        out.print("fail");
		        out.flush();
		        //out.close();
			}
		}
	}
	@RequestMapping("/varifyPsw")
	public void varifyPsw(@RequestParam(value="pwd",required=false)String pwd,HttpServletResponse response){
		UserBaseAccountInfo user= (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		if(user!=null&&(!user.toString().isEmpty())){
			PrintWriter out= null;
	        try {
				out = response.getWriter();
				//根据用户名查询密码
				UserAccountSafeInfo  userAccountSafeIn = userAccountSafeInfoServiceI.selectByBaseId(user.getId());
				if(userAccountSafeIn == null || userAccountSafeIn.getLoginpassword() == null){//用户名或密码错误
					out.print("fail");
				}else{
					//判断密码是否正确
					boolean pswFlag = BCrypt.checkpw(pwd,userAccountSafeIn.getLoginpassword());
			 		if(!pswFlag){//用户名或密码错误
			 			out.println("fail");
			 		}else{
			 			out.print("success");
			 		}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				out.flush();
		        out.close();
			}
		}
	}
}
