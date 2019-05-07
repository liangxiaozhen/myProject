<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>loanTypeObjectQuote_list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">
        function addloanmult() {
            var url = "${pageContext.request.contextPath}/admin/loanInfo/loanInfoPreset_ui.action";
            window.location.href = url;
        }
        //详情
        function toDetail(id) {
            var action = "${pageContext.request.contextPath}/admin/loanInfo/toDetailContentSet.action";
            var param = {
                "id": id
            }
            var callback = function (data) {
                $("#detailModal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body").html(data);
            }
            $.post(action, param, callback);
        }
        //增加
        function addMulti(multino) {
            var action = "${pageContext.request.contextPath}/admin/loanInfo/addMultiContentSetone.action";
            var param = {
                "multino": multino
            }
            var callback = function (data) {
                $("#addModal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body-add").html(data);
            }
            $.post(action, param, callback);
        }
        //增加
        function addContent() {
            var multino = $("#addMultiNo").val();
            var optionName = $("#addoptionName").val();
            var isneed = $("#addIsNeed").val();
            var action = "${pageContext.request.contextPath}/admin/loanInfo/addContent.action";
            var param = {
                "multino": multino,
                "optionname": optionName,
                "isneed": isneed
            }
            var callback = function (data) {
                alert(data);
                window.location.href = "${pageContext.request.contextPath}/admin/loanInfo/selectAllContentSet.action";
            }
            $.post(action, param, callback);
        }
        //添加
        function addContentSet() {
            var action = "${pageContext.request.contextPath}/admin/loanInfo/addMultiContentSetone.action";
            var callback = function (data) {
                $("#addModal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body-add").html(data);
            }
            $.post(action, callback);
        }

        //更新
        function toupdate(id) {
            var action = "${pageContext.request.contextPath}/admin/loanInfo/toupdateContentSet.action";
            var param = {
                "id": id
            }
            var callback = function (data) {
                $("#updateModal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body-update").html(data);
            }
            $.post(action, param, callback);
        }
        //更新
        function UpdateContent() {
            var id = $("#updateid").val();
            var multino = $("#updateMultiNo").val();
            var optionName = $("#updateoptionName").val();
            var isneed = $("#updateIsNeed").val();
            var action = "${pageContext.request.contextPath}/admin/loanInfo/UpdateContent.action";
            var param = {
                "id": id,
                "multino": multino,
                "optionname": optionName,
                "isneed": isneed
            }
            var callback = function (data) {
                window.location.href = "${pageContext.request.contextPath}/admin/loanInfo/selectAllContentSet.action";
            }
            $.post(action, param, callback);
        }

        //删除
        /* function todelete(id){
         var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");
         if(flag){
         var url="${pageContext.request.contextPath}/admin/loanInfo/toDeleteContent.action?id="+id;
         window.location.href=url;
         }
         } */

        //删除
        function todelete(id, index, isneed) {
            if (isneed == 1) {//启用状态
                alert("提示:项目处于启用状态,无法删除！");
            }
            if (isneed == 0) {
                $("#delspan").text(index);
                $("#modelID").val(id);
                $('#myModal').modal();
            }
        }
        //删除
        function toDeletByiscite() {
            var id = $("#modelID").val();
            var action = "${pageContext.request.contextPath}/admin/loanInfo/toDeleteContent.action";
            var param = {
                "id": id
            }
            var callback = function (data) {
                $('#myModal').modal("hide");
                if (data == "suc") {
                    alert("提示：删除成功");
                    window.location.href = "${pageContext.request.contextPath}/admin/loanInfo/selectAllContentSet.action";
                }
                if (data == "fail") {
                    alert("提示：此项目被引用,无法删除");
                }
            }
            $.post(action, param, callback, 'json');
        }

        //启用和停用
        function toopenmulti(id, str) {
            var action = "${pageContext.request.contextPath}/admin/loanInfo/toopenmulti.action";
            var param = {
                "id": id,
                "str": str
            }
            var callback = function (data) {
                alert(data);
                window.location.href = "${pageContext.request.contextPath}/admin/loanInfo/selectAllContentSet.action"
            }
            $.post(action, param, callback);
        }
        function queryAllPerson(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#form").submit();
        }
    </script>

