package com.example.moneylover.CursorAdapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moneylover.R;
import com.example.moneylover.data.MotionMoneyContract;

public class MotionsCursorAdapter extends CursorAdapter {
    public MotionsCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_motion,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvPurse = (TextView) view.findViewById(R.id.textPurse);
        TextView tvCategory = (TextView) view.findViewById(R.id.textCategory_list);
        TextView tvComent = (TextView) view.findViewById(R.id.textComent);
        TextView tvAmount = (TextView) view.findViewById(R.id.textAmount);
        TextView tvDate = (TextView) view.findViewById(R.id.textDate);
        ImageView ivCategory = (ImageView) view.findViewById(R.id.imageViewCategory_List);

        String purse = cursor.getString(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_Purse));
        String category = cursor.getString(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_Category));
        String coment = cursor.getString(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_coment));
        int amount = cursor.getInt(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_amount));
        Long date = cursor.getLong(cursor.getColumnIndexOrThrow(MotionMoneyContract.MotionEntry.KEY_date));
        // Populate fields with extracted properties
        tvPurse.setText(purse);
        tvCategory.setText(category);
        tvComent.setText(coment);
        tvAmount.setText(String.valueOf(amount));
        tvDate.setText(String.valueOf(date));
        ivCategory.setImageResource(R.drawable.img_bitcoin);
    }
}
