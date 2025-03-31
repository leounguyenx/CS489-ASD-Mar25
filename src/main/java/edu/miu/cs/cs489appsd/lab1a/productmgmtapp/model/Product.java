package edu.miu.cs.cs489appsd.lab1a.productmgmtapp.model;
public class Product {
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateSupplied() {
        return dateSupplied;
    }

    public void setDateSupplied(String dateSupplied) {
        dateSupplied = dateSupplied;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Product(long productId, String name, String dateSupplied, int quantityInStock, double unitPrice) {
        this.productId = productId;
        this.name = name;
        this.dateSupplied = dateSupplied;
        this.quantityInStock = quantityInStock;
        this.unitPrice = unitPrice;
    }
    public Product(){};

    long productId;
    String name;
    String dateSupplied;
    int quantityInStock;
    double unitPrice;

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', dateSupplied='%s', quantityInStock=%d, unitPrice=%.2f}",
                productId, name, dateSupplied, quantityInStock, unitPrice);
    }
}
