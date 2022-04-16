package ecommerce.service;

import ecommerce.dto.product.ProductDto;
import ecommerce.exceptions.ProductNotExistException;
import ecommerce.model.Category;
import ecommerce.model.Product;
import ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public static ProductDto getDtoFromProduct(Product product) {
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        return product;
    }

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }
    
    public void deleteProduct(Integer id) {
    	productRepository.delete(productRepository.getById(id));
    }
    
    public Optional<Product> readProduct(Integer productId) {
    	return productRepository.findById(productId);
	}

    public Product getProductById(Integer productId) throws ProductNotExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }

    public List<Product> getProductsByCategory(Category category){
    	return productRepository.findAllByCategory(category);
    }
    
    public void deleteAllByCategory(Category category) {
		List<Product> products = getProductsByCategory(category);
		
		for (Product product : products) {
			productRepository.delete(product);
		}
	}

	public List<ProductDto> findProductByName(String productName) {
		
		List<Product> products = productRepository.findByNameStartingWithIgnoreCase(productName);
		
		List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
	}
	
	public List<Product> findProduct(String productName) {
		
		return productRepository.findByNameStartingWithIgnoreCase(productName);
	}
  
}
