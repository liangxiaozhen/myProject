/* 
 * 实名认证
 * 新的实现
 * 因为产品将充值前的实名认证改成了嵌在充值页面里面
*/
var realnameFlag = false;
var identityFlag = false;
var certIdentityResultFlag = false;
$(document).ready(function(){
	
	$("#realname").focus(function(){
		if($(this).val().length==0){
			$("#realname").siblings().hide();
		}
	});
	
	$("#realname").blur(function(){
		if($(this).val().length==0){
			$("#realname").siblings().show();
		}
		
		var realname = $("#realname").val().replace(/·/g, '').replace(/•/g, '');
		if(realname==""){
			realnameFlag = false;
			$("#realnameMSG").css("display","").html("<i></i>请输入真实姓名！");
		}else if(realname.length > 50){
			realnameFlag = false;
			$("#realnameMSG").css("display","").html("<i></i>真实姓名不超过50个字符！");
		}else{
			var name = realname.replace(/·/g, ''); name = name.replace(/•/g, '');
			if(checkChinese(name)){
				realnameFlag = true;
				$("#realnameMSG").css("display","none");
			}else{
				realnameFlag = false;
				$("#realnameMSG").css("display","").html("<i></i>请输入汉字！");
			}
		}
 	});
	
	function checkChinese(str) { 
		var re = /[^\u4e00-\u9fa5]/; 
		if (re.test(str)) {
			return false;
		} else {
			return true;			
		}
	}; 
	
	$("#identity").blur(function(){
		var identity = $("#identity").val();
		var reg = /^(^\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		if(identity==""){
			identityFlag = false;
			$("#identityMSG").css("display","").html("<i></i>请输入身份证号！");
		}else if(!reg.test(identity)){
			identityFlag = false;
			$("#identityMSG").css("display","").html("<i></i>身份证号格式有误！");
		}else{
			$.ajax({
	        	type: "post",
	       	 	url: "/member/identitySole.do",
	        	dataType: "json",
	        	data:{
	        		identity:identity
	       		 },
	        	async: false,
	        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	        	success: function(msg){
	        		if(msg == 0){
	        			identityFlag = true;
	        			$("#identityMSG").css("display","none");
	        		}else if(msg == 1){
	        			identityFlag = false;
	        			$("#identityMSG").css("display","").html("<i></i>身份证号已存在！");
	        		}
	        	}   
	      });
		}
 	});
	

});

function centIdentitySave(){
	$("#realname").blur();
	$("#identity").blur();
	if(realnameFlag == true && identityFlag == true){
		$.ajax({
        	type: "post",
       	 	url: "/member/realnameAuthenticate.do",
        	dataType: "json",
        	data:{
        		identity:$("#identity").val(),
        		realname:$("#realname").val()
       		 },
        	async: false,
        	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        	success: function(msg){
        		if(msg == 1){
        			sendData4Adobe();
        			certIdentityResultFlag = true;//最终结果
        			identityFlag = true;
        			$("#identityMSG").css("display","none");   	
        			//close_RealName();//关闭认证弹框
        			//showCon_RealNameSuc();//弹出成功弹框
        			$("#realNameIdenTag").val('');//修改隐藏域为空
        		}else if(msg == 0){
        			identityFlag = false;
        			$("#identityMSG").css("display","").html("<i></i>身份证号认证失败，请重试！");
        		}else if(msg == -1){
        			identityFlag = false;
        			$("#identityMSG").css("display","").html("<i></i>身份证号已存在，请联系客服！");
        		}else if(msg == -2){
        			identityFlag = false;
        			$("#identityMSG").css("display","").html("<i></i><i></i>实名认证申请已达提交上限！");
        		}else if(msg == -3){
        			identityFlag = false;
        			$("#identityMSG").css("display","").html("<i></i><i></i>错误信息重复提交！");
        		}
        	}   
      });
	}
}

//Adobe | Begin | zhenhuaxi | 20141126
function sendData4Adobe(){
	var identityVal = $("#identity").val();
	var idCardInfoObjInfo = idCardInfoObj.handle(identityVal);
	var gender = "";
	if(idCardInfoObjInfo.gender == 0){
		gender = "男";
	}else{
		gender = "女";
	}
	var s = s_gi(s_account);
	s.linkTrackVars = "eVar22,eVar23,eVar24,events";
	s.linkTrackEvents = "event10";
	s.eVar22 = idCardInfoObjInfo.year; //出生年
	s.eVar23 = gender; //性别 
	s.eVar24 = idCardInfoObjInfo.city; //籍贯
	s.events = "event10"; //实名认证成功
	npo.tl(this,'o','rzcg');
}
// Adobe | End

