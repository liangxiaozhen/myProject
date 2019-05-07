<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绑定用户</title>
<%@ include file="/WEB-INF/jsp/common/nwdUserPublic.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/resource/js_easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/resource/js_easyui/easydialog.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/resource/js_easyui/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>/resources/resource/js_easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/resource/js_easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/resource/js_easyui/easydialog.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/resource/js_easyui/dialog.js"></script>
	<script type="text/javascript" src="<%=basePath%>/layer/layer.js"></script>
 </head>
<body>
	<%@ include file="/WEB-INF/jsp/common/nwdUserHeader.jsp"%>
	<div class="nwd_main bor_l bor_r bor_b clearfix">
		<div class="fl perCenterBg">
				<!-- 左侧 -->
				<%@ include file="/WEB-INF/jsp/common/nwdUserLeft.jsp"%>
				<div class="fl perCerterR bor_r bor_l">
						<div class="fl pad_30 wid_w900 clearfix">
							<div class="loadDiv fc_9 clearfix">
								<i class="nwd_icon nwd_icon_mianbaoxie"></i><span class="fc_6">账户绑定</span> 
							</div>
							<div class="pad_b10 pad_t20 clearfix mar_t30">
								<div class="pad_l20 accountCon">
									<div class="fs_18 txt_center fc_3">
										<span>绑定第三方网站</span>
									</div>
									<div class="fs_14 txt_center fc_9 mar_t10 mar_b30">
										<span>可使用第三方账号进行直接登录</span>
									</div>
									<!-- 自定义模态框 -->
								    <!-- <div class="easyDialog_wrapper" id="easyDialogWrapper" style="display:none">
									  <div class="easyDialog_content">
									    <h4 class="easyDialog_title" id="easyDialogTitle">
									      <a href="javascript:void(0)" title="验证登录密码" class="close_btn" id="closeBtn">&times;</a>
									     	验证登录密码
									    </h4>
									    <div class="easyDialog_text">为了您的账户安全，请验证登录密码</div>
									    	用户名<input id="accountId-modal" type="text" value="JSON.pase"/></br>
									    	密码<input id="passwordId-modal" type="password"/></br>
										    <div class="easyDialog_footer">
										      <button class="btn_highlight" id="easyDialogYesBtn" onclick="bingdingAccount()">确定</button>
										    </div>
									  </div>
									</div
								    <div class="easyDialog_wrapper" id="easyDialogWrapperTwo" style="display:none">
									  <div class="easyDialog_content">
									    <h4 class="easyDialog_title" id="easyDialogTitleTwo">
									      <a href="javascript:void(0)" title="验证登录密码" class="close_btn" id="closeBtnTwo">&times;</a>
									     	验证登录密码
									    </h4>
									    <div class="easyDialog_text">为了您的账户安全，请验证登录密码</div>
									    	用户名<input id="accountId-modalTwo" type="text" value=""/></br>
									    	密码<input id="passwordId-modalTwo" type="password"/></br>
										    <div class="easyDialog_footer">
										      <button class="btn_highlight" id="easyDialogYesBtnTwo" onclick="bingdingAccountTwo()">确定</button>
										    </div>
									  </div>
									</div> -->
									<ul class="wid_w360 bindqq clearfix mar_t30">
														    <li class="fl txt_center pad_t10 pad_b10 mar_r30">
																<div class="bindmanage_icon bindmanage_icon_qq"></div>
																<div class="fc_3 mar_t10"></div>
																<c:choose>
																	<c:when test="${appNameMap['qqState']=='hadBD'}">
																		<a href="javascript:void(0);" rel="leanModal" onclick="thirdAccountDelWith(this)" name="解除qq绑定" class="btn btn_36c btn_size100 mar_10" id="qqAccount">解除绑定</a>
																	</c:when>
																	<c:otherwise>
																		<a href="javascript:void(0);" onclick="thirdAccountDelWith(this)" name="绑定qq" class="btn btn_36c btn_size100 mar_10" id="qqAccountBD">绑定账户</a>
																	</c:otherwise>
																</c:choose>
															</li>
															<li class="fr txt_center pad_t10 pad_b10 mar_l30">
																<div class="bindmanage_icon bindmanage_icon_sina"></div>
																<div class="fc_3 mar_t10"></div>
																<%-- ${appNameMap['wbState']=='hadBD'} --%>
																<c:choose>
																	<c:when test="${appNameMap['wbState']=='hadBD'}">
																		<a href="javascript:void(0);" rel="leanModal" onclick="thirdAccountDelWith(this)" name="解除wb绑定" class="btn btn_36c btn_size100 mar_10" id="wbAccount">解除绑定</a>
																	</c:when>
																	<c:otherwise>
																		<a href="javascript:void(0);" onclick="thirdAccountDelWith(this)" name="绑定wb" class="btn btn_36c btn_size100 mar_10" id="wbAccountBD">绑定账户</a>
																	</c:otherwise>
																</c:choose>
															</li>						 
									</ul>
								</div>
							</div>
						</div>	
				</div>
 		</div>
	</div>
