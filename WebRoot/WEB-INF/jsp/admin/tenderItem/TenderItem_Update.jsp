<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta charset="UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <title>tenderItem_insert</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
    <!-- 日历 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        .laber_from {
            color: #222;
            font-weight: normal;
        }

        .route_bg {
            background-color: #E7E7E7;
            border-radius: 4px;
            padding: 5px;
            margin-right: 5px;
            margin-left: 5px;
            margin-top: 5px;
        }

        .route_bg i {
            color: #ccc;
            font-weight: 400;
            font-size: 12px;
            padding-right: 5px;
            line-height: 25px;
        }

        .route_bg a {
            font-size: 12px;
            color: #666;
            text-decoration: none;
            line-height: 1.6;
            font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei", "\9ED1\4F53", Arial, sans-serif !important;
        }

        .route_bg a:hover {
            color: #888;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="route_bg">
    <a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
    <a href="#">标的设置</a>
</div>
<div class="container" style="margin-top: 20px;">
    <form class="form-horizontal" method="post" id="form1"
          action="${pageContext.request.contextPath}/admin/tenderItem/updateTenderItem.action">
        <input type="hidden" name="id" value="${tenderItem.id}"/>
        <input type="hidden" name="aheadRepayMode.id" value="${tenderItem.aheadRepayMode.id}"/>
        <%-- <div class="form-group">
             <label class="col-sm-3 control-label">标的显示类型</label>
             <div class="col-sm-3">
                 <select name="displaytype" class="form-control">
                     <option value="1" selected>明标</option>
                     <option value="2">暗标</option>
                 </select>
             </div>
         </div>--%>
        <%--标的显示类型(1.明标 前台可以显示， 2.暗标 前台不显示)--%>
        <input type="hidden" name="displaytype" value="1">

        <div class="form-group">
            <label for="inputtname" class="col-sm-3 control-label">标的名称</label>
            <div class="col-sm-3">
                <input type="text" name="tname" value="${tenderItem.tname}" class="form-control" id="inputtname"
                       placeholder="标的名称" readonly>
            </div>
        </div>

        <div class="form-group">
            <label for="tamount" class="col-sm-3 control-label">标的金额</label>
            <div class="col-sm-3">
                <input type="text" name="" class="form-control" id="tamount" placeholder="标的金额"
                       value="${tenderItem.tamountstr}" readonly/>
                <input type="hidden" name="tamount" value="${tenderItem.tamount}"/>
            </div>
        </div>

        <div class="form-group">
            <label for="tinterest" class="col-sm-3 control-label">标收益</label>
            <div class="col-sm-3">
                <input type="text" name="tinterest" class="form-control" id="tinterest" placeholder="投资人收益，精确值0.01"
                       onkeyup="clearNoNum(this)"
                       value="${tenderItem.tinterest}"/>
            </div>
            <div class="col-sm-3"><label class="control-label" id="percent">%</label></div>

        </div>

        <div class="form-group">
            <label for="inputloantime" class="col-sm-3 control-label">借款周期</label>
            <div class="col-sm-3">
                <input type="text" name="" class="form-control" id="inputloantime"
                       placeholder="借款周期" value="${tenderItem.loantime}${tenderItem.dayormonth}" readonly/>
            </div>
        </div>
        <input type="hidden" name="loantime" value="${tenderItem.loantime}"/>
        <input type="hidden" name="dayormonth" value="${tenderItem.dayormonth}"/>
        <!--是否为约标-->
        <%--<div class="form-group">
            <label for="isappointtender" class="col-sm-3 control-label">是否为约标</label>
            <div class="col-sm-3">
                <select name="isappointtender" id="isappointtender" class="form-control">
                    <c:if test="${!empty tenderItem.isappointtender }">
                        <c:if test="${tenderItem.isappointtender==0}">
                            <option value="0">否</option>
                        </c:if>
                        <c:if test="${tenderItem.isappointtender==1}">
                            <option value="1">是</option>
                        </c:if>
                    </c:if>
                </select>
            </div>
        </div>--%>
        <!--约标吗-->
        <%-- <div class="form-group" id="tpass_div" style="display: none;">
             <label for="tpass" class="col-sm-3 control-label">约标码</label>
             <div class="col-sm-3">
                 <input type="text" name="tpass" class="form-control" id="tpass" placeholder="请填写约标码"/>
             </div>
         </div>--%>
        <input type="hidden" name="isappointtender" value="${tenderItem.isappointtender}"/>
        <!--起息规则-->
        <div class="form-group">
            <label class="col-sm-3 control-label" for="valueRule">起息日设置</label>
            <div class="col-sm-3">
                <select name="valuerule" id="valueRule" class="form-control" onchange="selectTime(this)">
                    <option value="">请选择</option>

                    <option value="1" <c:if test="${tenderItem.valuerule==1}">selected</c:if>>生成还款日当天</option>
                    <option value="2" <c:if test="${tenderItem.valuerule==2}">selected</c:if>>生成还款日次日</option>
                    <option value="3" <c:if test="${tenderItem.valuerule==3}">selected</c:if>>生成还款日指定时间点前后</option>
                </select>
            </div>
        </div>

        <!--起息日时间点-->
        <div class="form-group" id="valuePoint">
            <label class="col-sm-3 control-label">起息日时间点</label>
            <div class="col-sm-3">
                <input type="text" name="valuepoint" value="${tenderItem.valuepoint}" placeholder="起息时间点如:10:00:00"
                       class="form-control"
                       onclick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
            </div>
        </div>

        <!--标的开始结束日期-->
        <div class="form-group">
            <label for="inputtbegintime" class="col-sm-3 control-label">投标期</label>
            <div class="col-sm-3">
                <input type="text" name="tbegintime"
                       value=" <fmt:formatDate value='${tenderItem.tbegintime}' type='both'/>" class="form-control"
                       id="inputtbegintime" placeholder="设置投标开始时间"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'new Date'})">
                <span id="tbegintimeSpan" style="color: red;"></span>
            </div>

            <div class="col-sm-3">
                <input type="text" name="tendtime" class="form-control"
                       value="<fmt:formatDate value="${tenderItem.tendtime}" type="both"/>"
                       onfocus="tendtimeOnfocus()" id="inputtendtime" placeholder="设置投标结束时间"
                       onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'new Date'})">
                <span id="tendtimeSpan" style="color: red;"></span>
            </div>
            <div class="col-sm-3">
                <span id="spana1" style="color: red;"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">投标人设置</label>
            <div class="col-sm-3">
                <select name="investerrange" id="investerrange" class="form-control" onchange="investerChange(this);">
                    <option value="">请选择投标人</option>
                    <option value="1" <c:if test="${tenderItem.investerrange==1}">selected</c:if>>任何人皆可投标</option>
                    <option value="2" <c:if test="${tenderItem.investerrange==2}">selected</c:if>>只允许新手投标</option>
                    <option value="3" <c:if test="${tenderItem.investerrange==3}">selected</c:if>>按会员等级设置</option>
                    <option value="4" <c:if test="${tenderItem.investerrange==4}">selected</c:if>>按定向名单设置</option>
                </select>
            </div>
        </div>
        <!--会员等级-->

        <div id="uGrade">
            <div class="form-group">
                <label class="col-sm-3 control-label">会员等级</label>
                <div class="col-sm-3">
                    <label class="radio-inline">
                        <input type="radio" name="ugrestrict" id="uGradeone" value="1"
                               <c:if test="${ugrestrict==1}">checked="checked"</c:if> class="insert-ugrade-radio"/>全部等级
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="ugrestrict" id="ugradetwo" value="2"
                               <c:if test="${ugrestrict==2}">checked="checked"</c:if> class="insert-ugrade-radio"/>部分等级
                    </label>
                </div>
            </div>

            <!--会员等级-->

            <div class="form-group" id="ugradesdiv">
                <label class="col-sm-3 control-label"></label>
                <label class="radio-inline">
                    <input type="checkbox" name="ugrades" id="selectAll"/>全选
                </label>
                <div class="col-sm-6">
                    <c:if test="${!empty uGrades}">
                        <c:forEach items="${uGrades}" var="ade" varStatus="status">
                            <label class="checkbox-inline" style="width:110px;">
                                <input type="checkbox" name="ugrestricts" id="ugrades"
                                       value="${ade.ugrade}"/>${ade.ugradename}
                            </label>
                            <c:if test="${status.count%4==0}"><br></c:if>
                        </c:forEach>
                    </c:if>
                </div>
                <script>
                    var ugrestrict1 = eval("(" + '${ugrestrict1}' + ")");
                    if (ugrestrict1.length > 0) {
                        $.each(ugrestrict1, function (index, value) {
                            $("input[type=checkbox]").each(function () {
                                if ($(this).val() == value) {
                                    $(this).attr("checked", true);
                                }
                            });
                        });
                    }


                </script>
            </div>
        </div>

        <!-- 定向名单引用 -->

        <div id="snlid">
            <div class="form-group">
                <label class="col-sm-3 control-label">定向名单列表</label>
                <div class="col-sm-3">
                    <select class="form-control" name="dingxiang" id="dingxiang" onchange="snlidChange(this);">
                        <option value="">请选择名单</option>
                        <c:if test="${!empty snl2}">
                            <c:forEach items="${snl2}" var="sn">
                                <option value="${sn.id},${sn.businessNo}"
                                        <c:if test="${tenderItem.snlid == sn.id&&tenderItem.snlno == sn.businessNo}">selected</c:if>> ${sn.businessName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <input type="hidden" name="snlid" id="snid"/>
                    <input type="hidden" name="snlno" id="snlno"/>
                </div>
            </div>
        </div>

        <script>
            function snlidChange(t) {
                var val = $(t).val();
                var arr = val.split(",");
                $("#snid").val(arr[0]);
                $("#snlno").val(arr[1]);
            }
        </script>
        <!--单人投标次数限制-->
        <div class="form-group">
            <label for="onettimes" class="col-sm-3 control-label">单人投标次数限制</label>
            <div class="col-sm-3">
                <input type="text" name="onettimes" class="form-control" id="onettimes" value="${tenderItem.onettimes}"
                       placeholder="单人投标次数限制">
            </div>
        </div>
        <!--单笔允许投资金额最低与最高-->
        <div class="form-group">
            <label for="minoncetamount" class="col-sm-3 control-label">单笔投资金额范围</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="text" name="minoncetamount" value="${tenderItem.minoncetamount}" class="form-control"
                           id="minoncetamount"
                           placeholder="最低">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="text" name="maxoncetamount" value="${tenderItem.maxoncetamount}" class="form-control"
                           id="maxoncetamount"
                           placeholder="最高">
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="inputismultiple" class="col-sm-3 control-label">是否为起投金额整数倍</label>
            <div class="col-sm-3">
                <select class="form-control" name="ismultiple" id="inputismultiple">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${tenderItem.ismultiple==1}">selected</c:if>>是</option>
                    <option value="0" <c:if test="${tenderItem.ismultiple==0}">selected</c:if>>否</option>
                </select>
            </div>
        </div>

        <%-- <div class="form-group">
             <label for="isfaketender" class="col-sm-3 control-label">是否可以假投标</label>
             <div class="col-sm-3">
                 <select id="isfaketender" class="form-control" name="isfaketender">
                     <option value="">请选择</option>
                     <option value="1">是</option>
                     <option value="0">否</option>
                 </select>
             </div>
         </div>--%>
        <%--默认不可以假投标--%>
        <input type="hidden" name="isfaketender" value="0"/>

        <%-- <div class="form-group">
             <label for="isautotender" class="col-sm-3 control-label">是否可以自动投标</label>
             <div class="col-sm-3">
                 <select name="isautotender" id="isautotender" class="form-control">
                     <option value="">请选择</option>
                     <option value="1">是</option>
                     <option value="0">否</option>
                 </select>
             </div>
         </div>--%>
        <%--默认不可以自动投标--%>
        <input type="hidden" name="isautotender" value="0">

        <%-- <div id="isacancel_div">
             <!--投标是否允许撤回-->
             <div class="form-group" id="isacanceltwo_div">
                 <label for="inputisacancel" class="col-sm-3 control-label">投标是否允许撤回</label>
                 <div class="col-sm-3">
                     <select class="form-control" id="inputisacancel" name="isacancel">
                         <option value="">请选择</option>
                         <option value="1">是</option>
                         <option value="0">否</option>
                     </select>
                 </div>
             </div>
             <div id="allowcugrade_div">
                 <!--允许撤回的会员等级-->
                 <div class="form-group">
                     <label class="col-sm-3 control-label">允许撤回的会员等级</label>
                     <div class="col-sm-5">
                         <label class="radio-inline">
                             <input type="radio" name="allowcugrade" id="allowcugradeone" value="1"
                                    class="radio_allowcugrade"/>全部等级
                         </label>
                         <label class="radio-inline">
                             <input type="radio" name="allowcugrade" id="allowcugradetwo" value="2"
                                    class="radio_allowcugrade"/>选择等级
                         </label>
                     </div>
                 </div>
                 <!--会员等级-->
                 <div class="form-group" id="crestrict_div">
                     <label class="col-sm-3 control-label"></label>
                     <div class="col-sm-6">
                         <c:if test="${!empty uGrades }">
                             <c:forEach items="${uGrades }" var="ugr" varStatus="status">
                                 <label class="checkbox-inline" style="width:80px;">
                                     <input type="checkbox" name="allowcugrades" value="${ugr.ugrade}"/>${ugr.ugradename}
                                 </label>
                                 <c:if test="${status.count%4==0 }">
                                     <br/>
                                 </c:if>
                             </c:forEach>
                         </c:if>
                     </div>
                 </div>
             </div>
         </div>--%>
        <!--投标默认不允许撤回-->
        <input type="hidden" name="isacancel" value="0">

        <!--投标来源设置-->
        <div class="form-group">
            <label class="col-sm-3 control-label">投标来源设置</label>
            <div class="col-sm-4">
                <label class="checkbox-inline">
                    <input type="checkbox" name="crestrict" value="d"/>pc
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="crestrict" value="a"/>安卓
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="crestrict" value="w"/>微信
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="crestrict" value="p"/>苹果
                </label>
                <label class="checkbox-inline">
                    <input type="checkbox" name="crestrict" value="o"/>wap
                </label>
            </div>
        </div>
        <script>
            var crestrict = '${tenderItem.crestrict}';
            var crestrictarr = crestrict.split(',');
            if (crestrictarr.length > 0) {
                $.each(crestrictarr, function (index, value) {
                    $("input[name='crestrict']").each(function () {
                        if ($(this).val() == value) {
                            $(this).attr("checked", true);
                        }
                    });
                });
            }


        </script>

        <div class="form-group">
            <label class="col-sm-3 control-label">计息方式</label>
            <div class="col-sm-3">
                <select name="repaymentpro" id="repaymentpro" class="form-control">
                    <c:if test="${!empty repaymentpromaps }">
                        <c:forEach items="${repaymentpromaps}" var="pt">
                            <c:choose>
                                <c:when test="${tenderItem.repaymentpro==pt.key }">
                                    <option value="${pt.key}" selected="selected">${pt.value}</option>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <!--还款方式-->
        <div class="form-group">
            <label for="repaysetmode" class="col-sm-3 control-label">自动还款设置</label>
            <div class="col-sm-3">
                <select name="repaysetmode" id="repaysetmode" class="form-control" onchange="changeRepaysetmode(this);">
                    <option value="1"
                            <c:if test="${tenderItem.repaysetmode==1}">selected</c:if> >允许
                    </option>
                    <option value="0"
                            <c:if test="${tenderItem.repaysetmode==0}">selected</c:if> >不允许
                    </option>
                </select>
            </div>
        </div>

        <div class="form-group" id="repaytimepointDiv">
            <label for="inputrepaytimepoint" class="col-sm-3 control-label">还款时间点</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="repaytimepoint" id="inputrepaytimepoint"
                       value="${tenderItem.repaytimepoint}"
                       placeholder="还款时间点" onclick="WdatePicker({dateFmt:'HH:mm:ss'})"/>
                <span id="inputrepaytimepointSpan" style="color: red;"></span>
            </div>
        </div>

        <!--还款类型改为还款审核<-->
        <div class="form-group">
            <label for="repaymenttype1" class="col-sm-3 control-label">还款审核</label>
            <div class="col-sm-3">
                <select class="form-control" name="repaymenttype" id="repaymenttype1">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${tenderItem.repaymenttype==1}">selected</c:if>>不审核</option>
                    <option value="2" <c:if test="${tenderItem.repaymenttype==2}">selected</c:if>>审核</option>
                </select>
            </div>
        </div>

        <%--默认是及时还款--%>
        <%-- <input type="hidden" name="repaymenttype" value="1"/>--%>

        <div class="form-group">
            <label for="inputrepayman" class="col-sm-3 control-label">借款人</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="" id="inputrepayman"
                       value="${tenderItem.repayman} - ${tenderItem.loginname}" readonly/>
                <input type="hidden" name="repayman" value="${tenderItem.repayman}"/>
            </div>
        </div>

        <div class="form-group">
            <label for="isapartrepay" class="col-sm-3 control-label">是否支持部分还款</label>
            <div class="col-sm-3">
                <select name="isapartrepay" id="isapartrepay" class="form-control">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${tenderItem.isapartrepay==1}">selected</c:if>>是</option>
                    <option value="0" <c:if test="${tenderItem.isapartrepay==0}">selected</c:if>>否</option>
                </select>
            </div>
        </div>
        <%--默认支持部分还款--%>
        <%-- <input type="hidden" name="isapartrepay" value="1"/>--%>
        <div class="form-group">
            <label class="col-sm-3 control-label">担保人设置</label>
            <c:set value="${tenderItem.compensatoryman},${tenderItem.compensatorymaid}" var="ompensatory"/>
            <div class="col-sm-3 ">
                <select name="danbaoren" id="danbaoren" class="form-control" onchange="guaranteeChange(this);">
                    <option value="">请选择</option>
                    <c:forEach var="c" items="${compensatorymans}">
                        <option value="${c.key}"
                                <c:if test="${ompensatory==c.key}">selected</c:if> >${c.value}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group" id="toryman_div">
            <label for="compensatoryman" class="col-sm-3 control-label">代偿人</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="compensatoryman" id="compensatoryman"
                       value="" readonly
                       placeholder="代偿人"/>
                <input type="hidden" name="compensatorymaid" id="compensatorymaid" value=""/>
            </div>
        </div>


        <%--<div class="form-group">
            <label for="iscompensatory" class="col-sm-3 control-label">正常还款代偿开关</label>
            <div class="col-sm-3">
                <select name="iscompensatory" id="iscompensatory" class="form-control">
                    <option value="">请选择</option>
                    <option value="1">开</option>
                    <option value="0">关</option>
                </select>
            </div>
        </div>--%>
        <%--正常还款代偿开关默认为开--%>
        <input type="hidden" value="1" name="iscompensatory"/>

        <!--提前还款代偿开关-->
        <%--<div class="form-group">
            <label class="col-sm-3 control-label">提前还款代偿开关</label>
            <div class="col-sm-3">
                <select name="isaheadcompensatory" id="IsAheadCompensatory" class="form-control">
                    <option value="">请选择</option>
                    <option value="1">开</option>
                    <option value="0">关</option>
                </select>
            </div>
        </div>--%>
        <%--提前还款代偿开关，默认为关--%>
        <input type="hidden" value="0" name="isaheadcompensatory"/>

        <%--<div class="form-group">
            <label for="isaoverduec" class="col-sm-3 control-label">逾期代偿开关</label>
            <div class="col-sm-3">
                <select name="isaoverduec" id="isaoverduec" class="form-control">
                    <option value="0">请选择</option>
                    <option value="1">开</option>
                    <option value="0">关</option>
                </select>
            </div>
        </div>--%>
        <%--逾期代偿开关，默认为关--%>
        <input type="hidden" value="0" name="isaoverduec"/>

        <!--逾期宽限期(还款日第二天算起)-->
        <div class="form-group">
            <label for="gracePeriod" class="col-sm-3 control-label">逾期宽限期</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="text" class="form-control" name="graceperiod" id="gracePeriod"
                           value="${tenderItem.graceperiod}"
                           placeholder="正常还款日往后宽限天数"/>
                    <span class="input-group-addon">天</span>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="AheadPeriod" class="col-sm-3 control-label">还款宽限期</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="text" class="form-control" name="aheadperiod" id="AheadPeriod"
                           value="${tenderItem.aheadperiod}"
                           placeholder="正常还款日往前宽限天数"/>
                    <span class="input-group-addon">天</span>
                </div>
            </div>
        </div>

        <!--逾期滞纳金计算截止天数(逾期滞纳金计算截止天数)-->
        <div class="form-group">
            <label for="OCMAXDAY" class="col-sm-3 control-label">逾期滞纳金截止天数</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="text" class="form-control" name="ocmaxday" id="OCMAXDAY" value="${tenderItem.ocmaxday}"
                           placeholder="逾期滞纳金计算截止天数"/>
                    <span class="input-group-addon">天</span>
                </div>
            </div>
        </div>


        <%-- <!--是否设置站岗利息-->
         <div class="form-group">
             <label for="issetgfundsint" class="col-sm-3 control-label">是否设置站岗利息</label>
             <div class="col-sm-3">
                 <select name="issetgfundsint" id="issetgfundsint" class="form-control">
                     <option value="0">请选择</option>
                     <option value="1">设置</option>
                     <option value="0">不设置</option>
                 </select>
             </div>

         </div>--%>
        <!--是否设置站岗利息,默认不设置-->
        <input type="hidden" name="issetgfundsint" value="0"/>
        <%--
        <!-- 是否设置流标补偿       -->
        <div class="form-group">
            <label for="isIntCompensateOn" class="col-sm-3 control-label">是否设置流标补偿 </label>
            <div class="col-sm-3">
                <select name="isfailtc" id="isfailTc" class="form-control">
                    <option value="0">请选择</option>
                    <option value="1">开</option>
                    <option value="0">关</option>
                </select>
            </div>
        </div>
        <div id="isOpen">
            <!-- 流标利息方式补偿开关 -->
            <div class="form-group">
                <label for="isIntCompensateOn" class="col-sm-3 control-label">流标利息方式补偿开关</label>
                <div class="col-sm-3">
                    <select name="isintcompensateon" id="isIntCompensateOn" class="form-control">
                        <option value="0">请选择</option>
                        <option value="1">开</option>
                        <option value="0">关</option>
                    </select>
                </div>
            </div>
            <!-- 流标奖品补偿方式开关 -->
            <div class="form-group">
                <label for="isAwardCompensateOn" class="col-sm-3 control-label">流标奖品补偿方式开关</label>
                <div class="col-sm-3">
                    <select name="isawardcompensateon" id="isAwardCompensateOn" class="form-control">
                        <option value="0">请选择</option>
                        <option value="1">开</option>
                        <option value="0">关</option>
                    </select>
                </div>
            </div>
        </div>
        --%>

        <%--是否设置流标补偿,默认为关--%>
        <input type="hidden" name="isfailtc" value="0"/>
        <%--流标利息方式补偿开关,默认为关--%>
        <input type="hidden" name="isintcompensateon" value="0"/>
        <%--流标奖品补偿方式开关,默认为关--%>
        <input type="hidden" name="isawardcompensateon" value="0"/>

        <%--
         <div class="form-group">
             <label for="IsAOCFee" class="col-sm-3 control-label">收取平台追偿费开关</label>
             <div class="col-sm-3">
                 <select name="isaocfee" id="IsAOCFee" class="form-control">
                     <option value="0">请选择</option>
                     <option value="1">开</option>
                     <option value="0">关</option>
                 </select>
             </div>
         </div>
         --%>

        <%--收取平台追偿费开关,默认为关--%>
        <input type="hidden" name="isaocfee" value="0"/>

        <!--是否允许提前还款-->
        <div class="form-group">
            <label for="isaaheadrepay" class="col-sm-3 control-label">设置提前还款</label>
            <div class="col-sm-3">
                <select name="isaaheadrepay" id="isaaheadrepay" onchange="aheadRepay(this)" class="form-control">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${tenderItem.isaaheadrepay==1}">selected</c:if>>设置</option>
                    <option value="0" <c:if test="${tenderItem.isaaheadrepay==0}">selected</c:if>>不设置</option>
                </select>
            </div>
        </div>

        <div id="aheadSetDiv" style="background-color: #58ede0">
            <input name="repaytype" value="${tenderItem.repaymentpro}" type="hidden"/>
            <!--提前还款类型-->
            <div class="form-group" id="ARepayModeone">
                <label class="control-label col-sm-3">提前还款类型</label>
                <div class="col-sm-3">
                    <c:choose>
                        <%--一次还本付息--%>
                        <c:when test="${tenderItem.repaymentpro eq 1}">
                            <select name="arepaymode" class="form-control" id="ARepayMode">
                                <option value="1"
                                        <c:if test="${tenderItem.aheadRepayMode.arepaymode==1}">selected</c:if>>全部提前
                                </option>
                            </select>
                        </c:when>

                        <c:otherwise>
                            <select name="arepaymode" class="form-control" id="ARepayMode">
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${tenderItem.aheadRepayMode.arepaymode==1}">selected</c:if>>全部提前
                                </option>
                                <option value="2"
                                        <c:if test="${tenderItem.aheadRepayMode.arepaymode==2}">selected</c:if>>部分提前
                                </option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>


            <!--提前期数:部分提前时-->
            <div id="APeriods_div">
                <div class="form-group">
                    <label class="col-sm-3 control-label">提前期数</label>
                    <div class="col-sm-3">
                        <select name="aperiods" class="form-control" id="APeriods">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${tenderItem.aheadRepayMode.aperiods==1}">selected</c:if>>
                                当期提前
                            </option>

                            <option value="2" <c:if test="${tenderItem.aheadRepayMode.aperiods > 1}">selected</c:if>>
                                多期提前
                            </option>
                        </select>
                    </div>
                </div>
                <div class="form-group" id="APeriodsqi">
                    <label class="col-sm-3 control-label">具体期数</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="aperiodqi" id="aperiodqi" class="form-control"
                                   value="${tenderItem.aheadRepayMode.aperiods}"/>
                            <span class="input-group-addon">期</span>
                        </div>
                    </div>
                </div>
            </div>

            <!--利息设置:全部提前时-->
            <div class="form-group" id="IntMode">
                <label class="control-label col-sm-3">利息设置</label>
                <div class="col-sm-3">
                    <select name="intmode" class="form-control" id="intmode1">
                        <option value="">请选择</option>
                        <option value="1" <c:if test="${tenderItem.aheadRepayMode.intmode==1}">selected</c:if>>占天计息</option>
                        <option value="2" <c:if test="${tenderItem.aheadRepayMode.intmode==2}">selected</c:if>>全额利息</option>
                    </select>
                </div>
            </div>
            <!--利息设置:部分提前 多期提前-->
            <div class="form-group" id="IntModeonequanx">
                <label class="control-label col-sm-3">利息设置</label>
                <div class="col-sm-3">
                    <select name="intmodequane" class="form-control" id="intmodeonequan">
                        <option value="2" <c:if test="${tenderItem.aheadRepayMode.intmodequane==2}">selected</c:if>>全额利息
                        </option>
                    </select>
                </div>
            </div>
            <!--利息设置:部分提前 当期提前-->
            <div class="form-group" id="IntModezhant">
                <label class="control-label col-sm-3">利息设置</label>
                <div class="col-sm-3">
                    <select name="intmodetian" id="intmodetian" class="form-control">
                        <option value="1" <c:if test="${tenderItem.aheadRepayMode.intmodetian==1}">selected</c:if>>占天计息</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-3">备注</label>
                <div class="col-sm-3">
                    <textarea rows="3" name="remark" class="form-control">${tenderItem.aheadRepayMode.remark}</textarea>
                </div>
            </div>

        </div>

        <%-- <div class="form-group">
             <label class="col-sm-3 control-label">提前还款本金利息补偿设置</label>
             <div class="col-sm-3">
                 <select name="ispicompensateon" id="isPICompensateOn" class="form-control">
                     <option value="0">请选择</option>
                     <option value="1">设置</option>
                     <option value="0">不设置</option>
                 </select>
             </div>
         </div>--%>
        <%--提前还款本金利息补偿设置默认为关--%>
        <input type="hidden" name="ispicompensateon" value="0"/>

        <%--<div class="form-group">
            <label class="col-sm-3 control-label">提前还款增益利息补偿设置</label>
            <div class="col-sm-3">
                <select name="ispluscompensateon" id="isPlusCompensateOn" class="form-control">
                    <option value="0">请选择</option>
                    <option value="1">设置</option>
                    <option value="0">不设置</option>
                </select>
            </div>
        </div>--%>

        <%--提前还款增益利息补偿设置默认为关--%>
        <input type="hidden" name="ispluscompensateon" value="0"/>

        <%-- <div class="form-group">
             <label class="col-sm-3 control-label">提前还款还款人补偿平台设置</label>
             <div class="col-sm-3">
                 <select name="isforplatformon" id="isForPlatformOn" class="form-control">
                     <option value="0">请选择</option>
                     <option value="1">设置</option>
                     <option value="0">不设置</option>
                 </select>
             </div>
         </div>--%>

        <%--提前还款还款人补偿平台设置默认关--%>
        <input type="hidden" name="isforplatformon" value="0"/>

        <%--
        <!--是否允许债权转让-->
        <div class="form-group">
            <label for="isadebtattorn" class="col-sm-3 control-label">是否允许债权转让</label>
            <div class="col-sm-3">
                <select name="isadebtattorn" id="isadebtattorn" class="form-control">
                    <option value="0">请选择</option>
                    <option value="1">允许</option>
                    <option value="0">不允许</option>
                </select>
            </div>
        </div>
        <!-- 是否收取债权手续费 -->
        <div class="form-group">
            <label class="col-sm-3 control-label" for="isAdebtAttornFee">是否收取债权手续费</label>
            <div class="col-sm-3">
                <select name="isadebtattornfee" id="isAdebtAttornFee" class="form-control">
                    <option value="0">请选择</option>
                    <option value="1">开</option>
                    <option value="0">关</option>
                </select>
            </div>
        </div>--%>

        <%--是否允许债权转让,默认为关--%>
        <input type="hidden" name="isadebtattorn" value="0"/>
        <%--是否收取债权手续费,默认为关--%>
        <input type="hidden" name="isadebtattornfee" value="0"/>

        <%--
        <!--是否允许使用增益-->
        <div class="form-group">
            <label for="isaplus" class="col-sm-3 control-label">是否允许使用增益</label>
            <div class="col-sm-3">
                <select name="isaplus" id="isaplus" class="form-control">
                    <option value="0">请选择</option>
                    <option value="1">允许</option>
                    <option value="0">不允许</option>
                </select>
            </div>
        </div>
        --%>
        <!--是否允许使用增益,默认为不允许-->
        <input type="hidden" name="isaplus" value="0"/>

        <!--是否设置居间费-->
        <div class="form-group">
            <label for="isamediacy" class="col-sm-3 control-label">设置居间服务费</label>
            <div class="col-sm-3">
                <select name="isamediacy" id="isamediacy" class="form-control">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${tenderItem.isamediacy==1}">selected</c:if>>设置</option>
                    <option value="0" <c:if test="${tenderItem.isamediacy==0}">selected</c:if>>不设置</option>
                </select>
            </div>
        </div>

        <!--是否设置担保-->
        <div class="form-group">
            <label for="isaguarantee" class="col-sm-3 control-label">设置担保费</label>
            <div class="col-sm-3">
                <select name="isaguarantee" id="isaguarantee" class="form-control">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${tenderItem.isaguarantee==1}">selected</c:if>>设置</option>
                    <option value="0" <c:if test="${tenderItem.isaguarantee==0}">selected</c:if>>不设置</option>
                </select>
            </div>
        </div>
        <!--是否设置利息管理费-->
        <div class="form-group">
            <label for="isaintexp" class="col-sm-3 control-label">设置利息管理费</label>
            <div class="col-sm-3">
                <select name="isaintexp" id="isaintexp" class="form-control">
                    <option value="">请选择</option>
                    <option value="1" <c:if test="${tenderItem.isaintexp==1}">selected</c:if>>设置</option>
                    <option value="0" <c:if test="${tenderItem.isaintexp==0}">selected</c:if>>不设置</option>
                </select>
            </div>
        </div>
        <%-- <!--是否设置风险保证金-->
         <div class="form-group">
             <label for="isariskgm" class="col-sm-3 control-label">是否设置风险保证金</label>
             <div class="col-sm-3">
                 <select name="isariskgm" id="isariskgm" class="form-control">
                     <option value="0">请选择</option>
                     <option value="1">设置</option>
                     <option value="0">不设置</option>
                 </select>
             </div>
         </div>--%>

        <%--是否设置风险保证金,,默认为不设置--%>
        <input type="hidden" name="isariskgm" value="0"/>

        <%--<!--是否审核-->
        <div class="form-group">
            <label for="isaudit" class="col-sm-3 control-label">是否审核</label>
            <div class="col-sm-3">
                <select name="isaudit" id="isaudit" class="form-control">
                    &lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;
                    <option value="1" selected>必须审核</option>
                    &lt;%&ndash;<option value="0">不用审核</option>&ndash;%&gt;
                </select>
            </div>
        </div>--%>

        <%--默认为审核--%>
        <input type="hidden" name="isaudit" value="1"/>
        <!--标的描述-->
        <div class="form-group">
            <label class="col-sm-3 control-label">标的描述</label>
            <div class="col-sm-3">
                <textarea rows="3" class="form-control" name="tdesc" id="tdesc">${tenderItem.tdesc}</textarea>
            </div>
        </div>
        <%--<!--是否为模板-->
        <div class="form-group">
            <label for="istemplet" class="col-sm-3 control-label">是否为模板</label>
            <div class="col-sm-3">
                <select name="istemplet" id="istemplet" class="form-control">
                    <option value="0">请选择</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </div>
        </div>--%>

        <%--是否为模板,默认为否--%>
        <input type="hidden" name="istemplet" value="0"/>
        <input type="hidden" name="loanappid" value="${tenderItem.loanappid}"/>
        <input type="hidden" name="tpro" value="${tenderItem.tpro}"/>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-1">
                <%--<button type="submit" class="btn btn-primary" id="baocun">保存</button>--%>
                <a type="button" id="returnBack" class="btn btn-default" onclick="severCheck();">保存</a>

            </div>
            <div class="col-sm-1">
                <button type="button" class="btn btn-success" id="gobackList" onclick="javascript:;history.go(-1)">返回列表
                </button>
            </div>

        </div>
    </form>

</div>
<script type="text/javascript">

    //是否设置提前还款onchange事件
    function aheadRepay(t) {
        var value = $(t).val();
        if (value == 1) {
            $("#aheadSetDiv").show();
            $("#IntMode").show();
            $("#ARepayMode").val("");

        } else if (value == 0) {
            $("#ARepayMode").val("");
            $("#APeriods").val("");
            $("#aperiodqi").val("");
            $("#intmode1").val("");
            $("#intmodeonequan").val("");
            $("#intmodetian").val("");
            $("#remark").val("");
            $("#APeriods_div").hide();
            $("#IntMode").hide();
            $("#IntModeonequanx").hide();
            $("#IntModezhant").hide();
            $("#aheadSetDiv").hide();
        }
    }

    /*******************************************************************/

    /*$(function () {
     /!*$("#crestrict_div").hide();
     $("#ugrestrictone").attr("checked", "checked");//全部等級
     $("#allowcugradeone").attr("checked", "checked");//全部等级
     $("#ugrestrict_div").hide();//允許投標等級
     $("#allowcugrade_div").hide();//允許撤回等級allowcugrade_div
     $("#inputvaluedategrop").hide();//标的起息日\*!/

     //是否约标
     /!* var isappointtender = $("#isappointtender").val();
     if (isappointtender == 1) {
     $("#tpass_div").css("display", "block");
     } else {
     $("#tpass_div").css("display", "none");
     }*!/

     //投标是否允许撤回change事件
     /!*$("#inputisacancel").change(function () {
     var $acance = $(this).val();
     if ($acance == 1) {//允许撤回
     $("#allowcugrade_div").show();
     $(".radio_allowcugrade").change(function () {//选择全部等级或部分等级
     var $ugrade = $(".radio_allowcugrade:checked").val();
     if ($ugrade == 1) {
     $("#crestrict_div").hide();
     $("#crestrict_div input[type='checkbox']").each(function () {
     this.checked = false;
     });
     } else {
     $("#crestrict_div").show();
     }
     });
     } else if ($acance == 0) {//不允许撤回
     $("#allowcugrade_div").hide();
     } else {
     $("#allowcugrade_div").hide();
     }
     });*!/


     });*/

    //初始化部分内容 及 提前还款设置
    $(function () {
        var result = "${result}";
        if (result != null && result != '') {
            alert("保存失败");
        }
        //标利息
        $("#percent").html($("#tinterest").val() + "%");

        //起息日时间点
        $("#valuePoint").hide();
        var valueRule = $("#valueRule").val();
        if (valueRule == 3) {
            $("#valuePoint").show();
        }
        //还款时间点
        $("#repaytimepointDiv").hide();
        var repaysetmode = $("#repaysetmode").val();
        if (repaysetmode == 1) {
            $("#repaytimepointDiv").show();
        }

        $("#aheadSetDiv").hide();
        $("#APeriods_div").hide();//提前期数设置
        $("#APeriodsqi").hide();//期数

        $("#IntMode").hide();//利息设置:全部提前时:全额计息
        $("#IntModeonequanx").hide();//利息设置:部分提前 多期提前 全额计息
        $("#IntModezhant").hide();//利息设置:部分提前 当期提前 占天计息

        //提前还款设置
        $("#aheadSetDiv").hide();
        var isaaheadrepay = $("#isaaheadrepay").val();
        //提前还款类型：全部提前，部分提前
        var arepaymode = '${tenderItem.aheadRepayMode.arepaymode}';
        //当期或多期:1为当期   >1为多期
        var aperiods = '${tenderItem.aheadRepayMode.aperiods}';
        //计息方式
        var repaymentpro='${tenderItem.repaymentpro}';
        if (isaaheadrepay == 1) {
            $("#aheadSetDiv").show();
            //部分提前
            if (arepaymode == 2) {
                //先息后本
                if(repaymentpro==4){
                    $("#APeriods_div").show();
                    //多期提前
                    if(aperiods>1){
                        //具体期数
                        $("#APeriodsqi").show();
                        //利息设置
                        $("#IntModeonequanx").show();
                    }
                    //当期
                    if(aperiods==1){
                        //利息设置
                        $("#IntModeonequanx").show();
                    }
                }else{
                    $("#APeriods_div").show();
                    //多期提前
                    if(aperiods>1){
                        //具体期数
                        $("#APeriodsqi").show();
                        //利息设置
                        $("#IntModeonequanx").show();
                    }
                    //当期
                    if(aperiods==1){
                        //利息设置
                        $("#IntModezhant").show();
                    }
                }
            }
            //全部提前
            if (arepaymode == 1) {
                $("#IntMode").show();
            }
        }


        //全部提前/部分提前onchange事件
        $("#ARepayMode").change(function () {
            if ($(this).val() == 2) {//部分提前
                $("#APeriods_div").show();//提前期数设置
                $("#intmode1").val("");
                $("#IntMode").hide();//利息设置:全部提前时:全额计息
            } else if ($(this).val() == 1) {//全部提前
                $("#intmode1").val("");
                $("#IntMode").show();//利息设置:全部提前时:全额计息 占天计息
                $("#IntModeonequanx").hide();//利息设置:部分提前 多期提前 全额计息
                $("#IntModezhant").hide();//利息设置:部分提前 当期提前 占天计息
                $("#APeriodsqi").hide();
                $("#APeriods_div").hide();
                $("#APeriods_div select").val("");
                $("#APeriodsqi input").val("");
            } else {
                $("#APeriods_div").hide();
                $("#IntMode").hide();
                $("#IntModeonequanx").hide();
                $("#IntModezhant").hide();
            }
        });

        //当期提前/多期提前onchange事件

        $("#APeriods").change(function () {
            //先息后本时，无论是当期提前还是多期提前都只能是全额计息
            if(repaymentpro==4){
                if ($(this).val()==2) {//多期提前
                    $("#aperiodqi").val("");
                    $("#APeriodsqi").show();//期数
                    $("#IntModeonequanx").show();//全额利息
                } else if ($(this).val() == 1) {//当期提前
                    $("#aperiodqi").val("");
                    $("#APeriodsqi").hide();//期数
                    $("#IntModeonequanx").show();//全额利息
                }else {//请选择
                    $("#IntModeonequanx").hide();//全额
                    $("#IntModezhant").hide();//占天
                    $("#APeriodsqi").hide();
                    $("#APeriodsqi input").val("");
                }
            }else{
                if ($(this).val()==2) {//多期提前
                    $("#IntModezhant").hide();//占天计息
                    $("#APeriodsqi").show();
                    $("#aperiodqi").val("");
                    $("#IntModeonequanx").show();//全额利息
                } else if ($(this).val() == 1) {//当期提前
                    $("#APeriodsqi").hide();
                    $("#IntModeonequanx").hide();
                    $("#APeriodsqi input").val("");
                    $("#intmodetian").val("1");
                    $("#IntModezhant").show();//占天计息
                }else {//请选择
                    $("#IntModeonequanx").hide();//全额
                    $("#IntModezhant").hide();//占天
                    $("#APeriodsqi").hide();
                    $("#APeriodsqi input").val("");
                }
            }

        });
    });

    //自动还款设置onchange事件
    function changeRepaysetmode(t) {
        var value = $(t).val();
        if (value == 1) {
            $("#repaytimepointDiv").show();
        } else if (value == 0) {
            $("#inputrepaytimepoint").val("");
            $("#repaytimepointDiv").hide();
        }
    }

    //投标人设置:按会员等级设置的初始化事件
    $(function () {
        //会员等级
        $("#uGrade").hide();
        //定向名单引用
        $("#snlid").hide();
        var investerrange = $("#investerrange").val();
        if (investerrange == 3) {
            $("#uGrade").show();
        } else if (investerrange == 4) {
            $("#snlid").show();
            var val = $("#dingxiang").val();
            var arr = val.split(",");
            $("#snid").val(arr[0]);
            $("#snlno").val(arr[1]);
        }
        //担保人设置
        var value = $("#danbaoren").val();
        if (value != "") {
            var valArr = value.split(",");
            var usrcustid = valArr[0];
            var baseid = valArr[1];
            $("#compensatoryman").val(usrcustid);
            $("#compensatorymaid").val(baseid);
        }

        //全部等级与选择等级的change事件
        $(".insert-ugrade-radio").change(function () {
            var $radioVal = $(".insert-ugrade-radio:checked").val();
            if ($radioVal == 1) {
                $("#ugradesdiv").hide();
                $("#ugradesdiv :checkbox").each(function () {
                    this.checked = false;
                });
            } else {
                $("#ugradesdiv").show();
            }
        });

        $("#selectAll").click(function () {
            if (this.checked) {
                $("#ugradesdiv  :checkbox").prop("checked", true);
            } else {
                $("#ugradesdiv  :checkbox").prop("checked", false);
            }
        });

    });

    //投标人设置change事件
    function investerChange(t) {
        var value = $(t).val();
        //按会员等级设置
        if (value == 3) {
            $("#uGradeone").val("1");
            $("#ugradetwo").val("2");
            $("#snlid").hide();
            $("#snid").val("");
            $("#snlno").val("");
            $("#uGrade").show();
            //按定向名单设置
        } else if (value == 4) {
            $("#uGrade").hide();
            $("#uGradeone").val("");
            $("#ugradetwo").val("");
            $("#uGrade :checkbox").each(function () {
                this.checked = false;
            });
            $("#snlid").show();
        } else {
            $("#snid").val("");
            $("#snlno").val("");
            $("#uGradeone").val("");
            $("#ugradetwo").val("");
            $("#uGrade :checkbox").each(function () {
                this.checked = false;
            });
            $("#uGrade").hide();
            $("#snlid").hide();
        }
    }

    //标的结束时间应该大于标的开始时间 失去焦点事件
    function tendtimeOnblur() {
        var tbeginttime = $("#inputtbegintime").val();
        console.info(tbeginttime);
        var tendtime = $(this).val();
        console.info(tendtime);
        if (tbeginttime != "") {
            if (tendtime != "") {
                if (tendtime < tbeginttime) {
                    $("#spana1").html("标的结束时间必须大于标的开始时间");
                    $(this).val("");
                } else {
                    $("#spana1").html("");
                }
            } else {
                $("#spana1").html("请填写结束时间");
            }
        } else {
            $("#spana1").html(" 请先填写开始时间");
        }
    }

    //获取焦点事件
    function tendtimeOnfocus() {
        $("#spana1").html("");
    }

    //标收益的onkeyup事件
    function clearNoNum(obj) {
        //修复第一个字符是小数点 的情况.
        if (obj.value != '' && obj.value.substr(0, 1) == '.') {
            obj.value = "";
        }
        obj.value = obj.value.replace(/[^\d.]/g, "");  //清除“数字”和“.”以外的字符
        obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数
        if (obj.value.indexOf(".") < 0 && obj.value != "") {//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
            if (obj.value.substr(0, 1) == '0' && obj.value.length == 2) {
                obj.value = obj.value.substr(1, obj.value.length);
            }
        }
        $("#percent").html(obj.value + "%");
    }

    //起息规则onchange事件
    function selectTime(t) {
        var time = $(t).val();
        //结标时间点
        if (time == 3) {
            $("#valuePoint").css("display", "block")
        } else {
            $("#valuePoint :input[name='valuepoint']").val("");
            $("#valuePoint").css("display", "none")
        }
    }

    //担保人设置onchange事件
    function guaranteeChange(t) {
        var value = $(t).val();
        var valArr = value.split(",");
        var usrcustid = valArr[0];
        var baseid = valArr[1];
        $("#compensatoryman").val(usrcustid);
        $("#compensatorymaid").val(baseid);
    }

    function severCheck() {
        if (check()) {
            //定义规则
            $("#form1").bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    /*input状态样式图片*/
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    tinterest: {//标的收益
                        validators: {
                            notEmpty: {
                                message: '标的收益不能为空'
                            }
                        }
                    },
                    //起息日设置
                    valuerule: {
                        validators: {
                            notEmpty: {
                                message: '起息日设置不能为空'
                            }
                        }
                    },

                    //投标人设置
                    investerrange: {
                        validators: {
                            notEmpty: {
                                message: '投标人设置不能为空'
                            }
                        }
                    },
                    //投标人设置中的：会员等级
                    ugrestricts: {
                        validators: {
                            notEmpty: {
                                message: '最少选择一个'
                            }
                        }
                    },
                    //定向名单列表
                    dingxiang: {
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            }
                        }
                    },
                    //单人投标次数限制
                    onettimes: {
                        validators: {
                            notEmpty: {
                                message: '投标次数不能为空'
                            },
                            regexp: {
                                regexp: /^[0-9]*$/,
                                message: '单人投标次数必须为数字如：10'
                            },
                            stringLength: {
                                max: 2,
                                message: "单人投标次数长度不能超过2位数"
                            }
                        }
                    },
                    //单笔允许投资金额最低
                    minoncetamount: {
                        validators: {
                            notEmpty: {
                                message: '单笔允许投资最低金额不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '投资金额必须为浮点数字如：1000或1000.0 '
                            },
                        }
                    },
                    //单边投资金额最高
                    maxoncetamount: {
                        validators: {
                            notEmpty: {
                                message: '单笔投资金额最高不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '投资金额必须为浮点数如：1000或1000.0'
                            }
                        }
                    },
                    //是否为起投金额整数倍
                    ismultiple: {
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            }
                        }
                    },
                    //投标来源设置
                    crestrict: {
                        validators: {
                            notEmpty: {
                                message: '请至少选择一项'
                            }
                        }
                    },

                    //还款审核
                    repaymenttype: {
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            }
                        }
                    },
                    //是否支持部分还款
                    isapartrepay: {
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            }
                        }
                    },
                    //逾期宽限期
                    graceperiod: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            },
                            regexp: {
                                regexp: /^(0|[1-9]\d*)$/,
                                message: '天数只能为正整数'
                            }
                        }
                    },
                    //还款宽限期
                    aheadperiod: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            },
                            regexp: {
                                regexp: /^(0|[1-9]\d*)$/,
                                message: '天数只能为正整数'
                            }
                        }
                    },
                    //逾期滞纳金截止天数
                    ocmaxday: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            },
                            regexp: {
                                regexp: /^(0|[1-9]\d*)$/,
                                message: '天数只能为正整数'
                            }
                        }
                    },
                    //是否设置提前还款
                    isaaheadrepay: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //提前还款类型
                    arepaymode: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //提前期数(当期提前,多期提前)
                    aperiods: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //具体期数
                    aperiodqi: {
                        validators: {
                            regexp: {
                                regexp: /^([1-9]\d+)|([2-9])$/,
                                message: '天数只能为大于1的正整数'
                            },
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //利息设置
                    intmode: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //利息设置
                    intmodequane: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //利息设置
                    intmodetian: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //设置居间服务费
                    isamediacy: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //设置担保费
                    isaguarantee: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //是否设置利息管理费
                    isaintexp: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    //标的描述
                    tdesc: {
                        validators: {
                            notEmpty: {
                                message: '标的描述不能为空'
                            },
                        }
                    },
                }
            });
            var bv = $("#form1").data("bootstrapValidator");
            //手动调用验证
            bv.validate();
            if (bv.isValid()) {
                document.getElementById("form1").submit();
            }
        }
    }

    function check() {
        var tbeginttime = $("#inputtbegintime").val();
        if (tbeginttime == null || tbeginttime == "") {
            $("#tbegintimeSpan").html("不能为空");
            $("#inputtbegintime").focus();
            return false;
        } else {
            $("#tbegintimeSpan").html("");
        }

        var inputtendtime = $("#inputtendtime").val();
        if (inputtendtime == null || inputtendtime == "") {
            $("#tendtimeSpan").html("不能为空");
            $("#inputtendtime").focus();
            return false;
        } else {
            $("#tendtimeSpan").html("");
        }
        var inputrepaytimepoint = $("#inputrepaytimepoint").val();
        //如果是显示的就加验证
        if (!$("#inputrepaytimepoint").is(":hidden")) {
            if (inputrepaytimepoint == null || inputrepaytimepoint == "") {
                $("#inputrepaytimepointSpan").html("不能为空");
                $("#inputrepaytimepoint").focus();
                return false;
            } else {
                $("#inputrepaytimepointSpan").html("");
            }
        }
        
        return true;
    }


</script>
</body>
</html>