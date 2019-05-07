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
                <option value="1">通用</option>
                <option value="2">唯一</option>
            </select><span style="color: red;"><label id="insert-isfixed-lb"></label></span>
        </div>
    </div>
    <hr>
    <div id="infoattributediv">
        <div class="row">
            <div class="col-md-6 col-md-offset-1">
                内容属性：
                <select name="infoattribute" id="infoattribute" onchange="contentAttribute(this)">
                    <option value="">请选择</option>
                    <option value="1">图片</option>
                    <option value="2">文字</option>
                    <option value="3" id="option3">下拉</option>
                    <option value="4" id="option4">标签</option>
                </select><span style="color: red;"><label id="insert-infoattribute-lb"></label></span>
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


                <script>


                </script>
            </div>
            <%--文本--%>
            <div class="col-md-6 col-md-offset-1" id="content2">
                内容:<textarea id="myEditor" name="content2"></textarea>
            </div>
            <%--下拉--%>
            <div class="col-md-6 col-md-offset-1" id="content3">
                <div>
                    <span>选项1:</span>
                    <input type="text" value="" name="content" id=""/><input type="button" onclick="addDiv(this)"
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
        <input type="hidden" id="content_text" name="content_text">
        <span id="insert-content-lb"></span>
        <hr>

    </div>
    <%--下拉项目输入--%>
    <script>

    </script>
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
            <button type="button" class="btn btn-default" value="保存" onclick="severCheck();"/>

            &nbsp;&nbsp;
            <button type="button" class="btn btn-default"
                    onclick="javascript:history.go(-1);">取消
            </button>
        </div>
    </div>
    <div style="display: none"><input type="file" name="files" class="form-control"/></div>
</form>
<script type="text/javascript">

