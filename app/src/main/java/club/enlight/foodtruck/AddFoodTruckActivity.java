package club.enlight.foodtruck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity used to create a new food truck item to be stored in database
 *
 * Take user input from text fields to create a new food truck item
 *
 */
public class AddFoodTruckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_truck);
    }
}
