package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.StringUtil;
import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserPromo;
import com.ptpl.service.UserBaseAccountInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.web.util.HuifuParams;

/**
 * 
 * @ClassName: UserhuifuUserRegisterController
 * @Package com.ptpl.controller.huifu
 * @Description: TODO(汇付天下 用户开户 Controller层)
 * @author chenjiaming
 * @date 2016年8月8日 上午11:18:34
 * @version V1.0
 */
@Controller
@RequestMapping("/huifu")
public class HuifuUserRegisterController extends BaseController {

	/**
	 * 用户托管账户信息 ServiceI
	 */
	@Autowired
	private UserFsAccountInfoServiceI userFsAccountInfoServiceI;
	/**
	 * 用户基本信息 ServiceI
	 */
	@Autowired
	private UserBaseAccountInfoServiceI userBaseAccountInfoServiceI;
	/**
	 * 用户推广 ServiceI
	 */
	@Autowired
	private UserPromoServiceI userPromoService;
	/**
	 * 
	 * @Title: userRegister @Description: TODO(跳转汇付天下 用户开户页面) @param @param
	 * request @param @param res @param @return @param @throws Exception
	 * 参数说明 @return String 返回类型 @author chenjiaming @throws
	 */
	@RequestMapping("/UserRegister")
	public String userRegister(HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);
		if (userBaseAccountInfo != null)
		{
			if (userBaseAccountInfo.getAccountnumber() != null)
			{// 用户已经完善过信息了
				// 进入汇付天下 用户开户页面
				getParams(userBaseAccountInfo, request, response);
				return null;
			} else
			{// 这里返回用户完善信息页面
				return null;
			}
		} else
		{
			return "redirect:/user/tologin.action";
		}
	}

	/**
	 * @throws IOException @throws ServletException
	 * 
	 * @Title: UserRegisterCallback @Description: TODO(用户开户
	 * 实现后台应答方式) @param @param request @param @return 参数说明 @return String
	 * 返回类型 @author chenjiaming @throws
	 */

	@RequestMapping(value = "/UserRegisterCallback")
	public String UserRegisterCallback(HttpServletRequest request, HttpServletResponse response,
			HuifuParams huifuParams) throws ServletException, IOException
	{
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession()
				.getAttribute(Session_Constant.USER);
		System.out.println("进来了啊啊啊啊================" + huifuParams.getBgRetUrl());
		System.out.println("进来了啊啊啊啊=====getCmdId==========="
				+ StringUtils.trimToEmpty(huifuParams.getCmdId()));
		System.out.println("进来了啊啊啊啊========getRespCode========"
				+ StringUtils.trimToEmpty(huifuParams.getRespCode()));
		System.out.println("进来了啊啊啊啊=====getMerCustId==========="
				+ StringUtils.trimToEmpty(huifuParams.getMerCustId()));
		System.out.println("进来了啊啊啊啊===getUsrId============="
				+ StringUtils.trimToEmpty(huifuParams.getUsrId()));
		System.out.println("进来了啊啊啊啊=====getUsrId==========="
				+ StringUtils.trimToEmpty(huifuParams.getUsrId()));
		System.out.println("进来了啊啊啊啊=======getBgRetUrl========="
				+ StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
		System.out.println(
				"进来了啊啊啊啊=========getTrxId======" + StringUtils.trimToEmpty(huifuParams.getTrxId()));
		System.out.println("进来了啊啊啊啊========getRetUrl======="
				+ StringUtils.trimToEmpty(huifuParams.getRetUrl()));
		System.out.println("进来了啊啊啊啊=====getMerPriv=========="
				+ StringUtils.trimToEmpty(huifuParams.getMerPriv()));
		System.out.println("进来了啊啊啊啊=====getIdType=========="
				+ StringUtils.trimToEmpty(huifuParams.getIdType()));
		System.out.println(
				"进来了啊啊啊啊=====getUsrMp==========" + StringUtils.trimToEmpty(huifuParams.getUsrMp()));
		System.out.println(
				"进来了啊啊啊啊=====getIdNo==========" + StringUtils.trimToEmpty(huifuParams.getIdNo()));
		System.out.println("进来了啊啊啊啊=====getUsrCustId=========="
				+ StringUtils.trimToEmpty(huifuParams.getUsrCustId()));
		if (userBaseAccountInfo == null)
		{
			userBaseAccountInfo = userBaseAccountInfoServiceI
					.getUserBaseAccountInfoById(new BigDecimal(huifuParams.getMerPriv()));
		}
		/*
		 * CmdId + RespCode+ MerCustId + UsrId+UsrCustId +BgRetUrl + TrxId
		 * +RetUrl + MerPriv
		 */
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getRespCode()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getTrxId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
		String str = buffer.toString();
		BigDecimal baseid = (BigDecimal) (userBaseAccountInfo.getId() == null
				? huifuParams.getMerPriv()
				: userBaseAccountInfo.getId());
		UserFsAccountInfo accountInfo = userFsAccountInfoServiceI
				.findUserFsAccountInfoByBaseId(baseid);
		boolean flag = false;
		try
		{
			flag = SignUtils.verifyByRSA(str, huifuParams.getChkValue());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		if (flag)
		{// 验证签名成功
			UserFsAccountInfo userFsAccountInfo = new UserFsAccountInfo();
			String isopenfsinfo = huifuParams.getRespCode().equalsIgnoreCase("000") ? "1" : "0";
			userFsAccountInfo.setBaseid(baseid);// 用户ID
			if (huifuParams.getUsrCustId() != null)
			{
				userFsAccountInfo.setUsrcustid(huifuParams.getUsrCustId());// 用户商户号
			}
			if (huifuParams.getMerCustId() != null)
			{
				userFsAccountInfo.setMercustid(huifuParams.getMerCustId());// 商户号
			}
			if (huifuParams.getTrxId() != null)
			{
				userFsAccountInfo.setTrxid(huifuParams.getTrxId());// 交易唯一标识
			}
			if (huifuParams.getRespCode() != null)
			{
				userFsAccountInfo.setRespcode(huifuParams.getRespCode());// 应答返回码
			}
			if (huifuParams.getRespDesc() != null)
			{
				userFsAccountInfo.setRespdesc(huifuParams.getRespDesc());// 应答返回码描述
			}
			if (isopenfsinfo != null)
			{
				userFsAccountInfo.setIsopenfsinfo(new Short(isopenfsinfo));// 1已开通
																			// 0未开通
			}
			userFsAccountInfo.setChannelidentifier("汇付天下");// 托管通道标识 如汇付天下 宝付
			userFsAccountInfo.setOpeningtime(new Date());// 开通时间
			if (accountInfo == null && huifuParams.getRespCode().equalsIgnoreCase("000"))
			{// 用户没有开户
				if (huifuParams.getIdType() != null && huifuParams.getIdNo() != null
						&& huifuParams.getUsrName() != null)
				{
					if (huifuParams.getIdType() != null
							&& huifuParams.getIdType().equalsIgnoreCase("00"))
					{
						userBaseAccountInfo.setCertificationtype("0");// 证件类型0身份证1其他
					} else
					{
						userBaseAccountInfo.setCertificationtype("1");// 证件类型0身份证1其他
					}
					userBaseAccountInfo.setCertificationnumber(huifuParams.getIdNo());// 证件号码
					userBaseAccountInfo.setRealname(huifuParams.getUsrName());// 真实姓名
					userBaseAccountInfo.setIsreally(new Short("1"));// 是否实名认证:
																	// 1已认证 0
																	// 未认证
					userFsAccountInfo.setUsrname(huifuParams.getUsrName());// 用户真实名称
					userFsAccountInfo.setAccounttype((short) 1);// 账户类型（1个人，2企业）
					userFsAccountInfo.setUsrloginname(huifuParams.getUsrId());// 用户登录账户（汇付接口
																				// 前缀
																				// mykj_）
					if (huifuParams.getUsrMp() != null)
					{// 手机号
						userBaseAccountInfo.setMobilephone(huifuParams.getUsrMp());
					}
					userBaseAccountInfoServiceI.updateByPrimaryKeySelective(userBaseAccountInfo);// 保存用户基本信息
				}
				int count = 0;
				count = userFsAccountInfoServiceI.insertSelective(userFsAccountInfo);
				// 保存数据
				if (count > 0)
				{
					// 开户数据保存成功
					try
					{
						if (StringUtil.isNotEmpty(huifuParams.getTrxId()))
						{
							PrintWriter out = response.getWriter();
							out.print("RECV_ORD_ID_".concat(huifuParams.getTrxId()));
						}
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				} else
				{// 服务器异常中断保存失败 再保存一次
					userFsAccountInfoServiceI.insertSelective(userFsAccountInfo);
					try
					{
						if (StringUtil.isNotEmpty(huifuParams.getTrxId()))
						{
							PrintWriter out = response.getWriter();
							out.print("RECV_ORD_ID_".concat(huifuParams.getTrxId()));
						}
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				/*
				 * 用户推广设置 托管用户（开通），实名认证
				 */
				setUserPromo(baseid,huifuParams.getUsrName());
			}
		}
		return null;
	}
	/**
	 * 用户推广设置 托管用户（开通），实名认证
	 * @param baseid
	 */
	private void setUserPromo(BigDecimal baseid,String name)
	{
		UserBaseAccountInfo ubai=userBaseAccountInfoServiceI.selectByPrimaryKey(baseid);
		UserPromo userPromo = new UserPromo();
		//baseID
		userPromo.setId(baseid);
		//真实姓名
		userPromo.setName(name);
		//托管状态
		userPromo.setIsopenfsinfo((short) 1);
		//标记状态
		userPromo.setUserspecialflag((short) 0);
		//用户名
		userPromo.setLoginname(ubai.getLoginname());	
		userPromoService.updateByPrimaryKey(userPromo);
	}

	/**
	 * @throws ServletException
	 * 
	 * @Title: UserRegisterCallbackRet @Description: TODO(用户开户 实现页面显示
	 * ) @param @param request @param @param response @param @param
	 * huifuParams @param @return @param @throws IOException 参数说明 @return String
	 * 返回类型 @author chenjiaming @throws
	 */
	@RequestMapping("/UserRegisterRetUrlByRet")
	public String UserRegisterCallbackRet(HttpServletRequest request, HttpServletResponse response,
			HuifuParams huifuParams) throws IOException, ServletException
	{
		if (StringUtil.isNotEmpty(huifuParams.getChkValue()))
		{
			/*
			 * CmdId + RespCode+ MerCustId + UsrId+UsrCustId +BgRetUrl + TrxId
			 * +RetUrl + MerPriv
			 */
			StringBuffer buffer = new StringBuffer();
			buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getRespCode()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getUsrCustId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getTrxId()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
			buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
			String str = buffer.toString();
			boolean flag = false;
			try
			{
				flag = SignUtils.verifyByRSA(str, huifuParams.getChkValue());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			if (flag)
			{// 验签成功
				System.out.println(
						huifuParams.getRespCode() + "=========huifuParams.getRespCode()=========");
				System.out.println(
						huifuParams.getRespDesc() + "=========huifuParams.getRespCode()=========");
				request.setAttribute("RespDesc", huifuParams.getRespDesc());
				if (huifuParams.getRespCode().equalsIgnoreCase("521"))
				{// 用户已经开户了
					request.getRequestDispatcher("/WEB-INF/pages/UserRegister/UserRegistered.jsp")
							.forward(request, response);
				} else if (huifuParams.getRespCode().equalsIgnoreCase("221"))
				{// 开户失败
					request.getRequestDispatcher("/WEB-INF/pages/UserRegister/fail.jsp")
							.forward(request, response);
				} else if (huifuParams.getRespCode().equalsIgnoreCase("000"))
				{// 开户成功
					request.getRequestDispatcher("/WEB-INF/pages/UserRegister/success.jsp")
							.forward(request, response);
				} else
				{
					request.getRequestDispatcher("/WEB-INF/pages/UserRegister/other.jsp")
							.forward(request, response);
				}
			}
		} else
		{
			return "redirect:/user/tologin.action";
		}
		return null;
	}

	/**
	 * 
	 * @Title: getParams @Description: TODO(用户开户 请求参数设置) @param @param
	 * userBaseAccountInfo @param @param request @param @throws Exception
	 * 参数说明 @return void 返回类型 @author chenjiaming @throws
	 */
	public void getParams(UserBaseAccountInfo userBaseAccountInfo, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		HuifuParams huifuParams = new HuifuParams();
		huifuParams.setCmdId("UserRegister");/* 消息类型 */
		String basePath = com.ptpl.web.util.StringUtil.getBasePath(request);
		huifuParams.setBgRetUrl(basePath + "/huifu/UserRegisterCallback.action");/* 商户后台应答地址 */
		huifuParams.setRetUrl(basePath
				+ "/huifu/UserRegisterRetUrlByRet.action");/* 页面返回 URL */
		if (userBaseAccountInfo.getAccountnumber() != null)
		{/* 用户号 */
			huifuParams.setUsrId(userBaseAccountInfo.getAccountnumber().toString());
		}
		if (StringUtil.isNotEmpty(userBaseAccountInfo.getMobilephone()))
		{/* 手机号 */
			huifuParams.setUsrMp(userBaseAccountInfo.getMobilephone());
		}
		if (StringUtil.isNotEmpty(userBaseAccountInfo.getEmail()))
		{/* 用户Email */
			huifuParams.setUsrEmail(userBaseAccountInfo.getEmail());
		}
		if (userBaseAccountInfo.getId() != null)
		{/* 商户私有域 */
			huifuParams.setMerPriv(userBaseAccountInfo.getId().toString());
		}
		/*
		 * 参数拼接 格式不能乱 (Version + CmdId + MerCustId + BgRetUrl +RetUrl + UsrId +
		 * UsrName + IdType + IdNo +UsrMp + UsrEmail+ MerPriv+ PageType)
		 */
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getUsrId()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getUsrName()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getIdType()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getIdNo()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getUsrMp()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getUsrEmail()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
		sb.append(StringUtils.trimToEmpty(huifuParams.getPageType()));
		String plainStr = sb.toString();

		String chkValue = SignUtils.encryptByRSA(plainStr);
		request.setAttribute("Version", huifuParams.getVersion());
		request.setAttribute("CmdId", huifuParams.getCmdId());
		request.setAttribute("MerCustId", huifuParams.getMerCustId());
		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());
		request.setAttribute("RetUrl", huifuParams.getRetUrl());
		request.setAttribute("UsrId", huifuParams.getUsrId());
		request.setAttribute("UsrName", huifuParams.getUsrName());
		request.setAttribute("IdType", huifuParams.getIdType());
		request.setAttribute("IdNo", huifuParams.getIdNo());
		request.setAttribute("UsrMp", huifuParams.getUsrMp());
		request.setAttribute("UsrEmail", huifuParams.getUsrEmail());
		request.setAttribute("MerPriv", huifuParams.getMerPriv());
		request.setAttribute("PageType", huifuParams.getPageType());
		request.setAttribute("CharSet", huifuParams.getCharSet());
		request.setAttribute("ChkValue", chkValue);
		request.getRequestDispatcher("/WEB-INF/pages/UserRegister/UserRegister.jsp")
				.forward(request, response);
	}
}
