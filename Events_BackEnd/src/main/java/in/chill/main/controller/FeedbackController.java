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

import in.chill.main.entity.Feedback;
import in.chill.main.services.FeedbackService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FeedbackController {
    
    @Autowired
    private FeedbackService feedbackService;
    
    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }
    
    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int id) {
        Feedback feedback = feedbackService.getFeedbackById(id).orElse(null);
        if (feedback != null) {
            return ResponseEntity.ok(feedback);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/feedbacks")
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }
    
    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable int id, @RequestBody Feedback feedback) {
        try {
            Feedback updatedFeedback = feedbackService.updateFeedback(id, feedback);
            return ResponseEntity.ok(updatedFeedback);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int id) {
        try {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
