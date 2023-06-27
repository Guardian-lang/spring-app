package by.ahmed.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "by.ahmed.springapp")
@EnableJpaRepositories(basePackages = "by.ahmed.springapp.repository")
@EntityScan(basePackages = "by.ahmed.springapp.entity")
public class SpringAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAppApplication.class, args);
	}

}
