package com.example.dialogcheckinternetconnection.ui.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.Login.LoginActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private CircleImageView image_profile, addImage;
    private TextView logout;
    private Uri image;
    private Dialog dialogprofile;
    private String downloadUrl;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logout = view.findViewById(R.id.logout_item);
        image_profile = view.findViewById(R.id.profile_image);


        logout.setOnClickListener(v -> {
            auth.signOut();
            Objects.requireNonNull(getActivity()).finish();
            startActivity(new Intent(getContext(), LoginActivity.class));
        });

        image_profile.setOnClickListener(v -> {
            dialogprofile.show();
        });
        setDialogprofile();

    }

    private void setDialogprofile() {
        dialogprofile = new Dialog(getContext());
        dialogprofile.setContentView(R.layout.ubload_imag_profile);
        addImage = dialogprofile.findViewById(R.id.circle_image);
        Button add_btn = dialogprofile.findViewById(R.id.btn_add);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 110);
            }
        });
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (image == null) {
                    Toast.makeText(getContext(), "please select your image", Toast.LENGTH_SHORT).show();
                    return;
                }
                uploadData();
                //  uplode data
            }
        });

    }

    private void uploadData() {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imageReferance = storageReference.child("profile").child(image.getLastPathSegment());
        UploadTask uploadTask = imageReferance.putFile(image);
        Task<Uri> uriTask = uploadTask.continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();

            }

            return imageReferance.getDownloadUrl().addOnCompleteListener(task1 -> {
                if (task1.isSuccessful()) {
                    downloadUrl = task1.getResult().toString();
                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUrl = task.getResult();
            } else {
                Toast.makeText(getContext(), "SomeThing went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110) {
            if (requestCode == RESULT_OK) {
                image = data.getData();
                addImage.setImageURI(image);
            }
        }
    }
}



        /*
        image_profile.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 110);

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 110) {
            if (requestCode == RESULT_OK) {
                image = data.getData();
                image_profile.setImageURI(image);
            }
        }
    }
}

         */