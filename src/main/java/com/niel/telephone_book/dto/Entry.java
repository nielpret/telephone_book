/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niel.telephone_book.dto;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author nielpret
 */
public class Entry implements Serializable {

    private int Id;
    private String Name;
    private String PhoneNumber;

    public Entry() {
    }
    
    public Entry(ResultSet rs) throws SQLException {
        Id = rs.getInt("id");
        Name = rs.getString("name");
        PhoneNumber = rs.getString("phone_number");
    }

    /**
     * @return the id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.Id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }
}
