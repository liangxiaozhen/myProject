<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>标的活动奖励规则编辑页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<script type="text/javascript"
	src="${basePath}/js/validate/jquery.validate.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<div class="tab-content" id="tab-content">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)">标的活动奖励规则设置</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 标的活动奖励规则 开始 -->
	<div class="row" style="margin-top: 20px;">
		<form id="wCCAwardRule">
			<div class="alert alert-success text-center" role="alert"
				style="font-size: 14px;">标的活动奖励规则修改</div>
			<!-- 标的活动奖励规则设置 start -->
			<div id="ActivityAwardRule_box">
				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">累投金额值_低位</label>
						<div class="col-sm-6">
							<input type="hidden" value="${activityAwardRule.id}" name="id" />
							<input type="text" value="${activityAwardRule.tminmoney}"
								class="form-control" name="tminmoney" id="tminmoney"
								placeholder="请输入累投金额值_低位...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">累投金额值_高位</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.tmaxmoney}"
								class="form-control" name="tmaxmoney" id="tmaxmoney"
								placeholder="请输入累投金额值_高位...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖品的ID</label>
						<div class="col-sm-6">
							<select name="awardid" id="awardid" class="form-control"
								onchange="activityAwardRuleEdit.findAwardName(this)"
								data-awardid="${activityAwardRule.awardid}">
								<option value="">--请选择奖品ID--</option>
								<c:if test="${not empty awardList}">
									<c:forEach items="${awardList}" var="award">
										<option value="${award.id}">${award.id}----奖品名称--${award.aname}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖品的名称</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.awardname}"
								class="form-control" name="awardname" id="awardname"
								readonly="readonly" placeholder="请选择奖品ID...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖项名称</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.awardprize}"
								class="form-control" name="awardprize" id="awardprize"
								placeholder="请输入奖项的名称...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖品份数</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.awardcopies}"
								class="form-control" name="awardcopies" id="awardcopies"
								placeholder="请输入奖品份数...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖品发放方式</label>
						<div class="col-sm-6">
							<select name="distributetype" id="distributetype"
								class="form-control"
								data-distributetype="${activityAwardRule.distributetype}">
								<option value="">--请选择奖品发放方式--</option>
								<option value="1">系统</option>
								<option value="2">人工</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖品类型</label>
						<div class="col-sm-6">
							<select name="awardclass" id="awardclass" class="form-control"
								data-awardclass="${activityAwardRule.awardclass}">
								<option value="">--请选择奖品发放方式--</option>
								<option value="1">累投</option>
								<option value="2">单标</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖品是否审核
						</label>
						<div class="col-sm-6">
							<select name="isaudit" id="isaudit" class="form-control"
								data-isaudit="${activityAwardRule.isaudit}">
								<option value="">--请选择是否审核 --</option>
								<option value="1">需要审核</option>
								<option value="2">不需要审核</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖品发放时机</label>
						<div class="col-sm-6">
							<select name="distributemode" id="distributemode"
								class="form-control"
								data-distributemode="${activityAwardRule.distributemode}">
								<option value="">--请选择奖品发放时机-</option>
								<option value="1">成功</option>
								<option value="2">满标</option>
								<option value="3">活动结束</option>
							</select>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖励方式</label>
						<div class="col-sm-6">
							<select onChange="activityAwardRuleEdit.low_awardtype(this)"
								name="awardtype" id="awardtype" class="form-control"
								data-awardtype="${activityAwardRule.awardtype}">
								<option value="">-----请选择奖励方式-----</option>
								<option value="1">定额奖励方式</option>
								<option value="2">投资金额的百分比方式</option>
							</select>
						</div>
					</div>
				</div>

				<!-- 定额奖励方式  显示框开始 -->
				<div class="row" style="margin-top: 10px;" id="awardType1">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">定额奖励</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.quota}"
								class="form-control" id="quota" name="quota"
								placeholder="请输入定额金额..." />
						</div>
					</div>
				</div>
				<!-- 定额奖励方式  显示框结束 -->

				<!-- 奖励金额的百分比方式  显示框开始 -->
				<div class="row" style="margin-top: 10px;" id="awardType2">
					<div class="col-md-8" style="margin-top: 10px;">
						<label class="col-sm-2 text-right">金额的百分比</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.awardratio}"
								id="awardratio" class="form-control" id="awardratio"
								name="awardratio" placeholder="请输入定额金额百分比..." />
						</div>
					</div>
					<div class="col-md-8" style="margin-top: 10px;">
						<label class="col-sm-2 text-right">奖励最低值</label>
						<div class="col-sm-6">
							<input type="text" id="aname"
								value="${activityAwardRule.awardmin}" class="form-control"
								id="awardmin" name="awardmin" placeholder="请输入定额金额奖励最低值..." />
						</div>
					</div>
					<div class="col-md-8" style="margin-top: 10px;">
						<label class="col-sm-2 text-right">奖励最高值</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.awardmax}"
								class="form-control" id="awardmax" name="awardmax"
								placeholder="请输入定额金额奖励最高值..." />
						</div>
					</div>
				</div>
				<!-- 奖励金额的百分比方式  显示框结束 -->

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">首投时间限制</label>
						<div class="col-sm-6">
							<input type="text" id="firsttendertime" name="firsttendertime"
								class="Wdate" style="width: 200px;"
								value="${gj:formatDate(activityAwardRule.firsttendertime,'yyyy-MM-dd')}"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">首投金额限制</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.firsttendermoney}"
								class="form-control" name="firsttendermoney"
								id="firsttendermoney" placeholder="请输入首投金额限制...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">注册时间限制（多少天内）</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.regeditdayrest}"
								class="form-control" name="regeditdayrest" id="regeditdayrest"
								placeholder="请输入注册时间限制（多少天内）...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖励IP次数限制</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.iprestrict}"
								class="form-control" name="iprestrict" id="iprestrict"
								placeholder="请输入奖励IP次数限制...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">奖励cookie次数限制</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.cookierestrict}"
								class="form-control" name="cookierestrict" id="cookierestrict"
								placeholder="请输入奖励cookie次数限制...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">排名类型</label>
						<div class="col-sm-6">
							<input type="text" value="${activityAwardRule.ranking}"
								class="form-control" name="ranking" id="ranking"
								placeholder="请输入排名类型...">
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 20px;">
					<div class="col-md-8">
						<label for="exampleInputName2" class="col-sm-2 text-right">备注</label>
						<div class="col-sm-6">
							<textarea class="form-control" name="remark" id="remark"
								data-remark="${activityAwardRule.remark}" placeholder="请输入备注..."> </textarea>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 25px;">
					<div class="col-md-6">
						<div class="text-center">
							<button type="button" id="submit" class="btn btn-primary"
								onclick="activityAwardRuleEdit.low_save(this)">保存</button>
							<button type="button" class="btn btn-info"
								onclick="activityAwardRuleEdit.low_callback(this)">返回</button>
							<span class="red" id="form_error"></span>
						</div>
					</div>
				</div>

				<div class="row" style="margin-top: 45px;">
					<div class="col-md-6"></div>
				</div>
			</div>
		</form>
		<!-- 标的活动奖励规则设置 end -->
	</div>
	<script type="text/javascript">
 	$(function(){
 		//隐藏定额奖励设置 框框
		$("#awardType1").hide();
 		//隐藏奖励百分比设置 框框
		$("#awardType2").hide();
 		$("#remark").val($("#remark").data("remark"));//备注信息	
		$("#awardid").val($("#awardid").data("awardid"));//奖品ID
		$("#distributetype").val($("#distributetype").data("distributetype"));//奖品发放方式
		$("#awardclass").val($("#awardclass").data("awardclass"));//奖品类型
		$("#isaudit").val($("#isaudit").data("isaudit"));//是否审核
		$("#distributemode").val($("#distributemode").data("distributemode"));//发放时机
		var awardtype = $("#awardtype").data("awardtype");
		$("#awardtype").val(awardtype);//奖励方式
		if(awardtype == 1){
			//显示定额奖励设置 框框
			$("#awardType1").show();
			//隐藏奖励百分比设置 框框
			$("#awardType2").hide();
		}else if(awardtype == 2){
			//隐藏定额奖励设置 框框
			$("#awardType1").hide();
			//显示奖励百分比设置 框框
			$("#awardType2").show();
		}else{
			//隐藏定额奖励设置 框框
			$("#awardType1").hide();
			//隐藏奖励百分比设置 框框
			$("#awardType2").hide();
		}
  	});
 	var activityAwardRuleEdit={
 			//根据奖品ID查询奖品名称
			findAwardName:function(obj){
			   $.tzAjax.request({
		        	model:"/admin/activityRule",
		        	method:"/findAwardName.action",
		        	callback:function(data){
		        		var obj = $.parseJSON(data);
			        	$("#awardname").val(obj);
		        	}
		        },{"id":$(obj).val()});
			 },
 			//提低卷活动规则修改方法
 			low_save:function(obj){
 				if(!check().form()){
 					return;
				}
 				var params = $("#wCCAwardRule").serialize();
 				$("#submit").removeAttr("onclick").text("保存中...");
				$.tzAjax.request({
					model:"admin/activityAwardRule",
					method:"update.action",
					error:function(){$("#submit").attr("onclick","activityAwardRuleEdit.low_save(this)").text("保存");},
					callback:function(data){
						var obj = $.parseJSON(data);
						$("#submit").attr("onclick","activityAwardRuleEdit.low_save(this)").text("保存");
						if(obj.result =="fail"){
							loading("保存失败",4);
						}else if(obj.result=="success"){
							alert("保存成功");
						    window.location.href=basePath+"/admin/activityAwardRule/list.action";
						}
					}
				},params);
  			},
 			//返回
 			low_callback:function(){
 				window.location.href=basePath+"/admin/activityAwardRule/list.action";
 			},
 			//根据ID查找奖品名称
 			low_findaname:function(obj){
 				//获取选中的奖品ID
 				var opid = $(obj).val();
 				$.tzAjax.request({
 					model:"admin/activityAwardRule",
 					method:"findAname.action",
 					callback:function(data){
 						var obj =$.parseJSON(data);
 						$("#awardname").val(obj);
 					}
 				},{"id":opid});
  			},
  			//动态显示奖励方式框 
  			low_awardtype:function(obj){
  				var objVal = $(obj).val();
  				if(objVal==1){
  					$("#awardType1").show();
  		 			$("#awardType2").hide();
  				}else{
  					$("#awardType2").show();
  		 			$("#awardType1").hide();
  				}
  			}
 	};
 	
 	//验证活动
	function check(){
		//验证数字
		jQuery.validator.addMethod("Integer",function(value,element){
			var number = /^([1-9][\d]{0,7}|0)$/;
			return this.optional(element) || (number.test(value));
		},"*&nbsp;请输入有效的正整数");
		//验证float
		jQuery.validator.addMethod("Float",function(value,element){
			var number = /^([1-9][\d]{0,7}|0)(\.\d{1,2})?$/;
			return this.optional(element) || (number.test(value));
		},"*&nbsp;请输入合法的数字");
		 return  $("#wCCAwardRule").validate({
			 errorPlacement: function (error, element) {
					var p = $("<em></em>").append(error);
					p.appendTo(element.parent().parent());
					p.css({"color":"red"});
				},
  			 rules :{
 				 //活动奖励验证开始
				 tminmoney:{
					 "required" : true,
					 "Float" : true
				 },
				 tmaxmoney:{
					 "required" : true,
					 "Float" : true
				 },
				 awardid:{
					 "required" : true
				 },
				 awardclass:{
					 "required" : true
				 },
				 awardprize:{
					 "required" : true
				 },
 				 awardcopies:{
					 "required" : true,
					 "Integer" : true
				 },
				 awardtype:{
					 "required" : true
				 },
				 quota:{
					 "required" : true,
					 "Float" : true
				 },
				 awardratio:{
					 "required" : true,
					 "Float" : true
				 },
				 awardmin:{
					 "required" : true,
					 "Float" : true
				 },
				 awardmax:{
					 "required" : true,
					 "Float" : true
				 },
				 distributemode:{
					 "required" : true
				 },
				 distributetype:{
					 "required" : true
				 },
				 firsttendertime:{
					 "required" : true
				 },
				 firsttendermoney:{
					 "required" : true,
					 "Float" : true
				 },
				 regeditdayrest:{
					 "required" : true,
					 "Integer" : true
				 },
				 iprestrict:{
					 "required" : true,
					 "Integer" : true
				 },
				 cookierestrict:{
					 "required" : true,
					 "Integer" : true
				 },
				 isaudit:{
					 "required" : true
				 },
				 ranking:{
					 "required" : true
				 }
    		 },
			 messages :{
				//活动奖励验证开始
				 tminmoney:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入累投金额值_低位"
				 },
				 tmaxmoney:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入累投金额值_高位"
				 },
				 awardid:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择奖品ID"
				 },
 				 awardclass:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖品类型"
				 },
				 awardprize:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖项名称"
				 },
				 awardcopies:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖品份数"
				 },
				 awardtype:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择奖励方式"
				 },
				 quota:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励定额"
				 },
				 awardratio:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励百分比数"
				 },
				 awardmin:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励最低值"
				 },
				 awardmax:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励最高值"
				 },
				 distributemode:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择发放时机"
				 },
				 distributetype:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择发放方式"
				 },
				 firsttendertime:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入首投时间限制"
				 },
				 firsttendermoney:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入首投金额限制"
				 },
				 regeditdayrest:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入注册时间限制（多少天内）"
				 },
				 iprestrict:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励IP次数限制"
				 },
				 cookierestrict:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励cookie次数限制"
				 },
				 isaudit:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择是否审核"
				 },
				 ranking:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入排名类型"
				 },
 				 remark :{
					 "required" :"*&nbsp;备注信息最多输入100字符"
				 }
 			 }
		 });
  	};
 </script>
</body>
</html>
