package com.ptpl.web.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

import com.huishang.util.HSignUtil;
import com.ptpl.constant.TenderRecord_Constant;
import com.ptpl.controller.SpringContextHolder;
import com.ptpl.controller.huishang.FileHttpUpload;
import com.ptpl.controller.huishang.UserTenderCancleController;
import com.ptpl.model.Award;
import com.ptpl.model.BacthFileRecord;
import com.ptpl.model.FailTAwardCompensate;
import com.ptpl.model.FailTCRecord;
import com.ptpl.model.FailTCompensate;
import com.ptpl.model.TenderItem;
import com.ptpl.model.UserAccount;
import com.ptpl.model.UserAccountSafeInfo;
import com.ptpl.model.UserBonusPoints;
import com.ptpl.model.UserFsAccountInfo;
import com.ptpl.model.UserInterestRateCoupon;
import com.ptpl.model.UserOutsideAward;
import com.ptpl.model.UserRedEnvelope;
import com.ptpl.model.UserTender;
import com.ptpl.service.AwardServiceI;
import com.ptpl.service.BacthFileRecordServiceI;
import com.ptpl.service.FailTAwardCompensateServiceI;
import com.ptpl.service.FailTCRecordServiceI;
import com.ptpl.service.FailTCompensateServiceI;
import com.ptpl.service.TenderItemServiceI;
import com.ptpl.service.UserAccountSafeInfoServiceI;
import com.ptpl.service.UserAccountServiceI;
import com.ptpl.service.UserBonusPointsServiceI;
import com.ptpl.service.UserFsAccountInfoServiceI;
import com.ptpl.service.UserInterestRateCouponServiceI;
import com.ptpl.service.UserOutsideAwardServiceI;
import com.ptpl.service.UserRedEnvelopeServiceI;
import com.ptpl.service.UserTenderServiceI;

/**
 * 生成流标补偿记录工具类
 * @ClassName: GenerateFailTCRecordUtil
 * @Description: TODO(生成流标补偿记录工具类)
 * @author zhenglm
 * @date 2017年3月28日 下午6:32:26
 *
 */
public class GenerateFailTCRecordUtil {
	
	/** 投标记录Service */
	private static UserTenderServiceI userTenderService = SpringContextHolder.getBean(UserTenderServiceI.class);
	/** 标的流标补偿设置ervice */
	private static FailTCompensateServiceI failTCompensateService = SpringContextHolder.getBean(FailTCompensateServiceI.class);
	/** 用户账户信息安全Service */
	private static UserAccountSafeInfoServiceI userAccountSafeInfoService = SpringContextHolder.getBean(UserAccountSafeInfoServiceI.class);
	/** 标的设置Serice */
	private static TenderItemServiceI tenderItemService = SpringContextHolder.getBean(TenderItemServiceI.class);
	/** 标的流标奖品补偿设置Service */
	private static FailTAwardCompensateServiceI failTAwardCompensateService = SpringContextHolder.getBean(FailTAwardCompensateServiceI.class);
	/** 标的流标补偿记录Service */
	private static FailTCRecordServiceI failTCRecordService = SpringContextHolder.getBean(FailTCRecordServiceI.class);
	/** 奖品设置Service */
	private static AwardServiceI awardService = SpringContextHolder.getBean(AwardServiceI.class);
	/** 用户账户Service */
	private static UserAccountServiceI userAccountService = SpringContextHolder.getBean(UserAccountServiceI.class);
	/** 用户红包Service */
	private static UserRedEnvelopeServiceI userRedEnvelopeService = SpringContextHolder.getBean(UserRedEnvelopeServiceI.class);
	/** 用户积分Service */
	private static UserBonusPointsServiceI userBonusPointsService = SpringContextHolder.getBean(UserBonusPointsServiceI.class);
	/** 用户使用券Service */
	private static UserInterestRateCouponServiceI userInterestRateCouponService = SpringContextHolder.getBean(UserInterestRateCouponServiceI.class);
	/** 用户站外奖品Service */
	private static UserOutsideAwardServiceI userOutsideAwardService = SpringContextHolder.getBean(UserOutsideAwardServiceI.class);
	/** 用户托管账号信息Service */
	private static UserFsAccountInfoServiceI userFsAccountInfoService = SpringContextHolder.getBean(UserFsAccountInfoServiceI.class);
	/** 批量文件记录Service */
	private static BacthFileRecordServiceI bacthFileRecordService = SpringContextHolder.getBean(BacthFileRecordServiceI.class);
	
