<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String rootPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户奖品邮寄地址</title>
<!-- 注意文件的引入顺序 -->
<link href="<%= basePath %>bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=basePath%>jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="<%=basePath%>bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/util.js"></script>
<script src="<%=basePath%>js/validate/jquery.validate.js"></script>
<!-- 省市区/县 -->
<script type="text/javascript" src="<%=basePath%>js/pdata.js"></script>
<script type="text/javascript">
	$(function () {
		var html = "<option value=''>-- 请选择 --</option>"; $("#addressCity").append(html); $("#addressDistrict").append(html);
		$.each(pdata,function(idx,item){
			if (parseInt(item.level) == 0) {
   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
   			}
		});
		$("#addressProvince").append(html);

		$("#addressProvince").change(function(){
			if ($(this).val() == "") return;
			$("#addressCity option").remove(); $("#addressDistrict option").remove();
			var code = $(this).find("option:selected").attr("exid"); code = code.substring(0,2);
			var html = "<option value=''>-- 请选择--</option>"; $("#addressDistrict").append(html);
			$.each(pdata,function(idx,item){
				if (parseInt(item.level) == 1 && code == item.code.substring(0,2)) {
	   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
	   			}
			});
			$("#addressCity").append(html);		
		});

		$("#addressCity").change(function(){
			if ($(this).val() == "") return;
			$("#addressDistrict option").remove();
			var code = $(this).find("option:selected").attr("exid"); code = code.substring(0,4);
			var html = "<option value=''>-- 请选择 --</option>";
			$.each(pdata,function(idx,item){
				if (parseInt(item.level) == 2 && code == item.code.substring(0,4)) {
	   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
	   			}
			});
			$("#addressDistrict").append(html);		
		});
	});
	
	/* 备注显示字符个数限制*/
	jQuery.fn.limit = function() {
		var self = $("[limit]");
		self.each(function() {
			var objString = $(this).text();
			var objLength = $(this).text().length;
			var num = $(this).attr("limit");
			if (objLength > num) {
				objString = $(this).text(objString.substring(0, num) + "...");
			}
		})
	}

	$(function() {
		$("[limit]").limit();
	})

	/* 备注tips */
	$(function() {
		$("[data-toggle='tooltip']").tooltip({
			html : true
		});
	});
	
	/* 查看用户站外奖品详情 */
	function query_useraddress_detail(id) {
		var action = "${pageContext.request.contextPath}/userAddrss/queryuserAddressDetail.action";
		var param = {
			"id" : id
		}
		var callback = function(data) {
			$("#detailModal").modal({
				backdrop : 'static',
				keyboard : false
			});
			$("#modal-body").html(data);
		}
		$.post(action, param, callback);
	}

	//单元格验证
	$().ready(function() {
// 在键盘按下并释放及提交后验证提交表单
  $("#finishUserAddressForm").validate({
	    rules: {
	    	addressStreet:{
	    		maxlength:70,
	    		required: true,
	    	},
	    	zipcode:{
	    		required: false,
	    	},
	        recipients: {
	        required: true,
	        minlength:2,
	        maxlength:25
	      },
	      mobliephone: {
	        required: true,
	        maxlength:12
	      },
	         addressProvince:"required",
			 addressCity:"required",
			 addressDistrict:"required",
	    },
	    messages: {
	    	addressStreet:{ 
	    		required :"请输入详细地址",
	    		maxlength:"长度不超过60个字符",
	    	},
	    	recipients: {
	    		minlength:"姓名最低由两个字母组成",
	    		 required:"请输入收货人姓名",
	    		 maxlength:"长度不超过25个字符",
	      },
	      mobliephone: {
	    	  required:"请输入手机号码",
	    	  maxlength:"手机号码长度不超过11位",
	      },
	      addressProvince:  "请选择省份",
	      addressCity:"请选择城市",
	      addressDistrict:"请选择区域",
	    }
	});
});
  
	function del(){
		  var msg=confirm("你确定删除此记录吗？");
		 if(msg==true){
		   return true;
		 }else{
		  return false;
		 }
		}
	
	/* 分页查询用戶获奖信息 */
/* 	 function queryAllPerson(pageNum, pageSize) {
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#userpostAddrssform").submit()
		} */
