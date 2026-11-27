package vn.nhom.tracnghiem.thanh_phan

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import vn.nhom.tracnghiem.dieu_huong.DuongDan


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KhungManHinh(
    tieuDe: String,
    navController: NavHostController,
    hienThanhDuoi: Boolean = false,
    laQuanTri: Boolean = false,
    hienQuayLai: Boolean = true,
    noiDung: @Composable (PaddingValues) -> Unit
) {
    val manHinhHienTai by navController.currentBackStackEntryAsState()
    val duongDan = manHinhHienTai?.destination?.route

    Scaffold(
        containerColor = MauSac.Nen,
        topBar = {
            TopAppBar(
                title = { Text(tieuDe) },
                navigationIcon = {
                    if (hienQuayLai) {
                        TextButton(onClick = { navController.popBackStack() }) {
                            Text("‹", color = Color.White)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MauSac.XanhChinh,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            if (hienThanhDuoi) {
                NavigationBar(containerColor = Color.White) {
                    if (laQuanTri) {
                        val danhSach = listOf(
                            Triple(DuongDan.THONG_KE_ADMIN, "Thống kê", "▥"),
                            Triple(DuongDan.QUAN_LY_CHU_DE, "Chủ đề", "▤"),
                            Triple(DuongDan.QUAN_LY_CAU_HOI, "Câu hỏi", "?"),
                            Triple(DuongDan.QUAN_LY_BO_DE, "Bộ đề", "▣")
                        )
                        danhSach.forEach { muc ->
                            NavigationBarItem(
                                selected = duongDan == muc.first,
                                onClick = { navController.navigate(muc.first) { launchSingleTop = true } },
                                icon = { Text(muc.third) },
                                label = { Text(muc.second) }
                            )
                        }
                    } else {
                        val danhSach = listOf(
                            Triple(DuongDan.TRANG_CHU, "Trang chủ", "⌂"),
                            Triple(DuongDan.DANH_SACH_BO_DE, "Bộ đề", "▣"),
                            Triple(DuongDan.LICH_SU_LAM_BAI, "Lịch sử", "◷"),
                            Triple(DuongDan.THONG_TIN_CA_NHAN, "Cá nhân", "☺")
                        )
                        danhSach.forEach { muc ->
                            NavigationBarItem(
                                selected = duongDan == muc.first,
                                onClick = { navController.navigate(muc.first) { launchSingleTop = true } },
                                icon = { Text(muc.third) },
                                label = { Text(muc.second) }
                            )
                        }
                    }
                }
            }
        }
    ) { khoangCach -> noiDung(khoangCach) }
}
