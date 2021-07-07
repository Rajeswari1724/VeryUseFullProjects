package com.te.springmvc2.dao;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.te.springmvc2.beans.EmployeeBean;
import com.te.springmvc2.customexception.EmployeeExp;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceUnit
	EntityManagerFactory factory;

	@Override
	public EmployeeBean authenticate(int id, String password) {
		// EntityManagerFactory factory =
		// Persistence.createEntityManagerFactory("springdb");

		EntityManager manager = null;
		manager = factory.createEntityManager();

		try {

			EmployeeBean bean = manager.find(EmployeeBean.class, id);

			if (bean != null) {
				if (bean.getPassword().equalsIgnoreCase(password)) {
					System.out.println("login successful");
					return bean;
				} else {
					System.out.println("invalid credentials");
					throw new EmployeeExp("USER NOT FOUND!!!!!!! ");
				}

			} else {
				throw new EmployeeExp("USER NOT FOUND!!!!!!! ");
			}

		} catch (Exception e) {

			e.printStackTrace();
			throw new EmployeeExp("USER NOT FOUND!!!!!!! ");
		} // end of authenticate

	}

	@Override
	public EmployeeBean getEmployee(int id) {
		// EntityManagerFactory factory =
		// Persistence.createEntityManagerFactory("springdb");

		EntityManager manager = factory.createEntityManager();
		try {
			EmployeeBean bean = manager.find(EmployeeBean.class, id);

			if (bean != null) {
				return bean;
			} else {
				System.out.println("employee not found ");
				return null;
			}
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		} // end of getEmployee

	}

	@Override
	public boolean deleteEmployee(int id) {

		// EntityManagerFactory factory =
		// Persistence.createEntityManagerFactory("springdb");

		EntityManager manager = factory.createEntityManager();

		EntityTransaction transaction = manager.getTransaction();

		transaction.begin();

		EmployeeBean bean = manager.find(EmployeeBean.class, id);
		if (bean != null) {
			manager.remove(bean);
			transaction.commit();
			return true;
		} else {
			return false;
		}

	}// end of delete employee

	@Override
	public List<EmployeeBean> getAllData() {
		// EntityManagerFactory factory =
		// Persistence.createEntityManagerFactory("springdb");

		EntityManager manager = factory.createEntityManager();

		String query = "from EmployeeBean";

		javax.persistence.Query query2 = manager.createQuery(query);

		List<EmployeeBean> list = query2.getResultList();
		if (list != null) {
			return list;
		} else {
			return null;
		}

	}

	@Override
	public boolean addEmp(EmployeeBean employeedb) {
		// EntityManagerFactory factory=null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		boolean isadded = false;

		try {
			// factory= Persistence.createEntityManagerFactory("springdb");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();

			if (employeedb != null) {
				manager.persist(employeedb);
				transaction.commit();
				isadded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			isadded = false;
		}

		return isadded; // method returns

	}

	@Override
	public boolean updateEmp(EmployeeBean employeedb) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		try {
			transaction.begin();
			EmployeeBean bean = manager.find(EmployeeBean.class, employeedb.getId()); // Based on id
			if (employeedb.getName() != null && employeedb.getName() != "") {
				bean.setName(employeedb.getName()); // getNames()-----???????
			}
			if (employeedb.getBirtDate() != null) {
				bean.setBirtDate(employeedb.getBirtDate()); // getBirtDate()-----???????
			}
			if (employeedb.getPassword() != null && employeedb.getPassword() != "") {
				bean.setPassword(employeedb.getPassword()); // getPassword()------????????
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}