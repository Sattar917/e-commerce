package ecommerce.exceptions;

public class CustomException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;

	public CustomException(String msg) {
        super(msg);
    }

}
