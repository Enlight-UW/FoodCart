package club.enlight.foodtruck.data.model;

import android.support.annotation.NonNull;

/**
 * Created by jzmai on 10/17/17.
 */

public class MenuItem implements java.lang.Comparable<MenuItem> {
    private String name;
    private String category;
    private double price;
    private String description;

    // Todo: implement class constructor

    // Todo: implement setter and getter functions

    // Todo: implement comparator function to sort by category, then name
    @Override
    public int compareTo(@NonNull MenuItem T) {
        return 0;
    }
}
