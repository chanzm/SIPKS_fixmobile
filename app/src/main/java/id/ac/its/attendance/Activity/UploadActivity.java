package id.ac.its.attendance.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.Attendance.ResponseApi;
import id.ac.its.attendance.Response.UploadFoto.UploadWajah;
import id.ac.its.attendance.Response.UploadFoto.UploadWajahResponse;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import id.ac.its.attendance.Utility.Constans;
import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Base64;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadActivity extends AppCompatActivity {
    private static final int KODE_KAMERA = 555;
    private CameraView camerad;
    ImageView imgFoto;
    private CameraKitEventListener cameradListener;
    private Button btnAmbilKamera, btn,okcam;
    private TokenManager tokenManager;
    private ApiClientAttendance mAPIService;
    String namaFile, base64Encode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        okcam = findViewById(R.id.okcam);
        btnAmbilKamera = (Button) findViewById(R.id.tangkap_wajah);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Upload Foto");


        mAPIService = ServerAttendance.createService(ApiClientAttendance.class);

//        btnAmbilKamera.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onClick(View view) {
//                Intent intentKamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intentKamera, KODE_KAMERA);
//            }
//        });

        btnAmbilKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        Intent intent = new Intent(UploadActivity.this, BerhasilActivity.class);
//                        startActivity(intent);
//                uploadFoto(base64Encode);

            }
        });


        cameradListener = new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }


            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                byte[] picture = cameraKitImage.getJpeg(); //capture gambar
                Bitmap result = BitmapFactory.decodeByteArray(picture, 0, picture.length); //mengatur size
                result = Bitmap.createScaledBitmap(result, 96,96, true); //


//                String myBase64Image = Constans.encodeToBase64(result, Bitmap.CompressFormat.JPEG, 100);
//                ApiClientAttendance api = ServerAttendance.builder(UploadActivity.this).create(ApiClientAttendance.class);
//                Call<UploadWajahResponse> fill = api.kirimgambar(Constans.getNip(),"data:image/jpeg;base64,"+myBase64Image,"Bearer "+Constans.getToken());


                String myBase64Image = Constans.encodeToBase64(result, Bitmap.CompressFormat.JPEG, 100);
                tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
                ApiClientAttendance api = ServerAttendance.createServiceWithAuth(ApiClientAttendance.class,tokenManager);
//                Call<UploadWajahResponse> upload = api.kirim(Constans.getNip(), Constans.getPassword(), "data:image/jpeg;base64,"+myBase64Image);
//                Call<UploadWajahResponse> upload = api.kirim(UploadWajah., "data:image/jpeg;base64,"+myBase64Image);

                final SweetAlertDialog pDialog = new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Loading");
                pDialog.show();



//                upload.enqueue(new Callback<ResponseApi>() {
//                    @Override
//                    public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
//                        if (response.code() == 200 && !response.body().getMsg().equals("token_absent"))
//                        {
//                            pDialog.dismiss();
//                            new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.SUCCESS_TYPE)
//                                    .setTitleText("Hasil")
//                                    .setContentText(response.body().getMsg())
//                                    .setConfirmText("OK")
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sDialog) {
//                                            sDialog.dismiss();
//                                        }
//                                    }).show();
//                        }
//                        else
//                        {
//                            pDialog.dismiss();
//                            new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.WARNING_TYPE)
//                                    .setTitleText("Error")
//                                    .setContentText("Terjadi kesalahan, mohon ulangi lagi.")
//                                    .setConfirmText("OK")
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sDialog) {
//                                            sDialog.dismiss();
//                                        }
//                                    }).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseApi> call, Throwable t) {
//                        pDialog.dismiss();
//                        new SweetAlertDialog(UploadActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                .setTitleText("Hasil")
//                                .setContentText("Internet Anda Bermasalah")
//                                .setConfirmText("OK")
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sDialog) {
//                                        sDialog.dismissWithAnimation();
//                                    }
//                                }).show();
//                    }
//                });
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {
                Intent intent = new Intent(UploadActivity.this, BerhasilActivity.class);
                startActivity(intent);
            }
        };

        camerad = (CameraView) findViewById(R.id.camera);
        camerad.addCameraKitListener(cameradListener);

        btn = (Button) findViewById(R.id.btn_capture);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camerad.captureImage();
                Intent intent = new Intent(UploadActivity.this, BerhasilActivity.class);
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("VALUE RESULT CODE", String.valueOf(resultCode));
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case (KODE_KAMERA):
                    try {
//                        KOK GA MAU MASUK SINI OYYY
                        Log.d("TESPROSES", "TESPROSES");
                        prosesKamera(data);
                        Log.d("TESTES2", "TESTES2");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void prosesKamera(Intent kamera) throws IOException {
        Bitmap bm;
        bm = (Bitmap) kamera.getExtras().get("data");
        imgFoto.setImageBitmap(bm);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        Date tanggalSekarang = new Date();
        String formatNama = new SimpleDateFormat("yyyyMMdd_HHmmss").format(tanggalSekarang);
        namaFile = formatNama + ".png";
        File output = new File(dir, namaFile);
        FileOutputStream fo = new FileOutputStream(output);
        fo.write(byteArray);
        fo.flush();
        fo.close();
//        ENCODE BYTE ARRAY
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            base64Encode = Base64.getEncoder().encodeToString(byteArray);
            Log.d("ISI BASE64", base64Encode);
        }
        Toast.makeText(this, "Data Telah Terload ke ImageView", Toast.LENGTH_LONG).show();
    }


//    private void uploadFoto(String base64) {
//        if(base64 == null) {
////            Toast.makeText(getBaseContext(), "ADA ISINYA", Toast.LENGTH_LONG).show();
//            Toast.makeText(getBaseContext(), "Belum Ada Foto", Toast.LENGTH_LONG).show();
//        }
//        else {
//            Log.d("CEKISI", base64);
////            String tes = "nyobak";
//            //            Do Retrofit HERE
//            mAPIService.kirim(base64).enqueue(new Callback<UploadWajahResponse>() {
//                @Override
//                public void onResponse(Call<UploadWajahResponse> call, Response<UploadWajahResponse> response) {
//                    if(response.isSuccessful()) {
//                        Toast.makeText(getBaseContext(), "Foto Berhasil Dikirim ke API " + response.body().toString(), Toast.LENGTH_LONG).show();
//                        Log.i("POST BERHASIL", "Foto Berhasil Terupload melalui API " + response.body().toString());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<UploadWajahResponse> call, Throwable t) {
//                    Log.d("Call", String.valueOf(call));
//                    Log.d("Throwable", String.valueOf(t));
//                    if (t instanceof SocketTimeoutException)
//                    {
//                        // "Connection Timeout";
//                        Log.d("SocketTimeoutException", "SocketTimeoutException Error");
//                    }
//                    else if (t instanceof IOException)
//                    {
//                        // "Timeout";
//                        Log.d("TIMEOUT", "IOException Error");
//                    }
//                    else
//                    {
//                        //Call was cancelled by user
//                        if(call.isCanceled())
//                        {
//                            Log.d("CANCELED", "Call was cancelled forcefully");
//                        }
//                        else
//                        {
//                            //Generic error handling
//                            Log.d("GENERIC" ,"Network Error :: " + t.getLocalizedMessage());
//                        }
//                    }
//                    Toast.makeText(getBaseContext(), "Gagal Mengirim Foto", Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        camerad.start();
    }

    @Override
    protected void onPause() {
        camerad.stop();
        super.onPause();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
