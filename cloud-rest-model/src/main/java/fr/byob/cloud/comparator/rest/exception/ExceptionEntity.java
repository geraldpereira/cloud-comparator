package fr.byob.cloud.comparator.rest.exception;


public class ExceptionEntity {
	private String message;
	private String status;
	private int errorCode;

	public ExceptionEntity() {

	}

	public ExceptionEntity(final String message, final String status, final int errorCode) {
		super();
		this.message = message;
		this.status = status;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(final int errorCode) {
		this.errorCode = errorCode;
	}

}
