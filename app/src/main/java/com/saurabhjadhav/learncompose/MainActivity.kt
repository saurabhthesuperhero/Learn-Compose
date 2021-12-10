package com.saurabhjadhav.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saurabhjadhav.learncompose.ui.theme.LearnComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
                MainView()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", fontSize = 34.sp)
}

@Composable
fun Bio(body: String) {
    Text(text = body, fontSize = 24.sp)
}

@Composable
fun MainView(){
    val navController = rememberNavController()
    Scaffold (
        topBar= { TopBar() },
        bottomBar = {
            BottomBar(
                navController
            )
        }
    )
    {
        BottomBarMain(navController)
    }

}

@Composable
fun BottomBarMain(navController : NavHostController) {
    NavHost(navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomBar(navController: NavController){
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Profile,
    )

    BottomNavigation(
        elevation = 5.dp
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.map {
            BottomNavigationItem(
                icon= {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                },
                label= {
                    Text(
                        text = it.title
                    )
                },
                selected = currentRoute == it.route,
                selectedContentColor= Color.White,
                unselectedContentColor= Color.White.copy(alpha = 0.4f),
                onClick = {
                    navController.navigate(it.route){
                        restoreState = true
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                    }
                }
            )
        }

    }
}


@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.design_default_color_primary),
        contentColor = Color.White
    )
}


@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = Screen.Home.title,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 20.sp
        )
    }
}



@Composable
fun SearchScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = Screen.Search.title,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 20.sp
        )
    }
}

@Composable
fun ProfileScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = Screen.Profile.title,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 20.sp
        )
    }
}