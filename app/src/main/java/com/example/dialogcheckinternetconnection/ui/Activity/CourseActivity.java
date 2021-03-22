package com.example.dialogcheckinternetconnection.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dialogcheckinternetconnection.Adapter.CourseAdapter;
import com.example.dialogcheckinternetconnection.Model.CourseModel;
import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.MainActivity2;
import com.example.dialogcheckinternetconnection.ui.SplachScreen.SplachScreen;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<CourseModel> modelList;
    int id;
    private String title;
    private TextView txt_title;
    SharedPreferences onBoardingScreen;
    private boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        recyclerView = findViewById(R.id.rv);
        txt_title = findViewById(R.id.title_course);

        onBoardingScreen =getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
        isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

        Bundle extre = getIntent().getExtras();
        if (extre != null) {
            id = getIntent().getIntExtra("position", 0);
            title = getIntent().getStringExtra("title");
        }
        txt_title.setText(title);

        if (id == 0) {
            javaTarck();
        } else if (id == 1) {
            kotlinTrack();
        } else if (id == 2) {
            AndroidTrack();
        }

        RecyclerView.LayoutManager horizontalLayout = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        adapter = new CourseAdapter(modelList,
                this::onCourseClicked);
        recyclerView.setAdapter(adapter);
    }

    private void javaTarck() {
        modelList = new ArrayList<>();
        modelList.add(new CourseModel(0, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "90 h 30 min ", "BasicsJava"));
        modelList.add(new CourseModel(1, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "60 h 30 min ", "AdvancedJava"));
        modelList.add(new CourseModel(2, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "13 h 30 min ", "VersedJava"));
    }

    private void kotlinTrack() {
        modelList = new ArrayList<>();
        modelList.add(new CourseModel(3, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "1 h 30 min ", "BasicsKotlin"));
        modelList.add(new CourseModel(4, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "3 h 30 min ", "AdvancedKotlin"));
        modelList.add(new CourseModel(5, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "6 h 30 min ", "VersedKotlin"));
    }

    private void AndroidTrack() {
        modelList = new ArrayList<>();
        modelList.add(new CourseModel(6, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "9 h 30 min ", "BasicsAndroid"));
        modelList.add(new CourseModel(7, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "20 h 30 min ", "AdvancedAndroid"));
        modelList.add(new CourseModel(8, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "30 h 30 min ", "VersedAndroid"));
    }

    public void onCourseClicked(CourseModel courseModel, int position) {
        int id = modelList.get(position).getId();
        String title1 = modelList.get(position).getNameCourse();
        String time = modelList.get(position).getTime();
        Intent intent = new Intent(getApplicationContext(), AboutCourseActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title1);
        intent.putExtra("time", time);
        startActivity(intent);
    }
}

/*
   public void onCourseClicked(CourseModel courseModel, int position) {
       int id=modelList.get(position).getId();
       String title1=modelList.get(position).getNameCourse();
       String time=modelList.get(position).getTime();
        Intent intent = new Intent(getApplicationContext(), AboutCourseActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("title",title1);
        intent.putExtra("time",time);
        startActivity(intent);
 */