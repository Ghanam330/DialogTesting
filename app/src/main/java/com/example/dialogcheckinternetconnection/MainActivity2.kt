package com.example.dialogcheckinternetconnection

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

 class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        checkConnection()
    }




    private fun checkConnection() {
        val manager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = manager.activeNetworkInfo
        if (null != networkInfo) {
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "Wifi Connected", Toast.LENGTH_LONG).show()
            } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Mobile Data Connected", Toast.LENGTH_LONG).show()
            }
        } else {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.alert_dialog)
            dialog.setCanceledOnTouchOutside(false)
            dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val btn :Button =dialog.findViewById(R.id.btn_try_again)
            btn.setOnClickListener{
                recreate()
            }
            dialog.show()
        }
    }

   // loadingProgressBar.setVisibility(View.GONE);
}

