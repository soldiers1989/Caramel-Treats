package com.otc.api.aop;

import com.otc.api.domain.YioShop;
import com.otc.api.domain.YioSysUser;
import com.otc.api.mapper.YioShopMapper;
import com.otc.api.mapper.YioSysUserMapper;
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
 * Created by 张晓
 */
@Aspect
// 定义一个切面
@Configuration
public class TokenAspect {

	@Autowired
	private YioSysUserMapper yioSysUserMapper;

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
		//TODO 权限控制
		String uuid = request.getHeader("uuid");
		if (method.getAnnotation(Token.class) != null && method.getAnnotation(Token.class).value()) {
			if (StringUtils.isBlank(uuid)) {
				return g.toJson(new ApiResult(40001));
			}else{
				YioSysUser user = yioSysUserMapper.findAllByToken(uuid);
				if (user==null){
					return g.toJson(new ApiResult(40002));
				}else {
					if (user.getAuthority().equals(1) || user.getAuthority().equals(2)){
						YioShop shop = new YioShop();
						if (user.getServerId()==null){
							shop.setId(0);
						}else {
							shop.setId(user.getServerId());
						}
						shop.setAuthority(user.getAuthority());
						request.setAttribute("user",shop);
						request.setAttribute("token",uuid);
					}else if (user.getAuthority().equals(3) || user.getAuthority().equals(4)){
						request.setAttribute("user",user);
						request.setAttribute("token",uuid);
					}else {
						return g.toJson(new ApiResult(40002));
					}
				}
			}
		}
		if (method.getAnnotation(TokenAdmin.class) != null && method.getAnnotation(TokenAdmin.class).value()) {
			if (StringUtils.isBlank(uuid)) {
				return g.toJson(new ApiResult(40001));
			}else{
				YioSysUser user = yioSysUserMapper.findAllByToken(uuid);
				if (user==null){
					return g.toJson(new ApiResult(40002));
				}else {
					if (user.getAuthority().equals(1)){
						YioShop shop = new YioShop();
						if (user.getServerId()==null){
							shop.setId(0);
						}else {
							shop.setId(user.getServerId());
						}
						shop.setAuthority(user.getAuthority());
						request.setAttribute("user",shop);
						request.setAttribute("token",uuid);
					}else{
						return g.toJson(new ApiResult(40002));
					}

				}
			}
		}

		if (method.getAnnotation(TokenFinance.class) != null && method.getAnnotation(TokenFinance.class).value()) {
			if (StringUtils.isBlank(uuid)) {
				return g.toJson(new ApiResult(40001));
			}else{
				YioSysUser user = yioSysUserMapper.findAllByToken(uuid);
				if (user==null){
					return g.toJson(new ApiResult(40002));
				}else {
					if (!user.getAuthority().equals(4)){
						return g.toJson(new ApiResult(40002));
					}else{
						YioShop shop = new YioShop();
						if (user.getServerId()==null){
							shop.setId(0);
						}else {
							shop.setId(user.getServerId());
						}
						shop.setAuthority(user.getAuthority());
						request.setAttribute("user",shop);
						request.setAttribute("token",uuid);
					}

				}
			}
		}

		if (method.getAnnotation(TokenAll.class) != null && method.getAnnotation(TokenAll.class).value()) {
			if (StringUtils.isBlank(uuid)) {
				return g.toJson(new ApiResult(40001));
			}else {
				YioSysUser user = yioSysUserMapper.findAllByToken(uuid);
				if (user==null){
					return g.toJson(new ApiResult(40002));
				}else {
					request.setAttribute("user",user);
					request.setAttribute("token",uuid);
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
