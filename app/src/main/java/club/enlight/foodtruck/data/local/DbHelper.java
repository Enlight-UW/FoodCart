package club.enlight.foodtruck.data.local;

import club.enlight.foodtruck.data.model.FoodTruck;

/**
 * Database helper class
 * Used to save and store data to database
 * Created by jzmai on 10/17/17.
 */

public class DbHelper {


    /**
     * Constructs FoodTruck object from data queried from database based on id parameter
     *
     * @param id Unique ID of FoodTruck to query from database
     * @return FoodTruck with matching ID. Null if no truck exists
     */
    public FoodTruck getTruckById(int id){
        FoodTruck result = new FoodTruck();

        return result;
    }

}
