package club.enlight.foodtruck.data.model;

/**
 * Created by jzmai on 10/17/17.
 */

public class FoodTruck {
    private String name;                // Display Name
    private int id;                     // Unique ID number
    private Menu menu;
    private Schedule schedule;
    private boolean acceptsCreditCard;

    /**
     * Test constructor
     *
     */
    public FoodTruck(){
        name = "Default Name";
        id = 123456;
        menu = new Menu();
        schedule = new Schedule();
        acceptsCreditCard = false;
    }

    public FoodTruck(int id){
        this.id = id;
        name = "Default Name";
        menu = new Menu();
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
    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public Menu getMenu(){
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
// TODO: Implement comparable interface?

}
