package id.ac.its.attendance.Response.Pengajuan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePengajuan {

    @SerializedName("pengajuan")
    private List<Pengajuan> pengajuan;

    public List<Pengajuan> getPengajuan(){
        return pengajuan;
    }
}
