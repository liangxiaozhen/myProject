<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta name="viewport"
          content="width=device-width,user-scalable=no,initial-scale=1">
    <meta http-equiv="description" content="This is my page">

    <!-- Latest compiled and minified CSS -->
    <!-- link font icon -->
    <!-- <link rel="stylesheet" href="awesome/css/font-awesome.min.css"> -->

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script type="text/javascript">var basePath ="${pageContext.request.contextPath}"</script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="<%=basePath%>/js/bankcard/bindBankCard.js"></script>
    <style type="text/css">
        th {
            text-align: center;
            background: #ccc;
        }

        td {
            text-align: center;
        }

        body {
            font-family: "微软雅黑";
        }
    </style>
    <script>
        function queryAllPerson(pageNum, pageSize) {
            $("#pageNum").val(pageNum);
            $("#pageSize").val(pageSize);
            $("#msgForm").submit()
        }
        
        function queryDetail(id) {
            var action = basePath+"/admin/msg/showDetail.action";
            var param = {
                "id" : id
            }
            var callback = function(data) {
                $("#myModal").modal({
                    backdrop : 'static',
                    keyboard : false
                });
                $("#modal-body").html(data);
            }
            $.post(action, param, callback);
        }
        
        /**
         *导航栏数据回显
         */
        $(function(){
        	$("#baseid").val("${selectInfo.baseid}");
        	$("#item").val("${selectInfo.item}");
        	$("#msgtype").val("${selectInfo.msgtype}");
        
        });
    </script>
</head>
<body>
<div class="container" style="width: 90%">
    <div class="row clearfix">
        <div class="col-md-12 column"
             style="border-style: none; border-width: 5px; border-color: Black; background-color: none;">
            <div id="area_div" style="margin-top: 50px">
                <form role="form" method="post" id="msgForm"
                      action="${pageContext.request.contextPath}/admin/msg/toMsgSet.action">
                    <input type="hidden" id="pageNum" name="pageNum" value="${ pagehelper.pageNum}" />
                    <input type="hidden" id="pageSize" name="pageSize" value="${ pagehelper.pageSize}" />
                    <br /> 用户名ID:<input type="text" id="baseid" name="baseid" 	/>
                 状态:<select name="item" id="item" >
                    <option value="">请选择--</option>
                    <option value="流标">流标</option>
                    <option value="退标">退标</option>
                    <option value="收到回款">收到回款</option>
                    <option value="债权转让成功">债权转让成功</option>
                    <option value="满标">满标</option>
                    <option value="借款标发生债权转让">借款标发生债权转让</option>
                    <option value="还款成功">还款成功</option>
                </select>
                
                       消息类型:<select name="msgtype" id="msgtype">
                    <option value="">请选择--</option>
                    <option value="1">投资人</option>
                    <option value="2">借款人</option>
               
                </select>
                    <button id="query_all_person">查询</button>
                    <button type="reset">重置</button>

                </form>

                <div class="table-responsive">
                    <table class="table table-condensed table-bordered table-hover"
                           style="width: 100%">
                        <caption>
                            <strong>用户消息设置</strong>
                        </caption>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>用户ID</th>
                            <th>消息类型</th>
                            <th>项目</th>
                            <th>站内信</th>
                            <th>短信</th>
                            <th>邮箱</th>
                            <th>手机APP </th>
                            <th>微信服务号</th>
                            <th>备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${!empty pagehelper.list}">
                            <c:forEach items="${pagehelper.list}" var="userMsg">
                                <tr >
                                    <td>${userMsg.id}</td>
                                    <td>${userMsg.baseid}</td>
                                    <td><c:choose>
                                        <c:when test="${userMsg.msgtype==1}">投资人</c:when>
                                        <c:when test="${userMsg.msgtype==2}">借款人  </c:when>
                                    </c:choose></td>
                                    <td>${userMsg.item}</td>
                                    <td><c:choose>
                                        <c:when test="${userMsg.intmsg==1}">开启</c:when>
                                        <c:otherwise>关闭</c:otherwise>
                                         </c:choose>      
                                    </td>
                                    <td><c:choose>
                                        <c:when test="${userMsg.sms==1}">开启</c:when>
                                        <c:otherwise>关闭</c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td><c:choose>
                                        <c:when test="${userMsg.email==1}"> 开启</c:when>
                                        <c:otherwise>关闭</c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td><c:choose>
                                        <c:when test="${userMsg.app==1}">开启</c:when>
                                        <c:otherwise>关闭</c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td><c:choose>
                                        <c:when test="${userMsg.wechat==1}"> 开启 </c:when>
                                        <c:otherwise>  关闭</c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td><p id="remark" data-toggle='tooltip'
                                           title="<h5>${userMsg.remark}</h5>" limit="5">${userMsg.remark}</p></td>
                                    <td><div class="btn-group" role="group" aria-label="...">
                                        <button class="btn" onclick="queryDetail(${userMsg.id})"
                                                >查看详情</button>
                                    </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${empty pagehelper.list}">
                            <tr>
                                <td colspan="100">没有相关数据</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <div id="page_div">
                    <%@ include file="./../../common/pagehelper.jsp"%>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 详情模态框（Modal） -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">用户消息设置详情</h4>
            </div>
            <div class="modal-body" id="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </div>
</div>
</body>
</html>