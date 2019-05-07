<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
<title>加息券</title>
<style type="text/css">
	/* #liOne{
		margin-left: 35%
	}
	.pagination li{
		width: auto;
		float: left;
		margin-left: 10px
		
		
	}
	#pageCon{
		width: 100%;
	} */
	
	.pagination {
	display: inline-block;
	padding-left: 0;
	margin: 20px 0;
	border-radius: 4px;
	}
	
	.pagination>li {
		display: inline;
	}
	
	.pagination>li>a, .pagination>li>span {
		position: relative;
		float: left;
		padding: 6px 12px;
		margin-left: -1px;
		line-height: 1.42857143;
		color: #337ab7;
		text-decoration: none;
		background-color: #fff;
		border: 1px solid #ddd;
	}
	
	.pagination>li:first-child>a, .pagination>li:first-child>span {
		margin-left: 0;
		border-top-left-radius: 4px;
		border-bottom-left-radius: 4px;
	}
	
	.pagination>li:last-child>a, .pagination>li:last-child>span {
		border-top-right-radius: 4px;
		border-bottom-right-radius: 4px;
	}
	
	.pagination>li>a:hover, .pagination>li>span:hover, .pagination>li>a:focus,
		.pagination>li>span:focus {
		z-index: 3;
		color: #23527c;
		background-color: #eee;
		border-color: #ddd;
	}
	
	.pagination>.active>a, .pagination>.active>span, .pagination>.active>a:hover,
		.pagination>.active>span:hover, .pagination>.active>a:focus,
		.pagination>.active>span:focus {
		z-index: 2;
		color: #fff;
		cursor: default;
		background-color: #337ab7;
		border-color: #337ab7;
	}
	
	.pagination>.disabled>span, .pagination>.disabled>span:hover,
		.pagination>.disabled>span:focus, .pagination>.disabled>a, .pagination>.disabled>a:hover,
		.pagination>.disabled>a:focus {
		color: #777;
		cursor: not-allowed;
		background-color: #fff;
		border-color: #ddd;
	}
	
	.pagination-lg>li>a, .pagination-lg>li>span {
		padding: 10px 16px;
		font-size: 18px;
		line-height: 1.3333333;
	}
	
	.pagination-lg>li:first-child>a, .pagination-lg>li:first-child>span {
		border-top-left-radius: 6px;
		border-bottom-left-radius: 6px;
	}
	
	.pagination-lg>li:last-child>a, .pagination-lg>li:last-child>span {
		border-top-right-radius: 6px;
		border-bottom-right-radius: 6px;
	}
	
	.pagination-sm>li>a, .pagination-sm>li>span {
		padding: 5px 10px;
		font-size: 12px;
		line-height: 1.5;
	}
	
	.pagination-sm>li:first-child>a, .pagination-sm>li:first-child>span {
		border-top-left-radius: 3px;
		border-bottom-left-radius: 3px;
	}
	
	.pagination-sm>li:last-child>a, .pagination-sm>li:last-child>span {
		border-top-right-radius: 3px;
		border-bottom-right-radius: 3px;
	}
	
	.pager {
		padding-left: 0;
		margin: 20px 0;
		text-align: center;
		list-style: none;
	}
	
	.pager li {
		display: inline;
	}
	
	.pager li>a, .pager li>span {
		display: inline-block;
		padding: 5px 14px;
		background-color: #fff;
		border: 1px solid #ddd;
		border-radius: 15px;
	}
	
	.pager li>a:hover, .pager li>a:focus {
		text-decoration: none;
		background-color: #eee;
	}
	
	.pager .next>a, .pager .next>span {
		float: right;
	}
	
	.pager .previous>a, .pager .previous>span {
		float: left;
	}
	
	.pager .disabled>a, .pager .disabled>a:hover, .pager .disabled>a:focus,
		.pager .disabled>span {
		color: #777;
		cursor: not-allowed;
		background-color: #fff;
	}
	
</style>
<script type="text/javascript">

	function queryAllPerson(pageNum,pageSize){
		
		var isuse = "";
    	//获取激活状态的加息券
    	$($("#statusCoupon a")).each(function(){
    		if($(this).hasClass("active")){
    			var str = $(this).html();
    			if(str=="未到期"){
    				isuse = 1;
    			}else if(str=="可使用"){
    				isuse = 2;
    			}else if(str=="已冻结"){
    				isuse = 3;
    			}else if(str=="已使用"){
    				isuse = 4;
    			}else if(str=="已到期"){
    				isuse = 5;
				}else if(str=="已作废"){
					isuse = 6;
				}
    		}
    	});
    	
    	//遍历排序
		var cSort = "";
		$($("#couponSort a")).each(function(){
			
			if($(this).hasClass("active")){
				var sort = $(this).html();
				if(sort == "按获得时间"){
					cSort = "1";
				}else if(sort == "按过期时间"){
					cSort = "2";
				}else if(sort == "按金额"){
					cSort = "3";
				}
			}
				
		});
    	
    	//加息券
    	$.ajax({
    		url:"${pageContext.request.contextPath}/user/activity/queryCoupon.action",
    		async:false,
    		data:{
    			"isuse":isuse,
    			"cSort":cSort,
    			"pageNum":pageNum,
    			"pageSize":pageSize
    		},
    		success:function(data){
    			$("#raiseCoupon").html(data);
    		}
    	});
		
	}

</script>

</head>
<body>
	<input type="hidden" id="pageNum" name="pageNum" value="${pagehelper.pageNum}" />
	<input type="hidden" id="pageSize" name="pageSize" value="${pagehelper.pageSize}" />
	<ul class="fl mygift_dyq bor_l bor_t clearfix"> 
		<c:forEach items="${pagehelper.list}" var="pl">
			<li class="fl bor_b bor_r">
		  		<dl class="fl clearfix">
		  			<dd class="fl pad_l20 pad_t20">
			  			<div class="icon_integral pos1">
			  				<p class="Numfont fs_32 txt_center fc_gold mar_t20 lin_30">
			  					<fmt:formatNumber type="percent" maxIntegerDigits="2" value="${pl.icrate}" />
			  				</p>
			  				<p class="txt_center fc_samllred fs_16 fb lin_30"></p>
			  			</div>
		  			</dd>
		  			<dt class="fr pad_r20 pad_t20 wid_w298">
		  				<div class="box_height">
			  				<p class="fc_3 fs_16">${pl.award.aname}<span class="fc_9 fs_14 fr">券号：${pl.uircno}</span></p>
			  				<p class="fc_6">${pl.remark}</p>
			  				<p class="fc_3"><span class="fc_9">获得时间： </span>
			  				<span class="Numfont">
			  					<fmt:formatDate value="${pl.icdealtime}" type="both" pattern="yyyyMMdd HH:mm:ss"/>
			  				</span>
			  				</p>
			  				<p class="fc_3"><span class="fc_9">过期时间： </span>
			  					<span class="Numfont">
			  						<fmt:formatDate value="${pl.icfailtime}" type="both" pattern="yyyyMMdd HH:mm:ss"/>
			  					</span>
			  				</p>
		  				</div>
		  				<p class="pad_b20 pad_t10">
		  					<a href="{:U('Xiangmu/financialDetail')}" class="btn btn_bgf60 btn_size100">立即投资</a>
		  				</p>
		  			</dt>
		  		</dl>
		  	</li> 
		</c:forEach>
	</ul>
</body>
<div id="page_div" class="clearfix">
	<%@ include file="../../../common/pagehelper.jsp"%>
</div>
</html>