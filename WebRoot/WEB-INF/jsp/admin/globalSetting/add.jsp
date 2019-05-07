<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>干将系统全局设置添加页面</title>
<%@include file="/WEB-INF/jsp/common/public.jsp"%>
<style type="text/css">
tr {
	display: block; /*将tr设置为块体元素*/
	margin: 5px 0; /*设置tr间距为2px*/
}

textarea {
	resize: none;
	width: 280px;
	height: 150px;
}
</style>

<script type="text/javascript">
	function gj_role_save() {

		var websitename = $("#websitename").val();
		var title = $("#title").val();
		var keyWorld = $("#keyWorld").val();
		var description = $("#description").val();
		var strStatus = $("#strStatus").val();
		var GJIDCode = $("#GJIDCode").val();
		var presetStr = $("#presetStr").val();
		var annualRate = $("#annualRate").val().trim();
		if (!annualRate.isEmpty()) {
			var patrn = /^[0-9]{1,3}$/;
			if (!patrn.test(annualRate)) {
				$("#annualRate").select();
				loading("年率天数最多3位数字", 4);
				return false;
			}
		}else{
		    loading("年率天数不能为空",4);
		    return false;
		}


		var authtimes = $("#authtimes").val().trim();
		if (!authtimes.isEmpty()) {
			var patrn = /^[0-9]{1,2}$/;
			if (!patrn.test(authtimes)) {
				$("#authtimes").select();
				loading("认证次数为数字,默认三次", 4);
				return false
			}
		}else{
		    loading("认证次数不能为空",4);
		    return false;
		}

		var UrgentMethod = $("#UrgentMethod").val().trim();
		if (!UrgentMethod.isEmpty()) {
			var patrn = /^[0-9]{1,2}$/;
			if (!patrn.test(UrgentMethod)) {
				$("#UrgentMethod").select();
				loading("为数字", 4);
				return false
			}
		}

		var GlobalVerifyCode = $("#GlobalVerifyCode").val().trim();
		if (!GlobalVerifyCode.isEmpty()) {
			var patrn = /^[0-9]{1,2}$/;
			if (!patrn.test(GlobalVerifyCode)) {
				$("#GlobalVerifyCode").select();
				loading("为数字", 4);
				return false
			}
		}

		var UpGrade = $("#UpGrade").val().trim();
		if (!UpGrade.isEmpty()) {
			var patrn = /^[0-9]{1,2}$/;
			if (!patrn.test(UpGrade)) {
				$("#UpGrade").select();
				loading("为数字", 4);
				return false
			}
		}
		
		var autorptimesltd = $("#autorptimesltd").val();
		if (!autorptimesltd.isEmpty()) {
			var patrn = /^[0-9]{1,2}$/;
			if (!patrn.test(autorptimesltd)) {
				$("#autorptimesltd").select();
				loading("为数字", 4);
				return false
			}
		}
		
		var autorpstinvl = $("#autorpstinvl").val();
		if (!autorpstinvl.isEmpty()) {
			var patrn = /^[0-9]{1,2}$/;
			if (!patrn.test(autorpstinvl)) {
				$("#autorpstinvl").select();
				loading("为数字", 4);
				return false
			}
		}

        var pREAccount = $("#pREAccount").val();//红包账户
        if (!pREAccount.isEmpty()) {
            var patrn = /^[1-9]$/;
            if (!patrn.test(pREAccount)) {
                $("#pREAccount").select();
                loading("为数字", 4);
                return false;
            }
        }
        var pFeeAccount = $("#pFeeAccount").val();//手续费账户
        if (!pFeeAccount.isEmpty()) {
            var patrn = /^[1-9]$/;
            if (!patrn.test(pFeeAccount)) {
                $("#pFeeAccount").select();
                loading("为数字", 4);
                return false;
            }
        }

            var newerBidCount = $("#newerBidCount").val();//新手投标次数限制
            if (!newerBidCount.isEmpty()) {
                var patrn = /^\d{0,2}$/;
                if (!patrn.test(newerBidCount)) {
                    $("#newerBidCount").select();
                    loading("1-2位数字", 4);
                    return false;
                }
            }

            var newerBidAmount = $("#newerBidAmount").val();//新手累投金额限制
            if (!newerBidAmount.isEmpty()) {
                var patrn = /^[1-9]\d*$/;
                if (!patrn.test(newerBidAmount)) {
                    $("#newerBidAmount").select();
                    loading("为数字", 4);
                    return false;
                }
            }

        var newerBidDayLimit = $("#newerBidDayLimit").val();//新手投标注册时间天数限制
        if (!newerBidDayLimit.isEmpty()) {
            var patrn = /^[1-9][0-9]{1,4}$/;
            if (!patrn.test(newerBidDayLimit)) {
                $("#newerBidDayLimit").select();
                loading("1-5位数字", 4);
                return false;
            }
        }
        var newerBidRule = $("#newerBidRule").val();//新手投标定义 -- 的值

        var action = basePath + "/admin/globalSetting/add.action";
		$.ajax({
			type : "post",
			url : action,
			data : {
				"websitename" : websitename,
				"title" : title,
				"keyWorld" : keyWorld,
				"description" : description,
				"strStatus" : strStatus,
				"annualRate" : annualRate,
				"authtimes" : authtimes,
				"GJIDCode" : GJIDCode,
				"presetStr" : presetStr,
				"UrgentMethod" : UrgentMethod,
				"GlobalVerifyCode" : GlobalVerifyCode,
				"UpGrade" : UpGrade,
				"autorpstinvl" : autorpstinvl,
				"autorptimesltd" : autorptimesltd,
                "pREAccount": pREAccount,
                "pFeeAccount": pFeeAccount,
                "newerBidCount": newerBidCount,
                "newerBidAmount": newerBidAmount,
                "newerBidDayLimit": newerBidDayLimit,
                "newerBidRule" : newerBidRule
			},
			success : function(data) {
				var obj = $.parseJSON(data);
				if (obj.result == "success") {
					alert("保存成功");
					window.location.href = basePath
							+ "/admin/globalSetting/sove.action";
				} else if (obj.result == "fiel1") {
					$("#id").select();
					loading("Id存在", 4);
				} else if (obj.result == "fiel2") {
					$("#id").select();
					loading("添加失败", 4);
				} else if (obj.result == "fiel3") {
					$("#id").select();
					loading("Id不能为空", 4);
				} else if (obj.result == "fiel4") {
					alert("全局id已经存在,不能再添加！");
					window.location.href = basePath
							+ "/admin/globalSetting/sove.action";
				}
			}
		});

	}
	//新手投标定义select设置
	$(function () {

		$("#x1").css("display","block");
    })
	function  f1(Names) {
		var Nnews ;
        for (var i = 1; i < 3; i++) {
            var tempname="mun_x"+i
            var NewsHot="x"+i    //  “X”是ID名称，比如：ID命名为“case1”，这里的“X”即为“case”
            if (Names==tempname){
                Nnews=document.getElementById(NewsHot);
                Nnews.style.display='';
            }else{
                Nnews=document.getElementById(NewsHot);
                Nnews.style.display='none';
            }
			
        }
    }
