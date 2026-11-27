package vn.nhom.tracnghiem.giao_dien.tai_khoan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhXepHangThang(navController: NavHostController) {
    KhungManHinh("Bảng xếp hạng tháng", navController, hienThanhDuoi = true) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            Text("Điểm tích lũy thi đấu trong tháng (mẫu)", style = MaterialTheme.typography.titleMedium)
            Text("Đối kháng: +10 / -5 • Nhóm: hạng 1–3 có thưởng. Số dư không âm.")
            Spacer(Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(DuLieuMau.xepHangThang.sortedByDescending { it.diem }) { viTri, nguoi ->
                    Card(Modifier.fillMaxWidth()) {
                        Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("${viTri + 1}. ${nguoi.ten}")
                            Text("${nguoi.diem} điểm", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
