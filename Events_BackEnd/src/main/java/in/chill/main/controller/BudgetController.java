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

import in.chill.main.entity.Budget;
import in.chill.main.services.BudgetService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class BudgetController {
    
    @Autowired
    private BudgetService budgetService;
    
    @GetMapping("/budgets")
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }
    
    @GetMapping("/budgets/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable int id) {
        Budget budget = budgetService.getBudgetById(id).orElse(null);
        if (budget != null) {
            return ResponseEntity.ok(budget);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/budgets")
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.createBudget(budget);
    }
    
    @PutMapping("/budgets/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable int id, @RequestBody Budget budget) {
        try {
            Budget updatedBudget = budgetService.updateBudget(id, budget);
            return ResponseEntity.ok(updatedBudget);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/budgets/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable int id) {
        try {
            budgetService.deleteBudget(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
