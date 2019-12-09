package com.skapina.frecycle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {

    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String info1, String info2)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.INFO1, info1);
        contentValues.put(myDbHelper.INFO2, info2);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.NAME,myDbHelper.INFO1,myDbHelper.INFO2};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String info1 =cursor.getString(cursor.getColumnIndex(myDbHelper.INFO1));
            String info2 =cursor.getString(cursor.getColumnIndex(myDbHelper.INFO2));
            buffer.append(name+ "   " + info1 + "   " + info2 +" \n");
        }
        return buffer.toString();
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "products";    // Database Name
        private static final String TABLE_NAME = "items";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String NAME = "name";    //Column I
        private static final String INFO1 = "info1";
        private static final String INFO2 = "info2";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+NAME+" VARCHAR(30), "+INFO1+" VARCHAR(5000), "+INFO2+" VARCHAR(5000));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }
}
