package com.example.dialogcheckinternetconnection.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogcheckinternetconnection.Adapter.CourseAdapter;
import com.example.dialogcheckinternetconnection.Model.CourseModel;
import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.Activity.CourseActivity;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<CourseModel> modelList;


    public CoursesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv);

        modelList = new ArrayList<>();
        modelList.add(new CourseModel(0,"https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "3 h 30 min ", "Java"));
        modelList.add(new CourseModel(1,"https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "3 h 30 min ", "Kotlin"));
        modelList.add(new CourseModel(2,"https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "3 h 30 min ", "Android"));
        RecyclerView.LayoutManager horizontalLayout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        adapter = new CourseAdapter(modelList, this::onCourseClicked);
        recyclerView.setAdapter(adapter);
    }

    public void onCourseClicked(CourseModel courseModel, int position) {
        String title=modelList.get(position).getNameCourse();
        Intent intent = new Intent(getContext(), CourseActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}