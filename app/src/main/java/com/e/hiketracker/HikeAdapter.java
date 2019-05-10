package com.e.hiketracker;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter that handles hike data and transfers it to a ListView
 */
public class HikeAdapter extends ArrayAdapter<Hike> {

    private List<Hike> hikeList;            // The list of fish to display
    private Context context;                // The original activity that displays this
    private int layoutResource;                   // the layout to use

    /**
     * Creates HikeAdapter object
     *
     */
    public HikeAdapter(Context context, int resource, List<Hike> hikeList) {
        super(context, resource, hikeList);
        this.context = context;
        this.layoutResource = resource;
        this.hikeList = hikeList;
    }

    /**
     * gets the view and inflates the list
     *
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the fish we are displaying
        Hike hike = hikeList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.hike_row_layout, null);

        TextView textViewTrailName =(TextView)view.findViewById(R.id.textViewTrailName);
        TextView textViewDistance =(TextView)view.findViewById(R.id.textViewDistance);
        TextView textViewTime =(TextView)view.findViewById(R.id.textViewTime);

        textViewTrailName.setText(hike.getName());
        textViewDistance.setText(hike.getsDistance());
        Log.d("CIS3334", "Time = " + hike.getsTime());
        textViewTime.setText(hike.getsTime());
        Log.d("CIS3334", "Set Time in List View");

        return(view);
    }

}

