package com.otc.api.aop;

import com.otc.api.domain.YioShop;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.result.ApiResult;
import com.otc.api.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yc
 */
@Aspect
// 定义一个切面
@Configuration
public class TokenAspect {

	@Autowired
	private YioShopMapper yioShopMapper;

	// 定义切点Pointcut
	@Pointcut("execution(public * com.otc.api.web..*.*(..))")
	public void excudeService() {

	}

	@Around("excudeService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		HttpServletResponse response = sra.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		Gson g = new Gson();
		String uuid = request.getHeader("uuid");
		if (method.getAnnotation(Token.class) != null && method.getAnnotation(Token.class).value()) {
			if (StringUtils.isBlank(uuid)) {
				return g.toJson(new ApiResult(40001));
			}else{
				YioShop user = yioShopMapper.findAllByToken(uuid);
				if (user==null){
					return g.toJson(new ApiResult(40002));
				}else {
					request.setAttribute("user",user);
				}
			}
		}
		Object result = pjp.proceed();
		
		if(result instanceof PageInfo){
			PageInfo pageInfo = (PageInfo) result;
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", pageInfo.getList());
			map.put("page", pageInfo.getPages());//总页数
			map.put("total", pageInfo.getTotal());//总条数
			map.put("pageNum", pageInfo.getPageNum());//当前页
			result = map;
		}

		result = new ApiResult<Object>(0, result);

		return result;

	}
}
