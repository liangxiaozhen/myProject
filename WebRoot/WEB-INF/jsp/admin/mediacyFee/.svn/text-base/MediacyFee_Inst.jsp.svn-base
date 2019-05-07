<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>MediacyFee_Inst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
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
    <script type="text/javascript">
        $(function () {
            $("#uGradeone").attr("checked", "checked"); //默认选中全部等级
            $("#ugradesdiv").hide(); //隐藏全部等级
            //全部等级与选择等级的change监听事件
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
            })

            $('#defaultForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    /*input状态样式图片*/
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: { //验证
                    iepercent: { //利息管理费
                        validators: {
                            notEmpty: {
                                message: '利息管理费不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            }
                        }
                    },
                    miniefee: { //最小金額
                        validators: {
                            notEmpty: {
                                message: '最低金额不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            }
                        }
                    },
                    maxiefee: {
                        validators: {
                            notEmpty: { //最高金额
                                message: '最高金额不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            },
                            callback: {
                                message: '高值不能低于低值',
                                callback: function (value, validator) {
                                    var minEFee = $("#minIEFee").val(),
                                        sum = parseFloat(minEFee);
                                    return value >= sum;
                                }
                            }
                        }
                    },
                    ugrades: {
                        validators: {
                            notEmpty: {
                                message: '请选择一个或全部选择'
                            }
                        }
                    }
                }
            });
        });
    </script>
</head>
<body>
<div class="route_bg">
    <a href="#">标管理</a><i class="glyphicon glyphicon-chevron-right"></i>
    <a href="#">标的居间费设置页面</a>
</div>
<div class="container" style="margin-top: 25px;">
    <form class="form-horizontal" role="form"
          action="${pageContext.request.contextPath}/admin/mediacyFee/insertMediacyFeetwo.action" id="defaultForm">
        <input type="hidden" name="tid" value="${tid}">
        <!--居间服务费收款人-->
        <div class="form-group">
            <label for="mRecMan" class="col-sm-3 control-label laber_from">居间服务费收款人</label>
            <div class="col-sm-3">
                <input type="text" name="mrecman" id="mRecMan" placeholder="居间服务费收款人" class="form-control"
                       value="MDT000001" readonly/>
            </div>
        </div>


        <input type="hidden" name="mrecmanid" id="mRecManId" value="0"/>
        <!--收费方式-->
        <div class="form-group">
            <label class="col-sm-3 control-label laber_from" for="chargeType">收费类型</label>
            <div class="col-sm-3">
                <select name="chargetype" id="chargeType" class="form-control">
                    <option value="2">投标时收取</option>
                </select>
            </div>
        </div>
        <!--会员等级-->

        <div class="form-group">
            <label class="col-sm-3 control-label laber_from" for="uGrade">会员等级</label>
            <div class="col-sm-3">
                <c:if test="${isPart==0}">
                    <label class="radio-inline">
                        <input type="radio" name="ugrade" id="uGradeone" value="1" class="insert-ugrade-radio"/>全部等级
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="ugrade" id="ugradetwo" value="2" class="insert-ugrade-radio"/>部分等级
                    </label>
                </c:if>
                <c:if test="${isPart!=0}">
                    <label class="radio-inline">
                        <input type="radio" name="ugrade" id="ugradetwo" value="2" class="insert-ugrade-radio"/>部分等级
                    </label>
                </c:if>
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
                            <input type="checkbox" name="ugrades" id="ugrades" value="${ade.ugrade}"/>${ade.ugradename}
                        </label>
                        <c:if test="${status.count%4==0}"><br></c:if>
                    </c:forEach>
                </c:if>
            </div>
        </div>


        <!--百分比-->
        <div class="form-group">
            <label class="col-sm-3 control-label laber_from" for="IEPercent">居间管理费百份比</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="text" name="mfrate" id="IEPercent" placeholder="利息管理费百份比" class="form-control"/>
                    <span class="input-group-addon">%</span>
                </div>
            </div>
        </div>
        <!--收费最小值-收费最大值-->
        <div class="form-group">
            <label class="col-sm-3 control-label laber_from" for="minIEFee">收费最大金额</label>
            <div class="col-sm-3">
                <div class="input-group">
                    <input type="text" name="maxmfamount" id="maxIEFee" placeholder="最高利息管理收费金额" class="form-control"/>
                    <span class="input-group-addon">元</span>
                </div>
            </div>
        </div>
        <!--资金清算是否需要审核-->
        <div class="form-group">
            <label class="col-sm-3 control-label laber_from" for="isAudit">资金清算是否需要审核</label>
            <div class="col-sm-3">
                <c:if test="${empty insertInterpense.isaudit}">
                    <select name="isaudit" id="isAudit" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </c:if>
                <c:if test="${!empty insertInterpense.isaudit}">
                    <select name="isaudit" class="form-control">
                        <c:if test="${insertInterpense.isaudit eq 1}">
                            <option value="1">是</option>
                        </c:if>
                        <c:if test="${insertInterpense.isaudit eq 0}">
                            <option value="0">否</option>
                        </c:if>
                    </select>
                </c:if>
            </div>
        </div>
        <c:if test="${!empty insertInterpense}">
            <input name="tid" value="${insertInterpense.tid}" type="hidden"/>
            <input name="intexpno" value="${insertInterpense.intexpno}" type="hidden"/>
        </c:if>
        <!--是否为模板-->
        <div class="form-group">
            <label class="col-sm-3 control-label laber_from" for="isTemplet">是否为模板</label>
            <div class="col-sm-3">
                <c:if test="${empty insertInterpense.istemplet}">
                    <select name="istemplet" id="isTemplet" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </c:if>
                <c:if test="${!empty insertInterpense.istemplet}">
                    <select name="istemplet" class="form-control">
                        <c:if test="${insertInterpense.istemplet eq 1}">
                            <option value="1">是</option>
                        </c:if>
                        <c:if test="${insertInterpense.istemplet eq 0}">
                            <option value="0">否</option>
                        </c:if>
                    </select>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"></label>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-1">
                <button type="submit" class="btn btn-default" id="interButton">保存</button>
            </div>
            <div class="col-sm-1">
                <button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>