<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>莫邪科技</title>
    <link
            href="${pageContext.request.contextPath }/bootstrap/3.3.5/css/bootstrap.min.css"
            rel="stylesheet" type="text/css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor/ueditor.all.js"></script>
    <link href="${pageContext.request.contextPath }/BootstrapFileInput/css/fileinput.css" media="all" rel="stylesheet"
          type="text/css"/>
    <script src="${pageContext.request.contextPath }/BootstrapFileInput/js/fileinput.js"
            type="text/javascript"></script>
</head>
<body>
<div align="center">
    <h2>${tenderFrontEnd.infoname}</h2>
    <form method="post" id="content-form"
          action="${pageContext.request.contextPath}/admin/tenderFrontEnd/saveContent.action"
          enctype="multipart/form-data">
        <input type="hidden" name="id" value="${tenderFrontEnd.id }"/>
        <input type="hidden" name="infoattribute" value="${tenderFrontEnd.infoattribute}">
        <input type="hidden" name="ttypeid" value="${tenderFrontEnd.ttypeid}"/>
        <input type="hidden" name="infono" value="${tenderFrontEnd.infono}"/>


        <%--图片--%>
        <c:if test="${tenderFrontEnd.infoattribute==1}">
            <c:if test="${pictitleContent!=null}">
                <div id="father">
                    <c:forEach items="${pictitleContent}" var="p" varStatus="s">
                        <div id="${s.index}div">
                            <img src="http://localhost:8080/ptpbh/materialpic/${p.key}" style="width: 650px;height: 450px;"><br>
                                <%--<img src="http://my.ganjiangps.com/materialpic/${p.key}" style="width: 650px;height: 450px;"><br>--%>
                            <div id="${s.index}key">${p.value}</div>
                            <div>
                                <button type="button" class="del" onclick="del('${p.value}',this)">删除</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <input type="hidden" id="pictitle" name="pictitle" value="${tenderFrontEnd.pictitle}"/>
                <input type="hidden" id="content" name="content" value="${tenderFrontEnd.content}"/>
            </c:if>
            <input id="file-es" name="files" type="file" multiple>
            <script>

                $('#file-es').fileinput({
                    language: 'es',
                    showUpload: false,
                    showRemove: true,
                    uploadUrl: '#',
                    allowedFileExtensions: ['jpg', 'png', 'gif'],
                });
            </script>
            <script>
                //删除图片
                var pic = $("#pictitle").val();
                var content = $("#content").val();
                var picArray = pic.split(",");
                var picContent = content.split(",");
                function del(key, obj) {
                    for (var i = 0; i < pic.length; i++) {
                        if (picArray[i] == key) {
                            picArray.splice(i, 1);
                            picContent.splice(i, 1);
                            $("#pictitle").val(picArray.toString());
                            $("#content").val(picContent.toString());
                        }
                    }
                    $(obj).parent().parent().remove();
                    var children = $("#father").children();
                    if (children.length == 0) {
                        $("#pictitle").remove()
                        $("#content").remove();
                    }
                }

            </script>
        </c:if>
        <%--富文本--%>
        <c:if test="${tenderFrontEnd.infoattribute==2}">
			<textarea name="content" id="myEditor">
                    ${tenderFrontEnd.content}
            </textarea>
            <div style="display: none;"><input type="file" name="files"></div>
        </c:if>

        <%--下拉--%>
        <c:if test="${tenderFrontEnd.infoattribute==3}">
            <input type="hidden" id="content3" value="${tenderFrontEnd.content}"/>
            <div id="content3Content"></div>
            <script>
                var contentVal = $("#content3").val();
                var contentArray = contentVal.split(",");
                var content3Content = $("#content3Content");
                var n = 1;
                var newDiv = $("<div><br/><span>选项" + n + ": </span><input type='text' value='" + contentArray[n - 1] + "' name='content' id=''/><input type='button' onclick='addDiv(this)' value='+' style='width: 25px;height: 25px;background:#C9C5C5;'></div>");
                content3Content.append(newDiv);
                for (var i = n; i < contentArray.length; i++) {
                    var newDiv1 = $("<div><br/><span>选项" + (n + 1) + ": </span><input type='text' value='" + contentArray[n] + "' name='content' id=''/><input type='button' onclick='delDiv(this)' value='-' style='width: 25px;height: 25px;background:#C9C5C5;'></div>");
                    content3Content.append(newDiv1);
                    n++;
                }
                function addDiv(t) {
                    n++
                    var parent = $(t).parent().parent();
                    var newDiv = $("<div><br/><span>选项" + n + ": </span><input type='text' value='' name='content' id=''/><input type='button' onclick='delDiv(this)' value='-' style='width: 25px;height: 25px;background:#C9C5C5;'></div>");
                    parent.append(newDiv);
                }
                function delDiv(t) {
                    $(t).parent().remove();
                    n--;
                }
            </script>
            <div style="display: none;"><input type="file" name="files"></div>
        </c:if>
        <%--标签--%>
        <%--下拉或标签--%>
        <c:if test="${tenderFrontEnd.infoattribute==4}">
            ${tenderFrontEnd.infoname}：
            <select name="content">
                <c:if test="${!empty contentArr}">
                    <c:forEach items="${contentArr}" var="c">
                        <option value="${c}"
                                <c:if test="${tenderFrontEnd.content==c}">selected="selected"</c:if>>${c}</option>
                    </c:forEach>
                </c:if>
            </select>
            <div style="display: none;"><input type="file" name="files"></div>
        </c:if>
        <br/>
        <div style="margin-bottom: 10px;">
            <button type="button" class="btn btn-default"  onclick="check();">保存</button>
            &nbsp;&nbsp;
            <button type="button" class="btn btn-default"
                    onclick="javascript:history.go(-1);">返回
            </button>
        </div>
    </form>
</div>
<script type="text/javascript">
    //服务端验证
    function check() {
        //验证图片标题
        var pictitleArray = $(".file-footer-buttons input[name='pictitle']");
        if (pictitleArray.size() > 0) {
            for (var i = 0; i < pictitleArray.size(); i++) {
                var pictitle = $.trim($(pictitleArray[i]).val());
                if(pictitle.length>16){
                    alert("第" + (i + 1) + "个图片的标题长度不能大于16");
                    return false;
                }
                if (pictitle == null || pictitle == "") {
                    alert("请填写第" + (i + 1) + "个图片的标题");
                    return false;
                }
                $(pictitleArray[i]).val(pictitle);
            }
        }
        $("#content-form").submit();
    }
    //UEDITOR_CONFIG.UEDITOR_HOME_URL = './ueditor/';
    //一定要用这句话，否则你需要去ueditor.config.js修改路径的配置信息
    var ue = UE.getEditor('myEditor', {
        initialFrameWidth: 1000,
        initialFrameHeight: 350
    });
</script>
</body>
</html>