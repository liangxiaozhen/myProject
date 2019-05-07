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

    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">
        function queryAllPerson(pageNum, pageSize) {

            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);

            $("#form-select").submit();
        }
        $(function () {
            $(".remark-p").each(function () {
                var num = $(this).html();
                if (num.length > 5) {
                    $(this).html(num.substr(0, 5) + "...");
                }
            });
        });


        /**
         * 编辑序号页面
         */
        function forwardUpdateUI(id) {
            $("#crud").val("update");
            var action = "update_UI.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#crudType").text("修改");
                $("#modal-body").html(data);
            };
            $.post(action, params, callback);
        }

        /*   /!**
         * 编辑页面
         *!/
         function forwardUpdateUI(id) {
         var url = "update_UI.action?id=" + id;
         window.location.href = url;
         }*/
        /*
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


        /*
         * 编辑
         */
        function update() {
            var tno = $("#tno").val();
            $("#condition").val(tno);
            var action = "update.action";
            var callback = function (data) {
                if (data == "success") {
                    alert("提示：操作成功！");
                    queryAllPerson('${pagehelper.pageNum}', '');
                }
                if (data == "fail") {
                    alert("提示：操作失败！")
                }
                if (data == "exsit") {
                    $("#update-itiname-text").focus();
                    $("#update-itiname-lb").html("*项目名称已存在。");
                }
            };
            $.post(action, $("#update-form").serialize(), callback, 'json');
        }
        /*
         * 删除
         */
        function delByID() {
            var tno = $("#tno").val();
            $("#condition").val(tno);
            var action = "delete.action";
            var params = {
                "id": $("#del-id").val()
            };
            var callback = function (data) {
                returnDate(data);
            }
            $.post(action, params, callback, 'json');
        }
        /*
         * 回调
         */
        function returnDate(data) {
            if (data == "success") {
                alert("提示：操作成功！");
                queryAllPerson('${pagehelper.pageNum}', '');
            }
            if (data == "fail") {
                alert("提示：操作失败！")
            }
        }
        function tostaratQuote(id, str, tno) {
            var action = "${pageContext.request.contextPath}/admin/tenderFrontEndSingle/tostaratQuote.action";
            var param = {
                "id": id,
                "str": str
            }
            var callback = function (data) {
                alert(data);
                window.location.href = "${pageContext.request.contextPath}/admin/tenderFrontEndSingle/listByTenderTno.action?tno=" + tno;
            }
            $.post(action, param, callback);
        }


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
        /*
         * 内容查看
         */
        function forwardContentUI(id,index) {
            var url = "content_UI.action?id=" + id+"&index="+index;
            window.location.href = url;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-lg-12 column">
            <h3>单标前端信息</h3>
            <br>
            <form id="form-select" method="post" action="listByTenderTno.action">
                <input type="hidden" id="pageNum" name="pageNum">
                <input type="hidden" id="pageSize" name="pageSize">
                <input type="hidden" id="condition" name="tno" value="${tno}"/>
            </form>

            <table class="table table-hover">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>排序</td>
                    <td>标号</td>
                    <td>编号</td>
                    <td>标类型</td>
                    <td>资料类别</td>
                    <td>项目名称</td>
                    <td>来源</td>
                    <td>应用范围</td>
                    <td>内容属性</td>
                    <td>内容</td>
                    <td>前端状态</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="item"
                           varStatus="status">
                    <tr class="text-center">
                        <td>${status.count}</td>
                        <td>${item.sno }</td>
                        <td>${item.tno }</td>
                        <td>${item.infono }</td>
                        <td>${item.ttypename }</td>
                        <td>
                                ${item.tenderFeiType.typename}
                        </td>
                        <td>${item.infoname }</td>
                        <td>
                            <c:if test="${item.source eq 1}">
                                新增
                            </c:if>
                            <c:if test="${item.source eq 2}">
                                引用
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.isfixed==1}">
                                通用
                            </c:if>
                            <c:if test="${item.isfixed==2}">
                                唯一
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.infoattribute eq 1}">
                                图片
                            </c:if>
                            <c:if test="${item.infoattribute eq 2}">
                                文字
                            </c:if>
                            <c:if test="${item.infoattribute eq 3}">
                                下拉
                            </c:if>
                            <c:if test="${item.infoattribute eq 4}">
                                标签
                            </c:if>
                        </td>
                        <td>

                                <%--  （1新增，2引用）应用范围（1通用，2唯一）--%>
                                <%--来源为引用且内容为null时--%>
                            <c:if test="${item.source==2&&item.content==null}">无</c:if>
                                <%--来源为引用且内容不为null时 或 来源为新增且应用范围是唯一时--%>
                            <c:if test="${item.source==2&&item.content!=null&&item.isedit==null||item.source==1&&item.isfixed==2&&item.isedit==null}">
                                <button class="btn btn-default" style="color: red;"
                                        onclick="forwardContentUI('${item.id}','${status.count}')">编辑内容
                                </button>
                            </c:if>
                            <c:if test="${item.source==2&&item.content!=null&&item.isedit==1||item.source==1&&item.isfixed==2&&item.isedit==1}">
                                <button class="btn btn-default" style="color: blue;"
                                        onclick="forwardContentUI('${item.id}','${status.count}')">查看内容
                                </button>
                            </c:if>
                                <%--通用则显示查看内容--%>
                            <c:if test="${item.isfixed==1}">
                                <button class="btn btn-default" style="color: blue;;"
                                        onclick="forwardContentUI('${item.id}','${status.count}')">查看内容
                                </button>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.isdisplay eq 1}">
                                <span style="color: blue">显示</span>&nbsp;&nbsp;<span><a style="cursor:pointer;color: black" onclick="tostaratQuote('${item.id}','h','${item.tno}')">不显示</a></span>
                            </c:if>
                            <c:if test="${item.isdisplay eq 0}">
                                    <span><a style="cursor: pointer;color:black "
                                             onclick="tostaratQuote('${item.id}','s','${item.tno}')">显示 </a> </span>&nbsp;&nbsp;<span style="color: red">不显示</span>
                            </c:if>
                        </td>
                        <td>

                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#Modal" data-backdrop="static"
                                    onclick="forwardUpdateUI('${item.id}')">编辑序号
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