</script>
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
<script type="text/javascript">

    $().ready(function () {
        /*jQuery.validator.addMethod("abc", function () {
         var flag = true;
         if( $("#loanUserName").val()=="xxyy"){
         flag=false;
         }
         return flag;
         }, "xxxxxxx");*/
    });
    //客户端验证
    function check() {
        return $("#form").validate({
            rules: {
                ttypename: {
                    required: true,
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
                content2: {
                    required: true,
                },
                content: {
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
                content2: {
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
        $("input[name='content']").each(function(){
            alert(this.value);
            
        });
        if (check()) {
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

    //来源改变事件
    function itemSource(t) {
        var val = $(t).val();
        //来源为引用时
        if (val == 2) {
            //把内容属性设置为空
            $("#infoattribute").val("");
            $("#infoattributediv").hide();

            //把内容div隐藏
            $("#contentdiv").hide();

            //把应用范围设置为唯一
            $("#isfixed").val(2);
            $("#isfixed").attr("disabled", "disabled");

            //来源为新增
        } else if (val = 1) {
            $("#infoattributediv").show();
            $("#isfixed").removeAttr("disabled");
            $("#isfixed").val("");
        }
    }

    //应用范围改变事件    （通用时，内容属性有3种。唯一时，内容属性有4种）
    function appScope(t) {
        $("#infoattribute").val("");
        var scope = $(t).val();
        //2:唯一
        if (scope == 2) {
            //内容div隐藏
            $("#contentdiv").hide();
            $("#option3").css("display", "block");
            $("#option4").css("display", "block");
        //1:通用
        } else if (scope == 1) {
            //内容div隐藏
            $("#contentdiv").hide();
            $("#option3").css("display", "none");
        }
    }

    //内容属性改变时
    function contentAttribute(v) {
        var attributeVal = $(v).val();
        //应用范围:1通用  2.唯一
        var isfixed = $("#isfixed").val();
        //应用范围:1通用
        $("#contentdiv").show();
        if (isfixed == 1) {
            //图片(content在后台赋值上传文件时获取文件名再赋值)
            if (attributeVal == 1) {
                $("#content1").show();
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空下拉内容
                $("#content3 input[name='content']").val("");
                //清空标签内容
                $("#content_label").val("");
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();
                //应用范围是通用时，内容是标签的div隐藏
                $("#content4").hide();
                //应用范围是唯一时，内容是标签的div隐藏
                $("#content5").hide();
                //文本的内容是在提交时通过js赋值的
            } else if (attributeVal == 2) {
                $("#content2").show();
                //清空下拉内容
                $("#content3 input[name='content']").val("");
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();
                //应用范围是通用时，内容是标签的div隐藏
                $("#content4").hide();
                //应用范围是唯一时，内容是标签的div隐藏
                $("#content5").hide();
                //标签
            } else if (attributeVal == 4) {
                //应用范围是唯一时，内容是标签的div显示
                $("#content4").show();
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空下拉内容
                $("#content3 input[name='content']").val("");
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();
                //应用范围是通用时，内容是标签的div隐藏
                $("#content5").hide();
            }
            //应用范围:1唯一
        } else if (isfixed == 2) {
            if (attributeVal == 1) {
                //清空下拉内容
                $("#content3 input[name='content']").val("");
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();
                //应用范围是通用时，内容是标签的div隐藏
                $("#content4").hide();
                //应用范围是唯一时，内容是标签的div隐藏
                $("#content5").hide();
                //文本
            } else if (attributeVal == 2) {
                //清空下拉内容
                $("#content3 input[name='content']").val("");
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();
                //应用范围是通用时，内容是标签的div隐藏
                $("#content4").hide();
                //应用范围是唯一时，内容是标签的div隐藏
                $("#content5").hide();
                //下拉
            } else if (attributeVal == 3) {
                //清空下拉内容
                $("#content3 input[name='content']").val("");
                //内容是下拉的div显示
                $("#content3").show();
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //应用范围是通用时，内容是标签的div隐藏
                $("#content4").hide();
                //应用范围是唯一时，内容是标签的div隐藏
                $("#content5").hide();
                //标签
            } else if (attributeVal == 4) {
                //应用范围是通用时，内容是标签的div显示
                $("#content5").show();
                //清空下拉内容
                $("#content3 input[name='content']").val("");
                //清空富文本的内容
                ue.execCommand('cleardoc');
                //清空标签内容
                $("#content_label").val("");
                //直接把图片内容div隐藏
                $("#content1").hide();
                //内容是文本的div隐藏
                $("#content2").hide();
                //内容是下拉的div隐藏
                $("#content3").hide();
                //应用范围是唯一时，内容是标签的div隐藏
                $("#content4").hide();
            }
        }


    }
    var i = 1;
    function addDiv(t) {
        i++;
        var parent = $(t).parent().parent();
        var newDiv = $("<div><br/><span>选项" + i + ": </span><input type='text' value='' name='content' id=''/><input type='button' onclick='delDiv(this)' value='-' style='width: 25px;height: 25px;background:#C9C5C5;'></div>");
        parent.append(newDiv);
    }
    function delDiv(t) {
        $(t).parent().remove();
        i--;
    }

    /*//增加校验
    function validateInsert() {

        var ttypename = $("#ttypename").val();
        if (ttypename == null || ttypename == "") {
            $("#insert-ttypename-lb").html("*必须选择标的类型");
            return false;
        }

        var infoname = $("#infoname").val();
        if (infoname == null || infoname == "") {
            $("#insert-infoname-lb").html("*必须填写项目名称");
            $("#infoname").focus();
            return false;
        }

        var source = $("#source").val();
        if (source == null || source == "") {
            $("#insert-source-lb").html("*必须选择来源");
            return false;
        }

        var isfixed = $("#isfixed").val();
        if (isfixed == null || isfixed == "") {
            $("#insert-isfixed-lb").html("*必须选择应用范围");
            return false;
        }
        //如果内容属性div是隐藏的就不去验证
        var displayinfo = $('#infoattributediv').css('display');
        if (displayinfo != 'none') {
            var infoattribute = $("#infoattribute").val();
            if (infoattribute == null || infoattribute == "") {
                $("#insert-infoattribute-lb").html("*必须选择内容属性");
                return false;
            }
        }

        var pictitleArray = $("input[name='pictitle']");
        if (pictitleArray.size() > 0) {
            for (var i = 0; i < pictitleArray.size(); i++) {
                var pictitle = $(pictitleArray[i]).val();
                if (pictitle == null || pictitle == "") {
                    alert("请填写第" + (i + 1) + "个图片的标题");
                    return false;
                }
            }
        }

        var status = $("#status").val();
        if (status == null || status == "") {
            $("#insert-status-lb").html("*必须选择状态");
            return false;
        }

        var issynchisbid = $("#issynchisbid").val();
        if (issynchisbid == null || issynchisbid == "") {
            $("#insert-issynchisbid-lb").html("*必须是否同步存量标");
            return false;
        }
        return true;
    }*/

    /*function check11() {
        $("#isfixed").removeAttr("disabled");
        //唯一
        var isfixed = $("#isfixed").val();
        //引用
        if (isfixed == 2) {
            $("#content").val("");
        }
        //文本
        var infoattribute = ($("#infoattribute").val());
        if (infoattribute == 2) {
            //把富文本的内容作为content的值
            $("#content_text").val(ue.getContent());
        }
        //如果是图片，那就在后台处理文件的时候处理
        if (validateInsert()) {
            return true;
        } else {
            return false;
        }
    }*/

</script>
</body>
</html>