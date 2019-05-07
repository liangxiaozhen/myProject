<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>莫邪科技</title>
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
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/common/common.js"></script>
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".remark-p").each(function () {
                var num = $(this).html();
                if (num.length > 5) {
                    $(this).html(num.substr(0, 5) + "...");
                }
            });
        });


        /**
         *新增页面
         */
        function forwardInsertUI() {
            $("#crud").val("insert");
            var action = "insert_UI.action";
            var callback = function (data) {
                $("#modal-body").html(data);
                $("#crudType").text("增加");
            };
            $.post(action,callback);
        }


        /**
         *新增
         */
        function insert() {
            if(checkInsert()){
                var action = "insert.action";
                var callback = function (data) {
                    returnDate(data);
                }
                $.post(action, $("#insert-form").serialize(), callback, 'json');
            }
        }

        /**
         * 编辑页面
         */
        function forwardUpdateUI(id) {
            $("#crud").val("update");
            var action = "update_UI.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#modal-body").html(data);
                $("#crudType").text("修改")
            };
            $.post(action, params, callback);
        }


        /**
         * 编辑
         */
        function update() {

            var action = "update.action";
            var callback = function (data) {
                returnDate(data);
            }
            $.post(action, $("#update-form").serialize(), callback, 'json');
        }

        /**
         * 删除页面
         */
        function forwardDelUI(id) {
            $("#crud").val("del");
            var action = "del_UI.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#modal-body").html(data);
                $("#crudType").text("删除");
            };
            $.post(action, params, callback);
        }

        /**
         * 删除
         */
        function delByID() {
            var action = "delete.action";
            var params = {
                "id": $("#del-id").val()
            };
            var callback = function (data) {
                returnDate(data);
            }
            $.post(action, params, callback, 'json');
        }

        /**
         * 回调
         */
        function returnDate(data) {
                alert("提示："+data);
                queryAllPerson('${pagehelper.pageNum}', '');
        }

        /**
         * 操作
         */
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
<div class="container">
    <div class="row clearfix">
        <div class="col-lg-12 column">
            <h3>前端信息类别设置</h3>
            <br>
            <form id="form-select" method="post" action="list.action">
                <input type="hidden" id="pageNum" name="pageNum"> <input
                    type="hidden" id="pageSize" name="pageSize">
            </form>
            <div align="right">
                <button class="btn btn-default" data-toggle="modal"
                        data-target="#Modal" data-backdrop="static"
                        onclick="forwardInsertUI()">增加
                </button>
            </div>
            <table class="table table-hover">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>ID</td>
                    <td>类别名称</td>
                    <td>备注</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="item"
                           varStatus="status">
                    <tr class="text-center">
                        <td>${item.id}</td>
                        <td>${item.typename}</td>
                        <td>${item.remark }</td>
                        <td>

                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#Modal" data-backdrop="static"
                                    onclick="forwardUpdateUI('${item.id}')">编辑
                            </button>
                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#Modal" data-backdrop="static"
                                    onclick="forwardDelUI('${item.id}')">删除
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