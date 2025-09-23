package in.chill.main.services;

import in.chill.main.dto.*;
import in.chill.main.entity.User;
import in.chill.main.repository.UserRepository;
import in.chill.main.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        try {
            // Check if user already exists
            if (userRepository.existsByEmail(registerRequest.getEmail())) {
                return new AuthResponse(null, null, "User with this email already exists");
            }
            
            // Create new user
            User user = new User();
            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            user.setCollege(registerRequest.getCollege());
            user.setContact(registerRequest.getContact());
            
            // Save user
            User savedUser = userRepository.save(user);
            
            // Generate token
            String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getUserId());
            
            // Create user response
            UserResponse userResponse = new UserResponse(
                savedUser.getUserId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getCollege(),
                savedUser.getContact(),
                savedUser.getRole()
            );
            
            return new AuthResponse(token, userResponse, "User registered successfully");
            
        } catch (Exception e) {
            return new AuthResponse(null, null, "Registration failed: " + e.getMessage());
        }
    }
    
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // Find user by email
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            
            if (userOptional.isEmpty()) {
                return new AuthResponse(null, null, "Invalid email or password");
            }
            
            User user = userOptional.get();
            
            // Check password
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return new AuthResponse(null, null, "Invalid email or password");
            }
            
            // Generate token
            String token = jwtUtil.generateToken(user.getEmail(), user.getUserId());
            
            // Create user response
            UserResponse userResponse = new UserResponse(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getCollege(),
                user.getContact(),
                user.getRole()
            );
            
            return new AuthResponse(token, userResponse, "Login successful");
            
        } catch (Exception e) {
            return new AuthResponse(null, null, "Login failed: " + e.getMessage());
        }
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    @Override
    public UserResponse getUserProfile(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isEmpty()) {
            return null;
        }
        
        User user = userOptional.get();
        return new UserResponse(
            user.getUserId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getCollege(),
            user.getContact(),
            user.getRole()
        );
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
