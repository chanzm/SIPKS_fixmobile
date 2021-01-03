package id.ac.its.attendance.Retrofit.ServerAttendance;

import java.util.List;

import id.ac.its.attendance.Response.Attendance.ResponseAgendaAttendance;
import id.ac.its.attendance.Response.Attendance.ResponseApi;

import id.ac.its.attendance.Response.DetailPengajuan.ResponseDetailPengajuan;
import id.ac.its.attendance.Response.OKPengajuan.OKResponse;
import id.ac.its.attendance.Response.Pengajuan.Pengajuan;
import id.ac.its.attendance.Response.Pengajuan.ResponsePengajuan;
import id.ac.its.attendance.Response.Profile.ProfileResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClientAttendance {
    @FormUrlEncoded
    @POST("/sendImg/")
    Call<ResponseApi> kirim(@Field("idUser") String nrp,
                            @Field("password") String password,
                            @Field("image") String image);

    @Multipart
    @POST("sendface")
    Call<ResponseApi> sendface(@Part MultipartBody.Part body);

    @Multipart
    @POST("sendsignature")
    Call<ResponseApi> sendSignature(@Part MultipartBody.Part body);

    @GET("trainface")
    Call<ResponseApi> trainface();

    @GET("trainsignature")
    Call<ResponseApi> trainTTD();

    @Multipart
    @POST("predictsignature")
    Call<ResponseApi> predictTTD(@Part MultipartBody.Part body);

    @FormUrlEncoded
    @POST("/doPredict/")
    Call<ResponseApi> predict(@Field("idUser") String nrp,
                              @Field("password") String password,
                              @Field("image") String image);


    @FormUrlEncoded
    @POST("/signin/")
    Call<ResponseApi> signin(@Field("idUser") String user,
                             @Field("password") String password,
                             @Field("Lat") String Lat,
                             @Field("Lon") String Lon,
                             @Field("idAgenda") String idAgenda,
                             @Field("image") String image);
    @FormUrlEncoded
    @POST("/signin_TTD/")
    Call<ResponseApi> signinTTD(@Field("idUser") String user,
                                @Field("password") String password,
                                @Field("Lat") String Lat,
                                @Field("Lon") String Lon,
                                @Field("idAgenda") String idAgenda,
                                @Field("image") String image);
//
//    @FormUrlEncoded
//    @POST("/api/v1/login")
//    Call<ResponseLoginAttendance> login(@Field("nip") String nip,
//                                        @Field("password") String password);

    @GET("/api/v1/agenda")
    Call<ResponseAgendaAttendance> getAgenda();

    @FormUrlEncoded
    @POST("/api/v1/kirimgambar")
    Call<ResponseApi> kirimgambar(@Field("nip") String nip,
                                  @Field("images") String image,
                                  @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("login")
    Call<AccessToken> login(@Field("email") String nip,
                                        @Field("password") String password);

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("profile")
    Call<ProfileResponse> getprofile();

    @GET("pengajuan")
    Call<ResponsePengajuan> getpengajuan(@Query("status") int status);

    @GET("pengajuan/detail/{id}")
    Call<ResponseDetailPengajuan> getdetailpengajuan(@Path("id") int id);

    @POST("pengajuan/detail")
    @FormUrlEncoded
    Call<OKResponse> postdetailpengajuan(@Field("id") int id, @Field("status") String status);
}
