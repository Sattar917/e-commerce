package ecommerce.exceptions;

public class WishlistItemNotExistException extends IllegalArgumentException{
	
	private static final long serialVersionUID = 1L;

	public WishlistItemNotExistException(String msg) {
		super(msg);
	}

}
