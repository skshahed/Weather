package com.example.forever.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener{

   // private FrameLayout container;
   // private Fragment fragment;
    private ViewPageAdapter mPagerAdapter;
    private ViewPager viewPager;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Geocoder geocoder;
    private String currentLat = null, currentLng = null;
    private List<Address> addresslist;
    private Location locationset;
    public String apiurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //-------------------------FOR LOCATION
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        geocoder = new Geocoder(this);

        locationset = new Location(this);



        //--------------------------For TAB VIEW

        viewPager = (ViewPager) findViewById(R.id.container);
        mPagerAdapter =new ViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }
    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = LocationRequest.create()
                .setInterval(1000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(android.location.Location location) {

       // locationset.clearLocation();
        Toast.makeText(this, "changed", Toast.LENGTH_SHORT).show();


        currentLat =  String.valueOf(location.getLatitude());
        currentLng =  String.valueOf(location.getLongitude());


        try {

            addresslist = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            String city = addresslist.get(0).getLocality();
            Toast.makeText(this, ""+city, Toast.LENGTH_SHORT).show();
            //locationset.addCity(city);
            locationset.addLocation(currentLat,currentLng,city);
            //locationset.addLongitude(currentLng);

            //apiurl="data/2.5/forecast/daily?q="+locationset.getCity()+"&appid=a226ec225f23ea5717f7fa94ce785237";

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
