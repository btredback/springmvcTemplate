package com.demo.common.exception;

public class FtpErrorException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2710127059485131064L;

	public FtpErrorException(String msg) {
        super(msg);
    }

    public FtpErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}