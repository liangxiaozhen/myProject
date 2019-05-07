<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>multiContentSet_Inst</title>
</head>
<body>
    <c:if test="${!empty multino}">
    <form action="" method="post" class="form-horizontal">
        <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">多选内容资料编号</label>
				<div class="col-sm-3">
				   <input type="text" name="multino" id="addMultiNo" class="form-control" value="${multino}" readonly="readonly"/>
			   </div>
	    </div>
	    <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">选项名称</label>
				<div class="col-sm-3">
				   <input type="text" name="optionname" id="addoptionName" class="form-control"/>
			   </div>
	    </div>
	    <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">是否需要</label>
				<div class="col-sm-3">
					<select name="isneed" id="addIsNeed" class="form-control">
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
					</select>
				</div>
			</div>
          </form>
    </c:if>
    <c:if test="${!empty str}">
        <form action="" method="post" class="form-horizontal">
        <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">多选内容资料编号</label>
				<div class="col-sm-3">
				   <input type="text" name="multino" id="addMultiNo" class="form-control" value="${str}" readonly="readonly"/>
			   </div>
	    </div>
	    <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">选项名称</label>
				<div class="col-sm-3">
				   <input type="text" name="optionname" id="addoptionName" class="form-control"/>
			   </div>
	    </div>
	    <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">是否需要</label>
				<div class="col-sm-3">
					<select name="isneed" id="addIsNeed" class="form-control">
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
					</select>
				</div>
			</div>
          </form>
    </c:if>
    <c:if test="${!empty contentSet}">
        <form  method="post" class="form-horizontal">
        <input type="hidden" id="updateid" value="${contentSet.id}"/>
        <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">多选内容资料编号</label>
				<div class="col-sm-3">
				   <input type="text" name="multino" id="updateMultiNo" class="form-control" value="${contentSet.multino}" readonly="readonly"/>
			   </div>
	    </div>
	    <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">选项名称</label>
				<div class="col-sm-3">
				   <input type="text" name="optionname" id="updateoptionName" class="form-control" value="${contentSet.optionname}"/>
			   </div>
	    </div>
	    <div class="form-group">
			<label class="col-sm-3 control-label col-sm-offset-1">是否需要</label>
				<div class="col-sm-3">
					<select name="isneed" id="updateIsNeed" class="form-control">
					<c:if test="${contentSet.isneed eq 1}">
					   <option value="1" selected="selected">是</option>
					   <option value="0">否</option>
					</c:if>
					<c:if test="${contentSet.isneed eq 0}">
					   <option value="0" selected="selected">否</option>
					   <option value="1">是</option>
					</c:if>
					</select>
				</div>
			</div>
          </form>
    </c:if>
</body>
</html>