package com.ptpl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ptpl.constant.RechargeRstr_Constant;
import com.ptpl.exception.MoneyException;
import com.ptpl.mapper.RechargeRstrMapper;
import com.ptpl.mapper.RemoveNameMapper;
import com.ptpl.mapper.UserGradeMapper;
import com.ptpl.model.RechargeRate;
import com.ptpl.model.RechargeRstr;
import com.ptpl.model.RemoveName;
import com.ptpl.service.RechargeRstrServiceI;
import com.ptpl.web.util.StringUtil;

public class RechargeRstrServiceImpl implements RechargeRstrServiceI {
	@Autowired
	RechargeRstrMapper rechargeRstrMapper;
	@Autowired
	private UserGradeMapper userGradeMapper;
	@Autowired
	private RemoveNameMapper removeNameMapper;
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public RechargeRstr getByIdToRechargeRstr(BigDecimal id) {
		RechargeRstr rechargeRstr = rechargeRstrMapper.selectByPrimaryKey(id);
		if (rechargeRstr.getAddtime() != null && rechargeRstr.getAudittime() != null) {
			rechargeRstr.setAddtimeStr(sf.format(rechargeRstr.getAddtime()));
			rechargeRstr.setAudittimeStr(sf.format(rechargeRstr.getAudittime()));
		}
		cleared(rechargeRstr);
		parsing(rechargeRstr);
		return rechargeRstr;
	}

	@Override
	public List<RechargeRstr> getAll(RechargeRstr record) {
		List<RechargeRstr> list = rechargeRstrMapper.getAll(record);
		for (RechargeRstr rechargeRstr : list) {
			rechargeRstr.setAddtimeStr(sf.format(rechargeRstr.getAddtime()));
			rechargeRstr.setAudittimeStr(sf.format(rechargeRstr.getAudittime()));
			cleared(rechargeRstr);
		}
		return list;
	}

	public void cleared(RechargeRstr rechargeRstr) {
		// 获取会员等级占位符
		String[] strArr = StringUtil.getPlaceholderArr(rechargeRstr.getUgrade());
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
		rechargeRstr.setUgradeStr(ugradeStr);
		parsing(rechargeRstr);
	}

	@Override
	public int deleteRechargeRstr(BigDecimal id) {
		return rechargeRstrMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void saveRechargeRstr(RechargeRstr rechargeRstr) throws MoneyException {
		timeConversion(rechargeRstr);
		/*moneyJudge(rechargeRstr);*/
		rechargeRstrMapper.insertSelective(rechargeRstr);

	}

	@Override
	public int updateRechargeRstr(RechargeRstr rechargeRstr) throws MoneyException {
		parsing(rechargeRstr);
		timeConversion(rechargeRstr);
		/*moneyJudge(rechargeRstr);*/
		return rechargeRstrMapper.updateByPrimaryKeySelective(rechargeRstr);
	}

	@Override
	public RechargeRstr getByUgradeToRechargeRstr(String ugradeString) {
		return rechargeRstrMapper.selectByUgrade(ugradeString);
	}
	//条件封装
	public void parsing(RechargeRstr rechargeRstr){
			/*	// 排除人名单
				rechargeRstr.setRemovenamenoStr(removeNameMapper.selectNameForNameNo(rechargeRstr.getRemovenameno()));*/
				// 充值方式
				rechargeRstr.setRechartypeStr(RechargeRstr_Constant.RECHARTYPE_MAP.get(rechargeRstr.getRechartype()));
				// 手续费类型
				rechargeRstr.setRechartypeStr(RechargeRstr_Constant.FEETYPE_MAP.get(rechargeRstr.getFeetype()));
				// 是否启用
				rechargeRstr.setIsuseStr(RechargeRstr_Constant.ISUSE_MAP.get(rechargeRstr.getIsuse()));
				// 是否审核 
				rechargeRstr.setIsauditStr(RechargeRstr_Constant.ISAUDIT_MAP.get(rechargeRstr.getIsaudit()));
				//是否作为模板
				rechargeRstr.setIstempletStr(RechargeRstr_Constant.ISTEMPLET_MAP.get(rechargeRstr.getIstemplet()));
	}
	//时间转换
	public void timeConversion(RechargeRstr rechargeRstr){
		if (rechargeRstr.getAddtime() != null && rechargeRstr.getAudittime() != null) {
			rechargeRstr.setAddtimeStr(sf.format(rechargeRstr.getAddtime()));
			rechargeRstr.setAudittimeStr(sf.format(rechargeRstr.getAudittime()));
		}
	}
	public void moneyJudge(RechargeRstr rechargeRstr) throws MoneyException{
		  Double daymoneyrest = rechargeRstr.getDaymoneyrest();
		  Double hightestmoney = rechargeRstr.getHightestmoney();
		  Double  lowestmoney = rechargeRstr.getLowestmoney();
		 if(daymoneyrest<= hightestmoney|| daymoneyrest<=lowestmoney){
			 throw new MoneyException("日充值金额错误,请重新输入");
		 }
		 if(hightestmoney<lowestmoney){
			 throw new MoneyException("单笔金额错误,请重新输入");
		 }
	}

	@Override
	public List<RechargeRstr> getIsAudit() {
		
		return rechargeRstrMapper.getIsAudit();
	}

	@Override
	public RechargeRstr getUgradeAndRecharTypeAndIsuseForRechargeRstr(RechargeRstr rechargeRstr) {
		return rechargeRstrMapper.getUgradeAndRecharTypeAndIsuseForRechargeRstr(rechargeRstr);
	}

}
