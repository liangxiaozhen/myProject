package com.ptpl.service.impl;

import com.ptpl.constant.ActAward_Constant;
import com.ptpl.constant.Loanapp_Constant;
import com.ptpl.constant.TenderItem_Constant;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.huishang.model.FinTranResult;
import com.ptpl.mapper.DebtAttornMapper;
import com.ptpl.mapper.RepayMentMapper;
import com.ptpl.mapper.TenderItemMapper;
import com.ptpl.mapper.UserTenderMapper;
import com.ptpl.model.AccInExRecord;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.DebtAttorn;
import com.ptpl.model.RepayMent;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserDebtAttorn;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserMakeALoan;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.model.UserTender;
import com.ptpl.model.UserTenderPlusLink;
import com.ptpl.model.loanapp;
import com.ptpl.service.AccInExRecordServiceI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.GuaranteeFeeRecordServiceI;
import com.ptpl.service.GuaranteeFeeServiceI;
import com.ptpl.service.MediacyFeeRecordServiceI;
import com.ptpl.service.MediacyFeeServiceI;
import com.ptpl.service.RiskGuarantyFeeRecordServiceI;
import com.ptpl.service.RiskGuarantyMoneyServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserMakeALoanServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.service.UserTenderPlusLinkServiceI;
import com.ptpl.service.UserTenderServiceI;
import com.ptpl.service.loanappServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.ptpl.controller.huishang.MakeLoansController.readRedResult;
public class UserTenderServiceImpl implements UserTenderServiceI{
	
	protected SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 使用@Autowired注解标注UserTenderMapper变量，
     * 当需要使用UserTenderMapper时，Spring就会自动注入UserTenderMapper
     */
    @Autowired
    private UserTenderMapper userTenderMapper;//注入dao
    @Autowired
    private RepayMentMapper repayMentMapper;//注入dao
    @Autowired
    private TenderItemMapper tenderItemMapper;//注入dao
    @Autowired
    private DebtAttornMapper debtAttornMapper;//注入dao

	/**
	 * 投标记录Service
	 */
	@Resource
	UserTenderServiceI userTenderService;

	/**
	 * 投标放款记录service
	 */
	@Resource
	UserMakeALoanServiceI userMakeALoanService;

	/**
	 * 标的设置Service
	 */
	@Resource
	TenderItemServiceI tenderItemService;

	/**
	 * 用户托管账户信息Service
	 */
	@Resource
	UserFsAccountInfoServiceI userFsAccountInfoService;

	/**
	 * 批量文件记录Service
	 */
	@Resource
	BacthFileRecordServiceI bacthFileRecordService;


	/**
	 * 用户账户service
	 */
	@Resource
	UserAccountServiceI userAccountService;

	/**
	 * 账户收支记录service
	 */
	@Resource
	AccInExRecordServiceI accInExRecordService;

	/**
	 * 标的居间费记录service
	 */
	@Resource
	MediacyFeeRecordServiceI mediacyFeeRecordService;

	/**
	 * 标的担保费记录service
	 */
	@Resource
	GuaranteeFeeRecordServiceI guaranteeFeeRecordService;

	/**
	 * 标的风险保证金记录service
	 */
	@Resource
	RiskGuarantyFeeRecordServiceI riskGuarantyFeeRecordService;

	/**
	 * 标的居间费设置service
	 */
	@Resource
	MediacyFeeServiceI mediacyFeeService;

	/**
	 * 标的担保费设置service
	 */
	@Resource
	GuaranteeFeeServiceI guaranteeFeeService;

	/**
	 * 标的风险保证金设置service
	 */
	@Resource
	RiskGuarantyMoneyServiceI riskGuarantyMoneyService;

	/**
	 * 借款申请记录Service
	 */
	@Resource
	loanappServiceI loanappService;

	/**
	 * 投标增益使用关联Service
	 */
	@Resource
	UserTenderPlusLinkServiceI userTenderPlusLinkService;

	/**
	 * 用户红包Service
	 */
	@Resource
	UserRedEnvelopeServiceI userRedEnvelopeService;

	/**
	 * 用户使用券Service
	 */
	@Resource
	UserInterestRateCouponServiceI userInterestRateCouponService;


	@Override
	public int insertSelective(UserTender usertender) {
        return userTenderMapper.insertSelective(usertender);
    }

	@Override
	public List<UserTender> findUserTenderRecord(Map<String, Object> condition){
		return userTenderMapper.findUserTenderRecord(condition);
	}

	@Override
	public List<UserTender> findTenderList(UserTender tender) {
		return userTenderMapper.findTenderList(tender);
	}
    @Override
	public UserTender findUserTenderById(long id) {
		return userTenderMapper.findUserTenderById(id);
	}

