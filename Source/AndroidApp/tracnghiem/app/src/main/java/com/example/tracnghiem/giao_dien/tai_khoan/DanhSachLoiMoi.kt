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
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhDanhSachLoiMoi(navController: NavHostController) {
    KhungManHinh("Lời mời thi đấu", navController) { pad ->
        LazyColumn(Modifier.fillMaxSize().padding(pad), contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item { Text("Lời mời trực tiếp theo tên tài khoản, không cần kết bạn") }
            itemsIndexed(DuLieuMau.loiMoi) { chiSo, loiMoi ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Người mời: ${loiMoi.nguoiGui}", style = MaterialTheme.typography.titleMedium)
                        Text("Bộ đề: ${loiMoi.boDe}")
                        Text("Trạng thái: ${loiMoi.trangThai}")
                        if (loiMoi.trangThai == "Chờ trả lời") {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(onClick = {
                                    DuLieuMau.loiMoi[chiSo] = loiMoi.copy(trangThai = "Đồng ý")
                                    DuLieuMau.kieuPhong = "Đối kháng"
                                    DuLieuMau.soNguoiTrongPhong = 2
                                    navController.navigate(DuongDan.PHONG_CHO)
                                }) { Text("Đồng ý") }
                                OutlinedButton(onClick = {
                                    DuLieuMau.loiMoi[chiSo] = loiMoi.copy(trangThai = "Từ chối")
                                }) { Text("Từ chối") }
                            }
                        }
                    }
                }
            }
        }
    }
}
