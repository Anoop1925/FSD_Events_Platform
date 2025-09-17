package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
    // You can add custom queries here if needed
} 