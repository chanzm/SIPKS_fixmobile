package id.ac.its.attendance.Response.Profile;

import com.google.gson.annotations.SerializedName;

public class Profile{

	@SerializedName("profile")
	private Profile profile;

	@SerializedName("alamat_akun")
	private String alamatAkun;

	@SerializedName("id_sekolah")
	private int idSekolah;

	@SerializedName("role_akun")
	private String roleAkun;

	@SerializedName("NIP_akun")
	private String nIPAkun;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("username_akun")
	private String usernameAkun;

	@SerializedName("email")
	private String email;

	@SerializedName("no_telp_akun")
	private String noTelpAkun;

	public Profile getProfile(){
		return profile;
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

	public String getNIPAkun(){
		return nIPAkun;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public String getName(){
		return name;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public int getId(){
		return id;
	}

	public String getUsernameAkun(){
		return usernameAkun;
	}

	public String getEmail(){
		return email;
	}

	public String getNoTelpAkun(){
		return noTelpAkun;
	}
}