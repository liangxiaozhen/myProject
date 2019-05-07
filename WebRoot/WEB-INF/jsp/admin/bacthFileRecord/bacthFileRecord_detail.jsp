<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
	label{
		font-weight: normal;
	}
	hr{
		margin: 10px;
	}
</style>
</head>
<body>
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">ID：</label>
		<label class="col-md-7">${bfr.id}</label>
	</div>
	<hr/>
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">文件路径：</label>
		<label class="col-md-7">${bfr.filePath}</label>
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">上传文件名称：</label>
		<label class="col-md-7">${bfr.sendFileName}</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">下载文件名称：</label>
		<label class="col-md-7">${bfr.getFileName}</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">平台编号：</label>
		<label class="col-md-7">${bfr.coinstCode}</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">平台名称：</label>
		<label class="col-md-7">${bfr.PName}</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">上传文件时间：</label>
		<label class="col-md-7">
			<c:choose>
				<c:when test="${!empty bfr.submitTime}">
					<fmt:formatDate value="${bfr.submitTime}" type="both" pattern="yyyyMMdd HH:mm:ss"/>
				</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>
		</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">处理文件时间：</label>
		<label class="col-md-7">
			<c:choose>
				<c:when test="${!empty bfr.dealTime}">
					<fmt:formatDate value="${bfr.dealTime}" type="both" pattern="yyyyMMdd HH:mm:ss"/>
				</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>
		</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">业务文件类型：</label>
		<label class="col-md-7">
			<c:forEach items="${businesstype}" var="bt">
				<c:choose>
					<c:when test="${bfr.fileType eq bt.key}">${bt.value}</c:when>
				</c:choose>
			</c:forEach>
		</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">是否已发送到银行：</label>
		<label class="col-md-7">
			<c:forEach items="${sendbank}" var="sb">
				<c:choose>
					<c:when test="${bfr.isSend eq sb.key}">${sb.value}</c:when>
				</c:choose>
			</c:forEach>
		</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">发送结果：</label>
		<label class="col-md-7">
			<c:forEach items="${sendresult}" var="sr">
				<c:choose>
					<c:when test="${bfr.sendResult eq sr.key}">${sr.value}</c:when>
				</c:choose>
			</c:forEach>
		</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">是否已处理结果文件：</label>
		<label class="col-md-7">
			<c:if test="${!empty isdealfile}">
				<c:forEach items="${isdealfile}" var="idf">
					<c:choose>
						<c:when test="${bfr.isDealResult eq idf.key}">${idf.value}</c:when>
					</c:choose>
				</c:forEach>
			</c:if>
			<c:if test="${empty isdealfile}">--</c:if>
		</label>				
	</div>
	<hr/>	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">处理结果：</label>
		<label class="col-md-7">
			<c:if test="${!empty dealresult}">
				<c:forEach items="${dealresult}" var="dr">
					<c:choose>
						<c:when test="${bfr.dealResult eq dr.key}">${dr.value}</c:when>
					</c:choose>
				</c:forEach>
			</c:if>
			<c:if test="${empty dealresult}">--</c:if>
		</label>				
	</div>
	<hr/>
	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">文件业务批次号：</label>
		<label class="col-md-7">
			<c:choose>
				<c:when test="${!empty bfr.batchNo}">${bfr.batchNo}</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>
		</label>				
	</div>
	<hr/>
	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">银行返回码（上传文件）：</label>
		<label class="col-md-7">
			<c:choose>
				<c:when test="${!empty bfr.upResultCode}">${bfr.upResultCode}</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>
		</label>				
	</div>
	<hr/>
	
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">银行返回码（下载文件）：</label>
		<label class="col-md-7">
			<c:choose>
				<c:when test="${!empty bfr.downResultCode}">${bfr.downResultCode}</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>
		</label>				
	</div>
	<hr/>
		
	<div class="row" style="line-height:auto;">
		<label class="col-md-4 text-right">备注：</label>
		<label class="col-md-7">
			<c:choose>
				<c:when test="${!empty bfr.remark}">${bfr.remark}</c:when>
				<c:otherwise>--</c:otherwise>
			</c:choose>
		</label>				
	</div>
</body>
</html>