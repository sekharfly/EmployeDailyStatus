package com.emp.demo.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emp.demo.model.Admin;
import com.emp.demo.model.Employee;
import com.emp.demo.repo.AdminRepo;
import com.emp.demo.repo.EmployeeRepo;

@Controller
public class AdminController implements UserDetailsService {

	@Autowired
	AdminRepo repo;

	@Autowired
	EmployeeRepo empRepo;

	JSONObject jsonObject = new JSONObject();

	@GetMapping(value = { "/login","/" })
	public String login() {
		return "/page-login";
	}
	
	@GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

	@GetMapping(value = { "/index" })
	public String index() {
		return "/index";
	}
	
	@GetMapping(value = { "/admin" })
	public String admin() {
		return "/index";
	}
	
	@GetMapping(value = { "/user" })
	public String user() {
		return "/index";
	}
	
	@GetMapping(value = { "/home" })
	public String home() {
		return "/home";
	}

	@RequestMapping(value = "/admin/loginUser",method=RequestMethod.POST)
	public String logins(@ModelAttribute("emp") Admin adm, HttpSession session) throws JSONException {
		String email = adm.getEmail();
		String password = adm.getPassword();
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
				long count = empRepo.count();
				session.setAttribute("count", count);
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
		session.invalidate();
		return "redirect:/page-login";
	}

	@GetMapping(value = { "/page-signup" })
	public String signUp() {
		return "/page-signup";
	}

	@RequestMapping(value = "/getAllEmployees")
	public String getAllUsers(Map<String, Object> model) {
		Iterable<Employee> findAll = empRepo.findAll();
		model.put("employees", findAll);
		return "table-basic";
	}

	@RequestMapping(value = "/signup")
	public String createEmp(@ModelAttribute("adm") Admin adm) throws JSONException {
		String password = adm.getPassword();
		String cppassword = adm.getCppassword();
		Admin byEmail = repo.getByEmail(adm.getEmail());
		if (byEmail != null) {
			System.out.println("User already Existed");
			return "redirect:/page-signup";
		} else if (password.equals(cppassword)) {
			adm.setType("A");
			repo.save(adm);
			System.out.println("loginSuccessfully");
			return "redirect:/login";
		} else {
			System.out.println("Password != cp-Password");
			return "redirect:/page-signup";
		}
	}

	@GetMapping("/page-user")
	public String getProfile() {
		return "/page-user";
	}

	@RequestMapping(value = "/getprofile")
	public String getProfile1(Map<String, Object> model, HttpSession session, Admin employee) {
		Object attribute = session.getAttribute("email");
		String email = attribute.toString();
		Admin employee1 = repo.getByEmail(email);
		model.put("employee", employee1);
		return "/profile";
	}

	@RequestMapping(value = "/updateprofile")
	public String updateProfile(Map<String, Object> model, HttpSession session, Admin employee) {
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

	@RequestMapping(value = "/changepswd")
	public String changePassword(@ModelAttribute Admin employee, HttpSession session) {
		Object attribute = session.getAttribute("email");
		String email = attribute.toString();
		Admin employee1 = repo.getByEmail(email);
		String password = employee1.getPassword();
		String oldpassword = employee.getOldpassword();
		String newpassword = employee.getNewpassword();
		if (password.equals(oldpassword)) {
			employee1.setPassword(newpassword);
			repo.save(employee1);
			return "redirect:/index";
		} else {
			System.out.println("error");
			return "redirect:/changepassword";
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin byEmail = repo.getByEmail(username);
		ArrayList<SimpleGrantedAuthority> arrayList = new ArrayList<SimpleGrantedAuthority>();
		arrayList.add(new SimpleGrantedAuthority("A"));
		return new org.springframework.security.core.userdetails.User(byEmail.getEmail(), byEmail.getPassword(),
				arrayList);
	}

}
