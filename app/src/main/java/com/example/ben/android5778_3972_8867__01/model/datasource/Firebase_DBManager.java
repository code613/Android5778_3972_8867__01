package com.example.ben.android5778_3972_8867__01.model.datasource;

import android.content.ContentValues;
import android.location.Location;
import android.support.annotation.NonNull;

import com.example.ben.android5778_3972_8867__01.model.entities.Locationf;
import com.example.ben.android5778_3972_8867__01.model.backend.Backend;
import com.example.ben.android5778_3972_8867__01.model.entities.Client;
import com.example.ben.android5778_3972_8867__01.model.entities.ClientRequestStatus;
import com.example.ben.android5778_3972_8867__01.model.entities.Timestampf;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.ben.android5778_3972_8867__01.model.backend.Const.ContentValuesToClient;

public class Firebase_DBManager implements Backend {

    static FirebaseDatabase database;
    static DatabaseReference clientsRef;
    static List<Client> clientList;
    static boolean flag;

    static {
        database = FirebaseDatabase.getInstance();
        clientsRef = database.getReference("clients");
        clientList = new ArrayList<>();
        flag = false;
    }


    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);
    }

    @Override
    public Long addClient(ContentValues values, Location a, Location b, final Utils.Action<Long> action) {

        final Client client = ContentValuesToClient(values);

        Locationf aa=new Locationf(a);
        Locationf bb=new Locationf (b);
        client.setStartPoint(aa);
        client.setDestinationPoint(bb);

        client.setStatus(ClientRequestStatus.AVAILABLE);
        client.setDriverId(Long.valueOf(0));

//        Timestampf timestampf=new Timestampf(System.currentTimeMillis());
        Timestampf timestampf=new Timestampf(Calendar.getInstance()
                .getTime().getTime());
        client.setTstamp(timestampf);
        //client.setTime(String.valueOf(timestampf.getTime()));
        client.setTime(String.valueOf(Calendar.getInstance()
                .getTime().getTime()));
        String key = client.getClientId().toString();
        Task<Void> upload = clientsRef.child(key).setValue(client);
        //clientsRef.child(key).setValue(locationA);

        upload.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(client.getClientId());
                // action.onProgress("upload student data", 100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                // action.onProgress("error upload student data", 100);
            }
        });
        return client.getClientId();
    }
//        upload.addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> upload) {
//                if (upload.isSuccessful()) {
//                    flagTrue();
//                }
//            }
//
//        });
//    @Override
//    public Long addClient(ContentValues values, Location a, Location b, Utils.Action<Long> action) {
//
//        Client client = ContentValuesToCourse(values);
//        client.setStartPoint(a);
//        client.setDestinationPoint(b);
//        String key = client.getClientId().toString();
//        Task<Void> upload = clientsRef.child(key).setValue(client);
//        //clientsRef.child(key).setValue(locationA);
//
//        upload.addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> upload) {
//                if (upload.isSuccessful()) {
//                    flagTrue();
//                }
//            }
//
//        });
//        if (flag == false)
//            return client.getClientId();
//        else
//            return Long.valueOf(0);
//    }

    private void flagTrue() {
        flag = true;
    }


    @Override
    public boolean removeClient(Long id) {
        String key = id.toString();
        clientsRef.child(key).removeValue();


        return true;
    }

    @Override
    public boolean updateClient(Long id, ContentValues values) {

        Client client = ContentValuesToClient(values);
        String key = id.toString();
        //client.setName(name);  // to check
        clientsRef.child(key).setValue(client);

        return true;
    }

    @Override
    public List<Client> getClients() {


        return clientList;
    }
}
