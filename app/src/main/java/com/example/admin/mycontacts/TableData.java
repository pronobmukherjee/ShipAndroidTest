package com.example.admin.mycontacts;

import android.provider.BaseColumns;

/**
 * Created by admin on 15/04/15.
 */

//same as contract ...Define your column names,database name and table name
public class TableData implements BaseColumns
{
    TableData(){}

    public static final String CONTACT_NAME="user_name";
    public static final String CONTACT_CELLNO="cell_no";
    public static final String DATABASE_NAME="contact_info";
    public static final String TABLE_NAME="add_user";
    public static final String UID="_id";

}
