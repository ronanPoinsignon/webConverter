package configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.RequestInterceptor;

@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {

	//
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// LogInterceptor apply to all URLs.
		registry.addInterceptor(new RequestInterceptor());
	}

}