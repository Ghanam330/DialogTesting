package com.example.dialogcheckinternetconnection.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dialogcheckinternetconnection.Model.CourseModel;
import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.listeners.ItemListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {
List<CourseModel>modelList;
ItemListener itemListener;

public CourseAdapter(List<CourseModel>modelList,ItemListener itemListener){
    this.modelList=modelList;
    this.itemListener=itemListener;
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_course
                ,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(modelList.get(position).getImage())
                .into(holder.imageView);
        holder.textTime.setText(modelList.get(position).getTime());
        holder.textName.setText(modelList.get(position).getNameCourse());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.onNoteClicked(modelList.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList==null?0:modelList.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textTime;
        private TextView textName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_course);
            textTime=itemView.findViewById(R.id.time_course);
            textName=itemView.findViewById(R.id.course_name);
        }
    }
}
