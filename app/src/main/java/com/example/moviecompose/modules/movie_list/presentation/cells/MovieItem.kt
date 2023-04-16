package com.example.moviecompose.modules.movie_list.presentation.cells

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.view.ImageWithLoadingProgress


@ExperimentalMaterial3Api
@Composable
fun MovieItem(
    modifier: Modifier,
    imageLink:String?,
    onClick: (String?)->Unit
) {
    val painter =  rememberImagePainter(
        data =imageLink,
        builder = {

        }

    )

    Box(
        modifier = modifier
            .heightIn(min = 150.dp, max = 150.dp)
            .clickable(onClick = { onClick.invoke(imageLink) })
    ) {
        ImageWithLoadingProgress(modifier = Modifier.fillMaxSize(), imageLink = imageLink)
        Text("This text is drawn first", modifier = Modifier.align(Alignment.BottomCenter))

    }






}