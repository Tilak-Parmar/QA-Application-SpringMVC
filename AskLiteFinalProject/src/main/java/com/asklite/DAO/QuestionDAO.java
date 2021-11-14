/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.DAO;

import static com.asklite.DAO.DAO.getSession;
import com.asklite.pojo.Question;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author tilak
 */
@Component("queDao")
public class QuestionDAO extends DAO {
    public void addQuestion(Question que) {
        beginTransaction();
        getSession().save(que);
        commit();
    }

    public Question searchQuestionbyID(int id) {
        beginTransaction();
        Question que = getSession().get(Question.class, id);
        commit();
        return que;
    }

    public void updateQuestion(Question que) {
        beginTransaction();
        getSession().update(que);
        commit();
    }

    public void deleteQuestion(Question que) {
        beginTransaction();
        getSession().delete(que);
        commit();
    }
    
    public List<Question> getApprovedQuestions() {
        beginTransaction();
        String hql = "FROM Question Where Approved = :approved ORDER BY QueID DESC";
        Query query = getSession().createQuery(hql);
        query.setParameter("approved", true);
        List results = query.list();
        commit();
        return results;
    }
    
    public List<Question> getUnapprovedQuestions() {
        beginTransaction();
        String hql = "FROM Question Where Approved = :approved ORDER BY QueID DESC";
        Query query = getSession().createQuery(hql);
        query.setParameter("approved", false);
        List results = query.list();
        commit();
        return results;
    }
    
    public List<Question> getQuestionsbyID(int userID) {
        beginTransaction();
        String hql = "FROM Question Where UserID = :userID ORDER BY QueID DESC";
        Query query = getSession().createQuery(hql);
        query.setParameter("userID", userID);
        List results = query.list();
        commit();
        return results;
    }
    
    public void deleteQuestionsbyUserID(int userID) {
        try {
            beginTransaction();
            String hql = "DELETE FROM Question WHERE UserID = :userID";
            Query query = getSession().createQuery(hql);
            query.setParameter("userID", userID);
            query.executeUpdate();
            commit();
        } catch (Throwable t) {
            System.out.println("Error in deleting Questions by User ID");
            rollback();
        }
    }
}
