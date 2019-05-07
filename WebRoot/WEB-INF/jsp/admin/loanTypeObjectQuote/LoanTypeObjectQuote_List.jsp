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
    <script>

    </script>
    <script>
        //新增页面
        function insert_UI() {
            $("#crud").val("insert");
            var action = "insert_UI.action";
            var callback = function (data) {
                $("#Modal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body").html(data);
                $("#crudType").text("新增")
            };
            $.post(action,callback);
        }

        //新增
        function insert() {
            if(checkInsert()){
                var action = "${pageContext.request.contextPath}/admin/loantype/insert.action";
                var callback = function (data) {
                    alert(data);
                    window.location.href = "${pageContext.request.contextPath}/admin/loantype/selectAll.action";
                }
                $.post(action,$("#insert-form").serialize(), callback);
            }

        }


        //修改页面
        function update_UI(id) {
            $("#crud").val("update");
            var action = "${pageContext.request.contextPath}/admin/loantype/update_UI.action";
            var param = {
                "id": id
            }
            var callback = function (data) {
                $("#Modal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body").html(data);
                $("#crudType").text("修改")
            }
            $.post(action, param, callback);
        }

        //修改
        function update() {
            if(checkUpdate()){
                var action = "${pageContext.request.contextPath}/admin/loantype/update.action";
                var callback = function (data) {
                    alert(data);
                    window.location.href = "${pageContext.request.contextPath}/admin/loantype/selectAll.action";
                }
                $.post(action, $("#update-form").serialize(), callback);
            }

        }

        //停用和启用
        function Toenable(id, str) {
            var action = "${pageContext.request.contextPath}/admin/loantype/toenable.action";
            var param = {
                "id": id,
                "str": str
            }
            var callback = function (data) {
                alert(data);
                window.location.href = "${pageContext.request.contextPath}/admin/loantype/selectAll.action";
            }
            $.post(action, param, callback);
        }

        //操作
        function operate() {
            var crud = $("#crud").val();
            if (crud == "insert") {
                insert()
            } else if (crud == "update") {
                update()
            } else if (crud == "del") {
                delByID()
            }

        }
    </script>

</head>
<body>
<div class="container" style="width:80%;margin-top: 25px;">
    <form action="" method="post" role="form">
        <input type="hidden" id="pageNum" name="pageNum"/>
        <input type="hidden" id="pageSize" name="pageSize"/>
        <ul class="list-inline" style="margin: 10px;">
            <li><label class="">用户名1:</label></li>
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
        <button class="btn btn-default" type="button" id="add" onclick="insert_UI()"
                style="float: right; margin-right: 120px;margin-bottom: 5px;">添加
        </button>
    </form>
    <c:if test="${!empty pagehelper.list}">
        <table class="table table-hover">
            <thead>
            <tr class="text-center" style="background: #ccc;">
                <td>序号</td>
                <td>对象名称</td>
                <td>添加时间</td>
                <td>备注</td>
                <td>启用状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pagehelper.list}" var="loan" varStatus="stas">
                <tr class="text-center">
                    <td>${stas.count}</td>
                    <td>${loan.objectname}</td>
                    <td><fmt:formatDate value="${loan.addtime}" type="both"/></td>
                    <td><p limit="10" data-toggle="tooltip" title="<h5>${loan.remark}</h5>">${loan.remark}</p></td>
                    <td>
                        <c:if test="${loan.isuse  eq 1}"><span
                                style="color:blue">启用</span>&nbsp;&nbsp;<span><a style="cursor:pointer;color:black" onclick="Toenable('${loan.id}','t')">停用</a></span></c:if>
                        <c:if test="${loan.isuse eq 0 }"><span><a style="cursor:pointer;color: black;"
                                                                  onclick="Toenable('${loan.id}','q')">启用</a></span>&nbsp;&nbsp;<span style="color: red;">停用</span></c:if>
                    </td>
                    <td>
                        <button class="btn btn-default" data-toggle="modal" data-target="#delModal"
                                data-backdrop="static" onclick="update_UI('${loan.id}')">编辑
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

    <div id="page_div">
        <%@ include file="../../common/pagehelper.jsp" %>
    </div>
</div>


<!-- 模态框 -->
<div class="modal fade" id="Modal" tabindex="-1" role="dialog"
     aria-labelledby="delModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="delModalLabel">
                    <span class="glyphicon glyphicon-info-sign"></span>提示：
                </h4>
            </div>
            <div id="modal-body" class="modal-body"></div>
            <input type="hidden" id="crud"/>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick="operate()" id="crudType">
                    操作
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>