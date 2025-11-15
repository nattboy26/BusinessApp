/*
Nathanael Obrey
11/14/2025
CPSC-39 Final Project
*/

import java.util.*;

public class BusinessApp {
    public static void main(String[] args) throws Exception {
        Map<Integer, Customer> customers = DataLoader.loadCustomers("customers.csv");
        DataLoader.loadPurchases("purchases.csv", customers);

        Scanner sc = new Scanner(System.in);
        int choice;

        //execute the following code, until the user decides to exit
        do {
            System.out.println("\n=== Business App Menu ===");
            System.out.println("1. Add Customer");
            System.out.println("2. Search Customer by ID");
            System.out.println("3. List All Customers");
            System.out.println("4. View Purchase History");
            System.out.println("5. Save Data");
            System.out.println("6. Exit");
            // TODO: Add Delete option
            // - Add a menu entry (e.g. "7. Delete Customer" or renumber existing items)
            // - Implement a method `deleteCustomer(Scanner sc, Map<Integer, Customer> customers)`
            // - Validate ID exists, remove from the `customers` map, and optionally remove related purchases

            // TODO: Add Transaction option
            // - Add a menu entry for creating transactions (customer buys an item)
            // - Implement `addTransaction(Scanner sc, Map<Integer, Customer> customers)` which:
            //     * Prompts for Customer ID, product name, and price
            //     * Adds a new `Purchase` to the customer's `purchases` list
            //     * Marks data dirty / ensure `saveData` persists new transactions to `purchases.csv`

            // TODO: Running total for purchase history (use recursion)
            // - When selecting "4. View Purchase History", show a running total at the bottom
            // - Implement a recursive helper like `double runningTotal(List<Purchase> list, int idx)` that sums prices
            // - Display the total after listing purchases

            // TODO: Add Return/Refund option
            // - Add a menu entry for returning products
            // - Implement `processReturn(Scanner sc, Map<Integer, Customer> customers)` which:
            //     * Prompts for Customer ID, product name (or purchase index), and optionally refund amount
            //     * Removes or adjusts the matching `Purchase` from the customer's `purchases` list
            //     * Ensures `saveData` will persist the change to `purchases.csv`

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCustomer(sc, customers);
                    break;
                case 2:
                    searchCustomer(sc, customers);
                    break;
                case 3:
                    listCustomers(customers);
                    break;
                case 4:
                    viewPurchases(sc, customers);
                    break;
                case 5:
                    saveData(customers);
                    break;
                case 6:
                    System.out.print("Would you like to save changes before exiting? (Y/N): ");
                    String saveChoice = sc.nextLine().trim().toLowerCase();
                    if (saveChoice.equals("y") || saveChoice.equals("yes")) {
                        saveData(customers);
                    }
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    private static void addCustomer(Scanner sc, Map<Integer, Customer> customers) {
        System.out.print("Enter 5-digit Customer ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        Customer c = new Customer(id, lastName, firstName, phone);
        customers.put(id, c);
        System.out.println("Customer added!");
        // Optionally: append to customers.csv here
    }

    private static void searchCustomer(Scanner sc, Map<Integer, Customer> customers) {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        Customer c = customers.get(id);
        if (c != null) {
            System.out.println("Found: " + c.getFirstName() + " " + c.getLastName() + " (" + c.getPhone() + ")");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void listCustomers(Map<Integer, Customer> customers) {
        for (Customer c : customers.values()) {
            System.out.println(c.getCustomerID() + " - " + c.getFirstName() + " " + c.getLastName());
        }
    }

    private static void viewPurchases(Scanner sc, Map<Integer, Customer> customers) {
        System.out.print("Enter Customer ID: ");
        int id = sc.nextInt();
        Customer c = customers.get(id);
        if (c != null) {
            System.out.println("Purchase History for " + c.getFirstName() + " " + c.getLastName() + ":");
            for (Purchase p : c.getPurchases()) {
                System.out.println(" - " + p.getProductName() + " ($" + p.getPrice() + ")");
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void saveData(Map<Integer, Customer> customers) {
        try {
            DataLoader.saveCustomers("customers.csv", customers.values());
            DataLoader.savePurchases("purchases.csv", customers.values());
            System.out.println("Data saved to customers.csv and purchases.csv");
        } catch (java.io.IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
