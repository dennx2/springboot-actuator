package com.humber.j2ee.actuators;

import java.net.URL;
import java.net.URLConnection;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class InternetHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		return checkInternet() == true ? Health.up().withDetail("success code", "Active Internet Connection").build()
				: Health.down().withDetail("error code", "Inactive Internet Connection").build();

	}

	private boolean checkInternet() {
		try {
			URL url = new URL("https://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
