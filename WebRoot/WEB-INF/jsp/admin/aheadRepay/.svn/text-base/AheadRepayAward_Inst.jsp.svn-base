<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AheadRepayAward_Inst</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/tenderitem/change.js"></script>
    <script src="${pageContext.request.contextPath }/js/tenderitem/changetwo.js"></script>
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
    <a href="#">标的提前还款个人奖品奖励设置</a>
</div>
<div class="container" style="margin-top: 25px;">
    <form class="form-horizontal" role="form" method="post"
          action="${pageContext.request.contextPath}/admin/aheadRepay/insertAheadRepayAward.action" id="bookFormtwo">
        <!-- <div class="form-group">
             <label class="col-sm-3 control-label">增益利息补偿开关</label>
             <div class="col-sm-3">
                 <select name="ispluscompensateon" id="inputispluscompensateon" class="form-control">
                     <option value="">请选择</option>
                     <option value="1">补偿</option>
                     <option value="0">不补偿</option>
                 </select>
             </div>
         </div> -->
        <!--单个投资人累计增益欠收最小利息和最高利息-->
        <input type="hidden" name="tid" value="${tid}">
        <div id="InputsWrapper" class="wapperclass">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="minplusnoreceiveint">单个投资人累计增益欠收利息</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" value="0" readonly="readonly" name="aheadRepayAwards[0].minplusnoreceiveint"
                               id="minplusnoreceiveint" placeholder="累计增益欠收利息最小值" class="form-control"/>
                        <span class="input-group-addon">元</span>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" value="999999999999" readonly="readonly"
                               name="aheadRepayAwards[0].maxplusnoreceiveint" id="maxplusnoreceiveint"
                               placeholder="累计增益欠收利息最大值" class="form-control"/>
                        <span class="input-group-addon">元</span>
                    </div>
                </div>
                <div class="col-sm-1">
                    <span><button id="AddMoreFileBoxone"
                                  class="btn btn-default addButtontwo"><strong>+</strong></button></span>
                </div>
            </div>
            <!--增益奖励方式-->
            <div class="form-group">
                <label class="col-sm-3 control-label" for="plusawardtype">增益奖励方式</label>
                <div class="col-sm-3">
                    <select name="aheadRepayAwards[0].plusawardtype" id="inputplusawardtype" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">平台罚金</option>
                        <option value="2">平台奖励</option>
                        <option value="3">平台罚金且平台奖励</option>
                    </select>
                </div>
            </div>
            <!--增益平台罚金奖励名称-->
            <div id="pluspenaltyname_div">
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="pluspenaltyname">增益平台罚金奖励名称</label>
                    <div class="col-sm-3">
                        <input type="text" name="aheadRepayAwards[0].pluspenaltyname" id="pluspenaltyname"
                               placeholder="增益平台罚金奖励名称" class="form-control"/>
                    </div>
                </div>
                <!--增益类型选择-->
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="typetwo">类型</label>
                    <div class="col-sm-3">
                        <select name="typetwo" id="typetwo" class="form-control">
                            <option value="">请选择</option>
                            <option value="iequotatwo">定额</option>
                            <option value="iepercenttwo">百分比</option>
                        </select>
                    </div>
                </div>
                <!--增益平台罚金定额-->
                <div class="form-group" id="quotainput_divtwo">
                    <label class="col-sm-3 control-label" for="pluspenaltyquota">增益平台罚金定额</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="aheadRepayAwards[0].pluspenaltyquota" id="pluspenaltyquota"
                                   placeholder="增益平台罚金定额" class="form-control"/>
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                </div>
                <!--增益平台罚金百分比-->
                <div class="form-group" id="dayawardrate_divtwo">
                    <label class="col-sm-3 control-label" for="pluspenaltyrate">增益平台罚金百分比</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="aheadRepayAwards[0].pluspenaltyrate" id="pluspenaltyrate"
                                   placeholder="增益平台罚金百分比" class="form-control"/>
                            <span class="input-group-addon">%</span>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="aheadRepayAwards[0].plusmaxpenalty" id="plusmaxpenalty"
                                   placeholder="增益平台罚金最大值" class="form-control"/>
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                </div>
            </div>
            <!--增益平台奖励奖品名称-->
            <div id="plusPAwardName_div">
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="plusPAwardName">增益平台奖励奖品名称</label>
                    <div class="col-sm-3">
                        <c:if test="${!empty awards}">
                            <select name="aheadRepayAwards[0].pluspawardname" id="plusPAwardName" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach var="ard" items="${awards}" varStatus="sta">
                                    <option value="${ard.ano},${ard.aname}">${ard.aname}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">奖品分数</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="aheadRepayAwards[0].pluspawardcount" id="pluspawardcount"
                                   placeholder="请输入奖品吗的份数" class="form-control"/>
                            <span class="input-group-addon">份</span>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!--我是可耻的分割线-->
        <div id="InputsWrappertwo" class="hide form-remove wapperclass">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="minplusnoreceiveint">单个投资人累计增益欠收利息</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" readonly="readonly" name="minplusnoreceiveint" id="minplusnoreceiveint"
                               placeholder="累计增益欠收利息最小值" class="form-control"/>
                        <span class="input-group-addon">元</span>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" value="999999999999" readonly="readonly" name="maxplusnoreceiveint"
                               id="maxplusnoreceiveint" placeholder="累计增益欠收利息最大值" class="form-control"/>
                        <span class="input-group-addon">元</span>
                    </div>
                </div>
                <div class="col-sm-1">
                    <span><button id="AddMoreFileBoxtwo"
                                  class="btn btn-default removeButtontwo"><strong>-</strong></button></span>
                </div>
            </div>
            <!--增益奖励方式-->
            <div class="form-group">
                <label class="col-sm-3 control-label" for="plusawardtype">增益奖励方式</label>
                <div class="col-sm-3">
                    <select name="plusawardtype" id="inputplusawardtype" class="form-control">
                        <option value="">请选择</option>
                        <option value="1">平台罚金</option>
                        <option value="2">平台奖励</option>
                        <option value="3">平台罚金且平台奖励</option>
                    </select>
                </div>
            </div>
            <!--增益平台罚金奖励名称-->
            <div id="pluspenaltyname_div">
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="pluspenaltyname">增益平台罚金奖励名称</label>
                    <div class="col-sm-3">
                        <input type="text" name="pluspenaltyname" id="pluspenaltyname" placeholder="增益平台罚金奖励名称"
                               class="form-control"/>
                    </div>
                </div>
                <!--增益类型选择-->
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="typetwo">类型</label>
                    <div class="col-sm-3">
                        <select name="typetwo" id="typetwo" class="form-control">
                            <option value="">请选择</option>
                            <option value="iequotatwo">定额</option>
                            <option value="iepercenttwo">百分比</option>
                        </select>
                    </div>
                </div>
                <!--增益平台罚金定额-->
                <div class="form-group" id="quotainput_divtwo">
                    <label class="col-sm-3 control-label" for="pluspenaltyquota">增益平台罚金定额</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="pluspenaltyquota" id="pluspenaltyquota" placeholder="增益平台罚金定额"
                                   class="form-control"/>
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                </div>
                <!--增益平台罚金百分比-->
                <div class="form-group" id="dayawardrate_divtwo">
                    <label class="col-sm-3 control-label" for="pluspenaltyrate">增益平台罚金百分比</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="pluspenaltyrate" id="pluspenaltyrate" placeholder="增益平台罚金百分比"
                                   class="form-control"/>
                            <span class="input-group-addon">%</span>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="plusmaxpenalty" id="plusmaxpenalty" placeholder="增益平台罚金最大值"
                                   class="form-control"/>
                            <span class="input-group-addon">元</span>
                        </div>
                    </div>
                </div>
            </div>
            <!--增益平台奖励奖品名称-->
            <div id="plusPAwardName_div">
                <div class="form-group">
                    <label class="col-sm-3 control-label" for="plusPAwardName">增益平台奖励奖品名称</label>
                    <div class="col-sm-3">
                        <c:if test="${!empty awards}">
                            <select name="pluspawardname" id="plusPAwardName" class="form-control">
                                <option value="">请选择</option>
                                <c:forEach var="ard" items="${awards}" varStatus="sta">
                                    <option value="${ard.ano},${ard.aname}">${ard.aname}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">奖品份数</label>
                    <div class="col-sm-3">
                        <div class="input-group">
                            <input type="text" name="pluspawardcount" id="Pluspawardcount" placeholder="请输入奖品的份数"
                                   class="form-control"/>
                            <span class="input-group-addon">份</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 资金清算是否需要审核 -->
        <div class="form-group">
            <label class="col-sm-3 control-label" for="isAudit">资金清算是否需要审核</label>
            <div class="col-sm-3">
                <select name="isaudit" id="isAudit" class="form-control">
                    <option value="">请选择</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </div>
        </div>
        <!-- 是否为模板（0否，1是）-->
        <div class="form-group">
            <label class="col-sm-3 control-label" for="isTemplet">是否为模板</label>
            <div class="col-sm-3">
                <select name="istemplet" id="isTemplet" class="form-control">
                    <option value="">请选择</option>
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">备注</label>
            <div class="col-sm-3">
                <textarea rows="3" class="form-control" name="remark"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-1">
                <button type="submit" class="btn btn-default" id="aheButton">保存</button>
            </div>
            <div class="col-sm-1">
                <button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $(function () {
        //$("#InputsWrapper").hide(); //增益利息补偿
        $("#dayawardrate_divtwo").hide(); //增益百分比隐藏
        $("#quotainput_divtwo").hide(); //增益定额隐藏
        $("#pluspenaltyname_div").hide(); //平太罚金
        $("#plusPAwardName_div").hide(); //平太奖励
        //补偿投资人(增益利息补偿)开关
        /* $("#inputispluscompensateon").change(function() {
         var penval = $(this).val();
         if(penval == 1) {
         $("#InputsWrapper").show();
         } else {
         $("#InputsWrapper input").val("");
         $("#InputsWrapper select").val("");
         $("#InputsWrapper").hide();
         }
         }); */
        //奖励方式(增益利息补偿)
        $("#inputplusawardtype").change(function () {
            var typeval = $(this).val();
            if (typeval == 1) { //平太罚金
                $("#plusPAwardName_div select").val("");
                $("#plusPAwardName_div").hide();
                $("#pluspenaltyname_div").show();
            } else if (typeval == 2) { //平台奖励
                $("#pluspenaltyname_div input").val("");
                $("#pluspenaltyname_div select").val("");
                $("#pluspenaltyname_div").hide();
                $("#plusPAwardName_div").show();
            } else if (typeval == 3) { //平台罚金及平太奖励
                $("#pluspenaltyname_div input").val("");
                $("#pluspenaltyname_div select").val("");
                $("#plusPAwardName_div select").val("");
                $("#pluspenaltyname_div").show();
                $("#plusPAwardName_div").show();
            } else {
                $("#pluspenaltyname_div input").val("");
                $("#pluspenaltyname_div select").val("");
                $("#plusPAwardName_div select").val("");
                $("#pluspenaltyname_div").hide();
                $("#plusPAwardName_div").hide();
            }
        });
        //类型选择(增益利息补偿)
        $("#typetwo").change(function () {
            if ($(this).val() == "iequotatwo") {
                $("#dayawardrate_divtwo input").val("");
                $("#dayawardrate_divtwo").hide();
                $("#quotainput_divtwo").show();
            } else if ($(this).val() == "iepercenttwo") {
                $("#dayawardrate_divtwo").show();
                $("#quotainput_divtwo input").val("");
                $("#quotainput_divtwo").hide();
            } else {
                $("#quotainput_divtwo").hide();
                $("#dayawardrate_divtwo").hide();
                $("#quotainput_divtwo input").val("");
                $("#dayawardrate_divtwo input").val("");
            }
        });

    });
    $(document).ready(function () {
        var minplusnoreceiveint = {//累计增益欠收最小利息
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        },
                        regexp: {//匹配规则
                            regexp: /^(\d*\.)?\d+$/,
                            message: '金额必须为浮点数或小数'
                        }
                    }
                },
                maxplusnoreceiveint = {//累计增益欠收最高利息
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        },
                        regexp: {//匹配规则
                            regexp: /^(\d*\.)?\d+$/,
                            message: '金额必须为浮点数或小数'
                        }
                    }
                },
                plusawardtype = {//奖励方式
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        }
                    }
                },
                pluspenaltyname = {//罚金名称
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        }
                    }
                },
                pluspenaltyquota = {//定额
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        },
                        regexp: {//匹配规则
                            regexp: /^(\d*\.)?\d+$/,
                            message: '定额必须为浮点数或小数'
                        }
                    }
                },
                pluspenaltyrate = {//百分比
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        },
                        regexp: {//匹配规则
                            regexp: /^(\d*\.)?\d+$/,
                            message: '百分比必须为浮点数或小数'
                        }
                    }
                },
                plusmaxpenalty = {//最大值
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        }
                    }
                },
                pluspawardname = {//奖品名称
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        }
                    }
                }
        bookIndex = 0;

        $("#bookFormtwo").bootstrapValidator({
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                'aheadRepayAwards[0].maxplusnoreceiveint': maxplusnoreceiveint,
                'aheadRepayAwards[0].plusawardtype': plusawardtype,
                'aheadRepayAwards[0].pluspenaltyname': pluspenaltyname,
                'aheadRepayAwards[0].pluspenaltyquota': pluspenaltyquota,
                'aheadRepayAwards[0].pluspenaltyrate': pluspenaltyrate,
                'aheadRepayAwards[0].plusmaxpenalty': plusmaxpenalty,
                'aheadRepayAwards[0].pluspawardname': pluspawardname
            }
        })

        // Add button click handler
                .on('click', '.addButtontwo', function () {
                    var lastBeforeInput = $(InputsWrappertwo).prev().find("input").eq(1);
                    lastBeforeInput.attr("readonly", false);
                    lastBeforeInput.addClass("maxMoney");
                    lastBeforeInput.val("");
                    bookIndex++;
                    var $template = $('#InputsWrappertwo'),
                            $clone = $template
                                    .clone()
                                    .removeClass('hide')
                                    .removeAttr('id')
                                    .attr('data-book-index', bookIndex)
                                    .insertBefore($template);

                    // Update the name attributes
                    $clone
                            .find('[name="maxplusnoreceiveint"]').attr('name', 'aheadRepayAwards[' + bookIndex + '].maxplusnoreceiveint').end()
                            .find('[name="plusawardtype"]').attr('name', 'aheadRepayAwards[' + bookIndex + '].plusawardtype').end()
                            .find('[name="pluspenaltyname"]').attr('name', 'aheadRepayAwards[' + bookIndex + '].pluspenaltyname').end()
                            .find('[name="pluspenaltyquota"]').attr('name', 'aheadRepayAwards[' + bookIndex + '].pluspenaltyquota').end()
                            .find('[name="pluspenaltyrate"]').attr('name', 'aheadRepayAwards[' + bookIndex + '].pluspenaltyrate').end()
                            .find('[name="pluspawardname"]').attr('name', 'aheadRepayAwards[' + bookIndex + '].pluspawardname').end()
                            .find('[id="inputplusawardtype"]').attr('id', 'inputplusawardtype' + bookIndex).end()
                            .find('[id="plusPAwardName_div"]').attr('id', 'plusPAwardName_div' + bookIndex).end()
                            .find('[id="pluspenaltyname_div"]').attr('id', 'pluspenaltyname_div' + bookIndex).end()
                            .find('[id="typetwo"]').attr('id', 'typetwo' + bookIndex).end()
                            .find('[id="dayawardrate_divtwo"]').attr('id', 'dayawardrate_divtwo' + bookIndex).end()
                            .find('[id="quotainput_divtwo"]').attr('id', 'quotainput_divtwo' + bookIndex).end()
                            .find('[name="plusmaxpenalty"]').attr('name', 'aheadRepayAwards[' + bookIndex + '].plusmaxpenalty').end();
                    // Add new fields
                    // Note that we also pass the validator rules for new field as the third parameter
                    $('#bookFormtwo')
                            .bootstrapValidator('addField', 'aheadRepayAwards[' + bookIndex + '].maxplusnoreceiveint', maxplusnoreceiveint)
                            .bootstrapValidator('addField', 'aheadRepayAwards[' + bookIndex + '].plusawardtype', plusawardtype)
                            .bootstrapValidator('addField', 'aheadRepayAwards[' + bookIndex + '].pluspenaltyname', pluspenaltyname)
                            .bootstrapValidator('addField', 'aheadRepayAwards[' + bookIndex + '].pluspenaltyquota', pluspenaltyquota)
                            .bootstrapValidator('addField', 'aheadRepayAwards[' + bookIndex + '].pluspenaltyrate', pluspenaltyrate)
                            .bootstrapValidator('addField', 'aheadRepayAwards[' + bookIndex + '].pluspawardname', pluspawardname)
                            .bootstrapValidator('addField', 'aheadRepayAwards[' + bookIndex + '].plusmaxpenalty', plusmaxpenalty);
                    $("#plusPAwardName_div" + bookIndex).hide(); //影藏定额
                    $("#pluspenaltyname_div" + bookIndex).hide(); //影藏日奖励费率
                    $("#quotainput_divtwo" + bookIndex).hide(); //定额
                    $("#dayawardrate_divtwo" + bookIndex).hide(); //百分比
                })

                // Remove button click handler
                .on('click', '.removeButtontwo', function () {
                    var $row = $(this).parents('.form-remove'),
                            index = $row.attr('data-book-index');

                    // Remove fields
                    $('#bookFormtwo')
                            .bootstrapValidator('removeField', $row.find('[name="aheadRepayAwards[' + index + '].maxplusnoreceiveint"]'))
                            .bootstrapValidator('removeField', $row.find('[name="aheadRepayAwards[' + index + '].plusawardtype"]'))
                            .bootstrapValidator('removeField', $row.find('[name="aheadRepayAwards[' + index + '].pluspenaltyname"]'))
                            .bootstrapValidator('removeField', $row.find('[name="aheadRepayAwards[' + index + '].pluspenaltyquota"]'))
                            .bootstrapValidator('removeField', $row.find('[name="aheadRepayAwards[' + index + '].pluspenaltyrate"]'))
                            .bootstrapValidator('removeField', $row.find('[name="aheadRepayAwards[' + index + '].pluspawardname"]'))
                            .bootstrapValidator('removeField', $row.find('[name="aheadRepayAwards[' + index + '].plusmaxpenalty"]'));
                    // Remove element containing the fields
                    var bfInput = $(this).parents('.wapperclass').prev().find("input").eq(1);
                    var ntInput = $(this).parents('.wapperclass').next().find("input").eq(0);
                    ntInput.val(bfInput.val());
                    $row.remove();
                    bookIndex--;
                    var lastInput = $(InputsWrappertwo).prev().find("input").eq(1);
                    lastInput.val("999999999999");
                    lastInput.attr("readonly", true);
                });
        $("body").on("keyup", ".maxMoney", function (e) { //user click on remove text
            var nextInput = $(this).parents('.wapperclass').next().find("input").eq(0);
            nextInput.val($(this).val());
        });

        $("body").on("blur", ".maxMoney", function (e) { //user click on remove text
            var beforeInput = $(this).parents('.wapperclass').find("input").eq(0);
            var nextInput = $(this).parents('.wapperclass').next().find("input").eq(0);
            var nextMaxInput = $(this).parents('.wapperclass').next().find("input").eq(1);
            if (beforeInput.val() != "" && $(this).val() != "") {
                if (parseInt(beforeInput.val()) >= parseInt($(this).val())) {
                    alert("输入的值不能小于前面的最小值");
                    $(this).val("");
                    nextInput.val("");
                }
            }
            if (nextMaxInput.val() != "" && $(this).val() != "") {
                if (parseInt(nextMaxInput.val()) <= parseInt($(this).val())) {
                    alert("输入的值不能大于后面的最大值");
                    $(this).val("");
                    nextInput.val("");
                }
            }

        });
    });
</script>
</body>
</html>