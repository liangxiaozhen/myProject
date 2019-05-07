package com.ptpl.controller.ui.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserFsAccountInfo;

/**
 * 
 * @ClassName: UIAppMasterI
 * @Description: TODO(app 接口 )
 * @author cjm
 * @date 2017年6月10日 下午7:54:35
 *
 */
public interface UIAppMasterServiceI {

	/**
	 * 
	 * @Title: userLogin @Description: TODO(用户登录接口) @param @param
	 *         request @param @param response @param @return 设定文件 @return Map
	 *         <String,String> 返回类型 @author cjm @throws
	 */
	Map<String, String> userLogin(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 提现验证
	 * 
	 * @author 作者 xiaoy:
	 * @version 创建时间：2017年5月17日 上午10:48:52
	 * @param request
	 * @param ubai
	 *            用户基本信息
	 * @param ufai
	 *            用户安全信息
	 * @return
	 */
	Map<String, String> checkWithdraws(HttpServletRequest request, UserBaseAccountInfo ubai, UserAccountSafeInfo uasi);
}
