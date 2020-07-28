package cls.development.travelrush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.smartcar.sdk.SmartcarAuth;
import com.smartcar.sdk.SmartcarCallback;
import com.smartcar.sdk.SmartcarResponse;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_main);

        initViews();
        initSmartCar();


        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.SATELLITE, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments


                    }
                });

            }
        });

    }

    private void initViews() {
        mapView = (MapView) findViewById(R.id.mapView);


    }

    private void initSmartCar(){
        Log.d(TAG,"Hey");

        SmartcarAuth smartcarAuth = new SmartcarAuth(
                "1d3a636f-54fb-4c75-8bbe-849b7c308b84",
                "sc1d3a636f-54fb-4c75-8bbe-849b7c308b84://cls-development.com/callback",
                new String[] {"read_vehicle_info", "read_odometer"},

                // Create a callback to handle the redirect response
                new SmartcarCallback() {
                    @Override
                    public void handleResponse(SmartcarResponse smartcarResponse) {
                        // Retrieve the authorization code
                        Log.d("SmartcarAuth", "Authorization code: " + smartcarResponse.getCode());
                    }
                });
        smartcarAuth.launchAuthFlow(getApplicationContext());

    }
}