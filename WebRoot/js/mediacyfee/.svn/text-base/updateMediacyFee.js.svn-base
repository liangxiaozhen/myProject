function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#updateMediacyFeeForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  mrecman: {
				  "required":true
			  },
			  chargetype: {
				  "required":true
			  },
			  type: {
				  "required":true
			  },
			  minnmmoney: {
				  "required":true,
				  "number":true
			  },
			  maxnmmoney: {
				  "required":true,
				  "number":true
			  },
			  mfquota: {
				  "required":true,
				  "number":true
			  },
			  mfpercent: {
				  "required":true,
				  "number":true
			  },
			  minmffee: {
				  "required":true,
				  "number":true
			  },
			  maxmffee: {
				  "required":true,
				  "number":true
			  },
			  mfrate: {
				  "required":true,
				  "number":true
			  },
			  minmfamount: {
				  "required":true,
				  "number":true
			  },
			  maxmfamount: {
				  "required":true,
				  "number":true
			  },
			  istemplet: {
				  "required":true
			  },
		    },
			messages :  {
				mrecman: {
					  "required":"*请输入居间服务费收款人"
				  },
				  chargetype: {
					  "required":"*请输入收费类型"
				  },
				  type: {
					  "required":"*请选择类型"
				  },
				  minnmmoney: {
					  "required":"*请输入金额段的起始金额",
					  "number":"*请输入数字"
				  },
				  maxnmmoney: {
					  "required":"*请输入金额段的结束金额",
					  "number":"*请输入数字"
				  },
				  mfquota: {
					  "required":"*请输入定额",
					  "number":"*请输入数字"
				  },
				  mfpercent: {
					  "required":"*请输入百份比",
					  "number":"*请输入数字"
				  },
				  minmffee: {
					  "required":"*请输入居间费最低金额",
					  "number":"*请输入数字"
				  },
				  maxmffee: {
					  "required":"*请输入居间费最高金额",
					  "number":"*请输入数字"
				  },
				  mfrate: {
					  "required":"*请输入居间费费率",
					  "number":"*请输入数字"
				  },
				  minmfamount: {
					  "required":"*请输入居间费最低金额",
					  "number":"*请输入数字"
				  },
				  maxmfamount: {
					  "required":"*请输入居间费最高金额",
					  "number":"*请输入数字"
				  },
				  istemplet: {
					  "required":"*请选择是否为模板"
				  },
				 
			    }
	})
}




