package vn.nhom.tracnghiem.giao_dien.tai_khoan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhThongTinCaNhan(navController: NavHostController) {
    var ten by remember { mutableStateOf(DuLieuMau.tenDangNhap) }
    var email by remember { mutableStateOf(DuLieuMau.emailDangNhap) }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh("Thông tin cá nhân", navController, hienThanhDuoi = true) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Thông tin học sinh", style = MaterialTheme.typography.titleMedium)
            OutlinedTextField(ten, { ten = it }, label = { Text("Họ tên") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(email, { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                if (ten.isNotBlank() && email.contains("@")) {
                    DuLieuMau.tenDangNhap = ten
                    DuLieuMau.emailDangNhap = email
                    thongBao = "Đã lưu dữ liệu mẫu"
                } else thongBao = "Vui lòng kiểm tra họ tên và email"
            }, modifier = Modifier.fillMaxWidth()) { Text("Lưu thay đổi") }
            Text(thongBao)
            OutlinedButton(onClick = { navController.navigate(DuongDan.DOI_MAT_KHAU) }, modifier = Modifier.fillMaxWidth()) { Text("Đổi mật khẩu") }
            OutlinedButton(onClick = { navController.navigate(DuongDan.DANH_SACH_LOI_MOI) }, modifier = Modifier.fillMaxWidth()) { Text("Lời mời thi đấu") }
            OutlinedButton(onClick = { navController.navigate(DuongDan.XEP_HANG_THANG) }, modifier = Modifier.fillMaxWidth()) { Text("Bảng xếp hạng tháng") }
            TextButton(onClick = {
                navController.navigate(DuongDan.DANG_NHAP) {
                    popUpTo(DuongDan.TRANG_CHU) { inclusive = true }
                }
            }) { Text("Đăng xuất") }
        }
    }
}
