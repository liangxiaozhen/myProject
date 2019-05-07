<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>标的利息管理费设置编辑页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<link href="${basePath }/resources/Css/jujian.css" rel="stylesheet" type="text/css">
 </head>
<body>
	<div class="container">
		<div style="text-align: center;margin-bottom:15px;">
			<h3>标的利息管理费设置</h3>
		</div>
		<div class="col-md-12 column">
			<form class="form-horizontal" id="" action="" method="post">
				<div class="form-group  has-feedback">
					<font class="control-label col-sm-2">设置分类：</font>
					<div class="col-sm-8 classify">
						<p>
							<a href="javascript:void(0);" class="grade beijing">按等级</a>
							 <a href="javascript:void(0);" class="risk">按标风险</a> 
							 <a href="javascript:void(0);" class="subclass" onclick="interestExpenseSave.low_saveUserGradeCategory(this)">新增子分类</a>
							  &nbsp; &nbsp;<span id="bn" style="color: red;"></span>
						</p>
						<p>
					</div>
				</div>
 
				<div class="form-group  has-feedback">
					<div class="col-sm-12 classify">
						<font class="control-label col-sm-2">利息管理费是否审核：</font>
						<p>
 							<select id="low_isaudit">
								<option value="0">--请选择--</option>
 								<option value="1" selected="selected">审核</option>
 								<option value="2">不审核</option>
  							</select>
							 &nbsp; &nbsp;<span id="bn" style="color: red;"></span>
						<p>
					</div>
				</div>
				
				<div class="form-group  has-feedback">
					<div class="col-sm-12 classify">
						<font class="control-label col-sm-2">备注信息：</font>
						<p>
 							 <textarea style="max-width: 800px;height:65px;width:390px;" id="remark"></textarea>
							 &nbsp; &nbsp;<span id="bn" style="color: red;"></span>
						<p>
					</div>
				</div>
 
				<div class="categoryBox" id="categoryBox">
 					<div class="category">
 						<div class="dalei" style="margin-bottom:15px;">
							<p>分类1</p>
						</div>
						
 						<div class="form-group  has-feedback hidee" style="display: block;">
							<font class="control-label col-sm-2" style="line-height: 10px;">选择会员等级：</font>
							<div class="col-sm-9">
								<select class="aos drawshow userGraderSel" style="border-radius: 4px;" onchange="interestExpenseSave.low_UserGradeSelectChange(this)">
									<option value="-1">--选择等级--</option>
									<option value="10000">全部会员等级</option>
 									<c:if test="${not empty allUserGrades }">
											<c:forEach items="${allUserGrades}" var="userGrade">
  												<option value="${userGrade.ugrade}">${userGrade.ugradename }</option>
											</c:forEach>
									</c:if>
  								</select>
  								<span class="delete deletee cude cifen"> <a href="javascript:void(0);" onclick="interestExpenseSave.low_delete(this)" style="color:red;">删除此分类</a></span>
   								 &nbsp; &nbsp;<span id="sb" style="color: red;"></span>
							</div>
						</div>

						<div class="form-group  has-feedback drawgroup">
  							<div class="sbt_draw" style="display: inline;">
								<div style="overflow: hidden;" class="control-label col-sm-12 control_show">
									<p style="float: left;" class="col-sm-2">会员等级展示：</p>
 								</div>
 							</div>
						</div>
 
						<div id="system_business" class="form-group  has-feedback"
							style="display: block;">
							<font class="control-label col-sm-2" style="line-height: 10px;">选择标类型：</font>
							<div class="col-sm-9">
								<select class="aos abasb tendRiskSel" style="border-radius: 4px;" onchange="interestExpenseSave.low_TendRiskSelectChange(this)" >
									<option value="-1" selected="selected">-选择标类型</option>
 									<option value="10000">全部标类型</option>
 									<c:if test="${not empty allLoanTypeObjectQuotes }">
											<c:forEach items="${allLoanTypeObjectQuotes}" var="loanTypeObjectQuote">
  												<option value="${loanTypeObjectQuote.serialno}">${loanTypeObjectQuote.objectname }</option>
											</c:forEach>
									</c:if>
  								</select> 
 								 &nbsp; &nbsp;<span id="sb" style="color: red;"></span>
							</div>
						</div>

						<div class="form-group  has-feedback drafeed">
  							<div class="sbt_draww" style="display: inline;">
								<div style="overflow: hidden;"class="control-label col-sm-12 control_showw">
									<p style="float: left;" class="col-sm-2">标类型展示：</p>
 								</div>
 							</div>
						</div>

						<div class="form-group  has-feedback">
							<font class="control-label col-sm-2">设置利息管理费率：</font>
							<div class="col-sm-8 classify">
								<p>
									<span class="shuru">
										<input type="text" name="" id="" value="" class="insertRate" style="width:100px;" onkeyup="interestExpenseSave.low_vidKeyUp(this)"/>%
									</span>
									 &nbsp; &nbsp;<span id="bn" style="color: red;"></span>
								<p>
							</div>
						</div>
						<div class="form-group  has-feedback">
							<font class="control-label col-sm-2">设置最高利息管理费：</font>
							<div class="col-sm-8 classify">
								<p>
									<span class="shuru">
										<input type="text" name="" id="" value="" class="maxFee" style="width:100px;" onkeyup="interestExpenseSave.low_vidKeyUp(this)"/>
									</span>元
									 &nbsp; &nbsp;<span id="bn" style="color: red;"></span>
								<p>
							</div>
						</div>
 					</div>
  				</div>
  
				<div class="form-group has-success has-feedback">
					<label class="control-label col-sm-2"></label>
 					<div class="col-sm-3">
						<button type="button" id="save_edit" class="btn btn-default" onclick="interestExpenseSave.low_save(this)" id="low_save">保存</button>
					</div>
 				</div>
			</form>
 		</div>
	</div>
