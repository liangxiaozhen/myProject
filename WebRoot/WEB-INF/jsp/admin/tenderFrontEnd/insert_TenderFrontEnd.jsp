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
    <link href="${pageContext.request.contextPath }/js/sg/css/sg.css"
          rel="stylesheet" type="text/css">

    <link href="${pageContext.request.contextPath }/BootstrapFileInput/css/fileinput.css" media="all" rel="stylesheet"
          type="text/css"/>

</head>
<body>
<form id="form" enctype="multipart/form-data" method="post"
      action="${pageContext.request.contextPath}/admin/tenderFrontEnd/insert.action">
    <div class="row" style="line-height: 0px;">
        <div class="col-md-4 col-md-offset-1" style="padding-left: 30px;">
            <font color="red"><b>标的前端信息设置</b></font>
        </div>
        <hr>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            标的类型：
            <select name="ttypename" id="ttypename">
                <c:if test="${!empty loanTypeObjects}">
                    <option value="">---请选择---</option>
                    <c:forEach items="${loanTypeObjects}" var="sn">
                        <option value="${sn.objectname},${sn.id}">${sn.objectname}</option>
                    </c:forEach>
                </c:if>
            </select><span style="color: red;"><label id="insert-ttypename-lb"></label></span>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            资料类别：
            <select name="infotype" id="infotype">
                <c:if test="${!empty tenderFeiTypes}">
                    <option value="">---请选择---</option>
                    <c:forEach items="${tenderFeiTypes}" var="sn">
                        <option value="${sn.id}">${sn.typename}</option>
                    </c:forEach>
                </c:if>
            </select><span style="color: red;"><label id="insert-infotype-lb"></label></span>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            项目名称： <input type="text" name="infoname" id="infoname"><span style="color: red;"><label
                id="insert-infoname-lb"></label></span>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源：
            <select name="source" id="source" onchange="itemSource(this)">
                <option value="">请选择</option>
                <option value="1">新增</option>
                <option value="2">引用</option>
            </select><span style="color: red;"><label id="insert-source-lb"></label></span>
        </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            应用范围：
            <select name="isfixed" id="isfixed" onchange="appScope(this)">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <hr>
    <div id="infoattributediv">
        <div class="row">
            <div class="col-md-6 col-md-offset-1">
                内容属性：
                <select name="infoattribute" id="infoattribute" onchange="contentAttribute(this)">
                    <option value="">请选择</option>

                </select>
            </div>
        </div>
        <hr>
    </div>
    <div id="contentdiv">
        <div class="row">
            <%--图片--%>
            <div class="col-md-6 col-md-offset-1" id="content1">
                <%--内容:<input type="file" name="files" id="file"/>
                <img src="" class="img" id="img"
                     style="width: 33px;height: 33px;"/>--%>

                <input id="file-es" name="files" type="file" multiple>


            </div>
            <%--文本--%>
            <div class="col-md-6 col-md-offset-1" id="content2">
                内容:<textarea id="myEditor" name="aabb"></textarea>
            </div>
            <%--下拉--%>
            <div class="col-md-6 col-md-offset-1" id="content3">
                <div>
                    <span>选项1:</span>
                    <input type="text" value="" name="content_select"/><input type="button" onclick="addDiv(this)"
                                                                       value="+"
                                                                       style="width: 25px;height: 25px;background:#C9C5C5;">
                </div>
            </div>

            <%--标签--%>
            <div class="col-md-6 col-md-offset-1" id="content4">
                选择标签：
                <select name="content_label" id="content_label">
                    <option value="">请选择</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                </select>
            </div>
            <%--标签--%>
            <div class="col-md-6 col-md-offset-1" id="content5">
                系统自动初始化7个标签选项:1，2，3，4，5，6，7
            </div>
        </div>
        <input type="hidden" id="content_text" name="content_text"/>
        <input type="hidden" id="content_select" name="content"/>
        <span id="insert-content-lb"></span>

        <hr>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
            <select name="status" id="status">
                <option value="">请选择</option>
                <option value="1">启用</option>
                <option value="0">停用</option>
            </select><span style="color: red;"><label id="insert-status-lb"></label></span>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            是否同步存量标：
            <select name="issynchisbid" id="issynchisbid">
                <option value="">请选择</option>
                <option value="0">不同步</option>
                <option value="1">同步待投标的</option>
                <option value="2">同步待投标的 + 投标中的标的</option>
            </select><span style="color: red;"><label id="insert-issynchisbid-lb"></label></span>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-md-6 col-md-offset-1">
            <button type="button" class="btn btn-default"  onclick="severCheck();">保存</button>

            &nbsp;&nbsp;
            <button type="button" class="btn btn-default"
                    onclick="javascript:history.go(-1);">取消
            </button>
        </div>
    </div>
    <div style="display: none"><input type="file" name="files" class="form-control"/></div>
</form>

<script type="text/javascript"
        src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/calendar/WdatePicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/sg/sgutil.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/sg/sg.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/common/common.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/ueditor/ueditor.all.js"></script>

