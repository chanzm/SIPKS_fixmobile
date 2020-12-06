package id.ac.its.attendance.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.Pengajuan.ResponsePengajuan;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import retrofit2.Call;

public class BerhasilActivity  extends AppCompatActivity {
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.berhasilkonfirm);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        ApiClientAttendance api = ServerAttendance.createServiceWithAuth(ApiClientAttendance.class, tokenManager);
        final Call<ResponsePengajuan> call = api.getpengajuan(1);


    }
}