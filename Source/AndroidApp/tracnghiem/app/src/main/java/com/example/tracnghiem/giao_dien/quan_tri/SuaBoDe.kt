package vn.nhom.tracnghiem.giao_dien.quan_tri

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.BoDe
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhSuaBoDe(navController: NavHostController, ma: Int) {
    val cu = DuLieuMau.layBoDe(ma)
    var ten by remember(ma) { mutableStateOf(cu?.ten ?: "") }
    var thoiGian by remember(ma) { mutableStateOf(cu?.thoiGianPhut?.toString() ?: "10") }
    var maChuDe by remember(ma) { mutableStateOf(cu?.maChuDe ?: 1) }
    var doKho by remember(ma) { mutableStateOf(cu?.doKho ?: "Dễ") }
    var congBo by remember(ma) { mutableStateOf(cu?.congBo ?: false) }
    val dsChon = remember(ma) { mutableStateListOf<Int>().apply { addAll(cu?.maCauHoi ?: emptyList()) } }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh(if (ma == 0) "Tạo bộ đề" else "Sửa bộ đề", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(ten, { ten = it }, label = { Text("Tên bộ đề") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(thoiGian, { thoiGian = it }, label = { Text("Thời gian (phút)") }, modifier = Modifier.fillMaxWidth())
            Text("Chủ đề:")
            DuLieuMau.chuDe.forEach { cd ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = maChuDe == cd.ma, onClick = {
                        maChuDe = cd.ma
                        dsChon.clear()
                    })
                    Text("${cd.ten} (${cd.capHoc})")
                }
            }
            Text("Độ khó:")
            listOf("Dễ", "Vừa", "Khó").forEach { kho ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = doKho == kho, onClick = { doKho = kho })
                    Text(kho)
                }
            }
            Text("Chọn câu hỏi (${dsChon.size} câu):", style = MaterialTheme.typography.titleSmall)
            DuLieuMau.cauHoi.filter { it.maChuDe == maChuDe }.forEach { cau ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = cau.ma in dsChon, onCheckedChange = { chon ->
                        if (chon && cau.ma !in dsChon) dsChon.add(cau.ma)
                        if (!chon) dsChon.remove(cau.ma)
                    })
                    Text(cau.noiDung)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = congBo, onCheckedChange = { congBo = it })
                Text("Công bố bộ đề")
            }
            Button(onClick = {
                val phut = thoiGian.toIntOrNull()
                if (ten.isBlank() || phut == null || phut <= 0 || dsChon.isEmpty()) {
                    thongBao = "Nhập tên, thời gian > 0 và chọn ít nhất một câu"
                } else {
                    val moi = BoDe(if (ma == 0) (DuLieuMau.boDe.maxOfOrNull { it.ma } ?: 0) + 1 else ma,
                        ten, maChuDe, doKho, phut, dsChon.toList(), cu?.luotLam ?: 0,
                        cu?.diemTrungBinh ?: 0f, congBo)
                    if (ma == 0) DuLieuMau.boDe.add(moi)
                    else {
                        val i = DuLieuMau.boDe.indexOfFirst { it.ma == ma }
                        if (i >= 0) DuLieuMau.boDe[i] = moi
                    }
                    navController.popBackStack()
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Lưu bộ đề") }
            Text(thongBao)
        }
    }
}
