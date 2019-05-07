<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 注意文件的引入顺序 -->
<link href="<%=basePath%>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate.js"></script>

<title>${ userBonusPoints }</title>
</head>
<body style="padding: 50px">
	<div class="container" id="bonusInfo">
		<div id="myTabBonus" class="tab-content">
			<div class="col-md-6 column tab-pane fade in active" id="bonusId">

				<table class="table">
					<caption>用户账户积分</caption>
					<tbody>

						<tr>
							<td>用户系统积分</td>
							<td>${ userAccount.bonuspoints  }</td>
							<td>分</td>
						</tr>
						<tr>
							<td>用户交易积分</td>
							<td>${ userAccount.tradePoints  }</td>
							<td>分</td>
						</tr>
						<tr>
							<td>可用交易积分</td>
							<td>${ userAccount.avlTradePoints  }</td>
							<td>分</td>
						</tr>
						<tr>
							<td>冻结交易积分</td>
							<td>${ userAccount.freezeTradePoints  }</td>
							<td>分</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>