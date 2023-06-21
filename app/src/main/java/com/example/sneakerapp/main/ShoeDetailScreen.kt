package com.example.sneakerapp.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakerapp.AthletesObject.shoesList
import com.example.sneakerapp.Model.Shoe
import com.example.sneakerapp.ui.theme.SneakerAppTheme
import com.example.sneakerapp.R
import com.example.sneakerapp.auth.resourceForBrand
import com.example.sneakerapp.ui.theme.grey
import com.example.sneakerapp.ui.theme.yellowish

@Composable
fun ShoeDetailScreen(selectedShoeName: String){

    val selectedShoe = shoesList.first { it.name == selectedShoeName }
    Log.d("SHOE","desa")
    LazyColumn(Modifier.
    fillMaxSize()
        ) {
      item {
          Box( modifier = Modifier
              .fillMaxWidth()
              .height(320.dp),) {
              Image(
                  painter = painterResource(id = selectedShoe.imageResourceId!!),
                  contentDescription = "AirForces",
                  modifier = Modifier
                      .fillMaxSize(),
                  contentScale = ContentScale.Crop
              )

              Text(
                  text = "ASDSAKDKJSADAS",
                  modifier = Modifier
                          .align(Alignment.TopCenter) // Adjust this to suit your needs
                      .padding(16.dp),
                  color = Color.Black, // Set text color so it's visible on top of the image
                  style = MaterialTheme.typography.bodyLarge
              )
          }
      }

    item {
        Surface(
            shape = RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp),
            color = yellowish,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .height(550.dp)

        ) {
            Column(
                modifier = Modifier.padding(32.dp),
            ) {

                Text(
                    text = "Color",
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(selectedShoe.variations) { shoeVariation ->
                        ShoeImage(shoeVariation = shoeVariation)
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))


                Text(
                    text = "Size",
                    style = MaterialTheme.typography.titleLarge,
                )
                val shoeSizes = (7..14).toList()

                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(shoeSizes) { shoeSize ->
                        ShoeSizes(shoeSize = shoeSize.toString())
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))


                Button(
                    onClick = {},
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
                        text = "Add To Cart",
                        color = colorResource(id = R.color.white),
                        fontSize = 24.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(52.93.dp)
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(size = 8.dp)

                ) {
                    Text(
                        text = "Favourite",
                        color = colorResource(id = R.color.black),
                        fontSize = 24.sp
                    )
                }

            }
        }
        }
    }

}

@Composable
fun ShoeImage(shoeVariation: Shoe) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(68.dp)
            .width(80.dp)
            .padding(end = 8.dp),
    ) {
        shoeVariation.imageResourceId?.let { painterResource(id = it) }
            ?.let { Image(painter = it, contentDescription = "", contentScale = ContentScale.Crop) }
    }
}

@Composable
fun ShoeSizes(shoeSize : String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(68.dp)
            .width(80.dp)
            .padding(end = 8.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = shoeSize, style = MaterialTheme.typography.bodyLarge)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    SneakerAppTheme {
        ShoeDetailScreen("Air Force 1 '07")
    }
}