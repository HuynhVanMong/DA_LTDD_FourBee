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
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhDangKy(navController: NavHostController) {
    var hoTen by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var matKhau by remember { mutableStateOf("") }
    var nhapLai by remember { mutableStateOf("") }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh("Đăng ký tài khoản", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(hoTen, { hoTen = it }, label = { Text("Họ và tên") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(matKhau, { matKhau = it }, label = { Text("Mật khẩu") },
                visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            OutlinedTextField(nhapLai, { nhapLai = it }, label = { Text("Nhập lại mật khẩu") },
                visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                thongBao = when {
                    hoTen.isBlank() || !email.contains("@") || matKhau.isBlank() -> "Vui lòng nhập đầy đủ thông tin hợp lệ"
                    matKhau != nhapLai -> "Mật khẩu nhập lại không khớp"
                    else -> "Biểu mẫu hợp lệ (chưa gửi API đăng ký)"
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Kiểm tra đăng ký") }
            Text(thongBao)
            TextButton(onClick = { navController.navigate(DuongDan.DANG_NHAP) }) { Text("Về đăng nhập") }
        }
    }
}
