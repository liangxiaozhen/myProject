package com.ptpl.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.mapper.UserPromoMapper;
import com.ptpl.model.AGTPLink;
import com.ptpl.model.AgentGradePromoAuth;
import com.ptpl.model.PromoAgentGradeProfit;
import com.ptpl.model.PromoLevelRestrict;
import com.ptpl.model.PromoRegInfo;
import com.ptpl.model.PromoTotalRestrict;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserPromo;
import com.ptpl.model.UserPromoThirdParty;
import com.ptpl.service.AGTPLinkServiceI;
import com.ptpl.service.AgentGradePromoAuthServiceI;
import com.ptpl.service.PromoAgentGradeProfitServiceI;
import com.ptpl.service.PromoLevelRestrictServiceI;
import com.ptpl.service.PromoRegInfoServiceI;
import com.ptpl.service.PromoTotalRestrictServiceI;
import com.ptpl.service.UserPromoServiceI;
import com.ptpl.service.UserPromoThirdPartyServiceI;
import com.ptpl.web.util.ShareCodeUtil;
import com.ptpl.web.util.StringUtil;

/**
 * 用户推广设置 ServiceImpl
 * 
 * @author xiaoy
 *
 * @version 2016年9月30日 上午10:22:42
 */
public class UserPromoServiceImpl implements UserPromoServiceI {
	@Autowired
	UserPromoMapper userPromoMapper;
	@Autowired
	private PromoAgentGradeProfitServiceI promoAgentGradeProfitService;
	@Autowired
	private PromoRegInfoServiceI promoRegInfoService;
	@Autowired
	private PromoTotalRestrictServiceI promoTotalRestrictService;
	@Autowired
	private PromoLevelRestrictServiceI promoLevelRestrictService;
	@Autowired
	private UserPromoThirdPartyServiceI userPromoThirdPartyService;
	@Autowired
	private AGTPLinkServiceI aGTPLinkService;
	@Autowired
	private AgentGradePromoAuthServiceI agentGradePromoAuthService;

	@Override
	public int deleteByPrimaryKey(BigDecimal id) {
		// TODO Auto-generated method stub
		return userPromoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserPromo record) {
		// TODO Auto-generated method stub
		return userPromoMapper.insertSelective(record);
	}

	@Override
	public UserPromo selectByPrimaryKey(BigDecimal id) {
		UserPromo userPromo = userPromoMapper.selectByPrimaryKey(id);
		if (userPromo != null) {
			List<PromoAgentGradeProfit> pList = promoAgentGradeProfitService.getGradeName();
			for (PromoAgentGradeProfit p : pList) {
				if (userPromo.getProxygrade().equals(p.getId())) {
					userPromo.setProxygradename(p.getProxygradename());
					break;
				}
			}
		}
		return userPromo;
	}

