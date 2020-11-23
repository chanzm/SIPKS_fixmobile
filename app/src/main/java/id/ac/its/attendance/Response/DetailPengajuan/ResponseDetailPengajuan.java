package id.ac.its.attendance.Response.DetailPengajuan;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailPengajuan {

    @SerializedName("detailpengajuan")
    private DetailPengajuan detailpengajuan;

    public DetailPengajuan getDetailPengajuan(){
        return detailpengajuan;
    }
}

