package club.enlight.foodtruck;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import static java.lang.Math.toIntExact;

import club.enlight.foodtruck.data.local.DbHelper;
import club.enlight.foodtruck.data.model.FoodTruck;
import club.enlight.foodtruck.util.Debug;
public class MainActivity extends AppCompatActivity {
    private boolean hasLocation;
    private DbHelper mydb;
    private FusedLocationProviderClient mFusedLocationClient;
    private ArrayList<FoodTruck> array_list;
    private ListView mListView;

    private TextView mTextMessage;
    private Button mButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mydb = new DbHelper(this);
        hasLocation = false;
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                Debug.addDummyTrucks(5,location,mydb);

                                array_list = mydb.getNearbyTrucks(location);
                                hasLocation = true;
                            }
                        }
                    });
        }

        while(!hasLocation){
            //busy wait, not ideal
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        mListView = (ListView)findViewById(R.id.listView1);
        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                // TODO Auto-generated method stub
                long id_To_Search = array_list.get( (int)id ).id;

                Bundle dataBundle = new Bundle();
                dataBundle.putLong("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(),FoodTruckActivity.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

        mTextMessage = (TextView) findViewById(R.id.message);
        mButton = (Button) findViewById(R.id.button2);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.item1:Bundle dataBundle = new Bundle();
                dataBundle.putLong("id", -1);

                Intent intent = new Intent(getApplicationContext(),AddFoodTruckActivity.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void goToFoodTruck(View v) {
        Intent intent = new Intent(this, FoodTruckActivity.class);
        startActivity(intent);
    }



}
