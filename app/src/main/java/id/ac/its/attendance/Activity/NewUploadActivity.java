package id.ac.its.attendance.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.Attendance.ResponseApi;
import id.ac.its.attendance.Response.OKPengajuan.OKResponse;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import id.ac.its.attendance.Utility.Constans;
import id.ac.its.attendance.Utility.PermissionsDelegate;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewUploadActivity extends AppCompatActivity {
    private static final String LOGGING_TAG = "New Upload Activity";
    private static final String TAG = "Upload Activity";

    private final PermissionsDelegate permissionsDelegate = new PermissionsDelegate(this);
    private boolean hasCameraPermission;
    private CameraView cameraView;
    private Button capture,okcam;
    private TextView txtDetected;
    private TokenManager tokenManager;

    private int STATE_NOW = 0;
    private int STATE_BLINK_PERTAMA = 1;
    private int STATE_BLINK_KEDUA = 2;

    private float LEFT_EYE_CLOSE_PROB = 0.15F;
    private float RIGHT_EYE_CLOSE_PROB = 0.15F;
    private float SMILING_PROB = 0.80F;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        id = getIntent().getIntExtra("id",0);

        cameraView = findViewById(R.id.camera_view);
        capture = findViewById(R.id.btn_capture);
        hasCameraPermission = permissionsDelegate.hasCameraPermission();

//        txtDetected = findViewById(R.id.txtDetected);
        okcam = findViewById(R.id.okcam);

        cameraView.setFacing(Facing.FRONT);
        cameraView.setLifecycleOwner(this);
        cameraView.addCameraListener(new CamListener());

        okcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.takePicture();
            }
        });

        if (hasCameraPermission) {
            cameraView.setVisibility(View.VISIBLE);
        } else {
            permissionsDelegate.requestCameraPermission();
        }

    }


    class CamListener extends CameraListener{

        @Override
        public void onPictureTaken(@NonNull PictureResult result) {
            super.onPictureTaken(result);

            final SweetAlertDialog pDialog = new SweetAlertDialog(NewUploadActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Loading");
            pDialog.setCancelable(false);
            pDialog.show();

            result.toBitmap(96, 96, new BitmapCallback() {
                @Override
                public void onBitmapReady(@Nullable Bitmap bitmap) {
                    File file = new File(getApplicationContext().getCacheDir(), "ttd");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                    byte[] bitmap_data = byteArrayOutputStream.toByteArray();
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.write(bitmap_data);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);

                    tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
                    ApiClientAttendance api = ServerAttendance.createServiceWithAuth(ApiClientAttendance.class,tokenManager);
                    Call<ResponseApi> call = api.sendface(body);
                    call.enqueue(new Callback<ResponseApi>() {
                        @Override
                        public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                            if(response.isSuccessful()){
                                //intent berhasil
                        pDialog.dismiss();
                        new SweetAlertDialog(NewUploadActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Hasil")
                                .setContentText(response.body().getMsg())
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                    }
                                }).show();

                                Log.w("lalalayeye",response.body().getMsg());

                                        if(response.body().getMsg() != null && response.body().getMsg().equals("Upload Wajah Selesai, Data Tersimpan")){
                                            Intent intent = new Intent(NewUploadActivity.this, TrainActivity.class);
                                            startActivity(intent);
                                        }
//                                        Intent intent = new Intent(NewUploadActivity.this, TrainActivity.class);
//                                        intent.putExtra("id",id);
//                                        startActivity(intent);


                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseApi> call, Throwable t) {

                        }
                    });
                }
            });






        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, permissions, grantResults)) {
            hasCameraPermission = true;
            cameraView.setVisibility(View.VISIBLE);
        }
    }


}
