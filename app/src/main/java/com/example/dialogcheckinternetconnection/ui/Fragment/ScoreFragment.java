package com.example.dialogcheckinternetconnection.ui.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dialogcheckinternetconnection.R;


public class ScoreFragment extends Fragment {
    private TextView scored, total;
    private Button doneBtn;

    private int score_int, total_int;
    Bundle bundle = new Bundle();

    public ScoreFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        scored = view.findViewById(R.id.scored);
        total = view.findViewById(R.id.total);
        doneBtn = view.findViewById(R.id.done_btn);
        bundle = getArguments();


        scored.setText(String.valueOf(bundle.getInt("score", 0)));
        total.setText("Out OF " + bundle.getInt("total", 0));

        doneBtn.setOnClickListener(v ->
                getActivity().finish());
    }
}