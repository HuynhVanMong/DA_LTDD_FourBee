package vn.nhom.tracnghiem.giao_dien.quan_tri

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.ChuDe
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhSuaChuDe(navController: NavHostController, ma: Int) {
    val chuDeCu = DuLieuMau.layChuDe(ma)
    var ten by remember(ma) { mutableStateOf(chuDeCu?.ten ?: "") }
    var cap by remember(ma) { mutableStateOf(chuDeCu?.capHoc ?: "Tiểu học") }
    var loi by remember { mutableStateOf("") }
    KhungManHinh(if (ma == 0) "Thêm chủ đề" else "Sửa chủ đề", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(ten, { ten = it }, label = { Text("Tên môn học / chủ đề") }, modifier = Modifier.fillMaxWidth())
            Text("Cấp học:", style = MaterialTheme.typography.titleSmall)
            listOf("Tiểu học", "THCS", "THPT").forEach { luaChon ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = cap == luaChon, onClick = { cap = luaChon })
                    Text(luaChon)
                }
            }
            Button(onClick = {
                if (ten.isBlank()) loi = "Chưa nhập tên chủ đề"
                else if (DuLieuMau.chuDe.any { it.ma != ma && it.ten.equals(ten.trim(), true) && it.capHoc == cap }) {
                    loi = "Tên chủ đề đã tồn tại trong cùng cấp học"
                } else {
                    if (ma == 0) {
                        val maMoi = (DuLieuMau.chuDe.maxOfOrNull { it.ma } ?: 0) + 1
                        DuLieuMau.chuDe.add(ChuDe(maMoi, ten.trim(), cap))
                    } else {
                        val i = DuLieuMau.chuDe.indexOfFirst { it.ma == ma }
                        if (i >= 0) DuLieuMau.chuDe[i] = ChuDe(ma, ten.trim(), cap)
                    }
                    navController.popBackStack()
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Lưu") }
            Text(loi)
        }
    }
}
