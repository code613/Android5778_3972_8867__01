package com.example.ben.android5778_3972_8867__01.controller;


//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;

import com.example.ben.android5778_3972_8867__01.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    EditText etName;
    EditText etPhone;
    EditText etE_Mail;
    Button btnSighup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
       // etE_Mail = (EditText) findViewById(R.id.etE_Mail);
        btnSighup = (Button) findViewById(R.id.btnSignup);

        OnClickListener oclBtnSignup = new OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText("!");
            }
        };
        btnSighup.setOnClickListener(oclBtnSignup);

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //here, after we introduced something in the EditText we get the string from it
                String answerString = etName.getText().toString();

                //and now we make a Toast
                //modify "yourActivity.this" with your activity name .this
                etName.setText("Name "+answerString);

            }
        };
        etName.addTextChangedListener( textWatcher);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        DatabaseReference myRef1 = myRef.child("students");
       // myRef1.push().setValue("student message_a");
        //myRef1.push().setValue("student message 2","");
       // myRef1.push().setValue("student message888","");
        //String key = myRef1.push().getKey();
        //myRef1.child(key).setValue("student 181message 1");
    }

}
