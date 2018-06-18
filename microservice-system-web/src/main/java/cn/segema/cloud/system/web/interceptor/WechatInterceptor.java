package cn.segema.cloud.system.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class WechatInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		String queryString = request.getQueryString();
		System.out.println(queryString);
		String toUrl = request.getRequestURI();
		System.out.println(toUrl);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		String queryString = request.getQueryString();
		System.out.println(queryString);
		String toUrl = request.getRequestURI();
		System.out.println(toUrl);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		String queryString = request.getQueryString();
		System.out.println(queryString);
		String toUrl = request.getRequestURI();
		System.out.println(toUrl);
		// TODO Auto-generated method stub
		
	}

}
