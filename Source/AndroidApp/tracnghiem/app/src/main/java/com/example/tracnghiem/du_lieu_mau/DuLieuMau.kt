package vn.nhom.tracnghiem.du_lieu_mau

import androidx.compose.runtime.*

// Chỉ là dữ liệu mẫu để chạy giao diện và thử Navigation.
// Khi nhóm viết REST API sẽ thay dữ liệu này bằng dữ liệu từ server.
data class ChuDe(val ma: Int, val ten: String, val capHoc: String)
data class CauHoi(
    val ma: Int, val maChuDe: Int, val noiDung: String,
    val phuongAn: List<String>, val dapAnDung: Int, val doKho: String = "Dễ"
)
data class BoDe(
    val ma: Int, val ten: String, val maChuDe: Int,
    val doKho: String, val thoiGianPhut: Int, val maCauHoi: List<Int>,
    val luotLam: Int = 0, val diemTrungBinh: Float = 0f, val congBo: Boolean = true
)
data class NguoiDung(val ma: Int, val ten: String, val email: String, val vaiTro: String = "USER", val biKhoa: Boolean = false)
data class LuotLam(
    val ma: Int, val maBoDe: Int, val soCauDung: Int,
    val soCauSai: Int, val soCauBoTrong: Int, val thoiGianGiay: Int,
    val luaChon: Map<Int, Int> = emptyMap()
)
data class DanhGia(val maBoDe: Int, val tenNguoi: String, val soSao: Int, val noiDung: String)
data class LoiMoi(val nguoiGui: String, val boDe: String, val trangThai: String = "Chờ trả lời")
data class HangThang(val ten: String, val diem: Int)
data class HangPhong(val ten: String, val soCauDung: Int, val thoiGianGiay: Int)

object DuLieuMau {
    var tenDangNhap by mutableStateOf("Học sinh Demo")
    var emailDangNhap by mutableStateOf("hocsinh@example.com")
    val nguoiDung = mutableStateListOf(
        NguoiDung(1, "Nguyễn Minh An", "an@example.com"),
        NguoiDung(2, "Trần Bình", "binh@example.com"),
        NguoiDung(3, "Lê Chi", "chi@example.com")
    )
    val chuDe = mutableStateListOf(
        ChuDe(1, "Toán", "Tiểu học"),
        ChuDe(2, "Ngữ văn", "THCS"),
        ChuDe(3, "Vật lý", "THPT")
    )
    val cauHoi = mutableStateListOf(
        CauHoi(1, 1, "12 × 3 bằng bao nhiêu?", listOf("24", "36", "48", "54"), 1),
        CauHoi(2, 1, "18 + 7 bằng bao nhiêu?", listOf("23", "24", "25", "26"), 2),
        CauHoi(3, 1, "25 - 9 bằng bao nhiêu?", listOf("14", "15", "16", "17"), 2),
        CauHoi(4, 1, "6 × 7 bằng bao nhiêu?", listOf("36", "40", "42", "48"), 2),
        CauHoi(5, 1, "50 : 5 bằng bao nhiêu?", listOf("5", "10", "15", "20"), 1),
        CauHoi(6, 1, "7 + 8 bằng bao nhiêu?", listOf("14", "15", "16", "17"), 1),
        CauHoi(7, 1, "9 × 2 bằng bao nhiêu?", listOf("16", "17", "18", "19"), 2),
        CauHoi(8, 1, "100 - 35 bằng bao nhiêu?", listOf("55", "60", "65", "75"), 2),
        CauHoi(9, 1, "4 × 4 bằng bao nhiêu?", listOf("8", "12", "16", "20"), 2),
        CauHoi(10, 1, "81 : 9 bằng bao nhiêu?", listOf("7", "8", "9", "10"), 2),
        CauHoi(11, 1, "15 + 6 bằng bao nhiêu?", listOf("19", "20", "21", "22"), 2),
        CauHoi(12, 2, "Từ nào là danh từ?", listOf("Chạy", "Bàn học", "Đẹp", "Nhanh"), 1),
        CauHoi(13, 3, "Đơn vị đo vận tốc là gì?", listOf("kg", "m/s", "N", "J"), 1)
    )
    val boDe = mutableStateListOf(
        BoDe(1, "Toán tiểu học – 5 câu", 1, "Dễ", 5, (1..5).toList(), 120, 8.5f),
        BoDe(2, "Toán tiểu học – 10 câu", 1, "Vừa", 10, (1..10).toList(), 88, 7.2f),
        BoDe(3, "Toán tiểu học – 11 câu", 1, "Khó", 12, (1..11).toList(), 43, 7.7f),
        BoDe(4, "Ngữ văn THCS – Cơ bản", 2, "Dễ", 5, listOf(12), 16, 8.0f),
        BoDe(5, "Vật lý THPT – Cơ bản", 3, "Vừa", 5, listOf(13), 11, 6.0f)
    )
    val danhGia = mutableStateListOf(
        DanhGia(1, "Minh An", 5, "Câu hỏi dễ hiểu."),
        DanhGia(1, "Trần Bình", 4, "Nội dung phù hợp để ôn tập.")
    )
    val loiMoi = mutableStateListOf(
        LoiMoi("binh123", "Toán tiểu học – 5 câu")
    )
    val xepHangThang = mutableStateListOf(
        HangThang("Minh An", 85), HangThang("Trần Bình", 70),
        HangThang("Lê Chi", 55), HangThang("Học sinh Demo", 10)
    )
    val xepHangPhong = mutableStateListOf(
        HangPhong("Minh An", 5, 195), HangPhong("Học sinh Demo", 4, 201),
        HangPhong("Trần Bình", 3, 185)
    )

