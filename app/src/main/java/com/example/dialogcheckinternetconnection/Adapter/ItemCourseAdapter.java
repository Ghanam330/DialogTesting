package com.example.dialogcheckinternetconnection.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dialogcheckinternetconnection.Model.CourseModel;
import com.example.dialogcheckinternetconnection.Model.ItemCourseModel;
import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.listeners.ItemCourseListener;
import com.example.dialogcheckinternetconnection.listeners.ItemListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;


public class ItemCourseAdapter extends RecyclerView.Adapter<ItemCourseAdapter.MyViewHolder> {
    List<ItemCourseModel> itemlList;
    ItemCourseListener itemListener;
    int counter = 0;


    private SharedPreferences mPrefs;

    public ItemCourseAdapter(List<ItemCourseModel> itemlList, ItemCourseListener itemListener) {
        this.itemlList = itemlList;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemCourseAdapter.MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course
                        , parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(itemlList.get(position).getImage())
                .into(holder.imageView);
        holder.txt_nameCourse.setText(itemlList.get(position).getTitle());
        holder.itemView.setOnClickListener(v -> {
            itemListener.onItemCourseClicked(itemlList.get(position), position);
            final Timer t = new Timer();
            TimerTask tt = new TimerTask() {
                @Override
                public void run() {
                    counter++;
                    holder.progressBar.setProgress(counter);
                    if (counter == 100)
                        t.cancel();
                }
            };
            t.schedule(tt, 0, 3);
        });

    }


    @Override
    public int getItemCount() {
        return itemlList == null ? 0 : itemlList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txt_nameCourse;
        private ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_course);
            txt_nameCourse = itemView.findViewById(R.id.title_course);
            progressBar = itemView.findViewById(R.id.progress_ba);
        }
    }
}
