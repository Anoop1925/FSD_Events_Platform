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

import in.chill.main.entity.Participation;
import in.chill.main.services.ParticipationService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ParticipationController {
    
    @Autowired
    private ParticipationService participationService;
    
    @GetMapping("/participations")
    public List<Participation> getAllParticipations() {
        return participationService.getAllParticipations();
    }
    
    @GetMapping("/participations/{id}")
    public ResponseEntity<Participation> getParticipationById(@PathVariable int id) {
        Participation participation = participationService.getParticipationById(id).orElse(null);
        if (participation != null) {
            return ResponseEntity.ok(participation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/participations")
    public Participation createParticipation(@RequestBody Participation participation) {
        return participationService.createParticipation(participation);
    }
    
    @PutMapping("/participations/{id}")
    public ResponseEntity<Participation> updateParticipation(@PathVariable int id, @RequestBody Participation participation) {
        try {
            Participation updatedParticipation = participationService.updateParticipation(id, participation);
            return ResponseEntity.ok(updatedParticipation);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/participations/{id}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable int id) {
        try {
            participationService.deleteParticipation(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
