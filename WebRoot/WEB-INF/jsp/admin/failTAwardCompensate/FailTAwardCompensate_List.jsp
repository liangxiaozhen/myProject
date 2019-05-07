<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <title>failTAwardCompensate</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- 日历 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
    <style>
        .text-center td {
            vertical-align: text-top !important;
            border: 1px solid #666;
        }
    </style>
    <script type="text/javascript">
        function queryAllPerson(pageNum, pageSize) {
            selectfailTAwardCompensateByCondition(pageNum, pageSize);
        }
        $(function () {
            $(".ugrade").each(function (i) {
                var num = $(this).text();
                if (num.length > 5) {
                    $(this).text(num.substr(0, 5) + "...");
                }
            });
            $("#reset").click(function () {
                $("#failtcno").val("");
            })
        });

        function selectfailTAwardCompensateByCondition(pageNum, pageSize) {
            var atype = $("#atype1").val();
            if (atype == "请选择") {
                $("#atype1").val("");
            }

            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#selectfailTAwardCompensateByCondition").submit();
        }

        //查看详情
        function todetailUi(id) {
            var actionURL = "${pageContext.request.contextPath}/admin/failTAwardCompensate/selectFailTAwardCompensateByPrimaryKey.action";
            /* var param = {"tid": tid}; */
            var callback = function (data) {
            	var jsonObj = eval('(' + data + ')');
            	var htmlDom=$("#modalContener");
            	htmlDom.append("<table>"+"<thead><tr>"+"<td>流标补偿编号</td>"+"<td>会员等级</td>"
            			+"<td>分段最低投资金额</td>"+"<td>分段最高投资金额</td>"+"<td>奖品名称</td>"+"<td>奖品编号</td>"
            			+"<td>奖品分数</td>"+"<td>资金清算是否需要审核</td>"+"<td>是否为模板</td>"+"</tr></thead>"
            			+"<tbody><tr>"+"<td>"+jsonObj.failtacno+"</td>"+"<td>"+jsonObj.awardugrade+"</td>"
            			+"<td>"+jsonObj.minmoneyaward+"</td>"+"<td>"+jsonObj.maxmoneyaward+"</td>"
            			+"<td>"+jsonObj.awardname+"</td>"+"<td>"+jsonObj.awardno+"</td>"+
            			"<td>"+jsonObj.awardcopies+"</td>"+"<td>"+jsonObj.isaudit+"</td>"
            			+"<td>"+jsonObj.istemplet+"</td>"+"</tr></tbody>"+"</table>");
            	//+"<td><button id="returnBtn">返回</button></td>"
                $("#myModal").modal({
                    backdrop: 'static',
                    keyboard: false
                });
                $("#modal-body").html(htmlDom);
            }
            $.post(actionURL, {"id": id}, callback);
        }

        /* function toUpdateUi(minMoney,maxMoney) {
        	var params={"minMoney":minMoney,"maxMoney":maxMoney};
            var actionURL = "admin/failTAwardCompensate/updateFailTAwardCompensate.action";
            $.post(actionURL,params,function(data){
            	alert(data);
            	
            });
        } */
        function toUpdateUi(id) {
        	var params={"id":id};
            var actionURL = "admin/failTAwardCompensate/updateFailTAwardCompensate.action";
            $.post(actionURL,params,function(data){
            	alert(data);
            	
            });
        }

        function insert_failTAwardCompensate_Ui() {
            var url = "admin/failTAwardCompensate/insert_failTAwardCompensate_Ui.action";
            window.location.href = url;
        }

        function deleteById(id, tid) {
            var url = "${pageContext.request.contextPath}/admin/failTAwardCompensate/deletefailTAwardCompensate.action";
            var data = {
                "id": id,
                "tid": tid
            }
            var deleteCallBack = function (data) {
                if ($.trim(data) == "删除成功") {
                    alert(data);
                    window.location.href = "${pageContext.request.contextPath }/admin/failTAwardCompensate/selectfailTAwardCompensateByCondition.action";
                } else {
                    alert(data);
                }
            }
            var flag = window.confirm("确定要删除这条数据吗？如果删除将不能恢复");
            if (flag) {
                jQuery.post(url, data, deleteCallBack);
            }
        }
    </script>
	<script type="text/javascript">
		function addfailTAwardCompensate(){
	    	window.location.href="<%=basePath%>admin/failTAwardCompensate/add/failTAwardCompensate.action";
	    	/* var requestURL="${pageContext.request.contextPath }/admin/failTAwardCompensate/addfailTAwardCompensateFn.action";
	    	$.post(requestURL,function(data){
	    		alert("请求成功");
	    		return false;
	    	}); */
	    }
	</script>
