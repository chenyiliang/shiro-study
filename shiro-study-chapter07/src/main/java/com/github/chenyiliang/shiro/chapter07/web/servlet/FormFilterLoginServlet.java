package com.github.chenyiliang.shiro.chapter07.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

@WebServlet(name = "formFilterLoginServlet", urlPatterns = "/formfilterlogin")
public class FormFilterLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String errorClassName = (String) request.getAttribute("shiroLoginFailure");

		if (UnknownAccountException.class.getName().equals(errorClassName)) {
			request.setAttribute("error", "用户名/密码错误");
		} else if (IncorrectCredentialsException.class.getName().equals(errorClassName)) {
			request.setAttribute("error", "用户名/密码错误");
		} else if (errorClassName != null) {
			request.setAttribute("error", "未知错误：" + errorClassName);
		}

		request.getRequestDispatcher("/WEB-INF/jsp/formfilterlogin.jsp").forward(request, response);
	}

}
