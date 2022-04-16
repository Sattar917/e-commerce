package ecommerce.controller;

import ecommerce.common.ApiResponse;
import ecommerce.dto.product.ProductDto;
import ecommerce.exceptions.AuthenticationFailException;
import ecommerce.exceptions.WishlistItemNotExistException;
import ecommerce.model.Product;
import ecommerce.model.User;
import ecommerce.model.WishList;
import ecommerce.service.AuthenticationService;
import ecommerce.service.ProductService;
import ecommerce.service.WishListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

        @Autowired
        private WishListService wishListService;
        
        @Autowired
        private ProductService productService;

        @Autowired
        private AuthenticationService authenticationService;

        
        @GetMapping("/{username}")
        public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("username") String username) {
                int userId = authenticationService.getUser(username).getId();
                Set<WishList> body = wishListService.readWishList(userId);
                List<ProductDto> products = new ArrayList<>();
                for (WishList wishList : body) {
                   products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
                }
                return new ResponseEntity<>(products, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("username") String username) {
                User user = authenticationService.getUser(username);
                WishList wishList = new WishList(user, product);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);
        }

        @DeleteMapping("/delete/{itemName}")
        public ResponseEntity<ApiResponse> deleteWishlistItem(@PathVariable("itemName") String itemName, @RequestParam("username") String username) throws AuthenticationFailException, WishlistItemNotExistException {
            User user = authenticationService.getUser(username);
            List<Product> products = productService.findProduct(itemName);
            Product product = products.get(0);
            wishListService.deleteWishlistItem(user, product);
            return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
        }                     
}
