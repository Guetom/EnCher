package fr.eni.EnCher.exception;

public class EncherException extends Exception {
	
	public EncherException() {
		super();
	}
	
	public EncherException(String message) {
		super(message);
	}
	
	public EncherException(String message, Throwable exception) {
		super(message, exception);
	}

	//M�thodes
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("EnCher exception - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}


}
