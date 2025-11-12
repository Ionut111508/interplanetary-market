package ecommerce.interplanetary.service;

import org.springframework.stereotype.Service;
import ecommerce.interplanetary.entity.Planet;
import ecommerce.interplanetary.repository.PlanetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> getAll() {
        return planetRepository.findAll();
    }

    public Optional<Planet> getById(Long id) {
        return planetRepository.findById(id);
    }

    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    public Planet update(Long id, Planet planet) {
        return planetRepository.findById(id)
                .map(p -> {
                    p.setName(planet.getName());
                    p.setDescription(planet.getDescription());
                    p.setDistanceFromEarth(planet.getDistanceFromEarth());
                    p.setHasAtmosphere(planet.getHasAtmosphere());
                    return planetRepository.save(p);
                })
                .orElseThrow(() -> new RuntimeException("Planet not found"));
    }

    public void delete(Long id) {
        planetRepository.deleteById(id);
    }
}
