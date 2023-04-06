package com.example.moviecompose.modules.home.presentation.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.moviecompose.R


@ExperimentalMaterial3Api
@Composable
fun MovieItem(
    modifier: Modifier,
    imageLink:String?,
    onClick: ()->Unit
) {

    Box(modifier = modifier.heightIn(min = 150.dp, max = 150.dp) ) {
        Image(
            modifier=Modifier.fillMaxSize(),
            painter = rememberImagePainter(
                data =imageLink,
                builder = {
                    placeholder(R.drawable.app_logo)
                }

            ) ,
            contentScale = ContentScale.Crop,
            contentDescription =null )

        Text("This text is drawn first", modifier = Modifier.align(Alignment.BottomCenter))

    }






}