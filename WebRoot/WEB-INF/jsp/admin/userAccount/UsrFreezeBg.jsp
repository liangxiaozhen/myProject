<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container" style="width: 100%;" id="frozen-content">
	<div class="row clearfix">
		<div class="col-md-12 column">

			<form action="" id="userForm">
				<input value="${userAccount.baseid}" type="hidden" id="baseidlb" />
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						姓名:&nbsp;<label>${ubai.realname}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						总资产:&nbsp;<label>${df1.format(userAccount.balance)}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						可用余额:&nbsp;<label>${df1.format(userAccount.avlbalance)}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						冻结余额:&nbsp;<label>${df1.format(userAccount.freezebalance)}</label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						需冻结金额:&nbsp;<input type="text" onblur="checkBlur(this)"
							onkeyup="checkUp(event,this)" id="djAmountlb" /> <label
							id="amountlb" style="color: red"></label>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-10 col-md-offset-1">
						&nbsp;&nbsp;&nbsp;&nbsp;备&nbsp&nbsp;&nbsp;&nbsp;;注:&nbsp;
						<textarea rows="" cols="" id="remarklb"></textarea>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
