<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册 - 干将网贷</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
<style type="text/css">
.move .jiner {
	margin: 20px 0;
}

.move .jiner p {
	display: inline-block;
}

.move .jiner p span.now_num input {
	border: 1px solid #ddd;
	margin: 0 10px 0 0;
	height: 26px;
	line-height: 26px;
	text-indent: 2em;
}

.jiner label {
	width: 120px;
	text-align: right;
	margin: 0 10px 0 0;
}

.move span.btn {
	border-radius: 5px;
	font-size: 16px;
	color: #fff;
	width: 180px;
	height: 40px;
	background: #31b0ee;
	display: block;
	cursor: pointer;
	text-align: center;
	line-height: 40px;
	margin: 20px 0px 0 124px;
}
</style>
</head>
<body>
	<!--header-->
	<%@include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!-- 左侧 -->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- 右侧 -->
			<form method="post" action="testwithdraw.action" id="w-form">
				<div class="fl pad_30 wid_w900 min_height tx">
					<div class="loadDiv fc_9 clearfix">
						<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我要提现</span>
					</div>
					<div class="move">
						<div class="jiner">
							<label>可提现金额：</label>
							<p>
								<span class="money_num">${avlbalance }</span>元
							</p>
						</div>
						<div class="jiner">
							<label><span style="color: red; padding: 0 10px">*</span>提现金额：</label>
							<p>
								<span class="now_num"><input type="text"
									onkeyup="value=value.replace(/[^0-9Xx|.]/g,'')" name="amount"></span>元
							</p>
						</div>
						<div class="jiner">
							<label></label>
							<p class="hide" style="color: red; display: none;">亲，体现金额不能为空哦！</p>
						</div>
						<span class="btn">确认提交</span>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$(".now_num input").blur(function() {
						var text = $(".now_num input").val();
						if (text == "") {
							$(".hide").show();
						} else {
							$(".hide").hide();
						}
					})

					$("span.btn").click(function() {
						var text = $(".now_num input").val();
						if (text == "") {
							$(".hide").show();
						} else {
							$(".hide").hide();
							$("#w-form").submit();
						}
					});
					$(".side_nav").find("dl").siblings().removeClass(
							"navcurron").eq(1).addClass("navcurron");
				})
	</script>
	<%@include file="/WEB-INF/jsp/common/nwdUserFooter.jsp"%>
</body>
</html>