package com.github.chenyiliang.shiro.chapter19.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.chenyiliang.shiro.chapter19.entity.Role;
import com.github.chenyiliang.shiro.chapter19.service.ResourceService;
import com.github.chenyiliang.shiro.chapter19.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("roleList", roleService.findAll());
		return "role/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String showCreateForm(Model model) {
		setCommonData(model);
		model.addAttribute("role", new Role());
		model.addAttribute("op", "新增");
		return "role/edit";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Role role, RedirectAttributes redirectAttributes) {
		roleService.createRole(role);
		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/role";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("role", roleService.findOne(id));
		model.addAttribute("op", "修改");
		return "role/edit";
	}

	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String update(Role role, RedirectAttributes redirectAttributes) {
		roleService.updateRole(role);
		redirectAttributes.addFlashAttribute("msg", "修改成功");
		return "redirect:/role";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String showDeleteForm(@PathVariable("id") Long id, Model model) {
		setCommonData(model);
		model.addAttribute("role", roleService.findOne(id));
		model.addAttribute("op", "删除");
		return "role/edit";
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		roleService.deleteRole(id);
		redirectAttributes.addFlashAttribute("msg", "删除成功");
		return "redirect:/role";
	}

	private void setCommonData(Model model) {
		model.addAttribute("resourceList", resourceService.findAll());
	}

}
