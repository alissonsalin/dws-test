package dws.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DWSTestApp {

	public static void main(String[] args) {
		SpringApplication.run(DWSTestApp.class);
	}
}