</script>
<style>
.trcolor {
	border: 1px solid #CCCCCC;
	text-align: center;
}
</style>
</head>
<body>
	<div class="container" id="finishBaseInfo">
		<div class="col-md-12 column">
			<form class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-4 control-label">新增邮寄地址&nbsp;</label>
				</div>
				<div class="col-sm-3"></div>
				<div class="col-sm-3"></div>
			</form>
		</div>
		<div class="col-md-12 column">
			<form class="form-horizontal" role="form"
				action="<%= basePath %>/userAddrss/AdduserAddress.action"
				id="finishUserAddressForm" method="post">
				<div class="form-group">
					<label for="awardno" class="col-sm-2 control-label">奖品编号:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="awardno"
							id="awardnum" maxlength="20" placeholder="奖品编号">
					</div>
				</div>

				<div class="form-group">
					<label for="zipcode" class="col-sm-2 control-label">邮政编码:</label>
					<div class="col-sm-3">
						<input type="text"
							onkeyup='this.value=this.value.replace(/\D/gi,"")'
							class="form-control" name="zipcode" id="zipcode" maxlength="10"
							placeholder="如 0441300" required>
					</div>
				</div>

				<div class="form-group">
					<label for="recipients" class="col-sm-2 control-label">收货人姓名:<font
						color="red">*</font></label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="recipients"
							id="recipients" maxlength="25" placeholder="长度不超过25个字符">
					</div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-2 control-label">电话号码:</label>
					<div class="col-sm-3">
						<input type="text"
							onkeyup="this.value=this.value.replace(/[^\d-]/g,'');"
							class="form-control" name="telephone" id="telephone"
							maxlength="20" value="" placeholder="电话号码">
					</div>
				</div>
				<div class="form-group">
					<label for="mobliephone" class="col-sm-2 control-label">手机号码:<font
						color="red">*</font></label>
					<div class="col-sm-3">
						<input type="text"
							onkeyup='this.value=this.value.replace(/\D/gi,"")'
							class="form-control" name="mobliephone" id="mobliephone"
							maxlength="11" value="" placeholder="手机号码">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2">所在地区:<font
						color="red">*</font></label>
					<div class="col-sm-2">
						<select name="addressProvince" id="addressProvince"
							class="form-control">
						</select>
					</div>
					<div class="col-sm-2">
						<select name="addressCity" id="addressCity" class="form-control">
						</select>
					</div>
					<div class="col-sm-2">
						<select name="addressDistrict" id="addressDistrict"
							class="form-control">
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="addressStreet" class="col-sm-2 control-label">详细地址:<font
						color="red">*</font></label>
					<div class="col-sm-3">
						<textarea rows="3" cols="80" class="form-control"
							style="width: 110%" name="addressStreet" id="addressStreet"
							placeholder="建议您如实填写详细收货地址,列如街道名称,门牌号码，楼层等信息..." maxlength="60"></textarea>
					</div>
				</div>

				<div class="form-group">
					<label for="remark" class="col-sm-2 control-label">备注:</label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="remark" id="remark"
							maxlength="100" placeholder="随便写点什么...">
					</div>
				</div>
				<div class="form-group">
					<div class="checkbox  col-sm-offset-2 col-sm-10">
						<label> <input type="checkbox" name="Defaultaddress"
							value="默认"> 设置为默认收货地址
						</label>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<input type="submit" class="btn btn-default" value="保存" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 显示收货人的地址 -->
	<div class="container">
		<input type="hidden" id="pageNum" name="pageNum" /> <input
			type="hidden" id="pageSize" name="pageSize" />
		<c:if test="${!empty  pagehelper.list }">
			<table class="table table-hover" style="border: 1px solid silver;">
				<tr style="background-color: rgba(238, 238, 238, 0.98);"
					class="trcolor">
					<td>收货人</td>
					<td>所在地区</td>
					<td>详细地址</td>
					<td>邮编</td>
					<td>电话</td>
					<td>手机</td>
					<td>备注</td>
					<td>操作</td>
					<td></td>
				</tr>
				<c:forEach items="${pagehelper.list}" var="adr">
					<tr class="trcolor">
						<td>${adr.recipients }</td>
						<td>${adr. addressProvince}&nbsp;${adr. addressCity}&nbsp;${adr.addressDistrict}</td>
						<td>${adr.addressStreet}</td>
						<td>${adr.zipcode }</td>
						<td>${adr.telephone}</td>
						<td>${adr. mobliephone}</td>
						<td><p limit="5" data-toggle="tooltip"
								title="<h5>${adr.remark}</h5>">${adr.remark}</p></td>
						<td>
							<button type="button" class="btn btn-default"
								onclick="query_useraddress_detail(' ${adr.id}')">修改</button> <a
							href="${pageContext.request.contextPath}/userAddrss/deleteuserAddress.action?id=${adr.id}"
							class="btn btn-default">删除</a>
						</td>
						<c:choose>
							<c:when test="${adr. isdefaddress==1}">
								<td><c:out value="默认地址"></c:out></td>
							</c:when>
							<c:otherwise>
								<td><c:out value=" "></c:out></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty pagehelper.list}">
			<h3 class="col-md-4 col-md-offset-2">还没有收货地址哦</h3>
		</c:if>

		<!-- 详情模态框（Modal） -->
		<div id="detailModal" class="modal fade bs-example-modal-lg"
			tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">用户奖品邮寄地址</h4>
					</div>
					<div class="modal-body" id="modal-body"></div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<%-- <div id="page_div">
					<%@ include file="../../common/pagehelper.jsp"%>
				</div> --%>
	</div>
</body>
</html>