package ecommerce.interplanetary.controller;

import ecommerce.interplanetary.dto.LandPlotDTO;
import ecommerce.interplanetary.service.LandPlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/landplots")
@RequiredArgsConstructor
public class LandPlotController {

    private final LandPlotService landPlotService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<LandPlotDTO>> getAllLandPlots() {
        return ResponseEntity.ok(landPlotService.getAllLandPlots());
    }

    // GET BY PLANET
    @GetMapping("/planet/{planetId}")
    public ResponseEntity<List<LandPlotDTO>> getByPlanet(@PathVariable Long planetId) {
        return ResponseEntity.ok(landPlotService.getLandPlotsByPlanet(planetId));
    }

    // ADD LANDPLOT
    @PostMapping("/add")
    public ResponseEntity<?> addLandPlot(@RequestBody Map<String, Object> request) {
        try {
            String terrainType = (String) request.get("terrainType");
            Double areaSize = Double.valueOf(request.get("areaSize").toString());
            BigDecimal price = new BigDecimal(request.get("price").toString());
            Long planetId = Long.valueOf(request.get("planetId").toString());

            LandPlotDTO saved = landPlotService.addLandPlot(terrainType, areaSize, price, planetId);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    // UPDATE LANDPLOT
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLandPlot(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        try {
            String terrainType = (String) request.get("terrainType");
            Double areaSize = request.get("areaSize") != null ? Double.valueOf(request.get("areaSize").toString()) : null;
            BigDecimal price = request.get("price") != null ? new BigDecimal(request.get("price").toString()) : null;
            Long planetId = request.get("planetId") != null ? Long.valueOf(request.get("planetId").toString()) : null;

            LandPlotDTO updated = landPlotService.updateLandPlot(id, terrainType, areaSize, price, planetId);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // DELETE LANDPLOT
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLandPlot(@PathVariable Long id) {
        try {
            landPlotService.deleteLandPlot(id);
            return ResponseEntity.ok("LandPlot deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
