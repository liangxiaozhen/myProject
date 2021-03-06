<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    var basePath = "${pageContext.request.contextPath}";
</script>
<link href="${pageContext.request.contextPath}/resources/resource/Css/borrowing.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/Js/jiekuan/jiekuan.js"></script>

<style type="text/css">
    #YSF-BTN-HOLDER {
        position: fixed;
        max-width: 70px;
        max-height: 70px;
        right: 30px;
        bottom: 0px;
        cursor: pointer;
        overflow: visible;
        filter: alpha(opacity=100);
        opacity: 1;
        z-index: 9990
    }

    #YSF-BTN-HOLDER:hover {
        filter: alpha(opacity=95);
        opacity: .95
    }

    #YSF-BTN-HOLDER img {
        display: block;
        overflow: hidden;
    }

    #YSF-BTN-CIRCLE {
        display: none;
        position: absolute;
        right: -5px;
        top: -5px;
        width: auto;
        min-width: 12px;
        height: 20px;
        padding: 0 4px;
        background-color: #f00;
        font-size: 12px;
        line-height: 20px;
        color: #fff;
        text-align: center;
        white-space: nowrap;
        font-family: sans-serif;
        border-radius: 10px;
        z-index: 1;
    }

    #YSF-BTN-HOLDER.layer-1 #YSF-BTN-CIRCLE {
        top: -30px;
    }

    #YSF-BTN-HOLDER.layer-2 #YSF-BTN-CIRCLE {
        top: -30px;
    }

    #YSF-BTN-HOLDER.layer-3 #YSF-BTN-CIRCLE {
        top: -30px;
    }

    #YSF-BTN-HOLDER.layer-4 #YSF-BTN-CIRCLE {
        top: -30px;
    }

    #YSF-BTN-HOLDER.layer-5 #YSF-BTN-CIRCLE {
        top: -30px;
    }

    #YSF-BTN-HOLDER.layer-6 #YSF-BTN-CIRCLE {
        top: -5px;
    }

    #YSF-BTN-BUBBLE {
        display: none;
        position: absolute;
        left: -274px;
        bottom: 0px;
        width: 278px;
        height: 80px;
        box-sizing: border-box;
        padding: 14px 22px;
        filter: alpha(opacity=100);
        opacity: 1;
        background: url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg2x.png) no-repeat;
        background: url(https://qiyukf.com/sdk//res/img/sdk/bg_floatMsg.png) 9;
        background-size: 278px 80px;
        z-index: 1;
    }

    #YSF-BTN-HOLDER.layer-1 #YSF-BTN-BUBBLE {
        bottom: 9px;
    }

    #YSF-BTN-HOLDER.layer-2 #YSF-BTN-BUBBLE {
        bottom: 9px;
    }

    #YSF-BTN-HOLDER.layer-3 #YSF-BTN-BUBBLE {
        bottom: 9px;
    }

    #YSF-BTN-HOLDER.layer-4 #YSF-BTN-BUBBLE {
        bottom: 9px;
    }

    #YSF-BTN-HOLDER.layer-5 #YSF-BTN-BUBBLE {
        bottom: 9px;
    }

    #YSF-BTN-HOLDER.layer-6 #YSF-BTN-BUBBLE {
        bottom: -6px;
    }

    #YSF-BTN-BUBBLE:hover {
        filter: alpha(opacity=95);
        opacity: .95
    }

    #YSF-BTN-CONTENT {
        height: 45px;
        padding: 0;
        white-space: normal;
        word-break: break-all;
        text-align: left;
        font-size: 14px;
        line-height: 1.6;
        color: #222;
        overflow: hidden;
        z-index: 0;
    }

    #YSF-BTN-ARROW {
        display: none;
    }

    #YSF-BTN-CLOSE {
        position: absolute;
        width: 15px;
        height: 15px;
        right: 4px;
        top: -3px;
        filter: alpha(opacity=90);
        opacity: .9;
        cursor: pointer;
        background: url(https://qiyukf.com/sdk//res/img/sdk/btn-close.png) no-repeat;
        z-index: 1
    }

    #YSF-BTN-CLOSE:hover {
        filter: alpha(opacity=100);
        opacity: 1;
    }

    #YSF-PANEL-CORPINFO.ysf-chat-layeropen {
        width: 511px;
        height: 470px;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);
    }

    #YSF-PANEL-CORPINFO {
        position: fixed;
        bottom: 0px;
        right: 20px;
        width: 0;
        height: 0;
        z-index: 99999;
    }

    #YSF-PANEL-INFO.ysf-chat-layeropen {
        width: 311px;
        height: 470px;
        filter: alpha(opacity=100);
        opacity: 1;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, .15);
    }

    #YSF-PANEL-INFO {
        position: fixed;
        bottom: 0px;
        right: 20px;
        width: 0px;
        height: 0px;
        filter: alpha(opacity=0);
        opacity: 0;
        z-index: 99999;
    }

    #YSF-PANEL-INFO .u-btn {
        background-color: #F2A654
    }

    #YSF-CUSTOM-ENTRY {
        background-color: #F96868;
    }

    #YSF-CUSTOM-ENTRY-0 {
        position: relative;
        bottom: 24px;
        width: auto;
        background-color: #F2A654;
        box-shadow: 0px 6px 10px 0px rgba(0, 0, 0, 0.25);
    }

    #YSF-CUSTOM-ENTRY-1 {
        position: relative;
        bottom: 24px;
        width: auto;
        background-color: #F2A654;
        border-radius: 14px;
        box-shadow: 0px 6px 10px 0px rgba(0, 0, 0, 0.25);
    }

    #YSF-CUSTOM-ENTRY-2 {
        position: relative;
        bottom: 24px;
        width: auto;
        background-color: #F2A654;
        border-radius: 0;
        box-shadow: 0px 6px 10px 0px rgba(0, 0, 0, 0.25);
    }

    #YSF-CUSTOM-ENTRY-3 {
        position: relative;
        bottom: 24px;
        width: auto;
        background-color: #F2A654;
        border-radius: 50%;
        box-shadow: 0px 6px 10px 0px rgba(0, 0, 0, 0.25);
    }

    #YSF-CUSTOM-ENTRY-4 {
        position: relative;
        bottom: 24px;
        width: auto;
        background-color: #F2A654;
        border-radius: 50%;
        box-shadow: 0px 6px 10px 0px rgba(0, 0, 0, 0.25);
    }

    #YSF-CUSTOM-ENTRY-5 {
        position: relative;
        bottom: 24px;
        width: auto;
        background-color: #F2A654;
        border-radius: 5px;
        box-shadow: 0px 6px 10px 0px rgba(0, 0, 0, 0.25);
    }

    #YSF-CUSTOM-ENTRY-6 {
        position: relative;
        bottom: 0px;
        width: auto;
        background-color: #F2A654;
        border-radius: 5px;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0;
    }

    #YSF-CUSTOM-ENTRY-0 img {
        max-width: 300px;
        height: auto;
    }

    #YSF-CUSTOM-ENTRY-1 img {
        width: 28px;
        height: auto;
    }

    #YSF-CUSTOM-ENTRY-2 img {
        width: 58px;
        height: auto;
    }

    #YSF-CUSTOM-ENTRY-3 img {
        width: 60px;
        height: auto;
    }

    #YSF-CUSTOM-ENTRY-4 img {
        width: 60px;
        height: auto;
    }

    #YSF-CUSTOM-ENTRY-5 img {
        width: 60px;
        height: auto;
    }

    #YSF-CUSTOM-ENTRY-6 img {
        width: 115px;
        height: auto;
    }

    #YSF-IFRAME-LAYER {
        border: 0;
        outline: none;
    }

    .ysf-online-invite-mask {
        z-index: 10000;
        position: fixed;
        _position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        width: 100%;
        height: 100%;
        background: #fff;
        -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
        filter: alpha(opacity=0);
        opacity: 0;
    }

    .ysf-online-invite-wrap {
        z-index: 10001;
        position: fixed;
        _position: absolute;
        top: 50%;
        left: 50%;
        width: 250px;
    }

    .ysf-online-invite {
        position: relative;
        top: -50%;
        left: -50%;
        cursor: pointer;
    }

    .ysf-online-invite img {
        display: block;
        width: 250px;
    }

    .ysf-online-invite .text {
        position: absolute;
        top: -11px;
        left: 0;
        right: 0;
        overflow: hidden;
        margin: 36px 20px 0 67px;
        line-height: 140%;
        color: #526069;
        font-size: 14px;
        font-family: "Microsoft YaHei", "微软雅黑", tahoma, arial, simsun, "宋体";
        text-align: left;
        white-space: normal;
        word-wrap: break-word;
    }

    .ysf-online-invite .close {
        position: absolute;
        top: -6px;
        right: -6px;
        width: 32px;
        height: 32px;
        background: url(https://qiyukf.com/sdk/res/img/invite-close.png) no-repeat;
        cursor: pointer;
    }

    .btnserch {
        width: 50px;
        height: 20px;
        text-align: center;
        color: #fff;
        line-height: 20px;
        font-family: "微软雅黑";
        font-size: 14px;
        margin-left: 10px;
        border-top-color: currentColor;
        border-right-color: currentColor;
        border-bottom-color: currentColor;
        border-left-color: currentColor;
        border-style: none;
        display: inline-block;
        background-color: rgb(255, 153, 0);
        cursor: pointer;
    }
</style>

<!-- 头部-->
<include file="Index/header"/>
<!--左侧--> <!-- 头部-->
<include file="Message/mes"/>
<!--left nav end-->
<div class="fl perCerterR  bor_r bor_l">
    <div class="fl pad_t30 pad_r30 pad_b40 pad_l30 wid_w900 min_height clearfix">
        <div class="loadDiv fc_9 clearfix">
            <i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">普通资料</span>
        </div>
        <!-- 列表 -->

        <div class="investment_f">

            <form class="form-horizontal text-center" role="form" method="post" id="form1" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/user/common/adduserCommon.action">
                <div class="investment_con">
                    <div class="investment_con_list">
                        <div class="containe" style="margin-top: 25px;">
                            <input type="hidden" name="baseid" value="${user.id}"/>
                            <c:if test="${!empty loanItemQuotes}">
                                <c:forEach items="${loanItemQuotes}" var="loanItemQuote" varStatus="stat">
                                    <!-- 引用资料名称 -->
                                    <input type="hidden" name="UserCommonMaterials[${stat.index}].materialname"
                                           value="${loanItemQuote.quotename}"/>
                                    <!-- 引用资料编号 -->
                                    <input type="hidden" name="UserCommonMaterials[${stat.index}].liqno"
                                           value="${loanItemQuote.liqno}"/>
                                    <div class="form-group">
                                        <!-- 自填类 -->
                                        <c:if test="${loanItemQuote.quoteproperty==1}">
                                            <%--文本类--%>
                                            <c:if test="${loanItemQuote.loanInfoNeed.infotype==2}">
                                                <ul>
                                                    <li><label
                                                            class="col-sm-3 control-label">${loanItemQuote.quotename}：</label>
                                                    </li>
                                                    <li><input type="text"
                                                               name="UserCommonMaterials[${stat.index}].materialcontent"
                                                               class="form-control"
                                                               maxlength="${loanItemQuote.loanInfoNeed.charlength}"/>
                                                    </li>
                                                    <li><span
                                                            style="color:red;">长度限制在${loanItemQuote.loanInfoNeed.charlength}个字符之内</span>
                                                    </li>
                                                </ul>
                                            </c:if>
                                            <%--图片类--%>
                                            <c:if test="${loanItemQuote.loanInfoNeed.infotype==1}">
                                                <ul>
                                                    <li><label
                                                            class="col-sm-3 control-label">${loanItemQuote.quotename}:</label>
                                                    </li>

                                                    <li><input type="file" id="f${stat.index}"
                                                               name="files"
                                                               class="form-control" value=""/></li>
                                                    <li><span style="color:red;">文件大小限制在1M左右</span></li>
                                                    <li><img src="" class="img" id="img${stat.index}"
                                                             style="width: 33px;height: 33px;"/></li>
                                                    <li><input type="hidden" id="pic${stat.index}"
                                                               name="UserCommonMaterials[${stat.index}].materialpic"
                                                               value=""/></li>
                                                </ul>
                                            </c:if>
                                        </c:if>

                                        <script>
                                            $("#f${stat.index}").change(function () {
                                                str = $(this).val();
                                                var arr = str.split('\\');//注split可以用字符或字符串分割
                                                var my = arr[arr.length - 1];//这就是要取得的图片名称
                                                var file = this.files[0];
                                                if (window.FileReader) {
                                                    var reader = new FileReader();
                                                    reader.readAsDataURL(file);
                                                    //监听文件读取结束后事件
                                                    reader.onloadend = function (e) {
                                                        $("#img${stat.index}").attr("src", e.target.result);
                                                        $("#pic${stat.index}").val(my);    //e.target.result就是最后的路径地址
                                                    };
                                                }
                                            });
                                        </script>

                                        <!-- 选择类填写 -->
                                        <c:if test="${loanItemQuote.quoteproperty==2}">
                                            <c:if test="${!empty loanItemQuote.loanInfoPreset}">
                                                <div class="form-group">
                                                    <ul>
                                                        <li><label
                                                                class="col-sm-3 control-label">${loanItemQuote.quotename}：</label>
                                                        </li>
                                                        <!-- 单选 -->
                                                        <c:if test="${loanItemQuote.loanInfoPreset.oneormulti==1}">
                                                            <li>
                                                                <div class="col-sm-3">
                                                                    <select name="UserCommonMaterials[${stat.index}].materialcontent"
                                                                            class="form-control">
                                                                        <option value="">请选择</option>
                                                                        <c:forEach
                                                                                items="${loanItemQuote.loanInfoPreset.multiContentSets}"
                                                                                var="tent">
                                                                            <option value="${tent.optionname}">${tent.optionname}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </li>
                                                        </c:if>
                                                        <!-- 多选 -->
                                                        <c:if test="${loanItemQuote.loanInfoPreset.oneormulti==2}">
                                                            <li>
                                                                <div class="col-sm-6 text-left">
                                                                    <c:forEach
                                                                            items="${loanItemQuote.loanInfoPreset.multiContentSets}"
                                                                            var="tent">
                                                                        <label class="checkbox-inline">
                                                                            <input type="checkbox"
                                                                                   name="UserCommonMaterials[${stat.index}].materialcontent"
                                                                                   id="intugrades"
                                                                                   value="${tent.optionname}"/>${tent.optionname}
                                                                        </label>
                                                                    </c:forEach>
                                                                </div>
                                                            </li>
                                                        </c:if>
                                                    </ul>
                                                </div>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </c:forEach>
                                <div style="display: none"><input type="file" name="files" class="form-control"/></div>
                            </c:if>
                        </div>
                    </div>
                    <div>
                        <input type="submit" class="btn btn-default" value="保存">
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</P>

</div>


</div>
</div>

</div>
</div>

