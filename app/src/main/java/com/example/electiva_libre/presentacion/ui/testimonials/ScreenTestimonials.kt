package com.example.electiva_libre.presentacion.ui.testimonials
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.electiva_libre.R
import com.example.electiva_libre.presentacion.common.CustomProgressBar
import com.example.electiva_libre.presentacion.common.botomBar.CustomBottomBar
import com.example.electiva_libre.presentacion.common.buttons.CustomButton
import com.example.electiva_libre.presentacion.common.cards.CardBodyCourse
import com.example.electiva_libre.presentacion.common.cards.CardCustom
import com.example.electiva_libre.presentacion.common.inputs.CustomInput
import com.example.electiva_libre.presentacion.common.text.TextExpander
import com.example.electiva_libre.presentacion.ui.courses.CoursesVieModel
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTestimonials(
    navController: NavHostController,
    viewModel: TestimonialViewModel = hiltViewModel()
) {
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val response = viewModel.testimonialResponse


    // Estado del BottomSheet
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) } // Estado para controlar visibilidad

    Scaffold(
        bottomBar = { CustomBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ais),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.ais_logo),
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f)
                        .padding(10.dp)
                )
                Text(
                    text = "Testimonios",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 30.sp,
                    modifier = Modifier.weight(1f) // Hace que ocupe el espacio restante antes del botón
                )
                IconButton(
                    onClick = { showBottomSheet = true },
                    modifier = Modifier.size(40.dp).padding(end = 10.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir")
                }
            }
            when(response){
                is ApiResult.Error ->{
                    LaunchedEffect(Unit) {
                        scope.launch { snackBarState.showSnackbar(message = response.error.orEmpty()) }
                    }
                }
                is ApiResult.Loading -> CustomProgressBar()
                is ApiResult.Success -> {

                    val list = response.data?.data?: emptyList()

                    if (list.size == 0){
                        Column (modifier = Modifier.padding(10.dp)){
                            Text("La Lista esta vacia...", fontSize = 30.sp, color = MaterialTheme.colorScheme.onBackground)
                        }
                    } else {
                        LazyColumn (modifier = Modifier.fillMaxSize()){
                            itemsIndexed(items = list){ index, item ->
                                CardCustom(
                                    modifier = Modifier.padding(10.dp),
                                    color = if (item.isApproved) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
                                    title = {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxWidth()
                                        ){
                                            Text("Username: ${item.author.username}", fontSize = 20.sp, modifier = Modifier.padding(bottom = 20.dp),
                                                color = if (item.isApproved) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiary)
                                            Text("Nombre: ${item.author.firstName} ${item.author.lastName}",
                                                color = if (item.isApproved) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiary)
                                        }

                                    }
                                ){

                                    TextExpander(text = item.content, color = if (item.isApproved) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiary)
                                }
                            }
                        }
                    }
                }
                null -> {}
            }


            // Mostrar BottomSheet cuando showBottomSheet es true
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = bottomSheetState
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Crear un Testimonio", fontSize = 25.sp, color = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
                        Text("Los testimonios seran anonimos", color = MaterialTheme.colorScheme.onBackground)
                        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
                        CustomInput(
                            state = viewModel.newTestimonial,
                            label = "Testimonio",
                            errorText = viewModel.newErrTestimonial,
                            trailingIcon = if (viewModel.newErrTestimonial.isNotEmpty()) Icons.Outlined.Clear else null,
                            trailingIconDescription = if (viewModel.newErrTestimonial.isNotEmpty()) stringResource(R.string.clear_field) else null,
                            onTrailingIconClick = { viewModel.newTestimonial = ""},
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done,
                            maxLines = 5,
                            onImeAction = { focusManager.clearFocus() }
                        ){
                            viewModel.newTestimonial = it
                        }

                        Spacer(modifier = Modifier.fillMaxWidth().height(50.dp))
                        CustomButton(
                            text = "Enviar",
                            onclick = {
                                showBottomSheet = false
                                viewModel.validate()
                            }
                        )
                    }
                }
            }
        }
    }
}

