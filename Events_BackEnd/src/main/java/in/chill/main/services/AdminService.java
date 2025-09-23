package in.chill.main.services;

import in.chill.main.dto.AuthResponse;
import in.chill.main.dto.LoginRequest;
import in.chill.main.dto.UserResponse;
import in.chill.main.entity.Admin;
import in.chill.main.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Admin login - only existing admins can login
     * @param loginRequest Login credentials
     * @return Authentication response
     */
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // Validate admin email pattern
            if (!Admin.isValidAdminEmail(loginRequest.getEmail())) {
                return new AuthResponse(null, null, "Invalid admin email format. Must end with @admin.org");
            }
            
            Optional<Admin> adminOpt = adminRepository.findByEmail(loginRequest.getEmail());
            
            if (adminOpt.isEmpty()) {
                return new AuthResponse(null, null, "Admin not found. Contact support for access.");
            }
            
            Admin admin = adminOpt.get();
            
            // Verify password
            if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
                return new AuthResponse(null, null, "Invalid password");
            }
            
            // Create UserResponse from Admin
            UserResponse userResponse = new UserResponse();
            userResponse.setUserId(admin.getAdminId());
            userResponse.setEmail(admin.getEmail());
            userResponse.setFirstName(admin.getName()); // Admin has name field, not firstName
            userResponse.setLastName(""); // Admin doesn't have lastName
            userResponse.setRole(admin.getRole());
            userResponse.setCollege("N/A"); // Admins don't have college
            userResponse.setContact("N/A"); // Admins don't have contact in this model
            
            // For now, return success without JWT token (will implement later)
            return new AuthResponse("admin-temp-token", userResponse, "Admin login successful");
            
        } catch (Exception e) {
            return new AuthResponse(null, null, "Login failed: " + e.getMessage());
        }
    }
    
    /**
     * Get admin by email
     * @param email Admin email
     * @return Admin if found
     */
    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
    
    /**
     * Create new admin (for internal use only)
     * @param admin Admin to create
     * @return Created admin
     */
    public Admin createAdmin(Admin admin) {
        // Validate email pattern
        if (!Admin.isValidAdminEmail(admin.getEmail())) {
            throw new IllegalArgumentException("Admin email must end with @admin.org");
        }
        
        // Check if admin already exists
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new IllegalArgumentException("Admin with this email already exists");
        }
        
        // Hash password
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        
        return adminRepository.save(admin);
    }
    
    /**
     * Get all admins
     * @return List of all admins
     */
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}