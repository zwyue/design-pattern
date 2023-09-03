package com.zwyue.entity;

import com.zwyue.record.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

@ToString
public class ShoppingCart {

    @Getter
    private int itemsCount = 0;

    @Getter
    private double totalPrice = 0.0;

    private final List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public void remove(Item item) {
        this.items.remove(item) ;
        this.itemsCount =  this.itemsCount-1;
        this.totalPrice =  this.totalPrice-item.price();
    }

    public void clear() {
        this.items.clear();
        this.itemsCount = 0;
        this.totalPrice = 0.0;
    }

    public void add(Item item) {
        this.items.add(item);
        this.itemsCount = this.itemsCount + 1;
        this.totalPrice = this.totalPrice + item.price();
    }
}
