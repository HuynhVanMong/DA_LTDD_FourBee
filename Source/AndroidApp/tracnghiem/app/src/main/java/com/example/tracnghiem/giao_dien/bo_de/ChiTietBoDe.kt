package vn.nhom.tracnghiem.giao_dien.bo_de

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
fun ManHinhChiTietBoDe(navController: NavHostController, ma: Int) {
    val de = DuLieuMau.layBoDe(ma)
    KhungManHinh("Chi tiết bộ đề", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (de == null) {
                Text("Không tìm thấy bộ đề")
            } else {
                Text(de.ten, style = MaterialTheme.typography.headlineSmall)
                Text("Chủ đề: ${DuLieuMau.layChuDe(de.maChuDe)?.ten ?: "Chưa có"}")
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Độ khó: ${de.doKho}")
                        Text("Số câu: ${de.maCauHoi.size}")
                        Text("Thời gian: ${de.thoiGianPhut} phút")
                        Text("Số lượt làm: ${de.luotLam}")
                        Text("Điểm trung bình: ${de.diemTrungBinh}")
                        //Text("Điểm cao nhất / thấp nhất: chưa có API thống kê")
                    }
                }
                Button(onClick = {
                    DuLieuMau.batDauBai(de.ma)
                    navController.navigate(DuongDan.LAM_BAI)
                }, modifier = Modifier.fillMaxWidth()) { Text("Bắt đầu luyện tập") }
                OutlinedButton(onClick = { navController.navigate(DuongDan.danhGia(de.ma)) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Xem đánh giá và bình luận")
                }
            }
        }
    }
}
