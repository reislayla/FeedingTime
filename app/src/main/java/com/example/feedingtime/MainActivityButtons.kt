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
        oBotaoReg.setOnClickListener {
            executeActivityButtons(MainActivityRegisterAnimals::class.java)
        }
        oBotaoLogout.setOnClickListener {
            Toast.makeText(this,"Logging Out...", Toast.LENGTH_LONG).show()
            signOut()
            executeActivityButtons(MainActivity::class.java)
        }
        //Logout
        firebaseAuth.addAuthStateListener {
            if(firebaseAuth.currentUser==null) {
                this.finish()
            }
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