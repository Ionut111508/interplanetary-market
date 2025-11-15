package ecommerce.interplanetary.service;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public Double getTemperature(String planetName) {
        if (planetName == null) return null;

        return switch (planetName.toLowerCase()) {
            case "mars" -> -60.0;
            case "moon" -> -20.0;
            case "venus" -> 465.0;
            default -> null; // Unknown
        };
    }
}
