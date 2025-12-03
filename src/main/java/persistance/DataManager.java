package persistance;

import models.Actor;
import models.Category;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private BasicDataSource ds;

    public DataManager(BasicDataSource ds) {
        this.ds = ds;
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = """
                SELECT
                category_id,
                name
                FROM category""";
        try (
                Connection connection = ds.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet results = statement.executeQuery();
        ) {
            while (results.next()) {
                int categoryID = results.getInt("category_id");
                String categoryName = results.getString("name");

                Category category = new Category(categoryID, categoryName);
                categories.add(category);
            }
        }
        return categories;
    }

    public List<Actor> getActorsByLastName(String lastNameSearch) throws SQLException {
        List<Actor> actors = new ArrayList<>();

        try(
                Connection connection = ds.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT actor_id, first_name, last_name FROM actor WHERE last_name = ?")
        ) {

            preparedStatement.setString(1, lastNameSearch);

            try (ResultSet results = preparedStatement.executeQuery()) {


                    while (results.next()) {
                        int actorID = results.getInt("actor_id");
                        String firstName = results.getString("first_name");
                        String lastName = results.getString("last_name");

                        Actor a = new Actor(actorID, firstName, lastName);
                        actors.add(a);
                    }

            }
        }
        return actors;
    }

    public List<Actor> getActorByFullName(String firstNameSearch, String lastNameSearch) throws SQLException {
        List<Actor> actors = new ArrayList<>();

        try(
                Connection connection = ds.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT actor_id, first_name, last_name FROM actor WHERE first_name = ? && last_name = ?")
            ){

            preparedStatement.setString(1, firstNameSearch);
            preparedStatement.setString(2, lastNameSearch);

            try(ResultSet results = preparedStatement.executeQuery()) {


                while (results.next()) {
                    int actorID = results.getInt("actor_id");
                    String firstName = results.getString("first_name");
                    String lastName = results.getString("last_name");


                    Actor a = new Actor(actorID, firstName, lastName);
                    actors.add(a);
                }
            }
        }
        return actors;
    }

}


