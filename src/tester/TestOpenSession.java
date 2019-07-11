package tester;
import static utils.HibernateUtils.*;

import org.hibernate.*;


public class TestOpenSession {

	public static void main(String[] args) {
		try(SessionFactory sf=getSf())
		{
			Session hs=sf.openSession();
			Session hs2=sf.openSession();
			Session hs3=sf.openSession();
			System.out.println(hs == hs2);
			System.out.println(hs== hs3);
			System.out.println("Connected "+hs.isConnected()+" open "+hs.isOpen());
			Transaction tx=hs.beginTransaction();
			try {
				tx.commit();
				System.out.println("Connected "+hs.isConnected()+" open "+hs.isOpen());
			} catch (HibernateException e) {
				if(tx != null)
					tx.rollback();
				throw e;
			}finally {
				if(hs != null)
					hs.close();
			}
			System.out.println("Connected "+hs.isConnected()+" open "+hs.isOpen());
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
