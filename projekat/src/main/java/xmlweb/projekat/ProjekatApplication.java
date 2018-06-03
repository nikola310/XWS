package xmlweb.projekat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "xmlweb.projekat")
@EntityScan("xmlweb.projekat.model")
@EnableJpaRepositories("xmlweb.projekat.repository")
@EnableTransactionManagement
public class ProjekatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjekatApplication.class, args);
	}
}
