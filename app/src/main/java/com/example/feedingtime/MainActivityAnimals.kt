package com.example.feedingtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main_animals.*
import kotlinx.android.synthetic.main.activity_main_register_animals.view.*
import kotlinx.android.synthetic.main.item_lista.view.*


class MainActivityAnimals : AppCompatActivity() {

    //Firebase
    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    //RecyclerView
    lateinit var mRecycView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_animals)

        //Firebase
        database = FirebaseDatabase.getInstance("https://feedingtime-3213a-default-rtdb.europe-west1.firebasedatabase.app/")
        myRef = database.getReference("Animals")

        //get Data
        getData()


    }

    //get Data function
    private fun getData()
    {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Log.e("cancel", p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot) {
                var list=ArrayList<DatabaseModel>()
                for(data in p0.children)
                {
                    var model=data.getValue(DatabaseModel::class.java)
                    list.add(model as DatabaseModel)

                }
                if (list.size>0)
                {
                    var adapter = DataAdapter(list)
                    myRecyclerView.adapter = adapter
                }

            }

        })
    }



}
