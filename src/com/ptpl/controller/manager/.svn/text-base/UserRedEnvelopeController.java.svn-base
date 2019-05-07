package com.ptpl.controller.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Red_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.StringUtil;

/**
 * 管理员后台-用户红包
 * @author zhenglm
 *
 */
@Controller
@RequestMapping("/admin/bonus")
public class UserRedEnvelopeController extends BaseController {
	
	/**
	 * 用户红包Service
	 */
	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;
	
	/**
	 * 用户基本信息Service
	 */
	@Autowired
	UserBaseAccountInfoServiceI userBaseAccountInfoService;
	/**
	 * 用户汇付托管账户信息Service
	 */
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;
	
	/**
	 * 获奖名表
	 * */
	@Autowired
	private ActivityAwardListServiceI activityAwardListService;
	/**
	 * 现金红包发放对账记录Service
	 */
	@Autowired
	RedEnveLopeItemServiceI redEnveLopeItemService;
	
	/**
	 * 用户账户表
	 */
	@Autowired
	UserAccountServiceI userAccountService;
	
	UserRedEnvelope red=new UserRedEnvelope();//全局使用，从中拿到数据赋值给其他实体字段所需
	UserRedEnvelope	userRedEnvelope=new UserRedEnvelope();//全局使用（最主要用于红包的类型判断），从中拿到数据赋值给其他实体字段所需
	/**
	 * 分页查询红包列表
	 * @param request
	 * @param bonus
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/queryBonusList", method = {RequestMethod.POST,RequestMethod.GET} )
	public ModelAndView queryBonusList(HttpServletRequest request,UserRedEnvelope redEnv) throws Exception {
		
		System.out.println("redEnv: "+redEnv);
		
		// 处理分页请求
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		int num = 1;
		int size = 9;
		if (pageNum != null && !"".equals(pageNum)) {
			num = Integer.parseInt(pageNum);
		}
		if (pageSize != null && !"".equals(pageSize)) {
			size = Integer.parseInt(pageSize);
		}
		String sortString = "id.desc";
		Order.formString(sortString);
		PageHelper.startPage(num, size);

		// 调用service层的方法得到对象列表
		if(redEnv.getName()!=null && !"".equals(redEnv.getName().getLoginname()) && redEnv.getName().getLoginname()!=null){
			redEnv.getName().setLoginname(setEncrypt(redEnv.getName().getLoginname()));
		}
		List<UserRedEnvelope> redList = userRedEnvelopeService.getselectAll(redEnv);
		for(UserRedEnvelope u:redList){
			u.setName(getDecryptionUserBaseAccountInfoDetail(u.getName()));
		}
		
		PageInfo<UserRedEnvelope> pagehelper = new PageInfo<UserRedEnvelope>(redList);
		pagehelper.setFirstPage(1);

		int lastPageNum = 0;
		if (pagehelper.getTotal() % size == 0) {
			lastPageNum = (int) pagehelper.getTotal() / size;
		} else {
			lastPageNum = (int) pagehelper.getTotal() / size + 1;
		}
		pagehelper.setLastPage(lastPageNum);
		// 把对象放进modelAndView中
		ModelAndView mv = new ModelAndView();
		if(redEnv.getName()!=null && !"".equals(redEnv.getName().getLoginname()) && redEnv.getName().getLoginname()!=null){
			redEnv.getName().setLoginname(getDecrypt(redEnv.getName().getLoginname()));
		}
		mv.addObject("redEnv",redEnv);
		mv.addObject("pagehelper", pagehelper);
		mv.addObject("retypemaps",ActAward_Constant.RETYPE_MAP);//红包类型
		mv.addObject("rectypemaps",ActAward_Constant.RECTYPE_MAP);//获取方式类型
		mv.addObject("isusemaps",ActAward_Constant.AWARD_MAP);//红包状态
		mv.addObject("statusmaps",ActAward_Constant.AWARDSTATUS_MAP);//发放状态
		
		// 指定视图
		mv.setViewName("admin/Bonus/userredenvelopeList");
		return mv;
	}
	
	/**
	 * 查看用户红包详情
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value="/queryBonusDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryBonusDetail(HttpServletResponse response, BigDecimal id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try{
			UserRedEnvelope detail =  userRedEnvelopeService.selectByPrimaryKey(id); // 红包详情信息
			if(detail.getRestime() != null){
				detail.setRestimeStr(sf.format(detail.getRestime())); // 处理红包发放时间
			}
			if(detail.getRedealtime() != null){
				detail.setRedealtimeStr(sf.format(detail.getRedealtime())); // 处理红包处理时间（入账）
			}
			if(detail.getRefailtime() != null){
				detail.setRefailtimeStr(sf.format(detail.getRefailtime())); // 处理红包失效时间
			}
			if(detail.getAudittime() != null){
				detail.setAudittimeStr(sf.format(detail.getAudittime())); // 处理审核时间
			}
			
			detail.setName(userBaseAccountInfoService.selectByPrimaryKey(detail.getBaseid())); // 用户名（前台展示）
			detail.setName(getDecryptionUserBaseAccountInfoDetail(detail.getName()));
			
			mv.addObject("reddetail", detail);
			mv.addObject("redctype", ActAward_Constant.RECTYPE_MAP.get(detail.getRectype())); // 红包获取方式类型
			mv.addObject("redtype", ActAward_Constant.RETYPE_MAP.get(detail.getRetype())); //  // 红包类型
			mv.addObject("redstatus", ActAward_Constant.STATUS_MAP.get(detail.getStatus()));// 红包发放状态
			mv.addObject("redisuse", ActAward_Constant.AWARD_MAP.get(detail.getIsuse())); // 红包状态
			mv.addObject("redisaudit", ActAward_Constant.ISAUDIT_MAP.get(detail.getIsaudit())); // 是否审核 
			// 指定视图
			mv.setViewName("admin/Bonus/UserRedEnvelope_Detail"); // 详情页面
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return mv;
	}
	
	/**
	 * 红包作废
	 * @param redEnv
	 */
	@RequestMapping("/redEnvInvalid")
	public void redEnvInvalid(HttpServletResponse response,UserRedEnvelope redEnv){
		redEnv.setIsuse((short)6);
		int rows=userRedEnvelopeService.updateRedEnvById(redEnv);
		Map<String,String> map = new HashMap<String,String>();
		if(rows>0){
			map.put("result", "操作成功");
		}else{
			map.put("result", "操作失败");
		}
		String jsonStr = JSON.toJSONString(map);
		try {
			StringUtil.sendJsonData(response, jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
