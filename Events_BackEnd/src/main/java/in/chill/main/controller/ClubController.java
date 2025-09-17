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

import in.chill.main.entity.Club;
import in.chill.main.services.ClubService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClubController {
    
    @Autowired
    private ClubService clubService;
    
    @GetMapping("/clubs")
    public List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }
    
    @GetMapping("/clubs/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable int id) {
        Club club = clubService.getClubById(id).orElse(null);
        if (club != null) {
            return ResponseEntity.ok(club);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/clubs")
    public Club createClub(@RequestBody Club club) {
        return clubService.createClub(club);
    }
    
    @PutMapping("/clubs/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable int id, @RequestBody Club club) {
        try {
            Club updatedClub = clubService.updateClub(id, club);
            return ResponseEntity.ok(updatedClub);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/clubs/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable int id) {
        try {
            clubService.deleteClub(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
} 