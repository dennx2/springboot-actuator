package com.humber.j2ee.actuators;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class ActuatorController {

	@GetMapping("/displayHealth")
	public String displayHealth(Model model) {
		String apiUrl = "http://localhost:8080/actuator/health";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
		String jsonData = response.getBody();
		model.addAttribute("healthData", jsonData);
		return "actuator/health";
	}  
}
