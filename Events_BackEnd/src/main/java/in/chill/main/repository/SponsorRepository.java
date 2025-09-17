package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Sponsor;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {
}
