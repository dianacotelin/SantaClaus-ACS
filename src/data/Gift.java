package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Gift {
    private String productName;
    private double price;
    private String category;
    @JsonIgnore private Integer quantity;

    public Gift(final String productName, final double price,
                final String category, final Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Gift{"
                +
                "productName='" + productName + '\''
                +
                ", price=" + price
                +
                ", category='" + category + '\''
                +
                '}';
    }
}
