package com.example.alumno_fp.persistencia_datos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends ArrayAdapter<Place>{
    public PlaceAdapter(@NonNull Context context, List<Place> objects){
        super(context,0,objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater infalter = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = infalter.inflate(R.layout.activity_item,parent,false);
        }

        TextView textName = convertView.findViewById(R.id.text_name);
        final Place item = getItem(position);
        textName.setText(item.getName());

        return convertView;
    }
}
