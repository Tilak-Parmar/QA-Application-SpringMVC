/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asklite.pojo;

import javax.persistence.*;

/**
 *
 * @author tilak
 */
@Entity
@Table(name="Users")
public class User {
    @Id
    @Column(name = "UserID", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    
    @Column(name = "FirstName", nullable=false)
    private String firstName;
    
    @Column(name = "LastName", nullable=false)
    private String lastName;
    
    @Column(name = "Email", nullable=false)
    private String email;
    
    @Column(name = "UserPassword", nullable=false)
    private String password;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
