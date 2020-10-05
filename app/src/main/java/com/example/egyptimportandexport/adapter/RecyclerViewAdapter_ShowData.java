package com.example.egyptimportandexport.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.egyptimportandexport.R;


public class RecyclerViewAdapter_ShowData extends RecyclerView.Adapter<RecyclerViewAdapter_ShowData.ViewHolder> {

    private Context mContext ;
    private Cursor mCursor;

    public RecyclerViewAdapter_ShowData(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView hospitalViewHolder;
        public TextView doctorViewHolder;
        public TextView orderViewHolder;
        public TextView timeViewHolder;
        public TextView endViewHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            hospitalViewHolder = itemView.findViewById(R.id.hospital_TV_StyleRecyclerview);
            doctorViewHolder = itemView.findViewById(R.id.doctor_TV_StyleRecyclerview);
            orderViewHolder = itemView.findViewById(R.id.order_TV_StyleRecyclerview);
            timeViewHolder = itemView.findViewById(R.id.time_TV_StyleRecyclerview);
            endViewHolder = itemView.findViewById(R.id.end_TV_StyleRecyclerview);
        }
    }

    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.style_recyclerview , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     if (!mCursor.moveToPosition(position)){
        return;
     }

     if (mCursor == null){
         Toast.makeText(mContext, "eeeeee", Toast.LENGTH_SHORT).show();
     }else {
         String hospital = mCursor.getString(mCursor.getColumnIndex("hospital"));
         String doctor = mCursor.getString(mCursor.getColumnIndex("doctor"));
         String order = mCursor.getString(mCursor.getColumnIndex("orrder"));
         String time  = mCursor.getString(mCursor.getColumnIndex("time"));
         long id = mCursor.getLong(mCursor.getColumnIndex("id"));

         holder.hospitalViewHolder.setText(hospital);
         holder.doctorViewHolder.setText(doctor);
         holder.orderViewHolder.setText(order);
         holder.timeViewHolder.setText(time);
         holder.itemView.setTag(id);
     }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void SwapCursor(Cursor newCursor){
        if (mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}