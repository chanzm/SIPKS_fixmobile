package id.ac.its.attendance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private RecyclerView recyclerView;
    private TextView judul,tanggal,total,nama_pengaju,jabatan,deskripsi,status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpengajuan);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        recyclerView = findViewById(R.id.item_list_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        id = getIntent().getIntExtra("id",0);
        confirm = getIntent().getStringExtra("konfirmasi");
        konfirmasi = findViewById(R.id.konfirmasi);
        judul = findViewById(R.id.judul_pengajuan);
        tanggal = findViewById(R.id.tanggal);
        total = findViewById(R.id.total_pengajuan);
        nama_pengaju = findViewById(R.id.nama_pengaju);
        jabatan = findViewById(R.id.jabatan_pengaju);
        deskripsi = findViewById(R.id.deskripsi);
        status = findViewById(R.id.status_pengajuan);

        if (confirm.equals("1")) {
            konfirmasi.setVisibility(View.GONE);
        }

        final ApiClientAttendance api = ServerAttendance.createServiceWithAuth(ApiClientAttendance.class,tokenManager);
        final Call<ResponseDetailPengajuan> call = api.getdetailpengajuan(id);

        call.enqueue(new Callback<ResponseDetailPengajuan>() {
            @Override
            public void onResponse(Call<ResponseDetailPengajuan> call, Response<ResponseDetailPengajuan> response) {
                if(response.isSuccessful()){
                    ListAdapterDetail Adapter = new ListAdapterDetail(getApplicationContext(),response.body().getDetailpeng());
                    recyclerView.setAdapter(Adapter);
                    judul.setText(response.body().getDetailpeng().get(0).getJudulPengajuan());
                    tanggal.setText(response.body().getDetailpeng().get(0).getCreateTime());
                    total.setText(String.valueOf(response.body().getDetailpeng().get(0).getJumlahPengajuan()));
                    nama_pengaju.setText(response.body().getDetailpeng().get(0).getNamaPembuatPengajuan());
                    jabatan.setText(response.body().getDetailpeng().get(0).getJabatanPembuatPengajuan());
                    deskripsi.setText(response.body().getDetailpeng().get(0).getDeskripsiPengajuan());
                    status.setText(response.body().getDetailpeng().get(0).getStatusPengajuan().equals("1")?"Sudah Dikonfirmasi":"Belum Dikonfirmasi");

                    if(response.body().getDetailpeng().get(0).getStatusPengajuan().equals("1")){
                        konfirmasi.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailPengajuan> call, Throwable t) {

            }
        });
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Log.w("lalalayeye",response.body().getStatus());
                Intent intent = new Intent(DetailPengajuanActivity.this, UploadSignatureActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });
    }
}
