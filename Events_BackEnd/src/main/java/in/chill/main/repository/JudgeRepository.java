package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Judge;

@Repository
public interface JudgeRepository extends JpaRepository<Judge, Integer> {
    // You can add custom queries here if needed
} 