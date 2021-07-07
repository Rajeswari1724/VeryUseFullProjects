package com.te.springmvc2.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.springmvc2.beans.EmployeeBean;
import com.te.springmvc2.service.EmployeeService;

@Controller
public class SpringMVC2Controller {
	
//@Autowired(required = false)//serviceimpl lona serviceni comment chesina kuda errors emi ravu false este  //no wrong=> comment cheste error vastundhi
//@Autowired(required = true)//serviceimpl lona serviceni comment chesina kuda errors vastae true este //no wrong=>comment cheste error vastadhi comment cheyakunte error radhu
@Autowired // by default fault untundhi
	EmployeeService service;

	@GetMapping("/login")
	public String getLoginForm() {
		return "loginform";
	}

	
	
	
	
	
	@PostMapping("/emplogin")
	public String authenticate(int id, String password, HttpServletRequest request, ModelMap map) {

		EmployeeBean employeeBean = service.authenticate(id, password);                                     

		if (employeeBean != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("emp", employeeBean);
			return "homepage";                                                           //here we are returning string //in spring boot we are returning object
		} else {
			map.addAttribute("errmsg", "invalid credentials");
			return "loginform";
		}
	}
	
	
	
	
	
	
	
	

	@GetMapping("/searchform")
	public String getSearchForm(HttpSession session, ModelMap map) {
		if (session.getAttribute("emp") != null) {
			return "searchform";
		} else {
			map.addAttribute("errmsg", "please login first");
			return "loginform";
		}
	}

	@GetMapping("/search")
	public String searchEmployee(int id, ModelMap map,@SessionAttribute(name = "emp", required = false) EmployeeBean employeeBean) {
		if (employeeBean != null) {
			EmployeeBean employeeBean2 = service.getEmployee(id);
			if (employeeBean2 != null) {
				map.addAttribute("data", employeeBean2);
			} else {
				map.addAttribute("msg", "data not found for id: " + id);
			}
			return "searchform";
		} else {
			map.addAttribute("errmsg", "please login first");
			return "loginform";
		}

	}            // end of searchemployee
	
	
	
	
	

	@GetMapping("/logout")
	public String logOut(HttpSession session, ModelMap map) {
		session.invalidate();
		map.addAttribute("msg", "logout successful");
		return "loginform";
	}             // end of logout
	
	
	
	
	
	

	@GetMapping("/getdeleteform")
	public String getDeleteForm(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
		if (bean != null) {
			return "deleteform";
		} else {
			map.addAttribute("msg", "please login first");
			return "loginform";
		}

	}

	@GetMapping("/delete")
	public String deleteEmployee(int id, @SessionAttribute(name = "emp", required = false) EmployeeBean bean,ModelMap map) {
		if (bean != null) {
			boolean deleted = service.deleteEmployee(id);
			if (deleted == true) {
				map.addAttribute("msg", "deleted successfully");
				return "deleteform";
			} else {
				map.addAttribute("errmsg", "user not found");
				return "deleteform";
			}

		}
 
		return null;
      }              //end of delete
	 
	
	
	
	
	
	

	@GetMapping("/viewall")
	public String viewAllEmployee(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
		if (bean != null) {
			List<EmployeeBean> employeeBeans = service.getAllData();
			map.addAttribute("empdata", employeeBeans);
			return "alldata";
		} else {
			map.addAttribute("msg", "no employees found");
			return "alldata";
		}

	}            // end of get all emp no handler method //????????????????????????
	
	
	
	
	
	
	

	@GetMapping("/showAddemp")
	public String getaddEmpForm(ModelMap map,@SessionAttribute(name="emp",required = false)EmployeeBean bean) {
		if(bean!=null) {
		return"addform";
		}else {
			map.addAttribute("errmsg","Please Login First");
			return "loginform";
		} 
		
	}
	
	@PostMapping("/add")
	public String addEmployee(ModelMap map,@SessionAttribute(name="emp",required = false)EmployeeBean employeedb,EmployeeBean bean) {
		if(employeedb!=null) {
			
			if(service.addEmp(bean)) {
				map.addAttribute("msg", "Added successfully");
			}else {
				map.addAttribute("msg", "something went wrong");
			}
			return "addform";
		}else {
			map.addAttribute("errmsg", "Login First");
		}
		return "loginform";	
	}                             //end of add emp
	
	
	
	
	
	
	@GetMapping("/update")
	public String getUpdateForm(ModelMap map,@SessionAttribute(name="emp",required = false)EmployeeBean bean) {
		if(bean!=null) {
			map.addAttribute("eid", bean.getId());
			return"updateform";
		}else {
			map.addAttribute("errmsg","Login First");
			return "loginform";
		} 
		
	}
	
