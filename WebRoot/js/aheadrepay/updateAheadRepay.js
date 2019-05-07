function check(){
	// 在键盘按下并释放及提交后验证提交表单
	  return $("#updateAheadRepayForm").validate({
		  errorPlacement: function (error, element) {
				var p = $("<em></em>").append(error);
				p.appendTo(element.parent());
				p.css({"color":"red"});
			},
		  rules: {
			  ispicompensateon: {
				  "required":true
			  },
			  minnoreceiveint: {
				  "required":true,
				  "number":true
			  },
			  maxnoreceiveint: {
				  "required":true,
				  "number":true
			  },
			  awardtype: {
				  "required":true
			  },
			  loanpenaltyname: {
				  "required":true
			  },
			  type: {
				  "required":true
			  },
			  penaltyquota: {
				  "required":true,
				  "number":true
			  },
			  penaltyrate: {
				  "required":true,
				  "number":true
			  },
			  maxpenalty: {
				  "required":true,
				  "number":true
			  },
			  pawardname: {
				  "required":true
			  },
			  ispluscompensateon: {
				  "required":true
			  },
			  minplusnoreceiveint: {
				  "required":true,
				  "number":true
			  },
			  maxplusnoreceiveint: {
				  "required":true,
				  "number":true
			  },
			  plusawardtype: {
				  "required":true
			  },
			  pluspenaltyname: {
				  "required":true
			  },
			  type2: {
				  "required":true
			  },
			  pluspenaltyquota: {
				  "required":true,
				  "number":true
			  },
			  pluspenaltyrate: {
				  "required":true,
				  "number":true
			  },
			  plusmaxpenalty: {
				  "required":true,
				  "number":true
			  },
			  pawardname: {
				  "required":true
			  },
			  isforplatformon: {
				  "required":true
			  },
			  awardrecman: {
				  "required":true
			  },
			  minallnoreceiveint: {
				  "required":true,
				  "number":true
			  },
			  maxallnoreceiveint: {
				  "required":true,
				  "number":true
			  },
			  type3: {
				  "required":true
			  },
			  awardplatquota: {
				  "required":true,
				  "number":true
			  },
			  awardplatrate: {
				  "required":true,
				  "number":true
			  },
			  awardplatminmoney: {
				  "required":true,
				  "number":true
			  },
			  awardplatmaxmoney: {
				  "required":true,
				  "number":true
			  },
			  istemplet: {
				  "required":true
			  },
		    },
			messages : {
				ispicompensateon: {
					  "required":"*请选择本金利息补偿开关"
				  },
				  minnoreceiveint: {
					  "required":"*请输入金欠收最小利息",
					  "number":"*请输入数字"
				  },
				  maxnoreceiveint: {
					  "required":"*请输入金欠收最高利息",
					  "number":"*请输入数字"
				  },
				  awardtype: {
					  "required":"*请选择奖励方式"
				  },
				  loanpenaltyname: {
					  "required":"借款人罚金奖励名称"
				  },
				  type: {
					  "required":"*请选择类型"
				  },
				  penaltyquota: {
					  "required":"*请输入借款人罚金定额",
					  "number":"*请输入数字"
				  },
				  penaltyrate: {
					  "required":"*请输入借款人罚金百分比",
					  "number":"*请输入数字"
				  },
				  maxpenalty: {
					  "required":"*请输入借款人罚金最大值",
					  "number":"*请输入数字"
				  },
				  pawardname: {
					  "required":"*请选择平台奖励名称"
				  },
				  ispluscompensateon: {
					  "required":"*请选择增益利息补偿开关"
				  },
				  minplusnoreceiveint: {
					  "required":"*请输入累计增益欠收最小利息",
					  "number":"*请输入数字"
				  },
				  maxplusnoreceiveint: {
					  "required":"*请输入累计增益欠收最高利息",
					  "number":"*请输入数字"
				  },
				  plusawardtype: {
					  "required":"*请选择增益奖励方式"
				  },
				  pluspenaltyname: {
					  "required":"*请输入增益平台罚金奖励名称"
				  },
				  type2: {
					  "required":"*请选择类型"
				  },
				  pluspenaltyquota: {
					  "required":"*请输入增益平台罚金定额",
					  "number":"*请输入数字"
				  },
				  pluspenaltyrate: {
					  "required":"*请输入增益平台罚金百分比",
					  "number":"*请输入数字"
				  },
				  plusmaxpenalty: {
					  "required":"*请输入增益平台罚金最大值",
					  "number":"*请输入数字"
				  },
				  pawardname: {
					  "required":"*请输入增益平台奖励名称"
				  },
				  isforplatformon: {
					  "required":"*请选择还款人补偿平台开关"
				  },
				  awardrecman: {
					  "required":"*请输入奖励平台收款人"
				  },
				  minallnoreceiveint: {
					  "required":"*请输入欠收最小利息",
					  "number":"*请输入数字"
				  },
				  maxallnoreceiveint: {
					  "required":"*请输入欠收最大利息",
					  "number":"*请输入数字"
				  },
				  type3: {
					  "required":"*请选择类型",
				  },
				  awardplatquota: {
					  "required":"*请输入奖励平台定额",
					  "number":"*请输入数字"
				  },
				  awardplatrate: {
					  "required":"*请输入奖励平台百份比",
					  "number":"*请输入数字"
				  },
				  awardplatminmoney: {
					  "required":"*请输入增益平台罚金最小值",
					  "number":"*请输入数字"
				  },
				  awardplatmaxmoney: {
					  "required":"*请输入增益平台罚金最大值",
					  "number":"*请输入数字"
				  },
				  istemplet: {
					  "required":"*请选择是否为模板",
				  },
			}
	})
}




