package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
   private OrderDao orderDao = new OrderDao();
   private OrderValidator orderValidator = new OrderValidator();

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);

            if (validation != null) {
                throw new InvalidOrderException(validation);
            }

            int id = orderDao.createOrder(order);

            if (id == -1) {
                throw new FailedToCreateOrderException();
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateOrderException();
        }
    }
   public List<Order> getAllOrders() throws FailedToGetOrdersException {
       List<Order> orderList = null;
       try {
           orderList = orderDao.getAllOrders();
       } catch (SQLException e) {
           System.err.println(e.getMessage());
           throw new FailedToGetOrdersException();
       }

       System.out.println(orderList);

       return orderList;
   }

    public Order getOrderById(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);

            if (order == null) {
                throw new OrderDoesNotExistException();
            }

            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetOrdersException();
        }
    }
}
