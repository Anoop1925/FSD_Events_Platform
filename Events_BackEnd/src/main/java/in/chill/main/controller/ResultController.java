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

import in.chill.main.entity.Result;
import in.chill.main.services.ResultService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ResultController {
    
    @Autowired
    private ResultService resultService;
    
    @GetMapping("/results")
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }
    
    @GetMapping("/results/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable int id) {
        Result result = resultService.getResultById(id).orElse(null);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/results")
    public Result createResult(@RequestBody Result result) {
        return resultService.createResult(result);
    }
    
    @PutMapping("/results/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable int id, @RequestBody Result result) {
        try {
            Result updatedResult = resultService.updateResult(id, result);
            return ResponseEntity.ok(updatedResult);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/results/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable int id) {
        try {
            resultService.deleteResult(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
