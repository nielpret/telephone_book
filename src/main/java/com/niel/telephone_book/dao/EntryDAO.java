/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niel.telephone_book.dao;

import com.mysql.jdbc.Statement;
import com.niel.telephone_book.MyConnection;
import com.niel.telephone_book.dto.Entry;
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
public class EntryDAO {

    Connection conn;
    PreparedStatement stmt = null;
    MyConnection myConnection = new MyConnection();

    /**
     *
     * @param phoneBookId
     * @param entry
     * @return
     * @throws SQLException
     */
    public Entry saveEntry(int phoneBookId, Entry entry) throws SQLException {
        try {
            String sql = "INSERT INTO entries (name,phone_number) values (?,?)";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            stmt.setString(i++, entry.getName());
            stmt.setString(i++, entry.getPhoneNumber());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entry.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        linkPhoneBookEntry(phoneBookId, entry.getId());
        return entry;
    }

    /**
     *
     * @param entry
     * @return
     * @throws SQLException
     */
    public Entry updateEntry(Entry entry) throws SQLException {
        try {
            String sql = "UPDATE entries set name = ? , phone_number = ? WHERE id = ?";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            stmt.setString(i++, entry.getName());
            stmt.setString(i++, entry.getPhoneNumber());
            stmt.setInt(i++, entry.getId());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entry.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return entry;
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void deleteEntry(int id) throws SQLException {
        try {
            String sql = "DELETE FROM entries WHERE id = ?";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            int i = 1;
            stmt.setInt(i++, id);
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();

        }
    }
    /**
     *
     * @param id
     * @throws SQLException
     */
    public void deleteMapEntry(int id) throws SQLException {
        try {
            String sql = "DELETE FROM phone_book_entry WHERE entry_id = ?";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            int i = 1;
            stmt.setInt(i++, id);
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();

        }
    }
    
    /**
     *
     * @param phoneBookId
     * @param entryId
     * @throws SQLException
     */
    public void linkPhoneBookEntry(int phoneBookId, int entryId) throws SQLException {
        try {
            String sql = "INSERT IGNORE INTO phone_book_entry (phone_book_id,entry_id) values (?,?)";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            int i = 1;
            stmt.setInt(i++, phoneBookId);
            stmt.setInt(i++, entryId);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
    }
    
    /**
     *
     * @param phoneBookId
     * @return
     * @throws SQLException
     */
    public List<Entry> getEntriesByPhoneBookId(int phoneBookId) throws SQLException{
        List<Entry> entries = new ArrayList<>();
        try {
            String sql = "SELECT e.id,e.name,e.phone_number FROM entries e "
                    + "INNER JOIN phone_book_entry p ON p.entry_id = e.id "
                    + "WHERE p.phone_book_id = ? ORDER BY e.name ASC";
            conn = myConnection.getDataSource().getConnection();
            stmt = conn.prepareStatement(sql);
            int i = 1;
            stmt.setInt(i++, phoneBookId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Entry entry = new Entry(rs);
                entries.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }
        return entries;
    }
}
