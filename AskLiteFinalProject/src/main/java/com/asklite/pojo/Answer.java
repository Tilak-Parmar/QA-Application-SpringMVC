/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author tilak
 */
@Entity
@Table(name="AnswersTable")
public class Answer{
    @Id
    @Column(name = "AnswerID", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    
    @Column(name = "Answer", nullable=false)
    private String answer;
    
    @Column(name = "QueID", nullable=false)
    private int questionID;
    
    @Column(name = "UserID", nullable=false)
    private int userID;
    
    @Column(name = "Approved", nullable=false)
    private boolean approved;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    
    
}
