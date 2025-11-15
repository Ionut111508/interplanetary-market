package ecommerce.interplanetary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @GetMapping("/{planetName}")
    public Map<String, Object> getTemperature(@PathVariable String planetName) {
        Map<String, Object> result = new HashMap<>();
        result.put("planet", planetName);
        switch (planetName.toLowerCase()) {
            case "mars":
                result.put("temperature", "-60°C");
                break;
            case "moon":
                result.put("temperature", "-20°C");
                break;
            case "venus":
                result.put("temperature", "465°C");
                break;
            default:
                result.put("temperature", "Unknown");
        }
        return result;
    }
}