	@Override
	public int updateByPrimaryKey(UserPromo record) {
		// TODO Auto-generated method stub
		return userPromoMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserPromo> selective(UserPromo userPromo) {
		//userPromo.setLoginname(AES.getEncrypt(userPromo.getLoginname()));
		//userPromo.setName(AES.getEncrypt(userPromo.getName()));
		List<UserPromo> upList = userPromoMapper.selective(userPromo);
		List<PromoAgentGradeProfit> pList = promoAgentGradeProfitService.getGradeName();
		for (UserPromo up : upList) {
			for (PromoAgentGradeProfit p : pList) {
				if (up.getProxygrade().equals(p.getId())) {
					up.setProxygradename(p.getProxygradename());
					break;
				}
			}
		}
		return upList;
	}

	@Override
	public UserPromo selectByPromoCode(String promoCode) {
		// TODO Auto-generated method stub
		UserPromo userPromo = userPromoMapper.selectByPromoCode(promoCode);
		if (userPromo != null) {
			List<PromoAgentGradeProfit> pList = promoAgentGradeProfitService.getGradeName();
			for (PromoAgentGradeProfit p : pList) {
				if (userPromo.getProxygrade().equals(p.getId())) {
					userPromo.setProxygradename(p.getProxygradename());
					break;
				}
			}
		}
		return userPromo;
	}

	@Override
	public List<UserPromo> selectBySupName(String supName) {
		// TODO Auto-generated method stub
		List<UserPromo> upList = userPromoMapper.selectBySupName(supName);
		List<PromoAgentGradeProfit> pList = promoAgentGradeProfitService.getGradeName();
		for (UserPromo up : upList) {
			for (PromoAgentGradeProfit p : pList) {
				if (up.getProxygrade().equals(p.getId())) {
					up.setProxygradename(p.getProxygradename());
					break;
				}
			}
		}
		return upList;
	}

	@Override
	public int updateBySupName(UserPromo record) {
		// TODO Auto-generated method stub
		return userPromoMapper.updateBySupName(record);
	}

	@Override
	public List<UserPromo> getID() {
		// TODO Auto-generated method stub
		return userPromoMapper.getID();
	}

	@Override
	public int updatePromoCode(String oldSupPromoCode, String newSupPromoCode) {
		// TODO Auto-generated method stub
		return userPromoMapper.updatePromoCode(oldSupPromoCode, newSupPromoCode);
	}

	@Override
	public int insertForRegister(UserBaseAccountInfo ubai, String promoCode, HttpServletRequest request,
			HttpServletResponse response) {
		UserPromo userPromo = new UserPromo();
		// ID
		userPromo.setId(ubai.getId());
		// 托管开通
		userPromo.setIsopenfsinfo((short) 0);
		// 用户名
		userPromo.setLoginname(ubai.getLoginname());
		// 推荐码
		userPromo.setPromocode(ShareCodeUtil.toSerialCode(ubai.getId().longValue()));
		// 推广码未修改
		userPromo.setIsmodify((short) 0);
		// 注册时间
		userPromo.setRegdate(ubai.getRegdate());
		// 推广特殊标记
		userPromo.setUserspecialflag((short) 0);
		// 推广人数
		userPromo.setPromonum(0L);
		// 有效推广人数
		userPromo.setValidnum(0L);
		// 推广费总收入
		userPromo.setPromofee(0d);
		// 推广层数 （本级）
		userPromo.setPromolevels(1L);
		// 推广代理等级
		userPromo.setProxygrade(new BigDecimal(23));
		// 通过推广码查询
		UserPromo up = userPromoMapper.selectByPromoCode(promoCode);
		if (up != null) {
			/*
			 * 被推广人
			 */
			long parentLevels = up.getPromolevels();
			long validNum = up.getValidnum();
			// 新用户层级
			userPromo.setPromolevels(parentLevels + 1);
			// 新用户上级用户名
			userPromo.setSuploginname(up.getLoginname());
			// 新用户上级姓名
			userPromo.setSupname(up.getName());
			// 新用户上级推广码
			userPromo.setSuppromocode(promoCode);
			userPromo.setSuplevels2(up.getLoginname());
			userPromo.setIsvalid2((short) 0);
			/*
			 * 推广限制
			 */
			PromoTotalRestrict ptr = promoTotalRestrictService.selectByPrimaryKey(new BigDecimal(1));
			// 按层级升序排列
			List<PromoLevelRestrict> plrList = promoLevelRestrictService.selective(null);
			// 推广层数限制
			int levelLmit = ptr.getLevellimit().intValue();
			// 推广限制类型
			Short restrictType = ptr.getRestricttype();
			// 1 推广总人数限制
			if (restrictType.equals((short) 1)) {
				// 未超过限制人数，推广有效
				if (ptr.getTotalnumlimit() > validNum) {
					userPromo.setIsvalid2((short) 1);
					// 有效人数
					up.setValidnum(up.getValidnum() + 1);
				}
			}
			// 2推广层级人数限制
			if (restrictType.equals((short) 2)) {
				if (levelLmit > 1) {
					// 获取推广层级限制对象
					PromoLevelRestrict promoLR = plrList.get(0);
					// 未超过限制人数，推广有效
					if (promoLR.getLevellimitnum() > validNum) {
						userPromo.setIsvalid2((short) 1);
						// 有效人数
						up.setValidnum(up.getValidnum() + 1);
					}
				}
			}
			/*
			 * 推广人
			 */
			up.setPromonum(up.getPromonum() + 1);
			userPromoMapper.updateByPrimaryKey(up);
			try {
				// 上级推广码
				String code = up.getSuppromocode();
				boolean flag = true;
				for (int i = 3; i < 101; i++) {
					UserPromo userP = userPromoMapper.selectByPromoCode(code);
					/*
					 * 利用反射机制设置上级用户名和是否有效
					 */
					Class<?> clazz = userPromo.getClass();
					// 上级用户suplevels
					Method m1 = clazz.getDeclaredMethod("setSuplevels" + i, String.class);
					// 是否有效 isvalid
					Method m2 = clazz.getDeclaredMethod("setIsvalid" + i, Short.class);
					if (userP != null) {
						m1.invoke(userPromo, userP.getLoginname());
						m2.invoke(userPromo, (short) 0);
						if (restrictType.equals((short) 1) && flag) {
							// 未超过限制人数，推广有效
							if (ptr.getTotalnumlimit() > userP.getValidnum()) {
								// 上级推广码
								String supPromoCode = up.getSuppromocode();
								if (StringUtil.isNotEmpty(supPromoCode)) {
									int isvalid = up.getIsvalid2().intValue();
									// 上级推广有效
									if (isvalid == 1) {
										m2.invoke(userPromo, (short) 1);
										userP.setValidnum(userP.getValidnum() + 1);
									} else {
										flag = false;
									}
								} else {
									userP.setValidnum(userP.getValidnum() + 1);
									m2.invoke(userPromo, (short) 1);
								}
							}
						}
						if (restrictType.equals((short) 2) && flag) {
							if (levelLmit >= i) {
								UserPromo userP2 = new UserPromo();
								m1.invoke(userP2, userP.getLoginname());
								m2.invoke(userP2, (short) 1);
								PromoLevelRestrict plevelr = plrList.get(i - 2);
								// 当前用户的下i层有效人数
								List<UserPromo> up2List = userPromoMapper.selective(userP2);
								if (up2List.size() < plevelr.getLevellimitnum().intValue()) {
									// 上级推广码
									String supPromoCode = up.getSuppromocode();
									if (StringUtil.isNotEmpty(supPromoCode)) {
										int isvalid = up.getIsvalid2().intValue();
										// 上级推广有效
										if (isvalid == 1) {
											m2.invoke(userPromo, (short) 1);
											userP.setValidnum(userP.getValidnum() + 1);
										} else {
											flag = false;
										}
									} else {
										m2.invoke(userPromo, (short) 1);
										userP.setValidnum(userP.getValidnum() + 1);
									}
								}
							}
						}
						// 当前用户推广的上级推荐码
						code = userP.getSuppromocode();
						up.setSuppromocode(userP.getSuppromocode());
						up.setIsvalid2(userP.getIsvalid2());
						// 上级用户推广人数+1
						userP.setPromonum(userP.getPromonum() + 1);
						userPromoMapper.updateByPrimaryKey(userP);
					}
					// 上级推荐码为null,跳出循环
					if (StringUtil.isEmpty(code))
						break;
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		int iden = userPromoMapper.insertSelective(userPromo);
		if (iden > 0 && up != null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("promoInfo")) {
					// 无根推广码
					String promoInfo = cookie.getValue().toString();
					if (StringUtil.isNotEmpty(promoInfo)) {
						PromoRegInfo promoRegInfo = new PromoRegInfo();
						promoRegInfo.setRegusercode(promoInfo);
						promoRegInfo.setPuserid(up.getId());
						promoRegInfo.setRegtime(ubai.getRegdate());
						promoRegInfo.setRuserid(ubai.getId());
						promoRegInfoService.insertSelective(promoRegInfo);
						cookie.setMaxAge(0);
						cookie.setValue(null);
						response.addCookie(cookie);
					}
				}
			}
		}
		UserPromoThirdParty uptp = new UserPromoThirdParty();
		uptp.setUpid(ubai.getId());
		List<AgentGradePromoAuth> agpaList = agentGradePromoAuthService.selective(null);
		for (AgentGradePromoAuth agpa : agpaList) {
			// 推广链接地址
			uptp.setThirdpartycode(agpa.getThirdpartycode());
			// 推广第三方名称
			uptp.setThirdpartyname(agpa.getThirdpartyname());
			// 开关
			uptp.setIsopen((short) 0);
			if (agpa.getIsopen().equals((short) 1)) {
				AGTPLink link = aGTPLinkService.selectByProxyGradeAndAGAPID(agpa.getId(), new BigDecimal(23));
				uptp.setIsopen(link.getIsopen());
			}
			userPromoThirdPartyService.insertSelective(uptp);
		}
		return iden;
	}

}
