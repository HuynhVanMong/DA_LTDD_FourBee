package vn.nhom.tracnghiem.giao_dien.thi_dau

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.dieu_huong.DuongDan
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhChonCheDo(navController: NavHostController) {
    KhungManHinh("Chọn chế độ", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Bạn muốn học hay thi đấu?", style = MaterialTheme.typography.titleLarge)
            Button(onClick = { navController.navigate(DuongDan.DANH_SACH_BO_DE) }, modifier = Modifier.fillMaxWidth()) {
                Text("Luyện tập trắc nghiệm")
            }
            OutlinedButton(onClick = { navController.navigate(DuongDan.MOI_THI_DOI_KHANG) }, modifier = Modifier.fillMaxWidth()) {
                Text("Mời người khác thi đối kháng")
            }
            OutlinedButton(onClick = { navController.navigate(DuongDan.TAO_PHONG) }, modifier = Modifier.fillMaxWidth()) {
                Text("Tạo phòng thi nhóm (tối đa 50)")
            }
            OutlinedButton(onClick = { navController.navigate(DuongDan.VAO_PHONG) }, modifier = Modifier.fillMaxWidth()) {
                Text("Vào phòng bằng mã")
            }
            TextButton(onClick = { navController.navigate(DuongDan.XEP_HANG_THANG) }) {
                Text("Xem bảng xếp hạng tháng")
            }
        }
    }
}
