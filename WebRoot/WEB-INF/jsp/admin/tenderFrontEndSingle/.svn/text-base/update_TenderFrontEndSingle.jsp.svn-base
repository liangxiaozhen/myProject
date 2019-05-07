<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>莫邪科技</title>


</head>
<body>
<form id="update-form">
    <div class="row" style="line-height: 0px;">
        <div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
            <font color="red"><b>单标前端信息修改</b></font>
        </div>
        <hr>
    </div>
        <div class="row">
            <div class="col-md-6 col-md-offset-1">
                排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序： <input type="text" name="sno" value="${tenderFrontEndSingle.sno}">
            </div>
                <%--前端状态（1显示，0不显示）--%>
            <div class="col-md-6 col-md-offset-1" style="margin-top: 15px;">
                前端状态：
                <select name="isdisplay">
                <option value="1"
                        <c:if test="${tenderFrontEndSingle.isdisplay==1}">selected="selected"</c:if>>显示</option>
                <option value="0"
                        <c:if test="${tenderFrontEndSingle.isdisplay==0}">selected="selected"</c:if>>不显示</option>
            </select>
            </div>
        </div>
        <input type="hidden" name="id" value="${tenderFrontEndSingle.id}">
        <input type="hidden" name="tno" value="${tenderFrontEndSingle.tno}" id="tno">
</form>
<script>
    function selectItem(t) {
        var val = $(t).val();
        var valArray = val.split(",");
        if (val == "") {
            $("#showcontent").text("");
            $("#showInfoattribute").text("");

            $("#content").val(val);
            $("#infoattribute").val(val);
        }
        //文字
        if (valArray[0].length > 0) {
            $("#showcontent").text("");
            $("#showInfoattribute").text("");
            val = val.substring(0, val.length - 1);
            $("#showcontent").text(val);
            $("#content").val(val);
            $("#showInfoattribute").text("文字");
            $("#infoattribute").val(2);
        }
        //图片
        if (valArray[valArray.length - 1].length > 0) {
            $("#showcontent").text("");
            var pic = valArray[valArray.length - 1];
            var src = "http://localhost:8080/ptpjx/materialpic/" + pic;
            $("#showcontent").append($("<img style='width: 100px;height: 100px;margin-bottom: 10px;' src=" + src + "><br/>标题：<input type='text' name='pictitle' id='pictitle' style='width: 100px;'/> "));
            $("#content").val(pic);
            $("#showInfoattribute").text("");
            $("#showInfoattribute").text("图片");
            $("#infoattribute").val(1);
        }

    }
</script>
<script type="text/javascript">
    //UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
    //一定要用这句话，否则你需要去ueditor.config.js修改路径的配置信息
    var ue = UE.getEditor('myEditor', {
        initialFrameWidth: 1000,
        initialFrameHeight: 350
    });
</script>
</body>
</html>