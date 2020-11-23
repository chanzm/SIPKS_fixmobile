package id.ac.its.attendance.Response.Pengajuan;

import com.google.gson.annotations.SerializedName;

public class ResponsePengajuan {

    @SerializedName("pengajuan")
    private Pengajuan pengajuan;

    public Pengajuan getPengajuan(){
        return pengajuan;
    }
}
