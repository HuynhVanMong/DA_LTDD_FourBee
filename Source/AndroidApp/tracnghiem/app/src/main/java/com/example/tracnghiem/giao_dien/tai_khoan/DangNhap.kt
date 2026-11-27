package vn.nhom.tracnghiem.giao_dien.tai_khoan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhDangNhap(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var matKhau by remember { mutableStateOf("") }
    KhungManHinh("Đăng nhập", navController, hienQuayLai = false) { khoangCach ->
        Column(
            Modifier.fillMaxSize().padding(khoangCach)
                .verticalScroll(rememberScrollState()).padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text("ỨNG DỤNG TRẮC NGHIỆM GIÁO DỤC", style = MaterialTheme.typography.titleLarge)
            Text("Ôn tập từ Tiểu học đến THPT")
            OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(matKhau, { matKhau = it }, label = { Text("Mật khẩu") },
                visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            //Text("Bản giao diện minh họa: hai nút dưới không xác thực mật khẩu.", color = MaterialTheme.colorScheme.error)
            Button(
                onClick = {
                    DuLieuMau.emailDangNhap = email.ifBlank { "hocsinh@example.com" }
                    navController.navigate(DuongDan.TRANG_CHU) {
                        popUpTo(DuongDan.DANG_NHAP) { inclusive = true }
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) { Text("Vào giao diện HỌC SINH") }
            OutlinedButton(
                onClick = {
                    navController.navigate(DuongDan.THONG_KE_ADMIN) {
                        popUpTo(DuongDan.DANG_NHAP) { inclusive = true }
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) { Text("Vào giao diện ADMIN") }
            TextButton(onClick = { navController.navigate(DuongDan.DANG_KY) }) {
                Text("Chưa có tài khoản? Đăng ký")
            }
        }
    }
}
