package com.te.springmvc2.dao;

import com.te.springmvc2.beans.EmployeeBean;


public interface EmployeeDao {
	
	public EmployeeBean authenticate(int id,String password);
	
	public EmployeeBean getEmployee(int id);
	
	public boolean deleteEmployee(int id);
	
	public java.util.List<EmployeeBean> getAllData();
	public boolean addEmp(EmployeeBean employeedb);
	public boolean updateEmp(EmployeeBean employeedb);
}
