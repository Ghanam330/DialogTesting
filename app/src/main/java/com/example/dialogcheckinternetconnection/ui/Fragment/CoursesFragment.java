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
        modelList.add(new CourseModel(0,"https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/ProgramingLangwedge%2Fjava.png?alt=media&token=92c64a55-4aae-4c55-9d3c-8a82fb506933", "3 h 30 min ", "Java"));
        modelList.add(new CourseModel(1,"https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/ProgramingLangwedge%2FKotlin.png?alt=media&token=1fe5ff10-a4ab-40d2-a796-531cb64dcf95", "3 h 30 min ", "Kotlin"));
        modelList.add(new CourseModel(2,"https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/ProgramingLangwedge%2Funnamed.jpg?alt=media&token=6be2e6e0-f56a-4e35-ad28-0a7e856a2390", "3 h 30 min ", "Android"));
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