<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport"
	content="width=device-width,user-scalable=no,initial-scale=1">
<meta http-equiv="description" content="This is my page">
<title>标的活动和标的活动规则修改</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<script type="text/javascript"
	src="${basePath }/js/validate/jquery.validate.js"></script>
<style type="text/css">
.list-item {
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="container" style="margin-top: 5px;">
		<div class="col-md-12">
			<div class="alert alert-success text-center" role="alert"
				style="font-size: 14px;">标的活动规则修改</div>
			<form id="ActivityRule_from">
				<!-- 标的活动规则设置 start -->
				<div id="ActivityRule_box">

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">活动编号</label>
							<div class="col-sm-6">
								<input type="hidden" value="${activityRule.id}" name="id" /> <input
									type="text" value="${activityRule.actno}" class="form-control"
									readonly="readonly" />
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">活动名称</label>
							<div class="col-sm-6">
								<input type="text" value="${activityRule.actname}"
									class="form-control" name="actname" id="actname"
									placeholder="请输入活动名称...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">定向标号</label>
							<div class="col-sm-6">
								<input type="hidden" value="${activityRule.specifytno}"
									id="specifytnoParams">
								<ul id="tenderItems_box">
									<li class="row list-item">
										<div class="col-md-12">
											<select name="specifytnos" id="specifytno"
												class="form-control specifytno">
												<option value="">--请选择定向标号--</option>
												<c:if test="${not empty tenderItems}">
													<c:forEach items="${tenderItems}" var="tenders">
														<option value="${tenders.tno}">${tenders.tno}-定向标名称-${tenders.tname}</option>
													</c:forEach>
												</c:if>
											</select> <a href="javascript:void(0)" class="savespecifytno"
												onclick="ActivityRule.savespecifytno(this)">添加定向标号</a>
										</div>
										<div class="col-md-2"></div>
									</li>
									<c:if test="${not empty specifytnos}">
										<c:forEach begin="1" end="${specifytnos-1}">
											<li class="row list-item">
												<div class="col-md-12">
													<select name="specifytnos" id="specifytno"
														class="form-control specifytno">
														<option value="">--请选择定向标号--</option>
														<c:if test="${not empty tenderItems}">
															<c:forEach items="${tenderItems}" var="tenders">
																<option value="${tenders.tno}">${tenders.tno}-定向标名称-${tenders.tname}</option>
															</c:forEach>
														</c:if>
													</select>
												</div>
												<div class="col-md-2">
													<a href="javascript:void(0)"
														onclick="ActivityRule.deletespecifytno(this);">删除</a>
												</div>
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">排除的标号</label>
							<div class="col-sm-6">
								<input type="hidden" value="${activityRule.canceltno}"
									id="canceltnoParams">
								<ul id="canceltnoItems_box">
									<li class="row list-item">
										<div class="col-md-12">
											<select name="canceltnos" id="canceltno"
												class="form-control specifytno">
												<option value="">--请选择排除的标号--</option>
												<c:if test="${not empty tenderItems}">
													<c:forEach items="${tenderItems}" var="tenders">
														<option value="${tenders.tno}">${tenders.tno}-定向标名称-${tenders.tname}</option>
													</c:forEach>
												</c:if>
											</select> <a href="javascript:void(0)" class="savespecifytno"
												onclick="ActivityRule.savecanceltno(this)">添加排除标号</a>
										</div>
										<div class="col-md-2"></div>
									</li>

									<c:if test="${not empty canceltnos}">
										<c:forEach begin="1" end="${canceltnos-1}">
											<li class="row list-item">
												<div class="col-md-12">
													<select name="canceltnos" id="canceltno"
														class="form-control specifytno">
														<option value="">--请选择排除的标号--</option>
														<c:if test="${not empty tenderItems}">
															<c:forEach items="${tenderItems}" var="tenders">
																<option value="${tenders.tno}">${tenders.tno}-定向标名称-${tenders.tname}</option>
															</c:forEach>
														</c:if>
													</select>
												</div>
												<div class="col-md-2">
													<a href="javascript:void(0)"
														onclick="ActivityRule.deletecanceltno(this);">删除</a>
												</div>
											</li>
										</c:forEach>
									</c:if>
								</ul>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">投标期限限制(天数)</label>
							<div class="col-sm-6">
								<input type="text" value="${activityRule.tdayrestrict}"
									class="form-control" name="tdayrestrict" id="tdayrestrict"
									placeholder="请输入投标期限限制...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">投标收益限制最低值</label>
							<div class="col-sm-6">
								<input type="text" value="${activityRule.tmlrrestrict}"
									class="form-control" name="tmlrrestrict" id="tmlrrestrict"
									placeholder="请输入投标收益限制最低值...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">投标收益限制最高值</label>
							<div class="col-sm-6">
								<input type="text" value="${activityRule.tmhrrestrict}"
									class="form-control" name="tmhrrestrict" id="tmhrrestrict"
									placeholder="请输入投标收益限制最高值...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">备注</label>
							<div class="col-sm-6">
								<textarea class="form-control"
									data-remark="${activityRule.remark}" name="remark" id="remark"
									placeholder="请输入备注信息..."></textarea>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">活动类型</label>
							<div class="col-sm-6">
								<select id="acttype" name="acttype" class="form-control"
									data-acttype="${activityRule.acttype}">
									<option value="">----请选择活动类型----</option>
									<option value="1">累投</option>
									<option value="2">单标</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">活动状态</label>
							<div class="col-sm-6">
								<select id="status" name="status" class="form-control"
									data-status="${activityRule.status}">
									<option value="">----请选择活动状态----</option>
									<option value="1">启动活动</option>
									<option value="2">未启动活动</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">累投计算方式</label>
							<div class="col-sm-6">
								<select id="tctype" name="tctype" class="form-control"
									data-tctype="${activityRule.tctype}">
									<option value="">----请选择累投计算方式----</option>
									<option value="1">标内</option>
									<option value="2">全局</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">获奖名单生成方式</label>
							<div class="col-sm-6">
								<select id="gtype" name="gtype" class="form-control"
									data-gtype="${activityRule.gtype}">
									<option value="">----请选择获奖名单生成方式----</option>
									<option value="1">系统</option>
									<option value="2">手动</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">获奖名单是否需要审核
							</label>
							<div class="col-sm-6">
								<select id="isauditalist" name="isauditalist"
									class="form-control"
									data-isauditalist="${activityRule.isauditalist}">
									<option value="">----请选择获奖名单是否需要审核----</option>
									<option value="1">需要审核</option>
									<option value="2">不需要审核</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">是否审核(该表)</label>
							<div class="col-sm-6">
								<select id="isaudit" name="isaudit" class="form-control"
									data-isaudit="${activityRule.isaudit}">
									<option value="">----请选择获奖名单生成方式----</option>
									<option value="1">需要审核</option>
									<option value="2">不需要审核</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">活动生效日期
							</label>
							<div class="col-sm-6">
								<input type="text" id="actbtime" name="actbtime" class="Wdate"
									style="width: 200px;"
									value="${gj:formatDate(activityRule.actbtime,'yyyy-MM-dd HH:mm:ss')}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:ActivityRule.check_time})" />
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">活动截止日期
							</label>
							<div class="col-sm-6">
								<input type="text" id="actetime" name="actetime" class="Wdate"
									style="width: 200px;"
									value="${gj:formatDate(activityRule.actetime,'yyyy-MM-dd HH:mm:ss')}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:ActivityRule.check_actbtime})" />
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;" id="crestrict_box">
						<div class="col-md-8">
							<input type="hidden" value="${crestrictParams }"
								id="crestrictParams" /> <label for="exampleInputName2"
								class="col-sm-2 text-right">客户端限制</label> <label
								class="checkbox-inline"><input type="checkbox"
								class="crestricts" name="crestricts" value="1">pc </label> <label
								class="checkbox-inline"><input type="checkbox"
								class="crestricts" name="crestricts" value="2">ios </label> <label
								class="checkbox-inline"><input type="checkbox"
								class="crestricts" name="crestricts" value="3">安卓 </label>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;" id="tattribute_box">
						<div class="col-md-8">
							<input type="hidden" value="${tattributeParams}"
								id="tattributeParams" /> <label for="exampleInputName2"
								class="col-sm-2 text-right">投标属性限制</label> <label
								class="checkbox-inline"><input type="checkbox"
								class="tattribute" name="tattributes" value="1">新手标 </label> <label
								class="checkbox-inline"><input type="checkbox"
								class="tattribute" name="tattributes" value="2">担保标 </label> <label
								class="checkbox-inline"><input type="checkbox"
								class="tattribute" name="tattributes" value="3">信用标 </label> <label
								class="checkbox-inline"><input type="checkbox"
								class="tattribute" name="tattributes" value="4">抵押标 </label>
						</div>
					</div>


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
						<input type="hidden" value="${params}" id="ulist" />
						<div class="col-md-12 col-md-offset-1" id="ugrade-checkbox-div">
							<c:forEach items="${userGrades}" var="user">
								<label class="checkbox-inline"><input type="checkbox"
									class="ugrades" name="ugradesx" value="${user.ugrade}">${user.ugradename}
								</label>
							</c:forEach>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">排除名单表设置</label>
							<div class="col-sm-6">
								<label> <input type="radio" name="removenameno"
									class="insert-removenameno-radio" value="1">全部排除名单表编号
								</label> <label> <input type="radio" name="removenameno"
									class="insert-removenameno-radio" value="2" checked="checked">选择排除名单表编号
								</label>
							</div>
						</div>
					</div>

					<!-- 选择排除名单表开始 -->
					<div class="row" id="removeName-checkbox-div"
						style="margin-top: 20px;">
						<div class="col-md-12 col-md-offset-1">
							<input type="hidden" value="${activityRule.removenameno}"
								id="removenamenos" />
							<c:forEach items="${removeNames}" var="remove">
								<label class="checkbox-inline"> <input type="checkbox"
									name="removenamenos" class="removenamenos"
									value="${remove.nameno}" /> 名单表类型：<span class="red">${remove.nametype}</span>&nbsp;&nbsp;
									名单表名称：<span class="red">${remove.name}</span>&nbsp;&nbsp;
									名单表编号：<span class="red">${remove.nameno}</span>
								</label>
							</c:forEach>
						</div>
					</div>
					<!-- 选择排除名单表结束 -->
				</div>
				<!-- 标的活动规则设置 end -->

				<div class="alert alert-success text-center" role="alert"
					style="font-size: 14px;">标的活动奖励规则修改</div>
				<!-- 标的活动奖励规则设置 start -->
				<div id="ActivityAwardRule_box">
					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">累投金额值_低位</label>
							<div class="col-sm-6">
								<input type="hidden" value="${activityAwardRule.id}"
									name="activityAwardRule_id" /> <input type="text"
									value="${activityAwardRule.tminmoney}" class="form-control"
									name="tminmoney" id="tminmoney" placeholder="请输入累投金额值_低位...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">累投金额值_高位</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.tmaxmoney}"
									class="form-control" name="tmaxmoney" id="tmaxmoney"
									placeholder="请输入累投金额值_高位...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖品的ID</label>
							<div class="col-sm-6">
								<select name="awardid" id="awardid" class="form-control"
									onchange="ActivityRule.findAwardName(this)"
									data-awardid="${activityAwardRule.awardid}">
									<option value="">--请选择奖品ID--</option>
									<c:if test="${not empty awardList}">
										<c:forEach items="${awardList}" var="award">
											<option value="${award.id}">${award.id}----奖品名称--${award.aname}</option>
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
								<input type="text" value="${activityAwardRule.awardname}"
									class="form-control" name="awardname" id="awardname"
									readonly="readonly" placeholder="请选择奖品ID...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖项名称</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.awardprize}"
									class="form-control" name="awardprize" id="awardprize"
									placeholder="请输入奖项的名称...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖品份数</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.awardcopies}"
									class="form-control" name="awardcopies" id="awardcopies"
									placeholder="请输入奖品份数...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖品发放方式</label>
							<div class="col-sm-6">
								<select name="distributetype" id="distributetype"
									class="form-control"
									data-distributetype="${activityAwardRule.distributetype}">
									<option value="">--请选择奖品发放方式--</option>
									<option value="1">系统</option>
									<option value="2">人工</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖品类型</label>
							<div class="col-sm-6">
								<select name="awardclass" id="awardclass" class="form-control"
									data-awardclass="${activityAwardRule.awardclass}">
									<option value="">--请选择奖品发放方式--</option>
									<option value="1">累投</option>
									<option value="2">单标</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖品是否审核
							</label>
							<div class="col-sm-6">
								<select name="isaudit2" id="isaudit2" class="form-control"
									data-isaudit2="${activityAwardRule.isaudit}">
									<option value="">--请选择是否审核 --</option>
									<option value="1">需要审核</option>
									<option value="2">不需要审核</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖品发放时机</label>
							<div class="col-sm-6">
								<select name="distributemode" id="distributemode"
									class="form-control"
									data-distributemode="${activityAwardRule.distributemode}">
									<option value="">--请选择奖品发放时机-</option>
									<option value="1">成功</option>
									<option value="2">满标</option>
									<option value="3">活动结束</option>
								</select>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖励方式</label>
							<div class="col-sm-6">
								<select onChange="ActivityRule.awardType(this)" name="awardtype"
									id="awardtype" class="form-control"
									data-awardtype="${activityAwardRule.awardtype}">
									<option value="">-----请选择奖励方式-----</option>
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
								<input type="text" value="${activityAwardRule.quota}"
									class="form-control" id="quota" name="quota"
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
								<input type="text" value="${activityAwardRule.awardratio}"
									id="awardratio" class="form-control" id="awardratio"
									name="awardratio" placeholder="请输入定额金额百分比..." />
							</div>
						</div>
						<div class="col-md-8" style="margin-top: 10px;">
							<label class="col-sm-2 text-right">奖励最低值</label>
							<div class="col-sm-6">
								<input type="text" id="aname"
									value="${activityAwardRule.awardmin}" class="form-control"
									id="awardmin" name="awardmin" placeholder="请输入定额金额奖励最低值..." />
							</div>
						</div>
						<div class="col-md-8" style="margin-top: 10px;">
							<label class="col-sm-2 text-right">奖励最高值</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.awardmax}"
									class="form-control" id="awardmax" name="awardmax"
									placeholder="请输入定额金额奖励最高值..." />
							</div>
						</div>
					</div>
					<!-- 奖励金额的百分比方式  显示框结束 -->

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">首投时间限制</label>
							<div class="col-sm-6">
								<input type="text" id="firsttendertime" name="firsttendertime"
									class="Wdate" style="width: 200px;"
									value="${gj:formatDate(activityAwardRule.firsttendertime,'yyyy-MM-dd')}"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">首投金额限制</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.firsttendermoney}"
									class="form-control" name="firsttendermoney"
									id="firsttendermoney" placeholder="请输入首投金额限制...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">注册时间限制（多少天内）</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.regeditdayrest}"
									class="form-control" name="regeditdayrest" id="regeditdayrest"
									placeholder="请输入注册时间限制（多少天内）...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖励IP次数限制</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.iprestrict}"
									class="form-control" name="iprestrict" id="iprestrict"
									placeholder="请输入奖励IP次数限制...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">奖励cookie次数限制</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.cookierestrict}"
									class="form-control" name="cookierestrict" id="cookierestrict"
									placeholder="请输入奖励cookie次数限制...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">排名类型</label>
							<div class="col-sm-6">
								<input type="text" value="${activityAwardRule.ranking}"
									class="form-control" name="ranking" id="ranking"
									placeholder="请输入排名类型...">
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="col-md-8">
							<label for="exampleInputName2" class="col-sm-2 text-right">备注</label>
							<div class="col-sm-6">
								<textarea class="form-control" name="remark2" id="remark2"
									data-remark2="${activityAwardRule.remark}"
									placeholder="请输入备注..."> </textarea>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 25px;">
						<div class="col-md-6">
							<div class="text-center">
								<button type="button" id="submit" class="btn btn-primary"
									onclick="ActivityRule.low_save(this)">保存</button>
								<button type="button" class="btn btn-info"
									onclick="ActivityRule.low_callback(this)">返回</button>
								<span class="red" id="form_error"></span>
							</div>
						</div>
					</div>

					<div class="row" style="margin-top: 45px;">
						<div class="col-md-6"></div>
					</div>
				</div>
				<!-- 标的活动奖励规则设置 end -->
			</form>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
     	//隐藏定额奖励设置 框框
		$("#awardType1").hide();
 		//隐藏奖励百分比设置 框框
		$("#awardType2").hide();
		$("#remark").val($("#remark").data("remark"));//备注信息	
		$("#acttype").val($("#acttype").data("acttype"));//活动类型
		$("#status").val($("#status").data("status"));//活动状态
		$("#tctype").val($("#tctype").data("tctype"));//累投计算方式
		$("#gtype").val($("#gtype").data("gtype"));//获奖名单生成方式
		$("#isauditalist").val($("#isauditalist").data("isauditalist"));//获奖名单是否需要审核
		$("#isaudit").val($("#isaudit").data("isaudit"));//是否审核该表
		
		$("#remark2").val($("#remark2").data("remark2"));//备注信息	
		$("#awardid").val($("#awardid").data("awardid"));//奖品ID
		$("#distributetype").val($("#distributetype").data("distributetype"));//奖品发放方式
		$("#awardclass").val($("#awardclass").data("awardclass"));//奖品类型
		$("#isaudit2").val($("#isaudit2").data("isaudit2"));//是否审核
		$("#distributemode").val($("#distributemode").data("distributemode"));//发放时机
		var awardtype = $("#awardtype").data("awardtype");
		$("#awardtype").val(awardtype);//奖励方式
		if(awardtype == 1){
			//显示定额奖励设置 框框
			$("#awardType1").show();
			//隐藏奖励百分比设置 框框
			$("#awardType2").hide();
		}else if(awardtype == 2){
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
		
		$(".insert-removenameno-radio").change(function(){
 			//排除名单编号选择  1 为全部2 部分
			var $erval = $("input[name='removenameno']:checked").val();
 			if($erval==1){
				$("#removeName-checkbox-div").hide();
			}else if($erval == 2){
				$("#removeName-checkbox-div").show();
			}
		});
		
		//编辑的时候会员等级的默认选中
 		var ulist = $("#ulist").val();
 	    if(ulist.length>0){
 	   	    var u_ulist = ulist.split(",");
    		$.each(u_ulist,function(index,value){
	   			$("#ugrade-checkbox-div input[type='checkbox']").each(function(){
	   					if($(this).val()==value){
	   					$(this).attr("checked",true);
	   				}
	   			});
 	   		});
 	   	} 
 	    
 	   //编辑的时候排除名单表编号时默认选中
		var removenamenos = $("#removenamenos").val();
  	    if(removenamenos.length > 0){
 	   		var u_removenamenos = removenamenos.split(",");
  	    		$.each(u_removenamenos,function(index,value){
  	   			$("#removeName-checkbox-div input[type='checkbox']").each(function(){
   	   					if($(this).val()==value){
 	   						$(this).attr("checked",true);
 	   					}
 	   			});
 	   		});
 	   	}
  	    
   	   //编辑的时候客户端限制时默认选中
		var crestrictParams = $("#crestrictParams").val();
  	    if(crestrictParams.length > 0){
 	   		var u_crestrictParams = crestrictParams.split(",");
  	    		$.each(u_crestrictParams,function(index,value){
  	   			$("#crestrict_box input[type='checkbox']").each(function(){
   	   					if($(this).val()==value){
 	   						$(this).attr("checked",true);
 	   					}
 	   			});
 	   		});
 	   	}
  	    
  		//编辑的时候投标属性限制时默认选中
		var tattributeParams = $("#tattributeParams").val();
  	    if(tattributeParams.length > 0){
 	   		var u_tattributeParams = tattributeParams.split(",");
  	    		$.each(u_tattributeParams,function(index,value){
  	   			$("#tattribute_box input[type='checkbox']").each(function(){
   	   					if($(this).val()==value){
 	   						$(this).attr("checked",true);
 	   					}
 	   			});
 	   		});
 	   	}
  	    
  	    //编辑的时候定向标号默认选中
 		var specifytnoParams = $("#specifytnoParams").val();
    	if(specifytnoParams.length > 0){
 	   	    var u_specifytnoParams = specifytnoParams.split(",");
      		$.each(u_specifytnoParams,function(index,value){
      			$("#tenderItems_box").find(".list-item").find("select").eq(index).val(value);
   	   		});
 	   	} 
    	
   	    //编辑的时候排除标号默认选中
 		var canceltnoParams = $("#canceltnoParams").val();
     	if(canceltnoParams.length > 0){
 	   	    var u_canceltnoParams = canceltnoParams.split(",");
      		$.each(u_canceltnoParams,function(index,value){
       			$("#canceltnoItems_box").find(".list-item").find("select").eq(index).val(value);
   	   		});
 	   	}
     	
	});
	
	var timer = null;
	var ActivityRule = {
 			//根据奖品ID查询奖品名称
			findAwardName:function(obj){
			        $.tzAjax.request({
		        	model:"/admin/activityRule",
		        	method:"/findAwardName.action",
		        	callback:function(data){
		        		var obj = $.parseJSON(data);
			        	$("#awardname").val(obj);
		        	}
		        },{"id":$(obj).val()});
			 },
			//保存
			low_save:function(obj){
				if(!check().form()){
 					return;
				}
   				var params = $("#ActivityRule_from").serialize();
  				$("#submit").removeAttr("onclick").text("保存中...");
  				clearTimeout(timer);
  				timer = setTimeout(function(){
	 				$.ajax({
						type:"post",
						url:basePath+"/admin/activityRule/update.action",
						error:function(){$("#submit").attr("onclick","ActivityRule.low_save(this)").text("保存");},
						data:params,
						success:function(data){
							$("#submit").attr("onclick","ActivityRule.low_save(this)").text("保存");
							$("#form_error").text("");
	 						var obj = $.parseJSON(data);
	 						if(data=="logout"){
								window.location.href=basePath+"/login.action";
							}else if(obj.result == "fail"){
								$("#form_error").text("保存失败！请稍后再试");
							}else if(obj.result == "success"){
								alert("保存成功！");
								window.location.href=basePath+"/admin/activityRule/list.action";
							}
						}
	 				});
   				},1000);
			},
			//返回
			low_callback:function(){
				window.location.href=basePath+"/admin/activityRule/list.action";
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
		   //增加定向标
			savespecifytno:function(){
 				var html ="<li class='row list-item'>"+
				    	  "  	   <div class='col-md-12'>"+
					      "	    		<select name='specifytnos' id='specifytno' class='form-control specifytno'>"+
						  "  			<option value=''>--请选择定向标号--</option>"+
						  "  			<c:if test='${not empty tenderItems}'>"+
						  "	    			<c:forEach items='${tenderItems}' var='tenders'>"+
						  "		    			<option value='${tenders.tno}'>${tenders.tno}----定向标名称--${tenders.tname}</option>"+
						  "	    			</c:forEach>"+
					 	  "  			</c:if>"+
						  "			</select>"+
				    	  "  	   </div>"+
				    	  "  	   <div class='col-md-2'>"+
					      "	    	 <a href='javascript:void(0)' onclick='ActivityRule.deletespecifytno(this);'>删除</a>"+
				    	  "  	   </div>"+
			 	    	  "  </li>"
				$("#tenderItems_box").append(html);
			},
			//删除定向标
			deletespecifytno:function(obj){
 				$(obj).parent().parent().remove();
  			},
  		    //增加排除的标号
  			savecanceltno:function(){
  				var html ="<li class='row list-item'>"+
		    	  "  	   <div class='col-md-12'>"+
			      "	    		<select name='canceltnos' id='canceltno' class='form-control specifytno'>"+
				  "  			<option value=''>--请选择排除的标号--</option>"+
				  "  			<c:if test='${not empty tenderItems}'>"+
				  "	    			<c:forEach items='${tenderItems}' var='tenders'>"+
				  "		    			<option value='${tenders.tno}'>${tenders.tno}----定向标名称--${tenders.tname}</option>"+
				  "	    			</c:forEach>"+
			 	  "  			</c:if>"+
				  "			</select>"+
		    	  "  	   </div>"+
		    	  "  	   <div class='col-md-2'>"+
			      "	    	 <a href='javascript:void(0)' onclick='ActivityRule.deletecanceltno(this);'>删除</a>"+
		    	  "  	   </div>"+
	 	    	  "  </li>"
				$("#canceltnoItems_box").append(html);
  			},
  		   //删除排除的标号
  			deletecanceltno:function(obj){
  				$(obj).parent().parent().remove();
  			},
  			//活动生效日期和活动截止日期验证
  			check_actbtime:function(dp){
  				var actetime = dp.cal.getDateStr();
  				var actbtime = $("#actbtime").val();
   				if(isEmpty(actbtime)){
  					alert("请选择活动生效日期!");
  					$("#actetime").val("");
  				}else{
  					if(actbtime > actetime){
  						alert("活动截止日期必须在活动生效日期之后!");
  						$("#actetime").val("");
  					}
  				}
  			 
  			},
  			//验证活动生效时间
  			check_time:function(dp){
  				var actetime = dp.cal.getDateStr();
  				var date = (new Date()).format("yyyy-MM-dd hh:mm:ss");
  				if(actetime < date){
  					alert("活动生效时间必须是当前时间之后!");
  					$("#actbtime").val("");
  				}
   			}
	};
	//验证活动
	function check(){
		//验证数字
		jQuery.validator.addMethod("Integer",function(value,element){
			var number = /^([1-9][\d]{0,7}|0)$/;
			return this.optional(element) || (number.test(value));
		},"*&nbsp;请输入有效的正整数");
		//验证float
		jQuery.validator.addMethod("Float",function(value,element){
			var number = /^([1-9][\d]{0,7}|0)(\.\d{1,2})?$/;
			return this.optional(element) || (number.test(value));
		},"*&nbsp;请输入合法的数字");
		 return  $("#ActivityRule_from").validate({
			 errorPlacement: function (error, element) {
					var p = $("<em></em>").append(error);
					p.appendTo(element.parent().parent());
					p.css({"color":"red"});
				},
  			 rules :{
 				 actname : {
					 "required" : true
				 },
				 specifytnos : {
					 "required" : true
				 },
				 canceltnos : {
					 "required" : true
				 },
				 tdayrestrict : {
					 "required" : true,
					 "Integer" : true
				 },
				 tmlrrestrict : {
					 "required" : true,
					 "Float" : true
				 },
				 tmhrrestrict : {
					 "required" : true,
					 "Float" : true
				 },
				 acttype : {
					 "required" : true
				 },
				 status : {
					 "required" : true
				 },
				 tctype : {
					 "required" : true
				 },
				 crestricts : {
					 "required" : true
				 },
				 gtype : {
					 "required" : true
				 },
				 isauditalist : {
					 "required" : true
				 },
				 isaudit : {
					 "required" : true
				 },
				 isauditalist : {
					 "required" : true
				 },
				 actbtime : {
					 "required" : true
				 },
				 actetime: {
					 "required" : true
				 },
				 tattributes:{
					 "required" : true
				 },
				 ugradesx:{
					 "required" : true
				 },
				 removenamenos:{
					 "required" : true
				 },
 				 //活动奖励验证开始
				 actid:{
					 "required" : true
				 },
				 tminmoney:{
					 "required" : true,
					 "Float" : true
				 },
				 tmaxmoney:{
					 "required" : true,
					 "Float" : true
				 },
				 awardid:{
					 "required" : true
				 },
				 awardclass:{
					 "required" : true
				 },
				 awardprize:{
					 "required" : true
				 },
 				 awardcopies:{
					 "required" : true,
					 "Integer" : true
				 },
				 awardtype:{
					 "required" : true
				 },
				 quota:{
					 "required" : true,
					 "Float" : true
				 },
				 awardratio:{
					 "required" : true,
					 "Float" : true
				 },
				 awardmin:{
					 "required" : true,
					 "Float" : true
				 },
				 awardmax:{
					 "required" : true,
					 "Float" : true
				 },
				 distributemode:{
					 "required" : true
				 },
				 distributetype:{
					 "required" : true
				 },
				 firsttendertime:{
					 "required" : true
				 },
				 firsttendermoney:{
					 "required" : true,
					 "Float" : true
				 },
				 regeditdayrest:{
					 "required" : true,
					 "Integer" : true
				 },
				 iprestrict:{
					 "required" : true,
					 "Integer" : true
				 },
				 cookierestrict:{
					 "required" : true,
					 "Integer" : true
				 },
				 isaudit2:{
					 "required" : true
				 },
				 ranking:{
					 "required" : true
				 }
    		 },
			 messages :{
 				 actname :{
					 "required" :"*&nbsp;请输入活动名称"
				 },
				 specifytnos :{
					 "required" :"*&nbsp;请选择定向标号"
				 },
				 canceltnos :{
					 "required" :"*&nbsp;请选择排除的标号"
				 },
				 tdayrestrict :{
					 "required" :"*&nbsp;请输入投标期限限制"
				 },
				 tmlrrestrict :{
					 "required" :"*&nbsp;请输入投标收益限制最低值"
				 },
				 tmhrrestrict :{
					 "required" :"*&nbsp;请输入投标收益限制最高值"
				 },
				  
				 remark :{
					 "required" :"*&nbsp;备注信息最多输入100字符"
				 },
				 acttype :{
					 "required" :"*&nbsp;请选择活动类型"
				 },
				 status :{
					 "required" :"*&nbsp;请选择活动状态"
				 },
				 tctype :{
					 "required" :"*&nbsp;请选择累投计算方式"
				 },
				 crestricts :{
					 "required" :"*&nbsp;请选择客户端限制方式"
				 },
				 gtype :{
					 "required" :"*&nbsp;请选择获奖名单生成方式"
				 },
				 isauditalist :{
					 "required" :"*&nbsp;请选择获奖名单是否需要审核"
				 },
				 isaudit :{
					 "required" :"*&nbsp;请选择是否审核(该表)"
				 },
				 isauditalist :{
					 "required" :"*&nbsp;请选择获奖名单是否需要审核"
				 },
				 actbtime :{
					 "required" :"*&nbsp;请选择活动生效日期"
				 },
				 actetime :{
					 "required" :"*&nbsp;请选择活动截止日期"
				 },
				 tattributes:{
					 "required" :"*&nbsp;请选择投标属性限制"
				 },
				 ugradesx:{
					 "required" :"*&nbsp;请选择会员等级"
				 },
				 removenamenos:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择排除名单"
				 },
				 
				//活动奖励验证开始
				 tminmoney:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入累投金额值_低位"
				 },
				 tmaxmoney:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入累投金额值_高位"
				 },
				 awardid:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择奖品ID"
				 },
 				 awardclass:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖品类型"
				 },
				 awardprize:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖项名称"
				 },
				 awardcopies:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖品份数"
				 },
				 awardtype:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择奖励方式"
				 },
				 quota:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励定额"
				 },
				 awardratio:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励百分比数"
				 },
				 awardmin:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励最低值"
				 },
				 awardmax:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励最高值"
				 },
				 distributemode:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择发放时机"
				 },
				 distributetype:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择发放方式"
				 },
				 firsttendertime:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入首投时间限制"
				 },
				 firsttendermoney:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入首投金额限制"
				 },
				 regeditdayrest:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入注册时间限制（多少天内）"
				 },
				 iprestrict:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励IP次数限制"
				 },
				 cookierestrict:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入奖励cookie次数限制"
				 },
				 isaudit2:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请选择是否审核"
				 },
				 ranking:{
					 "required" :"&nbsp;&nbsp;&nbsp;*&nbsp;请输入排名类型"
				 },
 				 remark2 :{
					 "required" :"*&nbsp;备注信息最多输入100字符"
				 }
 			 }
		 });
  	};
  </script>
</body>
</html>