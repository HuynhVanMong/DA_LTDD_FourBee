package vn.nhom.tracnghiem.giao_dien.bai_lam

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

@Composable
fun ManHinhKetQua(navController: NavHostController) {
    val luot = DuLieuMau.layLuotDangXem()
    KhungManHinh("Kết quả làm bài", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Kết quả luyện tập", style = MaterialTheme.typography.titleLarge)
            if (luot == null) Text("Chưa có kết quả. Hãy làm bài và nộp trước.")
            else {
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Số câu đúng: ${luot.soCauDung}", style = MaterialTheme.typography.headlineMedium)
                        Text("Số câu sai: ${luot.soCauSai}")
                        Text("Số câu chưa làm: ${luot.soCauBoTrong}")
                        Text("Thời gian minh họa: ${luot.thoiGianGiay} giây")
                        Text("Điểm minh họa: ${luot.soCauDung}")
                    }
                }
                Button(onClick = { navController.navigate(DuongDan.CHI_TIET_KET_QUA) },
                    modifier = Modifier.fillMaxWidth()) { Text("Xem từng câu đúng / sai") }
                OutlinedButton(onClick = { navController.navigate(DuongDan.danhGia(luot.maBoDe)) },
                    modifier = Modifier.fillMaxWidth()) { Text("Đánh giá bộ đề") }
                OutlinedButton(onClick = { navController.navigate(DuongDan.LICH_SU_LAM_BAI) },
                    modifier = Modifier.fillMaxWidth()) { Text("Xem lịch sử") }
                TextButton(onClick = { navController.navigate(DuongDan.TRANG_CHU) }) { Text("Về trang chủ") }
            }
        }
    }
}
