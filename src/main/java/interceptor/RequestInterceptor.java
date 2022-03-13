package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import exception.TooManyRequestsException;
import service.CounterService;

@Component
public class RequestInterceptor implements HandlerInterceptor {

	@Value("${max_requests:10}")
	private int maxRequests;

	private CounterService counterService;

	public RequestInterceptor(CounterService counterService) {
		this.counterService = counterService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(counterService.getActualNumber() >= maxRequests) {
			throw new TooManyRequestsException();
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
