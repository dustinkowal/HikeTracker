package com.e.hiketracker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HikeAdapter extends ArrayAdapter<Hike> {

    private List<Hike> hikeList;            // The list of fish to display
    private Context context;                // The original activity that displays this
    private int layoutResource;                   // the layout to use

    public HikeAdapter(Context context, int resource, List<Hike> hikeList) {
        super(context, resource, hikeList);
        this.context = context;
        this.layoutResource = resource;
        this.hikeList = hikeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the fish we are displaying
        Hike hike = hikeList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //view = inflater.inflate(R.layout.fish_row_layout, null);
        view = inflater.inflate(R.layout.hike_row_layout, null);

        TextView textViewTrailName =(TextView)view.findViewById(R.id.textViewTrailName);
        //TextView textViewDistance =(TextView)view.findViewById(R.id.textViewDistance);
        TextView textViewPoints=(TextView)view.findViewById(R.id.textViewPoints);
        textViewTrailName.setText(hike.getName());
        // textViewDistance.setText(hike.getDistance());
        textViewPoints.setText(hike.getPoints());

        return(view);
    }

}

