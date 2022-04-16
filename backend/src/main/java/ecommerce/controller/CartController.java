package ecommerce.controller;

import ecommerce.common.ApiResponse;
import ecommerce.dto.cart.AddToCartDto;
import ecommerce.dto.cart.CartDto;
import ecommerce.exceptions.AuthenticationFailException;
import ecommerce.exceptions.CartItemNotExistException;
import ecommerce.exceptions.ProductNotExistException;
import ecommerce.model.*;
import ecommerce.service.AuthenticationService;
import ecommerce.service.CartService;
import ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

 
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("username") String username) throws AuthenticationFailException, ProductNotExistException {
        User user = authenticationService.getUser(username);
        Product product = productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }
    
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("username") String username) throws AuthenticationFailException {
        User user = authenticationService.getUser(username);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }
    
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
                                                      @RequestParam("username") String username) throws AuthenticationFailException,ProductNotExistException {
        User user = authenticationService.getUser(username);
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user,product);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("username") String username) throws AuthenticationFailException, CartItemNotExistException {
        int userId = authenticationService.getUser(username).getId();
        cartService.deleteCartItem(itemID, userId);
        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }

}
