package com.example.feedingtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivityButtons : AppCompatActivity() {

    //Variables
    lateinit var oBotaoReg: Button
    lateinit var oBotaoAnim: Button
    lateinit var oBotaoAlert: Button
    lateinit var oBotaoLogout: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainbuttons)

        //Firebase
        firebaseAuth=FirebaseAuth.getInstance()

        //FindView
        oBotaoReg = findViewById(R.id.registar)
        oBotaoAnim = findViewById(R.id.animais)
        oBotaoAlert = findViewById(R.id.alertas)
        oBotaoLogout = findViewById(R.id.logout)

        //onClick
        //Animal register
        oBotaoReg.setOnClickListener {
            executeActivityButtons(MainActivityRegisterAnimals::class.java)
        }
        //Logout
        oBotaoLogout.setOnClickListener {
            Toast.makeText(this,"Logging Out...", Toast.LENGTH_LONG).show()
            signOut()
            executeActivityButtons(MainActivity::class.java)
        }
        firebaseAuth.addAuthStateListener {
            if(firebaseAuth.currentUser==null) {
                this.finish()
            }
        }
        //Animal list
        oBotaoAnim.setOnClickListener {
            executeActivityButtons(MainActivityAnimals::class.java)
        }

        //Alerts
        oBotaoAlert.setOnClickListener {
            executeActivityButtons(AlertsAndNewAlert::class.java)
        }
    }

    //Execute ActivityButtons Function
    private fun executeActivityButtons(outraActivity: Class<*>) {
        val x = Intent(this, outraActivity)
        startActivity(x)
    }

    //Logout
    private fun signOut(){
        firebaseAuth.signOut()
    }
}