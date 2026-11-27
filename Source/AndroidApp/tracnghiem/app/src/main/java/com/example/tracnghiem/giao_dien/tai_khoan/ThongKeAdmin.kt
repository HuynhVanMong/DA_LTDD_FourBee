package vn.nhom.tracnghiem.giao_dien.tai_khoan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhThongKeAdmin(navController: NavHostController) {
    KhungManHinh("Thống kê quản trị", navController, hienThanhDuoi = true,
        laQuanTri = true, hienQuayLai = false) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("TỔNG QUAN HỆ THỐNG (dữ liệu mẫu)", style = MaterialTheme.typography.titleMedium)
            Card(Modifier.fillMaxWidth()) { Text("Số tài khoản: ${DuLieuMau.nguoiDung.size}", Modifier.padding(16.dp)) }
            Card(Modifier.fillMaxWidth()) { Text("Số chủ đề: ${DuLieuMau.chuDe.size}", Modifier.padding(16.dp)) }
            Card(Modifier.fillMaxWidth()) { Text("Số câu hỏi: ${DuLieuMau.cauHoi.size}", Modifier.padding(16.dp)) }
            Card(Modifier.fillMaxWidth()) { Text("Số bộ đề: ${DuLieuMau.boDe.size}", Modifier.padding(16.dp)) }
            Card(Modifier.fillMaxWidth()) { Text("Lượt làm đã lưu: ${DuLieuMau.lichSu.size}", Modifier.padding(16.dp)) }
            Button(onClick = { navController.navigate(DuongDan.QUAN_LY_NGUOI_DUNG) }, modifier = Modifier.fillMaxWidth()) { Text("Quản lý tài khoản") }
            OutlinedButton(onClick = { navController.navigate(DuongDan.QUAN_LY_CHU_DE) }, modifier = Modifier.fillMaxWidth()) { Text("Quản lý chủ đề") }
            OutlinedButton(onClick = { navController.navigate(DuongDan.QUAN_LY_CAU_HOI) }, modifier = Modifier.fillMaxWidth()) { Text("Quản lý câu hỏi") }
            OutlinedButton(onClick = { navController.navigate(DuongDan.QUAN_LY_BO_DE) }, modifier = Modifier.fillMaxWidth()) { Text("Quản lý bộ đề") }
            TextButton(onClick = { navController.navigate(DuongDan.DANG_NHAP) { popUpTo(DuongDan.THONG_KE_ADMIN) { inclusive = true } } }) {
                Text("Đăng xuất khỏi giao diện Admin")
            }
        }
    }
}
