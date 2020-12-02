package id.ac.its.attendance.Response.Pengajuan;

import com.google.gson.annotations.SerializedName;

public class Pengajuan{

	@SerializedName("jumlah_pengajuan")
	private int jumlahPengajuan;

	@SerializedName("NIP_akun")
	private String nIPAkun;

	@SerializedName("create_time")
	private String createTime;

	@SerializedName("jabatan_pembuat_pengajuan")
	private String jabatanPembuatPengajuan;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("username_akun")
	private String usernameAkun;

	@SerializedName("judul_pengajuan")
	private String judulPengajuan;

	@SerializedName("no_telp_akun")
	private String noTelpAkun;

	@SerializedName("alamat_akun")
	private String alamatAkun;

	@SerializedName("id_sekolah")
	private int idSekolah;

	@SerializedName("role_akun")
	private String roleAkun;

	@SerializedName("password")
	private String password;

	@SerializedName("id_pengajuan")
	private int idPengajuan;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("status_pengajuan")
	private String statusPengajuan;

	@SerializedName("nama_pembuat_pengajuan")
	private String namaPembuatPengajuan;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("id_akun")
	private int idAkun;

	@SerializedName("remember_token")
	private Object rememberToken;

	@SerializedName("deskripsi_pengajuan")
	private String deskripsiPengajuan;

	@SerializedName("email")
	private String email;

	public int getJumlahPengajuan(){
		return jumlahPengajuan;
	}

	public String getNIPAkun(){
		return nIPAkun;
	}

	public String getCreateTime(){
		return createTime;
	}

	public String getJabatanPembuatPengajuan(){
		return jabatanPembuatPengajuan;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public String getUsernameAkun(){
		return usernameAkun;
	}

	public String getJudulPengajuan(){
		return judulPengajuan;
	}

	public String getNoTelpAkun(){
		return noTelpAkun;
	}

	public String getAlamatAkun(){
		return alamatAkun;
	}

	public int getIdSekolah(){
		return idSekolah;
	}

	public String getRoleAkun(){
		return roleAkun;
	}

	public String getPassword(){
		return password;
	}

	public int getIdPengajuan(){
		return idPengajuan;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public String getStatusPengajuan(){
		return statusPengajuan;
	}

	public String getNamaPembuatPengajuan(){
		return namaPembuatPengajuan;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public int getIdAkun(){
		return idAkun;
	}

	public Object getRememberToken(){
		return rememberToken;
	}

	public String getDeskripsiPengajuan(){
		return deskripsiPengajuan;
	}

	public String getEmail(){
		return email;
	}
}