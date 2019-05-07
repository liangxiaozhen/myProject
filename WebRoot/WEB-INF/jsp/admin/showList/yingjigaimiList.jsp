<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>

<head>
    <base href="<%=basePath%>">
    <!--设定页面使用的字符集-->
    <meta charset="UTF-8">
    <!-- 告诉IE使用最新的引擎渲染网页 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设定禁止浏览器从本地机的缓存中调阅页面内容，设定后一旦离开网页就无法从Cache中再调出用法-->
    <meta http-equiv="pragma" content="no-cache">
    <!--清除缓存再访问这个网站要重新下载-->
    <meta http-equiv="cache-control" content="no-cache">
    <!--用于设定网页的到期时间。一旦网页过期，必须到服务器上重新传输-->
    <meta http-equiv="expires" content="0">
    <!--关键字给搜索引擎用的-->
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <!-- 按原始尺寸显示不缩放 -->
    <meta name="viewport"
          content="width=device-width,user-scalable=no,initial-scale=1">
    <!--页面的描述-->
    <meta http-equiv="description" content="This is my page">

    <!-- 应急改密名单 -->
    <link
            href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
            rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
          rel="stylesheet" type="text/css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/sg/sg.js"></script>
    <!-- 添加样式 -->
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }

        input {
            text-align: center;
        }
    </style>

    <script type="text/javascript">
        //跳转详情页面
        function detailUI(id) {
            var action = "admin/userRisk/detailUI.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#detail-modal-body").html(data);
            }
            $.post(action, params, callback);
        }
    </script>
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-lg-12 column">
            <h2>风控应急改密名单</h2>
            <br>
            <form role="form" method="post" id="baseform"
                  action="${pageContext.request.contextPath}/UserRisk/ZymdListShow.action">
                <input type="hidden" id="pageSize" name="pageSize"> <input
                    type="hidden" id="pageNo" name="pageNo">
                <div class="col-md-5 input-group"></div>
            </form>
            <table class="table table-hover">
                <thead>
                <tr
                        style="background: #ccc; vertical-align: text-top !important;"
                        class="text-center">
                    <td>序号</td>
                    <td>用户名</td>
                    <td>IP</td>
                    <td>cookie</td>
                    <td>手机编号</td>
                    <td>手机</td>
                    <td>邮箱</td>
                    <td>风险类型</td>
                    <td>备注</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <!-- varStatus="status" 循环产生升序序号 -->
                <c:forEach items="${pagehelper.list}" var="user"
                           varStatus="status">
                    <tr class="text-center">
                        <td>${status.count}</td>
                        <td>${user.username == null ? '---' : user.username}</td>
                        <td>${user.ip == null ? '---' : user.ip}</td>
                        <td>${user.cookie == null ? '---' : user.cookie}</td>
                        <td>${user.mac == null ? '---' : user.mac}</td>
                        <td>${user.mobile == null ? '---' : user.mobile}</td>
                        <td>${user.email == null ? '---' : user.email}</td>
                        <td><c:choose>
                            <c:when test="${user.type ==4}">风险名单</c:when>
                            <c:when test="${user.type ==3}">应急改密名单</c:when>
                            <c:when test="${user.type ==2}">黑名单</c:when>
                            <c:when test="${user.type ==1}">白名单</c:when>
                        </c:choose></td>
                        <td>${user.remark == null ? '---' : user.remark}</td>
                        <td>
                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#detailsModal" data-backdrop="static"
                                    onclick="detailUI('${user.id}')">详情
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 导入分页 -->
    <div id="page_div">
        <%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
    </div>
</div>


<!-- 引入模态框 -->
<%@ include file="../../common/userRiskmodal.jsp" %>

</body>
</html>