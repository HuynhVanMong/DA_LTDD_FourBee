package vn.nhom.tracnghiem.giao_dien.lich_su

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhChiTietLichSu(navController: NavHostController, ma: Int) {
    val luot = DuLieuMau.lichSu.firstOrNull { it.ma == ma }
    KhungManHinh("Chi tiết lịch sử", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            if (luot == null) Text("Không tìm thấy lượt làm")
            else {
                val de = DuLieuMau.layBoDe(luot.maBoDe)
                Text(de?.ten ?: "Bộ đề", style = MaterialTheme.typography.titleLarge)
                Text("Mã lượt làm: ${luot.ma}")
                Text("Đúng: ${luot.soCauDung} · Sai: ${luot.soCauSai} · Bỏ trống: ${luot.soCauBoTrong}")
                Text("Thời gian thực hiện: ${luot.thoiGianGiay} giây")
                Button(onClick = {
                    DuLieuMau.maLuotDangXem = luot.ma
                    navController.navigate(DuongDan.CHI_TIET_KET_QUA)
                }, modifier = Modifier.fillMaxWidth()) { Text("Xem từng câu đúng / sai") }
                OutlinedButton(onClick = {
                    DuLieuMau.batDauBai(luot.maBoDe)
                    navController.navigate(DuongDan.LAM_BAI)
                }, modifier = Modifier.fillMaxWidth()) { Text("Làm lại bộ đề") }
            }
        }
    }
}
