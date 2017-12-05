package club.enlight.foodtruck.data.model;

import android.support.annotation.NonNull;

/**
 * Created by jzmai on 10/17/17.
 */

public class MenuItem implements java.lang.Comparable<MenuItem> {
    public final double id;
    public final double truckId;
    private String name;
    private String category;
    private double price;
    private String description;

    // Todo: implement class constructor
    public MenuItem(double id, double truckId){
        this.id = id;
        this.truckId = truckId;
    }
    public MenuItem(){
        this.id = -1;
        this.truckId = -1;
    }
    // Todo: implement setter and getter functions

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Todo: implement comparator function to sort by category, then name
    @Override
    public int compareTo(@NonNull MenuItem T) {
        return 0;
    }
}
