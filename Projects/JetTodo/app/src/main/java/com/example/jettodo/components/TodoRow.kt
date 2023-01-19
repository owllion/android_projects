package com.example.jettodo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jettodo.model.TodoItem
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun TodoRow(
    modifier: Modifier = Modifier,
    todo: TodoItem,
    onTodoClicked: ()-> Unit
){
    Surface(
        modifier = modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0x768BC34A),
    ) {
Column(
    modifier
        .clickable { onTodoClicked() }
        .padding(horizontal = 14.dp, vertical = 20.dp),
    horizontalAlignment = Alignment.Start
) {
    val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
    Text(fontSize = 20.sp,text = todo.title,style = MaterialTheme.typography.subtitle2, fontWeight = FontWeight.Bold)
    Text(text = todo.description,style = MaterialTheme.typography.subtitle1)
//    Text(text = todo.entryDate.format(formatter),style = MaterialTheme.typography.caption)
    //這邊的formatter是上網查的喔~ 會另外補充~有滿多寫法的樣子
}
    }
}