<div class="bg"></div>	
<div id="tipBox">
 
 </div>
  <script type="text/javascript" src="${basePath}/resources/resource/JxUserScript/securitycenter.js"></script>
  <script>
  	function thirdAccountDelWith(txt){
  		var aid=txt.id;
  		var aname=txt.name;
  		/* var RMQQFn = function(){
  				var delQQAccountURL="${pageContext.request.contextPath}/third/removebingDingQQ.action";
	  		  $.post(delQQAccountURL,function(data){
	  			  //alert(data);
	  			  if(data=="success"){
	  				  easyDialog.close();
	  				  return true;
	  			  }else if("fail"){
	  				  //easyDialog.close();
	  				  return false; 
	  			  }
	  		  });
  		  };
  		var RMWBFn = function(){
  			//alert("解除..");
				var delQQAccountURL="${pageContext.request.contextPath}/third/removebingDingWB.action";
		  		  $.post(delQQAccountURL,function(data){
		  			  //alert(data);
		  			  if(data=="success"){
		  				  easyDialog.close();
		  				  return true;
		  			  }else if(data=="fail"){
		  				 //easyDialog.close();
		  				  return false; 
		  			  }
		  		  });
		  }; */
		  	  
		  /* var callWBFn = function(){
			  //alert("弹出........");
			  //window.location.reload();
			  //location.reload(true);
			  window.location.href="${pageContext.request.contextPath}/third/bingDing.action";
			  window.location.href = window.location.href;
		  };
		  var callQQFn = function(){
			  window.location.href="${pageContext.request.contextPath}/third/bingDing.action";
			  window.location.href = window.location.href;
			  //alert("真的要解除？");
			  //location.reload(true);
			  //window.location.href = window.location.href;
			  //location.replace(document.referrer);
			  //parent.location.reload(); 
			  //window.location.reload();
			  
		  }; */
		  /* var bdWBFn = function(){
			  window.location.href="${pageContext.request.contextPath}/weibo/bindWB.action"; */
				/* var delQQAccountURL="${pageContext.request.contextPath}/third/removebingDingWB.action";
		  		  $.post(delQQAccountURL,function(data){
		  			alert("返回参数："+data);
		  			  if(data=="success"){
		  				  $("#wbAccount").text("绑定账户");
		  				  easyDialog.close();
		  				  alert("解除绑定");
		  				  return true;
		  			  }else if(data=="fail"){
		  				  easyDialog.close();
		  				alert("解除绑定失败");
		  				  return false; 
		  			  }
		  		  }); 
		  };*/
		  /* var bdQQFn = function(){
			  window.location.href="${pageContext.request.contextPath}/qq/bindQQ.action"; */
				 /*var delQQAccountURL="${pageContext.request.contextPath}/third/removebingDingWB.action";
		  		  $.post(delQQAccountURL,function(data){
		  			alert("返回参数："+data);
		  			  if(data=="success"){
		  				  $("#wbAccount").text("绑定账户");
		  				  easyDialog.close();
		  				  alert("解除绑定");
		  				  return true;
		  			  }else if(data=="fail"){
		  				  easyDialog.close();
		  				alert("解除绑定失败");
		  				  return false; 
		  			  }
		  		  }); 
		  };*/
  		if(aname=="解除qq绑定"){
  			/* easyDialog.open({
  				container : {
  				    header : '解除qq绑定',
  				    content : '是否解除第三方账户绑定？',
  				    yesFn : RMQQFn,
  				    noFn : true
  				  },
  				callback : callQQFn
  			}); */
  			//alert("确定解除绑定吗？");
  			
  			 /* if (confirm("确定要解除绑定QQ吗？")){ 
  		      var delQQAccountURL="${pageContext.request.contextPath}/third/removebingDingQQ.action";
  	  		  $.post(delQQAccountURL,function(data){
  	  			  if(data=="success"){
  	  				  easyDialog.close();
  	  				  return true;
  	  			  }else if("fail"){
  	  				  //easyDialog.close();
  	  				  return false; 
  	  			  }
  	  		  });
  	  		window.location.reload();
  			 } */
  			 
  			layer.confirm('确定要解除绑定QQ吗？', {
  			  btn: ['确定','取消'] //按钮
  			}, function(){
  				var delQQAccountURL="${pageContext.request.contextPath}/third/removebingDingQQ.action";
    	  		  $.post(delQQAccountURL,function(data){
    	  			  if(data=="success"){
    	  				  //easyDialog.close();
    	  				  return true;
    	  			  }else if(data=="fail"){
    	  				  //easyDialog.close();
    	  				  //window.location.reload();
    	  				  return false; 
    	  				  
    	  			  }
    	  		  });
  			    layer.msg('解除成功');
  			  window.location.reload();
  			  window.location.href=window.location.href;
  			  
  			});
  		}else if(aname=="解除wb绑定"){
  			//alert("解除wb绑定.....................");
  			/* easyDialog.open({
  				container : {
  				    header : '解除wb绑定',
  				    content : '是否解除第三方账户绑定？',
  				    yesFn : RMWBFn,
  				    noFn : true
  				  },
  				callback : callWBFn
  				
  			}); */
  			
  			layer.confirm('确定要解除绑定微博吗？', {
    			  btn: ['确定','取消'] //按钮
    			}, function(){
    				var delQQAccountURL="${pageContext.request.contextPath}/third/removebingDingWB.action";
      	  		  $.post(delQQAccountURL,function(data){
      	  			  if(data=="success"){
      	  				  //easyDialog.close();
      	  				  return true;
      	  			  }else if(data=="fail"){
      	  				  //easyDialog.close();
      	  				  return false; 
      	  			  }
      	  		  });
    			  layer.msg('解除成功');
    			  window.location.reload();
    			  window.location.href=window.location.href;
    			});
  			   
  		}else if(aname=="绑定wb"){
  			/* easyDialog.open({
				container : {
				    header : '绑定微博',
				    content : '是否需要绑定微博？',
				    yesFn : bdWBFn,
				    noFn : true
				  },
				/* callback : callWBFn */
			//}); 
			layer.confirm('是否需要绑定微博？', {
    			  btn: ['确定','取消'] //按钮
    			}, function(){
    				window.location.href="${pageContext.request.contextPath}/weibo/bindWB.action";
      	  		  $.post(delQQAccountURL,function(data){
      	  			  if(data=="success"){
      	  				  //easyDialog.close();
      	  				  return true;
      	  			  }else if(data=="fail"){
      	  				  //easyDialog.close();
      	  				  return false; 
      	  			  }
      	  		  });
    			  layer.msg('绑定成功', {icon: 1});
    			window.location.reload();
    			});
			
			
			
  			/* var d = dialog({
  				title: '消息',
  				content: '<input id="property-returnValue-demo" value="1" />',
  				ok: function () {
  					var value = $('#property-returnValue-demo').val();
  					this.close(value);
  					this.remove();
  				}
  			});
  			d.addEventListener('close', function () {
  				alert(this.returnValue);
  			});
  			d.show(); */
  			/* var userBaseAccountInfo='${userBaseAccountInfo}';
  		    var jsonUser=JSON.parse(userBaseAccountInfo);
  			$("#accountId-modalTwo").val(jsonUser.loginname);
  			easyDialog.open({
  				container : 'easyDialogWrapperTwo',
  			}); */
		}else if(aname=="绑定qq"){
			/* easyDialog.open({
  				container : {
  				    header : '绑定QQ',
  				    content : '是否需要绑定QQ？',
  				    yesFn : bdQQFn,
  				    noFn : true
  				  },
  				/* allback : callWBFn*/
  			//}); */
  			
  			layer.confirm('是否需要绑定QQ？', {
    			  btn: ['确定','取消'] //按钮
    			}, function(){
    				window.location.href="${pageContext.request.contextPath}/qq/bindQQ.action";
      	  		  $.post(delQQAccountURL,function(data){
      	  			  if(data=="success"){
      	  				  return true;
      	  			  }else if(data=="fail"){
      	  				  return false; 
      	  			  }
      	  		  });
    			  layer.msg('绑定成功', {icon: 1});
    			//window.location.reload();
    			});
  			
			/* easyDialog.open({
  				container : {
  					content : 'easyDialogWrapper',
  				  },
  			}); */
  		}
  	}
  			 
  </script>
