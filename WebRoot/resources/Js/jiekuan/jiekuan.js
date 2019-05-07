$(function () {
    $("#chabiao").hide()
    /*$("#IsMultTender").change(function () {
        var teder = $(this).val();
        if (teder == 1) {
            $("#chabiao").show();
        } else {
            $("#chabiao input").val("");
            $("#chabiao").hide();
        }
    }*/);
});


$(function () {

    var indx = $("#a_inex").val();
    $("#pwoer").find(".interval a").eq(indx).addClass("active").siblings().removeClass("active");
    var app_index = $("#Appindex").val();
    $("#contain").find("#screening a").eq(app_index).addClass("active").siblings().removeClass("active");

    //条件筛选(最近一周,一个月...)
    $("#pwoer").find(".interval a").on("click", function (period) {
        $(this).addClass("active").siblings().removeClass("active");
        var index = $(this).index();
        $("#a_inex").val(index);
        var period = $(this).text();
        $("#period").val(period);
        $("#contain").find(".item").show();
        $("#selectplusByCondition").submit();
    });

    //条件筛选(借款审核,招标中...)
    $("#contain").find("#screening a").on("click", function (period) {
        $(this).addClass("active").siblings().removeClass("active");
        var app_index = $(this).index();
        var per = $(this).text();
        $("#Appstatus").val(per);
        $("#Appindex").val(app_index);
        $("#contain").find(".item").show();
        $("#selectplusByCondition").submit();
    });
});

//分页查询
function queryAllPerson(pageNum, pageSize) {
    var url = "";
    $("#pageNum").val(pageNum);
    $("#pageSize").val(pageSize);

    /*var period=$("#period").val();
     if(period != ""){
     document.getElementById('selectplusByCondition').action="${pageContext.request.contextPath }";*/
    /*}else{*/
    $("#selectplusByCondition").submit();
    /*}*/
}

//日期查询
function changedata() {
    var startdate = $("#startdate").val();//申请时间
    var enddate = $("#enddate").val();//申请时间
    if (startdate.length > 5 && enddate.length > 5) {
        $("#selectplusByCondition").submit();
    } else {
        alert("提示:查询时间不能为空");
        return false;
    }
}


$(function () {
    $("#defaultForm :input.required").each(function () {
        var $required = $("<strong class='high'> *</strong>"); //创建元素
        $(this).parent().append($required); //然后将它追加到文档中
    });
    //start
    $('#defaultForm :input').blur(function () {
        var $parent = $(this).parent();
        $parent.find(".formtips").remove();
        //验证借款标题
        if ($(this).is("#Loanname")) {
            if (this.value == "" || (this.value != "" && !/^[\u4E00-\u9FA5A-Za-z0-9]{2,16}$/.test(this.value))) {
                var errorMsg = '请输入小于16位的标题';
                $parent.append('<span class="formtips onError">' + errorMsg + '</span>');
            } else {
                var okMsg = '输入正确.';
                $parent.find(".high").remove();
                $parent.append('<span class="formtips onSuccess">' + okMsg + '</span>');
            }
        }
        //借款期限
        if ($(this).is('#Appday')) {
            if (this.value == "" || (this.value != "" && !/^[1-9]\d*$/.test(this.value))) {
                var errorMsg = '请输入正确的借款期限';
                $parent.append('<span class="formtips onError">' + errorMsg + '</span>');
            } else {
                var okMsg = '输入正确'
                $parent.find(".high").remove();
                $parent.append('<span class="formtips onSuccess">' + okMsg + '</span>');
            }
        }
        //借款期限
        if ($(this).is('#Loanamount')) {
            if (this.value == "" || (this.value != "" && !/^(([1-9]\d*)|0)(\.\d{1,2})?$/.test(this.value))) {
                var errorMsg = '请输入正确的金额数.';
                $parent.append('<span class="formtips onError">' + errorMsg + '</span>');
            } else {
                var okMsg = '输入正确'
                $parent.find(".high").remove();
                $parent.append('<span class="formtips onSuccess">' + okMsg + '</span>');
            }
        }
        //拆标数
        if ($(this).is('#Splitnum')) {
            if (this.value == "" || (this.value != "" && !/^[1-9]\d*$/.test(this.value))) {
                var errorMsg = '请输入正确的数字';
                $parent.append('<span class="formtips onError">' + errorMsg + '</span>');
            } else {
                var okMsg = '输入正确';
                $parent.find(".high").remove();
                $parent.append('<span class="formtips onSuccess">' + okMsg + '</span>');
            }
        }
    }).keyup(function () {
        $(this).triggerHandler("blur");
    }).focus(function () {
        $(this).triggerHandler("blur");
    });//end blur

    //提交
    $("#send").click(function () {
        $("#defaultForm :input.required").trigger('blur');
        var numError = $('#defaultForm .onError').length;
        if (numError) {
            return false;
        }
        $("#defaultForm").submit();
    });
});


