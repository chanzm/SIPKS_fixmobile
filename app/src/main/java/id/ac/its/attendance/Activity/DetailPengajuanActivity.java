package id.ac.its.attendance.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.DetailPengajuan.ResponseDetailPengajuan;
import id.ac.its.attendance.Response.OKPengajuan.OKResponse;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPengajuanActivity  extends AppCompatActivity {
    private int id;
    private Button konfirmasi,tolakKonfirmasi;
    private String confirm;
    private TokenManager tokenManager;
    private RecyclerView recyclerView;
    private TextView judul,tanggal,total,nama_pengaju,jabatan,deskripsi,status_bend, status_kepsek, tolak;

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
        tolakKonfirmasi = findViewById(R.id.tolakKonfirmasi);
        judul = findViewById(R.id.judul_pengajuan);
        tanggal = findViewById(R.id.tanggal);
        total = findViewById(R.id.total_pengajuan);
        nama_pengaju = findViewById(R.id.nama_pengaju);
//        jabatan = findViewById(R.id.jabatan_pengaju);
        deskripsi = findViewById(R.id.deskripsi);
        status_bend = findViewById(R.id.status_pengajuan_bend);
        status_kepsek = findViewById(R.id.status_pengajuan);

//        if (confirm.equals("1")) {
//            konfirmasi.setVisibility(View.GONE);
//        }

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
//                    jabatan.setText(response.body().getDetailpeng().get(0).get);
                    deskripsi.setText(response.body().getDetailpeng().get(0).getDeskripsiPengajuan());
                    status_bend.setText(response.body().getDetailpeng().get(0).getStatusPengajuan().equals("3")?"Ditolak":
                            (response.body().getDetailpeng().get(0).getStatusPengajuan().equals("1")) ? "Sudah Dikonfirmasi" :
                                    (response.body().getDetailpeng().get(0).getStatusPengajuan().equals("2")) ? "Sudah Dikonfirmasi" : "Belum Dikonfirmasi");

                    status_kepsek.setText(response.body().getDetailpeng().get(0).getStatusPengajuan().equals("3")?"Ditolak":
                            (response.body().getDetailpeng().get(0).getStatusPengajuan().equals("1")) ? "Belum Dikonfirmasi" :
                                    (response.body().getDetailpeng().get(0).getStatusPengajuan().equals("2")) ? "Sudah Dikonfirmasi" : "Belum Dikonfirmasi");


                    if(response.body().getDetailpeng().get(0).getMsg().equals("0")){
                        konfirmasi.setVisibility(View.GONE);
                        tolakKonfirmasi.setVisibility(View.GONE);
                    }
//                    else if(response.body().getDetailpeng().get(0).getMsg().equals("1")){
//
//                    }
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
                Intent intent = new Intent(DetailPengajuanActivity.this, PredictSignatureActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });

        tolakKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                confirm.equals(3);

                final SweetAlertDialog pDialog = new SweetAlertDialog(DetailPengajuanActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
                ApiClientAttendance api = ServerAttendance.createServiceWithAuth(ApiClientAttendance.class,tokenManager);
                Call<OKResponse> call = api.postdetailpengajuan(id,"3");
                call.enqueue(new Callback<OKResponse>() {
                    @Override
                    public void onResponse(Call<OKResponse> call, Response<OKResponse> response) {
                        if(response.isSuccessful()){
                            pDialog.dismiss();
                            new SweetAlertDialog(DetailPengajuanActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Hasil")
                                    .setContentText("Berhasil Ditolak")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismissWithAnimation();
                                        }
                                    }).show();

                            Intent intent = new Intent(DetailPengajuanActivity.this, TolakActivity.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
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
