<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="fl perCerterR bor_l">
    <div class="fl pad_t30 pad_r30 pad_b40 pad_l30 wid_w900 clearfix">
        <div class="loadDiv fc_9 clearfix">
            <i class="nwd_icon nwd_icon_mianbaoxie"></i>
            <span class="fc_6">消息中心</span>
        </div>
        <div class="fl pad_t20 tab indexTab clearfix indexHandle ui-select-listBox messagetab">
            <ul class="tab_title clearfix">
                <li class="fl ui-select-listBox-list ui-select-listBox-list--now active" value="1"><a href="javascript:;" class="pad_b10">提醒消息</a></li>
                <li class="fl ui-select-listBox-list" value="2"><a href="javascript:;" class="pad_b10">消息设置</a></li>
            </ul>
            <div class="ui-select-listBox-line">
                <div class="ui-select-listBox-l-blue" style="left: 20px; width: 83.3333px;"></div>
            </div>
        </div>
        <div class="tab_content clearfix">
            <!-- 提醒消息 start -->
            <div class="tab_box tab_box_one box box1" style="display: block;" id="myForm1">
                <jsp:include page="membermsgTemp.jsp"></jsp:include>
            </div>
            <!-- 提醒消息 end -->
            <div class="tab_box box box2" style="display: none;" >
                <div class="clearfix">
                    <!-- 我是投资人 start -->
                    <jsp:include page="membermsgTemp2.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="plusBankBg" style="height: 1192px; display: none;"></div>
<!-- 弹出 -->

<div class="plusBank1 mini" style="display: none; margin-left:-600px;">
    <div class="topper clearfix">
        <span class="fl fs_18">提示</span>
        <a class="fr nwd_icon plus_c"></a>
    </div>
    <div class="middle">
        <div class="content">
            消息提醒设置保存成功！
        </div>
        <div class="btnbox">
            <button type="button" class="btn btnSize_1 btn_blue" id="confirmBtn">确认</button>
        </div>
    </div>
</div>

<script type="text/javascript">
    function singleUpdateIsRead(id,isRead){
        if(isRead!=1){
            $.post("${pageContext.request.contextPath}/user/msg/singleIsRead.action", {id:id}, function (data) {});
        }
    }
    function batchUpdateIsRead(){
        var ids=selectCheckId().join("-");
        if(ids==""){
            alert("请选择要标记的消息！");
        }else{
            $.post("${pageContext.request.contextPath}/user/msg/batchIsRead.action", {ids:ids}, function (data) {
                history.go(0);
            });
        }
    }
    function delete1(){

        var ids=selectCheckId().join("-");
        if(ids==""){
            alert("请选择要删除的消息！");
        }else{
            $.post("${pageContext.request.contextPath}/user/msg/deleteMsgList.action", {ids:ids}, function (data) {
                history.go(0);
            });

        }

    }
    function selectCheckId() {
        var text=new Array();
        $("input[name='checkval']").each(function(index,element) {
            if ($(this).attr("checked")) {
                var aa=$(this).val();
                text.push(aa);
            }
        });
        return text;
    }


    function openMsg(id,isRead){
        $("#"+id+"content").slideToggle();
        $("#" + id + "ico").addClass("gray");
        singleUpdateIsRead(id,isRead);
    }
    $(document).ready(function(){
        $(function(){
            $("#msgSetForm input[type='checkbox']").change(function() {
                if ($(this).is(":checked")) {
                    this.value=1;
                }else {
                    this.value=0;
                }
            });
        });

        $("#msgSetForm input:checkbox[value='1']").attr('checked','true');


        $(".box2").hide();
        $(".tab_title.clearfix  li").click(function(){
            $(".tab_title.clearfix  li").removeClass("active")
            var num = $(this).val();
            $(this).addClass("active")
            $(".box").hide();
            $(".box"+num).show();
        });


        showDiv();
        function showDiv(obj){
            $(obj).show();
            center(obj);
            $(window).scroll(function(){
                center(obj);
            });
            $(window).resize(function(){
                center(obj);
            });
        }
        function center(obj){
            var windowWidth = document.documentElement.clientWidth;
            var windowHeight = document.documentElement.clientHeight;
            var popupHeight = $(obj).height();
            var popupWidth = $(obj).width();
            $(obj).css({
                "position": "absolute",
                "top": (windowHeight-popupHeight)/2+$(document).scrollTop(),
                "left": (windowWidth-popupWidth)/2
            });
        }

        //保存
        $("#save").click(function(){
            //$(".plusBank1.mini").show();
            var d=$("#msgSetForm").serialize();
            $.post("${pageContext.request.contextPath}/user/msg/saveUserMsgSet.action", d, function (result) {
                $(".plusBankBg").show();
                showDiv(".plusBank1.mini");
            });

        });
        //关闭
        $(".fr.nwd_icon.plus_c").click(function(){
            $(".plusBank1.mini,.plusBankBg").hide();
        })
        $("#confirmBtn").click(function(){
            $(".plusBank1.mini,.plusBankBg").hide();
        })

    })

    //分页查询通用方法
    function queryAllPerson(pageNo,pageSize){
        location.href="${pageContext.request.contextPath}/user/msg/toMemberMsg.action?pageNum="+pageNo+"&pageSize="+pageSize;
    }

</script>

