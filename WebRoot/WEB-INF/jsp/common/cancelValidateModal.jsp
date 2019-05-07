<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>屏蔽安全验证模态框</title>
</head>
<body>
<!-- 查看详情模态框 -->
<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
	aria-labelledby="detailsModalLabel">
	<div id="detail-modal" class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="detailsModalLabel">
					<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行查看详情操作
				</h4>
			</div>
			<div id="detail-modal-body" class="modal-body"></div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<!-- 删除  模态框 -->
<div class="modal fade" id="delModal" tabindex="-1" role="dialog"
	aria-labelledby="delModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="delModalLabel">
					<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行删除操作
				</h4>
			</div>
			<div id="del-modal-body" class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" onclick="delByID()">确定</button>

				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 用户添加名单模态框  -->
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
	aria-labelledby="addModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="addModalLabel">
					<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行用户添加操作
				</h4>
			</div>
			<div id="addUser-modal-body" class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" id="btn-insert" class="btn btn-primary"
					onclick="insertAddUser()">保存</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>


<!-- 编辑 模态框 -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
	aria-labelledby="editModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="editModalLabel">
					<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行编辑操作
				</h4>
			</div>
			<div id="update-modal-body" class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" id="btn-update" class="btn btn-primary"
					onclick="update()">保存</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>