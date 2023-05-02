package com.example.moviecompose.base.presentations.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import coil.transform.Transformation
import com.example.moviecompose.BuildConfig
import com.example.moviecompose.R
import java.lang.Math.ceil
import java.lang.Math.floor


@Composable
fun DotsIndicator(
    modifier: Modifier=Modifier,
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
){

    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}



@ExperimentalMaterial3Api
@Composable
fun ImageWithLoadingProgress(
    modifier: Modifier=Modifier,
    imageLink:String?,
    transformations: List<Transformation> = listOf(),
    content:(@Composable BoxScope.()->Unit)?=null
) {

    val painter =  rememberImagePainter(
        data =BuildConfig.ImageLink+imageLink,
        builder = {
            error(R.drawable.app_logo)
                  transformations(
                      transformations
                  )
        },

    )

    Box(modifier = modifier ) {
        Image(
            modifier=Modifier.fillMaxSize(),
            painter =painter,
            contentScale = ContentScale.Crop,
            contentDescription =null )



        if (painter.state is AsyncImagePainter.State.Loading)
            CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
        else
            content?.invoke(this)


    }
}
@Composable
fun RoundIcon(modifier: Modifier=Modifier,painter: ImageVector){
    Card(
        modifier = modifier,
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),

        ) {
        Box(Modifier.fillMaxSize()){
            Icon(
                painter,
                contentDescription = "back",
                modifier=Modifier.align(Alignment.Center),
                tint = Color.Black
            )
        }

    }
}



@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    size: Dp,
    starsColor: Color = Color.Yellow,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(modifier = Modifier.size(width = size, height = size),imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        if (halfStar) {
            Icon(
                modifier = Modifier.size(width = size, height = size),
                painter = painterResource(id = R.drawable.ic_baseline_star_half_24),
                contentDescription = null,
                tint = starsColor
            )
        }
        repeat(unfilledStars) {
            Icon(
                modifier = Modifier.size(width = size, height = size),
                painter = painterResource(id = R.drawable.ic_baseline_star_outline_24),
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}


@Composable
fun LoadingUiState(
    modifier: Modifier,
    isLoading:Boolean,
    content:@Composable ()-> Unit
){

        Box(modifier) {
            if (isLoading)
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            else
                content()
        }


}