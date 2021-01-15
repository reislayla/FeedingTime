package com.example.feedingtime

import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.synthetic.main.activity_main_register_animals.*

//@IgnoreExtraProperties
class DatabaseModel{

    //Model class
    var animalId: String? = null
    var animalname: String? = null
    var animalbreed: String? = null
    var animalspecies: String? = null
    var animalfood: String? = null
    var foodquantity: Int = 0
    var foodtimes: Int = 0
    var image: String? = null

    constructor() {}

    constructor(
        animalId: String?,
        animalname: String?,
        animalbreed: String?,
        animalspecies: String?,
        animalfood: String?,
        foodquantity: Int,
        foodtimes: Int,
        image: String?
    ) {
        this.animalId = animalId
        this.animalname = animalname
        this.animalbreed = animalbreed
        this.animalspecies = animalspecies
        this.animalfood = animalfood
        this.foodquantity = foodquantity
        this.foodtimes = foodtimes
        this.image = image
    }



}

