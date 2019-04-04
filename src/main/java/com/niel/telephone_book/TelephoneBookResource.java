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
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nielpret
 */
@Path("telephone_book")
public class TelephoneBookResource {
    EntryDAO entryDAO = new EntryDAO();
    PhoneBookDAO phoneBookDAO = new PhoneBookDAO();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(PhoneBook phoneBook) throws SQLException {
        //save entry
        if (phoneBook.getEntries().size() > 0) {
            for (Entry entry : phoneBook.getEntries()) {
                entryDAO.saveEntry(1,entry);
            }
        }
        return Response.ok().build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response query() throws SQLException {
        List<PhoneBook> phoneBooks = phoneBookDAO.getPhoneBooks();
        return Response.ok(phoneBooks).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response queryById(@DefaultValue("0") @PathParam("id") String idStr) throws SQLException {
        int id = Integer.valueOf(idStr);
        PhoneBook phoneBook = phoneBookDAO.getPhoneBookById(id);
        List<Entry> entries = entryDAO.getEntriesByPhoneBookId(id);
        phoneBook.setEntries(entries);
        return Response.ok(phoneBook).build();
    }

    @Path("entry/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEntry(@DefaultValue("0") @PathParam("id") String idStr, Entry entry) throws SQLException {
        entryDAO.updateEntry(entry);
        return Response.ok().build();
    }

    @Path("entry/{id}/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEntry(@DefaultValue("0") @PathParam("id") String idStr) throws SQLException {
        entryDAO.deleteEntry(Integer.valueOf(idStr));
        entryDAO.deleteMapEntry(Integer.valueOf(idStr));
        return Response.ok().build();
    }
}
