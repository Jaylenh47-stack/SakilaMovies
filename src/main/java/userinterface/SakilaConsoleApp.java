package userinterface;

import models.Actor;
import models.Category;
import models.Film;
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
                   4) Search Actor
                   0) Quit
                Command: """;

        int choice = -1;
        while (choice != 0) {
            choice = ConsoleHelper.promptForInt(prompt);

            switch (choice) {
                case 1:
                    processListAllCategories();
                    break;
                case 4:
                    processSearchActorRequest();
                    break;

            }
        }
    }

    private void processSearchActorRequest() {
        //List actors with matching last name
        try {
            String lastName = ConsoleHelper.promptForString("Enter last name of actor");
            List<Actor> actors =  dm.getActorsByLastName(lastName);

            //If there is a matching actor print it
           if (!actors.isEmpty()){
               actors.forEach(System.out::println);
               System.out.println();

               //Ask user for first and last name of an actor they would like to see films from
               System.out.println("Enter full name of an actor to see all films they are in");
               String firstName = ConsoleHelper.promptForString("First Name");
               String lastName2 = ConsoleHelper.promptForString("Last Name");

               //Display actor
               List<Actor> actor = dm.getActorByFullName(firstName, lastName2);
               System.out.println(actor);
               System.out.println(lastName2);

               //List films from actor
               List<Film> films = dm.getFilmsByActor(firstName, lastName2);
               films.forEach(System.out::println);

           }
           else{
               System.out.println("No actors found");
               System.out.println();
           }
        }
        catch (SQLException e) {
            System.out.println("There was a SQL error: " + e.getMessage());
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

