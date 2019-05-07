package com.ptpl.web.util;

import java.math.BigDecimal;

/**
 * 
* @ClassName: OneTimeServicingUtil 
* @Package com.ptpl.web.util 
* @Description: TODO(一次性还本付息、先息后本 工具类) 
* @author chenjiaming
* @date 2016年10月3日 下午3:24:35 
* @version V1.0
 */
public class OneTimeServicingUtil {
	
	/**
	 * 
	* @Title: getOneTimeInterestCount 
	* @Description: TODO(一次性还本付息 总利息) 
	* @param @param totalPrincipal
	* @param @param totalMonth
	* @param @param yearRate
	* @param @param scale
	* @param @return  参数说明 
	* @return double    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public static double getOneTimeInterestCount(double totalPrincipal,int totalMonth,double  yearRate,int scale){
		double monthRate = yearRate / 12;//月利率
		BigDecimal interestCount = new BigDecimal(Double.toString(totalPrincipal))
				.multiply(new BigDecimal(Double.toString(monthRate)))
				.multiply(new BigDecimal(totalMonth));
		interestCount = interestCount.setScale(10, BigDecimal.ROUND_DOWN);
		interestCount = interestCount.divide(new BigDecimal("1"), scale, BigDecimal.ROUND_HALF_UP);
		return interestCount.doubleValue();
	}

	/**
	 * 
	* @Title: getMonthInterest 
	* @Description: TODO(先息后本 每月利息) 
	* @param @param totalPrincipal
	* @param @param totalMonth
	* @param @param yearRate
	* @param @param scale
	* @param @return  参数说明 
	* @return double    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public static double getMonthInterest(double totalPrincipal,int totalMonth,double  yearRate,int scale){
		double monthRate = yearRate / 12;//月利率
		BigDecimal bigDecimal = new BigDecimal(Double.toString(totalPrincipal))
				.multiply(new BigDecimal(Double.toString(monthRate)));
		bigDecimal = bigDecimal.setScale(10, BigDecimal.ROUND_DOWN);
		bigDecimal = bigDecimal.divide(new BigDecimal("1"), scale, BigDecimal.ROUND_HALF_UP);
		return bigDecimal.doubleValue();
	}
	
	/**
	 * 
	* @Title: getInterestCount 
	* @Description: TODO(先息后本 总利息) 
	* @param @param totalPrincipal
	* @param @param totalMonth
	* @param @param yearRate
	* @param @param scale
	* @param @return  参数说明 
	* @return double    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public static double getInterestCount(double totalPrincipal,int totalMonth,double  yearRate,int scale){
		double monthInterest = getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);
		double InterestCount = monthInterest  * totalMonth ;
		return Arith.round(InterestCount, scale);
	}
	
	public static void main(String[] args) {
		double totalPrincipal = 100000;
		int totalMonth = 36;
		double  yearRate = 0.12;
		int scale = 2;
		System.out.println(getOneTimeInterestCount(totalPrincipal, totalMonth, yearRate, scale));
	}
}
