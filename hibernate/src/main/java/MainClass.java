import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Reader.class)
                .buildSessionFactory();

        Session session = null;
        try {

            // insert book
//            session = factory.getCurrentSession();
//            Book tmpBook = new Book("Harry Potter");
//            session.beginTransaction();
//            session.save(tmpBook);
//            session.getTransaction().commit();

            // select book
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book harryPotter2 = session.get(Book.class, 2);
//            session.getTransaction().commit();
//            System.out.println(harryPotter2);

            // update book
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book harryPotter3 = session.get(Book.class, 3);
//            harryPotter3.setTitle("Harry Potter 3 Updated");
//            session.getTransaction().commit();
//            System.out.println(harryPotter3);

            // delete book
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book trashBook = session.get(Book.class, 4);
//            session.delete(trashBook);
//            session.getTransaction().commit();

            // using HQL
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            List<Book> allBooks = session.createQuery("from Book").getResultList();
//            List<Book> harryBooks = session.createQuery("from Book b where b.title like 'Harry%'").getResultList();
//            session.createQuery("update Book set title='A'").executeUpdate();
//            session.createQuery("delete from Book where id = 3").executeUpdate();
//            session.getTransaction().commit();
//            System.out.println(allBooks);
//            System.out.println(harryBooks);

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book book = session.get(Book.class, 1);
//            Author author = session.get(Author.class, 1);
//            System.out.println(book);
//            System.out.println(author);
//            System.out.println(author.getBooks());
//            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Book book = session.get(Book.class, 1);
            System.out.println(book.getReaders());
            System.out.println(book.getReaders().get(0).getBooks());
            session.getTransaction().commit();

        } finally {
            factory.close();
            session.close();
        }
    }
}
