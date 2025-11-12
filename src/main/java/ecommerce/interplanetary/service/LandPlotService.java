package ecommerce.interplanetary.service;

import ecommerce.interplanetary.dto.LandPlotDTO;
import ecommerce.interplanetary.entity.LandPlot;
import ecommerce.interplanetary.entity.Planet;
import ecommerce.interplanetary.repository.LandPlotRepository;
import ecommerce.interplanetary.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LandPlotService {

    private final LandPlotRepository landPlotRepository;
    private final PlanetRepository planetRepository;

    // GET ALL LANDPLOTS
    public List<LandPlotDTO> getAllLandPlots() {
        return landPlotRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // GET LANDPLOTS BY PLANET
    public List<LandPlotDTO> getLandPlotsByPlanet(Long planetId) {
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new RuntimeException("Planet not found"));
        return landPlotRepository.findByPlanet(planet).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ADD LANDPLOT
    public LandPlotDTO addLandPlot(String terrainType, Double areaSize, BigDecimal price, Long planetId) {
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new RuntimeException("Planet not found"));

        LandPlot landPlot = LandPlot.builder()
                .terrainType(terrainType)
                .areaSize(areaSize)
                .price(price)
                .planet(planet)
                .build();

        LandPlot saved = landPlotRepository.save(landPlot);
        return mapToDTO(saved);
    }

    // HELPER DTO MAPPER
    private LandPlotDTO mapToDTO(LandPlot landPlot) {
        return new LandPlotDTO(
                landPlot.getId(),
                landPlot.getTerrainType(),
                landPlot.getAreaSize(),
                landPlot.getPrice(),
                landPlot.getPlanet() != null ? landPlot.getPlanet().getId() : null,
                landPlot.getPlanet() != null ? landPlot.getPlanet().getName() : null
        );
    }

    // UPDATE LANDPLOT
    public LandPlotDTO updateLandPlot(Long landPlotId, String terrainType, Double areaSize, BigDecimal price, Long planetId) {
        LandPlot landPlot = landPlotRepository.findById(landPlotId)
                .orElseThrow(() -> new RuntimeException("LandPlot not found"));

        if (terrainType != null) landPlot.setTerrainType(terrainType);
        if (areaSize != null) landPlot.setAreaSize(areaSize);
        if (price != null) landPlot.setPrice(price);

        if (planetId != null) {
            Planet planet = planetRepository.findById(planetId)
                    .orElseThrow(() -> new RuntimeException("Planet not found"));
            landPlot.setPlanet(planet);
        }

        LandPlot saved = landPlotRepository.save(landPlot);
        return mapToDTO(saved);
    }

    // DELETE LANDPLOT
    public void deleteLandPlot(Long landPlotId) {
        LandPlot landPlot = landPlotRepository.findById(landPlotId)
                .orElseThrow(() -> new RuntimeException("LandPlot not found"));
        landPlotRepository.delete(landPlot);
    }
}
