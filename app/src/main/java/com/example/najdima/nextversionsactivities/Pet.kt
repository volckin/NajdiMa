package com.example.najdima.nextversionsactivities

data class Pet(
    var animalSpecies: String,
    var breed: String,
    var size: String,
    var sex: String,
    var city: String,
    var moreDetails: String
) {
    constructor() : this("","","","","","")
}