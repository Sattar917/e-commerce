package ecommerce.service;

import java.util.Set;

import javax.transaction.Transactional;

import ecommerce.exceptions.WishlistItemNotExistException;
import ecommerce.model.Product;
import ecommerce.model.User;
import ecommerce.model.WishList;
import ecommerce.repository.WishListRepository;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public Set<WishList> readWishList(Integer userId) {
    	return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
    
    public WishList getWishList(Integer userId) {
    	return wishListRepository.getById(userId);
    }
	
    public void deleteWishlistItem(User user, Product product) throws WishlistItemNotExistException {
    	if (wishListRepository.findByUserAndProduct(user, product).isEmpty()) {
			throw new WishlistItemNotExistException("Wishlist item did not exist");
		}
		wishListRepository.deleteByUserAndProduct(user, product);		
	}

	public void deleteWishlistItems() {
	        wishListRepository.deleteAll();
	}

	 public void deleteUserWishlistItems(User user) {
	    	wishListRepository.deleteByUser(user);
	}
}