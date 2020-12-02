package id.ac.its.attendance.Retrofit.ServerSIPKS;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerSIPKS {
    private static Retrofit retrofit = null;
    public final static String BASE_URL = "http://192.168.1.8/sipks/public/api/";
//    private final static String API_BASE_URL = BASE_URL+"api/v1/";

    public static Retrofit builder(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(SelfSigningClientBuilder.createClient(context))
                    .build();
        }
        return retrofit;
    }


}
