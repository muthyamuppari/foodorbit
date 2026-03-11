package com.alpha.foodorbit.dto;

import com.alpha.foodorbit.entities.CartItem;
import com.alpha.foodorbit.entities.Coupon;

import java.util.List;

public class CartResponseDto {

        private List<CartItem> cartItems;
        private List<Coupon> coupons;

    public CartResponseDto(List<CartItem> cartItems, List<Coupon> coupons) {
        this.cartItems = cartItems;
        this.coupons = coupons;
    }

    public CartResponseDto() {
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }
}
