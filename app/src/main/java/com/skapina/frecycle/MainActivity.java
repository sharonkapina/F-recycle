package com.skapina.frecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //Declare required variables
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    myDbAdapter helper;
    String e1, e2, p1, p2, pa1, pa2, m1, m2, g1, g2, f1, f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request camera permissions
        int MY_PERMISSIONS_REQUEST_CAMERA=0;
        int LOCATION_REQUEST=0;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA );
            }
        }//Obtained camera permissions

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST );
            }
        }

        //Create tabbed layout using fragments
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab3Fragment(), "Capture Image");
        adapter.addFragment(new Tab2Fragment(), "Nearby Centres");
        adapter.addFragment(new Tab1Fragment(), "Recycling Tips");
        adapter.addFragment(new Tab4Fragment(), "tools");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);

        e1 = "If you have electronic items to recycle, your local recycling center may be able to help. " +
                "A number of certified retailers, and companies such as Safaricom have free electronic recycling programs at " +
                "most of their stores. Many municipalities also offer occasional electronic recycling; and the recycling " +
                "centers offer some options as well.";
        e2 = "If your items are still fully usable, consider donating or selling them. Websites like Jiji, Pigia Me, " +
                "and The Star Classifieds are all great places to sell or give away an electronic item to someone who needs it, " +
                "which will extend the life of the item.";

        p1 = "Most plastic items never fully disappear; they just get smaller and smaller. Many of these tiny plastic particles " +
                "are swallowed by farm animals or fish who mistake them for food, and thus can find their way onto our dinner plates. " +
                "The main issue of recycling plastic is sorting and collection of the waste material. Plastic also needs to be sorted " +
                "into recyclable and non-recyclable plastics.";
        p2 = "Smart recycling entails the use of automated waste bins, which helps in waste collection and sorting. This is done using " +
                "the Internet Of Things (IoT). Rewarding people for the proper disposal of plastic waste will help encourage others to " +
                "do the same.";



        pa1 = " Waste paper is a vital ingredient of a healthy compose heap. If you don’t have the space (or time) to build one yourself then" +
                " you can still recycle paper as mulch. Simply tear it into small strips and place it around your plants. Recycling paper in this" +
                " way helps to keep the soil in your garden moist and adds nutrients to it too.";
        pa2 = "Not sure how to recycle paper packaging and newspapers? Use them to bulk out boxes when packing items to move house, putting items into" +
                " storage, or sending gifts to friends. Wrap delicate items in newspaper and scrunch up other items of used paper or cardboard to fill gaps" +
                " in the box and protect your valuables.";

        m1 = "With scrap metal, the general rule is a product needs to be at least 50 percent metal. Even if that metal is surrounded by other materials like plastic" +
                ", it’s worth recycling if it’s made mostly out of metal.\n" +
                "If you have products with only a small amount of metal but it’s easy to remove, separate the metal. For example, a plastic three-ring binder is not scrap" +
                " metal, but remove the metal rings and now you’re talking.";
        m2 = "Scrap metal is classified as either ferrous (containing iron, such as steel) and nonferrous (everything else). So, you’ll want to identify whether your metal is" +
                " ferrous or nonferrous. The way to do this is with a magnet. Ferrous metals stick to a magnet; nonferrous metals don’t.";

        g1 = "";
        g2 = "";

        f1 = "f you want your clothing to live on, but don’t want to mess with reselling, donating your items to a local homeless shelter or community center in your town is an amazing" +
                " option to help others while keeping your clothing out of landfills. First though, make sure there is an actual need for the clothing items you have before just dropping them" +
                " off. Donating isn’t an excuse to just pawn your unwanted clothing onto someone else that also doesn’t want or need them.";
        f2 = "“Recycling” clothing doesn’t necessarily mean only sending it to get shredded up and turned into something new. Recycling can simply mean passing items on to be used and loved by" +
                " someone else.";


        helper = new myDbAdapter(this);
        SQLiteDatabase db = helper.myhelper.getWritableDatabase();
        String count = "SELECT count(*) FROM items";
        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if(icount>0) {
        }
        else {
            helper.insertData("electronics",e1, e2);
            helper.insertData("plastic",p1, p2);
            helper.insertData("paper",pa1, pa2);
            helper.insertData("metal",m1, m2);
            helper.insertData("glass",e1, e2);
            helper.insertData("fabric",f1, f2);
        }
    }//End onCreate method

}//End class