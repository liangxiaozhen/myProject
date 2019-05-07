$(function(){

   //点击充值记录的时候
   $("#czjl").click(function(){
	   $("#numberTwo").hide();
	   $("#middle").hide();
	   $("#kjnr_bottom").hide();
	   $("#czjl_nr").show();
   });
});
//点击银行列表(点击图片的时候选中单选框)
$(function(){
	$("#grwangy_nr li").click(function(){
		$(this).children("input[type='radio']").prop("checked",true);
		$(this).siblings().children("input[type='radio']").removeAttr("checked");
		$('.' + $(this).data('link')).show().siblings().hide();
	});
})

function rechargeAttr(){
	 var action = "user/userRecharge/rechargeDatails.action";
	 $.post(action,function(data){
		 alert(data);
	 });
}

//点击确认跳转(充值第一部曲)
function mmmdrawmoney() {
	//用户标识 1为个人  0为企业
	var accountType = $("#accountType").val();
	//获取选中的值:充值方式
	var select = $(".userrecharge input[name='cz']:checked").val();
	if(parseInt(accountType)==1 && parseInt(select)==4){//个人用户+企业网银
		$("#txt_Amount_span").text("对不起!您不能使用企业网银充值!请查看详细说明");
	}else if(parseInt(accountType)==0 && parseInt(select)==2){//企业用户+快捷充值
		$("#txt_Amount_span").text("对不起!您不能使用快捷方式充值!请查看详细说明");
	}else{
		//获取充值金额
		var transAmt = $("#transAmt").val();
		if(transAmt==null || isNaN(transAmt) ||transAmt==""){
			 $("#txt_Amount_span").text("请输入合法数据!");
		}else{
			 var action = "moneymoremore/userRecharge/dmoney.action";
			 var params = {
			 "transAmt":transAmt,
			 "select" : select,
			 };
			 $.post(action, params,function(data){
				 if(data=="isopenfsinfofalse" || data=="ufsfalse"){ //表示没有开通托管账户
					 $("#txt_Amount_span").text("对不起,您还没有开通托管账户!请开通托管账户后再进行操作!");
				 }else{
					 if(data!="0"){
						 var flagremovename = data.flagremovename;//验证充值人是否在定向名单中,如果是1说明不在(表示不行的),如果是0表示可以对该用户进行操作
						 var min = data.lowestmoney;//最低充值金额
						 var max = data.hightestmoney;//最高充值金额
						 var totalamount = data.totalamoount;//充值总额
						 var dayamount = data.daymoneyrest;//日充值限额
						 var canrecharge = dayamount-parseInt(totalamount);//还能充值多少钱
						 if(flagremovename==1){ //后台传回,1表示当前用户在排出人员名单表中
							 $("#txt_Amount_span").text("对不起!您当前不能充值,请联系客服!!");
						 }else{
							 if(parseInt(transAmt)<min){
								 $("#txt_Amount_span").text("最低限额为"+min+"元，请重新输入!");
							 }else{
								 if(parseInt(transAmt)>max){
									 $("#txt_Amount_span").text("最高限额为"+max+"元，请重新输入!");
								 }else if((parseInt(totalamount)+parseInt(transAmt))>dayamount){
									 $("#txt_Amount_span").text("日限额为"+dayamount+"元，请重新输入!您还能充值"+canrecharge+"元");
								 }else{
									 $("#txt_Amount_span").text("");
									 mmmsubmitRecharge(select,transAmt);
									 
								 } 
							 } 
						 }
					 }else{
						 $("#txt_Amount_span").text("对不起,您暂时无法充值,给您带来不便敬请谅解!如有疑问请联系客服人员400-800-1993");
					 }
				 }
			 }, 'json');
		 }  
	}

}

//点击确认跳转页面(充值第二部曲)
function mmmsubmitRecharge(select,transAmt) {
	 $("#rechargeModal").css({
			margin : '200px auto'
		}).modal({
			backdrop : 'static',
			keyboard : false
		}).modal('show');   
	window.open("moneymoremore/userRecharge/RechargeConfirmation.action?transAmt="
			+transAmt+"&select="+select);  
}
//点击模态框中的完成充值的时候跳转到资金详细页面
function rechargeSuccess() {
	location.href = "user/userAccInExRecord/queryAll.action";
}
//重新选择充值方式
function rechargeFail() {
	location.href = "user/userRecharge/rechargeList.action";
}
//导航栏查询 
function selectByCondition(){
	 var url ="user/userRecharge/rechargeRecord.action";
	 var params = {
			 "status":$("#status_select").val(),
			 "starttime":$("#starttime_select").val(),
			 "endtime":$("#endtime_select").val()	
	 }
	 var callback = function(data){
		 $("#czjl_nr").html(data);
	 }
	 $.post(url,params,callback);
}
//再次充值
function rechargeagain(id){
	 $("#rechargeModal").css({
			margin : '200px auto'
		}).modal({
			backdrop : 'static',
			keyboard : false
		}).modal('show');   
	window.open("moneymoremore/userRecharge/RechargeAgain.action?id="+id);  
}

//刷新页面
function refurbish() {
	queryAllPerson('${pagehelper.pageNum}', '${pagehelper.pageSize}');
}
//查询所有数据(翻页)
function queryAllPerson(pageNum, pageSize) {
	var action = "user/userRecharge/rechargeRecord.action";
	var param = {
			"pageNum":pageNum,
			 "status":$("#status_select").val(),
			 "starttime":$("#starttime_select").val(),
			 "endtime":$("#endtime_select").val()	
			};
	 $.post(action,param,function(data){
		 $("#czjl_nr").html(data);
	 });
}