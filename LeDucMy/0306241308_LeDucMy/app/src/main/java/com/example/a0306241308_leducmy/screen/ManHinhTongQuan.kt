package com.example.a0306241308_leducmy.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.a0306241308_leducmy.components.StatCard
import com.example.a0306241308_leducmy.navigation.AppRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhTongQuan(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Trang chủ", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("Bảng điều khiển Admin", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8F9FA))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Chào Admin!", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            // Các thẻ thống kê
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                StatCard(Modifier.weight(1f), "XX", "Tài khoản")
                StatCard(Modifier.weight(1f), "XX", "Bộ đề")
                StatCard(Modifier.weight(1f), "XX", "Câu hỏi")
            }

            Text("Danh mục", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            // Các nút quản lý nhanh
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                QuickActionCard(Modifier.weight(1f), "Tài khoản", Icons.Default.Person) {
                    navController.navigate(AppRoutes.UserManagement.route)
                }
                QuickActionCard(Modifier.weight(1f), "Chủ đề", Icons.Default.Folder) {
                    navController.navigate(AppRoutes.TopicManagement.route)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                QuickActionCard(Modifier.weight(1f), "Bộ đề", Icons.Default.ListAlt) {
                    navController.navigate(AppRoutes.ExamSetManagement.route)
                }
                QuickActionCard(Modifier.weight(1f), "Tải CSV", Icons.Default.FileUpload) {

                }
            }

            Text("Hoạt động gần đây", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Cập nhật lần 2", fontWeight = FontWeight.Medium)
                    Text("Thời gian", fontSize = 12.sp, color = Color.Gray)
                    HorizontalDivider()
                    Text("Cập nhật lần 1", fontWeight = FontWeight.Medium)
                    Text("Thời gian", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
private fun QuickActionCard(modifier: Modifier, title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(icon, contentDescription = null, tint = Color(0xFF2C5E4B))
            Text(title, fontWeight = FontWeight.Medium)
        }
    }
}