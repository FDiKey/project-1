package com.examplex.kirill.project1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.examplex.kirill.project1.R;

/**
 * Created by kirill on 17/06/2018.
 */

public class ItemAdapter extends ArrayAdapter {
    Context context;
    String[] temp;


    public ItemAdapter(Context context, String[] values) {
        super(context, R.layout.rowitem, values);
        this.context = context;
        this.temp = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowitem, parent,false);
        TextView textView = (TextView) rowView.findViewById(R.id.nickname);
        ImageView img = (ImageView) rowView.findViewById(R.id.avatar);


        String s = temp[position];

        switch(s){
            case "Zero Cool":{
                textView.setText(s);
                img.setImageResource(R.drawable.zerocool);
                break;
            }
            case "Acid Burn":{
                textView.setText(s);
                img.setImageResource(R.drawable.acidburn);
                break;
            }
            case "Cereal  Killer":{
                textView.setText(s);
                img.setImageResource(R.drawable.cereal);
                break;
            }
            case  "Lord Nikon":{
                textView.setText(s);
                img.setImageResource(R.drawable.nickon);
                break;
            }
            case "Phantom Phreak":{
                textView.setText(s);
                img.setImageResource(R.drawable.cookies);
                break;
            }
            case "Razor":{
                textView.setText(s);
                img.setImageResource(R.drawable.razor);
                break;
            }
            case "Blade":{
                textView.setText(s);
                img.setImageResource(R.drawable.blade);
                break;
            }
            case "UltraLazor":{
                textView.setText(s);
                img.setImageResource(R.drawable.killer);
                break;
            }
            case "Dr. Doom":{
                textView.setText(s);
                img.setImageResource(R.drawable.doom);
                break;
            }
            case "Crash Override":{
                textView.setText(s);
                img.setImageResource(R.drawable.crash);
                break;
            }

        }
        return rowView;
    }
}
