package Controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Ahmed Hussein
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class MainController {
	public static void main(String[] args) {
		SpringApplication.run(MainController.class, args);
	}
}
