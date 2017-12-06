package club.enlight.foodtruck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import club.enlight.foodtruck.data.CanvasView;

/**
 * Activity to display a whiteboard for drawing
 */

public class CanvasActivity extends AppCompatActivity {
    private static final String TAG = CanvasActivity.class.getSimpleName();

    private CanvasView whiteBoard;
    private Button postButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postButton = (Button) findViewById(R.id.postButton);
        setContentView(R.layout.activity_canvas);
    }

    public void post(View v) {
        CanvasView smallBoard = (CanvasView) findViewById(R.id.whiteBoard);
        String map = smallBoard.privatePost(v, truckId);
        Intent intent = new Intent(this, FoodTruckActivity.class);
        intent.putExtra("map", map);
        startActivity(intent);
    }
}
