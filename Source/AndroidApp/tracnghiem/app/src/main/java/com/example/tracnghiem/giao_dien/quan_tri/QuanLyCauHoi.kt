package vn.nhom.tracnghiem.giao_dien.quan_tri

import androidx.compose.foundation.layout.*
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
fun ManHinhQuanLyCauHoi(navController: NavHostController) {
    var tim by remember { mutableStateOf("") }
    KhungManHinh("Quản lý câu hỏi", navController, hienThanhDuoi = true, laQuanTri = true) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            OutlinedTextField(tim, { tim = it }, label = { Text("Tìm câu hỏi") }, modifier = Modifier.fillMaxWidth())
            Button(onClick = { navController.navigate(DuongDan.suaCauHoi(0)) }, modifier = Modifier.fillMaxWidth()) { Text("+ Thêm câu hỏi") }
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(DuLieuMau.cauHoi.filter { it.noiDung.contains(tim, true) }) { cauHoi ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp)) {
                            Text("Câu ${cauHoi.ma}: ${cauHoi.noiDung}", style = MaterialTheme.typography.titleMedium)
                            Text("Chủ đề: ${DuLieuMau.layChuDe(cauHoi.maChuDe)?.ten ?: "?"} · ${cauHoi.doKho}")
                            TextButton(onClick = { navController.navigate(DuongDan.suaCauHoi(cauHoi.ma)) }) { Text("Xem / sửa") }
                        }
                    }
                }
            }
        }
    }
}
