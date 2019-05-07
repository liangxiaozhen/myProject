<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/gj.tld" prefix="gj"%>
 		<table class="table table-hover">
	 	 	<thead>
		 		<tr class="text-center" style="background: #ccc;">
		 			<td>序号</td>
  			 		<td>计算方式</td>
  			 		<td>标的类型</td>
  			 		<td>会员等级</td>
  			 		<td>利息管理费百份比</td>
  			 		<td>最高利息管理收费金额</td>
   			 		<td>是否为模板</td>
   			 		<td>资金清算是否需要审核</td>
 			 		<td>添加人</td>
			 		<td>添加时间</td>
     			 	<td>操作</td>
		 		</tr>
	 	 	</thead>
	 	 	<tbody>
	 	 		<c:forEach items="${pagehelper.list}" var="user" varStatus="index"> 
 		 	 		<tr class="text-center">
 		 	 			<td>
 		 	 				 ${index.index+1 }
		 	 			</td>
		 	 			<td>
 		 	 				${user.gfitype == 1 ? '用户等级' : '标的风险等级'}
		 	 			</td>
 				 		<td class="tzui-tips" tip="${user.ttypestr == null ? '暂无数据' : user.ttypestr} "><span>${user.subttypestr}</span></td>
				 		<td class="tzui-tips" tip="${user.ugradestr == null ? '暂无数据' : user.ugradestr}">${user.subugradestr}</td>
				 		<td>${user.iepercent}</td>
				 		<td>${user.maxiefee}</td>
				 		<td>${user.isaudit == 1 ? '是' : '否'}</td>
				 		<td>${user.istemplet == 1 ? '是' : '否'}</td>
 				 		<td>${user.addman}</td>
 				 		<td>${user.addtimestr}</td>
     				 	<td>
  				 			<div class="btn-group">
								<button class="btn btn-default" onclick="interestExpense.low_detail(this)" data-opid="${user.id}">详情</button>
								<button class="btn btn-default" onclick="interestExpense.low_update(this)" data-opid="${user.id}">修改</button>
								<button class="btn btn-default" onclick="interestExpense.low_delete(this)" data-opid="${user.id}">删除</button>
							</div>
 				 		</td> 
 		 	 		</tr>
	 	 		</c:forEach>
	  	 	</tbody>
		 </table>
		 <div id="page_div">
			<%@ include file="/WEB-INF/jsp/common/pagehelper.jsp" %>
		</div>
		
		
