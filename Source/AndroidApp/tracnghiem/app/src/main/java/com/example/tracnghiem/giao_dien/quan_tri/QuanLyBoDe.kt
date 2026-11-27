package vn.nhom.tracnghiem.giao_dien.quan_tri

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhQuanLyBoDe(navController: NavHostController) {
    KhungManHinh("Quản lý bộ đề", navController, hienThanhDuoi = true, laQuanTri = true) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            Button(onClick = { navController.navigate(DuongDan.suaBoDe(0)) }, modifier = Modifier.fillMaxWidth()) { Text("+ Thêm bộ đề") }
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(DuLieuMau.boDe) { de ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp)) {
                            Text(de.ten, style = MaterialTheme.typography.titleMedium)
                            Text("${de.maCauHoi.size} câu • ${de.thoiGianPhut} phút • ${de.doKho}")
                            Text("Trạng thái: ${if (de.congBo) "Đã công bố" else "Bản nháp"}")
                            TextButton(onClick = { navController.navigate(DuongDan.suaBoDe(de.ma)) }) { Text("Sửa") }
                        }
                    }
                }
            }
        }
    }
}
