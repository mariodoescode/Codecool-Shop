package com.codecool.shop.model;

import java.math.BigDecimal;
import java.util.Currency;

public class Product extends BaseModel {

    private BigDecimal defaultPrice;
    private String defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;
    private int quantity;
    private int supplierID;
    private int categoryID;


    public Product(String name, BigDecimal defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        quantity = 1;
    }

    public Product(String name, BigDecimal defaultPrice, String currencyString, String description, String categoryName, String supplierName, String categoryDepartment
    , String categoryDescription, String supplierDescription) {
        super(name,description);
        this.defaultPrice = defaultPrice;
        this.defaultCurrency =  currencyString;
        this.description = description;
        this.productCategory = new ProductCategory(categoryName,categoryDepartment,categoryDescription);
        this.supplier = new Supplier(supplierName,supplierDescription);
    }

    public Product(String name, BigDecimal defaultPrice, String currencyString, String description, ProductCategory productCategory, int supplierID) {
        super(name,description);
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = currencyString;
        this.description = description;
        this.productCategory = productCategory;
        this.supplierID = supplierID;
    }

    public int getQuantity() {

        return quantity;
    }


    public String getSubtotalPrice() {
        return String.valueOf(this.defaultPrice.multiply(BigDecimal.valueOf(quantity))) + " " + this.defaultCurrency;
    }

    public BigDecimal getTotalPrice() {
        return this.defaultPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency;
    }

    public void setPrice(BigDecimal price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = currency;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
        this.productCategory.addProduct(this);
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
        this.supplier.addProduct(this);
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int id) {
        this.supplierID = id;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency,
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
