package tester;

import static utils.HibernateUtils.*;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.CustomerDaoImpl;

public class DeleteCustomerDetails {

	public static void main(String[] args) {
		try(SessionFactory sf=getSf();
				Scanner sc=new Scanner(System.in))
		{
			
			System.out.println("Enter customer id to delete");
			System.out.println(new CustomerDaoImpl().
					unsubscribeCustomer(sc.nextInt()));
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
