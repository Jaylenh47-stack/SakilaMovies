package com.pluralsight;

import com.mysql.cj.protocol.Resultset;
import models.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import userinterface.ConsoleHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws SQLException {

        if(args.length < 3){
            System.out.println("You need to provide a username, password and URL when running this command.");
            System.out.println("For example:");
            System.out.println("Main.exe myUsername myPassword");
        }

        String username = args[0];
        String password = args[1];
        String URL = args[2];









    }

    private static BasicDataSource getBasicDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
    }

//    private static List<Category> getAllCategories(BasicDataSource ds) throws SQLException {
//
//        List<Category> categories = new ArrayList<>();
//        String query = """
//                    SELECT
//                    category_id,
//                    name
//                    FROM category""";
//        try(
//                Connection connection = ds.getConnection();
//                PreparedStatement statement = connection.prepareStatement(query);
//                ResultSet results = statement.executeQuery();
//        ){
//            while(results.next()){
//                int categoryID = results.getInt("category_id");
//                String categoryName = results.getString("name");
//
//                Category category = new Category(categoryID, categoryName);
//                categories.add(category);
//            }
//        }
//        return categories;
//    }
}
