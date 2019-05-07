<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="${pageContext.request.contextPath }/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css">
<style>
    /*  #pageCon{height:140px;}
     #pageCon ul{padding-top: 20px; text-align: center;}
     #pageCon ul li{display:inline}
     #pageCon ul li a{width: 25px;height: 25px;}
     .active a{color:red; } */

</style>
<!-- 详情模态框 -->
 <script>
  function toDetail(id){
	  layer.open({
		  title:"查看详情信息",
		  type: 2,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['400px', '800px'], //宽高
		  content: '${pageContext.request.contextPath}/user/loan/deleteByid.action?id='+id
		});
  }
</script>


<script>
    //详情
    /*  function toDetail(id) {
    	alert(id);
        var action = "${pageContext.request.contextPath}/user/loan/deleteByid.action";
        var param = {
            "id": id
        }
        var callback = function (data) {
        	alert(data);
            $("#myModal").modal({
            	backdrop: 'static',
                keyboard: false
            });
            $("#modal-body").html(data);
        }
        $.post(action, param, callback);
    }   */

    //补充资料上传
    function toupload(id, baseid) {
        var bid = id;
        var action = "${pageContext.request.contextPath}/user/loan/tobeginupload.action";
        //var url="${pageContext.request.contextPath}/user/loan/usercommonbucho.action?id="+id;
        var param = {
            "baseid": baseid
        }
        var callback = function (data) {
            if (data == "fail") {
                alert("提示：请先上传公共资料!");
                window.location.href = "${pageContext.request.contextPath}/user/loan/jumpusercommon.action?baseid=" + bid;
            }
            if (data == "succ") {
                window.location.href = "${pageContext.request.contextPath}/user/loan/usercommonbucho.action?id=" + bid;
            }
        }
        $.post(action, param, callback, 'json');
    }
    //查看公共资料
    function lookData(baseid, loanno) {
        var url = "${pageContext.request.contextPath}/user/loan/lookData.action?baseid=" + baseid + "&loanno=" + loanno;
        window.location.href = url;
    }
</script>
<c:choose>
    <c:when test="${pagehelper.total <= 0}">没有数据...</c:when>
    <c:otherwise>
        <div class="box box1">
            <table class="table fc_6 mar_t5 bor_t" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <th>申请日期</th>
                    <th class="tr">借款ID</th>
                    <th class="tr"><span>借款金额</span></th>
                    <th>已还款期数</th>
                    <th class="tr">年化利率</th>
                    <th class="tr">状态</th>
                    <th class="tr">借款资料</th>
                    <th class="tr">查看资料</th>
                    <th class="l">操作</th>
                </tr>
                <c:forEach items="${pagehelper.list}" var="loan">
                    <tr>
                        <td class="Numfont table_timelog"><fmt:formatDate value="${loan.apptime}" type="both"/></td>
                        <td class="fc_3">${loan.id}</td>
                        <td class="fc_3">${loan.loanamount}</td>
                        <td class="fc_3">${loan.npers}</td>
                        <td class="fc_3">${loan.loanrate*100}%</td>
                        <td class="fc_3">
                            <c:choose>
                                <c:when test="${loan.appstatus eq 0}">审核中</c:when>
                                <c:when test="${loan.appstatus eq 1}">申请通过</c:when>
                                <c:when test="${loan.appstatus eq 2}">申请失败</c:when>
                                <c:when test="${loan.appstatus eq 3}">投标中</c:when>
                                <c:when test="${loan.appstatus eq 4}">已流标</c:when>
                                <c:when test="${loan.appstatus eq 5}">还款中</c:when>
                                <c:when test="${loan.appstatus eq 6}">已发布</c:when>
                                <c:when test="${loan.appstatus eq 7}">建标完成</c:when>
                            </c:choose>
                        </td>
                        <td class="fc_3">
                            <c:choose>
                                <c:when test="${loan.mastatus eq 1}">未填写</c:when>
                                <c:when test="${loan.mastatus eq 2}">待审核</c:when>
                                <c:when test="${loan.mastatus eq 3}">审核中</c:when>
                                <c:when test="${loan.mastatus eq 4}">审核成功</c:when>
                                <c:when test="${loan.mastatus eq 5}">审核失败</c:when>
                            </c:choose>
                        </td>
                        <td class="fc_3">
                            <a href="#" onclick="lookData('${loan.baseid}','${loan.loanno}')">查看补充资料</a>
                        </td>
                        <td class="fc_3">
                       <a class="btn btn-primary" href="javascript:;" onclick="toDetail('${loan.id}')">详情</a>
                          <%--资料审核状态(1未填写，2待审核，3审核中，4审核成功，5审核失败)--%>
                            <c:if test="${loan.mastatus eq 1}">
                                <button class="btn btn-primary" onclick="toupload('${loan.id}','${loan.baseid}')">
                                    上传补充资料
                                </button>
                            </c:if>
                            <c:if test="${loan.mastatus eq 5}">
                            <button class="btn btn-primary" onclick="toupload('${loan.id}','${loan.baseid}')">重新上传
                                </button>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>
<c:if test="${pagehelper.total > 0}">
    <div id="page_div">
        <%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
    </div>
</c:if>

