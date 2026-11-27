package vn.nhom.tracnghiem.giao_dien.bai_lam

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhThiDau(navController: NavHostController) {
    val ds = DuLieuMau.layCauHoiDangLam()
    val viTri = DuLieuMau.viTriCau.coerceIn(0, (ds.size - 1).coerceAtLeast(0))
    val cau = ds.getOrNull(viTri)
    KhungManHinh("Thi đấu – ${DuLieuMau.maPhong}", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Chế độ: ${DuLieuMau.kieuPhong}; thời hạn do server quyết định ở bản thật")
            if (cau == null) Text("Không có câu hỏi") else {
                Text("Câu ${viTri + 1}/${ds.size}: ${cau.noiDung}", style = MaterialTheme.typography.titleLarge)
                cau.phuongAn.forEachIndexed { i, phuongAn ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = DuLieuMau.dapAnDaChon[cau.ma] == i, onClick = { DuLieuMau.dapAnDaChon[cau.ma] = i })
                        Text("${'A' + i}. $phuongAn")
                    }
                }
                Text("Không có trợ giúp 50/50 khi thi đấu.")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { DuLieuMau.viTriCau-- }, enabled = viTri > 0, modifier = Modifier.weight(1f)) { Text("Trước") }
                    Button(onClick = { DuLieuMau.viTriCau++ }, enabled = viTri < ds.lastIndex, modifier = Modifier.weight(1f)) { Text("Sau") }
                }
                OutlinedButton(onClick = { navController.navigate(DuongDan.DANH_SACH_CAU_HOI) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Danh sách câu hỏi")
                }
                Button(onClick = { navController.navigate(DuongDan.XAC_NHAN_NOP) }, modifier = Modifier.fillMaxWidth()) { Text("Nộp bài thi") }
            }
        }
    }
}
