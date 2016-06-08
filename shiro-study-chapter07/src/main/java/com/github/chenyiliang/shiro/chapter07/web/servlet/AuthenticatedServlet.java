package com.github.chenyiliang.shiro.chapter07.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

@WebServlet(name = "authenticatedServlet", urlPatterns = "/authenticated")
public class AuthenticatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			request.setAttribute("subject", subject);
			request.getRequestDispatcher("/WEB-INF/jsp/authenticated.jsp").forward(request, response);
		} else {
			// 其实这里的权限判断没必要，如果没有认证成功shiro的filter会自动将页面导到登陆页面
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}

	}

}
