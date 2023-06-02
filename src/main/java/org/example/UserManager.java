package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class UserManager {
    private SessionFactory sessionFactory;

    public void init(){
        this.sessionFactory = new Configuration()
                .addAnnotatedClass(org.example.User.class)
                .buildSessionFactory();
    }

    public List<User> getUserList(){
        try(Session session = sessionFactory.openSession()){
            //Can create a native query which implements usual sql commands
            NativeQuery query = session.createNativeQuery("select * from usr");
            return session.createQuery("from User", User.class).getResultList();
        }
    }

    public void addUser(User user){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }

    public void deleteUser(User user){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
    }

    public void changeAllNames(){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            String hql = "update User u set u.name = 'Boba' where u.name like 'Vova'";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            transaction.commit();
        }
    }

    public String getUserNameById(Integer id){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select u.name from User u where u.id = :id", String.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }


}
