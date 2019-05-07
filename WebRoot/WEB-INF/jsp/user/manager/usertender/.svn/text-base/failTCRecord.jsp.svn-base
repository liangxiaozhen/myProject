<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta http-equiv="Content-Language" content="zh-cn">
<meta name="robots" content="all">

<title>投资管理-流标补偿</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${basePath}/resources/resource/Css/new.css" rel="stylesheet" type="text/css" >
<link href="${basePath}/resources/resource/Css/nwd_common.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_percenter.css" rel="stylesheet" type="text/css"> 
<link href="${basePath}/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet" type="text/css">
<link href="${basePath}/resources/resource/Css/mobiscroll.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/mobiscroll_date.css" rel="stylesheet" />
<link href="${basePath}/resources/resource/Css/pagehelper.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${basePath}/resources/resource/Scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
<style type="text/css">
.list-inline {
	padding-left: 0;
	margin-left: -5px;
	list-style: none;
}

.list-inline>li {
	display: inline-block;
	padding-right: 5px;
	padding-left: 5px;
}
.bs{
	padding:0px 4px;
	border:1px solid #D3D3D3;
	border-radius:3px;
	background-color:#fff;
	height:31px
}
</style>
<script type="text/javascript">
	$(function() {
		$("#reset").click(function() {
			$("#tno").val('');
			$("#isgrant").val('');
		})
		$(".awardAddress").on("click",function(){
			var id = $(this).data("opid");
			layer.open({
				type: 2, // 层的类型（0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层）
				title:"填写收货地址",
				area: ['450px', '450px'], //宽高
				fix:true,
				content: ["${pageContext.request.contextPath}/user/failtcrecord/addressSureUI.action?id="+id,"no"],
				btn:["确认",'取消'],
				yes:function(layero,index){
					var obj = layer.getChildFrame('body');
					//用户是否按要求填写
					var chk = obj.find("#chk");
										
					var flag = true;
					var remark = obj.find("#remark").val();
					if(!remark){
						flag = false;
						layer.alert("请按要求填写收货地址！");
					}else if(!chk.is(":checked")){
						flag = false;
						layer.alert("请勾选！");
					}
					if(flag){
						//获取备注（用户所填的收货地址信息）
						$.ajax({
							url:"${pageContext.request.contextPath}/user/failtcrecord/addressSure.action",
							type:"post",
							dataType:"json",
							data:{
								"id":id,
								"remark":remark
							},
							success:function(data){
								if(data["result"]=="success"){
									alert("操作成功");
									layer.closeAll();
								}else if(data["result"]=="fail"){
									alert("操作失败");
								}
							}
						}); 
					}
				}
			});
		}); 
	})

	/*** 分页查询标的站岗利息记录表 */
	function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#gfirForm").submit()
	}
</script>
</head>
<body>
	<!-- 头部-->
	<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<!-- 内容 -->
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
			<!--左侧-->
			<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
			<!-- 右侧 -->
			<div class="fl perCerterR bor_l">
				<div class="fl pad_30 wid_w900 clearfix">
					<div class="loadDiv fc_9 clearfix">
						<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我的流标补偿</span>
					</div>
					<div style="margin-top: 50px;">
						<form action="${basePath}/user/failtcrecord/query.action"
							id="ftcrForm" method="post">
							<input type="hidden" id="pageNum" name="pageNum" value="" /> <input
								type="hidden" id="pageSize" name="pageSize" value="" />
							<ul class="list-inline">
								<li><label>标号：</label><input class="input" id="tno"
									name="tenderitem.tno" value="${echodata.tenderitem.tno}" /></li>
								<li><label>是否发放：</label> <select id="isgrant"
									name="isgrant"
									style="border: 1px solid #e3e3e3; border-radius: 4px; height: 38px;"><c:if
											test="${!empty isgrantmap}">
											<option value="">--请选择--</option>
											<c:forEach items="${isgrantmap}" var="grant">
												<c:choose>
													<c:when test="${echodata.isgrant eq grant.key}">
														<option value="${grant.key}" selected="selected">${grant.value}</option>
													</c:when>
													<c:otherwise>
														<option value="${grant.key}">${grant.value}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</c:if>
								</select></li>
								<li><input type="submit" value="查询" class="btn"
									style="width: 60px; height: 30px;" /> <input type="button"
									value="重置" class="btn" id="reset"
									style="width: 60px; height: 30px;" /></li>
							</ul>
						</form>
					</div>
					<div class="tab_content clearfix">
						<div class="tab_box" style="display: block;">
							<table class="table fc_9 mar_t5 bor_t " cellspacing="0"
								cellpadding="0">
								<tr>
									<th class="fc_3">流标补偿流水号</th>
									<th class="fc_3">标号</th>
									<th class="fc_3">投标订单号</th>
									<th class="fc_3">奖励金额</th>
									<th class="fc_3">奖品名称</th>
									<th class="fc_3">是否发放</th>
									<th class="fc_3">审核状态</th>
									<th class="fc_3">操作</th>
								</tr>
								<tbody>
									<c:if test="${!empty pagehelper.list}">
										<c:forEach items="${pagehelper.list}" var="ftcr">
											<tr>
												<td>${ftcr.ftcorderno}</td>
												<td>${ftcr.tenderitem.tno}</td>
												<td>${ftcr.utorderno}</td>
												<td>${ftcr.rewardamount eq null ? '--' : df.format(ftcr.rewardamount)}</td>
												<td>${ftcr.awardname eq null ? '--' : ftcr.awardname}</td>
												<td><c:if test="${!empty isgrantmap}">
														<c:forEach items="${isgrantmap}" var="grant">
															<c:choose>
																<c:when test="${ftcr.isgrant eq grant.key}">${grant.value}</c:when>
															</c:choose>
														</c:forEach>
													</c:if></td>
												<td><c:if test="${!empty isauditmap}">
														<c:forEach items="${isauditmap}" var="audit">
															<c:choose>
																<c:when test="${ftcr.isaudit eq audit.key}">${audit.value}</c:when>
															</c:choose>
														</c:forEach>
													</c:if></td>
												<td>
													<button class="awardAddress bs" type="button" data-opid="${ftcr.id}" ${ftcr.isgrant eq 2 ? '' : 'disabled'}>收货地址</button>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<c:if test="${!empty pagehelper.list}">
								<div id="page_div"><%@ include
										file="/WEB-INF/jsp/common/pagehelper.jsp"%></div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>