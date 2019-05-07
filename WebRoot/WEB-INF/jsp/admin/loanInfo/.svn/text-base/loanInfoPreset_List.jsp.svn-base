<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>loanInfoPreset_List</title>
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
        /* 分页查询用戶获奖信息 */
        function queryAllPerson(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectuserAddress").submit()
        }
        /* 备注显示字符个数限制*/
        jQuery.fn.limit = function () {
            var self = $("[limit]");
            self.each(function () {
                var objString = $(this).text();
                var objLength = $(this).text().length;
                var num = $(this).attr("limit");
                if (objLength > num) {
                    objString = $(this).text(objString.substring(0, num) + "...");
                }
            })
        }

        $(function () {
            $("[limit]").limit();
            $(".quoteobject").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
        })

        /* 备注tips */
        $(function () {
            $("[data-toggle='tooltip']").tooltip({
                html: true
            });
        });
        function addloan() {
            var url = "${pageContext.request.contextPath}/admin/loanInfo/loanInfoPreset_ui.action";
            window.location.href = url;
        }
        function addloanmult() {
            var url = "${pageContext.request.contextPath}/admin/loanInfo/loanInfoPreset_ui.action";
            window.location.href = url;
        }
        //详情
        function toDetail(id) {
            var action = "${pageContext.request.contextPath}/admin/loanInfo/toDetailPreset.action";
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
        //删除
        function todelete(id, index, isneed) {
            if (isneed == 1) {//启用状态
                alert("提示：项目处于启用状态,无法删除！");
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
            var action = "${pageContext.request.contextPath}/admin/loanInfo/toDeletePreset.action";
            var param = {
                "id": id
            }
            var callback = function (data) {
                $('#myModal').modal("hide");
                if (data == "succ") {
                    alert("提示：删除成功");
                    window.location.href = "${pageContext.request.contextPath}/admin/loanInfo/selectAllPreset.action";
                }
                if (data == "fail") {
                    alert("提示：此项目被引用,无法删除");
                }
            }
            $.post(action, param, callback, 'json');
        }

        //更新
        function toupdate(id) {
            var url = "${pageContext.request.contextPath}/admin/loanInfo/toupdatePreset.action?id=" + id;
            window.location.href = url;
        }
        //停用和启用
        function toopen(id, str) {
            var action = "${pageContext.request.contextPath}/admin/loanInfo/toopen.action";
            var param = {
                "id": id,
                "str": str
            }
            var callback = function (data) {
                alert(data);
                window.location.href = "${pageContext.request.contextPath}/admin/loanInfo/selectAllPreset.action"
            }
            $.post(action, param, callback);
        }
    </script>
</head>
<body style="font-family:'微软雅黑'; ">
<div class="container" style="width:80%;margin-top: 25px;">
    <form action="" method="post" role="form">
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
        <button class="btn btn-default" type="button" id="add" onclick="addloan()"
                style="float: right; margin-right: 20px;margin-bottom: 5px;">添加
        </button>
    </form>
    <c:if test="${!empty pagehelper.list}">
        <table class="table table-hover">
            <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td class="t">序号</td>
                    <td class="t">项目名称</td>
                    <td class="t">类型</td>
                    <td class="t">设置人</td>
                    <td class="t">设置时间</td>
                    <td class="t">选择项内容</td>
                    <td class="t">选择方式</td>
                    <td class="t">引用对象</td>
                    <td class="t">前端说明</td>
                    <td class="t">备注</td>
                    <td class="t">状态</td>
                    <td class="t">操作</td>
                    <td class="t">详情</td>
                </tr>
            </thead>
            <c:forEach items="${pagehelper.list}" var="loan" varStatus="stas">
                <tr class="text-center">
                    <td><label style="line-height: 60px;">${stas.count}</label></td>
                    <td>${loan.infoname}</td>
                    <td>
                        <c:if test="${loan.infotype eq 1}">选择</c:if>
                    </td>
                    <td>${loan.addman}</td>
                    <td><fmt:formatDate value="${loan.addtime}" type="both"/></td>
                    <td>
                        <c:if test="${!empty contentSets}">
                            <table style="width:100%;">
                                <c:forEach items="${contentSets}" var="cont">
                                    <c:if test="${cont.multino eq loan.multino}">
                                        <tr style="border-bottom:1px solid #ccc;text-align: center;">
                                            <td style="border:none;">${cont.optionname}</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${loan.oneormulti eq 1}">单选</c:if>
                        <c:if test="${loan.oneormulti eq 2}">多选</c:if>
                    </td>
                    <td class="quoteobject" title="${loan.quoteobject}">${loan.quoteobject}</td>
                    <td>${loan.infodescription}</td>
                    <td>${loan.remark}</td>
                    <td>
                        <c:if test="${loan.isneed eq 1}">
                            <span style="color:blue">启用</span>&nbsp;&nbsp;<span><a style="cursor: pointer;color:black" onclick="toopen('${loan.id}','t')">停用</a></span>
                        </c:if>
                        <c:if test="${loan.isneed eq 0}">
                            <span><a style="cursor: pointer;color:black" onclick="toopen('${loan.id}','q')">启用</a></span>&nbsp;&nbsp;<span style="color:red">停用</span>
                        </c:if>
                    </td>
                    <td>
                        <button class="btn btn-default" id="update" onclick="toupdate('${loan.id}')">编辑</button>
                        <button class="btn btn-default" id="toDelte"
                                onclick="todelete('${loan.id}','${stas.count}','${loan.isneed}')">删除
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-default" id="toDetail" onclick="toDetail('${loan.id}')">详情</button>
                    </td>
                </tr>
            </c:forEach>
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
                    <h4 class="modal-title" id="myModalLabel">借款资料自填类选项设置</h4>
                </div>
                <div class="modal-body" id="modal-body">
                </div>
                <div class="modal-footer">
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