/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.DAO;

import static com.asklite.DAO.DAO.getSession;
import com.asklite.pojo.Answer;
import java.util.List;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author tilak
 */
@Component("ansDao")
public class AnswerDAO extends DAO {

    public void addAnswer(Answer ans) {
        beginTransaction();
        getSession().save(ans);
        commit();
    }

    public Answer searchAnswerbyID(int id) {
        beginTransaction();
        Answer ans = getSession().get(Answer.class, id);
        commit();
        return ans;
    }

    public void updateAnswer(Answer ans) {
        beginTransaction();
        getSession().update(ans);
        commit();
    }

    public void deleteAnswer(Answer ans) {
        beginTransaction();
        getSession().delete(ans);
        commit();
    }

    public List<Answer> getAnswersbyQueID(int queID) {
        beginTransaction();
        String hql = "FROM Answer WHERE QueID = :queID AND Approved=:approved ORDER BY AnswerID DESC";
        Query query = getSession().createQuery(hql);
        query.setParameter("queID", queID);
        query.setParameter("approved", true);
        List results = query.list();
        commit();
        return results;
    }

    public void deleteAnswersbyUserID(int userID) {
        try {
            beginTransaction();
            String hql = "DELETE FROM Answer WHERE UserID = :userID";
            Query query = getSession().createQuery(hql);
            query.setParameter("userID", userID);
            query.executeUpdate();
            commit();
        } catch (Throwable t) {
            System.out.println("Error in deleting Answers by User ID");
            rollback();
        }
    }

    public void deleteAnswersbyQueID(int queID) {
        try {
            beginTransaction();
            String hql = "DELETE FROM Answer WHERE QueID = :queID";
            Query query = getSession().createQuery(hql);
            query.setParameter("queID", queID);
            query.executeUpdate();
            commit();
        } catch (Throwable t) {
            System.out.println("Error in deleting Answers by Question ID");
            rollback();
        }
    }
}
