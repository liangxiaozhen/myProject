package com.ptpl.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.huifu.util.SignUtils;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.DebtAttornFee;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.MD5;
import com.ptpl.web.util.StringUtil;

@Controller
@RequestMapping("/user/userdebtattorn")
public class UserDebtAttornController extends UserDebtAttornControllerBase{
	
	/**
	 * 债转请求方法(购买债转请求方法)
	 * @param @param request
	 * @param @param response
	 * @param @param daorderno
	 * @param @param amount
	 * @param @param userid
	 * @param @param total
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/creditassign", method = { RequestMethod.POST, RequestMethod.GET })
	public void creditassign(HttpServletRequest request, HttpServletResponse response, String daorderno) throws Exception {
		Double total = (Double) request.getSession().getAttribute("undertaketotalprice");//承接人付给出让人的钱
		Double amount = (Double)request.getSession().getAttribute("undertakeamount");//转出本金
		BigDecimal userid = (BigDecimal)request.getSession().getAttribute("undertakebaseid");//承接人baseid
		// 根据债转编号查询用户债转对象
		UserDebtAttorn userdebtattorn = userdebtattornService.getdaorderno(daorderno);
		if(userdebtattorn!=null){
			UserFsAccountInfo ufs = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userdebtattorn.getBaseid());
			String SellCustId = ufs.getUsrcustid();// 转让人客户号
			UserFsAccountInfo ufsa = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userid);
			String BuyCustId = ufsa.getUsrcustid();// 承接人客户号
			UserFsAccountInfo ufa = userFsAccountInfoService.findUserFsAccountInfoByBaseId(userdebtattorn.getUsertender().getInaccountid());
			String BorrowerCustId = ufa.getUsrcustid();// 借款人客户号
			 
			// 承接人不是转让人,并且不是借款人时才能发请求到汇付,如果是其中任意一个那么就直接拦截
			if (BuyCustId != SellCustId && BuyCustId != BorrowerCustId) {
				// 可以传递值去汇付
				String Version = "30"; 
				String CmdId = "CreditAssign";
				String MerCustId = "6000060004166478";// 商户客户号
				// 转让金额:转让人实际转出的本金
				String CreditAmt = df1.format(amount);
				// 承接金额=页面传入的承接总额(债权转让承接人付给转让人的金额)
				String CreditDealAmt = df1.format(total);
				/** 债转明细 ***************************************************/
				String BidOrdId = userdebtattorn.getTorderno();// 被转让的投标订单号
				String BidOrdDate = sf1.format(userdebtattorn.getUsertender().getTbegintime());// 投标日期
				String BidCreditAmt = df1.format(amount);// 转让金额:是CreditAmt的二级参数
				System.out.println(BidCreditAmt + "这是CreditAmt的二级参数");
				String BorrowerCreditAmt = df1.format(amount);// 转让金额:是BidCreditAmt
				// BidDetails的二级参数
				//已经还款的金额
				Double pa = userdebtattornService.selectAlreadyRepaymentAmount(userdebtattorn);
				String PrinAmt = pa.toString();// 已还款金额**********************************??????????????????????
				String ProId = userdebtattorn.getTenderitem().getTno();// 项目ID
				
