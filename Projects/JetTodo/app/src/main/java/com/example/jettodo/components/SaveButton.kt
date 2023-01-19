package com.example.jettodo.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    onClicked: ()->Unit,
    title:String,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClicked,
        enabled = enabled,
        elevation = ButtonDefaults.elevation(0.dp) ,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xB0CDB90A)),
        modifier = modifier.fillMaxWidth().clip(shape = RoundedCornerShape(10.dp))
     ) {
        Icon(imageVector = Icons.Filled.Done,
            contentDescription ="Done" )
        Text(text = title)
    }
}