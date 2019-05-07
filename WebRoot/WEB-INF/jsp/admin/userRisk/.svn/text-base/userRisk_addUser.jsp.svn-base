<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加用户到风险名单</title>
    <script type="text/javascript">
        //必须先选择名单才能进行下面的操作
        $(function () {
            $("#InputsWrapper").hide();
            $("#userRiskType").change(function () {
                if ("#userRiskType.val()!=''") {
                    $("#InputsWrapper").show();
                }
            })
        });
        //定义全局变量x
        var x = 1;
        //添加用户时判断该用户是否存在
        function isAddUser() {
            //给按钮订一个开关
            var flag = true;
            //最大追加div数为10个
            var MaxInputs = 10;
            //输入框div的ID
            var InputsWrapper = $("#InputsWrapper");
            //增加按钮的id
            var AddButton = $("#AddMoreFileBox");
            //增加输入框计数
            //x = InputsWrapper.length;
            //文本框递增
            var FieldCount = 1;
            //先取到username的值
            var username = $("#usernameUserRisk").val();

            //如果未输入用户名
            if (username == "") {
                //改变按钮的状态 使其无法再继续追加div
                flag = false;
                alert("请输入用户名");
                return;
            }

            //ajax请求路径
            var action = "isAddUser.action";
            //设置要返回的参数
            var params = {
                "username": username
            };


            //回调函数
            var callback = function (data) {
                if (data != "添加成功!") {
                    alert(data);
                    flag = false;

                } else {
                    if (x <= MaxInputs) {
                        //取值
                        var username = $("#usernameUserRisk").val();
                        //alert("#usernameUserRisk");
                        //var remark = $("#remarkUserRisk").val();

                        $("input[name='username']").each(function () {
                            if ($(this).val() == username) {
                                flag = false;
                                alert("用户已添加");
                            }

                        });


                        FieldCount++;
                        var oDiv = $('<div class="wapperclass"></div>');
                        oDiv.html('<div class="form-group">' +
                            '<label class="col-sm-3 control-label" id="lb-count">' + x + '</label>' +
                            '<div class="col-sm-4">' +
                            '<div class="input-group">' +
                            '<input type="text" name="username" id="usernameUserRisk" value="' + username + '" class="form-control"/>' +
                            '</div>' +
                            '</div>' +


                            '<div class="col-sm-2">' +
                            '<span><button id="removeButton" class="btn btn-default removeclass" value="' + x + '"><strong>删除</strong></button></span>' +
                            '</div>' +
                            '</div>');

                        if (flag) {

                            //追加div
                            $(InputsWrapper).append(oDiv);

                            //点击添加按钮之后清空username输入框
                            var username = $("#usernameUserRisk").val("");
                            x++;
                        }

                    }

                }

            };

            $.post(action, params, callback, 'json');
        }


        //删除追加的div
        $(document).ready(function () {
            //删除
            $("body").on("click", ".removeclass", function (e) {
                //当前删除按钮的value值
                var value = $(this).val();
                var $row = $(this).parents('.wapperclass'),
                    $option = $row.find('[name="option[]"]');

                $(".wapperclass").each(function (i) {
                    if (value == i) {
                        $(this).find("#lb-count").html(value);
                        $(this).find("#removeButton").val(value);
                        value++;
                    }
                });

                $row.remove();
                //添加的用户备注条数为0时，x从1开始
                var size = $(".wapperclass").size();
                if (size == 0) {
                    x = 1;
                } else {
                    //添加的用户备注条数不为0时，添加的下一条用户备注为当前条数+1
                    x = $(".wapperclass").size() + 1;
                }

                if (size < MaxInputs) {
                    $("#AddMoreFileBox").attr("disabled", false);
                }
                $('form').bootstrapValidator('removeField', $option);
            });
        });

    </script>
</head>
<body>
<form id="insert-form" class="form-horizontal" role="form"
      method="post" action="isAddUser.action">
    <div class="form-group">
        <label for="clearmethod" class="col-sm-3 control-label">选择名单</label>
        <div class="col-sm-3" id="xz">
            <select name="type" id="userRiskType" class="form-control">
                <option value="">---请选择---</option>
                <option value="4">风险名单</option>
                <option value="3">应急改密名单</option>
                <option value="2">黑名单</option>
                <option value="1">白名单</option>
            </select>
        </div>
    </div>

    <!--添加用户和备注-->
    <div id="InputsWrapper">
        <div class="form-group">
            <label for="inputminmoney" class="col-sm-3 control-label">请输入用户名
            </label>

            <div class="col-sm-4">
                <div class="input-group">
                    <input type="text" name="user_name" id="usernameUserRisk"
                           class="form-control"/>
                </div>
            </div>
            <div class="col-sm-2">
					<span><button id="AddMoreFileBox" type="button"
                                  class="btn btn-default" onclick="isAddUser()">
							<strong>添加</strong>
						</button></span>
            </div>
        </div>
    </div>

</form>
</body>
</html>