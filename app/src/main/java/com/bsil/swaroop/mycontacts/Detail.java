package com.bsil.swaroop.mycontacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Detail extends ActionBarActivity
{
    Button              call;
    TextView            textView;
    TextView            textView2;
    ImageView           contactImage;
    String              name;
    String              number;
    ContactDatabase     db1;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name    = getIntent().getStringExtra("Name");
        number  = getIntent().getStringExtra("Number");

        textView        = (TextView) findViewById(R.id.idName);
        textView2       = (TextView) findViewById(R.id.idNumber);
        contactImage    = (ImageView) findViewById(R.id.profile_pic);

        contactImage.setImageResource(R.drawable.image);
        textView.setText(name);
        textView2.setText(number);

        ctx = this.getApplicationContext();
        db1 = new ContactDatabase(ctx);


        call= (Button) findViewById(R.id.callButton);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String callnumber=textView2.getText().toString();
                Toast.makeText(getApplicationContext(),"calling "+name,Toast.LENGTH_LONG).show();

                Intent phoneIntent=new Intent(Intent.ACTION_CALL);

                phoneIntent.setData(Uri.parse("tel:" + callnumber));
                 try
                     {
                     startActivity(phoneIntent);


                     } catch (Exception ex)
                     {
                     ex.printStackTrace();
                     }


            }
        });
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1) {
                String result = data.getStringExtra("name");
                String number = data.getStringExtra("number");
                final TextView textView = (TextView) findViewById(R.id.idName);
                final TextView textView2 = (TextView) findViewById(R.id.idNumber);
                textView.setText(name);
                textView2.setText(number);

            }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
       if(item.getItemId()==R.id.item2) {
           Intent intent = new Intent(Detail.this, Edit.class);
           intent.putExtra("Name", name);
           Log.d("passing name", name);
           intent.putExtra("Number", number);
           Log.d("passing number", number);

           //startActivity(intent);
          startActivityForResult(intent,1);
									}
       else if(item.getItemId()==R.id.item3)
       {
           long rowIdentity;
           Toast.makeText(getApplicationContext(),"Deleting contact",Toast.LENGTH_LONG).show();
           rowIdentity=db1.getRowID(number);
           db1.deleteContact(rowIdentity);
           Toast.makeText(getApplicationContext(),"Contact deleted",Toast.LENGTH_LONG).show();

       }

        return super.onOptionsItemSelected(item);
    }
}
