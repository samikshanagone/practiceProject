package com.velocity.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.velocity.dao.CustomerDAO;
import com.velocity.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerDAO customerDao;  //inject object
	
	@Transactional
	public List<Customer> getAllCustomer() {  //to get the list of all the customer
		List<Customer> list=customerDao.getAllCustomeries();  //calling the method of DAO class
		return list; 
		/*whatever the data is return by getAllCustomerires() that will be 
		 * stored into list object, finally we are returning list to getAllCustomer() method
		 */
	}

	@Transactional
	public Customer getCustomer(int id) {  //get the customer record by using id
		Customer customer=customerDao.getCustomer(id); //return data by getCustomer() method that will store into customer object
		return customer; //return customer object to getCustomer() method
	}

	@Transactional
	public void addCustomer(Customer customer) { //add the record or customer
		customerDao.addCustomer(customer);
	}

	@Transactional
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	@Transactional
	public void deleteCustomer(int id) {  //delete customer by id-2
		customerDao.deleteCustomer(id); //2
	}
}
