package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sf = Util.getConnection();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age TINYINT)")
                    .executeUpdate();
            transaction.commit();
            System.out.println("Таблица users создана");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица users удалена");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User " + id + " удален");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<User> usersList = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createNativeQuery("TRUNCATE TABLE users;").executeUpdate();
            transaction.commit();
            System.out.println("Таблица users очищена");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
