package com.ptpl.controller.huifu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.web.util.HuifuParams;
/**
 * 
* @ClassName: HuifuUserLoginController 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(汇付天下 用户登录后台) 
* @author chenjiaming
* @date 2016年9月18日 下午5:12:25 
* @version V1.0
 */
@Controller
@RequestMapping("/huifu/userLogin")
public class HuifuUserLoginController extends BaseController{
	
	@RequestMapping("/tologin")
	public String toLogin(HttpServletRequest request,HttpServletResponse response){
 		UserFsAccountInfo accountInfo = (UserFsAccountInfo) request.getSession().getAttribute(Session_Constant.USERFSACCOUNTINFO);
		if(accountInfo != null){
			HuifuParams huifuParams = new HuifuParams();
			/*消息类型 此处为 UserLogin*/
 			huifuParams.setCmdId("UserLogin");
 			if(accountInfo.getMercustid() != null){
 				/*商户客户号*/
 				huifuParams.setMerCustId(accountInfo.getMercustid());
 			}
  			if(accountInfo.getUsrcustid() != null){
 				/*用户客户号*/
 				huifuParams.setUsrCustId(accountInfo.getUsrcustid());
 			}
  			String url = "http://mertest.chinapnr.com/muser/publicRequests?Version="+
  						huifuParams.getVersion()+"&CmdId="+huifuParams.getCmdId()+
  						"&MerCustId="+huifuParams.getMerCustId()+"&UsrCustId="+huifuParams.getUsrCustId();
  			return "redirect:"+url;
  		}
 		return null;
	}
}
