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

import in.chill.main.entity.Judge;
import in.chill.main.services.JudgeService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class JudgeController {
    
    @Autowired
    private JudgeService judgeService;
    
    @GetMapping("/judges")
    public List<Judge> getAllJudges() {
        return judgeService.getAllJudges();
    }
    
    @GetMapping("/judges/{id}")
    public ResponseEntity<Judge> getJudgeById(@PathVariable int id) {
        Judge judge = judgeService.getJudgeById(id).orElse(null);
        if (judge != null) {
            return ResponseEntity.ok(judge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/judges")
    public Judge createJudge(@RequestBody Judge judge) {
        return judgeService.createJudge(judge);
    }
    
    @PutMapping("/judges/{id}")
    public ResponseEntity<Judge> updateJudge(@PathVariable int id, @RequestBody Judge judge) {
        try {
            Judge updatedJudge = judgeService.updateJudge(id, judge);
            return ResponseEntity.ok(updatedJudge);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/judges/{id}")
    public ResponseEntity<Void> deleteJudge(@PathVariable int id) {
        try {
            judgeService.deleteJudge(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
} 