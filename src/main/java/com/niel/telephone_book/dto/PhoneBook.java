/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niel.telephone_book.dto;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nielpret
 */

public class PhoneBook implements Serializable{
    private int Id;
    private String Name;
    private List<Entry> Entries;

    
    public PhoneBook(){
    }

    public PhoneBook(ResultSet rs) throws SQLException {
        Name = rs.getString("name");
        Id = rs.getInt("id");
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
     * @return the entries
     */
    public List<Entry> getEntries() {
        return Entries;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(List<Entry> entries) {
        this.Entries = entries;
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
}
