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

public class NikkahAdapter  extends ArrayAdapter<ApproveNikkahModel> {
    private Activity context;
    private List<ApproveNikkahModel> nikkahList;

    public NikkahAdapter(AppCompatActivity context, List<ApproveNikkahModel> nikkahList){
        super(context,R.layout.nikah_list_layout,nikkahList);
        this.context=context;
        this.nikkahList=nikkahList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.nikah_list_layout,null,true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.name);
        TextView textViewtime = (TextView) listViewItem.findViewById(R.id.time);
        TextView textViewdist = (TextView) listViewItem.findViewById(R.id.date);


        ApproveNikkahModel approveNikkahModel = nikkahList.get(position);

        textViewName.setText("Name: "+approveNikkahModel.getName());
        textViewtime.setText("Time: "+approveNikkahModel.getTime());
        textViewdist.setText("Date: "+approveNikkahModel.getDate());

        return listViewItem;
    }
}
