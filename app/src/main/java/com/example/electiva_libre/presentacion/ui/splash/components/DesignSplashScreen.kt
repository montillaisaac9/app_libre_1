
import android.content.res.Configuration
import androidx.compose.ui.draw.scale
import android.util.Base64
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.electiva_libre.R
import com.example.electiva_libre.presentacion.theme.Electiva_LibreTheme


fun decodeBase64ToImageBitmap(base64Str: String?): ImageBitmap? {
    return try {
        if (base64Str.isNullOrEmpty()) return null
        val decodedBytes = Base64.decode(base64Str, Base64.DEFAULT)
        val bitmap = android.graphics.BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        bitmap.asImageBitmap()
    } catch (e: IllegalArgumentException) {
        null
    }
}

@Composable
fun DesignSplashScreen(
    modifier: Modifier = Modifier, // Add optional base64 string
    scaleAnimation: Animatable<Float, AnimationVector1D> = Animatable(initialValue = 0.6f),
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.ais),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.ais_logo),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .aspectRatio(1f)
                        .padding(50.dp)
                        .scale(scale = scaleAnimation.value),
                )
            }
        }
    }


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun PreviewSplashScreen() {
    Electiva_LibreTheme {
        DesignSplashScreen()
    }
}