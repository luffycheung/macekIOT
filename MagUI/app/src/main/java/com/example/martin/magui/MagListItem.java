package com.example.martin.magui;

/**
 * Created by Martin on 21.04.2016.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MagListItem extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] id;
    private final String[] arm;
    private final String[] val;

    public MagListItem(Activity context,
                      String[] id, String[] arm,String[] val) {
        super(context, R.layout.magmodule, id);
        this.context = context;
        this.id = id;
        this.arm = arm;
        this.val = val;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.magmodule, null, true);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.id);
        TextView txtTitle2 = (TextView) rowView.findViewById(R.id.alarm);
        TextView txtTitle3 = (TextView) rowView.findViewById(R.id.val);


        txtTitle1.setText(id[position]);
        txtTitle2.setText(arm[position]);
        txtTitle3.setText(val[position]);


        return rowView;
    }
}