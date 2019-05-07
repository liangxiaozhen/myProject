package com.ptpl.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.model.DividedPayments;
import com.ptpl.model.GfundsInt;
import com.ptpl.model.GfundsIntRecord;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserTender;
import com.ptpl.service.DividedPaymentsServiceI;
import com.ptpl.service.GfundsIntRecordServiceI;
import com.ptpl.service.GfundsIntServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserTenderServiceI;


/**
 * 生成站岗利息记录工具类
 * @ClassName: GenerateGfundsIntUtil
 * @Description: TODO(生成站岗利息记录工具类)
 * @author zhenglm
 * @date 2017年3月27日 下午6:18:51
 *
 */
public class GenerateGfundsIntRecordUtil {
	
	/** 标的设置Serice */
	private static TenderItemServiceI tenderItemService = SpringContextHolder.getBean(TenderItemServiceI.class);
	/** 标的分期还款计划Service */
	private static DividedPaymentsServiceI dividedPaymentsService = SpringContextHolder.getBean(DividedPaymentsServiceI.class);
	/** 投标记录Service */
	private static UserTenderServiceI userTenderService = SpringContextHolder.getBean(UserTenderServiceI.class);
	/** 用户账户信息安全Service */
	private static UserAccountSafeInfoServiceI userAccountSafeInfoService = SpringContextHolder.getBean(UserAccountSafeInfoServiceI.class);
	/** 标的站岗利息设置Service */
	private static GfundsIntServiceI gfundsIntService = SpringContextHolder.getBean(GfundsIntServiceI.class);
	/** 标的站岗利息记录Service */
	private static GfundsIntRecordServiceI gfundsIntRecordService = SpringContextHolder.getBean(GfundsIntRecordServiceI.class);
	/** 用户托管账户信息Service */
	private static UserFsAccountInfoServiceI userFsAccountInfoService = SpringContextHolder.getBean(UserFsAccountInfoServiceI.class);
	
	/**
	 * 是否生成还款计划
	 * @Title: checkRepayMent
	 * @Description: TODO(是否生成还款计划)
	 * @param tid 标ID
	 * @return boolean    返回类型(true 生成还款计划成功，false 生成还款计划失败)
	 */
	private static boolean checkRepayMent(BigDecimal tid){
		boolean flag = false;
		if(tid == null){
			return flag;
		}
		
     	TenderItem  tenderItem = tenderItemService.findTenderItemById(tid);//标的信息
    	if(tenderItem == null){
    		return flag;
    	}
    	
    	DividedPayments dividedPayments = new DividedPayments();
    	dividedPayments.setTenderid(tid);
    	List<DividedPayments> dividedPay = dividedPaymentsService.findDividedPaymentss(dividedPayments);
    	if(dividedPay.size() > 0){ //已经生成过标的还款计划了
    		return flag;
    	}else{
    		return flag=true;
    	}
	}

