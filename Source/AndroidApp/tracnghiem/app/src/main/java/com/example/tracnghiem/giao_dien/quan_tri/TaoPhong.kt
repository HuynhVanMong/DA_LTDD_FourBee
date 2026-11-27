package vn.nhom.tracnghiem.giao_dien.quan_tri

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhTaoPhong(navController: NavHostController) {
    var soNguoi by remember { mutableStateOf("10") }
    var maDe by remember { mutableStateOf(1) }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh("Tạo phòng thi đấu", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("User tạo phòng, người khác nhập mã để vào. Tối đa 50 người.")
            OutlinedTextField(soNguoi, { soNguoi = it }, label = { Text("Số người tối đa (2–50)") }, modifier = Modifier.fillMaxWidth())
            Text("Chọn bộ đề (thời gian do bộ đề quy định)")
            DuLieuMau.boDe.filter { it.congBo }.forEach { de ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = maDe == de.ma, onClick = { maDe = de.ma })
                    Text("${de.ten} – ${de.thoiGianPhut} phút")
                }
            }
            Button(onClick = {
                val n = soNguoi.toIntOrNull()
                if (n == null || n !in 2..50) {
                    thongBao = "Số người phải từ 2 đến 50"
                } else {
                    DuLieuMau.soNguoiToiDa = n
                    DuLieuMau.soNguoiTrongPhong = 2 // giả lập để thử Navigation
                    DuLieuMau.kieuPhong = if (n == 2) "Đối kháng" else "Nhóm"
                    DuLieuMau.maBoDeDangLam = maDe
                    DuLieuMau.maPhong = "DEMO01" // mã giả; server thật phải sinh và kiểm tra trùng
                    navController.navigate(DuongDan.PHONG_CHO)
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Tạo phòng") }
            Text(thongBao)
            //Text("Lưu ý: Giới hạn phòng thực tế phải kiểm tra trên server, không dùng biến ở Android.")
        }
    }
}
