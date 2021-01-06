package com.example.feedingtime

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

public class VariaveisGlobais : Application() {

}

class MainActivity : AppCompatActivity() {

    //Temporary login
    private lateinit var oEmail: EditText
    private lateinit var oPass: EditText
    private lateinit var oSignInButton: Button
    private lateinit var oSignUp: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Firebase
        firebaseAuth=FirebaseAuth.getInstance()

        //FindView
        oEmail=findViewById(R.id.email)
        oPass=findViewById(R.id.password)
        oSignInButton=findViewById(R.id.loginbutton)
        oSignUp=findViewById(R.id.sign_up_text)

        //OnClick
        oSignInButton.setOnClickListener {
            MakeLogin();

        }
        oSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivityRegister::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    //Login
    private fun MakeLogin() {


        var email: String = oEmail.text.toString()
        var password: String = oPass.text.toString()

        //Field Validation
        if(TextUtils.isEmpty(email)) {
            oEmail.setError("Enter your email");
            return;
        }
        else if(TextUtils.isEmpty(password)) {
            oPass.setError("Enter your password");
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) {task ->
            if (task.isSuccessful) {
                Toast.makeText(this@MainActivity, "Successful login", Toast.LENGTH_SHORT).show()

                //Change Activity
                val intent = Intent(this@MainActivity, MainActivityButtons::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this@MainActivity, "Sign in fail!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}