    var maBoDeDangLam by mutableStateOf(1)
    var viTriCau by mutableStateOf(0)
    var dangThiDau by mutableStateOf(false)
    var daNopBai by mutableStateOf(false)
    var maLuotDangXem by mutableStateOf(0)
    var daDung5050 by mutableStateOf(false)
    var soLan5050 by mutableStateOf(0)
    var daCapThem5050 by mutableStateOf(false)
    val anPhuongAn5050 = mutableStateMapOf<Int, List<Int>>()
    val dapAnDaChon = mutableStateMapOf<Int, Int>()
    var maPhong by mutableStateOf("DEMO01")
    var soNguoiToiDa by mutableStateOf(10)
    var soNguoiTrongPhong by mutableStateOf(2)
    var kieuPhong by mutableStateOf("Nhóm")
    var sanSang by mutableStateOf(false)
    val lichSu = mutableStateListOf(
        LuotLam(101, 1, 4, 1, 0, 200, mapOf(1 to 1, 2 to 2, 3 to 2, 4 to 0, 5 to 1))
    )

    fun layChuDe(ma: Int) = chuDe.firstOrNull { it.ma == ma }
    fun layBoDe(ma: Int) = boDe.firstOrNull { it.ma == ma }
    fun layCauHoiDangLam(): List<CauHoi> {
        val ma = layBoDe(maBoDeDangLam)?.maCauHoi ?: emptyList()
        return ma.mapNotNull { so -> cauHoi.firstOrNull { it.ma == so } }
    }
    fun batDauBai(ma: Int, thiDau: Boolean = false) {
        maBoDeDangLam = ma
        viTriCau = 0
        daNopBai = false
        dangThiDau = thiDau
        daDung5050 = false
        soLan5050 = 0
        daCapThem5050 = false
        anPhuongAn5050.clear()
        dapAnDaChon.clear()
        maLuotDangXem = 0
    }
    fun nopBaiMau() {
        if (daNopBai) return
        val ds = layCauHoiDangLam()
        val dung = ds.count { dapAnDaChon[it.ma] == it.dapAnDung }
        val daTraLoi = ds.count { dapAnDaChon.containsKey(it.ma) }
        daNopBai = true
        if (!dangThiDau) {
            val ma = (lichSu.maxOfOrNull { it.ma } ?: 100) + 1
            lichSu.add(0, LuotLam(ma, maBoDeDangLam, dung, daTraLoi - dung,
                ds.size - daTraLoi, 220, dapAnDaChon.toMap()))
            maLuotDangXem = ma
        }
    }
    fun layLuotDangXem(): LuotLam? = lichSu.firstOrNull { it.ma == maLuotDangXem }
}
