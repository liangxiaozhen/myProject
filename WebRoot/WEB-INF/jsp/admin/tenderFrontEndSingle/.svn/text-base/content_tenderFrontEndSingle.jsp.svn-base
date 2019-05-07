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

    <link href="${pageContext.request.contextPath }/BootstrapFileInput/css/fileinput.css" media="all" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor/ueditor.all.js"></script>
    <script src="${pageContext.request.contextPath }/BootstrapFileInput/js/fileinput.js"
            type="text/javascript"></script>

</head>
<body>
<div align="center">
    <h2>${tenderFrontEnd.infoname}</h2>
    <form method="post" id="content-form"
          action="${pageContext.request.contextPath}/admin/tenderFrontEndSingle/saveContent.action"
          enctype="multipart/form-data">
        <input type="hidden" name="id" value="${tenderFrontEndSingle.id }"/>
        <input type="hidden" name="infoattribute" value="${tenderFrontEndSingle.infoattribute}"/>
        <input type="hidden" name="tno" value="${tenderFrontEndSingle.tno}"/>
        <input type="hidden" name="isedit" value="${tenderFrontEndSingle.isedit}"/>
        <input type="hidden" name="index" value="${index}"/>

        <label>项目名称(序号：${index}):${tenderFrontEndSingle.infoname}</label><br/>
        <%--图片--%>
        <c:if test="${tenderFrontEndSingle.infoattribute==1}">
            <c:if test="${pictitleContent!=null}">
                <div id="father">
                    <c:forEach items="${pictitleContent}" var="p" varStatus="s">
                        <div id="${s.index}div">
                            <img src="http://localhost:8080/ptpbh/materialpic/${p.key}"
                                 style="width: 650px;height: 450px;"><br>
                                <%--<img src="http://my.ganjiangps.com/materialpic/${p.key}" style="width: 650px;height: 450px;"><br>--%>
                            <div id="${s.index}key">${p.value}</div>
                            <div>
                                    <%--来源为引用的或应用范围为唯一的显示删除按钮--%>
                                <c:if test="${tenderFrontEndSingle.isfixed==2}">
                                    <button type="button" class="del" onclick="del('${p.value}',this)">删除</button>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <input type="hidden" id="pictitle" name="pictitle" value="${tenderFrontEndSingle.pictitle}"/>
                <input type="hidden" id="content" name="content" value="${tenderFrontEndSingle.content}"/>
            </c:if>
            <%--来源为引用的显示保存按钮或应用范围为唯一--%>
            <c:if test="${tenderFrontEndSingle.source==2||tenderFrontEndSingle.isfixed==2}">
                <input id="file-es" name="files" type="file" multiple>
            </c:if>
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
        <c:if test="${tenderFrontEndSingle.infoattribute==2}">
			<textarea name="content" id="myEditor">
                    ${tenderFrontEndSingle.content}
            </textarea>
            <div style="display: none;"><input type="file" name="files"></div>
        </c:if>

        <%--下拉--%>
        <c:if test="${tenderFrontEndSingle.infoattribute==3}">
            内容：
            <select name="content" id="sele">
                <option value="">请选择</option>
                <c:if test="${!empty contentArr}">
                    <c:forEach items="${contentArr}" var="c">

                        <option value="${c}"
                                <c:if test="${tenderFrontEndSingle.content==c}">selected="selected"</c:if>>${c}
                        </option>
                    </c:forEach>
                </c:if>
            </select>
            <div style="display: none;"><input type="file" name="files"></div>
        </c:if>

        <%--应用范围为通用时的标签--%>
        <c:if test="${tenderFrontEndSingle.isfixed==1&&tenderFrontEndSingle.infoattribute==4}">
            内容：${tenderFrontEndSingle.content}
            <div style="display: none;"><input type="file" name="files"></div>
        </c:if>
        <%--应用范围为唯一时的标签--%>
        <c:if test="${tenderFrontEndSingle.isfixed==2&&tenderFrontEndSingle.infoattribute==4}">
            内容：
            <select name="content" id="biaoQian">
                <option value="">请选择</option>
                <c:if test="${!empty contentArr}">
                    <c:forEach items="${contentArr}" var="c">
                        <option value="${c}"
                                <c:if test="${tenderFrontEndSingle.content==c}">selected="selected"</c:if>>${c}</option>
                    </c:forEach>
                </c:if>
            </select>
            <div style="display: none;"><input type="file" name="files"></div>
        </c:if>
        <br/>
        <div style="margin-bottom: 10px;">
            <%--来源（1新增，2引用）   应用范围（1通用，2唯一）   内容属性（1图片，2文字，3下拉，4标签） --%>
            <%--应用范围是唯一时--%>
            <c:if test="${tenderFrontEndSingle.isfixed==2}">
                <button type="button" class="btn btn-default" onclick="saveAndFillNext(1);">保存</button>
            </c:if>
            <c:if test="${hasNext==1&&tenderFrontEndSingle.isfixed==2}">
                <button type="button" class="btn btn-default" onclick="saveAndFillNext(2);">保存并填写下一个</button>
                <input type="hidden" name="hasNext" value="1" id="hasNext"/>
            </c:if>
            <c:if test="${hasNext==1&&tenderFrontEndSingle.isfixed==1}">
                <button type="button" class="btn btn-default"
                        onclick="fill_next('${tenderFrontEndSingle.tno}','${tenderFrontEndSingle.id}','${index}');">
                    填写下一个
                </button>
                <input type="hidden" name="hasNext" value="1" id="hasNext"/>
            </c:if>
            &nbsp;&nbsp;
            <button type="button" class="btn btn-default"
                    onclick="backList()">返回列表
            </button>

        </div>
    </form>
