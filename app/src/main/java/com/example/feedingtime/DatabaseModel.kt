package com.example.feedingtime

import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.synthetic.main.activity_main_register_animals.*

@IgnoreExtraProperties
class DatabaseModel(
    var animalId: String? = "",
    var animalname: String? = "",
    var animalbreed: String? = "",
    var animalspecies: String? = "",
    var animalfood: String? = "",
    var foodquantity: Int = 0,
    var foodtimes: Int = 0
)