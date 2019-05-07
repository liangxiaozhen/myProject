<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>提低卷活动奖励编辑页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<div class="tab-content" id="tab-content">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0)">提抵卷奖励规则设置</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<form id="wCCAwardRule">
		<!-- 提抵卷奖励规则 开始 -->
		<div id="tab2">
			<div class="row" style="margin-top: 30px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">制表人</label>
					<div class="col-sm-6">
						<input type="hidden" class="form-control"
							value="${wCCAwardRule.id}" name="id" id="id"> <input
							type="text" class="form-control" readonly="readonly"
							value="${wCCAwardRule.addman}" name="addman" id="addman"
							placeholder="请输入制表人名字....">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">注册后时间限制</label>
					<div class="col-sm-6">
						<input type="text" class="form-control"
							value="${wCCAwardRule.finishtime}" name="finishtime"
							id="finishtime" placeholder="请输入注册后时间限制....">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">备注</label>
					<div class="col-sm-6">
						<textarea class="form-control" name="remark" id="remark"
							data-remark="${wCCAwardRule.remark}"
							placeholder="请输入150字以内的备注信息...."></textarea>
					</div>
				</div>
			</div>

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">奖品的ID</label>
					<div class="col-sm-6">
						<select onchange="wCCAwardRuleEdit.low_findaname(this)"
							name="awardid" id="awardid"
							data-awardid="${wCCAwardRule.awardid}">
							<option value="0">--请选择奖品ID--</option>
							<c:if test="${not empty awardList}">
								<c:forEach items="${awardList}" var="award">
									<option value="${award.id}">${award.id}</option>
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
						<input type="text" value="${wCCAwardRule.awardname}"
							name="awardname" id="awardname" class="form-control" />
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">奖品份数</label>
					<div class="col-sm-6">
						<input class="form-control" value="${wCCAwardRule.awardcopies}"
							name="awardcopies" id="awardcopies" placeholder="请输入奖品份数....">
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">发放方式</label>
					<div class="col-sm-6">
						<select name="distributetype" id="distributetype"
							data-distributetype="${wCCAwardRule.distributetype}">
							<option value="0">--请选择奖品发放方式--</option>
							<option value="1">系统</option>
							<option value="2">人工</option>
						</select>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">奖励方式</label>
					<div class="col-sm-6">
						<select name="awardtype"
							onchange="wCCAwardRuleEdit.low_awardtype(this)" id="awardtype"
							data-awardtype="${wCCAwardRule.awardtype}">
							<option value="0">-----请选择奖励方式-----</option>
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
						<input type="text" class="form-control"
							value="${wCCAwardRule.quota}" id="quota" name="quota"
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
						<input type="text" id="aname" class="form-control"
							value="${wCCAwardRule.awardratio}" id="awardratio"
							name="awardratio" placeholder="请输入定额金额百分比..." />
					</div>
				</div>
				<div class="col-md-8" style="margin-top: 10px;">
					<label class="col-sm-2 text-right">奖励最低值</label>
					<div class="col-sm-6">
						<input type="text" id="aname" class="form-control"
							value="${wCCAwardRule.awardmin}" id="awardmin" name="awardmin"
							placeholder="请输入定额金额奖励最低值..." />
					</div>
				</div>
				<div class="col-md-8" style="margin-top: 10px;">
					<label class="col-sm-2 text-right">奖励最高值</label>
					<div class="col-sm-6">
						<input type="text" id="aname" class="form-control"
							value="${wCCAwardRule.awardmax}" id="awardmax" name="awardmax"
							placeholder="请输入定额金额奖励最高值..." />
					</div>
				</div>
			</div>
			<!-- 奖励金额的百分比方式  显示框结束 -->

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8">
					<label for="exampleInputName2" class="col-sm-2 text-right">会员等级设置</label>
					<label> <input type="radio" name="ugrade" class="ugrade"
						value="1">全部会员等级
					</label> <label> <input type="radio" name="ugrade" class="ugrade"
						value="2" checked="checked">选择会员等级
					</label>
				</div>
			</div>

			<!-- 选择会员等级选择开始 -->
			<div class="row" id="ugrade-box" style="margin-top: 20px;">
				<div class="col-md-12 col-md-offset-1">
					<input type="hidden" value="${params}" id="ulist" />
					<c:forEach items="${userGrades}" var="user">
						<label class="checkbox-inline"><input type="checkbox"
							class="ugrades" name="ugradesx" value="${user.ugrade}">${user.ugradename}
						</label>
					</c:forEach>
				</div>
			</div>

			<div class="row" style="margin-top: 20px;">
				<div class="col-md-8 text-left">
					<button type="button" class="btn btn-success" id="gj_save"
						onclick="wCCAwardRuleEdit.low_save(this)">保存</button>
					<button class="btn btn-info" type="button"
						onclick="wCCAwardRuleEdit.low_callback(this)">返回</button>
				</div>
			</div>
		</div>
		<!-- 提抵卷奖励规则 结束 -->
	</form>

	<script type="text/javascript">
 	$(function(){
 		$("#awardid").val($("#awardid").data("awardid"));
  		//编辑的时候会员等级的默认选中
		 var ulist = $("#ulist").val();
     	if(ulist.length>0){
    		var u = ulist.split(",");
     		$.each(u,function(index,value){
    			$("#ugrade-box input[type='checkbox']").each(function(){
    					if($(this).val()==value){
    					$(this).attr("checked",true);
    				}
    			});
    		});
    	}  
    	
 		var remark = $("#remark").data("remark");
 		var awardtype = $("#awardtype").data("awardtype");
 		var distributetype =$("#distributetype").data("distributetype");
 		//奖品发放方式
 		$("#distributetype").val(distributetype);
 		//奖品类型
 		$("#awardtype").val(awardtype);
 		//备注信息赋值
  		 $("#remark").text(remark);
  		if(awardtype ==1){
 			$("#awardType1").show();
 			$("#awardType2").hide();
  		}else{
  			$("#awardType2").show();
 			$("#awardType1").hide();
 		}
 		$(".ugrade").change(function(){
 			var $val = $("input[name='ugrade']:checked").val();
 			if($val ==1){
 				//会员等级选择框隐藏
 				$("#ugrade-box").hide();
 			}else{
 				//会员等级选择框显示
 				$("#ugrade-box").show();
 			}
 		});
  	});
 	var wCCAwardRuleEdit={
 			//提低卷活动规则修改方法
 			low_save:function(obj){
 				var params = $("#wCCAwardRule").serializeArray();
				 var json = this.jsonParams(params);
				 if(isEmpty(json["finishtime"])){
 					 $("#finishtime").select();
 					 loading("请输入注册后时间限制....",4);
 					 return false;
 				 }
				 if($("#awardid").val() == 0){
					 loading("请选择奖品ID....",4);
 					 return false;
				 } 
				 var remark =$("#remark").val();
				 if(remark.length >=150){
					 loading("您输入的备注信息超过150字数限制",4);
					 return false;
				 }
				 if(isEmpty(json["awardcopies"])){
 					 $("#awardcopies").select();
 					 loading("请输入奖品份数....",4);
 					 return false;
 				 }
				 if(isNaN(json["awardcopies"])){
						loading("请输入纯数字...",4);
						$("#awardcopies").select();
						return false;
				 };
				 if($("#distributetype").val() == 0){
					 loading("请选择奖品发放方式....",4);
 					 return false;
				 }
				 if($("#awardtype").val() == 0){
					 loading("请选择奖励方式....",4);
 					 return false;
				 }
				 if(json["awardtype"] == 1){
						if(isEmpty(json["quota"])){
							loading("请输入定额金额...",4);
	 						$("#quota").select();
	  						return false;
						};
						if(isNaN(json["quota"])){
	 						loading("定额金额请输入纯数字...",4);
	  						$("#quota").select();
	   						return false;
	 					};
					}
					
					if(json["awardtype"] == 2){
						if(isEmpty(json["awardratio"])){
							loading("请输入定额百分比...",4);
	 						$("#awardratio").select();
	  						return false;
						};
						if(isEmpty(json["awardmin"])){
							loading("请输入定额金额奖励最低值...",4);
	 						$("#awardmin").select();
	  						return false;
						};
						if(isEmpty(json["awardmax"])){
							loading("请输入定额金额奖励最高值...",4);
	 						$("#awardmax").select();
	  						return false;
						};
						
						if(isNaN(json["awardmin"])){
	 						loading("奖励最低值请输入纯数字...",4);
	  						$("#awardmin").select();
	   						return false;
	 					};
	 					if(isNaN(json["awardmax"])){
	 						loading("奖励最高值请输入纯数字...",4);
	  						$("#awardmax").select();
	   						return false;
	 					};
	 					if(isNaN(json["awardratio"])){
	 						loading("金额的百分比请输入纯数字...",4);
	  						$("#awardratio").select();
	   						return false;
	 					};
					}
				 
 				 //部分会员等级选择 验证
				 if($(".ugrade")[1].checked){
	 				 var ugrades = $(".ugrades").length;
	  				 var count1 = 0;
	  				 for(var i = 0;i<ugrades;i++){
	 					 if($(".ugrades")[i].checked){
	 						count1 +=1;
	 					 }
	  				 }
	 				if(count1 == 0){
						 loading("请至少选择一个会员等级",4);
						 return false;
					 }
 				 }
 				$(obj).removeAttr("onclick").text("保存中...");
				$.tzAjax.request({
					model:"admin/wCCAwardRule",
					method:"update.action",
					error:function(){
						$(obj).attr("onclick","wCCAwardRuleEdit.low_save(this)").text("保存");
 					},
					callback:function(data){
						$("#gj_save").attr("onclick","wCCAwardRuleEdit.low_save(this)").text("保存");
						var obj = $.parseJSON(data);
						if(obj.result =="fail"){
							loading("保存失败",4);
						}else if(obj.result=="success"){
							alert("保存成功");
							window.location.href=basePath+"/admin/wCCAwardRule/list.action";
						}
					}
				},params);
  			},
 			//返回
 			low_callback:function(){
 				window.location.href=basePath+"/admin/wCCAwardRule/list.action";
 			},
 			//根据ID查找奖品名称
 			low_findaname:function(obj){
 				//获取选中的奖品ID
 				var opid = $(obj).val();
 				$.tzAjax.request({
 					model:"admin/wCCAwardRule",
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
  			},
  			jsonParams:function(params){
				var jsonarr = {};
			if(params){
				var length = params.length;
				for(var i=0;i<length;i++){
					var data = params[i];
					jsonarr[data.name] = data.value;
				}
			}
			return jsonarr;
		}
 	};
 </script>
</body>
</html>
