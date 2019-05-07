<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加用户等级到风险名单</title>
    <script type="text/javascript">
        //必须先选择名单才能进行下面的操作
        $(function () {
            $("#InputsWrapper").hide();
            $("#ugrades_div").hide();
            $("#userRiskType").change(function () {
                if ("#userRiskType.val()!=''") {
                    $("#InputsWrapper").show();
                }

                $("#riskName").text($("#userRiskType").find("option:selected").text());
            })
        });
        $(".ugrade_radio").change(function () {
            var $radioval = $(".ugrade_radio:checked").val();
            if ($radioval == 1) {
                $("#ugrades_div").hide();
                $("#ugrades_div input[type='checkbox']").each(function () {
                    this.checked = false;
                });
            } else {
                $("#ugrades_div").show();
            }
        });


    </script>
    <style>
        .clear:after {
            content: "";
            display: block;
            clear: both;
            height: 0;
        }
    </style>
</head>
<body>
<form id="insert-form" class="form-horizontal" role="form">
    <div class="form-group">
        <label class="col-sm-3 control-label">选择名单</label>
        <div class="col-sm-3" id="xz">
            <select name="type" id="userRiskType" class="form-control">
                <option value="">---请选择---</option>
                <option value="4">风险名单</option>
                <option value="3">应急改密名单</option>
                <option value="2">黑名单</option>
                <option value="1">白名单</option>
            </select>
        </div>
    </div>

    <!--添加用户和备注-->
    <div id="InputsWrapper">
        <div class="clear">
            <label style="margin-right: 10px;">请选择要加入<span id="riskName"></span>的等级:</label>
            <label class="radio-inline" style="padding-top: 0px;">
                <input type="radio" name="ugrade" id="ugradeone" value="1" class="ugrade_radio" checked="checked"/>全部等级
            </label>
            <label class="radio-inline" style="padding-top: 0px;">
                <input type="radio" name="ugrade" id="ugradetwo" value="2" class="ugrade_radio"/>选择等级
            </label>
        </div>
        <div class="col-sm-3">

        </div>

        <!--允许投标的会员等级-->
        <div id="ugrades_div">
            <div class="form-group" id="ugrestrict_div">
                <label class="col-sm-3 control-label"></label>
                <div class="col-sm-6">
                    <c:if test="${!empty uGrades }">
                        <c:forEach items="${uGrades }" var="ugr" varStatus="status">
                            <label class="checkbox-inline" style="width:100px;">
                                <input type="checkbox" id="ugrestricts" name="ugrades"
                                       value="${ugr.ugrade}"/>${ugr.ugradename}
                            </label>
                            <c:if test="${status.count%6==0 }">
                                <br/>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</form>
</body>
</html>