/*
Nathanael Obrey
11/14/2025
CPSC-39 Final Project
*/

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerID;
    private String lastName;
    private String firstName;
    private String phone;
    private List<Purchase> purchases;

    public Customer(int customerID, String lastName, String firstName, String phone) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.purchases = new ArrayList<>();
    }

    //getters - note, no setters, as i will not be modifying customer info for this project as of now
    public int getCustomerID() { return customerID; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getPhone() { return phone; }
    public List<Purchase> getPurchases() { return purchases; }

    public void addPurchase(Purchase p) {
        purchases.add(p);
    }
}
