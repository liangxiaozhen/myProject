function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#insertPlusForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  aonceplusmode: {
				  "required":true,
				  "number":true
			  },
			  atotalplusmode: {
				  "required":true,
				  "number":true
			  },
			  payforplusman: {
				  "required":true
			  },
			  clearmode: {
				  "required":true
			  },
			  istemplet: {
				  "required":true
			  },
			  isaint: {
				  "required":true
			  },
			  aonceint: {
				  "required":true,
				  "number":true
			  },
			  atotalint: {
				  "required":true,
				  "number":true
			  },
			  aoneqrofit: {
				  "required":true,
				  "number":true
			  },
			  isavoucher: {
				  "required":true
			  },
			  aoncevoucher: {
				  "required":true,
				  "number":true
			  },
			  atotalvoucher: {
				  "required":true,
				  "number":true
			  },
			  aonevamount: {
				  "required":true,
				  "number":true
			  },
			  isalikevoucher: {
				  "required":true
			  },
			  aoncelikevoucher: {
				  "required":true,
				  "number":true
			  },
			  atotallikevoucher: {
				  "required":true,
				  "number":true
			  },
			  aonelvamount: {
				  "required":true,
				  "number":true
			  },
			  
		    },
			messages :  {
				aonceplusmode: {
					  "required":"请输入单次允许的增益方式种数",
					  "number":"请输入数字"
				  },
				  atotalplusmode: {
					  "required":"请输入总计允许的增益方式种数",
					  "number":"请输入数字"
				  },
				  payforplusman: {
					  "required":"请输入增益清算付款人"
				  },
				  clearmode: {
					  "required":"请选择清算方式"
				  },
				  istemplet: {
					  "required":"请选择是否为模板"
				  },
				  isaint: {
					  "required":"请选择是否允许加息卷"
				  },
				  aonceint: {
					  "required":"请输入单次允许使用加息张数",
					  "number":"请输入数字"
				  },
				  atotalint: {
					  "required":"请输入允许使用加息总张数",
					  "number":"请输入数字"
				  },
				  aoneqrofit: {
					  "required":"请输入允许单张加息收益",
					  "number":"请输入数字"
				  },
				  isavoucher: {
					  "required":"请选择是否允许类现金卷"
				  },
				  aoncevoucher: {
					  "required":"请输入单次允许类现金卷张数",
					  "number":"请输入数字"
				  },
				  atotalvoucher: {
					  "required":"请输入总计允许类现金卷张数",
					  "number":"请输入数字"
				  },
				  aonevamount: {
					  "required":"请输入允许单张类现金额度",
					  "number":"请输入数字"
				  },
				  isalikevoucher: {
					  "required":"请选择是否允许假现金卷"
				  },
				  aoncelikevoucher: {
					  "required":"请输入单次允许假现金卷张数",
					  "number":"请输入数字"
				  },
				  atotallikevoucher: {
					  "required":"请输入总计允许假现金卷张数",
					  "number":"请输入数字"
				  },
				  aonelvamount: {
					  "required":"请输入允许单张假现金额度",
					  "number":"请输入数字"
				  },
				  
				 
			    }
	})
}




