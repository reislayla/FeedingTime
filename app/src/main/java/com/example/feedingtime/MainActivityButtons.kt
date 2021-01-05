package com.example.feedingtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class MainActivityButtons : AppCompatActivity() {

    //Variables
    lateinit var oBotaoReg: Button
    lateinit var oBotaoAnim: Button
    lateinit var oBotaoAlert: Button
    lateinit var oBotaoLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainbuttons)

        //FindView
        oBotaoReg = findViewById(R.id.registar)
        oBotaoAnim = findViewById(R.id.animais)
        oBotaoAlert = findViewById(R.id.alertas)
        oBotaoLogout = findViewById(R.id.logout)

        //onClick
        oBotaoReg.setOnClickListener {
            executeActivityButtons(MainActivityRegisterAnimals::class.java)
        }
        oBotaoLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivityButtons::class.java)
            startActivity(intent)
        }
    }

    //Execute ActivityButtons Function
    private fun executeActivityButtons(outraActivity: Class<*>) {
        val x = Intent(this, outraActivity)
        startActivity(x)
    }
}