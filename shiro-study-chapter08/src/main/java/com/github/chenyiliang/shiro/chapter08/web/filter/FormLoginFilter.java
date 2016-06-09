package com.github.chenyiliang.shiro.chapter08.web.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

public class FormLoginFilter extends PathMatchingFilter {
	private String loginUrl = "/login.jsp";
	private String successUrl = "/";

	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return true;// 已经登录过
		}
		HttpServletRequest requ = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (isLoginRequest(requ)) {
			if ("post".equalsIgnoreCase(requ.getMethod())) {
				// form表单提交
				boolean loginSuccess = login(requ);
				// 登陆
				if (loginSuccess) {
					return redirectToSuccessUrl(requ, resp);
				}
			}
			return true;// 继续过滤器链
		} else {
			// 保存当前地址并重定向到登录界面
			saveRequestAndRedirectToLogin(requ, resp);
			return false;
		}
	}

	private boolean redirectToSuccessUrl(HttpServletRequest requ, HttpServletResponse resp) throws IOException {
		WebUtils.redirectToSavedRequest(requ, resp, successUrl);
		return false;
	}

	private void saveRequestAndRedirectToLogin(HttpServletRequest requ, HttpServletResponse resp) throws IOException {
		WebUtils.saveRequest(requ);
		WebUtils.issueRedirect(requ, resp, loginUrl);
	}

	private boolean login(HttpServletRequest requ) {
		String username = requ.getParameter("username");
		String password = requ.getParameter("password");
		try {
			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
		} catch (Exception e) {
			requ.setAttribute("shiroLoginFailure", e.getClass());
			return false;
		}
		return true;
	}

	private boolean isLoginRequest(HttpServletRequest requ) {
		return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(requ));
	}
}
