package com.example.activity.conversions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MyArrayAdapter extends ArrayAdapter<Favorites> {
    Context context;
    ArrayList<Favorites> favorites;
    String parameter;
    public MyArrayAdapter(Context context, int resource,String parameter, ArrayList<Favorites> favorites) {
        super(context, resource, favorites);
        this.context = context;
        this.favorites = favorites;
        this.parameter = parameter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.favoriterows, parent, false);

        TextView tv1 = (TextView) row.findViewById(R.id.textViewFavFROMValue);
        tv1.setText(favorites.get(position).getConversionFROM());

        TextView tv2 = (TextView) row.findViewById(R.id.textViewFavTOValue);
        tv2.setText(favorites.get(position).getConversionTO());

        if(parameter.contentEquals(ConversionActivity.UNIT)) {
            TextView measure = (TextView) row.findViewById(R.id.textViewFavMeasure);
            measure.setVisibility(View.VISIBLE);
            TextView tv3 = (TextView) row.findViewById(R.id.textViewFavMeasureValue);
            tv3.setText(favorites.get(position).getMeasureUnit());
            tv3.setVisibility(View.VISIBLE);
        }

        return row;
    }


}

