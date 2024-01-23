package com.example.zakupy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProduktAdapter extends RecyclerView.Adapter<ProduktAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;
    private DatabaseHelper db;
    private static OnCheckedChangeListener onCheckedChangeListener;

    public ProduktAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        this.db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }
        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.C_ID));
        @SuppressLint("Range") String nazwa = cursor.getString(cursor.getColumnIndex(DatabaseHelper.C_NAZWA));
        @SuppressLint("Range") String kategoria = cursor.getString(cursor.getColumnIndex(DatabaseHelper.C_KATEGORIA));
        @SuppressLint("Range") boolean wybrany = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.C_WYBRANY)) == 1;

        holder.textViewC_Nazwa.setText(nazwa);
        holder.textViewC_Kategoria.setText(kategoria);
        holder.checkBoxC_Wybrany.setChecked(wybrany);
        holder.itemView.setTag(id);

        holder.checkBoxC_Wybrany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Object currentPosition = holder.itemView.getTag();
                db.aktualizujProdukty(currentPosition, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void closeCursor() {
        if (cursor != null) {
            cursor.close();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewC_Nazwa;
        public TextView textViewC_Kategoria;
        public CheckBox checkBoxC_Wybrany;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewC_Nazwa = itemView.findViewById(R.id.textViewC_Nazwa);
            textViewC_Kategoria = itemView.findViewById(R.id.textViewC_Kategoria);
            checkBoxC_Wybrany = itemView.findViewById(R.id.checkBoxC_Wybrany);
        }


    }

    public void updateData(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int position, boolean isChecked);
    }



    public static void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        ProduktAdapter.onCheckedChangeListener = listener;
    }
}
