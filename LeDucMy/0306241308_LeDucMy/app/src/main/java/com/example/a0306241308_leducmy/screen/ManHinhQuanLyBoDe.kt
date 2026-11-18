package com.example.a0306241308_leducmy.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a0306241308_leducmy.navigation.AppRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhQuanLyBoDe(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quản lý bộ đề", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Trở về")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).background(Color(0xFFF8F9FA)).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { navController.navigate(AppRoutes.CreateEditExam.route) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2C5E4B))
            ) {
                Text("+ Tạo bộ đề mới", color = Color.White)
            }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(listOf("Bộ đề 1", "Bộ đề 2", "Bộ đề 3")) { boDe ->
                    Card(
                        onClick = { },
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(boDe, fontWeight = FontWeight.Bold)
                            Text("Sửa", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}