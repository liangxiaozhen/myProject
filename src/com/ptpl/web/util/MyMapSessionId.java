package com.ptpl.web.util;

import java.util.HashMap;

/**
 * 
 * @author Administrator
 * @classname: com.ptpl.core.interceptor
 * @author : xielinsen
 * @Description :用于用户登入时保存sessionId , 作为后期对同一帐号是否多地登入作工具
 * @date 2017/4/12
 */
public class MyMapSessionId {

	private static MyMapSessionId m;
	private HashMap<String,String> mymap;

	private MyMapSessionId() {
		mymap = new HashMap<String,String>();
	}

	public static synchronized MyMapSessionId getInstance() {
		if (m == null) {
			m = new MyMapSessionId();
		}
		return m;
	}

	public synchronized void AddSession(String loginname, String sessionID) {
		mymap.put(loginname, sessionID);
	}

	public String getSessionId(String loginname) {
		String SessionId = (String) mymap.get(loginname);
		return SessionId;

	}
	public synchronized void DeleteSessionId(String loginname){
		mymap.remove(loginname);
	}

}
