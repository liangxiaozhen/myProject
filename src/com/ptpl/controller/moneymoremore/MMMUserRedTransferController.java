package com.ptpl.controller.moneymoremore;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Session_Constant;
import com.ptpl.controller.moneymoremore.model.LoanInfoBean;
import com.ptpl.controller.moneymoremore.model.LoanReturnInfoBean;
import com.ptpl.controller.moneymoremore.model.LoanSubmitInfoBean;
import com.ptpl.controller.moneymoremore.model.LoanTransferReturnBean;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.ActivityAwardList;
import com.ptpl.model.AdminUser;
import com.ptpl.model.Award;
import com.ptpl.model.RedEnveLopeItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.ActivityAwardListServiceI;
import com.ptpl.service.AdminUserServiceI;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.RedEnveLopeItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.web.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huifu.util.httpClient.HttpClientHandler;
import com.moneymoremore.util.Common;
import com.moneymoremore.util.HttpClientUtil;
import com.moneymoremore.util.RsaHelper;

@Controller
@RequestMapping("/moneymoremore/giro")
public class MMMUserRedTransferController {
	
	@Autowired
	UserFsAccountInfoServiceI userFsAccountInfoService;//用户托管账户信息Service

	@Autowired
	UserRedEnvelopeServiceI userRedEnvelopeService;//用户红包Service
	
	@Autowired
	UserAccountServiceI userAccountService;//用户账户表
	
	@Autowired
	AccInExRecordServiceI accInExRecordService;//账户收支记录表
	
	@Autowired
	AwardServiceI awardService;//奖品
	
	@Autowired
	RedEnveLopeItemServiceI redEnveLopeItemService;//红包发放对账记录表
	
	@Autowired
	ActivityAwardListServiceI activityAwardListService;//获奖名单表
	
	@Autowired
	AdminUserServiceI adminUserService;//用户管理员后台
	
