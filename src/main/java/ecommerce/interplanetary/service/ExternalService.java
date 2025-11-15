package ecommerce.interplanetary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Object getPlanetResources(String planetName) {
        String url = "https://example.com/api/resources?planet=" + planetName;
        return restTemplate.getForObject(url, Object.class);
    }
}
