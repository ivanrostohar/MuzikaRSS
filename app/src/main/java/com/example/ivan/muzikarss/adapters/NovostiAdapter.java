package com.example.ivan.muzikarss.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.models.NovostiRssItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ivan on 13.1.2017..
 */

public class NovostiAdapter extends RecyclerView.Adapter<NovostiAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NovostiRssItem> novostiList;

    public NovostiAdapter(Context context, ArrayList<NovostiRssItem> novostiList) {
        this.context = context;
        this.novostiList = novostiList;
    }

    @Override
    public NovostiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.novosti_row_, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NovostiAdapter.ViewHolder holder, int position) {
        holder.txt_novosti_title.setText(novostiList.get(position).getTitle().toString());
//        holder.txt_novosti_description.setText(novostiList.get(position).getDescription().toString());
        holder.txt_novosti_date.setText(novostiList.get(position).getPubDate().toString());
//        Glide.with(context).load(novostiList.get(position).getPicture()).override(500, 500).into(holder.img_novosti);
        Picasso.with(context).load(novostiList.get(position).getPicture()).fit().into(holder.img_novosti);

    }

    @Override
    public int getItemCount() {
        return novostiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img_novosti;
        public TextView txt_novosti_title, txt_novosti_description, txt_novosti_date;

        public ViewHolder(View itemView) {
            super(itemView);

            img_novosti = (ImageView) itemView.findViewById(R.id.img_novosti);
            txt_novosti_title = (TextView) itemView.findViewById(R.id.txt_novosti_title);
//            txt_novosti_description = (TextView) itemView.findViewById(R.id.txt_novosti_description);
            txt_novosti_date = (TextView) itemView.findViewById(R.id.txt_date);
        }
    }
}
