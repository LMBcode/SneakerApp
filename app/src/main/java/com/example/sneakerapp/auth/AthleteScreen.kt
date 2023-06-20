package com.example.sneakerapp.auth

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakerapp.AthletesObject.allAthletes
import com.example.sneakerapp.Model.Athlete
import com.example.sneakerapp.R
import com.example.sneakerapp.ui.theme.SneakerAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AthleteScreen(selectedBrand: String?, onNavigateToMain: (String) -> Unit) {

    Log.d("AthleteScreen", "selectedBrand: $selectedBrand")
    var selectedAthlete by remember { mutableStateOf("") }


    val selectedBrandAthletes = allAthletes.filter { it.brand == selectedBrand }

    val columns = 2

    Spacer(modifier = Modifier.height(51.dp))

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item() {
            Spacer(modifier = Modifier.height(100.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Who's your favorite athlete?",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 48.dp)
                )
            }
        }

    Log.d("SELECTED ATHLETE", "selectedAthlete: $selectedAthlete")


        gridItems(
            data = selectedBrandAthletes,
            columnCount = 2,
            modifier = Modifier.padding(8.dp)
        ) { athlete ->
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .height(118.dp)
                    .width(131.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                border = if (selectedAthlete == athlete.name) BorderStroke(2.dp, Color.Blue) else null, // set card border based on selection
                onClick = {
                    selectedAthlete = athlete.name
                },

            ) {
                Image(
                    painter = painterResource(id = athlete.imageResourceId),
                    contentDescription = athlete.name,
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { onNavigateToMain(selectedAthlete) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(52.93.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(size = 8.dp)

                ) {
                    Text(
                        text = "NEXT",
                        color = colorResource(id = R.color.white),
                        fontSize = 24.sp
                    )
                }
            }
        }
    }

}



fun <T> LazyListScope.gridItems(
    data: List<T>,
    columnCount: Int,
    modifier: Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    itemContent: @Composable BoxScope.(T) -> Unit,
) {
    val size = data.count()
    val rows = if (size == 0) 0 else 1 + (size - 1) / columnCount
    items(rows, key = { it.hashCode() }) { rowIndex ->
        Row(
            horizontalArrangement = horizontalArrangement,
            modifier = modifier
        ) {
            for (columnIndex in 0 until columnCount) {
                val itemIndex = rowIndex * columnCount + columnIndex
                if (itemIndex < size) {
                    Box(
                        modifier = Modifier.weight(1F, fill = true),
                        propagateMinConstraints = true
                    ) {
                        itemContent(data[itemIndex])
                    }
                } else {
                    Spacer(Modifier.weight(1F, fill = true))
                }
            }
        }
    }
}





