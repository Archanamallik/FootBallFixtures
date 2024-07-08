package com.example.footballfixtures

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.footballfixtures.navigations.BottomBarAnimationApp

import com.example.footballfixtures.ui.theme.FootballFixturesTheme
import com.example.footballfixtures.utils.hasInternetConnection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FootballFixturesTheme {
              //  val navController = rememberNavController()

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   // BottomNavigationBar(navController)
                    if(!hasInternetConnection()){
                        NoInternet(modifier = Modifier.fillMaxSize())
                    }else
                    BottomBarAnimationApp()
                }

            }
        }
    }
}

@Composable
fun NoInternet(modifier: Modifier = Modifier) {
    Text(
        text = "No internet connection.",
        modifier = modifier
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FootballFixturesTheme {
        Greeting("Android")
    }
}
