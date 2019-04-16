package com.example.activity.conversions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class SpinnerAdapter extends ArrayAdapter{

        private Context context;
        String [] list;

        public SpinnerAdapter(Context context, int resource, String []list)
        {
            super(context, resource, list);
            this.context = context;
            this.list = list;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {   // Ordinary view in Spinner, we use android.R.layout.simple_spinner_item
            return super.getView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent)
        {   // This view starts when we click the spinner.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.spinner_layout, parent, false);

            String stringItem = list[position];
            TextView item = (TextView) row.findViewById(R.id.item);
            item.setText(stringItem);
            return row;
        }
}
