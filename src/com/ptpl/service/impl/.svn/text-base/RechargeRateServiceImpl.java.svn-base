package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.constant.RechargeRate_Constant;
import com.ptpl.exception.CustomException;
import com.ptpl.exception.MinfeeAndMaxfeeException;
import com.ptpl.exception.SectionMoneyException;
import com.ptpl.mapper.RechargeQuotaFeeMapper;
import com.ptpl.mapper.RechargeRateMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.RechargeQuotaFee;
import com.ptpl.model.RechargeRate;
import com.ptpl.service.RechargeRateServiceI;
import com.ptpl.web.util.Arith;
import com.ptpl.web.util.StringUtil;


public class RechargeRateServiceImpl implements RechargeRateServiceI{
	@Autowired
	private RechargeRateMapper rechargeRateMapper;
	@Autowired
	private UserGradeMapper userGradeMapper;
	@Autowired
	private RechargeQuotaFeeMapper rechargeQuotaFeeMapper;
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public int addRechargeRate(RechargeRate rechargeRate) throws SectionMoneyException, MinfeeAndMaxfeeException{
				moneyJudge(rechargeRate);
				timeConversion(rechargeRate);
				return rechargeRateMapper.insert(rechargeRate);
	}

	@Override
	public RechargeRate getById(BigDecimal id) throws Exception {
		RechargeRate rechargeRate = rechargeRateMapper.getById(id);
		/*cleared(rechargeRate);
		parsing(rechargeRate);
		timeConversion(rechargeRate);*/
		return rechargeRate;
	}

	@Override
	public List<RechargeRate> getAll2(RechargeRate recrod) throws Exception {
		List<RechargeRate> list = rechargeRateMapper.getAll2(recrod);
		for (RechargeRate rechargeRate : list) {
			rechargeRate.setAddtimeStr(sf.format(rechargeRate.getAddtime()));
			//cleared(rechargeRate);
			parsing(rechargeRate);
		}
		return list;
	}
	public void cleared(RechargeRate rechargeRate) throws Exception {
		// 获取会员等级占位符
		String[] strArr = StringUtil.getPlaceholderArr(rechargeRate.getUgrade());
		String ugradeStr = "";

		int size = userGradeMapper.getUserGradeAll(null).size();
		int length = strArr.length;

		System.out.println("============length=" + length + "========size=" + size);
		if (strArr.length == size) {
			ugradeStr = "全部等级";
		} else {

			for (String str : strArr) {
				// 根据会员等级 获取会员名称
				int ugrade = Integer.parseInt(str);
				ugradeStr += "("+userGradeMapper.getCodeByUserGradeName(new BigDecimal(ugrade)) + ")";
			}
		}
		// 会员等级
		rechargeRate.setUgradeStr(ugradeStr);
		parsing(rechargeRate);
		if(rechargeRate.getMaxmoney()<=rechargeRate.getMinmoney()){
			throw new Exception("分段金额异常");
		}
		if(rechargeRate.getChargetype()==1){
			rechargeRate.setMaxfee(0.0);
			rechargeRate.setMinfee(0.0);
			rechargeRate.setCharrate(0.0);		
		}else{
			rechargeRate.setQuota(0.0);
		}
	}

	@Override
	public int update(RechargeRate rechargeRate) {
		timeConversion(rechargeRate);
		return rechargeRateMapper.update(rechargeRate);
	}



	@Override
	public long delete(BigDecimal id) {
		return rechargeRateMapper.delete(id);
	}

	@Override
	public int getByTerm(RechargeRate record) {
		
		return rechargeRateMapper.getByTerm(record);
	}

	@Override
	public RechargeRate getMaxMoney(RechargeRate record) {
		
		return rechargeRateMapper.getByMaxMoney(record);
	}

	@Override
	public List<RechargeRate> getTremAll(RechargeRate rechargeRate) {
		return rechargeRateMapper.getTremAll(rechargeRate);
	}

