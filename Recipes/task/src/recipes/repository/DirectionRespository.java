package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recipes.entity.Directions;

public interface DirectionRespository extends JpaRepository<Directions,Long> {}
