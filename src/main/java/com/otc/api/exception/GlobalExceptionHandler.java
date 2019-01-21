package com.otc.api.exception;

import com.otc.api.result.ApiResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = Logger.getLogger(getClass());

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ApiResult defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ApiResult r = new ApiResult(100, e.getMessage());
		logger.info("错误代码100 : " + e.toString()+req.getMethod());
		return r;
	}

	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public ApiResult jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
		String str = "";
		String str2 = "";
		ApiResult r = null;
		if (e.getLocalizedMessage().length() > 5) {
			str = e.getLocalizedMessage().substring(0, 5);
			str2 = e.getLocalizedMessage().substring(5, e.getLocalizedMessage().length());
			r = new ApiResult(Integer.parseInt(str));
			r.setData(str2);
		} else {
			str = e.getLocalizedMessage();
			r = new ApiResult(Integer.parseInt(str));
		}
		return r;

	}

}
