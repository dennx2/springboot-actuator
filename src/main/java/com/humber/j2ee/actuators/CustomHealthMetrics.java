package com.humber.j2ee.actuators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomHealthMetrics implements HealthIndicator {
	
	@Value("${apiEndpointUrl}")
	private String apiEndpointUrl;

    @Override
    public Health health() {
        boolean isApiHealthy = checkApiHealth();
        if (isApiHealthy) {
            return Health.up().build();
        } else {
            return Health.down().withDetail("error", "API not responding").build();
        }
    }
    
    private boolean checkApiHealth() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(apiEndpointUrl, String.class);

            // Check if the API responded with a successful status code (e.g., 200 OK).
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            // If an exception occurs during the request, consider the API as unhealthy.
            return false;
        }
    }

}
