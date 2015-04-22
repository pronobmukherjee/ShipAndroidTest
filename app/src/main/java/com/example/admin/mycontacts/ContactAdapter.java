package com.example.admin.mycontacts;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by admin on 15/04/15.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>
{

    private LayoutInflater inflator;
    public List<Contact> list;
    Context context;
    int itemPosition;
    int number;
    private SQLiteDatabase database;
    ContactDatabase db1;


    public ContactAdapter(Context context,List<Contact> list)
    {
        this.context=context;
        inflator=LayoutInflater.from(context);
        this.list=list;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.customcontact, null);
        Log.d("swaroop","onCreateViewHolder called");
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactAdapter.MyViewHolder holder,final int position)
    {
        Contact current=list.get(position);
        Log.d("swaroop","onBindViewHolder called"+ position);
        holder.icon.setImageResource(R.drawable.contact);
        holder.name.setText(current.name);
        holder.cell_no.setText(current.cell_no);

    }



    @Override
    public int getItemCount()
    {
       return list.size();
    }

    @Override
    public long getItemId(int position)
    {
        return super.getItemId(position);
        

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView name;
        TextView cell_no;
        ImageView icon;


        public MyViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            icon= (ImageView) itemView.findViewById(R.id.Cicon);
            Picasso.with(context).load(" https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRGgc4B417dk9XDKzA4VUM5f6S7jZVHJj72TtgKRNctCagaTCes").resize(40,40).into(icon);
            name=(TextView)itemView.findViewById(R.id.Cname);
            cell_no=(TextView)itemView.findViewById(R.id.Ccell_no);
        }


        @Override
        public void onClick(View view)
        {
            int itemPosition =  getAdapterPosition();
            Log.d("Item Position = ",""+itemPosition);
            Contact current=list.get(itemPosition);
            Log.d("current.cell_no = ", ""+current.cell_no);

            Intent intent=new Intent(context,Detail.class);
            intent.putExtra("Name", current.name);
            intent.putExtra("Number", current.cell_no);


            context.startActivity(intent);
        }


    }
 }


