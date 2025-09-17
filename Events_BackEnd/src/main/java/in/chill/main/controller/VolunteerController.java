package in.chill.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.chill.main.entity.Volunteer;
import in.chill.main.services.VolunteerService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VolunteerController {
    
    @Autowired
    private VolunteerService volunteerService;
    
    @GetMapping("/volunteers")
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.getAllVolunteers();
    }
    
    @GetMapping("/volunteers/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable int id) {
        Volunteer volunteer = volunteerService.getVolunteerById(id).orElse(null);
        if (volunteer != null) {
            return ResponseEntity.ok(volunteer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/volunteers")
    public Volunteer createVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.createVolunteer(volunteer);
    }
    
    @PutMapping("/volunteers/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable int id, @RequestBody Volunteer volunteer) {
        try {
            Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, volunteer);
            return ResponseEntity.ok(updatedVolunteer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/volunteers/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable int id) {
        try {
            volunteerService.deleteVolunteer(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
