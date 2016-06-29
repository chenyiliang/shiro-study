package com.github.chenyiliang.shiro.chapter19.web.bind.method;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.chenyiliang.shiro.chapter19.web.bind.annotation.CurrentUser;

/**
 * <p>
 * 用于绑定@FormModel的方法参数解析器
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public CurrentUserMethodArgumentResolver() {
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		CurrentUser currentUserAnnotation = parameter.getParameterAnnotation(CurrentUser.class);
		return webRequest.getAttribute(currentUserAnnotation.value(), NativeWebRequest.SCOPE_REQUEST);
	}
}
