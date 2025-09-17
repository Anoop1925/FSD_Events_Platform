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

import in.chill.main.entity.Sponsor;
import in.chill.main.services.SponsorService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SponsorController {
    
    @Autowired
    private SponsorService sponsorService;
    
    @GetMapping("/sponsors")
    public List<Sponsor> getAllSponsors() {
        return sponsorService.getAllSponsors();
    }
    
    @GetMapping("/sponsors/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable int id) {
        Sponsor sponsor = sponsorService.getSponsorById(id).orElse(null);
        if (sponsor != null) {
            return ResponseEntity.ok(sponsor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/sponsors")
    public Sponsor createSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.createSponsor(sponsor);
    }
    
    @PutMapping("/sponsors/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable int id, @RequestBody Sponsor sponsor) {
        try {
            Sponsor updatedSponsor = sponsorService.updateSponsor(id, sponsor);
            return ResponseEntity.ok(updatedSponsor);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/sponsors/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable int id) {
        try {
            sponsorService.deleteSponsor(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
