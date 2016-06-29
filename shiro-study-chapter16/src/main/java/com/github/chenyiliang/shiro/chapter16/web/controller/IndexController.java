package com.github.chenyiliang.shiro.chapter16.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.chenyiliang.shiro.chapter16.entity.Resource;
import com.github.chenyiliang.shiro.chapter16.entity.User;
import com.github.chenyiliang.shiro.chapter16.service.ResourceService;
import com.github.chenyiliang.shiro.chapter16.service.UserService;
import com.github.chenyiliang.shiro.chapter16.web.bind.annotation.CurrentUser;

import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(@CurrentUser User loginUser, Model model) {
		Set<String> permissions = userService.findPermissions(loginUser.getUsername());
		List<Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

}
