package com.example.sneakerapp.auth

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakerapp.R
import com.example.sneakerapp.ui.theme.SneakerAppTheme
import java.lang.Integer.min
import java.util.logging.Logger


@Composable
fun FavouriteCategoryScreen(navigateToAthlete: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center

    ) {

        val brandList = listOf("nike", "underarmour", "adidas", "rebook", "puma", "newbalance")
        var selectedBrand by remember { mutableStateOf("") }

        Text(
            text = "Welcome \n\n Lamine",
            fontSize = 45.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Which is your favourite shoe brand?", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(30.dp))

        for (i in brandList.indices step 3) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                brandList.slice(i until min(i + 3, brandList.size)).forEach { brand ->
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .height(52.dp)
                            .width(71.dp)
                            .clickable {
                                selectedBrand = brand

                                Log.d("BRAND", "Selected brand is $selectedBrand")

                            }
                        ,
                        color = if (selectedBrand == brand) Color.Cyan else Color.Gray
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = resourceForBrand(brand)),
                            contentDescription = brand,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(48.dp))

        }
        Button(
            onClick = {
                if (selectedBrand != "") {
                    navigateToAthlete(selectedBrand)
                }

                      },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(52.93.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(size = 8.dp)

        ) {
            Text(
                text = "Next",
                color = colorResource(id = R.color.white),
                fontSize = 24.sp
            )
        }
    }
}

fun resourceForBrand(brand: String): Int {
    return when (brand) {
        "nike" -> R.drawable.nike_icon
        "underarmour" -> R.drawable.underarmour
        "adidas" -> R.drawable.adidas
        "rebook" -> R.drawable.rebook_icon
        "puma" -> R.drawable.puma_icon
        "newbalance" -> R.drawable.nb_icon
        else -> throw IllegalArgumentException("Unknown brand")
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    SneakerAppTheme {
        FavouriteCategoryScreen({})
    }
}