package ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ecommerce.model.Category;
import ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByNameStartingWithIgnoreCase(String name);
	
	@Query("SELECT p FROM Product p WHERE p.category = ?1")
	List<Product> findAllByCategory(Category category);
}
