package com.xyz.professorreview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText etName, etNumber, etEmail, etBatch, etReview;
    Spinner spnSubject;
    RadioGroup rgGender;
    RadioButton rbGender;
    Button btnSubmit;
    RatingBar rabSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etBatch = (EditText) findViewById(R.id.etBatch);
        etReview = (EditText) findViewById(R.id.etReview);
        spnSubject = (Spinner) findViewById(R.id.spnSubject);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        rabSubject= (RatingBar) findViewById(R.id.rabSubject);

        ArrayList<String> Subject = new ArrayList<String>();
        Subject.add("DBMS");
        Subject.add("OS");
        Subject.add("OST");
        Subject.add("CGVR");
        ArrayAdapter<String> SubjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Subject);
        spnSubject.setAdapter(SubjectAdapter);

        spnSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Subject = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int s1=rgGender.getCheckedRadioButtonId();
                rbGender= (RadioButton) findViewById(s1);

                String name = etName.getText().toString();
                String phone = etNumber.getText().toString();
                String email = etEmail.getText().toString();
                String batch = etBatch.getText().toString();
                String review = etReview.getText().toString();
                float rating= rabSubject.getRating();

                int subjects = spnSubject.getSelectedItemPosition();

                if (name.length()==0){
                    Toast.makeText(getApplicationContext(), "Invalid Name", Toast.LENGTH_LONG).show();
                    etName.requestFocus();
                }

                else if (phone.length()!=10){
                    Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_LONG).show();
                    etNumber.requestFocus();
                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_LONG).show();
                    etEmail.requestFocus();
                }

                else if (batch.length()==0){
                    Toast.makeText(getApplicationContext(), "Invalid Batch", Toast.LENGTH_LONG).show();
                    etBatch.requestFocus();
                }

                else if (review.length()==0){
                    Toast.makeText(getApplicationContext(), "Please write a review", Toast.LENGTH_LONG).show();
                    etReview.requestFocus();
                }
                else {
                    String msg = "Name: " + name + "\n" + "Phone Number: " + phone + "\n" + "Email: " + email + "\n" + "Gender: " + rbGender.getText().toString() + "\n" + "Batch Code: " + batch + "\n" + "Subject Selected: " + subjects + "\n" + "Rating: " + rating + "\n" + "Review: " + review + "\n";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}