package persistance;

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
}

