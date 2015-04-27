package com.bsil.swaroop.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swaroop on 15/04/15.
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


    public String CREATE_QUERY ="create table " + TABLE_NAME +"("+ CONTACTS_COLUMN_ID +" INTEGER PRIMARY KEY  ,"+ CONTACT_NAME +" TEXT ,"+ CONTACT_CELLNO +" TEXT NOT NULL );";


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
        database.insert(TABLE_NAME, null, values);
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
            Toast.makeText(context,"Edited successfully",Toast.LENGTH_LONG).show();
            database.close();
        }
        catch(Exception e)
        {
           e.printStackTrace();
        }

    }

   public long getRowID(String number)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = new String[]{CONTACTS_COLUMN_ID};
        Cursor c = database.query(TABLE_NAME, columns, CONTACT_CELLNO+"=" + "'"+number.trim()+"'", null, null, null, null);
        c.moveToFirst();

        long rowid = c.getLong(c.getColumnIndex(CONTACTS_COLUMN_ID));
        //long rowid = c.getColumnIndex(CONTACTS_COLUMN_ID);
        System.out.println("row id is"+rowid);

        return rowid;
    }

    public void updateByRowID(long id,String name,String number)
    {
        long rowID=id;
        String s1 = CONTACTS_COLUMN_ID+"=" + "'"+rowID+"'";
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(CONTACT_NAME,name);
        content.put(CONTACT_CELLNO,number);
        database.update(TABLE_NAME,content,s1,null);

    }

    public void deleteContact(long id)
    {
        long idToDelete=id;
        String s1=CONTACTS_COLUMN_ID+"=" + "'"+idToDelete+"'";
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(TABLE_NAME,s1,null);
        Log.d("data deleted","successfully");

    }
}