</body>
<!-- <script type="text/javascript">
  $(document).ready(function(){
	  var userBaseAccountInfo='${userBaseAccountInfo}';
	  var jsonUser=JSON.parse(userBaseAccountInfo);
	  alert(jsonUser.loginname);
  });
  	function bingdingAccount(){
  		$("#easyDialogWrapper").hide();
 		 window.location.href="${pageContext.request.contextPath}/qq/bindQQ.action";
  		var vrfPwdUrl="${pageContext.request.contextPath}/third/varifyPsw.action";
  		var pwdInputVal=$("#passwordId-modal").val();
  		$.post(vrfPwdUrl,{pwd:pwdInputVal},function(data){
  			alert(data);
  			if(data=="success"){
  				var bindTUrl="${pageContext.request.contextPath}/qq/Rgtoqq.action";
  			}
  		});
  	}
  	function bingdingAccountTwo(){
  		$("#easyDialogWrapperTwo").hide();
  		 window.location.href="${pageContext.request.contextPath}/weibo/bindWB.action";
  		var vrfPwdUrl="${pageContext.request.contextPath}/third/varifyPsw.action";
  		var pwdInputVal=$("#passwordId-modalTwo").val();
  		$.post(vrfPwdUrl,{pwd:pwdInputVal},function(data){
  			alert(data);
  			if(data=="success"){
  				window.location.href="${pageContext.request.contextPath}/weibo/registerForWB.action";
  			}
  		});
  	}
  </script> -->
</html>