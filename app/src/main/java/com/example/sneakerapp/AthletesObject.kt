package com.example.sneakerapp

import com.example.sneakerapp.Model.Athlete
import com.example.sneakerapp.R

object AthletesObject {
    val allAthletes = listOf(
        Athlete("MJ", "nike", R.drawable.mj, listOf(
            R.drawable.mj1,
            R.drawable.mj2,
            R.drawable.mj3
        )),
        Athlete("RONALDO", "nike", R.drawable.ronaldo),
        Athlete("MESSI", "adidas", R.drawable.ronaldo),
        Athlete("LEBRON", "nike", R.drawable.lebron),
        Athlete("CURRY", "underarmour", R.drawable.curry),
        Athlete("KOBE", "nike", R.drawable.kobe)
    )
}