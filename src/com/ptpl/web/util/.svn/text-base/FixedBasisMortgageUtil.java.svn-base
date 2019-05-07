package com.ptpl.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: FixedBasisMortgageUtil 
* @Package com.ptpl.web.util 
* @Description: TODO(等额本金 计算公式) 
* @author chenjiaming
* @date 2016年9月30日 上午10:13:06 
* @version V1.0
 */
public class FixedBasisMortgageUtil {
 	/**
	 * 
	* @Title: getMonthPrincipal 
	* @Description: TODO(等额本金算法 每月还款本金) 
	* @param @param totalPrincipal 借款总金额
	* @param @param totalMonth 借款总月数
	* @param @param scale 四舍五入位数
 	* @param @return  参数说明 
	* @return double    返回类型 
	* @author chenjiaming
	* @throws
	* 计算公式：每月还款本金 = {借款总金额 ÷ 借款总月份}
	 */
	public static double getMonthPrincipal(double totalPrincipal,int totalMonth,int scale){
		BigDecimal bigDecimal = new BigDecimal(Double.toString(totalPrincipal))
				.divide(new BigDecimal(String.valueOf(totalMonth)),10,BigDecimal.ROUND_DOWN);
 		return Arith.round(bigDecimal.doubleValue(), scale);
 	}
	/**
	 * 
	* @Title: getLastMonthPrincipal 
	* @Description: TODO(最后一期还款 本金) 
	* @param @param totalPrincipal
	* @param @param totalMonth
	* @param @param scale
	* @param @return  参数说明 
	* @return double    返回类型 
	* @author cjm
	* @throws
	 */
	public static double getLastMonthPrincipal(double totalPrincipal,int totalMonth,int scale){
 		double pr = 0.00;
  		for(int i = 1;i < totalMonth;i++){
 			pr += getMonthPrincipal(totalPrincipal, totalMonth,scale);
  		} 
 		pr = Arith.round(pr,scale);
 		double pe = Arith.round(totalPrincipal-pr,scale) ;
  		return  pe;
 	}
	
	/**
	 * 
	* @Title: MonthlyPayInterest 
	* @Description: TODO(等额本金算法  每月还款利息) 
	* @param @param totalPrincipal 借款总金额
	* @param @param totalMonth 借款总月数
	* @param @param yearRate 借款年化利率
	* @param @param scale 四舍五入位数
	* @param @return  参数说明 
	* @return Map<Integer,BigDecimal>    返回类型 
	* @author chenjiaming
	* @throws
	* 等额本金算法 每月还款利息计算公式：
	 */
	public static Map<Integer,BigDecimal> getMonthInterest(double totalPrincipal,int totalMonth,double  yearRate,int scale){
		Map<Integer,BigDecimal> mapInterest = new HashMap<Integer,BigDecimal>();
		double monthPrincipal = getMonthPrincipal(totalPrincipal, totalMonth, scale);//每月本金
		Map<Integer,BigDecimal> mapMonthPrincipalAndInterest = getMonthPrincipalAndInterest(totalPrincipal, totalMonth, yearRate, scale);//每月本息
		for(Map.Entry<Integer,BigDecimal> entry : mapMonthPrincipalAndInterest.entrySet()){
 			mapInterest.put(entry.getKey(), entry.getValue().subtract(new BigDecimal(Double.toString(monthPrincipal))));
		}
		return mapInterest;
	}
	
