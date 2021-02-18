package com.example.dialogcheckinternetconnection.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dialogcheckinternetconnection.R
import com.example.dialogcheckinternetconnection.ui.Fragment.CoursesFragment
import com.example.dialogcheckinternetconnection.ui.Fragment.ProfileFragment
import com.example.dialogcheckinternetconnection.ui.Fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity2 : AppCompatActivity() {
    private var bottomNavigationView: BottomNavigationView? = null
    private val coursesFragment = CoursesFragment()
    private val profileFragment = ProfileFragment()
    private val settingFragment = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        replaceFragment(coursesFragment)
        bottomNavigationView=findViewById(R.id.bottom_navigation)
        bottomNavigationView?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //  check the  phone connect Internet or not
        checkConnection()
        // .....................



    }


    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_counter, fragment)
            transaction.commit()
        }

    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.profile -> replaceFragment(profileFragment)
            R.id.setting ->replaceFragment(settingFragment)
            R.id.cources -> replaceFragment(coursesFragment)
        }
        true
    }


    //  check the  phone connect Internet or not
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
            val btn: Button = dialog.findViewById(R.id.btn_try_again)
            btn.setOnClickListener {
                recreate()
            }
            dialog.show()
        }
    }
    // .....................


    // loadingProgressBar.setVisibility(View.GONE);
}

