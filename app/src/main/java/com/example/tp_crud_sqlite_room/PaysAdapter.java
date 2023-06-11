package com.example.tp_crud_sqlite_room;


import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaysAdapter extends RecyclerView.Adapter<PaysAdapter.ViewHolder> {
    private Activity context;
    private List<PaysEntity> data;
    private Database database;

    public PaysAdapter(Activity context, List<PaysEntity> data) {
        this.context = context;
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PaysAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pays_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaysAdapter.ViewHolder holder, int position) {
        final PaysEntity pays = data.get(position);
        database = Database.getInstance(context);
        holder.textView.setText(pays.getText());
        holder.textView2.setText(pays.getCapital());
        holder.textView3.setText(pays.getHabitants().toString());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaysEntity pays = data.get(holder.getAdapterPosition());
                final int id = pays.getId();
                String sText = pays.getText();
                String cText = pays.getCapital();
                Float nText = pays.getHabitants();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.update_dialog);
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setLayout(width, height);
                dialog.show();
                final EditText editText = dialog.findViewById(R.id.edit_text);
                final EditText editText2 = dialog.findViewById(R.id.edit_text4);
                final EditText editText3 = dialog.findViewById(R.id.edit_text5);
                Button btnUpdate = dialog.findViewById(R.id.btnupdate);
                editText.setText(sText);
                editText2.setText(cText);
                editText3.setText(nText.toString());

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String uText = editText.getText().toString().trim();
                        String uCText = editText2.getText().toString().trim();
                        Float aFloat = Float.parseFloat(editText3.getText().toString().trim());
                        database.paysDao().update(id, uText, uCText, aFloat);
                        data.clear();
                        data.addAll(database.paysDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaysEntity d = data.get(holder.getAdapterPosition());
                database.paysDao().delete(d);
                int position = holder.getAdapterPosition();
                data.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, data.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView, textView2, textView3;
        ImageView btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewPays);
            textView2 = itemView.findViewById(R.id.textViewCapital);
            textView3 = itemView.findViewById(R.id.textViewHabitant);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}

