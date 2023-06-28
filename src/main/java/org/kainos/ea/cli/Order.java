package org.kainos.ea.cli;

import java.util.Date;
public class Order implements Comparable<Order>{
    private int orderId;
    private int customerId;

    public Order(int orderId, int customerId, Date orderDate) {
        this.setOrderId(orderId);
        this.setCustomerId(customerId);
        this.setOrderDate(orderDate);
    }

    private Date orderDate;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderId: " + this.getOrderId()
                + ", CustomerId: " + this.getCustomerId()
                + " Order Date:" + this.getOrderDate();
    }


    @Override
    public int compareTo(Order order) {
        return 0;
    }
}
