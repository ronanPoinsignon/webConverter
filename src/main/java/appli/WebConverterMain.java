package appli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "configuration", "service", "interceptor"} )
public class WebConverterMain {

	public static void main(String[] args) {
		SpringApplication.run(WebConverterMain.class, args);
	}

}
