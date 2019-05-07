<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>GuaranteeFee_Inst</title>
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
            $("#ugradeone").attr("checked", "checked");//默认选择全部等级
            $("#ugrades_div").hide();//影藏全部等级
            $(".ugrade_radio").change(function () {
                var $radioval = $(".ugrade_radio:checked").val();
                if ($radioval == 1) {
                    $("#ugrades_div").hide();

                } else {
                    $("#ugrades_div").show();
                }
            });
            $("#selectAll").click(function () {
                if (this.checked) {
                    $("#ugrestrict_div  :checkbox").prop("checked", true);
                } else {
                    $("#ugrestrict_div  :checkbox").prop("checked", false);
                }
            })


        });

        $(document).ready(function () {
            var MaxInputs = 15;  //maximum input boxes allowed
            var InputsWrapper = $("#InputsWrapper"); //Input boxes wrapper ID
            var AddButton = $("#AddMoreFileBox"); //Add button ID

            var x = InputsWrapper.length; //initlal text box count
            var FieldCount = 1; //to keep track of text box added
            /* 加载页面后把定额和百分比的div隐藏 */
            $(AddButton).click(function (e) //on add input button click
            {
                if (x <= MaxInputs) //max input box allowed
                    if (x == MaxInputs) {
                        $(this).attr("disabled", "disabled");
                        return true;
                    }
                return false;
            });
            //刪除標籤
            $("body").on("click", ".removeclass", function (e) { //user click on remove text
                var $row = $(this).parents('.wapperclass'),
                    $option = $row.find('[name="option[]"]');
                $row.remove();
                $('form').bootstrapValidator('removeField', $option);
            });
        });

        $(function () {
            $('#defaultForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    /*input状态样式图片*/
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: { //验证
                    ugrade: {
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            }
                        }
                    },
                    ugrades: {
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            }
                        }
                    },
                    guaranteeid: {
                        validators: {
                            notEmpty: {
                                message: '不能为空'
                            }
                        }
                    },
                    chargetype: {//收费类型
                        validators: {
                            notEmpty: {
                                message: '收费类型不能为空'
                            }
                        }
                    },
                    'guaranteefees[0].minnmmoney': {//低值
                        validators: {
                            notEmpty: {
                                message: '低值不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            }
                        }
                    },
                    'guaranteefees[0].maxnmmoney': {//高值
                        validators: {
                            notEmpty: {
                                message: '高值不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            },
                            callback: {
                                message: '高值不能低于低值',
                                callback: function (value, validator) {
                                    var minney = $("#minNMMoney").val(),
                                        sum = parseFloat(minney);
                                    return value >= sum;
                                }
                            }
                        }
                    },
                    type: {//类型
                        validators: {
                            notEmpty: {
                                message: '类型不能为空'
                            }
                        }
                    },
                    'guaranteefees[0].gfquota': {//担保费定额
                        validators: {
                            notEmpty: {
                                message: '担保费定额不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            }
                        }
                    },
                    'guaranteefees[0].gfpercent': {//担保费百份比
                        validators: {
                            notEmpty: {
                                message: '担保费百份比不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '百分比必须为数字'
                            }
                        }
                    },
                    'guaranteefees[0].mingffee': {//收费最小金额-收费最大金额
                        validators: {
                            notEmpty: {
                                message: '收费最小金额不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            }
                        }
                    },
                    'guaranteefees[0].maxgffee': {//收费最小金额-收费最大金额
                        validators: {
                            notEmpty: {
                                message: '收费最大金额不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            },
                            callback: {
                                message: '高值不能低于低值',
                                callback: function (value, validator) {
                                    var minFFee = $("#minGFFee").val(),
                                        sum = parseFloat(minFFee);
                                    return value >= sum;
                                }
                            }
                        }
                    },
                    gfrate: {//担保费费率
                        validators: {
                            notEmpty: {
                                message: '担保费费率不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '费率必须为数字'
                            }
                        }
                    },
                    mingfamount: {//担保费最低收费-最高收费
                        validators: {
                            notEmpty: {
                                message: '担保费最低收费不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
                            }
                        }
                    },
                    maxgfamount: {//担保费最低收费-最高收费
                        validators: {
                            notEmpty: {
                                message: '担保费最高收费不能为空'
                            },
                            regexp: {
                                regexp: /^(\d*\.)?\d+$/,
                                message: '金额必须为浮点数如100.0或100'
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
    <a href="#">标的担保费率设置页面</a>
</div>
<div class="container" style="margin-top: 25px;">
    <form class="form-horizontal" role="form"
          action="${pageContext.request.contextPath}/admin/guaranteeFee/insertGuaranteeFeetwo.action" id="defaultForm">
        <input type="hidden" name="tid" value="${tid}">
        <!--担保公司-->
        <div class="form-group">
            <label class="col-sm-3 control-label laber_from">担保公司</label>
            <div class="col-sm-3">
                <select id="GFRecManId" onchange="aa(this)" class="form-control">
                    <option value="">请选择</option>
                    <c:if test="${!empty enterpriseUsersInfos}">
                        <c:forEach items="${enterpriseUsersInfos}" var="list" varStatus="status">
                            <option value="${list.baseid},${list.usrcustid}">${list.usrname}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
        </div>
        <script>
            function aa(t) {
                var val = $(t).val();
                var valArr = val.split(",");
                var gfrecman = valArr[1];
                /*$("#GFRecMan").val(gfrecman);*/
                $("#GFRecMan").val("9930040290000050011");
                $("#gfrecmanid").val(valArr[0]);
            }
        </script>
        <input type="hidden" name="gfrecmanid" id="gfrecmanid"/><%--担保服务费收款人Id--%>
        <!--担保费收款人-->
        <div class="form-group">
            <label for="GFRecMan" class="col-sm-3 control-label laber_from">担保费收款人</label><%--为用户的资金托管账号--%>
            <div class="col-sm-3">
                <input type="text" name="gfrecman" id="GFRecMan" placeholder="担保费收款人" class="form-control"
                       value="" readonly/>
            </div>
        </div>
        <!--投标人等级-->
        <div class="form-group">
            <label for="ugradeone" class="col-sm-3 control-label">投标人等级</label>
            <div class="col-sm-3">
                <c:if test="${isPart==0}">
                    <label class="radio-inline">
                        <input type="radio" name="ugrade" id="ugradeone" value="1" class="ugrade_radio"/>全部等级
                    </label>
                </c:if>
                <label class="radio-inline">
                    <input type="radio" name="ugrade" id="ugradetwo" value="2" class="ugrade_radio"/>选择等级
                </label>

            </div>
        </div>
        <!--允许投标的会员等级-->
        <div id="ugrades_div">

            <div class="form-group">
                <label class="radio-inline">
                    <input type="checkbox" name="ugrades" id="selectAll"/>全选
                </label>
                <label for="ugrestricts" class="col-sm-3 control-label"></label>
                <div class="col-sm-6" id="ugrestrict_div">
                    <c:if test="${!empty uGrades }">
                        <c:forEach items="${uGrades }" var="ugr" varStatus="status">
                            <label class="checkbox-inline" style="width:80px;">
                                <input type="checkbox" id="ugrestricts" name="ugrades"
                                       value="${ugr.ugrade}"/>${ugr.ugradename}
                            </label>
                            <c:if test="${status.count%4==0 }">
                                <br/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
        <!--担保费收费方式设置-->
        <div class="form-group">
            <label class="col-sm-3 control-label laber_from" for="chargeType">收费类型</label>
            <div class="col-sm-3">
                <select name="chargetype" id="chargeType" class="form-control">
                    <!-- 冻结投标 -->
                    <option value="2">投标时收取</option>
                </select>
            </div>
        </div>

        <!--担保费费率(投标时收取)-->
        <!--投标时收取-->
        <div id="toubiao_div">
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from" for="GFRate">担保费费率</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" name="gfrate" id="GFRate" placeholder="担保费费率" class="form-control"/>
                        <span class="input-group-addon">%</span>
                    </div>
                </div>
            </div>
            <!--担保费最低收费-(投标时收取)-->
            <div class="form-group">
                <label class="col-sm-3 control-label laber_from">担保费最高收费</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" name="maxgfamount" id="maxGFAmount" placeholder="担保费最高收费"
                               class="form-control"/>
                        <span class="input-group-addon">元</span>
                    </div>
                </div>
            </div>
        </div>


        <!-- 备注 -->
        <div class="form-group">
            <label class="control-label col-sm-3">备注</label>
            <div class="col-sm-3">
                <textarea rows="3" name="remark" class="form-control"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"></label>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-1">
                <button type="submit" class="btn btn-default" id="guarButton">保存</button>
            </div>
            <div class="col-sm-1">
                <button type="button" class="btn btn-default" onclick="javascript:;history.go(-1)">返回列表</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>