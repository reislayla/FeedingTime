package com.example.feedingtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivityRegisterAnimals : AppCompatActivity() {

    //Cancel and Save button
    lateinit var oBotaoCancel: Button
    lateinit var oBotaoSave: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register_animals)

        //FindView
        oBotaoCancel = findViewById(R.id.cancel_button)
        oBotaoSave = findViewById(R.id.save_button)

        //OnClick
        //Cancel
        oBotaoCancel.setOnClickListener {
            executeActivityButtons(MainActivityButtons::class.java)
        }
    }
    //Execute ActivityButtons Function
    private fun executeActivityButtons(outraActivity: Class<*>) {
        val x = Intent(this, outraActivity)
        startActivity(x)
    }
}