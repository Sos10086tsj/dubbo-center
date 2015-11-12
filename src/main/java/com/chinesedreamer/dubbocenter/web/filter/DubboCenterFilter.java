package com.chinesedreamer.dubbocenter.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DubboCenterFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//增加ctx路径
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if(session != null){
			String ctx = req.getContextPath();
    		session.setAttribute("ctx", (null == ctx  ? "" : ctx) );
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}
