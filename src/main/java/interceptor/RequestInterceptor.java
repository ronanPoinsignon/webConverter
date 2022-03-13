package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import exception.TooManyRequestsException;
import modele.Counter;

public class RequestInterceptor implements HandlerInterceptor {

	private static final int MAX_REQUESTS = 10;

	Counter counter = Counter.getInstance();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(counter.getActualNumber() >= RequestInterceptor.MAX_REQUESTS) {
			throw new TooManyRequestsException();
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
