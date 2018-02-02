package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UsersDaoHibernateImpl implements UsersDAO {

    public UsersDaoHibernateImpl() {
    }

    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = session.createQuery("FROM model.User").list();
        session.close();
        return users;
    }

    public void insertUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteUser(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public User getUser(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String queryString = "FROM model.User WHERE id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }
}
