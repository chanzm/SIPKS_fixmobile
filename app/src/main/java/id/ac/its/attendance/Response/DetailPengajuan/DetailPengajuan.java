package id.ac.its.attendance.Response.DetailPengajuan;


import com.google.gson.annotations.SerializedName;

public class DetailPengajuan{

    @SerializedName("jumlah_detail")
    private int jumlahDetail;

    @SerializedName("nama_detail")
    private String namaDetail;

    @SerializedName("total_harga_detail")
    private int totalHargaDetail;

    @SerializedName("id_pengajuan")
    private int idPengajuan;

    @SerializedName("harga_satuan_detail")
    private int hargaSatuanDetail;

    @SerializedName("id_detail")
    private int idDetail;

    @SerializedName("create_time")
    private String createTime;

    public int getJumlahDetail(){
        return jumlahDetail;
    }

    public String getNamaDetail(){
        return namaDetail;
    }

    public int getTotalHargaDetail(){
        return totalHargaDetail;
    }

    public int getIdPengajuan(){
        return idPengajuan;
    }

    public int getHargaSatuanDetail(){
        return hargaSatuanDetail;
    }

    public int getIdDetail(){
        return idDetail;
    }

    public String getCreateTime(){
        return createTime;
    }
}
