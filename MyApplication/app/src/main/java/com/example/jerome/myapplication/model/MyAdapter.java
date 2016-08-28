package com.example.jerome.myapplication.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jerome.myapplication.R;
import com.example.jerome.myapplication.util.Contract;
import com.example.jerome.myapplication.util.ContractDB;

import java.util.List;

/**
 * Created by alan on 2016/8/27.
 */
public class MyAdapter extends ArrayAdapter<Contract>{

    private Context context;
    private List<Contract> list;
    private int resourceId;
    private ContractDB db;

    public MyAdapter(Context context,int resourceId,List<Contract> list)
    {
        super(context,resourceId,list);
        this.context=context;
        this.resourceId=resourceId;
        this.list=list;
    }

    public View getView(int possition, View convertView, ViewGroup parent)
    {
        db=ContractDB.getInstance(getContext());
        Contract contract=getItem(possition);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        ImageView imageView =(ImageView)view.findViewById(R.id.img);
        TextView textView=(TextView)view.findViewById(R.id.note);
        Drawable drawable=db.getDrawable(db.getBitmap(contract.getImg()));
        imageView.setImageDrawable(drawable);
        textView.setText(contract.getContent());
        return view;
    }
}
