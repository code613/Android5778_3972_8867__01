package com.example.ben.android5778_3972_8867__01.controller;


import android.app.Dialog;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
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
import com.example.ben.android5778_3972_8867__01.model.backend.Const;
import com.example.ben.android5778_3972_8867__01.model.datasource.Firebase_DBManager;
import com.example.ben.android5778_3972_8867__01.model.datasource.Utils;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText editTextClientId;
    private EditText editTextClientName;
    private EditText editTextEmail;
    private EditText editTextPhone;

    private Button findLocationButton;
    private Button sendClientButton;

    private TextView distanceTextView;
    // BackendFactory b;
    //  BackendFactory b = new BackendFactory(this);
    //Firebase_DBManager firebase_dbManager;
    LocationManager locationManager; //for what you need again??
    LocationListener locationListener;
    //String currentLocationString, destinationLocationString;
    //myAsyncTask m = new myAsyncTask();

    private PlaceAutocompleteFragment placeAutocompleteFragment1;
    private PlaceAutocompleteFragment placeAutocompleteFragment2;

    Location locationA = new Location("A");//= new Location(from);
    Location locationB = new Location("B");//= new Location(to);


    @SuppressLint("CutPasteId")
    public void findViews() {
        placeAutocompleteFragment1 = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment1);
        placeAutocompleteFragment2 = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment2);
        placeAutocompleteFragment1.setHint("כתובת  מוצא");
        placeAutocompleteFragment2.setHint("כתובת היעד");

        editTextClientId = (EditText) findViewById(R.id.editTextClientId);
        editTextClientName = (EditText) findViewById(R.id.editTextClientName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);//refrince our object to the place in the layout
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        findLocationButton = (Button) findViewById(R.id.findLocationButton);
        findLocationButton.setOnClickListener(this);
        sendClientButton = (Button) findViewById(R.id.sendClientButton);
        sendClientButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    final Dialog dialog = new Dialog(MainActivity.this);
                                                    dialog.setContentView(R.layout.dialog_add_client);
                                                    dialog.show();

                                                    //כפתור שנמצא בתוך הדיאלוג
                                                    Button dialog_cancel = dialog.findViewById(R.id.dialog_cancel);
                                                    dialog_cancel.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            dialog.dismiss();
                                                        }
                                                    });

                                                    Button dialog_ok = dialog.findViewById(R.id.dialog_ok);  //כפתור שנמצא בתוך הדיאלוג
                                                    dialog_ok.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            addClient();
                                                            dialog.dismiss();


                                                        }
                                                    });
                                                }
                                            });

        distanceTextView = (TextView) findViewById(R.id.distanceTextView);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {

                //showSunTimes(location.getLatitude(), location.getLongitude()); /// ...

                // Called when a new location is found by the network location provider.
                //    Toast.makeText(getBaseContext(), location.toString(), Toast.LENGTH_LONG).show();
                //locationTextView.setText(getPlace(location));////location.toString());
                placeAutocompleteFragment1.setText(getPlace(location));
                locationA=location;

                // Remove the listener you previously added
                //  locationManager.removeUpdates(locationListener);
            }


            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        placeAutocompleteFragment1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                locationA.setLatitude(place.getLatLng().latitude);
                locationA.setLongitude(place.getLatLng().longitude);
                // showDistance();
                // .getAddress().toString();//get place details here
            }

            @Override
            public void onError(Status status) {

            }
        });

        placeAutocompleteFragment2.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                //  to = place.getAddress().toString();//get place details here
                locationB.setLatitude(place.getLatLng().latitude);
                locationB.setLongitude(place.getLatLng().longitude);
                showDistance();
            }

            @Override
            public void onError(Status status) {

            }
        });


    }
        //autocompleteFragment = (PlaceAutocompleteFragment)
         //       getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
       // locationA = new Location("A");//= new Location(from);
        //autocompleteFragment.setHint("Enter Destination...");
        //   placeAutocompleteFragment1.

        //buttonAddRide.setOnClickListener( this);
        //autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
           // @Override
           // public void onPlaceSelected(Place place) {
           //     locationA.setLatitude(place.getLatLng().latitude);
           //     locationA.setLongitude(place.getLatLng().longitude);
                // .getAddress().toString();//get place details here
           // }

           // @Override
          //  public void onError(Status status) {

          //  }
       // });
       // locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
       // locationListener = new LocationListener() {
        //    @Override
            //public void onLocationChanged(Location location) {
