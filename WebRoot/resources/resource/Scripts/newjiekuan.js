



//户口所在地
function findCitys(){ 
	//$("#bankProvCode option:gt(0)").remove(); 
	var provCode = $("#s_province").val();
	if (provCode != "-1") {
		$.ajax({
			type : "POST", 
			url : "/daikuan/getProvSelect1.do", 
			data : "provCode=" + provCode, 
			success : function(result) { 
				data = result; 
				//删除ul中全部的li，初始化显示数据，后台保存input
				$("#city option").remove(); 
			
				//遍历json的数据
				$(data).each(function(index,domEle){ 
					var li = "";
					if(domEle.id==$("#cityName").val()){
						li =  "<option value='"+domEle.id+"' selected='selected'>"+domEle.name+"</option>";
					} else {
						li =  "<option value='"+domEle.id+"'>"+domEle.name+"</option>";
					}
					$("#city").append(li); 
				});
			} 
		}); 
	} else {
		$("#s_province").html("<option selected='selected' value='-1'>请选择</option>");
	}
}






/*$(function(){
	var vcity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
			21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
			33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
			42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
			51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
			63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"
	};

	checktheform = function()
	{
		var card = document.getElementById('card_no').value;
		//是否为空
		if(card === '')
		{
			$("#codeError").text("请输入身份证号，身份证号不能为空");
			$("#codeError").show();
			//alert('请输入身份证号，身份证号不能为空');
			document.getElementById('card_no').focus;
			return false;
		}
		//校验长度，类型
		if(isCardNo(card) === false)
		{
			$("#codeError").text("您输入的身份证号码不正确，请重新输入");
			$("#codeError").show();
			//alert('您输入的身份证号码不正确，请重新输入');
			document.getElementById('card_no').focus;
			return false;
		}

		//检查省份
		if(checkProvince(card) === false)
		{
			$("#codeError").text("您输入的身份证号码不正确,请重新输入");
			$("#codeError").show();
			//alert('您输入的身份证号码不正确,请重新输入');
			document.getElementById('card_no').focus;
			return false;
		}
		//校验生日
		if(checkBirthday(card) === false)
		{
			$("#codeError").text("您输入的身份证号码生日不正确,请重新输入");
			$("#codeError").show();
			//alert('您输入的身份证号码生日不正确,请重新输入');
			document.getElementById('card_no').focus();
			return false;
		}
		//检验位的检测
		if(checkParity(card) === false)
		{
			$("#codeError").text("您的身份证校验位不正确,请重新输入");
			$("#codeError").show();
			//alert('您的身份证校验位不正确,请重新输入');
			document.getElementById('card_no').focus();
			return false;
		}
		$("#codeError").hide();
		return true;
	};

	//检查号码是否符合规范，包括长度，类型
	isCardNo = function(card)
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
	checkProvince = function(card)
	{
		var province = card.substr(0,2);
		if(vcity[province] == undefined)
		{
			return false;
		}
		return true;
	};

	//检查生日是否正确
	checkBirthday = function(card)
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
	verifyBirthday = function(year,month,day,birthday)
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
	checkParity = function(card)
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
	changeFivteenToEighteen = function(card)
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

});


function discriCard(){
	//获取输入身份证号码
	var UUserCard = document.getElementById("card_no").value;
	UUserCard= document.getElementById("card_no").value.toUpperCase();

	//获取出生日期
	UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);

	//获取年龄
	var myDate = new Date();
	var month = myDate.getMonth() + 1;
	var day = myDate.getDate();
	var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
	if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
		age++;
	}
	if(age<23){
		var province_2 =15;
		$("#age option[value='"+province_2+"']").attr("selected",true).siblings('#age option').attr("selected",false);
	}else if(age<=55){
		var province_3 =40;
		$("#age option[value='"+province_3+"']").attr("selected",true).siblings('#age option').attr("selected",false);
	}else if(age>55&&age<100){
		var province_4 =80;
		$("#age option[value='"+province_4+"']").attr("selected",true).siblings('#age option').attr("selected",false);
	}

	//年龄 age
}*/



