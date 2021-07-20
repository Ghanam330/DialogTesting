package com.example.dialogcheckinternetconnection.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.Adapter.CourseAdapter;
import com.example.dialogcheckinternetconnection.Model.CourseModel;
import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.MainActivity2;
import com.example.dialogcheckinternetconnection.ui.SplachScreen.SplachScreen;

import java.util.ArrayList;
import java.util.List;

public class AboutCourseActivity extends AppCompatActivity {
    int id;
    String time, title;
    private TextView txt_title, discribtion, txt_time;
    private Button go_to_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_course);
        txt_title = findViewById(R.id.title_course);
        discribtion = findViewById(R.id.body_text);
        txt_time = findViewById(R.id.text_time);
        go_to_course = findViewById(R.id.btn_goto_course);

        Bundle extre = getIntent().getExtras();
        if (extre != null) {
            id = getIntent().getIntExtra("id", 0);
            title = getIntent().getStringExtra("title");
            time = getIntent().getStringExtra("time");
        }
        txt_title.setText(title);
        txt_time.setText(time);

        if (id == 0) {
            discribtion.setText("ما هي لغة الجافا ؟" + "\n" + "جافا (بالإنجليزية: Java) هي عبارة عن لغة برمجة" +
                    " ابتكرها جيمس جوسلينج في عام 1992م أثناء عمله في مختبرات شركة صن ميكروسيستمز،" +
                    " وذلك لاستخدامها بمثابة العقل المفكر المستخدم لتشغيل الأجهزة التطبيقية الذكية مثل التيلفزيون التفاعلي"
            );
        } else if (id == 1) {
        } else if (id == 2) {
        } else if (id == 3) {
        } else if (id == 4) {
        } else if (id == 5) {
        } else if (id == 6) {
        } else if (id == 7) {
        } else if (id == 8) {
        }
        go_to_course.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContentCourse.class);
            intent.putExtra("title", title);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

}
// id java 0 ,1,2
// id kotlin 3 ,4,5
// Android 6,7,8