</div>

<script type="text/javascript">
    function backList() {
        window.location.href = '${pageContext.request.contextPath}/admin/tenderFrontEndSingle/listByTenderTno.action?tno=' + '${tenderFrontEndSingle.tno}';
    }

    function fill_next(tno, id, index) {
        var url = "fill_next.action?tno=" + tno + "&id=" + id + "&index=" + index;
        window.location.href = url;
    }


    //保存 或 保存并填写下一个
    function saveAndFillNext(num) {
        if (num == 1) {
            $("#hasNext").val("");
        }
        //图片不能为null
        if ('${tenderFrontEndSingle.infoattribute}' == 1) {
            //验证图片标题
            //如果原有的图片删除完了，$("#content").val()就是undefined
            if ($("#content").val() == undefined) {
                //如果还没有选择图片$(".file-preview-frame").val()就是undefined
                if ($(".file-preview-frame").val() == undefined) {
                    alert("请选择图片");
                    return false;
                } else {
                    var pictitleArray = $(".file-footer-buttons input[name='pictitle']");
                    if (pictitleArray.size() > 0) {
                        for (var i = 0; i < pictitleArray.size(); i++) {
                            var pictitle = $.trim($(pictitleArray[i]).val());
                            if (pictitle.length > 16) {
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
            } else {
                var pictitleArray = $(".file-footer-buttons input[name='pictitle']");
                if (pictitleArray.size() > 0) {
                    for (var i = 0; i < pictitleArray.size(); i++) {
                        var pictitle = $.trim($(pictitleArray[i]).val());
                        if (pictitle.length > 16) {
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
        }

        //富文本不能为null
        if ('${tenderFrontEndSingle.infoattribute}' == 2) {
            var content_text = ue.getContent();
            if (content_text == "") {
                alert("请填写内容");
                return false;
            } else {
                $("#content-form").submit();
            }
        }

        //下拉不能为null
        if ('${tenderFrontEndSingle.infoattribute}' == 3) {
            if ($("#sele").val() == "") {
                alert("请选择");
                return false;
            } else {
                $("#content-form").submit();
            }
        }

        //标签不能为null
        if ('${tenderFrontEndSingle.isfixed}' == 2 && '${tenderFrontEndSingle.infoattribute}' == 4) {
            if ($("#biaoQian").val() == "") {
                alert("请选择");
                return false;
            } else {
                $("#content-form").submit();
            }
        }


    }

    //初始化UEditor
    var ue = UE.getEditor('myEditor', {
        initialFrameWidth: 1000,
        initialFrameHeight: 350
    });

</script>
</body>
</html>