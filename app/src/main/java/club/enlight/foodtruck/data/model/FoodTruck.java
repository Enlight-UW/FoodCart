package club.enlight.foodtruck.data.model;

import java.util.ArrayList;

/**
 * Created by jzmai on 10/17/17.
 */

public class FoodTruck {
    public final double id;                     // Unique ID number

    private String name;                // Display Name
    private ArrayList<MenuItem> menu;
    private Schedule schedule;
    private double Latitude;
    private double Longitude;
    private boolean acceptsCreditCard;

    /**
     * Test constructor
     *
     */
    public FoodTruck(){
        name = "Default Name";
        id = 123456;
        menu = new ArrayList<MenuItem>();
        schedule = new Schedule();
        acceptsCreditCard = false;
    }

    public FoodTruck(int id){
        this.id = id;
        name = "Default Name";
        menu = new ArrayList<MenuItem>();
        schedule = new Schedule();
        acceptsCreditCard = false;
    }

    // TODO: Implement setter and getter functions
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }
    public ArrayList<MenuItem> getMenu(){
        return menu;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public boolean isAcceptsCreditCard() {
        return acceptsCreditCard;
    }

    public void setAcceptsCreditCard(boolean acceptsCreditCard) {
        this.acceptsCreditCard = acceptsCreditCard;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    // TODO: Implement comparable interface?

}
