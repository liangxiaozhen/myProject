<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-6">
		<div class="tab-content" id="tab-content">
			<ul class="nav nav-tabs">
				<li class="active"><a href="javascript:void(0)">提抵卷活动规则设置</a></li>
				<li><a href="javascript:void(0)">提抵卷奖励规则设置</a></li>
			</ul>
		</div>
	</div>
</div>
<!-- 提低卷新增 页面  开始-->
<form id="ugradeLow">
	<!-- 提抵卷活动规则 开始 -->
	<div id="tab1">
		<div class="row" style="margin-top: 60px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">活动编号</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="actno" name="actno"
						placeholder="请输入活动编号....">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">活动名称</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="actname" name="actname"
						placeholder="请输入活动名称....">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">制表人</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="addman" name="addman"
						placeholder="请输入制表人名字....">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">备注</label>
				<div class="col-sm-6">
					<textarea class="form-control textarea" id="remark" name="remark"
						rows="" cols="3" placeholder="请输入150字以内的备注信息..."></textarea>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">活动生效日期</label>
				<div class="col-sm-6">
					<input type="text" class="Wdate" id="actbtime" name="actbtime"
						placeholder="请选中日期...."
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">活动截止日期</label>
				<div class="col-sm-6">
					<input type="text" class="Wdate" id="actetime" name="actetime"
						placeholder="请选中日期...."
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">执行时间点</label>
				<div class="col-sm-6">
					<input type="text" class="Wdate" id="executetime"
						name="executetime" placeholder="请选中日期...."
						onclick="WdatePicker({dateFmt:'HH:mm:ss'})">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">活动规则</label>
				<label> <input type="radio" name="actrule" value="1"
					checked="checked">升降级只发一次
				</label> <label> <input type="radio" name="actrule" value="2">升降级重发取消之前奖品
				</label>
			</div>
		</div>

		<div class="row" style="margin-top: 20px;">
			<div class="col-md-9">
				<label for="exampleInputName2" class="col-sm-2 text-right">获奖名单是否审核</label>
				<div class="col-sm-7">
					<select name="isauditalist">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">会员等级设置</label>
				<label> <input type="radio" name="ugrade"
					class="insert-ugrade-radio" value="1" checked="checked">全部会员等级
				</label> <label> <input type="radio" name="ugrade"
					class="insert-ugrade-radio" value="2">选择会员等级
				</label>
			</div>
		</div>

		<!-- 选择会员等级选择开始 -->
		<div class="row" id="ugrade-checkbox-div" style="margin-top: 20px;">
			<div class="col-md-12 col-md-offset-1">
				<c:forEach items="${userGrades}" var="user">
					<label class="checkbox-inline"><input type="checkbox"
						class="ugrades" name="ugrades" value="${user.ugrade}">${user.ugradename}
					</label>
				</c:forEach>
			</div>
		</div>
		<!-- 选择会员等级选择结束 -->

		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">排除名单表设置</label>
				<div class="col-sm-6">
					<label> <input type="radio" name="removenameno"
						class="insert-removenameno-radio" value="1" checked="checked">全部排除名单表编号
					</label> <label> <input type="radio" name="removenameno"
						class="insert-removenameno-radio" value="2">选择排除名单表编号
					</label>
				</div>
			</div>
		</div>

		<!-- 选择排除名单表开始 -->
		<div class="row" id="removeName-checkbox-div"
			style="margin-top: 20px;">
			<div class="col-md-12 col-md-offset-1">
				<c:forEach items="${removeNames}" var="remove">
					<label class="checkbox-inline"> <input type="checkbox"
						name="removenamenos" class="removenamenos"
						value="${remove.nameno}"> 名单表类型：<span class="red">${remove.nametype}</span>&nbsp;&nbsp;
						名单表名称：<span class="red">${remove.name}</span>&nbsp;&nbsp; 名单表编号：<span
						class="red">${remove.nameno}</span>
					</label>
				</c:forEach>
			</div>
		</div>
		<!-- 选择排除名单表结束 -->

		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8 text-left">
				<button class="btn btn-success" type="button"
					onclick="ugradeLow.callnext(this)">下一步</button>
				<button class="btn btn-info" type="button"
					onclick="ugradeLow.callback(this)">返回列表</button>
			</div>
		</div>
	</div>
	<!-- 提抵卷活动规则 结束 -->

	<!-- 提抵卷奖励规则 开始 -->
	<div id="tab2">
		<div class="row" style="margin-top: 60px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">制表人</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="addman_addman"
						id="addman_addman" placeholder="请输入制表人名字....">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">注册后时间限制</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="finishtime"
						id="finishtime" placeholder="请输入注册后时间限制....">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">备注</label>
				<div class="col-sm-6">
					<textarea class="form-control" name="remark_remark"
						id="remark_remark" placeholder="请输入150字以内的备注信息...."></textarea>
				</div>
			</div>
		</div>

		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">奖品的ID</label>
				<div class="col-sm-6">
					<select onchange="ugradeLow.findaname(this)" name="awardid">
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
					<input type="text" name="awardname" id="awardname"
						class="form-control" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">奖品份数</label>
				<div class="col-sm-6">
					<input class="form-control" name="awardcopies" id="awardcopies"
						placeholder="请输入奖品份数....">
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">发放方式</label>
				<div class="col-sm-6">
					<select name="distributetype">
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
					<select onChange="ugradeLow.awardType(this)" name="awardtype">
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
					<input type="text" class="form-control" id="quota" name="quota"
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
					<input type="text" id="aname" class="form-control" id="awardratio"
						name="awardratio" placeholder="请输入定额金额百分比..." />
				</div>
			</div>
			<div class="col-md-8" style="margin-top: 10px;">
				<label class="col-sm-2 text-right">奖励最低值</label>
				<div class="col-sm-6">
					<input type="text" id="aname" class="form-control" id="awardmin"
						name="awardmin" placeholder="请输入定额金额奖励最低值..." />
				</div>
			</div>
			<div class="col-md-8" style="margin-top: 10px;">
				<label class="col-sm-2 text-right">奖励最高值</label>
				<div class="col-sm-6">
					<input type="text" id="aname" class="form-control" id="awardmax"
						name="awardmax" placeholder="请输入定额金额奖励最高值..." />
				</div>
			</div>
		</div>
		<!-- 奖励金额的百分比方式  显示框结束 -->

		<div class="row" style="margin-top: 20px;">
			<div class="col-md-8">
				<label for="exampleInputName2" class="col-sm-2 text-right">会员等级设置</label>
				<label> <input type="radio" name="ugrade_ugrade"
					class="insert-ugrade-radio1" value="1" checked="checked">全部会员等级
				</label> <label> <input type="radio" name="ugrade_ugrade"
					class="insert-ugrade-radio1" value="2">选择会员等级
				</label>
			</div>
		</div>

		<!-- 选择会员等级选择开始 -->
		<div class="row" id="ugrade-checkbox-div1" style="margin-top: 20px;">
			<div class="col-md-12 col-md-offset-1">
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
					onclick="ugradeLow.save(this)">保存</button>
				<button class="btn btn-info" type="button"
					onclick="ugradeLow.callnext2(this)">返回上一步</button>
				<button class="btn btn-info" type="button"
					onclick="ugradeLow.callback(this)">取消</button>
			</div>
		</div>
	</div>
	<!-- 提抵卷奖励规则 结束 -->
