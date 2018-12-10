package com.example.nouman.mosqueadmin;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class IttekaafAdapter extends ArrayAdapter<ApproveIttekaafModel> {
    private Activity context;
    private List<ApproveIttekaafModel> IttekaafList;

    public IttekaafAdapter(AppCompatActivity context, List<ApproveIttekaafModel> ItList){
        super(context,R.layout.ittekaaf_single_list,ItList);
        this.context=context;
        this.IttekaafList=ItList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.nikah_list_layout,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.name);
        TextView textViewtime = (TextView) listViewItem.findViewById(R.id.time);
        TextView textViewdist = (TextView) listViewItem.findViewById(R.id.date);


        ApproveIttekaafModel approveIttekaafModel = IttekaafList.get(position);

        textViewName.setText("Name: "+approveIttekaafModel.getName());
        textViewtime.setText("Time: "+approveIttekaafModel.getPhoneNo());


        return listViewItem;
    }
}
