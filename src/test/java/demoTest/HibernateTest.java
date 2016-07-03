package demoTest;


import demo.HibernateUtil;
import demo.models.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;

public class HibernateTest {
    @Test
    public void testConnection() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Office office = session.get(Office.class, 11);
            Assert.assertNotNull(office);
            Assert.assertNotNull(office.getRegion());
            System.out.println(office.getCity());

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