<script src="${pageContext.request.contextPath }/BootstrapFileInput/js/fileinput.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/jquery.metadata.js"></script>
<script src="${pageContext.request.contextPath}/js/validate/messages_zh.js"></script>
<script>
    var arrIsfixed = [{"id": "1", "value": "通用"}, {"id": "2", "value": "唯一"}];
    var arrInfoattribute1 = [{"id": "1", "value": "图片"},{ "id": "2", "value": "文字"},{"id":"3","value":"下拉"},{"id":"4","value":"标签"}]
    var arrInfoattribute2 = [{"id": "1", "value": "图片"},{ "id": "2", "value": "文字"},{"id":"4","value":"标签"}]
    var isfixed = $("#isfixed");
    var infoattribute = $("#infoattribute");
    isfixed.attr("disabled", "true");
    infoattribute.attr("disabled", "true");
    //来源事件
    function itemSource(t) {
        var source = $(t).val();
        var str = "<option value=''>请选择</option>";
        //新增
        if (source == 1) {
            for (var i = 0; i < arrIsfixed.length; i++) {
                str += "<option value='" + arrIsfixed[i].id + "'>" + arrIsfixed[i].value + "</option>";
            }
            isfixed.html(str);
            isfixed.removeAttr("disabled");
            infoattribute.html("<option value=''>请选择</option>");
            infoattribute.attr("disabled", "true");
            $("#infoattributediv").show();
            //引用
        } else if (source == 2) {
            //唯一
            str = "<option value='" + arrIsfixed[1].id + "'>" + arrIsfixed[1].value + "</option>";
            isfixed.html(str);
            isfixed.attr("disabled", "true");
            $("#infoattribute").val("");
            $("#infoattributediv").hide();
            handleContentDiv(ue);
        } else {
            isfixed.html(str);
            isfixed.attr("disabled", "true");
            $("#infoattributediv").hide();
            handleContentDiv(ue);
        }

    }
    //清空内容div并隐藏
    function handleContentDiv(ue) {
//直接把图片内容div隐藏
        $("#content1").hide();
        $(".fileinput-remove-button").trigger("click");
        //清空富文本的内容
        ue.execCommand('cleardoc');
        //清空下拉内容
        $("#content3 input[name^='content_select']").val("");
        //清空标签内容
        $("#content_label").val("");
        //内容是图片的div隐藏
        $("#content1").hide();
        //内容是文本的div隐藏
        $("#content2").hide();
        //内容是下拉的div隐藏
        $("#content3").hide();
        //应用范围是通用时，内容是标签的div隐藏
        $("#content4").hide();
        //应用范围是唯一时，内容是标签的div隐藏
        $("#content5").hide();
        //内容的父div隐藏
        $("#contentdiv").hide();
    }
    function appScope(t) {
        var isfixed = $(t).val();
        var str = "<option value=''>请选择</option>";
        //通用
        if (isfixed == 1) {
            for (var i = 0; i < arrInfoattribute2.length; i++) {
                str += "<option value='" + arrInfoattribute2[i].id + "'>" + arrInfoattribute2[i].value + "</option>";
            }
            infoattribute.html(str);
            infoattribute.removeAttr("disabled");

            handleContentDiv(ue);
        //唯一
        } else if (isfixed == 2) {
            for (var i = 0; i < arrInfoattribute1.length; i++) {
                str += "<option value='" + arrInfoattribute1[i].id + "'>" + arrInfoattribute1[i].value + "</option>";
            }
            infoattribute.html(str);
            infoattribute.removeAttr("disabled");

            handleContentDiv(ue);
        }else{
            infoattribute.html(str);
            infoattribute.attr("disabled", "true");

            handleContentDiv(ue);
        }
    }
    //内容属性改变时
    function contentAttribute(v) {
        var attributeVal = $(v).val();
        //应用范围:1通用  2.唯一
        var isfixed = $("#isfixed").val();
        if (isfixed == 1) {
            //应用范围:1通用
            $("#contentdiv").show();
            //图片(content在后台赋值上传文件时获取文件名再赋值的)
            if (attributeVal == 1) {
                $("#content1").show();
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空标签内容
                $("#content_label").val("");
                //内容是文本的div隐藏
                $("#content2").hide();

                //应用范围是通用时，内容是标签的div隐藏
                $("#content4").hide();

                //文本的内容是在提交时通过js赋值的
            } else if (attributeVal == 2) {
                $("#content2").show();
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                $(".fileinput-remove-button").trigger("click");

                //应用范围是通用时，内容是标签的div隐藏
                $("#content4").hide();

                //标签
            } else if (attributeVal == 4) {
                //应用范围是唯一时，内容是标签的div显示
                $("#content4").show();
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                $(".fileinput-remove-button").trigger("click");
                //内容是文本的div隐藏
                $("#content2").hide();


            }else{
                //直接把图片内容div隐藏
                $("#content1").hide();
                $(".fileinput-remove-button").trigger("click");
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空标签内容
                $("#content_label").val("");
                $("#content1").hide();
                $("#content2").hide();
                $("#content4").hide();
            }
            //应用范围:1唯一
        } else if (isfixed == 2) {
            if (attributeVal == 1) {
                $("#contentdiv").hide();
                //清空下拉内容
                $("#content3 input[name^='content']").val("");
                //清空富文本的内容
                ue.execCommand('cleardoc');

                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();

                //应用范围是唯一时，内容是标签的div隐藏
                $("#content5").hide();
                //文本
            } else if (attributeVal == 2) {
                $("#contentdiv").hide();
                //清空下拉内容
                $("#content3 input[name^='content']").val("");
                //清空富文本的内容
                ue.execCommand('cleardoc');

                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();

                //应用范围是唯一时，内容是标签的div隐藏
                $("#content5").hide();
                //下拉
            } else if (attributeVal == 3) {
                $("#contentdiv").show();
                //清空下拉内容
                $("#content3 input[name^='content']").val("");
                //内容是下拉的div显示
                $("#content3").show();
                //清空富文本的内容
                ue.execCommand('cleardoc');

                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //应用范围是通用时，内容是标签的div隐藏

                $("#content5").hide();
                //标签
            } else if (attributeVal == 4) {
                $("#contentdiv").show();
                //应用范围是通用时，内容是标签的div显示
                $("#content5").show();
                //清空下拉内容
                $("#content3 input[name^='content']").val("");
                //清空富文本的内容
                ue.execCommand('cleardoc');

                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();
            }
        }
    }


