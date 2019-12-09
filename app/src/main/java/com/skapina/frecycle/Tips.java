package com.skapina.frecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Tips extends AppCompatActivity {

    String selection;
    ImageView banner, image;
    TextView heading, info1, info2;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        Intent intent = getIntent();
        selection = intent.getStringExtra("select");

        banner = findViewById(R.id.imgBanner);
        image = findViewById(R.id.ImgDeco);
        heading = findViewById(R.id.txtHeading);
        info1 = findViewById(R.id.txtInfo1);
        info2 = findViewById(R.id.txtInfo2);

        heading.setText(selection + " recycling tips");
        Picasso.get().load("file:///android_asset/images/"+selection+"1.jpg").fit().into(banner);
        Picasso.get().load("file:///android_asset/images/"+selection+"2.jpg").fit().into(image);
        getInfo(selection);

    }

    public void getInfo(String text){

        helper = new myDbAdapter(this);
        SQLiteDatabase db = helper.myhelper.getWritableDatabase();
        String query = "SELECT * FROM items WHERE name='" +text+"'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        //setting related user info in User Object
        info1.setText(cursor.getString(cursor.getColumnIndex("info1")));
        info2.setText(cursor.getString(cursor.getColumnIndex("info2")));


        //close cursor & database
        cursor.close();
        db.close();

    }
}
