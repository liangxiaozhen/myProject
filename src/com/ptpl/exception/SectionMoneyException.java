package com.ptpl.exception;
/**
 * 分段金额异常
 * @author jxy
 *
 */
public class SectionMoneyException extends Exception{
	private static final long serialVersionUID = 1L;
	public SectionMoneyException(String message){
		super(message);
	}
}
