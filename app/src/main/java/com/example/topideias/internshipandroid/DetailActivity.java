package com.example.topideias.internshipandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Person person;
    TextView name, email, location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView) findViewById(R.id.text_name);
        email = (TextView) findViewById(R.id.text_email);
        location = (TextView) findViewById(R.id.text_location);

        Intent intent = getIntent();
        person = (Person) intent.getSerializableExtra("person");

        name.setText(person.getName());
        email.setText(person.getEmail());
        location.setText(person.getCountry());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
