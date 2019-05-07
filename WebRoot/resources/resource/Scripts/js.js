// JavaScript Document
$(function(){
	function swap(name,title,content,Sub,cur){
		  $(name+' '+title).mouseover(function(){
			  $(this).addClass(cur).siblings().removeClass(cur).parent().siblings('.tab_content').find('.tj_nr01').hide();
			  $(content+' '+Sub).eq($(name+' '+title).index(this)).show().siblings().hide();
		  });
		};
		swap(".nwd_tab","span",".tab_content",".img","active");
});