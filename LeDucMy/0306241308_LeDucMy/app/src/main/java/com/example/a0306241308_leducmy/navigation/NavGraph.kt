package com.example.a0306241308_leducmy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a0306241308_leducmy.screen.ManHinhQuanLyBoDe
import com.example.a0306241308_leducmy.screen.ManHinhQuanLyCauHoi
import com.example.a0306241308_leducmy.screen.ManHinhQuanLyChuDe
import com.example.a0306241308_leducmy.screen.ManHinhQuanLyTaiKhoan
import com.example.a0306241308_leducmy.screen.ManHinhTaoSuaBoDe
import com.example.a0306241308_leducmy.screen.ManHinhTongQuan

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.Dashboard.route
    ) {
        composable(AppRoutes.Dashboard.route) { ManHinhTongQuan(navController) }
        composable(AppRoutes.UserManagement.route) { ManHinhQuanLyTaiKhoan(navController) }
        composable(AppRoutes.TopicManagement.route) { ManHinhQuanLyChuDe(navController) }
        composable(AppRoutes.ExamSetManagement.route) { ManHinhQuanLyBoDe(navController) }
        composable(AppRoutes.CreateEditExam.route) { ManHinhTaoSuaBoDe(navController) }
        composable(AppRoutes.QuestionManagement.route) { ManHinhQuanLyCauHoi(navController) }

    }
}