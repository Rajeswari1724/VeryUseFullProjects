package com.te.springmvc2.customexception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeControllerAdvice  {
	@ExceptionHandler(EmployeeExp.class)
public String handleExp(EmployeeExp exp,HttpServletRequest request) {
	request.setAttribute("err", exp.getMessage());
	return "errPage";
}
}
