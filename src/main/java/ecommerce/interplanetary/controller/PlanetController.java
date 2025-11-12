package ecommerce.interplanetary.controller;

import org.springframework.web.bind.annotation.*;
import ecommerce.interplanetary.entity.Planet;
import ecommerce.interplanetary.service.PlanetService;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
@CrossOrigin(origins = "http://localhost:3000")
public class PlanetController {

    private final PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping
    public List<Planet> getAll() {
        return planetService.getAll();
    }

    @GetMapping("/{id}")
    public Planet getById(@PathVariable Long id) {
        return planetService.getById(id)
                .orElseThrow(() -> new RuntimeException("Planet not found"));
    }

    @PostMapping
    public Planet create(@RequestBody Planet planet) {
        return planetService.save(planet);
    }

    @PutMapping("/{id}")
    public Planet update(@PathVariable Long id, @RequestBody Planet planet) {
        return planetService.update(id, planet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        planetService.delete(id);
    }
}
