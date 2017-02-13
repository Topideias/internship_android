package com.example.topideias.internshipandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Person> people = new ArrayList<>();
    private PeopleViewAdapter personViewAdapter;

    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

    private static final int EDIT_REQUEST = 0;
    public static final int SAVE_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById((R.id.people_recycler_view));

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener(){

                    @Override
                    public void onItemClick(View view, int position){
                        Intent intent = new Intent(MainActivity.this,  DetailActivity.class);
                        intent.putExtra("person", people.get(position));
                        startActivityForResult(intent, EDIT_REQUEST);
                    }

                    @Override
                    public void onItemLongClick(View view, final int position){
                        DialogInterface.OnClickListener dialogClickListener =  new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int which){
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        if(PeopleAdapter.getInstance(MainActivity.this).DeleteGroup(people.get(position))){
                                            Toast.makeText(MainActivity.this, "Person Successfully Deleted", Toast.LENGTH_LONG).show();
                                            LoadData();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Can't Delet this person", Toast.LENGTH_LONG).show();
                                        }
                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Do you really wanna delete this person ? " + people.get(position).getName())
                                .setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();

                        Log.e("TAG", "Long Click Position" + position);

                    }

                })
        );

        LoadData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,  NewPerson.class);
                startActivityForResult(intent, EDIT_REQUEST);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == EDIT_REQUEST){
            if(requestCode == SAVE_REQUEST){
                LoadData();
            }
        }
    }

    private void LoadData(){
        Cursor groupRegister = PeopleAdapter.getInstance(this).getAllRows();

        people = PeopleAdapter.getInstance(this).getAllList(groupRegister);

        personViewAdapter = new PeopleViewAdapter(this, people);

        recyclerView.setAdapter(personViewAdapter);
    }
}
