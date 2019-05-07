<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" style="width: 100%;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					姓名:&nbsp;<label><c:if test="${!empty aft.username}">${aft.username}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					冻结人:&nbsp;<label><c:if test="${!empty aft.operator}">${aft.operator}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					冻结时间:&nbsp;<label id="thawtime"> <c:if
							test="${!empty aft.recordtime}">${sf.format(aft.recordtime)}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					解冻人:&nbsp;<label id="thawtime"> <c:if
							test="${!empty aft.thawman}">${aft.thawman}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					解冻时间:&nbsp;<label id="thawtime"> <c:if
							test="${!empty aft.thawtime}">${sf.format(aft.thawtime)}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					订单号:&nbsp;<label><c:if test="${!empty aft.aftorderno}">${aft.aftorderno}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					汇付解冻标识:&nbsp;<label id="trxidlb"><c:if
							test="${!empty aft.trxid}">${aft.trxid}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					说明:&nbsp;<label><c:if test="${!empty aft.description}">${aft.description}</c:if></label>
				</div>
			</div>
			<div class="row" style="margin-top: 10px">
				<div class="col-md-10 col-md-offset-1">
					备注:&nbsp;<label><c:if test="${!empty aft.remark}">${aft.remark}</c:if></label>
				</div>
			</div>


		</div>
	</div>
</div>