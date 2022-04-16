package ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.model.User;
import ecommerce.repository.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
    UserRepository userRepository;


	public String getUsername(User user) {
        return user.getUsername();
    }
	
	public User getUser(String username) {
		final User user = userRepository.findByUsername(username).orElse(null); 
		return user;
	}
}