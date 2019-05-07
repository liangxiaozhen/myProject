<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="account_table2">
		<form id="withcashform" method="POST">
			<table id="tab1" class="table" width="100%" cellpadding="0"
				cellspacing="0">
				<tbody>
					<tr>
						<td class="left deposit_title">提现至银行 ：</td>
						<td class="right deposit_content">${bankName }&nbsp;&nbsp;${cardNo }</td>
					</tr>
					<tr>
						<td class="left deposit_title">可以提现金额 ：</td>
						<td class="right deposit_content">￥
							${df.format(userAccount.avlbalance)}</td>
					</tr>
					<tr>
						<td class="left deposit_title">提现金额 ：</td>
						<td class="right deposit_content"><input type="text"
							name="money" id="txt_Amount" onblur="checkNum(this)"
							onkeyup="clearNoNum(event,this)"> <span
							style="color: red;" id="txt_Amount_span"></span></td>
					</tr>
					<!--  
							<tr>
								<td class="left deposit_title">手续费 ：</td>
								<td class="right deposit_content"><b>￥<span id="fee">0.00</span></b></td>
							</tr>
							<tr>
								<td class="left deposit_title">实际到账金额 ：</td>
								<td class="right deposit_content"><b>￥<span
										id="reachAmount">0.00</span></b></td>
							</tr>-->
					<tr>
						<td class="left deposit_title">提现优惠券 ：</td>
						<td class="right deposit_content"><select id="select"><option>暂无优惠券可用</option></select></td>
					</tr>
					<!-- 
							<tr>
								<td class="left deposit_title">交易密码 ：</td>
								<td class="right deposit_content"><input type="password"
									id="txt_Password"> <a href="${pageContext.request.contextPath }/user/securitycenter/list.action">[忘记密码]</a> &nbsp;&nbsp;
									<span style="color:red;" id="txt_Password_span"></span>
									</td>
							</tr> -->
					<tr>
						<td class="left deposit_title"><span class="verification-1">到账方式
								：</span></td>
						<td class="right deposit_content"><input type="radio"
							name="reachType" value="GENERAL" checked>普通提现 <input
							type="radio" name="reachType" value="FAST">快速提现 <input
							type="radio" name="reachType" value="IMMEDIATE">即时提现</td>
					</tr>
					<tr>
						<td class="left deposit_title"></td>
						<td class="right deposit_content"><input type="button"
							class="btn btn-warning" value="确认提现" onclick="withCash()"></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="account_notice" class="account_notice">
			<!-- Nav tabs -->
			<ul id="myTab3" class="nav nav-tabs bank-tab"
				style="width: 740px; height: auto;">
				<li class="active"><a href="#payment" data-toggle="tab"
					style="margin-right: 10px;">提现到账时间</a></li>
				<li class=""><a href="#wangyin" data-toggle="tab"
					style="margin-right: 10px;">提现手续费</a></li>
				<li class=""><a href="#tishi" data-toggle="tab"
					style="margin-right: 10px;">温馨提示</a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<div class="tab-pane fade active in" id="payment">
					<table class="table table-bordered  in fade text-center">
						<thead style="background: #edf0f2;">
							<tr>
								<th class="text-center">提现申请时间</th>
								<th class="text-center">一般提现（T+1）</th>
								<th class="text-center">快速提现（T+0）</th>
								<th class="text-center">即时提现（T+0）</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>周日24:00--周一14:30</td>
								<td>周二24:00前到账</td>
								<td>周一24:00前到账</td>
								<td>周一即时到账</td>
							</tr>
							<tr>
								<td>周一24:00--周二14:30</td>
								<td>周三24:00前到账</td>
								<td>周二24:00前到账</td>
								<td>周二即时到账</td>
							</tr>
							<tr>
								<td>周二24:00--周三14:30</td>
								<td>周四24:00前到账</td>
								<td>周三24:00前到账</td>
								<td>周三即时到账</td>
							</tr>
							<tr>
								<td>周三24:00--周四14:30</td>
								<td>周五24:00前到账</td>
								<td>周四24:00前到账</td>
								<td>周四即时到账</td>
							</tr>
							<tr>
								<td>周四24:00--周五14:30</td>
								<td>下周一24:00前到账</td>
								<td>周五24:00前到账</td>
								<td>周五即时到账</td>
							</tr>
							<tr>
								<td>周五24:00--周日14:30</td>
								<td>下周一24:00前到账</td>
								<td>下周一24:00前到账</td>
								<td>下周一即时到账</td>
							</tr>
						</tbody>
					</table>
					<p style="font-size: 14px; line-height: 1.5em; color: #f25446;">注：以上即时提现到账时间：中国银行、兴业、农业、渤海、上海、平安银行、华夏银行、工行、招行、建行、邮储、民生、交行、浦发、北京农商行、深发展、北京银行、杭州银行、南京银行、浙江泰隆商业银行实时到账。</p>
				</div>
				<div class="tab-pane fade" id="wangyin">
					<div>
						<p>提现费用说明：</p>
						<p>1.
							第三方支付平台将收取相应的费用，相关费用问题请咨询第三方支付公司。为了保证客户提现的资金安全，请提现时关注各提现方式的发起时间节点。具体费用如下：</p>
						<p>
							（1）汇付天下收取<span style="color: #f25446;">每笔提现金额 × 0.2% </span>提现服务费；
						</p>
						<p>（2）提现手续费：</p>
						<p style="text-indent: 2em;">A. 普通提现（建议提现方式）提现费用：2元/笔。</p>
						<p style="text-indent: 2em;">B. 快速提现费用：提现金额*0.5‰+2元/笔。</p>
						<p style="text-indent: 2em;">C.
							即时提现费用：提现金额*0.5‰+2元/笔,如在节假日前一个工作日使用，另按照提现金额*0.5‰*（节假日天数+1）+2元/笔收取。</p>
						<p>2.请确认账户中留有足够余额用于扣除提现费用；提现实际到账时间，依据第三方支付平台（汇付天下）及银行的政策可能存在差异,请耐心等待并随时关注账户余额。</p>
					</div>
				</div>
				<div class="tab-pane fade" id="tishi">
					<div>
						<p style="color: #f25446;">温馨提示：</p>
						<p>1、先将本人的银行卡与"汇付天下"托管账户进行绑定，否则无法提现；</p>
						<p>2、确保本人的银行卡开户人真实姓名与地标金融实名认证的真实姓名一致；</p>
						<p>3、个人用户当日网银充值资金当日不可提现（快捷充值不受限制），用户可提现余额=可用余额-当日网银充值金额，敬请留意个人可提现余额的金额变化。；</p>
						<p>
							4、输入正确的提现金额（<font color="#f25446">提现金额=可用余额-当日网银充值金额</font>），汇付天下将优先在可用余额中扣取相应提现手续费，余额不足以扣收取续费的情况下会在提现金额中扣收。
						</p>
						<p>5、提现成功会有短信或邮件通知您，具体通知方式请查看个人设置；</p>
						<p>6、若提现过程中遇到任何问题，请及时联系我们 400-811-3038。</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>