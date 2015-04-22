package com.example.admin.mycontacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Detail extends ActionBarActivity
{
    Button call;
    TextView textView;
    TextView textView2;
    String name;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = getIntent().getStringExtra("Name");
        number = getIntent().getStringExtra("Number");

        final TextView textView = (TextView) findViewById(R.id.idName);
        final TextView textView2 = (TextView) findViewById(R.id.idNumber);

        textView.setText(name);
        textView2.setText(number);

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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent=new Intent(Detail.this,Edit.class);
        intent.putExtra("Name", name);
        Log.d("passing name",name);
        intent.putExtra("Number", number);
        Log.d("passing number",number);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
