package in.chill.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.chill.main.entity.Participation;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
}
