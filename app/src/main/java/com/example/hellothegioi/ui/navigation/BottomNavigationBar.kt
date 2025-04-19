package com.example.hellothegioi.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun BottomNavigationBar(
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Menu,
        BottomNavItem.Notification,
        BottomNavItem.Profile
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDDDDDD))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            IconButton(onClick = { onItemSelected(item.route) }) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.route,
                    tint = if (selectedItem == item.route) Color.Black else Color.Gray,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    BottomNavigationBar(
//        selectedItem = "home",
//        onItemSelected = {}
//    )
//}
