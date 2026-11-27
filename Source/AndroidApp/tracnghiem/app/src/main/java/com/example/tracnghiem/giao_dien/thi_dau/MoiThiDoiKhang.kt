package vn.nhom.tracnghiem.giao_dien.thi_dau

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.du_lieu_mau.LoiMoi
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhMoiThiDoiKhang(navController: NavHostController) {
    var tenTaiKhoan by remember { mutableStateOf("") }
    var maDe by remember { mutableStateOf(1) }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh("Mời thi đối kháng", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Không cần kết bạn. Nhập tên tài khoản để gửi lời mời.")
            OutlinedTextField(tenTaiKhoan, { tenTaiKhoan = it }, label = { Text("Tên tài khoản người nhận") }, modifier = Modifier.fillMaxWidth())
            Text("Chọn bộ đề:")
            DuLieuMau.boDe.filter { it.congBo }.forEach { de ->
                Row {
                    RadioButton(selected = maDe == de.ma, onClick = { maDe = de.ma })
                    Text(de.ten, modifier = Modifier.padding(top = 12.dp))
                }
            }
            Button(onClick = {
                if (tenTaiKhoan.isBlank()) thongBao = "Nhập tên tài khoản người nhận"
                else {
                    DuLieuMau.loiMoi.add(LoiMoi(DuLieuMau.tenDangNhap, DuLieuMau.layBoDe(maDe)?.ten ?: "Bộ đề"))
                    DuLieuMau.maBoDeDangLam = maDe
                    DuLieuMau.kieuPhong = "Đối kháng"
                    DuLieuMau.soNguoiTrongPhong = 2
                    DuLieuMau.soNguoiToiDa = 2
                    thongBao = "Đã tạo lời mời demo cho $tenTaiKhoan (chưa gửi mạng)"
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Gửi lời mời") }
            Text(thongBao)
            OutlinedButton(onClick = { navController.navigate(DuongDan.PHONG_CHO) }, modifier = Modifier.fillMaxWidth()) {
                Text("Xem phòng chờ minh họa")
            }
        }
    }
}
