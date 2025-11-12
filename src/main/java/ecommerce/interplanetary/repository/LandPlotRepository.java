package ecommerce.interplanetary.repository;

import ecommerce.interplanetary.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import ecommerce.interplanetary.entity.LandPlot;

import java.util.List;

public interface LandPlotRepository extends JpaRepository<LandPlot, Long> {
    List<LandPlot> findByPlanetId(Long planetId);
    List<LandPlot> findByPlanet(Planet planet);

}
