<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<!-- 设定html里面为简体中文 -->
<html lang="zh-CN">
<head>
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
    <meta name="viewport" content="width=device-width,user-scalable=no,initial-scale=1">
    <!--页面的描述-->
    <meta http-equiv="description" content="This is my page">
    <title>风控用户展示</title>
    <link href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
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
        //重置查询条件
        function chongzhi() {
            $("#userRiskType").val("");
            $("#userRiskUsername").val("");
        }

        //跳转详情页面
        function detailUI(id) {
            var action = "detailUI.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#detail-modal-body").html(data);
            }
            $.post(action, params, callback);
        }


        //查询所有数据
        function queryAllPerson(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#form-select").submit();
        }


    </script>
</head>
<body>
<body style="font-family: '微软雅黑'; ">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>风险用户展示表</h3>
            <br>
            <form id="form-select" method="post" action="${action}">
                <input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}"/>
                <input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}"/>
                <ul class="list-inline">
                    <li>
                        <label>选择名单：</label>
                        <select name="type" id="userRiskType">
                            <option value="">---请选择---</option>
                            <option value="4" <c:if test="${userRisk.type eq 4}">selected="selected"</c:if>>风险名单
                            </option>
                            <option value="3" <c:if test="${userRisk.type eq 3}">selected="selected"</c:if>>应急改密
                            </option>
                            <option value="2" <c:if test="${userRisk.type eq 2}">selected="selected"</c:if>>黑名单</option>
                            <option value="1" <c:if test="${userRisk.type eq 1}">selected="selected"</c:if>>白名单</option>
                        </select>
                        <%-- <li><button type="button" class="btn btn-default" onclick="findAll('${user.type}')"">查询全部</button></li> --%>
                    </li>
                    <li><label>用户名：</label>
                        <input type="text" id="userRiskUsername"
                               name="username"
                               value="${userRisk.username}"
                               placeholder="---请输入用户名搜索---"/></li>
                    <li><input class="btn btn-default" type="submit" value="查询">&nbsp;&nbsp;
                        <button class="btn btn-default" type="button" onclick="chongzhi()" value="重置">重置</button>
                    </li>
                </ul>
            </form>
            <br>

            <table class="table table-hover" id="persionList_table">
                <thead>
                    <tr style="background: #ccc;vertical-align: text-top!important;"
                        class="text-center">
                        <td>编号</td>
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
                <c:forEach items="${pagehelper.list}" var="user" varStatus="status">
                    <tr class="text-center">
                        <td>${status.count}</td>
                        <td>${user.username}</td>
                        <td>${user.ip}</td>
                        <td>${user.cookie}</td>
                        <td>${user.mac}</td>
                        <td>${user.mobile}</td>
                        <td>${user.email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.type ==1}">黑名单</c:when>
                                <c:when test="${user.type ==2}">风险名单</c:when>
                                <c:when test="${user.type ==3}">应急改密</c:when>
                                <c:when test="${user.type ==4}">白名单</c:when>
                            </c:choose>
                        </td>
                        <td>${user.remark}</td>
                        <td>
                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#detailsModal" data-backdrop="static"
                                    onclick="detailUI('${user.id}')">详情
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty pagehelper.list}">
                    <tr>
                        <td colspan="100" class="text-center">没有相关数据</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            <div class="page_div">
                <%@ include file="../../common/pagehelper.jsp" %>
            </div>
        </div>
    </div>
</div>
<!-- 引入模态框 -->
<%@ include file="../../common/userRiskmodal.jsp" %>


</body>
</html>