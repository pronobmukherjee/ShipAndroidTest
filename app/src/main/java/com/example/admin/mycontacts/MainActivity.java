package com.example.admin.mycontacts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity

{
    RecyclerView Crecyclerview;
    //you have to specify an adapter and a layout manager
    //To reuse (or recycle) a view, a layout manager may ask the adapter to replace the contents of the view with a different element from the dataset.
    RecyclerView.Adapter Cadapter;
    RecyclerView.LayoutManager Cmanager;

    Button Addbtn;




    //CREATE DATABASE OBJECT
    ContactDatabase db1;
    EditText edit1 = null;
    EditText edit2 = null;
    Context ctx = this;
    List<Contact> data = new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Addbtn = (Button) findViewById(R.id.btn1);




        db1 = new ContactDatabase(ctx);

        Crecyclerview = (RecyclerView) findViewById(R.id.my_recycler_view);

        //RecyclerView can perform several optimizations if it can know in advance that changes in adapter content cannot change the size of the RecyclerView itself.
        Crecyclerview.setHasFixedSize(true);

        Cmanager = new LinearLayoutManager(this);
        //set layout manager to the recyclerview
        Crecyclerview.setLayoutManager(Cmanager);

        data = db1.getAllRecords();
        Cadapter = new ContactAdapter(ctx, data);

        //Toast.makeText(getApplicationContext(),"ok data sent",Toast.LENGTH_LONG).show();
        Crecyclerview.setAdapter(Cadapter);


    }



    public void addContact(View v)
    {


        if (v.getId() == R.id.btn1)
        {
            //Toast.makeText(getApplicationContext(),"okk",Toast.LENGTH_LONG).show();
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("ADD CONTACT");
            dialog.setMessage("Enter Contact Details");

            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog, null);

            edit1 = (EditText) view.findViewById(R.id.editText1);
            edit2 = (EditText) view.findViewById(R.id.editText2);

            edit1.setTextColor(Color.BLACK);
            edit2.setTextColor(Color.BLACK);

            dialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int i) {

                    Contact contact = new Contact();
                    contact.name = edit1.getText().toString();
                    contact.cell_no = edit2.getText().toString();

                    //now that you have object insert this object into database
                    db1 = new ContactDatabase(ctx);
                    db1.insert(contact);

                    Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_LONG).show();

                    data = db1.getAllRecords();
                    Cadapter = new ContactAdapter(ctx, data);
                    //Toast.makeText(getApplicationContext(),"ok data sent",Toast.LENGTH_LONG).show();
                    Crecyclerview.setAdapter(Cadapter);

                    d.dismiss();
                }
            });

            dialog.setView(view);

            dialog.setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
            dialog.show();

        }


    }


}


