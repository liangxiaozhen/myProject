<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container" style="width: 100%;" id="thaw-content">
	<div class="row clearfix">
		<div class="col-md-12 column">

			<form action="" id="usrFreezefrom">
				<input value="${accountFreezeThaw.baseid}" type="hidden"
					id="baseidlb" />
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						用户名:&nbsp;<label>${accountFreezeThaw.username}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						操作人:&nbsp;<label>${accountFreezeThaw.operator}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						解冻冻结流水号:&nbsp;<label>${accountFreezeThaw.aftorderno}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						解冻标识:&nbsp;<label id="trxidlb">${accountFreezeThaw.trxid}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						解冻余额:&nbsp;<label>${df.format(accountFreezeThaw.amount)}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						说明:&nbsp;<label>${accountFreezeThaw.description}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						备注:&nbsp;
						<textarea rows="" cols="" id="remark-text"></textarea>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
