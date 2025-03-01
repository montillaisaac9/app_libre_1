package com.example.electiva_libre.presentacion.ui.news
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.electiva_libre.R
import com.example.electiva_libre.presentacion.common.CustomProgressBar
import com.example.electiva_libre.presentacion.common.botomBar.CustomBottomBar
import com.example.electiva_libre.presentacion.common.cards.CardBodyNews
import com.example.electiva_libre.presentacion.common.cards.CardCustom
import com.example.electiva_libre.utils.ApiResult
import kotlinx.coroutines.launch

@Composable
fun ScreenNews(
    navController: NavHostController,
    viewModel: NewsVieModel = hiltViewModel()
) {

    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val response = viewModel.newsResponse

    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    Scaffold (
        bottomBar = {
            CustomBottomBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ais),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = stringResource(R.string.ais_logo),
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .aspectRatio(1f)
                        .padding(10.dp)
                )
                Text("Noticias", color = MaterialTheme.colorScheme.onBackground, fontSize = 30.sp)
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
                        Column (modifier = Modifier.padding(10.dp).fillMaxSize()){
                        Text("La Lista esta vacia...", fontSize = 30.sp, color = MaterialTheme.colorScheme.onBackground)
                        }
                    }else {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(items = list) { index, item ->
                                CardCustom(
                                    modifier = Modifier.padding(10.dp),
                                    color = if (item.isFeatured) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer,
                                    title = {
                                        Text(
                                            item.title,
                                            fontSize = 20.sp,
                                            modifier = Modifier.padding(bottom = 20.dp),
                                            color = if (item.isFeatured) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiary
                                        )
                                    }
                                ) {
                                    CardBodyNews(item.content, item.publishedDate, item.isFeatured)
                                }
                            }
                        }
                    }
                }
                null -> {}
            }
        }
    }
}
