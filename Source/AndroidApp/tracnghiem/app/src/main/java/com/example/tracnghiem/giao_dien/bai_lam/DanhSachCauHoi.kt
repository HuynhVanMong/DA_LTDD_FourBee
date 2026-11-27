package vn.nhom.tracnghiem.giao_dien.bai_lam

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vn.nhom.tracnghiem.du_lieu_mau.DuLieuMau
import vn.nhom.tracnghiem.thanh_phan.KhungManHinh

@Composable
fun ManHinhDanhSachCauHoi(navController: NavHostController) {
    val ds = DuLieuMau.layCauHoiDangLam()
    KhungManHinh("Danh sách câu hỏi", navController) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).padding(16.dp)) {
            Text("Chọn số câu để quay lại làm bài")
            Text("Đã trả lời: ${ds.count { DuLieuMau.dapAnDaChon.containsKey(it.ma) }}/${ds.size}")
            Spacer(Modifier.height(12.dp))
            LazyVerticalGrid(columns = GridCells.Fixed(5), verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(ds) { viTri, cau ->
                    val daTraLoi = DuLieuMau.dapAnDaChon.containsKey(cau.ma)
                    Button(onClick = {
                        DuLieuMau.viTriCau = viTri
                        navController.popBackStack()
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = if (daTraLoi) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    ), contentPadding = PaddingValues(0.dp)) { Text("${viTri + 1}") }
                }
            }
        }
    }
}
