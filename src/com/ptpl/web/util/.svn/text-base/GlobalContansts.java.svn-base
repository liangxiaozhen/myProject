package com.ptpl.web.util;

import com.ptpl.web.Iconstant.Iconstant;

public class GlobalContansts {
	
	public static final String configFilePrefix(String prefix)
	{
		String os = System.getProperty("os.name");
		if (os != null && os.toLowerCase().startsWith("win"))
		{
			return prefix;
		}
		else
		{
			return "";
		}
	}
	
	//身份认证接口标识码(国政通)
	public static final String nciicmerno = "sqwlsqwl50459";
	//身份认证接口授权文件(国政通)
	public static final String nciiclicense = GlobalContansts.configFilePrefix("D:") + "/web/授权文件";
	
	//身份认证接口(悦园数据)
	public static final String funParkId = "8868785";
	public static final String funParkAppkey = "25b82df4ef27f86e1b94dc82af216ccc";
	public static final String funParkPostUrl = "http://api.id98.cn/api/idcard";
	
	//身份认证接口(通付盾)
	public static final String payegisId = "688568";
	public static final String payegisAppkey = "7943224b-bed5-4000-9f01-2929553fa04b";
	public static final String payegisPostUrl = "https://pws.payegis.com.cn/id_creditcardapi/idcard/simple_query";

}
