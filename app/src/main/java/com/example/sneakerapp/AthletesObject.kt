package com.example.sneakerapp

import com.example.sneakerapp.Model.Athlete
import com.example.sneakerapp.Model.Shoe
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


    val shoesList = listOf(
        Shoe(
            name = "Air Force 1 '07",
            brand = "Nike",
            price = 120.0,
            imageResourceId = R.drawable.nike_air_force_1_07,
            variations = listOf(Shoe("Af1",
                "nike",
                199.0,
                R.drawable.nikeaf1),
                Shoe("Af2",
                    "nike",
                    199.0,
                    R.drawable.nikeaf2),
                Shoe("Af3",
                    "nike",
                    199.0,
                    R.drawable.nikeaf3),
                Shoe("Af4",
                    "nike",
                    199.0,
                    R.drawable.nikeaf4)
            )
        ),
        Shoe(
            name = "Air Force 1 Low Premium",
            brand = "Nike",
            price = 150.0,
            imageResourceId = R.drawable.nike_air_force_1_low_premium,
            variations = listOf()
        ),
        Shoe(
            name = "Air Max 1 Premium",
            brand = "Nike",
            price = 130.0,
            imageResourceId = R.drawable.nike_air_max_1_premium,
            variations = listOf()
        ),
        Shoe(
            name = "Dunk High Retro Premium",
            brand = "Nike",
            price = 180.0,
            imageResourceId = R.drawable.nike_dunk_high_retro_premium,
            variations = listOf()
        ),
        Shoe(
            name = "Dunk Low SE",
            brand = "Nike",
            price = 140.0,
            imageResourceId = R.drawable.nike_dunk_low_se,
            variations = listOf()
        )
    )

}