	@Override
	public int getByTermMaxMoney(String ugrade,short chargetype,short rechartype,String bankcode,double maxmoney) {
		
		return rechargeRateMapper.getByTermMaxMoney(ugrade,chargetype, rechartype, bankcode,maxmoney);
	}

    //条件封装
	public void parsing(RechargeRate rechargeRate){
		// 充值方式
		rechargeRate.setRechartypeStr(RechargeRate_Constant.RECHARTYPE_MAP.get(rechargeRate.getRechartype()));
		//收费类型
		rechargeRate.setChargetypeStr(RechargeRate_Constant.RECHARMODE_MAP.get(rechargeRate.getChargetype()));
		// 是否审核]
		//rechargeRate.setIsauditStr(RechargeRate_Constant.ISAUDIT_MAP.get(rechargeRate.getIsaudit()));
		// 是否启用
		//rechargeRate.setIsuseStr(RechargeRate_Constant.ISUSE_MAP.get(rechargeRate.getIsuse()));
		//充值银行
		//rechargeRate.setBankcodeStr(RechargeRate_Constant.BANKCODE_MAP.get(rechargeRate.getBankcode()));
		//充值模板
		rechargeRate.setIstempletStr(RechargeRate_Constant.ISTEMPLET_MAP.get(rechargeRate.getIstemplet()));
	}
	//金额判断
	public void moneyJudge(RechargeRate rechargeRate) throws SectionMoneyException, MinfeeAndMaxfeeException{
	
		//收费类型
		short chargetype = rechargeRate.getChargetype();
		if(chargetype==2){
			//判断条件
			if (rechargeRate.getMaxmoney() <= rechargeRate.getMinmoney()) {
				throw new SectionMoneyException("分段金额异常");
			}

		}
	}
	//时间转换
	public void timeConversion(RechargeRate rechargeRate){
		if (rechargeRate.getAddtime() != null && rechargeRate.getAudittime() != null) {
			rechargeRate.setAddtimeStr(sf.format(rechargeRate.getAddtime()));
		}
	}

	@Override
	public List<RechargeRate> getIsAudit() {
		
		return rechargeRateMapper.getIsAudit();
	}

	@Override
	public List<RechargeRate> getUgrade(String ugrade) {
		return rechargeRateMapper.getUgrade(ugrade);
	}

	@Override
	public RechargeRate getUgradeAndRecharTypeAndIsuseForRechargeRate(RechargeRate rechargeRate) {
		return rechargeRateMapper.getUgradeAndRecharTypeAndIsuseForRechargeRate(rechargeRate);
	}

	@Override
	public List<RechargeRate> getAll() {
		List<RechargeRate> rechargeRateList = rechargeRateMapper.getAll();
		for (RechargeRate rechargeRate : rechargeRateList) {
			if(rechargeRate.getCharrate()!=null){
				rechargeRate.setCharrate(Arith.mul(rechargeRate.getCharrate(), 100));
			}
			if(rechargeRate.getProxypayratio()!=null){
				rechargeRate.setProxypayratio(Arith.mul(rechargeRate.getProxypayratio(), 100));;
			}
			List<RechargeQuotaFee> rqList = rechargeQuotaFeeMapper.selectByrrid(rechargeRate.getId());
			rechargeRate.setRechargeQuotaFeeList(rqList);
			System.out.println(rechargeRate);
			
		}
		return rechargeRateList;
	}

	@Override
	public RechargeRate selectRechartypeByRechargeRate(short rechartype) {
		return rechargeRateMapper.selectRechartypeByRechargeRate(rechartype);
	}
	public RechargeRate getRechargeRate(String sum){
		/**获取充值费率表*/
		RechargeRate rechargeRate   = rechargeRateMapper.selectRechartypeByRechargeRate(new Short(sum));
		return rechargeRate;
	}
	

}
