package com.example.movieapp.navigation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.R
import com.example.movieapp.datasource.DataSource.movieList
import com.example.movieapp.model.Movie


// Theme -> xxxApp
//xxApp -> Scaffold(topBar = {元件化的的Bar()}) {列表、主內容.etc}
//topBar元件分開寫
//--------------------------
//App Bar
@Composable
fun MovieTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar() {
        //不能在TopAppBar的()裡面寫title = {Text()}
        //會整個報錯(就算TopAppBar的屬性有title也會報錯= = )
        Text(text = "Movies")
    }
}
@Composable
fun HomeScreen(
    onCardClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(topBar = { MovieTopAppBar() }) {
        LazyColumn {
            itemsIndexed(movieList) {
                    index, movie ->
                MovieItem(
                    movie = movie,
                    num = index,
                    onCardClicked= { onCardClicked(movie.title) },
                )
            }
        }
    }
}

@Composable
fun MovieItem(
    movie:Movie,
    num: Int,
    onCardClicked: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .clickable { onCardClicked() },
        elevation = 4.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp),
                shape = CircleShape,
                elevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[2])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            //挖靠!!! 我在Column加上weight :1f 版面直接就ok诶!!超級方便阿...
            Column(modifier = Modifier
                .padding(12.dp)
                .weight(1f)) {
                Text(text = movie.title,style = MaterialTheme.typography.h6)
                Text(text = "Director ${movie.director}",style = MaterialTheme.typography.caption)
                Text(text = "year ${movie.year}",style = MaterialTheme.typography.caption)

                AnimatedVisibility(visible = expanded) {
                    Column(modifier = Modifier.padding(top = 20.dp)) {
                        //AnnotatedString! 針對單獨部分文字做調整!
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            ) {
                                append("Plot:  ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,

                                )
                            ) {
                                append(movie.plot)
                            }
                        },)
                    Divider(modifier = Modifier.padding(6.dp))
                    Text(text ="Director ${movie.director}",style = MaterialTheme.typography.h6, fontSize = 14.sp )
                    Text(text = "Actors ${movie.actors}",style = MaterialTheme.typography.caption)
                    Text(text = "Rating ${movie.rating}", style = MaterialTheme.typography.caption)
                    }
                }
            }
            Icon(
                modifier = Modifier
                    .width(50.dp)
                    .clickable { expanded = !expanded },
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "see more"
            )

        }
    }
}