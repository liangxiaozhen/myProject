<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提抵卷活动规则列表</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style>
.text-center td {
	vertical-align: text-top !important;
}

.input-group-addon a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
 $(function(){
		$(".tzui-tips").tzTip();
	});
  	var low = {
 			//跳转设置页面
 			low_save_button:function(){
  				$.tzAjax.request({
 					model:"admin/withdrawsCashCoupon",
 					method:"insert.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				});
 			},
 			//查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				$.tzIframe({width:"400",cancelText:"返回列表",height:"560","title":"提低卷活动详情",
 					content:basePath+"/admin/withdrawsCashCoupon/detail.action?opid="+opid,
 					callback:function(iframe,$dialog,opts){
 						if(iframe){
 							$dialog.next().remove();
 		  					$dialog.remove();
 						}
 					}		 
 				});
 			},
 			//跳转修改提低卷活动规则设置页面
 			low_edit:function(obj){
  				var opid=$(obj).data("opid");
 				$.tzAjax.request({
 					model:"admin/withdrawsCashCoupon",
 					method:"edit.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				},{"opid":opid});
 			},
 			//审核操作
 			low_reviewed:function(obj){
 				var actno = $(obj).data("actno");
 				var opid = $(obj).data("opid");
 				$.tzAlert({"title":"审核提示","content":"您确定通过活动编号为<span class='red'>"+actno+"</span>的审核吗",callback:function(ok){
 					if(ok){
 						$.tzAjax.request({
 							model:"admin/withdrawsCashCoupon",
 							method:"reviewed.action",
 							callback:function(data){
  								var obj = $.parseJSON(data);
 								if(obj.result =="fail"){
 									loading("审核失败,请重新操作",4);
 								}else if(obj.result=="success"){
 									alert("审核成功");
 									window.location.href=basePath+"/admin/withdrawsCashCoupon/list.action";
 								}
 							}
 						},{"id":opid});
 					}
 				}});
 			},
 			//启用停用活动设置操作
 			low_status:function(obj){
 				var actno = $(obj).data("actno");
 				var opid = $(obj).data("opid");
 				var status = $(obj).data("status");
 				var mark = (status ==1 ? 0:1); 
 				var parasm = (status ==1?'启用':'停用');
 				$.tzAlert({"title":""+parasm+"提示","content":"您确定通过活动编号为<span class='red'>"+actno+"</span>的"+parasm+"设置吗",callback:function(ok){
 					if(ok){
 						$.tzAjax.request({
 							model:"admin/withdrawsCashCoupon",
 							method:"status.action",
 							callback:function(data){
  								var obj = $.parseJSON(data);
 								if(obj.result =="fail"){
 									loading("审核失败,请重新操作",4);
 								}else if(obj.result=="success"){
 									alert("设置成功");
 									window.location.href=basePath+"/admin/withdrawsCashCoupon/list.action";
 								}
 							}
 						},{"id":opid,"status":mark});
 					}
 				}});
  			},
  			low_findAll:function(){
  				$("#search_username").val("");
  				$("#box").load(basePath+"/admin/withdrawsCashCoupon/list.action");
   			}
  	};
     //分页查询通用方法
 	function queryAllPerson(pageNo,pageSize){
  		$("#queryall_list").html("数据正在加载中.....");
  		var username = $("#search_username").val();
 		var pageSize = "20";
  		$.ajax({
			type:"post",
			data:{"pageSize":pageSize,"pageNo":pageNo,"username":username},
			url:basePath+"/admin/withdrawsCashCoupon/template.action",
			success:function(data){
				$("#queryall_list").html(data);
				$(".tzui-tips").tzTip();
				//关键词高亮
				gjKeywordHi(username);
			}
		});
  	}
   //模糊搜索
 	function search(obj){
 		var username = $("#search_username").val();
  		if(isEmpty(username)){
 			alert("请输入活动编号搜索....");
 			$("#search_username").select();
 			return false;
 		}
 		  $.ajax({
 			  type:"post",
 			  url:basePath+"/admin/withdrawsCashCoupon/template.action",
 			  data:{"actno":username},
 			  success:function(data){
    			  $("#queryall_list").html(data);
    			  $(".tzui-tips").tzTip();
    			  gjKeywordHi(username);
   			  }
 		  });
 	}
 	
 	//代码高亮
 	function gjKeywordHi(keyword){
 		$("#queryall_list").find(".gj_keyword").each(function(){
			  var text = $(this).text();
 			  var regex = new RegExp(keyword,"ig");
			  var rtext = text.replace(regex,"<span class='red'>"+keyword+"</span>");
			  $(this).html(rtext);
		  });
 	}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12" style="margin-top: 15px;">
				<div id="box">
					<div class="col-md-5 input-group">
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-search"></span></span> <input type="text"
							name="search_username" id="search_username" class="form-control"
							placeholder="请输入活动编号搜索..."> <span
							class="input-group-addon"><a
							href="javascript:search(this)">搜索</a></span>
						<div class="col-md-2 col-md-offset-3">
							<button class="btn btn-default" onclick="low.low_findAll(this)">查询全部</button>
						</div>
					</div>
					<div class="col-md-12 text-right">
						<button class="btn btn-default"
							onclick="low.low_save_button(this)">设置</button>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/withdrawsCashCoupon/listTemplate.jsp"></jsp:include>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>