/*
Nathanael Obrey
11/14/2025
CPSC-39 Final Project
*/

public class Purchase {
    private String productName;
    private double price;

    public Purchase(String productName, double price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() { return productName; }
    public double getPrice() { return price; }
}
