<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>loanItemQuote_List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/common/common.js"></script>
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">
        function tostaratQuote(id, str) {
            var action = "${pageContext.request.contextPath}/admin/loanItem/tostaratQuote.action";
            var param = {
                "id": id,
                "str": str
            }
            var callback = function (data) {
                alert(data);
                window.location.href = "${pageContext.request.contextPath}/admin/loanItem/selectAllQuote.action";
            }
            $.post(action, param, callback);
        }
    </script>
    <script>
        /*
         * 新增页面
         */
        function forwardInsertUI() {
            var action = "${pageContext.request.contextPath}/admin/loanItem/addloanItemQuote_ui.action";
            var callback = function (data) {
                $("#insert-modal-body").html(data);
            };
            $.post(action, null, callback);
        }
        /*
         * 新增
         */
        function insert() {
            $("#type").removeAttr("disabled")
            var action = "${pageContext.request.contextPath}/admin/loanItem/addloanItemQuote.action";
            var callback = function (data) {
                returnDate(data);
            };
            $.post(action, $("#insert-form").serialize(), callback);
            /*if (validateInsert()) {

             }*/
        }
        /*
         *删除页面
         */
        function forwardDelUI(id, index, isuse) {
            if (isuse == 1) {//启用状态
                alert("提示: 项目处于启用状态,无法删除！");
                return false;
            } else {
                $("#modelID").val(id);
                $('#delModal').modal();
            }



        }

        /*
         * 删除
         */
        function delByID() {
            var action = "${pageContext.request.contextPath}/admin/loanItem/toDelete.action";
            var params = {
                "id": $("#modelID").val()
            };
            var callback = function (data) {
                returnDate(data);
            }
            $.post(action, params, callback);
        }
        /*
         * 回调
         */
        function returnDate(data) {
            var obj = $.parseJSON(data);
            if (obj.result == "成功") {
                alert("操作成功");
                 $('#delModal').modal("hide");
                queryAllPerson('${pagehelper.pageNum}', '');
            }
            if (obj.result == "失败") {
                alert("提示：操作失败！")
                 $('#delModal').modal("hide");
            }
            if (obj.result == "项目被引用中") {
                alert("提示：项目被引用中！")
                 $('#delModal').modal("hide");
            }

        }

        /*
         * 详情
         */
        function forwardDetailUI(id) {// 跳转详情页面
            var action = "${pageContext.request.contextPath}/admin/loanItem/toDetail.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#detail-modal-body").html(data);
            }
            $.post(action, params, callback);
        }
        /*
         * 编辑页面
         */
        function forwardUpdateUI(id) {
            var action = "${pageContext.request.contextPath}/admin/loanItem/toUpdate.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#update-modal-body").html(data);
            };
            $.post(action, params, callback);
        }
        /*
         * 编辑
         */
        function update() {
            var action = "updateloanItem.action";
            var callback = function (data) {
                returnDate(data);
            };
            $.post(action, $("#update-form").serialize(), callback);
            /*if (validateUpdate()) {

             }*/
        }
    </script>
