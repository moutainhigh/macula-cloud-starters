package org.macula.cloud.sdk.exception;


import org.macula.cloud.sdk.utils.CloudConstants;

/**
 * <p>
 * <b>ServiceException</b> 服务层默认异常
 * </p>
 *
 */
public class ServiceException extends MaculaException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param ex
	 */
	public ServiceException(String message, Throwable ex) {
		super(message, ex);
	}

	@Override
	public String getParentCode() {
		return CloudConstants.MACULA_CORE_SERVICE_CODE;
	}

}
