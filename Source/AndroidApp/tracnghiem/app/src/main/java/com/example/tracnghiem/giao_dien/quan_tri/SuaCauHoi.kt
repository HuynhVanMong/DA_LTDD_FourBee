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
import vn.nhom.tracnghiem.du_lieu_mau.CauHoi
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhSuaCauHoi(navController: NavHostController, ma: Int) {
    val cu = DuLieuMau.cauHoi.firstOrNull { it.ma == ma }
    var noiDung by remember(ma) { mutableStateOf(cu?.noiDung ?: "") }
    var a by remember(ma) { mutableStateOf(cu?.phuongAn?.getOrNull(0) ?: "") }
    var b by remember(ma) { mutableStateOf(cu?.phuongAn?.getOrNull(1) ?: "") }
    var c by remember(ma) { mutableStateOf(cu?.phuongAn?.getOrNull(2) ?: "") }
    var d by remember(ma) { mutableStateOf(cu?.phuongAn?.getOrNull(3) ?: "") }
    var dapAn by remember(ma) { mutableStateOf(cu?.dapAnDung ?: 0) }
    var maChuDe by remember(ma) { mutableStateOf(cu?.maChuDe ?: 1) }
    var doKho by remember(ma) { mutableStateOf(cu?.doKho ?: "Dễ") }
    var thongBao by remember { mutableStateOf("") }
    KhungManHinh(if (ma == 0) "Thêm câu hỏi" else "Sửa câu hỏi", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(noiDung, { noiDung = it }, label = { Text("Nội dung câu hỏi") }, modifier = Modifier.fillMaxWidth())
            Text("Chọn chủ đề", style = MaterialTheme.typography.titleSmall)
            DuLieuMau.chuDe.forEach { cd ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = maChuDe == cd.ma, onClick = { maChuDe = cd.ma })
                    Text("${cd.ten} – ${cd.capHoc}")
                }
            }
            Text("Bốn đáp án", style = MaterialTheme.typography.titleSmall)
            OutlinedTextField(a, { a = it }, label = { Text("Đáp án A") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(b, { b = it }, label = { Text("Đáp án B") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(c, { c = it }, label = { Text("Đáp án C") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(d, { d = it }, label = { Text("Đáp án D") }, modifier = Modifier.fillMaxWidth())
            Text("Chọn đáp án đúng")
            listOf("A", "B", "C", "D").forEachIndexed { i, chu ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = dapAn == i, onClick = { dapAn = i })
                    Text("Phương án $chu")
                }
            }
            Text("Độ khó")
            listOf("Dễ", "Vừa", "Khó").forEach { kho ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = doKho == kho, onClick = { doKho = kho })
                    Text(kho)
                }
            }
            Button(onClick = {
                if (noiDung.isBlank() || listOf(a,b,c,d).any { it.isBlank() }) {
                    thongBao = "Cần nhập câu hỏi và đủ bốn đáp án"
                } else {
                    val moi = CauHoi(if (ma == 0) (DuLieuMau.cauHoi.maxOfOrNull { it.ma } ?: 0) + 1 else ma,
                        maChuDe, noiDung, listOf(a,b,c,d), dapAn, doKho)
                    if (ma == 0) DuLieuMau.cauHoi.add(moi)
                    else {
                        val i = DuLieuMau.cauHoi.indexOfFirst { it.ma == ma }
                        if (i >= 0) DuLieuMau.cauHoi[i] = moi
                    }
                    navController.popBackStack()
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Lưu câu hỏi") }
            Text(thongBao)
        }
    }
}
