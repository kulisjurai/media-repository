package net.codejava.user;

import net.codejava.user.entity.User;
import net.codejava.user.repository.UserRepository;
import net.codejava.user.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        userDetailsService.checkIfUserExists(user.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody User user) {
        var userData = userDetailsService.loadUserByEmail(user.getEmail());
        return ResponseEntity.ok(userData);
    }
}