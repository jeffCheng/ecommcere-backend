package com.easybuy.shopping.error;

public enum EmBusinessError implements CommonError{
	USER_NOT_EXIST(20001, "Customer is not existed"),
	PRODUCT_NOT_EXIST(20001,"Product is not existed" ),
	PARAMETER_VALIDATION_ERROR(20002, "illegal"),
	UNKNOWN_ERROR(10002,"Unknown Err"),
	USER_LOGIN_ERROR(30001,"Login is failled."),
	ORDER_QTY_IS_NOT_ENOUGH(40001,"Order quantity is not enough.");
	
	private int errCode;
	private String errMsg;
	
	private EmBusinessError (int errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	@Override
	public int getErrCode() {
		return this.errCode;
	} 

	@Override
	public String getErrMsg() {
		return this.errMsg;
	}

	@Override
	public CommonError setErrMsg(String errMsg) {
		this.errMsg = errMsg;
		return this;
	}

}
