package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moneymoremore.util.Common;
import com.moneymoremore.util.HttpClientUtil;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.moneymoremore.util.SignUtils;
import com.ptpl.controller.BaseController;
import com.ptpl.controller.moneymoremore.model.LoanOrderQueryBean;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.service.RedEnveLopeItemServiceI;

/**
 * 现金红包对账（乾多多）
 * @author admin
 *
 */

@Controller
@RequestMapping("/admin/moneyMoreMoreBlend")
public class MMMUserRedBlendController extends BaseController{

	@Autowired
	private RedEnveLopeItemServiceI redEnveLopeItemService;
	
	//现金红包转账  人工勾兑
	@RequestMapping("/userRedBlend")
	public void userRedBlend(BigDecimal id){
		
		//根据传过来的id来获取数据
		RedEnveLopeItem r = redEnveLopeItemService.selectByPrimaryKey(id);
		
		String platformMoneymoremore = "p2089";//平台乾多多标识
		//Integer action = null;//查询类型   空.转账  1.充值  2.提现
		String orderNo = r.getOrderno();//网贷平台订单号
		
		String dataStr = platformMoneymoremore.trim() +  orderNo.trim();
		RsaHelper rsa = RsaHelper.getInstance();
		//用私钥签名
		String signInfo = rsa.signData(dataStr, RsaHelper.privateString);
		Map<String,String> req = new TreeMap<String,String>();
		req.put("PlatformMoneymoremore", platformMoneymoremore);
		//req.put("Action", null);
		req.put("OrderNo", orderNo);
		req.put("SignInfo",signInfo);
		String[] resultArr = HttpClientUtil.doPostQueryCmd("http://test.moneymoremore.com:88/main/loan/loanorderquery.action", req);
		
		System.out.println("00==="+resultArr[0]);
		System.out.println("11==="+resultArr[1]);
		if (StringUtils.isNotBlank(resultArr[1]) && (resultArr[1].startsWith("[") || resultArr[1].startsWith("{"))){
			
			//转账
			List<Object> loanObjectList	= Common.JSONDecodeList(resultArr[1], LoanOrderQueryBean.class);		
			int length = loanObjectList.size();
			if(loanObjectList !=null && length > 0){
				for(int i=0;i<length;i++){
					if(loanObjectList.get(i) instanceof LoanOrderQueryBean){
						LoanOrderQueryBean loqb =(LoanOrderQueryBean)loanObjectList.get(i);
						System.out.println("loqb:   "+loqb);
						String remark="";
						//是否已转账
						//Integer transferState = loqb.getTransferState();//转账状态
						Double amount = loqb.getAmount();
						System.out.println(amount.toString());
						
						boolean flag1 = (df1.format(r.getReamount()).equals(df1.format(amount)) ? true : false);
						if(!flag1){
							remark+="转账金额异常";
						}
						RedEnveLopeItem reli = new RedEnveLopeItem();
						reli.setManbtime(new Date());//人工勾兑时间
						reli.setId(id);//红包对账记录表id
						if(r.getIsmanblending()==0){//表明未勾兑
							reli.setIsmanblending((short)1);//人工勾兑 0.未勾兑  1.已勾兑
							reli.setReceivetime(new Date());//人工勾兑接收数据时间 第一次
						}
						reli.setReqquerydata(req.toString());//请求数据包
						reli.setRecresultdata(resultArr[1]);//接收数据包
						reli.setRemark(remark);
						redEnveLopeItemService.updateByPrimaryKeySelective(reli);
						
					}
				}
			}
			
		}
	
	}
	
	//现金红包转账  系统勾兑
	@RequestMapping("/redEnvelopeSysBlend")
	public void redEnvelopeSysBlend(HttpServletRequest request,HttpServletResponse response){
			
		String platformMoneymoremore = "p2089";//平台乾多多标识
		//Integer action = null;//查询类型   空.转账  1.充值  2.提现
		String beginTime = "20170223090101";//开始日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String endTime = sdf.format(new Date());//结束日期
		
		String dataStr = platformMoneymoremore + beginTime + endTime;
		System.out.println("dataStr===="+dataStr);
		
		RsaHelper rsa = RsaHelper.getInstance();
		//用私钥签名
		String signInfo = rsa.signData(dataStr, RsaHelper.privateString);
		System.out.println("私钥签名结果：    "+signInfo);
		Map<String,String> req = new TreeMap<String,String>();
		req.put("PlatformMoneymoremore", platformMoneymoremore);
		//req.put("Action", "null");
		req.put("BeginTime", beginTime);
		req.put("EndTime", endTime);
		req.put("SignInfo", signInfo);
		
		String[] resultArr = HttpClientUtil.doPostQueryCmd(MMMParams.RECONCILIATION, req);
		System.out.println("00====   "+resultArr[0]);
		System.out.println("11====   "+resultArr[1]);
		
		if (StringUtils.isNotBlank(resultArr[1]) && (resultArr[1].startsWith("[") || resultArr[1].startsWith("{"))){
			
			// 转账
			List<Object> loanobjectlist = Common.JSONDecodeList(resultArr[1], LoanOrderQueryBean.class);
			System.out.println("loanobjectlist===="+loanobjectlist);
			int size = loanobjectlist.size();
			if(size > 0){
				for(int i=0;i<size;i++){
					if(loanobjectlist.get(i) instanceof LoanOrderQueryBean){
						
						LoanOrderQueryBean loqb = (LoanOrderQueryBean) loanobjectlist.get(i);
						System.out.println("loqb===="+loqb);
						System.out.println("转账类型是否为3（其它）："+loqb.getTransferAction());
						if(loqb!=null && loqb.getTransferAction()==3){
							//获取双乾返回需要对比的值
							Double amount = loqb.getAmount();//金额
							//根据网贷平台订单号查询本地数据库的充值记录
							RedEnveLopeItem reli = redEnveLopeItemService.getByOrderNo(loqb.getOrderNo());
							
							if(reli!=null){
								//对比金额
								boolean flag1 = (df1.format(reli.getReamount()).equals(df1.format(amount)) ? true : false);
								String remark = "";
								if(!flag1){
									remark+="转账金额异常";
								}
								System.out.println("id=="+reli.getId());//id
								System.out.println("网贷平台订单号:  "+reli.getOrderno());
								RedEnveLopeItem r = new RedEnveLopeItem();
								r.setSysbtime(new Date());//系统勾兑时间
								r.setId(reli.getId());//红包对账记录表id
								if(reli.getIsblending()==0){//表明系统未勾兑
									r.setIsblending((short)1);//系统勾兑 0.未勾兑  1.已勾兑
									r.setSysrectime(new Date());//系统勾兑接收数据时间 第一次
								}
								r.setReqquerydata(req.toString());//请求数据包
								System.out.println("接受数据包：  "+loanobjectlist.get(i).toString());
								r.setRecresultdata(loanobjectlist.get(i).toString());//接收数据包
								r.setRemark(remark);
								redEnveLopeItemService.updateByPrimaryKeySelective(r);
							}
							
						}
					}
					
					
				}
			}
			
		}
		
	}
	
	
}
