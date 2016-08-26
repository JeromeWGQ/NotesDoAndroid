package com.example.jerome.myapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 2016/8/26.
 */
public class ContractDB {

    public static final String DB_NAME="ContractDB";
    public static final int VERSION=1;
    private static ContractDB contractDB;
    private SQLiteDatabase db;

    private ContractDB(Context context)
    {
        CreateDB createDB = new CreateDB(context,DB_NAME,null,VERSION);
        db=createDB.getWritableDatabase();
    }

    public synchronized static ContractDB getInstance(Context context)
    {
        if(contractDB==null)
        {
            contractDB=new ContractDB(context);
        }
        return contractDB;
    }
    public void saveContract(Contract contract)
    {
        if(contract!=null)
        {
            ContentValues values =new ContentValues();
            values.put("id",contract.getId());
            values.put("user_name",contract.getUserName());
            values.put("content",contract.getContent());
            values.put("img",contract.getImg());
            db.insert("Contract",null,values);
        }

    }
    public List<Contract> loadContract(String userName)
    {
        List<Contract> list=new ArrayList<Contract>();
        Cursor cursor = db.query("Contract",null,"user_name=?",new String[]{userName},null,null,null);
        if (cursor.moveToFirst())
        {
           do {
               Contract contract =new Contract();
               contract.setId(cursor.getInt(cursor.getColumnIndex("id")));
               contract.setContent(cursor.getString(cursor.getColumnIndex("content")));
               contract.setUserName(cursor.getString(cursor.getColumnIndex("user_name")));
               contract.setImg(cursor.getBlob(cursor.getColumnIndex("img")));
               list.add(contract);
           }while (cursor.moveToNext());
        }
        if (cursor!=null)
        {
            cursor.close();
        }
        return list;
    }
    public void delete(int id)
    {
        db.delete("Contract","id=?",new String[]{String.valueOf(id)});
    }
    public void update(Contract contract)
    {
        ContentValues values=new ContentValues();
        values.put("id",contract.getId());
        values.put("user_name",contract.getUserName());
        values.put("img",contract.getImg());
        values.put("content",contract.getContent());
        db.update("Contract",values,"id=?",new String[]{String.valueOf(contract.getId())});
    }

    public byte[] image(int id)
    {
        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        Bitmap bitmap =((BitmapDrawable) getResources().getDrawable(id)).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        return outputStream.toByteArray();
    }
    private Resources getResources()
    {
        Resources myResources=null;
        myResources=getResources();
        return myResources;
    }
    public Bitmap getBitmap(byte[] img)
    {
        Bitmap bitmap= BitmapFactory.decodeByteArray(img,0,img.length);
        return bitmap;
    }

    public Drawable getDrawable(Bitmap bitmap)
    {
        Bitmap bm=bitmap;
        BitmapDrawable drawable=new BitmapDrawable(getResources(),bm);
        return drawable;
    }

}
