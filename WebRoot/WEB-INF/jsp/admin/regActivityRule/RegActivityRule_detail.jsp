<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<div class="well text-center"
	style="font-size: 14px; background: #d6e9c6; line-height: 0px;">注册活动规则详情</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">活动编号</label><label class="col-md-4">${detail.actno}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">活动名称</label><label class="col-md-4">${detail.actname}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">活动开始日期</label><label
		class="col-md-4"><f:formatDate value="${detail.actbtime}"
			pattern="yyyy-MM-dd HH:mm:ss" /></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">活动结束日期</label><label
		class="col-md-4"><f:formatDate value="${detail.actetime}"
			pattern="yyyy-MM-dd HH:mm:ss" /></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">是否判断注册时间</label><label
		class="col-md-4">${isconsiderregtime}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">获奖名单是否需要审核</label><label
		class="col-md-4">${isauditalist}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">生成方式</label><label class="col-md-4">${generatetype}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">是否为模板</label><label class="col-md-4">${istemplet}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">活动状态</label><label class="col-md-4">${status}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">执行时间</label><label class="col-md-4"><f:formatDate
			value="${detail.executetime}" pattern="yyyy-MM-dd HH:mm:ss" /> <c:if
			test="${empty detail.executetime}">--</c:if></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">制表人</label><label class="col-md-4">${detail.addman}</label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">制表时间</label><label class="col-md-4"><f:formatDate
			value="${detail.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></label>
</div>
<div class="row" style="line-height: auto;">
	<label class="col-md-5 text-right">备注</label><label class="col-md-6">${detail.remark}</label>
</div>
<c:if test="${!empty infoList}">
	<div class="well text-center"
		style="font-size: 14px; background: #d6e9c6; line-height: 0px;">注册活动奖励规则详情</div>
	<c:forEach items="${infoList}" var="info" varStatus="key">
		<font>子活动${key.index+1}</font>
		<c:if test="${!empty business_map}">
			<c:forEach items="${business_map}" var="business">
				<c:choose>
					<c:when test="${info.business eq business.key}">
						<div class="row" style="line-height: auto;">
							<label class="col-md-5 text-right">指定业务</label><label
								class="col-md-4">${business.value}</label>
						</div>
					</c:when>
				</c:choose>
			</c:forEach>
		</c:if>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">客户端限制</label><label
				class="col-md-5"><c:if test="${!empty info.crestrict}">
					<c:forEach items="${info.crestrict}" var="client">
						<c:if test="${!empty crestrict_map}">
							<c:forEach items="${crestrict_map}" var="crestrict">
								<c:choose>
									<c:when test="${client eq crestrict.key}">${crestrict.value}</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
					</c:forEach>
				</c:if></label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">限定时间</label><label
				class="col-md-4"><c:if test="${empty info.finishtime}">--</c:if>
				<c:if test="${!empty info.finishtime}">
					${info.finishtime}<span>小时</span>
				</c:if> </label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">奖品的名称</label><label
				class="col-md-4">${info.awardname}</label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">奖品份数</label><label
				class="col-md-4"><c:if test="${empty info.awardcopies}">--</c:if>
				<c:if test="${!empty info.awardcopies}">
					${info.awardcopies}<span>份</span>
				</c:if> </label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">奖励定额</label><label
				class="col-md-4"><c:if test="${empty info.quota}">--</c:if>
				<c:if test="${!empty info.quota}">
					${info.quota}<span>元</span>
				</c:if> </label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">子活动设置人</label><label
				class="col-md-4">${info.addman}</label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">子活动设置时间</label><label
				class="col-md-4"><f:formatDate value="${info.addtime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></label>
		</div>
		<div class="row" style="line-height: auto;">
			<label class="col-md-5 text-right">备注</label><label class="col-md-4">${info.remark}</label>
		</div>
		<br />
	</c:forEach>
</c:if>
