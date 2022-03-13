package configuration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.RequestInterceptor;

@SpringBootConfiguration
public class WebMvcConfig implements WebMvcConfigurer {

	RequestInterceptor requestInterceptor;

	public WebMvcConfig(RequestInterceptor requestInterceptor) {
		this.requestInterceptor = requestInterceptor;
	}

	//
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// LogInterceptor apply to all URLs.
		registry.addInterceptor(requestInterceptor);
	}

}