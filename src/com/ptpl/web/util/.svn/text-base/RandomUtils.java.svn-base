package com.ptpl.web.util;

import java.util.Random;

/**
 * 工具类用于生成验证码
 * @author sheng
 *
 */
public class RandomUtils 
{

	/**
	 * 产生指定范围内的随机数(仅限非负数)
	 * 
	 * @param min
	 *            最小范围
	 * @param containMin
	 *            是否包括这个最小范围(true:包括;false:不包括)
	 * @param max
	 *            最大范围
	 * @param containMax
	 *            是否包括这个最大范围(true:包括;false:不包括)
	 * @return 正常情况:>=0 异常情况:-1
	 */
	public static int threadLocalRandom(int min, boolean containMin, int max, boolean containMax)
	{
		if(min < 0 || max < 0)
		{
			return -1;
		}
		if(min > max)
		{
			max = max ^ min;
			min = max ^ min;
			max = max ^ min;
		}
		Random random = new Random();
		if(containMin == true && containMax == true)
		{
			//产生min-max之间的随机数(包括min和max,即[min, max])
			return random.nextInt(max - min + 1) + min;
		}
		else if(containMin == true && containMax == false)
		{
			//产生min-max之间的随机数(包括min不包括max,即[min, max])
			return random.nextInt(max - min) + min;
		}
		else if(containMin == false && containMax == false)
		{
			//产生min-max之间的随机数(不包括min也不包括max,即[min, max])
			max = max - 1;
			return random.nextInt(max - min) + min + 1;
		}
		else
		{
			//产生min-max之间的随机数(不包括min包括max,即[min, max])
			min = min + 1;
			return random.nextInt(max - min + 1) + min;
		}
	}
}
