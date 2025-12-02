package userinterface;

import models.Category;
import org.apache.commons.dbcp2.BasicDataSource;
import persistance.DataManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SakilaConsoleApp {

    private DataManager dm;

    public SakilaConsoleApp(DataManager dm) {
        this.dm = dm;
    }

    public void start() {
        String prompt = """
                Please select from one of the following:
                   1) List all Categories
                   2) List all Films
                   3) List Films by Category
                   0) Quit
                Command: """;

        int choice = -1;
        while (choice != 0) {
            choice = ConsoleHelper.promptForInt(prompt);

            switch (choice) {
                case 1:
                    processListAllCategories();
                    break;

            }

        }

    }


    private void processListAllCategories() {
       try{
           List<Category> categories = dm.getAllCategories();
           ConsoleHelper.displayList(categories);
       } catch (SQLException e) {
           System.out.println("There was a SQL error: " + e.getMessage());
       }

    }



}

