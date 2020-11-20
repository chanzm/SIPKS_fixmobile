package id.ac.its.attendance.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import id.ac.its.attendance.R;
import id.ac.its.attendance.Response.Attendance.ResponseLoginAttendance;
import id.ac.its.attendance.Response.Login.ResponseLogin;
import id.ac.its.attendance.Retrofit.ServerAttendance.AccessToken;
import id.ac.its.attendance.Retrofit.ServerAttendance.ApiClientAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.ServerAttendance;
import id.ac.its.attendance.Retrofit.ServerAttendance.TokenManager;
import id.ac.its.attendance.Retrofit.ServerSIPKS.ApiClientSIPKS;
import id.ac.its.attendance.Retrofit.ServerSIPKS.ServerSIPKS;
import id.ac.its.attendance.Rule.Bendahara.BendaharaActivity;
import id.ac.its.attendance.Rule.Kepala.Login.PosisiActivity;
import id.ac.its.attendance.Utility.Constans;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView title;
    private CardView cardView,cardView1,cardView2;
    private EditText email,password;
    private Intent intent;
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        title = (TextView)findViewById(R.id.tv_title);
        cardView1 = (CardView)findViewById(R.id.btn_login);
        cardView1.setBackgroundResource(R.drawable.boder_cardview_blue);
        cardView = findViewById(R.id.card_password);
        cardView.setBackgroundResource(R.drawable.boder_cardview_blue);
        cardView2 = findViewById(R.id.btn_register);
        cardView2.setOnClickListener(action);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/LuckiestGuy.ttf");
        title.setTypeface(custom_font);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
    }

    protected void login() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.show();
//        Constans.setNip("05111740000015");
//        Constans.setNama("kiki");
//        Constans.setPassword("qwe123");
//        Constans.setToken("12345678");

        ApiClientAttendance api = ServerAttendance.createService(ApiClientAttendance.class);
        Call<AccessToken> login = api.login(email.getText().toString().trim(), password.getText().toString().trim());
        login.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                pDialog.dismiss();
                if (response.code() == 200) {
                    tokenManager.saveToken(response.body());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Login Gagal")
                            .setContentText("Password Atau Username Anda Salah")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                }
                            }).show();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
//                System.out.print( "=====" +call.toString());
//                System.out.print( "=====" +t.toString());
                pDialog.dismiss();
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Internet")
                        .setContentText("Internet Anda bermasalah")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        }).show();
            }
        });
    }

    View.OnClickListener action = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btn_register:
//                    Log.d("btn","mulai klik");
                    login();

                    final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading");
                    pDialog.show();
                    ApiClientSIPKS api = ServerSIPKS.builder(LoginActivity.this).create(ApiClientSIPKS.class);
                    Call<ResponseLogin> login = api.login(email.getText().toString().trim(),password.getText().toString().trim());
                    login.enqueue(new Callback<ResponseLogin>() {
                        @Override
                        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                            pDialog.dismiss();
                            if(response.code()==200)
                            {
//                                Log.d("btn","ini sukses");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {

                                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Login Gagal")
                                        .setContentText("Password Atau Username Anda Salah")
                                        .setConfirmText("OK")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                sDialog.dismiss();
                                            }
                                        }).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseLogin> call, Throwable t) {
                            System.out.print( "=====" +call.toString());
                            t.toString();
                            pDialog.dismiss();
                            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Internet")
                                    .setContentText("Internet Anda bermasalah")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            sDialog.dismiss();
                                        }
                                    }).show();
                            Log.d("btn","ini gagal");
                        }
                    });
                    break;

            }
        }
    };


}
