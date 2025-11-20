package andre.dev.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "andre.dev.*")
@EntityScan(basePackages = "andre.dev.entity")
@ComponentScan(basePackages = { "andre.dev.*" })
@EnableJpaRepositories(basePackages = { "andre.dev.repository" })
public class SpringBootApp {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}
}
