package vn.nhom.tracnghiem.giao_dien.quan_tri

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
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
fun ManHinhDanhSachBoDe(navController: NavHostController) {
    var tim by remember { mutableStateOf("") }
    var cap by remember { mutableStateOf("Tất cả") }
    var kho by remember { mutableStateOf("Tất cả") }
    val ds = DuLieuMau.boDe.filter { it.congBo }
        .filter { it.ten.contains(tim, true) }
        .filter { cap == "Tất cả" || DuLieuMau.layChuDe(it.maChuDe)?.capHoc == cap }
        .filter { kho == "Tất cả" || it.doKho == kho }
    KhungManHinh("Danh sách bộ đề", navController, hienThanhDuoi = true) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(12.dp)) {
            OutlinedTextField(tim, { tim = it }, label = { Text("Tìm bộ đề") }, modifier = Modifier.fillMaxWidth())
            Text("Lọc theo cấp học:")
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                listOf("Tất cả", "Tiểu học", "THCS", "THPT").forEach { muc ->
                    FilterChip(selected = cap == muc, onClick = { cap = muc }, label = { Text(muc) })
                }
            }
            Text("Lọc theo độ khó:")
            Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Tất cả", "Dễ", "Vừa", "Khó").forEach { muc ->
                    FilterChip(selected = kho == muc, onClick = { kho = muc }, label = { Text(muc) })
                }
            }
            Spacer(Modifier.height(8.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(9.dp)) {
                items(ds) { de ->
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(de.ten, style = MaterialTheme.typography.titleMedium)
                            Text("${de.maCauHoi.size} câu • ${de.thoiGianPhut} phút • ${de.doKho}")
                            Text("${de.luotLam} lượt • Điểm trung bình ${de.diemTrungBinh}")
                            Button(onClick = { navController.navigate(DuongDan.chiTietBoDe(de.ma)) }) { Text("Xem chi tiết") }
                        }
                    }
                }
            }
        }
    }
}
