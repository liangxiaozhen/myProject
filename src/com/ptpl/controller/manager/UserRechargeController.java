package com.ptpl.controller.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.moneymoremore.util.MMMParams;
import com.moneymoremore.util.RsaHelper;
import com.moneymoremore.util.SignUtils;
import com.ptpl.constant.UserRecharge_Constant;
import com.ptpl.controller.BaseController;
import com.ptpl.model.PersonSaveReconciliation;
import com.ptpl.model.UserRecharge;
import com.ptpl.service.UserRechargeServiceI;
import com.ptpl.web.util.AES;

@Controller
@RequestMapping("/admin/userRecharge")
public class UserRechargeController extends BaseController{
	
	@Autowired
	UserRechargeServiceI   userRechargeService;
	/**
	 * 查询列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/query", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView queryAll(HttpServletRequest  request
			,HttpServletResponse response,UserRecharge userRecharge) throws Exception {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
	    initPage(map, pageNum, pageSize);
		//UserBaseAccountInfo userInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		List<UserRecharge>  userRechargeList = userRechargeService.getAll(userRecharge);
		for (UserRecharge userRecharge2 : userRechargeList) {
			userRecharge2.getUbai().setRealname(AES.getDecrypt(userRecharge2.getUbai().getRealname()));
		}

		
		PageInfo<Object> pagehelper = initPagehelper(map,userRechargeList);
		ModelAndView modelAndView  = new ModelAndView(UserRecharge_Constant.List);
		
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("userRecharge", userRecharge);
		modelAndView.addObject("df", df);
		modelAndView.addObject("sf", sf);
		return modelAndView;
	}
	
	/**
	 * 对账列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryBlending", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView queryBlending(HttpServletRequest  request
			,HttpServletResponse response,UserRecharge userRecharge) throws Exception {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		//UserBaseAccountInfo userInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		List<UserRecharge>  userRechargeList = userRechargeService.getAll(userRecharge);
		//针对日期进行处理的
		if(userRecharge.getStarttime()!=null){
			userRecharge.setStarttimeStr( sf.format(userRecharge.getStarttime()));
		}
		if(userRecharge.getEndtime()!=null){
			userRecharge.setEndtimeStr(sf.format(userRecharge.getEndtime()));
		}
		if(userRecharge.getReceivetime()!=null){
			userRecharge.setReceivetimeStr(sf.format(userRecharge.getReceivetime()));  
		}
		if(userRecharge.getChecktime()!=null){
			userRecharge.setChecktimeStr(sf.format(userRecharge.getChecktime()));
		}
		if(userRecharge.getSyschktime()!=null){
			userRecharge.setSyschktimeStr(sf.format(userRecharge.getSyschktime()));
		}
		if(userRecharge.getSysrectime()!=null){
			userRecharge.setSysrectimeStr(sf.format(userRecharge.getSysrectime()));
		}
		
		PageInfo<Object> pagehelper = initPagehelper(map, userRechargeList);
		ModelAndView modelAndView  = new ModelAndView(UserRecharge_Constant.Blending);
		
		modelAndView.addObject("pagehelper", pagehelper);
		modelAndView.addObject("userRecharge", userRecharge);
		modelAndView.addObject("df", df);
		return modelAndView;
	}
	/**
	 * 详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView queryDetails(HttpServletRequest request, HttpServletResponse response, BigDecimal id)
			throws Exception {
		UserRecharge userRecharge = userRechargeService.queryKey(id);
		/*System.out.println(userRecharge.getUbai().getRealname());*/
		// 充值银行
		userRecharge.setBanknameStr(UserRecharge_Constant.BANK_MAP.get(userRecharge.getBankname()));
		ModelAndView modelAndView = new ModelAndView(UserRecharge_Constant.DETALIS);
		// 加入时间有空的情况下就不转换时间,也不加载时间字段,这样就可以避免在点击下一页的时候报null指针异常
		if(userRecharge.getSyschktime() != null){
			userRecharge.setSyschktimeStr(sf.format(userRecharge.getSyschktime()));
		}
		if(userRecharge.getChecktime() != null ){
			userRecharge.setChecktimeStr(sf.format(userRecharge.getChecktime()));
		}
		if(userRecharge.getReceivetime()!=null){
			userRecharge.setReceivetimeStr(sf.format(userRecharge.getReceivetime()));
		}
		if(userRecharge.getSysrectime()!=null){
			userRecharge.setSysrectimeStr(sf.format(userRecharge.getSysrectime()));
		}
		if(userRecharge.getCardno()!=null){
			userRecharge.setCardno(AES.getDecrypt(userRecharge.getCardno()));
		}
		if(userRecharge.getApplyman()!=null){
			logger.info(userRecharge.getApplyman()+"------------------");
			logger.info(AES.getDecrypt(userRecharge.getApplyman())+"*******************");
			userRecharge.setApplyman(AES.getDecrypt(userRecharge.getApplyman()));
		}
		
