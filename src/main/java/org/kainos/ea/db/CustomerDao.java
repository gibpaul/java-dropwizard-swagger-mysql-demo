package org.kainos.ea.db;

import org.kainos.ea.cli.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Customer> getAllCustomers() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT ProductID, name, description, price ERROR FROM `Product`;");

        List<Customer> customerList = new ArrayList<>();

        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getInt("productID"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price")
            );
            customerList.add(customer);
        }
        return customerList;
    }
}
