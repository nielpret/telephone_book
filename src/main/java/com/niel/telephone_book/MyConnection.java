/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niel.telephone_book;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author nielpret
 */
public class MyConnection {

    MysqlDataSource dataSource = new MysqlDataSource();

    public MyConnection() {
        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File(path);
        String parentDirName = file.getParent(); // to get the parent dir name
        Properties prop = new Properties();
        InputStream input = null;
        try {
            if (Main.ENVIRONMENT.equalsIgnoreCase("development")) {
                input = new FileInputStream(parentDirName + "/../config.properties");
            } else {
                input = new FileInputStream(parentDirName + "/config.properties");
            }
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        dataSource.setUser(prop.getProperty("user"));
        dataSource.setPassword(prop.getProperty("password"));
        dataSource.setServerName(prop.getProperty("server_name"));
        dataSource.setDatabaseName(prop.getProperty("database_name"));

    }

    public MysqlDataSource getDataSource() {
        return dataSource;
    }

}
