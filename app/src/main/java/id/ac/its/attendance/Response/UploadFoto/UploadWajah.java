package id.ac.its.attendance.Response.UploadFoto;

import com.google.gson.annotations.SerializedName;

public class UploadWajah{

	@SerializedName("id_wajah")
	private int idWajah;

	@SerializedName("sample_wajah")
	private String sampleWajah;

	@SerializedName("name")
	private String name;

	@SerializedName("id_akun")
	private int idAkun;

	public int getIdWajah(){
		return idWajah;
	}

	public String getSampleWajah(){
		return sampleWajah;
	}

	public String getName(){
		return name;
	}

	public int getIdAkun(){
		return idAkun;
	}
}