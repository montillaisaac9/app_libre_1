package com.example.electiva_libre.presentacion.common.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.electiva_libre.presentacion.common.text.TextExpander
import com.example.electiva_libre.presentacion.theme.GrayLight


@Composable
fun CardCustom(
    modifier: Modifier = Modifier,
    horizontalAlignmentTitle: Alignment.Horizontal = Alignment.Start,
    horizontalAlignmentContent: Alignment.Horizontal = Alignment.Start,
    paddingHorizontal: Dp = 10.dp,
    width: Double  =  1.0,
    color: Color = MaterialTheme.colorScheme.background,
    title: @Composable ColumnScope.() -> Unit = {},
    content: @Composable ColumnScope.() -> Unit = {}
) {
    Column {
        Card (
            modifier = modifier
                .heightIn(min = 100.dp)
                .width((LocalConfiguration.current.screenWidthDp.dp * width.toFloat()).coerceAtMost(LocalConfiguration.current.screenWidthDp.dp))
                .padding(horizontal = paddingHorizontal),
            colors = CardDefaults.cardColors(
                containerColor = color,
            ),
            border = BorderStroke(color = GrayLight, width = 1.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()

            ) {
                Column(
                    modifier = Modifier
                        .align(horizontalAlignmentTitle)
                        .padding(horizontal = 16.dp),
                    content = title
                )
                Column(
                    modifier = Modifier
                        .align(horizontalAlignmentContent)
                        .padding(horizontal = 16.dp),
                    content = content
                )
            }
        }
    }
}


@Composable
fun CardBodyNews(
    content: String,
    date: String,
    featured: Boolean
){
    if (featured){
        Text("Destacada",color = MaterialTheme.colorScheme.onPrimary)

        TextExpander(
            text = content,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Text("Fecha: $date", color = MaterialTheme.colorScheme.onPrimary)
    } else {
        TextExpander(
            text = content,
            color = MaterialTheme.colorScheme.onTertiary
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Text("Fecha: $date", color = MaterialTheme.colorScheme.onTertiary)
    }
}

@Composable
fun CardBodyCourse(
    content: String,
    dateStart: String,
    dateEnd: String,
    duracion: String,
    isWorkshop: Boolean
){
    if (isWorkshop){
        Text("Activo",color = MaterialTheme.colorScheme.onPrimary)

        TextExpander(
            text = content,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fecha de Inicio: $dateStart", fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimary)
            Text("Fecha de Fin: $dateEnd", fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimary) }
        Text("Duracion total: $duracion", color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.padding(end = 5.dp))
    } else {
        TextExpander(
            text = content,
            color = MaterialTheme.colorScheme.onTertiary
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Fecha de Inicio: $dateStart", fontSize = 12.sp, color = MaterialTheme.colorScheme.onTertiary)
            Text("Fecha de Fin: $dateEnd", fontSize = 12.sp, color = MaterialTheme.colorScheme.onTertiary) }
        Text("Duracion total: $duracion", color = MaterialTheme.colorScheme.onTertiary, modifier = Modifier.padding(end = 5.dp))
    }
}

@Composable
fun CardBodyTestimonial(
    content: String,
    date: String,
    featured: Boolean
){
    if (featured){
        Text("Destacada",color = MaterialTheme.colorScheme.onPrimary)

        TextExpander(
            text = content,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Text("Fecha: $date", color = MaterialTheme.colorScheme.onPrimary)
    } else {
        TextExpander(
            text = content,
            color = MaterialTheme.colorScheme.onTertiary
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Text("Fecha: $date", color = MaterialTheme.colorScheme.onTertiary)
    }
}