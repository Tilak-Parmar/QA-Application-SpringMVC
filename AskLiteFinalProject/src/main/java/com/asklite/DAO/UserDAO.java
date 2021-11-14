/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.DAO;

import com.asklite.pojo.Question;
import com.asklite.pojo.User;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author tilak
 */
@Component("userDao")
public class UserDAO extends DAO {
    
    public void addUser(User user) {
        beginTransaction();
        getSession().save(user);
        commit();
    }

    public User searchUserbyID(int id) {
        beginTransaction();
        User user = getSession().get(User.class, id);
        commit();
        return user;
    }

    public void updateUser(User user) {
        beginTransaction();
        getSession().update(user);
        commit();
    }

    public void deleteUser(User user) {
        QuestionDAO queDao = new QuestionDAO();
        AnswerDAO ansDao = new AnswerDAO();
        ansDao.deleteAnswersbyUserID(user.getID());
        List<Question> queList = queDao.getQuestionsbyID(user.getID());
        for(Question ques : queList) {
            ansDao.deleteAnswersbyQueID(ques.getID());
        }
        queDao.deleteQuestionsbyUserID(user.getID());
        beginTransaction();
        getSession().delete(user);
        commit();
    }
    
    public boolean UserExists(String email) {
        beginTransaction();
        String hql = "FROM User WHERE Email = :email";        
        Query query = getSession().createQuery(hql);
        query.setParameter("email", email);
        User result = (User) query.uniqueResult();
        commit();
        if(result != null)
            return true;
        return false;
    }
    
    public User verifyUser(String email, String password) {
        beginTransaction();
        String hql = "FROM User WHERE Email = :email AND UserPassword = :password";        
        Query query = getSession().createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", password);
        User user = (User) query.uniqueResult();
        commit();
        return user;
    }
    
    public List<User> getUsers() {
        beginTransaction();
        String hql = "FROM User";
        Query query = getSession().createQuery(hql);
        List results = query.list();
        commit();
        return results;
    }
    
}
