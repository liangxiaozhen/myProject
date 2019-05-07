function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#insertGuaranteeFeeForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  gfrecman: {
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
			  gfquota: {
				  "required":true,
				  "number":true
			  },
			  gfpercent: {
				  "required":true,
				  "number":true
			  },
			  mingffee: {
				  "required":true,
				  "number":true
			  },
			  maxgffee: {
				  "required":true,
				  "number":true
			  },
			  gfrate: {
				  "required":true,
				  "number":true
			  },
			  mingfamount: {
				  "required":true,
				  "number":true
			  },
			  maxgfamount: {
				  "required":true,
				  "number":true
			  },
			  istemplet:{
				  "required":true
			  }
			  
		    },
			messages :  {
				  gfrecman: {
					  "required":"*请输入担保服务费收款人"
				  },
				  chargetype: {
					  "required":"*请选择收费类型"
				  },
				  type: {
					  "required":"*请选择类型"
				  },
				  minnmmoney: {
					  "required":"*请输入结标金额段起始金额",
					  "number":"*请输入数字"
				  },
				  maxnmmoney: {
					  "required":"*请输入结标金额段结束金额",
					  "number":"*请输入数字"
				  },
				  gfquota: {
					  "required":"*请输入定额",
					  "number":"*请输入数字"
				  },
				  gfpercent: {
					  "required":"*请输入百份比",
					  "number":"*请输入数字"
				  },
				  mingffee: {
					  "required":"*请输入担保费最低金额",
					  "number":"*请输入数字"
				  },
				  maxgffee: {
					  "required":"*请输入担保费最高金额",
					  "number":"*请输入数字"
				  },
				  gfrate: {
					  "required":"*请输入担保费费率",
					  "number":"*请输入数字"
				  },
				  mingfamount: {
					  "required":"*请输入担保费最低金额",
					  "number":"*请输入数字"
				  },
				  maxgfamount: {
					  "required":"*请输入担保费最低金额",
					  "number":"*请输入数字"
				  },
				  istemplet:{
					  "required":"*请选择是否为模板"
				  }
			    }
	})
}