 	/**
	 * 
	* @Title: getMonthPrincipalAndInterest 
	* @Description: TODO(等额本金算法 每月还款本息) 
	* @param @param totalPrincipal 借款总金额
	* @param @param totalMonth 借款总月数
	* @param @param yearRate 年化利率
	* @param @param scale 四舍五入位数
	* @param @return  参数说明 
	* @return Map<Integer,BigDecimal>    返回类型 
	* @author chenjiaming
	* @throws
	* 计算公式：每月偿还本金=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
	 */
	public static Map<Integer,BigDecimal> getMonthPrincipalAndInterest(double totalPrincipal,int totalMonth,double  yearRate,int scale){
		double monthRate = yearRate / 12 ;//月利率
		double monthPrincipal = getMonthPrincipal(totalPrincipal, totalMonth, scale);//每月本金
		Map<Integer,BigDecimal> monthPrincipalAndInterest = new HashMap<Integer, BigDecimal>();
		BigDecimal bigDecimal1 = null ;
		for(int i = 1; i <= totalMonth; i++){
			double monthPrincipal2 = monthPrincipal + (totalPrincipal - monthPrincipal * (i-1)) * monthRate ;
			bigDecimal1 = new BigDecimal(Double.toString(monthPrincipal2)).setScale(10, BigDecimal.ROUND_DOWN);
			monthPrincipal2 = Arith.round(bigDecimal1.doubleValue(), scale);
			monthPrincipalAndInterest.put(i, new BigDecimal(Double.toString(monthPrincipal2)));
     	}
		return monthPrincipalAndInterest;
	}
	
	/**
	 * 
	* @Title: getInterestCount 
	* @Description: TODO(等额本金算法 总利息) 
	* @param @param totalPrincipal 借款总金额
	* @param @param totalMonth 借款总月数
	* @param @param yearRate 借款年化利率
	* @param @param scale  四舍五入位数
	* @param @return  参数说明 
	* @return double  返回类型 
	* @author chenjiaming
	* @throws
	 */
	public static double getInterestCount(double totalPrincipal,int totalMonth,double  yearRate,int scale){
		Map<Integer,BigDecimal> mapMonthInterest = getMonthInterest(totalPrincipal, totalMonth, yearRate, scale);
		BigDecimal interestCount = new BigDecimal("0");
		for(Map.Entry<Integer, BigDecimal> entry : mapMonthInterest.entrySet()){
			interestCount = interestCount.add(entry.getValue());
		}
		return interestCount.doubleValue();
	}
	
	public static void main(String[] args) {
		double totalPrincipal = 1000;
		int  totalMonth = 12;
		double yearRate = 12;
		int scale = 2;
		System.out.println("等额本金算法每月还款本金是："+getMonthPrincipal(totalPrincipal, totalMonth,scale));
		System.out.println("等额本金算法每月还款本息是："+getMonthPrincipalAndInterest(totalPrincipal, totalMonth, yearRate, scale));
		System.out.println("等额本金算法每月还款利息是："+getMonthInterest(totalPrincipal, totalMonth, yearRate, scale));
		System.out.println("等额本金算法总利息是："+getInterestCount(totalPrincipal, totalMonth, yearRate, scale));
 		System.out.println("最后一期还款本金"+getLastMonthPrincipal(totalPrincipal, totalMonth, scale));
 		
// 		double totalPrincipal1 = 60;
//		 
//		System.out.println("等额本金算法每月还款本金是："+getMonthPrincipal(totalPrincipal1, totalMonth,scale));
//		System.out.println("等额本金算法每月还款本息是："+getMonthPrincipalAndInterest(totalPrincipal1, totalMonth, yearRate, scale));
//		System.out.println("等额本金算法每月还款利息是："+getMonthInterest(totalPrincipal1, totalMonth, yearRate, scale));
//		System.out.println("等额本金算法总利息是："+getInterestCount(totalPrincipal1, totalMonth, yearRate, scale));
// 		System.out.println("最后一期还款本金"+getLastMonthPrincipal(totalPrincipal1, totalMonth, scale));
// 		
// 		double totalPrincipal2 = 40;
//		 
//		System.out.println("等额本金算法每月还款本金是："+getMonthPrincipal(totalPrincipal2, totalMonth,scale));
//		System.out.println("等额本金算法每月还款本息是："+getMonthPrincipalAndInterest(totalPrincipal2, totalMonth, yearRate, scale));
//		System.out.println("等额本金算法每月还款利息是："+getMonthInterest(totalPrincipal2, totalMonth, yearRate, scale));
//		System.out.println("等额本金算法总利息是："+getInterestCount(totalPrincipal2, totalMonth, yearRate, scale));
// 		System.out.println("最后一期还款本金"+getLastMonthPrincipal(totalPrincipal2, totalMonth, scale));
 	}
}