	// 生成批次号
	@Autowired
	private static OracleSequenceMaxValueIncrementer failtcnumber;
	
	/**
	 * 判断标的流标补偿设置规则生成标的流标补偿记录
	 * @Title: generateFailTCRecord
	 * @Description: TODO(判断标的流标补偿设置规则生成标的流标补偿记录)
	 * @param tid 标号ID
	 * @return void    返回类型
	 */
	public static boolean generateFailTCRecord(BigDecimal tid){
		List<UserTender> userTenderList = userTenderService.findIsFailTC(tid); // 根据标的ID查询投标记录
		if(userTenderList.size()>0){
			return false;
		}
//		TenderItem tenderItem = tenderItemService.findTenderItemById(tid);
//		Short isintcompensateon = tenderItem.getIsintcompensateon(); // 流标利息方式补偿开关是否打开
//		Short isawardcompensateon = tenderItem.getIsawardcompensateon(); // 流标奖品方式补偿开关是否打开
//		UserTender tender = new UserTender();
//		tender.setTenderid(tid);
//		tender.setUtproperty(TenderRecord_Constant.UTPROPERTY_ORIGINAL);
//		tender.setTstatus(TenderRecord_Constant.TSTATUS_COMPLETED);
//		tender.setTstatus(TenderRecord_Constant.TSTATUS_PENDINGAUDIT);
//		userTenderList = userTenderService.findTenderList(tender); // 根据标的ID查询投标记录
//		Short isAudit = 0; // 是否需要审核
//		FailTCRecord record = new FailTCRecord();
//		for(UserTender ut : userTenderList){
//			new UserTenderCancleController().doParams(ut);
//			BigDecimal investorId = ut.getOutaccountid(); // 投资人baseid
//			Double amount = ut.getRealamount(); // 投资本金金额（类现金和假现金要退回）
//			UserAccountSafeInfo userAccountSafeInfo = userAccountSafeInfoService.selectByBaseId(investorId);
//			Short ugrade = userAccountSafeInfo.getUgrade(); // 用户等级
//			if(isintcompensateon.equals((short) 1)){ // 流标利息方式补偿开关打开
//				List<FailTCompensate> failTCompensateList = failTCompensateService.selectefatlpenBytid(tid);
//				for(FailTCompensate fail : failTCompensateList){
//					Double compensation = null; // 奖励金额
//					char level[] = fail.getIntugrade().toCharArray(); // 将标的流标补偿设置表中的会员等级占位符转换为char数组
//					if(level[ugrade]=='1'){
//						if(Arith.sub(amount, fail.getMinmoney())>=0 && Arith.sub(amount, fail.getMaxmoney())<=0){
//							if(fail.getQuota() != null){
//								compensation = fail.getQuota();
//								record.setPayouttype((short) 1); // 奖励方式（1金额，2奖品，3金额+奖品）
//								record.setRewardamount(compensation); // 奖励金额
//								isAudit = fail.getIsaudit();
//							}else if(fail.getDayawardrate() != null){
//								int days = StringUtil.daysBetween(ut.getTbegintime(), new Date()); // 天数 = 当前日期-投标日
//								double ondaycompensation = Arith.mul(amount, fail.getDayawardrate()); // 平均每天流标补偿金 = 投资金额 * 日奖励费率
//								compensation = Arith.mul(ondaycompensation, days); // 总流标补偿金 = 平均每天的流标补偿金 * 天数
//								if(Arith.sub(compensation, fail.getMaxcompensate())>0){
//									compensation = fail.getMaxcompensate(); // 最高补偿金额
//								}
//								record.setPayouttype((short) 1); // 奖励方式（1金额，2奖品，3金额+奖品）
//								record.setRewardamount(compensation); // 奖励金额
//								isAudit = fail.getIsaudit();
//							}
//						}
//					}
//				}
//			}
//			if(isawardcompensateon.equals((short) 1)){ // 流标奖品方式补偿开关打开
//				List<FailTAwardCompensate> failTAwardCompensateList = failTAwardCompensateService.selectfailAwardBytid(tid);
//				for(FailTAwardCompensate failAward : failTAwardCompensateList){
//					char level[] = failAward.getAwardugrade().toCharArray(); // 将标的流标奖品补偿设置表中的会员等级占位符转换为char数组
//					if(level[ugrade]=='1'){
//						if(record.getPayouttype() == null){
//							record.setPayouttype((short) 2); // 奖励方式（1金额，2奖品，3金额+奖品）
//						}else if(record.getPayouttype().equals((short) 1)){
//							record.setPayouttype((short) 3); // 奖励方式（1金额，2奖品，3金额+奖品）
//						}
//						record.setAwardname(failAward.getAwardname()); // 奖品名称
//						record.setAwardno(failAward.getAwardno()); // 奖品编号
//						isAudit = failAward.getIsaudit();
//						Award award = awardService.selectByAwardNo(failAward.getAwardno());
//						record.setAwardamount(award.getCashorvoucher()); // 奖额
//					}
//				}
//			}
//			record.setFtcorderno(StringUtil.getNo()); // 流标补偿流水号
//			record.setTenderid(ut.getTenderid()); // 标号ID
//			record.setUtorderno(ut.getOrderno()); // 投标订单号
//			record.setInvestorid(ut.getOutaccountid()); // 投资人ID
//			record.setTenderamount(ut.getRealamount()); // 投标金额
//			record.setCreateway((short) 1); // 记录生成方式（1.人工生成，2.系统自动）
//			record.setIsgrant((short) 0); // 补偿是否发放（0.未发放，1.已发放，2.待确认，3.已确认）
//			record.setIsblending((short) 0); // 是否系统（0.未勾兑，1.已勾兑）
//			record.setIsmanblending((short) 0); // 是否人工勾兑（0.未勾兑，1.）
//			record.setMadetime(new Date()); // 创建时间
//			record.setIsaudit(isAudit); // 是否审核
//			int count = 0;
//			if(record.getPayouttype() != null){
//				count = failTCRecordService.insertSelective(record); // 保存标的流标补偿记录
//			}
//			if(count > 0){
//				if(isAudit.equals((short) 0)){
//					// 不需要审核直接发放（调用转账接口）
//					if(record.getPayouttype().equals((short) 2) || record.getPayouttype().equals((short) 3)){
//						// 发放流标补偿奖品
//						grantAward(record);
//						return true;
//					}
//				}
//			}
//		}
//		if(isAudit.equals((short) 0)){
//			// 不需要审核直接发放（调用转账接口）
//			if(record.getPayouttype().equals((short) 1) || record.getPayouttype().equals((short) 1)){
//				try {
//					if(redBatchTransfer(tid).equals("0000")){
//						return true;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
		return true;
	}
	
