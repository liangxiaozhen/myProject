package com.ptpl.web.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
 /**
 * 
* @ClassName: FixedPaymentMortgageUtil 
* @Package com.ptpl.web.util 
* @Description: TODO(等额本息 计算公式) 
* @author chenjiaming
* @date 2016年9月30日 上午10:11:45 
* @version V1.0
 */
public class FixedPaymentMortgageUtil {
	
   	/**
	 * 
	* @Title: getPrincipalAndInterest 
	* @Description: TODO(每月还款 总本息) 
	* @param @param totalPrincipal 贷款总本金
	* @param @param interestRate  月利率
	* @param @param totalMonth    总月数
	* @param @param scale    四舍五入位数
	* @param @return  参数说明 
	* @return double    返回类型 
	* @author chenjiaming
	* @throws
	* 计算公式：每月偿还本息={贷款本金×月利率×(1＋月利率)＾还款月数}÷{(1＋月利率)＾还款月数-1}
 	 */
	public static double getPrincipalAndInterest(double totalPrincipal,double  yearRate ,int totalMonth,int scale){
 		double monthRate = yearRate / 12;//月利率
 		double  interest =  Arith.div(Arith.mul(totalPrincipal, monthRate * Math.pow((1 + monthRate), totalMonth)),Math.pow((1 + monthRate), totalMonth)-1,scale);
  		return Arith.round(interest,scale);
 	}
	
 	/**
	 * 
	* @Title: getMonthlyPayInterest 
	* @Description: TODO(每月还款利息) 
	* @param @param totalPrincipal 贷款总本金
	* @param @param yearRate 月利率
	* @param @param totalMonth 总月数
	* @param @param scale 四舍五入位数
	* @param @return  参数说明 
	* @return Map<Integer,BigDecimal>    返回类型 
	* @author chenjiaming
	* @throws
	* 计算公式：每月偿还利息=贷款本金×月利率×{(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)}÷{(1+月利率)^还款月数-1}
	 */
	public static Map<Integer,BigDecimal> getMonthInterest(double totalPrincipal,double  yearRate ,int totalMonth,int scale){
        Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();  
        double monthRate = yearRate / 12;//月利率  
        BigDecimal monthInterest;
        for(int i = 1;i<totalMonth+1;i++){
        	BigDecimal v1 = new BigDecimal(Double.toString(totalPrincipal)).multiply(new BigDecimal(Double.toString(monthRate)));
        	BigDecimal v2 = new BigDecimal(Math.pow(1+monthRate, totalMonth)).subtract(new BigDecimal(Math.pow(1+monthRate, i-1)));
        	BigDecimal monthInterest1 = v1.multiply(v2).divide(new BigDecimal(Math.pow(1+monthRate, totalMonth)-1),10,BigDecimal.ROUND_DOWN);
         	double payInterest = Arith.round(monthInterest1.doubleValue(), scale);
            monthInterest = new BigDecimal(Double.toString(payInterest));
          	map.put(i, monthInterest);
        }
		return map;
	}
	
