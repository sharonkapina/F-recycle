package com.skapina.frecycle;

//Import dependencies
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


//begin class
public class Tab1Fragment extends Fragment {
    //Declare required variables
    private RecyclerView rv;
    View myView;
    private static String[] products ={"electronics", "plastic", "paper", "metal", "glass", "fabric"};
    String images;
    int pos;
    private Activity mActivity;

    MyAdapter adapter;

    //Begin onCreateView method
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.fragment_one, container, false);
        rv = myView.findViewById(R.id.RV);

        //LAYOUT MANAGER
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        images = products[pos];

        //ADAPTER
        adapter = new MyAdapter(getActivity(), images, products);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new ItemListener() {
            @Override
            public void onItemClick(int pos) {

                Toast.makeText(getContext(),"Now viewing : " +products[pos], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), Tips.class);
                String select = products[pos];
                intent.putExtra("select", select);
                startActivity(intent);

            }
        });

        return myView;
    }//End onCreateView


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (Activity) activity;
    }

}//End class
