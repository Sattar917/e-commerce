package ecommerce.controller;

import ecommerce.common.ApiResponse;
import ecommerce.dto.product.ProductDto;
import ecommerce.model.Category;
import ecommerce.model.Product;
import ecommerce.service.CategoryService;
import ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired 
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{productID}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productID") Integer productID, @RequestBody @Valid ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        Category category = optionalCategory.get();
        productService.updateProduct(productID, productDto, category);
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{productID}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productID") Integer productID){
    	Optional<Product> optionalProduct = productService.readProduct(productID);
    	if(optionalProduct.isEmpty())
    		return new ResponseEntity<>(new ApiResponse(false, "Product do not exist"), HttpStatus.CONFLICT);
    	productService.deleteProduct(productID);
    	return new ResponseEntity<>(new ApiResponse(true, "Product has been removed"), HttpStatus.OK);
    }
    
    @GetMapping("/search/{productName}")
    public ResponseEntity<List<ProductDto>> findProduct(@PathVariable("productName") String productName){
    	
    	List<ProductDto> body = productService.findProductByName(productName);
    	if (body.isEmpty()) return new ResponseEntity<>(body, HttpStatus.OK);
    	return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
  