	@RequestMapping("/transferAccounts")
	public synchronized void transferAccounts(HttpServletRequest request,HttpServletResponse response,BigDecimal id){
		
		//测试地址：http://113.99.87.125:8885/ptpjx/moneymoremore/giro/transferAccounts.action
		
		/*
		 * 根据id获取对应的获奖记录
		 * 用来获取转账金额
		 */
		ActivityAwardList aal=activityAwardListService.getselectById(id);
		System.out.println("现金红包====="+aal);
		Double amount = aal.getAwardmoney()*aal.getAwardquantity();//转账金额
		/* UserFsAccountInfo:用户托管账户信息
		 * 根据baseid查出当前用户的客户号
		 */
		UserFsAccountInfo ufai=userFsAccountInfoService.findUserFsAccountInfoByBaseId(aal.getBaseid());
		//用户没有开通托管账户
		String jsonStr = null;
		if(ufai==null){
			jsonStr = JSON.toJSONString("false");
			try {
				StringUtil.sendJsonData(response, jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else if(ufai.getIsopenfsinfo()==0){
			jsonStr = JSON.toJSONString("false");
			try {
				StringUtil.sendJsonData(response, jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		String basePath = StringUtil.getBasePath(request);
		
		List<LoanInfoBean> listLib = new ArrayList<LoanInfoBean>();
		
		LoanInfoBean lib = new LoanInfoBean();
		lib.setLoanOutMoneymoremore("p2089");//付款人乾多多标识
		lib.setLoanInMoneymoremore(ufai.getMoneymoremoreid());//收款人乾多多标识 比如："m111808"
		lib.setOrderNo(StringUtil.getNo());//网贷平台订单
		lib.setBatchNo(Common.getRandomNum(10));//网贷平台标号   自己定义
		lib.setAmount(amount);//金额
		//lib.setSecondaryJsonList("");//二次分配列表
		
		listLib.add(lib);
		
		//将模型进行JSON编码
		String LoanJsonList = Common.JSONEncode(listLib);
		
		System.out.println("LoanJsonList===="+LoanJsonList);
		
		LoanSubmitInfoBean lsib = new LoanSubmitInfoBean();
		lsib.setLoanJsonList(LoanJsonList);
		lsib.setPlatformMoneymoremore("p2089");//平台乾多多标识
		lsib.setTransferAction(3);//转账类型   1.投标  2.还款  3.其它
		lsib.setAction(2);//操作类型    1.手动转账    2.自动转账
		lsib.setTransferType(2);//转账方式  1.桥连   2.直连
		//lsib.setArrivalTime(null);//到账时间  空.实时转账   1.普通转账   2.次日转账
		lsib.setNeedAudit(1);//通过是否需要审核  空.需要审核   1.自动通过
		lsib.setRemark1(id.toString());//自定义备注  存放奖品名单列表的id
		//lsib.setReturnURL(basePath+"/moneymoremore/giro/userRedAddressReturn.action");//页面返回网址
		lsib.setNotifyURL(basePath+"/moneymoremore/giro/userRedAddressNotify.action");//后台通知网址
		
		userRedSign(lsib,request,response);//加签
		
	}
	
	//加签 验签及处理逻辑
	public void userRedSign(LoanSubmitInfoBean lsib,HttpServletRequest request,HttpServletResponse response){
		
		//后台管理员
		AdminUser au = (AdminUser) request.getSession().getAttribute(Session_Constant.ADMINUSER); 
		
		String loanJsonList = lsib.getLoanJsonList();
		String platformMoneymoremore = lsib.getPlatformMoneymoremore();//平台乾多多标识
		Integer transferAction = lsib.getTransferAction();//转账类型
		Integer action = lsib.getAction();//操作类型
		Integer transferType = lsib.getTransferType();//转账方式
		Integer needAudit = lsib.getNeedAudit();//通过是否需要审核
		String remark1 = lsib.getRemark1();//备注1
		//Integer ArrivalTime = lsib.getArrivalTime();//到账时间
		//String returnURL = lsib.getReturnURL();//页面返回网址
		String notifyURL = lsib.getNotifyURL();//后台通知网址
		
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtils.trimToEmpty(loanJsonList))
		  .append(StringUtils.trimToEmpty(platformMoneymoremore))
		  .append(StringUtils.trimToEmpty(transferAction.toString()))
		  .append(StringUtils.trimToEmpty(action.toString()))
		  .append(StringUtils.trimToEmpty(transferType.toString()))
		  .append(StringUtils.trimToEmpty(needAudit.toString()))
		  .append(StringUtils.trimToEmpty(remark1))
		  //.append(StringUtils.trimToEmpty(ArrivalTime.toString()))
		  //.append(StringUtils.trimToEmpty(returnURL))
		  .append(StringUtils.trimToEmpty(notifyURL));
		
		System.out.println("sb===="+sb);
		
		//签名
		RsaHelper rsa = RsaHelper.getInstance();
		//参加签名的是原串
		String signInfo = rsa.signData(sb.toString(), RsaHelper.privateString);
		System.out.println("signInfo===="+signInfo);
		
		//提交的时候要进行UrlEncode，编码为utf-8；
		String LoanJsonList = Common.UrlEncoder(loanJsonList, "utf-8");
		
		Map<String,String> req = new TreeMap<String,String>();
		req.put("LoanJsonList", LoanJsonList);
		req.put("PlatformMoneymoremore", platformMoneymoremore);
		req.put("TransferAction", transferAction.toString());
		req.put("Action", action.toString());
		req.put("TransferType", transferType.toString());
		req.put("NeedAudit", needAudit.toString());
		req.put("Remark1", remark1);
		//req.put("ReturnURL", returnURL);
		req.put("NotifyURL", notifyURL);
		req.put("SignInfo", signInfo);
		
		String[] resultArr = HttpClientUtil.doPostQueryCmd("http://test.moneymoremore.com:88/main/loan/loan.action", req);
		System.out.println("00===="+resultArr[0]);
		System.out.println("11===="+resultArr[1]);

		if(StringUtils.isNotBlank(resultArr[1])&&(resultArr[1].startsWith("[")||resultArr[1].startsWith("{"))){
			
			//公钥
			String publickey = RsaHelper.publicString;
			
			List<Object> objectList = Common.JSONDecodeList(resultArr[1], LoanTransferReturnBean.class);
			int length = objectList.size();
			if(objectList !=null && length >0){
				int line = 0;
				for(int i=0;i<length;i++){
					if(objectList.get(i) instanceof LoanTransferReturnBean){
						LoanTransferReturnBean ltrb = (LoanTransferReturnBean) objectList.get(i);
						System.out.println("返回参数===="+ltrb);
						
						//接收的时候要进行UrlDecode，编码为utf-8
						ltrb.setLoanJsonList(Common.UrlDecoder(ltrb.getLoanJsonList(), "utf-8"));
						
						String dataStr = ltrb.getLoanJsonList().trim() + ltrb.getPlatformMoneymoremore().trim() + ltrb.getAction().trim() + 
								ltrb.getRemark1().trim() + ltrb.getResultCode().trim();
						
						//验签
						boolean flag = rsa.verifySignature(ltrb.getSignInfo(), dataStr, publickey);
						
						System.out.println("验签flag===="+flag);
						//验签通过
						if(flag){
							if(StringUtils.isNotBlank(ltrb.getLoanJsonList())){
								List<Object> loaninfolist = Common.JSONDecodeList(ltrb.getLoanJsonList(), LoanReturnInfoBean.class);
								if(loaninfolist !=null && loaninfolist.size()>0){
									for(int j=0;j<loaninfolist.size();j++){
										if(loaninfolist.get(j) instanceof LoanReturnInfoBean){
											LoanReturnInfoBean lrib = (LoanReturnInfoBean) loaninfolist.get(j);
											System.out.println("lrib====="+lrib);
											
											//处理自己的业务逻辑
											//通过获奖名单里面红包的id获取对应的红包记录
											ActivityAwardList aal=activityAwardListService.getselectById(new BigDecimal(ltrb.getRemark1()));
											//现金红包发放对账记录表
											RedEnveLopeItem reli=new RedEnveLopeItem();
											reli.setOrderno(lrib.getOrderNo());//记录表的流水号 对应订单号
											reli.setRedenvelopeno(aal.getAwardno());//红包编号
											reli.setRedenvelopename(aal.getAwardname());//奖品的名称
											reli.setReamount(Float.parseFloat(lrib.getAmount()));//奖品的金额
											reli.setBaseid(aal.getBaseid());//用户id
											reli.setUsername(aal.getUsername());//用户名
											reli.setBusinesstype(ActAward_Constant.RECTYPE_MAP.get(aal.getAl().getActtype()));//业务类型  5.手动颁奖
											reli.setSendtime(new Date());//处理时间
											reli.setIsblending((short)0);//是否系统勾兑
											reli.setIsmanblending((short)0);//是否人工勾兑
											reli.setPaycompany("乾多多");//托管通道公司（乾多多）
											
											/*先根据流水号判断数据库中是否有该条数据，如果有，不再添加*/
											RedEnveLopeItem redeli=redEnveLopeItemService.getByOrderNo(lrib.getOrderNo());
											if(redeli == null){
												//插入数据
												int rows = redEnveLopeItemService.insert(reli);
												if(rows>0){
													//用户账户表
													UserAccount usAc=userAccountService.getUserAccountByBaseId(aal.getBaseid());
													Double balance = usAc.getBalance() + new Double(lrib.getAmount());
													Double avlbalance = usAc.getAvlbalance() + new Double(lrib.getAmount());
													Double freezeBalance = usAc.getFreezebalance() ==null?0.00 : usAc.getFreezebalance();
													//汇付转账成功后，才给用户余额进账，更新用户账户表
													usAc.setBalance(balance);
													usAc.setAvlbalance(avlbalance);
													//插入数据
													userAccountService.updateUseraccount(usAc);
													
													//账户收支记录表
													AccInExRecord aier = new AccInExRecord();
													aier.setBaseid(aal.getBaseid());//用户的id
													aier.setAieorderno(StringUtil.getNo());//收支记录流水号
													aier.setBorderno(lrib.getOrderNo());//业务流水号(这里放的是订单编号)
													aier.setType((short)32);//业务类型   现金
													aier.setInamount(new Double(lrib.getAmount()));//收入
													aier.setOutamount(0.00);//支出
													aier.setPaccount("p2089");//平台账户
													aier.setPinamount(0.00);//平台账户收入,平台产生的费用
													aier.setPoutamount(0.00);//平台账户支出,平台产生的费用
													aier.setStatus((short)1);//业务状态  0冻结  1成功 2失败
													aier.setDescription("现金红包转账");//说明
													aier.setBalance(avlbalance);//用户的可用余额
													aier.setFreebalance(freezeBalance);//用户的冻结余额
													aier.setTotalbalance(balance);//用户的总金额
													aier.setRecordtime(new Date());//发生时间
													aier.setRemark("现金红包转账");//备注
													//插入数据
													accInExRecordService.insertSelective(aier);
													
													//插入红包表记录(需要一份一份的插入)
													UserRedEnvelope urel= new UserRedEnvelope();
													urel.setBaseid(aal.getBaseid());//用户ID
													urel.setUreno(aal.getAwardno());//奖品编号
													urel.setRectype(aal.getAl().getActtype());//红包获取方式类型(1.注册  2.累投  3.单标  4.首投 5.手动颁奖 6.自主颁奖 7.完善资料)
													urel.setRetype(aal.getAwardattribute());//红包类型（1.现金，2.类现金，3.假现金）
													urel.setRestime(new Date());//红包发放时间
													urel.setRedenvelope(aal.getAwardmoney());//红包金额
													urel.setStatus((short)3);//奖品发放状态 （1待审核 2待处理 3已领取 4待确认 5已经确认 6发货中 7领取失败 8审核失败）
													urel.setIsuse((short)4);//奖品状态  （1未到期 2可使用 3已冻结 4已使用 5已到期 6已作废）
													//根据奖品的编号获取奖品（到期时间）
													Award a = awardService.selectByAwardNo(aal.getAwardno());
												    urel.setRefailtime(a.getEndtime());//到期时间
												    if(aal.getAuditman()==null||"".equals(aal.getAuditman())){
												    	urel.setIsaudit((short)1);//是否审核（0.是，1.否）
												    }else{
												    	urel.setIsaudit((short)0);//是否审核（0.是，1.否）
												    }
												    urel.setAuditman(aal.getAuditman());//审核人
												    urel.setAudittime(aal.getAudittime());//审核时间
												    urel.setPurpose("该红包可以直接提现");
												    urel.setRemark(aal.getRemark());//备注
												    urel.setRedealtime(new Date());//红包的入账时间
												    for(int k = 0;k<aal.getAwardquantity();k++){
												    	int lines = userRedEnvelopeService.insert(urel);
												    }
												    
												    //更新奖品名单的处理人和处理时间 
												    ActivityAwardList activityAwardList = new ActivityAwardList();
												    activityAwardList.setId(aal.getId());
												    activityAwardList.setStatus((short)3);//发放状态 1.待审核 2.待处理 3.已领取 4.待确认 5.已经确认 6.发货中 7.领取失败 
												    activityAwardList.setDealman(au.getUsername());//处理人
												    activityAwardList.setDealtime(new Date());//处理时间
												    line = activityAwardListService.update(activityAwardList);
												    
												}
											}
											
										}
									}
								}
							
							}
							
						}
					
					}
				}
				
				String jsonStr = null;
				if(line>0){
					jsonStr = JSON.toJSONString("success");
					
				}else{
					jsonStr = JSON.toJSONString("fail");
				}
				try {
					StringUtil.sendJsonData(response, jsonStr);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
				
		/*request.setAttribute("LoanJsonList", LoanJsonList);//编码之后的签名
		request.setAttribute("PlatformMoneymoremore", platformMoneymoremore);
		request.setAttribute("TransferAction", transferAction);
		request.setAttribute("Action", action);
		request.setAttribute("TransferType", transferType);
		//request.setAttribute("ReturnURL", returnURL);
		request.setAttribute("NotifyURL", notifyURL);
		request.setAttribute("SignInfo", signInfo);
		
		try {
			request.getRequestDispatcher("/WEB-INF/MMMPages/userRed/redEnvelopes.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	@RequestMapping("/userRedAddressNotify")
	private void userRedNotifyAddress(HttpServletRequest request,HttpServletResponse response,LoanTransferReturnBean ltrb){
		
		//System.out.println("123456");
				
		try {
			request.setCharacterEncoding("UTF-8");
			String loanJsonList = Common.UrlDecoder(ltrb.getLoanJsonList(), "utf-8");
			if(ltrb.getAction()==null){
				ltrb.setAction("");
			}
			
			String dataStr = loanJsonList.trim() + ltrb.getPlatformMoneymoremore().trim() +ltrb.getAction().trim()+ ltrb.getRemark1().trim() + ltrb.getResultCode().trim();
					
			RsaHelper rsa = RsaHelper.getInstance();
			boolean flag = rsa.verifySignature(ltrb.getSignInfo(), dataStr, RsaHelper.publicString);
			
			System.out.println("验签flag是否成功==="+flag);
			System.out.println("ResultCode返回码===="+ltrb.getResultCode());
			
			if("88".equals(ltrb.getResultCode().trim())&&flag){
				
				response.setContentType("text/plain;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				try {
					response.getWriter().write("SUCCESS");
					response.getWriter().flush();
					response.getWriter().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	/*@RequestMapping("/userRedAddressReturn")
	private void userRedReturnAddress(){
		System.out.println("进来了。。。。。");
	}*/
	
}
