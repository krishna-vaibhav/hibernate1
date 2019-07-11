package dao;

import java.util.Date;
import static utils.HibernateUtils.*;
import org.hibernate.*;
import java.util.List;

import pojos.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public List<Customer> listCustomersByRoleRegDate(String role1, Date d1) {
		List<Customer> l1 = null;
		String jpql = "select c from Customer c where c.role = :rl and c.regDate > :dt";
		// hs
		Session hs = getSf().getCurrentSession();
		// tx
		Transaction tx = hs.beginTransaction();
		try {
			l1 = hs.createQuery(jpql, Customer.class).setParameter("rl", role1).setParameter("dt", d1).getResultList();
			// l1 --- list of PERSISTENT pojos
			tx.commit();// session closed , db cn reted to pool , L1 cache
						// destroyed
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return l1;// l1 -- list of DETACHED pojos
	}

	@Override
	public List<Customer> applyDiscount(Date d1, double discount) {
		List<Customer> list = null;
		// jpql
		String jpql = "select c from Customer c where c.regDate < :dt";
		// hs
		Session hs = getSf().getCurrentSession();
		// tx
		Transaction tx = hs.beginTransaction();
		try {
			list = hs.createQuery(jpql, Customer.class).setParameter("dt", d1).getResultList();
			for (Customer c : list)
				c.setRegAmount(c.getRegAmount() - discount);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		for (Customer c : list)
			c.setRegAmount(c.getRegAmount() - discount);
		return list;
	}

	@Override
	public int applyDiscountViaUpdate(Date d1, double discount) {
		int count = 0;
		// jpql
		String jpql = "update Customer c set c.regAmount=c.regAmount-:disc where c.regDate < :dt";
		// hs
		Session hs = getSf().getCurrentSession();
		// tx
		Transaction tx = hs.beginTransaction();
		try {
			count = hs.createQuery(jpql).setParameter("disc", discount).setParameter("dt", d1).executeUpdate();

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return count;

	}

	@Override
	public String unsubscribeCustomer(int custId) {
		String status = "Customer un-subscription failed";
		// hs
		Session hs = getSf().getCurrentSession();
		// tx
		Transaction tx = hs.beginTransaction();
		try {
			// get customer details by id
			Customer c = hs.get(Customer.class, custId);
			if (c != null) {
				hs.delete(c); //pojo marked for removal
				status = "Customer details deleted successfully";
			}
			tx.commit();//delete query fired,l1 destroyed,db cn rets to pool
			
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return status;

	}

}
