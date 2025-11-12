package ecommerce.interplanetary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.interplanetary.entity.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
