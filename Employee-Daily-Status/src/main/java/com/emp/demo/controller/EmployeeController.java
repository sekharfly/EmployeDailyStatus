package com.emp.demo.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emp.demo.model.Admin;
import com.emp.demo.model.Employee;
import com.emp.demo.repo.AdminRepo;
import com.emp.demo.repo.EmployeeRepo;
import com.emp.demo.repo.StatusRepo;

@Controller
public class EmployeeController {

	@Autowired
	AdminRepo repo;

	@Autowired
	StatusRepo repo2;
	
	@Autowired
	EmployeeRepo repo3;

	JSONObject jsonObject = new JSONObject();

	/*@GetMapping(value = { "/", "/login", "/page-login" })
	public String login() {
		return "/page-login";
	}

	@GetMapping(value = { "/index" })
	public String index() {
		return "/index";
	}
	
	@RequestMapping(value = "/loginUser")
	public String logins(@ModelAttribute("emp") Admin emp, HttpSession session) throws JSONException {
		String email = emp.getEmail();
		String password = emp.getPassword();
		if (email != null && password != null && email != "" && password != "") {
			Admin byEmail = repo.getByEmail(email);
			if (byEmail == null) {
				System.out.println("email not registerd");
				return "/page-login";
			}
			session.setAttribute("name", byEmail.getName());
			session.setAttribute("designation", byEmail.getDesignation());
			session.setAttribute("email", byEmail.getEmail());
			if (password.equals(byEmail.getPassword()) && byEmail != null) {
				jsonObject.put("data", "login success");
				return "redirect:/index";
			} else {
				jsonObject.put("data", "invalid credentials");
				return "/page-login";
			}
		} else {
			System.out.println("3");
			JSONObject put = jsonObject.put("data", "Enter email and password");
			System.out.println(put);
			return "/page-login";
		}

	}

	@RequestMapping(value = "/logout")
	public String logOut(HttpSession session) {
		session.removeAttribute("name");
		session.removeAttribute("email");
		session.removeAttribute("designation");
		return "redirect:/page-login";
	}*/
	
	
	@GetMapping(value = { "/left-side" })
	public String leftSide() {
		return "fragments/left-side";
	}
	@GetMapping(value = { "/header" })
	public String head() {
		return "fragments/header";
	}

	@GetMapping(value = { "/emp-signup" })
	public String signUp() {
		return "/emp-signup";
	}
	@RequestMapping(value = "/empregister")
	public String createEmp(@ModelAttribute("emp") Employee emp,HttpSession session) throws JSONException {
		Employee byEmail = repo3.getByEmail(emp.getEmail());
		Admin byEmail2 = repo.getByEmail((String)session.getAttribute("email"));
		if (byEmail != null) {
			System.out.println("User already Existed");
			return "redirect:/emp-signup";
		} else {
			emp.setAdmin(byEmail2);
			emp.setType("E");
			repo3.save(emp);
			System.out.println("registerd successfully");
			return "redirect:/index";
		}
	}
	
	@RequestMapping("/empCount")
	public void empCount(Employee emp) {
		
	}
	
	
	
	/*@GetMapping("/page-emps")
	public String getProfile() {
		return "/page-emps";
	}
	@RequestMapping(value ="/empgetprofile")
	public String getProfile1(Map<String, Object> model,HttpSession session,Employee employee) {
		Object attribute = session.getAttribute("email");
		String email = attribute.toString();
		Admin employee1 = repo.getByEmail(email);
		model.put("employee", employee1);
		return "/profile"; 
	}
	*/
	/*@RequestMapping(value ="/updateprofile")
	public String updateProfile(Map<String, Object> model,HttpSession session,Admin employee) {
		Object attribute = session.getAttribute("email");
		String email = attribute.toString();
		Admin employee1 = repo.getByEmail(email);
		model.put("employee", employee1);
		return "/profile";
	}
	
	@GetMapping("/changepassword")
	public String changePasswrd() {
		return "/changpassword";
	}
	
	@RequestMapping(value ="/changepswd")
	public String changePassword(@ModelAttribute Admin employee,HttpSession session) {
		Object attribute = session.getAttribute("email");
		String email = attribute.toString();
		Admin employee1 = repo.getByEmail(email);
		String password = employee1.getPassword();
		String oldpassword = employee.getOldpassword();
		String newpassword = employee.getNewpassword();
		if(password.equals(oldpassword)) {
			employee1.setPassword(newpassword);
			repo.save(employee1);
			return "redirect:/index";
		}
		else {
			System.out.println("error");
			return "redirect:/changepassword";
		}
	}*/
	
}