</script>
<script type="text/javascript">
    //内容为下拉的选项事件
    var i = 1;
    function addDiv(t) {
        i++;
        var parent = $(t).parent().parent();
        var newDiv = $("<div><br/><span>选项" + i + ": </span><input type='text' value='' name='content_select" + i + "'  class='required'/><input type='button' onclick='delDiv(this)' value='-' style='width: 25px;height: 25px;background:#C9C5C5;'></div>");
        parent.append(newDiv);
    }
    function delDiv(t) {
        $(t).parent().remove();
        i--;
    }
    //客户端验证
    function check() {
        return $("#form").validate({
            rules: {
                ttypename: {
                    required: true,
                },
                infotype:{
                    required:true,
                },
                infoname: {
                    required: true,
                    maxlength: 18,
                },
                source: {
                    required: true,
                },
                isfixed: {
                    required: true,
                },
                infoattribute: {
                    required: true,
                },
                files: {
                    required: true,
                },
                content_select: {
                    required: true,
                },
                content_label: {
                    required: true,
                },
                status: {
                    required: true,
                },
                issynchisbid: {
                    required: true,
                },
            },
            messages: {
                ttypename: {
                    required: "必填",
                },
                infoname: {
                    required: "必填",
                    maxlength: 18,
                },
                source: {
                    required: "必填",
                },
                isfixed: {
                    required: "必填",
                },
                infoattribute: {
                    required: "必填",
                },
                files: {
                    required: "必填",
                },
                content: {
                    required: "必填",
                },
                content_label: {
                    required: "必填",
                },
                status: {
                    required: "必填",
                },
                issynchisbid: {
                    required: "必填",
                },
            }
        }).form();
    }


    //服务端验证
    function severCheck() {
        if (check()) {
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
            var content_select="";
            var infoattibute=$("#infoattribute").val();
            if(infoattibute==3){
                //下拉内容处理
                $("#content3 input[name^='content_select']").each(function () {
                    content_select+=$(this).val()+",";
                })
                $("#content_select").val(content_select);
            }

            $("#isfixed").removeAttr("disabled");

            var isfixed = $("#isfixed").val();

            //通用且文本
            var infoattribute = ($("#infoattribute").val());
            if (isfixed==1&&infoattribute == 2) {
                //把富文本的内容作为content的值
                var content_text=ue.getContent();
                if(content_text==null||content_text==""){
                    alert("请填写内容");
                    return false;
                }
                $("#content_text").val(ue.getContent());
            }
            //如果是图片，那就在后台处理文件的时候处理
            $("#form").submit();
        }
    }

    function clearInput() {
        $("#form")[0].reset()
    }


    //初始化UEditor
    var ue = UE.getEditor("myEditor", {
        initialFrameWidth: 1000,
        initialFrameHeight: 350
    });

    /*初始化页面*/
    $(function () {
        $("#infoattributediv").hide();
        //内容是图片的div隐藏
        $("#content1").hide();
        //内容是文本的div隐藏
        $("#content2").hide();
        //内容是下拉的div隐藏
        $("#content3").hide();
        //应用范围是通用时，内容是标签的div隐藏
        $("#content4").hide();
        //应用范围是唯一时，内容是标签的div隐藏
        $("#content5").hide();
        //内容的父div隐藏
        $("#contentdiv").hide();
    });

    //初始化多文件上传控件
    $('#file-es').fileinput({
        language: 'es',
        showUpload: false,
        showRemove: true,
        uploadUrl: '#',
        allowedFileExtensions: ['jpg', 'png', 'gif'],
    });


</script>
</body>
</html>