	@Override
	public List<UserTender> findTenderByTstatusAndTenderid(BigDecimal tenderid) {
		return userTenderMapper.findTenderByTstatusAndTenderid(tenderid);
	}

	@Override
	public int updateByPrimaryKeySelective(UserTender tender) {
		return userTenderMapper.updateByPrimaryKeySelective(tender);
	}

	@Override
	public int updateByOrderNO(UserTender tender) {
		return userTenderMapper.updateByOrderNO(tender);
	}
	@Override
	public List<UserTender> selectPendingLoanByTno(String tno) {
		return userTenderMapper.selectPendingLoanByTno(tno);
	}

	@Override
	public List<UserTender> findMyTenderRecord(UserTender tender) {
		return userTenderMapper.findMyTenderRecord(tender);
	}

	@Override
	public int updateValueDateByTenderId(UserTender tender) {
		return userTenderMapper.updateValueDateByTenderId(tender);
	}

	@Override
	public UserTender selectInitialOrderByBaseidAndTid(UserTender tender) {
		return userTenderMapper.selectInitialOrderByBaseidAndTid(tender);
	}

	@Override
	public UserTender selectByLoanNo(String loanno) {
		return userTenderMapper.selectByLoanNo(loanno);
	}

	@Override
	public void dealTime(UserTender tender) {
		if(tender.getTbegintime() != null){
			tender.setTbegintimeStr(sf.format(tender.getTbegintime())); // 处理转账开始时间
		}
		if(tender.getTendtime() != null){
			tender.setTendtimeStr(sf.format(tender.getTendtime())); // 处理转账完成时间
		}
		if(tender.getSysbtime() != null){
			tender.setSysbtimeStr(sf.format(tender.getSysbtime())); // 处理系统勾兑时间
		}
		if(tender.getManbtime() != null){
			tender.setManbtimeStr(sf.format(tender.getManbtime())); // 处理人工勾兑时间
		}
		if(tender.getSysrectime() != null){
			tender.setSysrectimeStr(sf.format(tender.getSysrectime())); // 处理系统勾兑接收数据时间第一次
		}
		if(tender.getReceivetime() != null){
			tender.setReceivetimeStr(sf.format(tender.getReceivetime())); // 人工勾兑接收数据时间第一次
		}
		if(tender.getAudittime() != null){
			tender.setAudittimeStr(sf.format(tender.getAudittime())); // 处理审核时间
		}
	}
	/**
     *
    * @Title: findUserTenderByUtorderno
    * @Description: TODO(根据投标订单号查询)
    * @param @param utorderno
    * @param @return  参数说明
    * @return UserTender    返回类型
    * @author cjm
    * @throws
     */
	@Override
	public UserTender findUserTenderByOrderno(String utorderno) {
 		return userTenderMapper.findUserTenderByOrderno(utorderno);
	}

	@Override
	public List<UserTender> findAllUserTender(BigDecimal outaccountid) {
		return userTenderMapper.findAllUserTender(outaccountid);
	}

	@Override
	public List<UserTender> findUserTenderisadebtattorn(BigDecimal record) {
		return userTenderMapper.findUserTenderisadebtattorn(record);
	}

