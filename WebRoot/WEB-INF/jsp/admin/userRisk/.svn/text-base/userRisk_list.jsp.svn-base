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

    <title>用户风控列表</title>
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

        //跳转删除页面
        function deleteUI(id) {
            var action = "deleteUI.action";
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

        //删除(模态框)
        function delByID() {
//	    opid==userRisk.id
            var opid = $("#del-rname-id").val();
//      uid==userRisk.baseid
            var uid = $("#del-rname-baseid").val();
            var action = "deleteById.action";
            var params = {
                "opid": opid,
                "uid": uid
            };
            var callback = function (data) {
                alert(data);
                $("#form-select")[0].reset();
                queryAllPerson("", "");
            };
            $.post(action, params, callback, 'json');
        }

        //跳转到编辑页面
        function updateUI(id) {
            var action = "updateUI.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#update-modal-body").html(data);
            }
            $.post(action, params, callback);
        }

        //编辑更新后保存
        function update() {
            var opid = $("#update-rname-id").val();
            var uid = $("#update-rname-baseid").val();
            var type = $("#update-nametype-select").val();
            var username = $("#update-username").val();
            var ip = $("#update-rname-ip").val();
            var cookie = $("#update-rname-cookie").val();
            var mac = $("#update-rname-mac").val();
            var mobile = $("#update-rname-mobile").val();
            var remark = $("#update-remark-text").val();
            var email = $("#update-rname-email").val();
            var params = {
                "opid": opid, "uid": uid, "type": type, "username": username, "ip": ip, "cookie": cookie,
                "mac": mac, "mobile": mobile, "remark": remark, "email": email
            };
            var action = "updateUserRisk.action";

            var callback = function (data) {
                alert(data);
                queryAllPerson("", "");
            }
            $.post(action, params, callback, 'json');
        }

        //跳转增加风险因子页面
        function fengXianYinZhiAddUI() {
            var action = "fengXianYinZhiAddUI.action";
            var callback = function (data) {
                $("#insert-modal-body").html(data);
            };
            $.post(action, null, callback);
        }

        //保存新增的风险因子(可任意添加)
        function insert() {
            var fengXianYinZhiType = $("#insert-nametype-select").val()
            if(fengXianYinZhiType==""){
                alert("请选择风险因子的类别");
                return false;
            }
            /*      <option value="2">风险名单</option>
            <option value="3">应急改密名单</option>
            <option value="1">黑名单</option>
            <option value="4">白名单</option>*/
            /**/
            if (fengXianYinZhiType == "1" || fengXianYinZhiType == "2" || fengXianYinZhiType == "4") {
                var username = $("#insert-username-text").val();
                var ip = $("#insert-ip-text").val();
                var mobile = $("#insert-mobile-text").val();
                var email = $("#insert-email-text").val();
                var cookie = $("#insert-cookie-text").val();
                var mac = $("#insert-mac-text").val();
                var remark = $("#insert-remark-text").val();
                var params = {
                    "type": fengXianYinZhiType,
                    "username": username,
                    "ip": ip,
                    "mobile": mobile,
                    "email": email,
                    "cookie": cookie,
                    "mac": mac,
                    "remark": remark
                };
            } else if (fengXianYinZhiType == "3") {
                var username = $("#insert-username-text").val();
                var mobile = $("#insert-mobile-text").val();
                var email = $("#insert-email-text").val();
                var remark = $("#insert-remark-text").val();
                var params = {
                    "type": fengXianYinZhiType,
                    "username": username,
                    "mobile": mobile,
                    "email": email,
                    "remark": remark
                };
            }
            /*风险因子增加*/
            var action = "batchInsert.action";
            var callback = function (data) {
                alert(data);
                queryAllPerson("", "");
            };
            $.post(action, params, callback, 'json');
        }

        //重置
        function chongzhi() {
            $("#username").val("");
            $("#ip").val("");
            $("#cookie").val("");
            $("#email").val("");
            $("#mobile").val("");

        }

        //跳转用户添加页面
        function addUserUI() {
            var action = "addUserUI.action";
            var callback = function (data) {
                $("#addUser-modal-body").html(data);
            };
            $.post(action, null, callback);
        }

        /*用户添加*/
        function insertUserRisk() {
            //获得前端输入框中填写的参数
            var type = $("#userRiskType").val();
            var username = $("#usernameUserRisk").val();
            //后台接收
            var params = {"type": type, "username": username};
            var action = "save.action";
            var callback = function (data) {
                alert(data);
                queryAllPerson("", "");
            };
            $.post(action, $("#insert-form").serialize(), callback, 'json');
        }

        //跳转到定向添加页面
        function dingXiangAddUI() {
            var action = "dingXiangAddUI.action";
            var callback = function (data) {
                $("#dingXiang-modal-body").html(data);
            };
            $.post(action, null, callback);
        }
         //跳转到等级添加页面
        function dengjiAddUI() {
            var action = "dengjiAddUI.action";
            var callback = function (data) {
                $("#dengji-modal-body").html(data);
            };
            $.post(action, null, callback);
        }

        //定向添加
        function dingXiangUserRisk() {
            //获得前端输入框中的userRisk中的类型(type)
            var type = $("#dingXiangType").val();
            //获得前端输入框中的specialNameList的定向编号(businessNo)
            var businessNo = $("#businessNo").val();

            //后台接收数据
            var params = {"type": type, "businessNo": businessNo};
            var action = "addDingXiang.action";
            //回调
            var callback = function (data) {
                alert(data);
                queryAllPerson("", "");
            };
            $("#dingXiang-form").serialize();
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

            <h3>用户风险控制表</h3>
            <br>
            <form id="form-select" method="post" action="${action}">
                <input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}"/>
                <input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}"/>
                <label>用户名&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="username" id="username"
                                                                  value="${userRisk.username}"
                                                                  placeholder="---请输入用户名搜索---">&nbsp;&nbsp;&nbsp;
                <label>IP&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="ip" id="ip" value="${userRisk.ip}"
                                                                 placeholder="---请输入ip搜索---">&nbsp;&nbsp;&nbsp;
                <label>cookie&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="cookie" id="cookie"
                                                                     value="${userRisk.cookie}"
                                                                     placeholder="---请输入cookie搜索---">&nbsp;&nbsp;&nbsp;
                <label>邮箱&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="email" id="email"
                                                                 value="${userRisk.email}" placeholder="---请输入邮箱搜索---">&nbsp;&nbsp;&nbsp;
                <label>手机号&nbsp;:&nbsp;&nbsp;&nbsp;</label><input type="text" name="mobile" id="mobile"
                                                                  value="${userRisk.mobile}"
                                                                  placeholder="---请输入手机搜索---">&nbsp;&nbsp;&nbsp;
                <input class="btn btn-default" type="submit" value="查询">
                &nbsp;&nbsp;
                <button class="btn btn-default" type="button" onclick="chongzhi()" value="重置">重置</button>
            </form>
            <br>
            <div class="form-group" style="margin-left: 65%">
                <button class="btn btn-default" data-toggle="modal"
                        data-target="#insertModal" id="add-btn1" data-backdrop="static"
                        onclick="fengXianYinZhiAddUI()">风险因子添加
                </button>
                <button class="btn btn-default"
                        data-toggle="modal"
                        data-target="#addUserModal" id="add-btn2" data-backdrop="static"
                        onclick="addUserUI()" data-id="${user.id }">用户添加
                </button>
                <button class="btn btn-default" data-toggle="modal"
                        data-target="#dingXiangModal" id="add-btn3" data-backdrop="static"
                        onclick="dingXiangAddUI()">定向添加
                </button>
                <button class="btn btn-default" data-toggle="modal"
                        data-target="#dengjiModal" id="add-btn3" data-backdrop="static"
                        onclick="dengjiAddUI()">按等级添加
                </button>
            </div>
            <table class="table table-hover" id="persionList_table" style="border: 1px solid silver;">
                <thead>
                    <tr style="background: #ccc;vertical-align: text-top!important;"
                        class="text-center">
                        <td>编号</td>
                        <td>用户名</td>
                        <td>手机</td>
                        <td>邮箱</td>
                        <td>风险类型</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list}" var="user" varStatus="status">
                    <tr class="text-center">
                        <td>${status.count}</td>
                        <c:choose>
                            <c:when test="${empty user.username}">
                                <td>--</td>
                            </c:when>
                            <c:otherwise>
                                <td>${user.username}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${empty user.mobile}">
                                <td>--</td>
                            </c:when>
                            <c:otherwise>
                                <td>${user.mobile}</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${empty user.email}">
                                <td>--</td>
                            </c:when>
                            <c:otherwise>
                                <td>${user.email}</td>
                            </c:otherwise>
                        </c:choose>

                        <td>
                            <c:choose>
                                <c:when test="${user.type ==4}">风险名单</c:when>
                                <c:when test="${user.type ==3}">应急改密名单</c:when>
                                <c:when test="${user.type ==2}">黑名单</c:when>
                                <c:when test="${user.type ==1}">白名单</c:when>
                            </c:choose>
                        </td>

                        <td>
                            <button class="btn btn-default" data-toggle="modal" data-target="#detailsModal"
                                    id="details-btn"
                                    data-backdrop="static" onclick="detailUI('${user.id}')">详情
                            </button>
                            <button class="btn btn-default" data-toggle="modal" data-target="#updateModal"
                                    id="update-btn"
                                    data-backdrop="static"
                                    onclick="updateUI('${user.id}')">编辑
                            </button>
                            <button class="btn btn-default" data-toggle="model" data-target="#delModal" id="del-btn"
                                    data-backdrop="static" onclick="deleteUI('${user.id}')">删除
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
<%@ include file="../../common/userRiskmodal.jsp" %>
</body>
</html>