package org.kainos.ea.db;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<Order> getAllOrders() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Order`;");

        List<Order> orderList = new ArrayList<>();

        while (rs.next()) {
            Order order = new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );
            orderList.add(order);
        }
        return orderList;
    }

    public Order getOrderById(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate"
                + " FROM `Order` where OrderID=" + id);

        while (rs.next()) {
            Order order = new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );
        }
        return null;
    }

    public int createOrder(OrderRequest order) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO `Order` (CustomerID, OrderDate) VALUES (?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setInt(1, order.getCustomerId());
        st.setDate(2, (Date) order.getOrderDate());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }
}


