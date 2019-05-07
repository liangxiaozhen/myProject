<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="row" style="line-height: 0px; display: none;">
	<font class="col-md-3 text-right">ID:</font> <font id="actAwardId">${activityAwardList.id }</font>
</div>


<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">活动编号:</font> <font id="addtime-lb">${activityAwardList.actid }</font>
</div>
<hr>
<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">用户名:</font><font id="addman-lb">${activityAwardList.username }</font>
</div>
<hr>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品编号:</font> <font id="addman-lb">${activityAwardList.awardno }</font>
</div>
<hr>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品名称:</font> <font id="addtime-lb">${activityAwardList.awardname }</font>
</div>
<hr>
<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">获奖金额:</font> <font id="addman-lb">${activityAwardList.awardmoney }</font>
</div>
<hr>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品个数:</font> <font id="addtime-lb">${activityAwardList.awardquantity }</font>

</div>
<hr>
<div class="row" style="display: none" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品状态:</font> <font
		id="actAwardStatus">${activityAwardList.status}</font>
</div>

<div class="row" style="display: none" style="line-height: 0px;">
	<font class="col-md-3 text-right">奖品属性:</font> <font
		id="actAwardAttribute">${activityAwardList.awardattribute}</font>
</div>

<div class="row" style="line-height: 0px;">
	<font class="col-md-3 text-right">审核人:</font> <font id="auditmanId">${adminuser.username}</font>
</div>


