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
fun ManHinhLamBai(navController: NavHostController) {
    val danhSach = DuLieuMau.layCauHoiDangLam()
    val de = DuLieuMau.layBoDe(DuLieuMau.maBoDeDangLam)
    val viTri = DuLieuMau.viTriCau.coerceIn(0, (danhSach.size - 1).coerceAtLeast(0))
    val cau = danhSach.getOrNull(viTri)
    KhungManHinh("Làm bài trắc nghiệm", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(de?.ten ?: "Bộ đề", style = MaterialTheme.typography.titleMedium)
            Text("Thời gian của đề: ${de?.thoiGianPhut ?: 0} phút")
            Text("Đã trả lời ${danhSach.count { DuLieuMau.dapAnDaChon.containsKey(it.ma) }}/${danhSach.size} câu")
            if (cau == null) Text("Chưa có câu hỏi") else {
                LinearProgressIndicator(progress = { (viTri + 1).toFloat() / danhSach.size }, modifier = Modifier.fillMaxWidth())
                Text("Câu ${viTri + 1}/${danhSach.size}: ${cau.noiDung}", style = MaterialTheme.typography.titleLarge)
                cau.phuongAn.forEachIndexed { chiSo, phuongAn ->
                    if (!(DuLieuMau.anPhuongAn5050[cau.ma]?.contains(chiSo) ?: false)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = DuLieuMau.dapAnDaChon[cau.ma] == chiSo,
                                onClick = {
                                    DuLieuMau.dapAnDaChon[cau.ma] = chiSo
                                    val daTraLoi = danhSach.count { DuLieuMau.dapAnDaChon.containsKey(it.ma) }
                                    val tong = danhSach.size
                                    val moc = if (tong in 2..9) (tong + 1) / 2 else 10
                                    if (tong != 10 && tong > 1 && daTraLoi >= moc && daTraLoi < tong) {
                                        DuLieuMau.daCapThem5050 = true
                                    }
                                }
                            )
                            Text("${'A' + chiSo}. $phuongAn")
                        }
                    }
                }
                val quyenCon = 1 + (if (DuLieuMau.daCapThem5050) 1 else 0) - DuLieuMau.soLan5050
                OutlinedButton(
                    onClick = {
                        // Chỉ demo: Android có sẵn đáp án đúng. Khi dùng API, server phải chọn 2 câu sai.
                        val sai = cau.phuongAn.indices.filter { it != cau.dapAnDung }
                        DuLieuMau.anPhuongAn5050[cau.ma] = sai.take(2)
                        DuLieuMau.soLan5050++
                    }, enabled = quyenCon > 0 && !DuLieuMau.anPhuongAn5050.containsKey(cau.ma)
                ) { Text("Dùng 50/50 (còn $quyenCon quyền – demo)") }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(onClick = { if (DuLieuMau.viTriCau > 0) DuLieuMau.viTriCau-- },
                        enabled = viTri > 0, modifier = Modifier.weight(1f)) { Text("Câu trước") }
                    Button(onClick = { if (viTri < danhSach.lastIndex) DuLieuMau.viTriCau++ },
                        enabled = viTri < danhSach.lastIndex, modifier = Modifier.weight(1f)) { Text("Câu sau") }
                }
                OutlinedButton(onClick = { navController.navigate(DuongDan.DANH_SACH_CAU_HOI) },
                    modifier = Modifier.fillMaxWidth()) { Text("Xem danh sách câu hỏi") }
                Button(onClick = { navController.navigate(DuongDan.XAC_NHAN_NOP) },
                    modifier = Modifier.fillMaxWidth()) { Text("Nộp bài") }
                //Text("Chỉ mô phỏng lựa chọn. Bài thật phải lưu đáp án và thời hạn ở server.")
            }
        }
    }
}
