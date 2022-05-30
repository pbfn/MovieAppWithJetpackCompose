package com.pedro_bruno.movieappwithjetpackcompose.screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pedro_bruno.movieappwithjetpackcompose.model.Movie
import com.pedro_bruno.movieappwithjetpackcompose.model.getMovies
import com.pedro_bruno.movieappwithjetpackcompose.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {

    val movie = getMovies().first { movie ->
        movie.id == movieId
    }

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })

                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = movie.title)
                }

            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                MovieRow(movie = movie)

                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")

                HorizontalScrollableImageView(movie)

            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(movie: Movie) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp), elevation = 5.dp
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        //.transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "Movie poster",
                )
            }
        }
    }
}