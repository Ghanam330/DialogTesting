package com.example.dialogcheckinternetconnection.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.Adapter.CourseAdapter;
import com.example.dialogcheckinternetconnection.Adapter.ItemCourseAdapter;
import com.example.dialogcheckinternetconnection.Model.CourseModel;
import com.example.dialogcheckinternetconnection.Model.ItemCourseModel;
import com.example.dialogcheckinternetconnection.R;

import java.util.ArrayList;
import java.util.List;

public class ContentCourse extends AppCompatActivity {
    private int id;
    private TextView txt_title;
    private String title;
    private RecyclerView recyclerView;
    private ItemCourseAdapter adapter;
    private List<ItemCourseModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_course);

        Bundle extre = getIntent().getExtras();
        if (extre != null) {
            id = getIntent().getIntExtra("id", 0);
            title = getIntent().getStringExtra("title");
        }
        txt_title = findViewById(R.id.title_course);
        recyclerView = findViewById(R.id.rv);
        txt_title.setText(title);
        Toast.makeText(this, "welcome" + id, Toast.LENGTH_SHORT).show();

        if (id == 0) {
            basicJava();
        } else if (id == 1) {

        } else if (id == 2) {

        }
        RecyclerView.LayoutManager horizontalLayout = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        adapter = new ItemCourseAdapter(modelList,
                this::onItemCourseClicked);
        recyclerView.setAdapter(adapter);
    }


    private void basicJava() {
        modelList = new ArrayList<>();
        modelList.add(new ItemCourseModel("Ahmed", "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/video%2F1.mp4?alt=media&token=6ae47a79-a7d8-4250-aeaa-7e1ca66bfba6", "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f"
                , "AhmedmOHAMEDgHANAM"));
        modelList.add(new ItemCourseModel("mohamed", "", "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f"
                , "AhmedmOHAMEDgHANAM"));
        modelList.add(new ItemCourseModel("Ahmed", "", "https://firebasestorage.googleapis.com/v0/b/codera-m-g.appspot.com/o/Patch%2FCS50.png?alt=media&token=b7653441-462c-410c-821b-4a59888c6e5f"
                , "AhmedmOHAMEDgHANAM"));
    }

    public void onItemCourseClicked(ItemCourseModel courseModel, int position) {
        Intent intent = new Intent(getApplicationContext(), MainCourse.class);
        String txt_course = modelList.get(position).getTitle();
        String url_course = modelList.get(position).getUrlVideo();
        String description_course = modelList.get(position).getDescription();
        intent.putExtra("titleItemCourse", txt_course);
        intent.putExtra("title",title);
        intent.putExtra("urlVideo", url_course);
        intent.putExtra("description", description_course);
        startActivity(intent);
    }
}


// id java 0 ,1,2
// id kotlin 3 ,4,5
// Android 6,7,8