	@PostMapping("/update")
	public String updateEmp(@SessionAttribute(name="emp", required = false)EmployeeBean employeedb,EmployeeBean bean,ModelMap map) {
		
		if(employeedb!=null) {
			if(service.updateEmp(bean)) {
				map.addAttribute("eid", employeedb.getId());
				map.addAttribute("msg", "Updated Successfully");
			}
			else {
				map.addAttribute("eid", employeedb.getId());
                map.addAttribute("msg", "Something went Wrong");
			}
			return "updateform";
		}else {
			map.addAttribute("errmsg", "Login First");
		}
		return "loginform";               //end of update emp
		
	}

}

	


















































//package com.te.springmvc2.controllers;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.SessionAttribute;
//
//import com.te.springmvc2.beans.EmployeeBean;
//import com.te.springmvc2.dao.EmployeeDao;
//import com.te.springmvc2.service.EmployeeServiceImpl;
//
//@Controller
//public class SpringMVC2Controller {
//
//	@Autowired
//	EmployeeServiceImpl service;
//
//	@GetMapping("/login")
//	public String getLoginForm() {
//		return "loginform";
//	}
//
//	@PostMapping("/emplogin")
//	public String authenticate(int id, String password, HttpServletRequest request, ModelMap map) {
//
//		EmployeeBean employeeBean = service.authenticate(id, password);
//
//		if (employeeBean != null) {
//			HttpSession session = request.getSession(true);
//			session.setAttribute("emp", employeeBean);
//			return "homepage";
//		} else {
//			map.addAttribute("errmsg", "invalid credentials");
//			return "loginform";
//		}
//	}
//
//	@GetMapping("/searchform")
//	public String getSearchForm(HttpSession session, ModelMap map) {
//		if (session.getAttribute("emp") != null) {
//			return "searchform";
//		} else {
//			map.addAttribute("errmsg", "please login first");
//			return "loginform";
//		}
//	}
//
//	@GetMapping("/search")
//	public String searchEmployee(int id, ModelMap map,
//			@SessionAttribute(name = "emp", required = false) EmployeeBean employeeBean) {
//		if (employeeBean != null) {
//			EmployeeBean employeeBean2 = service.getEmployee(id);
//			if (employeeBean2 != null) {
//				map.addAttribute("data", employeeBean2);
//			} else {
//				map.addAttribute("msg", "data not found for id: " + id);
//			}
//			return "searchform";
//		} else {
//			map.addAttribute("errmsg", "please login first");
//			return "loginform";
//		}
//
//	}// end of searchemployee
//
//	@GetMapping("/logout")
//	public String logOut(HttpSession session, ModelMap map) {
//		session.invalidate();
//		map.addAttribute("msg", "logout successful");
//		return "loginform";
//	}// end of logout
//
//	@GetMapping("/getdeleteform")
//	public String getDeleteForm(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
//		if (bean != null) {
//			return "deleteform";
//		} else {
//			map.addAttribute("msg", "please login first");
//			return "loginform";
//		}
//
//	}
//
//	@GetMapping("/delete")
//	public String deleteEmployee(int id, @SessionAttribute(name = "emp", required = false) EmployeeBean bean,
//			ModelMap map) {
//		if (bean != null) {
//			boolean deleted = service.deleteEmployee(id);
//			if (deleted == true) {
//				map.addAttribute("msg", "deleted successfully");
//				return "deleteform";
//			} else {
//				map.addAttribute("errmsg", "user not found");
//				return "deleteform";
//			}
//
//		}
//
//		return null;
//
//	}
//
//	@GetMapping("/viewall")
//	public String viewAllEmployee(ModelMap map, @SessionAttribute(name = "emp", required = false) EmployeeBean bean) {
//		if (bean != null) {
//			List<EmployeeBean> employeeBeans = service.getAllData();
//			map.addAttribute("empdata", employeeBeans);
//			return "alldata";
//		} else {
//			map.addAttribute("msg", "no employees found");
//			return "alldata";
//		}
//
//	}
//
//}











