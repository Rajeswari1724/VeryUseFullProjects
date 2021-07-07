package com.te.springrest1.service;

import java.util.List;

import com.te.springrest1.beans.EmployeeBean;

public interface EmployeeService {

	public EmployeeBean getEmployee(int id);

	public boolean deleteEmpData(int id);

	public List<EmployeeBean> getAllEmp();

	public boolean addEmployee(EmployeeBean bean);

	public boolean updateEmployee(EmployeeBean bean);
}
