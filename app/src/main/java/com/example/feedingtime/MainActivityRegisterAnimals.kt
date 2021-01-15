package com.example.feedingtime

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main_register_animals.*
import kotlinx.android.synthetic.main.item_lista.*

@Suppress("DEPRECATION")
class MainActivityRegisterAnimals : AppCompatActivity() {

    //Firebase
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference
    private lateinit var myRefImage: FirebaseStorage

    //Animal Info
    private lateinit var theAnimalName: EditText
    private lateinit var theAnimalBreed: EditText
    private lateinit var theAnimalSpecies: EditText
    private lateinit var theAnimalFood: EditText
    private lateinit var theFoodQuantity: EditText
    private lateinit var theFoodTimes: EditText

    //Image
    lateinit var filepath : Uri

    //Cancel and Save button
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button
    private lateinit var buttonAnimalImage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register_animals)

        //Firebase
        database = FirebaseDatabase.getInstance("https://feedingtime-3213a-default-rtdb.europe-west1.firebasedatabase.app/")
        myRef = database.reference
        myRefImage = FirebaseStorage.getInstance();

        //FindView
        //Buttons
        buttonCancel = findViewById(R.id.cancel_button)
        buttonSave = findViewById(R.id.save_button)
        buttonAnimalImage = findViewById(R.id.chooseImage)
        //Infos
        theAnimalName = findViewById(R.id.animal_name)
        theAnimalBreed = findViewById(R.id.animal_breed)
        theAnimalSpecies = findViewById(R.id.animal_species)
        theAnimalFood = findViewById(R.id.animal_food)
        theFoodQuantity = findViewById(R.id.quantityperday)
        theFoodTimes = findViewById(R.id.timesperday)

        //OnClick
        //Upload Image
        buttonAnimalImage.setOnClickListener {
            startFileChooser()
        }
        //Cancel
        buttonCancel.setOnClickListener {
            executeActivityButtons(MainActivityButtons::class.java)
        }
        //Save
        buttonSave.setOnClickListener {
            sendData()
            startActivity(Intent(applicationContext,MainActivityAnimals::class.java ))
        }
    }

    //Upload Image
    private fun startFileChooser(){

        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i, "Choose Picture"), 111)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 111 && resultCode == Activity.RESULT_OK && data != null){
            //load imagem on ImageView
            filepath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            imageViewAnimal.setImageBitmap(bitmap)
        }
    }

    //Firebase SEND
    private fun sendData() {

        val animalName = theAnimalName.text.toString()
        val animalBreed = theAnimalBreed.text.toString()
        val animalSpecies = theAnimalSpecies.text.toString()
        val animalFood = theAnimalFood.text.toString()
        val foodQuantity = theFoodQuantity.text.toString().toInt()
        val foodTimes = theFoodTimes.text.toString().toInt()

        if(animalName.isNotEmpty() && animalBreed.isNotEmpty() && animalSpecies.isNotEmpty() && animalFood.isNotEmpty()) {

            //Loading
            var progressDialog = ProgressDialog(this@MainActivityRegisterAnimals)
            progressDialog.setMessage("Application is loading, please wait")
            progressDialog.show()

            //Image
            var imageRefer = myRefImage.reference.child("images/pic.jpg")   //.child(filepath)
            imageRefer.putFile(filepath)
                .addOnSuccessListener {p0 ->
                    val animalId = myRef.push().key!!
                    var model = DatabaseModel(animalId, animalName, animalBreed , animalSpecies, animalFood, foodQuantity, foodTimes,
                        imageRefer.toString()
                    )
                    myRef.child("Animals").child(animalId).setValue(model)
                    //upload successfully
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Animal successfully registered", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{p0 ->
                    //not uploaded
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, p0.message, Toast.LENGTH_LONG).show()
                }
                .addOnProgressListener {p0 ->
                    //continous progress
                    var progress = (100.0 * p0.bytesTransferred / p0.totalByteCount)
                    progressDialog.setMessage("Uploaded ${progress.toInt()}%")
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