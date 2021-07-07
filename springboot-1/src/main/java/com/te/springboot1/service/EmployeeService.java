package com.te.springboot1.service;

import java.util.List;

import com.te.springboot1.beans.EmployeeBean;



public interface EmployeeService {
	public EmployeeBean authenticate(int id, String password);

	public EmployeeBean getEmployee(int id);

	public boolean deleteEmp(int id);

	public List<EmployeeBean> getAllEmployees();
	
	public boolean getAddEmpForm(EmployeeBean bean);
	
	public boolean updateEmp(EmployeeBean bean);

}
