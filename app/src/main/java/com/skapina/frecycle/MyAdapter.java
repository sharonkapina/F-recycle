package com.skapina.frecycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;



public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.RecyclerVH>  {

    Context c;
    String[] products;
    String images;
    ItemListener itemListener;

    public MyAdapter(Context c, String images, String[] products) {
        this.c = c;
        this.products = products;
        this.images = images;

    }

    @Override
    public RecyclerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.list_layout, parent, false);
        return new RecyclerVH(v);
    }

    @Override
    public void onBindViewHolder(RecyclerVH holder, final int position) {

        images = "file:///android_asset/images/" +products[position] + ".jpg";

        Picasso.get().load(images).into(holder.photo);
        holder.name.setText(products[position]);

        holder.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return products.length;
    }

    public void setOnItemClickListener(ItemListener itemListener){
        this.itemListener = itemListener;
    }

    /*
    VIEWHOLDER CLASS
     */
    public class RecyclerVH extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView name;

        public RecyclerVH(final View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.p_image);
            name = itemView.findViewById(R.id.p_text);

        }
    }
}