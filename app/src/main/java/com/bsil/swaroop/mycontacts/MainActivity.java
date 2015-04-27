package com.bsil.swaroop.mycontacts;

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
    RecyclerView                    cRecyclerview;
    RecyclerView.Adapter            cAdapter;
    RecyclerView.LayoutManager      cManager;

    Button          Addbtn;
    ContactDatabase db1;
    EditText        contactName = null;
    EditText        contactNumber = null;
    Context         ctx = this;
    List<Contact>   data = new ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Addbtn        = (Button) findViewById(R.id.btn1);
        db1           = new ContactDatabase(ctx);
								cRecyclerview = (RecyclerView) findViewById(R.id.my_recycler_view);
        cRecyclerview.setHasFixedSize(true);
							 cManager      = new LinearLayoutManager(this);
        cRecyclerview.setLayoutManager(cManager);
							 data          = db1.getAllRecords();
        cAdapter      = new ContactAdapter(ctx, data);
        cRecyclerview.setAdapter(cAdapter);


    }
    @Override
    protected void onResume() {
        super.onResume();
        data     = db1.getAllRecords();
        cAdapter = new ContactAdapter(ctx, data);
        cRecyclerview.setAdapter(cAdapter);

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

            contactName   = (EditText) view.findViewById(R.id.editText1);
            contactNumber = (EditText) view.findViewById(R.id.editText2);

            contactName.setTextColor(Color.BLACK);
            contactNumber.setTextColor(Color.BLACK);

            dialog.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int i) {

                    Contact contact = new Contact();
                    contact.name = contactName.getText().toString();
                    contact.cell_no = contactNumber.getText().toString();
                    db1 = new ContactDatabase(ctx);
                    db1.insert(contact);

                    Toast.makeText(getApplicationContext(), "data added", Toast.LENGTH_LONG).show();

                    data = db1.getAllRecords();
                    cAdapter = new ContactAdapter(ctx, data);
                    cRecyclerview.setAdapter(cAdapter);

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


