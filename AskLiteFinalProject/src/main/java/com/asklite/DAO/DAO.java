/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.DAO;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author tilak
 */
public class DAO {
    private static final Logger log = Logger.getAnonymousLogger();
    private static final ThreadLocal session = new ThreadLocal();
    private static final SessionFactory sf = 
            new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    
    protected DAO() {
        
    }
    
    public static Session getSession() {
        Session session = (Session) DAO.session.get();
        if (session == null) {
            session = sf.openSession();
            DAO.session.set(session);
        }
        return session;
    }
    
    protected void beginTransaction() {
        getSession().beginTransaction();
    }
    
    protected void commit() {
        getSession().getTransaction().commit();
    }
    
    protected void rollback() {
        getSession().getTransaction().rollback();
        getSession().close();
        DAO.session.set(null);
    }
    
    public static void close() {
        getSession().close();
        DAO.session.set(null);
    }
}
