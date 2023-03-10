package com.example.fuckyou

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
){
    val visibleState = remember {
        MutableTransitionState(false).apply {
            //start the animation immediately
            targetState = true
        }
    }

    //Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut()
    ) {
        LazyColumn() {
            itemsIndexed(heroes) {
                    index,hero ->
                HeroListItem(
                    hero = hero,
                    modifier = Modifier

                        .padding(horizontal = 50.dp, vertical = 80.dp)
                        //Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                //????????????index??????!!!!!
                                initialOffsetY = { it * (index + 1) }
                            )
                        )
                )
            }
        }
    }
}




@Composable
fun HeroListItem(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(80.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    style = MaterialTheme.typography.h3,
                    text = stringResource(hero.nameRes) )
                Text(
                    style = MaterialTheme.typography.body1,
                    text = stringResource(hero.descriptionRes)
                )
            }
            //??????spacer
            Spacer(Modifier.width(16.dp))
            //Image????????????Box ????????????
            Box(
                modifier =
                Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
                //modifier????????????Box??? ?????????padding (padding????????????Card???Row??????)
            ) {
                Image(
                    painter = painterResource(hero.imageRes) ,
                    contentDescription =null,
                    alignment = Alignment.TopCenter ,
                    contentScale = ContentScale.FillWidth
                )
            }
        }

    }
}