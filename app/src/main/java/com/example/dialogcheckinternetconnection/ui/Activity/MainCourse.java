package com.example.dialogcheckinternetconnection.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.Fragment.EditorFragment;
import com.example.dialogcheckinternetconnection.ui.Fragment.ExamFragment;
import com.example.dialogcheckinternetconnection.ui.Fragment.LessonFragment;

public class MainCourse extends AppCompatActivity {
    String titleCourse, urlVideo, description, titleItemCourse;
    private Button btn_lesson, btn_editor, btn_exam;
    Fragment fragment;
    Bundle bundle = new Bundle();
    private TextView title_txt, titleCourse_txt;

    int setNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_course);
        btn_lesson = findViewById(R.id.fragment_lesion);
        btn_editor = findViewById(R.id.fragment_editor);
        btn_exam = findViewById(R.id.fragment_test);
        title_txt = findViewById(R.id.title_item_course);
        titleCourse_txt = findViewById(R.id.title_course);
        fragment = new LessonFragment();


        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            titleCourse = getIntent().getStringExtra("title");
            titleItemCourse = getIntent().getStringExtra("titleItemCourse");
            urlVideo = getIntent().getStringExtra("urlVideo");
            description = getIntent().getStringExtra("description");

            setNo = getIntent().getIntExtra("setNo",0);
        }

        title_txt.setText(titleItemCourse);
        titleCourse_txt.setText(titleCourse);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment).commit();

        bundle.putString("title", titleCourse);
        bundle.putString("urlVideo", urlVideo);
        bundle.putString("description", description);
        fragment.setArguments(bundle);

        btn_lesson.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout
                    , fragment).commit();
            fragment.setArguments(bundle);

        });
        btn_editor.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout
                    , new EditorFragment()).commit();

        });
        btn_exam.setOnClickListener(v -> {
            Fragment fragment1 =new ExamFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout
                    , fragment1).commit();
            bundle.putInt("setNo",setNo);
            bundle.putString("title", titleCourse);
            fragment1.setArguments(bundle);
        });
    }
}