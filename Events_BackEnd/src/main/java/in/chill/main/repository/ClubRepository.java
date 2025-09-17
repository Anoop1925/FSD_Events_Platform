package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.chill.main.entity.Club;
 
public interface ClubRepository extends JpaRepository<Club, Integer> {
    // You can add custom queries here if needed
} 