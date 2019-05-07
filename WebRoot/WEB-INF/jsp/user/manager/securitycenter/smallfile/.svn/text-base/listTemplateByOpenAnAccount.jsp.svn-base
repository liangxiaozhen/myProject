<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 开户 start -->
<tr class="validation-status clearfix">
        <td class="left wid_w120 bor_b">第三方账号</td>
        <td class="wid_w480 fc_9 bor_b">未开通第三方账号</td><!--pos1 -->
        <td class="wid_w100 fc_9 bor_b"><i class="icon_base icon_16 icon_base_tipnote16 vertical_middle mar_r10"></i><span class="vertical_middle">未开通</span></td>
        <td class="bor_b wid_w200"><div class="pad_l30"><a title="立即开通" class="OpenAnAccount" href="javascript:;" onclick="securitycenter.openAnAccountCheck(this)">立即开通</a></div></td>
</tr>
<tr class="only none OpenAnAccount_box">
   <td class="pad_t20 pad_b20" colspan="4">
    <table id="loginPwd" class="table_child form" width="100%" cellspacing="0" cellpadding="0">
     <tbody>
     <c:choose>
     	<c:when test="${not empty userBaseAccountInfo and userBaseAccountInfo.isreally == 1 and not empty userBaseAccountInfo.realname and not empty userBaseAccountInfo.certificationnumber}">
     			<tr>
			      	<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">真实姓名</th>
			      	<th class="rightside pad_b20" valign="top">
			                   <input type="text" id="relaname" name="relaname" value="${userBaseAccountInfo.realname }" class="input_all ui-input w320-input" autocomplete="off">
			                   <br><span id="relanameMassage" style="display:none"></span>
			         </th>
			      </tr>
			      <tr>
			         	<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">身份证号码</th>
				               <th class="rightside pad_b20" valign="top">
				               <input type="text" id="relanumber" name="relanumber" value="${userBaseAccountInfo.certificationnumber}" class="input_all ui-input w320-input" autocomplete="off">
				               <br><span id="relanumberMassage"  style="display:none"></span>
			            </th>
			       </tr>
     	</c:when>
     	<c:otherwise>
     		<tr>
		      	<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">真实姓名</th>
		      	<th class="rightside pad_b20" valign="top">
		                   <input type="text" id="relaname" name="relaname" class="input_all ui-input w320-input" autocomplete="off">
		                   <br><span id="relanameMassage" style="display:none"></span>
		         </th>
		      </tr>
		      <tr>
		         	<th class="leftside pad_b20 wid_w190 pad_t10" valign="top">身份证号码</th>
			               <th class="rightside pad_b20" valign="top">
			               <input type="text" id="relanumber" name="relanumber" class="input_all ui-input w320-input" autocomplete="off">
			               <br><span id="relanumberMassage"  style="display:none"></span>
		            </th>
		       </tr>
      	</c:otherwise>
     </c:choose>
       
       <c:choose>
       			<c:when test="${not empty userBaseAccountInfo and userBaseAccountInfo.ismobileverify == 1}">
       					<tr>
				            <th class="leftside wid_w190 pad_b20 pad_t10" valign="top">短信验证码</th>
				            <th class="rightside pad_b20" valign="top">
				            <input type="text" id="smscodebyrela" name="smscodebyrela" class="input_all ui-input w320-input" autocomplete="off">
				            &nbsp;&nbsp;<button class="btn btn_bgf60 btn_size100 sc_sendSSM" style="height:38px;"  id="sc_sendSSM" data-isphone= "1" onclick="securitycenter.sendSSMByOpenAnAccount(this)">发送短信</button>
				            <button class="btn btn_bgf60 btn_size400 sc_sendSSM" style="height:38px;display:none;" id="sc_sendSSMed">发送短信</button>
				            &nbsp;&nbsp; <span id="sc_sendSSMMassage" style="display:none;"></span>
				            </th>
				        </tr>
       			</c:when>
       			<c:otherwise>
       					<tr>
				            <th class="leftside wid_w190 pad_b20 pad_t10" valign="top">手机号</th>
				            <th class="rightside pad_b20" valign="top">
				            <input type="text" id="phone" name="phone" class="input_all ui-input w320-input" autocomplete="off">
				            &nbsp;&nbsp;<button class="btn btn_bgf60 btn_size100 sc_sendSSM" style="height:38px;" id="sc_sendSSM" onclick="securitycenter.sendSSMByOpenAnAccount(this)">发送短信验证码</button>
				            <button class="btn btn_bgf60 btn_size400 sc_sendSSM" style="height:38px;display:none;" id="sc_sendSSMed">发送短信</button>
				             &nbsp;&nbsp; <span id="sc_sendSSMMassage" style="display:none;"></span>
				            </th>
				        </tr>
				        <tr>
				            <th class="leftside wid_w190 pad_b20 pad_t10" valign="top">短信验证码</th>
				            <th class="rightside pad_b20" valign="top">
				            <input type="text" id="smscodebyrela" name="smscodebyrela" class="input_all ui-input w320-input" autocomplete="off">
 				            <br><span id="smscodeMassage" style="display:none;"></span>
				            </th>
				        </tr>
       			</c:otherwise>
       </c:choose>
        <tr>
            <th>&nbsp;</th>
            <th class="rightside">
            <button class="btn btn_36c btn_size120" onclick="securitycenter.openAnAccount(this)" id="sc_openAnAccount">立即开户</button>
            &nbsp;&nbsp;&nbsp;&nbsp;<span id="sc_openAnAccountMassage" style="display:none;"></span>
            </th>
        </tr>
     </tbody>
    </table>
    <!-- 成功页面 start -->
    <div class="sucess clearfix wid_w240 none" id="sucessPwd">
              <div class="icon_base icon_50 icon_base_tipright50 mar_r10 fl"></div>
              <div class="fl">
                  <div class="fs_18 pad_b20">开户成功！</div>
                  <div><a class="btn btn_size100 btn_blue" href="javascript:;" title="确定">确定</a></div>
              </div>
    </div>
    <!-- 成功页面 end -->
   </td>
 </tr>
 <!-- 开户 end -->