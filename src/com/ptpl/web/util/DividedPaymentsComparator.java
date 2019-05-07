package com.ptpl.web.util;

import java.util.Comparator;

import com.ptpl.model.DividedPayments;
/**
 * 
* @ClassName: DividedPaymentsComparator 
* @Package com.ptpl.web.util 
* @Description: TODO(标的还款计划排序) 
* @author chenjiaming
* @date 2016年9月29日 下午4:15:35 
* @version V1.0
 */
public class DividedPaymentsComparator implements Comparator<DividedPayments> {

	@Override
	public int compare(DividedPayments o1, DividedPayments o2) {
		if(o1.getRepayday().getTime() < o2.getRepayday().getTime()){
			return -1;
		}else{
			return 1;
		}
 	}
 }
