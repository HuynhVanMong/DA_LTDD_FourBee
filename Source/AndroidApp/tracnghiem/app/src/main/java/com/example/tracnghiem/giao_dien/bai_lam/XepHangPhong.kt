package vn.nhom.tracnghiem.giao_dien.bai_lam

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.du_lieu_mau.HangPhong
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhXepHangPhong(navController: NavHostController) {
    val n = DuLieuMau.soNguoiTrongPhong.coerceIn(2, 50)
    val dsMinhHoa = DuLieuMau.xepHangPhong.take(n) +
        (4..n).map { HangPhong("Học sinh $it", (5 - it).coerceAtLeast(0), 220 + 5 * it) }
    val ds = dsMinhHoa.sortedWith(compareByDescending<HangPhong> { it.soCauDung }.thenBy { it.thoiGianGiay })
    KhungManHinh("Xếp hạng phòng thi", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            Text("Xếp theo số câu đúng, rồi thời gian nhanh hơn", style = MaterialTheme.typography.titleMedium)
            Text("Phòng ${DuLieuMau.maPhong} • $n người (dữ liệu minh họa)")
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(ds) { viTri, nguoi ->
                    val hang = viTri + 1
                    val diem = when {
                        n == 2 && hang == 1 -> "+10"
                        n == 2 && hang == 2 -> "-5"
                        n == 3 && hang == 1 -> "+15"
                        n == 3 && hang == 3 -> "-15"
                        n == 3 -> "0"
                        hang == 1 -> "+20"
                        hang == 2 -> "+10"
                        hang == 3 -> "+5"
                        hang == n -> "-15"
                        else -> "0"
                    }
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp)) {
                            Text("Hạng $hang. ${nguoi.ten}", style = MaterialTheme.typography.titleMedium)
                            Text("Đúng ${nguoi.soCauDung} câu • ${nguoi.thoiGianGiay} giây • $diem điểm dự kiến")
                        }
                    }
                }
                item {
                    Text("Thang điểm chỉ để xem UI. Cộng/trừ thật, xử lý hòa và không âm điểm cần làm trên server.")
                    Button(onClick = { navController.navigate(DuongDan.XEP_HANG_THANG) }, modifier = Modifier.fillMaxWidth()) {
                        Text("Xem bảng xếp hạng tháng")
                    }
                    OutlinedButton(onClick = { navController.navigate(DuongDan.TRANG_CHU) }, modifier = Modifier.fillMaxWidth()) { Text("Về trang chủ") }
                }
            }
        }
    }
}
