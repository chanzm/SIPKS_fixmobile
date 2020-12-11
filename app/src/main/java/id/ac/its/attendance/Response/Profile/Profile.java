package id.ac.its.attendance.Response.Profile;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Profile{

	@SerializedName("bank_norek_sekolah")
	private String bankNorekSekolah;

	@SerializedName("NIP_akun")
	private String nIPAkun;

	@SerializedName("jenjang_sekolah")
	private String jenjangSekolah;

	@SerializedName("create_time")
	private String createTime;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("alamat_sekolah")
	private String alamatSekolah;

	@SerializedName("username_akun")
	private String usernameAkun;

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

	@SerializedName("email_sekolah")
	private String emailSekolah;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("no_telp_sekolah")
	private String noTelpSekolah;

	@SerializedName("name")
	private String name;

	@SerializedName("nama_sekolah")
	private String namaSekolah;

	@SerializedName("nama_norek_sekolah")
	private String namaNorekSekolah;

	@SerializedName("no_rek_sekolah")
	private String noRekSekolah;

	@SerializedName("id")
	private int id;

	@SerializedName("remember_token")
	private Object rememberToken;

	@SerializedName("email")
	private String email;

	public String getBankNorekSekolah(){
		return bankNorekSekolah;
	}

	public String getNIPAkun(){
		return nIPAkun;
	}

	public String getJenjangSekolah(){
		return jenjangSekolah;
	}

	public String getCreateTime(){
		return createTime;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public String getAlamatSekolah(){
		return alamatSekolah;
	}

	public String getUsernameAkun(){
		return usernameAkun;
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

	public String getEmailSekolah(){
		return emailSekolah;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public String getNoTelpSekolah(){
		return noTelpSekolah;
	}

	public String getName(){
		return name;
	}

	public String getNamaSekolah(){
		return namaSekolah;
	}

	public String getNamaNorekSekolah(){
		return namaNorekSekolah;
	}

	public String getNoRekSekolah(){
		return noRekSekolah;
	}

	public int getId(){
		return id;
	}

	public Object getRememberToken(){
		return rememberToken;
	}

	public String getEmail(){
		return email;
	}
}