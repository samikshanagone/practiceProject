package com.velocity.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.velocity.model.Customer;
//Database related operation
@Repository
public class CustomerDAO {

	@Autowired   //we are injecting sessiofactory reference into customerDAO
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	//fetch the list of customer from database
	public List<Customer> getAllCustomeries() {
		Session session = this.sessionFactory.getCurrentSession();
		//cusotmerList ----contain list of customer from db
		List<Customer> customerList = session.createQuery("from Customer").list();
		return customerList; //return the list to getAllCustomeries()-
	}

	//fetch the customer data based on id
	public Customer getCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		//customer- contain data of specific id
		Customer customer = (Customer) session.get(Customer.class, new Integer(id)); //2
		return customer; //return the customer object to getCustomer() method
	}

	//Adding the new customer into database
	public Customer addCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customer); //session.save(customer)
		return customer; //return the customer object to addCustomer()
	}

	//update the customer data into database
	public void updateCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(customer); //updating the data
	}

	//delete the data
	public void deleteCustomer(int id) { //2
		Session session = this.sessionFactory.getCurrentSession();
		Customer p = (Customer) session.load(Customer.class, new Integer(id)); //2-DB
		if (null != p) {
			session.delete(p); //delete the data
		}
	}	
}