				String BidDetails = "{" + "&quot;BidDetails&quot;:" + "[" + "{" + "&quot;BidOrdId&quot;:" + "&quot;"
						+ BidOrdId + "&quot;" + "," + "&quot;BidOrdDate&quot;:" + "&quot;" + BidOrdDate + "&quot;" + ","
						+ "&quot;BidCreditAmt&quot;:" + "&quot;" + BidCreditAmt + "&quot;" + "," 
						+ "&quot;BorrowerDetails&quot;:" + "[" + "{" + "&quot;BorrowerCustId&quot;:" + "&quot;"
						+ BorrowerCustId + "&quot;" + "," + "&quot;BorrowerCreditAmt&quot;:" + "&quot;" + BorrowerCreditAmt
						+ "&quot;" + "," + "&quot;PrinAmt&quot;:" + "&quot;" + PrinAmt + "&quot;" + ","
						+ "&quot;ProId&quot;:" + "&quot;" + ProId + "&quot;" + "}" + "]" + "}" + "]" + "}";
				System.out.println(BidDetails.replaceAll("&quot;", "\""));
				// 获取手续费str1[2],手续费收取模式str1[0],债转手续费收取类型str1[1]
				String str = userdebtattornService.amountvalidation(userdebtattorn,amount);
				String[] str1 = str.split(","); 
				System.out.println("按会员等级收费1;持有时间2:==" + str1[0] + "==============" + str1[1] + "==================" + str1[2]);
				String Fee = df1.format(Double.valueOf(str1[2]));
				System.out.println(Fee + "手续费****************");
				String DivAcctId = null;
				String DivAmt = null;
				String DivDetails = null;
				if (Double.valueOf(str1[2]) != 0.00) {// 如果手续费不等于0,那么就必须传手续费收取对象
					DivAcctId = "&quot;DivAcctId&quot;:&quot;MDT000001&quot;";// 分账账户号
					DivAmt = "&quot;DivAmt&quot;:&quot;" + Fee + "&quot;";// 分账金额:也就是商户要收取的手续费金额
					DivDetails = "[{" + DivAcctId + "," + DivAmt + "}]";// 分账账户串
				}
				String OrdId = StringUtil.getNo(); // 订单号
				Calendar cal = Calendar.getInstance();// 当前时间点
				Date date = cal.getTime(); // 转化为Date类型的时间,方便数据库存储
				String OrdDate = sf1.format(date);// 订单日期
				// 商户后台应答地址
				String URL = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				String BgRetUrl = URL + "/huifu/creditAssign/creditAssignCallback.action";// 汇付返回
				String LcId = userdebtattorn.getLcid();// 挂牌债权 ID:可选
				/** 本次用户购买的这个债转标的发布金额 */
				String TotalLcAmt = df1.format(userdebtattorn.getDaamount()).toString();// 挂牌债权总金额,挂牌债权
				// ID有时,这个必须要有
				String MerPriv = "&quot;" + OrdId + "," + userdebtattorn.getTenderid().toString() + "," + userid+","+daorderno
						+ "&quot;";// 商品私有域
				System.out.println(MerPriv.replaceAll("&quot;", "\"") + "这是私有域**************");
				
				StringBuffer sb = new StringBuffer();
				sb.append(StringUtils.trimToEmpty(Version)).append(StringUtils.trimToEmpty(CmdId))
				.append(StringUtils.trimToEmpty(MerCustId)).append(StringUtils.trimToEmpty(SellCustId))
				.append(StringUtils.trimToEmpty(CreditAmt)).append(StringUtils.trimToEmpty(CreditDealAmt))
				.append(StringUtils.trimToEmpty(BidDetails).replaceAll("&quot;", "\"")).append(StringUtils.trimToEmpty(Fee))
				.append(StringUtils.trimToEmpty(DivDetails).replaceAll("&quot;", "\""))
				.append(StringUtils.trimToEmpty(BuyCustId)).append(StringUtils.trimToEmpty(OrdId))
				.append(StringUtils.trimToEmpty(OrdDate))
				/* .append(StringUtils.trimToEmpty(RetUrl)) */.append(StringUtils.trimToEmpty(BgRetUrl))
				.append(StringUtils.trimToEmpty(MerPriv).replaceAll("&quot;","\""))/* .append(StringUtils.trimToEmpty(ReqExt)) */
				/* .append(StringUtils.trimToEmpty(PageType)) */.append(StringUtils.trimToEmpty(LcId))
				.append(StringUtils.trimToEmpty(TotalLcAmt));
				String plainStr = sb.toString();
				System.out.println(plainStr + "这是字符串");
				MD5 md5 = new MD5();
				String md5Str = md5.getMD5Info(plainStr).toLowerCase();
				String ChkValue = SignUtils.encryptByRSA(md5Str);
				System.out.println(ChkValue + "加签之后的字符串");
				
