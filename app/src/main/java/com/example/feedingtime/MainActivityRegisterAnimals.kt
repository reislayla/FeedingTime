package com.example.feedingtime

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main_register_animals.*

class MainActivityRegisterAnimals : AppCompatActivity() {

    //Firebase
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    //Animal Info
    private lateinit var theAnimalName: EditText
    private lateinit var theAnimalBreed: EditText
    private lateinit var theAnimalSpecies: EditText
    private lateinit var theAnimalFood: EditText
    private lateinit var theFoodQuantity: EditText
    private lateinit var theFoodTimes: EditText

    //Cancel and Save button
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register_animals)

        //Firebase
        database = FirebaseDatabase.getInstance("https://feedingtime-3213a-default-rtdb.europe-west1.firebasedatabase.app/")
        myRef = database.reference



        //FindView
        buttonCancel = findViewById(R.id.cancel_button)
        buttonSave = findViewById(R.id.save_button)
        theAnimalName = findViewById(R.id.animal_name)
        theAnimalBreed = findViewById(R.id.animal_breed)
        theAnimalSpecies = findViewById(R.id.animal_species)
        theAnimalFood = findViewById(R.id.animal_food)
        theFoodQuantity = findViewById(R.id.quantityperday)
        theFoodTimes = findViewById(R.id.timesperday)

        //OnClick
        //Cancel
        buttonCancel.setOnClickListener {
            executeActivityButtons(MainActivityButtons::class.java)
        }
        //Save
        buttonSave.setOnClickListener {
            sendData()
           /* val myRef = FirebaseDatabase.getInstance().reference
            myRef.setValue("teste")
            Toast.makeText(this@MainActivityRegisterAnimals, "Animal successfully registered", Toast.LENGTH_LONG).show()*/
        }
    }

    //Firebase
    private fun sendData() {

        val animalName = theAnimalName.text.toString()
        val animalBreed = theAnimalBreed.text.toString()
        val animalSpecies = theAnimalSpecies.text.toString()
        val animalFood = theAnimalFood.text.toString()
        val foodQuantity = theFoodQuantity.text.toString().toInt()
        val foodTimes = theFoodTimes.text.toString().toInt()

        if(animalName.isNotEmpty()) {  //&& animalBreed.isNotEmpty() && animalSpecies.isNotEmpty() && animalFood.isNotEmpty()) {

            //Firebase
            //val myRef = FirebaseDatabase.getInstance().getReference("Animals")

            //Unique key inside the reference Animals
            val animalId = myRef.push().key!!
            var model = DatabaseModel(animalId, animalName, animalBreed , animalSpecies, animalFood, foodQuantity, foodTimes)
            myRef.child("Animals").child(animalId).setValue(model)
                .addOnSuccessListener {
                //Confirmation
                    Toast.makeText(this@MainActivityRegisterAnimals, "Animal successfully registered", Toast.LENGTH_LONG).show()
                   }
                .addOnFailureListener { ex : Exception ->
                    Log.e("TAG", ex.toString())
                }
        }
        else {
          Toast.makeText(applicationContext, "All field required", Toast.LENGTH_LONG).show()
        }


    }

    //Execute ActivityButtons Function
    private fun executeActivityButtons(otherActivity: Class<*>) {
        val intent = Intent(this, otherActivity)
        startActivity(intent)
    }
}