	/**
	 * 发放流标补偿奖品
	 * @Title: grantAward
	 * @Description: TODO(发放流标补偿奖品)
	 * @param failTCRecord 流标补偿记录
	 * @return void    返回类型
	 */
	private static void grantAward(FailTCRecord failTCRecord) {
		Award award = awardService.selectByAwardNo(failTCRecord.getAwardno());
		Short awardattribute = award.getAttribute(); // 奖品属性（1.现金券、2.类现金券、3.假现金券、4.交易积分、5.系统积分、6.加息券、31.虚拟其他、61.实体其他）
		Double awardamount = failTCRecord.getAwardamount(); // 奖额
		award.setQuantityrest(award.getQuantityrest() - failTCRecord.getAwardcopies()); // 扣除奖品剩余份数
		int count = awardService.update(award);
		if(count > 0){
		    //更新用户账户表，扣除奖品表中剩余奖品份数
			UserAccount ua = userAccountService.getUserAccountByBaseId(failTCRecord.getInvestorid()); // 获取投资人用户账户信息
			if(awardattribute.equals((short) 2) || awardattribute.equals((short) 3)){ // 发放类现金券或者假现金券
		    	ua.setRedenvelope(Arith.add(ua.getRedenvelope(), awardamount)); // 用户红包 （类现金  假现金）
		    	ua.setAvlredenvelope(Arith.add(ua.getAvlredenvelope(), awardamount)); // 可用红包（类现金  假现金）
		    	int rows = userAccountService.updateUseraccount(ua);  // 更新投资人用户账户
			    if(rows>0){
					// 用户红包表
					UserRedEnvelope userred= new UserRedEnvelope();
					userred.setBaseid(failTCRecord.getInvestorid()); // 用户ID
					userred.setUreno(failTCRecord.getAwardno()); // 红包编号（对应奖品表编号）
					userred.setRectype((short) 8); // 红包获取方式类型(1.注册、2.累投、3.单标、4.首投、5.手动颁奖、6.自主颁奖、7.完善资料、8.流标补偿)
					userred.setRetype(awardattribute); // 红包类型（1.现金、2.类现金、3.假现金）
					userred.setRestime(new Date()); // 红包发放时间
				    userred.setRedenvelope(awardamount); // 红包金额
					userred.setStatus((short) 3); // 奖品发放状态 （1.待审核、2.待处理、3.已领取、4.待确认、5.已经确认、6.发货中、7.领取失败、8.审核失败）
					userred.setIsuse((short) 2); // 奖品状态 （1.未到期、2.可使用、3.已冻结、4.已使用、5.已到期、6.已作废）
					userred.setRedealtime(new Date()); // 红包的入账时间
				    userred.setRefailtime(award.getEndtime()); // 到期时间
				    userred.setPurpose("该红包不能直接提现，只能交易和投标后当本金与所产生的收益后才能提现");
				    userred.setRemark("流标补偿"); // 备注
				    for(int i=0;i<failTCRecord.getAwardcopies();i++){
				    	userRedEnvelopeService.insert(userred);
				    }
			    }	    
			}else if(awardattribute.equals((short) 4) || awardattribute.equals((short) 5)){ //4.交易积分，5.系统积分（不可交易）
			 	//更新用户账户表中的交易积分
		    	if(awardattribute.equals((short) 4)){//交易积分
		    		ua.setTradePoints(new BigDecimal(Arith.add(ua.getTradePoints().doubleValue(), awardamount))); // 用户交易积分
		    		ua.setAvlTradePoints(new BigDecimal(Arith.add(ua.getAvlTradePoints().doubleValue(), awardamount))); // 可用交易积分
		    	}else if(awardattribute.equals((short) 5)){//系统积分（不可交易）
		    		ua.setBonuspoints(new BigDecimal(Arith.add(ua.getBonuspoints().doubleValue(), awardamount))); // 系统积分
		    	}
		    	int rows = userAccountService.updateUseraccount(ua);
		    	if(rows>0){
		    		// 用户积分表
					UserBonusPoints usbopo=new UserBonusPoints();
					usbopo.setBaseid(failTCRecord.getInvestorid()); // 用户Id
					usbopo.setUbpno(failTCRecord.getAwardno()); // 积分编号（对应奖品表编号）
					usbopo.setBptype((short) 8); // 积分来源类型(1.注册、2.累投、3.单标、4.首投、5.手动颁奖、6.自主颁奖、7.完善资料、8.流标补偿)
					usbopo.setBpUseType(awardattribute); // 积分用途类型
					usbopo.setBpstime(new Date()); // 积分发放时间
					usbopo.setBonuspoints(awardamount.longValue()); // 积分
					usbopo.setStatus((short) 3); // 奖品发放状态 （1.待审核、2.待处理、3.已领取、4.待确认、5.已经确认、6.发货中、7.领取失败、8.审核失败）
					usbopo.setBpdealtime(new Date()); // 积分处理时间（入账）
					usbopo.setRemark("流标补偿"); // 备注
					for(int i=0;i<failTCRecord.getAwardcopies();i++){
						userBonusPointsService.insert(usbopo);
					}
		    	}
			}else if(awardattribute.equals((short) 6)){ // 加息券
				//用户使用券表
				UserInterestRateCoupon usinra=new UserInterestRateCoupon();
				usinra.setBaseid(failTCRecord.getInvestorid()); // 用户Id
				usinra.setUircno(failTCRecord.getAwardno()); // 券编号（对应奖品表编号）
				usinra.setUirctype((short) 6); // 券的类型
				usinra.setIctype((short) 8); // 券来源类型(1.注册、2.累投、3.单标、4.首投、5.手动颁奖、6.自主颁奖、7.完善资料、8.流标补偿)
				usinra.setIctime(new Date()); // 发放时间
				usinra.setStatus((short) 3); // 奖品发放状态 （1.待审核、2.待处理、3.已领取、4.待确认、5.已经确认、6.发货中、7.领取失败、8.审核失败）
				usinra.setIsuse((short) 2); // 奖品状态  （1.未到期、2.可使用、3.已冻结、4.已使用、5.已到期、6.已作废）
				usinra.setIcrate(awardamount); // 券利率
				usinra.setIcdealtime(new Date()); // 券处理时间（入账）
			    usinra.setIcfailtime(award.getEndtime()); // 券失效时间
				usinra.setPurpose("投标增加利息"); // 说明
			    usinra.setRemark("流标补偿"); // 备注
				for(int i=0;i<failTCRecord.getAwardcopies();i++){
					userInterestRateCouponService.insert(usinra);
				}
			}else if(awardattribute>=31){
				// 用户站外奖品表
				UserOutsideAward usouaw=new UserOutsideAward();
				usouaw.setBaseid(failTCRecord.getInvestorid()); // 用户Id
				usouaw.setUoawardno(failTCRecord.getAwardno()); // 站外奖品编号
				usouaw.setUoawardname(failTCRecord.getAwardname()); // 站外奖品名称
				usouaw.setUoatype((short) 8);// 奖品来源类型(1.注册、2.累投、3.单标、4.首投、5.手动颁奖、6.自主颁奖、7.完善资料、8.流标补偿)
				usouaw.setUoatime(new Date()); // 奖品发放时间
				usouaw.setStatus((short) 4); // 奖品发放状态（1.待审核、2.待处理、3.已领取、4.待确认、5.已经确认、6.发货中、7.领取失败、8.审核失败）
				//生成发放编号
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String sendNo = "GJ"+sdf.format(new Date())+new Random().nextInt(99999);
				usouaw.setSendno(sendNo);
				usouaw.setRemark("流标补偿"); // 备注
				for(int i=0;i<failTCRecord.getAwardcopies();i++){
					userOutsideAwardService.insert(usouaw);
				}
				failTCRecord.setIsgrant((short) 2); // 待确认
			}
			if(awardattribute < 31){ // 修改标的流标补偿记录表发放状态
				if(failTCRecord.getPayouttype().equals((short) 2)){
					failTCRecord.setIsgrant((short) 1); // 已发放
				}else if(failTCRecord.getPayouttype().equals((short) 3)){
					failTCRecord.setIsgrant((short) 2); // 已发放奖品
				}
				failTCRecordService.updateByPrimaryKeySelective(failTCRecord);
			}
		}
	}
	
