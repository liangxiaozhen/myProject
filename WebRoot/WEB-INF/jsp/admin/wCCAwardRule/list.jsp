<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提抵卷活动奖励规则设置列表</title>
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
  	var wCCAwardRule = {
  			//查看详情
 			low_detail:function(obj){
 				var opid=$(obj).data("opid");
 				var actid=$(obj).data("actid");
//  				$.tzAjax.request({
//  					model:"admin/wCCAwardRule",
//  					method:"detail.action",
//  					callback:function(data){
//   						$("#box").html(data);
//  					}
//  				},{"opid":opid,"actid":actid});
 				
//  			var opid=$(obj).data("opid");
 				$.tzIframe({width:"800",cancelText:"返回列表",height:"560","title":"提低卷活动奖励设置详情信息",
 					content:basePath+"/admin/wCCAwardRule/detail.action?opid="+opid+"&actid="+actid+"",
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
 					model:"admin/wCCAwardRule",
 					method:"edit.action",
 					callback:function(data){
   						$("#box").html(data);
  					}
 				},{"opid":opid});
 			},
 			//查找全部提低卷活動信息
    		low_findAll:function(){
  				$("#search_username").val("");
  				$("#box").load(basePath+"/admin/wCCAwardRule/list.action");
   			},
   			low_callback:function(obj){
					window.location.href=basePath+"/admin/wCCAwardRule/list.action";
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
			url:basePath+"/admin/wCCAwardRule/template.action",
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
 			  url:basePath+"/admin/wCCAwardRule/template.action",
 			  data:{"actid":username},
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
							placeholder="请输入注册活动ID编号搜索..."> <span
							class="input-group-addon"><a
							href="javascript:search(this)">搜索</a></span>
						<div class="col-md-2 col-md-offset-3">
							<button class="btn btn-default"
								onclick="wCCAwardRule.low_findAll(this)">查询全部</button>
						</div>
					</div>
					<div id="queryall_list" style="margin-top: 40px;">
						<jsp:include
							page="/WEB-INF/jsp/admin/wCCAwardRule/listTemplate.jsp"></jsp:include>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>