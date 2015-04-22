package com.example.admin.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15/04/15.
 */
public class ContactDatabase extends SQLiteOpenHelper
{
    public static final int database_version=1;
    //to insert into data
    public SQLiteDatabase database;


    public static final String CONTACT_NAME="user_name";
    public static final String CONTACT_CELLNO="cell_no";
    public static final String DATABASE_NAME="contact_info";
    public static final String TABLE_NAME="add_user";
    public static final String CONTACTS_COLUMN_ID = "_id";
    public static final String CONTACT_EMAIL="EMAIL_ID";
    Context context;


    public String CREATE_QUERY ="create table " + TABLE_NAME +"("+ CONTACTS_COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+ CONTACT_NAME +" TEXT ,"+ CONTACT_CELLNO +" TEXT NOT NULL ."+ CONTACT_EMAIL +" TEXT NOT NULL .);";


    public ContactDatabase(Context context) {
        super(context,DATABASE_NAME, null,database_version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        //to create Table USING query
        db.execSQL(CREATE_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2)
    {
        db.execSQL("DROP TABLE IF EXIXTS"+TABLE_NAME);
        onCreate(db);

    }

    public void insert(Contact db2)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CONTACT_NAME,db2.name);
        values.put(CONTACT_CELLNO,db2.cell_no);

        //to insert into database pass values in the insert method of sqlitedatabase
        database.insert(TABLE_NAME,null,values);


        Log.d("database entry","successfully added");

     }

    public List<Contact> getAllRecords()
    {
        List<Contact> list = new ArrayList<Contact>();
        try
        {
            SQLiteDatabase database = this.getReadableDatabase();
            Cursor cursor = database.rawQuery("select * from "+TABLE_NAME,null);
            cursor.moveToFirst();
            while(cursor.isAfterLast()==false)
            {
                Contact obj = new Contact();
                obj.name = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
                obj.cell_no=cursor.getString(cursor.getColumnIndex(CONTACT_CELLNO));
                list.add(obj);
                cursor.moveToNext();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void update(String name,String number)
    {
        Log.d("update name",name);
        Log.d("update number",number);


        try {
            System.out.println("value of this = "+this);
            SQLiteDatabase database = this.getWritableDatabase();
            String s1 = CONTACT_NAME+"=" + "'"+name.trim()+"'";

            ContentValues args = new ContentValues();
            args.put(CONTACT_CELLNO, number);
            database.update(TABLE_NAME, args, s1, null);
            database.close();
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }

    }
   public void updateByRowId(String name,String number)
    {

    }

}