</head>
<body>
<div class="container" style="width:80%;margin-top: 25px;">
    <form id="form-select" action="${pageContext.request.contextPath}/admin/loanItem/selectAllQuote.action">
        <input type="hidden" id="pageNum" name="pageNum"/>
        <input type="hidden" id="pageSize" name="pageSize"/>
        <button class="btn btn-default">查询</button>
        &nbsp;&nbsp;
        <button class="btn btn-default" type="reset">重置</button>
        &nbsp;&nbsp;

    </form>
    <div align="right">
        <button class="btn btn-primary" data-toggle="modal"
                data-target="#insertModal" id="add-btn" data-backdrop="static"
                onclick="forwardInsertUI()">新增
        </button>
    </div>
    <c:if test="${!empty pagehelper.list}">
        <table class="table table-hover">
            <thead>
            <tr class="text-center" style="background: #ccc;">
                <td>序号</td>
                <td>对象</td>
                <td>排序</td>
                <td colspan="2" style="margin: 0px;padding: 0px;border:none; ">
                    <table class="table table-hover" style="margin: 0px;padding: 0px;">
                        <tr style="background: #ccc;height: 50%;">
                            <td colspan="2" style="background: #ccc;border-left:none;border-right: none;">引用项目</td>
                        </tr>
                        <tr style="background: #ccc;height: 50%;">
                            <td style="width:50%;height: 100%;border-left: none;">属性</td>
                            <td style="width:50%;border-left: none;border-right: none;">名称</td>
                        </tr>
                    </table>
                </td>
                <td>填写要求</td>
                <td>设置人</td>
                <td>状态</td>
                <td>设置时间</td>
                <td>备注</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pagehelper.list}" var="loan" varStatus="stas">
                <tr class="text-center">
                    <td>${stas.count}</td>
                    <td>
                        <c:if test="${loan.infoattribute eq 1}">
                            <span style="color: blue;">公共资料</span>
                        </c:if>
                        <c:if test="${loan.infoattribute eq 2}">
                            <span style="color: red;">补充资料</span>
                        </c:if>
                    </td>
                    <td>${loan.seriesno}</td>
                    <td colspan="2">
                        <table style="margin: 0px;padding: 0px;width: 100%;height: 100%;">
                            <tr style="text-align: center;border:none;">
                                <td style="width: 50%;height:100%; border:none;text-align: center;">
                                    <c:if test="${loan.quoteproperty eq 1}">
                                        自填类
                                    </c:if>
                                    <c:if test="${loan.quoteproperty eq 2}">
                                        选择类
                                    </c:if>
                                </td>
                                <td style="border-right: 1px;"></td>
                                <td style="border:none;text-align: center;width: 50%;height:100%;">${loan.quotename}</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <c:if test="${loan.isneed eq 1}">
                            必填
                        </c:if>
                        <c:if test="${loan.isneed eq 0}">
                            选填
                        </c:if>
                    </td>
                    <td>${loan.addman}</td>
                    <td>
                        <c:if test="${loan.isuse eq 1}">
                            <span style="color: blue">启用</span>&nbsp;&nbsp;<span><a style="cursor:pointer;color: black" onclick="tostaratQuote('${loan.id}','t')">停用</a></span>
                        </c:if>
                        <c:if test="${loan.isuse eq 0}">
                                    <span><a style="cursor: pointer;color:black "
                                             onclick="tostaratQuote('${loan.id}','q')">启用 </a> </span>&nbsp;&nbsp;<span style="color: red">停用</span>
                        </c:if>
                    </td>
                    <td><fmt:formatDate value="${loan.addtime}" type="both"/></td>
                    <td>${loan.remark}</td>

                    <td>
                        <button class="btn btn-default" data-toggle="modal"
                                data-target="#detailsModal" data-backdrop="static"
                                onclick="forwardDetailUI('${loan.id}')">查看
                        </button>

                        <button class="btn btn-default" data-toggle="modal"
                                data-target="#updateModal" data-backdrop="static"
                                onclick="forwardUpdateUI('${loan.id}')">编辑
                        </button>

                        <button class="btn btn-default"
                                onclick="forwardDelUI('${loan.id}','${stas.count}','${loan.isuse}')">删除
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

<!-- 查看详情模态框 -->
<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
     aria-labelledby="detailsModalLabel">
    <div id="detail-modal" class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="detailsModalLabel">
                    <span class="glyphicon glyphicon-info-sign"></span>提示：正在进行查看详情操作
                </h4>
            </div>
            <div id="detail-modal-body" class="modal-body"></div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- 设置模态框  -->
<div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
     aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="addModalLabel">
                    <span class="glyphicon glyphicon-info-sign"></span>提示：正在进行设置操作
                </h4>

            </div>
            <div id="insert-modal-body" class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" id="btn-insert" class="btn btn-primary"
                        onclick="insert()">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 编辑 模态框 -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
     aria-labelledby="editModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editModalLabel">
                    <span class="glyphicon glyphicon-info-sign"></span>提示：正在进行编辑操作
                </h4>
            </div>
            <div id="update-modal-body" class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" id="btn-update" class="btn btn-primary"
                        onclick="update()">保存
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>


<!-- 删除  模态框 -->
<div class="modal fade" id="delModal" tabindex="-1" role="dialog"
     aria-labelledby="delModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="delModalLabel">
                    <span class="glyphicon glyphicon-info-sign"></span>提示：正在进行删除操作
                </h4>
            </div>
            <div id="del-modal-body" class="modal-body">
                <input type="hidden" id="modelID"/>
                你确定要删除这条数据吗？
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick="delByID()">删除</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>