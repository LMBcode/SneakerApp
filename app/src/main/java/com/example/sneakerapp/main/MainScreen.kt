package com.example.sneakerapp.main

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sneakerapp.AthletesObject.allAthletes
import com.example.sneakerapp.auth.AthleteScreen
import com.example.sneakerapp.ui.theme.SneakerAppTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(selectedAthlete : String) {
    Log.d("AND THE SELECTED ATHLETE IS", selectedAthlete)


    val selectedAthlete = allAthletes.firstOrNull { it.name == selectedAthlete }

    val gallery = selectedAthlete?.gallery
    var currentImageId by remember { mutableStateOf(gallery?.random() ?: selectedAthlete?.imageResourceId) }
    val pageCount = gallery?.size ?: 1
    val pagerState = rememberPagerState()

    /*
   LaunchedEffect(key1 = pagerState.currentPage, key2 = gallery?.size) {
        if (gallery != null && gallery.isNotEmpty()) {
            while (true) {
                delay(3000L)  // delay for 3 seconds
                val nextPage = (pagerState.currentPage + 1) % gallery.size
                pagerState.animateScrollToPage(nextPage)  // move to the next page, wrapping around
            }
        }
    }
    */



    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(16.dp))

        HorizontalPager(pageCount = pageCount,
            state = pagerState,
            pageSpacing = 0.dp,
            key = { gallery?.get(it) ?: "a" },
            modifier = Modifier.padding(16.dp)

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
                        .align(Alignment.CenterHorizontally)
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

    }
    }



@Preview(showBackground = true)
@Composable
fun Preview() {
    SneakerAppTheme {
        MainScreen(selectedAthlete = "MJ")
    }
}