//todo- location.toString();
            //    currentLocationString = getPlace(location);
          //  }

         //   @Override
         //   public void onStatusChanged(String provider, int status, Bundle extras) {

          //  }

          //  @Override
          //  public void onProviderEnabled(String provider) {

          //  }

          //  @Override
          //  public void onProviderDisabled(String provider) {

          //  }
       // };
       // getCurrentLocation();
       // m.execute();
   // }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }


    @SuppressLint("StaticFieldLeak")
    private void getLocation() {

        //     Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);

        } else {

            // getting GPS status
            Boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            Boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            // getting network status
            Boolean isPassivenabled = locationManager
                    .isProviderEnabled(LocationManager.PASSIVE_PROVIDER);





            if (isGPSEnabled) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                if (locationManager != null) {
                    Location location = locationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (location != null) {

                        //latitude = location.getLatitude();
                        //longitude = location.getLongitude();
                    }
                }

            }
            else if ( isNetworkEnabled) {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

                if (locationManager != null) {
                    Location location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if (location != null) {

                        //latitude = location.getLatitude();
                        //longitude = location.getLongitude();
                    }
                }
                return;
            }
            else if (isPassivenabled) {

                locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);

                if (locationManager != null) {
                    Location location = locationManager
                            .getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                    if (location != null) {

                        //latitude = location.getLatitude();
                        //longitude = location.getLongitude();
                    }
                }
                return;
            }
            else
            {
                Toast.makeText(this, "cant access location PROVIDER", Toast.LENGTH_SHORT).show();
            }








            // Android version is lesser than 6.0 or the permission is already granted.
            //stopUpdateButton.setEnabled(true);
            //getLocationButton.setEnabled(false);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);


            if (addresses.size() > 0) {
                String cityName = addresses.get(0).getAddressLine(0);
//              String stateName = addresses.get(0).getAddressLine(1);
                // String countryName = addresses.get(0).getAddressLine(2);
                return cityName; //+ "\n" + stateName + "\n" + countryName;
            }

            return "no place: \n (" + location.getLongitude() + " , " + location.getLatitude() + ")";
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                //stopUpdateButton.setEnabled(true);
                //getLocationButton.setEnabled(false);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onClick(View v) {
//        if (v == sendClientButton)
//            addClient();
        if (v == findLocationButton)
            getLocation();

//            if (v == searchButton) {
//
//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//
//                try {
//                    startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//            }

    }

    @SuppressLint("StaticFieldLeak")
    private void addClient() {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(Const.ClientConst.ID, this.editTextClientId.getText().toString());
        contentValues.put(Const.ClientConst.NAME, this.editTextClientName.getText().toString());
        contentValues.put(Const.ClientConst.PHONE, this.editTextPhone.getText().toString());
        contentValues.put(Const.ClientConst.EMAIL, this.editTextEmail.getText().toString());
        //Long x = BackendFactory.getDB().addClient(contentValues);


        new AsyncTask<Void, Void, Long>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //openDialog();
                Toast.makeText(getBaseContext(),
                        "send request.... ", Toast.LENGTH_SHORT).show();

            }

            @Override
            protected Long doInBackground(Void... params) {
                try {
                    return BackendFactory.getDB().addClient(contentValues, locationA, locationB, new Utils.Action<Long>() {
                        @Override
                        public void onSuccess(Long obj) {
                            Toast.makeText(getBaseContext(), "Upload successful id: " + obj, Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onFailure(Exception exception) {
                            Toast.makeText(getBaseContext(), "Error \n" + exception.getMessage(), Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onProgress(String status, double percent) {

                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Error ", Toast.LENGTH_LONG).show();
                    return Long.valueOf(0);
                }
            }

            @Override
            protected void onPostExecute(Long aLong) {
//
//                if (aLong == Long.valueOf(0))
//                    Toast.makeText(getBaseContext(), "problem with uploud", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(getBaseContext(), "client request added ", Toast.LENGTH_LONG).show();
            }

        }.execute();
    }
    //checks if email input is valid.
        /*
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void checkInput() throws Exception {
        if (!isValidEmail(editTextEmail.getText()))
            throw new Exception("Not valid email!");
        if (!PhoneNumberUtils.isGlobalPhoneNumber(editTextPhone.toString()))
            throw new Exception("Not valid phone");


    }*/
/*
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
    }*/
        private void showDistance() {

            float[] results = new float[1];
            Location.distanceBetween(locationA.getLatitude(), locationA.getLongitude(),
                    locationB.getLatitude(), locationB.getLongitude(), results);

            float distance = locationA.distanceTo(locationB);

            if (distance > 1000)
                distanceTextView.setText("Distance : " + distance / 1000 + " km");
            else {
                distanceTextView.setText("Distance :" + distance + " meter");
            }
        }

        public void openDialog() {
            final Dialog dialog = new Dialog(this); // Context, this, etc.
            dialog.setContentView(R.layout.dialog_add_client);
            dialog.setTitle("1111111");
            dialog.show();
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
   // @SuppressLint("MissingPermission")
  //  @Override
  /*  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the location", Toast.LENGTH_SHORT).show();
            }
        }

    }*/
    //location.toString()
  /*  public String getPlace(Location location) {
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
                return /*stateName + "\n" + address + "\n" + countryName;
            }
            return "no place: \n (" + location.getLongitude() + " , " + location.getLatitude() + ")";
        } catch (
                IOException e)

        {
            e.printStackTrace();
        }
        return "IOException ...";
    }*/

}
