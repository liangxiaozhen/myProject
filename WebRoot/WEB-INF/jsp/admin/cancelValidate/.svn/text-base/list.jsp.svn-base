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

    <title>用户屏蔽安全验证列表</title>
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
        //重置
        function chongzhi() {
            $("#username").val("");
            $("#ip").val("");
            $("#cookie").val("");
            $("#email").val("");
            $("#mobile").val("");
        }

        //跳转详情页面
        function details(id) {
            var action = "detail.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#detail-modal-body").html(data);
            }
            $.post(action, params, callback);
        }

        //跳转删除页面
        function deleteUser(id) {
            var action = "findDelete.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#delModal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#del-modal-body").html(data);
            }
            $.post(action, params, callback);
        }

        //删除
        function delByID() {
            var id = $("#del-name-id").val();
            var action = "deleteUser.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                alert(data);
                $("#form-select")[0].reset();
                queryAllPerson("", "");
            };
            $.post(action, params, callback, 'json');
        }

        //跳转到添加页面
        function addUser() {
            var action = "findAddUser.action"
            var callback = function (data) {
                $("#addUser-modal-body").html(data);
            };
            $.post(action, null, callback);
        }

        function insertAddUser() {
            var canceltype = $("#CancelType").val();
            var username = $("#usernameCancelValidate").val();
            var params = {"canceltype": canceltype, "username": username};
            var action = "insertUser.action";
            var callback = function (data) {
                alert(data);
                queryAllPerson("", "");
            };
            $.post(action, $("#insert-form").serialize(), callback, 'json');
        }

        //跳转到编辑页面
        function updateUser(id) {
            var action = "findUpdate.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#update-modal-body").html(data);
            }
            $.post(action, params, callback);
        }

        //编辑之后进行保存
        function update() {
            var id = $("#update-name-id").val();
            var remark = $("#update-remark-text").val();
            var canceltype = $("#update-nametype-select").val();

            var params = {
                "id": id, "remark": remark, "canceltype": canceltype
            };
            var action = "updateCancelUser.action";
            var callback = function (data) {
                alert(data);
                queryAllPerson("", "");
            };
            $.post(action, params, callback, 'json');
        }


        //查询所有数据
        function queryAllPerson(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#form-select").submit();
        }

    </script>
</head>

<body style="font-family: '微软雅黑'; ">
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <h3>用户屏蔽安全验证表</h3>
            <br>
            <form id="form-select" method="post" action="${action}">
                <input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}"/>
                <input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}"/>
                <label>用户名&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="username" id="username"
                                                                  value="${cancelValidate.username}"
                                                                  placeholder="---请输入用户名搜索---">&nbsp;&nbsp;&nbsp;
                <label>IP&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="ip" id="ip"
                                                                 value="${cancelValidate.ip}"
                                                                 placeholder="---请输入ip搜索---">&nbsp;&nbsp;&nbsp;
                <label>cookie&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="cookie" id="cookie"
                                                                     value="${cancelValidate.cookie}"
                                                                     placeholder="---请输入cookie搜索---">&nbsp;&nbsp;&nbsp;
                <label>邮箱&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="email" id="email"
                                                                 value="${cancelValidate.email}"
                                                                 placeholder="---请输入邮箱搜索---">&nbsp;&nbsp;&nbsp;
                <label>手机号&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="mobile" id="mobile"
                                                                  value="${cancelValidate.mobile}"
                                                                  placeholder="---请输入手机搜索---">&nbsp;&nbsp;&nbsp;
                <input class="btn btn-default" type="submit" value="查询">&nbsp;&nbsp;
                <button class="btn btn-default" type="button" onclick="chongzhi()" value="重置">重置</button>
            </form>
            <br>
            <div class="form-group" style="margin-left: 92%">
                <button class="btn btn-default" data-toggle="modal"
                        data-backdrop="static" data-target="#addUserModal" id="add-btn"
                        onclick="addUser()" data-id="${cancelValidate.id }">用户添加
                </button>
            </div>
            <table class="table table-hover" id="persionList_table">
                <thead>
                <tr style="background: #ccc;vertical-align: text-top!important;" class="text-center">
                    <td>编号</td>
                    <td>用户名</td>
                    <td>IP</td>
                    <td>cookie</td>
                    <td>手机编号</td>
                    <td>手机</td>
                    <td>邮箱</td>
                    <td>取消验证类型</td>
                    <td>备注</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list}" var="cancelValidate" varStatus="status">
                    <tr class="text-center">
                        <td>${status.count}</td>
                        <td>${cancelValidate.username}</td>
                        <td>${cancelValidate.ip}</td>
                        <td>${cancelValidate.cookie}</td>
                        <td>${cancelValidate.mac}</td>
                        <td>${cancelValidate.mobile}</td>
                        <td>${cancelValidate.email}</td>
                        <td>
                            <c:choose>
                                <c:when test="${cancelValidate.canceltype ==1}">登录验证码</c:when>
                                <c:when test="${cancelValidate.canceltype ==2}">注册验证码</c:when>
                                <c:when test="${cancelValidate.canceltype ==3}">密码控件</c:when>
                                <c:when test="${cancelValidate.canceltype ==4}">登录U盾</c:when>
                            </c:choose>
                        </td>

                        <td>${cancelValidate.remark}</td>
                        <td>
                            <button class="btn btn-default" data-toggle="modal" data-target="#detailsModal"
                                    id="details-btn"
                                    data-backdrop="static" onclick="details('${cancelValidate.id}')">详情
                            </button>
                            <button class="btn btn-default" data-toggle="modal" data-target="#updateModal"
                                    id="update-btn"
                                    data-backdrop="static"
                                    onclick="updateUser('${cancelValidate.id}')">编辑
                            </button>
                            <button class="btn btn-default" data-toggle="model" data-target="#delModal" id="del-btn"
                                    data-backdrop="static" onclick="deleteUser('${cancelValidate.id}')">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="page_div">
                <%@ include file="../../common/pagehelper.jsp" %>
            </div>
        </div>
    </div>
</div>
<!-- 引入模态框 -->
<%@ include file="../../common/cancelValidateModal.jsp" %>
</body>
</html>