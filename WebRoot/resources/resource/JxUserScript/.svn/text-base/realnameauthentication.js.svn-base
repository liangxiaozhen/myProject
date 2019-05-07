 ;var realnameauthentication = (function(){
 	return {
  		save:function(obj){
  			var realname = $("#realname").val();
   			var identity = $("#identity").val();
   			 if(isEmpty(realname)){
    			 $("#realnameMSGError").css("display","");
    			 $("#realname").focus();
   				 $("#realnameMSGError").addClass("prompt_1 error_1").html("<i></i>请输入身份证姓名");
   				 return ;
   			 }else{
   				$("#realnameMSGError").css("display","none");
   			 }
   			 
   			 if(isEmpty(identity)){
    			 $("#identityMSGError").css("display","");
    			 $("#identity").focus();
   				 $("#identityMSGError").addClass("prompt_1 error_1").html("<i></i>请输入身份证号码");
   				 return ;
   			 }else{
   				$("#identityMSGError").css("display","none");
   			 }
   			 
   			if(!checkCard(identity)){
   				 $("#identityMSGError").css("display","");
   				 $("#identity").focus();
  				 $("#identityMSGError").addClass("prompt_1 error_1").html("<i></i>请输入正确的身份证号码");
  				 return ;
   			}else{
   				$("#identityMSGError").css("display","none");
   			}
   			 $("#realnameauthenticationSum").removeAttr("onclick").text("姓名匹配中...");
  			 $.ajax({
 				 type:"post",
 				 data:{"realname":realname,"identity":identity},
 				 url:basePath + "/user/securitycenter/realNameAuthentication.action",
 				 error:function(){$("#realnameauthenticationSum").attr("onclick","realnameauthentication.save(this)").text("提交认证");},
 				 success:function(data){
 					 if(data == "logout"){
 						 window.location.href = basePath + "/user/tologin.action";
 					 }
 					$("#realnameauthenticationSum").attr("onclick","realnameauthentication.save(this)").text("提交认证");
  					 var obj = $.parseJSON(data);
 					 if(obj.result == "realname_null"){
 						 alert("请输入身份证姓名");
 					 }else if(obj.result == "identity_null"){
 						 alert("请输入身份证号码");
 					 }else if(obj.result == "realNameed"){
 						alert("已实名认证");
 						window.location.href = basePath + "/user/securitycenter/list.action";
 					 }else if(obj.result == "identity_error"){
 						alert("请输入正确的身份证号码");
 					 }else if(obj.result == "success"){
 						alert("身份证认证成功");
 						window.location.href = basePath + "/user/securitycenter/list.action";
 					 }else if(obj.result == "fail"){
 						alert("因网络响应不及时,身份证认证不成功,请重新操作!");
  					 }else if(obj.result == "matching"){
 						alert("身份证匹配失败,请检查您输入的姓名身份证号码是否正确！");
  					 }else if(obj.result == "identity_error2"){
 						alert("此身份证号码已存在!");
 						window.location.href = basePath + "/user/securitycenter/list.action";
 					 }
 				 }
 			 });
 		}
 	};
 })();
 
 var vcity={ 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",  
         21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",  
         33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",  
         42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",  
         51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",  
         63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"  
        };  

 function checkCard(card)  
{  
  //是否为空  
 if(card === '')  
 {  
     alert('请输入身份证号，身份证号不能为空');  
     document.getElementById('identity').focus;  
     return false;  
 }  
 //校验长度，类型  
 if(isCardNo(card) === false)  
 {  
     alert('您输入的身份证号码不正确，请重新输入');  
     document.getElementById('identity').focus;  
     return false;  
 }  
 //检查省份  
 if(checkProvince(card) === false)  
 {  
     alert('您输入的身份证号码不正确,请重新输入');  
     document.getElementById('identity').focus;  
     return false;  
 }  
 //校验生日  
 if(checkBirthday(card) === false)  
 {  
     alert('您输入的身份证号码生日不正确,请重新输入');  
     document.getElementById('identity').focus();  
     return false;  
 }  
 //检验位的检测  
 if(checkParity(card) === false)  
 {  
     alert('您的身份证校验位不正确,请重新输入');  
     document.getElementById('identity').focus();  
     return false;  
 }  
  return true;  
};  


//检查号码是否符合规范，包括长度，类型  
function isCardNo(card)  
{  
 //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
 var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;  
 if(reg.test(card) === false)  
 {  
     return false;  
 }  

 return true;  
};  

//取身份证前两位,校验省份  
function checkProvince (card)  
{  
 var province = card.substr(0,2);  
 if(vcity[province] == undefined)  
 {  
     return false;  
 }  
 return true;  
};  

//检查生日是否正确  
function checkBirthday(card)  
{  
 var len = card.length;  
 //身份证15位时，次序为省（3位）市（3位）年（2位）月（2位）日（2位）校验位（3位），皆为数字  
 if(len == '15')  
 {  
     var re_fifteen = /^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/;   
     var arr_data = card.match(re_fifteen);  
     var year = arr_data[2];  
     var month = arr_data[3];  
     var day = arr_data[4];  
     var birthday = new Date('19'+year+'/'+month+'/'+day);  
     return verifyBirthday('19'+year,month,day,birthday);  
 }  
 //身份证18位时，次序为省（3位）市（3位）年（4位）月（2位）日（2位）校验位（4位），校验位末尾可能为X  
 if(len == '18')  
 {  
     var re_eighteen = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;  
     var arr_data = card.match(re_eighteen);  
     var year = arr_data[2];  
     var month = arr_data[3];  
     var day = arr_data[4];  
     var birthday = new Date(year+'/'+month+'/'+day);  
     return verifyBirthday(year,month,day,birthday);  
 }  
 return false;  
};  

//校验日期  
function verifyBirthday(year,month,day,birthday)  
{  
 var now = new Date();  
 var now_year = now.getFullYear();  
 //年月日是否合理  
 if(birthday.getFullYear() == year && (birthday.getMonth() + 1) == month && birthday.getDate() == day)  
 {  
     //判断年份的范围（3岁到100岁之间)  
     var time = now_year - year;  
     if(time >= 3 && time <= 100)  
     {  
         return true;  
     }  
     return false;  
 }  
 return false;  
};  

//校验位的检测  
function checkParity (card)  
{  
 //15位转18位  
 card = changeFivteenToEighteen(card);  
 var len = card.length;  
 if(len == '18')  
 {  
     var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   
     var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');   
     var cardTemp = 0, i, valnum;   
     for(i = 0; i < 17; i ++)   
     {   
         cardTemp += card.substr(i, 1) * arrInt[i];   
     }   
     valnum = arrCh[cardTemp % 11];   
     if (valnum == card.substr(17, 1))   
     {  
         return true;  
     }  
     return false;  
 }  
 return false;  
};  

//15位转18位身份证号  
function changeFivteenToEighteen(card)  
{  
 if(card.length == '15')  
 {  
     var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);   
     var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');   
     var cardTemp = 0, i;     
     card = card.substr(0, 6) + '19' + card.substr(6, card.length - 6);  
     for(i = 0; i < 17; i ++)   
     {   
         cardTemp += card.substr(i, 1) * arrInt[i];   
     }   
     card += arrCh[cardTemp % 11];   
     return card;  
 }  
 return card;  
};
  
 function isEmpty(val) {
		val = $.trim(val);
		if (val == null)
			return true;
		if (val == undefined || val == 'undefined')
			return true;
		if (val == "")
			return true;
		if (val.length == 0)
			return true;
		if (!/[^(^\s*)|(\s*$)]/.test(val))
			return true;
		return false;
};