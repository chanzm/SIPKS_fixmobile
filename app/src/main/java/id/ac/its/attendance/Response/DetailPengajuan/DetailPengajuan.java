package id.ac.its.attendance.Response.DetailPengajuan;

import com.google.gson.annotations.SerializedName;

public class DetailPengajuan{

	@SerializedName("jumlah_pengajuan")
	private int jumlahPengajuan;

	@SerializedName("nama_detail")
	private String namaDetail;

	@SerializedName("harga_satuan_detail")
	private int hargaSatuanDetail;

	@SerializedName("create_time")
	private String createTime;

	@SerializedName("jabatan_pembuat_pengajuan")
	private String jabatanPembuatPengajuan;

	@SerializedName("judul_pengajuan")
	private String judulPengajuan;

	@SerializedName("jumlah_detail")
	private int jumlahDetail;

	@SerializedName("id_sekolah")
	private int idSekolah;

	@SerializedName("total_harga_detail")
	private int totalHargaDetail;

	@SerializedName("id_pengajuan")
	private int idPengajuan;

	@SerializedName("id_detail")
	private int idDetail;

	@SerializedName("status_pengajuan")
	private String statusPengajuan;

	@SerializedName("nama_pembuat_pengajuan")
	private String namaPembuatPengajuan;

	@SerializedName("id_akun")
	private int idAkun;

	@SerializedName("deskripsi_pengajuan")
	private String deskripsiPengajuan;

	public int getJumlahPengajuan(){
		return jumlahPengajuan;
	}

	public String getNamaDetail(){
		return namaDetail;
	}

	public int getHargaSatuanDetail(){
		return hargaSatuanDetail;
	}

	public String getCreateTime(){
		return createTime;
	}

	public String getJabatanPembuatPengajuan(){
		return jabatanPembuatPengajuan;
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

	public int getTotalHargaDetail(){
		return totalHargaDetail;
	}

	public int getIdPengajuan(){
		return idPengajuan;
	}

	public int getIdDetail(){
		return idDetail;
	}

	public String getStatusPengajuan(){
		return statusPengajuan;
	}

	public String getNamaPembuatPengajuan(){
		return namaPembuatPengajuan;
	}

	public int getIdAkun(){
		return idAkun;
	}

	public String getDeskripsiPengajuan(){
		return deskripsiPengajuan;
	}
}