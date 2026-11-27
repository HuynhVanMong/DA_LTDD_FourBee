package vn.nhom.tracnghiem.dieu_huong

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.nhom.tracnghiem.giao_dien.bai_lam.*
import vn.nhom.tracnghiem.giao_dien.bo_de.*
import vn.nhom.tracnghiem.giao_dien.lich_su.*
import vn.nhom.tracnghiem.giao_dien.quan_tri.*
import vn.nhom.tracnghiem.giao_dien.tai_khoan.*
import vn.nhom.tracnghiem.giao_dien.thi_dau.*
import vn.nhom.tracnghiem.giao_dien.trang_chu.*

object DuongDan {
    const val DANG_NHAP = "dang_nhap"
    const val DANG_KY = "dang_ky"
    const val THONG_TIN_CA_NHAN = "thong_tin_ca_nhan"
    const val DOI_MAT_KHAU = "doi_mat_khau"
    const val QUAN_LY_NGUOI_DUNG = "quan_ly_nguoi_dung"
    const val THONG_KE_ADMIN = "thong_ke_admin"
    const val XEP_HANG_THANG = "xep_hang_thang"
    const val QUAN_LY_CHU_DE = "quan_ly_chu_de"
    const val SUA_CHU_DE = "sua_chu_de/{ma}"
    const val QUAN_LY_CAU_HOI = "quan_ly_cau_hoi"
    const val SUA_CAU_HOI = "sua_cau_hoi/{ma}"
    const val QUAN_LY_BO_DE = "quan_ly_bo_de"
    const val SUA_BO_DE = "sua_bo_de/{ma}"
    const val DANH_SACH_BO_DE = "danh_sach_bo_de"
    const val CHI_TIET_BO_DE = "chi_tiet_bo_de/{ma}"
    const val DANH_GIA_BINH_LUAN = "danh_gia_binh_luan/{ma}"
    const val LAM_BAI = "lam_bai"
    const val DANH_SACH_CAU_HOI = "danh_sach_cau_hoi"
    const val XAC_NHAN_NOP = "xac_nhan_nop"
    const val KET_QUA = "ket_qua"
    const val CHI_TIET_KET_QUA = "chi_tiet_ket_qua"
    const val LICH_SU_LAM_BAI = "lich_su_lam_bai"
    const val CHI_TIET_LICH_SU = "chi_tiet_lich_su/{ma}"
    const val CHON_CHE_DO = "chon_che_do"
    const val MOI_THI_DOI_KHANG = "moi_thi_doi_khang"
    const val DANH_SACH_LOI_MOI = "danh_sach_loi_moi"
    const val TAO_PHONG = "tao_phong"
    const val VAO_PHONG = "vao_phong"
    const val PHONG_CHO = "phong_cho"
    const val THI_DAU = "thi_dau"
    const val XEP_HANG_PHONG = "xep_hang_phong"
    const val TRANG_CHU = "trang_chu"

    fun suaChuDe(ma: Int) = "sua_chu_de/$ma"
    fun suaCauHoi(ma: Int) = "sua_cau_hoi/$ma"
    fun suaBoDe(ma: Int) = "sua_bo_de/$ma"
    fun chiTietBoDe(ma: Int) = "chi_tiet_bo_de/$ma"
    fun danhGia(ma: Int) = "danh_gia_binh_luan/$ma"
    fun chiTietLichSu(ma: Int) = "chi_tiet_lich_su/$ma"
}

@Composable
fun BoDieuHuong() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DuongDan.DANG_NHAP) {
        composable(DuongDan.DANG_NHAP) { ManHinhDangNhap(navController) }
        composable(DuongDan.DANG_KY) { ManHinhDangKy(navController) }
        composable(DuongDan.THONG_TIN_CA_NHAN) { ManHinhThongTinCaNhan(navController) }
        composable(DuongDan.DOI_MAT_KHAU) { ManHinhDoiMatKhau(navController) }
        composable(DuongDan.QUAN_LY_NGUOI_DUNG) { ManHinhQuanLyNguoiDung(navController) }
        composable(DuongDan.THONG_KE_ADMIN) { ManHinhThongKeAdmin(navController) }
        composable(DuongDan.XEP_HANG_THANG) { ManHinhXepHangThang(navController) }
        composable(DuongDan.QUAN_LY_CHU_DE) { ManHinhQuanLyChuDe(navController) }
        composable(DuongDan.SUA_CHU_DE) { ve ->
            val ma = ve.arguments?.getString("ma")?.toIntOrNull() ?: 0
            ManHinhSuaChuDe(navController, ma)
        }
        composable(DuongDan.QUAN_LY_CAU_HOI) { ManHinhQuanLyCauHoi(navController) }
        composable(DuongDan.SUA_CAU_HOI) { ve ->
            val ma = ve.arguments?.getString("ma")?.toIntOrNull() ?: 0
            ManHinhSuaCauHoi(navController, ma)
        }
        composable(DuongDan.QUAN_LY_BO_DE) { ManHinhQuanLyBoDe(navController) }
        composable(DuongDan.SUA_BO_DE) { ve ->
            val ma = ve.arguments?.getString("ma")?.toIntOrNull() ?: 0
            ManHinhSuaBoDe(navController, ma)
        }
        composable(DuongDan.DANH_SACH_BO_DE) { ManHinhDanhSachBoDe(navController) }
        composable(DuongDan.CHI_TIET_BO_DE) { ve ->
            val ma = ve.arguments?.getString("ma")?.toIntOrNull() ?: 0
            ManHinhChiTietBoDe(navController, ma)
        }
        composable(DuongDan.DANH_GIA_BINH_LUAN) { ve ->
            val ma = ve.arguments?.getString("ma")?.toIntOrNull() ?: 0
            ManHinhDanhGiaBinhLuan(navController, ma)
        }
        composable(DuongDan.LAM_BAI) { ManHinhLamBai(navController) }
        composable(DuongDan.DANH_SACH_CAU_HOI) { ManHinhDanhSachCauHoi(navController) }
        composable(DuongDan.XAC_NHAN_NOP) { ManHinhXacNhanNop(navController) }
        composable(DuongDan.KET_QUA) { ManHinhKetQua(navController) }
        composable(DuongDan.CHI_TIET_KET_QUA) { ManHinhChiTietKetQua(navController) }
        composable(DuongDan.LICH_SU_LAM_BAI) { ManHinhLichSuLamBai(navController) }
        composable(DuongDan.CHI_TIET_LICH_SU) { ve ->
            val ma = ve.arguments?.getString("ma")?.toIntOrNull() ?: 0
            ManHinhChiTietLichSu(navController, ma)
        }
        composable(DuongDan.CHON_CHE_DO) { ManHinhChonCheDo(navController) }
        composable(DuongDan.MOI_THI_DOI_KHANG) { ManHinhMoiThiDoiKhang(navController) }
        composable(DuongDan.DANH_SACH_LOI_MOI) { ManHinhDanhSachLoiMoi(navController) }
        composable(DuongDan.TAO_PHONG) { ManHinhTaoPhong(navController) }
        composable(DuongDan.VAO_PHONG) { ManHinhVaoPhong(navController) }
        composable(DuongDan.PHONG_CHO) { ManHinhPhongCho(navController) }
        composable(DuongDan.THI_DAU) { ManHinhThiDau(navController) }
        composable(DuongDan.XEP_HANG_PHONG) { ManHinhXepHangPhong(navController) }
        composable(DuongDan.TRANG_CHU) { ManHinhTrangChu(navController) }
    }
}
