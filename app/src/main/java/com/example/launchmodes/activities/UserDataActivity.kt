package com.example.launchmodes.activities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import com.example.launchmodes.fragments.FormFragment
import com.example.launchmodes.R
import com.example.launchmodes.fragments.UserDataFragment
import com.example.launchmodes.helper.DataFlairProvider

class UserDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)
        val br = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_tag, UserDataFragment.newInstance(), null)
                    .addToBackStack("")
                    .commit()
            }

        }
        registerReceiver(br, IntentFilter(DataFlairProvider.PROVIDER_NAME))
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, FormFragment.newInstance(), null)
            .addToBackStack("").commit()
    }

}