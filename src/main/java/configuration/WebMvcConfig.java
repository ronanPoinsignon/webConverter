package configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.RequestInterceptor;
import service.CounterService;

@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {

	private CounterService counterService;

	public WebMvcConfig(CounterService counterService) {
		this.counterService = counterService;
	}

	//
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// LogInterceptor apply to all URLs.
		registry.addInterceptor(new RequestInterceptor(counterService));
	}

}