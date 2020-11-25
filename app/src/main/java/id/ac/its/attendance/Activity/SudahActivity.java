package id.ac.its.attendance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;

import id.ac.its.attendance.R;

import id.ac.its.attendance.Response.Pengajuan.Pengajuan;
import id.ac.its.attendance.Response.Pengajuan.ResponsePengajuan;
import id.ac.its.attendance.Response.Profile.ProfileResponse;
import id.ac.its.attendance.Retrofit.ServerAttendance.AccessToken;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import id.ac.its.attendance.Utility.Constans;


import java.util.ArrayList;
import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SudahActivity extends AppCompatActivity {
    private TokenManager tokenManager;
    RecyclerView recyclerView;
    ListAdapter adapter;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudah);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        recyclerView = findViewById(R.id.list_pengajuan);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        ApiClientAttendance api = ServerAttendance.createServiceWithAuth(ApiClientAttendance.class,tokenManager);
        final Call<List<Pengajuan>> call = api.getpengajuan();

        call.enqueue(new Callback<List<Pengajuan>>() {
            @Override
            public void onResponse(Call<List<Pengajuan>> call, Response<List<Pengajuan>> response) {
                if(response.isSuccessful()){
                    List<Pengajuan> get = response.body();
                    adapter = new ListAdapter(SudahActivity.this, get);

                    return;
                }
            }

            @Override
            public void onFailure(Call<List<Pengajuan>> call, Throwable t) {
                System.out.println("failed" + t.toString());
            }
        });
    }
}