	/**
	 * 保存债转记录进投标记录表中
	* @Title: saveObject
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param ub
	* @param @param ugradeandbfb
	* @param @param fee
	* @param @param amountStr
	* @param @param cal  参数说明
	* @return void    返回类型
	* @author jiangxueyou
	* @throws
	 */
	public void saveObject(UserDebtAttorn ub,String []str,Calendar cal,BigDecimal userid,String OrdId,Double amount,Double total){
		UserTender userTender = new UserTender();
		userTender.setTenderid(ub.getTenderid());//标的号id
		userTender.setOrderno(OrdId);//债转订单号(新的投标订单号)
		userTender.setDaid(ub.getDaid());//债转设置表id
		userTender.setOlddaorderno(ub.getTorderno());//原投标订单号
		userTender.setHolddate(days(ub));//原投标持有天数
		userTender.setPmiscrecman("MDT00001");//收取收费费对象
		Double fee = Arith.div(amount, ub.getDaamount(), 3);//算出这次的购买金额占了出让人债转金额的几分之几
		Double lixi = Arith.mul(ub.getFactintamount(), fee);//等比例算出承接利息
		if(ub.getDaproperty()==2){//逾期债转
			Double latefee = Arith.mul(ub.getFactocamount(), fee);//等比例算出滞纳金
			userTender.setOcamount(latefee);//承接滞纳金
		}else{
			userTender.setOcamount(0.00);//承接滞纳金
		}
		userTender.setUtintamount(lixi);//承接利息
		userTender.setUtprinamount(amount);//承接本金
		userTender.setTotalamount(total);//承接总金额=承接本金+承接利息+承接滞纳金
		userTender.setRestamount(restamount(ub));//剩余金额:原标的剩余金额

		userTender.setYearprofit(ub.getYearprofit());//承接时年化收益
		userTender.setDatimes(ub.getDebtattorn().getDatimes().shortValue());//债转次数:原标总次数
		userTender.setFeemode(new Short(str[0]));//手续费收取模式 0初始 1根据用户等级，2根据投标持有天数
		userTender.setDahfee(Double.valueOf(str[2]));//债转手续费
		userTender.setDatype(new Short(str[1]));//手续费收费类型:0初始 1:定额 2:百分比 3:最低 4;最高
		userTender.setUtproperty((short)2);//投标属性（1原始投标，2债转投标）
		userTender.setDaproperty(ub.getDaproperty());//债转属性（1正常债转，2逾期债转）
		userTender.setOutaccountid(userid);//ub.getBaseid()投资人就是当前购买人**************************
		userTender.setInaccountid(functionObj(ub).getInaccountid());//原始标的借款人
		userTender.setAmount(functionObj(ub).getAmount());//原始投标金额
		userTender.setTendertype((short)1);//投标方式（1手动，2自动）		都存手动
		userTender.setIsda((short)0);//是否债转（0未债转，1全额债转，2部分债转）		是不是原始标的状态修改,新增的债转标状态存0,是的
		userTender.setTbegintime(cal.getTime());//ub.getSettime()投标开始时间  就是发布时间
		userTender.setFee(0.00);//手续费  存0
		userTender.setTstatus((short)0);//投标的状态(0.初始，1.待审核，2.失败，3.撤销，4.已完成)
		userTender.setValuedate(ValueDate(ub,cal));//起息日
		userTender.setIsblending((short)0);//是否系统勾兑（0未勾兑，1已勾兑）
		userTender.setIsmanblending((short)0);//是否人工勾兑（0未勾兑，1已勾兑）
		userTender.setPaycompany("汇付天下");
		userTender.setTransfertype((short)1);//转账类型:1是账户 2是银行卡
		userTender.setIsaudit((short)0);//不审核
		userTenderMapper.insertSelective(userTender);
	}
	/**
	 * 原投标持有天数=债转发布时间-起息日
	 * 债转标持有天数 = 债转发布时间-承接时间
	* @Title: days
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return  参数说明
	* @return int    返回类型
	* @author jiangxueyou
	* @throws
	 */
	public int days(UserDebtAttorn ub){
		UserTender ut = userTenderMapper.findUserTenderByOrderno(ub.getTorderno());
		System.out.println(ub.getSetendtime());
		System.out.println(ut.getValuedate());
		DebtAttorn debtAttorn = debtAttornMapper.selectByTid(ub.getTenderid());
		Date date = null;
		if(debtAttorn.getIsdebtaudit()==0){//不需要审核
			date = ub.getSettime();
		}else{//需要审核
			date = ub.getAudittime();
		}
		int day = 0;
		/**假如是债转投标的话,那么此标的持有天数就不是*/
		if(ut.getUtproperty()==2){//债转投标:持有天数=这次债转成功时间-上一个投标完成时间
			day =  (int)((date.getTime() -ut.getTendtime().getTime())/86400000);
		}
		if(ut.getUtproperty()==1){//原始投标
			day =  (int)((date.getTime() -ut.getValuedate().getTime())/86400000);
		}
		return day;
	}
	/**
	 * 算出承接总金额
	* @Title: totalAmount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param ub
	* @param @return  参数说明
	* @return Double    返回类型
	* @author jiangxueyou
	* @throws
	 */
	public Double totalAmount(UserDebtAttorn ub){
		Double totalamount = 0.00;
		System.out.println(ub.getDaamount());
		System.out.println(ub.getIntamount());
		totalamount = Arith.add(ub.getDaamount(), ub.getFactintamount());
		if(ub.getOcamount()!=null){
			totalamount = Arith.add(totalamount, ub.getFactocamount());
		}
		return totalamount;

	}
	/**
	 * 查询原标的剩余金额
	* @Title: restamount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param ub
	* @param @return  参数说明
	* @return Double    返回类型
	* @author jiangxueyou
	* @throws
	 */
	public Double restamount(UserDebtAttorn ub){
		Double surplusAmount = 0.00;
		int periods = 0;
		if(ub.getDaproperty()==1){//正常标
			//获取当前债转时间之前的最近的还款时间>和最大期数
			RepayMent repayMent = RtimeAndPeriodsforRepayMent2(ub,new Date());
			if(repayMent==null){
				periods = 1;
			}else{
				periods=repayMent.getPeriods();
			}
		}else{//逾期标
			periods = ub.getOdperiods().intValue();
		}
		RepayMent repayMent3  = getAndPeriods(ub,periods);
		surplusAmount =Arith.add(repayMent3.getRamount(),repayMent3.getRestprincipal());

		return surplusAmount;
		}
	/**
	 * 返回一个投标记录对象
	* @Title: functionObj
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param ub
	* @param @return  参数说明
	* @return UserTender    返回类型
	* @author jiangxueyou
	* @throws
	 */
	public UserTender functionObj(UserDebtAttorn ub){
		UserTender ut = userTenderMapper.findUserTenderByOrderno(ub.getTorderno());
		return ut;
		}
	/**
	 * 算起息时间
	* @Title: ValueDate
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param ub
	* @param @return  参数说明
	* @return Date    返回类型
	* @author jiangxueyou
	* @throws
	 */
	public Date ValueDate(UserDebtAttorn ub,Calendar cal){
		/**判断当前标是否是一次性还本付息,如果是,那么就是这个债转标的起息日就去原始标的起息日
		 * 判断当前债转是不是第一期债转,如果是,那么就取原始标的起息日
		 * 判断当前债转不是第一期债转,那么起息日就是原始标的上一个还款日
		 * */
		Date date = null;
		/**第一步:获取原始标的信息*/
		UserTender userTender = userTenderMapper.findUserTenderByOrderno(ub.getTorderno());
		/**第二步:获取原始标的类型:一次性还本付息......*/
		TenderItem  tenderitem = tenderItemMapper.findTenderItemById(userTender.getTenderid());
		/**获取投资人还款计划表*/
		/**查询小于当前时间的最大还款日期,如果查询出来的结果为空,那么说明是第一期就发生的债转,如果有值,那么就是此笔债转标的起息日*/
		RepayMent rmt = RtimeAndPeriodsforRepayMent(ub,cal.getTime());
		if(rmt==null || tenderitem.getRepaymentpro()==1){
			date = userTender.getValuedate();//原始标的起息日
		}else{
			date = rmt.getRtime();
		}
		return date;
		}
	/**
     * 算承接人起息日到上一个还款日(原始标起息日)之间的天数
    * @Title: ValueDate
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param ub
    * @param @param cal
    * @param @return  参数说明
    * @return Date    返回类型
    * @author jiangxueyou
    * @throws
     */
	public int valuedate(DebtAttorn ub,Calendar cal){
		int days = 0;
		//算起息日
		//起息规则（1承接日当天，2承接日次日，3承接日固定时间之前，4承接日固定时间之后）
		short  valuerule = ub.getValuerule();
		//short  valuerule = 1;
		//起息时间点
		String valuepoint = ub.getValuepoint();
		//String valuepoint = "10:00:00";
		//获取承接时间
		SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
		/**算起息时间*/
		Date date = null;
		if(valuerule==1){
			date = cal.getTime();
			days=1;
		}
		if(valuerule==2){
			cal.add(Calendar.DAY_OF_MONTH, 1);//债转时间+1天=次日
			date = cal.getTime();
			days=2;
		}
		if(valuerule==3){
			//承接时间
			String caltime = sdf3.format(cal.getTime());
			String [] calTime = caltime.split(":");
			//标的设置表中设置的时间点
			String [] valuepointArray = valuepoint.split(":");
			if(Double.valueOf(calTime[0])<=Double.valueOf(valuepointArray[0])){//时
				if(Double.valueOf(calTime[1])<=Double.valueOf(valuepointArray[1])){//分
					if(Double.valueOf(calTime[2])<=Double.valueOf(valuepointArray[2])){//秒
						date = cal.getTime();
						days=1;
					}else{
						cal.add(Calendar.DAY_OF_MONTH, 1);//债转时间+1天=次日
						date = cal.getTime();
						days=2;
					}
				}else{
					cal.add(Calendar.DAY_OF_MONTH, 1);//债转时间+1天=次日
					date = cal.getTime();
					days=2;
				}
			}else{
				cal.add(Calendar.DAY_OF_MONTH, 1);//债转时间+1天=次日
				date = cal.getTime();
				days=2;
			}
		}
		return days;
	}
	/**
	 * 获取当前债转时间之前的最近的还款时间<和最大期数
	 * @param @param ub
	 * @param @param cal
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent RtimeAndPeriodsforRepayMent(UserDebtAttorn ub,Date date){
		RepayMent rm = new RepayMent();
		rm.setInaccountid(ub.getBaseid());
		rm.setUtorderno(ub.getTorderno());
		rm.setRtime(date);
		rm.setTenderid(ub.getTenderid());
		RepayMent rmt = repayMentMapper.getRtimeAndPeriodsforRepayMent(rm);
		return rmt;
	}
	/**
	 * 获取当前债转时间之前的最近的还款时间<和最大期数
	 * @param @param ub
	 * @param @param cal
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent RtimeAndPeriodsforRepayMent2(UserDebtAttorn ub,Date date){
		RepayMent rm = new RepayMent();
		rm.setInaccountid(ub.getBaseid());
		rm.setUtorderno(ub.getTorderno());
		rm.setRtime(date);
		rm.setTenderid(ub.getTenderid());
		RepayMent rmt = repayMentMapper.getRtimeAndPeriodsforRepayMent2(rm);
		return rmt;
	}
	/**
	 * 获取当期还款计划
	 * @param @param ub
	 * @param @param periods
	 * @param @return
	 * @return RepayMent
	 * @author jiangxueyou
	 */
	public RepayMent getAndPeriods(UserDebtAttorn ub,int periods){
		RepayMent rmt = new RepayMent();
		rmt.setInaccountid(ub.getBaseid());//投资人id
		rmt.setUtorderno(ub.getTorderno());//投标订单号
		rmt.setTenderid(ub.getTenderid());
		rmt.setPeriods(periods);
		RepayMent repayment =  repayMentMapper.getAndPeriods(rmt);
		return repayment;
	}

