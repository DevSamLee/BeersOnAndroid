package com.mbialowas.beersonandroid.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mbialowas.beersonandroid.api.BeersManager
import com.mbialowas.beersonandroid.screens.Beers

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavController,beersManager: BeersManager){
    val screens = listOf("Beers", "Likes", "Profile")
    var selectedScreen by remember { mutableStateOf(screens.first())}

    Scaffold(
        bottomBar = {
            NavigationBar(

                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            ) {
                screens.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = getIconForScreen(screen),
                                contentDescription = screen
                            )
                        },
                        label = { Text(screen) },
                        selected = screen == selectedScreen,
                        onClick = { navController.navigate("beers") },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Text(text = "Selected Screen: $selectedScreen")
                Beers(beersManager = beersManager, navController = navController)
            }
        }
    )
}

@Composable
fun getIconForScreen(screen: String): ImageVector {
    return when (screen) {
        "Home" -> Icons.Default.Home
        "Feed" -> Icons.Default.AccountBox
        "Post" -> Icons.Default.Add
        "Alert" -> Icons.Default.Notifications
        "Jobs" -> Icons.Default.Done
        else -> Icons.Default.Home
    }
}
