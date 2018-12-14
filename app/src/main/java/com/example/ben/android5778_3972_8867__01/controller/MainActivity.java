package com.example.ben.android5778_3972_8867__01.controller;


//import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.AsyncTask;

import com.example.ben.android5778_3972_8867__01.R;
import com.example.ben.android5778_3972_8867__01.model.backend.BackendFactory;
import com.example.ben.android5778_3972_8867__01.model.datasource.Firebase_DBManager;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {
    EditText editTextDestination;
    EditText editTextEmail;
    EditText editTextPhone;
    Button buttonAddRide;
    BackendFactory b;
    //  BackendFactory b = new BackendFactory(this);
    Firebase_DBManager firebase_dbManager;
    LocationManager locationManager; //for what you need again??
    LocationListener locationListener;
    String currentLocationString, destinationLocationString;
    myAsyncTask m = new myAsyncTask();
    PlaceAutocompleteFragment autocompleteFragment; //see import..
    Location locationA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    @SuppressLint("CutPasteId")
    public void findViews() {
        //editTextDestination = (EditText) findViewById(R.id.editTextDestination);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);//refrince our object to the place in the layout
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        buttonAddRide = (Button) findViewById(R.id.buttonAddRide);
        autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        locationA = new Location("A");//= new Location(from);
        autocompleteFragment.setHint("Enter Destination...");
        //   placeAutocompleteFragment1.

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                locationA.setLatitude(place.getLatLng().latitude);
                locationA.setLongitude(place.getLatLng().longitude);
                // .getAddress().toString();//get place details here
            }

            @Override
            public void onError(Status status) {

            }
        });
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
//todo- location.toString();
                currentLocationString = getPlace(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        getCurrentLocation();
        m.execute();
    }

    //checks if email input is valid.
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void checkInput() throws Exception {
        if (!isValidEmail(editTextEmail.getText()))
            throw new Exception("Not vaild email!");
        if (!PhoneNumberUtils.isGlobalPhoneNumber(editTextPhone.toString()))
            throw new Exception("Not valid phone");


    }

    private class myAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            BackendFactory b = new BackendFactory();
            firebase_dbManager = b.getInstance();
            buttonAddRide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        getCurrentLocation();
                        currentLocationString = getPlace(getLastKnownLocation());
                        destinationLocationString = getPlace(locationA);
                        checkInput();
                        //    curretLocationString=getPlace(locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER));
                        firebase_dbManager.addRide(currentLocationString, destinationLocationString, editTextEmail.getText().toString()
                                , editTextPhone.getText().toString());
                        Toast t = Toast.makeText(getBaseContext(), "succesfully uploaded to firebase", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                        t.show();
                        autocompleteFragment.setText("");
                        editTextEmail.setText("");
                        editTextPhone.setText("");
                    } catch (Exception e) {
                        Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            return null;
        }
    }

    public void benstryingtobesmart() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)  {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    public Location getLastKnownLocation() {
        Location l = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            //  l = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            l = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);

        }
        return l;
    }

    public void getCurrentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        }
    }

    //if didnt have permission, tried getting permission. coming back from requestPermissions here.
    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }

    }
    //location.toString()
    public String getPlace(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                int i = 0;
                String address = "";
                while (addresses.get(0).getAddressLine(i) != null) {
                    address += addresses.get(0).getAddressLine(i);
                    //    String stateName = addresses.get(0).getAddressLine(1);
                    //  String countryName = addresses.get(0).getAddressLine(2);
                    i++;
                }
                return /*stateName + "\n" +*/ address + "\n"/* + countryName*/;
            }
            return "no place: \n (" + location.getLongitude() + " , " + location.getLatitude() + ")";
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }

}
