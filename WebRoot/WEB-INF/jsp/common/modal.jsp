<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	<!-- 提现费率，提现限制 审核模态框 
	<div class="modal fade" id="auditModal" tabindex="-1" role="dialog"
		aria-labelledby="auditModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="auditModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行审核操作
					</h4>
				</div>
				<div id="audit-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="auditSuccess()">审核通过</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>-->
	<!-- 启用  提现费率，提现限制 模态框 
	<div class="modal fade" id="isuseModal" tabindex="-1" role="dialog"
		aria-labelledby="isuseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="isuseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<input type="hidden" id="isuseid"> 您是否启用序号 : <label
							id="isuseidlb"></label> 的设置？
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="isUse()">启用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>-->
	<!-- 停用  提现费率，提现限制 模态框
	<div class="modal fade" id="cancelUseModal" tabindex="-1" role="dialog"
		aria-labelledby="cancelUseModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="cancelUseModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行使用状态设置操作
					</h4>
				</div>
				<div class="modal-body">
					<div style="text-align: center;">
						<input type="hidden" id="canceluseid"> 您是否停用序号 : <label
							id="canceluseidlb"></label> 的记录？
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" onclick="cancelUse()">停用</button>
					<button type="button" class="btn" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div> -->
	<!-- 设置模态框  -->
	<div class="modal fade" id="insertModal" tabindex="-1" role="dialog"
		aria-labelledby="addModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="addModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行设置操作
					</h4>

				</div>
				<div id="insert-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" id="btn-insert" class="btn btn-primary"
						onclick="insert()">确定</button>
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

	<!-- 批量编辑 模态框  -->
	<div class="modal fade" id="batchUpdateModal" tabindex="-1"
		role="dialog" aria-labelledby="editModalLabel">
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
				<div id="batchUpdate-modal-body" class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="batchUpdate()">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
					<button type="button" class="btn btn-danger" onclick="delByID()">删除</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--  批量删除 模态框 
	<div class="modal fade" id="batchDelModal" tabindex="-1" role="dialog"
		aria-labelledby="batchDelModalLabel">
		<div id="detail-modal" class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="batchDelModalLabel">
						<span class="glyphicon glyphicon-info-sign"></span>提示：正在进行批量删除操作
					</h4>
				</div>
				<div id="batchDel-modal-body" class="modal-body"></div>

				<div class="modal-footer">
					<button type="button" onclick="batchDel()" class="btn btn-danger">删除</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
-->
</body>
</html>