package club.enlight.foodtruck;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import club.enlight.foodtruck.data.local.DbBitmapUtility;
import club.enlight.foodtruck.data.local.DbHelper;
import club.enlight.foodtruck.data.model.Doodle;

/**
 * Activity to display information for selected food truck such as location, menu, schedule etc
 */

public class FoodTruckActivity extends AppCompatActivity {
    private static final String TAG = FoodTruckActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck);
        LinearLayout gallery = (LinearLayout) findViewById(R.id.imageGallery);
        if (getIntent().hasExtra("map")) {
            String map = getIntent().getStringExtra("map");
            Bitmap bitmap = DbBitmapUtility.getImage(map);
            gallery.addView(makeView(bitmap));
            Log.d(TAG, "The current number of pictures is: " + gallery.getChildCount());
        }

        populateGallery(gallery);

    }

    private void populateGallery(LinearLayout gallery) {
        DbHelper db = new DbHelper(this);
        //once this exists, or something similar
        ArrayList<Doodle> pics = db.getImagesByTruck(truckId);
        for (Doodle pic : pics) {
            Bitmap bitmap = DbBitmapUtility.getImage(pic.getRawImage());
            gallery.addView(makeView(bitmap));
        }
    }

    private View makeView(Bitmap bitmap) {
        ImageView tempImage = new ImageView(getApplicationContext());
        tempImage.setImageBitmap(bitmap);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 10, 0);
        tempImage.setLayoutParams(lp);
        return tempImage;
    }

    public void goToCanvas(View v) {
        Intent intent = new Intent(this, CanvasActivity.class);
        startActivity(intent);
    }
}
