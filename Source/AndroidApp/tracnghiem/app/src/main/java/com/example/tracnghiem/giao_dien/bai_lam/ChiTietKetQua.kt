package vn.nhom.tracnghiem.giao_dien.bai_lam

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh
import vn.nhom.tracnghiem.thanh_phan.MauSac

@Composable
fun ManHinhChiTietKetQua(navController: NavHostController) {
    val luot = DuLieuMau.layLuotDangXem()
    val de = DuLieuMau.layBoDe(luot?.maBoDe ?: 0)
    val ds = de?.maCauHoi?.mapNotNull { ma -> DuLieuMau.cauHoi.firstOrNull { it.ma == ma } } ?: emptyList()
    KhungManHinh("Chi tiết kết quả", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            if (luot == null) Text("Chưa có bài làm được chấm")
            else {
                Text(de?.ten ?: "Bộ đề", style = MaterialTheme.typography.titleLarge)
                ds.forEachIndexed { viTri, cau ->
                    val chon = luot.luaChon[cau.ma]
                    val dung = chon == cau.dapAnDung
                    Card(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("Câu ${viTri + 1}: ${cau.noiDung}", style = MaterialTheme.typography.titleMedium)
                            Text(when {
                                chon == null -> "Chưa trả lời"
                                dung -> "Bạn trả lời đúng"
                                else -> "Bạn trả lời sai"
                            }, color = if (dung) MauSac.XanhLa else MauSac.Do)
                            Text("Bạn chọn: ${chon?.let { cau.phuongAn.getOrNull(it) } ?: "(bỏ trống)"}")
                            Text("Đáp án đúng: ${cau.phuongAn[cau.dapAnDung]}", color = MauSac.XanhLa)
                        }
                    }
                }
            }
        }
    }
}
