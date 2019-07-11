package tester;

import static utils.HibernateUtils.*;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.CustomerDaoImpl;

public class FetchCustomersByCriteria {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter role & reg date yr-mon-day");
			new CustomerDaoImpl().listCustomersByRoleRegDate(sc.next(), sdf.parse(sc.next()))
					.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
