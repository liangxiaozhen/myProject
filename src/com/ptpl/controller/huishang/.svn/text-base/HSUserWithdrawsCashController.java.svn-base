package com.ptpl.controller.huishang;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huifu.util.httpClient.HttpClientHandler;
import com.huishang.util.HSignUtil;
import com.ptpl.controller.BaseController;
import com.ptpl.model.UserWithdrawsCash;
import com.ptpl.service.UserWithdrawsCashServiceI;
import com.ptpl.web.util.AES;

import net.sf.json.JSONObject;

/**
 * 徽商银行接口取现回调
 * 
 * @author 作者 xiaoy:
 * @version 创建时间：2017年4月7日 下午6:40:57
 *
 */
@Controller
@RequestMapping("huishang/userwithdrawscash")
@Scope("prototype")
public class HSUserWithdrawsCashController extends BaseController {
	@Autowired
	private UserWithdrawsCashServiceI userWithdrawsCashService;

	@RequestMapping(value = "/callback")
	public synchronized void callback() {
		Map map = JSONObject.fromObject( request.getParameter("bgData"));
		String VERVISON = (String) map.get("VERSION");
		String ORDERNO = (String) map.get("ORDERNO");
		String TRXDATE = (String) map.get("TRXDATE");
		String TRXTIME = (String) map.get("TRXTIME");
		String BANKCODE = (String) map.get("BANKCODE");
		String COINSTCODE = (String) map.get("COINSTCODE");
		String COINSTCHANNEL = (String) map.get("COINSTCHANNEL");
		String CARDNBR = (String) map.get("CARDNBR");
		String AMOUNT = (String) map.get("AMOUNT");
		String RETCODE = (String) map.get("RETCODE");
		String RETMSG = (String) map.get("RETMSG");
		String SIGN = (String) map.get("SIGN");
		System.out.println("VERSION=" + VERVISON);
		System.out.println("ORDERNO=" + ORDERNO);
		System.out.println("TRXDATE=" + TRXDATE);
		System.out.println("TRXTIME=" + TRXTIME);
		System.out.println("BANKCODE=" + BANKCODE);
		System.out.println("COINSTCODE=" + COINSTCODE);
		System.out.println("COINSTCHANNEL=" + COINSTCHANNEL);
		System.out.println("CARDNBR=" + CARDNBR);
		System.out.println("AMOUNT=" + AMOUNT);
		System.out.println("RETCODE=" + RETCODE);
		System.out.println("RETMSG=" + RETMSG);
		System.out.println("SIGN=" + SIGN);

		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(VERVISON)).append(StringUtils.trimToEmpty(ORDERNO))
				.append(StringUtils.trimToEmpty(TRXDATE)).append(StringUtils.trimToEmpty(TRXTIME))
				.append(StringUtils.trimToEmpty(BANKCODE)).append(StringUtils.trimToEmpty(COINSTCODE))
				.append(StringUtils.trimToEmpty(COINSTCHANNEL)).append(StringUtils.trimToEmpty(CARDNBR))
				.append(StringUtils.trimToEmpty(AMOUNT)).append(StringUtils.trimToEmpty(RETCODE))
				.append(StringUtils.trimToEmpty(RETMSG));
		String responseMerged = buffer.toString();
		boolean flag = false;
		try {
			flag = HSignUtil.getVerify(responseMerged, SIGN);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			System.out.println("验签成功。。");
			UserWithdrawsCash uwdCash = userWithdrawsCashService.selectByCashNo(ORDERNO);
			if (uwdCash != null) {
				// 交易描述
				uwdCash.setRemark(RETCODE + " " + RETMSG);
				// 处理时间
				Date date = new Date();
				uwdCash.setAuditdate(date);
				// 判断电子账户是否相同
				if (!CARDNBR.equals(AES.getDecrypt(uwdCash.getUsrcustid())))
					return;
				// if (!AMOUNT.equals(df1.format(uwdCash.getAmount())))
				// return;
				if ("00000000".equals(RETCODE)) {
					// 交易成功
					uwdCash.setStatus((short) 1);
					userWithdrawsCashService.updateForSuccess(uwdCash);
				} else {
					// 交易失败
					uwdCash.setStatus((short) 2);
					userWithdrawsCashService.updateByCashNo(uwdCash);
				}
			}
		}
		// try {
		// String bgData = request.getParameter("bgData");
		// response.setContentType("text/html;charset=UTF-8");
		// response.getWriter().write("success");
		// Map map = JSONObject.fromObject(bgData);
		// String str = doPost(map);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	@RequestMapping(value = "callback2")
	public synchronized void callback2() {
		String VERVISON = request.getParameter("VERSION");
		String ORDERNO = request.getParameter("ORDERNO");
		String TRXDATE = request.getParameter("TRXDATE");
		String TRXTIME = request.getParameter("TRXTIME");
		String BANKCODE = request.getParameter("BANKCODE");
		String COINSTCODE = request.getParameter("COINSTCODE");
		String COINSTCHANNEL = request.getParameter("COINSTCHANNEL");
		String CARDNBR = request.getParameter("CARDNBR");
		String AMOUNT = request.getParameter("AMOUNT");
		String RETCODE = request.getParameter("RETCODE");
		String RETMSG = request.getParameter("RETMSG");
		String SIGN = request.getParameter("SIGN");
		System.out.println("VERSION=" + VERVISON);
		System.out.println("ORDERNO=" + ORDERNO);
		System.out.println("TRXDATE=" + TRXDATE);
		System.out.println("TRXTIME=" + TRXTIME);
		System.out.println("BANKCODE=" + BANKCODE);
		System.out.println("COINSTCODE=" + COINSTCODE);
		System.out.println("COINSTCHANNEL=" + COINSTCHANNEL);
		System.out.println("CARDNBR=" + CARDNBR);
		System.out.println("AMOUNT=" + AMOUNT);
		System.out.println("RETCODE=" + RETCODE);
		System.out.println("RETMSG=" + RETMSG);
		System.out.println("SIGN=" + SIGN);

		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(VERVISON)).append(StringUtils.trimToEmpty(ORDERNO))
				.append(StringUtils.trimToEmpty(TRXDATE)).append(StringUtils.trimToEmpty(TRXTIME))
				.append(StringUtils.trimToEmpty(BANKCODE)).append(StringUtils.trimToEmpty(COINSTCODE))
				.append(StringUtils.trimToEmpty(COINSTCHANNEL)).append(StringUtils.trimToEmpty(CARDNBR))
				.append(StringUtils.trimToEmpty(AMOUNT)).append(StringUtils.trimToEmpty(RETCODE))
				.append(StringUtils.trimToEmpty(RETMSG));
		String responseMerged = buffer.toString();
		boolean flag = false;
		try {
			flag = HSignUtil.getVerify(responseMerged, SIGN);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			System.out.println("验签成功。。");
			UserWithdrawsCash uwdCash = userWithdrawsCashService.selectByCashNo(ORDERNO);
			if (uwdCash != null) {
				// 交易描述
				uwdCash.setRemark(RETCODE + " " + RETMSG);
				// 处理时间
				Date date = new Date();
				uwdCash.setAuditdate(date);
				// 判断电子账户是否相同
				if (!CARDNBR.equals(uwdCash.getUsrcustid()))
					return;
				// if (!AMOUNT.equals(df1.format(uwdCash.getAmount())))
				// return;
				if ("00000000".equals(RETCODE)) {
					// 交易成功
					uwdCash.setStatus((short) 1);
					userWithdrawsCashService.updateForSuccess(uwdCash);
				} else {
					// 交易失败
					uwdCash.setStatus((short) 2);
					userWithdrawsCashService.updateByCashNo(uwdCash);
				}
			}
		}
	}

	// 如果关注性能问题可以考虑使用HttpClientConnectionManager
	public String doPost(Map<String, String> params) throws ClientProtocolException, IOException {
		String result = null;
		HttpPost httpPost = new HttpPost(
				"http://113.116.159.92:8881/ptpjx/huishang/userwithdrawscash/callback2.action");
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if (params != null) {
			UrlEncodedFormEntity formEntiry = HttpClientHandler.buildUrlEncodedFormEntity(params);
			httpPost.setEntity(formEntiry);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getReasonPhrase().equals("OK")
						&& response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
					result = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		}
		return result;
	}
}
