package vn.nhom.tracnghiem.giao_dien.thi_dau

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhVaoPhong(navController: NavHostController) {
    var maPhong by remember { mutableStateOf("") }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh("Vào phòng bằng mã", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Nhập mã phòng được chủ phòng chia sẻ")
            OutlinedTextField(maPhong, { maPhong = it.uppercase() }, label = { Text("Mã phòng") }, modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                if (maPhong == DuLieuMau.maPhong) navController.navigate(DuongDan.PHONG_CHO)
                else thongBao = "Không tìm thấy mã phòng mẫu. Thử: ${DuLieuMau.maPhong}"
            }, modifier = Modifier.fillMaxWidth()) { Text("Vào phòng") }
            Text(thongBao)
            //Text("Mã thử: ${DuLieuMau.maPhong}. Ứng dụng thật phải hỏi server và kiểm tra phòng đầy.")
        }
    }
}