</form>
<!-- 提低卷新增 页面  开始-->
<script type="text/javascript">
	$(function() {
 		var remark = $("#remark").data("remark");
		if(isNotEmpty(remark)){
			$("#remark").text(remark);
		}
		//提抵卷活动设置隐藏选择会员等级 框框
		$("#ugrade-checkbox-div").hide();
		//提抵卷奖励设置框隐藏选择会员等级
		$("#ugrade-checkbox-div1").hide();
 		//提抵卷奖励设置框隐藏
		$("#tab2").hide();
		//隐藏定额奖励设置 框框
		$("#awardType1").hide();
		//隐藏奖励百分比设置 框框
		$("#awardType2").hide();
		
		//隐藏提低卷活动规则设置选择排除名单表 框框
		$("#removeName-checkbox-div").hide();
		//隐藏提低卷奖励规则设置选择排除名单表 框框
		$("#removeName-checkbox-div2").hide();
 		$(".insert-ugrade-radio,.insert-ugrade-radio2,.removeName,.insert-ugrade-radio1,.insert-removenameno-radio").change(function() {
			var $inputval = $("input[name='ugrade']:checked").val();
			var $removeNameval = $("input[name='removenameno']:checked").val();
			var $inputva2 = $("input[name='ugrade_ugrade']:checked").val();
			var $removenameno2 = $("input[name='removenameno2']:checked").val();
   			if ($inputval == 1) {
				//隐藏提抵卷活动设置选择会员等级 框框
				$("#ugrade-checkbox-div").hide();
			} else {
				//显示提抵卷活动设置选择会员等级 框框
				$("#ugrade-checkbox-div").show();
			}
			if ($removeNameval == 1) {
				//隐藏提抵卷活动设置选择排除名单表 框框
				$("#removeName-checkbox-div").hide();
			} else {
				//显示提抵卷活动设置选择排除名单表 框框
				$("#removeName-checkbox-div").show();
			}
			
			
			if ($inputva2 == 1) {
				//隐藏提低卷奖励规则设置选择会员等级 框框
				$("#ugrade-checkbox-div1").hide();
			} else {
				//显示提低卷奖励规则设置选择会员等级 框框
				$("#ugrade-checkbox-div1").show();
			}
			if ($removenameno2 == 1) {
				//隐藏提低卷奖励规则设置选择排除名单 框框
				$("#removeName-checkbox-div2").hide();
			} else {
				//显示提低卷奖励规则设置选择排除名单  框框
				$("#removeName-checkbox-div2").show();
			}
		});
 	});
		var ugradeLow = {
				//保存方法
				save:function(obj){
  					var params = $("#ugradeLow").serializeArray();
  						//验证开始
 						var json = this.jsonParams(params);
 						if(isEmpty(json["addman_addman"])){
 	 						loading("请输入制表人名字...",4);
 							$("#addman_addman").select();
 							return false;
 						}
 						if(isEmpty(json["finishtime"])){
 	 						loading("请输入注册时间限制...",4);
 							$("#finishtime").select();
 							return false;
 						}
 						var remark = $("#remark_remark").val();
 	  					if(remark.length >=150){
 	  						loading("您输入的备注信息超过150字限制.....",4);
 	  						return false;
 	  					}
 						if(isNaN(json["finishtime"])){
 							loading("注册时间请输入纯数字...",4);
 							$("#finishtime").select();
 							return false;
 						};
 						if(json["awardid"] < 1){
 	 						loading("请选择奖品ID...",4);
 	 						return false;
 						}
 						if(isEmpty(json["awardcopies"])){
 	 						loading("请输入奖品份数...",4);
 	 						$("#awardcopies").select();
 	 						return false;
 						}
 						if(isNaN(json["awardcopies"])){
 							loading("请输入纯数字...",4);
 	 						$("#awardcopies").select();
 	  						return false;
 						};
 						if(json["distributetype"] <1){
 	 						loading("请选择奖品发放方式..",4);
 	 						return false;
 						}
 						if(json["awardtype"] <1){
 	 						loading("请选择奖励方式...",4);
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
					 if($(".insert-ugrade-radio1")[1].checked){
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
  					//验证结束
					$(obj).removeAttr("onclick").text("保存中...");
      				$.tzAjax.request({
						model:"admin/withdrawsCashCoupon",
						method:"save.action",
						error:function(){
 							$(obj).attr("onclick","ugradeLow.save(this)").text("保存");
						},
						callback:function(data){
 							$("#gj_save").attr("onclick","ugradeLow.save(this)").text("保存");
 							var obj = $.parseJSON(data);
							if(obj.result == "fail"){
								loading("保存失败",4);
							}else if(obj.result == "success"){
								alert("保存成功");
						     window.location.href=basePath+"/admin/withdrawsCashCoupon/list.action";
 							} 
						}
					},params);
				},
				//根据奖品ID查询奖品名称
				findaname:function(obj){
 			        $.tzAjax.request({
			        	model:"/admin/withdrawsCashCoupon",
			        	method:"/findAname.action",
			        	callback:function(data){
			        		var obj = $.parseJSON(data);
 			        		$("#awardname").val(obj);
			        	}
			        },{"id":$(obj).val()});
				},
 				//取消
				callback:function(){
					window.location.href=basePath+"/admin/withdrawsCashCoupon/list.action";
				},
				//下一步
				callnext:function(){
					var params = $("#ugradeLow").serializeArray();
					var json = this.jsonParams(params);
					if(isEmpty(json["actno"])){
 						loading("请输入活动编号...",4);
						$("#actno").select();
						return false;
					}
					if(isEmpty(json["actname"])){
 						loading("请输入活动名称...",4);
						$("#actname").select();
						return false;
					}
					if(isEmpty(json["addman"])){
 						loading("请输入制表人名字...",4);
						$("#addman").select();
						return false;
					}
					var remark = $("#remark").val();
  					if(remark.length >=150){
  						loading("您输入的备注信息超过150字限制.....",4);
  						return false;
  					}
 					if(isEmpty(json["actbtime"])){
 						loading("请选择活动生效日期...",4);
 						return false;
					}
					if(isEmpty(json["actetime"])){
 						loading("请选择活动截止日期...",4);
 						return false;
					} 
					if(isEmpty(json["executetime"])){
 						loading("请选择每日执行时间点...",4);
 						return false;
					}
					//部分会员等级选择 验证
	 				 if($(".insert-ugrade-radio")[1].checked){
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
	 				 //排除名单表部分  验证
	  				 if($(".insert-removenameno-radio")[1].checked){
		 				 //排除名单表部分选择length
		 				 var length = $(".removenamenos").length;
		  				 var count = 0;
		 				 for(var i = 0;i<length;i++){
		 					 if($(".removenamenos")[i].checked){
		 						 count +=1;
		 					 }
		  				 }
		 				 if(count == 0){
		 					 loading("请至少选择一个排除名单表",4);
		 					 return false;
		 				 }
	   				 }
  					$("#tab-content").find(".nav li").eq(0).removeClass("active").end().eq(1).addClass("active");
 					//提抵卷活动规则设置框隐藏
					$("#tab1").hide();
					//提抵卷奖励设置框显示
					$("#tab2").show();
				},
				//返回上一步
				callnext2:function(){
					$("#tab-content").find(".nav li").eq(1).removeClass("active").end().eq(0).addClass("active");
 					//提抵卷奖励设置框隐藏
					$("#tab2").hide();
					//提抵卷活动规则设置框显示
					$("#tab1").show();
				},
				//奖励方式显示框
				awardType:function(obj){
					var valObj = $(obj).val();
					if(valObj == 1){
						//显示定额奖励设置 框框
						$("#awardType1").show();
						//隐藏奖励百分比设置 框框
						$("#awardType2").hide();
 					}else if(valObj == 2){
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
 				},
				//格式验证方法
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