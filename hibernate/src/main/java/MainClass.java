import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainClass {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();

        Session session = null;
        try {
//            session = factory.getCurrentSession();
//            Book tmpBook = new Book("Harry Potter");
//            session.beginTransaction();
//            session.save(tmpBook);
//            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Book harryPotter2 = session.get(Book.class, 2);
            session.getTransaction().commit();
            System.out.println(harryPotter2);

        } finally {
            factory.close();
            session.close();
        }
    }
}
