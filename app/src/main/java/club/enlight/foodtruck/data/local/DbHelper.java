package club.enlight.foodtruck.data.local;

import club.enlight.foodtruck.data.model.FoodTruck;

/**
 * Database helper class
 * Used to save and store data to database
 * Created by jzmai on 10/17/17.
 */

public class DbHelper {


    /**
     * Takes FoodTruck object and pushes it to the database
     *
     * @param truck truck to push to database
     * @return
     */
    public static int pushFoodTruck(FoodTruck truck){

        return -1;
    }

    /**
     * Takes FoodTruck object and pulls data from
     *
     * @param truck
     * @return
     */
    public static FoodTruck getFoodTruck(FoodTruck truck){
        // update data in FoodTruck object from database

        if( truck.getId() < 0 ) return null;

        return truck;
    }

    public static FoodTruck getFoodTruck(int id){
        FoodTruck truck = new FoodTruck(id);
        return getFoodTruck(truck);
    }

    /**
     * Constructs FoodTruck object from data queried from database based on id parameter
     *
     * @param id Unique ID of FoodTruck to query from database
     * @return FoodTruck with matching ID. Null if no truck exists
     */
    public static FoodTruck getTruckById(int id){
        FoodTruck result = new FoodTruck();
        return result;
    }



}
