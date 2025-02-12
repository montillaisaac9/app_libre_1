package com.example.electiva_libre.presentacion.common.text

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow


@Composable
fun TextExpander(
    modifier: Modifier = Modifier,
    text: String = "",
    line: Int = 1,
    textAlign: TextAlign? = null,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    fontWeight: FontWeight? = FontWeight.Normal,
) {

    var textMaxLines by remember { mutableIntStateOf(line) }

    Text(
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures {
                textMaxLines = if (textMaxLines == line) {
                    Int.MAX_VALUE
                } else line
            }
        },
        color = color,
        textAlign = textAlign,
        maxLines = textMaxLines,
        fontWeight = fontWeight,
        style = MaterialTheme.typography.bodySmall,
        text = text
    )

}