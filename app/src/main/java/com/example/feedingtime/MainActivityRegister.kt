package com.example.feedingtime


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class MainActivityRegister : AppCompatActivity() {

    private lateinit var oEmail: EditText
    private lateinit var oPass: EditText
    private lateinit var oPassConfirm: EditText
    private lateinit var oSignUpButton: Button
    private lateinit var oSignIn: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)

        //Firebase
        firebaseAuth=FirebaseAuth.getInstance()

        //FindView
        oEmail=findViewById(R.id.email)
        oPass=findViewById(R.id.password)
        oPassConfirm=findViewById(R.id.confirmpassword)
        oSignUpButton=findViewById(R.id.sign_up)
        oSignIn=findViewById(R.id.sign_in_text)

        //OnClick
        oSignUpButton.setOnClickListener {
              signUpUser()

        }
        oSignIn.setOnClickListener {
            val intent = Intent(this@MainActivityRegister, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser(){
        var email: String = oEmail.text.toString()
        var password1: String = oPass.text.toString()
        var password2 = oPassConfirm.text.toString()

        //Field Validation
        if(email.isEmpty()) {
            oEmail.error = "Enter your email"
            oEmail.requestFocus()
            return
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            oEmail.error = "Invalid Email";
            oEmail.requestFocus()
            return
        }
        else if(password1.isEmpty()) {
            oPass.error = "Enter your password";
            oPass.requestFocus()
            return
        }
        else if(password2.isEmpty()) {
            oPassConfirm.error = "Confirm your password";
            oPassConfirm.requestFocus()
            return
        }
        else if(password1 != password2) {
            oPassConfirm.error = "Different Password";
            oPassConfirm.requestFocus()
            return
        }
        else if(password1.length < 4) {
            oPass.error = "Length should be greater than 4";
            oPass.requestFocus()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password1)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}