package com.chinesedreamer.dubbocenter.web.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.chinesedreamer.dubbocenter.web.message.ResponseVo;

@Component
public class ExceptionHandler implements HandlerExceptionResolver{

	private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
		if (responseBody != null) {// json return
			try {
				logger.error(" request faild, unsupported exception happened.");
				PrintWriter writer = response.getWriter();
				ResponseVo vo = ResponseVo.getFailureResponse("ERR999", "提交失败，请重试", null);
				writer.write(JSON.toJSONString(vo));
				writer.flush();
			} catch (IOException e) {
				logger.error("",e);
			}
			return null;
		}
		return new ModelAndView();
	}

}
