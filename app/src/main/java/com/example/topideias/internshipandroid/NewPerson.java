package com.example.topideias.internshipandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class NewPerson extends AppCompatActivity {

    private Person person;

    TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutCountry;
    EditText edName, edEmail, edCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutCountry = (TextInputLayout) findViewById(R.id.input_layout_country);

        edName = (EditText) findViewById(R.id.name);
        edEmail = (EditText) findViewById(R.id.email);
        edCountry = (EditText) findViewById(R.id.country);

        Intent intent = getIntent();
        person = (Person) intent.getSerializableExtra("person");

        if (person != null) {
            setTitle("Edit Person");

            edName.setText(person.getName());
            edEmail.setText(person.getEmail());
            edCountry.setText(person.getCountry());
        } else {
            setTitle("New Person");
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

        public void save (View v){
        if(person != null){
        person.setName(edName.getText().toString());
        person.setEmail(edEmail.getText().toString());
        person.setCountry(edCountry.getText().toString());

        PeopleAdapter.getInstance(this).UpdatePerson(person);

    }  else {

            person = new Person();

            person.setName(edName.getText().toString());
            person.setEmail(edEmail.getText().toString());
            person.setCountry(edCountry.getText().toString());

            PeopleAdapter.getInstance(this).InsertPerson(person);
        }

        setResult(MainActivity.SAVE_REQUEST);

        finish();
        }






    }


