package vn.nhom.tracnghiem.giao_dien.bai_lam

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhXacNhanNop(navController: NavHostController) {
    val ds = DuLieuMau.layCauHoiDangLam()
    val chua = ds.count { !DuLieuMau.dapAnDaChon.containsKey(it.ma) }
    KhungManHinh("Xác nhận nộp bài", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Xác nhận kết thúc bài làm?", style = MaterialTheme.typography.titleLarge)
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Tổng số câu: ${ds.size}")
                    Text("Đã trả lời: ${ds.size - chua}")
                    Text("Câu chưa trả lời: $chua", color = MaterialTheme.colorScheme.error)
                }
            }
            Text("Sau khi nộp, bạn không thể sửa đáp án.")
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) { Text("Tiếp tục làm bài") }
            Button(onClick = {
                DuLieuMau.nopBaiMau()
                navController.navigate(if (DuLieuMau.dangThiDau) DuongDan.XEP_HANG_PHONG else DuongDan.KET_QUA) {
                    popUpTo(DuongDan.XAC_NHAN_NOP) { inclusive = true }
                }
            }, modifier = Modifier.fillMaxWidth()) { Text("Xác nhận nộp") }
            //Text("Chấm bài demo tại Android chỉ phục vụ kiểm tra UI; bản thật phải gọi API.")
        }
    }
}
