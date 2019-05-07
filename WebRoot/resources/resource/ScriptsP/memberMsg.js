	/*查询多选*/
	$("input[name='checkval']").click(function() {
			var ids = [];
			$("input[name='checkval']:checked").each(function() {
				ids.push($(this).val());
			});
			$("#msgId").val(ids.join(","));			
		});
		
	/*查询当前*/
    function select1(){
    		 var aid= ($('#checkval').val());
    		 $("#msgId").val(aid);
  
    }
    
    /*查询所有复选框*/
    function selectall(){
    var ids = document.getElementsByName("checkval"); 
    var s = [];
    if(document.myForm.checkall.checked) {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="checked";
                        s[s.length]=ids[i].value;
                    }
                } else {
                    for(var i=0; i<ids.length; i++) {
                        ids[i].checked="";
                    }
                }
                $("#msgId").val(s.join(","));        
    }
    
function delete1(){
	var id = $("#msgId").val();
	if(id ==''){
		return false;
	}
	showCon_1();
}
//确定删除
function confirmDel(){
	var id = $("#msgId").val();
    var stok = "";
    if(document.getElementById ("stok")){
        stok = document.getElementById ("stok").value;
    }
	$.ajax({
		type : "post",
		url : "/member/deleteMemberMsg.do",
		data : {
			msgId : id,
            stok:stok
		},
		async : false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(msg) {
			if (msg > 0) {
				window.location.href = "/member/memberMsg.do";
			} else {
				alert("删除失败，请重试！");
			}
		}
	});
}

//点击标记为已读
function updateIsRead(id, title) {
	$.ajax({
		type : "post",
		url : "/member/updateIsRead.do",
		data : {
			msgId : id
		},
		async : false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(msg) {
			if (msg > 0) {
				// Adobe | Begin | zhenhua.xi | 20141103
				adobeSend4znxxdj(title);
				// Adobe | End

				/* window.location.reload(); */
				$("#" + id + "content").css("display", "");
				$("#" + id + "ico").removeClass().addClass(
						"nwd_icon nwd_icon_message gift_atten  mar_r20");
				$("#" + id + "update")
						.attr("onclick", "openMsg('" + msg + "')");
				// 关闭其他信息
				$("[id$='content']").each(function() {
					var name = $(this).attr('name');
					if (name != msg) {
						$(this).css("display", "none");
					}
				});
			} else {
				alert("读取失败，请重试！");
			}
		}
	});
}

//批量标记为已读
function batchUpdateIsRead(){
	var idsStr = $("#msgId").val();
	if(idsStr==""){
		return false;
	}
	$.ajax({
		type : "post",
		url : "/member/updateIsRead.do",
		data : {
			msgId : idsStr
		},
		async : false,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(msg) {
			if (msg > 0) {
				var idsArr = idsStr.split(",");				
				$.each(idsArr,function(n,id) {					
					$("#"+id+"content").css("display","");
					$("#"+id+"ico").removeClass().addClass("nwd_icon nwd_icon_message gift_atten  mar_r20");
					$("#"+id+"update").attr("onclick", "openMsg('"+msg+"')");
					//关闭其他信息
					$("[id$='content']").each(function () {
						 var name = $(this).attr('name');
						 if(name != msg){
							 $(this).css("display","none");
						 }
					 });
			    });
				window.location.reload();
			}else {
				alert("读取失败，请重试！");
			}
		}
	});
}

function openMsg(id, title){
	// Adobe | Begin | zhenhua.xi | 20141103
	adobeSend4znxxdj(title);
	// Adobe | End
	if($("#"+id+"content").is(":visible") == true){
		console.log(1);
		$("#"+id+"content").css("display","none");
		
	}else{
		 $("[id$='content']").each(function () {
			 var name = $(this).attr('name');
			 if(name != id){
				 $(this).css("display","none");
			 }
		 });
		$("#"+id+"content").css("display","");
		
	}
}

// Adobe | Begin | zhenhua.xi | 20141103
function adobeSend4znxxdj(title){
	var s = s_gi(s_account);
	s.linkTrackVars = "eVar38,events";
	s.linkTrackEvents = "event19";
	//点击的站内消息主题
	s.eVar38 = title;
	//站内消息点击事件
	s.events = "event19";
	npo.tl(this,'o','znxxdj');
}
// Adobe | End