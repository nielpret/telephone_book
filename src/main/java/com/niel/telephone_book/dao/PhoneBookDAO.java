/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niel.telephone_book.dao;

import com.mysql.jdbc.Statement;
import com.niel.telephone_book.MyConnection;
import com.niel.telephone_book.dto.Entry;
import com.niel.telephone_book.dto.PhoneBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nielpret
 */
public class PhoneBookDAO {

    Connection conn;
    PreparedStatement stmt = null;
    MyConnection myConnection = new MyConnection();

    public PhoneBook savePhoneBook(PhoneBook phoneBook) throws SQLException {
        try {
            String sql = "INSERT INTO phone_books (name) values (?)";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            stmt.setString(i++, phoneBook.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                phoneBook.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return phoneBook;
    }

    public List<PhoneBook> getPhoneBooks() throws SQLException {
        List<PhoneBook> phoneBooks = new ArrayList<>();
        try {
            String sql = "SELECT id,name FROM phone_books";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                PhoneBook phoneBook = new PhoneBook(rs);
                phoneBooks.add(phoneBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return phoneBooks;
    }

    public PhoneBook getPhoneBookById(int id) throws SQLException {
        PhoneBook phoneBook = null;
        try {
            String sql = "SELECT id,name FROM phone_books";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                phoneBook = new PhoneBook(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return phoneBook;
    }

}
