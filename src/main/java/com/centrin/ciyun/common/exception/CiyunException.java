package com.centrin.ciyun.common.exception;

public class CiyunException extends Exception {
	private static final long serialVersionUID = 1L;

	private int errorCode;

	public CiyunException() {
		super();
		this.setErrorCode(-1);
	}

	public CiyunException(String msg) {
		super(msg);
		this.setErrorCode(-1);
	}

	public CiyunException(int code) {
		this.setErrorCode(code);
	}

	public CiyunException(int code, String msg) {
		super(msg);
		this.setErrorCode(code);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
