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

        $(function(){
            $("#reset").click(function () {
                $("#ttypename").val("");
                $("#infoname").val("");
            });
        });
        function queryAllPerson(pageNum, pageSize) {
            selectByCondition(pageNum, pageSize);
        }

        function selectByCondition(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectByCondition").submit();
        }


        $(function () {
            $(".remark-p").each(function () {
                var num = $(this).html();
                if (num.length > 5) {
                    $(this).html(num.substr(0, 5) + "...");
                }
            });
        });


        /*
         * 新增页面
         */
        function forwardInsertUI() {
            var url = "insert_UI.action";
            window.location.href = url;
        }

        /**
         * 编辑页面ajax方式
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


        /*
         * 删除页面
         */
        function forwardDelUI(id, isuse) {
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

        /*
         *新增
         */
        function insert() {
            if (validateInsert()) {
                var action = "insert.action";
                $("#isfixed").removeAttr("disabled")
                var callback = function (data) {
                    returnDate(data);
                }
                $.post(action, $("#insert-form").serialize(), callback, 'json');
            }
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
                queryAllPerson('${pagehelper.pageNum}', '');
            }
            if (data == "提示: 项目处于启用状态,无法删除") {
                alert("提示: 项目处于启用状态,无法删除");
                queryAllPerson('${pagehelper.pageNum}', '');
            }
        }


        function tostaratQuote(id, str) {
            var action = "${pageContext.request.contextPath}/admin/tenderFrontEnd/tostaratQuote.action";
            var param = {
                "id": id,
                "str": str
            }
            var callback = function (data) {
                alert(data);
                window.location.href = "${pageContext.request.contextPath}/admin/tenderFrontEnd/list.action";
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
            } else if (crud == "import_content") {
                if (check()) {
                    alert();
                    import_content();
                }
            }

        }
        /**
         * 引入项目页面
         */
        function forwardImportUI(id) {
            $("#crud").val("import_content");
            var action = "Import_UI.action";
            var params = {
                "id": id
            };
            var callback = function (data) {
                $("#crudType").text("修改");
                $("#modal-body").html(data);
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
         * 导入
         */
        function import_content() {
            var action = "import.action";
            var callback = function (data) {
                alert("11");
                if (data == "success") {
                    alert("提示：操作成功！");
                    queryAllPerson('${pagehelper.pageNum}', '');
                }
                if (data == "fail") {
                    alert("提示：操作失败！")
                    queryAllPerson('${pagehelper.pageNum}', '');
                }

            };
            $.post(action, $("#import-form").serialize(), callback, 'json');
        }
        /*
         * 内容查看
         */
        function forwardContentUI(id) {
            var url = "content_UI.action?id=" + id;
            window.location.href = url;
        }

        function check() {
            alert()
            var liqno = $("#liqno").val();
            alert(liqno);
            if (liqno == null || liqno == "") {
                alert(123);
                $("#insert-liqno-lb").html("*请选择引用项目");
                alert(1232);
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-lg-12 column">
            <h3>标的前端信息</h3>
            <br>
            <form id="selectByCondition" method="post"
                  action="${pageContext.request.contextPath}/admin/tenderFrontEnd/list.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>标类型</label><input type="text" name="ttypename" id="ttypename" value="${tenderFrontEnd.ttypename}">
                <label>项目名称</label><input type="text" name="infoname" id="infoname" value="${tenderFrontEnd.infoname}">
                <div style="margin: 10px;">
                    <button id="query_btn" class="btn btn-default" onclick="selectByCondition(1,20)">查询
                    </button>
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
            </form>
            <div align="right">
                <button class="btn btn-primary"
                        onclick="forwardInsertUI()">新增
                </button>
            </div>
            <table class="table table-hover">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>编号</td>
                    <td>标类型</td>
                    <td>资料类别</td>
                    <td>项目名称</td>
                    <td>来源</td>
                    <td>内容属性</td>
                    <td>应用范围</td>
                    <td>是否同步存量标</td>
                    <td>内容</td>
                    <td>状态</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="item"
                           varStatus="status">
                    <tr class="text-center">
                        <td>${status.count}</td>
                        <td>${item.infono}</td>
                        <td>
                            <a style="color: blue;"
                               href="${pageContext.request.contextPath}/admin/tenderFrontEnd/list.action?ttypeid=${item.ttypeid}">${item.ttypename }</a>
                        </td>
                        <td>
                            <c:forEach items="${tenderFeiTypes}" var="tf">
                                <c:if test="${item.infotype==tf.id}">${tf.typename}</c:if>
                            </c:forEach>
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
                            <c:if test="${item.isfixed eq 1}">
                                通用
                            </c:if>
                            <c:if test="${item.isfixed eq 2}">
                                唯一
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.issynchisbid==0}">不同步</c:if>
                            <c:if test="${item.issynchisbid==1}">同步待投标的</c:if>
                            <c:if test="${item.issynchisbid==2}">同步待投标的 + 投标中的标的</c:if>
                        </td>
                        <td>
                            <c:if test="${item.content==null&&item.isfixed eq 2}">无</c:if>
                            <c:if test="${item.isfixed eq 1}">
                                <button class="btn btn-default"
                                        onclick="forwardContentUI('${item.id}')">查看
                                </button>
                            </c:if>
                            <c:if test="${item.content!=null&&item.isfixed eq 2}">
                                ${item.content}
                            </c:if>

                        </td>
                        <td>
                            <c:if test="${item.status eq 1}">
                                <span style="color: blue">启用</span>&nbsp;&nbsp;<span><a style="cursor:pointer;color: black" onclick="tostaratQuote('${item.id}','t')">停用</a></span>
                            </c:if>
                            <c:if test="${item.status eq 0}">
                                    <span><a style="cursor: pointer;color:black "
                                             onclick="tostaratQuote('${item.id}','q')">启用 </a> </span>&nbsp;&nbsp;<span style="color: red">停用</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${item.source==2}">
                                <button class="btn btn-default" data-toggle="modal"
                                        data-target="#Modal" data-backdrop="static"
                                        onclick="forwardImportUI('${item.id}')">引入
                                </button>
                            </c:if>
                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#Modal" data-backdrop="static"
                                    onclick="forwardUpdateUI('${item.id}')">编辑
                            </button>
                            <button class="btn btn-default" data-toggle="modal"
                                    data-target="#Modal" data-backdrop="static"
                                    onclick="forwardDelUI('${item.id}','${item.status}')">删除
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