<script type="text/javascript">

$(function(){
 	$("body").on("click",".dalei",function(){
  		if($(this).siblings('.hidee').is(':hidden')){
			$(this).siblings().css("display","block");
		}else{
 			$(this).siblings().css("display","none");
 	    } 
 	});
  });

 
var interestExpenseSave = {
		//保存
		low_save:function(obj){
			var $categoryBox = $("#categoryBox");
			var $category = $categoryBox.children(".category");
			var categoryLength = $category.length;
 			if(categoryLength < 1){
				alert("至少选择一个子分类");
				return ;
			}
			
			var isaudit = $("#low_isaudit").val();
			if(isaudit < 1){
				alert("请选择是否审核");
				return ;
			}
			
			var remark = $("#remark").val();
			if(!isEmpty(remark)){
				if(remark.length > 80){
					alert("备注信息超出字数限制！不允许超过80字");
					return ;
				}
			}
  			var vidObj =  this.low_saveValidation(obj);
 			if(!vidObj.falg){
				alert(vidObj.Msg);
				return ;
			}
 			
 			var list = {};
  			for(var i = 0 ;i < categoryLength ; i++){
  				var ugrade = "";
  				$category.eq(i).find(".control_show button").each(function(index,context){
  	 				var $option = $(this);
   	 				ugrade += $option.val()+",";
  	  			});
  				
  				ugrade = ugrade.substring(0, ugrade.lastIndexOf(","));
  				var ttype = "";
  				$category.eq(i).find(".control_showw button").each(function(index,context){
  	 				var $option = $(this);
   	 				ttype  += $option.val()+",";
  	  			});
  				ttype = ttype.substring(0, ttype.lastIndexOf(","));
  				 
  				 var InterestExpense = new Object();
 				 InterestExpense.gfitype = "1";
 				 InterestExpense.ttype = ttype;
 				 InterestExpense.ugrade = ugrade;
 				 InterestExpense.iepercent = $category.eq(i).find(".insertRate").val();
 				 InterestExpense.maxiefee = $category.eq(i).find(".maxFee").val();
  				 list[i] =  InterestExpense;
  			}
 			
 			$.tzAdminAjax.request({
 				model:"admin/interestExpense",
				method:"save.action",
				callback:function(data){
					 var obj = $.parseJSON(data);
					 alert(obj.Msg);
					 window.location.href= basePath + "/admin/interestExpense/edit.action";
 				}
  			},{"params":JSON.stringify(list),"isaudit":isaudit,"remark":remark});
		},
		//验证参数
		low_saveValidation:function(obj){
			var $categoryBox = $("#categoryBox");
			var $category = $categoryBox.children(".category");
			var categoryLength = $category.length;
			var Msg = "";
			var falg = true;
			for(var i = 0 ; i < categoryLength ; i++){
				var buttonLength = $category.eq(i).find(".control_show button").length;//会员等级
				if(buttonLength < 1){
					Msg = "分类"+(i+1)+"未选择会员等级！请选择会员等级";
					falg = false;
					break;
				}
				
				var buttonLength2 = $category.eq(i).find(".control_showw button").length;//标的类型
				if(buttonLength2 < 1){
					Msg = "分类"+(i+1)+"未选择标的类型！请选择标的类型";
					falg = false;
					break;
				}
				
				var insertRateValue = $category.eq(i).find(".insertRate").val();
 				if(isEmpty(insertRateValue) || insertRateValue < -1){
					Msg = "分类"+(i+1)+"未填写利息管理费率！请填写利息管理费率";
					falg = false;
					break;
				}
 				
 				if(insertRateValue == 0){
					Msg = "分类"+(i+1)+"未填写利息管理费率！请填写利息管理费率!必须大于0";
					falg = false;
					break;
				}
 				
 				var reg = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/; ;
				if(!reg.test(insertRateValue)){
					Msg = "分类"+(i+1)+"填写利息管理费率格式错误！请填写正确的利息管理费率！精确到小数点后两位";
					falg = false;
					break;
				}
				
				if(insertRateValue > 100){
					Msg = "分类"+(i+1)+"填写利息管理费率格式错误！请填写正确的利息管理费率！利息管理费率不能超过100%";
					falg = false;
					break;
				}
 				
				var maxFeeValue = $category.eq(i).find(".maxFee").val();
				if(isEmpty(maxFeeValue) || maxFeeValue < -1){
					Msg = "分类"+(i+1)+"未填写最高利息管理费！请填写最高利息管理费";
					falg = false;
					break;
				}
				
				if(maxFeeValue == 0){
					Msg = "分类"+(i+1)+"最高利息管理费必须大于0";
					falg = false;
					break;
				}
				
				var reg = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/; ;
				if(!reg.test(maxFeeValue)){
					Msg = "分类"+(i+1)+"填写最高利息管理费格式错误！请填写正确的最高利息管理费！精确到小数点后两位";
					falg = false;
					break;
				}
 			}
			return {"Msg":Msg,"falg":falg};
		},
		//添加会员等级分类
		low_saveUserGradeCategory:function(obj){
  			var Html =  "<div class='category'>"+
" 						<div class='dalei' style='margin-bottom:15px;'>"+
"							<p>分类1</p>"+
"						</div>"+
" 						<div class='form-group  has-feedback hidee' style='display: block;'>"+
"							<font class='control-label col-sm-2' style='line-height: 10px;'>选择会员等级：</font>"+
"							<div class='col-sm-9'>"+
"								<select class='aos drawshow userGraderSel' style='border-radius: 4px;' onchange='interestExpenseSave.low_UserGradeSelectChange(this)'>"+
"									<option value='-1'>--选择等级--</option>"+
"									<option value='10000'>全部会员等级</option>"+
" 									<c:if test='${not empty allUserGrades }'>"+
"											<c:forEach items='${allUserGrades}' var='userGrade'>"+
"  												<option value='${userGrade.ugrade}'>${userGrade.ugradename }</option>"+
"											</c:forEach>"+
"									</c:if>"+
"  								</select>"+
"							<span class='delete deletee cude cifen'> <a href='javascript:void(0);' onclick='interestExpenseSave.low_delete(this)' style='color:red;'>删除此分类</a></span>"+
"  								 &nbsp; &nbsp;<span id='sb' style='color: red;'></span>"+
"							</div>"+
"						</div>"+
"						<div class='form-group  has-feedback drawgroup'>"+
"  							<div class='sbt_draw' style='display: inline;'>"+
"								<div style='overflow: hidden;' class='control-label col-sm-12 control_show'>"+
"									<p style='float: left;' class='col-sm-2'>会员等级展示：</p>"+
 "								</div>"+
" 							</div>"+
"						</div>"+
"						<div id='system_business' class='form-group  has-feedback'"+
"							style='display: block;'>"+
"							<font class='control-label col-sm-2' style='line-height: 10px;'>选择标类型：</font>"+
"							<div class='col-sm-9'>"+
"								<select class='aos abasb tendRiskSel' style='border-radius: 4px;' onchange='interestExpenseSave.low_TendRiskSelectChange(this)'>"+
"									<option value='-1' selected='selected'>-选择标类型</option>"+
" 									<option value='10000'>全部标类型</option>"+
" 									<c:if test='${not empty allLoanTypeObjectQuotes }'>"+
"											<c:forEach items='${allLoanTypeObjectQuotes}' var='loanTypeObjectQuote'>"+
"  												<option value='${loanTypeObjectQuote.serialno}'>${loanTypeObjectQuote.objectname }</option>"+
"											</c:forEach>"+
"									</c:if>"+
"  								</select> "+
" 								 &nbsp; &nbsp;<span id='sb' style='color: red;'></span>"+
"							</div>"+
"						</div>"+
"						<div class='form-group  has-feedback drafeed'>"+
" 							<div class='sbt_draww' style='display: inline;'>"+
"								<div style='overflow: hidden;'class='control-label col-sm-12 control_showw'>"+
"									<p style='float: left;' class='col-sm-2'>标类型展示：</p>"+
" 								</div>"+
" 							</div>"+
"						</div>"+
"						<div class='form-group  has-feedback'>"+
"							<font class='control-label col-sm-2'>设置利息管理费率：</font>"+
"							<div class='col-sm-8 classify'>"+
"								<p>"+
"									<span class='shuru'>"+
"										<input type='text' name='' id='' value='' class='insertRate' style='width:100px;' onkeyup='interestExpenseSave.low_vidKeyUp(this)'/>%"+
"									</span>"+
"									 &nbsp; &nbsp;<span id='bn' style='color: red;'></span>"+
"								<p>"+
"							</div>"+
"						</div>"+
"						<div class='form-group  has-feedback'>"+
"							<font class='control-label col-sm-2'>设置最高利息管理费：</font>"+
"							<div class='col-sm-8 classify'>"+
"								<p>"+
"									<span class='shuru'>"+
"										<input type='text' name='' id='' value='' class='maxFee' style='width:100px;' onkeyup='interestExpenseSave.low_vidKeyUp(this)'/>"+
"									</span>元"+
"									 &nbsp; &nbsp;<span id='bn' style='color: red;'></span>"+
"								<p>"+
"							</div>"+
"						</div>"+
" 					</div>"
			$("#categoryBox").append(Html);
			var ii=0;	
		    $(".dalei").each(function(){
				ii++;
				var html =  "<p>"+"分类"+ii+" </p>"; 
				$(".dalei").eq(ii-1).html(html);
		    });
		},
		//删除分类
		low_delete:function(obj){
    		$(obj).parents(".category").remove();
 			var ii=0;	
		    $(".dalei").each(function(){
				ii++;
				var html =  "<p>"+"分类"+ii+" </p>"; 
				$(".dalei").eq(ii-1).html(html);
		    });
		},
		//会员等级选择
		low_UserGradeSelectChange:function(obj){
 			var userGraValue = $(obj).val(); 
			if(userGraValue < 0){
				alert("请选择会员等级！");
				return ;
 			}
			var categoryIndex = $(obj).parents(".category").index();
			if(userGraValue == "10000"){//选择全部会员
				var allFalg = this.low_checkAllUserGradeValue(categoryIndex, userGraValue);
 				if(!allFalg.falg){
					var $btnObj = $(obj).parents(".category").find(".control_showw button");
					var RiskFindIndex = -1;
					var RiskFalg = true;
					var TenRiskName = "";
					if($btnObj.length > 0){
						var $this = this;
 						$btnObj.each(function(index,context){
	  		 				var $option = $(this);
	  		 				var btnVal = $option.val();
							var TendRiskObj =  $this.low_checkAllTendRiskValue(categoryIndex, btnVal);
  	   		 				if(!TendRiskObj.falg){
	   		 					RiskFalg = false;
	   		 				    RiskFindIndex = TendRiskObj.findIndex;
	   		 				    TenRiskName  = TendRiskObj.TendRiskName;
	   		 				    return true;
	   		 				} 
	  		  			});
					}
 					if(!RiskFalg){
						alert("分类"+(RiskFindIndex+1)+"已设置相同的标类型:"+TenRiskName);
						return ;
					}
				}
 				var html = "<p style='float: left;' class='col-sm-2'>会员等级展示：</p>"+
				"<button type='button' class='btn btn-default btn-none' value='10000' style='margin-right: 15px; float: left'>"+
				"全部会员等级</button>";
				var $back = $(obj).parents(".has-feedback");
		 		var $Next  = $back.next(".has-feedback");
	 			$Next.find(".control_show").html(html);
			}else{
   				var falg = this.low_checkUserGradeValue(obj, userGraValue);
				if(falg){
					var allFalg = this.low_checkAllUserGradeValue(categoryIndex, userGraValue);//验证其他是否选择相同的会员等级
 					if(!allFalg.falg){
						var $btnObj = $(obj).parents(".category").find(".control_showw button");
						var RiskFindIndex = -1;
						var RiskFalg = true;
						var TenRiskName = "";
						if($btnObj.length > 0){
							var $this = this;
	 						$btnObj.each(function(index,context){
		  		 				var $option = $(this);
		  		 				var btnVal = $option.val();
								var TendRiskObj =  $this.low_checkAllTendRiskValue(categoryIndex, btnVal);
 	 	   		 				if(!TendRiskObj.falg){
		   		 					RiskFalg = false;
		   		 				    RiskFindIndex = TendRiskObj.findIndex;
		   		 				    TenRiskName  = TendRiskObj.TendRiskName;
		   		 				    return true;
		   		 				} 
		  		  			});
						}
	 					if(!RiskFalg){
							alert("分类"+(RiskFindIndex+1)+"已设置相同的标类型:"+TenRiskName);
							return ;
						}
					}
  	 				var userGraName = $(obj).find("option:selected").text();
		  			var $back = $(obj).parents(".has-feedback");
		 			var btnHtml = this.low_UserGradeObjectShow("UserGra",userGraValue, userGraName);
		 			var $Next  = $back.next(".has-feedback");
		 			$Next.find(".control_show").append(btnHtml);
				}
			}
			
		},
		//标类型选择
		low_TendRiskSelectChange:function(obj){
 			var tendRiskValue = $(obj).val(); 
			if(tendRiskValue < 0){
				alert("请选择标的类型！");
				return ;
 			}
			
 			var categoryIndex = $(obj).parents(".category").index();
  			if(tendRiskValue == "10000"){
  				var allFalg = this.low_checkAllTendRiskValue(categoryIndex, tendRiskValue);
 				if(!allFalg.falg){//其他分类已设置全部会员等级
					var $btnObj = $(obj).parents(".category").find(".control_show button");
					var UserGradeNameIndex = -1;
					var UserGradeNameFalg = true;
					var UserGradeName = "";
					if($btnObj.length > 0){
						var $this = this;
 						$btnObj.each(function(index,context){
	  		 				var $option = $(this);
	  		 				var btnVal = $option.val();
							var UserGradeObj =  $this.low_checkAllUserGradeValue(categoryIndex, btnVal);
  	   		 				if(!UserGradeObj.falg){
  	   		 					UserGradeNameFalg = false;
	   		 					UserGradeNameIndex = UserGradeObj.findIndex;
	   		 					UserGradeName  = UserGradeObj.UserGradeName;
	   		 				    return true;
	   		 				} 
	  		  			});
					}
 					if(!UserGradeNameFalg){
						alert("分类"+(UserGradeNameIndex+1)+"已设置相同的会员等级:"+UserGradeName);
						return ;
					}
				}
				var html = "<p style='float: left;' class='col-sm-2'>标类型展示：</p>"+
				"<button type='button' class='btn btn-default btn-none' value='10000' style='margin-right: 15px; float: left'>"+
				"全部标类型</button>";
				var $back = $(obj).parents(".has-feedback");
		 		var $Next  = $back.next(".has-feedback");
	 			$Next.find(".control_showw").html(html);
 			}else{
 				var falg = this.low_checkTendTypeValue(obj, tendRiskValue);
 				if(falg){
 					var allFalg = this.low_checkAllTendRiskValue(categoryIndex, tendRiskValue);
 	 				if(!allFalg.falg){
 						var $btnObj = $(obj).parents(".category").find(".control_show button");
 						var UserGradeNameIndex = -1;
 						var UserGradeNameFalg = true;
 						var UserGradeName = "";
 						if($btnObj.length > 0){
 							var $this = this;
 	 						$btnObj.each(function(index,context){
 		  		 				var $option = $(this);
 		  		 				var btnVal = $option.val();
 								var UserGradeObj =  $this.low_checkAllUserGradeValue(categoryIndex, btnVal);
 	  	   		 				if(!UserGradeObj.falg){
 	  	   		 					UserGradeNameFalg = false;
 		   		 					UserGradeNameIndex = UserGradeObj.findIndex;
 		   		 					UserGradeName  = UserGradeObj.UserGradeName;
 		   		 				    return true;
 		   		 				} 
 		  		  			});
 						}
 	 					if(!UserGradeNameFalg){
 							alert("分类"+(UserGradeNameIndex+1)+"已设置相同的会员等级:"+UserGradeName);
 							return ;
 						}
 					}
  	  				var tendRiskName = $(obj).find("option:selected").text();
		  			var $back = $(obj).parents(".has-feedback");
		 			var btnHtml = this.low_UserGradeObjectShow("TendRisk",tendRiskValue, tendRiskName);
		 			var $Next  = $back.next(".has-feedback");
		 			$Next.find(".control_showw").append(btnHtml);
 				}
			}
		},
		//检查是否已经选择了会员等级
		low_checkUserGradeValue:function(obj,value){
			var $this = this;
			var falg = true;
 			var $back = $(obj).parents(".has-feedback");
  			var $Next  = $back.next(".has-feedback");
 			$Next.find(".control_show button").each(function(index,context){
 				var $option = $(this);
 				var btnVal = $option.val();
 				if(btnVal == "10000"){
 					$option.remove();
 				}
 				
 				if(value == btnVal){
 					falg = false;
 					return true;
  				}
  			});
   			return falg;
		},
		//检查是否已经选择了标类型
		low_checkTendTypeValue:function(obj,value){
			var $this = this;
			var falg = true;
			var $back = $(obj).parents(".has-feedback");
  			var $Next  = $back.next(".has-feedback");
 			$Next.find(".control_showw button").each(function(index,context){
 				var $option = $(this);
 				var btnVal = $option.val();
 				if(btnVal == "10000"){
 					$option.remove();
 				}
 				
 				if(value == btnVal){
 					falg = false;
 					return true;
  				}
  			});
    		return falg;
		},
		//判断是否重复选择标的类型
		low_checkAllTendRiskValue:function(categoryIndex,TendRiskValue){
			var falg = true;
			var $categoryBox = $("#categoryBox");
			var $category = $categoryBox.children(".category");
			var categoryLength = $category.length;
			var $this = this;
			var findIndex = -1 ;
			var TendRiskName = "";
 			for(var i = 0 ; i < categoryLength ; i++){
   				if(i == categoryIndex){
 					continue;
 				}
  				
  				if(TendRiskValue == "10000"){//选择了全部标类型
	 				var buttonLength = $category.eq(i).find(".control_showw button").length;
   					if(buttonLength > 0){
  						falg = false;
  						findIndex = i;
  						break;
  					}
  				}else{
  					$category.eq(i).find(".control_showw button").each(function(index,context){
  		 				var $option = $(this);
  		 				var btnVal = $option.val();
  		 				if(btnVal == "10000"){//全部标类型
  		 					falg = false;
  		 					findIndex = i;
  		 					TendRiskName = $option.text();
  		 					return true
  		 				}
  		 				
   		 				if(TendRiskValue == btnVal){//部分标类型
  		 					falg = false;
  		 					findIndex = i;
  		 					TendRiskName = $option.text();
  		 					return true;
  		  				}
  		  			});
   					if(!falg){
  						break;
  					}
  				}
    		}
  			return {"falg":falg,"findIndex":findIndex,"TendRiskName":TendRiskName};
		},
		//判断是否重复选择会员等级
		low_checkAllUserGradeValue:function(categoryIndex,UserGradeValue){
			var falg = true;
			var $categoryBox = $("#categoryBox");
			var $category = $categoryBox.children(".category");
			var categoryLength = $category.length;
			var $this = this;
			var findIndex = -1 ;
			var UserGradeName = "";
 			for(var i = 0 ; i < categoryLength ; i++){
   				if(i == categoryIndex){
 					continue;
 				}
  				
  				if(UserGradeValue == "10000"){//选择了全部会员
	 				var buttonLength = $category.eq(i).find(".control_show button").length;
   					if(buttonLength > 0){
  						falg = false;
  						findIndex = i;
  						UserGradeName =  $category.eq(i).find(".control_show button").text();
  						break;
  					}
  				}else{
  					$category.eq(i).find(".control_show button").each(function(index,context){
  		 				var $option = $(this);
  		 				var btnVal = $option.val();
  		 				if(btnVal == "10000"){
  		 					falg = false;
  		 					findIndex = i;
  		 					UserGradeName = $option.text();
  		 					return true;
  		 				}
  		 				
   		 				if(UserGradeValue == btnVal){
  		 					falg = false;
  		 					findIndex = i;
  		 					UserGradeName = $option.text();
  		 					return true;
  		  				}
  		  			});
  					
   					if(!falg){
  						break;
  					}
  				}
    		}
  			return {"falg":falg,"findIndex":findIndex,"UserGradeName":UserGradeName};
		},
		//删除标类型/会员等级对象展示
		low_UserGradeDelet:function(obj){
 			$(obj).remove();
		},
		//展示btn拼接
		low_UserGradeObjectShow:function(UserGra,vaule,name){
			if(UserGra == "UserGra"){
 				var html = "<button type='button' class='btn btn-default btn-none' value='"+vaule+"' style='margin-right: 15px; float: left' onclick='interestExpenseSave.low_UserGradeDelet(this)'>"+
				""+name+""+
				"<span style='color: red' class='glyphicon glyphicon-remove'></span>"+
				"</button>";
				return html;
			}else{
				var html = "<button type='button' class='btn btn-default btn-nonee' value='"+vaule+"' style='margin-right: 15px; float: left' onclick='interestExpenseSave.low_UserGradeDelet(this)'>"+
				""+name+""+
				"<span style='color: red' class='glyphicon glyphicon-removee'></span>"+
				"</button>";
				return html;
			}
		},
		low_vidKeyUp:function(obj){
			 if(obj.value !=''&& obj.value.substr(0,1) == '.'){  
		            obj.value="";  
		        }  
		          
		        obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
		        obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的       
		        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");      
		        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数       
		        if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额  
		            if(obj.value.substr(0,1) == '0' && obj.value.length == 2){  
		                obj.value= obj.value.substr(1,obj.value.length);      
		            }  
		        }
		}
}


</script>
</body>
</html>