</script>
</head>
<body>
	<form id="inputForm" class="form-horizontal">

		<table>
			<tr>
				<td style="padding-right: 48px">网站名称:</td>
				<td><input type="text" name="websitename" value=""
					id="websitename" /></td>
			</tr>

			<tr>
				<td style="padding-right: 48px">网站抬头:</td>
				<td><input type="text" name="title" value="" id="title" /></td>
			</tr>

			<tr>
				<td style="padding-right: 35px">网站关键词:</td>
				<td><input type="text" name="keyWorld" value="" id="keyWorld" /></td>
			</tr>


			<tr>
				<td style="padding-right: 70px">开&nbsp;关:</td>
				<td><input type="text" name="strStatus" value="" id="strStatus" /></td>
			</tr>

			<tr>
				<td style="padding-right: 48px">年率天数:</td>
				<td><input type="text" name="annualRate" value=""
					id="annualRate" /></td>
			</tr>
			<tr>
				<td style="padding-right: 48px">认证次数:</td>
				<td><input type="text" name="authtimes" value="" id="authtimes" /></td>
				<td><p>默认3次</p></td>
			</tr>

			<tr>
				<td style="padding-right: 25px">业务编号前缀:</td>
				<td><input type="text" name="presetStr" id="presetStr"
					value=" " /></td>
			</tr>
			<tr>
				<td>应急改密方式设置:</td>
				<td><input type="text" name="UrgentMethod" id="UrgentMethod"
					value="" /></td>
			</tr>
			<tr>
				<td style="padding-right: 12px">全局验证验证码:</td>
				<td><input type="text" name="GlobalVerifyCode"
					id="GlobalVerifyCode" value="" /></td>
				<td><p>1开启全局验证，2关闭全局验证码，3开启正常验证码</p></td>
			</tr>
			<tr>
				<td style="padding-right: 15px">会员升级方向：</td>
				<td><input type="text" name="UpGrade" id="UpGrade" value=" " /></td>
				<td><p>1.单向 ; 2.双向</p></td>
			</tr>
			<tr>
				<td style="padding-right: 15px">系统自动提交还款次数限制：</td>
				<td><input type="text" name="autorptimesltd" id="autorptimesltd" value=" " /></td>
 			</tr>
			<tr>
				<td style="padding-right: 15px">系统自动提交还款提交间隔（单位 分钟）：</td>
				<td><input type="text" name="autorpstinvl" id="autorpstinvl" value=" " /></td>
 			</tr>

			<tr>
				<td style="padding-right: 15px">徽商红包账户：</td>
				<td><input type="text" name="pREAccount" id="pREAccount"
						   value=""/></td>
				<td><p>&nbsp;填入2为红包账户 </p></td>
			</tr>

			<tr>
				<td style="padding-right: 5px">徽商手续费账户：</td>
				<td><input type="text" name="pFeeAccount" id="pFeeAccount"
						   value=""/></td>
				<td><p>&nbsp;填入3设置为手续费账户 </p></td>
			</tr>

			<tr>
				<td style="padding-right: 15px">新手投标定义：</td>
				<td><select onchange="javascript:f1('mun_x'+value)" id="newerBidRule">
							<option value="1">投标次数+注册时间天数</option>
							<option value="2">累投金额+注册时间天数</option>

				</select>
				</td>
			</tr>

				<tr style="display: none" id="x2">
					<td style="padding-left: 25px;padding-right: 22px">累投金额：</td>
					<td><input type="text" name="newerBidAmount" id="newerBidAmount"
							   value=""/></td>
					<td><p>&nbsp;新手累投金额限制 </p></td>
				</tr>

				<tr style="display: none" id="x1">

					<td style="padding-left: 25px;padding-right: 22px">投标次数：</td>
					<td><input type="text" name="newerBidCount" id="newerBidCount"
							   value=""/></td>
					<td><p>&nbsp;次以下</p></td>
				</tr>

			<tr >

				<td style="padding-left: 25px;padding-right: 22px">注册天数：</td>
				<td><input type="text" name="newerBidDayLimit" id="newerBidDayLimit"
						   value=""/></td>
				<td><p>&nbsp;天以内 </p></td>
			</tr>

			<tr>
				<td style="padding-right: 35px">干将识别码:</td>
				<td>
					<p>
						<textarea rows="5" cols="25" name="GJIDCode" id="GJIDCode"
							style="height: 40px"></textarea>
					</P>
				</td>
			</tr>
			<tr>
				<td style="padding-right: 48px">网站描述:</td>
				<td>
					<p>
						<textarea rows="5" cols="25" name="description" id="description"
							style="height: 40px"> </textarea>
					</P>
				</td>
			</tr>

			<tr>
				<td><input type="button" id="btnSubmit" class="btn btn-primary"
					onclick="gj_role_save(this)" value="提交" /></td>
				<td>&nbsp;&nbsp;&nbsp;<a
					href="${basePath }/admin/globalSetting/sove.action"
					class="btn btn-primary">返回</a></td>
			</tr>
		</table>
	</form>

</body>
</html>