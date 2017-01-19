package com.example.ivan.muzikarss.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.muzikarss.R;
import com.example.ivan.muzikarss.models.KalendarHrvatskaModel;
import com.example.ivan.muzikarss.models.NovostiRssItem;

import java.util.ArrayList;

/**
 * Created by Ivan on 18.1.2017..
 */

public class KalendarHrvatskaAdapter extends RecyclerView.Adapter<KalendarHrvatskaAdapter.ViewHolder> {

    /**
     * Interface that specifes listeners behaviour. The click will return the object type KalendarHrvatskaModel
     */
    public interface OnItemClickListener{
        void onItemClick(KalendarHrvatskaModel kalendarHrvatskaModel);
    }

    private OnItemClickListener onItemClickListener;
    private Context context;
    private ArrayList<KalendarHrvatskaModel> khItems;

    //Adapter constructor will receive an object that implements OnItemClickListener interface
    public KalendarHrvatskaAdapter(Context context, ArrayList<KalendarHrvatskaModel> khItems, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.khItems = khItems;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public KalendarHrvatskaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hrvatska_kalendar_row, parent ,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(khItems.get(position), onItemClickListener);

        holder.txt_kh_title.setText(khItems.get(position).getTitle());
        holder.txt_kh_event_type.setText(khItems.get(position).getEvent_type());
        holder.txt_kh_event_venue.setText(khItems.get(position).getEvent_venue());
        holder.txt_kh_venue_date.setText(khItems.get(position).getEvent_date());

    }


    @Override
    public int getItemCount() {
        return khItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_kh_title, txt_kh_event_type, txt_kh_event_venue, txt_kh_venue_date;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_kh_title = (TextView)itemView.findViewById(R.id.txt_kh_title);
            txt_kh_event_type = (TextView)itemView.findViewById(R.id.txt_kh_event_type);
            txt_kh_event_venue = (TextView)itemView.findViewById(R.id.txt_kh_event_venue);
            txt_kh_venue_date = (TextView)itemView.findViewById(R.id.txt_kh_event_date);
        }

        /**
         * See the definition at
         * {@link com.example.ivan.muzikarss.adapters.NovostiAdapter.ViewHolder#bind(NovostiRssItem, NovostiAdapter.OnItemClickListener)}
         */
        public void bind(final KalendarHrvatskaModel kalendarHrvatskaModel, final OnItemClickListener onItemClickListener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(kalendarHrvatskaModel);
                }
            });
        }
    }
}
