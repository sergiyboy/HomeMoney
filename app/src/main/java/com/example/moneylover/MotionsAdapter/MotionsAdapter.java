package com.example.moneylover.MotionsAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneylover.Activitys.MotionActivity;
import com.example.moneylover.Motion;
import com.example.moneylover.R;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class MotionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static class HeaderViewHolder extends RecyclerView.ViewHolder {

            TextView txt_headDate;
            TextView txt_headAmount;
            TextView txt_dayOfWeek;
            TextView txt_month;

            HeaderViewHolder(View itemView) {
                super(itemView);
                txt_headDate = (TextView) itemView.findViewById(R.id.day);
                txt_dayOfWeek = (TextView) itemView.findViewById(R.id.dayOfWeek);
                txt_month = (TextView) itemView.findViewById(R.id.month);
                txt_headAmount = (TextView) itemView.findViewById(R.id.headAmount);
            }

        }

        private class EventViewHolder extends RecyclerView.ViewHolder {

            TextView tvPurse;
            TextView tvCategory;
            TextView tvComent;
            TextView tvAmount;
            TextView tvDate;
            ImageView ivCategory;

            EventViewHolder(View itemView) {
                super(itemView);
                tvPurse = (TextView) itemView.findViewById(R.id.textPurse);
                tvCategory = (TextView) itemView.findViewById(R.id.textCategory_list);
                tvComent = (TextView) itemView.findViewById(R.id.textComent);
                tvAmount = (TextView) itemView.findViewById(R.id.textAmount);
                tvDate = (TextView) itemView.findViewById(R.id.textDate);
                ivCategory = (ImageView) itemView.findViewById(R.id.imageViewCategory_List);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MotionItem motionItem = (MotionItem) items.get(getLayoutPosition());
                        onMotionClickListener(motionItem,v);
                    }
            });
        }
        public void onMotionClickListener(MotionItem motionItem,View v){
            Motion motion=motionItem.getmotion();
            //Log.i("Great work today",""+motion.getComent());
            Intent i = new Intent(v.getContext(), MotionActivity.class);
            i.putExtra("motionObject", (Serializable) motion);
            Bundle mBundle = new Bundle();
            startActivity(v.getContext(),i,mBundle);

        }

        }
        @NonNull
        private List<ListItem> items = Collections.emptyList();


        public MotionsAdapter(@NonNull List<ListItem> items) {
            this.items = items;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            switch (viewType) {
                case ListItem.TYPE_HEADER: {
                    View itemView = inflater.inflate(R.layout.item_date, parent, false);
                    return new HeaderViewHolder(itemView);
                }
                case ListItem.TYPE_MOTION: {
                    View itemView = inflater.inflate(R.layout.item_motion, parent, false);
                    return new EventViewHolder(itemView);
                }
                default:
                    throw new IllegalStateException("unsupported item type");
            }
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            int viewType = getItemViewType(position);
            switch (viewType) {
                case ListItem.TYPE_HEADER: {
                    HeaderItem header = (HeaderItem) items.get(position);
                    HeaderViewHolder holder = (HeaderViewHolder) viewHolder;

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(header.getDate());

                    int mYear = calendar.get(Calendar.YEAR);
                    int mDayOfWeak = calendar.get(Calendar.DAY_OF_WEEK);
                    int mMonth = calendar.get(Calendar.MONTH)+1;
                    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                    String textData=""+mMonth+"/"+mYear;
                    if (mDay<10){
                        holder.txt_headDate.setText("0"+mDay);
                    }
                    else {
                        holder.txt_headDate.setText(""+mDay);
                    }
                    Long dateInt = header.getDate();
                    holder.txt_dayOfWeek.setText(""+mDayOfWeak);
                    holder.txt_month.setText(""+textData);

                    holder.txt_headAmount.setText(Double.toString(header.getAmmount()));
                    break;
                }
                case ListItem.TYPE_MOTION: {
                    MotionItem motion = (MotionItem) items.get(position);
                    EventViewHolder holder = (EventViewHolder) viewHolder;
                    // your logic here
                    Motion motionObject=motion.getmotion();
                    holder.tvCategory.setText(motionObject.getCategory());
                    holder.tvComent.setText(motionObject.getComent());
                    Double amount = motionObject.getAmount();
                    holder.tvAmount.setText(Double.toString(amount));;
                    if (amount>0){
                        holder.tvAmount.setTextColor(0xFF13BDBD);
                    }
                    else {
                        holder.tvAmount.setTextColor(0xFFBD1313);
                    }
                    holder.tvPurse.setText(motionObject.getPurse());

                    break;
                }
                default:
                    throw new IllegalStateException("unsupported item type");
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).getType();
        }
}
