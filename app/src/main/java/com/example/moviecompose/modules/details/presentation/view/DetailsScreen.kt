package com.example.moviecompose.modules.details.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.transform.RoundedCornersTransformation
import com.example.moviecompose.R
import com.example.moviecompose.base.presentations.view.ImageWithLoadingProgress
import com.example.moviecompose.base.presentations.view.RatingBar
import com.example.moviecompose.base.presentations.view.RoundIcon
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUIEvents
import com.example.moviecompose.modules.details.presentation.uimodel.DetailsUiModel
import com.example.moviecompose.modules.details.presentation.viewmodel.DetailsViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel= hiltViewModel(),
) {
    
    val uiModel=viewModel.uiModel.collectAsStateWithLifecycle()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        HeaderLayout(modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 260.dp, max = 260.dp),
            imageUrl = uiModel.value.movieEntity?.poster_path,
            backgroundImageUrl = uiModel.value.movieEntity?.backdrop_path){
            viewModel.sendEvent(DetailsUIEvents.BackToHomeScreen)
        }

        ContentLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp),
        detailsUiModel = uiModel.value)
        
        
    }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderLayout(modifier:Modifier,imageUrl:String?,backgroundImageUrl:String?,onBackClick:()->Unit){
    Box(modifier = modifier) {



        ImageWithLoadingProgress(modifier = Modifier.fillMaxSize(), imageLink = backgroundImageUrl?:imageUrl,
            transformations = listOf(), isGradientBackgroundEnable = true)



        ImageWithLoadingProgress(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 80.dp, vertical = 30.dp), imageLink = imageUrl,
            transformations = listOf(RoundedCornersTransformation(topLeft = 20f, topRight = 20f, bottomLeft = 20f, bottomRight = 20f)))

        RoundIcon(modifier = Modifier
            .padding(all = 20.dp)
            .size(width = 48.dp, height = 48.dp)
            .align(Alignment.TopStart)
            .clickable(onClick = onBackClick), painter = Icons.Default.ArrowBack)


        
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ContentLayout(modifier: Modifier,detailsUiModel: DetailsUiModel){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp)) {

            Text(modifier=Modifier.fillMaxWidth(),text = detailsUiModel.movieEntity!!.title, style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold), textAlign = TextAlign.Start)

            FlowRow(
                modifier = Modifier.padding(top = 15.dp),
                maxItemsInEachRow = 5
            ) {

                detailsUiModel.movieCategories.forEach{
                    ElevatedFilterChip(
                        modifier=Modifier.padding(end = 2.dp),
                        shape = CircleShape,
                        colors = FilterChipDefaults.filterChipColors(containerColor = Color.Gray),
                        selected = false,
                        onClick = {  },
                        label = {Text(it.name, color = Color.White)})
                }

            }

            Row(modifier=Modifier.padding(top = 15.dp)) {
                Text(modifier = Modifier.align(CenterVertically), text = detailsUiModel.movieEntity.vote_average, style = TextStyle(color = Color.Yellow, fontSize = 14.sp))
                RatingBar(modifier= Modifier
                    .padding(horizontal = 5.dp)
                    .align(CenterVertically), rating = detailsUiModel.movieEntity.vote_average.toDoubleOrNull()?:0.0, size = 18.dp)

                Spacer(Modifier.weight(2f))

                Icon(modifier = Modifier
                    .size(width = 18.dp, height = 14.dp)
                    .align(CenterVertically), painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24) , contentDescription = "eye")
                Text(modifier = Modifier
                    .padding(start = 4.dp)
                    .align(CenterVertically), text = detailsUiModel.movieEntity.popularity, style = TextStyle(color = Color.Gray, fontSize = 14.sp))

            }
            
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = detailsUiModel.movieEntity.overview,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal),
                textAlign = TextAlign.Start
            )




        }

    }
}