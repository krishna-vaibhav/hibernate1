package dao;

import java.util.Date;
import java.util.List;

import pojos.Customer;

public interface CustomerDao {
	List<Customer> listCustomersByRoleRegDate(String role, Date d1);
	//apply discount on reg amount
	List<Customer> applyDiscount(Date d1, double discount);
	int applyDiscountViaUpdate(Date d1, double discount);
	String unsubscribeCustomer(int custId);
}
