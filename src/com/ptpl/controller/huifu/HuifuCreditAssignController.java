package com.ptpl.controller.huifu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

 import com.huifu.util.SignUtils;
import com.huifu.util.httpClient.HttpClientHandler;
import com.ptpl.controller.BaseController;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.MD5;
import com.ptpl.web.util.StringUtil;
 
/**
 * 
* @ClassName: HuifuCreditAssignController 
* @Package com.ptpl.controller.huifu 
* @Description: TODO(债权转让 汇付天下 测试文件) 
* @author chenjiaming
* @date 2016年9月23日 下午5:07:25 
* @version V1.0
 */
@Controller
@RequestMapping("/huifu/CreditAssign")
public class HuifuCreditAssignController extends BaseController {
	 
	@RequestMapping("/test")
	public String test(){
		return "";
	}
	@RequestMapping("/doCreditAssign")
	 public void doCreditAssign(HttpServletRequest request,HttpServletResponse response){
		 HuifuParams huifuParams = new HuifuParams ();
		 huifuParams.setVersion("30");//版本号 Version 必须 目前固定为 30，如版本升级，能向前兼容
		 huifuParams.setCmdId("CreditAssign"); // 消息类型 CmdId   必须 此处为 CreditAssign
		 huifuParams.setMerCustId("6000060004166478"); //商户客户号 MerCustId  6000060004166478
		 huifuParams.setSellCustId("6000060005590122");//转让人客户号 SellCustId 必须 债权转让转让人客户号
		 huifuParams.setCreditAmt("10.00");//转让金额 CreditAmt 必须 债权转让转出的本金
		 huifuParams.setCreditDealAmt("10.00"); //承接金额CreditDealAmt 必须 债权转让承接人付给转让人的金额
 		 huifuParams.setBidOrdId("20161110101745242850");// 被转让的投标订单号  BidOrdId  必须  被转让的投标订单号 BidDetails的二级参数
		 huifuParams.setBidOrdDate("20161110"); // 被转让的投标订单日期 BidOrdDate 必须 BidDetails的二级参数
		 huifuParams.setBidCreditAmt("10.00"); //转让金额 BidCreditAmt 必须 BidDetails的二级参数 BidDetails中的转让金额总和等于CreditAmt
 		 huifuParams.setBorrowerCustId("6000060005590676"); //借款人客户号 BorrowerCustId 必须 BidDetails的二级参数借款人客户号  
		 huifuParams.setBorrowerCreditAmt("10.00");// 明细转让金额 BorrowerCreditAmt 必须 BidDetails的二级参数 明细转让金额BorrowerDetails中的明细转让金额的总和等于BidCreditAmt
		 huifuParams.setPrinAmt("0.00");// 已还款金额 PrinAmt 必须 BidDetails的二级参数
		 huifuParams.setProId("gjbd2016111014");// 项目 ID ProId 必须 BidDetails的二级参数 ProdId是项目标的唯一标识，需要配合4.2.23标的信息输入接口
		 String str = "{&quot;BidDetails&quot;:[{&quot;BidOrdId&quot;:&quot;"+huifuParams.getBidOrdId()+"&quot;,"
		+ "&quot;BidOrdDate&quot;:&quot;"+huifuParams.getBidOrdDate()+"&quot;,&quot;BidCreditAmt&quot;:&quot;"+huifuParams.getBidCreditAmt()+"&quot;,"
		+"&quot;BorrowerDetails&quot;:[{&quot;BorrowerCustId&quot;:&quot;"+huifuParams.getBorrowerCustId()+"&quot;,"
		+ "&quot;BorrowerCreditAmt&quot;:&quot;"+huifuParams.getBorrowerCreditAmt()+"&quot;,"
		+ "&quot;PrinAmt&quot;:&quot;"+huifuParams.getPrinAmt()+"&quot;,"
		+"&quot;ProId&quot;:&quot;"+huifuParams.getProId()+"&quot;}]}]}";
		 huifuParams.setBrowserStr(str);
		 String str2 = "{\"BidDetails\":[{\"BidOrdId\":\""+huifuParams.getBidOrdId()+"\","
		+ "\"BidOrdDate\":\""+huifuParams.getBidOrdDate()+"\",\"BidCreditAmt\":\""+huifuParams.getBidCreditAmt()+"\","
		+"\"BorrowerDetails\":[{\"BorrowerCustId\":\""+huifuParams.getBorrowerCustId()+"\","
		+ "\"BorrowerCreditAmt\":\""+huifuParams.getBorrowerCreditAmt()+"\","
		+ "\"PrinAmt\":\""+huifuParams.getPrinAmt()+"\","
		+"\"ProId\":\""+huifuParams.getProId()+"\"}]}]}";
		 huifuParams.setBidDetails(str2);//明细转让金额  BidDetails 中的转让金额总和等于 CreditAmt 债权转让明细 BidDetails 必须
		 /*{"BidDetails":[{"BidOrdId":"20131220000002",
		 "BidOrdDate":"20131220","BidCreditAmt":"1.00",
		 "BorrowerDetails":[{"BorrowerCustId":"6000060000059546",
		 "BorrowerCreditAmt":"1.00",
		 "PrinAmt":"0.00",
		 "ProId":"0000000000000001"}]},{"BidOrdId":"201301031130001","BidOrd
		 Date":"20130103","BidCreditAmt":"2.00","Borr
		 owerDetails":[{"BorrowerCustId":"6000060000
		 059546","BorrowerCreditAmt":"2.00","PrinAm
		 t":"0.00","ProId":"0000000000000001"}]}]}
		 (请注意 BidDetails 的 size 最大为 10)*/
		 huifuParams.setFee("1.00");// 扣款手续费 Fee 必须 放款或扣款的手续费
		 huifuParams.setDivAcctId("SDT000001");//分账账户号 DivAcctId  必须 DivDetails 参数下的二级参数
		 huifuParams.setDivAmt("1.00"); //分账金额 DivAmt 必须 DivDetails 参数下的二级参数
		 String detailsStr = "[{&quot;DivAcctId&quot;:&quot;"+huifuParams.getDivAcctId()+"&quot;,"+
							 "&quot;DivAmt&quot;:&quot;"+huifuParams.getDivAmt()+"&quot;}]";
		 
		String detailsStr2 = "[{\"DivAcctId\":\""+huifuParams.getDivAcctId()+"\","+
							 "\"DivAmt\":\""+huifuParams.getDivAmt()+"\"}]";							 
        huifuParams.setDivDetails(detailsStr2);// 分账账户串 DivDetails  可选
		 /*数据格式：
		 [{"DivAcctId":"MDT000023" ，
		 "DivAmt":"1.00"} ，
		 {"DivAcctId":"MDT000024"，"DivAmt":"2.00"}，
		 {"DivAcctId":"MDT000025"，"DivAmt":"3.00"}]
		 当 Fee！=0 时是必填项
		  */
		 huifuParams.setBuyCustId("6000060005590202"); // 承接人客户号 BuyCustId 必须 债权转让承接人客户号
		 huifuParams.setOrdId(StringUtil.getNo()); // 订单号 OrdId 必须 由商户的系统生成，必须保证唯一，请使用纯数字
		 huifuParams.setOrdDate(StringUtil.formatDate(new Date(), "yyyyMMdd")); // 订单日期 OrdDate 定长 8 位的 必须 格式为 YYYYMMDD，例如：20130307
//		 huifuParams.setRetUrl(""); // 页面返回 URL RetUrl
		 huifuParams.setBgRetUrl(StringUtil.getBasePath(request)+"/huifu/CreditAssign/callbackCreditAssign.action");//商户后台应答地址 BgRetUrl 必须
//		 huifuParams.setLcId(""); // 挂牌债权 ID LcId 本次挂牌转让的债权编号。编号由商户定义，不可以重复。
//		 huifuParams.setTotalLcAmt(""); //挂牌债权总金额 TotalLcAmt 可选 本次挂牌转让的债权总金额，传挂牌债权 ID 时，挂牌债权总金额为必须
		 String merPriv = HttpClientHandler.getBase64Encode(huifuParams.getProId());
 		 huifuParams.setMerPriv(merPriv);// 商户私有域 MerPriv
//		 String reqExtStr = "{\"ProId\"}";
//		 String reqExtStr1 = "{&quot;ProId&quot;}";
// 		 huifuParams.setReqExt(reqExtStr);// 入参扩展域 ReqExt
//		 huifuParams.setReqExtStr(reqExtStr1);// 入参扩展域 ReqExt
		 try {
			getHuifuParams(huifuParams, request, response,detailsStr);
		} catch (ServletException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		 
  	 }

	 @RequestMapping("/callbackCreditAssign")
	 public String callbackCreditAssign(HuifuParams huifuParams ,HttpServletRequest request,HttpServletResponse response){
		/*消息类型 CmdId + 应答返回码 RespCode + 应答描述 RespDesc + 商户客户号 MerCustId + 转让人客户号 SellCustId + 转让金额 CreditAmt
 	     + 承接金额CreditDealAmt + 扣款手续费 Fee + 承接人客户号 BuyCustId + 订单号 OrdId + 订单日期 OrdDate +  页面返回 URL RetUrl
 		+ 商户后台应答地址 BgRetUrl + 挂牌债权 ID LcId + 挂牌债权总金额 TotalLcAmt + 商户私有域 MerPriv + 返参扩展域 RespExt +签名 ChkValue*/
  		 System.out.println("=======getRespCode==========="+huifuParams.getRespCode());
		 System.out.println("=======getRespDesc==========="+huifuParams.getRespDesc());
		 System.out.println("=======getCmdId=============="+huifuParams.getCmdId());
		 System.out.println("=======getMerCustId=========="+huifuParams.getMerCustId());
		 System.out.println("=======getSellCustId========="+huifuParams.getSellCustId());
		 System.out.println("=======getCreditAmt=========="+huifuParams.getCreditAmt());
		 System.out.println("=======getCreditDealAmt======"+huifuParams.getCreditDealAmt());
		 System.out.println("=======getFee================"+huifuParams.getFee());
		 System.out.println("=======getBuyCustId=========="+huifuParams.getBuyCustId());
		 System.out.println("=======getOrdId=============="+huifuParams.getOrdId());
		 System.out.println("=======getOrdDate============"+huifuParams.getOrdDate());
		 System.out.println("=======getRetUrl============="+huifuParams.getRetUrl());
		 System.out.println("=======getBgRetUrl==========="+huifuParams.getBgRetUrl());
		 System.out.println("=======getLcId==============="+huifuParams.getLcId());
		 System.out.println("=======getTotalLcAmt========="+huifuParams.getTotalLcAmt());
		 System.out.println("=======getMerPriv============"+huifuParams.getMerPriv());
		 String merPriv = request.getParameter("MerPriv");
		 String str = "";
		 try {
			 str = URLDecoder.decode(merPriv, "UTF-8");
			 str =  HttpClientHandler.getBase64Decode(str);
			System.out.println(str+"=======getMerPriv 解密后============"+URLDecoder.decode(huifuParams.getMerPriv(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
 			e.printStackTrace();
		}
		 System.out.println(str);
		 System.out.println("=======getRespExt============"+huifuParams.getRespExt());
		 System.out.println("=======getChkValue==========="+huifuParams.getChkValue());
		 
		 
		 /*CmdId + RespCode + MerCustId + SellCustId + CreditAmt + CreditDealAmt +Fee + BuyCustId +
		  *OrdId + OrdDate +RetUrl + BgRetUrl + MerPriv+ RespExt +LcId + TotalLcAmt
		  * */
		 return null;
	 }
	/**
	 * 
	* @Title: getHuifuParams 
	* @Description: TODO(参数设置) 
	* @param @param huifuParams
	* @param @param request
	* @param @param response
	* @param @throws ServletException
	* @param @throws IOException  参数说明 
	* @return void    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public void getHuifuParams(HuifuParams huifuParams, HttpServletRequest request,HttpServletResponse response, String str34) throws ServletException, IOException{
		/*Version + CmdId + MerCustId + SellCustId + CreditAmt + 
		  CreditDealAmt + BidDetails + Fee + DivDetails + BuyCustId 
		  + OrdId +OrdDate + RetUrl + BgRetUrl + MerPriv+ ReqExt + 
		  PageType + LcId + TotalLcAmt*/
		StringBuffer buffer = new StringBuffer();
		buffer.append(StringUtils.trimToEmpty(huifuParams.getVersion()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getCmdId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerCustId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getSellCustId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getCreditAmt()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getCreditDealAmt()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBidDetails()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getFee()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getDivDetails()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBuyCustId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getOrdDate()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getReqExt()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getPageType()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getLcId()));
		buffer.append(StringUtils.trimToEmpty(huifuParams.getTotalLcAmt()));
 		String str = buffer.toString();
 		MD5 md5 = new MD5();
 		String str1 = md5.getMD5Info(str).toLowerCase();
 		System.out.println("str1==="+str1);
 		String ChkValue ;
 		try {
			ChkValue = SignUtils.encryptByRSA(str1);
			System.out.println("ChkValue==="+ChkValue);
			if(StringUtil.isNotEmpty(ChkValue)){
				huifuParams.setChkValue(ChkValue);
			}
			System.out.println("ChkValue==="+huifuParams.getChkValue());
 		} catch (Exception e) {
 			e.printStackTrace();
		}
		request.setAttribute("Version", huifuParams.getVersion());//版本号
		request.setAttribute("CmdId", huifuParams.getCmdId());//消息类型
		request.setAttribute("MerCustId", huifuParams.getMerCustId());//商户客户号
		request.setAttribute("SellCustId", huifuParams.getSellCustId());//转让人客户号
		request.setAttribute("CreditAmt", huifuParams.getCreditAmt());//转让金额
		request.setAttribute("CreditDealAmt", huifuParams.getCreditDealAmt());//承接金额
		request.setAttribute("BidDetails", huifuParams.getBrowserStr());//债权转让明细
		request.setAttribute("BidOrdId", huifuParams.getBidOrdId());//被转让的投标订单号
		request.setAttribute("BidOrdDate", huifuParams.getBidOrdDate());//被转让的投标订单日期
		request.setAttribute("BidCreditAmt", huifuParams.getBidCreditAmt());//转让金额
		request.setAttribute("BorrowerCustId", huifuParams.getBorrowerCustId());//借款人客户号
		request.setAttribute("BorrowerCreditAmt", huifuParams.getBorrowerCreditAmt());//明细转让金额
		request.setAttribute("PrinAmt", huifuParams.getPrinAmt());//已还款金额
		request.setAttribute("ProId", huifuParams.getProId());//项目 ID
		request.setAttribute("Fee", huifuParams.getFee());//扣款手续费
		request.setAttribute("DivDetails", str34);//分账账户串
		request.setAttribute("DivAcctId", huifuParams.getDivAcctId());//分账账户号
		request.setAttribute("DivAmt", huifuParams.getDivAmt());//分账金额
		request.setAttribute("BuyCustId", huifuParams.getBuyCustId());//承接人客户号
		request.setAttribute("OrdId", huifuParams.getOrdId());//订单号
		request.setAttribute("OrdDate", huifuParams.getOrdDate());//订单日期
		request.setAttribute("RetUrl", huifuParams.getRetUrl());//页面返回 URL
		request.setAttribute("BgRetUrl", huifuParams.getBgRetUrl());//商户后台应答地址
		request.setAttribute("LcId", huifuParams.getLcId());//挂牌债权 ID
		request.setAttribute("TotalLcAmt", huifuParams.getTotalLcAmt());//挂牌债权总金额
		request.setAttribute("MerPriv", huifuParams.getMerPriv());//商户私有域
		request.setAttribute("ReqExt", huifuParams.getReqExtStr());//入参扩展域
		request.setAttribute("PageType", huifuParams.getPageType());//页面类型
		request.setAttribute("ChkValue", huifuParams.getChkValue());//签名
		request.setAttribute("CharSet", huifuParams.getCharSet());//编码
		
 		request.getRequestDispatcher("/WEB-INF/pages/CreditAssign/CreditAssign.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
