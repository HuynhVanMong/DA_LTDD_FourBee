package vn.nhom.tracnghiem.giao_dien.thi_dau

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
fun ManHinhPhongCho(navController: NavHostController) {
    val cheDo = if (DuLieuMau.soNguoiTrongPhong == 2) "Đối kháng" else DuLieuMau.kieuPhong
    KhungManHinh("Phòng chờ thi đấu", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("MÃ PHÒNG: ${DuLieuMau.maPhong}", style = MaterialTheme.typography.headlineSmall)
            Text("Chế độ hiện tại: $cheDo")
            Text("Thành viên: ${DuLieuMau.soNguoiTrongPhong}/${DuLieuMau.soNguoiToiDa}")
            Text("Bộ đề: ${DuLieuMau.layBoDe(DuLieuMau.maBoDeDangLam)?.ten ?: "Bộ đề"}")
            Text("Thời lượng: ${DuLieuMau.layBoDe(DuLieuMau.maBoDeDangLam)?.thoiGianPhut ?: 5} phút")
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text("1. ${DuLieuMau.tenDangNhap} (Chủ phòng)")
                    if (DuLieuMau.soNguoiTrongPhong >= 2) Text("2. Khách mời mẫu")
                    if (DuLieuMau.soNguoiTrongPhong >= 3) Text("3. Học sinh mẫu 3")
                    //Text("Danh sách chỉ minh họa; phòng thật cần lấy từ server.")
                }
            }
            Row {
                Switch(checked = DuLieuMau.sanSang, onCheckedChange = { DuLieuMau.sanSang = it })
                Text(if (DuLieuMau.sanSang) "Đã sẵn sàng" else "Chưa sẵn sàng", modifier = Modifier.padding(12.dp))
            }
            if (DuLieuMau.soNguoiTrongPhong < DuLieuMau.soNguoiToiDa) {
                OutlinedButton(onClick = { DuLieuMau.soNguoiTrongPhong++ }, modifier = Modifier.fillMaxWidth()) {
                    Text("+ Thêm một người giả lập")
                }
            }
            Button(onClick = {
                DuLieuMau.batDauBai(DuLieuMau.maBoDeDangLam, thiDau = true)
                navController.navigate(DuongDan.THI_DAU)
            }, enabled = DuLieuMau.sanSang && DuLieuMau.soNguoiTrongPhong >= 2,
                modifier = Modifier.fillMaxWidth()) { Text("Bắt đầu thi đấu") }
            //Text("Tối đa 50 người. Ở bản thật, chủ phòng và server xác nhận tất cả đã sẵn sàng.")
        }
    }
}
