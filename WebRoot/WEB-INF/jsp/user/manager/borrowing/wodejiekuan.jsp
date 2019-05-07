<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/JavaScript">
    var t = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery/1.11.3/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/resource/Scripts/visitorapi-1.2.1-min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/resource/Scripts/appmeasurement-1.2.1-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/Js/jiekuan/jiekuan.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/calendar/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath }/resources/resource/Css/nwd_percenter.css" rel="stylesheet"
      type="text/css">
<link href="${pageContext.request.contextPath }/resources/resource/Css/nwd_vipstyle.css" rel="stylesheet"
      type="text/css">
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

<!-- <include file="Index/header" />
左侧 头部
<include file="Message/mes" /> -->

<!--left nav end-->
<div class="fl perCerterR  bor_r bor_l">
    <div class="fl pad_t30 pad_r30 pad_b40 pad_l30 wid_w900 min_height clearfix">
        <div class="loadDiv fc_9 clearfix">
            <i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_9">我的借款</span>
        </div>
        <!-- 列表 -->

        <form action="${pageContext.request.contextPath}/user/loan/loanappui.action" method="post" class="nwd-formUi"
              id="selectplusByCondition">
            <input type="hidden" id="pageNum" name="pageNum"/>
            <input type="hidden" id="pageSize" name="pageSize"/>
            <div class="searchCon pad_t10 pad_b10 pad_l20 fc_9 clearfix" id="pwoer">
                <dl class="fl searchdl clearfix">
                    <dt class="fl fc_6 pad_t5">申请日期：</dt>
                    <dd class="fl a_btn">
                        <input class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime hasDatepicker" id="startdate"
                               name="startdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly">-
                        <input class="input pad_5 input_w150 nwd_icon nwd_icon_inputtime hasDatepicker" id="enddate"
                               name="enddate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})"
                               readonly="readonly">
                        <button class="btnserch" type="button" onclick="changedata()">查询</button>
                    </dd>
                    <dd class="fl a_btn interval" style="margin-top: 10px;">
                        <a href="javascript:void(0);" class="fc_6 mar_l5 mar_r5 serachDate ">全部</a>
                        <a href="javascript:void(0);" class="fc_6 mar_l5 mar_r5 serachDate ">最近一周</a>
                        <a href="javascript:void(0);" class="fc_6 mar_l5 mar_r5 serachDate ">最近一个月</a>
                        <a href="javascript:void(0);" class="fc_6 mar_l5 mar_r5 serachDate ">最近三个月</a>
                        <input type="hidden" name="period" value="${period}" id="period"/>
                        <input type="hidden" name="index" value="${index}" id="a_inex"/>
                    </dd>
                </dl>
            </div>
            <!-- 分割线 -->
            <div id="contain">
                <div class="item">
                    <div class="searchCon pad_t5 pad_b10 pad_l20 fc_9 clearfix">
                        <dl class="fl searchdl clearfix jy_more_statue" style="display: block;">
                            <dt class="fl fc_9">状态：</dt>
                            <dd class="fl a_btn ">
									<span id="screening">
	                                    <a href="javascript:void(0)" id="atype" value="-1"
                                           class="fc_6 mar_l5 mar_r5 mar_b5 ">全部</a>
	                                    <a href="javascript:void(0)" id="stype" value="0"
                                           class="fc_6 mar_l5 mar_r5 mar_b5  ">借款审核中</a>
	                                    <a href="javascript:void(0)" id="ftype" value="3"
                                           class="fc_6 mar_l5 mar_r5 mar_b5 ">招标中</a>
	                                    <a href="javascript:void(0)" id="htype" value="7"
                                           class="fc_6 mar_l5 mar_r5 mar_b5 ">借款成功</a>
	                                    <a href="javascript:void(0)" id="ktype" value="2"
                                           class="fc_6 mar_l5 mar_r5 mar_b5 ">借款失败</a>
	                                    <input type="hidden" name="appstatus" value="${appstatus}" id="Appstatus"/>
	                                    <input type="hidden" name="appindex" value="${appindex}" id="Appindex"/>
                                        <!-- <a href="javascript:void(0)" id="qtype" value="100" class="fc_6 mar_l5 mar_r5 mar_b5 ">借款已结清</a> -->
                                    </span>
                            </dd>
                        </dl>
                    </div>
                    <div class="tab_content clearfix ">
                        <jsp:include page="/WEB-INF/jsp/user/manager/borrowing/borrowingdata.jsp"></jsp:include>
                    </div>
                </div>
                <!--  <div class="item">abc</div>
                 <div class="item">1234</div>
                 <div class="item">
                    <div class="item-content"></div>
                 </div> -->
            </div>
            <div class="wujilu" id="errorMsg" style="text-align: center;">

            </div>
        </form>
    </div>
</div>

</div>
</div>
<!--layout end-->

