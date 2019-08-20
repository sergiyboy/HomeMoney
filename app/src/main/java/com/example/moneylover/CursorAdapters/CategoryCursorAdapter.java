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
import com.example.moneylover.data.CategoryContract;

public class CategoryCursorAdapter extends CursorAdapter {
    public CategoryCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_category,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvCategory = (TextView) view.findViewById(R.id.textCategory_list);
        ImageView ivCategory  = (ImageView) view.findViewById(R.id.imageViewCategory_List);
        String category = cursor.getString(cursor.getColumnIndexOrThrow(CategoryContract.CategoryEntry.KEY_name));
        tvCategory.setText(category);
        ivCategory.setImageResource(R.drawable.img_bitcoin);
    }

}