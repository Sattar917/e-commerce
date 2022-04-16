package ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(value = CustomException.class)
	public final ResponseEntity<String> handleCustomException(CustomException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AuthenticationFailException.class)
    public final ResponseEntity<String> handleAuthenticationFailException(AuthenticationFailException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = ProductNotExistException.class)
    public final ResponseEntity<String> handleProductNotExistsException(ProductNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = CartItemNotExistException.class)
    public final ResponseEntity<String> handleCartItemNotExistException(CartItemNotExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = OrderNotFoundException.class)
    public final ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = WishlistItemNotExistException.class)
	public final ResponseEntity<String> handleWishlistItemNotExistException(WishlistItemNotExistException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