	/**
	 * 判断标的站岗利息设置规则生成站岗利息记录
	 * @Title: generateGfundsIntRecord
	 * @Description: TODO(判断标的站岗利息设置规则生成站岗利息记录)
	 * @param tid 标ID
	 * @return void    返回类型
	 */
	public static void generateGfundsIntRecord(BigDecimal tid){
		TenderItem tenderItem = tenderItemService.findTenderItemById(tid);
		Short issetgfundsint = tenderItem.getIssetgfundsint(); // 是否设置站岗利息
		if(issetgfundsint.equals((short) 1)){ // 判断标的是否设置站岗利息
			boolean flag = checkRepayMent(tid); // 判断是否生成还款计划
			if(!flag){ // 成功生成还款计划
				UserTender tender = new UserTender();
				tender.setTenderid(tid);
				tender.setUtproperty(TenderRecord_Constant.UTPROPERTY_ORIGINAL);
				tender.setTstatus(TenderRecord_Constant.TSTATUS_COMPLETED);
				List<UserTender> userTenderList = userTenderService.findTenderList(tender); // 根据标的ID查询投标记录
				for(UserTender ut : userTenderList){
					BigDecimal investorId = ut.getOutaccountid(); // 投资人baseid
					Double amount = ut.getAmount(); // 投资金额
					UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(investorId);
					Short ugrade = userAccountSafeInfo.getUgrade();
					List<GfundsInt> gfundsIntList = gfundsIntService.selectGfundBytid(tid);
					for(GfundsInt gfundsInt : gfundsIntList){
						Double interest = null; // 站岗利息
						short type = 0; // 奖励方式
						char level[] = gfundsInt.getUgrade().toCharArray(); // 将站岗利息设置表中的会员等级占位符转换为char数组
						if(level[ugrade]=='1'){
							if(Arith.sub(amount, gfundsInt.getMinmoney())>=0 && Arith.sub(amount, gfundsInt.getMaxmoney())<=0){
								if(gfundsInt.getQuota() != null){
									interest = gfundsInt.getQuota();
									type = 1; // 定额
								}else if(gfundsInt.getDayawardrate() != null){
									int days = StringUtil.daysBetween(ut.getTbegintime(), ut.getValuedate()); // 站岗天数 = 起息日-投标日
									double ondayInterest = Arith.mul(amount, gfundsInt.getDayawardrate()); // 平均每天的站岗利息 = 投资金额 * 日奖励费率
									interest = Arith.mul(ondayInterest, days); // 总站岗利息 = 平均每天的站岗利息 * 站岗天数
									type = 2; // 日奖励比例
									if(Arith.sub(interest, gfundsInt.getMaxcompensate())>0){
										interest = gfundsInt.getMaxcompensate();
										type = 3; // 最高金额
									}
								}
							}
						}
						if(interest != null){
							try {
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							throw new NullPointerException("请选择利息补偿方式！");
						}
					}
				}
			}
		}
	}
	
	/**
	 * 生成标的站岗利息记录
	 * @Title: saveGfundsIntRecord
	 * @Description: TODO(生成标的站岗利息记录)
	 * @param gfundsInt 标的站岗利息设置
	 * @param ut 投标记录
	 * @param interest 奖励金额
	 * @param type 奖励方式
	 * @return void    返回类型
	 */
	private static void saveGfundsIntRecord(String pmiscpayman, GfundsInt gfundsInt, UserTender ut, Double interest, short type){
		GfundsIntRecord record = new GfundsIntRecord();
		record.setGfiorderno(StringUtil.getNo()); // 站岗利息流水号
		record.setGfiid(gfundsInt.getId()); // 站岗利息设置表Id
		record.setTenderid(ut.getTenderid()); // 标号ID
		record.setPmiscpayman(pmiscpayman); // 平台杂项付款人
		record.setUtorderno(ut.getOrderno()); // 投标订单号
		record.setInvestorid(ut.getOutaccountid()); // 投资人ID
		record.setTenderamount(ut.getAmount()); // 投标金额
		record.setRewardamount(interest); // 奖励金额
		record.setPayouttype(type); // 奖励方式
		record.setCreateway((short) 1); // 生成方式（1.人工生成，2.自动生成）
		record.setIsgrant((short) 0); // 是否发放（0.未发放，1.已发放）
		record.setIsblending((short) 0); // 是否系统勾兑（0.未勾兑，1.已勾兑）
		record.setIsmanblending((short) 0); // 是否人工勾兑（0.未勾兑，1.已勾兑）
		record.setMadetime(new Date()); // 创建时间
		record.setIsaudit(gfundsInt.getIsaudit()); // 是否审核
		int count = 0;
		count = gfundsIntRecordService.insertSelective(record); // 保存标的站岗利息记录
		if(count > 0){
			if(gfundsInt.getIsaudit().equals((short) 0)){
				// 不需审核直接发放（调用转账接口）
			}
		}
	}
}
