package tester;

import static utils.HibernateUtils.*;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.CustomerDaoImpl;

public class UpdateCustomerDetails {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try (SessionFactory sf = getSf(); Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter  reg date : yr-mon-day n discount");
			new CustomerDaoImpl().
			applyDiscount(sdf.parse(sc.next()),
					sc.nextDouble()).forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