				String CharSet = "UTF-8";
				request.setAttribute("Version", Version);// 版本号
				request.setAttribute("CmdId", CmdId);// 消息类型
				request.setAttribute("MerCustId", MerCustId);// 商户客户号
				request.setAttribute("SellCustId", SellCustId);// 转让人客户号
				request.setAttribute("CreditAmt", CreditAmt);// 转让金额
				request.setAttribute("CreditDealAmt", CreditDealAmt);// 承接金额
				request.setAttribute("BidDetails", BidDetails);// 债权转让明细
				request.setAttribute("BidOrdId", BidOrdId);// 被转让的投标订单号
				request.setAttribute("BidOrdDate", BidOrdDate);// 被转让的投标订单日期
				request.setAttribute("BidCreditAmt", BidCreditAmt);// 转让金额
				request.setAttribute("BorrowerCustId", BorrowerCustId);// 借款人客户号
				request.setAttribute("BorrowerCreditAmt", BorrowerCreditAmt);// 明细转让金额
				request.setAttribute("PrinAmt", PrinAmt);// 已还款金额
				request.setAttribute("ProId", ProId);// 项目 ID
				request.setAttribute("Fee", Fee);// 扣款手续费
				request.setAttribute("DivDetails", DivDetails);// 分账账户串
				request.setAttribute("DivAcctId", DivAcctId);// 分账账户号
				request.setAttribute("DivAmt", DivAmt);// 分账金额
				request.setAttribute("BuyCustId", BuyCustId);// 承接人客户号
				request.setAttribute("OrdId", OrdId);// 订单号
				request.setAttribute("OrdDate", OrdDate);// 订单日期
				// request.setAttribute("RetUrl", huifuParams.getRetUrl());//页面返回
				// URL
				request.setAttribute("BgRetUrl", BgRetUrl);// 商户后台应答地址
				request.setAttribute("LcId", LcId);// 挂牌债权 ID
				request.setAttribute("TotalLcAmt", TotalLcAmt);// 挂牌债权总金额
				request.setAttribute("MerPriv", MerPriv);// 商户私有域
				/*
				 * request.setAttribute("ReqExt", huifuParams.getReqExt());//入参扩展域
				 * request.setAttribute("PageType",
				 * huifuParams.getPageType());//页面类型
				 */
				request.setAttribute("CharSet", CharSet);// 编码
				request.setAttribute("ChkValue", ChkValue);// 签名
				userTenderService.saveObject(userdebtattorn,str1, cal,userid,OrdId,amount,total);
				request.getRequestDispatcher("/WEB-INF/pages/CreditAssign/userCreditAssign.jsp").forward(request, response);
			}
		}
	}
	/**
	 * 债转专区
	 * @param @param id
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping("/userdebtrron")
	public ModelAndView userdebtrron(String id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String lState = request.getParameter("lState");
		String dateType = request.getParameter("dateType");
	
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, "5");
		UserBaseAccountInfo userBaseAccountInfo = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		//设定一个通用的集合
		
		
		if(lState==null){
			lState = "1";
		}
		if(dateType==null){
			dateType = "all";
		}
		PageInfo<Object> pagehelper = null;
		if(lState.equals("1")){//可债转的标
			List<UserTender> userTenderList =  Transferable(userBaseAccountInfo.getId(),dateType,startdate,enddate);
			pagehelper = initPagehelper(maps, userTenderList);
			// 将list存入session中
			request.getSession().setAttribute("tenderList2", userTenderList);
			mav.addObject("dat", 1);//页面标记
		}else{//转让中,也就是对应债转记录表的已经上架的标||已转让,就是对应债转记录表中已经下架的标||转让记录,就是对应债转记录表中已经下架的标
			List<UserDebtAttorn> userDebtAttornList=  userdebttronRecord(userBaseAccountInfo.getId(),dateType,startdate,enddate,lState);
			pagehelper = initPagehelper(maps, userDebtAttornList);
			mav.addObject("datt", 2);//页面标记
		}
		
		
		
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("df", df);
		mav.addObject("sf", sf);
		mav.addObject("lState", lState);
		mav.addObject("dateType", dateType);
		mav.addObject("startdate", startdate);
		mav.addObject("enddate", enddate);
		
		mav.setViewName("user/manager/userdebtrron/transfer");
		return mav;
	}
	
	/**
	 * 转让中,已转让,转让记录
	 * @param @param id
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping("/userdebttronRecord")
	public List<UserDebtAttorn> userdebttronRecord(BigDecimal id,String dateType,String startdate,String enddate,String lState){
		
		UserDebtAttorn ub = new UserDebtAttorn();
		ub.setBaseid(id);
		if (lState.equals("2")){//转让中==发布中
			ub.setDastatus((short) 2);
		}
		if (lState.equals("3")) {//已转让==已经完成
			ub.setDastatus((short) 3);
		}
		if (lState.equals("4")) {//转让记录==已经下架
			ub.setDastatus((short) 4);
		}
	
		if(startdate!=null && startdate!=""){
			ub.setStarttime(StringUtil.stringforDateTwo(startdate));
		}else if(enddate!=null && enddate!=""){
			ub.setEndtime(StringUtil.stringforDateTwo(enddate));
		}else{
			ub.setSign(dateType);
		}
		List<UserDebtAttorn> userDebtAttornList = userdebtattornService.getAllListCode(ub);
		return userDebtAttornList;
		
	}
	
	/**
	 * 可转让
	 * @param @param id
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping("/Transferable")
	public List<UserTender> Transferable(BigDecimal id,String dateType,String startdate,String enddate){
		Date d = new Date();// 当前时间
		/**
		 * 第一步:查询当前用户持有的可债转所有标(当前用户+是否允许债转+投标成功) 第二步:遍历查询用户持有的标中哪些是有逾期的,哪些是正常标
		 * 在标的分期还款表中查询此标有没有逾期,第几期逾期,把逾期的那期数据记录下来+投资人id+投标订单号 查询当期投资人的还款计划
		 * 第三步:正常标的话,判断其是否允许债转;如果允许债转,就把此标列出来
		 * 第四步:去标的债转设置表中查询此逾期标是否允许你债转;允许的话,就去投资人还款计划表中查询投资人这笔标的还款计划,并把每期记录都亮出来
		 */
		// 查询当前用户可债转标(正常标+逾期标+债转标(承接别人的债转标))
		UserTender uTender = new UserTender();
		uTender.setOutaccountid(id);
		if(startdate!=null && startdate!=""){
			uTender.setStarttime(StringUtil.stringforDateTwo(startdate));
		}else if(enddate!=null && enddate!=""){
			uTender.setEndtime(StringUtil.stringforDateTwo(enddate));
		}else{
			uTender.setSign(dateType);
		}
		
		List<UserTender> tenderList = userTenderService.findUserTenderisadebtattornAndCode(uTender);
		for (int i = 0; i < tenderList.size(); i++) {
			// 1,获取到当前标的记录 
			UserTender tender = tenderList.get(i);
			/** 持有时间 */
			//int day = userdebtattornService.CYdays(d, tender);
			//tender.setDays(day + 1);
			/**思路:先判断这个标是不是一次性还本付息,如果是,那么就不存在提前还款,如果不是才会有提前还款*/
			TenderItem tenderItem = tenderItemService.findTenderItemById(tender.getTenderid());
			RepayMent repayment = userdebtattornService.selectRepayMent(tender);//查询未还款的最小一期的还款计划

			if (repayment != null) {
				/**
				 * 新增#######################################################
				 * ################ 如果当期还款时间在当前时间之后,说明有提前还款!
				 * 如果当期还款时间在当前时间之前,说明存在过了 还款日还没有还钱的情况( 
				 * 1.在逾期宽限期内 正常
				 * 2.在逾期宽限期外 逾期)
				 */
				if (repayment.getRtime().getTime() > d.getTime()) {// 没有还款的当期时间大于当前时间,说明有提前还款
					tender.setIdentifying(1);// 逾期标正常标的标识,1为正常 2为逾期
					tender.setLatamark("N");// 是否是正常标的标志 N为逾期标
					System.out.println(repayment.getRamount());
					System.out.println(repayment.getRestprincipal());
					System.out.println(tender.getAmount()+"********************");
					tender.setSurplusamount(Arith.add(repayment.getRamount(), repayment.getRestprincipal()));// 当期还款本金
					//tenderList2.add(tender);
				}
				if (repayment.getRtime().getTime() < d.getTime()) {// 如果当期还款时间小于当前时间,就判断是否在逾期宽限期内
					/**如果当前时间实在还款时间之后,那么判断,当前时间是不是在逾期宽限期内,如果是,表明属于正常标,如果不是表明是逾期标*/
					Date calTime = userdebtattornService.returnDate(repayment, tenderItem);
					if (d.getTime() < calTime.getTime()) {// 如果当前时间在逾期宽限期到期之前,那么就是不算逾期
						tender.setIdentifying(1);// 正常标的标志
						tender.setLatamark("Y"); // 是否是正常标的标志 Y正常标
						tender.setSurplusamount(Arith.add(repayment.getRamount(), repayment.getRestprincipal()));// 当期还款本金
						//tenderList2.add(tender);
					} else {// 如果在逾期宽限期后,就算逾期
						tender.setIdentifying(2);// 逾期标的标志
						tender.setSurplusamount(Arith.add(repayment.getRamount(), repayment.getRestprincipal()));
						//tenderList2.add(tender);
					}
				}
			}
		}
		return tenderList;
	}
	/**暂时废弃
	 * 债转列表展示:待审核,已上架,已下架,已完成展示
	 * @param @param request
	 * @param @param response
	 * @param @param id
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/queryAllRecord", method = { RequestMethod.POST, RequestMethod.GET})
	public ModelAndView queryAllRecord(HttpServletRequest request, HttpServletResponse response, int id)
			throws Exception {
		// 获取登录的用户
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		ModelAndView mav = new ModelAndView();
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		if (user != null) {
			UserDebtAttorn ub = new UserDebtAttorn();
			ub.setBaseid(user.getId());
			if (id == 1) {
				ub.setDastatus((short) 1);
			}
			if (id == 2) {
				ub.setDastatus((short) 2);
			}
			if (id == 3) {
				ub.setDastatus((short) 3);
			}
			if (id == 4) {
				ub.setDastatus((short) 4);
			}
			// 获取三表联查的数据,把相应数据添加到
			List<UserDebtAttorn> userDebtAttornList = userdebtattornService.getAllList(ub);

			PageInfo<Object> pagehelper = initPagehelper(map, userDebtAttornList);
			mav.setViewName("user/userdebtattorn/userdebtrron_status1");
			mav.addObject("pagehelper", pagehelper);
			mav.addObject("df", df);
			mav.addObject("sf", sf);
			return mav;
		}
		mav.setViewName("redirect:/user/tologin.action");
		return mav;
	}
	/**
	 * 点击发布的时候后台的接收方法
	 * @param @param request
	 * @param @param response
	 * @param @param orderno
	 * @param @param periods
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/install", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView install(HttpServletRequest request, HttpServletResponse response, String orderno,
			String periods) throws Exception {
		// identifying+"*********1为正常标,2为逾期标************"
		String data = "";
		Double nomalFee = 0.00;// 待收利息
		Double lateFee = 0.00;// 代收滞纳金
		ModelAndView mav = new ModelAndView();
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		/**获取当前要发布的标的信息:通过和session中tenderList2中的对象比对!比对得到相同的当前标*/
		UserTender userTender = querySameObject(orderno);
		if (userTender != null) {
			// 查询标的债转设置
			DebtAttorn debtAttorn = debtAttornService.selectByTid(userTender.getTenderid());
			// 查询标的设置
			TenderItem tenderItem = tenderItemService.findTenderItemById(userTender.getTenderid());
			String[] str = userdebtattornService.normal(userTender, periods).split(",");
			Double fee = Double.valueOf(str[0]);// 利息费率
			Date d = new Date();
			// 查询未还款的当期对象
			RepayMent rmt =  userdebtattornService.getRepayMent(userTender,periods);
			if (rmt != null) {// 假如能查询到的当期还款计划不为null的情况下
				if (debtAttorn.getIsasplit() == 0) {// 不可拆分
					if (userTender.getIdentifying() == 1) {
						nomalFee = Arith.mul(Arith.add(rmt.getRamount(), rmt.getRestprincipal()), fee);// 待收利息
						lateFee = 0.00;
						userTender.setIdentifying(1);
					} else {
						nomalFee = rmt.getRinterest();
						lateFee = Arith.mul(Arith.add(rmt.getRamount(), rmt.getRestprincipal()),Double.valueOf(str[2]));// 待收滞纳金
						userTender.setIdentifying(2); 
					}
					userTender.setNomalfee(nomalFee);// 全额时保存转出利息
					userTender.setLatefee(lateFee);// 全额时保存转出滞纳金
				}
				/** 算出最多可以上架多少天 */
				if (userTender.getIdentifying() == 1) {
					int days = (int) ((rmt.getRtime().getTime() - d.getTime()) / 86400000);
					if (days > debtAttorn.getIntervalday()) {
						// 可上架天数
						int day = days - debtAttorn.getIntervalday();// 可上架天数
						userTender.setDay(day);
						mav.addObject("day", day);
					} else {
						data = "可上架天数不足";
						mav.addObject("data", data);
						mav.setViewName("user/userdebtattorn/error");
					}
				}
				userTender.setDaamount(userTender.getSurplusamount());
				/**保存需要的对象进session中*/
				if (userTender.getIdentifying() == 1) {// 正常标
					periods = "0";
				}
				request.getSession().setAttribute("tender", userTender);
				request.getSession().setAttribute("periods", periods);

				debtAttorn.setBaseid(user.getId());
				if (str[1].equals("success")) {
					mav.addObject("lateFee", lateFee);
					mav.addObject("debtAttorn", debtAttorn);
					mav.addObject("usertender", userTender);
					mav.addObject("tenderitem", tenderItem);
					mav.addObject("rmt", rmt);
					mav.addObject("benjin", userTender.getSurplusamount());
					mav.addObject("nomalFee", nomalFee);
					mav.addObject("identifying", userTender.getIdentifying());
					mav.addObject("periods", periods);
					mav.addObject("df1", df1);
					mav.setViewName("user/userdebtattorn/install");
				} else {
					if (str[1].equals("ugradefasle")) {
						data = "不在允许的债转会员等级中";
					}
					if (str[1].equals("removefalse")) {
						data = "在排除人名单中";
					}
					if (str[1].equals("dayfalse")) {
						data = "持有时间不足";
					}
					if (str[1].equals("intervalday")) {
						data = "距离还款时间太近"; 
					}
					if (str[1].equals("aheadocday")) {
						data = "距离逾期宽限期太近";
					}
					mav.addObject("data", data);
					mav.setViewName("user/userdebtattorn/error");
				}
			} else {
				data = "还款计划不存在";
				mav.addObject("data", data);
				mav.setViewName("user/userdebtattorn/error");
			}
		} else {
			data = "没有找到相应的标";
			mav.addObject("data", data);
			mav.setViewName("user/userdebtattorn/error");
		}

		return mav;
	}
	/**
	 * 算正常标    部分债转的利息和滞纳金
	 * @param @param request
	 * @param @param response
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/partfee", method = { RequestMethod.POST, RequestMethod.GET })
	public void partFee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String totalamount = "";
		Double total = 0.00;
		Double total2 = 0.00;
		System.out.println("我进来-*******************");
		String daamountlb = request.getParameter("daamountlb");// 债转金额
		String orderno = request.getParameter("orderno");// 订单号
		String periods = request.getParameter("periods");// 逾期,正常的判断标志
		System.out.println(orderno);
		UserTender userTender = querySameObject(orderno);
		if (userTender != null) {
			// 判断债转金额是否在要求的债转范围
			DebtAttorn dAttorn = debtAttornService.selectByTid(userTender.getTenderid());
			Double daamount = Double.valueOf(daamountlb);
			if (daamount <= userTender.getSurplusamount() && daamount >= dAttorn.getAttornmoneylow()
					&& daamount <= dAttorn.getAttornmoney()) {// 债转金额符合范围
				userTender.setDaamount(daamount);
				String[] str = userdebtattornService.normal(userTender, periods).split(",");
				Double fee = Double.valueOf(str[0]);
				if (userTender.getIdentifying() == 1) {
					total = Arith.mul(daamount, fee);
					userTender.setNomalfee(total);// 债转利息
					userTender.setIdentifying(1);
					totalamount = total.toString();
					System.out.println(JSON.toJSONString(total) + "******************这是data的状态");
					sendJsonData(response, JSON.toJSONString(totalamount));

				}
				if (userTender.getIdentifying() == 2) {
					// 债转利息
					total = Arith.mul(daamount, fee);
					userTender.setNomalfee(total);// 债转利息
					Double latefee = Double.valueOf(str[2]);
					// 债转滞纳金
					total2 = Arith.mul(daamount, latefee);
					userTender.setLatefee(total2);
					totalamount = total.toString() + "," + total2.toString();
					userTender.setIdentifying(2);
				}
				request.getSession().setAttribute("tender", userTender);
			} else {
				totalamount = "false";// 说明债转金额不符合要求
			}
		}

		System.out.println(JSON.toJSONString(totalamount) + "******************这是data的状态");
		sendJsonData(response, JSON.toJSONString(totalamount));
	}
	/**
	 * 模拟逾期页面
	 * @param @param request
	 * @param @param response
	 * @param @param orderno
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/overdue", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView overdue(HttpServletRequest request, HttpServletResponse response, String orderno)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		UserTender userTender = userTenderService.findUserTenderByOrderno(orderno);
		if (userTender != null) {
			List<RepayMent> rmList = userdebtattornService.rmlist(user, userTender);
			if (rmList != null && rmList.size() != 0) {
				for (RepayMent repayMent : rmList) {
					repayMent.setTotalamont(Arith.add(repayMent.getRamount(), repayMent.getRestprincipal()));
				}
				mav.addObject("rmList", rmList);
				mav.addObject("sf", sf);
				mav.addObject("df", df);
				mav.setViewName("user/userdebtattorn/overdue");
			} else {
				String data = "没有相应的还款计划";
				mav.addObject("data", data);
				mav.setViewName("user/userdebtattorn/error");
			}
		} else {
			String data = "没有找到相应的标";
			mav.addObject("data", data);
			mav.setViewName("user/userdebtattorn/error");
		}
		return mav;
	}
	/**
	 * 判断利息债转系数
	 * @param @param request
	 * @param @param response
	 * @param @param orderno
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/amountFee", method = { RequestMethod.POST, RequestMethod.GET })
	public void amountfee(HttpServletRequest request, HttpServletResponse response, String orderno) throws Exception {
		UserTender userTender = querySameObject(orderno);
		String totalamount = "";
		String interestcoefficient = request.getParameter("interestcoefficient");// 待收利息债转系数
		if (userTender != null) {
			if(userTender.getIdentifying()==1){//此标是正常标
				 Double k = userDebtAttornService.debtInterest(userTender);
				 if(Double.valueOf(interestcoefficient)<k){//页面填的利息债转系数<规定的利息债转系数
					 Double total = Arith.mul(userTender.getNomalfee(), Double.valueOf(interestcoefficient));
					 userTender.setInterestcoefficient(Double.valueOf(interestcoefficient));
					 userTender.setTotallixi(total);// 把算出的债转利息存入session
					 request.getSession().setAttribute("tender", userTender);
					 totalamount = total.toString();
					 System.out.println(JSON.toJSONString(total) + "******************这是data的状态");
				 }else{
					 totalamount = "InterestFalse"+","+k;//利息债转系数超过限制
				 }
			}else{//逾期标
				 Double total = Arith.mul(userTender.getNomalfee(), Double.valueOf(interestcoefficient));
				 userTender.setInterestcoefficient(Double.valueOf(interestcoefficient));
				 userTender.setTotallixi(total);// 把算出的债转利息存入session
				 request.getSession().setAttribute("tender", userTender);
				 totalamount = total.toString();
			}
			sendJsonData(response, JSON.toJSONString(totalamount));
		}
	}


	/**
	 * 算出上架天数是否符合要求
	 * @param @param request
	 * @param @param response
	 * @param @param orderno
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/deadline", method = { RequestMethod.POST, RequestMethod.GET })
	public void deadline(HttpServletRequest request, HttpServletResponse response, String orderno) throws Exception {
		int deadline = Integer.valueOf(request.getParameter("deadline"));// 上架天数
		String data = "";
		UserTender userTender = querySameObject(orderno);
		if (userTender != null) {
			if (userTender.getIdentifying() == 1) {// 正常标
				UserTender tUserTender = (UserTender) request.getSession().getAttribute("tender");
				if (deadline < tUserTender.getDay()) {
					data = "success" + "," + tUserTender.getDay() + "," + 1;
				} else {
					data = "false" + "," + tUserTender.getDay() + "," + 1;
				}
			} else {
				data = "success" + "," + 0 + "," + 2;
			}
			userTender.setDeadline(deadline);
			request.getSession().setAttribute("tender", userTender);
		}
		sendJsonData(response, JSON.toJSONString(data));
	}
	/**
	 * 滞纳金的算法
	 * @param @param request
	 * @param @param response
	 * @param @param orderno
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/lateFee", method = { RequestMethod.POST, RequestMethod.GET })
	public void lateFee(HttpServletRequest request, HttpServletResponse response, String orderno) throws Exception {
		UserTender userTender = querySameObject(orderno);
		if (userTender != null) {
			String latecoefficient = request.getParameter("latecoefficient");// 待收滞纳金债转系数
			Double total = Arith.mul(userTender.getLatefee(), Double.valueOf(latecoefficient));
			userTender.setLatecoefficient(Double.valueOf(latecoefficient));
			userTender.setTotalOcamount(total);
			request.getSession().setAttribute("tender", userTender);// 把算出的债转滞纳金存入session
			String totalamount = total.toString();
			System.out.println(JSON.toJSONString(total) + "******************这是data的状态");
			sendJsonData(response, JSON.toJSONString(totalamount));
		}
	}
	/**
	 *  判断金额债转系数是否符合要求,算债转金额以多少债转系数转出
	 * @param @param request
	 * @param @param response
	 * @param @param orderno
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/amount", method = { RequestMethod.POST, RequestMethod.GET })
	public void amount(HttpServletRequest request, HttpServletResponse response, String orderno) throws Exception {
		String totalamount = "";
		Double daamount = Double.valueOf(request.getParameter("daamountlb"));// 债转金额
		Double coefficient = Double.valueOf(request.getParameter("coefficientlb"));// 债转金额债转系数
		UserTender userTender = querySameObject(orderno);
		if (userTender != null) {
			if (daamount <= userTender.getSurplusamount() && daamount >= userTender.getDebtattorn().getAttornmoneylow()
					&& daamount <= userTender.getDebtattorn().getAttornmoney()) {// 债转金额符合范围
				if (coefficient >= userTender.getDebtattorn().getMinattornratio()
						&& coefficient <= userTender.getDebtattorn().getMaxattornratio()) {// 判断债转系数符合要求不
					System.out.println(userDebtAttornService.Amountcoeffcient(userTender));
		            if(coefficient<userDebtAttornService.Amountcoeffcient(userTender)){
		            	Double total = Arith.mul(daamount, coefficient);
		            	userTender.setTotaldaamount(total);//金额实际成交价
		            	userTender.setDaamount(daamount);
		            	userTender.setCoefficient(coefficient);
		            	request.getSession().setAttribute("coefficient", coefficient);
		            	request.getSession().setAttribute("tender", userTender);// 把算出的债转金额存入session
		            	totalamount = total.toString();
		            }else{
		            	Double coff = userDebtAttornService.Amountcoeffcient(userTender);
		            	String coffStr = coff.toString();
		            	totalamount = "coffFalse"+","+coffStr;// 债转系数范围有错
		            }
				} else {
					totalamount = "coefficientFalse";// 债转系数范围有错
				}
			} else {
				totalamount = "daamountFalse";// 债转金额有错
			}
		}
		sendJsonData(response, JSON.toJSONString(totalamount));
	}

	/**
	 * 算正常标的债转金额和债转利息折价后的总额
	 * @param @param request
	 * @param @param response 
	 * @param @param orderno
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/total", method = { RequestMethod.POST, RequestMethod.GET })
	public void total(HttpServletRequest request, HttpServletResponse response, String orderno) throws Exception {
		Double sum = 0.00;
		UserTender userTender = querySameObject(orderno);
		if (userTender != null) {
			Double totalamount = userTender.getTotaldaamount();// 实际债转金额
			Double totallixi = userTender.getTotallixi();// 实际债转利息
			if (userTender.getIdentifying() == 1) {// 正常标
				sum = Arith.add(totalamount, totallixi);
			}
			if (userTender.getIdentifying() == 2) {// 逾期标
				Double totalOcamount = userTender.getTotalOcamount();// 实际滞纳金
				sum = Arith.add(Arith.add(totalamount, totallixi), totalOcamount);
			}
			Double total = Arith.div(sum, 1, 3);//把金额转换成double 类型的参数
			String totalAmount = total.toString();
			System.out.println(JSON.toJSONString(total) + "******************这是data的状态");
			sendJsonData(response, JSON.toJSONString(totalAmount)); 
		}
	}
	/**
	 * 详情页面跳转
	 * @param @param request
	 * @param @param response
	 * @param @param daorderno
	 * @param @return
	 * @param @throws Exception
	 * @return ModelAndView
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/details", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView details(HttpServletRequest request, HttpServletResponse response, String daorderno)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		UserDebtAttorn ub = userdebtattornService.getdaorderno(daorderno);
		if (ub != null) {
			mav.addObject("ub", ub);
			mav.setViewName("user/userdebtattorn/details");
			mav.addObject("sf", sf);
		}
		return mav;

	}
	/**
	 * 保存用户债转记录
	 * @param @param request
	 * @param @param response
	 * @param @param orderno
	 * @param @param udapass
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping(value = "/save", method = { RequestMethod.POST, RequestMethod.GET })
	public void saveObject(HttpServletRequest request, HttpServletResponse response, String orderno, String udapass)
			throws Exception {
		UserTender userTender = querySameObject(orderno);
		System.out.println(userTender);
		if (userTender != null) {
			// 用来保存用户债转对象
			UserDebtAttorn userdebtattorn = new UserDebtAttorn();
			// 获取用户基本信息表,获取用户的登录名/真实姓名
			UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
			// 查询当前用户的会员等级
			UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(user.getId());
			//获取标的设置信息
			TenderItem tenderItem = tenderItemService.findTenderItemById(userTender.getTenderid());
			short ugrade = userAccountSafeInfo.getUgrade();
			// 获取投标结束时间到当前时间之间相差的天数
			Date d1 = new Date(); // 获取当前时间
			System.out.println(sf.format(d1));
			int days = userdebtattornService.CYdays(d1, userTender);

			// 假如债转次数没有超限制和金额有没有超限
			 String data = userdebtattornService.ublistmanytimes(user.getId(), userTender,userdebtattorn);
			/** 第一步:判断次数有没有超限 */
			if (data.equals("success")) {
				// 得到手续费对象
				List<DebtAttornFee> debtAttornFee = debtAttornFeeService.selectByTid(userTender.getTenderid());
				DebtAttornFee dbf = null;
				for (DebtAttornFee dnf : debtAttornFee) {/**开关变为标的设置中去了isAdebtAttornFee*/
					if (tenderItem.getIsadebtattornfee() == 0) {// 手续费开关为关闭状态
						userdebtattornService.saveUserDebtarron(userdebtattorn, userTender, user, d1, udapass,data);
					}
					if (tenderItem.getIsadebtattornfee() == 1) {// 手续费开关为打开状态
						if (dnf.getFeemode() == 1) {// 按照会员等级收费
							if (userdebtattornService.ugradeyanz(dnf.getUgrade(), ugrade)) {
								if (userTender.getDaamount() >= dnf.getMinattornmoney() && userTender.getDaamount() <= dnf.getMaxattornmoney()) {
									dbf = dnf;
									break;
								}
							}
						}
						if (dnf.getFeemode() == 2) {// 按照持有时间收费
							if (userdebtattornService.dayyanz(days, dnf.getHadday())) {
								if (userTender.getDaamount() >= dnf.getMinattornmoney() && userTender.getDaamount() <= dnf.getMaxattornmoney()) {
									dbf = dnf;
									break;
								}
							}
						}
					}
				}
				System.out.println(dbf.getAttornrate());
				data = userdebtattornService.quotaAndAttornrate(dbf, userTender,data);
				if (data.equals("success")) {
					data = userdebtattornService.saveUserDebtarron(userdebtattorn, userTender, user, d1, udapass,data);
				}
			}
			System.out.println(JSON.toJSONString(data) + "******************这是data的状态");
			sendJsonData(response, JSON.toJSONString(data)); 

		}
	}
	
}
