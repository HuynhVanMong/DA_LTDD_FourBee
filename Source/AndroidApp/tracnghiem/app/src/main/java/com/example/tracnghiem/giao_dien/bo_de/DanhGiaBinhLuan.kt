package vn.nhom.tracnghiem.giao_dien.bo_de

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DanhGia
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhDanhGiaBinhLuan(navController: NavHostController, ma: Int) {
    var binhLuan by remember { mutableStateOf("") }
    var soSao by remember { mutableStateOf(5) }
    var thongBao by remember { mutableStateOf("") }
    val daLam = DuLieuMau.lichSu.any { it.maBoDe == ma }
    KhungManHinh("Đánh giá và bình luận", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Các đánh giá về bộ đề", style = MaterialTheme.typography.titleLarge)
            DuLieuMau.danhGia.filter { it.maBoDe == ma }.forEach { dg ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(12.dp)) {
                        Text("${dg.tenNguoi} · ${dg.soSao} / 5 sao", style = MaterialTheme.typography.titleMedium)
                        Text(dg.noiDung)
                    }
                }
            }
            HorizontalDivider()
            Text("Viết đánh giá của bạn", style = MaterialTheme.typography.titleMedium)
            Text("Chọn số sao:")
            Row(verticalAlignment = Alignment.CenterVertically) {
                (1..5).forEach { so ->
                    RadioButton(selected = soSao == so, onClick = { soSao = so })
                    Text("$so")
                }
            }
            OutlinedTextField(binhLuan, { binhLuan = it }, label = { Text("Nội dung bình luận") },
                minLines = 3, modifier = Modifier.fillMaxWidth())
            Button(onClick = {
                if (!daLam) thongBao = "Cần hoàn thành bộ đề trước khi đánh giá"
                else if (binhLuan.isBlank()) thongBao = "Hãy nhập nội dung bình luận"
                else {
                    DuLieuMau.danhGia.add(DanhGia(ma, DuLieuMau.tenDangNhap, soSao, binhLuan))
                    binhLuan = ""
                    thongBao = "Đã lưu bình luận mẫu"
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Gửi đánh giá") }
            Text(thongBao)
        }
    }
}
