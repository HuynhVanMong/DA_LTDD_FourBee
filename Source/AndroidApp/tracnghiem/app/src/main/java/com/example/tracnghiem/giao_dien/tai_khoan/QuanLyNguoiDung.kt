package vn.nhom.tracnghiem.giao_dien.tai_khoan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhQuanLyNguoiDung(navController: NavHostController) {
    var timKiem by remember { mutableStateOf("") }
    KhungManHinh("Quản lý người dùng", navController, hienThanhDuoi = true, laQuanTri = true) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            OutlinedTextField(timKiem, { timKiem = it }, label = { Text("Tìm tên hoặc email") }, modifier = Modifier.fillMaxWidth())
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(DuLieuMau.nguoiDung.filter { it.ten.contains(timKiem, true) || it.email.contains(timKiem, true) }) { nguoi ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(nguoi.ten, style = MaterialTheme.typography.titleMedium)
                            Text(nguoi.email)
                            Text("Quyền: ${nguoi.vaiTro} • ${if (nguoi.biKhoa) "Đã khóa" else "Hoạt động"}")
                            Button(onClick = {
                                val i = DuLieuMau.nguoiDung.indexOfFirst { it.ma == nguoi.ma }
                                if (i >= 0) DuLieuMau.nguoiDung[i] = nguoi.copy(biKhoa = !nguoi.biKhoa)
                            }) { Text(if (nguoi.biKhoa) "Mở khóa" else "Khóa tài khoản") }
                        }
                    }
                }
            }
        }
    }
}