		modelAndView.addObject("userRecharge", userRecharge);
		modelAndView.addObject("df", df);
		return modelAndView;
	}
	/**
	 * 编辑跳转
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUserRecharge", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView updateUserRecharge(HttpServletRequest request, HttpServletResponse response, BigDecimal id)
			throws Exception {
		UserRecharge userRecharge = userRechargeService.queryKey(id);
		System.out.println(userRecharge.getApplyman());
		ModelAndView modelAndView = new ModelAndView(UserRecharge_Constant.EDITS);
		modelAndView.addObject("userRecharge", userRecharge);
		modelAndView.addObject("df1", df1);
		return modelAndView;
	}
	
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @param userRecharge
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST, RequestMethod.GET })
	public void update(HttpServletRequest request, HttpServletResponse response, UserRecharge userRecharge)
			throws Exception {
		userRecharge.setRemark("");
		userRechargeService.update(userRecharge);
		String jsonStr = JSON.toJSONString(userRecharge);
		sendJsonData(response, jsonStr);
	}
	/**
	 * 文件上传页面跳转
	 * @return
	 */
	@RequestMapping(value = "/fileUpload", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView  fileUpload() {
		ModelAndView modelAndView = new ModelAndView(UserRecharge_Constant.FILEUPLOAD);
		return modelAndView;
	}
	/**
	 * 解析TXT文本,然后对账
	 * @param file
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value = "file", required = true) CommonsMultipartFile file,Model model) throws Exception{
		String fileName = file.getOriginalFilename();
		model.addAttribute("msg", "上传失败。");
		if (fileName.substring(fileName.lastIndexOf(".") + 1).equals("txt")){
			InputStreamReader reader = new InputStreamReader(file.getInputStream());
			BufferedReader buffer = new BufferedReader(reader);
			if (buffer.readLine() != null){
				String lineText = null;
				List<UserRecharge> list = new ArrayList<UserRecharge>();
				while ((lineText = buffer.readLine()) != null){
					String[] strs = lineText.split("	");
					if (strs.length > 1)
					{
						UserRecharge ur = new UserRecharge();
						for (String str : strs)
						{
							String[] strs1 = str.split(":");
							switch (strs1[0])
							{
								case "商户订单号" :
									ur.setRechargeno(strs1[1]);
									break;
								case "充值金额" :
									ur.setAmount((double)Double.valueOf(strs1[1]));
									break;
								case "手续费金额" :
									ur.setRecharfee((double)Double.valueOf(strs1[1]));
									break;
								case "交易状态" :
									switch (strs1[1])
									{
										case "成功" :
											ur.setStatus((short) 1);
											break;
										case "失败" :
											ur.setStatus((short) 2);
											break;
										case "初始" :
											ur.setStatus((short) 3);
											break;
									}
									break;
							}
						}
						list.add(ur);
					}
				}
				model.addAttribute("msg", "没有找到匹配的信息。");
				if (list.size() > 0){
					for (UserRecharge userrecharge : list){
						UserRecharge ur1 = userRechargeService.select(userrecharge.getRechargeno());
						if (ur1 != null){
							
							String refeeamt = df1.format(Double.valueOf(userrecharge.getRecharfee()));
							String feeamt = df1.format(Double.valueOf(ur1.getRecharfee()));
							String reamount = df1.format(Double.valueOf(userrecharge.getAmount()));
							String amount = df1.format(Double.valueOf(ur1.getAmount()));
							boolean	 flag1 = feeamt.equals(refeeamt);
							boolean flag2 = reamount.equals(amount);
							boolean flag3 = userrecharge.getStatus() == ur1.getStatus();
							boolean flag4  = userrecharge.getBankname().equals(ur1.getBankname());
							short rechargetype =-1;
							if(ur1.getRechargetype().equals("B2C")){
								rechargetype = 1;
							}
							if(ur1.getRechargetype().equals("B2B")){
								rechargetype = 2;
							}
							if(ur1.getRechargetype().equals("QP")){
								rechargetype = 3;
							}
							boolean flag5 = userrecharge.getRechargetype()==rechargetype;
							String remark = "";
							if (!flag1)
								remark += "手续费:"+userrecharge.getRecharfee()+"  ";
							if (!flag2)
								remark += "充值金额:"+userrecharge.getAmount()+"  ";
							if (!flag3)
								remark += "充值状态:"+UserRecharge_Constant.STATUS_MAP.get(userrecharge.getStatus())+"  ";
							if (!flag4)
								remark += "充值银行:"+UserRecharge_Constant.BANK_MAP.get(userrecharge.getBankname())+"  ";
							if (!flag5)
								remark += "充值方式:"+UserRecharge_Constant.RECHARGETYPE_MAP.get(userrecharge.getRechargetype())+"  ";
							UserRecharge urce = new UserRecharge();
							if (!(flag1 && flag2 && flag3 && flag4 && flag5)){
								urce.setIsexceptions((short) 1);
							}
								urce.setRechargeno(ur1.getRechargeno());
								urce.setIsmanblending((short) 1);
								urce.setReceivetime(new Date());
								urce.setChecktime(new Date());
								urce.setRemark(remark == "" ? null : remark);
								if(ur1.getEndtime()==null){
									urce.setEndtime(new Date());
								}
								if(ur1.getBankreturnno()==null){
									urce.setBankreturnno("无");
								}
								userRechargeService.update(urce);
						}
					}
				}
				model.addAttribute("msg", "上传成功。");
			}
		}
		return "admin/userRecharge/success";
	}
	/**
	 * 系统勾兑(批量),批量对象的时候只天开始时间和结束时间,但是时间范围只能填一个月之内的
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveReconciliation", method = {RequestMethod.POST,RequestMethod.GET})
	public void  saveReconciliation(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		/*平台乾多多标识*/
		String PlatformMoneymoremore ="p2089";
		/*查询类型:空.转账1.充值2.提现*/
		String Action = "1";
		/*LoanNo 乾多多流水号 对应充值记录的bankreturnno(银行返回订单号)*/
		String LoanNo = "";
		/*OrderNo 订单号 ,队形充值记录的充值订单号 Rechargeno*/
		String OrderNo = "";
		/*BatchNo 网贷平台标号*/
		String BatchNo = "";
		/*开始日期*/
		String BeginTime = "20170223154821";
		/*结束日期*/
		SimpleDateFormat sd =new SimpleDateFormat("yyyyMMddHHmmss");
		String EndTime = sd.format(new Date());
		
	    StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
						.append(StringUtils.trimToEmpty(Action))
						.append(StringUtils.trimToEmpty(LoanNo))
						.append(StringUtils.trimToEmpty(OrderNo))
						.append(StringUtils.trimToEmpty(BatchNo))
						.append(StringUtils.trimToEmpty(BeginTime))
						.append(StringUtils.trimToEmpty(EndTime));
						
		String plainStr = buffer.toString();
		System.out.println(plainStr);
		//私钥签名
		String privateResult = "";
		
		RsaHelper rsa = RsaHelper.getInstance();
		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
		System.out.println(privateResult);

		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("PlatformMoneymoremore", PlatformMoneymoremore);
		hashMap.put("Action", Action);
		hashMap.put("LoanNo", LoanNo);
		hashMap.put("OrderNo", OrderNo);
		hashMap.put("BatchNo", BatchNo);
		hashMap.put("BeginTime", BeginTime);
		hashMap.put("EndTime", EndTime);
		hashMap.put("SignInfo", privateResult);
		String resultStr = "";
		try {
			resultStr = SignUtils.doPost(hashMap, MMMParams.RECONCILIATION);//HttpClient 提交
		} catch (ClientProtocolException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		System.out.println(resultStr+"返回参数");

		
		if(resultStr!=null && !resultStr.equals("")){
			JSONArray jsonArray = JSONArray.parseArray(resultStr);
			System.out.println(jsonArray+"返回参数");
			if(jsonArray.size()!=0){
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject json = JSON.parseObject(jsonArray.get(i).toString()); //解析汇付返回
					//把json对象转化成java对象
					PersonSaveReconciliation psr = JSON.toJavaObject(json,PersonSaveReconciliation.class);
					if(psr!=null){
						//获取双乾返回需要比对的值
						String RechargeType = psr.getRechargeType();//充值方式
							if(RechargeType.equals("")){//说明是网银充值
								RechargeType = "0";
							}
						String Amount = psr.getAmount();//充值金额
						String Fee = psr.getFee();//充值手续费
						String FeePlatform = psr.getFeePlatform();//平台承担手续费
						String RechargeState = psr.getRechargeState(); //充值状态
						//根据订单号查询本地数据库充值记录
						UserRecharge recharge =  userRechargeService.select(psr.getOrderNo());
						if(recharge!=null){
							//判断是否人工勾兑过,如果人工勾兑过就不用系统勾兑了
							if(recharge.getIsmanblending()!=1){
								//获取数据库查询出来的需要比对的值
								String SQLRechargeType ="";
								if(recharge.getRechargetype()==null){//说明是网银充值
									RechargeType = "0";
								}
								String SQLFee  = df1.format(Double.valueOf(recharge.getRecharfee()));
								String SQLRechargeState = recharge.getStatus().toString();
								String SQLAmount = df1.format(Double.valueOf(recharge.getAmount()));
								//开始比对 
								//1.比对充值方式
								boolean flag = RechargeType.equals(SQLRechargeType);
								//2.比对充值金额
								boolean flag1 = Amount.equals(SQLAmount);
								//3.比对充值手续费:
								/*逻辑:假如说是网银充值的时候,说明是不收费的,那么只需要比对fee和sqlfee就可以了
								   如果不是网银充值,那么看FeePlatform是不是为0,如果不为0,那么就比对平台手续费和数据库的手续费是不是一样的*/
								boolean flag2 = false;
								int FPlatform = 0;
								if(RechargeType.equals("0")){
									flag2= Fee.equals(SQLFee);
								}else{
									if(!FeePlatform.equals("0.00")){
										FPlatform = 1;
										flag2= FeePlatform.equals(SQLFee);
									}else{
										flag2= Fee.equals(SQLFee);
									}
								}
								//4.比对状态
								boolean flag3 = SQLRechargeState.equals(RechargeState);
								
								recharge.setSysrectime(new Date());//系统勾兑接收数据时间 第一次
								recharge.setSyschktime(new Date());//系统勾兑时间
								String remark ="";
								if(!flag1){
									remark+="充值金额:"+psr.getAmount()+"  ";
								}
								if(!flag2){
									if(FPlatform!=1){
										remark+="充值手续费:"+psr.getFee()+"  ";
									}else{
										remark+="充值手续费:"+psr.getFeePlatform()+"  ";
									}
								}
								if(!flag3){
									remark+="充值状态:"+UserRecharge_Constant.STATUS_MAP.get(new Short(RechargeState))+"  ";
								}
								if(!flag){
									remark+="充值方式:"+UserRecharge_Constant.RECHARGETYPE_MAP.get(new Short(RechargeType))+"  ";
								}
								if(!(flag1 && flag2 && flag3 && flag)){
									recharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_YES);//表示异常
									recharge.setRemark(remark);
								}
								recharge.setIsblending(UserRecharge_Constant.ISMANBLENDING_YES);//表示已经勾兑
								recharge.setReqquerydata(hashMap.toString());//请求数据包
								recharge.setRecresultdata(json.toString());//接收数据包
								userRechargeService.update(recharge);
								String jsonStr = JSON.toJSONString(recharge);
								sendJsonData(response, jsonStr);
							}
							}
						}
						
					}
				}
			}
	}
	
	
	/**
	 * 人工勾兑(交易明细查询接口)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/personSaveReconciliation", method = {RequestMethod.POST,RequestMethod.GET})
	public void personSaveReconciliation(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		 
		String id = request.getParameter("id");
		//根据id查到当前要勾兑的那条数据
		UserRecharge userRecharge = userRechargeService.queryKey(new BigDecimal(id));
		/*平台乾多多标识*/
		String PlatformMoneymoremore =userRecharge.getMercustid();
		/*查询类型:空.转账1.充值2.提现*/
		String Action = "1";
		/*LoanNo 乾多多流水号 对应充值记录的bankreturnno(银行返回订单号)*/
		String LoanNo = userRecharge.getBankreturnno();
		if(LoanNo==null){
			LoanNo = "";
		}
		/*OrderNo 订单号 ,队形充值记录的充值订单号 Rechargeno*/
		String OrderNo = userRecharge.getRechargeno();
		/*BatchNo 网贷平台标号*/
		String BatchNo = "";
		/*开始日期*/
		String BeginTime = "";
		/*结束日期*/
		//SimpleDateFormat sd =new SimpleDateFormat("yyyyMMdd");
		String EndTime = "";
		
	    StringBuffer buffer = new StringBuffer();
				buffer.append(StringUtils.trimToEmpty(PlatformMoneymoremore))
						.append(StringUtils.trimToEmpty(Action))
						.append(StringUtils.trimToEmpty(LoanNo))
						.append(StringUtils.trimToEmpty(OrderNo))
						.append(StringUtils.trimToEmpty(BatchNo))
						.append(StringUtils.trimToEmpty(BeginTime))
						.append(StringUtils.trimToEmpty(EndTime));
						
		String plainStr = buffer.toString();
		System.out.println(plainStr);
		//私钥签名
		String privateResult = "";
		
		RsaHelper rsa = RsaHelper.getInstance();
		privateResult = rsa.signData(plainStr, RsaHelper.privateString);
		System.out.println(privateResult);

		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("PlatformMoneymoremore", PlatformMoneymoremore);
		hashMap.put("Action", Action);
		hashMap.put("LoanNo", LoanNo);
		hashMap.put("OrderNo", OrderNo);
		hashMap.put("BatchNo", BatchNo);
		hashMap.put("BeginTime", BeginTime);
		hashMap.put("EndTime", EndTime);
		hashMap.put("SignInfo", privateResult);
		String resultStr = "";
		try {
			resultStr = SignUtils.doPost(hashMap, MMMParams.RECONCILIATION);//HttpClient 提交
			System.out.println(resultStr+"返回参数");
		} catch (ClientProtocolException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		if(resultStr!=null && !resultStr.equals("")){
			JSONArray jsonArray = JSONArray.parseArray(resultStr);
			if(jsonArray.size()!=0){
				JSONObject json = JSON.parseObject(jsonArray.get(0).toString()); //解析汇付返回
				//把json对象转化成java对象
				PersonSaveReconciliation psr = JSON.toJavaObject(json,PersonSaveReconciliation.class);
				System.out.println(psr);
				if(psr!=null){
					//获取本地数据库数据
					/**比对顺序:
					 * 			 1.首先比对充值方式
					 * 			 2.比对充值金额
					 * 			 3.比对充值手续费,看是自付还是他付
					 * 			 4.比对充值状态
					 * */
					//获取双乾返回需要比对的值
					String RechargeType = psr.getRechargeType();//充值方式
						if(RechargeType.equals("")){//说明是网银充值
							RechargeType = "0";
						}
					String Amount = psr.getAmount();//充值金额
					String Fee = psr.getFee();//充值手续费
					String FeePlatform = psr.getFeePlatform();//平台承担手续费
					String RechargeState = psr.getRechargeState(); //充值状态
					
					//获取数据库查询出来的需要比对的值
					String SQLRechargeType =userRecharge.getRechargetype().toString();
					String SQLFee  = df1.format(Double.valueOf(userRecharge.getRecharfee()));
					String SQLRechargeState = userRecharge.getStatus().toString();
					String SQLAmount = df1.format(Double.valueOf(userRecharge.getAmount()));
				   
					//开始比对 
					//1.比对充值方式
					boolean flag = RechargeType.equals(SQLRechargeType);
					//2.比对充值金额
					boolean flag1 = Amount.equals(SQLAmount);
					//3.比对充值手续费:
					/*逻辑:假如说是网银充值的时候,说明是不收费的,那么只需要比对fee和sqlfee就可以了
						   如果不是网银充值,那么看FeePlatform是不是为0,如果不为0,那么就比对平台手续费和数据库的手续费是不是一样的*/
					boolean flag2 = false;
					int FPlatform = 0;
					if(RechargeType.equals("0")){
						flag2= Fee.equals(SQLFee);
					}else{
						if(!FeePlatform.equals("0.00")){
							FPlatform = 1;
							flag2= FeePlatform.equals(SQLFee);
						}else{
							flag2= Fee.equals(SQLFee);
						}
					}
					//4.比对状态
					boolean flag3 = SQLRechargeState.equals(RechargeState);
				
					userRecharge.setChecktime(new Date());//手工勾兑时间
					userRecharge.setReceivetime(new Date());//人工勾兑接收数据时间 第一次
					String remark ="";
					if(!flag1){
						remark+="充值金额:"+psr.getAmount()+"  ";
					}
					if(!flag2){
						if(FPlatform!=1){
							remark+="充值手续费:"+psr.getFee()+"  ";
						}else{
							remark+="充值手续费:"+psr.getFeePlatform()+"  ";
						}
					}
					if(!flag3){
						remark+="充值状态:"+UserRecharge_Constant.STATUS_MAP.get(new Short(RechargeState))+"  ";
					}
					if(!flag){
						remark+="充值方式:"+UserRecharge_Constant.RECHARGETYPE_MAP.get(new Short(RechargeType))+"  ";
					}
					if(!(flag1 && flag2 && flag3 && flag)){
						userRecharge.setIsexceptions(UserRecharge_Constant.ISEXCEPTIONS_YES);//表示异常
						userRecharge.setRemark(remark);
					}
						userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_YES);//表示已经勾兑
						userRecharge.setCheckman(getAdminUser().getUsername());//勾兑人员
						userRecharge.setReqquerydata(hashMap.toString());//请求数据包
						userRecharge.setRecresultdata(json.toString());//接收数据包
						userRechargeService.update(userRecharge);
						String jsonStr = JSON.toJSONString(userRecharge);
						sendJsonData(response, jsonStr);
				}
			}
		}else{
			userRecharge.setIsmanblending(UserRecharge_Constant.ISMANBLENDING_YES);//表示已经勾兑
			userRecharge.setReqquerydata(hashMap.toString());//请求数据包
			userRecharge.setChecktime(new Date());//手工勾兑时间
			userRecharge.setReceivetime(new Date());//人工勾兑接收数据时间 第一次
			userRecharge.setCheckman(getAdminUser().getUsername());//勾兑人员
			userRechargeService.update(userRecharge);
		}
	}
}