	/**
	 * 调用红包转账接口
	 * @Title: redBatchTransfer
	 * @Description: TODO(调用红包转账接口)
	 * @param tenderid
	 * @return
	 * @throws Exception
	 * @return String    返回类型
	 */
	private static String redBatchTransfer(BigDecimal tenderid) throws Exception{
		FailTCRecord r = new FailTCRecord();
		r.setTenderid(tenderid);
		List<FailTCRecord> recordList = failTCRecordService.findFailTCRecordList(r);
		String filePath = "";
		String batchNumber = StringUtil.stringLeftPading(6, failtcnumber.toString(), 1);;
		Calendar c = Calendar.getInstance();
		c.set(2016, 04, 10);
		String date = StringUtil.formatDate(c.getTime(), "yyyyMMdd");
		File file = null;
		PrintWriter out = null;
		for(FailTCRecord record : recordList){
			if(!record.getIsgrant().equals(1)){
				Double amount = record.getAwardamount();
				DecimalFormat df = new DecimalFormat("0000000000.00");
				//放在同一天的文件夹内
				String file_path = HSignUtil.FILEUPLOAD+File.separator+"trqt"+File.separator+StringUtil.formatDate(new Date(), "yyyyMMdd");
				
				//文件输出流  如果文件名是一个目录，会抛出异常
				//3004:徽商银行的银行编号
				file = new File(file_path);
				//如果文件夹不存在则创建    
				if  (!file .exists()  && !file .isDirectory()){       
				    file.mkdirs();
				}
				//文件路径
				filePath = file+File.separator+"3004-TRQT-800114-"+batchNumber+"-"+date;
				FileOutputStream fos = new FileOutputStream(filePath,true);
				//采用GBK编码
				out = new PrintWriter(new OutputStreamWriter(fos,"GBK"),true);
				out.print("3004");//银行编号  4
				out.print(batchNumber);//批次号  6
				out.print(date);//日期 8
				out.print("001");//业务类别 3
				UserFsAccountInfo uf = userFsAccountInfoService.findUserFsAccountInfoByBaseId(record.getInvestorid());
				out.print(uf.getUsrcustid());//转入方电子账号 19
				String[] a = df.format(amount).split("\\.");
				out.print(a[0]+a[1]);//入账金额  12
				out.print(156);//币种  人民币
				out.print(uf.getUsrname());//持卡人姓名  60
				out.print(StringUtil.stringLeftPading(40, "", 2));//自定义交易描述  40
				out.print(StringUtil.stringLeftPading(100, "", 2));//保留域 100
				out.print(StringUtil.stringLeftPading(100, "", 2));//第三方保留域 100
				out.print("\n");
			}
		}
		out.flush();
		out.close();
		
		//插入批量文件记录表
		BacthFileRecord bfr = new BacthFileRecord();
		bfr.setFilePath(filePath);//文件路径
		bfr.setSendFileName("3004-TRQT-800114-"+batchNumber+"-"+date);//上传文件名称
		bfr.setGetFileName("3004-TRQTRES-800114-"+batchNumber+"-"+date);//下载文件的名称
		bfr.setCoinstCode("800114");//平台编号
		bfr.setPName("干将P2P平台");//平台名称
		bfr.setIsDealResult((short)0);//是否已处理结果文件   0.否   1.是
		bfr.setFileType((short)2);//业务文件类型  1.开户  2.红包转账
		bfr.setRemark("流标补偿");
		
		//可以将红包转账的批量文件发给徽商银行了
		return new FileHttpUpload().testHttpUpload((file+File.separator).toString(), "3004-TRQT-800114-"+batchNumber+"-"+date, date, bacthFileRecordService, bfr);
	}
}
