package vn.nhom.tracnghiem.giao_dien.tai_khoan

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhDoiMatKhau(navController: NavHostController) {
    var cu by remember { mutableStateOf("") }
    var moi by remember { mutableStateOf("") }
    var nhapLai by remember { mutableStateOf("") }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh("Đổi mật khẩu", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(cu, { cu = it }, label = { Text("Mật khẩu cũ") },
                visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            OutlinedTextField(moi, { moi = it }, label = { Text("Mật khẩu mới") },
                visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            OutlinedTextField(nhapLai, { nhapLai = it }, label = { Text("Nhập lại mật khẩu mới") },
                visualTransformation = PasswordVisualTransformation(), modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                thongBao = when {
                    cu.isBlank() || moi.isBlank() -> "Không được để trống"
                    moi != nhapLai -> "Mật khẩu mới chưa khớp"
                    else -> "Biểu mẫu hợp lệ, cần gọi API để đổi mật khẩu thật"
                }
            }) { Text("Kiểm tra đổi mật khẩu") }
            Text(thongBao)
        }
    }
}