	/**
	 * 
	* @Title: getMonthPrincipal 
	* @Description: TODO(每月还款 本金) 
	* @param @param totalPrincipal 借款总本金
	* @param @param yearRate  年化利率
	* @param @param totalMonth 还款总月数
	* @param @param scale  四舍五入精确位数
	* @param @return  参数说明 
	* @return Map<Integer,BigDecimal>    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public static Map<Integer,BigDecimal> getMonthPrincipal(double totalPrincipal,double  yearRate ,int totalMonth,int scale){
		double monthRate = yearRate / 12;//月利率 每月偿还本息={贷款本金×月利率×(1＋月利率)＾还款月数}÷{(1＋月利率)＾还款月数-1}
		BigDecimal bigDecimal = new BigDecimal(Double.toString(totalPrincipal))
				.multiply(new BigDecimal(Double.toString(monthRate)))
				.multiply(new BigDecimal(Math.pow(1+monthRate, totalMonth)))
				.divide(new BigDecimal(Math.pow(1+monthRate, totalMonth)-1), 10, BigDecimal.ROUND_DOWN);
		BigDecimal bigDecimal2 = bigDecimal.divide(new BigDecimal("1"), scale, BigDecimal.ROUND_HALF_UP);
 		Map<Integer,BigDecimal> monthPrincipal = new HashMap<Integer, BigDecimal>();//每月还款本金
		Map<Integer,BigDecimal> monthInterest = getMonthInterest(totalPrincipal, yearRate, totalMonth, scale);//每月还款利息
		for(Map.Entry<Integer, BigDecimal> entry :monthInterest.entrySet()){
			monthPrincipal.put(entry.getKey(), bigDecimal2.subtract(entry.getValue()));
		}
		return monthPrincipal;
	}
	
	/**
	 * 
	* @Title: getLastMonthPrincipal 
	* @Description: TODO(最后一期 还款本金) 
	* @param @param totalPrincipal
	* @param @param yearRate
	* @param @param totalMonth
	* @param @param scale
	* @param @return  参数说明 
	* @return Map<Integer,BigDecimal>    返回类型 
	* @author cjm
	* @throws
	 */
	public static double getLastMonthPrincipal(double totalPrincipal,double  yearRate ,int totalMonth,int scale){
		Map<Integer,BigDecimal> monthPrincipal = getMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale);//每月还款本金
 		double countPrincipal  = 0.00;
		for(int i =1;i<totalMonth;i++){
			countPrincipal += monthPrincipal.get(i).doubleValue();
		}
		countPrincipal = Arith.round(countPrincipal,scale);
    	return Arith.round(totalPrincipal-countPrincipal,scale);
  	}
	/**
	 * 
	* @Title: getMonthlyPayInterestCount 
	* @Description: TODO(还款 总利息) 
	* @param @param totalPrincipal 借款本金
	* @param @param yearRate 年化利率
	* @param @param totalMonth 还款总月数
	* @param @param scale 四舍五入位数
	* @param @return  参数说明 
	* @return double    返回类型 
	* @author chenjiaming
	* @throws
	 */
	public static double getMonthlyPayInterestCount(double totalPrincipal,double  yearRate ,int totalMonth,int scale){
		BigDecimal monthlyPayInterestCount = new BigDecimal(0);
		Map<Integer,BigDecimal> mapInterest = getMonthInterest(totalPrincipal, yearRate, totalMonth, scale);
		for(Map.Entry<Integer, BigDecimal> entry : mapInterest.entrySet()){
			monthlyPayInterestCount = monthlyPayInterestCount.add(entry.getValue());
		}
		double ed = Arith.round(monthlyPayInterestCount.doubleValue(), scale);
		return ed;
	}
	
	
	public static void main(String[] args) {
		double totalPrincipal = 1300.00;//本金
		double yearRate = 0.12;//利率
		int totalMonth = 12;//总月数
		int scale = 10;
 		double totmethoy = getPrincipalAndInterest(totalPrincipal, yearRate, totalMonth,scale);
		System.out.println("等额本息算法的每月还款利息是："+getMonthInterest(totalPrincipal, yearRate, totalMonth, scale));
		System.out.println("等额本息算法的总利息是："+getMonthlyPayInterestCount(totalPrincipal, yearRate, totalMonth, scale));
		System.out.println("等额本息算法的每月还款本金是："+getMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale)); 
		System.out.println("等额本息算法每月还款总本息是==="+totmethoy);//172.54 1035.24
		System.out.println("等额本息算法总本息是getLastMonthPrincipal==="+getLastMonthPrincipal(totalPrincipal, yearRate, totalMonth, scale));//172.54 1035.24getLastMonthPrincipal
		System.out.println("\n");
//		Math.pow((1 + monthRate), totalMonth)
		Double monthRate = yearRate / 12;
		System.out.println(Math.pow((1 + monthRate), totalMonth));
//		double totalPrincipal1 = 40.00;//本金
//		double totmethoy1 = getPrincipalAndInterest(totalPrincipal1, yearRate, totalMonth,scale);
//		System.out.println("等额本息算法的每月还款利息是："+getMonthInterest(totalPrincipal1, yearRate, totalMonth, scale));
//		System.out.println("等额本息算法的总利息是："+getMonthlyPayInterestCount(totalPrincipal1, yearRate, totalMonth, scale));
//		System.out.println("等额本息算法的每月还款本金是："+getMonthPrincipal(totalPrincipal1, yearRate, totalMonth, scale)); 
//		System.out.println("等额本息算法每月还款总本息是==="+totmethoy1);//172.54 1035.24
//		System.out.println("等额本息算法总本息是getLastMonthPrincipal==="+getLastMonthPrincipal(totalPrincipal1, yearRate, totalMonth, scale));//172.54 1035.24getLastMonthPrincipal
//		System.out.println("\n");
//		double totalPrincipal2 = 60.00;//本金
//		double totmethoy2 = getPrincipalAndInterest(totalPrincipal2, yearRate, totalMonth,scale);
//		System.out.println("等额本息算法的每月还款利息是："+getMonthInterest(totalPrincipal2, yearRate, totalMonth, scale));
//		System.out.println("等额本息算法的总利息是："+getMonthlyPayInterestCount(totalPrincipal1, yearRate, totalMonth, scale));
//		System.out.println("等额本息算法的每月还款本金是："+getMonthPrincipal(totalPrincipal2, yearRate, totalMonth, scale)); 
//		System.out.println("等额本息算法每月还款总本息是==="+totmethoy2);//172.54 1035.24
//		System.out.println("等额本息算法总本息是getLastMonthPrincipal==="+getLastMonthPrincipal(totalPrincipal2, yearRate, totalMonth, scale));//172.54 1035.24getLastMonthPrincipal
	}
	
}
