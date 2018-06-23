package inciDashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"util", "controllers", "services", "repositories", "model", "apacheKafka", "configuration", "validators"})
public class InciDashboardApplication {

    public static void main(String[] args) {
	SpringApplication.run(InciDashboardApplication.class, args);
    }
}
