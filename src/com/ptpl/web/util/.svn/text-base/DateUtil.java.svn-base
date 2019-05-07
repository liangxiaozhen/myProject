package com.ptpl.web.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.util.Assert;

import com.ptpl.model.TenderItem;

/**
 * Title: 日期时间 Description: 工具类
 * 
 * @author huanglq
 * @version 1.0
 */
public class DateUtil {

	/** 类名 */
	private static String ClassName = "com.huanglq.util.DateTime";

	/** 本地化 */
	private static Locale locale = Locale.SIMPLIFIED_CHINESE;

	/** 缺省的DateFormat对象，可以将一个java.util.Date格式化成 yyyy-mm-dd 输出 */
	private static DateFormat dateDF = DateFormat
			.getDateInstance(DateFormat.MEDIUM);

	/** 缺省的DateFormat对象，可以将一个java.util.Date格式化成 HH:SS:MM 输出 */
	private static DateFormat timeDF = DateFormat
			.getTimeInstance(DateFormat.MEDIUM);

	/** 缺省的DateFormat对象，可以将一个java.util.Date格式化成 yyyy-mm-dd HH:SS:MM 输出 */
	private static DateFormat datetimeDF = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM, DateFormat.MEDIUM);

	/**
	 * 私有构造函数，表示不可实例化
	 */
	public DateUtil() {
	}

	/**
	 * 返回一个当前的时间，并按格式转换为字符串 例：17:27:03
	 * 
	 * @return String
	 */
	public static String getTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		return timeDF.format(dNow);
	}

	/**
	 * 返回一个当前日期，并按格式转换为字符串 例：2004-4-30
	 * 
	 * @return String
	 */
	public static String getDate() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		return dateDF.format(dNow);
	}

	/**
	 * 返回一个当前日期和时间，并按格式转换为字符串 例：2004-4-30 17:27:03
	 * 
	 * @return String
	 */
	public static String getDateTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		return datetimeDF.format(dNow);
	}

	/**
	 * 返回当前年的年号
	 * 
	 * @return int
	 */
	public static int getYear() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(Calendar.YEAR);
	}

	/**
	 * 返回本月月号：从 0 开始
	 * 
	 * @return int
	 */
	public static int getMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(Calendar.MONTH);
	}

	/**
	 * 返回今天是本月的第几天
	 * 
	 * @return int 从1开始
	 */
	public static int getToDayOfMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回一格式化的日期
	 * 
	 * @param date
	 *            java.util.Date
	 * @return String yyyy-mm-dd 格式
	 */
	public static String formatDate(java.util.Date date) {
		return dateDF.format(date);
	}

	/**
	 * 返回一格式化的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(long date) {
		return formatDate(new java.util.Date(date));
	}

	/**
	 * 返回一格式化的时间
	 * 
	 * @param date
	 *            Date
	 * @return String hh:ss:mm 格式
	 */
	public static String formatTime(java.util.Date date) {
		return timeDF.format(date);
	}

	/**
	 * 返回一格式化的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTime(long date) {
		return formatTime(new java.util.Date(date));
	}

	/**
	 * 返回一格式化的日期时间
	 * 
	 * @param date
	 *            Date
	 * @return String yyyy-mm-dd hh:ss:mm 格式
	 */
	public static String formatDateTime(java.util.Date date) {
		return datetimeDF.format(date);
	}

	/**
	 * 返回一格式化的日期时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(long date) {
		return formatDateTime(new java.util.Date(date));
	}

	/**
	 * 将字串转成日期和时间，字串格式: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param string
	 *            String
	 * @return Date
	 */
	public static java.util.Date toDateTime(String string) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return formatter.parse(string);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将字串转成日期和时间，字串格式: yyyy/MM/dd HH:mm:ss
	 * 
	 * @param string
	 *            String
	 * @return Date
	 */
	public static java.util.Date toDateTimeF(String string) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return formatter.parse(string);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将字串转成日期，字串格式: yyyy/MM/dd
	 * 
	 * @param string
	 *            String
	 * @return Date
	 */
	public static java.util.Date toDate(String string) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.parse(string);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将字串转成日期，字串格式: yyyy-MM-dd
	 * 
	 * @param tstringring
	 *            String
	 * @return yyyyMMdd
	 */
	public static String toFormatyy(String time) {
		String yy = time.replaceAll("-", "");
		return yy;
	}

	/**
	 * 取值：某日期的年号
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int getYear(String date) {
		java.util.Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取值：某日期的月号
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int getMonth(String date) {
		java.util.Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 取值：某日期的日号
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return 从1开始
	 */
	public static int getDayOfMonth(String date) {
		java.util.Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算两个日期的年数差
	 * 
	 * @param one
	 *            格式: yyyy/MM/dd
	 * @param two
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int compareYear(String one, String two) {
		return getYear(one) - getYear(two);
	}

	/**
	 * 计算岁数
	 * 
	 * @param date
	 *            格式: yyyy/MM/dd
	 * @return
	 */
	public static int compareYear(String date) {
		return getYear() - getYear(date);
	}

	public static String returnGreenwich(Date date) {
		TimeZone tz = TimeZone.getTimeZone("Etc/Greenwich");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");
		sdf.setTimeZone(tz);
		return sdf.format(date);
	}
	
	/**
	 * @see 判断是否为工作时间
	 */
	public boolean checkWorkDay(){
		Date bankReturnTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sBankReturnTime = sdf.format(bankReturnTime);
		
		
		
		boolean flag = false;
		Calendar cal = Calendar.getInstance();
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    if (w < 0) w = 0;
		if(1<=w && w<=5){
			if(cal.get(Calendar.HOUR_OF_DAY) >= 9 && cal.get(Calendar.HOUR_OF_DAY) <=15){
				flag = true;
			}
		}
//		else if(sBankReturnTime.equals("2015-09-03")){
//			if(cal.get(Calendar.HOUR_OF_DAY) >= 9 && cal.get(Calendar.HOUR_OF_DAY) <=15){
//				flag = true;
//			}
//		}
		
		if(cal.get(Calendar.MONTH) == 9 && 1 <= cal.get(Calendar.DATE) && cal.get(Calendar.DATE) <=7){
			flag = false;
		}
		//过滤阅兵式假期
		if(sBankReturnTime.equals("2015-09-03") || sBankReturnTime.equals("2015-09-04") || sBankReturnTime.equals("2015-09-05")){
			flag = false;
		}
		return flag;
	}
	
	 /**
     * String(yyyy-MM-dd HH:mm:ss) 转 Date
     * 
     * @param time
     * @return
     * @throws ParseException
     */
    // String date = "2010/05/04 12:34:23";
    public static Date StringToDate(String time) throws ParseException {
         
        Date date = new Date();
        // 注意format的格式要与日期String的格式相匹配
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = dateFormat.parse(time);
            System.out.println(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return date;
    }
 
    /**
     * Date转为String(yyyy-MM-dd HH:mm:ss)
     * 
     * @param time
     * @return
     */
    public static String DateToString(Date time) {
        String dateStr = "";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");
        try {
            dateStr = dateFormat.format(time);
            System.out.println(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }
    /**
     * String(yyyy-MM-dd HH:mm:ss)转10位时间戳
     * @param time
     * @return
     */
    public static Integer StringToTimestamp(String time){
    
        int times = 0;
        try {  
            times = (int) ((Timestamp.valueOf(time).getTime())/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        if(times==0){
            System.out.println("String转10位时间戳失败");
        }
        return times; 
         
    }
    /**
     * 10位int型的时间戳转换为String(yyyy-MM-dd HH:mm:ss)
     * @param time
     * @return
     */
    public static String timestampToString(Integer time){
        //int转long时，先进行转型再进行计算，否则会是计算结束后在转型
        long temp = (long)time*1000;
        Timestamp ts = new Timestamp(temp);  
        String tsStr = "";  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        try {  
            //方法一  
            tsStr = dateFormat.format(ts);  
            System.out.println(tsStr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return tsStr;  
    }
    /**
     * 10位时间戳转Date
     * @param time
     * @return
     */
    public static Date TimestampToDate(Integer time){
        long temp = (long)time*1000;
        Timestamp ts = new Timestamp(temp);  
        Date date = new Date();  
        try {  
            date = ts;  
            //System.out.println(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return date;
    }
    /**
     * Date类型转换为10位时间戳
     * @param time
     * @return
     */
    public static Integer DateToTimestamp(Date time){
        Timestamp ts = new Timestamp(time.getTime());
         
        return (int) ((ts.getTime())/1000);
    }
    /**
     * 
    * @Title: addMonth 
    * @Description: TODO(月份添加 1) 
    * @param @param date
    * @param @param n
    * @param @return  参数说明 
    * @return String    返回类型 
    * @author cjm
    * @throws
     */
    public static String addMonth(String date,int n){
    	try {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     		Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateFormat.parse(date));
			calendar.add(Calendar.MONTH, n);
			return  dateFormat.format(calendar.getTime());
 		} catch (Exception e) {
 			e.printStackTrace();
 			return null;
		}
    }
    /**
     * 
    * @Title: addMonth 
    * @Description: TODO(月份添加 1) 
    * @param @param date
    * @param @param n
    * @param @return  参数说明 
    * @return Date    返回类型 
    * @author cjm
    * @throws
     */
    public static Date addMonth(Date date,int n){
    	try {
     		Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, n);
			return  calendar.getTime();
 		} catch (Exception e) {
 			e.printStackTrace();
 			return null;
		}
    }
    /**
     * 
    * @Title: getDateDifference 
    * @Description: TODO(获取两个日期的时间差) 
    * @param @param startDate
    * @param @param endDate
    * @param @return  参数说明 
    * @return long    返回类型 
    * @author cjm
    * @throws
     */
    public static long getDateDifference(Date startDate, Date endDate) {
    	  long dayDifference = 0;
    	  if (startDate == null) {
    		  throw new IllegalArgumentException("startDate 不能为null");
    	  } 
    	  
    	  if (endDate == null) {
    		  throw new IllegalArgumentException("endDate 不能为null");
    	  }
    	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	  startDate = StringUtil.getDateByString(dateFormat.format(startDate), "yyyy-MM-dd");
    	  endDate = StringUtil.getDateByString(dateFormat.format(endDate), "yyyy-MM-dd");
     	  try {
   	    	   dayDifference = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
    	  } catch (Exception e) {
    		  return -1;
    	  }
    	  	return dayDifference;
   }
    
    /**
     * 
    * @Title: getOverdueNumDay 
    * @Description: TODO(获取当前日期和标的逾期宽限日期相加后的时间) 
    * @param @param tenderItem 标的对象
    * @param @param date   当前日期
    * @param @return  参数说明 
    * @return Date    返回类型 
    * @author cjm
    * @throws
     */
    public static Date getOverdueNumDay(TenderItem tenderItem,Date date){
    	Integer gracePeriod = tenderItem.getGraceperiod();//标的逾期宽限日期
     	Assert.notNull(gracePeriod, "'标的逾期宽限日不能为null'");
    	Assert.notNull(date, "'当前日期不能为null'");
     	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, gracePeriod);
     	return calendar.getTime();
    }
    
    /**
     * 
    * @Title: getOverdueNumDay 
    * @Description: TODO(获取当前日期和标的还款宽限期 （还款日往前）相加后的时间) 
    * @param @param tenderItem 标的对象
    * @param @param date   当前日期
    * @param @return  参数说明 
    * @return Date    返回类型 
    * @author cjm
    * @throws
     */
    public static Date getAheadperiodNumDay(TenderItem tenderItem,Date date){
    	Short aheadperiod = tenderItem.getAheadperiod();//标的逾期宽限日期
     	Assert.notNull(aheadperiod, "'标的还款宽限期 （还款日往前）不能为null'");
    	Assert.notNull(date, "'当前日期不能为null'");
     	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
     	calendar.add(Calendar.DATE, - aheadperiod);
     	return calendar.getTime();
    }
    
    
    public static void main(String[] args) {
//    	int time = DateUtil.DateToTimestamp(new Date());
//    	String s = String.valueOf(time);
//    	System.out.println(s);
    	Date date = new Date();
    	Date date1 = StringUtil.getDateByString("2017-07-23 12:12:12", "yyyy-MM-dd HH:ss:mm");
    	Date date2 = StringUtil.getDateByString("2017-07-30 12:12:12", "yyyy-MM-dd HH:ss:mm");
    	System.out.println("==========="+(date1.getTime()-date2.getTime()));
//    	System.out.println(getDateDifference(date2, date1));
    	TenderItem tenderItem = new TenderItem();
    	tenderItem.setGraceperiod(5);
    	System.out.println(StringUtil.formatDate(getOverdueNumDay(tenderItem, date1), "yyyy-MM-dd HH:ss:mm"));
    	System.out.println();
//    	for(int i = 1;i<=12;i++){
//    		
//    		System.out.println(i+"==="+StringUtil.formatDate(addMonth(date2, i), "yyyy-MM-dd")+addMonth(date2, i)); 
//    	}
    }

}
