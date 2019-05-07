package com.ptpl.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ptpl.constant.Session_Constant;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.DebtAttornBuyer;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserBaseAccountInfo;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.model.UserTender;
import com.ptpl.web.util.Arith;

@Controller
@RequestMapping("/user/undertakedebtattorn")
public class UndertakeBondsTransferController extends UserDebtAttornControllerBase{
	
	@RequestMapping("/cjdebtrron")
	public ModelAndView userdebtrron() throws Exception {
		ModelAndView mav = new ModelAndView();
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		System.out.println(user.getId());
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, pageSize);
		List<UserTender>  list = userTenderService.findAllUserTender(user.getId());
		for (UserTender userTender : list) {
			TenderItem tenderItem = tenderItemService.findTenderItemById(userTender.getTenderid());
			userTender.setRepaymentpro(tenderItem.getRepaymentpro().toString());
		}
		PageInfo<Object> pagehelper = initPagehelper(maps, list);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("df1", df1);
		mav.addObject("sf", sf);
		mav.setViewName("user/cjdebtattorn/cjdebtrron");
		return mav;
	}
	@RequestMapping("/queryAllRecord")
	public ModelAndView queryAllRecord(HttpServletRequest request, HttpServletResponse response, int id) throws Exception {
		ModelAndView mav = new ModelAndView();
		// 获取登录的用户
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map map = new HashMap();
		initPage(map, pageNum, pageSize);
		List<UserTender>  normallist = new ArrayList<UserTender>();//正常
		List<UserTender>  overduelist = new ArrayList<UserTender>();//逾期
		List<UserTender>  completelist = new ArrayList<UserTender>();//完成
		List<UserTender>  userTenderList = userTenderService.findAllUserTender(user.getId());
		for (UserTender userTender : userTenderList) {
			TenderItem tenderItem = tenderItemService.findTenderItemById(userTender.getTenderid());
			userTender.setRepaymentpro(tenderItem.getRepaymentpro().toString());
			if(tenderItem.getTstatus()==9){//正常回款 	 
				normallist.add(userTender);
			}
			if(tenderItem.getTstatus()==11){//逾期中 	 
				overduelist.add(userTender);
			}
			if(tenderItem.getTstatus()==10){//已经完成 	 
				completelist.add(userTender);
			}
		  }
			PageInfo<Object> pagehelper = null;
			if (id == 51) {
				pagehelper = initPagehelper(map, userTenderList);
			}
			if (id == 1) {
				pagehelper = initPagehelper(map, normallist);//正常
			}
			if (id == 2) {
				pagehelper = initPagehelper(map, overduelist);//逾期中
			}
			if (id == 3) {
				pagehelper = initPagehelper(map, completelist);//已完成
			}
			mav.setViewName("user/cjdebtattorn/queryAllRecord");
			mav.addObject("pagehelper", pagehelper);
			mav.addObject("df", df);
			mav.addObject("sf", sf);
			return mav;
	}
	@RequestMapping("/details")
	public ModelAndView details() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/cjdebtattorn/cjdebtrron");
		return mav;
	}
	/**
	 * 列表页面
	* @Title: zhaiquan 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping("/obligatoryright")
	public ModelAndView zhaiquan() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/cjdebtattorn/zqlist");
		return mav;
	}
	@RequestMapping("/buy")
	public ModelAndView buy(String daorderno) throws Exception {
		System.out.println(daorderno+"债转标号");
		ModelAndView mav = new ModelAndView();
		//获取债转信息
		UserDebtAttorn userDebtAttorn = functionObject(daorderno);
	    if(userDebtAttorn!=null){
	    	//获取上一个投标记录中的借款人信息
	    	UserTender userTender = userTenderService.findUserTenderByOrderno(userDebtAttorn.getTorderno());
	    	//获取购买人的信息
	    	UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
	    	//获取购买人的用户账户有多少钱
	    	UserAccount useraccount = userAccountService.getUserAccountByBaseId(user.getId());
	    	//获取标的债转设置表信息
    		DebtAttornBuyer debtattornbuyer = debtAttornBuyerService.selectByTid(userDebtAttorn.getTenderid());
	    	if(debtattornbuyer.getIsabuyallorpart()==1){//全额购买的时候
	    		mav.addObject("total", Arith.mul(userDebtAttorn.getTotalfee(),userDebtAttorn.getTotalamount()));
	    		request.getSession().setAttribute("undertaketotalprice",Arith.mul(userDebtAttorn.getTotalfee(),userDebtAttorn.getTotalamount()));
	    		request.getSession().setAttribute("undertakeamount",userDebtAttorn.getDaamount());
	    		request.getSession().setAttribute("undertakebaseid",user.getId());
	    	}
	    	mav.addObject("useraccount", useraccount);
	    	mav.addObject("debtattornbuyer", debtattornbuyer);
	    	mav.addObject("user", user);
	    	mav.addObject("userDebtAttorn", userDebtAttorn);
	    	mav.addObject("df1", df1);
	    	boolean ugradeflag = true;//userDebtAttornService.ugradeFalsePublic(debtattornbuyer.getSnlid(),user.getId());
	    	String data = "";
	    	if(ugradeflag){
    			if(userTender.getInaccountid().intValue()!=user.getId().intValue()){
    				mav.setViewName("user/cjdebtattorn/buy");
    			}else{
    				data="承接人不能是借款人!";
    				mav.addObject("data", data);
    				mav.setViewName("user/cjdebtattorn/error");
    			}
	    	
	    	}else{
	    		data = "对不起,你在定向名单排出列表中";
	    		mav.addObject("data", data);
	    		mav.setViewName("user/cjdebtattorn/error");
	    	}	
	    }
	    return mav;
	}
	@SuppressWarnings("unchecked")
	public UserDebtAttorn functionObject(String daorderno){
		UserDebtAttorn userDebtAttorn = null;
	    List<UserDebtAttorn> userDebtAttornList = (List<UserDebtAttorn>) request.getSession().getAttribute("userDebtAttornList");
	    for (UserDebtAttorn userDebtAttorn2 : userDebtAttornList) {
			if(daorderno.equals(userDebtAttorn2.getDaorderno())){
				userDebtAttorn = userDebtAttorn2;
			}
		}
		return userDebtAttorn;
	}
	/**
	 * 部分债转的时候算出承接总价和承接滞纳金和承接利息
	 * @param @param daorderno
	 * @param @param amount
	 * @param @throws Exception
	 * @return void
	 * @author jiangxueyou
	 */
	@RequestMapping("/buy2")
	public void buy2(String daorderno,String amount) throws Exception{
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		String data = "";
		//获取债转信息
		UserDebtAttorn userDebtAttorn = functionObject(daorderno);
	    if(userDebtAttorn!=null){
			if(amount!=null && Double.valueOf(amount)<userDebtAttorn.getDaamount()){
				Double fee = Arith.div(Double.valueOf(amount), userDebtAttorn.getDaamount(), 3);//算出当前债转金额占了可债转金额的比例
				Double lixi = Arith.mul(fee, userDebtAttorn.getFactintamount());//债转利息等于实际债转利息的*fee
				//债转汇总金额:在正常标的情况下,就是这个
				Double total = 0.00;
				if(userDebtAttorn.getDaproperty()==1){//假如是正常标债转
					total= Arith.add(Arith.mul(Double.valueOf(amount), userDebtAttorn.getCoefficient()), Arith.mul(lixi, userDebtAttorn.getIntcoefficient()));
					data = userDebtAttorn.getDaproperty()+","+lixi.toString()+","+total;
				}
				if(userDebtAttorn.getDaproperty()==2){//假如是逾期债转
					Double zhinajin = Arith.mul(fee, userDebtAttorn.getFactocamount());
					total = Arith.add(total, Arith.mul(zhinajin, userDebtAttorn.getLatefeecoefficient()));
					data = userDebtAttorn.getDaproperty()+","+lixi.toString()+","+total+","+zhinajin.toString();
				}
				request.getSession().setAttribute("undertaketotalprice",total);
				request.getSession().setAttribute("undertakeamount",Double.valueOf(amount));
				request.getSession().setAttribute("undertakebaseid",user.getId());
			}
		}
		System.out.println(JSON.toJSONString(data) + "******************这是data的状态");
		sendJsonData(response, JSON.toJSONString(data));
	}
	/**
	 *详情跳转页面
	* @Title: dstails 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param daorderno
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping("/dstails")
	public ModelAndView dstails(String daorderno) throws Exception {
		System.out.println(daorderno+"债转标号");
		UserDebtAttorn userDebtAttorn = userDebtAttornService.getdaorderno(daorderno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("userDebtAttorn", userDebtAttorn);
		mav.addObject("df1", df1);
		mav.setViewName("user/cjdebtattorn/dstails");
		return mav;
	}
	/**
	 * 页面点击债券转让按钮的时候
	* @Title: cjdebtattorn 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping("/zhaizhuanfb")
	public ModelAndView cjdebtattorn() throws Exception {
		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> maps = new HashMap<String, Object>();
		initPage(maps, pageNum, pageSize);
		ModelAndView mav = new ModelAndView();
		//获取当前登录用户的id:如果和转让人id相同,那么就不显示给当前用户看到自己发布的债券
		UserBaseAccountInfo user = (UserBaseAccountInfo) request.getSession().getAttribute(Session_Constant.USER);
		List<UserDebtAttorn> userDebtAttornList = userDebtAttornService.getCjZz(user.getId());
		//汇总金额=债转本金+债转利息+债转滞纳金
		Double totalamount = 0.00;
		//年利率
		Double yearProfit = 0.00;
		//汇总系数=(债转本金*债转本金系数+债转利息*债转利息系数+债转滞纳金*债转滞纳金系数)/汇总金额
		Double totalfee = 0.00;
		Double  totalfee3 =0.00;//滞纳金实际成交价
		for (int i = 0; i < userDebtAttornList.size(); i++) {
			UserDebtAttorn uda = userDebtAttornList.get(i);
			int isfixed = userdebtattornService.isfixedFun(uda);
			Double lixi =0.00;
			Double zhinajin = 0.00;
			if(uda.getDaproperty()==1){//正常标
				Double totalfee1 =Arith.mul(uda.getDaamount(), uda.getCoefficient()); //债转金额实际成交价
				Double totalfee2 = 0.00;
				if(isfixed==1){//固定
					totalfee2=Arith.mul(uda.getIntamount(), uda.getIntcoefficient()); //债转利息实际成交价
					lixi = uda.getIntamount();//债转利息
					/**测试不固定的数据*/
					/*String str = normal(uda);
					String []str1 = str.split(",");
					//出让利息
					lixi = Double.valueOf(str1[0]);*/
				}else{//不固定
					String str = normal(uda);
					String []str1 = str.split(",");
					//出让利息
					lixi = Double.valueOf(str1[0]);
					totalfee2=Arith.mul(lixi, uda.getIntcoefficient()); //债转利息
				}
				totalamount = Arith.add(uda.getDaamount(), lixi);//汇总金额=债转本金+债转利息+债转滞纳金
				Double CJRAmount = Arith.add(totalfee1, totalfee2); 
				Double proceeds =  Arith.sub(Arith.add(uda.getDaamount(), totallixi(uda)),CJRAmount);//实际收益:比如说,你用400买到别人的(本金+利息)600元,那么收益就是200元
				totalfee = Arith.div(Arith.add(totalfee1, totalfee2), totalamount, 3);//汇总系数=实际汇总金额/汇总总额
				yearProfit = userdebtattornService.yearrate(uda,proceeds,CJRAmount);
			}
			/**逾期标:逾期利息是固定的,逾期滞纳金和正常标的利息一样:分固定和不固定
			 * 逾期的情况下,债转金额和债转利息是不变的,债转滞纳金是随着时间变化而变化的
			 */
			Double profit = 0.00;//逾期承接人收益
			if(uda.getDaproperty()==2){
				Double totalamount1 = Arith.add(uda.getDaamount(), uda.getIntamount());//债转金额+债转利息
				Double totalfee1 =Arith.mul(uda.getDaamount(), uda.getCoefficient()); //金额实际成交价
				lixi = uda.getIntamount();
				Double totalfee2 =Arith.mul(uda.getIntamount(), uda.getIntcoefficient()); //利息实际成交价
				//承接人要付的钱
				Double copewith = 0.00;
				//承接人得到的收益
				Double obtain = 0.00;
				if(isfixed==1){//固定 
					zhinajin = uda.getOcamount();
					totalfee3 = Arith.mul(zhinajin, uda.getLatefeecoefficient());
					/**算出逾期承接人的收益率*/
					//当为固定的时候,出让人出让的金额不变,也就是承接人付款金额不变,但是挂单期的滞纳金归承接人
					String str = normal(uda);
					String []str1 = str.split(",");
					Double zhinajin2 = Double.valueOf(str1[1]);
					//承接人要付的钱
					copewith = Arith.add(Arith.add(totalfee1,totalfee2),totalfee3);
					//承接人得到的收益
					obtain = Arith.add(totalamount1,zhinajin2);
					profit = Arith.div(Arith.sub(obtain, copewith), copewith,3);
				}else{
					String str = normal(uda);
					String []str1 = str.split(",");
					zhinajin = Double.valueOf(str1[1]);
					totalfee3 = Arith.mul(zhinajin, uda.getLatefeecoefficient());
					/**算出逾期承接人的收益率*/
					//承接人要付的钱
					copewith = Arith.add(Arith.add(totalfee1,totalfee2),totalfee3);
					//承接人得到的收益
					obtain = Arith.add(totalamount1,zhinajin);
					profit = Arith.div(Arith.sub(obtain, copewith), copewith,3);
				}
				totalamount = Arith.add(totalamount1, zhinajin);//汇总金额=totalamount1+滞纳金zhinajin
				totalfee = Arith.div(Arith.add(Arith.add(totalfee1, totalfee2),totalfee3),totalamount, 3);//汇总系数
				yearProfit = 0.00;
			}
			
			
			uda.setFactintamount(lixi);//存入实际出让利息
			uda.setFactocamount(zhinajin);//存入实际滞纳金
			uda.setYearprofit(yearProfit);//年利率
			DebtAttorn debtAttorn =  dAttornService.selectByTid(uda.getTenderid());
			uda.setIsaudit(debtAttorn.getIsdebtaudit());
			System.out.println(uda.getId());
			int a = userDebtAttornService.updateByPrimaryKeySelective(uda);//保存并且修改实际滞纳金,实际利息,年利率
			System.out.println(a);
			uda.setTotalamount(Arith.div(totalamount, 1, 3));//汇总金额
			uda.setTotalfee(totalfee);//汇总系数
			
			/**对年华收益做百分比处理*/
			Double yearprofit = Arith.mul(yearProfit, 100);
			uda.setYearProfitStr(yearprofit);
			/**对收益率做处理*/
			Double profitStr = Arith.mul(profit, 100);
			uda.setProfitStr(profitStr);
		}
		request.getSession().setAttribute("userDebtAttornList",userDebtAttornList);
		PageInfo<Object> pagehelper = initPagehelper(maps, userDebtAttornList);
		mav.addObject("pagehelper", pagehelper);
		mav.addObject("sf", sf);
		mav.setViewName("user/cjdebtattorn/zhaizhuanfb");
		return mav;
	}
	public Double totallixi(UserDebtAttorn uda){
		//当前时间
		Date date=new Date(); 
		//获取投标记录的信息
		UserTender userTender = userTenderService.findUserTenderByOrderno(uda.getTorderno());
		//获取标的设置记录
		TenderItem tItem = tenderItemService.findTenderItemById(userTender.getTenderid());
		//得到投资人还款计划对象
		RepayMent rMent = userDebtAttornService.getRepayMent(userTender, date, String.valueOf(uda.getDaproperty()), tItem, String.valueOf(uda.getOdperiods()));
		Double totallixi = userDebtAttornService.totalFee(userTender, rMent);
		return totallixi;
	}
	/**
	 * 算出当出让人不固定的时候,出让人利息是多少还有承接人利息的变化,还有天数的变化
	 * @param @param uda
	 * @param @return
	 * @param @throws Exception
	 * @return String
	 * @author jiangxueyou
	 */
	public String normal(UserDebtAttorn uda) throws Exception{
		//当前时间
		Date date=new Date(); 
		//获取投标记录的信息
		UserTender userTender = querySameObject(uda.getTorderno());//userTenderService.findUserTenderByOrderno(uda.getTorderno());
		//获取标的设置记录
		TenderItem tItem = tenderItemService.findTenderItemById(userTender.getTenderid());
		//得到投资人还款计划对象
		RepayMent rMent = userDebtAttornService.getRepayMent(userTender,String.valueOf(uda.getOdperiods()));
		
		Double CfeeLate = 0.00; //出让滞纳金 
		Double Cfee = 0.00;//出让利息
		int days = 0; //时间 
		int day = 0;//当期时间
		//查询此标小于当前时间
		if(uda.getDaproperty()==1){//正常债转
			if(rMent.getPeriods()==1){//如果是第一期
				//算出让人持有此标的时间
				days =  (int)((date.getTime() - userTender.getValuedate().getTime())/86400000);
				//获取当期时间
				day =   (int)((rMent.getRtime().getTime() - userTender.getValuedate().getTime())/86400000);
			}else{
				RepayMent rMent2 = userDebtAttornService.everyRepayment(userTender,rMent.getPeriods()-1);
				//获取当期时间
				day = (int)((rMent.getRtime().getTime() - rMent2.getRtime().getTime())/86400000);
			}
			/*RepayMent remy = userdebtattornService.everyRepayment(userTender,rMent.getPeriods()-1);//查询上一期的还款计划,得到上一期的还款时间
			if(remy!=null){//当查询到上一期的还款计划的时候
				//获取当期天数
				day =  (int)((rMent.getRtime().getTime() - remy.getRtime().getTime())/86400000);//当期时间有多少天
				if(remy.getPeriods()!=1){
					//获取投资人此标持有时间
					days =  (int)((date.getTime() -  remy.getRtime().getTime())/86400000);//出让人持有时间
				}
			}else{//假如没有上一期还款计划
				//获取当期天数
				day =  (int)((rMent.getRtime().getTime() - userTender.getValuedate().getTime())/86400000);//当期时间有多少天
				//获取投资人此标持有时间
				days =  (int)((date.getTime() -  userTender.getValuedate().getTime())/86400000);//出让人持有时间
			}*/
			//获取当期待还本金
			Double SurplusAmount = Arith.add(rMent.getRamount(), rMent.getRestprincipal());
			//算出当期一元一天的利息
			Double fee =  Arith.div(Arith.div(rMent.getRinterest(), SurplusAmount, 5),day,4); 
			//判断起息日是当期还是次日;如果是当日,那么时间就要-1,如果是次日就是days
			DebtAttorn dAttorn = dAttornService.selectByTid(uda.getTenderid());
			Calendar cal=Calendar.getInstance();  
			cal.setTime(date); 
			int flag = userTenderService.valuedate(dAttorn, cal);
			if(flag==1){//如果承接人当期起息的话,那么出让人的时间就会减少一天,如果承接人是次日起息,那么出让人的天数就是days
				days=days-1;
			}
			//算出出让人转出利息
			Cfee = Arith.mul(Arith.mul(days, fee),uda.getDaamount());
		}else{//逾期债转
			//获取当前时间到逾期当期还款时间之间的时间差
			days =   (int)((date.getTime() - rMent.getRtime().getTime())/86400000);
			//出让人转出滞纳金的算法=日滞纳率*滞纳天数*滞纳本金
			CfeeLate =Arith.mul(Arith.mul(tItem.getDaylatefeerate(), days),Arith.add(rMent.getRamount(), rMent.getRestprincipal()));
		}
		return Cfee+","+CfeeLate;
	}

	
	
	/**
	 * 点击散标投资的时候页面跳转
	* @Title: sanbiao 
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return
	* @param @throws Exception  参数说明 
	* @return ModelAndView    返回类型 
	* @author jiangxueyou
	* @throws
	 */
	@RequestMapping("/sanbiao")
	public ModelAndView sanbiao() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/cjdebtattorn/sanbiao");
		return mav;
	}

}
