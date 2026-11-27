package vn.nhom.tracnghiem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import vn.nhom.tracnghiem.dieu_huong.BoDieuHuong
import vn.nhom.tracnghiem.thanh_phan.MauSac

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = lightColorScheme(primary = MauSac.XanhChinh)) {
                BoDieuHuong()
            }
        }
    }
}
