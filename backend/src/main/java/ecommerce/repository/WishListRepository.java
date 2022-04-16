package ecommerce.repository;

import ecommerce.model.Product;
import ecommerce.model.User;
import ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    Set<WishList> findAllByUserIdOrderByCreatedDateDesc(Integer userId);

    List<WishList> deleteByUser(User user);
    
    List<WishList> deleteByUserAndProduct(User user, Product product);
    
    List<WishList> findByUserAndProduct(User user, Product product);

}