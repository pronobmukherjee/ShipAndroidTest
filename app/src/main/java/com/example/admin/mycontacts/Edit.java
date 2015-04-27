package com.example.admin.mycontacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Edit extends ActionBarActivity

{
    EditText            contactName;
    EditText            contactNumber;
    Button              saveChanges;
    ContactDatabase     db1;
    Context             ctx;
    String              name;
    String              number;
    long                rowID;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        contactName     = (EditText) findViewById(R.id.editName);
        contactNumber   = (EditText) findViewById(R.id.editNumber);

        name    = getIntent().getExtras().getString("Name");
        number  = getIntent().getExtras().getString("Number");
        Log.d("name",name);
        Log.d("number",number);


        contactName.setText(name, TextView.BufferType.EDITABLE);

        contactNumber.setText(number, TextView.BufferType.EDITABLE);


        saveChanges= (Button) findViewById(R.id.buttonSave);

        ctx     = this.getApplicationContext();
        db1     = new ContactDatabase(ctx);
        rowID   =db1.getRowID(number);
        Toast.makeText(this,"Row id is ="+rowID, Toast.LENGTH_LONG).show();

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                String newName  =contactName.getText().toString();
                String newNumber=contactNumber.getText().toString();
                db1.update(newName,newNumber);
                db1.updateByRowID(rowID,newName,newNumber);

                Intent returnIntent=new Intent();
                returnIntent.putExtra("name",newName);
                returnIntent.putExtra("number",newNumber);
                setResult(1, returnIntent);
                finish();
            }
        });

    }
}
