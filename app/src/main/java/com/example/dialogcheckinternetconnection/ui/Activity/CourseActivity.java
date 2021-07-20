package com.example.dialogcheckinternetconnection.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.Adapter.CourseAdapter;
import com.example.dialogcheckinternetconnection.Model.CourseModel;
import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.MainActivity2;
import com.example.dialogcheckinternetconnection.ui.SplachScreen.SplachScreen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<CourseModel> modelList = new ArrayList<>();
    int id;
    private String title;
    private TextView txt_title;

    Intent intent;

    public SharedPreferences onBoardingScreen;
    private boolean isFirstTime;


    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        recyclerView = findViewById(R.id.rv);
        txt_title = findViewById(R.id.title_course);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = getIntent().getIntExtra("position", 0);
            title = getIntent().getStringExtra("title");
        }

        txt_title.setText(title);



        RecyclerView.LayoutManager horizontalLayout = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        adapter = new CourseAdapter(modelList,
                this::onCourseClicked);
        recyclerView.setAdapter(adapter);



        if (id == 0) {

            javaTarck();

        } else if (id == 1) {

            kotlinTrack();

        } else if (id == 2) {

            AndroidTrack();
        }
    }

    private void javaTarck() {


        myRef.child("Course").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    modelList.add(snapshot1.getValue(CourseModel.class));

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CourseActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void kotlinTrack() {


        myRef.child("Course2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    modelList.add(snapshot1.getValue(CourseModel.class));

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                finish();
            }
        });


    }

    private void AndroidTrack() {
        myRef.child("Course3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    modelList.add(snapshot1.getValue(CourseModel.class));

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                finish();
            }
        });


    }


    public void onCourseClicked(CourseModel courseModel, int position) {
        int id = modelList.get(position).getId();
        String title1 = modelList.get(position).getNameCourse();
        String time = modelList.get(position).getTime();
        intent = new Intent(getApplicationContext(), AboutCourseActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title1);
        intent.putExtra("time", time);
        startActivity(intent);
    }
}

/*

    private void javaTarck() {

        modelList.add(new CourseModel(0, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "90 h 30 min ", "BasicsJava"));
        modelList.add(new CourseModel(1, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "60 h 30 min ", "AdvancedJava"));
        modelList.add(new CourseModel(2, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "13 h 30 min ", "VersedJava"));
    }

    private void kotlinTrack() {

        modelList.add(new CourseModel(3, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "1 h 30 min ", "BasicsKotlin"));
        modelList.add(new CourseModel(4, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "3 h 30 min ", "AdvancedKotlin"));
        modelList.add(new CourseModel(5, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "6 h 30 min ", "VersedKotlin"));
    }

    private void AndroidTrack() {

        modelList.add(new CourseModel(6, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "9 h 30 min ", "BasicsAndroid"));
        modelList.add(new CourseModel(7, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "20 h 30 min ", "AdvancedAndroid"));
        modelList.add(new CourseModel(8, "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f", "30 h 30 min ", "VersedAndroid"));
    }
 */