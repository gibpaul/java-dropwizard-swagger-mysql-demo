package org.kainos.ea.cli;

public class Customer  {

    private int customerID;
    private String name;
    private String address;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    private long phone;


    public Customer(int customerID, String name, String address, long phone) {
        this.setCustomerID(customerID);
        this.setName(name);
        this.setAddress(address);
        this.setPhone(phone);
    }

}
