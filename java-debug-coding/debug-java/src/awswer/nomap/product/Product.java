package awswer.nomap.product;

import java.time.LocalDate;

public class Product {
    private String productCode;
    private String productName;
    private double price;
    private int quantity;
    private LocalDate manufactureDate;
    private LocalDate expirationDate;

    public String getProductCode() {
        if(productCode.charAt(0) != 'P')
            return null;
        else
            return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        if(price <=0)
            return 0;
        else
            return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        if(quantity <=0)
            return 0;
        else
            return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public LocalDate getExpirationDate() {
        if(!expirationDate.isAfter(manufactureDate))
            return manufactureDate;
        else
            return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public Product() {
        this.productCode = null;
        this.productName = null;
        this.price = 0.0;
        this.quantity = 0;
        this.manufactureDate = null;
        this.expirationDate = null;
    }

    public Product(String productCode, String productName, double price, int quantity, LocalDate manufactureDate, LocalDate expirationDate) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", manufactureDate=" + manufactureDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
