/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niel.telephone_book;

import com.niel.telephone_book.dao.EntryDAO;
import com.niel.telephone_book.dao.PhoneBookDAO;
import com.niel.telephone_book.dto.Entry;
import com.niel.telephone_book.dto.PhoneBook;


import org.junit.Test;

/**
 *
 * @author nielpret
 */
public class EntryDAOTest {
   
    @Test
    public void testCreatePhonebook() throws Exception{
        PhoneBook phoneBook = new PhoneBook();
        PhoneBookDAO phoneBookDAO  = new PhoneBookDAO();
        phoneBook.setName("My PhoneBook");
        phoneBook = phoneBookDAO.savePhoneBook(phoneBook);
    }
    
    @Test
    public void testCreateEntry() throws Exception{
        EntryDAO entryDAO = new EntryDAO();
        Entry entry = new Entry();
        entry.setName("Niel Pretorius");
        entry.setPhoneNumber("0829523709");
        entryDAO.saveEntry(1, entry);
    }
}
