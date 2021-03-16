package com.example.dialogcheckinternetconnection.ui.Fragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.Model.ReminderBroadcast;
import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.Login.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class SettingFragment extends Fragment {
    private TextView txt_name, txt_email;
    private RelativeLayout reset_password;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private Switch sw;


    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txt_name = view.findViewById(R.id.text_name);
        txt_email = view.findViewById(R.id.text_email);
        reset_password = view.findViewById(R.id.reset_password);
        sw = view.findViewById(R.id.simpleSwitch);

        sharedPreferences = getActivity().getSharedPreferences("SHARD_PRE", Context.MODE_PRIVATE);
        SharedPreferences shared = getActivity().getSharedPreferences("save",
                Context.MODE_PRIVATE);
        sw.setChecked(shared.getBoolean("value", true));


        notification();
        getdatashard();
        resetPassword();
    }

    private void getdatashard() {
        String name = sharedPreferences.getString("Name", "");
        txt_name.setText(name);
        String email = sharedPreferences.getString("Email", "");
        txt_email.setText(email);
    }

    private void notification() {
        sw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("save",
                            Context.MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    sw.setChecked(true);
                    createNotification();
                } else {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("save",
                            Context.MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    sw.setChecked(false);
                    createNotificationChannel();
                }
            }
        });
    }

    private void createNotification() {
        Toast.makeText(getContext(), "Reminder Set!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), ReminderBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);  //set repeating every 24 hours
        /*
        long timeAtButtonClick = System.currentTimeMillis();
       long tendSecondInMills = 1000000 * 10;
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonClick +
                        tendSecondInMills,
                pendingIntent);

         */
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = " LemubitReminderChannel";
            String descriPtion = "Cancel for lemubit reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel cancel = new NotificationChannel("notifyLemubit", name, importance);
            cancel.setDescription(descriPtion);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(cancel);
        }
    }

    private void resetPassword() {
        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset password?");
                passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("yes", (dialog,which) -> {
                    String mail = resetMail.getText().toString();
                    if (mail.isEmpty()) {
                        resetMail.setError("please write email");
                        return;
                    }
                    auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                });
                passwordResetDialog.setNegativeButton("No", (dialog, which) -> {
                });
                passwordResetDialog.create().show();
            }
        });
    }
}