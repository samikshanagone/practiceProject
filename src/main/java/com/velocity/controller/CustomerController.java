package com.velocity.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.velocity.model.Customer;
import com.velocity.service.CustomerService;

@Controller
public class CustomerController {

	//here we injecting the service into controller
	@Autowired
	private CustomerService customerService;

	//to fetch the list- calling the method of CustomerService class
	@RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCustomer(Model model) {
		List<Customer> listOfCustomeries = customerService.getAllCustomer();
		model.addAttribute("customer", new Customer()); //customerDetails.jsp-ModelAttribute=customer
		model.addAttribute("listOfCustomer", listOfCustomeries);
		//listOfCustomer name and ui page name it should same
		return "customerDetails"; //customerDetails.jsp page is return here
	}

	//get the customer data by using id
	@RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Customer getCustomerById(@PathVariable int id) {   //int m1(int a){   }
		Customer customer = customerService.getCustomer(id); //just pass @pathvariable id here
		return customer;  //return customer object to getCustomerById() method
		/*
		 * data is return by getCustomer() that will stored into customer object
		 */
	}

	//Adding the new customer 
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCustomer(@ModelAttribute("customer") Customer customer) {
		if (customer.getId() == 0) {  //0==0
			customerService.addCustomer(customer); //add new customer
		} else {
			customerService.updateCustomer(customer); //update the customer
		}

		return "redirect:/getAllCustomer"; //it will redirect or navigate to getAllCustomer page list
	}

	@RequestMapping(value = "/updateCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String updateCustomer(@PathVariable("id") int id, Model model) {
		model.addAttribute("customer", this.customerService.getCustomer(id)); //get record
		model.addAttribute("listOfCustomer", this.customerService.getAllCustomer());
		return "customerDetails";
	}

	@RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteCustomer(@PathVariable("id") int id) { //2
		customerService.deleteCustomer(id); //calling method
		return "redirect:/getAllCustomer";//after deleting the record, it will navigate to getAllCustomer() method

	}
}
