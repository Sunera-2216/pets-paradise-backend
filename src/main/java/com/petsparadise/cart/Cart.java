package com.petsparadise.cart;

import com.petsparadise.buyer.Buyer;
import com.petsparadise.product.Product;
import com.petsparadise.seller.Seller;

public class Cart {
    private String cartId;
    private Buyer buyer;
    private Seller seller;
    private Product product;

    public Cart() {
    }

    public Cart(String cartId, Buyer buyer, Seller seller, Product product) {
        this.cartId = cartId;
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    
}

