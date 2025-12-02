package com.pluralsight;

import com.mysql.cj.protocol.Resultset;
import models.*;
import org.apache.commons.dbcp2.BasicDataSource;
import userinterface.ConsoleHelper;
import persistance.*;
import userinterface.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws SQLException {

        if (!ensureArgs(args)) { return; }

        try{
            BasicDataSource ds = getBasicDataSource(args);

            DataManager dm = new DataManager(ds);

            SakilaConsoleApp app = new SakilaConsoleApp(dm);

            app.start();

        } catch (Exception e) {
            System.out.println("There was a SQL exception: "+ e.getMessage());
        }










    }

    private static BasicDataSource getBasicDataSource(String username, String password, String URL){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    private static BasicDataSource getBasicDataSource(String[] args){
        String username = args[0];
        String password = args[1];
        String URL = args[2];
        return getBasicDataSource(username, password, URL);
    }

    private static boolean ensureArgs(String[] args){
        if(args.length < 3){
            System.out.println("You need to provide a username, password and URL when running this command.");
            System.out.println("For example:");
            System.out.println("Main.exe myUsername myPassword");
            return false;
        }
        return true;
    }
}
