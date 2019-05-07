package com.ptpl.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBonusPointsServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.Iconstant.IUserBackground;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user/userAccount")
public class UserAccountController extends BaseController implements IUserBackground {
	@Autowired
	private UserAccountServiceI userAccountService;
	@Autowired
	UserBonusPointsServiceI userBonusPointsService;
	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;
	
	//用户账户余额
	@RequestMapping(value = "/accountBalance", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView userAccountInfo(){
		ModelAndView mv = new ModelAndView();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(getUserAccountSafeInfo().getBaseid());
		mv.addObject("userAccount",userAccount);
		mv.addObject("df",df);
		mv.setViewName("user/account/accountBalance");
		mv.addObject("userBalance", IUserBackground.USERBALANCE);
		return mv;
	}
	
	//用户账户积分
	@RequestMapping(value = "/accountBonusPoints", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView userBonusPoints(){
		ModelAndView mv = new ModelAndView();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(getUserAccountSafeInfo().getBaseid());
		mv.addObject("userAccount",userAccount);
		mv.setViewName("user/account/accountBonusPoints");
		mv.addObject("userBonusPoints", IUserBackground.BONUSPOINTS);
		return mv;
	}
		
	//用户账户红包
	@RequestMapping(value = "/accountRedEnvelope", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView userRedEnvelope(){
		ModelAndView mv = new ModelAndView();
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(getUserAccountSafeInfo().getBaseid());
		mv.addObject("userAccount",userAccount);
		mv.setViewName("user/account/accountRedEnvelope");
		mv.addObject("userRedEnvelope", IUserBackground.REDENVELOPE);
		return mv;
	}
	/**
	 * fck
	 * @param request
	 * @param response
	 * @param loginname
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserByLoginName", method = { RequestMethod.POST, RequestMethod.GET })
	public void getUserAccountByLoginName(HttpServletRequest request, HttpServletResponse response,String loginname) throws Exception {
		 System.out.println("传过来的参数："+loginname);
		 String loginName=setEncrypt(loginname);//将获取的用户名加密
		 UserAccount userAccount= userAccountService.getUserAccountByLoginName(loginName);
		 System.out.println("结果："+userAccount);
		 if(userAccount!=null){
			 Map<String,Object> map=new HashMap<String,Object>();
			 DecimalFormat    df   = new DecimalFormat("######0.00");//截取小数点后两位
			 System.out.println(""+df.format(userAccount.getBalance()));
			 String totleMoney=df.format(userAccount.getBalance());
			 String avBalance=df.format(userAccount.getAvlbalance());
			 String freeMoney=df.format(userAccount.getFreezebalance());
			 System.out.println("返回的结果："+userAccount.getFreezebalance());
			 map.put("totleMoney", totleMoney);
			 map.put("avBalance", avBalance);
			 map.put("freeMoney", freeMoney);
			String data = "";
			data=JSON.toJSONString(map);
			sendJsonData(response, data);
			}else{
				
			}
	}
	
	
	/**
	 * fck
	 * 根据登陆的用户名来查询用户积分
	 */
	@RequestMapping(value = "/userBounsPointsByLoginName", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String getUserBounsPointsByLoginName(String loginname) throws Exception {
		 String loginName=setEncrypt(loginname);
		UserBonusPoints bonusPoints= userBonusPointsService.getUserBounsPointsByLoginName(loginName);
		System.out.println("bonusPoints----------->:"+bonusPoints);
		if(bonusPoints!=null){
			System.out.println("返回的结果："+bonusPoints.getBonuspoints());
			Long bounsPoints=bonusPoints.getBonuspoints();
			ObjectMapper mapper=new ObjectMapper();
			String json=mapper.writeValueAsString(bounsPoints);
			return json;
		}else{
			return "null";
		}
		
		
	}
	/**
	 * 只计算红包可使用的状态下的钱
	 * @param loginname
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/redBaoByLoginName", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public String getRedBaoByLoginName(String loginname) throws Exception {
		 String loginName=setEncrypt(loginname);
		List<UserRedEnvelope> list= userRedEnvelopeService.getUserRedBaoByLoginName(loginName);
		if(list.size()!=0){
			double money=0.0;
			for (int i = 0; i <list.size(); i++) {
				System.out.println(list.get(i)+"红包状态："+list.get(i).getIsuse());
				if(list.get(i).getIsuse()==2){
					money+=list.get(i).getRedenvelope();
				}
			}
			ObjectMapper mapper=new ObjectMapper();
			String json=mapper.writeValueAsString(money);
			System.out.println("可用的红包："+money);
			return json;
			}else{
				return "null";
			}
		
		
	}
}
