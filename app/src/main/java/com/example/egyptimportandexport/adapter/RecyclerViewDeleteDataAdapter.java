package com.example.egyptimportandexport.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egyptimportandexport.R;
import com.example.egyptimportandexport.data.model.Hospital;
import com.example.egyptimportandexport.data.model.HospitalDelete;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecyclerViewDeleteDataAdapter extends FirebaseRecyclerAdapter<HospitalDelete, RecyclerViewDeleteDataAdapter.myviewholder> {

    public RecyclerViewDeleteDataAdapter(@NonNull FirebaseRecyclerOptions<HospitalDelete> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull HospitalDelete model) {
        holder.textView.setText(model.getHospitalName());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.style_delete_recyclerview , parent , false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        TextView textView;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_StyleDeleteData);
        }
    }
}