	@Override
	public UserTender findUserTenderByOrdernoAndUtProperty(String orderno) {
		return userTenderMapper.findUserTenderByOrdernoAndUtProperty(orderno);
	}

	@Override
	public List<UserTender> findUserTenderisadebtattornAndCode(UserTender ut) {
		return userTenderMapper.findUserTenderisadebtattornAndCode(ut);
	}

	@Override
	public List<UserTender> findIsFailTC(BigDecimal tenderid) {
		return userTenderMapper.findIsFailTC(tenderid);
	}

	@Override
	public List<UserTender> findRecordByBaseId(BigDecimal baseId) {
		return userTenderMapper.findRecordByBaseId(baseId);
	}

	@Override
	public String editFile(BacthFileRecord bacthFileRecord,BufferedReader br ) throws IOException{
		String line = null;
		Boolean flag=true;
		String returnMsg="";
		while ((line = br.readLine()) != null) {
			FinTranResult ft = readRedResult(line);
			if (!"00".equals(ft.getRspcode())) {
				updateTstatusWhenFail(ft.getSerialno().trim());
				System.out.println("处理失败");
				returnMsg="处理失败";
				flag=false;
			}else {
				saveLoansRecord(ft.getSerialno().trim());
				returnMsg="处理成功";
			}
		}
		if(!flag){
			return null;
		}
		//更新文件为已处理
		bacthFileRecord.setIsDealResult((short) 1);
		bacthFileRecord.setDealResult((short) 1);
		bacthFileRecordService.update(bacthFileRecord);
		return returnMsg;
	}


