package com.example.feedingtime

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity

class AlertsAndNewAlert : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerts_and_add_alert)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            executeActivityButtons(MainActivityAlerts::class.java)
        }
    }


    //Execute ActivityButtons Function
    private fun executeActivityButtons(outraActivity: Class<*>) {
        val x = Intent(this, outraActivity)
        startActivity(x)
    }

}