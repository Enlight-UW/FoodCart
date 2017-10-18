package club.enlight.foodtruck.data.model;

/**
 * Created by jzmai on 10/17/17.
 */

public class FoodTruck implements Serializable {
    private String name;                // Display Name
    private int id;                     // Unique ID number
    private Menu menu;
    private Schedule schedule;
    private boolean acceptsCreditCard;

    public FoodTruck(String name){

    }

    // TODO: Implement setter and getter functions
    // TODO: Implement comparable interface?

}
