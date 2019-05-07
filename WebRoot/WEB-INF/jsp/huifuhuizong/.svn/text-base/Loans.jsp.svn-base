<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
</head>
<body>
	<div id="box">
		<form id="Loans">

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">出账客户号（投资人）
						OutCustId:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="OutCustId"
							value="6000060005290553" />
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">交易金额（放款金额）
						TransAmt:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="TransAmt"
							value="300.00" /> <span class="red">保留两位小数，比如 2.00</span>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">扣款手续费
						Fee:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="Fee" value="3.00" />
						<span class="red">保留两位小数，比如 2.00</span>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">关联投标订单号
						SubOrdId：&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="SubOrdId"
							value="20160926150244943702" />
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">关联投标订单日期
						SubOrdDate:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="SubOrdDate"
							value="20160926" /> <span class="red">格式为
							YYYYMMDD，例如：20130307</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">入账客户号(借款人)
						InCustId:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="InCustId"
							value="6000060004190334" />
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">分账商户号
						DivCustId:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="DivCustId"
							value="6000060004166478" /> <span class="red">当 Fee！=0
							时是必填项</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">分账账户号
						DivAcctId:&nbsp;&nbsp;&nbsp;</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="DivAcctId"
							value="SDT000001" /> <span class="red">当 Fee！=0 时是必填项</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">分账金额
						DivAmt:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="DivAmt" value="3.00" />
						<span class="red">当 Fee！=0 时是必填项</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">手续费收取对象标志
						I/O:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="FeeObjFlag"
							value="I" /> <span class="red">I--向入款客户号 InCustId 收取<br>O--向出款客户号
							OutCustId 收取<br>若 扣款手续费fee 大于 0.00，FeeObjFlag 为必填参数
						</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">是否默认
						IsDefault:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="IsDefault" value="N" />
						<span class="red">Y--默认添加资金池:这部分资金需要商户调用商户代取现接口，帮助用户做后台取现动作<br>N--不默认不添加资金池:这部分资金用户可以自己取现
						</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">是否解冻
						IsUnFreeze:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="IsUnFreeze"
							value="Y" /> <span class="red">Y--解冻<br>N--不解冻
						</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">冻结标识
						FreezeTrxId:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="FreezeTrxId"
							value="201609260006770873" /> <span class="red">若
							IsUnFreeze=Y，则解冻，此时 FreezeTrxId 为必填项</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">代金券金额
						loansVocherAmt:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="loansVocherAmt"
							value="" /> <span class="red">若 投标中使用了代金券，则此处为必填项，<br>若投标未使用代金券，此处为空，不填
						</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">项目
						ID ProId:</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="ProId"
							value="gjbh201609051640" /> <span class="red">若
							主动投标/自动投标的借款人信息BorrowerDetails 字段的项目 ID ProId 有值，则必填，否则为可选</span>
					</div>
				</div>
			</div>

			<button style="margin-top: 50px;" class="btn col-md-offset-2 "
				id="submit" type="button" onclick="loansSubmit(this)">&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;</button>

		</form>
	</div>
	<script type="text/javascript">
 	   function loansSubmit(){
 		   var params = $("#Loans").serialize();
 		  $.tzAjax.request({
 			  model:"huifu/loans",
 			  method:"loansTest.action",
 			  callback:function(data){
  				  $("#box").html(data);
 			  }
  		  },params);
 	   }
 </script>
</body>
</html>