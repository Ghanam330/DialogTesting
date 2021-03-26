package com.example.dialogcheckinternetconnection.ui.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dialogcheckinternetconnection.R;

import me.testica.codeeditor.Editor;
import me.testica.codeeditor.SyntaxHighlightRule;


public class EditorFragment extends Fragment {


    public EditorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Editor editor = (Editor) view.findViewById(R.id.editor);
        // changing text color and background color to number lines view
        editor.getNumLinesView().setBackgroundColor(Color.BLACK);
        editor.getNumLinesView().setTextColor(Color.WHITE);

// applying left padding to code view
        editor.getEditText().setPadding(10, 0, 0, 0);
        editor.setSyntaxHighlightRules(
                new SyntaxHighlightRule("[0-9]*", "#00838f"),
                new SyntaxHighlightRule("/\\\\*(?:.|[\\\\n\\\\r])*?\\\\*/|(?<!:)//.*", "#9ea7aa")
        );
    }
}
