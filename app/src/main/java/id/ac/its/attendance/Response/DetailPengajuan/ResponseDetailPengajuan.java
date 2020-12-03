package id.ac.its.attendance.Response.DetailPengajuan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDetailPengajuan {

    @SerializedName("detailpeng")
    private List<DetailPengajuan> detailpeng;

    public List<DetailPengajuan> getDetailpeng(){
        return detailpeng;
    }
}

