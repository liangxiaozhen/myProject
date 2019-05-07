<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>莫邪科技</title>
    <script type="text/javascript">
        $(function () {
            $("#insert-itiname-text").change(function () {
                $("#insert-itiname-lb").html("");
            });
            var source = $("#source").val();
            if (source == "2") {
                $("#infoattributediv").hide();
                $("#contentdiv").hide();
            }
            var fix = $("#isfixed").val();
            if (fix == 2) {
                $("#contentdiv").hide();
            }
        });
    </script>

    <script>
        function itemSource(t) {
            var val = $(t).val();
            if (val == 2) {
                $("#infoattribute").val("");
                $("#infoattributediv").hide();
                $("#content").val("");
                $("#contentdiv").hide();
                $("#isfixed").val(1);
                $("#isfixed").attr("disabled", "disabled");
            } else if (val = 1) {
                $("#infoattributediv").show();
                $("#isfixed").removeAttr("disabled");
                $("#isfixed").val("");
                $("#contentdiv").show();
            }
        }

    </script>
    <script>
        /*
         * 编辑校验
         */
        function validateUpdate() {
            var ttypename = $("#ttypename").val();
            if (ttypename == null || ttypename == "") {
                $("#update-ttypename-lb").html("*必须选择标的类型");
                return false;
            }
            var infoname = $("#infoname").val();
            if (infoname == null || infoname == "") {
                $("#update-infoname-lb").html("*必须填写项目名称");
                $("#infoname").focus();
                return false;
            }
            var source = $("#source").val();
            if (source == null || source == "") {
                $("#update-source-lb").html("*必须选择来源");
                return false;
            }
            //如果内容属性div是隐藏的就不去验证
            var displayinfo = $('#infoattributediv').css('display');
            if (displayinfo != 'none') {
                var infoattribute = $("#infoattribute").val();
                if (infoattribute == null || infoattribute == "") {
                    $("#update-infoattribute-lb").html("*必须选择内容属性");
                    return false;
                }
            }

            var isfixed = $("#isfixed").val();
            if (isfixed == null || isfixed == "") {
                $("#update-isfixed-lb").html("*必须选择应用范围");
                return false;
            }
            //如果内容div是隐藏的就不去验证
            var displaycon = $("#contentdiv").css('display')
            if (displaycon != 'none') {
                var content = $("#content").val();
                if (content == null || content == "") {
                    $("#update-content-lb").html("*必须填写内容");
                    $("#content").focus();
                    return false;
                }
            }

            var status = $("#status").val();
            if (status == null || status == "") {
                $("#update-status-lb").html("*必须选择状态");
                return false;
            }

            return true;
        }
        function removePic() {
            $("#content").val("");
            $("#img1").removeAttr("src");
        }
        function check() {
            if (validateInsert()) {
                var isfixed = $("#isfixed").val();
                //唯一
                if (isfixed == 2) {
                    $("#content").val("");
                    return true;
                }
                var infoattribute = ($("#infoattribute").val());
                //文本
                if (infoattribute == 2) {
                    $("#content").val(ue.getContent());
                    return true;
                }
                return true;
            } else {
                return false;
            }

        }
    </script>
</head>
<body>
<form id="update-form"  method="post"
      action="${pageContext.request.contextPath}/admin/tenderFrontEnd/update.action">
    <div class="row" style="line-height: 0px;">
        <div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
            <font color="red"><b>标的前端信息修改</b></font>
        </div>

        <hr>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            项目名称： <input type="text" name="infoname" id="infoname" value="${tenderFrontEnd.infoname}"><span
                style="color: red;"><label id="update-infoname-lb"></label></span>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            状态
            <select name="status" id="status">
                <option value="">请选择</option>
                <option value="1" <c:if test="${tenderFrontEnd.status==1}">selected</c:if>>启用</option>
                <option value="0" <c:if test="${tenderFrontEnd.status==2}">selected</c:if>>停用</option>
            </select><span style="color: red;"><label id="update-status-lb"></label></span>
        </div>
    </div>
    <input type="hidden" name="id" value="${tenderFrontEnd.id}"/>
    <input type="hidden" name="ttypeid" value="${tenderFrontEnd.ttypeid}"/>
    <input type="hidden" name="infono" value="${tenderFrontEnd.infono}"/>
</form>
</body>
</html>