package com.skapina.frecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class RecyclingTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_tips);
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, RecyclingTips.class);
    }
}
