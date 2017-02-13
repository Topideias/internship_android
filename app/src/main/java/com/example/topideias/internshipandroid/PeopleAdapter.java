package com.example.topideias.internshipandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maau_ on 10/02/2017.
 */

public class PeopleAdapter {

    private SQLiteDatabase mDb;
    private String TAG = "dbase";
    private static Context context;
    private String NameTable = "people";

    private static PeopleAdapter instance = new PeopleAdapter();
    public static PeopleAdapter getInstance(Context ctx) {

        context = ctx;

        if(instance.mDb == null || !instance.mDb.isOpen()){
            instance.mDb = new DBHelper(ctx).getWritableDatabase();
        }

        return instance;
    }

    private PeopleAdapter(){}

    public void InsertPerson(Person person){

        ContentValues values = new ContentValues();

        values.put("name", person.getName());
        values.put("email", person.getEmail());
        values.put("country", person.getCountry());

        mDb.insert("people", null, values);


    }

    public boolean UpdatePerson(Person person){

        ContentValues values = new ContentValues();

        values.put("name", person.getName());
        values.put("email", person.getEmail());
        values.put("country", person.getCountry());

        return mDb.update("people", values, "id = " + person.getId(), null) > 0;

    }

    public boolean DeleteGroup(Person person){
        return mDb.delete("people", "id = " + person.getId(), null) > 0;
    }

    public Cursor getAllRows(){
        Cursor cursor;

        cursor = mDb.rawQuery("SELECT * FROM people" + " ORDER BY name ASC", null);
        return cursor;
    }

    public List<Person> getAllList(Cursor people){

        ArrayList<Person> list = new ArrayList<>();
        long rows = people.getCount();
        people.moveToFirst();

        for (int i = 0; i < rows ; ++i){
            Person person = new Person();
            person.setId(people.getInt(people.getColumnIndex("id")));
            person.setName(people.getString(people.getColumnIndex("name")));
            person.setEmail(people.getString(people.getColumnIndex("email")));
            person.setCountry(people.getString(people.getColumnIndex("country")));
            list.add(person);

            people.moveToNext();
        }
        return list;
    }
}
