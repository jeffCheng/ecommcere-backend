package com.easybuy.shopping.error;

public class BusinessException extends Exception implements CommonError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonError commonError;
	
	public BusinessException(CommonError commonError) {
		super();
		this.commonError = commonError;
	}
	
	public BusinessException(CommonError commonError, String errMsg) {
		super();
		this.commonError = commonError;
		this.commonError.setErrMsg(errMsg);
	}
	
	@Override
	public int getErrCode() {
		return commonError.getErrCode();
	}

	@Override
	public String getErrMsg() {
		return commonError.getErrMsg();
	}

	@Override
	public CommonError setErrMsg(String errMsg) {
		commonError.setErrMsg(errMsg);
		return this;
	}

}
