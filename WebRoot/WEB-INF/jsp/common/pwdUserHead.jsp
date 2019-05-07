<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.fr.login dl:hover dd{display: block;}
</style>
 <!--head-->
<div class="niwdoawi_top">
	<div class="header w1000 clearfix">
        <div class="fl tel">
        	<img src="${basePath}/resources/resource/Picture/tel.jpg" width="16" height="30"/><em class="fs_14 mr_5">客服热线</em><i class="aril">400-7910-888</i>
        </div>
        <div class="fr login">
            <input type="hidden" value="" id="stok"/>
        	<div class='login_bt'>
	           <a href="${basePath}/user/tologin.action" title="登录" class="fff">登录</a>
	           <a href="${basePath}/user/register.action" title="注册" class="fff">注册</a>
	        </div>
            <dl>
                <dt><a href="{:U('Message/Investors')}" target="_blank" rel="nofollow" title="账户中心" class="txnone">账户中心</a></dt>
                <dd><a href="{:U('Message/step')}" target="_blank" rel="nofollow" title="充值">充值</a></dd>
                <dd><a href="{:U('Message/carry')}" target="_blank" rel="nofollow" title="提现">提现</a></dd>
                <dd><a href="{:U('Message/myacceptlist')}" target="_blank" rel="nofollow" title="我的投资">我的投资</a></dd>
                                <dd><a href="{:U('Message/myacceptlist')}" target="_blank" rel="nofollow" title="我的投资">我的投资</a></dd>
                
            </dl>
        </div>
    </div>
</div>
<div class="niwdoawi_center" style="background:none; box-shadow:none">
	<div class="w1000 logo clearfix">
    	<a href="{:U('Index/index')}" title="给梦想可能" class="fl">
    		<img src="${basePath}/resources/Images/logo.png"  height="52" alt="干将网贷"/>
    	</a>
    </div>
</div>
<!--head end-->	 