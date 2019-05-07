package com.ptpl.controller.huifu;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huifu.util.SignUtils;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.model.UserTender;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserDebtAttornServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.AutoGenerateRepayMentUtil;
import com.ptpl.web.util.HuifuParams;
import com.ptpl.web.util.MD5;
/**
 * 债转汇付返回
 * @author admin
 *
 */
@Controller
@RequestMapping("/huifu/creditAssign")
public class HuifuUserDebtAttornController {
	@Autowired
	UserAccountServiceI userAccountService;
	@Autowired
	UserDebtAttornServiceI   userdebtattornService;
	@Autowired
	UserTenderServiceI   userTenderService;
	@Autowired
	AccInExRecordServiceI   accInExRecordService;
	@RequestMapping(value="/creditAssignCallback")
	public synchronized void  creditAssignCallback(HttpServletRequest request,HttpServletResponse response,HuifuParams huifuParams) throws Exception{
		 System.out.println("=======getRespCode==========="+huifuParams.getRespCode());
		 System.out.println("=======getRespDesc==========="+huifuParams.getRespDesc());
		 System.out.println("=======getCmdId=============="+huifuParams.getCmdId());
		 System.out.println("=======getMerCustId=========="+huifuParams.getMerCustId());
		 System.out.println("=======getSellCustId========="+huifuParams.getSellCustId());
		 System.out.println("=======getCreditAmt=========="+huifuParams.getCreditAmt());
		 System.out.println("=======getCreditDealAmt======"+huifuParams.getCreditDealAmt());
		 System.out.println("=======getFee================"+huifuParams.getFee());
		 System.out.println("=======getBuyCustId=========="+huifuParams.getBuyCustId());
		 System.out.println("=======getOrdId=============="+huifuParams.getOrdId());
		 System.out.println("=======getOrdDate============"+huifuParams.getOrdDate());
		 System.out.println("=======getRetUrl============="+huifuParams.getRetUrl());
		 System.out.println("=======getBgRetUrl==========="+huifuParams.getBgRetUrl());
		 System.out.println("=======getLcId==============="+huifuParams.getLcId());
		 System.out.println("=======getTotalLcAmt========="+huifuParams.getTotalLcAmt());
		 System.out.println("=======getMerPriv============"+huifuParams.getMerPriv());
		 System.out.println("=======getRespExt============"+huifuParams.getRespExt());
		 System.out.println("=======getChkValue==========="+huifuParams.getChkValue());
		 System.out.println("=======BidCreditAmt==========="+huifuParams.getBidCreditAmt());
		 
		 /*返回值拼接*/
			StringBuffer sb = new StringBuffer();
			sb.append(StringUtils.trimToEmpty(huifuParams.getCmdId())).append(StringUtils.trimToEmpty(huifuParams.getRespCode()))
			.append(StringUtils.trimToEmpty(huifuParams.getMerCustId())).append(StringUtils.trimToEmpty(huifuParams.getSellCustId()))
			.append(StringUtils.trimToEmpty(huifuParams.getCreditAmt())).append(StringUtils.trimToEmpty(huifuParams.getCreditDealAmt()))
			.append(StringUtils.trimToEmpty(huifuParams.getFee())).append(StringUtils.trimToEmpty(huifuParams.getBuyCustId()))
			.append(StringUtils.trimToEmpty(huifuParams.getOrdId())).append(StringUtils.trimToEmpty(huifuParams.getOrdDate()))
			/*.append(StringUtils.trimToEmpty(huifuParams.getRetUrl()))*/.append(StringUtils.trimToEmpty(huifuParams.getBgRetUrl()))
			.append(StringUtils.trimToEmpty(huifuParams.getMerPriv()))/*.append(StringUtils.trimToEmpty(huifuParams.getRespExt()))*/
			.append(StringUtils.trimToEmpty(huifuParams.getLcId())).append(StringUtils.trimToEmpty(huifuParams.getTotalLcAmt()));
			String plainStr = sb.toString();
			System.out.println(plainStr);
			boolean flag = false; 
			MD5 md5 = new MD5();
			String md5Str = md5.getMD5Info(plainStr).toLowerCase();
				flag = SignUtils.verifyByRSA(md5Str, huifuParams.getChkValue());
				if (!flag) {
				    System.out.println("验证签名失败...");
				}  
				if (StringUtils.isNotBlank(huifuParams.getOrdId())) {
					PrintWriter out = response.getWriter();
					out.print("RECV_ORD_ID_".concat(huifuParams.getOrdId()));
				}
			
				
				if(flag && "000".equals(huifuParams.getRespCode())){ //解签成功
					 String  a = huifuParams.getMerPriv().substring(0, huifuParams.getMerPriv().length());
					 String []merpriv = a.split(","); 
					 String orderno = merpriv[0].substring(1,  merpriv[0].length());//债转标号
					 String tenderid = merpriv[1];//标号id
					 String userId = merpriv[2];//购买人id
					 String daorderno = merpriv[3].substring(0, merpriv[3].length()-1);//债转订单号
					 
					 System.out.println(orderno+"投标订单号");
					 System.out.println(daorderno+"债转订单号");
					 System.out.println(userId+"承接人id");
					 BigDecimal tenderId = new BigDecimal(tenderid);//转化标的号id
					 BigDecimal userid = new BigDecimal(userId);//获取承接人id
					 
					 //根据债转标号查询相关信息
					 UserDebtAttorn userdebtattorn = userdebtattornService.getdaorderno(daorderno);
					 //更新投标记录
					 int aa = userdebtattornService.updateUserTender(huifuParams, orderno);
					 System.out.println(aa);
					 //获取汇付返回债转出的本金
					 Double amount = Double.valueOf(huifuParams.getCreditAmt());
					 //获取用户可债转金额
					 Double candaamount = userdebtattorn.getCandaamount();
					 
					 if(userdebtattorn.getDastatus()!=4){
						 if(Arith.sub(candaamount,amount)==0){//说明用户发布的债转金额已经全部债转出去了,就修改状态,改为已完成
							 userdebtattorn.setCandaamount(0.00);
							 userdebtattorn.setDastatus((short)4);//表示已经完成
						 }else{
							 userdebtattorn.setCandaamount(Arith.sub(candaamount,amount));//假如还没有转出完,就减去债转出去的金额
						 }
						 userdebtattornService.updateByPrimaryKeySelective(userdebtattorn);//修改用户债转设置表记录
						 userdebtattornService.updateUserAccount(huifuParams,userdebtattorn,orderno);
						 userdebtattornService.updateCJUserAccount(userid,huifuParams,userdebtattorn,orderno);
						 //生成债转还款计划:正常标的还款计划
						 System.out.println(tenderId);
						 System.out.println("BuyCustId"+huifuParams.getBuyCustId());
						 System.out.println("SellCustId"+huifuParams.getSellCustId());
						 System.out.println(orderno);
						 AutoGenerateRepayMentUtil.AutoGenerateRepayMentsBydebtAttornAfter(tenderId, huifuParams.getBuyCustId(), huifuParams.getSellCustId(),orderno);
						 
					 }
				}
		
	}
}
