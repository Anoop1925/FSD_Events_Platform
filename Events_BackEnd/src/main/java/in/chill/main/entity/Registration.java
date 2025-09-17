package in.chill.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "registration")
public class Registration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id")
    private int registrationId;
    
    @Column(name = "registration_name")
    private String name;
    
    @Column(name = "college")
    private String college;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "contact")
    private String contact;
    
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Feedback> feedbacks;
    
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Participation> participations;
    
    // Default constructor
    public Registration() {}
    
    // Constructor with parameters
    public Registration(String name, String college, String email, String contact) {
        this.name = name;
        this.college = college;
        this.email = email;
        this.contact = contact;
    }
    
    // Getters and setters
    public int getRegistrationId() {
        return registrationId;
    }
    
    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCollege() {
        return college;
    }
    
    public void setCollege(String college) {
        this.college = college;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
    
    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    public List<Participation> getParticipations() {
        return participations;
    }
    
    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }
    
    @Override
    public String toString() {
        return "Registration{" +
                "registrationId=" + registrationId +
                ", name='" + name + '\'' +
                ", college='" + college + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
