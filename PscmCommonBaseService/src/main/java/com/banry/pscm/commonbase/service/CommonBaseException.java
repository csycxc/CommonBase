package com.banry.pscm.commonbase.service;

public class CommonBaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1576541371333504367L;

	public CommonBaseException(){
		super();
	}
	
	public CommonBaseException(String message){
		super(message);
	}
	
	public CommonBaseException(String message,Throwable e){
		super(message, e);
	}

}
