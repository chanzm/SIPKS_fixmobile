package id.ac.its.attendance.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.DetailPengajuan.ResponseDetailPengajuan;
import id.ac.its.attendance.Response.OKPengajuan.OKResponse;
import id.ac.its.attendance.Response.Pengajuan.ResponsePengajuan;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPengajuanActivity  extends AppCompatActivity {
    private int id;
    private Button konfirmasi;
    private String confirm;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpengajuan);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        id = getIntent().getIntExtra("id",0);
        confirm = getIntent().getStringExtra("konfirmasi");
        konfirmasi = findViewById(R.id.konfirmasi);
        if (confirm.equals("1")) {
            konfirmasi.setVisibility(View.GONE);
        }

        final ApiClientAttendance api = ServerAttendance.createServiceWithAuth(ApiClientAttendance.class,tokenManager);
        final Call<ResponseDetailPengajuan> call = api.getdetailpengajuan(id);

        call.enqueue(new Callback<ResponseDetailPengajuan>() {
            @Override
            public void onResponse(Call<ResponseDetailPengajuan> call, Response<ResponseDetailPengajuan> response) {
                if(response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<ResponseDetailPengajuan> call, Throwable t) {

            }
        });
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<OKResponse> call = api.postdetailpengajuan(id);
                call.enqueue(new Callback<OKResponse>() {
                    @Override
                    public void onResponse(Call<OKResponse> call, Response<OKResponse> response) {
                        Log.w("lalalayeye",response.body().getStatus());
                        if(response.isSuccessful())
                        {
                            konfirmasi.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<OKResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}
