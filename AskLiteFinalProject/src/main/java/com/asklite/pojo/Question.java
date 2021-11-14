/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.pojo;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author tilak
 */
@Entity
@Table(name="Ques")
public class Question {
    @Id
    @Column(name = "QueID", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    
    @Column(name = "Question", nullable=false)
    private String question;
    
    @Column(name = "UserID", nullable=false)
    private int userID;
    
    @Column(name = "Approved", nullable=false)
    private boolean approved;
    
    @Transient
    private List<Answer> answersList;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public List<Answer> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Answer> answersList) {
        this.answersList = answersList;
    }
}
