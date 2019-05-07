<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标的增益设置</title>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<style type="text/css">
     *{margin: 0px;padding: 0px;}
	.laber_from {color: #222;font-weight: normal;}
	.route_bg{ background-color: #E7E7E7; border-radius: 4px; padding: 5px; margin-right: 5px;margin-left: 5px;margin-top: 5px; } 
	.route_bg i{ color: #ccc;font-weight: 400;font-size: 12px;padding-right: 5px;line-height: 25px; } 
	.route_bg a{ font-size: 12px; color: #666; text-decoration: none;line-height: 1.6;font-family: "Helvetica Neue","Hiragino Sans GB","Microsoft YaHei","\9ED1\4F53",Arial,sans-serif !important; } 
	.route_bg a:hover{ color: #888; text-decoration: none;}
</style>
<script type="text/javascript">
			$(function() {
				$("#aint").hide();//加息券设置隐藏
				$("#cash").hide();//红包设置隐藏
				$("#Fakecash").hide();//假现金隐藏
				//加息券设置
				$("#isAInt").change(function() {
					var is = $(this).val();
					if("0" == is || "" == is) {
						$("#aint input").val("");
						$("#aint").hide();
					} else if("1" == is) {
						$("#aint").show();
					}
				});
				//红包设置(类现金)
				$("#isAVoucher").change(function() {
					var is = $(this).val();
					if("0" == is || "" == is) {
						$("#cash input").val("");
						$("#cash").hide();
					} else if("1" == is) {
						$("#cash").show();
					}
				});
				//红包设置(假现金)
				$("#isALikeVoucher").change(function() {
					var is = $(this).val();
					if("0" == is || "" == is) {
						$("#Fakecash input").val("");
						$("#Fakecash").hide();
					} else if("1" == is) {
						$("#Fakecash").show();
					}
				});
				$('#defaultForm').bootstrapValidator({
					message: 'This value is not valid',
					feedbackIcons: { /*input状态样式图片*/
						valid: 'glyphicon glyphicon-ok',
						invalid: 'glyphicon glyphicon-remove',
						validating: 'glyphicon glyphicon-refresh'
					},
					fields: { //验证
						aonceint: { //加息券单次张数
							validators: {
								notEmpty: {
									message: '单次张数不能为空'
								},
								stringLength: {
									max: 5,
									message: '单次张数不能超过5位数'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '张数只能为正整数'
								}
							}
						},
						atotalint: { //总计张数
							validators: {
								notEmpty: {
									message: '总计张数不能为空'
								},
								stringLength: {
									max: 5,
									message: '总计张数不能超过5位数'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '张数只能为正整数'
								}
							}
						},
						aoneqrofit: { //允许单张加息收益
							validators: {
								notEmpty: {
									message: '单张加息收益不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '收益为数字'
								}
							}
						},
						aoncevoucher: { //类现金单次张数
							validators: {
								notEmpty: {
									message: '单次张数不能为空'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '张数只能为正整数'
								},
								stringLength: {
									max: 5,
									message: '单张次数不能超过5位数'
								}
							}
						},
						atotalvoucher: { //总计张数
							validators: {
								notEmpty: {
									message: '总计张数不能为空'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '张数只能为正整数'
								},
								stringLength: {
									max: 5,
									message: '总计张数不能超过5位数'
								}
							}
						},
						aonevamount: { //单张金额
							validators: {
								notEmpty: {
									message: '单张类现金额度不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额只能为浮点数如100.0或100'
								}
							}
						},
						aoncelikevoucher: { //单次张数
							validators: {
								notEmpty: {
									message: '单次张数不能为空'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '张数只能为正整数'
								},
								stringLength: {
									max: 5,
									message: '单次张数不能超过5位数'
								}
							}
						},
						atotallikevoucher: { //总计张数
							validators: {
								notEmpty: {
									message: '总计张数不能为空'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '张数只能为正整数'
								},
								stringLength: {
									max: 5,
									message: '单次张数不能超过5位数'
								}
							}
						},
						aonelvamount: { //单张假现金额度
							validators: {
								notEmpty: {
									message: '单张假现金额度不能为空'
								},
								regexp: {
									regexp: /^(\d*\.)?\d+$/,
									message: '金额只能为浮点数如100.0或100'
								}
							}
						},
						aonceplusmode: { //单次允许的增益方式
							validators: {
								notEmpty: {
									message: '单次叠加不能为空'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '种数只能为正整数'
								},
								stringLength: {
									max: 5,
									message: '单次种数不能超过5位数'
								}
							}
						},
						atotalplusmode: { //总计叠加
							validators: {
								notEmpty: {
									message: '总计叠加不能为空'
								},
								regexp: {
									regexp: /^(0|[1-9]\d*)$/,
									message: '种数只能为正整数'
								},
								stringLength: {
									max: 5,
									message: '总计种数不能超过5位数'
								}
							}
						},
					}
				});
				/* $("#PlusButton").click(function() {
					$("#defaultForm").bootstrapValidator('validate');
				}); */
			});
		</script>
</head>
<body>
<div  class="route_bg">
	<a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
	<a href="#">标的增益设置页面</a>
</div>
	<div class="container">
			<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/plus/insertPlustwo.action" id="defaultForm">
				<input type="hidden" name="tid" value="${tid}">
				<!--加息券设置-->
				<h3>加息券设置</h3>
				<!--是否允许加息券-->
				<div class="form-group">
					<label for="isAInt" class="col-sm-3 control-label laber_from">是否允许加息券</label>
					<div class="col-sm-3">
						<select name="isaint" id="isAInt" class="form-control">
							<option value="">请选择</option>
							<option value="1">允许</option>
							<option value="0">不允许</option>
						</select>
					</div>
				</div>
				<!--单次张数-->
				<div id="aint">
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="AOnceInt">单次张数</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="aonceint" id="AOnceInt" placeholder="单次允许使用加息张数" class="form-control" />
								<span class="input-group-addon">张</span>
							</div>
						</div>
					</div>
					<!--总计张数-->
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="ATotalInt">总计张数</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="atotalint" id="ATotalInt" placeholder="允许使用加息总张数" class="form-control" />
								<span class="input-group-addon">张</span>
							</div>
						</div>
					</div>
					<!--单张收益-->
					<div class="form-group">
						<label class="col-sm-3 control-label laber_from" for="AOneQrofit">单张收益</label>
						<div class="col-sm-3">
							<div class="input-group">
								<input type="text" name="aoneqrofit" id="AOneQrofit" placeholder="允许单张加息收益" class="form-control" />
								<span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
				</div>
				<h3>红包设置(类现金)</h3>
				<!--类型金-->
				<!--是否允许类现金卷-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAVoucher">是否允许类现金卷</label>
					<div class="col-sm-3">
						<select name="isavoucher" id="isAVoucher" class="form-control">
							<option value="">请选择</option>
							<option value="1">允许</option>
							<option value="0">不允许</option>
						</select>
					</div>
				</div>
				<!--单次张数-->
				<div id="cash">
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AOnceVoucher">单次张数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="aoncevoucher" id="AOnceVoucher" placeholder="单次允许类现金卷张数" class="form-control" />
							<span class="input-group-addon">张</span>
						</div>
					</div>
				</div>
				<!--总计张数-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="ATotalVoucher">总计张数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="atotalvoucher" id="ATotalVoucher" placeholder="总计允许类现金卷张数" class="form-control" />
							<span class="input-group-addon">张</span>
						</div>
					</div>
				</div>
				<!--单张金额-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AOneVAmount">单张金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="aonevamount" id="AOneVAmount" placeholder="允许单张类现金额度" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				</div>
				<h3>红包设置(假现金)</h3>
				<!--是否允许假现金卷-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isALikeVoucher">是否允许假现金卷</label>
					<div class="col-sm-3">
						<select name="isalikevoucher" id="isALikeVoucher" class="form-control">
							<option value="">请选择</option>
							<option value="1">允许</option>
							<option value="0">不允许</option>
						</select>
					</div>
				</div>
				<!--单次张数-->
				<div id="Fakecash">
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AOnceLikeVoucher">单次张数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="aoncelikevoucher" id="AOnceLikeVoucher" placeholder="单次允许假现金卷张数" class="form-control" />
							<span class="input-group-addon">张</span>
						</div>
					</div>
				</div>
				<!--总计张数-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="ATotalLikeVoucher">总计张数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="atotallikevoucher" id="ATotalLikeVoucher" placeholder="总计允许假现金卷张数" class="form-control" />
							<span class="input-group-addon">张</span>
						</div>
					</div>
				</div>
				<!--单张金额-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AOneLVAmount">单张金额</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="aonelvamount" id="AOneLVAmount" placeholder="允许单张假现金额度" class="form-control" />
							<span class="input-group-addon">元</span>
						</div>
					</div>
				</div>
				</div>
				<h3>叠加限制</h3>
				<!--叠加限制-->
				<!--单词叠加-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="AOncePlusMode">单次叠加</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="aonceplusmode" id="AOncePlusMode" placeholder="单次允许的增益方式" class="form-control" />
							<span class="input-group-addon">种</span>
						</div>
					</div>
				</div>
				<!--总计叠加-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="ATotalPlusMode">总计叠加</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="atotalplusmode" id="ATotalPlusMode" placeholder="总计允许的增益方式" class="form-control" />
							<span class="input-group-addon">种</span>
						</div>
					</div>
				</div>
				<h3>增益清算</h3>

				<!--设置付款人-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="payForPlusMan">付款人</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" name="payforplusman" id="payForPlusMan" placeholder="投标成功后多少天" class="form-control" value="MDT000001" readonly/>
							<span class="input-group-addon">天</span>
						</div>
					</div>
				</div>
				<!--清算方式设置-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="clearMode">清算方式</label>
					<div class="col-sm-3">
						<select name="clearmode" id="clearMode" class="form-control">
							<option value="">请选择</option>
							<option value="1">结标清算</option>
							<option value="2">首期清算</option>
							<option value="3">每期清算</option>
							<option value="4">尾期清算</option>
						</select>
					</div>
				</div>
				<!--资金清算是否需要审核-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isAudit">资金清算是否需要审核</label>
					<div class="col-sm-3">
						<select name="isaudit" id="isAudit" class="form-control">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<!--是否为模板-->
				<div class="form-group">
					<label class="col-sm-3 control-label laber_from" for="isTemplet">是否为模板</label>
					<div class="col-sm-3">
						<select name="istemplet" id="isTemplet" class="form-control">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
					</div>
				</div>
				<!-- 备注 -->
			   <div class="form-group">
			      <label class="control-label col-sm-3">备注</label>
			      <div class="col-sm-3">
			      <textarea rows="3" name="remark" class="form-control"></textarea>
			      </div>
			   </div>
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-default" id="PlusButton">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default" onclick="javascritp:;history.go(-1)">返回列表</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>