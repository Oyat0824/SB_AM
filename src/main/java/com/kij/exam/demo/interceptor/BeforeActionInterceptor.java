package com.kij.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kij.exam.demo.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		Rq rq = new Rq(req, res);
		req.setAttribute("rq", rq);

		return HandlerInterceptor.super.preHandle(req, res, handler);
	}

}
