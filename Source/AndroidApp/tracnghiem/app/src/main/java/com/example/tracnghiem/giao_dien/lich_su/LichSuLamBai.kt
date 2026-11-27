package vn.nhom.tracnghiem.giao_dien.lich_su

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhLichSuLamBai(navController: NavHostController) {
    var tenChuDe by remember { mutableStateOf("Tất cả") }
    val ds = DuLieuMau.lichSu.filter { luot ->
        tenChuDe == "Tất cả" || DuLieuMau.layChuDe(DuLieuMau.layBoDe(luot.maBoDe)?.maChuDe ?: 0)?.ten == tenChuDe
    }
    KhungManHinh("Lịch sử làm bài", navController, hienThanhDuoi = true) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            Text("Lọc theo chủ đề", style = MaterialTheme.typography.titleMedium)
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Tất cả", "Toán", "Ngữ văn", "Vật lý").forEach { ten ->
                    FilterChip(selected = tenChuDe == ten, onClick = { tenChuDe = ten }, label = { Text(ten) })
                }
            }
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(ds) { luot ->
                    val de = DuLieuMau.layBoDe(luot.maBoDe)
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp)) {
                            Text(de?.ten ?: "Bộ đề đã xóa", style = MaterialTheme.typography.titleMedium)
                            Text("Số câu đúng: ${luot.soCauDung} · Sai: ${luot.soCauSai}")
                            Text("Thời gian: ${luot.thoiGianGiay} giây · Điểm minh họa: ${luot.soCauDung}")
                            TextButton(onClick = { navController.navigate(DuongDan.chiTietLichSu(luot.ma)) }) {
                                Text("Xem chi tiết / Làm lại")
                            }
                        }
                    }
                }
                if (ds.isEmpty()) item { Text("Chưa có lịch sử cho chủ đề này") }
            }
        }
    }
}
