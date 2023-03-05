package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.entity.Direction;

public interface DirectionRepository extends JpaRepository<Direction,Long> {}
