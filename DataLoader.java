/*
Nathanael Obrey
11/14/2025
CPSC-39 Final Project
*/

import java.io.*;
import java.util.*;

public class DataLoader {

    // load customers from CSV file, keyed by customerID - (O(1) average time lookup)
    // takes param filename, returns map and adds a new customer
    // customers.csv file format: customerID,lastName,firstName,phone
    public static Map<Integer, Customer> loadCustomers(String filename) throws IOException {
        Map<Integer, Customer> customers = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) { //buffered reader, similar to scanner but for files
            String line = br.readLine(); // skip header
            // while loop to read each line, and split the data into parameters to be put into Customer object, with comma delimiter
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String lastName = parts[1];
                String firstName = parts[2];
                String phone = parts[3];
                customers.put(id, new Customer(id, lastName, firstName, phone));
            } // end while
        } // end try
        return customers;
    } // end loadCustomers

    // same as above, but for purchases - links purchases to customers via customerID (O(1) average time lookup)
    // purchases.csv file format: customerID,productName,price
    public static void loadPurchases(String filename, Map<Integer, Customer> customers) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String product = parts[1];
                double price = Double.parseDouble(parts[2]);

                Customer c = customers.get(id);
                if (c != null) {
                    c.addPurchase(new Purchase(product, price));
                } // end if
            } // end while
        } // end try
    } // end loadPurchases

    // save customers to CSV (overwrites file)
    // Note: saves all customers in the collection passed to it, and completely bypasses using setters in Customer class
    public static void saveCustomers(String filename, Collection<Customer> customers) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("customerID,lastName,firstName,phone");
            bw.newLine();
            for (Customer c : customers) {
                bw.write(c.getCustomerID() + "," + c.getLastName() + "," + c.getFirstName() + "," + c.getPhone());
                bw.newLine();
            } // end for
        } // end try
    } // end saveCustomers

    // save purchases to CSV (overwrites file)
    public static void savePurchases(String filename, Collection<Customer> customers) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write("customerID,productName,price");
            bw.newLine();
            for (Customer c : customers) {
                for (Purchase p : c.getPurchases()) {
                    bw.write(c.getCustomerID() + "," + p.getProductName() + "," + p.getPrice());
                    bw.newLine();
                } // end for
            } // end for
        } // end try
    } // end savePurchases
} // end DataLoader class
