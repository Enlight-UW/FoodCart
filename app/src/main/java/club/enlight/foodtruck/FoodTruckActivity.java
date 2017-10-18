package club.enlight.foodtruck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity to display information for selected food truck such as location, menu, schedule etc
 */

public class FoodTruckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck);
    }
}
