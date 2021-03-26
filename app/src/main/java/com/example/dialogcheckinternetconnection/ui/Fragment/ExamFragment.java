package com.example.dialogcheckinternetconnection.ui.Fragment;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.Adapter.ViewDialog;
import com.example.dialogcheckinternetconnection.Model.QuestionModel;
import com.example.dialogcheckinternetconnection.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ExamFragment extends Fragment {
    int setNo;
    private String titleCourse;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private TextView question, noIndicator;
    private LinearLayout optionsContainer;
    private Button nextBtn;

    private int postion = 0;
    private int count = 0;
    private int score = 0;
    List<QuestionModel> list = new ArrayList<>();
    Bundle bundle=new Bundle();


    public ExamFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiaz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle=getArguments();
        if (bundle != null) {
            setNo = bundle.getInt("setNo", 0);
            titleCourse = bundle.getString("title");
        }


        question = view.findViewById(R.id.quastion);
        noIndicator = view.findViewById(R.id.no_indicator);
        optionsContainer = view.findViewById(R.id.options_container);
        nextBtn = view.findViewById(R.id.next_btn);


        // get dataQuestion From FireBase

        myRef.child("SETS").child(titleCourse).child("questions").orderByChild("setNo").equalTo(setNo)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            list.add(snapshot1.getValue(QuestionModel.class));
                        }

                        if (list.size() > 0) {

                            for (int i = 0; i < 4; i++) {
                                optionsContainer.getChildAt(i).setOnClickListener(v ->
                                        checkAnswer(((Button) v)));
                            }
                            playAnim(question, 0, list.get(postion).getQuestion());
                            nextBtn.setOnClickListener(v -> {

                                nextBtn.setEnabled(false);
                                nextBtn.setAlpha(0.7f);
                                enableOption(true);
                                postion++;
                                if (postion == list.size()) {
                                    


                                  ScoreFragment scoreFragment =new ScoreFragment();
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.frame_layout,scoreFragment)
                                            .addToBackStack(null)
                                            .commit();
                                    bundle.putInt("score", score);
                                    bundle.putInt("total", list.size());
                                    scoreFragment.setArguments(bundle);


                                   // getActivity().finish();
                                    // score activity
                                    return;
                                }

                                count = 0;
                                playAnim(question, 0, list.get(postion).getQuestion());
                            });
                        } else {
                            ViewDialog alert = new ViewDialog();
                            alert.showDialog(getActivity(), "no question");

                            final Timer t = new Timer();
                            TimerTask tt = new TimerTask() {
                                @Override
                                public void run() {
                                    if (count == 100)
                                        getActivity().finish();

                                    t.cancel();
                                }
                            };
                            t.schedule(tt, 0, 3);

                            /*
                           LessonFragment lessonFragment =new LessonFragment();
                                    getActivity().getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.frame_layout,lessonFragment)
                                            .addToBackStack(null)
                                            .commit();
                            Toast.makeText(getContext(), "no question", Toast.LENGTH_SHORT).show();

                             */
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                });





/*
      //  playAnim(question, 0, list.get(postion).getQuestion());
        nextBtn.setOnClickListener(v -> {

            nextBtn.setEnabled(false);
            nextBtn.setAlpha(0.7f);
            enableOption(true);
            postion++;
            if (postion == list.size()) {

                // score activity
                return;
            }

            count = 0;
            playAnim(question, 0, list.get(postion).getQuestion());
        });

 */
    }


    // anmation question
    private void playAnim(View view, final int value, final String data) {
        view.animate().alpha(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {

                        option = list.get(postion).getOptionA();
                    } else if (count == 1) {

                        option = list.get(postion).getOptionB();
                    } else if (count == 2) {
                        option = list.get(postion).getOptionC();

                    } else if (count == 3) {
                        option = list.get(postion).getOptionD();

                    }
                    playAnim(optionsContainer.getChildAt(count), 0, option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        noIndicator.setText(postion + 1 + "/" + list.size());
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void checkAnswer(Button selectOption) {

        enableOption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);

        if (selectOption.getText().toString().equals(list.get(postion).getCorrectANS())) {
            // correct
            score++;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            }

        } else {

            // in correct
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ff0000")));
            }
            Button correctoption = optionsContainer.findViewWithTag(list.get(postion).getCorrectANS());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
            }

        }
    }


    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            optionsContainer.getChildAt(i).setEnabled(enable);


            if (enable) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
                }
            }

        }
    }
}


/*


 list.add(new QuestionModel("Question1", "a", "b", "c", "d", "a", 0));
        list.add(new QuestionModel("Question1", "a", "b", "c", "d", "b", 0));
        list.add(new QuestionModel("Question3", "a", "b", "c", "d", "c", 0));
        list.add(new QuestionModel("Question4", "a", "b", "c", "d", "a", 0));
        list.add(new QuestionModel("Question5", "a", "b", "c", "d", "d", 0));
        list.add(new QuestionModel("Question6", "a", "b", "c", "d", "c", 0));
        list.add(new QuestionModel("Question7", "a", "b", "c", "d", "b", 0));


 */