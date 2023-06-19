package com.example.sneakerapp.auth

import android.util.Log
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakerapp.Model.Athlete
import com.example.sneakerapp.R
import com.example.sneakerapp.ui.theme.SneakerAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AthleteScreen(selectedBrand: String?) {

    Log.d("AthleteScreen", "selectedBrand: $selectedBrand")
    val allAthletes = listOf(
        Athlete("MJ", "nike", R.drawable.mj),
        Athlete("RONALDO", "nike", R.drawable.ronaldo),
        Athlete("MESSI", "adidas", R.drawable.ronaldo),

        // Add the other athletes here
    )

    val selectedBrandAthletes = allAthletes.filter { it.brand == selectedBrand }

    val columns = 2

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        gridItems(
            data = selectedBrandAthletes,
            columnCount = 2,
            modifier = Modifier.padding(8.dp)
        ) { athlete ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                        .height(118.dp)
                    .width(131.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    // Navigate to athlete's details screen
                }
            ) {
                Image(
                    painter = painterResource(id = athlete.imageResourceId),
                    contentDescription = athlete.name,
                    contentScale = ContentScale.Crop,
                )
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


fun resourceForAthlete(brand: String): Int {
    return when (brand) {
        "..." -> R.drawable.mj
        "..." -> R.drawable.ronaldo
        "..." -> R.drawable.adidas
        "..." -> R.drawable.rebook_icon
        "..." -> R.drawable.puma_icon
        ".." -> R.drawable.nb_icon
        else -> throw IllegalArgumentException("Unknown brand")
    }
}

@Preview(showBackground = true)
@Composable
fun NPreview() {
    SneakerAppTheme {
        AthleteScreen(selectedBrand = "nike")
    }
}



