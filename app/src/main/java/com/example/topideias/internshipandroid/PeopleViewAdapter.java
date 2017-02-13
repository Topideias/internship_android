package com.example.topideias.internshipandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by maau_ on 10/02/2017.
 */

public class PeopleViewAdapter extends RecyclerView.Adapter<PeopleViewAdapter.ViewHolder>{

    private List<Person> people;
    private Context context;

    public PeopleViewAdapter(Context context, List<Person> people){
        this.context = context;
        this.people = people;
    }

    @Override
    public PeopleViewAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_person, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.name.setText(people.get(position).getName());
        viewHolder.location.setText(people.get(position).getCountry());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView location;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);

            name = (TextView) itemLayoutView.findViewById(R.id.person_description);
            location = (TextView) itemLayoutView.findViewById(R.id.person_location);
        }
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
