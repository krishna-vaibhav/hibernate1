package tester;
import static utils.HibernateUtils.*;

import org.hibernate.*;


public class TestCurrentSession {

	public static void main(String[] args) {
		try(SessionFactory sf=getSf())
		{
			Session hs=sf.getCurrentSession();
			Session hs2=sf.getCurrentSession();
			Session hs3=sf.getCurrentSession();
			System.out.println(hs == hs2);
			System.out.println(hs== hs3);
			Transaction tx=hs.beginTransaction();
			System.out.println("Connected "+hs.isConnected()+" open "+hs.isOpen());
			try {
				tx.commit();
				System.out.println("Connected "+hs.isConnected()+" open "+hs.isOpen());
			} catch (HibernateException e) {
				if(tx != null)
					tx.rollback();
				throw e;
			}
			System.out.println("Connected "+hs.isConnected()+" open "+hs.isOpen());
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
