package vn.nhom.tracnghiem.giao_dien.trang_chu

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh
import vn.nhom.tracnghiem.thanh_phan.MauSac

@Composable
fun ManHinhTrangChu(navController: NavHostController) {
    KhungManHinh("Trang chủ", navController, hienThanhDuoi = true, hienQuayLai = false) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Xin chào, ${DuLieuMau.tenDangNhap}!", style = MaterialTheme.typography.headlineSmall)
            Text("Bạn muốn ôn tập môn học nào hôm nay?")
            Card(colors = CardDefaults.cardColors(containerColor = MauSac.XanhNhat), modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("ÔN TẬP KIẾN THỨC", style = MaterialTheme.typography.titleLarge)
                    Text("Trắc nghiệm dành cho học sinh Tiểu học, THCS, THPT")
                    Button(onClick = { navController.navigate(DuongDan.DANH_SACH_BO_DE) }) { Text("Xem tất cả bộ đề") }
                }
            }
            Text("Các môn học", style = MaterialTheme.typography.titleLarge)
            DuLieuMau.chuDe.forEach { chuDe ->
                OutlinedButton(onClick = { navController.navigate(DuongDan.DANH_SACH_BO_DE) }, modifier = Modifier.fillMaxWidth()) {
                    Text("${chuDe.ten} – ${chuDe.capHoc}")
                }
            }
            Text("Bộ đề nổi bật", style = MaterialTheme.typography.titleLarge)
            DuLieuMau.boDe.filter { it.congBo }.take(3).forEach { de ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(de.ten, style = MaterialTheme.typography.titleMedium)
                        Text("${de.maCauHoi.size} câu • ${de.thoiGianPhut} phút • ${de.luotLam} lượt làm")
                        TextButton(onClick = { navController.navigate(DuongDan.chiTietBoDe(de.ma)) }) { Text("Xem bộ đề") }
                    }
                }
            }
            Text("Thi đấu & thành tích", style = MaterialTheme.typography.titleLarge)
            Button(onClick = { navController.navigate(DuongDan.CHON_CHE_DO) }, modifier = Modifier.fillMaxWidth()) { Text("Luyện tập / Thi đấu") }
            OutlinedButton(onClick = { navController.navigate(DuongDan.XEP_HANG_THANG) }, modifier = Modifier.fillMaxWidth()) { Text("Bảng xếp hạng tháng") }
            OutlinedButton(onClick = { navController.navigate(DuongDan.DANH_SACH_LOI_MOI) }, modifier = Modifier.fillMaxWidth()) { Text("Lời mời thi đấu") }
        }
    }
}
