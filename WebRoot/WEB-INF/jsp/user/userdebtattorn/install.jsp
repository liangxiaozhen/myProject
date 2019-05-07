<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath;
	int prot = request.getServerPort();
	if (prot == 80) {
		basePath = request.getScheme() + "://" + request.getServerName() + path;
	} else {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}
	pageContext.setAttribute("basePath", basePath);
%>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资记录</title>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/userdebtattorn/userdebtattornlist.js"></script> --%>
<script type="text/javascript">var basePath="${basePath}";</script>
<script type="text/javascript">
    	/* 设置是否定向转让 */
	 	$(".isattornlb").change(function(){
	  		var isattorn = $("input[name='isattorn']:checked").val();
	  		if(isattorn==1){
				$("#passowrddiv").show();
			}else{
				$("#passowrddiv").hide();
			}
	  	});
    
    </script>
</head>
<!-- <body>
	<div  style="width:100%;margin-left: 200px;margin-right: auto;" id="frozen-content">
		<div class="row clearfix" >
			<div class="col-md-12 column" > -->
<form action="" id="userForm">
	<input value="${day}" type="hidden" id="daylb" />
	<!-- 可上架天数 -->
	<input value="${periods}" type="hidden" id="periodslb" />
	<!-- 当前期数 -->
	<input value="${debtAttorn.id}" type="hidden" id="idlb" /> <input
		value="${identifying}" type="hidden" id="identifyinglb" /> <input
		value="${debtAttorn.baseid}" type="hidden" id="baseidlb" /> <input
		value="${debtAttorn.isdebtaudit}" type="hidden" id="isdebtauditlb" />
	<!-- 是否需要审核 -->
	<input value="${debtAttorn.tid}" type="hidden" id="tidlb" />
	<!-- 唯一标识 -->
	<input value="${debtAttorn.aownergrade}" type="hidden"
		id="aownergradelb" />
	<!-- 允许债转的会员等级 -->
	<input value="${debtAttorn.removenameno}" type="hidden"
		id="removenamenolb" />
	<!-- 排出人员名单 -->
	<input value="${tenderitem.tstatus}" type="hidden" id="tstatuslb" />
	<!--标的状态 -->
	<input value="${debtAttorn.isocdebt}" type="hidden" id="isocdebtlb" />
	<!--是否支持逾期债转-->
	<input value="${debtAttorn.minattornmoney}" type="hidden"
		id="minattornmoneylb" />
	<!--债转金额最低值-->
	<input value="${debtAttorn.maxattornmoney}" type="hidden"
		id="maxattornmoneylb" />
	<!--债转金额最高值-->
	<input value="${debtAttorn.quota}" type="hidden" id="quotalb" />
	<!--定额-->
	<input value="${debtAttorn.attornrate}" type="hidden" id="attornratelb" />
	<!--百分比-->
	<input value="${debtAttorn.minfee}" type="hidden" id="minfeelb" />
	<!--百分比最低-->
	<input value="${debtAttorn.maxfee}" type="hidden" id="maxfeelb" />
	<!--百分比最高-->
	<input value="${usertender.tendtime}" type="hidden" id="tendtimelb" />
	<!--投标结束时间-->
	<input value="${debtAttorn.holdday}" type="hidden" id="holddaylb" />
	<!--持有时间-->
	<input value="${debtAttorn.intervalday}" type="hidden"
		id="intervaldaylb" />
	<!--距离下个还款日-->
	<input value="${debtAttorn.tenderitem.retdate}" type="hidden"
		id="retdatelb" />
	<!--首次还款时间-->
	<div class="row" style="margin-top: 10px; margin-left: -30%;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">标的号id&nbsp;:</span> <span
				id="tenderidlb"> <input value="${usertender.tenderid}"
				type="hidden" id="tenderidlb_input" /> <%-- ${debtAttorn.usertender.tenderid} --%>
				${usertender.tenderid}
			</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; margin-left: -30%">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">投标订单号&nbsp;:</span> <span
				id="ordernolb"> <input value="${usertender.orderno}"
				type="hidden" id="ordernolb_input" /> ${usertender.orderno}
			</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; margin-left: -30%">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">投标金额&nbsp;:</span> <span
				id="amountlb"> <input value="${usertender.amount}"
				type="hidden" id="amountlb_input" /> <c:if
					test="${!empty usertender.amount}">${df1.format(usertender.amount)}</c:if>
			</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; margin-left: -30%">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">待收本金&nbsp;:</span>
			<%-- 	 <c:if test="${tenderitem.repaymentpro eq 1}"><!-- 假如是一次性还本付息,那么待收本金就是当期还款金额 -->
							 		<span id="restprincipallb_input">
							 			<c:if test="${!empty rmt.ramount}">${df1.format(rmt.ramount)}</c:if>
							 		</span>
							 </c:if>
							  <c:if test="${tenderitem.repaymentpro eq 2 or tenderitem.repaymentpro eq 3 or tenderitem.repaymentpro eq 4}"> --%>
			<span id="restprincipallb_input"> <c:if
					test="${!empty benjin}">${df1.format(benjin)}</c:if>
			</span>
			<%-- </c:if> --%>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; margin-left: -30%">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">债转金额范围&nbsp;:</span> <span
				id="amountfanweilb"> <input
				value="${debtAttorn.attornmoneylow}" type="hidden"
				id="attornmoneylowlb" /> <input value="${debtAttorn.attornmoney}"
				type="hidden" id="attornmoneylb" /> <c:if
					test="${!empty debtAttorn.attornmoneylow}">${df1.format(debtAttorn.attornmoneylow)}</c:if>--
				<c:if test="${!empty debtAttorn.attornmoney}">${df1.format(debtAttorn.attornmoney)}</c:if>
			</span>
		</div>
	</div>
	<c:if test="${debtAttorn.dattrestrict eq 1}">
		<div class="row" style="margin-top: 10px; margin-left: -30%">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">可债转层数&nbsp;:</span> <span
					id="datimeslb"> <input value="${debtAttorn.datimes}"
					type="hidden" id="datimeslb_input" /> ${debtAttorn.datimes}层
				</span>
			</div>
		</div>
	</c:if>
	<c:if test="${debtAttorn.dattrestrict eq 2}">
		<div class="row" style="margin-top: 10px; margin-left: -30%">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">可债转次数&nbsp;:</span> <span
					id="datimeslb"> <input value="${debtAttorn.datimes}"
					type="hidden" id="datimeslb_input" /> ${debtAttorn.datimes}次
				</span>
			</div>
		</div>
	</c:if>
	<div class="row" style="margin-top: 10px; margin-left: -30%">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">全部/部分&nbsp;:</span> <span> <input
				value="${debtAttorn.isasplit}" type="hidden" id="isasplitlb" /> <c:if
					test="${debtAttorn.isasplit eq 0}">全部</c:if> <c:if
					test="${debtAttorn.isasplit eq 1}">部分</c:if>
			</span>
		</div>
	</div>
	<div class="row"
		style="margin-top: 10px; margin-left: -30%; display: block;">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">是否逾期&nbsp;:</span> <span> <c:if
					test="${identifying eq 1}">否</c:if> <c:if
					test="${identifying eq 2}">是</c:if>
			</span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; margin-left: -30%">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">是否定向转让&nbsp;:</span> <span
				id="isattornlb"> <input type="radio" class="isattornlb"
				name="isattorn" value="0" checked="checked" />否 <input type="radio"
				class="isattornlb" name="isattorn" value="1" />是
			</span>
		</div>
	</div>
	<div class="row"
		style="margin-top: 10px; margin-left: -30%; display: none"
		id="passowrddiv">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">定向债转码&nbsp;:</span> <span> <input
				type="text" id="udapasslb" onblur="udapassFun()" />
			</span><span id="udapasslb_yanz" style="color: red"></span>
		</div>
	</div>
	<%-- --%>

	<div class="row" style="margin-top: 10px; margin-left: -30%">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">上架时间&nbsp;:</span>
			<%-- <input value="${debtAttorn.usertender.orderno}" type="hidden" id="ordernolb_input"/> --%>
			<input value="" type="text" id="deadline" style="width: 100px"
				onblur="deadlineFun(this)" onkeyup="checkUp(event,this)" />天 <span
				id="deadline_yanz" style="color: red"></span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; margin-left: -30%"
		id="daamountlb_div">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">债转金额&nbsp;:</span> <span> <c:if
					test="${debtAttorn.isasplit eq 1}">
					<!--可拆分:就是部分 -->
					<input id="daamountlb" value="" onblur="checkBlur(this)"
						onkeyup="checkUp(event,this)" style="width: 100px" />
				</c:if> <c:if test="${debtAttorn.isasplit eq 0}">
					<!-- 不可拆分:就是全部债转 -->
					<%-- <input id="daamountlb" value="${df1.format(debtAttorn.repayment.restprincipal)}"/> --%>
					<input id="daamountlb" value="${benjin}" style="width: 100px"
						disabled='disabled' />
				</c:if> 以转让系数为 <input value="${debtAttorn.minattornratio}" type="hidden"
				id="minattornratiolb" /> <input value="${debtAttorn.maxattornratio}"
				type="hidden" id="maxattornratiolb" /> <input id="coefficientlb"
				style="width: 100px" onblur="checkBlur2(this)"
				onkeyup="checkUp(event,this)" /> 转让给承接人 <input
				id="amountcoefficient" style="width: 100px" disabled='disabled' />
			</span> <span id="daamountlb_yanz" style="color: red"></span> <span
				id="coefficientlb_yanz" style="color: red"></span>
		</div>
	</div>
	<c:if test="${identifying eq 2}">
		<div class="row" style="margin-top: 10px; margin-left: -30%"
			id="passowrddiv">
			<div class="col-md-10 col-md-offset-1">
				<span class="col-sm-4 text-right">代收滞纳金&nbsp;:</span> <span>

					<c:if test="${debtAttorn.isasplit eq 0}">
						<!-- 部分债转 -->
						<input id="Collect_fines" value="" disabled='disabled'
							style="width: 100px" />
					</c:if> <c:if test="${debtAttorn.isasplit eq 1}">
						<!-- 全部债转 -->
						<input id="Collect_fines" value="${lateFee}" style="width: 100px"
							disabled='disabled' />
					</c:if> &nbsp;以转让系数为 <input type="text" id="latecoefficient"
					onblur="checkBlur4(this)" onkeyup="checkUp(event,this)"
					style="width: 100px" /> 转让给承接人 <input id="latefee"
					style="width: 100px" disabled='disabled' />
				</span> <span id="Collect_fineslb" style="color: red"></span>
			</div>
		</div>
	</c:if>
	<div class="row" style="margin-top: 10px; margin-left: -30%"
		id="passowrddiv">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">待收利息&nbsp;:</span> <span> <c:if
					test="${debtAttorn.isasplit eq 0}">
					<input id="Collecting_interest" value="${nomalFee}"
						disabled='disabled' style="width: 100px" />
				</c:if> <c:if test="${debtAttorn.isasplit eq 1}">
					<input id="Collecting_interest" value="" style="width: 100px"
						disabled='disabled' />
				</c:if> &nbsp;以转让系数为 <input type="text" id="interestcoefficient"
				onblur="checkBlur3(this)" onkeyup="checkUp(event,this)"
				style="width: 100px" /> 转让给承接人<input id="interestfee"
				style="width: 100px" disabled='disabled' />
			</span> <span id="Collecting_interestlb" style="color: red"></span>
		</div>
	</div>
	<div class="row" style="margin-top: 10px; margin-left: -30%"
		id="passowrddiv">
		<div class="col-md-10 col-md-offset-1">
			<span class="col-sm-4 text-right">待收总额&nbsp;:</span> <span><input
				id="totalfee" style="width: 100px" disabled='disabled' /> </span>
		</div>
	</div>
	<%-- 	<div class="row" style="margin-top: 10px;margin-left: -30%">
						<div class="col-md-10 col-md-offset-1">
							<span class="col-sm-4 text-right">债转系数&nbsp;:</span>
							<span>
								<input value="${debtAttorn.minattornratio}" type="hidden" id="minattornratiolb"/>
								<input value="${debtAttorn.maxattornratio}" type="hidden" id="maxattornratiolb"/>
								<input id="coefficientlb" onblur="checkBlur2(this)" onkeyup="checkUp(event,this)"/>
							</span>
							<span id="coefficientlb_yanz" style="color: red"></span>
						</div>
					</div>  --%>
</form>
<!--			</div>
 		</div>
	</div>
</body>
</html> -->