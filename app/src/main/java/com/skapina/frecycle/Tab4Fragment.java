package com.skapina.frecycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.skapina.frecycle.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Tab4Fragment extends Fragment implements CardViewOptionsAdapter.OnItemClickListener {
View myView;
    @BindView(R.id.utility_options_recycle_view)
    RecyclerView mUtilityOptionsRecycleView;
    private Activity mActivity;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.activity_tab4_fragment, container, false);


        ButterKnife.bind(this, myView);

        List<CardItemEntity> cardEntities = getUtilityItems();

        CardViewOptionsAdapter cardViewOptionsAdapter = new CardViewOptionsAdapter(this, cardEntities);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        mUtilityOptionsRecycleView.setLayoutManager(mLayoutManager);
        mUtilityOptionsRecycleView.setItemAnimator(new DefaultItemAnimator());
        mUtilityOptionsRecycleView.setAdapter(cardViewOptionsAdapter);



        return myView;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.mActivity = (Activity) activity;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent;
        switch (position) {
            case 0:
                intent = LoginActivity.getStartIntent(mActivity);
                startActivity(intent);
                break;
            case 1:


        }
    }

    private List<CardItemEntity> getUtilityItems() {
        List<CardItemEntity> cardEntities = new ArrayList<>();
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.bulb),
                        getResources().getString(R.string.glass)));
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.flowers),
                        getResources().getString(R.string.plastic)));

        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.spoons),
                        getResources().getString(R.string.metal)));
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.small),
                        getResources().getString(R.string.metal)));
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.wallunit),
                        getResources().getString(R.string.wood)));
        cardEntities.add(
                new CardItemEntity(
                        getResources().getDrawable(R.drawable.rubber),
                        getResources().getString(R.string.rubber)));


        return cardEntities;
    }





    }

