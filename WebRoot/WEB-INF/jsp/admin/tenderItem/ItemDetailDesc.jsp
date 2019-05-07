<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script>
		function goBackList(){
		window.location.href="${pageContext.request.contextPath}/admin/tenderItem/selectTenderItemByCondition.action";
	}
	</script>
</head>
<body>
	 <form class="form-horizontal" role="form" id="contentInfoForm" action="${pageContext.request.contextPath}/admin/tenderItem/additemdetaildesc.action" method="post">
			<div class="container" id="InputsWrapper">
				<!--分段投资最低与最高金额-->
				<div class="form-group">
					<label    class="col-sm-3 control-label">项目介绍</label>
					<input type="hidden" name="moduletypes[0].moduletype" value="项目介绍"/>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">项目名称:</label>
					<input type="hidden" name="categorynames[0].categoryname" value="项目名称"/>
					<div class="col-sm-3">
						 <textarea name="categorynames[0].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">项目介绍:</label>
					<input type="hidden" name="categorynames[1].categoryname" value="项目介绍"/>
					<div class="col-sm-3">
						 <textarea name="categorynames[1].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">项目摘要:</label>
					<input type="hidden" name="categorynames[2].categoryname" value="项目摘要"/>
					<div class="col-sm-3">
						 <textarea name="categorynames[2].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				 <div class="form-group">
					<label class="col-sm-3 control-label">保障类型:</label>
					<input type="hidden" name="categorynames[3].categoryname" value="保障类型"/>
					<div class="col-sm-3">
						<input type="text" name="categorynames[3].categorydetail" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">项目期限:</label>
					<input type="hidden" name="categorynames[4].categoryname" value="项目期限"/>
					<div class="col-sm-3">
						<input type="text" name="categorynames[4].categorydetail" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">项目规模:</label>
					<input type="hidden" name="categorynames[5].categoryname" value="项目规模"/>
					<div class="col-sm-3">
						<input type="text" name="categorynames[5].categorydetail" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">投资条件:</label>
					<input type="hidden" name="categorynames[6].categoryname" value="投资条件"/>
					<div class="col-sm-3">
						 <textarea name="categorynames[6].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">计息方式:</label>
					<input type="hidden" name="categorynames[7].categoryname" value="计息方式"/>
					<div class="col-sm-3">
						<input type="text" name="categorynames[7].categorydetail" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">还款方式:</label>
					<input type="hidden" name="categorynames[8].categoryname" value="还款方式"/>
					<div class="col-sm-3">
						<input type="text" name="categorynames[8].categorydetail" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">债权转让:</label>
					<input type="hidden" name="categorynames[9].categoryname" value="债权转让"/>
					<div class="col-sm-3">
						 <textarea name="categorynames[9].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">相关费用:</label>
					<input type="hidden" name="categorynames[10].categoryname" value="相关费用"/>
					<div class="col-sm-3">
						 <textarea name="categorynames[10].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<!--我是分割线-->
				<div class="form-group">
					<label class="col-sm-3 control-label">项目详情</label>
					<input type="hidden" name="moduletypes[1].moduletype" value="项目详情"/>
				</div>
				<input type="hidden" name="categorynameDetail[0].categoryname" value="基础描述"/>
				<input type="hidden" name="itemDetailDescs[0].categoryname" value="企业信息"/>
				
				<input type="hidden" name="infotypes[0].infotype" value="基础描述"/>
				<input type="hidden" name="infotypes[1].infotype" value="企业信息"/>
				<div class="form-group">
				<label class="col-sm-3 control-label">资金用途:</label>
				<input type="hidden" name="categorynameDetail[1].categoryname" value="资金用途"/>
				<div class="col-sm-3">
					 <textarea name="categorynameDetail[1].categorydetail" rows="2" class="form-control"></textarea>
				</div>
			    </div>
				<div class="form-group">
					<label class="col-sm-3 control-label">还款来源:</label>
					<input type="hidden" name="categorynameDetail[2].categoryname" value="还款来源"/>
					<div class="col-sm-3">
						 <textarea name="categorynameDetail[2].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">企业背景:</label>
					<input type="hidden" name="itemDetailDescs[1].categoryname" value="企业背景"/>
					<div class="col-sm-3">
						 <textarea name="itemDetailDescs[1].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">经营状况:</label>
					<input type="hidden" name="itemDetailDescs[2].categoryname" value="经营状况"/>
					<div class="col-sm-3">
						 <textarea name="itemDetailDescs[2].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">涉诉信息:</label>
					<input type="hidden" name="itemDetailDescs[3].categoryname" value="涉诉信息"/>
					<div class="col-sm-3">
						 <textarea name="itemDetailDescs[3].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">营业范围:</label>
					<input type="hidden" name="itemDetailDescs[4].categoryname" value="营业范围"/>
					<div class="col-sm-3">
						 <textarea name="itemDetailDescs[4].categorydetail" rows="2" class="form-control"></textarea>
					</div>
				</div>
				<div class="form-group">
					 <label class="col-sm-3 control-label">是否为模板</label>
					 <div class="col-sm-3">
					 	<select name="istemplate" class="form-control">
					 		<option value="">请选择</option>
					 		<option value="1">是</option>
					 		<option value="0">否</option>
					 	</select>
					 </div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">备注</label>
					<div class="col-sm-3">
						<textarea name="remark" rows="2" class="form-control"></textarea>
					</div>
				</div>
				  <div class="form-group">
					<div class="col-sm-offset-3 col-sm-1">
						<button type="submit" class="btn btn-primary" id="baocun">保存</button>
					</div>
					<div class="col-sm-1">
						<button type="button" class="btn btn-default"   onclick="goBackList();">返回列表</button>
					</div>
				</div>
			</div>
		</form>
</body>
</html>