	private void updateTstatusWhenFail(String utorderno){
		UserTender usertender = userTenderMapper.findUserTenderByOrderno(utorderno);
		if(usertender.getTstatus().equals(TenderRecord_Constant.TSTATUS_PROCESSING)){
			usertender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT); // 投标状态改为待放款
			userTenderMapper.updateByOrderNO(usertender);
		}
	}

	private void saveLoansRecord(String utorderno){
		List<UserMakeALoan> userMakeALoan = userMakeALoanService.selectByOrderNo(utorderno);
		for (UserMakeALoan umal : userMakeALoan) {
			UserTender usertender = userTenderService.findUserTenderByOrderno(utorderno);
			TenderItem tenderItem = tenderItemService.findTenderItemById(usertender.getTenderid());
			List<UserTenderPlusLink> userTenderPlusLink = userTenderPlusLinkService.findUserTenderPlusLinkByUtId(usertender.getId()); // 查询有无使用增益
			if (userTenderPlusLink != null) {
				for (UserTenderPlusLink plusLink : userTenderPlusLink) {
					if (plusLink.getReid() != null) {
						UserRedEnvelope userRedEnvelope = userRedEnvelopeService.findUserRedEnvelopeById(plusLink.getReid());
						userRedEnvelope.setIsuse(ActAward_Constant.AWARD_ALUSE);
						userRedEnvelopeService.updateRedEnvById(userRedEnvelope);
					}
					if (plusLink.getIcid() != null) {
						UserInterestRateCoupon userInterestRateCoupon = userInterestRateCouponService.findUserInterestRateCouponById(plusLink.getIcid());
						userInterestRateCoupon.setIsuse(ActAward_Constant.AWARD_ALUSE);
						userInterestRateCouponService.updataCouponById(userInterestRateCoupon);
					}
				}
			}
			if (umal.getMalstatus() == 0) {
				updateInvestorAccount(usertender);
				updateBorrowerAccount(usertender);
				loanapp loanapp = loanappService.selectByPrimaryKey(tenderItem.getLoanappid());
				loanapp.setReceiptsamount(Arith.add(loanapp.getReceiptsamount(), usertender.getAmount())); // 已入账借款金额
				int count = 0;
				count = loanappService.updateByPrimaryKeySelective(loanapp);
				if (count > 0) {
					System.out.println("更新借款申请已入账借款金额成功！！！！！！！！！！！！！！！！！！！！！！！！！！");
				}
				if (usertender.getMediacyfee() != 0) { // 扣除居间费
					deductionBorrowerMediacyfee(usertender);
				}
				if (usertender.getGuaranteefee() != 0) { // 扣除担保费
					deductionBorrowerGuaranteefee(usertender);
				}
				if (usertender.getRiskguarantyfee() != 0) { // 扣除风险保证金
					deductionBorrowerRiskguarantyfee(usertender);
				}
			}
			umal.setMalendtime(new Date());                // 放款完成时间
			umal.setMalstatus((short) 1);                    // 放款的状态（0.失败，1.成功）
			umal.setIsthaw((short) 1);                        // 是否解冻（0.否，1.是）
			int number = 0;
			number = userMakeALoanService.updateByMLoanOrderNoSelective(umal);
			if (number > 0) {
				System.out.println("更新投标放款记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			}
			usertender.setTstatus(TenderRecord_Constant.TSTATUS_COMPLETED); // 投标状态
			usertender.setIsfreeze(TenderRecord_Constant.ISFREEZE_UNFREEZE); // 已解冻
			usertender.setIsrepayend(TenderRecord_Constant.ISREPAYEND_REPAYMENTING); // 还款中
			int rows = 0;
			rows = userTenderService.updateByOrderNO(usertender);
			if (rows > 0) {
				System.out.println("更新投标记录成功");
			}


			if(tenderItem.getTamount().equals(tenderItem.getFinishtamount())){
				loanapp loanapp = loanappService.selectByPrimaryKey(tenderItem.getLoanappid());
				tenderItem.setTstatus(TenderItem_Constant.T6);
				loanapp.setAppstatus(Loanapp_Constant.T9);
				loanappService.updateByPrimaryKeySelective(loanapp);
				tenderItemService.update(tenderItem);
			}
		}
	}

	/**
	 * 扣除借款人居间费
	 *
	 * @param huifuParam
	 */
	private void deductionBorrowerMediacyfee(UserTender userTender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userTender.getInaccountid());                            // 根据baseid查询借款人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), userTender.getMediacyfee());                                            // 用户新总资产=用户原总资产-居间费
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), userTender.getMediacyfee());                                        // 用户新可用余额=用户原可用余额-居间费
		userAccount.setBalance(balance);                                                                                            // 用户总资产
		userAccount.setAvlbalance(avlbalance);                                                                                        // 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if (count > 0) {
			System.out.println("扣除借款人居间费成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			chargeMediacyFee(userTender, userAccount);
		}
	}

	/**
	 * 扣除借款人担保费
	 *
	 * @param huifuParam
	 */
	private void deductionBorrowerGuaranteefee(UserTender userTender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userTender.getInaccountid());                            // 根据baseid查询借款人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), userTender.getGuaranteefee());                                            // 用户新总资产=用户原总资产-担保费
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), userTender.getGuaranteefee());                                    // 用户新可用余额=用户原可用余额-担保费
		userAccount.setBalance(balance);                                                                                            // 用户总资产
		userAccount.setAvlbalance(avlbalance);                                                                                        // 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if (count > 0) {
			System.out.println("更新借款人担保费成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			chargeGuaranteeFee(userTender, userAccount);
		}
	}

	/**
	 * 扣除借款人风险保证金
	 *
	 * @param huifuParam
	 */
	private void deductionBorrowerRiskguarantyfee(UserTender userTender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(userTender.getInaccountid());                            // 根据baseid查询借款人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), userTender.getRiskguarantyfee());                                        // 用户新总资产=用户原总资产-风险保证金
		double avlbalance = Arith.sub(userAccount.getAvlbalance(), userTender.getRiskguarantyfee());                                // 用户新可用余额=用户原可用余额-风险保证金
		userAccount.setBalance(balance);                                                                                            // 用户总资产
		userAccount.setAvlbalance(avlbalance);                                                                                        // 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if (count > 0) {
			System.out.println("更新借款人风险保证金成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			chargeRiskGuarantyFee(userTender, userAccount);
		}
	}


	/**
	 * 收取居间费
	 *
	 * @param userTender
	 */
	private void chargeMediacyFee(UserTender userTender, UserAccount userAccount) {
		AccInExRecord accInExRecord = new AccInExRecord();
		accInExRecord.setBaseid(userTender.getInaccountid());                        // 用户ID-借款人
		accInExRecord.setAieorderno(StringUtil.getNo());                            // 收支记录流水号
		accInExRecord.setType((short) 15);                                            // 业务类型-居间费
		accInExRecord.setInamount((double) 0);                                        // 收入
		accInExRecord.setOutamount(userTender.getMediacyfee());                        // 支出
		accInExRecord.setPaccount("MDT000001");                                        // 平台账户
		accInExRecord.setPinamount(userTender.getMediacyfee());                        // 平台收入
		accInExRecord.setPoutamount((double) 0);                                    // 平台支出
		accInExRecord.setStatus((short) 1);                                            // 业务状态
		accInExRecord.setDescription("收取居间费");                                        // 说明
		accInExRecord.setBalance(userAccount.getAvlbalance());                        // 用户可用余额
		accInExRecord.setFreebalance(userAccount.getFreezebalance());                // 用户冻结余额
		accInExRecord.setTotalbalance(userAccount.getBalance());                    // 用户总金额
		accInExRecord.setRecordtime(new Date());                                    // 发生时间
		accInExRecord.setRemark("收取居间费");                                            // 备注
		int count = 0;
		count = accInExRecordService.insertSelective(accInExRecord);
		if (count > 0) {
			System.out.println("新增居间费收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}

	/**
	 * 收取担保费
	 *
	 * @param userTender
	 * @param userAccount
	 */
	private void chargeGuaranteeFee(UserTender userTender, UserAccount userAccount) {
		AccInExRecord accInExRecord = new AccInExRecord();
		accInExRecord.setBaseid(userTender.getInaccountid());                        // 用户ID-借款人
		accInExRecord.setAieorderno(StringUtil.getNo());                            // 收支记录流水号
		accInExRecord.setType((short) 16);                                            // 业务类型-担保费
		accInExRecord.setInamount((double) 0);                                        // 收入
		accInExRecord.setOutamount(userTender.getGuaranteefee());                    // 支出
		accInExRecord.setPaccount("MDT000001");                                        // 平台账户
		accInExRecord.setPinamount(userTender.getGuaranteefee());                    // 平台收入
		accInExRecord.setPoutamount((double) 0);                                    // 平台支出
		accInExRecord.setStatus((short) 1);                                            // 业务状态
		accInExRecord.setDescription("收取担保费");                                        // 说明
		accInExRecord.setBalance(userAccount.getAvlbalance());                        // 用户可用余额
		accInExRecord.setFreebalance(userAccount.getFreezebalance());                // 用户冻结余额
		accInExRecord.setTotalbalance(userAccount.getBalance());                    // 用户总金额
		accInExRecord.setRecordtime(new Date());                                    // 发生时间
		accInExRecord.setRemark("收取担保费");                                            // 备注
		int count = 0;
		count = accInExRecordService.insertSelective(accInExRecord);
		if (count > 0) {
			System.out.println("新增担保费收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}

	/**
	 * 收取风险保证金
	 *
	 * @param userTender
	 * @param userAccount
	 */
	private void chargeRiskGuarantyFee(UserTender userTender, UserAccount userAccount) {
		AccInExRecord accInExRecord = new AccInExRecord();
		accInExRecord.setBaseid(userTender.getInaccountid());                        // 用户ID-借款人
		accInExRecord.setAieorderno(StringUtil.getNo());                            // 收支记录流水号
		accInExRecord.setType((short) 18);                                            // 业务类型-担保费
		accInExRecord.setInamount((double) 0);                                        // 收入
		accInExRecord.setOutamount(userTender.getRiskguarantyfee());                // 支出
		accInExRecord.setPaccount("MDT000001");                                        // 平台账户
		accInExRecord.setPinamount(userTender.getRiskguarantyfee());                // 平台收入
		accInExRecord.setPoutamount((double) 0);                                    // 平台支出
		accInExRecord.setStatus((short) 1);                                            // 业务状态
		accInExRecord.setDescription("收取风险保证金");                                    // 说明
		accInExRecord.setBalance(userAccount.getAvlbalance());                        // 用户可用余额
		accInExRecord.setFreebalance(userAccount.getFreezebalance());                // 用户冻结余额
		accInExRecord.setTotalbalance(userAccount.getBalance());                    // 用户总金额
		accInExRecord.setRecordtime(new Date());                                    // 发生时间
		accInExRecord.setRemark("收取风险保证金");                                        // 备注
		int count = 0;
		count = accInExRecordService.insertSelective(accInExRecord);
		if (count > 0) {
			System.out.println("新增风险保证金收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}



	/**
	 * 更新借款人用户账户表-转账金额
	 *
	 * @param huifuParam
	 */
	private void updateBorrowerAccount(UserTender usertender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(usertender.getInaccountid());                        // 根据baseid查询借款人用户账户信息
		double balance = Arith.add(userAccount.getBalance(), usertender.getAmount());                                            // 用户新总资产=用户原总资产+转账金额
		double avlbalance = Arith.add(userAccount.getAvlbalance(), usertender.getAmount());                                        // 用户新可用余额=用户原可用余额+转账金额
		userAccount.setBalance(balance);                                                                                        // 用户总资产
		userAccount.setAvlbalance(avlbalance);                                                                                    // 用户可用余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新借款人用户账户表
		if (count > 0) {
			System.out.println("更新借款人用户账户表成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			insertLoanAccInExRecord(usertender.getAmount(), userAccount);
		}
	}


	/**
	 * 更新投资人用户账户表
	 *
	 * @param investor-投资人客户号
	 * @param transAmt-转账金额
	 */
	private void updateInvestorAccount(UserTender usertender) {
		UserAccount userAccount = userAccountService.getUserAccountByBaseId(usertender.getOutaccountid());                            // 根据baseid查询投资人用户账户信息
		double balance = Arith.sub(userAccount.getBalance(), usertender.getAmount());                                                // 用户新总资产=用户原总资产-转账金额
		double freezebalance = Arith.sub(userAccount.getFreezebalance(), usertender.getAmount());                                    // 用户冻结余额=用户原冻结余额-转账金额
		userAccount.setBalance(balance);                                                                                            // 用户总资产
		userAccount.setFreezebalance(freezebalance);                                                                                // 冻结余额
		int count = 0;
		count = userAccountService.updateUseraccount(userAccount); // 更新投资人用户账户表
		if (count > 0) {
			System.out.println("更新投资人用户账户表成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
			insertInvestorAccInExRecord(usertender.getAmount(), userAccount);
		}
	}

	/**
	 * 新增投资人账户收支记录-投标
	 */
	private void insertInvestorAccInExRecord(Double amount, UserAccount userAccount) {
		AccInExRecord record = new AccInExRecord();
		record.setBaseid(userAccount.getBaseid());                            // 投资人baseId
		record.setAieorderno(StringUtil.getNo());                            // 收支记录流水号
		record.setType((short) 5);                                            // 业务类型-投标
		record.setInamount((double) 0);                                        // 收入
		record.setOutamount(amount);        // 支出
		record.setStatus((short) 1);                                        // 业务状态-成功
		record.setDescription("投标转账");                                        // 说明
		record.setBalance(userAccount.getAvlbalance());                        // 用户可用余额
		record.setFreebalance(userAccount.getFreezebalance());                // 用户冻结余额
		record.setTotalbalance(userAccount.getBalance());                    // 用户总金额
		record.setRecordtime(new Date());                                    // 发生时间
		record.setRemark("转账转出");                                            // 备注
		int count = 0;
		count = accInExRecordService.insertSelective(record);                // 新增投标转账转出收支记录明细
		if (count > 0) {
			System.out.println("新增投资人账户收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}


	/**
	 * 新增借款人账户收支记录-投标
	 */
	private void insertLoanAccInExRecord(Double amount, UserAccount userAccount) {
		AccInExRecord record = new AccInExRecord();
		record.setBaseid(userAccount.getBaseid());                            // 借款人baseId
		record.setAieorderno(StringUtil.getNo());                            // 收支记录流水号
		record.setType((short) 5);                                            // 业务类型-投标
		record.setInamount(amount);                                            // 收入
		record.setOutamount((double) 0);                                    // 支出
		record.setStatus((short) 1);                                        // 业务状态-成功
		record.setDescription("投标转账");                                        // 说明
		record.setBalance(userAccount.getAvlbalance());                        // 用户可用余额
		record.setFreebalance(userAccount.getFreezebalance());                // 用户冻结余额
		record.setTotalbalance(userAccount.getBalance());                    // 用户总金额
		record.setRecordtime(new Date());                                    // 发生时间
		record.setRemark("转账转入");                                            // 备注
		int count = 0;
		count = accInExRecordService.insertSelective(record);                // 新增投标转账转入收支记录明细
		if (count > 0) {
			System.out.println("新增借款人账户收支记录成功！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
		}
	}


}