</head>

<body style="font-family:'微软雅黑'; font-size: 13px;">

<div class="container" style="width:90%">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <h3>流标补偿设置列表</h3>
            <form id="selectfailTAwardCompensateByCondition" method="post"
                  action="<%=basePath%>admin/failTAwardCompensate/getFailTAwardCompensateByPager.action">
                <input type="hidden" id="pageNum" name="pageNum"/>
                <input type="hidden" id="pageSize" name="pageSize"/>
                <label>流标补偿编号</label><input type="text" name="failtacno" id="failtacno"
                                            value="${failTAwardCompensate.failtacno }">
                <div>
                    <button id="query_btn" class="btn btn-default" onclick="selectfailTAwardCompensateByCondition(1,9)">查询
                    </button>
                    <a class="btn btn-default" href="<%=basePath%>admin/failTCompensate/insert_FailTAwardCompensate_Ui.action">添加</a>

                    <!-- <button id="query_btn" class="btn btn-default" onclick="addfailTAwardCompensate()">添加
                    </button> -->
                    <input type="button" value="重置" class="btn btn-default" id="reset"/>
                </div>
            </form>
            <table class="table table-hover " id="personList_table">
                <thead>
                <tr class="text-center" style="background: #ccc;">
                    <td>序号</td>
                    <td>流标补偿编号</td>
                    <td>会员等级</td>
                    <td>分段最低投资金额</td>
                    <td>分段最高投资金额</td>
                    <td>奖品名称</td>
                    <td>奖品编号</td>
                    <td>奖品分数</td>
                    <td>资金清算是否需要审核</td>
                    <td>是否为模板</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${pagehelper.list }" var="failTAwardCompensate" varStatus="failsta">
                    <tr class="text-center">
                        <td>${failsta.count}</td>
                        <td>${failTAwardCompensate. failtacno}</td>
                        <td class="ugrade" title="${failTAwardCompensate. awardugrade }">${failTAwardCompensate. awardugrade}</td>
                        <td>
                            <c:if test="${!empty failTAwardCompensate.minmoneyaward}">
                                ${failTAwardCompensate.minmoneyaward}元
                            </c:if>
                            <c:if test="${empty failTAwardCompensate.minmoneyaward}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty failTAwardCompensate.maxmoneyaward}">
                                ${failTAwardCompensate. maxmoneyaward}元
                            </c:if>
                            <c:if test="${empty failTAwardCompensate.maxmoneyaward}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty failTAwardCompensate.awardname}">
                                ${failTAwardCompensate. awardname}
                            </c:if>
                            <c:if test="${empty failTAwardCompensate.awardname}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty failTAwardCompensate.awardno}">
                                ${failTAwardCompensate. awardno}
                            </c:if>
                            <c:if test="${empty failTAwardCompensate.awardno}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty failTAwardCompensate.awardcopies}">
                                ${failTAwardCompensate. awardcopies}份
                            </c:if>
                            <c:if test="${empty failTAwardCompensate.awardcopies}">
                                <label>......</label>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty failTAwardCompensate.isaudit}">
                                <c:if test="${failTAwardCompensate.isaudit eq 1}">是</c:if>
                                <c:if test="${failTAwardCompensate.isaudit eq 0}">否</c:if>
                            </c:if>
                            <c:if test="${empty failTAwardCompensate.isaudit}"><label>......</label> </c:if>
                        </td>
                        <td>
                            <c:if test="${!empty failTAwardCompensate.istemplet}">
                                <c:if test="${failTAwardCompensate.istemplet eq 1}">是</c:if>
                                <c:if test="${failTAwardCompensate.istemplet eq 0}">否</c:if>
                            </c:if>
                            <c:if test="${empty failTAwardCompensate.istemplet}"><label>......</label> </c:if>
                        </td>
                        <td>
                            <button class="btn btn-default" onclick="todetailUi('${failTAwardCompensate.id}')">查看详情</button>
                            <button class="btn btn-default" onclick="toUpdateUi('${failTAwardCompensate.id}')">修改</button>
                            <%-- <button class="btn btn-default" onclick="toUpdateUi('${failTAwardCompensate.minmoneyaward}','${failTAwardCompensate.maxmoneyaward}')">修改</button> --%>
                            <button class="btn btn-default" id="modify"
                                    onclick="deleteById('${failTAwardCompensate.id}','${failTAwardCompensate.tid }');">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- 模态框 -->
            <div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
                 role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel"></h4>
                        </div>
                        <div class="modal-body" id="modal-body">
                        </div>
                        <div id="modalContener"></div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </div>
            </div>
            <div id="page_div">
                <%@ include file="../../common/pagehelper.jsp" %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
