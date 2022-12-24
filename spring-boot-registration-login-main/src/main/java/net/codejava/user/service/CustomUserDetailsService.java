package net.codejava.user.service;

import net.codejava.user.details.CustomUserDetails;
import net.codejava.user.repository.UserRepository;
import net.codejava.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	public User loadUserByEmail(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return userRepo.findByEmail(user.getEmail());
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return null;
	}

	public User checkIfUserExists(String email) throws ResponseStatusException {
		User user = userRepo.findByEmail(email);
		if(user != null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}
		return null;
	}

	public User checkPassword(String password) throws ResponseStatusException{
		User user = userRepo.findByPassword(password);
		if(user != null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
		}
		return null;
	}
}