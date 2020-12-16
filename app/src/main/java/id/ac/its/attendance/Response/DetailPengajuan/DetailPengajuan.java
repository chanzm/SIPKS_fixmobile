package id.ac.its.attendance.Response.DetailPengajuan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailPengajuan{

	@SerializedName("jumlah_pengajuan")
	private int jumlahPengajuan;

	@SerializedName("nama_detail")
	private String namaDetail;

	@SerializedName("create_time")
	private String createTime;

	@SerializedName("judul_pengajuan")
	private String judulPengajuan;

	@SerializedName("jumlah_detail")
	private int jumlahDetail;

	@SerializedName("id_sekolah")
	private int idSekolah;

	@SerializedName("id_pengajuan")
	private int idPengajuan;

	@SerializedName("id_detail")
	private int idDetail;

	@SerializedName("harga_satuan")
	private int hargaSatuan;

	@SerializedName("satuan")
	private String satuan;

	@SerializedName("status_pengajuan")
	private String statusPengajuan;

	@SerializedName("nama_pembuat_pengajuan")
	private String namaPembuatPengajuan;

	@SerializedName("sub_total")
	private int subTotal;

	@SerializedName("create_time_pengajuan")
	private String createTimePengajuan;

	@SerializedName("id_akun")
	private int idAkun;

	@SerializedName("deskripsi_pengajuan")
	private String deskripsiPengajuan;

	@SerializedName("id_mapping_pengajuan_detail")
	private int idMappingPengajuanDetail;

	public int getJumlahPengajuan(){
		return jumlahPengajuan;
	}

	public String getNamaDetail(){
		return namaDetail;
	}

	public String getCreateTime(){
		return createTime;
	}

	public String getJudulPengajuan(){
		return judulPengajuan;
	}

	public int getJumlahDetail(){
		return jumlahDetail;
	}

	public int getIdSekolah(){
		return idSekolah;
	}

	public int getIdPengajuan(){
		return idPengajuan;
	}

	public int getIdDetail(){
		return idDetail;
	}

	public int getHargaSatuan(){
		return hargaSatuan;
	}

	public String getSatuan(){
		return satuan;
	}

	public String getStatusPengajuan(){
		return statusPengajuan;
	}

	public String getNamaPembuatPengajuan(){
		return namaPembuatPengajuan;
	}

	public int getSubTotal(){
		return subTotal;
	}

	public String getCreateTimePengajuan(){
		return createTimePengajuan;
	}

	public int getIdAkun(){
		return idAkun;
	}

	public String getDeskripsiPengajuan(){
		return deskripsiPengajuan;
	}

	public int getIdMappingPengajuanDetail(){
		return idMappingPengajuanDetail;
	}
}