</head>
<body>
<div class="container" style="width:80%;margin-top: 25px;">
    <form action="${pageContext.request.contextPath}/admin/loanInfo/selectAllContentSet.action" method="post"
          role="form" id="form">
        <input type="hidden" id="pageNum" name="pageNum"/>
        <input type="hidden" id="pageSize" name="pageSize"/>
        <ul class="list-inline" style="margin: 10px;">
            <li><label class="">用户名:</label></li>
            <li><input type="text" id="login" placeholder="输入用户姓名,可模糊查询" name="userBaseAccountInfo.loginname"
                       id="loginname"/>
            </li>
            <li><label>默认地址:</label>
                <select name="isdefaddress" id="isdefaddress">
                    <option value="" selected="selected">--请选择--</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </li>
            <li><input type="submit" value="查询" class="btn btn-default" id="btn"/></li>
            <li><input type="button" value="重置" class="btn btn-default" id="reset"/></li>
        </ul>
        <button class="btn btn-default" type="button" id="add" onclick="addContentSet()"
                style="float: right; margin-right: 20px;margin-bottom: 5px;">添加
        </button>
    </form>
    <c:if test="${!empty pagehelper.list}">
        <table class="table table-hover">
            <thead>
            <tr class="text-center" style="background: #ccc;">
                <td>序号</td>
                <td>多选内容资料编号</td>
                <td>选项名称</td>
                <td>是否需要</td>
                <td>操作</td>
                <td>详情</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pagehelper.list}" var="loan" varStatus="stas">
                <tr class="text-center">
                            <td>${stas.count}</td>
                            <td>${loan.multino}</td>
                            <td>${loan.optionname}</td>
                            <td>
                                <c:if test="${loan.isneed eq 1}">
                                    <span style="color: blue">是</span>&nbsp;&nbsp;<span><a style="cursor: pointer;color: black;" onclick="toopenmulti('${loan.id}','f')">否</a></span>
                                </c:if>
                                <c:if test="${loan.isneed eq 0}">
                                    <span><a style="cursor: pointer;color:black "
                                             onclick="toopenmulti('${loan.id}','s')">是</a></span>&nbsp;&nbsp;<span style="color: red">否</span>
                                </c:if>
                            </td>
                            <td>
                                <button class="btn btn-default" id="addmult" onclick="addMulti('${loan.multino}')">增加
                                </button>
                                <button class="btn btn-default" id="update" onclick="toupdate('${loan.id}')">编辑</button>
                                <button class="btn btn-default" id="toDelte"
                                        onclick="todelete('${loan.id}','${stas.count}','${loan.isneed}')">删除
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-default" id="toDetail" onclick="toDetail('${loan.id}')">详情
                                </button>
                            </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty pagehelper.list}">
        <h3 class="col-md-4 col-md-offset-2">还没有数据哦!</h3>
    </c:if>
    <!-- 详情模态框（Modal） -->
            <div id="detailModal" class="modal fade bs-example-modal-lg"
                 tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">选择类多选内容详情</h4>
                        </div>
                        <div class="modal-body" id="modal-body">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 增加模态框（Modal） -->
            <div id="addModal" class="modal fade bs-example-modal-lg"
                 tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">选择类多选内容设置表</h4>
                        </div>
                        <div class="modal-body" id="modal-body-add">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="addContent()">增加</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 增加模态框（Modal） -->
            <div id="updateModal" class="modal fade bs-example-modal-lg"
                 tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">选择类多选内容设置表</h4>
                        </div>
                        <div class="modal-body" id="modal-body-update">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="UpdateContent()">修改</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 删除模态框（Modal） -->
            <div id="myModal" class="modal fade bs-example-modal-lg"
                 tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">借款资料自填类选项设置</h4>
                        </div>
                        <div class="modal-body" id="modal-body-delete">
                            <input type="hidden" id="modelID"/>
                            <label>请确认是否删除序号为<span id="delspan" style="color:red;"></span>的数据？</label>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" onclick="toDeletByiscite()">删除</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
    <div id="page_div">
        <%@ include file="../../common/pagehelper.jsp" %>
    </div>
</div>
</body>
</html>