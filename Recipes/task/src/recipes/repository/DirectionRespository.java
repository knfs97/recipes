package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.entity.Direction;

public interface DirectionRespository extends JpaRepository<Direction,Long> {
}
