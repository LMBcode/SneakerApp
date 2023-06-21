package com.example.sneakerapp.main

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakerapp.AthletesObject.allAthletes
import com.example.sneakerapp.AthletesObject.shoesList
import com.example.sneakerapp.Grid.gridItems
import com.example.sneakerapp.Model.Shoe
import com.example.sneakerapp.auth.AthleteScreen
import com.example.sneakerapp.auth.resourceForBrand
import com.example.sneakerapp.ui.theme.SneakerAppTheme
import com.example.sneakerapp.ui.theme.grey
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(selectedAthlete : String,onNavigateToDetail : (String) -> Unit) {
    Log.d("AND THE SELECTED ATHLETE IS", selectedAthlete)


    val selectedAthlete = allAthletes.firstOrNull { it.name == selectedAthlete }

    val gallery = selectedAthlete?.gallery
    var currentImageId by remember {
        mutableStateOf(
            gallery?.random() ?: selectedAthlete?.imageResourceId
        )
    }
    val pageCount = gallery?.size ?: 1
    val pagerState = rememberPagerState()

    val brandList = listOf("nike", "underarmour", "adidas", "rebook", "puma", "newbalance")
    val selectedBrand = remember { mutableStateOf("") }
    val selectedShoe = remember { mutableStateOf("") }


    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Spacer(modifier = Modifier.height(48.dp))

            HorizontalPager(pageCount = pageCount,
                state = pagerState,
                pageSpacing = 0.dp,
                key = { gallery?.get(it) ?: "a" }
            ) {
                val imageId = gallery?.get(it) ?: selectedAthlete?.imageResourceId
                Surface(
                    modifier = Modifier
                        .height(215.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Image(
                        painter = painterResource(id = imageId ?: 0),
                        contentDescription = selectedAthlete?.name,
                        contentScale = ContentScale.Crop
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        repeat(pageCount) { pageIndex ->
                            Canvas(
                                modifier = Modifier
                                    .size(12.dp)
                                    .padding(4.dp)
                            ) {
                                drawCircle(
                                    color = if (pagerState.currentPage == pageIndex) Color.White else Color.Gray,
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Choose Category", fontSize = 24.sp, fontWeight = FontWeight.Bold)

                Text(
                    text = "See All",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow {
                items(brandList) { brand ->
                    CategoryItem(brand = brand, selectedBrand = selectedBrand)

                }
            }

        }

        gridItems(
            data = shoesList,
            columnCount = 2,
            modifier = Modifier.padding(8.dp)
        ) { shoe ->
            ShoeItem(shoe = shoe, selectedShoe = selectedShoe,onNavigateToDetail)
        }


    }
}



@Preview(showBackground = true)
@Composable
fun Preview() {
    SneakerAppTheme {
        MainScreen(selectedAthlete = "MJ",{})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoeItem(shoe: Shoe, selectedShoe: MutableState<String>, onNavigateToDetail: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .height(150.dp)
            .width(150.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        onClick = {
            selectedShoe.value = shoe.name.toString()
            Log.d("zapatos","${selectedShoe.value}")
            onNavigateToDetail(selectedShoe.value)
        },

        ) {
        shoe.imageResourceId?.let { painterResource(id = it) }?.let {
            Image(
                painter = it,
                contentDescription = shoe.name,
                contentScale = ContentScale.FillBounds,
            )
        }
    }
}



@Composable
fun CategoryItem(brand: String, selectedBrand: MutableState<String>) {

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .height(52.dp)
            .width(71.dp)
            .padding(end = 8.dp)
            .clickable {
                selectedBrand.value = brand
                Log.d("BRAND", "Selected brand is $selectedBrand")

            },
        color = if (selectedBrand.value == brand) Color.Cyan else grey
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = resourceForBrand(brand)),
            contentDescription = brand,
            modifier = Modifier.padding(8.dp)
        )
    }
}