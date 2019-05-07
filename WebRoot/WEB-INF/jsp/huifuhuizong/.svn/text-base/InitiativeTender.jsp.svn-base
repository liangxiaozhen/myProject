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
		<form id="InitiativeTender">
			<div class="row" style="margin-top: 10px;">

				<div class="row" style="margin-top: 10px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">用户客户号（投资人）
							UsrCustId:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
						</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="UsrCustId"
								value="6000060005590676" />
						</div>
					</div>
				</div>

				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">交易金额
						TransAmt:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="TransAmt"
							value="100.00" /> <span class="red">如果使用代金券，则此金额包含代金券的金额，为投资人实际投资金额</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">最大投资手续费率
						MaxTenderRate:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="MaxTenderRate"
							value="0.50" /> <span class="red">测试环境取值范围 0.00&lt;=
							MaxTenderRate&lt;=0.50<br>放款手续费&lt;=放款金额 *
							最大投资手续费率（MaxTenderRate）
						</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">借款人客户号
						BorrowerCustId:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="BorrowerCustId"
							value="6000060005590676" />
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">借款金额
						BorrowerAmt:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="BorrowerAmt"
							value="100.00" /> <span class="red">必须与上面的交易金额一致</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">借款手续费率
						BorrowerRate:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="BorrowerRate"
							value="1.00" /> <span class="red">测试环境取值范围 0.00&lt;=
							BorrowerRate&lt;=1.00<br>本次还款金额（TransAmt+Fee）+已还款金额（TransAmt+Fee）&lt;=投标金额
							* （1+BorrowerRate）
						</span>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">标的ID
						ProId:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="ProId"
							value="gjbd2016111014" />
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">是否冻结
						IsFreeze:&nbsp;&nbsp;&nbsp;<span class="red">*（必填）</span>
					</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="IsFreeze" value="Y" />
						<span class="red">Y-冻结，N-不冻结</span>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">代金券出账子账户
						AcctId：&nbsp;&nbsp;&nbsp;</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="AcctId" value='' />
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 10px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">代金券金额
						VocherAmt:&nbsp;&nbsp;&nbsp;</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" name="VocherAmt" value="" />
					</div>
				</div>
			</div>

			<button style="margin-top: 50px;" class="btn col-md-offset-2 "
				id="submit" type="button" onclick="tenderSubmit(this)">&nbsp;&nbsp;&nbsp;提交&nbsp;&nbsp;&nbsp;</button>

		</form>
	</div>
	<script type="text/javascript">
 	   function tenderSubmit(){
 		   var params = $("#InitiativeTender").serialize();
 		  $.tzAjax.request({
 			  model:"huifu/initiativeTender",
 			  method:"initiativeTenderTest.action",
 			  callback:function(data){
  				  $("#box").html(data);
 			  }
  		  },params);
 	   }
 </script>
</body>
</html>