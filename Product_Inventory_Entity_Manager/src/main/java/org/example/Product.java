package org.example;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private int stockQuantity;
    public Product(String productId, String productName, double price, int stockQuantity){
        this.productId=productId;
        this.productName=productName;
        this.price=price;
        this.stockQuantity=stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getStockQuantity(){
        return stockQuantity;
    }
    public void setStockQuantity(int stockQuantity){
        this.stockQuantity=stockQuantity;
    }
    public void applyDiscount(double percentage){
        price=price-(price*percentage/100);
    }
};
